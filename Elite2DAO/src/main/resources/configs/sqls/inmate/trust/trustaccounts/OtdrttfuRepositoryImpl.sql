
OTDRTTFU_OFFTT_FIND_OFFENDER_TRUST_TRANSFERS {
 	SELECT TXN_ID ,FROM_CASELOAD ,TO_CASELOAD ,AMOUNT ,TRANSFER_DATE ,POSTED_FLAG ,MODIFY_DATE ,MODIFY_USER_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG
   FROM OFFENDER_TRUST_TRANSFERS  WHERE  TO_CASELOAD = :GLOBALCASELOAD  AND POSTED_FLAG != 'Y' ORDER BY TRANSFER_DATE ,FROM_CASELOAD
}
OTDRTTFU_OFFTT_INSERT_OFFENDER_TRUST_TRANSFERS {
	INSERT INTO OFFENDER_TRUST_TRANSFERS(TXN_ID ,FROM_CASELOAD ,TO_CASELOAD ,AMOUNT ,TRANSFER_DATE ,POSTED_FLAG ,MODIFY_DATE ,MODIFY_USER_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG ) VALUES(:txnId ,:fromCaseload ,:toCaseload ,:amount ,:transferDate ,:postedFlag ,:modifyDate ,:modifyUserId ,:createDatetime ,:createUserId ,:modifyDatetime ,:sealFlag )
}

OTDRTTFU_OFFTT_UPDATE_OFFENDER_TRUST_TRANSFERS {
	UPDATE OFFENDER_TRUST_TRANSFERS set TXN_ID  = :txnId ,FROM_CASELOAD  = :fromCaseload ,TO_CASELOAD  = :toCaseload ,AMOUNT  = :amount ,TRANSFER_DATE  = :transferDate ,POSTED_FLAG  = :postedFlag ,MODIFY_DATE  = :modifyDate ,MODIFY_USER_ID  = :modifyUserId ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = :modifyDatetime ,SEAL_FLAG  = :sealFlag /* where */
}

OTDRTTFU_OFFTXN_FIND_OFFENDER_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM OFFENDER_TRANSACTIONS  /* where  */
}
OTDRTTFU_OFFTXN_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ) VALUES(:txnId ,:txnEntrySeq ,:caseloadId ,:offenderId ,:offenderBookId ,:txnPostingType ,:txnType ,:txnEntryDesc ,:txnEntryAmount ,:txnEntryDate ,:subAccountType ,:txnReferenceNumber ,:modifyDate ,:receiptNumber ,:slipPrintedFlag ,:transferCaseloadId ,:receiptPrintedFlag ,:preWithholdAmount ,:deductionFlag ,:closingChequeNumber ,:remitterName ,:payeeCode ,:payeeNameText ,:payeeCorporateId ,:payeePersonId ,:adjustTxnId ,:adjustTxnEntryId ,:adjustOffenderId ,:adjustAccountCode ,:txnAdjustedFlag ,:deductionType ,:infoNumber ,:holdClearFlag ,:holdUntilDate ,:holdNumber ,:grossAmount ,:grossNetFlag ,:remitterId ,:applySpendingLimitAmount ,:receiptPendingPrintFlag ,:sealFlag ,:orgTxnType ,:createDatetime ,:createUserId ,:modifyDatetime ,:modifyUserId )
}

OTDRTTFU_OFFTXN_DELETE_OFFENDER_TRANSACTIONS { 
	DELETE FROM OFFENDER_TRANSACTIONS/* where */
}

OTDRTTFU_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTDRTTFU_CGFKCHK_OFF_TT_OFF_TT_CSLD {
	SELECT CSLD.DESCRIPTION FROM   CASELOADS CSLD WHERE  CSLD.CASELOAD_ID = :FROMCASELOAD
}

