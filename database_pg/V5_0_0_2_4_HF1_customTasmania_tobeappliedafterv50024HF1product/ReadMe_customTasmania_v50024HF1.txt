--***** dml subfolder
psql --no-psqlrc -U oms_owner -d dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4_HF1.run >>  1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4_HF1.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4_HF1.log

---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions
Qucik_Action_S4_19058_updated.zip
Quick_Action_get_offender_details_19056_story.zip
Quick_Action_S4_17974_updated.zip
Quick_Action_S4_19052_updated.zip
Quick_Action_S4_19053.zip
Quick_Action_S4_19054_updated.zip
Quick_Action_S4_19055.zip
Quick_Action_S4_19056_updated.zip
S4_19057_actions.zip


--cmn processes
cmnprocesses_S4_19056_updated.zip
cmnprocesses_S4_19058_updated.zip

--Processes
Process_S4_17974_updated.zip
Process_S4_19052_updated.zip
Process_S4_19053.zip
Process_S4_19054_updated.zip
Process_S4_19055.zip
Process_S4_19056_updated.zip
Process_S4_19058_updated.zip
S4_19057_processes.zip

