
---*****dml subfolder - oms_owner 

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 3_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_0_11_HF.run >> 3_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_0_11_HF.log 2>&1  & type 3_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_0_11_HF.log

-- automation setup subfolder import_processes
-- import processes.zip