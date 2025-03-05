OFFENDER_RELEASE_DETAILS_T2_L_CHECK_EXIST_CUR{
select 'Y' from offender_tmp_rel_schedules  where offender_book_id = :offenderBookId and release_date is not null and release_date = TO_DATE(:releaseDate, 'DD/MM/YYYY') and session_id = (select max(otrs.session_id) from offender_tmp_rel_schedules otrs  where otrs.offender_book_id = :offenderBookId)
}
OFFENDER_RELEASE_DETAILS_T2_L_SEX_CHECK_EXIST_CUR{
select 'Y' from offence_indicators oi where indicator_code = 'SEX' and ( select offence_code from offences o where o.offence_id = oi.offence_id) in ( select distinct offence_code from offender_charges where offender_book_id = :offenderBookId)
}
OFFENDER_RELEASE_DETAILS_T2_L_TRG_EVENT_ID{
select nextval('trg_event_id') from dual
}
OFFENDER_RELEASE_DETAILS_T2_INSERT{
INSERT INTO OFFENDER_ASSOC_P_EVENT_NOTIFS(TRG_EVENT_ID,EVENT_DATE,OFFENDER_BOOK_ID,NOTIFICATION_CODE,CREATE_DATETIME,CREATE_USER_ID )VALUES (:trgEventId,:eventDate,:offenderBookId,:notificationCode,CURRENT_TIMESTAMP,:createUserId)
}
OFFENDER_RELEASE_DETAILS_T2_UPDATE{
INSERT INTO OFFENDER_ASSOC_P_EVENT_NOTIFS(TRG_EVENT_ID,EVENT_DATE,OFFENDER_BOOK_ID,NOTIFICATION_CODE,CREATE_DATETIME,CREATE_USER_ID )VALUES (:trgEventId,:eventDate,:offenderBookId,:notificationCode,CURRENT_TIMESTAMP,:createUserId)
}
OFFENDER_RELEASE_DETAILS_T2_SELECT_FOR_OLD_RECORDS{
SELECT OFFENDER_BOOK_ID,RELEASE_DATE,COMMENT_TEXT,MOVEMENT_TYPE,MOVEMENT_REASON_CODE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,EVENT_ID,EVENT_STATUS,VERIFIED_FLAG,SEAL_FLAG FROM offender_release_details WHERE OFFENDER_BOOK_ID=:offenderBookId
}