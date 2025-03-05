update
	system_profiles
set
	description = 'COMM Status for New Offender Booking' ,
	profile_value = 'New - No Sentence',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	profile_type = 'CLIENT'
	and profile_code = 'PROB_STATUS1';

update
	system_profiles
set
	description = 'COMM Status for Reopened Booking' ,
	profile_value = 'Repeat - No Sentence',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	profile_type = 'CLIENT'
	and profile_code = 'PROB_STATUS2';

update
	system_profiles
set
	description = 'COMM Status for Active Sentences Without Config' ,
	profile_value = 'Sentenced - Config Missing',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	profile_type = 'CLIENT'
	and profile_code = 'PROB_STATUS3';

update
	system_profiles
set
	description = 'COMM Status for Inactive Sentences' ,
	profile_value = 'Inactive',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	profile_type = 'CLIENT'
	and profile_code = 'PROB_STATUS4';

delete
from
	system_profiles
where
	profile_type = 'CLIENT'
	and profile_code = 'PROB_STATUS5';