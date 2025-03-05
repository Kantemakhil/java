---***** ddl subfolder - V5_0_0_4_0 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_4_0.run >> 1_0_batch_oms_owner_ddl_5_0_0_4_0.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_4_0.log

---***** dml subfolder - V5_0_0_4_0 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_4_0.run >> 1_0_batch_oms_owner_dml_5_0_0_4_0.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_4_0.log


---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions

S4-21607 GET_GLOBAL_STAFF_EMAIL_BY_STAFFID Qucik actions.zip
S4-21607 GET_GLOBAL_STAFF_EMAIL_BY_TEAM_MEMBER Quick actions.zip
S4-21607 GET_OFFENDER_DETAILS Quick actions.zip
S4-21607 GET_TEAM_CODE Quick actions.zip


--processes
S4-21607 OCONDAWAIT_CONDITIONS_ASSIGNMENT_NOTIFY processes (2).zip
