---***** ddl subfolder - V5_0_0_9_7 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_9_7.run >> 1_0_batch_oms_owner_ddl_5_0_0_9_7.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_9_7.log

---***** dml subfolder - V5_0_0_9_7 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_9_7.run >> 1_0_batch_oms_owner_dml_5_0_0_9_7.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_9_7.log

---*****import_processes subfolder
--Actions
Actions_S4-26026.zip
Actions_S4-26374.zip

--Processes
Processes_S4-26026.zip