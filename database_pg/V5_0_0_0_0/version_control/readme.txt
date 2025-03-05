Refer to document ReadMe_OptionWithBatchRunFilesAndVersionControl.txt.


****call as following 
psql --no-psqlrc -U oms_owner  -d databasename -h hostname -f 1_0_batch_oms_owner_db_versioning.run  >>  1_0_batch_oms_owner_db_versioning.log 2>&1  & type 1_0_batch_oms_owner_db_versioning.log


--verify 
select * from db_version_control;
select * from db_version_control_patches order by create_datetime;