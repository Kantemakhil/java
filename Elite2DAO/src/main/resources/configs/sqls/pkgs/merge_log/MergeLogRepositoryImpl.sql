MERGE_LOG_GET_MERGE_TRANSACTION_LOG_ID{
select NEXTVAL('merge_transaction_log_id')
}
MERGE_LOG_INSERT_MERGE_TRNS_LOGS{
INSERT INTO merge_transaction_logs (merge_transaction_id,log_timestamp, log_text,log_level,log_msg_part,merge_transaction_log_id,create_datetime,create_user_id,seal_flag)
values (:mergeTransactionId, :logTimestamp, :logText,:logLevel,:logMsgPart,:mergeTransactionLogId,current_timestamp,:createUserId,:sealFlag)
}
