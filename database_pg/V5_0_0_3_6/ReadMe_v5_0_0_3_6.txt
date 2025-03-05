---***** ddl subfolder - V5_0_0_3_6 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_3_6.run >> 1_0_batch_oms_owner_ddl_5_0_0_3_6.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_3_6.log

---***** dml subfolder - V5_0_0_3_6 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_3_6.run >> 1_0_batch_oms_owner_dml_5_0_0_3_6.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_3_6.log


---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions

Actions S4-18747.zip
S4-21007 GET_GLOBAL_STAFF_EMAIL_BY_STAFFID quick actions.zip
S4-21007 GET_OFFENDER_DETAILS quick actions.zip


--processes

Processes S4-18747.zip
S4-21007 Allocated as Primary Officer processes.zip
