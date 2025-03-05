
OIIBOOKS_OFFBOOKS_FIND_OFFENDER_BOOKINGS {
 	SELECT offender_book_id, booking_begin_date, booking_end_date, booking_no, offender_id, agy_loc_id, living_unit_id, disclosure_flag, in_out_status, active_flag, booking_status, youth_adult_code, finger_printed_staff_id, search_staff_id, photo_taking_staff_id, assigned_staff_id, create_agy_loc_id, booking_type, booking_created_date, root_offender_id, agency_iml_id, service_fee_flag, earned_credit_level, ekstrand_credit_level, intake_agy_loc_id, activity_date, intake_caseload_id, intake_user_id, case_officer_id, case_date, case_time, community_active_flag, create_intake_agy_loc_id, comm_staff_id, comm_status, community_agy_loc_id, no_comm_agy_loc_id, comm_staff_role, agy_loc_id_list, status_reason, total_unexcused_absences, request_name, record_user_id, intake_agy_loc_assign_date, create_datetime, create_user_id, modify_datetime, modify_user_id FROM offender_bookings WHERE ROOT_OFFENDER_ID = :offenderBookId ORDER BY booking_no DESC
}
OIIBOOKS_BOOKDETAIL_FIND_FORM_ACCESSIBLE_FORMS {
 	select ORIGINATING_FORM, DESTINATION_FORM, LIST_SEQ, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID from FORM_ACCESSIBLE_FORMS where  ORIGINATING_FORM = 'OIIBOOKS'
}

OIIBOOKS_BOOK_DETAIL_POSTQUERY {
	SELECT DESCRIPTION FROM OMS_MODULES  WHERE MODULE_NAME = :destinationForm AND MODULE_TYPE = 'SCREEN'
}

OIIBOOKS_CHECK_FORM_ACCESS {
select '1' from DUAL where exists ( select '1' from MODULE_PRIVILEGES MP, STAFF_MEMBER_ROLES SMR, STAFF_MEMBERS SM where SM.USER_ID = :create_userid and SM.STAFF_ID = SMR.STAFF_ID and SMR.ROLE_ID = MP.ROLE_ID and MP.MODULE_NAME = :modulename)
}

GET_OFFENDER_OBJECT_ON_ID{
	select A.*, B.image_id from ( select * from V_HEADER_BLOCK_FN(:USERID) v_header_block where offender_book_id = :offenderBookId)A left outer join ( select * from images i where image_object_type = 'OFF_BKG' and active_flag = 'Y')B on A.offender_book_id = B.image_object_id
}