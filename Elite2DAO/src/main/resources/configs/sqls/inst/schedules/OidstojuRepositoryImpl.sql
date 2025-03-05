
OIDSTOJU_FIND_RGLOCATION {
 	SELECT CODE ,DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'PROV_STATE' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR :MODE = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION
}

OIDSTOJU_FIND_RGESCORT {
 	SELECT CODE ,DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'ESCORT' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR :MODE = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION
}

OIDSTOJU_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES {
 	SELECT EVENT_ID ,OFFENDER_BOOK_ID ,IN_OUT_STATUS ,BOOKING_NO ,BOOKING_ACTIVE_FLAG ,OFFENDER_ID ,OFFENDER_ID_DISPLAY ,OFFENDER_LAST_NAME ,OFFENDER_FIRST_NAME ,EVENT_DATE ,START_TIME ,END_TIME ,EVENT_CLASS ,EVENT_TYPE ,EVENT_TYPE_DESC ,EVENT_SUB_TYPE ,EVENT_SUB_TYPE_DESC ,ACTIVE_FLAG ,EVENT_STATUS ,EVENT_STATUS_DESC ,EVENT_OUTCOME ,EVENT_OUTCOME_DESC ,BUSY_DATE_FLAG ,OUTCOME_REASON_CODE ,REFERENCE_ID ,APPLICATION_DATE ,APPLICATION_TIME ,RETURN_DATE ,RETURN_TIME ,COMMENT_TEXT ,DETAILS ,AGY_LOC_ID ,AGY_LOC_DESC ,LIVING_UNIT_ID ,LIVING_UNIT_DESC ,LU_LEVEL_1_CODE ,LU_LEVEL_2_CODE ,LU_LEVEL_3_CODE ,LU_LEVEL_4_CODE ,AGENCY_IML_ID ,AGENCY_IML_DESC ,AGENCY_IML_LEVEL_1_CODE ,AGENCY_IML_LEVEL_2_CODE ,AGENCY_IML_LEVEL_3_CODE ,TO_AGY_LOC_ID ,TO_AGY_LOC_DESC ,TO_LOC ,TO_LOC_DESC ,ESCORT_CODE ,ESCORT_DESC ,DIRECTION_CODE ,SCHEDULE_MOVEMENT_TIME ,FROM_CITY_CODE ,FROM_CITY_NAME ,TO_CITY_CODE ,TO_CITY_NAME ,TO_INTERNAL_LOCATION_ID ,TO_INTERNAL_LOCATION_DESC ,TO_INT_LOC_LEVEL_1_CODE ,TO_INT_LOC_LEVEL_2_CODE ,TO_INT_LOC_LEVEL_3_CODE ,TO_INT_LOC_USER_DESC ,CREDITED_HOURS ,ENGAGEMENT_CODE ,UNDERSTANDING_CODE ,PIECE_WORK ,IN_TIME ,OUT_TIME ,PERFORMANCE_CODE ,TRANSPORT_CODE ,SICK_NOTE_EXPIRY_DATE ,SICK_NOTE_RECEIVED_DATE ,UNEXCUSED_ABSENCE_FLAG ,UNPAID_WORK_ACTION ,UNPAID_WORK_BEHAVIOUR ,AGREED_TRAVEL_HOUR ,CHECK_BOX_1 ,CHECK_BOX_2 ,HIDDEN_COMMENT_TEXT ,IN_CHARGE_STAFF_ID ,IN_CHARGE_STAFF_NAME ,OFF_PRGREF_ID ,CONTACT_PERSON_NAME ,TO_ADDRESS_OWNER_CLASS ,TO_ADDRESS_ID ,UNPAID_WORK_SUPERVISOR ,TA_ID ,RECORD_SOURCE ,CHECK_SUM ,PROV_STATE_CODE ,PROV_STATE_DESC ,SCHEDULED_TRIP_ID   FROM V_OFFENDER_ALL_SCHEDULES 
}
OIDSTOJU_OFFSCH_INSERT_V_OFFENDER_ALL_SCHEDULES {
	INSERT INTO V_OFFENDER_ALL_SCHEDULES(EVENT_ID ,OFFENDER_BOOK_ID ,IN_OUT_STATUS ,BOOKING_NO ,BOOKING_ACTIVE_FLAG ,OFFENDER_ID ,OFFENDER_ID_DISPLAY ,OFFENDER_LAST_NAME ,OFFENDER_FIRST_NAME ,EVENT_DATE ,START_TIME ,END_TIME ,EVENT_CLASS ,EVENT_TYPE ,EVENT_TYPE_DESC ,EVENT_SUB_TYPE ,EVENT_SUB_TYPE_DESC ,ACTIVE_FLAG ,EVENT_STATUS ,EVENT_STATUS_DESC ,EVENT_OUTCOME ,EVENT_OUTCOME_DESC ,BUSY_DATE_FLAG ,OUTCOME_REASON_CODE ,REFERENCE_ID ,APPLICATION_DATE ,APPLICATION_TIME ,RETURN_DATE ,RETURN_TIME ,COMMENT_TEXT ,DETAILS ,AGY_LOC_ID ,AGY_LOC_DESC ,LIVING_UNIT_ID ,LIVING_UNIT_DESC ,LU_LEVEL_1_CODE ,LU_LEVEL_2_CODE ,LU_LEVEL_3_CODE ,LU_LEVEL_4_CODE ,AGENCY_IML_ID ,AGENCY_IML_DESC ,AGENCY_IML_LEVEL_1_CODE ,AGENCY_IML_LEVEL_2_CODE ,AGENCY_IML_LEVEL_3_CODE ,TO_AGY_LOC_ID ,TO_AGY_LOC_DESC ,TO_LOC ,TO_LOC_DESC ,ESCORT_CODE ,ESCORT_DESC ,DIRECTION_CODE ,SCHEDULE_MOVEMENT_TIME ,FROM_CITY_CODE ,FROM_CITY_NAME ,TO_CITY_CODE ,TO_CITY_NAME ,TO_INTERNAL_LOCATION_ID ,TO_INTERNAL_LOCATION_DESC ,TO_INT_LOC_LEVEL_1_CODE ,TO_INT_LOC_LEVEL_2_CODE ,TO_INT_LOC_LEVEL_3_CODE ,TO_INT_LOC_USER_DESC ,CREDITED_HOURS ,ENGAGEMENT_CODE ,UNDERSTANDING_CODE ,PIECE_WORK ,IN_TIME ,OUT_TIME ,PERFORMANCE_CODE ,TRANSPORT_CODE ,SICK_NOTE_EXPIRY_DATE ,SICK_NOTE_RECEIVED_DATE ,UNEXCUSED_ABSENCE_FLAG ,UNPAID_WORK_ACTION ,UNPAID_WORK_BEHAVIOUR ,AGREED_TRAVEL_HOUR ,CHECK_BOX_1 ,CHECK_BOX_2 ,HIDDEN_COMMENT_TEXT ,IN_CHARGE_STAFF_ID ,IN_CHARGE_STAFF_NAME ,OFF_PRGREF_ID ,CONTACT_PERSON_NAME ,TO_ADDRESS_OWNER_CLASS ,TO_ADDRESS_ID ,UNPAID_WORK_SUPERVISOR ,TA_ID ,RECORD_SOURCE ,CHECK_SUM ,PROV_STATE_CODE ,PROV_STATE_DESC ,SCHEDULED_TRIP_ID ) VALUES(:eventId ,:offenderBookId ,:inOutStatus ,:bookingNo ,:bookingActiveFlag ,:offenderId ,:offenderIdDisplay ,:offenderLastName ,:offenderFirstName ,:eventDate ,:startTime ,:endTime ,:eventClass ,:eventType ,:eventTypeDesc ,:eventSubType ,:eventSubTypeDesc ,:activeFlag ,:eventStatus ,:eventStatusDesc ,:eventOutcome ,:eventOutcomeDesc ,:busyDateFlag ,:outcomeReasonCode ,:referenceId ,:applicationDate ,:applicationTime ,:returnDate ,:returnTime ,:commentText ,:details ,:agyLocId ,:agyLocDesc ,:livingUnitId ,:livingUnitDesc ,:luLevel1Code ,:luLevel2Code ,:luLevel3Code ,:luLevel4Code ,:agencyImlId ,:agencyImlDesc ,:agencyImlLevel1Code ,:agencyImlLevel2Code ,:agencyImlLevel3Code ,:toAgyLocId ,:toAgyLocDesc ,:toLoc ,:toLocDesc ,:escortCode ,:escortDesc ,:directionCode ,:scheduleMovementTime ,:fromCityCode ,:fromCityName ,:toCityCode ,:toCityName ,:toInternalLocationId ,:toInternalLocationDesc ,:toIntLocLevel1Code ,:toIntLocLevel2Code ,:toIntLocLevel3Code ,:toIntLocUserDesc ,:creditedHours ,:engagementCode ,:understandingCode ,:pieceWork ,:inTime ,:outTime ,:performanceCode ,:transportCode ,:sickNoteExpiryDate ,:sickNoteReceivedDate ,:unexcusedAbsenceFlag ,:unpaidWorkAction ,:unpaidWorkBehaviour ,:agreedTravelHour ,:checkBox1 ,:checkBox2 ,:hiddenCommentText ,:inChargeStaffId ,:inChargeStaffName ,:offPrgrefId ,:contactPersonName ,:toAddressOwnerClass ,:toAddressId ,:unpaidWorkSupervisor ,:taId ,:recordSource ,:checkSum ,:provStateCode ,:provStateDesc ,:scheduledTripId )
}

