CREATE OR REPLACE FUNCTION oms_owner.trigger_fct_v_offender_all_schedules_2_tu()
 RETURNS trigger
 LANGUAGE plpgsql
 SECURITY DEFINER
AS $function$
DECLARE

  v_rec_count bigint;
BEGIN
  BEGIN
/*
============================================================
  MODIFICATION HISTORY
   Person       Date                   version      Comments
 -----------  -------------------------- -----------  -------------------------------
 Akhil           June 22, 2022   2.0
  NIKO           MAR 13, 2007   1.0               Added a new column - prov_state_code
                                                                          Removed 2 columns - TO_COUNTRY_CODE and OJ_LOCATION_CODE
*/
  IF (OLD.Event_date <> NEW.Event_Date) AND (NEW.Event_Date < date_trunc('day', LOCALTIMESTAMP) + -3*'1 month'::interval ) THEN
       RAISE EXCEPTION '%', msg= 'No Back-dating 3-month old record is allowed.' USING ERRCODE = num= '45107';
  END IF;

  CASE OLD.record_source
  WHEN 'SCH' THEN
       UPDATE OFFENDER_IND_SCHEDULES
       SET  EVENT_DATE = date_trunc('day', NEW.EVENT_DATE),
            START_TIME = NEW.START_TIME,
            END_TIME = NEW.END_TIME,
            EVENT_SUB_TYPE = NEW.event_sub_type,
            EVENT_STATUS = NEW.EVENT_STATUS,
            EVENT_OUTCOME = NEW.EVENT_OUTCOME,
            CONFIRM_FLAG = NEW.confirm_flag,
            COMMENT_TEXT = NEW.COMMENT_TEXT,
            HIDDEN_COMMENT_TEXT = NEW.HIDDEN_COMMENT_TEXT,
            APPLICATION_DATE = NEW.APPLICATION_DATE,
            AGY_LOC_ID = NEW.AGY_LOC_ID,
            TO_AGY_LOC_ID = NEW.TO_AGY_LOC_ID,
            TO_INTERNAL_LOCATION_ID = NEW.TO_INTERNAL_LOCATION_ID,
            OUTCOME_REASON_CODE = NEW.OUTCOME_REASON_CODE,
            IN_CHARGE_STAFF_ID = NEW.IN_CHARGE_STAFF_ID,
            CREDITED_HOURS = NEW.CREDITED_HOURS,
            PIECE_WORK = NEW.PIECE_WORK,
            ENGAGEMENT_CODE = NEW.ENGAGEMENT_CODE,
            UNDERSTANDING_CODE = NEW.UNDERSTANDING_CODE,
            DETAILS = NEW.DETAILS,
            AGREED_TRAVEL_HOUR = NEW.AGREED_TRAVEL_HOUR,
            UNPAID_WORK_SUPERVISOR = NEW.UNPAID_WORK_SUPERVISOR,
            UNPAID_WORK_BEHAVIOUR = NEW.UNPAID_WORK_BEHAVIOUR,
            UNPAID_WORK_ACTION = NEW.UNPAID_WORK_ACTION,
            SICK_NOTE_RECEIVED_DATE = NEW.SICK_NOTE_RECEIVED_DATE,
            SICK_NOTE_EXPIRY_DATE = NEW.SICK_NOTE_EXPIRY_DATE,
            UNEXCUSED_ABSENCE_FLAG = NEW.UNEXCUSED_ABSENCE_FLAG,
            ESCORT_CODE = NEW.ESCORT_CODE,
            DIRECTION_CODE = NEW.DIRECTION_CODE,
            TO_CITY_CODE = NEW.TO_CITY_CODE,
            FROM_CITY_CODE = NEW.FROM_CITY_CODE,
            OFF_PRGREF_ID = NEW.OFF_PRGREF_ID,
            IN_TIME = NEW.IN_TIME,
            OUT_TIME = NEW.OUT_TIME,
            PERFORMANCE_CODE = NEW.PERFORMANCE_CODE,
            TRANSPORT_CODE = NEW.TRANSPORT_CODE,
            APPLICATION_TIME = NEW.APPLICATION_TIME,
             CONTACT_PERSON_NAME = NEW.CONTACT_PERSON_NAME,
            TO_address_owner_class = NEW.to_address_owner_class,
            TO_address_ID          = NEW.to_address_ID,
            RETURN_DATE            = NEW.Return_date,
            RETURN_TIME            = NEW.Return_time,
            TO_CORPORATE_ID        = NEW.TO_CORPORATE_ID,
            PROV_STATE_CODE = NEW.PROV_STATE_CODE,
            Scheduled_Trip_id      = NEW.scheduled_trip_id
      WHERE EVENT_ID = OLD.event_ID;
  WHEN 'V_OFF_CRS' THEN
       IF (coalesce(OLD.Event_ID::text, '') = '') THEN
          UPDATE v_offender_course_events
          SET  EVENT_DATE 		= date_trunc('day', NEW.EVENT_DATE),
               START_TIME 		= NEW.START_TIME,
               END_TIME 		= NEW.END_TIME,
               EVENT_SUB_TYPE 		= NEW.event_sub_type,
               EVENT_STATUS 		= NEW.EVENT_STATUS,
               EVENT_OUTCOME 		= NEW.EVENT_OUTCOME,
               COMMENT_TEXT 		= NEW.COMMENT_TEXT,
               AGY_LOC_ID 	        = NEW.AGY_LOC_ID,
               TO_INTERNAL_LOCATION_ID 	= NEW.TO_INTERNAL_LOCATION_ID,
               UNEXCUSED_ABSENCE_FLAG   = NEW.UNEXCUSED_ABSENCE_FLAG,
               OUTCOME_REASON_CODE 	= NEW.OUTCOME_REASON_CODE,
               CREDITED_HOURS 		= NEW.CREDITED_HOURS,
               PIECE_WORK 		= NEW.PIECE_WORK,
               ENGAGEMENT_CODE 		= NEW.ENGAGEMENT_CODE,
               UNDERSTANDING_CODE 	= NEW.UNDERSTANDING_CODE,
               AGREED_TRAVEL_HOUR	= NEW.AGREED_TRAVEL_HOUR,
               BEHAVIOUR_CODE      	= NEW.UNPAID_WORK_BEHAVIOUR,
               ACTION_CODE 		= NEW.UNPAID_WORK_ACTION,
               SUPERVISOR_STAFF_ID      = NEW.In_Charge_Staff_ID,
               SICK_NOTE_RECEIVED_DATE = NEW.SICK_NOTE_RECEIVED_DATE,
               SICK_NOTE_EXPIRY_DATE = NEW.SICK_NOTE_EXPIRY_DATE,
               OFF_PRGREF_ID = NEW.OFF_PRGREF_ID,
               IN_TIME = NEW.IN_TIME,
               OUT_TIME = NEW.OUT_TIME,
               PERFORMANCE_CODE = NEW.PERFORMANCE_CODE,
               TO_address_ID          = NEW.to_address_ID
          WHERE Offender_Book_ID = OLD.Offender_Book_ID
          AND   Reference_ID     = OLD.Reference_ID
          AND   Event_Date       = OLD.Event_Date;
       ELSE
          UPDATE v_offender_course_events
          SET  EVENT_DATE 		= date_trunc('day', NEW.EVENT_DATE),
               START_TIME 		= NEW.START_TIME,
               END_TIME 		= NEW.END_TIME,
               EVENT_SUB_TYPE 		= NEW.event_sub_type,
               EVENT_STATUS 		= NEW.EVENT_STATUS,
               EVENT_OUTCOME 		= NEW.EVENT_OUTCOME,
               COMMENT_TEXT 		= NEW.COMMENT_TEXT,
               AGY_LOC_ID 	        = NEW.AGY_LOC_ID,
               TO_INTERNAL_LOCATION_ID 	= NEW.TO_INTERNAL_LOCATION_ID,
               OUTCOME_REASON_CODE 	= NEW.OUTCOME_REASON_CODE,
               CREDITED_HOURS 		= NEW.CREDITED_HOURS,
               UNEXCUSED_ABSENCE_FLAG   = NEW.UNEXCUSED_ABSENCE_FLAG,
               PIECE_WORK 		= NEW.PIECE_WORK,
               ENGAGEMENT_CODE 		= NEW.ENGAGEMENT_CODE,
               UNDERSTANDING_CODE 	= NEW.UNDERSTANDING_CODE,
               AGREED_TRAVEL_HOUR 	= NEW.AGREED_TRAVEL_HOUR,
               BEHAVIOUR_CODE      	= NEW.UNPAID_WORK_BEHAVIOUR,
               ACTION_CODE 		= NEW.UNPAID_WORK_ACTION,
               SICK_NOTE_RECEIVED_DATE = NEW.SICK_NOTE_RECEIVED_DATE,
               SICK_NOTE_EXPIRY_DATE = NEW.SICK_NOTE_EXPIRY_DATE,
               OFF_PRGREF_ID = NEW.OFF_PRGREF_ID,
               IN_TIME = NEW.IN_TIME,
               OUT_TIME = NEW.OUT_TIME,
               PERFORMANCE_CODE = NEW.PERFORMANCE_CODE,
               TO_address_ID          = NEW.to_address_ID
          WHERE Event_ID = OLD.Event_ID;
       END IF;
  WHEN 'OFF_VIS' THEN
       UPDATE v_Offender_Visit_schedules
       SET  EVENT_STATUS        = NEW.EVENT_STATUS,
            EVENT_OUTCOME       = NEW.EVENT_OUTCOME,
            Visitor_COMMENT_TEXT= NEW.COMMENT_TEXT,
            OUTCOME_REASON_CODE = NEW.OUTCOME_REASON_CODE
      WHERE EVENT_ID            = OLD.event_ID;
  WHEN 'OIC_HEARING' THEN
       UPDATE OIC_HEARINGS
       SET  HEARING_DATE        = date_trunc('day', NEW.EVENT_DATE),
            HEARING_TIME 	= NEW.START_TIME,
            EVENT_STATUS        = NEW.EVENT_STATUS,
            COMMENT_TEXT        = NEW.COMMENT_TEXT
      WHERE EVENT_ID      = OLD.event_ID;
  WHEN 'OFF_REL' THEN
       UPDATE OFFENDER_RELEASE_DETAILS
       SET  Release_date        = date_trunc('day', NEW.EVENT_DATE),
            MOvement_Reason_Code = NEW.Event_Sub_type,
            EVENT_STATUS        = NEW.EVENT_STATUS,
            COMMENT_TEXT        = NEW.COMMENT_TEXT
      WHERE EVENT_ID            = OLD.event_ID;
  WHEN 'COURT' THEN
       UPDATE COURT_EVENTS
       SET  EVENT_DATE          = date_trunc('day', NEW.EVENT_DATE),
            START_TIME 		= NEW.START_TIME,
            Court_EVENT_TYPE    = NEW.event_sub_type,
            EVENT_STATUS        = NEW.EVENT_STATUS,
            EVENT_OUTCOME       = NEW.EVENT_OUTCOME,
            COMMENT_TEXT        = NEW.COMMENT_TEXT,
            AGY_LOC_ID          = NEW.TO_Agy_Loc_ID,
            scheduled_trip_id   = NEW.scheduled_trip_id,
            OUTCOME_REASON_CODE = NEW.OUTCOME_REASON_CODE
      WHERE EVENT_ID = OLD.event_ID;
  ELSE
     SELECT COUNT(*)
     INTO STRICT   v_rec_count
     FROM   OFFENDER_IND_SCHEDULES
     WHERE  Offender_Book_id = OLD.Offender_book_id
     AND    Event_type       = OLD.event_type
     AND    Reference_id     = OLD.reference_id;
     IF (v_rec_count = 0) THEN
     INSERT INTO OFFENDER_IND_SCHEDULES(EVENT_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, END_TIME, EVENT_CLASS,
            EVENT_TYPE, EVENT_SUB_TYPE, EVENT_STATUS, EVENT_OUTCOME, COMMENT_TEXT, HIDDEN_COMMENT_TEXT,
            APPLICATION_DATE, AGY_LOC_ID,
            TO_AGY_LOC_ID, TO_INTERNAL_LOCATION_ID,
            OUTCOME_REASON_CODE,
            IN_CHARGE_STAFF_ID, CREDITED_HOURS,
            PIECE_WORK, ENGAGEMENT_CODE, UNDERSTANDING_CODE, DETAILS,
            AGREED_TRAVEL_HOUR, UNPAID_WORK_SUPERVISOR, UNPAID_WORK_BEHAVIOUR,
            UNPAID_WORK_ACTION, SICK_NOTE_RECEIVED_DATE, SICK_NOTE_EXPIRY_DATE,
            UNEXCUSED_ABSENCE_FLAG, ESCORT_CODE, DIRECTION_CODE, TO_CITY_CODE,
            FROM_CITY_CODE, OFF_PRGREF_ID, IN_TIME,
            OUT_TIME, SELECTANCE_CODE, REFERENCE_ID, TRANSPORT_CODE,
            APPLICATION_TIME, CONTACT_PERSON_NAME,
            TO_address_owner_class, TO_address_ID, RETURN_DATE, RETURN_TIME,
            TO_CORPORATE_ID, TA_ID, PROV_STATE_CODE, scheduled_trip_id,create_datetime)
     VALUES (nextval('event_id'), NEW.OFFENDER_BOOK_ID, date_trunc('day', NEW.EVENT_DATE), NEW.START_TIME,
             NEW.END_TIME, NEW.EVENT_CLASS, NEW.EVENT_TYPE, NEW.EVENT_SUB_TYPE,
             NEW.EVENT_STATUS, NEW.EVENT_OUTCOME, NEW.COMMENT_TEXT, NEW.HIDDEN_COMMENT_TEXT,
             NEW.APPLICATION_DATE,
             NEW.AGY_LOC_ID, NEW.TO_AGY_LOC_ID, NEW.TO_INTERNAL_LOCATION_ID,
             NEW.OUTCOME_REASON_CODE,
             NEW.IN_CHARGE_STAFF_ID,
             NEW.CREDITED_HOURS, NEW.PIECE_WORK, NEW.ENGAGEMENT_CODE,
             NEW.UNDERSTANDING_CODE, NEW.DETAILS,
             NEW.AGREED_TRAVEL_HOUR, NEW.UNPAID_WORK_SUPERVISOR, NEW.UNPAID_WORK_BEHAVIOUR,
             NEW.UNPAID_WORK_ACTION, NEW.SICK_NOTE_RECEIVED_DATE, NEW.SICK_NOTE_EXPIRY_DATE,
             NEW.UNEXCUSED_ABSENCE_FLAG, NEW.ESCORT_CODE, NEW.DIRECTION_CODE, NEW.TO_CITY_CODE,
             NEW.FROM_CITY_CODE, NEW.OFF_PRGREF_ID, NEW.IN_TIME,
             NEW.OUT_TIME, NEW.PERFORMANCE_CODE, NEW.REFERENCE_ID, NEW.TRANSPORT_CODE,
             NEW.APPLICATION_TIME, NEW.CONTACT_PERSON_NAME,
             NEW.TO_address_owner_class, NEW.TO_address_ID, NEW.RETURN_DATE, NEW.RETURN_TIME,
             NEW.TO_CORPORATE_ID, NEW.TA_ID, NEW.PROV_STATE_CODE, NEW.scheduled_trip_id,current_timestamp);
      END IF;
  END CASE;

EXCEPTION
  WHEN SQLSTATE '50016' THEN
       RAISE EXCEPTION '%', msg= 'Duplicate Event record' USING ERRCODE = num= '45107';
  END;
RETURN NEW;
END
$function$
;


create trigger v_offender_all_schedules_2_tu instead of
update
    on
    oms_owner.v_offender_all_schedules_2 for each row execute function trigger_fct_v_offender_all_schedules_2_tu();
