OFFENDER_PAYMENT_SCHEDULES_TJN_INSERT{
insert into OFFENDER_PAYMENT_SCHEDULES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, PAYMENT_PLAN_ID, PAYMENT_PLAN_SEQ, PAYMENT_DATE , PAYMENT_AMOUNT, RECURSIVE_AMOUNT, PAID_AMOUNT , PAID_RECURSIVE_AMOUNT, INFORMATION_NUMBER, OFFENDER_DEDUCTION_ID , GROUP_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG ) 
values ('INS', :createUserId, current_timestamp, null, null, 0, :PAYMENTPLANID, :PAYMENTPLANSEQ, :PAYMENTDATE , :PAYMENTAMOUNT, :RECURSIVEAMOUNT, :PAIDAMOUNT , :PAIDRECURSIVEAMOUNT, :INFORMATIONNUMBER, :OFFENDERDEDUCTIONID , :GROUPID, current_timestamp, :createUserId, current_timestamp, :SEALFLAG)
}

OFFENDER_PAYMENT_SCHEDULES_TJN_UPDATE{
insert into OFFENDER_PAYMENT_SCHEDULES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, PAYMENT_PLAN_ID, PAYMENT_PLAN_SEQ , PAYMENT_DATE, PAYMENT_AMOUNT, RECURSIVE_AMOUNT , PAID_AMOUNT, PAID_RECURSIVE_AMOUNT, INFORMATION_NUMBER , OFFENDER_DEDUCTION_ID, GROUP_ID , CREATE_DATETIME, CREATE_USER_ID , MODIFY_DATETIME, SEAL_FLAG ) 
values ('UPD', :createUserId, current_timestamp, null, null, 0, :PAYMENTPLANID, :PAYMENTPLANSEQ, :PAYMENTDATE , :PAYMENTAMOUNT, :RECURSIVEAMOUNT, :PAIDAMOUNT , :PAIDRECURSIVEAMOUNT, :INFORMATIONNUMBER, :OFFENDERDEDUCTIONID , :GROUPID, current_timestamp, :createUserId, current_timestamp, :SEALFLAG)
}

OFFENDER_PAYMENT_SCHEDULES_TJN_DELETE{
insert into OFFENDER_PAYMENT_SCHEDULES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, PAYMENT_PLAN_ID, PAYMENT_PLAN_SEQ , PAYMENT_DATE, PAYMENT_AMOUNT, RECURSIVE_AMOUNT , PAID_AMOUNT, PAID_RECURSIVE_AMOUNT, INFORMATION_NUMBER , OFFENDER_DEDUCTION_ID, GROUP_ID , CREATE_DATETIME, CREATE_USER_ID , MODIFY_DATETIME, SEAL_FLAG ) 
values ('DEL', :createUserId, current_timestamp, null, null, 0, :PAYMENTPLANID, :PAYMENTPLANSEQ, :PAYMENTDATE , :PAYMENTAMOUNT, :RECURSIVEAMOUNT, :PAIDAMOUNT , :PAIDRECURSIVEAMOUNT, :INFORMATIONNUMBER, :OFFENDERDEDUCTIONID , :GROUPID, current_timestamp, :createUserId, current_timestamp, :SEALFLAG)
}