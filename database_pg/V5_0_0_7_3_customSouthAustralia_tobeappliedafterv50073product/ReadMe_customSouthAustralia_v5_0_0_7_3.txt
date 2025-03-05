---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_7_3.run >> 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_7_3.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_7_3.log


---*****import_processes subfolder
--process
Processes_S4-24213.zip
Processes_S4-18179.zip