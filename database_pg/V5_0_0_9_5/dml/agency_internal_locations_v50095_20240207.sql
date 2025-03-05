update
	agency_internal_locations
set
	user_desc = description,
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	user_desc is null;
