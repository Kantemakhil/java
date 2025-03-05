---***** ddl subfolder - V5_0_0_8_0 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_8_0.run >> 1_0_batch_oms_owner_ddl_5_0_0_8_0.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_8_0.log

---***** dml subfolder - V5_0_0_8_0 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_8_0.run >> 1_0_batch_oms_owner_dml_5_0_0_8_0.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_8_0.log

---*****import_processes subfolder
--Actions
Actions_S4-24504.zip
Actions_S4-24568.zip
--Process
Processes_S4-24568.zip