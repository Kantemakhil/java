GET_LOCKED_DATE{
SELECT LOCKED_DATE
        FROM LOCKED_MODULES
       WHERE MODULE_NAME = :P_MODULE_NAME
         AND CASELOAD_ID = :P_CASELOAD_ID
}

L_BEN_PERSON_TXN_CUR{
--SELECT BT.*,
--                OD.OFFENDER_ID
--           FROM BENEFICIARY_TRANSACTIONS BT, OFFENDER_DEDUCTIONS OD, BENEFICIARY_TRANSACTIONS BTX
--          WHERE BT.PERSON_ID IS NOT NULL
--            AND BT.PERSON_ID = :P_PERSON_ID
--            AND BT.ACCOUNT_CODE = :P_ACCOUNT_CODE
--            AND BT.CASELOAD_ID = :P_CASELOAD_ID
--				AND BT.REV_TXN_ID = BTX.TXN_ID(+)
--				AND BT.REV_TXN_ENTRY_SEQ = BTX.TXN_ENTRY_SEQ(+)
--				AND BT.REV_GL_ENTRY_SEQ = BTX.GL_ENTRY_SEQ(+)
--				AND BT.BEN_ENTRY_SEQ = BTX.BEN_ENTRY_SEQ(+)
--            AND (  BT.BENEFICIARY_CLEARED_FLAG = 'N'
--                OR BT.BENEFICIARY_CLEARED_FLAG IS NULL)
--            AND DECODE(BT.REV_TXN_ID, NULL, BT.TXN_ENTRY_DATE, BTX.TXN_ENTRY_DATE) <=
--                   (SELECT SYSDATE - NVL (MAX (TO_NUMBER (S1.PROFILE_VALUE)), 0)
--                      FROM SYSTEM_PROFILES S1, SYSTEM_PROFILES S2
--                     WHERE S1.PROFILE_TYPE = 'TRUST_INF'
--                       AND S1.PROFILE_CODE = 'CHECK_AGING'
--                       AND S2.PROFILE_TYPE = 'CHECK_AGING'
--                       AND S2.PROFILE_CODE = BT.RECEIPT_TXN_TYPE)
--            AND BT.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID
--            AND BT.TXN_ENTRY_TIME < :P_LOCKED_DATE
--          ORDER BY BT.TXN_ID

SELECT
    bt.*,
    od.offender_id
FROM
    beneficiary_transactions bt
    INNER JOIN offender_deductions      od ON bt.offender_deduction_id = od.offender_deduction_id
    LEFT JOIN beneficiary_transactions btx ON bt.rev_txn_id = btx.txn_id
                                              AND bt.rev_txn_entry_seq = btx.txn_entry_seq
                                              AND bt.rev_gl_entry_seq = btx.gl_entry_seq
                                              AND bt.ben_entry_seq = btx.ben_entry_seq
WHERE
    bt.person_id IS NOT NULL
    AND bt.person_id = :P_PERSON_ID
    AND bt.account_code = :P_ACCOUNT_CODE
    AND bt.caseload_id = :P_CASELOAD_ID
    AND ( bt.beneficiary_cleared_flag = 'N'
          OR bt.beneficiary_cleared_flag IS NULL )
    AND case 
         when bt.rev_txn_id IS NULL then  bt.txn_entry_date  else btx.txn_entry_date
     end <= (
        SELECT
                    current_timestamp - coalesce(
                        MAX(
                            s1.profile_value::bigint), 0
                    ) * interval '1 day'
         
        FROM
            system_profiles s1,
            system_profiles s2
        WHERE
            s1.profile_type = 'TRUST_INF'
            AND s1.profile_code = 'CHECK_AGING'
            AND s2.profile_type = 'CHECK_AGING'
            AND s2.profile_code = bt.receipt_txn_type
    )
    AND bt.txn_entry_time < :P_LOCKED_DATE
ORDER BY
    bt.txn_id
}

