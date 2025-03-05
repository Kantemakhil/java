delete from reference_codes rc where domain = 'EM_TAG_STRAP';

insert into reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values  ('EM_TAG_STRAP', 'SMALL', 'Small', 99, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values ('EM_TAG_STRAP', 'MEDIUM', 'Medium', 99, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values ('EM_TAG_STRAP', 'LARGE', 'Large', 99, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


