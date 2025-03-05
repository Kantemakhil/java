INSERT_TRANSACTION_PAYEES_JN_TABLE {
INSERT INTO TRANSACTION_PAYEES_JN (
        jn_operation,
        jn_oracle_user,
        jn_datetime,
        jn_session,
        txn_type,
        payee_seq,
        payee_person_id,
        payee_corporate_id,
        modify_date,
        default_payee_flag,
        create_datetime,
        create_user_id,
        modify_datetime,
        seal_flag
    ) VALUES (
        :jnOperation,
        :jnOracleUser,
        current_timestamp,
        0,
        :txnType ,
        :payeeSeq ,
        :payeePersonId , 
        :payeeCorporateId ,
        :modifyDate ,
        :defaultPayeeFlag ,
        current_timestamp , 
        :createUserId , 
        current_timestamp ,
        :sealFlag     )
}