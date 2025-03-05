
OIDHOUST_AGY_LOCID_RECORDGROUP {

SELECT cagy.agy_loc_id code, description FROM agency_locations agy, caseload_agency_locations cagy WHERE cagy.caseload_id = :caseload_id AND cagy.agy_loc_id = agy.agy_loc_id 
and agy.deactivation_date is null AND cagy.agy_loc_id NOT IN ('OUT','TRN') ORDER BY 1

}

OIDHOUST_LOC_FROM_RECORDGROUP {

SELECT living_unit_code , to_char(living_unit_id) code, description FROM living_units WHERE  AGY_LOC_ID =:agy_id  AND PARENT_LIVING_UNIT_ID IS NULL ORDER BY LIVING_UNIT_CODE

}

OIDHOUST_LOC_TO_RECORDGROUP {
SELECT living_unit_code , to_char(living_unit_id) code, description FROM living_units WHERE  AGY_LOC_ID =:agy_id  AND PARENT_LIVING_UNIT_ID IS NULL ORDER BY LIVING_UNIT_CODE
}

OIDHOUST_RG_MOVEMENT_TYPE_RECORDGROUP {
SELECT DISTINCT mov.internal_schedule_type code, refc.description, refc.list_seq  FROM internal_schedule_reasons mov, reference_codes refc 
WHERE mov.internal_schedule_type = refc.code  AND domain = 'INT_SCH_TYPE'  AND ( (refc.active_flag = 'Y' AND refc.expired_date IS NULL) 
OR 'ENTER-QUERY' = 'ENTER-QUERY') ORDER BY refc.list_seq
}

OIDHOUST_RG_MOVEMENT_REASON_RECORDGROUP {
SELECT internal_schedule_rsn_code code, description FROM internal_schedule_reasons 
WHERE internal_schedule_type = :move_type  AND ( (active_flag = 'Y' AND expiry_date IS NULL)) ORDER BY 1
}

OIDHOUST_RG_APPROVAL_STATUS_RECORDGROUP {
SELECT code, description FROM reference_codes WHERE domain = 'APP_STATUS' and
( ( active_flag = 'Y' AND expired_date IS NULL )) AND code IN ('APP','PEN','DEN') ORDER BY list_seq

}

OIDHOUST_EXTRMOVE_FIND_V_HOUSE_MOVES {
select off_name	,offender_id_display,Offender_id,root_offender_id,offender_book_id,curr_agy_id,living_unit_id,location_seq,detail_seq,
from_living_unit_id	,to_living_unit_id,movement_type	,movement_reason,status_code,txn_status ,omkhead_get_alerts ( offender_book_id ) alertCode,
(select description from refereNce_codes where domain = 'SEX' and CODE=sex_code) as sex_code,
(select description from refereNce_codes where domain = 'ETHNICITY' and CODE=race_code) as race_code
, (select description from refereNce_codes where domain = 'IMP_STS' 
and CODE =(SELECT imprisonment_status FROM offender_imprison_statuses WHERE offender_book_id = v_housing_moves.offender_book_id AND expiry_date IS NULL)) as impStatus
, case when (SELECT COUNT(*) FROM offender_oic_sanctions WHERE offender_book_id = v_housing_moves.offender_book_id AND status = 'ACTIVE') >0 then 'Y' else 'N' end sancCode
,case when (SELECT count(*)  FROM v_off_sch_overview WHERE offender_book_id = offender_book_id) >0 then 'Y' else 'N' end potSchFlag
,case  when (select count(*) from stg_relationships where relationship_type = 'ENEMY' and stg_id in (SELECT STG_ID FROM OFFENDER_STG_AFFILIATIONS WHERE OFFENDER_BOOK_ID = v_housing_moves.offender_book_id 
AND ACTIVE_FLAG = 'Y') and active_flag = 'Y') > 0 then 'Y' 
 when (select count(*)  from offender_na_details where offender_id = root_offender_id and (ns_expiry_date is null or date_trunc('day', ns_expiry_date) > date_trunc('day', clock_timestamp()))) > 0 then 'Y'
else 'N' end nonAssoFlag
,(select comment_text from offender_proposed_intlocs where offender_book_id = v_housing_moves.offender_book_id and location_seq =v_housing_moves.location_seq)
from V_HOUSING_MOVES  where curr_agy_id = :agyId
}

OIDHOUST_INMATE_DET_COMMIT_QUERY {
update offender_proposed_intlocs set comment_text = :commentText,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId where offender_book_id = :offenderBookId and location_seq=:LocationSeq
}

OIDHOUST_GET_STATE_DET_EXECUTEQUERY {
select * from offender_loc_chng_dtls DT where offender_book_id = :offender_book_id
and location_seq=:location_seq and detail_seq=( SELECT MAX(vhm.detail_seq)  FROM offender_loc_chng_dtls vhm WHERE vhm.offender_book_id = DT.offender_book_id  AND vhm.location_seq = DT.location_seq AND  VHM.txn_status = DT.txn_status );
}

OIDHOUST_CUR_INM_APP_STATUS {
 SELECT status_code FROM offender_loc_chng_dtls  WHERE offender_book_id = :offender_book_id AND location_seq= :location_seq
 AND detail_seq=(SELECT MAX(detail_seq) FROM offender_loc_chng_dtls WHERE offender_book_id = :offender_book_id
 AND location_seq= :location_seq AND txn_status != 'CREQ')

}
