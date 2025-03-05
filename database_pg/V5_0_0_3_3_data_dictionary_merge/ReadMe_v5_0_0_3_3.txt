--V5_0_0_3_3 re-applies the data dictionary corrected for V3/V4
--V5_0_0_3_3 MUST be run after v5_0_0_3_2 and prior to v5_0_0_3_4
--V5_0_0_3_3 MUST be run as oms_owner

---***** ddl subfolder - V5_0_0_3_3 oms_owner  

--re-applies the original data dictionary corrected for V3/V4
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_v5_0_0_3_3.run >> 1_0_batch_oms_owner_v5_0_0_3_3.log 2>&1  & type  1_0_batch_oms_owner_v5_0_0_3_3.log


--re-applies the data dictionary for V5 db patches V5_0_0_0_1 to V5_0_0_3_2
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_v5_0_0_3_3.run >> 2_0_batch_oms_owner_v5_0_0_3_3.log 2>&1  & type  2_0_batch_oms_owner_v5_0_0_3_3.log

