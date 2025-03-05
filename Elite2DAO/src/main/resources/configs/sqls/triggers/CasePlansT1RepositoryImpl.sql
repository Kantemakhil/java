UPDATE_OFFENDER_BOOKINGS_CASE_PLANST1{

update Offender_bookings set COMM_STAFF_ROLE = :role , COMM_STAFF_ID = :SAC_STAFF_ID, modify_datetime = current_timestamp, modify_user_id = :modifyUserId where Offender_Book_ID = :Offender_Book_ID
}

UPDATE_OFFENDER_BOOKINGS_OLD_RECORD{
select offender_book_id, booking_begin_date, booking_end_date, booking_no, offender_id , agy_loc_id, living_unit_id, disclosure_flag, in_out_status, active_flag, booking_status, youth_adult_code, finger_printed_staff_id , search_staff_id, photo_taking_staff_id, assigned_staff_id, create_agy_loc_id, booking_type, booking_created_date, root_offender_id, agency_iml_id , service_fee_flag, earned_credit_level, ekstrand_credit_level, intake_agy_loc_id, activity_date, intake_caseload_id , intake_user_id, case_officer_id, case_date, case_time, community_active_flag, create_intake_agy_loc_id, comm_staff_id, comm_status, community_agy_loc_id, no_comm_agy_loc_id, comm_staff_role , agy_loc_id_list, status_reason, total_unexcused_absences, request_name, record_user_id, intake_agy_loc_assign_date , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag from OFFENDER_BOOKINGS where OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID 
}