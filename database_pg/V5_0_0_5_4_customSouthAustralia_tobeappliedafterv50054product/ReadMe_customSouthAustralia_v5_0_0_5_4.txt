---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_5_4.run >> 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_5_4.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_5_4.log

---*****import_processes subfolder
--Actions
Actions S4-22288.zip
Actions S4-22738.zip
--processes
Cmnprocesses S4-22738.zip
Processes S4-22288.zip