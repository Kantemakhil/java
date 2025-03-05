update
	reference_codes
set
	parent_code = 'A',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'ALLOC';

update
	reference_codes
set
	parent_code = 'I',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'END';

update
	reference_codes
set
	parent_code = 'A',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'WAIT';

update
	reference_codes
set
	parent_code = 'A',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'PLAN';

update
	reference_codes
set
	parent_code = 'A',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'ASI';

update
	reference_codes
set
	parent_code = 'I',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'REM';

update
	reference_codes
set
	parent_code = 'I',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'COMP';

update
	reference_codes
set
	parent_code = 'A',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'REF';

update
	reference_codes
set
	parent_code = 'A',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'CANC';

update
	reference_codes
set
	parent_code = 'A',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	"domain" = 'OFF_PRG_STS'
	and CODE = 'WORK';