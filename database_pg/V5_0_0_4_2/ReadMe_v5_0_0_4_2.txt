---***** ddl subfolder - V5_0_0_4_2 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_4_2.run >> 1_0_batch_oms_owner_ddl_5_0_0_4_2.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_4_2.log

---***** dml subfolder - V5_0_0_4_2 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_4_2.run >> 1_0_batch_oms_owner_dml_5_0_0_4_2.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_4_2.log

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_4_2.run >> 2_0_batch_oms_owner_dml_5_0_0_4_2.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_4_2.log


---*****import_processes subfolder
Import automation related items in the folowing order:

--processes
S4-20345 Scheduled appointment Process (2).zip