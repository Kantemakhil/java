OFFENDER_ASSOC_PARTY_NOTES_T1_OFFENDER_ASSOC_P_EVENT_NOTIFS{
insert into offender_assoc_p_event_notifs(trg_event_id,event_date, offender_book_id, notification_code,create_datetime,modify_datetime,create_user_id) values (:trgEventId, :eventDate, :offenderBookId,:notificationCode,:createDatetime,:createDatetime,:createUserId)
}
OFFENDER_ASSOC_PARTY_NOTES_T1_TRG_EVENT_ID {
	SELECT  TRG_EVENT_ID.NEXTVAL FROM    DUAL
}

OFFENDER_ASSOC_PARTY_NOTES_T1_OFFENDER_BOOK_ID{
select offender_book_id from offender_associated_parties where associated_party_id = :associatedPartyId
}
