---***** ddl subfolder - V5_0_0_6_0 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_6_0.run >> 1_0_batch_oms_owner_ddl_5_0_0_6_0.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_6_0.log

---***** dml subfolder - V5_0_0_6_0 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_6_0.run >> 1_0_batch_oms_owner_dml_5_0_0_6_0.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_6_0.log


---*****import_processes subfolder
--actions
Actions S4-21436.zip
QuickAction.zip

--common processes
Cmnprocesses S4-21436.zip

---processes
Processes S4-21436.zip
processes.zip