OIDSTOJU_OFFSCH_UPDATE_V_OFFENDER_ALL_SCHEDULES {
	UPDATE V_OFFENDER_ALL_SCHEDULES set EVENT_ID  = :eventId ,OFFENDER_BOOK_ID  = :offenderBookId ,IN_OUT_STATUS  = :inOutStatus ,BOOKING_NO  = :bookingNo ,BOOKING_ACTIVE_FLAG  = :bookingActiveFlag ,OFFENDER_ID  = :offenderId ,OFFENDER_ID_DISPLAY  = :offenderIdDisplay ,OFFENDER_LAST_NAME  = :offenderLastName ,OFFENDER_FIRST_NAME  = :offenderFirstName ,EVENT_DATE  = :eventDate ,START_TIME  = :startTime ,END_TIME  = :endTime ,EVENT_CLASS  = :eventClass ,EVENT_TYPE  = :eventType ,EVENT_TYPE_DESC  = :eventTypeDesc ,EVENT_SUB_TYPE  = :eventSubType ,EVENT_SUB_TYPE_DESC  = :eventSubTypeDesc ,ACTIVE_FLAG  = :activeFlag ,EVENT_STATUS  = :eventStatus ,EVENT_STATUS_DESC  = :eventStatusDesc ,EVENT_OUTCOME  = :eventOutcome ,EVENT_OUTCOME_DESC  = :eventOutcomeDesc ,BUSY_DATE_FLAG  = :busyDateFlag ,OUTCOME_REASON_CODE  = :outcomeReasonCode ,REFERENCE_ID  = :referenceId ,APPLICATION_DATE  = :applicationDate ,APPLICATION_TIME  = :applicationTime ,RETURN_DATE  = :returnDate ,RETURN_TIME  = :returnTime ,COMMENT_TEXT  = :commentText ,DETAILS  = :details ,AGY_LOC_ID  = :agyLocId ,AGY_LOC_DESC  = :agyLocDesc ,LIVING_UNIT_ID  = :livingUnitId ,LIVING_UNIT_DESC  = :livingUnitDesc ,LU_LEVEL_1_CODE  = :luLevel1Code ,LU_LEVEL_2_CODE  = :luLevel2Code ,LU_LEVEL_3_CODE  = :luLevel3Code ,LU_LEVEL_4_CODE  = :luLevel4Code ,AGENCY_IML_ID  = :agencyImlId ,AGENCY_IML_DESC  = :agencyImlDesc ,AGENCY_IML_LEVEL_1_CODE  = :agencyImlLevel1Code ,AGENCY_IML_LEVEL_2_CODE  = :agencyImlLevel2Code ,AGENCY_IML_LEVEL_3_CODE  = :agencyImlLevel3Code ,TO_AGY_LOC_ID  = :toAgyLocId ,TO_AGY_LOC_DESC  = :toAgyLocDesc ,TO_LOC  = :toLoc ,TO_LOC_DESC  = :toLocDesc ,ESCORT_CODE  = :escortCode ,ESCORT_DESC  = :escortDesc ,DIRECTION_CODE  = :directionCode ,SCHEDULE_MOVEMENT_TIME  = :scheduleMovementTime ,FROM_CITY_CODE  = :fromCityCode ,FROM_CITY_NAME  = :fromCityName ,TO_CITY_CODE  = :toCityCode ,TO_CITY_NAME  = :toCityName ,TO_INTERNAL_LOCATION_ID  = :toInternalLocationId ,TO_INTERNAL_LOCATION_DESC  = :toInternalLocationDesc ,TO_INT_LOC_LEVEL_1_CODE  = :toIntLocLevel1Code ,TO_INT_LOC_LEVEL_2_CODE  = :toIntLocLevel2Code ,TO_INT_LOC_LEVEL_3_CODE  = :toIntLocLevel3Code ,TO_INT_LOC_USER_DESC  = :toIntLocUserDesc ,CREDITED_HOURS  = :creditedHours ,ENGAGEMENT_CODE  = :engagementCode ,UNDERSTANDING_CODE  = :understandingCode ,PIECE_WORK  = :pieceWork ,IN_TIME  = :inTime ,OUT_TIME  = :outTime ,PERFORMANCE_CODE  = :performanceCode ,TRANSPORT_CODE  = :transportCode ,SICK_NOTE_EXPIRY_DATE  = :sickNoteExpiryDate ,SICK_NOTE_RECEIVED_DATE  = :sickNoteReceivedDate ,UNEXCUSED_ABSENCE_FLAG  = :unexcusedAbsenceFlag ,UNPAID_WORK_ACTION  = :unpaidWorkAction ,UNPAID_WORK_BEHAVIOUR  = :unpaidWorkBehaviour ,AGREED_TRAVEL_HOUR  = :agreedTravelHour ,CHECK_BOX_1  = :checkBox1 ,CHECK_BOX_2  = :checkBox2 ,HIDDEN_COMMENT_TEXT  = :hiddenCommentText ,IN_CHARGE_STAFF_ID  = :inChargeStaffId ,IN_CHARGE_STAFF_NAME  = :inChargeStaffName ,OFF_PRGREF_ID  = :offPrgrefId ,CONTACT_PERSON_NAME  = :contactPersonName ,TO_ADDRESS_OWNER_CLASS  = :toAddressOwnerClass ,TO_ADDRESS_ID  = :toAddressId ,UNPAID_WORK_SUPERVISOR  = :unpaidWorkSupervisor ,TA_ID  = :taId ,RECORD_SOURCE  = :recordSource ,CHECK_SUM  = :checkSum ,PROV_STATE_CODE  = :provStateCode ,PROV_STATE_DESC  = :provStateDesc ,SCHEDULED_TRIP_ID  = :scheduledTripId
}

