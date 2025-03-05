OIMOFFOB_OBSERVATION_TYPES_RETRIVE_DATA {
select A.*, case when ( select count(1) from off_obs_add_details where obs_type_version_id = a.obs_type_version_id and detail_type = 'CELL_CNDITNS') > 0 then 'Y' else 'N' end as Cell_Condition_Flag, case when ( select count(1) from off_obs_add_details where obs_type_version_id = a.obs_type_version_id and detail_type = 'ACTIVITY') > 0 then 'Y' else 'N' end as Activity_Flag, case when ( select count(1) from off_obs_add_details where obs_type_version_id = a.obs_type_version_id and detail_type = 'NOT_IN_CELL') > 0 then 'Y' else 'N' end as Not_In_Cell_Flag, case when ( select count(1) from off_obs_add_details where obs_type_version_id = a.obs_type_version_id and detail_type = 'COM_DET_CAT') > 0 then 'Y' else 'N' end as Demeanor_Flag from ( select MAX(obs_type_version_id) max_obs_type_version_id, observation_type from offender_observation_types group by observation_type) B, offender_observation_types A where A.observation_type = B.observation_type and A.obs_type_version_id = B.max_obs_type_version_id
}
OIMOFFOB_INSERT_OBSERVATION_TYPES_DATA {
insert into offender_observation_types( observation_type, frequency, notification_flag, notification_timing, list_seq, active_flag, expiry_date, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values ( :observationType, :frequency, :notificationFlag, :notificationTiming, :listSeq, :activeFlag, :expiryDate, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :modifyUserId, :sealFlag )
}
OIMOFFOB_UPDATE_OBSERVATION_TYPES_DATA {
update offender_observation_types set frequency=:frequency ,notification_flag=:notificationFlag, notification_timing=:notificationTiming , list_seq=:listSeq,active_flag=:activeFlag,expiry_date=:expiryDate, modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId where observation_type=:observationType
}
OIMOFFOB_DELETE_OBSERVATION_TYPES_DATA {
delete from offender_observation_types where observation_type = :observationType
}
OIMOFFOB_FACILTY_BASED_ZONE_RETRIVE_DATA {
select * from offender_observation_zones where agy_loc_id=:agyLocId
}
OIMOFFOB_INSERT_OBSERVATION_TYPES_ZONE_DATA {
insert into offender_observation_zones(agy_loc_id, zone_code, list_seq, active_flag, expiry_date, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values (:agyLocId, :zoneCode, :listSeq, :activeFlag, :expiryDate, CURRENT_TIMESTAMP, :createUserId, null, :modifyUserId, :sealFlag )
}

OIMOFFOB_UPDATE_OBSERVATION_TYPES_ZONE_DATA {
update offender_observation_zones set  list_seq=:listSeq,active_flag=:activeFlag,expiry_date=:expiryDate, modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId where agy_loc_id=:agyLocId and zone_code=:zoneCode 
}

OIMOFFOB_DELETE_OBSERVATION_TYPES_ZONE_DATA {
delete from offender_observation_zones where agy_loc_id=:agyLocId and zone_code=:zoneCode 
}

OIMOFFOB_FACILTY_BASED_ZONE_HOUSING_RETRIVE_DATA {
select *, case when ( select cn from ( select count(1)cn from off_obs_zone_details A where A.internal_location_id = B.internal_location_id and A.zone_code <> B.zone_code and active_flag = 'Y')A) >= 1 then 'Y' else 'N' end other_loc_exist from off_obs_zone_details B where agy_loc_id =:agyLocId and zone_code =:zoneCode
}

OIMOFFOB_INSERT_OBSERVATION_TYPES_ZONE_HOUSING_DATA {
insert into off_obs_zone_details( agy_loc_id, zone_code, internal_location_id, sensor_id, list_seq, active_flag, expiry_date, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values ( :agyLocId, :zoneCode, :internalLocationId, :sensorId, :listSeq, :activeFlag, :expiryDate, CURRENT_TIMESTAMP, :createUserId, null, :modifyUserId, :sealFlag )
}
OIMOFFOB_UPDATE_OBSERVATION_TYPES_ZONE_HOUSING_DATA {
update off_obs_zone_details set sensor_id=:sensorId, list_seq=:listSeq,active_flag=:activeFlag,expiry_date=:expiryDate, modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId where agy_loc_id=:agyLocId and zone_code=:zoneCode and internal_location_id=:internalLocationId
}
OIMOFFOB_DELETE_OBSERVATION_TYPES_ZONE_HOUSING_DATA {
delete from off_obs_zone_details where agy_loc_id=:agyLocId and zone_code=:zoneCode and internal_location_id=:internalLocationId
}

OIMOFFOB_INSERT_OBSERVATION_CHARECTERSTICS_DATA {
insert into off_obs_characteristics( observation_type, characteristics_code, characteristics_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values (:observationType, :characteristicsCode, :characteristicsType,CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :modifyUserId, :sealFlag)
}
OIMOFFOB_UPDATE_OBSERVATION_CHARECTERSTICS_DATA {
update off_obs_characteristics set characteristics_code=:characteristicsCode , modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId  where observation_type=:observationType and characteristics_type=:characteristicsType 
}

OIMOFFOB_DELETE_OBSERVATION_CHARECTERSTICS_DATA {
delete
from
	off_obs_add_details
where
	obs_type_version_id in (
		select
			oot.OBS_TYPE_VERSION_ID
		from
			offender_observation_types oot
		where
			 oot.observation_type = :observationType)
}

OIMOFFOB_OBSERVATION_CHARECTERSTICS_RETRIVE_DATA {
select * from off_obs_add_details where obs_type_version_id=:obsTypeVersionId
}

OIMOFFOB_FIND_RGAGYLOC {
SELECT description, al.agy_loc_id code FROM agency_locations al WHERE agency_location_type = 'INST' AND ( ( active_flag = 'Y' AND deactivation_date IS NULL ) OR 'ENTER-QUERY' = 'ENTER-QUERY' ) AND al.agy_loc_id IN ( SELECT ca.agy_loc_id FROM caseload_agency_locations ca WHERE ca.caseload_id = ( SELECT working_caseload_id FROM staff_members WHERE user_id = :P_USER_ID ) ) AND al.agy_loc_id NOT IN ( 'TRN', 'OUT' ) ORDER BY list_seq
}

OIMOFFOB_GET_HOUSING_LOCATION_DESCRIPTION{
select description as location_description, living_unit_code as location_code  from living_units lu where living_unit_id = :livingUnitId 
}

OIMOFFOB_UPDATE_FLAG_OFFENDER_OBSERVATION_TYPES_DETAILS {
update offender_observation_types set cell_condition_flag=:cellConditionFlag,activity_flag=:activityFlag,demeanor_flag=:demeanorFlag,not_in_cell_flag=:notInCellFlag,officer_notes_flag=:officerNotesFlag , modify_datetime=CURRENT_TIMESTAMP ,modify_user_id=:modifyUserId where observation_type=:observationType
}

OIMOFFOB_GET_OBSERVATION_PERIOD_DELETE_COUNT {
select count(*) from off_observation_periods where observation_type=:observationType
}

OIMOFFOB_GET_OBSERVATION_TYPE_VERSION_ID {
select nextval('obs_type_version_id_seq') from dual
}

OIMOFFOB_INSERT_OBSERVATION_TYPES_COMMON_SAVE_DATA {
insert into offender_observation_types(obs_type_version_id, observation_type, frequency, notification_flag, notification_timing, list_seq, active_flag, expiry_date,officer_notes_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values (:obsTypeVersionId, :observationType, :frequency, :notificationFlag, :notificationTiming, :listSeq, :activeFlag, :expiryDate,:officerNotesFlag, CURRENT_TIMESTAMP, :createUserId, null, :modifyUserId, :sealFlag )
}

OIMOFFOB_INSERT_OBSERVATION_DETAILS_COMMON_SAVE_DATA {
insert into off_obs_add_details( obs_type_version_id , detail_type, detail_code, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values (:obsTypeVersionId, :detailType, :detailCode, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :modifyUserId, :sealFlag)
}
OIMOFFOB_UPDATE_ACTIVE_FLAG{
UPDATE OFFENDER_OBSERVATION_TYPES SET ACTIVE_FLAG = 'N', EXPIRY_DATE = CURRENT_TIMESTAMP,MODIFY_DATETIME=CURRENT_TIMESTAMP ,MODIFY_USER_ID=:modifyUserId WHERE OBS_TYPE_VERSION_ID = :obsTypeVersionId
}
OIMOFFOB_OBSERVATION_TYPES_ACTIVE_EXISTING_DATA {
select * from offender_observation_types where observation_type=:observationType and active_flag='Y'
}
OIMOFFOB_CHECK_CHILD_RECORDS_COUNT{
select count(*) from off_observation_periods oop,offender_observation_types oot
where oop.obs_type_version_id = oot.obs_type_version_id and oot.observation_type  = :observationType
}