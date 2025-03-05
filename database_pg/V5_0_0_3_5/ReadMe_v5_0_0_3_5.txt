---***** ddl subfolder - V5_0_0_3_5 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_3_5.run >> 1_0_batch_oms_owner_ddl_5_0_0_3_5.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_3_5.log

---***** dml subfolder - V5_0_0_3_5 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_3_5.run >> 1_0_batch_oms_owner_dml_5_0_0_3_5.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_3_5.log


---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions

S4-19678 GET_STAFF_REPORTS_TO_LOCK Quick Action.zip
S4-19678 UPDATE_STAFF_REPORTS_LOCK Quick Action.zip
S4-19741 GET_STAFF_DATA quick actions.zip
S4-19741 GET_STAFF_REPORT_CONFIGURATION quick actions.zip


--processes
S4-19678 TIMER_AUTOMATIC_STAFF_REPORTS_LOCK_UPDATE process.zip
S4-19741 OIDINCDE_STAFF_INVOLVEMENT_NOTIFY processes (2).zip
