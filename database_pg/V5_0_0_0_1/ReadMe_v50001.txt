More detailed instructions as well as options for using PgAdmin tool and checking the database can be found in the "PostgreSQL Setup Guide for V5 Elite database" guide in wiki.


---***** V5_0_0_0_1 oms_owner DDL 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_0_1.run >> 1_0_batch_oms_owner_ddl_5_0_0_0_1.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_0_1.log
