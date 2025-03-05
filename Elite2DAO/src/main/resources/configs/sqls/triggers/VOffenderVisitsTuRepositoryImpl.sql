V_OFFENDER_VISITS_TU_V_OFFENDER_VISITS{
SELECT VISIT_INTERNAL_LOCATION_ID,AGENCY_VISIT_SLOT_ID,AGY_LOC_ID,OFFENDER_VISIT_VISITOR_ID,OFFENDER_BOOK_ID,OFFENDER_ID,OFFENDER_LAST_NAME,OFFENDER_FIRST_NAME,OFFENDER_ID_DISPLAY,VISIT_OWNER_FLAG,EVENT_STATUS,EVENT_OUTCOME,OUTCOME_REASON_CODE,VISITOR_COMMENT_TEXT,EVENT_ID,CHECK_SUM,OFFENDER_VISIT_ID,VISIT_OFFENDER_BOOK_ID,COMMENT_TEXT,RAISED_INCIDENT_TYPE,RAISED_INCIDENT_NUMBER,VISIT_DATE,START_TIME,END_TIME,VISIT_TYPE,VISIT_STATUS FROM V_OFFENDER_VISITS
}
V_OFFENDER_VISITS_TU_L_COUNT{
SELECT COUNT (*)  FROM OFFENDER_VISITS  WHERE offender_visit_id = :offenderVisitId
}
V_OFFENDER_VISITS_TU_OFFENDER_VISITS{
INSERT INTO OFFENDER_VISITS(offender_visit_id,offender_book_id, comment_text,raised_incident_type, raised_incident_number,visit_date,start_time, end_time, visit_type,visit_status, visit_internal_location_id,agency_visit_slot_id, agy_loc_id,create_datetime,modify_datetime,create_user_id) VALUES (:offenderVisitId,:visitOffenderBookId, :commentText,:raisedIncidentType, :raisedIncidentNumber, :visitDate,:startTime, :endTime, :visitType,:visitStatus, :visitInternalLocationId,:agencyVisitSlotId, :agyLocId,:createDatetime,:modifyDatetime,:createUserId)
}
V_OFFENDER_VISITS_TU_OFFENDER_VISIT_VISITORS{
INSERT INTO OFFENDER_VISIT_VISITORS(offender_visit_id,offender_visit_visitor_id,offender_book_id, event_id, event_status,event_outcome,comment_text,create_datetime,modify_datetime,create_user_id)
             VALUES (:offenderVisitId ,offender_visit_visitor_id.NEXTVAL ,:visitOffenderBookId, event_id.NEXTVAL, 'SCH',NVL(:eventOutcome,'ATT'),:visitorCommentText,:createDatetime,:modifyDatetime,:createUserId)
}
V_OFFENDER_VISITS_TU_OFFENDER_VISITS_UPDATE{
UPDATE OFFENDER_VISITS SET offender_book_id = :visitOffenderBookId ,comment_text = :commentText ,raised_incident_type = :raisedIncidentType ,raised_incident_number = :raisedIncidentNumber,visit_date = :visitDate,start_time = :startTime,end_time = :endTime,visit_status = :visitStatus,visit_internal_location_id = :visitInternalLocationId,agency_visit_slot_id = :agencyVisitSlotId,agy_loc_id = :agyLocId,visit_type = :visitType,modify_datetime=modifyDatetime,modify_user_id=:modifyUserId  WHERE offender_visit_id = :offenderVisitId
}
V_OFFENDER_VISITS_TU_OFFENDER_VISIT_VISITORS_UPDATE{
UPDATE OFFENDER_VISIT_VISITORS  SET comment_text = :visitorCommentText,event_status = NVL(:eventStatus,'SCH'),event_outcome = NVL(:eventOutcome,'ATT') ,outcome_reason_code = :outcomeReasonCode,modify_datetime=modifyDatetime,modify_user_id=:modifyUserId WHERE offender_visit_visitor_id = :offenderVisitVisitorId
}
