INSERT INTO oms_roles(role_id, role_name, role_seq, create_datetime, create_user_id, role_code, parent_role_code, seal_flag)
VALUES(nextval('role_id'), 'Sentence Calculation Verifier', 1, current_timestamp, 'OMS_OWNER', 'SENT_VERIFIER', NULL, NULL);

INSERT INTO oms_roles(role_id, role_name, role_seq, create_datetime, create_user_id, role_code, parent_role_code, seal_flag)
VALUES(nextval('role_id'), 'Sentence Calculation Overrider', 1, current_timestamp, 'OMS_OWNER', 'SENT_OVERRIDER', NULL, NULL);