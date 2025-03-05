INSERT INTO oms_owner.oms_roles(role_id, role_name, role_seq, create_datetime, create_user_id, modify_datetime, modify_user_id, role_code, parent_role_code, seal_flag)
VALUES(nextval('role_id'), 'Advanced Externl Investigation', 75, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, 'ADV_EXT_INVEST', NULL, NULL);

INSERT INTO oms_owner.oms_roles(role_id, role_name, role_seq, create_datetime, create_user_id, modify_datetime, modify_user_id, role_code, parent_role_code, seal_flag)
VALUES(nextval('role_id'), 'External Investigation Delete', 75, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, 'EXT_INVEST_DLT', NULL, NULL);