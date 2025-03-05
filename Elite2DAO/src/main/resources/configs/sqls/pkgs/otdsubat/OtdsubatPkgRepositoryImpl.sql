PROCESS_TRANSACTION_GET_OFFENDER_ID{
select ob.offender_book_id
        from offender_bookings ob
       where ob.root_offender_id = :P_OFFENDER_ID
         and ob.offender_book_id
                = (select max(obx.offender_book_id)
                     from offender_bookings obx
                    where obx.root_offender_id = :P_OFFENDER_ID
                      and obx.booking_type
                             = (select caseload_type
                                  from caseloads
                                 where caseload_id = :P_CASELOAD_ID
                               )
                  )
   }
   
  PROCESS_TRANSACTION_UP_OFF_TRANS{
/* update offender_transactions
               set txn_adjusted_flag = 'N',
                   hold_clear_flag = 'N'
             where txn_id = :p_txn_id
               and txn_entry_seq = :p_txn_entry_seq */
  update offender_transactions set txn_adjusted_flag = 'N', hold_clear_flag = 'N', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where txn_id = :p_txn_id and txn_entry_seq = :p_txn_entry_seq
}  