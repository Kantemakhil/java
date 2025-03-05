insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OIDITRAN','oiditran.nobedavailableforselectedlocation','Not enough space available in the selected location','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OIDITRAN' and msg_key ='oiditran.nobedavailableforselectedlocation');
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OIMMHOLO','oimmholo.noofoccupantcannotbegreatherthancapacity',
  'Offenders are present in the selected location so, cannot reduce the capacity',
  'LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OIMMHOLO' and msg_key ='oimmholo.noofoccupantcannotbegreatherthancapacity');