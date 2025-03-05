The delta set-up config scripts are to be applied only after maintenece data was migrated from another source.
More detailed instructions can be found in the "SqlCL - Move Maintenance Data from Oracle to PostgreSql" guide in wiki.


---***** 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_delta.run  >> 1_0_batch_oms_owner_dml_delta.log  2>&1  
