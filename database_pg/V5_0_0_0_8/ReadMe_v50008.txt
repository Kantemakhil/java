
---***** V5_0_0_0_8 oms_owner DDL 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_0_8.run >> 1_0_batch_oms_owner_ddl_5_0_0_0_8.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_0_8.log


!!Note: dml file system_settings_v50008_20220816.sql needs to be updated to reflect the desired URLs
   This can be done manually before running the DML batch run file, or the script can be applied as is and the value can be modified from the application.


---***** - V5_0_0_0_8 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_0_8.run >> 2_0_batch_oms_owner_dml_5_0_0_0_8.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_0_8.log

