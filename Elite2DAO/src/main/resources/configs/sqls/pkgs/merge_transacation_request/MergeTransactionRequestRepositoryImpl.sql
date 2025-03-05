 MERGE_TRANSACTION_REQUEST_SEQ{
  select coalesce(MAX(MERGE_TRANSACTION_ID)+1,1) from merge_transactions
 }
 
 MERGE_TRANSACTION_REQUEST_MERGE_TRANSACTIONS{
  INSERT INTO merge_transactions (merge_transaction_id, request_date, request_status_code, queue_message_id, transaction_source, offender_book_id_1, root_offender_id_1, offender_id_1, offender_id_display_1, last_name_1, first_name_1, offender_book_id_2, root_offender_id_2, offender_id_2, offender_id_display_2, last_name_2, first_name_2 ) 
  VALUES (:mergeTransactionId,:requestDate, 'PENDING', :queueMessageId, 'MANUAL',:offenderBookId1,:rootOffenderId1,:offenderIdDisplay,:lastName1,:firstName1,:offenderBookId2,:rootOffenderId2,:offenderId2,:offenderIdDisplay2,:lastName2,:firstName2 )
 }
 MERGE_TRANSACTION_REQUEST_UPDATE_MERGE_TRANSACTIONS{
   UPDATE merge_transactions SET request_status_code = :requestStatusCode  WHERE merge_transaction_id = :mergeTransactionId
 }
 
 MERGE_TRANSACTION_REQUEST_UPDATE_TRANSACTIONS{
 update merge_transactions set offender_book_id_1 =:fromOffenderBookId, root_offender_id_1 = :fromRootOffenderId, offender_id_1 = :fromOffenderId, offender_id_display_1 = :fromOffenderIdDisplay, last_name_1 = :fromLastName, first_name_1 = :fromfirstName, offender_book_id_2 = :toOffenderBookId, root_offender_id_2 = :toRootOffenderId, offender_id_2 = :toOffenderId, offender_id_display_2 = :toOffenderIdDisplay, last_name_2 = :toLastName, first_name_2 = :tofirstName where merge_transaction_id = :mergeTransactionId
 }
 
 
 MERGE_TRANSACTION_REQUEST_INSERT_MRG_TRANSACTIONS{
 INSERT INTO merge_transactions (merge_transaction_id, transaction_type, request_date, request_status_code, queue_message_id, transaction_source, offender_book_id_1, root_offender_id_1, offender_id_1, offender_id_display_1, last_name_1, first_name_1, offender_book_id_2, root_offender_id_2, offender_id_2, offender_id_display_2, last_name_2, first_name_2, create_user_id ,create_datetime ) 
 VALUES (:pMergeTransactionId, 'TRANSFER', current_timestamp, 'PENDING', :queueMessageId, 'MANUAL', :pFromOffBookId, :pFromRootOffId, :pFromOffenderId, :pFromOffIdDisplay, :pFromLastname, :pFromFirstName, :pToOffBookId, :pToRootOffId, :pToOffenderId, :pToOffIdDisplay, :pToLastName, :pToFirstName, :createUserId , current_timestamp)
  }