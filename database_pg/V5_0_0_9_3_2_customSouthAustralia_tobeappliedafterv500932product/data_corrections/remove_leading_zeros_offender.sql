-----------------------------------------------------------------------------------------------------------------------------------

--Terminology
--"offender unique identifier" refers to the information visisble in the application header block that uniquely identifies a person who is an offender
--the "offender unique identifier" is persisted in the database in table.column=offenders.offender_id_display


--offenders with offender unique identifier containing leading zeros counts before changes
select count(*) from oms_owner.offenders where offender_id_display <> LTRIM(offender_id_display, '0');

--identifiers configuration
select profile_value from system_profiles where profile_type = 'LABEL' AND profile_code = 'OFF_ID_CODE';
select profile_value from system_profiles where profile_type = 'PRODUCT' AND profile_code = 'MERGE_ID';

--offender identifiers related to merge aka "offender unique identifier" saved as identifiers
select count(*) from offender_identifiers 
  where identifier_type = (select profile_value from system_profiles where profile_type = 'PRODUCT' AND profile_code = 'MERGE_ID') 
    and identifier <> LTRIM(identifier, '0');


--offender identifiers related to changes to the offender unique identifier aka "offender unique identifier" saved as identifiers
select count(*) from offender_identifiers 
  where identifier_type = COALESCE((select profile_value from system_profiles where profile_type = 'LABEL' AND profile_code = 'OFF_ID_CODE'), 'SBI') 
    and identifier <> LTRIM(identifier, '0');
    

-------------------------------------------------- corrections ---------------------------------------------------------------------
--1. remove leading zeroes from the offender unique identifier stored in offenders.offender_id_display

UPDATE offenders  
   SET  modify_user_id = UPPER(USER)
       ,modify_datetime = current_timestamp 
       ,offender_id_display = LTRIM(offender_id_display, '0')
 WHERE offender_id_display <> LTRIM(offender_id_display, '0');


--2. adjust the offender unique identifier stored as an identifier in table offender_identifiers when updated using screen UPDOFFID ("Update Offender Id");
-----------screen UPDOFFID creates an offender identifier with identifier type as configured system profile 'LABEL','OFF_ID_CODE' or hard coded identifier type 'SBI' if none defined in the system profile

update offender_identifiers 
   set  modify_user_id = UPPER(USER)
       ,modify_datetime = current_timestamp 
       ,identifier = LTRIM(identifier, '0')
  where identifier_type = (select profile_value from system_profiles where profile_type = 'PRODUCT' AND profile_code = 'MERGE_ID') 
    and identifier <> LTRIM(identifier, '0'); 
  
--3. adjust offender unique identifier stored as an identifier in table offender_identifiers when merging two offenders using screen OUMMEROF ("Merge Offenders");
-----------screen OUMMEROF (Merge Offenders) creates an offender identifier with identifier type as configured in system profile 'PRODUCT','MERGE_ID'
  
update offender_identifiers 
   set  modify_user_id = UPPER(USER)
       ,modify_datetime = current_timestamp 
       ,identifier = LTRIM(identifier, '0')
  where identifier_type =  COALESCE((select profile_value from system_profiles where profile_type = 'LABEL' AND profile_code = 'OFF_ID_CODE'), 'SBI') 
    and identifier <> LTRIM(identifier, '0'); 
    
------------------------------------------------------------------------------------------------------------------------------------
  
--offenders with offender unique identifier containing leading zeros counts before changes
select count(*) from oms_owner.offenders where offender_id_display <> LTRIM(offender_id_display, '0');

--offender identifiers related to merge
select count(*) from offender_identifiers 
  where identifier_type = (select profile_value from system_profiles where profile_type = 'PRODUCT' AND profile_code = 'MERGE_ID') 
   and identifier <> LTRIM(identifier, '0');


--offender identifiers related to changes to the offender unique identifier
select count(*) from offender_identifiers 
  where identifier_type = COALESCE((select profile_value from system_profiles where profile_type = 'LABEL' AND profile_code = 'OFF_ID_CODE'), 'SBI') 
    and identifier <> LTRIM(identifier, '0');
                   



