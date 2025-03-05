CREATE OR REPLACE PROCEDURE oms_owner.log_db_version_control(p_database_version character varying, p_db_version_notes character varying)  
language plpgsql 
AS $procedure$  
DECLARE
BEGIN  
      
       INSERT INTO db_version_control (database_version, db_version_notes)
         SELECT  p_database_version, p_db_version_notes  WHERE NOT EXISTS (SELECT 1 FROM db_version_control WHERE database_version = p_database_version);
   
END;
$procedure$
;

