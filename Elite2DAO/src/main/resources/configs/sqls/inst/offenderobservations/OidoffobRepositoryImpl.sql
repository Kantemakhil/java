OIDOFFOB_GET_OBSERVATION_TYPE_DATA {
select 
oot.*,
case when (select count(*) from off_obs_add_details ooad where ooad.obs_type_version_id = oot.OBS_TYPE_VERSION_ID and detail_type = 'CELL_CNDITNS') > 0 then 'Y' else 'N' end as cell_condition_flag,
case when (select count(*) from off_obs_add_details ooad where ooad.obs_type_version_id = oot.OBS_TYPE_VERSION_ID and detail_type = 'ACTIVITY') > 0 then 'Y' else 'N' end as activity_flag,
case when (select count(*) from off_obs_add_details ooad where ooad.obs_type_version_id = oot.OBS_TYPE_VERSION_ID and detail_type = 'COM_DET_CAT') > 0 then 'Y' else 'N' end as demeanor_flag,
case when (select count(*) from off_obs_add_details ooad where ooad.obs_type_version_id = oot.OBS_TYPE_VERSION_ID and detail_type = 'NOT_IN_CELL') > 0 then 'Y' else 'N' end as not_in_cell_flag
from offender_observation_types oot where oot.OBS_TYPE_VERSION_ID=:obsTypeVersionId
}

OIDOFFOB_GET_OBSERVATION_PERIODS_DATA{
select
	oop.obs_period_id,
	oop.offender_book_id,
	oop.obs_period_seq,
	oop.obs_type_version_id,
	oop.start_datetime,
	oop.end_reason_code,
	oop.end_datetime,
	oop.status_code,
	oop.create_datetime,
	oop.create_user_id,
	oop.modify_datetime,
	oop.modify_user_id,
	oop.seal_flag,
	to_char(oop.obs_type_version_id) as obs_type_version_id_temp,
	oot.frequency,oot.notification_flag,oot.notification_timing,
	(select count(*) from off_obs_period_checks where obs_period_id =oop.obs_period_id )as check_record_count
from
	off_observation_periods oop,
	offender_observation_types oot
where
	oop.offender_book_id =:offenderBookId and oop.obs_type_version_id = oot.obs_type_version_id 
	order by
	status_code asc,obs_period_id desc
}

OIDOFFOB_INSERT_OBSERVATION_PERIODS_DATA {
insert into off_observation_periods(
obs_period_id,
offender_book_id,
obs_period_seq,
obs_type_version_id,
start_datetime,
end_reason_code,
end_datetime,
status_code,
create_datetime,
create_user_id,
modify_datetime,
modify_user_id,
seal_flag) values(:obsPeriodId,:offenderBookId,:obsPeriodSeq ,:obsTypeVersionId,:startDatetime,:endReasonCode,
:endDatetime,:statusCode,CURRENT_TIMESTAMP, :createUserId, null, :modifyUserId, :sealFlag)
}
OIDOFFOB_UPDATE_OBSERVATION_PERIODS_DATA {
update off_observation_periods set start_datetime=:startDatetime,end_reason_code=
:endReasonCode,end_datetime=:endDatetime,status_code=:statusCode, modify_datetime=CURRENT_TIMESTAMP ,
modify_user_id=:modifyUserId where obs_period_id=:obsPeriodId
}
OIDOFFOB_DELETE_OBSERVATION_PERIODS_DATA {
delete from off_observation_periods where obs_period_id=:obsPeriodId
}

