
---***** ddl subfolder - v5_0_0_9_3_audit oms_owner 

-------------------------------
--initial verification 
-------------------------------

psql --no-psqlrc -U oms_owner -d dbname -h hostname -a -f  verification_scripts_initial.sql > verification_scripts_initial.log 2>&1


---------------------------------------------------------------------
--ddl and dml to support ddl for missing audit columns and seal flag 
---------------------------------------------------------------------

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.run >> 1_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log 2>&1  & type  1_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log


---------------------------------------------------------------------
--ddl and dml to support ddl for nullable/not nullable and default 
---------------------------------------------------------------------

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.run >> 2_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log 2>&1  & type  2_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log


----------------------------------------------------------------------------------------------------------------
--ddl and dml to support ddl for non-audit column that use a default of user, or have the mistake of quoted user
----------------------------------------------------------------------------------------------------------------

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 3_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.run >> 3_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log 2>&1  & type  3_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log


----------------------------------------------------------------------------------------------------------------
--drop un-required tables
----------------------------------------------------------------------------------------------------------------

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 4_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.run >> 4_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log 2>&1  & type  4_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log


----------------------------------------------------------------------------------------------------------------
--removed default on create_datetime
----------------------------------------------------------------------------------------------------------------

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 5_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.run >> 5_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log 2>&1  & type  5_0_batch_oms_owner_ddl_v5_0_0_9_3_audit.log


-------------------------------
--post verification  
-------------------------------

psql --no-psqlrc -U oms_owner -d dbname -h hostname -a -f  verification_scripts_final.sql > verification_scripts_final.log 2>&1


------------------------------------------------------------------------------
--post verification with queries that have no restrictions in the where clause 
------------------------------------------------------------------------------

psql --no-psqlrc -U oms_owner -d dbname -h hostname -a -f  verification_scripts_final_no_restrictions.sql > verification_scripts_final_no_restrictions.log 2>&1




