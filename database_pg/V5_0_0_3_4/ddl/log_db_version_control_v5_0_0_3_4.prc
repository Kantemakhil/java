CREATE OR REPLACE PROCEDURE oms_owner.log_db_version_control(p_database_version character varying, p_db_version_notes character varying)
 LANGUAGE plpgsql 
AS $procedure$  
DECLARE
BEGIN  
      
       INSERT INTO oms_owner.db_version_control (database_version, db_version_notes)
         SELECT  p_database_version, p_db_version_notes  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.db_version_control WHERE database_version = p_database_version);
   
END;
$procedure$
;


GRANT SELECT, INSERT ON oms_owner.db_version_control TO postgres;
GRANT EXECUTE ON PROCEDURE oms_owner.log_db_version_control TO postgres;