OIDSTOJU_OFFSCH_DELETE_V_OFFENDER_ALL_SCHEDULES { 
	DELETE FROM V_OFFENDER_ALL_SCHEDULES
}


OIDSTOJU_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_IND_SCHEDULES O WHERE O.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDSTOJU_GET_CURRENT_DATE {
	SELECT CURRENT_TIMESTAMP FROM DUAL
}

OIDSTOJU_SCH_INSERT_OFFENDER_IND_SCHEDULES {
INSERT INTO OFFENDER_IND_SCHEDULES(EVENT_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, END_TIME, EVENT_CLASS, EVENT_TYPE, EVENT_SUB_TYPE, EVENT_STATUS, COMMENT_TEXT, HIDDEN_COMMENT_TEXT, APPLICATION_DATE, PARENT_EVENT_ID, AGY_LOC_ID, TO_AGY_LOC_ID, FROM_CITY, TO_CITY, CRS_SCH_ID, ORDER_ID, SENTENCE_SEQ, OUTCOME_REASON_CODE, JUDGE_NAME, CHECK_BOX_1, CHECK_BOX_2, CREDITED_HOURS, REPORT_IN_DATE, PIECE_WORK, ENGAGEMENT_CODE, UNDERSTANDING_CODE, DETAILS, CREDITED_WORK_HOUR, AGREED_TRAVEL_HOUR, UNPAID_WORK_SUPERVISOR, UNPAID_WORK_BEHAVIOUR, UNPAID_WORK_ACTION, SICK_NOTE_RECEIVED_DATE, SICK_NOTE_EXPIRY_DATE, COURT_EVENT_RESULT, UNEXCUSED_ABSENCE_FLAG, CREATE_USER_ID,CREATE_DATETIME, ESCORT_CODE, CONFIRM_FLAG, DIRECTION_CODE, TO_CITY_CODE, FROM_CITY_CODE, OFF_PRGREF_ID, IN_TIME, OUT_TIME, PERFORMANCE_CODE, TEMP_ABS_SCH_ID, TRANSPORT_CODE, APPLICATION_TIME, CONTACT_PERSON_NAME, TO_ADDRESS_OWNER_CLASS,  RETURN_DATE, RETURN_TIME, TO_CORPORATE_ID, TA_ID, EVENT_OUTCOME, PROV_STATE_CODE, SEAL_FLAG, MODIFY_USER_ID, MODIFY_DATETIME)
VALUES (:eventId,:offenderBookId,:eventDate,:startTime,:endTime,:eventClass,:eventType,:eventSubType,:eventStatus,:commentText,:hiddenCommentText,:applicationDate,:parentEventId,:agyLocId,:toAgyLocId,:fromCity,:toCity,:crsSchId,:orderId,:sentenceSeq,:outcomeReasonCode,:judgeName,:checkBox1,:checkBox2,:creditedHours,:reportInDate,:pieceWork,:engagementCode,:understandingCode,:details,:creditedWorkHour,:agreedTravelHour,:unpaidWorkSupervisor,:unpaidWorkBehaviour,:unpaidWorkAction,:sickNoteReceivedDate,:sickNoteExpiryDate,:courtEventResult,:unexcusedAbsenceFlag,:createUserId,current_timestamp,:escortCode,:confirmFlag,:directionCode,:toCityCode,:fromCityCode,:offPrgrefId,:inTime,:outTime,:performanceCode,:tempAbsSchId,:transportCode,:applicationTime,:contactPersonName,:toAddressOwnerClass,:returnDate,:returnTime,:toCorporateId,:taId,:eventOutcome,:provStateCode,:sealFlag, null, null)
}

