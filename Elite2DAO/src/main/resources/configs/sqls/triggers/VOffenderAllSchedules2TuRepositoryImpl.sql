V_OFFENDER_ALL_SCHEDULES_2_TU_SCH_UPDATE{
UPDATE OFFENDER_IND_SCHEDULES SET  EVENT_DATE = :eventDate,
START_TIME = :startTime,END_TIME = :endTime,EVENT_SUB_TYPE = :eventSubType, EVENT_STATUS = :eventStatus,EVENT_OUTCOME = :eventOutcome,
CONFIRM_FLAG = :confirmFlag,COMMENT_TEXT = :commentText,HIDDEN_COMMENT_TEXT = :hiddenCommentText,APPLICATION_DATE = :applicationDate,
AGY_LOC_ID = :agyLocId, TO_AGY_LOC_ID = :toAgyLocId,TO_INTERNAL_LOCATION_ID = :toInternalLocationId,
OUTCOME_REASON_CODE = :outcomeReasonCode,IN_CHARGE_STAFF_ID = :inChargeStaffId,CREDITED_HOURS = :creditedHours,
PIECE_WORK = :pieceWork,ENGAGEMENT_CODE = :engagementCode,UNDERSTANDING_CODE = :understandingCode,DETAILS = :details,
AGREED_TRAVEL_HOUR = :agreedTravelHour,UNPAID_WORK_SUPERVISOR = :unpaidWorkSupervisor, UNPAID_WORK_BEHAVIOUR = :unpaidWorkBehaviour, UNPAID_WORK_ACTION = :unpaidWorkAction,
SICK_NOTE_RECEIVED_DATE = :sickNoteReceivedDate,SICK_NOTE_EXPIRY_DATE = :sickNoteExpiryDate,UNEXCUSED_ABSENCE_FLAG = :unexcusedAbsenceFlag,ESCORT_CODE = :escortCode,
DIRECTION_CODE = :directionCode,TO_CITY_CODE = :toCityCode,FROM_CITY_CODE = :fromCityCode,OFF_PRGREF_ID = :offPrgrefId,
IN_TIME = :inTime,  OUT_TIME = :outTime,PERFORMANCE_CODE = :performanceCode,TRANSPORT_CODE = :transportCode,APPLICATION_TIME = :applicationTime,
CONTACT_PERSON_NAME = :contactPersonName, TO_address_owner_class = :toAddressOwnerClass,TO_address_ID= :toAddressId,
RETURN_DATE= :returnDate,RETURN_TIME= :returnTime,  TO_CORPORATE_ID= :toCorporateId,PROV_STATE_CODE = :provStateCode,
Scheduled_Trip_id      = :scheduledTripId,
SMS_SCHEDULE_HOURS_BEFORE =:smsScheduleHoursBefore,
EMAIL_SCHEDULE_HOURS_BEFORE =:emailScheduleHoursBefore,
EMAIL_FLAG =:emailFlag,
SMS_FLAG =:smsFlag,
modify_datetime  = current_timestamp ,modify_user_id = :modifyUserId
WHERE EVENT_ID = :eventId
}

V_OFFENDER_ALL_SCHEDULES_2_TU_V_OFF_CRS_SCH_UPDATE{
UPDATE v_offender_course_events SET  EVENT_DATE= :eventDate,START_TIME 		= :startTime, END_TIME 		= :endTime,
EVENT_SUB_TYPE 		= :eventSubType, EVENT_STATUS 		= :eventStatus, EVENT_OUTCOME 		= :eventOutcome,
COMMENT_TEXT 		= :commentText, AGY_LOC_ID 	        = :agyLocId, TO_INTERNAL_LOCATION_ID 	= :toInternalLocationId,
UNEXCUSED_ABSENCE_FLAG   = :unexcusedAbsenceFlag, OUTCOME_REASON_CODE 	= :outcomeReasonCode,
CREDITED_HOURS 		= :creditedHours,PIECE_WORK 		= :pieceWork, ENGAGEMENT_CODE 		= :engagementCode,UNDERSTANDING_CODE 	= :understandingCode,
AGREED_TRAVEL_HOUR	= :agreedTravelHour, BEHAVIOUR_CODE      	= :behaviourCode,ACTION_CODE 		= :actionCode,
SUPERVISOR_STAFF_ID      = :inChargeStaffId,SICK_NOTE_RECEIVED_DATE = :sickNoteReceivedDate,SICK_NOTE_EXPIRY_DATE = :sickNoteExpiryDate,
OFF_PRGREF_ID = :offPrgrefId,IN_TIME = :inTime,OUT_TIME = :outTime,PERFORMANCE_CODE = :performanceCode, TO_address_ID          = :toAddressId
WHERE Offender_Book_ID = :offenderBookId  AND   Reference_ID     = :referenceId AND   Event_Date       = :eventDate
}


