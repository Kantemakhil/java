
INSERT INTO oms_owner.system_labels (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID )
SELECT NEXTVAL('lable_id_sequence'), 'OCDCSCH', 'ocdcsch.title', 'Scheduler', 'LABEL', CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCDCSCH' AND msg_key = 'ocdcsch.title');

UPDATE
    oms_owner.system_labels 
SET
    msg_value = 'Scheduler'
   ,modify_datetime = CURRENT_TIMESTAMP 
   ,modify_user_id = 'OMS_OWNER'
WHERE module_name = 'OCDCSCH' 
  AND msg_key = 'ocdcsch.title';