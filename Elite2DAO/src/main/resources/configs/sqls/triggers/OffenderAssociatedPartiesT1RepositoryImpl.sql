OFFENDER_ASSOCIATED_PARTIES_T1_OFFENDER_ASSOCIATED_PARTIES_GET{
SELECT ASSOCIATED_PARTY_ID,OFFENDER_BOOK_ID,PARTY_TYPE,PARTY_ID,RELATIONSHIP_CODE,VICTIM_EMAIL_ADDR,AGE,ETHNICITY,CREATE_USER_ID,CREATE_DATETIME,CASE_ID,SEAL_FLAG,MODIFY_DATETIME,MODIFY_USER_ID FROM OFFENDER_ASSOCIATED_PARTIES WHERE ASSOCIATED_PARTY_ID=:associatedPartyId 
}
OFFENDER_ASSOCIATED_PARTIES_T1_L_CHECK_EXIST_CUR{
select 'Y',event_date from offender_assoc_p_event_notifs where notification_code = 'NEWVIC' and offender_book_id = :offenderBookId order by trg_event_id desc
}
OFFENDER_ASSOCIATED_PARTIES_T1_TRG_EVENT_ID{
select trg_event_id.nextval from dual
}
OFFENDER_ASSOCIATED_PARTIES_T1_OFFENDER_ASSOC_P_EVENT_NOTIFS{
insert into offender_assoc_p_event_notifs(trg_event_id,event_date, offender_book_id,notification_code,create_datetime,modify_datetime,create_user_id) values (:trgEventId,:eventDate,:offenderBookId,'NEWVIC',:createDatetime,:modifyDatetime,:createUserId)
}
