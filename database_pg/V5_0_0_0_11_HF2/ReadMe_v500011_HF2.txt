
---***** ddl subfolder - V5_0_0_0_11_HF2 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_0_11_HF2.run >> 1_0_batch_oms_owner_ddl_5_0_0_0_11_HF2.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_0_11_HF2.log


---*****dml subfolder - V5_0_0_0_11_HF2 oms_owner DML 

--!!Note: dml file system_settings_v500011HF2_20221006.sql needs to be updated to reflect the desired URLs/setup
--   This can be done manually before running the DML batch run file, or the script can be applied as is and the value can be modified from the application.


psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 2_0_batch_oms_owner_dml_5_0_0_0_11_HF2.run >> 2_0_batch_oms_owner_dml_5_0_0_0_11_HF2.log 2>&1  & type 2_0_batch_oms_owner_dml_5_0_0_0_11_HF2.log

---*****import_processes subfolder
1. first import the quick actions in zip file "updateCourseSchActions.zip" using the application "Import Quick Action API" screen
2. second import the processes in zip file "updateCourseSch.zip" using the application "Import Process" screen and selecting the "Process/PROCESS" from the "Import" LOV 
3. third import the processes in zip file "Sentence_Calculation process.zip" using the application "Import Process" screen and selecting the "Process/PROCESS" from the "Import" LOV 

4. first import the quick actions in zip file "quickActions_17507&17509_stories.zip" using the application "Import Quick Action API" screen
5. second import the common processes in zip file "authentication_common_process.zip" using the application "Import Process" screen and selecting the "Common Process/CMNPROCESS" from the "Import" LOV 
6. third import the processes in zip file "17507&17508_process.zip" using the application "Import Process" screen and selecting the "Process/PROCESS" from the "Import" LOV 