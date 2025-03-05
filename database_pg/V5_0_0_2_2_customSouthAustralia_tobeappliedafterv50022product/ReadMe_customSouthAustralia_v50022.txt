---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 3_0_batch_oms_owner_dml_custom_SOUA_5_0_2_2.run >> 3_0_batch_oms_owner_dml_custom_SOUA_5_0_2_2.log 2>&1  & type 3_0_batch_oms_owner_dml_custom_SOUA_5_0_2_2.log


---*****import_processes subfolder
Import automation related items in the folowing order:
--quick actions
Actions - S4-18175.zip
Actions - S4-18176.zip
Actions - S4-18177.zip
Actions - S4-18178.zip
Actions - S4-18179.zip
Actions - S4-18183.zip

--common processes
Common Process - S4-18175.zip
Common Process - S4-18176.zip
Common Process - S4-18177.zip
Common Process - S4-18179.zip
Common Process - S4-18183.zip

--processes
Process - S4-18175.zip
Process - S4-18176.zip
Process - S4-18177.zip
Process - S4-18178.zip
Process - S4-18179.zip
Process - S4-18183.zip