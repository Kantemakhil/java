OFF_EXT_MV_VINE_INTF_TRG_OFFENDER_EXTERNAL_MOVEMENTS{
SELECT OFFENDER_BOOK_ID,MOVEMENT_SEQ,MOVEMENT_DATE,MOVEMENT_TIME,INTERNAL_SCHEDULE_TYPE,INTERNAL_SCHEDULE_REASON_CODE,MOVEMENT_TYPE,MOVEMENT_REASON_CODE,DIRECTION_CODE,ARREST_AGENCY_LOC_ID,TO_PROV_STAT_CODE,ESCORT_CODE,FROM_AGY_LOC_ID,TO_AGY_LOC_ID,ACTIVE_FLAG,ESCORT_TEXT,COMMENT_TEXT,REPORTING_DATE,TO_CITY,FROM_CITY,REPORTING_TIME,EVENT_ID,PARENT_EVENT_ID,TO_COUNTRY_CODE,OJ_LOCATION_CODE,APPLICATION_DATE,APPLICATION_TIME,TO_ADDRESS_ID,FROM_ADDRESS_ID,SEAL_FLAG,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID FROM OFFENDER_EXTERNAL_MOVEMENTS WHERE OFFENDER_BOOK_ID =:offenderBookId AND MOVEMENT_SEQ =:movementSeq
}
OFF_EXT_MV_VINE_INTF_TRG_CUR_BOOK_OR{
SELECT OFFENDER_BOOK_ID,OFFENDER_ID,AGY_LOC_ID,BOOKING_NO,BOOKING_BEGIN_DATE FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :offenderBookId
}
OFF_EXT_MV_VINE_INTF_TRG_CUR_OFF{
SELECT OFFENDER_ID_DISPLAY, FIRST_NAME, MIDDLE_NAME, LAST_NAME,BIRTH_DATE,  RACE_CODE,  SEX_CODE FROM OFFENDERS WHERE OFFENDER_ID = :pOffId
}
OFF_EXT_MV_VINE_INTF_TRG_UPDATE{
UPDATE or_audit  SET action_type= 'U',agy_loc_id = :agyLocId,offender_id_display = :offenderIdDisplay,booking_no = :bookingNo,
booking_begin_date  = :bookingBeginDate,first_name = :firstName,middle_name= :middleName,last_name  = :lastName,
birth_date = :birthDate,race_code  = :raceCode,sex_code   = :sexCode,release_date= :releaseDate,release_time= :releaseTime,rel_reason_code = :relReasonCode,
 offender_book_id= :offenderBookId,modify_datetime=current_timestamp,modify_user_id=:modifyUserId WHERE offender_id = :offenderId   AND booking_no = :bookingNo   AND action_type IN ('A', 'U')
}
OFF_EXT_MV_VINE_INTF_TRG_INSERT{
INSERT INTO or_audit(action_type, agy_loc_id, offender_id_display, booking_no, booking_begin_date, first_name, middle_name, last_name,
 birth_date, race_code, sex_code, release_date, release_time, rel_reason_code, offender_id, modified_date, offender_book_id,
 create_datetime,modify_datetime,create_user_id)
 VALUES ('U', :agyLocId, :offenderIdDisplay, :bookingNo, :bookingBeginDate, :firstName, :middleName, :lastName, :birthDate, :raceCode,
 :sexCode, :releaseDate, :releaseTime, :relReasonCode, :offenderId, current_timestamp, :offenderBookId, current_timestamp,null,:createUserId)
}