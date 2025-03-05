--apply custom DML

psql --no-psqlrc -U oms_owner -d dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4.run >>  1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_2_4.log

---*****import_processes subfolder
Import automation related items in the folowing order:

--quick actions

 Actions - S4-19053.zip
 Actions S4-19055.zip
 Actions-S4-17505.zip
 Quick_Actions_19056.zip

--common processes

 cmnprocesses_19056.zip

--processes

 Process - S4-19053.zip
 Process - S4-19055.zip
 Process-S4-17505.zip
 processes_19052.zip
 processes_19054.zip
 processes_19056.zip
