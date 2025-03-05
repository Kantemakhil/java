---***** ddl subfolder - V5_0_0_5_1 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_5_1.run >> 1_0_batch_oms_owner_ddl_5_0_0_5_1.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_5_1.log

---***** dml subfolder - V5_0_0_5_1 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_5_1.run >> 1_0_batch_oms_owner_dml_5_0_0_5_1.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_5_1.log


---*****import_processes subfolder
-- Actions
Actions S4-22040.zip
Actions S4-22041.zip
Actions S4-22753.zip
Actions S4-19121.zip
--processes
Processes S4-22040.zip
Processes S4-22041.zip
Processes S4-22753.zip
Processes S4-19121.zip
