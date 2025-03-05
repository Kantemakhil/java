More detailed instructions as well as options for using PgAdmin tool and checking the database can be found in the "PostgreSQL Setup Guide for V5 Elite database" guide in wiki.


--Create user/role oms_owner
psql --no-psqlrc -U postgres -d postgres -h hostname  -a -f create_oms_owner_user.sql

--Create database instance
psql --no-psqlrc -U postgres -d postgres -h hostname -a -f create_database.sql

--Create the oms_owner schema
psql --no-psqlrc -U oms_owner -d dbname -h hostname -a -f create_oms_owner_schema.sql

--Create Elite database tablespaces
psql --no-psqlrc -U postgres -d postgres -h hostname -a -f create_tablespaces.sql

--Create database versioning objects
psql --no-psqlrc -U oms_owner -d dbname -h hostname -f 1_0_batch_oms_owner_db_versioning.run  >>  1_0_batch_oms_owner_db_versioning.log 2>&1  & type 1_0_batch_oms_owner_db_versioning.log

--Create the oms_owner schema objects V5_0_0_0_0
psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 1_0_batch_oms_owner_objects_5_0_0_0_0.run >> 1_0_batch_oms_owner_objects_5_0_0_0_0.log 2>&1  & type 1_0_batch_oms_owner_objects_5_0_0_0_0.log

psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 2_0_batch_oms_owner_objects_5_0_0_0_0.run >> 2_0_batch_oms_owner_objects_5_0_0_0_0.log 2>&1  & type 2_0_batch_oms_owner_objects_5_0_0_0_0.log

psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 3_0_batch_oms_owner_objects_5_0_0_0_0.run >> 3_0_batch_oms_owner_objects_5_0_0_0_0.log 2>&1  & type 3_0_batch_oms_owner_objects_5_0_0_0_0.log

psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 4_0_batch_oms_owner_objects_5_0_0_0_0.run >> 4_0_batch_oms_owner_objects_5_0_0_0_0.log 2>&1  & type 4_0_batch_oms_owner_objects_5_0_0_0_0.log

psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 5_0_batch_oms_owner_objects_5_0_0_0_0.run >> 5_0_batch_oms_owner_objects_5_0_0_0_0.log 2>&1  & type 5_0_batch_oms_owner_objects_5_0_0_0_0.log

psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 6_0_batch_oms_owner_objects_5_0_0_0_0.run >> 6_0_batch_oms_owner_objects_5_0_0_0_0.log 2>&1  & type 6_0_batch_oms_owner_objects_5_0_0_0_0.log

psql --no-psqlrc -U oms_owner  -d dbname -h hostname -f 7_0_batch_oms_owner_update_dictionary_5_0_0_0_0.run >> 7_0_batch_oms_owner_update_dictionary_5_0_0_0_0.log 2>&1  & type 7_0_batch_oms_owner_update_dictionary_5_0_0_0_0.log

--Create mandatory V5 application users
psql --no-psqlrc -U oms_owner -d dbname -h hostname -a -f create_mandatory_oms_users_list.sql
