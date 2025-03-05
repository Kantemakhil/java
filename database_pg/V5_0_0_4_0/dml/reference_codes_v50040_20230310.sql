update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='UNALLOC';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='ALLOC';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='PROG';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='REV';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='COMP';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='SUBM';
update reference_codes set parent_code ='INACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='CLOSED';
update reference_codes set parent_code ='INACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='REP_REQ_STS' and code ='NR';

update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='PROCEED_STS' and code ='ACTIVE';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='PROCEED_STS' and code ='PROVEN';
update reference_codes set parent_code ='INACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='PROCEED_STS' and code ='WITHDRAWN';
update reference_codes set parent_code ='INACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='PROCEED_STS' and code ='RECALL';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='PROCEED_STS' and code ='ALLOCATED';
update reference_codes set parent_code ='ACTIVE',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where "domain" ='PROCEED_STS' and code ='UNALLOCATED';