OTDRTTFU_CGWHEN_NEW_FORM_INSTANCE_ {
	--SELECT  SYSDATE, USER FROM    SYS.DUAL
	SELECT SYSDATE(),USER FROM DUAL;
}
OTDRTTFU_CHEQUE_NUMBER {
 SELECT CHEQUE_NUMBER  FROM   BANK_CHEQUE_REGISTERS  WHERE  CASELOAD_ID = :FRCASELOAD  AND  TXN_ID = :TXNID  AND  CHEQUE_STATUS = 'PRINTED'
 }
 
 OTDRTTFU_GET_OFFENDER_ID_TXNAMT_CLOSED_FLAG {
 --SELECT ot.offender_id, SUM (ot.txn_entry_amount) txnamt,
   --          DECODE (NVL (ota.account_closed_flag, 'X'), 'Y', '1', 'N', '2', '0') STA
     --   FROM offender_transactions ot, offender_trust_accounts ota
       --WHERE ot.txn_id = :txnId
         --AND ot.caseload_id = :frCaseloadId
        -- AND ota.caseload_id (+) = :toCaseloadId
        -- AND ot.offender_id = ota.offender_id (+)
       --GROUP BY DECODE (NVL (ota.account_closed_flag, 'X'), 'Y', '1', 'N', '2', '0'), ot.offender_id
 select
	ot.offender_id,
	SUM(ot.txn_entry_amount) txnamt,
	case
		when coalesce(ota.account_closed_flag, 'X')= 'Y' then '1'
		when coalesce(ota.account_closed_flag, 'X')= 'N' then '2'
		else '0'
	end STA
from
	offender_transactions ot
left outer join offender_trust_accounts ota on
	(ot.offender_id = ota.offender_id
		and ota.caseload_id = :toCaseloadId)
where
	ot.txn_id = :txnId
	and ot.caseload_id = :frCaseloadId
group by
	case
		when coalesce(ota.account_closed_flag, 'X')= 'Y' then '1'
		when coalesce(ota.account_closed_flag, 'X')= 'N' then '2'
		else '0'
	end ,
	ot.offender_id     
 }
       OTDRTTFU_GET_BOOK_ID {
       SELECT OFFENDER_BOOK_ID FROM OFFENDER_BOOKINGS WHERE OFFENDER_ID=:offenderId
       }
       
       OTDRTTFU_GET_ACTIVE_FLAG {
      -- SELECT DECODE (OFF_BKG.ACTIVE_FLAG, 'Y', 'ACTIVE-', 'N', 'INACTIVE-', NULL) ||
                --DECODE (OFF_BKG.IN_OUT_STATUS,
                   --'OUT', DECODE (
                          --   MOV_RSN.HEADER_STATUS_FLAG,
                        --     'Y', SUBSTR (
                      --               OFF_BKG.STATUS_REASON,
                    --                 INSTR (OFF_BKG.STATUS_REASON, '-', 1) + 1
                  --                ),
                --             OFF_BKG.IN_OUT_STATUS
              --            ),
            --       OFF_BKG.IN_OUT_STATUS
          --   ) ACTIVE_FLAG
        --FROM OFFENDER_BOOKINGS OFF_BKG, 
       --      MOVEMENT_REASONS MOV_RSN
      -- WHERE OFF_BKG.OFFENDER_BOOK_ID = :offenderBookId
        -- AND SUBSTR(OFF_BKG.STATUS_REASON, 1, INSTR (OFF_BKG.STATUS_REASON, '-', 1) - 1)
          --       = MOV_RSN.MOVEMENT_TYPE (+)
         --AND SUBSTR (OFF_BKG.STATUS_REASON, INSTR (OFF_BKG.STATUS_REASON, '-', 1) + 1)
                -- = MOV_RSN.MOVEMENT_REASON_CODE (+)
                
 
   SELECT CASE  OFF_BKG.ACTIVE_FLAG  WHEN 'Y' THEN  'ACTIVE-'  WHEN 'N' THEN  'INACTIVE-'  ELSE NULL END ||
                CASE  OFF_BKG.IN_OUT_STATUS
                    WHEN 'OUT' THEN  CASE 
                             MOV_RSN.HEADER_STATUS_FLAG
                              WHEN 'Y' THEN  SUBSTR (
                                     OFF_BKG.STATUS_REASON,
                                     INSTR (OFF_BKG.STATUS_REASON, '-', 1) + 1
                                  )
                              ELSE OFF_BKG.IN_OUT_STATUS
                           END
                    ELSE OFF_BKG.IN_OUT_STATUS
              END ACTIVE_FLAG
        FROM OFFENDER_BOOKINGS OFF_BKG LEFT OUTER JOIN 
             MOVEMENT_REASONS MOV_RSN ON SUBSTR(OFF_BKG.STATUS_REASON, 1, INSTR (OFF_BKG.STATUS_REASON, '-', 1) - 1)
                 = MOV_RSN.MOVEMENT_TYPE
       WHERE OFF_BKG.OFFENDER_BOOK_ID = :offenderBookId
                  AND SUBSTR (OFF_BKG.STATUS_REASON, INSTR (OFF_BKG.STATUS_REASON, '-', 1) + 1)
                 = MOV_RSN.MOVEMENT_REASON_CODE 
                 
                 }
