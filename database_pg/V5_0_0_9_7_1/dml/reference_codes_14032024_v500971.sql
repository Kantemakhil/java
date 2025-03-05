update
	oms_owner.reference_codes
set
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	parent_code = 'BOTH'
where
	code = 'DTC'
	and "domain" = 'LO_REL_TYPE';
	
update
	oms_owner.reference_codes
set
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	parent_code = 'BOTH'
where
	code = 'CC'
	and "domain" = 'LO_REL_TYPE';
	

update
	oms_owner.reference_codes
set
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	parent_code = 'BOTH'
where
	code = 'CU'
	and "domain" = 'LO_REL_TYPE';
	

update
	oms_owner.reference_codes
set
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	parent_code = 'NCUS'
where
	code = 'RFC'
	and "domain" = 'LO_REL_TYPE';