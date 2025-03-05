
---***** ddl subfolder - V5_0_0_3_1 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_3_1.run >> 1_0_batch_oms_owner_ddl_5_0_0_3_1.log 2>&1  & type  1_0_batch_oms_owner_ddl_5_0_0_3_1.log


---***** dml subfolder - V5_0_0_3_1 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_3_1.run >> 2_0_batch_oms_owner_dml_5_0_0_3_1.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_3_1.log

---***** Import Reports
1.Go to reports on the left link
2.click on Import Reports
3.select the zip in the drop down and browse the file.
4.click on import.

The sample reports loaded are:

OIRIINST    Institutional Activities (Sample)
OIRIPOPU    Prison Population (Sample)
OIRIEXMO    External Movements (Sample)

Please manually change the report description with the above description in the "Manage Reports" screen. This is a one time step and it will not be required for other future report deliveries.