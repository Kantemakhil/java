
---***** V5_0_0_0_6_hf_2 oms_owner DDL 
psql --no-psqlrc -U oms_owner  -d  databasename -h host -f 1_0_batch_oms_owner_ddl_5_0_0_0_6_hf_2.run >> 1_0_batch_oms_owner_ddl_5_0_0_0_6_hf_2.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_0_6_hf_2.log


---***** V5_0_0_0_6_hf_2 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  databasename -h host -f 2_0_batch_oms_owner_dml_5_0_0_0_6_hf_2.run >> 2_0_batch_oms_owner_dml_5_0_0_0_6_hf_2.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_0_6_hf_2.log