OTDRTTFU_GET_ACT_STATUS {
--SELECT DECODE (ACCOUNT_CLOSED_FLAG, 'Y', 'CLOSED', 'OPEN') acStatus FROM OFFENDER_TRUST_ACCOUNTS  WHERE CASELOAD_ID = :toCaseloadId  AND OFFENDER_ID = :offenderId
SELECT CASE  ACCOUNT_CLOSED_FLAG  WHEN 'Y' THEN  'CLOSED'  ELSE 'OPEN' END acStatus FROM OFFENDER_TRUST_ACCOUNTS  WHERE CASELOAD_ID = :toCaseloadId  AND OFFENDER_ID = :offenderId
}
OTDRTTFU_GET_LASTNAME_FIRSTNAME {
SELECT OB.OFFENDER_BOOK_ID, LAST_NAME, FIRST_NAME, O.OFFENDER_ID_DISPLAY
           FROM OFFENDERS O, OFFENDER_BOOKINGS OB
          WHERE OB.ROOT_OFFENDER_ID = :offenderId
            AND OB.OFFENDER_ID = O.OFFENDER_ID
            AND OB.BOOKING_TYPE = :caseloadType
            AND OB.OFFENDER_BOOK_ID = (SELECT MAX (OB2.OFFENDER_BOOK_ID)
                                          FROM OFFENDER_BOOKINGS OB2
                                         WHERE OB2.ROOT_OFFENDER_ID = OB.ROOT_OFFENDER_ID
                                           AND OB2.BOOKING_TYPE = :caseloadType)
                                           }
                                           
                                           
                                           
OTDRTTFU_UPDATE_OFFENDER_TRUST_TRANSFERS {                              
   -- UPDATE OFFENDER_TRUST_TRANSFERS  SET POSTED_FLAG    = 'Y',  MODIFY_DATE    = TRUNC(SYSDATE),  MODIFY_USER_ID = USER   WHERE TXN_ID = :txnid
    UPDATE OFFENDER_TRUST_TRANSFERS  SET POSTED_FLAG    = 'Y',  MODIFY_DATE    = CURRENT_TIMESTAMP,  MODIFY_DATETIME = CURRENT_TIMESTAMP, MODIFY_USER_ID = :modifyUserId WHERE TXN_ID = :txnid
    }
    
    OTDRTTFU_SUBACNT_OFFID_TXNAMT_BKGID {
     SELECT OFFENDER_ID, SUB_ACCOUNT_TYPE, TXN_ENTRY_AMOUNT, OFFENDER_BOOK_ID FROM OFFENDER_TRANSACTIONS WHERE TXN_ID = :txnId
       ORDER BY OFFENDER_ID
    
    }
    OTDRTTFU_TXNTYPE_DES_TXNUSAGE_RECPRODFLG {
   -- SELECT TOPS.TXN_TYPE,   TTYS.DESCRIPTION, TTYS.TXN_USAGE,  NVL(TOPS.RECEIPT_PRODUCTION_FLAG, 'N') RECPRODFLG
     --     FROM TRANSACTION_OPERATIONS TOPS, TRANSACTION_TYPES TTYS  WHERE TOPS.MODULE_NAME = 'OTDRTTFU'    AND TOPS.TXN_TYPE = TTYS.TXN_TYPE
       --    AND TOPS.CASELOAD_ID = :globalCaseload   AND TTYS.CASELOAD_TYPE = :caseloadType     AND EXISTS   (SELECT ACCOUNT_CODE   FROM ACCOUNT_CODES 
         --  WHERE ACCOUNT_CODE = TOPS.CR_ACCOUNT_CODE
           --        AND SUB_ACCOUNT_TYPE = :subacntType)
    
     SELECT TOPS.TXN_TYPE,   TTYS.DESCRIPTION, TTYS.TXN_USAGE,  COALESCE(TOPS.RECEIPT_PRODUCTION_FLAG, 'N') RECPRODFLG
          FROM TRANSACTION_OPERATIONS TOPS, TRANSACTION_TYPES TTYS  WHERE TOPS.MODULE_NAME = 'OTDRTTFU'    AND TOPS.TXN_TYPE = TTYS.TXN_TYPE
           AND TOPS.CASELOAD_ID = :globalCaseload   AND TTYS.CASELOAD_TYPE = :caseloadType     AND EXISTS   (SELECT ACCOUNT_CODE   FROM ACCOUNT_CODES 
           WHERE ACCOUNT_CODE = TOPS.CR_ACCOUNT_CODE
                   AND SUB_ACCOUNT_TYPE = :subacntType)
                
    }
