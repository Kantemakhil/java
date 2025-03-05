V_OFFENDER_VISITS_TI_L_COUNT{
SELECT COUNT (*)  l_count FROM OFFENDER_VISITS WHERE offender_visit_id = :offenderVisitId
}
V_OFFENDER_VISITS_TI_OFFENDER_VISITS_INSERT{
INSERT INTO OFFENDER_VISITS(offender_visit_id,offender_book_id, comment_text ,raised_incident_type, raised_incident_number,visit_date ,start_time, end_time, visit_type,visit_status, visit_internal_location_id,agency_visit_slot_id, agy_loc_id,create_datetime,modify_datetime,create_user_id)VALUES ( :offenderVisitId ,:visitOffenderBookId, :commentText,:raisedIncidentType, :raisedIncidentNumber,:visitDate,:startTime, :endTime, :visitType,:visitStatus, :visitInternalLocationId,:agencyVisitSlotId, :agyLocId,:createDatetime,:modifyDatetime,:createUserId)
}
V_OFFENDER_VISITS_TI_OFFENDER_VISIT_VISITORS_INSERT{
 INSERT INTO OFFENDER_VISIT_VISITORS(offender_visit_id, offender_visit_visitor_id,offender_book_id, event_id, event_status ,comment_text,create_datetime,modify_datetime,create_user_id) VALUES (:offenderVisitId, offender_visit_visitor_id.NEXTVAL,:visitOffenderBookId, event_id.NEXTVAL, 'SCH',:visitorCommentText,:createDatetime,:modifyDatetime,:createUserId)
}