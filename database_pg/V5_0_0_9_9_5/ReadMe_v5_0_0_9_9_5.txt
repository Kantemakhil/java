---***** ddl subfolder - V5_0_0_9_9_5 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_9_9_5.run >> 1_0_batch_oms_owner_ddl_5_0_0_9_9_5.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_9_9_5.log

---***** dml subfolder - V5_0_0_9_9_5 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_9_9_5.run >> 1_0_batch_oms_owner_dml_5_0_0_9_9_5.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_9_9_5.log

---*****Import_Process subfolder
--Actions
Actions_TAS-134.zip
--CmnProcess
CmnProcesses_TAS-134.zip
--Processes
Processes_TAS-134.zip
