V_OFFENDER_COURSE_EVENTS_TD_INSERT{
 INSERT INTO Offender_course_attendances(EVENT_ID, offender_book_ID, event_date, off_prgref_id,Start_Time, End_Time, in_time, out_time, event_status, Reference_id,crs_acty_id, event_type, event_sub_type,crs_sch_id, crs_appt_id, agy_loc_id, event_class, comment_text, piece_work, performance_code, direction_code)
 VALUES (nextval('Event_ID'), :offenderBookId, :eventDate,:offPrgrefId, :startTime, :endTime, :inTime, :outTime,  'DEL', :referenceId, :crsActyId, :eventType, :eventSubType, :crsSchId, :crsApptId, :agyLocId, :eventClass, :commentText, :pieceWork, :performanceCode, :directioncode)
}
V_OFFENDER_COURSE_EVENTS_TD_UPDATE {
 UPDATE Offender_course_attendances
        SET    Event_Status = 'DEL'
        WHERE  event_id = :eventId
}