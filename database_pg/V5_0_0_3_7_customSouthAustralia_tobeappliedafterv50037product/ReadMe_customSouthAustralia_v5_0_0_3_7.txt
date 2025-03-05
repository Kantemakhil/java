---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_3_7.run>> 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_3_7.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_3_7.log

---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions
Actions S4-18176.zip
Actions S4-20680.zip

--Common process
Cmnprocesses S4-18176.zip

--Process
Processes S4-20679.zip
Processes S4-20680.zip