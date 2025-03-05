---***** ddl subfolder - V5_0_0_3_2 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_3_2.run >> 1_0_batch_oms_owner_ddl_5_0_0_3_2.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_3_2.log

---***** dml subfolder - V5_0_0_3_2 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_3_2.run >> 1_0_batch_oms_owner_dml_5_0_0_3_2.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_3_2.log


---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions

S4-18748_Quick Actions.zip
S4-19121_Quick Actions.zip
S4-19678_Quick Actions.zip
S4-19741_Quick Actions.zip

S4-20345 GET_AGLOCID_DESC_AND_EXTRACT_DATETIME Quick Action.zip
S4-20345 GET_GLOBAL_STAFF_EMAIL_BY_STAFFID Quick Action.zip
S4-20345 GET_OFFENDER_DETAILS Quick Action.zip

S4-20348 GET_GLOBAL_STAFF_EMAIL_BY_STAFFID Quick Action.zip
S4-20348 GET_OFFENDER_DETAILS quick actions.zip

S4-20631 GET_GLOBAL_STAFF_EMAIL_BY_STAFFID quick action.zip
S4-20631 GET_GLOBAL_STAFF_EMAIL_BY_TEAM_MEMBER quick action.zip
S4-20631 GET_OFFENDER_DETAILS actions.zip
S4-20631 GET_TEAM_CODE Quick Action.zip


--processes
S4-18748_Processes.zip
S4-19121_Process.zip
S4-19678_Processes.zip
S4-19741_Processes.zip
S4-20345 Scheduled appointment process.zip
S4-20348 Allocated as Primary Officer process.zip
S4-20631 OCONDAWAIT_CONDITIONS_ASSIGNMENT_NOTIFY processes.zip
