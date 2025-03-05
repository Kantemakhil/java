V_OFFENDER_ALL_SCHEDULES_2_TD_OFFENDER_IND_SCHEDULES_UPDATE{
UPDATE OFFENDER_IND_SCHEDULES SET    Event_Status = 'DEL',modify_datetime=:modifyDatetime,modify_user_id=:createUserId  WHERE  Event_ID = :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TD_OFFENDER_IND_SCHEDULES_DELETE{
 DELETE FROM OFFENDER_IND_SCHEDULES WHERE  Event_ID = :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TD_V_OFFENDER_COURSE_EVENTS_UPDATE{
UPDATE v_Offender_course_events SET    Event_Status = 'DEL',modify_datetime=:modifyDatetime,modify_user_id=:createUserId WHERE  Offender_Book_ID = :offenderBookId AND    Reference_ID     = :referenceId AND   Event_Date   = :eventDate 
}
V_OFFENDER_ALL_SCHEDULES_2_TD_V_OFFENDER_COURSE_EVENTS2{
UPDATE v_Offender_course_events  SET    Event_Status = 'DEL',modify_datetime=:modifyDatetime,modify_user_id=:createUserId WHERE  Event_ID = :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TD_{

}