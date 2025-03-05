
---***** ddl subfolder - V5_0_0_0_11_HF1 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_0_11_HF1.run >> 1_0_batch_oms_owner_ddl_5_0_0_0_11_HF1.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_0_11_HF1.log


---*****dml subfolder - V5_0_0_0_11_HF1 oms_owner DML 

!!Note: dml file system_settings_v5000HF1_20220929.sql needs to be updated to reflect the desired URLs
   This can be done manually before running the DML batch run file, or the script can be applied as is and the value can be modified from the application.


psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_0_11_HF1.run >> 2_0_batch_oms_owner_dml_5_0_0_0_11_HF1.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_0_11_HF1.log