OIDSTOJU_SCH_UPDATE_V_OFFENDER_ALL_SCHEDULES_2 {
UPDATE V_OFFENDER_ALL_SCHEDULES_2 SET EVENT_DATE = :eventDate ,START_TIME = :startTime, AGY_LOC_ID= :agyLocId, ESCORT_CODE = :escortCode , COMMENT_TEXT = :commentText ,   PROV_STATE_CODE = :provStateCode WHERE EVENT_ID  = :eventId
}

OIDSTOJU_SCH_DELETE_V_OFFENDER_ALL_SCHEDULES_2 {   
DELETE FROM V_OFFENDER_ALL_SCHEDULES_2 WHERE EVENT_ID =:eventId
}

OIDSTOJU_OFF_SCH_PREINSERT {
	SELECT NEXTVAL('EVENT_ID') FROM DUAL
}
OIDSTOJU_CHECK_SCHEDULE_CONFLICT {
select
	count(*)
from
	V_OFFENDER_ALL_SCHEDULES_2
where
	OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
	and EVENT_STATUS = 'SCH'
	and :EVENT_DATE::timestamp >= EVENT_DATE 
	and :EVENT_DATE::timestamp <= COALESCE(RETURN_DATE, EVENT_DATE)
	 AND EVENT_ID <> :EVENT_ID
}
OIDSTOJU_EVENTTYPE_EVENTSUBTYPE_CODES {
select CODE,DESCRIPTION from REFERENCE_CODES where  domain in ( 'MOVE_TYPE' , 'MOVE_RSN' ) and CODE in ( 'TRN' , 'OJ')
}
 
