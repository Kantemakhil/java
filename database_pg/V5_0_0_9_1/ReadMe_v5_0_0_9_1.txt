---***** ddl subfolder - V5_0_0_9_1 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_9_1.run >> 1_0_batch_oms_owner_ddl_5_0_0_9_1.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_9_1.log

---***** dml subfolder - V5_0_0_9_1 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_9_1.run >> 1_0_batch_oms_owner_dml_5_0_0_9_1.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_9_1.log

---*****import_processes subfolder
--Actions
Actions_S4-25410.zip
--CmnProcesses
Cmnprocesses_S4-25410.zip
--Process
Processes_S4-25410.zip
