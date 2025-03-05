Step 1:

--apply custom DML

psql --no-psqlrc -U oms_owner -d dbname -h hostname -f 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_0_10.run  >>  1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_0_10.log 2>&1  & type 1_0_batch_oms_owner_dml_custom_Tasmania_5_0_0_0_10.log

Step 2: Import processes:

QuickActions_15424.zip
QuickAction_19056.zip
QuickAction_19058.zip

CommonProcess_15424.zip
CommonProcess_19056.zip
CommonProcess_19058.zip

Processes_15424.zip

[
Note:
Read the link to use Import process.
https://syscon.atlassian.net/wiki/spaces/V4/pages/2290188319/Video+Explainers
]