OIDSTOJU_UPD_NOTIFICATION_1 {
 SELECT  'Y'
            FROM  OFFENDER_IND_SCHEDULES
           WHERE  event_id = :eventId
             AND (event_date <> :vMvtDate
              OR  event_sub_type <> :eventSubType)
}           
OIDSTOJU_UPD_NOTIFICATION_2 {              
         SELECT noti_seq,
                noti_move_seq
           FROM OFFENDER_PEND_NOTIFICATIONS opn
          WHERE opn.schedule_id = v_sch_id
}
OIDSTOJU_UPD_NOTIFICATION_3 {
          SELECT COUNT(*)
            FROM OFFENDER_NOT_COMPLETIONS
           WHERE noti_seq = :vNotSeq
             AND noti_move_seq = :vMNotSeq
             AND offender_book_id = :offenderBookId
             AND STATUS = 'COMPLETED'
}
OIDSTOJU_UPD_NOTIFICATION_4 {
          SELECT  notification_flag, notification_type
            FROM  MOVEMENT_REASONS
           WHERE  movement_type = 'TRN'
             AND  movement_reason_code = :eventSubType
}             
OIDSTOJU_UPD_NOTIFICATION_5 {
          SELECT COUNT(*)
            FROM OFFENDER_PEND_NOTIFICATIONS
           WHERE offender_book_id = :offenderBookId AND
                 movement_type    = 'TRN'    AND
                 movement_reason_code = :eventSubType
           AND  noti_seq <> :vNotSeq
           AND  noti_move_seq <> :vMNotSeq
}
OIDSTOJU_UPD_NOTIFICATION_6 {
          SELECT  COUNT(*)
            FROM  OFFENDER_RELEASE_NOTIS orn
           WHERE  orn.offender_book_id = :offenderBookId
             AND (orn.expiration_date IS NULL
              OR  orn.expiration_date >= SYSDATE)
             AND  NOT EXISTS (SELECT NULL
                                FROM NOTIFY_MOVEMENT_TYPES nmt
                               WHERE nmt.movement_type = 'TRN'
                                 AND nmt.movement_reason_code = :eventSubType
                                 AND nmt.offender_book_id = :offenderBookId
                                 AND nmt.noti_seq = orn.noti_seq)
}
OIDSTOJU_UPD_NOTIFICATION_7 {
  DELETE OFFENDER_NOT_COMPLETIONS
                WHERE status <> 'COMPLETED'
                AND noti_seq = :vNotSeq
                AND noti_move_seq = :vMNotSeq
                AND offender_book_id =:offenderBookId
}
OIDSTOJU_UPD_NOTIFICATION_8 {
UPDATE OFFENDER_NOT_COMPLETIONS
                     SET status = 'CANCEL'
                     AND noti_seq = :vNotSeq
                     AND noti_move_seq = :vMNotSeq
                     AND status = 'COMPLETED'
}
OIDSTOJU_DEL_NOTIFICATION_1 {
SELECT noti_seq,noti_move_seq
                 FROM OFFENDER_PEND_NOTIFICATIONS opn
                WHERE opn.schedule_id = :eventId
                  AND opn.movement_reason_code = :eventSubType
                  AND opn.movement_date = :eventDate
                  AND opn.movement_type = 'TRN'
}
OIDSTOJU_DEL_NOTIFICATION_2 {
UPDATE OFFENDER_NOT_COMPLETIONS
                     SET status = 'CANCEL',
                     modify_datetime=current_timestamp,
                     modify_user_id=:modifyUserId
                     AND noti_seq = :vNotSeq
                     AND noti_move_seq = :vMNotSeq
                     AND status <> 'COMPLETED'
                     AND offender_book_id =:offenderBookId
}
OIDSTOJU_INS_NOTIFICATION_1 {
SELECT  notification_flag, notification_type
            FROM  MOVEMENT_REASONS
           WHERE  movement_type = 'TRN'
             AND  movement_reason_code = :eventSubType
}
OIDSTOJU_INS_NOTIFICATION_2 {
          SELECT COUNT(*)
            FROM OFFENDER_PEND_NOTIFICATIONS
           WHERE offender_book_id = :offenderBookId
             AND movement_type    = 'TRN'
             AND movement_reason_code = :eventSubType
             }
