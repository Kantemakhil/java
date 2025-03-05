
--**************************************************************************************************************--
--                  Insert/Update/Delete Audit Changes                                                          --
--**************************************************************************************************************--

---***** ddl subfolder - V5_0_0_9_5_2 postgres 

Step 1: --Please execute this step as postgres user on the Audit Storage Database 

Modify the audit log table in the audit schema in the audit storage database (replace "auditDatabase" and "auditServer" with the appropriate values):

psql -U postgres -d auditStorageDatabase -h auditStorageServer

\i elite_audit_log_v500952.tab 

The above script will auto poulate a new mandatory column, audit_id, in table audit_log.elite_audit_log.
Please execute the following statement and retain the maximum value plus one:

select COALESCE(MAX(audit_id), 0) + 1 from audit_log.elite_audit_log;

-----------------------------------------------------------------

Step 2: --manually adjust script "iudsql_audit_id_seq_v500952.seq" provided by Syscon

Sequence "iudsql_audit_id_seq" generates a unique identifier for each audit entry (insert / update/ delete application audit).
The sequence covers both the remote audit table (audit_log.elite_audit_log) and the fallback audit table (oms_owner.elite_generic_log).
In order the preserve the data that already exists in the remote audit table (audit_log.elite_audit_log) , please start the sequence at the appropriate value.

Please manually modify script "...\ddl\iudsql_audit_id_seq_v500952.seq"  with the value established in Step 1.

-----------------------------------------------------------------

Step 3: -Please Execute this step as oms_owner user on the Elite database

---***** ddl subfolder - V5_0_0_9_5_2 oms_owner  
psql --no-psqlrc -U oms_owner  -d  dbname -h hostname -f 1_0_batch_oms_owner_5_0_0_9_5_2.run >> 1_0_batch_oms_owner_5_0_0_9_5_2.log 2>&1  & type  1_0_batch_oms_owner_5_0_0_9_5_2.log

-----------------------------------------------------------------

--**************************************************************************************************************--
--                  View Audit                                                                                  --
--**************************************************************************************************************--

---***** ddl subfolder - V5_0_0_9_5_2 postgres 

Step 1: --Please execute this step as postgres user on the Audit Storage Database  

psql -U postgres -d auditStorageDatabase -h auditStorageServer 


\i view_log_id_seq_v500952.seq
\i elite_view_log_v500952.tab 