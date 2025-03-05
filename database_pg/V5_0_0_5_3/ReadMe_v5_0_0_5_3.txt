---***** ddl subfolder - V5_0_0_5_3 oms_owner DDL 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_5_3.run >> 1_0_batch_oms_owner_ddl_5_0_0_5_3.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_5_3.log

---***** dml subfolder - V5_0_0_5_3 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_5_3.run >> 1_0_batch_oms_owner_dml_5_0_0_5_3.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_5_3.log

---*****import_processes subfolder
-- Actions
actions.zip
--processes
processes.zip