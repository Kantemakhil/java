
OUIMTLOG_MERGELOG_FIND_MERGE_TRANSACTION_LOGS {
 	SELECT MERGE_TRANSACTION_ID ,LOG_TIMESTAMP ,LOG_TEXT ,LOG_LEVEL ,LOG_MSG_PART ,MERGE_TRANSACTION_LOG_ID ,MODIFY_USER_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM MERGE_TRANSACTION_LOGS   where  merge_transaction_id =:mergeTransactionLogId order by log_timestamp desc
}
