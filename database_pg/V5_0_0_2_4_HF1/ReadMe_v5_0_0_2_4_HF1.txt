--***** ddl subfolder

psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_ddl_5_0_0_2_4_HF1.run >> 1_0_batch_oms_owner_ddl_5_0_0_2_4_HF1.log 2>&1  & type 1_0_batch_oms_owner_ddl_5_0_0_2_4_HF1.log

--***** dml subfolder

psql --no-psqlrc -U oms_owner -d dbname -h hostname  -f 1_0_batch_oms_owner_dml_5_0_0_2_4_HF1.run >>  1_0_batch_oms_owner_dml_5_0_0_2_4_HF1.log 2>&1  & type 1_0_batch_oms_owner_dml_5_0_0_2_4_HF1.log

The application has been modified to work with encrypted password in the system settings configuration.
Please re-enter the following password information from the application, System Settings screen.
The application will encrypt when persisting the information in the database.

1.re-enter and save the password for the automation user: 'serverConfig'/'AUTOMATION_USER';

2.re-enter and save the password for the email clicksend: 'eMail'/'CLICKSEND';

3.re-enter and save the password for the insights: 'INSIGHTS'/'AUTH';

4.re-enter and save the password for the sms clicksend: 'SMS'/'CLICKSEND_SMS';