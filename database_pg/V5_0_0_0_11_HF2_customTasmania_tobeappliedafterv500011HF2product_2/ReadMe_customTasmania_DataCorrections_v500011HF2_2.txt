---*****data_corrections subfolder - oms_owner 

Step 1: 
--retain some information from table agency_internal_locations related to the data fix
--keep the csv files and inspect them using Notepad++
--provide the csv files to the business analyst group for testing of the fix

psql --no-psqlrc -U oms_owner -d dbname -h hostname 

\i spool_agency_internal_locations_safe_copy.sql

\i spool_agy_int_locs_hous_locs_dash_issue.sql

\i spool_incorrect_description.sql 

Step 2: 
--correct the data: correct location code, description and user description 
--
--first a specific incorrect record in Tasmania('MHWP-ROLAND-4-AA') is being taken care to avoid duplicates (try to delete the incorrect record, if not possible update the internal location code )
--location code is being corrected by replacing dash with underscore
--if location code contains leading or trailing spaces, they are NOT removed
--description is being corrected by recreating the description for records where description does not match the calculated description 
--the user description is corrected only for records that have dashes in the user description and only for records where description does not match the calculated description; in this case the user description is being updated with the corrected description  


psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_Tasmania_data_correction_5_0_0_0_11_HF2_2.run >> 1_0_batch_oms_owner_dml_custom_Tasmania_data_correction_5_0_0_0_11_HF2_2.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_Tasmania_data_correction_5_0_0_0_11_HF2_2.log

Step 3: 
--please inspect the log for errors and verification queries and report any issues
