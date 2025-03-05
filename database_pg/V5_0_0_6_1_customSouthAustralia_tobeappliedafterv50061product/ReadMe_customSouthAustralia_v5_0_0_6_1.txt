---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_6_1.run >> 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_6_1.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_SOAU_5_0_0_6_1.log
---*****import_processes subfolder
--Actions
Actions S4-23928.zip
Actions S4-23939.zip
Actions S4-23950.zip
--Process
Processes S4-23928.zip