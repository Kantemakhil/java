---***** dml subfolder - oms_owner DML 
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_custom_SouthAustralia_5_0_0_2_4_HF.run >> 2_0_batch_oms_owner_dml_custom_SouthAustralia_5_0_0_2_4_HF.log 2>&1  & type 2_0_batch_oms_owner_dml_custom_SouthAustralia_5_0_0_2_4_HF.log

---***** ImportQucikAction:

1.Log into the Application
2.Navigate to the screen(System Administration-->System Automation-->Import Qucik Action API)
3.Click on Browse and pick the zip.
4.Click on import
5.On the below grid check the quick action and then save it.