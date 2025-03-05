INSERT INTO reference_codes (DOMAIN,CODE,DESCRIPTION,LIST_SEQ,ACTIVE_FLAG,SYSTEM_DATA_FLAG
                            ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
       SELECT 'ID_TYPE','SPI','TAS Police Identification',2,'Y','N'
              ,SYSTIMESTAMP, USER,SYSTIMESTAMP, NULL, NULL 
  FROM dual
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'ID_TYPE' AND code = 'SPI');


UPDATE reference_codes  
   SET description = 'TAS Police Identification'
      ,list_seq = 2
      ,active_flag = 'Y'
      ,system_data_flag = 'N'
      ,modify_datetime = SYSTIMESTAMP
      ,modify_user_id = USER
 WHERE domain = 'ID_TYPE' 
  AND code = 'SPI';

COMMIT;

