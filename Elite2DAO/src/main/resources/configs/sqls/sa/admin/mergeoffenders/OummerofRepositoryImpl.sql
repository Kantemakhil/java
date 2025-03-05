
OUMMEROF_OFFBOOKS_FIND_OFFENDER_BOOKINGS {
 	SELECT offender_book_id, booking_begin_date, booking_end_date, booking_no, offender_id, agy_loc_id, living_unit_id, disclosure_flag, in_out_status, active_flag, booking_status, youth_adult_code, finger_printed_staff_id, search_staff_id, photo_taking_staff_id, assigned_staff_id, create_agy_loc_id, booking_type, booking_created_date, root_offender_id, agency_iml_id, service_fee_flag, earned_credit_level, ekstrand_credit_level, intake_agy_loc_id, activity_date, intake_caseload_id, intake_user_id, case_officer_id, case_date, case_time, community_active_flag, create_intake_agy_loc_id, comm_staff_id, comm_status, community_agy_loc_id, no_comm_agy_loc_id, comm_staff_role, agy_loc_id_list, status_reason, total_unexcused_absences, request_name, record_user_id, intake_agy_loc_assign_date, create_datetime, create_user_id, modify_datetime, modify_user_id FROM offender_bookings WHERE ROOT_OFFENDER_ID = :offenderBookId ORDER BY ACTIVE_FLAG desc, BOOKING_NO desc
}

OUMMEROF_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_BOOKINGS O WHERE O.ROOT_OFFENDER_ID = :ROOTOFFENDERID
}

OUMMEROF_OFF_BOOKS_POSTQUERY {
	SELECT REFERENCE_CODES.DESCRIPTION FROM   REFERENCE_CODES REFERENCE_CODES WHERE  REFERENCE_CODES.CODE = :bookingStatus AND     DOMAIN = 'BOOK_STS'
}

OUMMEROF_OFF_BKG2_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_BOOKINGS O WHERE O.ROOT_OFFENDER_ID = :ROOTOFFENDERID
}

OUMMEROF_OFF_BOOKS2_POSTQUERY_ {
	SELECT REFERENCE_CODES.DESCRIPTION FROM   REFERENCE_CODES REFERENCE_CODES WHERE  REFERENCE_CODES.CODE = :BOOKINGSTATUS AND     DOMAIN = 'BOOK_STS'
}

OUMMEROF_OFF_BOOKS2_POSTQUERY {
	SELECT COUNT (*) FROM OFFENDER_TRUST_ACCOUNTS WHERE OFFENDER_ID = :offenderId
}
OUMMEROF_GET_OFFENDER_BOOK_ID {
	SELECT OFFENDER_BOOK_ID FROM V_HEADER_BLOCK_fn(:user_name) WHERE OFFENDER_ID_DISPLAY = :offenderIdDisplay
}
OUMMEROF_VOFFBKG_FIND_V_HEADER_BLOCK {
 	SELECT OFFENDER_ID ,ALIAS_OFFENDER_ID ,OFFENDER_ID_DISPLAY ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,SUFFIX ,BIRTH_DATE ,OFFENDER_BOOK_ID ,BOOKING_NO ,BOOKING_BEGIN_DATE ,
 	BOOKING_END_DATE , case when booking_type = 'COMM' then intake_agy_loc_id when booking_type = 'INST' then AGY_LOC_ID else AGY_LOC_ID end AGY_LOC_ID,AGENCY_IML_ID ,LIVING_UNIT_ID ,DISCLOSURE_FLAG ,ACTIVE_FLAG ,BOOKING_STATUS ,LIVING_UNIT_DESCRIPTION ,IN_OUT_STATUS ,STATUS_DISPLAY ,ROOT_OFFENDER_ID ,ASSIGNED_STAFF_ID ,AGY_LOC_TYPE ,CREATE_AGY_LOC_ID ,BOOKING_TYPE ,BOOKING_CREATED_DATE ,LOCATION_CODE ,LIV_UNIT_DESC ,INTAKE_AGY_LOC_ID ,COMMUNITY_ACTIVE_FLAG ,CREATE_INTAKE_AGY_LOC_ID ,COMMUNITY_STATUS ,STATUS_REASON ,HEADER_STATUS ,AGE ,GENDER ,OFFICER ,PRISON_LOCATION ,OFF_ALERTS ,STATUS_1 ,STATUS_2 ,STATUS_3 ,ETHNICITY ,MOVEMENT_REASON ,OFF_SUP_LEVEL,SEAL_FLAG   FROM V_HEADER_BLOCK_fn(:user_name) VHB1
}

