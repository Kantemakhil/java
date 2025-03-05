
UPDATE_COUT_EVENTS{
update offender_bookings set community_active_flag = 'Y', booking_status = 'O', intake_agy_loc_id =:p_agy_loc_id, create_intake_agy_loc_id =:p_agy_loc_id, assigned_staff_id =:p_staffid, booking_end_date = null, community_agy_loc_id = null, agy_loc_id_list = null, comm_status = :p_comm_status, booking_type =:p_booking_type, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id =:p_off_book_id
}

C_GET_TRUST_ACT_FLAG{
SELECT trust_accounts_flag FROM caseloads WHERE caseload_id = :p_csld_id
}

GET_CRAC_AND_REC_FLAG{
SELECT cr_account_code, receipt_production_flag FROM transaction_operations WHERE module_name = 'OCDINTAK' AND txn_type = 'ADI' AND caseload_id = :pCsldId
}

GET_PTXN_PTG_TYPE_AND_SUBAC_TYPE{
SELECT txn_posting_type, sub_account_type FROM account_codes WHERE account_code = :cr_ac AND caseload_type = 'COMM'
}

GET_P_TXN_DESC{
SELECT description FROM transaction_types WHERE txn_type = :p_txn_type AND caseload_type = 'COMM'
}

CHECK_IF_SEQUENCE_EXISTS{
SELECT COUNT(*) FROM all_sequences WHERE sequence_name = :inputStg
}

GET_TXN_ID{
SELECT NEXTVAL('txn_id') FROM dual
}


UPDATE_OFFEN_SUB_ACC{
update offender_sub_accounts set modify_user_id = :modifyUserId, modify_date = current_timestamp , MODIFY_DATETIME = CURRENT_TIMESTAMP, last_txn_id = :p_txn_id, balance = coalesce (balance, 0) + 0 where trust_account_code in ( select account_code from account_codes where sub_account_type is not null and sub_account_type = :p_subac_type and caseload_type = 'COMM' ) and caseload_id = :p_csld_id and offender_id = :p_off_id
}

UPDATE_OFFENDER_TRUST_ACCOUNTS{
update offender_trust_accounts set account_closed_flag = 'N', hold_balance = 0, modify_user_id = :modifyUserId, MODIFY_DATETIME = CURRENT_TIMESTAMP, modify_date = CURRENT_TIMESTAMP, current_balance = 0 where offender_id = :p_off_id and caseload_id = :p_csld_id
}

INSERT_OFFENDER_TRUST_ACCOUNTS{
insert into offender_trust_accounts ( account_closed_flag, caseload_id, offender_id, hold_balance, current_balance, modify_date, CREATE_USER_ID , CREATE_DATETIME, MODIFY_DATETIME) values ( 'N', :p_csld_id, :p_off_id, 0, 0, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP , null)
}

INSERT_OFFENDER_SUB_ACCOUNTS{
insert into offender_sub_accounts ( caseload_id, offender_id, trust_account_code, balance, last_txn_id, modify_date, CREATE_USER_ID , CREATE_DATETIME, MODIFY_DATETIME) select :p_csld_id, :p_off_id, account_code, 0, :p_txn_id, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP , null from account_codes where sub_account_type is not null and caseload_type = 'COMM'
}

DED_TYPE_CURSOR{
SELECT deduction_type FROM offender_deductions WHERE offender_id = :p_off_id
}

GET_CASE_LOAD_CODE{
SELECT caseload_code FROM deduction_types WHERE deduction_type = :deduction_type
}

GET_GROUP_ID{
SELECT GROUP_ID FROM grouped_obligations WHERE deduction_type = :deduction_type
}

UPDATE_OFFENDER_DEDUCTIONS_OCDINTAK_PKG{
update offender_deductions set group_id = :v_group_id, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_id =:p_off_id and deduction_type =:deduction_type
}

INSERT_OFF_TXN{
insert into offender_transactions ( txn_id, txn_entry_seq, caseload_id, offender_id, offender_book_id, txn_posting_type, txn_type, txn_entry_desc, txn_entry_amount, txn_entry_date, sub_account_type, modify_date, CREATE_USER_ID, hold_clear_flag, receipt_number, CREATE_DATETIME) values ( :p_txn_id, 1, :p_csld_id, :p_off_id, null, :p_txn_ptg_type, 'ADI', :p_txn_desc, 0, CURRENT_TIMESTAMP, :p_subac_type, CURRENT_TIMESTAMP, :createUserId, 'Y', :p_receipt_no , CURRENT_TIMESTAMP)
}

