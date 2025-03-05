
L_OFFENDER_BOOK_CUR{
SELECT ob.offender_book_id FROM offender_bookings ob, offenders o WHERE ob.root_offender_id = :p_root_offender_id AND o.root_offender_id = :p_root_offender_id AND o.offender_id = ob.offender_id AND ob.active_flag = 'Y' AND ob.offender_book_id = ( SELECT MAX(obx.offender_book_id) FROM offender_bookings obx WHERE obx.root_offender_id = ob.root_offender_id AND obx.booking_type = ( SELECT caseload_type FROM caseloads WHERE caseload_id = :p_caseload_id ) )  
}

L_OFFENDER_INACT_CUR{
SELECT ob.offender_book_id FROM offender_bookings ob, offenders o WHERE ob.root_offender_id = :p_root_offender_id AND o.root_offender_id = :p_root_offender_id AND o.offender_id = ob.offender_id AND ob.offender_book_id = ( SELECT MAX(obx.offender_book_id) FROM offender_bookings obx WHERE obx.root_offender_id = ob.root_offender_id AND obx.booking_type = ( SELECT caseload_type FROM caseloads WHERE caseload_id = :p_caseload_id ) )  
}


INSERT_OFFENDER_TRANSACTIONS{
-- INSERT INTO OFFENDER_TRANSACTIONS ( TXN_ID, TXN_ENTRY_SEQ, CASELOAD_ID, OFFENDER_ID, OFFENDER_BOOK_ID, TXN_POSTING_TYPE, TXN_TYPE, TXN_ENTRY_DESC, TXN_ENTRY_AMOUNT, TXN_ENTRY_DATE, SUB_ACCOUNT_TYPE, MODIFY_DATE, MODIFY_USER_ID, SLIP_PRINTED_FLAG, PRE_WITHHOLD_AMOUNT, DEDUCTION_FLAG, PAYEE_CODE, PAYEE_CORPORATE_ID, PAYEE_PERSON_ID, PAYEE_NAME_TEXT, DEDUCTION_TYPE, INFO_NUMBER, REMITTER_NAME, REMITTER_ID, RECEIPT_NUMBER, TXN_REFERENCE_NUMBER ) VALUES ( :P_TXN_ID, :P_TXN_ENTRY_SEQ, :P_CASELOAD_ID, :P_OFFENDER_ID, :L_OFFENDER_BOOK_ID, :L_TXN_POSTING_TYPE, :P_TXN_TYPE, :P_TXN_DESC, :P_TXN_ENTRY_AMOUNT, TRUNC(SYSDATE), :L_SUB_ACCOUNT_TYPE, TRUNC(SYSDATE), USER, 'N', NULL, 'N', NULL, :P_PAYEE_CORP_ID, :P_PAYEE_PERSON_ID, :P_PAYEE_NAME_TEXT, NULL, NULL, NULL, NULL, :L_RECEIPT_NUMBER, :P_TXN_REF_NUMBER )
insert into OFFENDER_TRANSACTIONS ( TXN_ID, TXN_ENTRY_SEQ, CASELOAD_ID, OFFENDER_ID, OFFENDER_BOOK_ID, TXN_POSTING_TYPE, TXN_TYPE, TXN_ENTRY_DESC, TXN_ENTRY_AMOUNT, TXN_ENTRY_DATE, SUB_ACCOUNT_TYPE, MODIFY_DATE, SLIP_PRINTED_FLAG, PRE_WITHHOLD_AMOUNT, DEDUCTION_FLAG, PAYEE_CODE, PAYEE_CORPORATE_ID, PAYEE_PERSON_ID, PAYEE_NAME_TEXT, DEDUCTION_TYPE, INFO_NUMBER, REMITTER_NAME, REMITTER_ID, RECEIPT_NUMBER, TXN_REFERENCE_NUMBER , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :P_TXN_ID, :P_TXN_ENTRY_SEQ, :P_CASELOAD_ID, :P_OFFENDER_ID, :L_OFFENDER_BOOK_ID, :L_TXN_POSTING_TYPE, :P_TXN_TYPE, :P_TXN_DESC, :P_TXN_ENTRY_AMOUNT, CURRENT_TIMESTAMP, :L_SUB_ACCOUNT_TYPE, CURRENT_TIMESTAMP, 'N', null, 'N', null, :P_PAYEE_CORP_ID, :P_PAYEE_PERSON_ID, :P_PAYEE_NAME_TEXT, null, null, null, null, :L_RECEIPT_NUMBER, :P_TXN_REF_NUMBER, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

L_CHEQUE_EXISTS_CUR{
SELECT COUNT(*) FROM bank_cheque_data WHERE txn_id = :p_txn_id
}

UPDATE_BANK_CHEQUE_DATA{
-- UPDATE bank_cheque_data SET txn_entry_amount = txn_entry_amount + :p_txn_entry_amount WHERE txn_id = :P_TXN_ID
update bank_cheque_data set txn_entry_amount = txn_entry_amount + :p_txn_entry_amount, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where txn_id = :P_TXN_ID
}

