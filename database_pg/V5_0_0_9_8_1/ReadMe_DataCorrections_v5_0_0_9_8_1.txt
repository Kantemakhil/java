---*****data_corrections subfolder - oms_owner 

-- Creates a table copy of table offender_bookings containing data only for bookings that are in transfer but have a living unit associated with;
-- removes the living unit association for the offender bookings; 

-- Creates a table copy of table agency_internal_locations content;
-- sets the no_of_occupant to 0;
-- calculates the correct occupancy based on bookings and updates the no_of_occupant;

Step 1: Create the table/data copy tables:

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_dml_data_correction_5_0_0_9_8_1.run >> 1_0_batch_oms_owner_dml_data_correction_5_0_0_9_8_1.log 2>&1  & type 1_0_batch_oms_owner_dml_data_correction_5_0_0_9_8_1.log

-- Please check that the tables were created correctly and have the original data
-- Retain the copy tables as safe backups until the changes are reviewed and approved

Step 2: Execute data corrections:

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_data_correction_5_0_0_9_8_1.run >> 2_0_batch_oms_owner_dml_data_correction_5_0_0_9_8_1.log 2>&1  & type 2_0_batch_oms_owner_dml_data_correction_5_0_0_9_8_1.log

-- Please inspect the log for errors and report any issues.
-- Retain the copy tables as a safe backups until the change is reviewed and approved.


Step 3: Spool the differences for review and review for accuracy and completeness:
        --generates files "bkgs_in_trn_liv_unit_original_vs_corrections_v500981.txt" and "occupancy_original_vs_corrections_v500981.txt"

psql --no-psqlrc -U oms_owner -d dbname -h hostname 

\i spool_bkgs_in_trn_liv_unit_original_vs_corrections_v500981.sql

\i spool_occupancy_original_vs_corrections_v500981.sql


  When the data correction fixes has passed testing, please drop table agency_internal_locations_cpy_500981 and offender_bookings_partial_cpy_500981.tab
  
Step 4: Spool bookings living unit where the living unit is not the lowest level: 
        --generates file "bkgs_liv_unit_not_lowest_level_v500981.txt"
		--if the file contains any data, please discuss with Syscon as a manual correction using the application is required 

psql --no-psqlrc -U oms_owner -d dbname -h hostname 

\i spool_bkgs_liv_unit_not_lowest_level_v500981.sql

