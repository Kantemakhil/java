---*****data_corrections subfolder - oms_owner 

--Terminology
--"offender unique identifier" refers to the information visisble in the application header block that uniquely identifies a person who is an offender
--the "offender unique identifier" is persisted in the database in table.column=offenders.offender_id_display

Step 1: 
--retain some information from table offenders for the offenders that have leading zeros in the offender unique identifier 
--retain some information from table offender_identifiers for the identifiers related to merging offenders
--retain some information from table offender_identifiers for the identifiers related to changes of the offender unique identifier 
--keep the csv files and inspect them using Notepad++
--provide the csv files to the business analyst group for testing of the fix

psql --no-psqlrc -U oms_owner -d dbname -h hostname 

\i spool_offenders_leadzeros_before_fix.sql

\i spool_identifiers_merge_before_fix.sql

\i spool_identifiers_changes_before_fix.sql


Step 2: 
--correct the data: 
--1. remove leading zeroes from the offender unique identifier stored in offenders.offender_id_display

--2. adjust the offender unique identifier stored as an identifier in table offender_identifiers when updated using screen UPDOFFID ("Update Offender Id");
-----------screen UPDOFFID creates an offender identifier with identifier type as configured system profile 'LABEL','OFF_ID_CODE' or hard coded identifier type 'SBI' if none defined in the system profile

--3. adjust offender unique identifier stored as an identifier in table offender_identifiers when merging two offenders using screen OUMMEROF ("Merge Offenders");
-----------screen OUMMEROF (Merge Offenders) creates an offender identifier with identifier type as configured in system profile 'PRODUCT','MERGE_ID'


psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_SOAU_data_correction_500932.run >> 1_0_batch_oms_owner_dml_custom_SOAU_data_correction_500932.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_SOAU_data_correction_500932.log


Step 3: 
--please inspect the log for errors and verification queries and report any issues
