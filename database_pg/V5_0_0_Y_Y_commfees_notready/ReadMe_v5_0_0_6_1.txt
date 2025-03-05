---***** ddl subfolder - V5_0_0_6_1 oms_owner  

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_ddl_5_0_0_6_0.run >> 2_0_batch_oms_owner_ddl_5_0_0_6_0.log 2>&1  & type  2_0_batch_oms_owner_ddl_5_0_0_6_0.log


---***** dml subfolder - V5_0_0_6_1 oms_owner DML

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_6_0.run >> 2_0_batch_oms_owner_dml_5_0_0_6_0.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_6_0.log


---*****import_processes subfolder
--actions
S4-23835 TIMER_GENERATE_FEE_ACCOUNT_BILLS Quick Actions.zip
S4-23836 TIMER_GENERATE_BACK_DATE_BILLS Quick Actions.zip
S4-23837 TIMER_BILL_AGING_DAILY_PROCESS Quick Actions.zip 
S4-23839 TIMER_GENERATE_BILLS_FOR_DISCHARGE_OFFENDERS Quick Actions.zip
S4-23839 TIMER_GENERATE_BILLS_FOR_TRANSFER_OFFENDERS Quick Actions.zip

--common processes
S4-23835 and S4-23836 CMN_GENERATE_FEE_BILLS  cmnprocesses.zip
S4-23839 CMN_GENERATE_BILLS_FOR_TRANSFER_AND_DISCHARGE_OFFENDERS cmnprocesses.zip

---processes
S4-23835 TIMER_GENERATE_FEE_ACCOUNT_BILLS processes.zip
S4-23836 TIMER_GENERATE_BACK_DATE_BILLS processes.zip
S4-23837 TIMER_BILL_AGING_DAILY_PROCESS processes.zip
S4-23839 TIMER_GENERATE_BILLS_FOR_DISCHARGE_OFFENDERS processes.zip
S4-23839 TIMER_GENERATE_BILLS_FOR_TRANSFER_OFFENDERS processes.zip