L_BEN_CORPORATE_TXN_CUR{
--SELECT BT.*,
--                OD.OFFENDER_ID
--           FROM BENEFICIARY_TRANSACTIONS BT, OFFENDER_DEDUCTIONS OD, BENEFICIARY_TRANSACTIONS BTX
--          WHERE BT.CORPORATE_ID IS NOT NULL
--            AND BT.CORPORATE_ID = :P_CORPORATE_ID
--            AND BT.ACCOUNT_CODE = :P_ACCOUNT_CODE
--            AND BT.CASELOAD_ID = :P_CASELOAD_ID
--				AND BT.REV_TXN_ID = BTX.TXN_ID(+)
--				AND BT.REV_TXN_ENTRY_SEQ = BTX.TXN_ENTRY_SEQ(+)
--				AND BT.REV_GL_ENTRY_SEQ = BTX.GL_ENTRY_SEQ(+)
--				AND BT.BEN_ENTRY_SEQ = BTX.BEN_ENTRY_SEQ(+)
--            AND (  BT.BENEFICIARY_CLEARED_FLAG = 'N'
--                OR BT.BENEFICIARY_CLEARED_FLAG IS NULL)
--            AND DECODE(BT.REV_TXN_ID, NULL, BT.TXN_ENTRY_DATE, BTX.TXN_ENTRY_DATE) <=
--                   (SELECT SYSDATE - NVL (MAX (TO_NUMBER (S1.PROFILE_VALUE)), 0)
--                      FROM SYSTEM_PROFILES S1, SYSTEM_PROFILES S2
--                     WHERE S1.PROFILE_TYPE = 'TRUST_INF'
--                       AND S1.PROFILE_CODE = 'CHECK_AGING'
--                       AND S2.PROFILE_TYPE = 'CHECK_AGING'
--                       AND S2.PROFILE_CODE = BT.RECEIPT_TXN_TYPE)
--            AND BT.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID
--            AND BT.TXN_ENTRY_TIME < :P_LOCKED_DATE
--          ORDER BY BT.TXN_ID
SELECT
    bt.*,
    od.offender_id
FROM
    beneficiary_transactions bt
    INNER JOIN offender_deductions      od ON bt.offender_deduction_id = od.offender_deduction_id
    LEFT JOIN beneficiary_transactions btx ON bt.rev_txn_id = btx.txn_id
                                              AND bt.rev_txn_entry_seq = btx.txn_entry_seq
                                              AND bt.rev_gl_entry_seq = btx.gl_entry_seq
                                              AND bt.ben_entry_seq = btx.ben_entry_seq
WHERE
    bt.corporate_id IS NOT NULL
    AND bt.corporate_id = :P_CORPORATE_ID
    AND bt.account_code = :P_ACCOUNT_CODE
    AND bt.caseload_id = :P_CASELOAD_ID
    AND ( bt.beneficiary_cleared_flag = 'N'
          OR bt.beneficiary_cleared_flag IS NULL )
    AND case 
         when bt.rev_txn_id IS NULL then  bt.txn_entry_date  else btx.txn_entry_date
     end <= (
        SELECT
                    current_timestamp - coalesce(
                        MAX(
                            s1.profile_value::bigint), 0
                    ) * interval '1 day'
        FROM
            system_profiles s1,
            system_profiles s2
        WHERE
            s1.profile_type = 'TRUST_INF'
            AND s1.profile_code = 'CHECK_AGING'
            AND s2.profile_type = 'CHECK_AGING'
            AND s2.profile_code = bt.receipt_txn_type
    )
    AND bt.txn_entry_time < :P_LOCKED_DATE
ORDER BY
    bt.txn_id
}

L_CHEQUE_DETAILS{
SELECT A.CR_ACCOUNT_CODE,
                A.TXN_TYPE,
                A.CHEQUE_PRODUCTION_FLAG,
                B.DESCRIPTION,
                C.TXN_POSTING_TYPE,
                D.TXN_POSTING_TYPE
           FROM TRANSACTION_OPERATIONS A,
                TRANSACTION_TYPES B,
                ACCOUNT_CODES C,
                ACCOUNT_CODES D
          WHERE A.MODULE_NAME = :P_MODULE_NAME
            AND A.CASELOAD_ID = :P_CASELOAD_ID
            AND B.TXN_TYPE = A.TXN_TYPE
            AND C.ACCOUNT_CODE = :P_DR_ACCOUNT_CODE
            AND D.ACCOUNT_CODE = A.CR_ACCOUNT_CODE
}

