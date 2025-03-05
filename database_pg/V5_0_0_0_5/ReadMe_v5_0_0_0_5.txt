
---***** V5_0_0_0_5 oms_owner DDL 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_0_5.run >> 1_0_batch_oms_owner_ddl_5_0_0_0_5.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_0_5.log

---***** V5_0_0_0_5 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_0_5.run >> 2_0_batch_oms_owner_dml_5_0_0_0_5.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_0_5.log

