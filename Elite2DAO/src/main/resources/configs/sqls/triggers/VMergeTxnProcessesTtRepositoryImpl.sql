V_MERGE_TXN_PROCESSES_IT_INSERT{
INSERT INTO merge_transaction_processes(MERGE_TRANSACTION_ID,PROCESS_ID, BEGIN_DATE, END_DATE, TIMEFRAME_FLAG)
   VALUES (:mergeTransactionId, :processId, trunc(:beginDate)+(:beginTime - trunc(:beginTime)),
         trunc(:endDate)+(:endTime - trunc(:endTime)) , :timeframeFlag )
}
V_MERGE_TXN_PROCESSES_IT_UPDATE{
UPDATE merge_transaction_processes
   SET    begin_date = trunc(:beginDate)+(:beginTime - trunc(:beginTime)),
          end_date   =  trunc(:endDate)+(:endTime - trunc(:endTime)),
          timeframe_flag = :timeframeFlag
   WHERE  merge_transaction_ID = :mergeTransactionId
     AND  process_ID     = :processId
}
V_MERGE_TXN_PROCESSES_IT_DELETE{
DELETE from merge_transaction_processes
   WHERE  merge_transaction_ID = :mergeTransactionId
     AND  process_ID     = :processId
}