OIDSTOJU_INS_NOTIFICATION_3 {
          SELECT COUNT(offender_book_id)
            FROM OFFENDER_RELEASE_NOTIS orn
           WHERE orn.offender_book_id = :offenderBookId
             AND (orn.expiration_date IS NULL
              OR  orn.EXPIRATION_DATE >= SYSDATE)
             AND  NOT EXISTS (SELECT NULL
                                FROM NOTIFY_MOVEMENT_TYPES nmt
                               WHERE nmt.movement_type = 'TRN'
                                 AND nmt.movement_reason_code = :eventSubType
                                 AND nmt.offender_book_id = :offenderBookId
                                 AND nmt.noti_seq = orn.noti_seq)
}
OIDSTOJU_INS_NOTIFICATION_4 {
  SELECT orn.noti_seq,
    orn.noti_agency_party_code,
    orn.noti_corp_id,
    orn.noti_person_id
    FROM OFFENDER_RELEASE_NOTIS orn
    WHERE offender_book_id =:offenderBookId
     AND (orn.expiration_date IS NULL
     OR orn.EXPIRATION_DATE >= SYSDATE)
     AND  NOT EXISTS (SELECT NULL
         FROM NOTIFY_MOVEMENT_TYPES nmt
          WHERE nmt.movement_type = 'TRN'
              AND nmt.movement_reason_code = :eventSubType
              AND nmt.offender_book_id = :offenderBookId
              AND nmt.noti_seq = orn.noti_seq)
   }
OIDSTOJU_INS_NOTIFICATION_5 {
    SELECT NVL(MAX(noti_move_seq),0)
    FROM OFFENDER_PEND_NOTIFICATIONS
    WHERE offender_book_id = :offenderBookId
    AND noti_seq = :vNotiSeq
    }
OIDSTOJU_INS_NOTIFICATION_6 {
INSERT INTO OFFENDER_PEND_NOTIFICATIONS(offender_book_id, noti_seq,  noti_move_seq, movement_type, movement_reason_code,   movement_date, move_schedule_flag,  schedule_id)
                                                  VALUES( :offenderBookId, :vNotiSeq, :vNotiMSeq, 'TRN', :eventSubType, :eventDate, 'S', :eventId)
 }       
OIDSTOJU_INS_NOTIFICATION_7 {
  INSERT INTO OFFENDER_NOT_COMPLETIONS(offender_book_id,noti_seq,  noti_move_seq, status, noti_agency_party_code, noti_corp_id, noti_person_id)
                                                         VALUES(:offenderBookId, :vNotiSeq, :vNotiMSeq,'OPEN',:notiAgencypartyCode ,:notiCorpId , :notiPersonId)
 }
                                                         
