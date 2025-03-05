OCUBADJS_ADJUSTMENT_TYPE_RG {
	select txn_type code,description from transaction_types where txn_usage in ('BI', 'BD')
}

OCUBADJS_FIND_BILL_OVERRIDE_DETAILS {
select ofbt.*,  tt.txn_usage ,ofb.BILL_LDPP_START_DATE,ofb.BILL_LDPP_END_DATE,ofb.BILL_AR_DUE_DATE from transaction_types TT, off_fee_bill_transactions ofbt,OFF_FEE_BILLS ofb where ofbt.bill_id ::text =:billId ::text and ofbt.bill_id = ofb.bill_id and ofbt.bill_TXN_TYPE=TT.txn_type  
    order by ofbt.bill_txn_no asc
}

OCUBADJS_GET_CURRENT_STAFFDETAIL {
 SELECT STAFF_ID FROM   STAFF_MEMBERS  STAFF WHERE  USER_ID = :userId	
 }
 
 OCUBADJS_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT {
 (
select
	coalesce(max(BILL_TXN_NO)+ 1 , 1)
from
	OFF_FEE_BILL_TRANSACTIONS
where
	BILL_ID :: text  = :BILL_ID::text)
 }

 
 OCUBADJS_OFF_FEE_BILLS_TRANSACTIONS_INSERT {
 INSERT INTO OFF_FEE_BILL_TRANSACTIONS (BILL_ID,BILL_TXN_NO,BILL_TXN_DATETIME,BILL_TXN_STAFF_ID,BILL_TXN_USER,
BILL_TXN_TYPE,BILL_TXN_AMOUNT,TRUST_TXN_ID,TRUST_TXN_ENTRY_SEQ,OFF_ADJ_CANC_RSN,OFF_ADJ_SUB_RSN,OFF_ADJ_TXN_ID,OFF_ADJ_REV_RSN,
BILL_STATUS,BILL_AGING_START_DATE,BILL_AGING_END_DATE,BILL_TXN_COMMENT,ORIGINAL_BILL_ID,ORIGINAL_BILL_TXN_NO,
ORIGINAL_OFF_ADJ_TXN_ID,CREATE_DATETIME,CREATE_USER_ID,SEAL_FLAG)
values(:billId,:billTxnNo,current_timestamp,:billTxnStaffId,:billTxnUser,
:billTxnType,:billTxnAmount,:trustTxnId,:trustTxnEntrySeq,:offAdjCancRsn,:offAdjSubRsn,:offAdjTxnId,:offAdjRevRsn,
:billStatus,:billAgingStartDate,:billAgingEndDate,:billTxnComment,:originalBillId,:originalBillTxnNo,
:originalOffAdjTxnId,current_timestamp,:createUserId,:sealFlag)
 }
 OCUBADJS_FIND_GET_BILL_TXN_USAGE {
 select txn_usage from transaction_types where txn_type=:billTxnType
 }
 OCUBADJS_GET_ORIGINAL_BALANCE_OWING{
 select ofbt.*, ofb.BILL_AR_DUE_DATE from OFF_FEE_BILL_TRANSACTIONS ofbt, off_fee_bills ofb where ofbt.bill_id = ofb.bill_id and ofbt.bill_id = :billId  and 
ofbt.create_datetime = (select max(create_datetime) from OFF_FEE_BILL_TRANSACTIONS where bill_id ::text  = :billId ::text )
 }
 
 OCUBADJS_GET_LATEST_RECORD{
 SELECT * FROM (SELECT * FROM OFF_FEE_BILL_TRANSACTIONS WHERE BILL_ID ::text =:BILLID ::text  ORDER BY BILL_TXN_DATETIME DESC) as a limit 1
 }
 
