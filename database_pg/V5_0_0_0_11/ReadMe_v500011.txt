
---***** ddl subfolder - V5_0_0_0_11 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_0_11.run >> 1_0_batch_oms_owner_ddl_5_0_0_0_11.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_0_11.log


---*****dml subfolder - V5_0_0_0_11 oms_owner DML 

!!Note: dml file oms_modules_base_help_url_v500011_20220920.sql needs to be manually modified to reflect desired URLs before running the DML batch run file 
--replace text "onlinehelpbaseurl" with correct value or use the application screen Help to apply at a later date


psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_0_11.run >> 2_0_batch_oms_owner_dml_5_0_0_0_11.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_0_11.log

