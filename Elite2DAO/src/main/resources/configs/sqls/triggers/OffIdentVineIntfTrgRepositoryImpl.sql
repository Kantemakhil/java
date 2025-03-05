OFF_IDENT_VINE_INTF_TRG_CUR_BOOK_OR {
 SELECT offender_book_id, offender_id, agy_loc_id, booking_no, booking_begin_date FROM offender_bookings WHERE offender_id = :v_off_id AND active_flag = 'Y' 
}

OFF_IDENT_VINE_INTF_TRG_CUR_OFF {
 SELECT offender_id_display, first_name, middle_name, last_name, birth_date, race_code, sex_code FROM offenders WHERE offender_id = :p_off_id
}

OFF_IDENT_VINE_INTF_TRG_UPDATE_OR_AUDIT {
update or_audit set action_type = :actionType, agy_loc_id = :agyLocId, offender_id_display = :offenderIdDisplay, booking_no = :bookingNo, booking_begin_date = :bookingBeginDate, first_name = :firstName, middle_name = :middleName, last_name = :lastName, birth_date = :birthDate, race_code = :raceCode, sex_code = :sexCode, release_date = :releaseDate, release_time = :releaseTime, rel_reason_code = :relReasonCode, modified_date = current_timestamp, offender_book_id =:offenderBookId, MODIFY_USER_ID =:modifyUserId, modify_datetime =current_timestamp where offender_id = :offenderId and booking_no = :bookingNo and action_type in ('A', 'U')
}

OFF_IDENT_VINE_INTF_TRG_INSERT_OR_AUDIT{
insert into or_audit (action_type, agy_loc_id, offender_id_display, booking_no, booking_begin_date, first_name, middle_name, last_name, birth_date, race_code, sex_code, release_date, release_time, rel_reason_code, offender_id, modified_date, offender_book_id, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME) values (:actionType, :agyLocId, :offenderIdDisplay, :bookingNo, :bookingBeginDate, :firstName, :middleName, :lastName, :birthDate, :raceCode, :sexCode, null, null, null, :offenderId, current_timestamp, :offenderBookId, current_timestamp , :createUserId , NULL) 
}