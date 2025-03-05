---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_SOUA_5_0_0_3_5.run >> 1_0_batch_oms_owner_dml_custom_SOUA_5_0_0_3_5.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_SOUA_5_0_0_3_5.log


---*****import_processes subfolder
Import automation related items in the folowing order:
--quick actions
Actions - S4-18176.zip
Actions - S4-18177.zip
Actions - S4-18178.zip
Actions - S4-18179.zip
Actions S4-20666.zip
Actions S4-20667.zip
Actions S4-20668.zip
Actions S4-20679.zip
Actions S4-20680.zip

--common processes
Cmnprocesses - S4-18177.zip
Cmnprocesses S4-20667.zip

--processes
Processes - S4-18177.zip
Processes - S4-18178.zip
Processes S4-20666.zip
Processes S4-20667.zip
Processes S4-20668.zip
Processes S4-20679.zip
Processes S4-20680.zip
Processes S4-21055.zip
Processes S4-21058.zip