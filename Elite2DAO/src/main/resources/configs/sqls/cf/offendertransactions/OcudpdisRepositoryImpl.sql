OCUDPDIS_FIND_TRANSACTIONDETAILS {
SELECT top.txn_type, 
         tp.description
        FROM transaction_operations top,
             transaction_types tp
       WHERE top.module_name = 'OCUDPDIS'
         AND top.CASELOAD_ID =:caseloadId
         AND top.txn_type = tp.txn_type
}
OCUDPDIS_FIND_BALANCEDETAILS {
 SELECT coalesce(balance, 0)
        FROM offender_sub_accounts osa
       WHERE osa.offender_id = :offenderId
         AND osa.caseload_id = :caseloadId
         AND osa.trust_account_code IN (SELECT top.dr_account_code
                                          FROM transaction_operations top
                                         WHERE top.caseload_id = :caseloadId
                                           AND top.txn_type = :txnType)
                                           
                                           
}
OCUDPDIS_FIND_BAL_SUB_ACC_DETAILS{
 SELECT coalesce(balance, 0) current_balance,
             ac.sub_account_type
        FROM offender_sub_accounts osa,
             account_codes ac
       WHERE osa.offender_id = :offenderId
         AND osa.caseload_id = :caseloadId
         AND osa.trust_account_code = ac.account_code
         AND osa.trust_account_code IN (SELECT top.dr_account_code
                                          FROM transaction_operations top
                                         WHERE top.caseload_id = :caseloadId
                                           AND top.txn_type = :txnType)
                                          

}

OCUDPDIS_TXN_ID_SEQUENCE {
SELECT nextval('TXN_ID') next_seq from dual
}

OCUDPDIS_OFFENDER_BOOK_ID {
 SELECT offender_book_id
     FROM offender_bookings ob
    WHERE ob.root_offender_id = :offenderId
      AND ob.offender_book_id 
             = (SELECT MAX(obx.offender_book_id)
                  FROM offender_bookings obx
                 WHERE obx.root_offender_id = ob.root_offender_id
                   AND obx.booking_type =
                          (SELECT caseload_type
                             FROM caseloads 
                            WHERE caseload_id = :caseloadId
                          )
               )
}

