---***** ddl subfolder - V5_0_0_4_5_F1 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_4_5_F1.run >> 1_0_batch_oms_owner_ddl_5_0_0_4_5_F1.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_4_5_F1.log

---***** dml subfolder - V5_0_0_4_5_F1 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_4_5_F1.run >> 1_0_batch_oms_owner_dml_5_0_0_4_5_F1.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_4_5_F1.log

