CREATE OR REPLACE PROCEDURE oms_owner.log_db_version_control_patches(p_database_version character varying, p_database_patch_version character varying, p_db_patch_notes character varying, p_batch_run_file_name character varying)
 LANGUAGE plpgsql 
AS $procedure$  
DECLARE
BEGIN  
      
       INSERT INTO oms_owner.db_version_control_patches (database_version, database_patch_version, db_patch_notes, batch_run_file_name)
         VALUES (p_database_version, p_database_patch_version, p_db_patch_notes, p_batch_run_file_name);
   
END;
$procedure$
;

GRANT SELECT, INSERT ON oms_owner.db_version_control_patches TO postgres;
GRANT EXECUTE ON PROCEDURE oms_owner.log_db_version_control_patches TO postgres;