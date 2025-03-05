OFFENDER_TMP_REL_SCHEDULES_T1_OFFENDER_TMP_REL_SCHEDULES{
SELECT OFFENDER_BOOK_ID,SESSION_ID,OFFENDER_ID_DISPLAY,LAST_NAME,FIRST_NAME,COLUMN_FOUR_DATE,COLUMN_FIVE_DATE,COLUMN_SIX_DATE,RELEASE_DATE,COMMENT_TEXT,MOVEMENT_TYPE,MOVEMENT_REASON_CODE,COLUMN_SEVEN_DATE,COLUMN_EIGHT_DATE,COLUMN_NINE_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG FROM OFFENDER_TMP_REL_SCHEDULES WHERE OFFENDER_BOOK_ID=:offenderBookId AND SESSION_ID=:sessionId
}
OFFENDER_TMP_REL_SCHEDULES_T1_L_CHECK_EXIST_CUR{
select 'Y' from offender_release_details where offender_book_id = :offenderBookId and release_date is not null and release_date = :releaseDate
}
OFFENDER_TMP_REL_SCHEDULES_T1_OFFENCE_INDICATORS{
select 'Y' from offence_indicators oi where indicator_code = 'SEX' and ( select offence_code from offences o where o.offence_id = oi.offence_id) in (select distinct offence_code   from offender_charges  where offender_book_id =:offenderBookId)
}
OFFENDER_TMP_REL_SCHEDULES_T1_TRG_EVENT_ID_NEXTVAL{
select trg_event_id.nextval from dual
}
OFFENDER_TMP_REL_SCHEDULES_T1_INSERT{
insert into offender_assoc_p_event_notifs(trg_event_id,  event_date,offender_book_id, notification_code,create_datetime,modify_datetime,create_user_id) values (:trgEventId,:releaseDateNew,:offenderBookId,'DISCH',:createDatetime,:modifyDatetime,:createUserId)
}
OFFENDER_TMP_REL_SCHEDULES_T1_UPDATE{
insert into offender_assoc_p_event_notifs(trg_event_id,  event_date,offender_book_id, notification_code,modify_datetime,modify_user_id) values (:trgEventId,:releaseDateNew,:offenderBookId,'DISCH',:modifyDatetime,:modifyUserId)
}
