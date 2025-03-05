
OTDHOLDT_FIND_CGFKOFFTXNSUBACCOUNTTYPE {
 	SELECT REF_CODE.CODE CODE  , REF_CODE.DESCRIPTION DESCRIPTION  , REF_CODE.DOMAIN AS DOMAIN FROM REFERENCE_CODES REF_CODE WHERE PARENT_CODE != 'S' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL AND DOMAIN = 'SUB_AC_TYPE' AND EXISTS ( SELECT 'X' FROM TRANSACTION_OPERATIONS OT , ACCOUNT_CODES AC WHERE OT.MODULE_NAME = 'OTDHOLDT' AND OT.CASELOAD_ID = :caseloadId AND OT.DR_ACCOUNT_CODE = AC.ACCOUNT_CODE AND AC.SUB_ACCOUNT_TYPE = REF_CODE.CODE  ) 
}

OTDHOLDT_OFFTXN_FIND_OFFENDER_TRANSACTIONS {
 	SELECT COUNT(1) FROM OFFENDER_TRANSACTIONS
}
OTDHOLDT_OFFTXN_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ) VALUES(:txnId ,:txnEntrySeq ,:caseloadId ,:offenderId ,:offenderBookId ,:txnPostingType ,:txnType ,:txnEntryDesc ,:txnEntryAmount , :txnEntryDate ,:subAccountType ,:txnReferenceNumber ,:modifyDate ,:receiptNumber ,:slipPrintedFlag ,:transferCaseloadId ,:receiptPrintedFlag ,:preWithholdAmount , 'Y' ,:closingChequeNumber ,:remitterName ,:payeeCode ,:payeeNameText ,:payeeCorporateId ,:payeePersonId ,:adjustTxnId ,99 ,:adjustOffenderId ,:adjustAccountCode ,:txnAdjustedFlag ,:deductionType ,:infoNumber ,:holdClearFlag ,:holdUntilDate ,:holdNumber ,:grossAmount ,:grossNetFlag ,:remitterId ,:applySpendingLimitAmount ,:receiptPendingPrintFlag ,:sealFlag ,:orgTxnType ,current_timestamp ,:createUserId ,current_timestamp  )
}

OTDHOLDT_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTDHOLDT_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_TRANSACTIONS O WHERE O.OFFENDER_ID = :ROOTOFFENDERID
}

OTDHOLDT_CGFKCHK_OFF_TXN_OFF_TXN_REF_C {
	SELECT REF_CODE.DESCRIPTION ,REF_CODE.DOMAIN FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :SUBACCOUNTTYPE AND     PARENT_CODE != 'S' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL AND DOMAIN = 'SUB_AC_TYPE'
}

OTDHOLDT_CGWHEN_NEW_FORM_INSTANCE {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OTDHOLDT_NBT_EXISTING_HOLD_AMOUNT {
SELECT coalesce (hold_balance, 0)
        as HOLDBALANCE
        FROM offender_trust_accounts
       WHERE offender_id = :OFFENDERID
         AND caseload_id = :CASELOADID
}

OTDHOLDT_NBT_SUB_ACCOUNT_AMOUNT {
          SELECT coalesce (balance, 0) as BALANCE
        FROM offender_sub_accounts
       WHERE caseload_id = :CASELOADID
         AND offender_id =  :OFFENDERID
         AND trust_account_code IN (SELECT account_code
                                      FROM account_codes
                                     WHERE sub_account_type = :SUBACCOUNTTYPE)
}

OTDHOLDT_HOLD_NUMBER_SEQUENCE {
select nextval('HOLD_NUMBER') 

}

OTDHOLDT_TXN_ID_SEQUENCE {
select nextval('TXN_ID') 
}
OTDHOLD_MAX_OFFENDER_BOOK_ID {
select MAX(offender_book_id) 
			from offender_bookings
			where offender_id = :OFFENDERID
}
OTDHOLD_UPDATE_OFFENDER_TRUST_ACCOUNTS {
update offender_trust_accounts
	set	hold_balance = coalesce(hold_balance,0) + :TOTALAMT,
	modify_user_id =:modifyUserId, modify_datetime = current_timestamp
	where	offender_id = :OFFENDERID
			and
		caseload_id = :CASELOADID
}
OTDHOLD_GET_SUBAC_HOLDBALANCE {
SELECT coalesce (HOLD_BALANCE, 0) FROM offender_sub_accounts  WHERE caseload_id = :caseloadId  AND offender_id = :offenderId AND trust_account_code IN (SELECT account_code FROM account_codes WHERE sub_account_type = :subAccountType)
}
OTDHOLD_UPDATE_SUBAC_HOLDBALANCE {
UPDATE offender_sub_accounts SET hold_balance = :holdBalance,modify_user_id =:modifyUserId, modify_datetime = current_timestamp WHERE caseload_id  = :caseloadId  AND offender_id  = :offenderId
         AND trust_account_code IN (SELECT account_code FROM account_codes WHERE sub_account_type = :subAccoutType)
}
OTDHOLD_DEDUCTIONS_GET_AC_AND_SET_IND_DATE {
CALL OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:P_OFF_ID, :P_CSLD_ID)
}