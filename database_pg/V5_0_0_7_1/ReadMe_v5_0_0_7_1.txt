---***** ddl subfolder - V5_0_0_7_1 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_7_1.run >> 1_0_batch_oms_owner_ddl_5_0_0_7_1.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_7_1.log

---***** dml subfolder - V5_0_0_7_1 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_5_0_0_7_1.run >> 1_0_batch_oms_owner_dml_5_0_0_7_1.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_7_1.log

---*****import_processes subfolder
--Actions
Actions_S4-24081.zip
Actions_S4-24084.zip
Actions_S4-24088.zip
Actions_S4-24090.zip
Actions_S4-22934 And Actions_S4-22935.zip
Actions_S4_24170.zip
--CommonProcess
CmnProcesses_S4-24049.zip
CmnProcesses_S4_24170.zip
--Process
Processes_S4-24081.zip
Processes_S4-24084.zip
Processes_S4-24088.zip
Processes_S4-24090.zip
Processes_S4-24552.zip
Processes_S4-24554.zip
Processes_S4-22934 And Processes_S4-22935.zip
Processes_S4_24170.zip