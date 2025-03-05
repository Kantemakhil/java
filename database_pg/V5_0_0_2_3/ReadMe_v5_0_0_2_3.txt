
---***** ddl subfolder - V5_0_0_2_3 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_2_3.run >> 1_0_batch_oms_owner_ddl_5_0_0_2_3.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_2_3.log


---*****dml subfolder - V5_0_0_2_3 oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_2_3.run >> 2_0_batch_oms_owner_dml_5_0_0_2_3.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_2_3.log

Adjust system settings from the application.
This patch introduces setting for addressify.
An example of URL for ADDRSSIFY_URL is:

https://api.addressify.com.au/


