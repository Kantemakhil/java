OFFENDERS_VINE_INTF_TRG_CUR_ACT_BOOK_AN {
 SELECT AGY_LOC_ID, BOOKING_NO, OFFENDER_ID, offender_book_id FROM offender_bookings WHERE root_offender_id = :v_root_off_id AND offender_id <> :v_off_id AND active_flag = 'Y'
}

OFFENDERS_VINE_INTF_TRG_INSERT_AN_AUDIT {
insert into an_audit (action_type, agy_loc_id, offender_id_display, booking_no, first_name, last_name, middle_name, suffix, birth_date, offender_id, modified_date, offender_book_id, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME) values (:actionType, :agyLocId, :offenderIdDisplay, :bookingNo, :firstName, :lastName, :middleName, :suffix, :birthDate, :offenderId, current_timestamp, :offenderBookId, current_timestamp , :createUserId , NULL) 
}

OFFENDERS_VINE_INTF_TRG_OFFEDER_EXECUTE_QUERY {
	select * from offenders where offender_id=:offender_id
}

OFFENDERS_VINE_INTF_TRG_CUR_ACT_BOOK_OR {
 SELECT OFFENDER_BOOK_ID, AGY_LOC_ID, BOOKING_NO, BOOKING_BEGIN_DATE FROM offender_bookings WHERE offender_id = :v_off_id AND active_flag = 'Y' 
}

OFFENDERS_VINE_INTF_TRG_UPDATE_OR_AUDIT {
UPDATE or_audit SET action_type = :actionType, agy_loc_id = :agyLocId, offender_id_display = :offenderIdDisplay, booking_no = :bookingNo, booking_begin_date = :bookingBeginDate, first_name = :firstName, middle_name = :middleName, last_name = :lastName, birth_date = :birthDate, race_code = :raceCode, sex_code = :sexCode, release_date = :releaseDate, rel_reason_code = :relReasonCode, modified_date = current_timestamp, MODIFY_USER_ID =:modifyUserId, gender_code = :genderCode WHERE offender_id = :offenderId AND booking_no = :bookingNo AND action_type IN ('A','U')
}

OFFENDERS_VINE_INTF_TRG_INSERT_OR_AUDIT{
   insert into or_audit (action_type, agy_loc_id, offender_id_display, booking_no, booking_begin_date, first_name, middle_name, last_name, birth_date, race_code, sex_code, release_date, rel_reason_code, offender_id, modified_date, offender_book_id, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME, GENDER_CODE) values (:actionType, :agyLocId, :offenderIdDisplay, :bookingNo, :bookingBeginDate, :firstName, :middleName, :lastName, :birthDate, :raceCode, :sexCode, null, null, :offenderId, current_timestamp, :offenderBookId, current_timestamp , :createUserId , NULL, :genderCode)        
}

OFFENDERS_VINE_INTF_TRG_UPDATE_AN_AUDIT {
UPDATE an_audit SET action_type=:actionType, agy_loc_id=:agyLocId, offender_id_display=:offenderIdDisplay, booking_no=:bookingNo, first_name=:firstName, last_name=:lastName, middle_name=:middleName, suffix=:suffix, birth_date=:birthDate, modified_date=current_timestamp, offender_book_id = :offenderBookId, MODIFY_USER_ID =:modifyUserId WHERE offender_id=:offenderId AND action_type IN ('A','U')
}

OFFENDERS_VINE_INTF_TRG_CUR_ACT_BOOK_AN_ME {
 SELECT AGY_LOC_ID, BOOKING_NO, OFFENDER_ID, offender_book_id FROM offender_bookings WHERE root_offender_id = :v_root_off_id AND offender_id <> :v_off_id AND (active_flag = 'Y' OR EXISTS (SELECT 1 FROM offender_bookings WHERE root_offender_id = :v_old_root_off_id AND active_flag = 'Y')) 
}

OFFENDERS_VINE_INTF_TRG_CUR_ME_EXIST {
 SELECT count(*) FROM me_audit WHERE old_offender_id_display = :offender_id_display
}

OFFENDERS_VINE_INTF_TRG_INSERT_ME_AUDIT {
 insert into me_audit (action_type, agy_loc_id, old_offender_id_display, new_offender_id_display, new_booking_no, modified_date, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME) values (:actionType, :agyLocId, :oldOffenderIdDisplay, :newOffenderIdDisplay, :newBookingNo, current_timestamp, current_timestamp , :createUserId , NULL) 
}