V_OFFENDER_ALL_SCHEDULES_2_TU_V_OFF_CRS_SCH_UPDATE_ELSE{
UPDATE v_offender_course_events SET  EVENT_DATE 		= :eventDate,  START_TIME 		= :startTime, END_TIME 		= :endTime,
EVENT_SUB_TYPE 		= :eventSubType, EVENT_STATUS 		= :eventStatus,  EVENT_OUTCOME 		= :eventOutcome,COMMENT_TEXT 		= :commentText,
AGY_LOC_ID 	        = :agyLocId, TO_INTERNAL_LOCATION_ID 	= :toInternalLocationId,  OUTCOME_REASON_CODE 	= :outcomeReasonCode,
CREDITED_HOURS 		= :creditedHours,UNEXCUSED_ABSENCE_FLAG   = :unexcusedAbsenceFlag,PIECE_WORK 		= :pieceWork,
ENGAGEMENT_CODE 		= :engagementCode, UNDERSTANDING_CODE 	= :understandingCode, AGREED_TRAVEL_HOUR 	= :agreedTravelHour,
BEHAVIOUR_CODE      	= :behaviourCode,ACTION_CODE 		= :actionCode,SICK_NOTE_RECEIVED_DATE = :sickNoteReceivedDate,
SICK_NOTE_EXPIRY_DATE = :sickNoteExpiryDate, OFF_PRGREF_ID = :offPrgrefId,IN_TIME = :inTime,OUT_TIME = :outTime,
PERFORMANCE_CODE = :performanceCode,TO_address_ID          = :toAddressId WHERE Event_ID = :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TU_OFF_VIS{
UPDATE OFFENDER_VISIT_VISITORS SET  EVENT_STATUS= :eventStatus, EVENT_OUTCOME= :eventOutcome, Comment_text= :visitorCommentText,OUTCOME_REASON_CODE = :outcomeReasonCode WHERE EVENT_ID= :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TU_OIC_HEARING{
 UPDATE OIC_HEARINGS SET  HEARING_DATE= :hearingDate, HEARING_TIME= :hearingTime, EVENT_STATUS= :eventStatus,  COMMENT_TEXT= :commentText,modify_datetime  = current_timestamp ,modify_user_id = :modifyUserId WHERE EVENT_ID= :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TU_OFF_REL{
UPDATE OFFENDER_RELEASE_DETAILS SET  Release_date= :releaseDate, MOvement_Reason_Code = :mOvementReasonCode, EVENT_STATUS= :eventStatus,COMMENT_TEXT= :commentText WHERE EVENT_ID= :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TU_COURT{
UPDATE COURT_EVENTS SET  EVENT_DATE= :eventDate,START_TIME= :startTime,Court_EVENT_TYPE    = :courtEventType,
EVENT_STATUS= :eventStatus, EVENT_OUTCOME= :eventOutcome, COMMENT_TEXT= :commentText, AGY_LOC_ID= :toAgyLocId,
scheduled_trip_id   = :scheduledTripId, OUTCOME_REASON_CODE = :outcomeReasonCode  WHERE EVENT_ID = :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_TU_COURT_ELSE{
insert into OFFENDER_IND_SCHEDULES(EVENT_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, END_TIME, EVENT_CLASS, EVENT_TYPE, EVENT_SUB_TYPE, EVENT_STATUS, EVENT_OUTCOME, COMMENT_TEXT, HIDDEN_COMMENT_TEXT, APPLICATION_DATE, AGY_LOC_ID, TO_AGY_LOC_ID, TO_INTERNAL_LOCATION_ID, OUTCOME_REASON_CODE, IN_CHARGE_STAFF_ID, CREDITED_HOURS, PIECE_WORK, ENGAGEMENT_CODE, UNDERSTANDING_CODE, DETAILS, AGREED_TRAVEL_HOUR, UNPAID_WORK_SUPERVISOR, UNPAID_WORK_BEHAVIOUR, UNPAID_WORK_ACTION, SICK_NOTE_RECEIVED_DATE, SICK_NOTE_EXPIRY_DATE, UNEXCUSED_ABSENCE_FLAG, ESCORT_CODE, DIRECTION_CODE, TO_CITY_CODE, FROM_CITY_CODE, OFF_PRGREF_ID, IN_TIME, OUT_TIME, PERFORMANCE_CODE, REFERENCE_ID, TRANSPORT_CODE, APPLICATION_TIME, CONTACT_PERSON_NAME, TO_address_owner_class, TO_address_ID, RETURN_DATE, RETURN_TIME, TO_CORPORATE_ID, TA_ID, PROV_STATE_CODE, scheduled_trip_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME, SMS_FLAG, EMAIL_FLAG, EMAIL_SCHEDULE_HOURS_BEFORE, SMS_SCHEDULE_HOURS_BEFORE)
values (NEXTVAL('EVENT_ID'), :offenderBookId, :eventDate, :startTime, :endTime, :eventClass, :eventType, :eventSubType, :eventStatus, :eventOutcome, :commentText, :hiddenCommentText, :applicationDate, :agyLocId, :toAgyLocId, :toInternalLocationId, :outcomeReasonCode, :inChargeStaffId, :creditedHours, :pieceWork, :engagementCode, :understandingCode, :details, :agreedTravelHour, :unpaidWorkSupervisor, :unpaidWorkBehaviour, :unpaidWorkAction, :sickNoteReceivedDate, :sickNoteExpiryDate, :unexcusedAbsenceFlag, :escortCode, :directionCode, :toCityCode, :fromCityCode, :offPrgrefId, :inTime, :outTime, :performanceCode, :referenceId, :transportCode, :applicationTime, :contactPersonName, :toAddressOwnerClass, :toAddressId, :returnDate, :returnTime, :toCorporateId, :taId, :provStateCode, :scheduledTripId, :createUserId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :smsFlag, :emailFlag, :emailScheduleHoursBefore, :smsScheduleHoursBefore)
}
V_OFFENDER_ALL_SCHEDULES_2_TU_COURT_COUNT{
SELECT COUNT(*) FROM   OFFENDER_IND_SCHEDULES WHERE  Offender_Book_id = :offenderBookId AND    Event_type= :eventType AND    Reference_id= :referenceId
}