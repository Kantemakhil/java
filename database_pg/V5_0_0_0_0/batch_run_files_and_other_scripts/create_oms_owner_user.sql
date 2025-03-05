CREATE USER oms_owner with password 'oms_owner';


--oms_owner user id not a superuser based on discussion with Om 
--if superuser is required in the future, grant as below:

--for self managed database
--ALTER ROLE oms_owner SUPERUSER CREATEDB CREATEROLE;

--for AWS RDS
--GRANT rds_superuser TO oms_owner;

