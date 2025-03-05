
OIMVLIMT_FIND_RGSECLVL {
 SELECT ref_code.description, ref_code.code FROM reference_codes ref_code WHERE domain = 'SUP_LVL_TYPE' AND ( active_flag = 'Y' ) ORDER BY ref_code.list_seq, ref_code.description, ref_code.code
}
OIMVLIMT_FIND_RGCYCTYP {
SELECT   REF_CODE.DESCRIPTION ,          REF_CODE.CODE     FROM REFERENCE_CODES REF_CODE    WHERE DOMAIN = 'VIS_CYC_TYP'      AND (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' ) ORDER BY REF_CODE.LIST_SEQ , REF_CODE.DESCRIPTION ,REF_CODE.CODE
}

OIMVLIMT_FIND_RGVISTYP {
SELECT   REF_CODE.DESCRIPTION ,          REF_CODE.CODE     FROM REFERENCE_CODES REF_CODE    WHERE DOMAIN = 'VISIT_TYPE'      AND (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' ) ORDER BY REF_CODE.LIST_SEQ , REF_CODE.DESCRIPTION ,REF_CODE.CODE
 }

OIMVLIMT_FIND_RGSTRDAY {
SELECT   REF_CODE.DESCRIPTION ,          REF_CODE.CODE     FROM REFERENCE_CODES REF_CODE    WHERE DOMAIN = 'VIS_START'      AND (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' )      AND PARENT_CODE =:CYCLETYPE ORDER BY REF_CODE.LIST_SEQ , REF_CODE.DESCRIPTION ,REF_CODE.CODE
}

OIMVLIMT_FIND_RGAGYINTLOC {
SELECT  AGY_LOC.DESCRIPTION DESCRIPTION ,  AGY_LOC.AGY_LOC_ID AGY_LOC_ID , AGY_LOC.ACTIVE_FLAG   FROM AGENCY_LOCATIONS AGY_LOC  WHERE AGENCY_LOCATION_TYPE = 'INST'    AND AGY_LOC_ID IN (SELECT AGY_LOC_ID      FROM CASELOAD_AGENCY_LOCATIONS           WHERE CASELOAD_ID =(SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :p_User_Id))    AND AGY_LOC_ID NOT IN ('OUT' , 'TRN' )    AND (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION , AGY_LOC_ID 	
}

OIMVLIMT_VISCYC_FIND_VISIT_CYCLE_LIMITS {
select * from visit_cycle_limits vcl where agy_loc_id = :agylocid and visit_config_type=:visitConfigType   
}
OIMVLIMT_VISCYC_INSERT_VISIT_CYCLE_LIMITS {
insert into oms_owner.visit_cycle_limits (visit_cycle_limit_id, agy_loc_id, visit_config_type_value, cycle_type, tot_hrs, tot_visits, start_day, active_flag, expiry_date, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, visit_config_type) values(:visitCycleLimitId, :agyLocId , :visitConfigTypeValue, :cycleType , :totHrs , :totVisits , :startDay , :activeFlag , :expiryDate , current_timestamp , :createUserId , NULL , NULL, :sealFlag, :visitConfigType)
}

OIMVLIMT_VISCYC_UPDATE_VISIT_CYCLE_LIMITS {
update oms_owner.visit_cycle_limits set agy_loc_id = :agyLocId, visit_config_type_value = :visitConfigTypeValue, cycle_type =:cycleType , TOT_HRS = :totHrs , TOT_VISITS = :totVisits , START_DAY = :startDay , ACTIVE_FLAG = :activeFlag , EXPIRY_DATE = :expiryDate , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag, visit_config_type =:visitConfigType where visit_cycle_limit_id = :visitCycleLimitId
}

OIMVLIMT_VISTYP_FIND_VISIT_TYPE_LIMITS {
SELECT visit_cycle_limit_id, visit_type, max_hrs_type, max_visits_type, max_visitors_type, reinstate_flag, active_flag, expiry_date, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag FROM visit_type_limits WHERE visit_cycle_limit_id = :visitcyclelimitid ORDER BY active_flag DESC
}
OIMVLIMT_VISTYP_INSERT_VISIT_TYPE_LIMITS {
insert into VISIT_TYPE_LIMITS(VISIT_CYCLE_LIMIT_ID , VISIT_TYPE , MAX_HRS_TYPE , MAX_VISITS_TYPE , MAX_VISITORS_TYPE , REINSTATE_FLAG , ACTIVE_FLAG , EXPIRY_DATE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:visitCycleLimitId , :visitType , :maxHrsType , :maxVisitsType , :maxVisitorsType , :reinstateFlag , :activeFlag , :expiryDate , current_timestamp , :createUserId , NULL , :sealFlag )
}

OIMVLIMT_VISTYP_UPDATE_VISIT_TYPE_LIMITS {
update VISIT_TYPE_LIMITS set VISIT_CYCLE_LIMIT_ID = :visitCycleLimitId , VISIT_TYPE = :visitType , MAX_HRS_TYPE = :maxHrsType , MAX_VISITS_TYPE = :maxVisitsType , MAX_VISITORS_TYPE = :maxVisitorsType , REINSTATE_FLAG = :reinstateFlag , ACTIVE_FLAG = :activeFlag , EXPIRY_DATE = :expiryDate , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where VISIT_CYCLE_LIMIT_ID =:visitCycleLimitId and VISIT_TYPE = :visitType
}


OIMVLIMT_VIS_CYC_PREINSERT {
select COUNT(*) from VISIT_CYCLE_LIMITS where AGY_LOC_ID = :NBTAGYID and visit_config_type_value = :visitConfigTypeValue and ACTIVE_FLAG = 'Y'
}

OIMVLIMT_VIS_CYC_PREINSERT_SEQ {
SELECT NEXTVAL('VISIT_CYCLE_LIMIT_ID')
}

OIMVLIMT_VIS_CYC_ONCHECKDELETEMASTER_ {
SELECT 1 FROM VISIT_TYPE_LIMITS V WHERE V.VISIT_CYCLE_LIMIT_ID = :VISITCYCLELIMITID
}

OIMVLIMT_VIS_CYC_PREUPDATE_ {
select COUNT(*) from VISIT_CYCLE_LIMITS where VISIT_CYCLE_LIMIT_ID != :VISITCYCLELIMITID and AGY_LOC_ID = :NBTAGYID and visit_config_type_value = :visitConfigTypeValue and CYCLE_TYPE = :CYCLETYPE and ACTIVE_FLAG = 'Y'
}
OIMVLIMT_VISIT_CYCLE_LIMIT_ID {
SELECT NEXTVAL('visit_cycle_limit_id')
}

OIMVLIMT_VIS_RG_IEP_LEVEL{
 select  ilm.iep_level_code code  ,ilm.iep_level_description description,ilm.active_flag  from incentives_earn_privs ilm
 }
 
OIMVLIMT_VISCYC_FIND_IEP_LEVEL {
select *, ( case when ( select count(*) from visit_type_limits vtl where vtl.visit_cycle_limit_id = visit_cycle_limits.visit_cycle_limit_id)=0 then 0 when ( (select count(*) from visit_type_limits vtl where vtl.visit_cycle_limit_id = visit_cycle_limits.visit_cycle_limit_id)>0 and visit_cycle_limits.tot_visits is not null) then 1 else 2 end ) as visitCount from visit_cycle_limits where agy_loc_id = :agylocid and visit_config_type =:visitConfigType order by active_flag = 'Y' desc
}
GET_IEP_LEVEL_DETAILS{
select offiep.iep_level_code iep_level_description, ( select iep_level_description from incentives_earn_privs iep where iep_level_code = offiep.iep_level_code) iep_level_code, ( select TO_DATE(to_char(next_review_date, 'DD-MM-YYYY'), 'DD-MM-YYYY') from off_incentives_earn_privs iep1 order by create_datetime desc limit 1) next_review_date from off_incentives_earn_privs offiep, incentives_earn_privs il where offiep.offender_book_id =:offenderBookId order by offiep.create_datetime desc limit 1 
}

OIMVLIMT_SAVE_IEP_SEC_DATA{
insert into agency_visit_config(agy_loc_id, visit_config_type, create_datetime, create_user_id, modify_datetime) values(:agyLocId, :visitConfigType, current_timestamp, :createUserId, NULL)
}
OIMVLIMT_UPDATE_IEP_SEC_DATA{
UPDATE AGENCY_VISIT_CONFIG set visit_config_type = :visitConfigType,modify_datetime =current_timestamp ,modify_user_id =:modifyUserId where agy_loc_id=:agyLocId

}
OIMVLIMT_GET_IEP_SEC_DATA{
select agy_loc_id,visit_config_type from AGENCY_VISIT_CONFIG
}
