insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT 
  nextval('LABLE_ID_SEQUENCE'),'OWHEADER','owheader.ieplevel.title','Incentives and Earned Privileges','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL 
   WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OWHEADER' and msg_key ='owheader.ieplevel.title');

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OCDCORDS','ocdcords.cannotdeleteorderhasattacheddoc','Cannot be deleted as the order has attached documents','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCDCORDS' and msg_key ='ocdcords.cannotdeleteorderhasattacheddoc');
  
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OCDNCODE','ocdncode.cannotdeleteorderhasattacheddoc','Cannot be deleted as the order has attached documents','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCDNCODE' and msg_key ='ocdncode.cannotdeleteorderhasattacheddoc');
  
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OCDPAROR','ocdparor.cannotdeleteorderhasattacheddoc','Cannot be deleted as the order has attached documents','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCDPAROR' and msg_key ='ocdparor.cannotdeleteorderhasattacheddoc');
  
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OCDBAILO','ocdbailo.cannotdeleteorderhasattacheddoc','Cannot be deleted as the order has attached documents','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCDBAILO' and msg_key ='ocdbailo.cannotdeleteorderhasattacheddoc');

