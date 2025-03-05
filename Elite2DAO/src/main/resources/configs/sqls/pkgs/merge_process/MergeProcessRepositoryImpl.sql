MERGE_PROCESS_GET_START_PROCESS_ID{
select process_id from merge_processes where starting_process_flag = 'Y' and transaction_type = :transactionType
}

MERGE_PROCESS_GET_MERGE_PROCESSES_INFO{
 select * from merge_processes where process_id =:processId
}

MERGE_PROCESS_CHECK_TRANSFER_FLAG{
select COUNT (0) from v_merge_transaction_rules mt where process_id = :processId and merge_transaction_id = :mergeTransactionId and transfer_flag = 'Y'
}

MERGE_PROCESS_GET_MERGE_PROCESS_RULES_INFO{
select * from merge_process_rules where rule_id = :ruleId
}
