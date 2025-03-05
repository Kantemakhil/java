---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 3_0_batch_oms_owner_dml_custom_SOUA_5_0_3_1.run >> 3_0_batch_oms_owner_dml_custom_SOUA_5_0_3_1.log 2>&1  & type 3_0_batch_oms_owner_dml_custom_SOUA_5_0_3_1.log

---*****import process subfolder
Import automation related items in the following order:

--quick actions (this order is mandatory, please note exact file name when loading)
Actions - S4-18175.zip
Actions - S4-18179.zip
Actions - S4-18183.zip
Actions S4-18177.zip
Actions S4-18178.zip

Quick_Actions_GET_PRISONER_IDENTIFIERS_18179.zip

Actions - S4-18176.zip
Actions S4-18179.zip



--Common Processes
Cmnprocesses S4-18176.zip

--processes
Process - S4-18183.zip
Processes S4-18179.zip
Processes S4-21055.zip
Processes S4-21058.zip


