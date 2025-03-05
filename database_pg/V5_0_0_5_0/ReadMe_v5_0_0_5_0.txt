---***** ddl subfolder - V5_0_0_5_0 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_5_0.run >> 1_0_batch_oms_owner_ddl_5_0_0_5_0.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_5_0.log

---***** dml subfolder - V5_0_0_5_0 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_5_0.run >> 1_0_batch_oms_owner_dml_5_0_0_5_0.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_5_0.log


---*****import_processes subfolder
-- Actions
Actions S4-19209.zip
--processes
Processes S4-19209.zip

1. Import the process and deploy it
2. Open the process in development mode and search for "url"
3. replace the Sentence calculator url
4. save and deploy