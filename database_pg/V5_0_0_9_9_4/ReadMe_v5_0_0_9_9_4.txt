---***** ddl subfolder - V5_0_0_9_9_4 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_9_9_4.run >> 1_0_batch_oms_owner_ddl_5_0_0_9_9_4.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_9_9_4.log

---***** dml subfolder - V5_0_0_9_9_4 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_9_9_4.run >> 1_0_batch_oms_owner_dml_5_0_0_9_9_4.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_9_9_4.log

---*****Import_Process subfolder
--Actions
Actions_TAS-174.zip
--CmnProcess
Cmnprocesses_TAS-174.zip
cmnprocesses_TAS-1682.zip