INST_OFF_BOOKING{
insert into OFFENDER_BOOKINGS (OFFENDER_BOOK_ID, BOOKING_BEGIN_DATE, BOOKING_NO, OFFENDER_ID, ROOT_OFFENDER_ID, DISCLOSURE_FLAG, IN_OUT_STATUS, BOOKING_STATUS, YOUTH_ADULT_CODE, BOOKING_TYPE, CREATE_INTAKE_AGY_LOC_ID, BOOKING_CREATED_DATE, ASSIGNED_STAFF_ID, INTAKE_CASELOAD_ID, COMMUNITY_ACTIVE_FLAG, INTAKE_AGY_LOC_ID, COMM_STATUS , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values (:P_BOOK_ID, :P_BEGIN_DATE, :P_BOOKING_NO, :P_OFFENDER_ID, :P_ROOT_OFF_ID, :P_DISCLOSURE_FLAG, :P_IN_OUT_STATUS, :P_BOOKING_STATUS, :P_YOUTH_ADULT_CODE, :P_BOOKING_TYPE, :P_CREATE_AGY_LOC_ID, :P_BOOKING_CREATED_DATE, :P_STAFFID, :P_CASELOAD_ID, :P_COMMUNITY_ACTIVE_FLAG, :P_INTAKE_AGY_LOC_ID, :P_COMM_STATUS , :createUserId, CURRENT_TIMESTAMP , NULL ) 
}

INST_OFF_BOOK_AGY_LOC{
insert into OFFENDER_BOOKING_AGY_LOCS (CASELOAD_ID, AGY_LOC_ID, OFFENDER_BOOK_ID, ADDITION_DATE, ADDITION_TIME, REASON_CODE, REMOVED_DATE, ADDITION_COMMENT, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values (:P_CASELOAD_ID, :P_AGY_LOC_ID, :P_OFFENDER_BOOK_ID, :P_ADDITION_DATE, :P_ADDITION_TIME, :P_REASON_CODE, :P_REMOVED_DATE, :P_ADDITION_COMMENT , :createUserId, CURRENT_TIMESTAMP , null )
}



V_OFFENDER_ALL_SCHEDULES{
 INSERT INTO OFFENDER_IND_SCHEDULES
           (EVENT_ID
		   , OFFENDER_BOOK_ID
		   , EVENT_DATE
		   , START_TIME, END_TIME
		   , EVENT_CLASS,
            EVENT_TYPE, EVENT_SUB_TYPE, EVENT_STATUS, EVENT_OUTCOME, confirm_flag, COMMENT_TEXT, HIDDEN_COMMENT_TEXT,
            APPLICATION_DATE, AGY_LOC_ID,
            TO_AGY_LOC_ID, TO_INTERNAL_LOCATION_ID,
            OUTCOME_REASON_CODE,
            IN_CHARGE_STAFF_ID, CREDITED_HOURS,
            PIECE_WORK, ENGAGEMENT_CODE, UNDERSTANDING_CODE, DETAILS,
            AGREED_TRAVEL_HOUR, UNPAID_WORK_SUPERVISOR, UNPAID_WORK_BEHAVIOUR,
            UNPAID_WORK_ACTION, SICK_NOTE_RECEIVED_DATE, SICK_NOTE_EXPIRY_DATE,
            UNEXCUSED_ABSENCE_FLAG, ESCORT_CODE, DIRECTION_CODE, TO_CITY_CODE,
            FROM_CITY_CODE, OFF_PRGREF_ID, IN_TIME,
            OUT_TIME, PERFORMANCE_CODE, REFERENCE_ID, TRANSPORT_CODE,
            APPLICATION_TIME, CONTACT_PERSON_NAME,
            TO_address_owner_class, TO_address_ID, RETURN_DATE, RETURN_TIME,
            TO_CORPORATE_ID, TA_ID,PROV_STATE_CODE, scheduled_trip_id,create_user_id,create_datetime,modify_datetime)
     VALUES (NEXTVAL('EVENT_ID'), :offenderBookId, :movedOutDate, :movedOutTime,
             :endTime, :eventClass, :eventType, :eventSubType,
             :eventStatus, :eventOutcome, :confirmFlag, :commentText, :hiddenCommentText,
             :applicationDate,
             :agyLocId, :createAgyLocId, :toInternalLocationId,
             :outcomeReasonCode,
             :inChargeStaffId,
             :creditedHours, :pieceWork, :engagementCode,
             :understandingCode, :details,
             :agreedTravelHour, :unpaidWorkSupervisor, :unpaidWorkBehaviour,
             :unpaidWorkAction, :sickNoteReceivedDate, :sickNoteExpiryDate,
             :unexcusedAbsenceFlag, :escortCode, :directionCode, :toCityCode,
             :fromCityCode, :offPrgrefId, :inTime,
             :outTime, :performanceCode, :referenceId, :transportCode,
             :applicationTime,  :contactPersonName,
             :toAddressOwnerClass, :toAddressId, :returnDate, :returnTime,
             :toCorporateId, :taId, :provStateCode, :scheduledTripId,:createUserId,CURRENT_TIMESTAMP,null)
 }
 
 GET_OFFENDER_LATEST_BOOKING {
SELECT MAX (offender_book_id)
     FROM v_header_block
    WHERE root_offender_id = :rootOffenderId
    }
                  