OIDOFFOB_INSERT_OBSERVATION_PERIODS_CHECKS_DATA{
insert into off_obs_period_checks(obs_period_id, check_id,check_seq, check_datetime, schedule_datetime,  performing_staff_id, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values(:obsPeriodId, nextval('check_id_seq'), (select coalesce(max(check_seq)+1 , 1) from off_obs_period_checks where obs_period_id=:obsPeriodId),:checkDatetime, :scheduleDatetime,  :performingStaffId, current_timestamp, :createUserId, null, :modifyUserId, :sealFlag)
}

OIDOFFOB_UPDATE_OBSERVATION_PERIODS_CHECK_DATA {
update off_obs_period_checks set check_datetime=:checkDatetime,performing_staff_id=:performingStaffId, modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId where check_id=:checkId
}

OIDOFFOB_INSERT_OBSERVATION_PERIODS_GET_SEQ{
SELECT NEXTVAL('OBS_PERIOD_ID_SEQ') FROM DUAL
}

OIDOFFOB_GET_OBSERVATION_PERIODS_CHECK_DATA{
SELECT * ,(select frequency from offender_observation_types where obs_type_version_id = :obsTypeVersionId ) as frequency, (select last_name || ',' || ' '  || first_name as user_id from staff_members sm where staff_id =obp.performing_staff_id ) as user_id FROM OFF_OBS_PERIOD_CHECKS obp WHERE  obp.OBS_PERIOD_ID = :OBSPERIODID order by check_id desc
}
OIDOFFOB_OBSERVATION_CHARECTERSTICS_ADDITIONAL_RETRIVE_DATA {
select * from off_obs_add_check_details where check_id=:checkId
}

OIDOFFOB_DELETE_OBSERVATION_CHARECTERSTICS_ADDITIONAL_DATA {
delete from off_obs_add_check_details where check_id=:checkId
}

OIDOFFOB_INSERT_OBSERVATION_CHARECTERSTICS_ADDITIONAL_DATA {
insert
	into
	off_obs_add_check_details(check_id,
	detail_type,
	detail_code,
	obs_type_version_id,
	detail_datetime,
	reporting_staff_id,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values(:checkId,
:detailType,
:detailCode,
:obsTypeVersionId,
current_timestamp,
:reportingStaffId,
current_timestamp,
:createUserId,
current_timestamp,
:modifyUserId,
:sealFlag)
}

OIDOFFOB_UPDATE_OBSERVATION_PERIODS_CHECK_UPDATE_COMMENT_DATA {
update off_obs_period_checks set COMMENT_TEXT=:commentText,modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId, performing_staff_id=:performingStaffId where check_id=:checkId
}
OIDOFFOB_GET_OBSERVATION_PERIODS_CHECK_COMMENT_DATA {
select * from off_obs_period_checks where check_id=:checkId
}
OIDOFFOB_CELL_CONDITIONS_RECORD_GROUP_DATA{
select refer.code,refer.description from off_obs_add_details va, reference_codes refer where refer.domain='CELL_CNDITNS' AND va.obs_type_version_id=:observationType::bigint and va.detail_type='CELL_CNDITNS' and refer.code=va.detail_code 
}
OIDOFFOB_ACTIVITY_RECORD_GROUP_DATA{
select refer.code,refer.description from off_obs_add_details va, reference_codes refer where refer.domain='ACTIVITY' AND va.obs_type_version_id=:observationType::bigint and va.detail_type='ACTIVITY' and refer.code=va.detail_code 
}
OIDOFFOB_DEMEANOR_RECORD_GROUP_DATA{
select refer.code,refer.description from off_obs_add_details va, reference_codes refer where refer.domain='COM_DET_CAT' AND va.obs_type_version_id=:observationType::bigint and va.detail_type='COM_DET_CAT' and refer.code=va.detail_code 
}
OIDOFFOB_NOT_IN_CELL_RECORD_GROUP_DATA{
select refer.code,refer.description from off_obs_add_details va, reference_codes refer where refer.domain='NOT_IN_CELL' AND va.obs_type_version_id=:observationType::bigint and va.detail_type='NOT_IN_CELL' and refer.code=va.detail_code 
}

OIDOFFOB_GET_LIVING_UNIT_MAINTAIN_SETUP{
SELECT COUNT(*) FROM OFF_OBS_ZONE_DETAILS WHERE INTERNAL_LOCATION_ID = (SELECT LIVING_UNIT_ID  FROM V_HEADER_BLOCK VHB  WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID)
}


OIDOFFOB_GET_LIVING_UNIT_MAINTAIN_SETUP_NOT_IN_LOCATION{
select count(*) from offender_observation_zones where zone_code =(select
	zone_code
from
	OFF_OBS_ZONE_DETAILS
where
	INTERNAL_LOCATION_ID = (
	select
		LIVING_UNIT_ID
	from
		V_HEADER_BLOCK_FN(:username) VHB
	where
		OFFENDER_BOOK_ID = :OFFENDERBOOKID)
	and active_flag = 'Y') and active_flag='Y' and agy_loc_id =:agyLocId
}

OIDOFFOB_REFERENCE_CODE_OFFENDER_OBSERVATION {
select
	rc.code as observation_type,
	rc.description,
	oot.active_flag,
	to_char(oot.obs_type_version_id) as code 
from
	reference_domains rd,
	reference_codes rc,
	offender_observation_types oot
where
	rd.domain = rc.domain
	and rc.code = oot.observation_type
	and rd."domain" = 'OBSRVATN_TYP'
}
OIDOFFOB_DELETE_OBSERVATION_ADD_CHAR_DATA {
delete from off_obs_add_check_details where check_id=:checkId AND detail_type=:detailType AND detail_code=:detailCode
}
OIDOFFOB_CELL_CONDITIONS_REFRENCE_RECORD_GROUP_DATA{
select code,description from reference_codes rc where domain = 'CELL_CNDITNS'
}
OIDOFFOB_ACTIVITY_REFERENCE_RECORD_GROUP_DATA{
select code,description from reference_codes rc where domain = 'ACTIVITY'
}

OIDOFFOB_DEMEANOR_REFERNCE_RECORD_GROUP_DATA{
select code,description from reference_codes rc where domain = 'COM_DET_CAT'
}
OIDOFFOB_NOT_IN_CELL_REFERENCE_RECORD_GROUP_DATA{
select code,description from reference_codes rc where domain = 'NOT_IN_CELL'
}
OIDOFFOB_GET_OBS_PERIOD_SEQ_MAX_VALUE {
select coalesce(max(obs_period_seq)+1 , 1) from off_observation_periods where offender_book_id=:offenderBookId
}
OIDOFFOB_UPDATE_OBSERVATION_PERIODS_CHECK_DATA_SCHEDULE_DATE_TIME {
update off_obs_period_checks set schedule_datetime=:scheduleDatetime, modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId where check_id=:checkId
}

OIDOFFOB_GET_CURRENT_STAFF_ID_LOGIN {
select
		staff_id 
	from
		staff_members sm
	where
		user_id  = :currentUserId
		}
		
OIDOFFOB_SYSPFL_FIND_SYSTEM_PROFILES {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'OBS_PER_BDTE'
}