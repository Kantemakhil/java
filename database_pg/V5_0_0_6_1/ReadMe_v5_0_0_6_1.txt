---***** ddl subfolder - V5_0_0_6_1 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_6_1.run >> 1_0_batch_oms_owner_ddl_5_0_0_6_1.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_6_1.log

---***** dml subfolder - V5_0_0_6_1 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_6_1.run >> 1_0_batch_oms_owner_dml_5_0_0_6_1.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_6_1.log


---*****import_processes subfolder
--actions
Actions S4-21436.zip
S4-22934 &S4-22935_Quick Actions.zip
Actions S4-22040.zip

V5_S4-23091_Quick Actions.zip

--common processes
Cmnprocesses S4-21436.zip

---processes
Processes S4-21436.zip
Processes S4-23713.zip 
S4-22052 IEP Segregation Payroll Notification processes.zip
S4-22934 &S4-22935_processes.zip

V5_S4-23091_Processes.zip



