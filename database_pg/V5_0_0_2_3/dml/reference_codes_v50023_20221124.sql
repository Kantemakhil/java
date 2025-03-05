INSERT INTO REFERENCE_CODES(DOMAIN ,CODE ,DESCRIPTION ,LIST_SEQ ,ACTIVE_FLAG ,SYSTEM_DATA_FLAG ,MODIFY_USER_ID ,EXPIRED_DATE ,NEW_CODE ,PARENT_CODE ,PARENT_DOMAIN ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG )
VALUES('IN_POLICY' ,'FORCE' ,'Use of Force' ,1,'Y' ,'Y' ,NULL ,NULL ,NULL ,NULL ,NULL ,CURRENT_TIMESTAMP ,'OMS_OWNER' ,CURRENT_TIMESTAMP ,NULL );

INSERT INTO REFERENCE_CODES(DOMAIN ,CODE ,DESCRIPTION ,LIST_SEQ ,ACTIVE_FLAG ,SYSTEM_DATA_FLAG ,MODIFY_USER_ID ,EXPIRED_DATE ,NEW_CODE ,PARENT_CODE ,PARENT_DOMAIN ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG )
VALUES('IN_POLICY' ,'SOP' ,'Standard Operating Procedure' ,1,'Y' ,'Y' ,NULL ,NULL ,NULL ,NULL ,NULL ,CURRENT_TIMESTAMP ,'OMS_OWNER' ,CURRENT_TIMESTAMP ,NULL );

INSERT INTO REFERENCE_CODES(DOMAIN ,CODE ,DESCRIPTION ,LIST_SEQ ,ACTIVE_FLAG ,SYSTEM_DATA_FLAG ,MODIFY_USER_ID ,EXPIRED_DATE ,NEW_CODE ,PARENT_CODE ,PARENT_DOMAIN ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG )
VALUES('IN_COMP' ,'COMP' ,'Compliant' ,1,'Y' ,'Y' ,NULL ,NULL ,NULL ,NULL ,NULL ,CURRENT_TIMESTAMP ,'OMS_OWNER' ,CURRENT_TIMESTAMP ,NULL );

INSERT INTO REFERENCE_CODES(DOMAIN ,CODE ,DESCRIPTION ,LIST_SEQ ,ACTIVE_FLAG ,SYSTEM_DATA_FLAG ,MODIFY_USER_ID ,EXPIRED_DATE ,NEW_CODE ,PARENT_CODE ,PARENT_DOMAIN ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG )
VALUES('IN_COMP' ,'NCOMP' ,'Non-Compliant' ,2,'Y' ,'Y' ,NULL ,NULL ,NULL ,NULL ,NULL ,CURRENT_TIMESTAMP ,'OMS_OWNER' ,CURRENT_TIMESTAMP ,NULL );

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('P_TRN_CAN', 'RE-ALLOCATE', 'Re Allocation', NULL, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('P_TRN_REJ', 'REJECT', 'Transfer Rejected', NULL, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ASSESS_STATS', 'A', 'Active', 1, 'Y', 'Y', NULL, NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ASSESS_STATS', 'P', 'Submitted', 2, 'Y', 'Y',NULL, NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ASSESS_STATS', 'I', 'Inactive', 3, 'Y', 'Y', NULL, NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ASSESS_STATS', 'D', 'Draft', 4, 'Y', 'Y', NULL, NULL, NULL, NULL, NULL,CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL);