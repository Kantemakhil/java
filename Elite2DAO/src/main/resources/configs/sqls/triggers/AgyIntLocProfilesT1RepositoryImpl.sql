AGY_INT_LOC_PROFILES_T1_INSERT_AGY_INT_LOC_AMENDMENTS{
INSERT INTO agy_int_loc_amendments ( agy_int_loc_amendment_id,internal_location_id,action_code, amend_user_id,amend_date,column_name,old_value,new_value,create_user_id,create_datetime,modify_datetime)
VALUES ( NEXTVAL('agy_int_loc_amendment_id'), :internalLocationId,:actionCode,:amendUserId, current_timestamp, :columnName, :oldValue,:newValue,:amendUserId,current_timestamp,current_timestamp)
}