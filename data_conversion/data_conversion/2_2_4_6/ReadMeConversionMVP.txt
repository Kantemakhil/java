-------------------------
About staging schema:
-------------------------

Scheam OMS_STAGE is expected to be present before applying/running and conversion pbjects.

-------------------------
About order of execution:
-------------------------

Please execute the conversion in the order indicated by the numbers in the name of each folder.
Within each folder, please execute the scripts in the order indicated by the numbers in the name of batch file.
The numbers used for the folder names and for the batch file names withing the folders were spaces out to allow for future development without having to change and re-arrange the existing batch files.


-------------------------------
About some important sequences:
-------------------------------

Package conv_off_core_1 adjust the offender sequences. It is possible that , due to test data, the number value of offender_id_display is so high that reducing this number before executing the conversion might be desired. Please check the following :
Method conv_utilities.reset_offender_id_display() called by package conv_off_core_1 is going to possibly adjust the sequence to a very high value.

   --get max offender id display
   SELECT NVL(MAX(offender_id_display_no), 0) 
     FROM (SELECT CONV_UTILITIES.off_id_display_to_no(offender_id_display) as offender_id_display_no FROM offenders); 
  
   SELECT offender_id_display.nextval FROM dual;   



If required to align the three sequences, offender_id, alias_offender_id and offender_id_display, to start at the same number, please modify code in package conv_off_core_1 
to call method conv_utilities.reset_all_offender_seq instead of the individual methods for each sequence. 
Again, if the number value of offender_id_display is so far compared with the offender_id ahead, please consider manual clean-up first or all three sequences will end up starting at a very high value.

   SELECT NVL(MAX(offender_id), 0) FROM offenders; 
   --get sequence lv_off_id_display_no_max_val
   SELECT offender_id.nextval  FROM dual; 

   --get max alias offender id
   SELECT NVL(MAX(alias_offender_id), 0)  FROM offenders; 
   --get sequence lv_alias_offender_id
   SELECT offender_alias_id.nextval  FROM dual; 

   --get max offender id display
   SELECT NVL(MAX(offender_id_display_no), 0) 
     FROM (SELECT CONV_UTILITIES.off_id_display_to_no(offender_id_display) as offender_id_display_no FROM offenders); 
  
    select offender_id_display.nextval from dual;   

-------------------------
About disabling triggers:
-------------------------
The data conversion disables most of the triggers before the conversion and enables them at the end. As oms_owner, please run the following queries and retain the information in order to validate that all required triggers and constraints are enabled properly at the end of conversion.

select count(*) from all_triggers where owner= 'OMS_OWNER' and status = 'ENABLED' 
select * from all_triggers where owner= 'OMS_OWNER' and status = 'DISABLED' 

  SELECT count(*) --constraint_name, table_name, constraint_type 
        FROM all_constraints
       WHERE UPPER(constraint_type) IN ('R') -- R U P C O
         AND UPPER(status) = 'ENABLED'
         AND owner = 'OMS_OWNER'
      AND constraint_name NOT LIKE '%==$0'
      AND  UPPER(table_name) NOT LIKE 'AQ$_%'
      AND upper(table_name) NOT IN ('QM_QUEUETABLE'); 
      
SELECT count(*) --constraint_name, table_name, constraint_type 
        FROM all_constraints
       WHERE UPPER(constraint_type) IN ('R') -- R U P C O
         AND UPPER(status) = 'DISABLED'
         AND owner = 'OMS_OWNER'
      AND constraint_name NOT LIKE '%==$0'
      AND  UPPER(table_name) NOT LIKE 'AQ$_%'
      AND upper(table_name) NOT IN ('QM_QUEUETABLE');       
      
SELECT * --constraint_name, table_name, constraint_type 
        FROM all_constraints
       WHERE UPPER(constraint_type) IN ('R') -- R U P C O
         AND UPPER(status) = 'DISABLED'
         AND owner = 'OMS_OWNER'
      AND constraint_name NOT LIKE '%==$0'
      AND  UPPER(table_name) NOT LIKE 'AQ$_%'
      AND upper(table_name) NOT IN ('QM_QUEUETABLE');    
      
-----------------------------------
About checking the conversion logs:
-----------------------------------

select * from oms_conv.conv_logs order by log_id;


