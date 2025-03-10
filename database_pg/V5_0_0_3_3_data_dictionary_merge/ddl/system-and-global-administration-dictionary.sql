COMMENT ON TABLE APPLICATION_LOGS IS 'For scheduled jobs that form part of the Elite application, records the detailed job activity.';
COMMENT ON COLUMN APPLICATION_LOGS.APPLICATION_LOG_ID IS 'The unique identifier of the application log. Generated from sequence ( APPLICATION_LOG_ID )';
COMMENT ON COLUMN APPLICATION_LOGS.INTERFACE_ID IS 'Additional numeric identifier that may be included with the log record.';
COMMENT ON COLUMN APPLICATION_LOGS.JOB_CONTROL_ID IS 'The job control ID of the job.';
COMMENT ON COLUMN APPLICATION_LOGS.LOG_MESSAGE IS 'The detailed log message.';
COMMENT ON COLUMN APPLICATION_LOGS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN APPLICATION_LOGS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN APPLICATION_LOGS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN APPLICATION_LOGS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN APPLICATION_LOGS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE COPY_TABLES IS 'Defines what offender information from immediately previous booking is to be copied into a new booking created via Institutional Admission or Community Intake.';
COMMENT ON COLUMN COPY_TABLES.TABLE_OPERATION_CODE IS 'A code used to identify the type of operation to perform. Ref Domain ( TABLE_OPER ) Valid Code values are (COP COPY, DEL : DELETE) Only copy has been implemented, so only records with COP operation code are used.';
COMMENT ON COLUMN COPY_TABLES.MOVEMENT_TYPE IS 'A code used to identify the type of movement (e.g., Admission, Release, Transfer). Ref Domain ( MOVE_TYPE )';
COMMENT ON COLUMN COPY_TABLES.MOVEMENT_REASON_CODE IS 'A code used to identify the reason for the movement. Ref Domain ( MOVE_RSN )';
COMMENT ON COLUMN COPY_TABLES.TABLE_NAME IS 'The name of the database table upon which to copy booking records if booking data is present.';
COMMENT ON COLUMN COPY_TABLES.ACTIVE_FLAG IS 'A flag (Y/N) used to identify whether data will be copied from the identified table for the noted movement type and reason.';
COMMENT ON COLUMN COPY_TABLES.LIST_SEQ IS 'A column used to control the order tables should be copied in. This is especially important for tables that have data dependencies.';
COMMENT ON COLUMN COPY_TABLES.COL_NAME IS 'For PK columns that are populated based on an Oracle sequence, identifies the column name.';
COMMENT ON COLUMN COPY_TABLES.EXPIRY_DATE IS 'The date the record was deactivated.';
COMMENT ON COLUMN COPY_TABLES.PARENT_TABLE IS 'For tables that are child to other tables in a parent-child relationship, identifies the table''s parent. The parent table is always populated before the child table, regardless of the value applied in list_seq column.';
COMMENT ON COLUMN COPY_TABLES.SEQ_NAME IS 'For PK columns that are populated based on an Oracle sequence, identifies the sequence name.';
COMMENT ON COLUMN COPY_TABLES.UPDATE_ALLOWED_FLAG IS 'Not used.';
COMMENT ON COLUMN COPY_TABLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN COPY_TABLES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN COPY_TABLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN COPY_TABLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN COPY_TABLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE LOCKED_MODULES IS 'Used to facilitate locking of user funcionality to a single user. Used for check printing and clearing of payments to beneficiaries by only a single user at a time by Caseload. (Trust Accounting and Community Financials), and by Institutional Counts to facilitate locking of facility count administration to a single user per facility.';
COMMENT ON COLUMN LOCKED_MODULES.CASELOAD_ID IS 'The code used to uniquely identify the Caseload that the lock is restricted to. This is used to restrict and define the scope of the lock.';
COMMENT ON COLUMN LOCKED_MODULES.LOCKED_DATE IS 'The date the module was locked.';
COMMENT ON COLUMN LOCKED_MODULES.MODULE_NAME IS 'The name of the module (Screen) that has placed an exclusive lock on the record. This is used to restrict the scope of the lock.';
COMMENT ON COLUMN LOCKED_MODULES.USER_ID IS 'The code used to uniquely identify a user account.';
COMMENT ON COLUMN LOCKED_MODULES.ACCOUNT_CODE IS 'The trust account code that has been locked. This is used to restrict the scope of the lock.';
COMMENT ON COLUMN LOCKED_MODULES.AGY_LOC_ID IS 'For Institutional Counts, the agency location that has placed an exclusive lock on the record.';
COMMENT ON COLUMN LOCKED_MODULES.OFFENDER_ID IS 'An internal unique identifier for the Offender. This is used to restrict the scope of the lock.';
COMMENT ON COLUMN LOCKED_MODULES.SESSION_ID IS 'A unique identifier for the user session.';
COMMENT ON COLUMN LOCKED_MODULES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN LOCKED_MODULES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN LOCKED_MODULES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN LOCKED_MODULES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN LOCKED_MODULES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MERGE_LOGS IS 'Log tables for merge. No longer used--replaced by merge_transaction_logs.';
COMMENT ON TABLE MERGE_OFFENDER_RECORDS_TMP IS 'Used by merge business logic to track record updates during a merge transaction to support linking child records.';
COMMENT ON COLUMN MERGE_OFFENDER_RECORDS_TMP.TABLE_NAME IS 'The name of the table that has had records updated.';
COMMENT ON COLUMN MERGE_OFFENDER_RECORDS_TMP.OFFENDER_ID IS 'Not used.';
COMMENT ON COLUMN MERGE_OFFENDER_RECORDS_TMP.PK_FIRST_COLUMN IS 'First key identifier of the merged record as defined in transfer_booking_tables.';
COMMENT ON COLUMN MERGE_OFFENDER_RECORDS_TMP.PK_SECOND_COLUMN IS 'Second key identifier of the merged record as defined in transfer_booking_tables. NULL for tables with a single key value.';
COMMENT ON COLUMN MERGE_OFFENDER_RECORDS_TMP.SEQ_INCREMENT IS 'For tables with a sequence column as defined on transfer_booking_tables, identifies the offset used for populating into the target table. E.g., if the maximum sequence on the target offender for table X is 3, then an offset of 3 would be used when transferring the records on table X from the source offender, increasing the sequence value of transferred records by the offset.';
COMMENT ON COLUMN MERGE_OFFENDER_RECORDS_TMP.SEQ_NO IS 'Not used.';
COMMENT ON TABLE MERGE_OFFENDER_SQLS IS 'Used by the merge business logic when running in debug mode, to identify the SQL generated by the merge process, and the generation order.';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.GENERATION_TIME IS 'The date and time the record was created.';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.SQL_ID IS 'A unique identifier of the record. Generated from sequence ( SQL_ID )';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.SQL_TEXT IS 'The DML generated.';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MERGE_OFFENDER_SQLS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MERGE_OFFENDER_TABLES IS 'Describes tables for dynamic SQL for full offender record merge.';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.TABLE_NAME IS 'The name of the table';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.ADDITIONAL_UPDATE_RULE IS 'For tables that require multiple columns updated on a merge, identifies secondary column(s) for update.';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.APPLICATION_CODE IS 'Identifies the business area that the table belongs to.';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.CALLING_PROCEDURE IS 'Not used.';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.COMPLETED_FLAG IS 'A flag (Y/N) identifying whether the table is available for selection by the rule-based engine.';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.OBJECT_CLASS IS 'The object class of the record in common object tables such as Addresses, Phones, etc...';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.OFFENDER_ID_COLUMN IS 'The column which stores the Offender_ID';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.PARENT_TABLE IS 'For parent child relationships, identifies the parent of the selected table.';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.PK_TYPE IS 'The type of the table primary key. If the value is SURROGATE, the surrogate key column is used. Values ( SURROGATE, PARTIAL, PARENT )';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.PROCESS_METHOD IS 'The process method for the table.  [RULE : handled by rule-based SQLs, CUSTOM : customized ]';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.ROOT_FLAG IS 'A flag (Y/N) indicating whether the table is a root table for processing by the rules based engine. This is the root (starting point) wrt processing, not necessarily for the model as a whole. I.e., this root may have a parent table, and there may be other processes associated with that parent table, also with the root flag marked.';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.SEQ_COLUMN IS 'For tables that have a sequence column as part of the primary key, identifies the name of the column that requires re-sequencing during merge';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MERGE_OFFENDER_TABLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MERGE_PROCESSES IS 'Identifies business areas in the merge process, allowing the grouping and organization of business logic, and for the refinement of merge business rules against each logical area.';
COMMENT ON COLUMN MERGE_PROCESSES.PROCESS_ID IS 'The unique identifier of the merge process record.';
COMMENT ON COLUMN MERGE_PROCESSES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the identified business area is included in the merge process.';
COMMENT ON COLUMN MERGE_PROCESSES.DEFAULT_ON_FLAG IS 'A flag (Y/N) indicating whether the business area is automatically defaulted to be included in the transfer process. Not used for merge, as all data must be included in the merge process.';
COMMENT ON COLUMN MERGE_PROCESSES.DESCRIPTION IS 'The description of the business area.';
COMMENT ON COLUMN MERGE_PROCESSES.ENDING_PROCESS_FLAG IS 'A flag (Y/N) indicating whether this business area contains the last (terminating) set of business rules for the merge business logic.';
COMMENT ON COLUMN MERGE_PROCESSES.FIRST_RULE_ID IS 'The identifier of the first of a set of rules associated with this business process. These are executed after the initialization process (if any) is executed, based on details stored in merge process rules.';
COMMENT ON COLUMN MERGE_PROCESSES.FIRST_RULE_ID_OFF IS 'For transfer logic, if the business area is turned off for transfer, invokes this set of business rules. This allows for booking reference clean up to data that will not be transferred with the booking.';
COMMENT ON COLUMN MERGE_PROCESSES.INIT_PROC IS 'Identifies an initialization procedure that must be executed before the main business process.';
COMMENT ON COLUMN MERGE_PROCESSES.MANDATORY_ON_FLAG IS 'A flag (Y/N) indicating whether the business area must be included in the transfer process. Not used for merge, as all data must be included in the merge process.';
COMMENT ON COLUMN MERGE_PROCESSES.NEXT_CHECK_PROC IS 'A business rule that must be invoked after the set of rules associated with first_rule_id, and after the initialization procedure.';
COMMENT ON COLUMN MERGE_PROCESSES.NEXT_CHECK_PROC_TYPE IS 'An indicator of whether next_check_proc is a PROCEDURE or a FUNCTION.';
COMMENT ON COLUMN MERGE_PROCESSES.PROCESS_NAME IS 'The name of the business process.';
COMMENT ON COLUMN MERGE_PROCESSES.RESULT_FALSE_NEXT_ID IS 'Identifier the process_id of next business area in the merge process, and allows for an alternate flow based on results from the current business area.';
COMMENT ON COLUMN MERGE_PROCESSES.RESULT_TRUE_NEXT_ID IS 'Identifies the process_id of the next business area in the merge process.';
COMMENT ON COLUMN MERGE_PROCESSES.STARTING_PROCESS_FLAG IS 'A flag (Y/N) indicating the first business area in the merge process. Each transaction type must have exactly one process defined as the starting process.';
COMMENT ON COLUMN MERGE_PROCESSES.TERM_PROC IS 'The identifier for a final termination procedure that must be executed after all other business process rules for this business area have been completed. Used, for example, to update the status of the merge in progress at the end of each business area.';
COMMENT ON COLUMN MERGE_PROCESSES.TIMEFRAME_REQUIRED_FLAG IS 'A flag (Y/N) indicating whether the date range for transfer of data must be entered for this business area. Used to determine exactly which records to transfer from one offender to another for business areas that are not explicitly tied to the booking, but which may contain data recorded during the booking period.';
COMMENT ON COLUMN MERGE_PROCESSES.TRANSACTION_TYPE IS 'Indicates whether the merge process is for a full MERGE of two offender records, or the TRANSFER of a selected booking.';
COMMENT ON COLUMN MERGE_PROCESSES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MERGE_PROCESSES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MERGE_PROCESSES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MERGE_PROCESSES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MERGE_PROCESSES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MERGE_PROCESS_RULES IS 'Defines the details business rules in the merge process.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.RULE_ID IS 'The unique identifier of the merge process rule.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the identified business rule is included in the merge process. Not used.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.APPLICATION_CODE IS 'A code used to identify the business area that this business rule is related to.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.DEFAULT_ON_FLAG IS 'A flag (Y/N) identifying whether the rule is automatically defaulted to be included in the transfer process. Not used.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.DESCRIPTION IS 'The description of the business rule.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.ENDING_RULE_FLAG IS 'A flag (Y/N) indicating whether this is the last (terminating) business rule for the merge process.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.INIT_PROC IS 'Identifies an initialization procedure that must be executed before the main business rule logic.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.MANDATORY_ON_FLAG IS 'A flag (Y/N) indicating whether the business area must be included in the transfer process. Not used.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.PROCESS_ID IS 'The unique identifier of the parent merge process record.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.PROCESS_NAME IS 'The name of the business process.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.RESULT_FALSE_NEXT_ID IS 'Identifier the rule_id of next business rule in the merge process, and allows for an alternate flow based on results from the current business rule.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.RESULT_TRUE_NEXT_ID IS 'Identifies the rule_id of the next business rule in the merge process.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.RULE_PROC IS 'The name of the function or procedure that encapsulates the business rule.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.RULE_PROC_TYPE IS 'An indicator of whether rule_proc is a PROCEDURE or a FUNCTION.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.TERM_PROC IS 'The identifier for a final termination procedure that must be executed after the business rule has been processed.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.TIMEFRAME_REQUIRED_FLAG IS 'A flag (Y/N) indicating whether the date range for transfer of data must be entered for this business rule''s parent business area. Used to determine exactly which records to transfer from one offender to another for business areas that are not explicitly tied to the booking, but which may contain data recorded during the booking period.';
COMMENT ON COLUMN MERGE_PROCESS_RULES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MERGE_PROCESS_RULES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MERGE_PROCESS_RULES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MERGE_PROCESS_RULES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MERGE_PROCESS_RULES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MERGE_TABLES IS 'Maintains a list of all tables linked to an offender for purposes of supporting the Merge Offender utility. No longer used.';
COMMENT ON COLUMN MERGE_TABLES.TABLE_NAME IS 'The name of the database table upon which to perform merge activity if offender data is present.';
COMMENT ON COLUMN MERGE_TABLES.COLUMN_NAME IS 'The name of a column on the table that must be updated for merge.';
COMMENT ON COLUMN MERGE_TABLES.FINANCIAL_FLAG IS 'An indicator of the type of data on a table, to segregate merge by functional areas.';
COMMENT ON COLUMN MERGE_TABLES.CREATE_DATETIME IS 'The timestamp when the record is created';
COMMENT ON COLUMN MERGE_TABLES.CREATE_USER_ID IS 'The user who creates the record';
COMMENT ON COLUMN MERGE_TABLES.MODIFY_DATETIME IS 'The timestamp when the record is modified ';
COMMENT ON COLUMN MERGE_TABLES.MODIFY_USER_ID IS 'The user who modifies the record';
COMMENT ON TABLE MERGE_TRANSACTIONS IS 'Records each request for transfer (merge) of a booking and the result of the request. The transaction includes identifying information for the offenders, as the nature of a successful merge leads to a transformation of some of the original key details of the merged from offender in order to be absored into the merged to offender.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.MERGE_TRANSACTION_ID IS 'The unique identifier of the merge transaction request. Generated from sequence ( MERGE_TRANSACTIONS_ID )';
COMMENT ON COLUMN MERGE_TRANSACTIONS.REQUEST_DATE IS 'The date and time of the merge request.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.REQUEST_STATUS_CODE IS 'A code indicating the merge request status. A transaction can have one of the following statuses that are updated during the transfer execution: PENDING, IN PROGRASS, CANCELED, COMPLETE, FAILED. Ref Domain ( TRANS_STAT )';
COMMENT ON COLUMN MERGE_TRANSACTIONS.TRANSACTION_SOURCE IS 'A code identifying whether the merge request source. Ref Domain ( TRANS_SOURCE )';
COMMENT ON COLUMN MERGE_TRANSACTIONS.FIRST_NAME_1 IS 'The first name of the offender merged from.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.FIRST_NAME_2 IS 'The first name of the offender merged to.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.LAST_NAME_1 IS 'The last name of the offender merged from.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.LAST_NAME_2 IS 'The last name of the offender merged to.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.OFFENDER_BOOK_ID_1 IS 'The booking number of the offender merged from.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.OFFENDER_BOOK_ID_2 IS 'The booking number of the offender merged to.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.OFFENDER_ID_1 IS 'The unique identifier of the working name of the merged from offender.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.OFFENDER_ID_2 IS 'The unique identifier of the working name of the merged to offender.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.OFFENDER_ID_DISPLAY_1 IS 'The offender id display of the offender merged from.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.OFFENDER_ID_DISPLAY_2 IS 'The offender id display of the offender merged to.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.QUEUE_MESSAGE_ID IS 'For automated merge records, an internal unique identifier of the transaction''s place in queue.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.ROOT_OFFENDER_ID_1 IS 'The root offender id of the offender merged from.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.ROOT_OFFENDER_ID_2 IS 'The root offender id of the offender merged to.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.TRANSACTION_TYPE IS 'Values is NULL for a merge of two offender records, and TRANSFER for transfer of a booking between two offenders.';
COMMENT ON COLUMN MERGE_TRANSACTIONS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MERGE_TRANSACTIONS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MERGE_TRANSACTIONS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MERGE_TRANSACTIONS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MERGE_TRANSACTIONS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MERGE_TRANSACTION_LOGS IS 'Records information messages, warnings and errors during the transfer-booking process. Log level based on system profile MERGE LOG_LEVEL. If not found, uses log level ERROR:WARN:INFO, which would be the standard operating log level in production systems.';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.MERGE_TRANSACTION_LOG_ID IS 'The unique identifier of the merge transaction log. Generated from sequence ( MERGE_TRANSACTION_LOG_ID )';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.MERGE_TRANSACTION_ID IS 'The unique identifier of the merge transaction request.';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.LOG_LEVEL IS 'A code indicating the type of log record. Valid values: ERROR, WARN, INFO, TRACE, DEBUG';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.LOG_MSG_PART IS 'Default to 1. For messages longer than 4000 bytes, incremented for each part of the message.';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.LOG_TEXT IS 'Text describing the log event.';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.LOG_TIMESTAMP IS 'The timestamp when the event occurred.';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MERGE_TRANSACTION_LOGS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MERGE_TRANSACTION_PROCESSES IS 'Identifies the optional processes that were selected to proceed for booking transfers, and includes the user entered date filters recorded for processes that have date filters (timeframe_required_flag = Y for the associated merge process).';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.MERGE_TRANSACTION_ID IS 'The unique identifier of the merge transaction request.';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.PROCESS_ID IS 'The unique identifier of the parent merge process business area';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.TIMEFRAME_FLAG IS 'A flag to indicate that a timeframe for transfer of information has been applied.';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.BEGIN_DATE IS 'The timeframe begin date and time for data transfer.';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.END_DATE IS 'The timeframe end date and time for data transfer.';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MERGE_TRANSACTION_PROCESSES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MODULE_TABLES IS 'Map of tag modules objects to database tables, used to map some features such as the configuration key to form objects.';
COMMENT ON COLUMN MODULE_TABLES.MODULE_TAB_ID IS 'The unique identifier of this record. Generated from sequence ( MODULE_TAB_ID )';
COMMENT ON COLUMN MODULE_TABLES.MODULE_NAME IS 'The short name that uniquely identifies a form.';
COMMENT ON COLUMN MODULE_TABLES.OBJECT_NAME IS 'The name of a module block.';
COMMENT ON COLUMN MODULE_TABLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MODULE_TABLES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MODULE_TABLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MODULE_TABLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MODULE_TABLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MODULE_TAB_COLUMNS IS 'Columns for tag modules, used to map some features, such as configuration keys, to form objects.';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.MODULE_TAB_ID IS 'The unique identifier of the parent module record.';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.MODULE_TAB_SEQ IS 'Part of the unique identifier of this module object.';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.SETUP_MODULE IS 'The short name that identifies the associated maintenance form for update through the configuration key.';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.COLUMN_NAME IS 'The name of the module item on the associated module block. Uniquely identifies a GUI item / column.';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.DOMAIN IS 'Identifies the maintenance reference domain for update through the configuration key (supersedes setup module).';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.LOV_ITEM_NAME IS 'The name of the associated LOV for a code driven field.';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.REF_TABLES IS 'The name of the configuration table associated with the item.';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MODULE_TAB_COLUMNS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE PURGED_OFFENDER_HISTORIES IS 'Tracks history of all purged booking records if historical tracking is turned on (System profile CLIENT PURGE_HIST = ''Y'').';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.ROOT_OFFENDER_ID IS 'The unique identifier of the offender that had information purged.';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.OFFENDER_BOOK_ID IS 'The unique identifier of the booking that had information purged.';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.LAST_NAME IS 'The working last name of the offender associated with this booking.';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.OFFENDER_ID_DISPLAY IS 'The user facing offender identifier of the offender record purged.';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.PURGE_DATE IS 'The date and time the record was purged.';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.STAFF_ID IS 'The unique identifier of the staff member who purged the record.';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.FIRST_NAME IS 'The working first name of the offender associated with this booking.';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN PURGED_OFFENDER_HISTORIES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE PURGE_TABLES IS 'Describes the table properties for generation of dynamic SQL for purging offender specific records from the TAG system. No longer used.';
COMMENT ON COLUMN PURGE_TABLES.TABLE_NAME IS 'Stores table names';
COMMENT ON COLUMN PURGE_TABLES.CREATE_DATETIME IS 'The timestamp when the record is created';
COMMENT ON COLUMN PURGE_TABLES.CREATE_USER_ID IS 'The user who creates the record';
COMMENT ON COLUMN PURGE_TABLES.MODIFY_DATETIME IS 'The timestamp when the record is modified ';
COMMENT ON COLUMN PURGE_TABLES.MODIFY_USER_ID IS 'The user who modifies the record';
COMMENT ON TABLE TAG_ERROR_LOGS IS 'Tracking of run time errors of TAG system';
COMMENT ON COLUMN TAG_ERROR_LOGS.TAG_ERROR_ID IS 'Sequence to find out the error';
COMMENT ON COLUMN TAG_ERROR_LOGS.ERROR_LOCATION IS 'location of the error originated from...';
COMMENT ON COLUMN TAG_ERROR_LOGS.ERROR_MESSAGE IS 'Sql error message';
COMMENT ON COLUMN TAG_ERROR_LOGS.MODULE_NAME IS 'Name of module that caused the exception.';
COMMENT ON COLUMN TAG_ERROR_LOGS.PROCEDURE_NAME IS 'Name of the procedure that caused the exception.';
COMMENT ON COLUMN TAG_ERROR_LOGS.SID IS 'Session Id';
COMMENT ON COLUMN TAG_ERROR_LOGS.USER_ERROR_CODE IS 'The error code';
COMMENT ON COLUMN TAG_ERROR_LOGS.USER_LOCATION IS 'The user location';
COMMENT ON COLUMN TAG_ERROR_LOGS.USER_MESSAGE IS 'The message text';
COMMENT ON COLUMN TAG_ERROR_LOGS.USER_MODULE IS 'The user module name';
COMMENT ON COLUMN TAG_ERROR_LOGS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN TAG_ERROR_LOGS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN TAG_ERROR_LOGS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN TAG_ERROR_LOGS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN TAG_ERROR_LOGS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE TRANSFER_BOOKING_TABLES IS 'Describes the table properties for generation of dynamic SQL for transfer booking.';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.TABLE_NAME IS 'The name of the table';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.APPLICATION_CODE IS 'Identifies the business area that the table belongs to.';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.BOOKING_ID_COLUMN IS 'Identifies the table column that stores Offender_Book_ID';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.BOOKING_WHERE_CLAUSE IS 'Identifies part of the where clause required to select the records that require update for the transfer.';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.COMPLETED_FLAG IS 'A flag (Y/N) identifying whether the table is available for selection by the rule-based engine.';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.FILTER_COLUMN IS 'The logic column used to filter records [ Offender_ID / Offender_Book_ID]';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.OBJECT_CLASS IS 'The object class of the record in common object tables such as Addresses, Phones, etc...';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.OFFENDER_ID_COLUMN IS 'The column which stores the Offender_ID';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.PARENT_OFF_ID_COLUMN IS 'For recursive tables, the column which points to the Offender_ID column of its parent records';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.PARENT_SEQ_COLUMN IS 'For recursive tables, the column which points to the sequence column of its parent records';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.PK_FIRST_COLUMN IS 'The first column of the primary key';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.PK_SECOND_COLUMN IS 'The second column of the primary key';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.PK_TYPE IS 'The type of the table primary key. If the value is SURROGATE, the surrogate key column is used. Values ( SURROGATE, PARTIAL, PARENT )';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.PROCESS_METHOD IS 'The process method for the table.  [RULE : handled by rule-based SQLs, CUSTOM : customized ]';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.ROOT_FLAG IS 'A flag (Y/N) indicating whether the table is a root (non-child) table for processing by the rules based engine.';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.SEQ_COLUMN IS 'For tables that have a sequence column as part of the primary key, identifies the name of the column that requires re-sequencing during transfer';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.SURROGATE_KEY_COLUMN IS 'For tables with a surrogate key, the column name of the surrogate key';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.TRANSFER_DATE_COLUMN IS 'The column which will be used with the transfer date filter to determine if the record should be transfered or not.';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.TRANSFER_SEQ IS 'The processing order of the table in the Transfer Booking Status.  (Could be important for table with multiple parent tables)';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.UPDATE_CLAUSE IS 'Identifies the data change required to fulfill transfer on this table.';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN TRANSFER_BOOKING_TABLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE TRANSFER_TABLE_RELATIONSHIPS IS 'Describes the relationships between parent and child tables for generation of dynamic SQL for transfer booking, and to allow for cascade of data updates from parent to child records.';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.PARENT_TABLE IS 'The name of the parent table';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.CHILD_TABLE IS 'The name of the child table';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.FK_PK_TYPE IS 'The type of FK. Not used.';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.ACTIVE_FLAG IS 'If the relationship active for Transfer Booking module ?';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.CHILD_UPDATE_CLAUSE IS 'The details of the update clause';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.FK_OFFENDER_ID_COLUMN IS 'The Offender_ID column in the foreign key';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.FK_SEQ_COLUMN IS 'The sequence column in the foreign key';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.PRE_PROCESSING_RULE IS 'Defines SQL that must be applied before updating the child records for a table.';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.RELATIONSHIP_SEQ IS 'The order of the relationship in Transfer Booking module. Not used.';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN TRANSFER_TABLE_RELATIONSHIPS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
