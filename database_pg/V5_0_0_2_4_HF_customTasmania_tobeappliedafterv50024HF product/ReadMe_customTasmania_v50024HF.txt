--dml subfolder
psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4_HF.run >> 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4_HF.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4_HF.log

---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions
Quick_actions_19054.zip
