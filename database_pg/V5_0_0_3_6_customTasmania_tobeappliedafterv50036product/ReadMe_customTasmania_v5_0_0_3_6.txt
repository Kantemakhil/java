---*****dml subfolder - oms_owner 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_3_6.run >> 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_3_6.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_3_6.log

---*****import_processes subfolder
Import automation related items in the folowing order:
--quick actions
Actions S4-20547.zip

--common processes
Cmnprocesses S4-20547.zip
cmnprocesses_Prisoner_Admission.zip
cmnprocesses_Prisoner_Housing.zip 
cmnprocesses_Priosoner_Transfer.zip 
cmnprocesses_Prisoner_Release.zip 
