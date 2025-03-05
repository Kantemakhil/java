---*****data_corrections subfolder - oms_owner 

Step 1: 
--retain a safe copy for table agency_internal_locations before the data fix

psql --no-psqlrc -U oms_owner -d dbname -h hostname 

\i spool_agency_internal_locations_safe_copy.sql

Step 2: 
--correct the data:
--update housing level codes for LRP agency.
--Add new LRP housing location, and set all current housing as its children.
--Update the housing user description based on the new hierarchy.
--Set the capacity and number of occupants for the newly added record.

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_customTasmania_data_correction_5_0_0_2_4_HF2_2.run >> 2_0_batch_oms_owner_dml_customTasmania_data_correction_5_0_0_2_4_HF2_2.log 2>&1  & type 2_0_batch_oms_owner_dml_customTasmania_data_correction_5_0_0_2_4_HF2_2.log

Step 3: 
--please inspect the log for errors and verification queries and report any issues