OUMMEROF_FIND_RGASSIGNMENTREASON {
 	SELECT  CODE ,DESCRIPTION    FROM REFERENCE_CODES   WHERE DOMAIN = 'CHG_HOUS_RSN' AND ACTIVE_FLAG ='Y'
}
OUMMEROF_UPDATE_OFFENDER_BOOKINGS {
UPDATE offender_bookings SET OFFENDER_ID = :offenderId WHERE offender_book_id = :offenderBookId
}
OUMMEROF_GET_MERGE_TRANSACTION_ID_VAL {
select coalesce(MAX(MERGE_TRANSACTION_ID)+1,1) from merge_transactions
}
OUMMEROF_MERGE_TRANSACTION_RECORD_INSERT {
insert INTO merge_transactions ( merge_transaction_id, request_date, request_status_code, queue_message_id, transaction_source, offender_book_id_1, root_offender_id_1, offender_id_1, offender_id_display_1, last_name_1, first_name_1, offender_book_id_2, root_offender_id_2, offender_id_2, offender_id_display_2, last_name_2, first_name_2, create_user_id, create_datetime, transaction_type ) 
values ( :pMergeTransactionId, current_timestamp, :requestStatusCode, :queueMessageId, :transactionSource, :pFromOffBookId, :pFromRootOffId, :pFromOffenderId, :pFromOffIdDisplay, :pFromLastname, :pFromFirstName, :pToOffBookId, :pToRootOffId, :pToOffenderId, :pToOffIdDisplay, :pToLastName, :pToFirstName, :createUserId,current_timestamp, :transactionType )
}
OUMMEROF_UPDATE_OFFENDER_RECORD {
UPDATE offender_bookings SET ROOT_OFFENDER_ID = :rootOffenderId WHERE offender_id = :offenderId
}
OUMMEROF_VOFFBKG_FIND_V_HEADER_BLOCK {
 	SELECT OFFENDER_ID ,ALIAS_OFFENDER_ID ,OFFENDER_ID_DISPLAY ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,SUFFIX ,BIRTH_DATE ,OFFENDER_BOOK_ID ,BOOKING_NO ,BOOKING_BEGIN_DATE ,BOOKING_END_DATE ,AGY_LOC_ID ,AGENCY_IML_ID ,LIVING_UNIT_ID ,DISCLOSURE_FLAG ,ACTIVE_FLAG ,BOOKING_STATUS ,LIVING_UNIT_DESCRIPTION ,IN_OUT_STATUS ,STATUS_DISPLAY ,ROOT_OFFENDER_ID ,ASSIGNED_STAFF_ID ,AGY_LOC_TYPE ,CREATE_AGY_LOC_ID ,BOOKING_TYPE ,BOOKING_CREATED_DATE ,LOCATION_CODE ,LIV_UNIT_DESC ,INTAKE_AGY_LOC_ID ,COMMUNITY_ACTIVE_FLAG ,CREATE_INTAKE_AGY_LOC_ID ,COMMUNITY_STATUS ,STATUS_REASON ,HEADER_STATUS ,AGE ,GENDER ,OFFICER ,PRISON_LOCATION ,OFF_ALERTS ,STATUS_1 ,STATUS_2 ,STATUS_3 ,ETHNICITY ,MOVEMENT_REASON ,OFF_SUP_LEVEL   FROM V_HEADER_BLOCK_FN(:user_name) VHB1
}
OUMMEROF_GET_OFF_BOOK_ID{
select offender_book_id  from offender_bookings ob where offender_id = :offenderId
}


OUMMEROF_UPDATE_OFFENDER_RECORD {
UPDATE offenders  set last_name = :lastName, first_name = :firstName  WHERE offender_id = :offenderId
}

OUMMEROF_UPDATE_OFFENDER_ID_RECORD {
UPDATE offenders  SET offender_id = :offenderId WHERE ROOT_OFFENDER_ID = :rootOffenderId
}

OUMMEROF_OLD_OFF_DATA{
select * from offenders where offender_id = :offenderId
}


OUMMEROF_GET_NEW_OFF_ID{
select offender_id from offender_bookings ob where offender_book_id = :offBookId
}
