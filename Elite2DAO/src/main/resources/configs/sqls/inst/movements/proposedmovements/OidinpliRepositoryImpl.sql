OIDINPLI_FIND_RGLOC {
	 	SELECT DISTINCT AGY.AGY_LOC_ID CODE, DESCRIPTION  FROM AGENCY_LOCATIONS AGY , CASELOAD_AGENCY_LOCATIONS CSL WHERE CSL.CASELOAD_ID = :caseLoadId AND CSL.AGY_LOC_ID = AGY.AGY_LOC_ID AND AGY.DEACTIVATION_DATE IS NULL AND CSL.AGY_LOC_ID NOT IN ('OUT' ,'TRN' )  ORDER BY 1 	
}
OIDINPLI_LOC_FROM_RECORDGROUP {
SELECT living_unit_code , to_char(living_unit_id) code, description FROM living_units WHERE  AGY_LOC_ID =:agyLocId and active_flag = 'Y' AND PARENT_LIVING_UNIT_ID IS NULL ORDER BY LIVING_UNIT_CODE
}

OIDINPLI_LOC_TO_RECORDGROUP {
SELECT living_unit_code , to_char(living_unit_id) code, description FROM living_units WHERE  AGY_LOC_ID =:agyLocId and active_flag = 'Y' AND PARENT_LIVING_UNIT_ID IS NULL ORDER BY LIVING_UNIT_CODE
}
OIDINPLI_FIND_RGMOVEREASON {
select
	internal_schedule_rsn_code code,
	description
from
	internal_schedule_reasons
where
	internal_schedule_type = :move_type
	and (active_flag = 'Y'
		and expiry_date is null)
order by
	1
}

OIDINPLI_FIND_RGMOVETYPE {
 	SELECT DISTINCT mov.internal_schedule_type code, refc.description, refc.list_seq FROM internal_schedule_reasons mov, reference_codes refc WHERE mov.internal_schedule_type = refc.code AND domain = 'INT_SCH_TYPE' AND ( (refc.active_flag = 'Y' AND refc.expired_date IS NULL) )ORDER BY refc.list_seq
}

OIDINPLI_EXTRMOVE_FIND_V_HOUSING_MOVES {
	select off_name	,offender_id_display,Offender_id,root_offender_id,offender_book_id,curr_agy_id,living_unit_id,location_seq,detail_seq,
from_living_unit_id	,to_living_unit_id,movement_type	,movement_reason,status_code,txn_status ,omkhead_get_alerts ( offender_book_id ) alertCode,
(select description from refereNce_codes where domain = 'SEX' and CODE=sex_code) as sex_code,
(select description from refereNce_codes where domain = 'ETHNICITY' and CODE=race_code) as ethnicity
, (select description from refereNce_codes where domain = 'IMP_STS' and CODE= (oms_miscellaneous_get_imprisonment_status (offender_book_id))) as impStatus
, tag_transport_if_sanction_exists (offender_book_id ) sancCode
,non_association_get_stg_affiliation (offender_book_id) affiliation
,(select comment_text from offender_proposed_intlocs where offender_book_id = v_housing_moves.offender_book_id and location_seq =v_housing_moves.location_seq)
from V_HOUSING_MOVES where curr_agy_id = :agyId
}	

OIDINPLI_INMADET_UPDATE_OFFENDERPROPOSEDINTLOCS {
	update offender_proposed_intlocs set comment_text = :commentText, modify_datetime= CURRENT_TIMESTAMP, modify_user_id=:modifyUserId where offender_book_id = :offenderBookId and location_seq=:LocationSeq
}

OIDINPLI_CAPACITY_CUR{
	SELECT capacity as vCapacity
      FROM living_units
      WHERE living_unit_id = :p_living_unit_id
}
OIDINPLI_GET_MAX_BED_ASSIGN_SEQ_CUR{
	SELECT COALESCE(MAX(bed_assign_seq),0) as lvBedMaxAssignSeq
           FROM bed_assignment_histories
          WHERE offender_book_id = :offender_book_id
}

OIDINPLI_OCCUPIED_CUR{
 SELECT count(*) as vOccupied
      FROM offender_bookings
      WHERE living_unit_id = :p_living_unit_id
}

OIDINPLI_NEXT_BED_SEQ_CUR{
SELECT COALESCE(MAX(bed_assign_seq),0) + 1 as vBedSeq
           FROM bed_assignment_histories
          WHERE offender_book_id = :offender_book_id
}

OIDINPLI_OFFENDER_BOOKINGS_UPDATE{
	UPDATE offender_bookings 
				    SET living_unit_id = :livingUnitId,
				    	modify_user_id  = :modifyUserId,
	 	               modify_datetime = current_timestamp
				  WHERE offender_book_id = :offenderBookId
}

OIDINPLI_BED_ASSIGNMENT_HISTORIES_UPDATE{
	UPDATE bed_assignment_histories
	 	           SET ASSIGNMENT_END_DATE = current_timestamp,
	 	               ASSIGNMENT_END_TIME = current_timestamp,
	 	               modify_user_id = :modifyUserId,
	 	               modify_datetime  = current_timestamp
	 	         WHERE offender_book_id    = :offenderBookId
	 	           AND bed_assign_seq      = :lvBedMaxAssignSeq
}

OIDINPLI_BED_ASSIGNMENT_HISTORIES_INSERT{
	INSERT INTO BED_ASSIGNMENT_HISTORIES ( OFFENDER_BOOK_ID, BED_ASSIGN_SEQ, LIVING_UNIT_ID, ASSIGNMENT_DATE, ASSIGNMENT_TIME, CREATE_USER_ID , CREATE_DATETIME  )
				 				  VALUES ( :offenderBookId, :vBedSeq, :livingUnitId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP)	 				
}

OIDINPLI_STGNON_ASSO_CURSOR{
	select
		count(*)
from
		stg_relationships sr ,
	offender_na_details ond
where
		sr.relationship_type = 'ENEMY'
	and sr.stg_id in (
	select
			STG_ID
	from
			offender_stg_affiliations osa 
	where
			osa.offender_book_id = :offender_book_id
		and osa.active_flag = 'Y')
	and sr.active_flag = 'Y'
	and ond.offender_id =:root_offender_id
	and (ond.ns_expiry_date is null
		or date_trunc('day',
		ond.ns_expiry_date) > date_trunc('day',
		clock_timestamp()))
}
OIDINPLI_POTE_SCH_CURSOR{
	select
	count(*)
from
	v_off_sch_overview
where
	offender_book_id = :offender_book_id
}