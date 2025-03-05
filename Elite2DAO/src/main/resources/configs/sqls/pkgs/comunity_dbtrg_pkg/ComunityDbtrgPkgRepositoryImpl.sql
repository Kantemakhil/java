COMUNITY_DBTRG_PKG_VS_EMAILCUR{
SELECT ia.internet_address,sm.user_id FROM   staff_members sm, internet_addresses ia WHERE  sm.staff_id = ia.owner_id(+) AND    ia.owner_class(+) = 'STF' AND   EXISTS( SELECT 'X' FROM case_plans cp WHERE cp.sac_staff_id     = sm.staff_id  AND cp.offender_book_id = :offenderBookId  AND cp.case_plan_status = 'ACTIVE' AND cp.end_date IS NULL)
}
COMUNITY_DBTRG_PKG_VS_CASE_NOTE_CUR{
SELECT coalesce( active_flag, NULL) FROM automatic_case_notes WHERE expiry_date IS NULL  AND active_flag   = 'Y' AND cas_not_type  = :casNotType AND reason= :reason  AND trigger_event = :triggerEvent
}
COMUNITY_DBTRG_PKG_VS_NSEQ_CUR{
SELECT coalesce( MAX( note_seq ), 0) + 1 FROM case_notes WHERE offender_book_id = :offenderBookId
}
COMUNITY_DBTRG_PKG_INSERT_CASE_NOTES_INSERT{
--INSERT INTO CASE_NOTES(offender_book_id,note_seq,staff_id,note_date,note_time,cas_not_type,reason,text,contact_date,schedule_id,create_datetime,modify_datetime,create_user_id)VALUES(:offenderBookId,:noteSeq,:staffId,:noteDate,:noteDate,:casNotType,:reason,:text,:contactDate,:scheduleId,createDatetime,modifyDatetime,createUserId)
insert into CASE_NOTES(offender_book_id, note_seq, staff_id, note_date, note_time, cas_not_type, reason, text, contact_date, schedule_id, create_datetime, modify_datetime, create_user_id) values(:offenderBookId, :noteSeq, :staffId, :noteDate, :noteDate, :casNotType, :reason, :text, :contactDate, :scheduleId, current_timestamp, current_timestamp, :createUserId)

}
COMUNITY_DBTRG_PKG_VS_STAFF_CUR{
SELECT staff_id FROM staff_members WHERE user_id = :userId
}
COMUNITY_DBTRG_PKG_VS_OFF_INFO_CUR{
SELECT offender_id_display,last_name,first_name FROM V_HEADER_BLOCK_FN(:USERID) WHERE offender_book_id = :offenderBookId
}
COMUNITY_DBTRG_PKG_CUR_EMAIL_PBOARD{
SELECT internet_address FROM staff_members sm, internet_addresses ia WHERE sm.staff_id = ia.owner_id(+) AND   ia.owner_class(+) = 'STF' AND    staff_id = (SELECT sac_staff_id FROM staff_location_roles WHERE cal_agy_loc_id = (SELECT agy_loc_id FROM agency_locations WHERE agency_location_type = 'PBOARD'))
}
COMUNITY_DBTRG_PKG_VS_CASE_NOTES_CUR{
SELECT coalesce(active_flag, NULL) FROM automatic_case_notes WHERE expiry_date IS NULL AND active_flag  = 'Y'  AND cas_not_type = :caseType AND reason = :reason
}
COMUNITY_DBTRG_PKG_STAFF_CUR{
SELECT last_name || ', ' || first_name FROM staff_members WHERE staff_id = :staffId
}
COMUNITY_DBTRG_PKG_GET_MISC_VALUES_AGY_LOC_CUR{
SELECT description FROM agency_locations WHERE agy_loc_id = :pAgyLocId
}
COMUNITY_DBTRG_PKG_GET_MISC_VALUES_EVENT_DSP_CUR{
SELECT description FROM reference_codes WHERE domain = 'EVENTS' AND code   = :pEventType
}
COMUNITY_DBTRG_PKG_GET_MISC_VALUES_EVT_SUB_DSP_CUR{
 SELECT description FROM reference_codes WHERE domain = 'EVENTS'  AND code   = :pEventSubType AND parent_code  = :pEventType
}