OTDRTTFU_UPDATE_OFFENDER_TRUST_TRANSFERS_WITH_CLOSED_FLAG_N {          
  UPDATE offender_trust_accounts   SET account_closed_flag = 'N', MODIFY_DATETIME = CURRENT_TIMESTAMP,
  MODIFY_USER_ID = :modifyUserId,  WHERE caseload_id = :tocaseload   AND offender_id = :offenderId          
          }
    OTDRTTFU_INSERT_INTO_OFFENDER_TRANS_FORM {   
         --INSERT INTO OFFENDER_TRUST_ACCOUNTS(
          -- ACCOUNT_CLOSED_FLAG,
          -- CASELOAD_ID,
           --OFFENDER_ID,
          -- HOLD_BALANCE,
          -- CURRENT_BALANCE,
        --   MODIFY_DATE,
      --     MODIFY_USER_ID)
    --  VALUES('N',:P_csld_id,:P_off_id,0,0,TRUNC(SYSDATE),USER)
   
    INSERT INTO OFFENDER_TRUST_ACCOUNTS(
           ACCOUNT_CLOSED_FLAG,
           CASELOAD_ID,
           OFFENDER_ID,
           HOLD_BALANCE,
           CURRENT_BALANCE,
           MODIFY_DATE,
           CREATE_DATETIME,         
           MODIFY_DATETIME,
           CREATE_USER_ID)
      VALUES('N',:P_csld_id,:P_off_id,0,0,CURRENT_DATE,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,:create_user_id)
    
    }
   OTDRTTFU_INSERT_OFFENDER_SUB_ACCOUNTS {
   --INSERT INTO OFFENDER_SUB_ACCOUNTS(
     --      CASELOAD_ID, 
       --    OFFENDER_ID, 
         --  TRUST_ACCOUNT_CODE,
          -- BALANCE,
           --LAST_TXN_ID,
           --MODIFY_DATE,
          --CREATE_DATETIME,
          --CREATE_USER_ID,
          --MODIFY_DATETIME)
     -- SELECT :caseloadId,:offenderId,account_code,0,:txnId,TRUNC(SYSDATE),USER
       -- FROM account_codes
       --WHERE sub_account_type IS NOT NULL
       --AND   CASELOAD_TYPE = :caseloadType
       INSERT INTO OFFENDER_SUB_ACCOUNTS(
           CASELOAD_ID, 
           OFFENDER_ID, 
           TRUST_ACCOUNT_CODE,
           BALANCE,
           LAST_TXN_ID,
           MODIFY_DATE,
          CREATE_DATETIME,
          CREATE_USER_ID,
          MODIFY_DATETIME)
      SELECT :caseloadId,:offenderId,account_code,0,:txnId,CURRENT_DATE,CURRENT_TIMESTAMP,:createUserId,CURRENT_TIMESTAMP
        FROM account_codes
       WHERE sub_account_type IS NOT NULL
       AND   CASELOAD_TYPE = :caseloadType
   
   
   }
   OTDRTTFU_GET_NEXT_VAL {
   -- SELECT  TXN_ID.NEXTVAL FROM DUAL   
   select nextval('txn_id') from dual
   }
   
   OTDRTTFU_INSERT_OFFENDER_TRANSACTIONS {
 --  INSERT INTO OFFENDER_TRANSACTIONS (
   --       TXN_ID,
     --     TXN_ENTRY_SEQ,
       --   CASELOAD_ID,
         -- OFFENDER_ID,
         -- OFFENDER_BOOK_ID,
       --   TXN_POSTING_TYPE,
        --  TXN_TYPE,
         -- TXN_ENTRY_DESC,
         -- TXN_ENTRY_AMOUNT,
          --TXN_ENTRY_DATE,
          --SUB_ACCOUNT_TYPE,
	 -- RECEIPT_NUMBER,
       --   MODIFY_DATE,
         -- MODIFY_USER_ID,
          --SLIP_PRINTED_FLAG,
         -- PRE_WITHHOLD_AMOUNT,
          --DEDUCTION_FLAG,
          --PAYEE_CODE,
          --PAYEE_CORPORATE_ID,
          --PAYEE_PERSON_ID,
          --PAYEE_NAME_TEXT,
          --DEDUCTION_TYPE,
          --INFO_NUMBER,
          --TRANSFER_CASELOAD_ID )
  -- SELECT :txnId,
    --      :txnseq,
      --    :tocaseloadId,
        --  :offenderId,
          --:offenderBookId,
          --'CR',
          --:txnType,
          --:txnDesc,
         -- :txnentryAmnt,
          --trunc(SYSDATE),
          --:subAcntType,
          --:receiptNum,
         -- SYSDATE,
         -- USER,
          --'N',
          --:txnentryAmnt,
          --'N',
         -- NULL,
         -- NULL,
         -- NULL,
         -- NULL,
         -- NULL,
          --NULL,
         -- :frCaseload
   --FROM   SYS.DUAL
   insert into OFFENDER_TRANSACTIONS ( TXN_ID, TXN_ENTRY_SEQ, CASELOAD_ID, OFFENDER_ID, OFFENDER_BOOK_ID, TXN_POSTING_TYPE, TXN_TYPE, TXN_ENTRY_DESC, TXN_ENTRY_AMOUNT, TXN_ENTRY_DATE, SUB_ACCOUNT_TYPE, RECEIPT_NUMBER, MODIFY_DATE, CREATE_USER_ID , SLIP_PRINTED_FLAG, PRE_WITHHOLD_AMOUNT, DEDUCTION_FLAG, PAYEE_CODE, PAYEE_CORPORATE_ID, PAYEE_PERSON_ID, PAYEE_NAME_TEXT, DEDUCTION_TYPE, INFO_NUMBER, TRANSFER_CASELOAD_ID , CREATE_DATETIME, MODIFY_DATETIME, MODIFY_USER_ID) select :txnId, :txnseq, :tocaseloadId, :offenderId, :offenderBookId, 'CR', :txnType, :txnDesc, :txnentryAmnt, CURRENT_TIMESTAMP, :subAcntType, :receiptNum, CURRENT_TIMESTAMP, :create_user_id , 'N', :txnentryAmnt, 'N', null, null, null, null, null, null, :frCaseload, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :create_user_id from DUAL
      }
   
   OTDRTTFU_GET_PROFILE_VALUE {
   SELECT 	profile_value 	FROM	SYSTEM_PROFILES WHERE	profile_type = 'CLIENT' AND	profile_code = 'DED_TRU_TRSF'
   }
   OTDRTTFU_DEDUCTIONS_GET_AC_AND_SET_IND_DATE {
   CALL OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:P_OFF_ID, :P_CSLD_ID)
   }
   