L_VALIDATE_PERSON{
SELECT 1
           FROM OFFENDER_BENEFICIARIES
          WHERE PERSON_ID = :P_PERSON_ID
            AND OFFENDER_DEDUCTION_ID = :P_OFFENDER_DEDUCTION_ID
}

L_VALIDATE_CORPORATE{
SELECT 1
           FROM OFFENDER_BENEFICIARIES
          WHERE CORPORATE_ID = :P_CORPORATE_ID
            AND OFFENDER_DEDUCTION_ID = :P_OFFENDER_DEDUCTION_ID
}

UPDATE_BENEFICIARY_TRANSACTIONS_BY_PERSON_ID{
UPDATE BENEFICIARY_TRANSACTIONS 
               SET BENEFICIARY_CLEARED_FLAG = 'Y',
               		MODIFY_DATETIME=current_timestamp,
               		MODIFY_USER_ID=:MODIFY_USER_ID
             WHERE PERSON_ID = :P_PERSON_ID
               AND TXN_ID = :TXN_ID
               AND OFFENDER_DEDUCTION_ID =
                      :OFFENDER_DEDUCTION_ID
               AND ACCOUNT_CODE = :P_ACCOUNT_CODE
}

INSERT_BANK_CHEQUE_BENEFICIARIES_FOR_PERSON_ID{
INSERT INTO BANK_CHEQUE_BENEFICIARIES (
                           CHEQUE_TXN_ID,
                           CHEQUE_AMOUNT,
                           PERSON_ID,
                           CORPORATE_ID,
                           TXN_ID,
                           OFFENDER_ID,
                           AMOUNT,
                           OFFENDER_DEDUCTION_ID,
                           CREATE_DATETIME,
                           MODIFY_DATETIME,
                           CREATE_USER_ID
                        )
                 VALUES (
                    :P_NEW_TXN_ID,
                    :P_TOTAL_AMOUNT,
                    :PERSON_ID,
                    NULL,
                   	:TXN_ID,
                    :OFFENDER_ID::bigint,
                    :L_ABS_TXN_ENTRY_AMOUNT, 
                    :OFFENDER_DEDUCTION_ID,
                    current_timestamp,
                    current_timestamp,
                    :CREATE_USER_ID
                 )
}

UPDATE_BENEFICIARY_TRANSACTIONS_BY_CORPORATE_ID{
UPDATE BENEFICIARY_TRANSACTIONS 
               SET BENEFICIARY_CLEARED_FLAG = 'Y',
               	   MODIFY_DATETIME=current_timestamp,
               	   MODIFY_USER_ID=:userName
             WHERE CORPORATE_ID = :P_CORPORATE_ID
               AND TXN_ID = :TXN_ID
               AND OFFENDER_DEDUCTION_ID =
                      :OFFENDER_DEDUCTION_ID
               AND ACCOUNT_CODE = :P_ACCOUNT_CODE
}

INSERT_BANK_CHEQUE_BENEFICIARIES_FOR_CORPORATE_ID{
INSERT INTO BANK_CHEQUE_BENEFICIARIES (
                           CHEQUE_TXN_ID,
                           CHEQUE_AMOUNT,
                           PERSON_ID,
                           CORPORATE_ID,
                           TXN_ID,
                           OFFENDER_ID,
                           AMOUNT,
                           OFFENDER_DEDUCTION_ID,
                           CREATE_DATETIME,
                           MODIFY_DATETIME,
                           CREATE_USER_ID
                        )
                 VALUES (
                    :P_NEW_TXN_ID,
                    :P_TOTAL_AMOUNT,
                    NULL,
                    :CORPORATE_ID,
                    :TXN_ID,
                    :OFFENDER_ID::bigint,
                    :AMOUNT, 
                    :OFFENDER_DEDUCTION_ID,
                    current_timestamp,
                    current_timestamp,
                    :CREATE_USER_ID
                 )
}

