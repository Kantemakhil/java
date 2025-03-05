DROP TRIGGER IF EXISTS v_offender_course_events_td ON v_offender_course_events CASCADE;
CREATE OR REPLACE FUNCTION trigger_fct_v_offender_course_events_td() RETURNS trigger AS $BODY$
BEGIN
     IF (coalesce(OLD.Event_ID::text, '') = '') THEN
        INSERT INTO Offender_course_attendances(EVENT_ID, offender_book_ID, event_date, off_prgref_id,
             Start_Time, End_Time, in_time, out_time,
             event_status, Reference_id,crs_acty_id, event_type, event_sub_type,
             crs_sch_id, crs_appt_id, agy_loc_id, event_class,
             comment_text, piece_work, SELECTance_code, direction_code)
        VALUES (nextval('event_id'), OLD.Offender_book_ID, OLD.event_date,
             OLD.Off_prgref_id,
             NEW.start_time, NEW.end_time, NEW.in_time, NEW.out_time,
	     'DEL', OLD.Reference_ID, OLD.crs_acty_id,
             OLD.event_type, OLD.event_sub_type, OLD.crs_sch_id, OLD.crs_appt_id,
             OLD.agy_loc_id, OLD.event_class, OLD.comment_text, OLD.piece_work,
             OLD.performance_code, OLD.direction_code);
     ELSE
        UPDATE Offender_course_attendances
        SET    Event_Status = 'DEL'
        WHERE  event_id = OLD.event_id;
     END IF;

RETURN OLD;
END
$BODY$
 LANGUAGE 'plpgsql' SECURITY DEFINER;
-- REVOKE ALL ON FUNCTION trigger_fct_v_offender_course_events_td() FROM PUBLIC;

CREATE TRIGGER v_offender_course_events_td
	INSTEAD OF DELETE ON v_offender_course_events FOR EACH ROW
	EXECUTE PROCEDURE trigger_fct_v_offender_course_events_td();

DROP TRIGGER IF EXISTS v_offender_course_events_ti ON v_offender_course_events CASCADE;
CREATE OR REPLACE FUNCTION trigger_fct_v_offender_course_events_ti() RETURNS trigger AS $BODY$
BEGIN
  BEGIN
  IF ( (NEW.Off_prgref_ID IS NOT NULL AND NEW.Off_prgref_ID::text <> '') AND (coalesce(NEW.Event_Class::text, '') = '') ) THEN
        INSERT INTO OFFENDER_COURSE_ATTENDANCES(EVENT_ID, event_class, offender_book_id, event_date, off_prgref_id,
             event_status, crs_acty_id, event_sub_type, event_type, Start_Time,
             End_Time, in_time, out_time,
             event_outcome, agy_loc_ID,
             comment_text, piece_work, performance_code, outcome_reason_code,
             to_internal_location_id, to_address_id, to_agy_loc_id,
             credited_hours, agreed_travel_hour,
             engagement_code, understanding_code,
             behaviour_code,supervisor_staff_id, unexcused_absence_flag, session_no,
             reference_id, direction_code,
             crs_appt_id
             )
        SELECT
             coalesce(NEW.Event_ID,nextval('event_id')),
             CASE WHEN RC.Parent_Code='BOTH' THEN  TAG_PRG.PRG_EVENT_CLASS(opp.off_prgref_id)  ELSE RC.Parent_Code END ,
             opp.offender_book_id, NEW.Event_date,
             opp.off_prgref_id, coalesce(NEW.Event_Status, 'SCH'), Opp.crs_acty_ID,
             ca.Course_activity_type, coalesce(ps.program_category, tag_prg.prg_event_type(ps.program_id)), NEW.Start_Time,
             NEW.End_Time, coalesce(NEW.In_time, NEW.Start_Time), coalesce(NEW.Out_Time,NEW.End_Time),
             NEW.Event_Outcome, ob.agy_loc_id,
             NEW.Comment_text, NEW.piece_work, NEW.Performance_code, NEW.outcome_reason_code,
             ca.internal_location_id, ca.services_address_ID,
             CASE WHEN ca.provider_party_class='AGY' THEN  provider_party_code  ELSE CA.agy_loc_id END ,
             NEW.credited_hours, opp.agreed_travel_hour,
             NEW.engagement_code, NEW.understanding_code,
             NEW.behaviour_code, NEW.supervisor_staff_id, NEW.unexcused_absence_flag,
             NEW.session_no,
             NEW.reference_id, NEW.direction_code,
             NEW.crs_appt_id
        FROM reference_codes rc, program_services ps, offender_program_profiles opp, offender_bookings ob, course_activities ca
LEFT OUTER JOIN addresses addr ON (ca.Services_Address_ID = Addr.Address_ID)
WHERE ca.crs_acty_id = opp.crs_acty_id AND opp.Offender_Program_status = 'ALLOC' AND ca.program_id = ps.program_id AND RC.domain = 'PS_CATEGORY' AND RC.code = coalesce(ps.program_category, tag_prg.prg_event_type(ps.program_id)) AND ca.active_flag = 'Y'  --             AND addr.Owner_class       = 'AGY'
  AND ob.offender_book_id = opp.offender_book_id AND opp.off_prgref_id = NEW.off_prgref_id;
  ELSE
        INSERT INTO OFFENDER_COURSE_ATTENDANCES(EVENT_ID, event_class, offender_book_id, event_date, off_prgref_id,
             event_status, Reference_id, crs_sch_id, crs_appt_id, crs_acty_id, event_sub_type,
             event_type, Start_Time, End_Time, in_time, out_time, event_outcome, agy_loc_ID,
             comment_text, piece_work, SELECTance_code, outcome_reason_code,
             to_agy_loc_id, to_internal_location_id, engagement_code, understanding_code,
             credited_hours, agreed_travel_hour, behaviour_code,supervisor_staff_id,
             unexcused_absence_flag, to_address_id, direction_code, session_no)
        VALUES (coalesce(NEW.Event_ID,nextval('event_id')), NEW.event_class,NEW.Offender_Book_id, NEW.event_date,
             NEW.Off_prgref_id, NEW.Event_status, NEW.Reference_ID, NEW.crs_sch_id, NEW.crs_appt_id,
             NEW.crs_acty_id, NEW.event_sub_type, NEW.event_type,
             NEW.start_time, NEW.end_time, NEW.in_time, NEW.out_time, NEW.event_outcome, NEW.agy_loc_ID,
             NEW.comment_text, NEW.piece_work, NEW.performance_code, NEW.outcome_reason_code,
             NEW.to_agy_loc_id, NEW.to_internal_location_id, NEW.engagement_code, NEW.understanding_code,
             NEW.credited_hours, NEW.agreed_travel_hour, NEW.behaviour_code, NEW.supervisor_staff_id,
             NEW.unexcused_absence_flag, NEW.to_address_id, NEW.direction_code, NEW.session_no);
  END IF;
  EXCEPTION
      WHEN OTHERS
      THEN
         RAISE '% %',SQLERRM,SQLSTATE;
  END;
RETURN NEW;
END
$BODY$
 LANGUAGE 'plpgsql' SECURITY DEFINER;
-- REVOKE ALL ON FUNCTION trigger_fct_v_offender_course_events_ti() FROM PUBLIC;

CREATE TRIGGER v_offender_course_events_ti
	INSTEAD OF INSERT ON v_offender_course_events FOR EACH ROW
	EXECUTE PROCEDURE trigger_fct_v_offender_course_events_ti();

DROP TRIGGER IF EXISTS v_offender_course_events_tu ON v_offender_course_events CASCADE;
CREATE OR REPLACE FUNCTION trigger_fct_v_offender_course_events_tu() RETURNS trigger AS $BODY$
BEGIN
     IF (coalesce(OLD.Event_ID::text, '') = '') THEN
        INSERT INTO OFFENDER_COURSE_ATTENDANCES(EVENT_ID, event_class, offender_book_id, event_date, off_prgref_id,
             event_status, Reference_id, crs_sch_id, crs_appt_id, crs_acty_id, event_sub_type,
             event_type, Start_Time, End_Time, in_time, out_time, event_outcome, agy_loc_ID,
             comment_text, piece_work, SELECTance_code, outcome_reason_code,
             to_agy_loc_id, to_internal_location_id, to_address_id,
             engagement_code, understanding_code,
             credited_hours, agreed_travel_hour, behaviour_code,supervisor_staff_id,
             unexcused_absence_flag, direction_code)
        VALUES (coalesce(NEW.Event_ID,nextval('event_id')), NEW.event_class,NEW.Offender_Book_id, NEW.event_date,
             NEW.Off_prgref_id, NEW.Event_status, NEW.Reference_ID, NEW.crs_sch_id, NEW.crs_appt_id,
             NEW.crs_acty_id, NEW.event_sub_type, NEW.event_type,
             NEW.start_time, NEW.end_time, NEW.in_time, NEW.out_time, NEW.event_outcome, NEW.agy_loc_ID,
             NEW.comment_text, NEW.piece_work, NEW.performance_code, NEW.outcome_reason_code,
             NEW.to_agy_loc_id, NEW.to_internal_location_id, NEW.to_address_id,
             NEW.engagement_code, NEW.understanding_code,
             NEW.credited_hours, NEW.agreed_travel_hour, NEW.behaviour_code,NEW.supervisor_staff_id,
             NEW.unexcused_absence_flag, NEW.direction_code);
     ELSE
        UPDATE OFFENDER_COURSE_ATTENDANCES
        SET    Event_Status  = NEW.Event_Status,
               Event_date    = NEW.event_date,
               Start_Time    = NEW.Start_time,
               End_Time      = NEW.End_Time,
               in_Time       = NEW.in_time,
               Out_Time      = NEW.Out_time,
               crs_acty_id   = NEW.crs_acty_id,
               comment_text  = NEW.comment_text,
               Piece_work    = NEW.piece_work,
               performance_code = NEW.performance_code,
               event_outcome = NEW.event_outcome,
               off_prgref_id = NEW.off_prgref_id,
               crs_sch_id    = NEW.crs_sch_id,
               unexcused_absence_flag  = NEW.unexcused_absence_flag,
               outcome_reason_code     = NEW.outcome_reason_code,
               supervisor_staff_id     = NEW.supervisor_staff_id,
               to_agy_loc_id 	       = NEW.to_agy_loc_id,
               to_address_id           = NEW.to_address_id,
               to_internal_location_id = NEW.to_internal_location_id,
               engagement_code         = NEW.engagement_code,
               understanding_code      = NEW.understanding_code,
               credited_hours          = NEW.credited_hours,
               agreed_travel_hour     = NEW.agreed_travel_hour,
               behaviour_code          = NEW.behaviour_code,
               direction_code          = NEW.direction_code,
               crs_appt_id   = NEW.crs_appt_id
        WHERE  event_id = OLD.event_id;
     END IF;
RETURN NEW;
END
$BODY$
 LANGUAGE 'plpgsql' SECURITY DEFINER;
-- REVOKE ALL ON FUNCTION trigger_fct_v_offender_course_events_tu() FROM PUBLIC;

CREATE TRIGGER v_offender_course_events_tu
	INSTEAD OF UPDATE ON v_offender_course_events FOR EACH ROW
	EXECUTE PROCEDURE trigger_fct_v_offender_course_events_tu();
	
	
CREATE OR REPLACE PROCEDURE TAG_off_ap_v1_prdel (prOldRec IN record, prNewRec IN record) AS $body$
BEGIN
    DELETE FROM offender_action_plans
    WHERE off_action_plan_id = prOldRec.off_action_plan_id;
  EXCEPTION
    WHEN OTHERS THEN
          RAISE EXCEPTION '%', 'Error in tag_off_ap_v1_pkg.prDel : ' || SQLERRM USING ERRCODE = '45001';
END;
$body$
LANGUAGE PLPGSQL
;
-- REVOKE ALL ON PROCEDURE oms_owner.prdel (prOldRec OffApV1Type, prNewRec OffApV1Type) FROM PUBLIC;



CREATE OR REPLACE PROCEDURE TAG_off_ap_v1_prIns (prOldRec IN record, prNewRec IN record) AS $body$
BEGIN
    INSERT INTO offender_action_plans(off_action_plan_id
                                     ,casework_type
                                     ,off_crim_need_id
                                     ,off_case_cond_id
                                     ,program_id
                                     ,notes
                                     ,start_date
                                     ,end_date)
                   VALUES (prnewrec.off_action_plan_id
                          ,prnewrec.casework_type
                          ,prnewrec.off_crim_need_id
                          ,null
                          ,prnewrec.program_id
                          ,prnewrec.notes
                          ,prnewrec.start_date
                          ,prnewrec.end_date);
  EXCEPTION
    WHEN OTHERS THEN
          RAISE EXCEPTION '%', 'Error in tag_off_ap_v1_pkg.prIns : ' || SQLERRM USING ERRCODE = '45001';
END;
$body$
LANGUAGE PLPGSQL
;
-- REVOKE ALL ON PROCEDURE oms_owner.prins (prOldRec OffApV1Type, prNewRec OffApV1Type) FROM PUBLIC;



CREATE OR REPLACE PROCEDURE TAG_off_ap_v1_prupd (prOldRec IN record, prNewRec in record) AS $body$
BEGIN
  UPDATE offender_action_plans
     SET program_id   = prnewrec.program_id
        ,notes        = prnewrec.notes
                    ,start_date   = prnewrec.start_date
                  ,end_date     = prnewrec.end_date
  WHERE off_action_plan_id = proldrec.off_action_plan_id;
  EXCEPTION
    WHEN OTHERS THEN
          RAISE EXCEPTION '%', 'Error in tag_off_ap_v1_pkg.prUpd : ' || SQLERRM USING ERRCODE = '45001';
END;
$body$
LANGUAGE PLPGSQL;



DROP TRIGGER IF EXISTS ap_vr_iud ON off_ap_v1 CASCADE;
CREATE OR REPLACE FUNCTION trigger_fct_ap_vr_iud() RETURNS trigger AS $BODY$
/******************************************************************************
  NAME:       AP_VR_IUD_TRG
  PURPOSE: To handle all DML activies against this view and it's related
    dependant children
******************************************************************************/
DECLARE

 lrOldRec record;
 lrNewRec record;
BEGIN
  BEGIN
/* =========================================================
    Version Number = 1.1 Date Modified = 08/21/2008
   ========================================================= */
-- MODIFICATION HISTORY
-- Author     Date     SR               Description
-- ----- ----------  ---------------  ------------------------------------
--  Niko   08/21/2008   1.1              Version update
--  Niko   08/01/2008   1.0              Generated BL stub.
  -- Capture OLD Values
  lrOldRec.off_action_plan_id := OLD.off_action_plan_id;
  lrOldRec.casework_type := OLD.casework_type;
  lrOldRec.casework_type_desc := OLD.casework_type_desc;
  lrOldRec.off_crim_need_id := OLD.off_crim_need_id;
  lrOldRec.off_case_cond_id := OLD.off_case_cond_id;
  lrOldRec.program_id := OLD.program_id;
  lrOldRec.program_desc := OLD.program_desc;
  lrOldRec.notes := OLD.notes;
  lrOldRec.start_date := OLD.start_date;
  lrOldRec.end_date := OLD.end_date;

  -- Capture NEW Values
  lrNewRec.off_action_plan_id := NEW.off_action_plan_id;
  lrNewRec.casework_type := NEW.casework_type;
  lrNewRec.casework_type_desc := NEW.casework_type_desc;
  lrNewRec.off_crim_need_id := NEW.off_crim_need_id;
  lrNewRec.off_case_cond_id := NEW.off_case_cond_id;
  lrNewRec.program_id := NEW.program_id;
  lrNewRec.program_desc := NEW.program_desc;
  lrNewRec.notes := NEW.notes;
  lrNewRec.start_date := NEW.start_date;
  lrNewRec.end_date := NEW.end_date;

  IF TG_OP = 'INSERT' THEN
   CALL TAG_off_ap_v1_prIns(lrOldRec, lrNewRec);
  ELSIF TG_OP = 'UPDATE' THEN
    call TAG_off_ap_v1_prupd(lrOldRec, lrNewRec);
  ELSIF TG_OP = 'DELETE' THEN
   CALL TAG_off_ap_v1_prDel(lrOldRec, lrNewRec);
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    RAISE; /*error_pkg.raise_unhandled_error;*/
  END;
IF TG_OP = 'DELETE' THEN
        RETURN OLD;
ELSE
        RETURN NEW;
END IF;

END
$BODY$
 LANGUAGE 'plpgsql';

CREATE TRIGGER ap_vr_iud
        INSTEAD OF INSERT OR UPDATE OR DELETE ON off_ap_v1 FOR EACH ROW
        EXECUTE PROCEDURE trigger_fct_ap_vr_iud();
       
       
DROP TRIGGER IF EXISTS v_acp_progress_td ON v_acp_progress CASCADE;
CREATE OR REPLACE FUNCTION trigger_fct_v_acp_progress_td() RETURNS trigger AS $BODY$
BEGIN
       DELETE FROM offender_program_profiles V WHERE V.OFFENDER_PRG_OBLIGATION_ID = new.OFFENDER_PRG_OBLIGATION_ID;
	   RETURN OLD;
END
$BODY$
 LANGUAGE 'plpgsql' SECURITY DEFINER;

CREATE TRIGGER v_acp_progress_td
	INSTEAD OF delete ON v_acp_progress FOR EACH ROW
	EXECUTE PROCEDURE trigger_fct_v_acp_progress_td();		
		
DROP TRIGGER IF EXISTS v_offender_prg_obligations_ti ON v_offender_prg_obligations CASCADE;
CREATE OR REPLACE FUNCTION v_offender_prg_obligations_ti() RETURNS trigger AS $BODY$
BEGIN
  IF TG_OP = 'INSERT'
  THEN
    INSERT INTO offender_prg_obligations(offender_prg_obligation_id,
       offender_book_id,
       program_id,
       status,
       status_change_date,
       status_change_reason,
       offender_sent_cond_act_id,
       start_date,
       end_date,
       event_type,
       event_sub_type,
       comment_text,
       sentence_seq,
       length,
       length_unit,
       offender_sent_condition_id,
       special_need_flag,
       availability_code,
       obligation_source)
    VALUES (NEW.offender_prg_obligation_id,
       NEW.offender_book_id,
       NEW.program_id,
       NEW.status,
       NEW.status_change_date,
       NEW.status_change_reason,
       NEW.offender_sent_cond_act_id,
       NEW.start_date,
       NEW.end_date,
       NEW.event_type,
       NEW.event_sub_type,
       NEW.comment_text,
       NEW.sentence_seq,
       NEW.length,
       NEW.length_unit,
       NEW.offender_sent_condition_id,
       NEW.special_need_flag,
       NEW.availability_code,
       NEW.obligation_source);
  ELSIF TG_OP = 'UPDATE'
  THEN
    UPDATE offender_prg_obligations
       SET referral_date     = NEW.referral_date,
           special_need_flag = NEW.special_need_flag,
           comment_text      = NEW.comment_text,
           availability_code = NEW.availability_code
     WHERE offender_prg_obligation_id = NEW.offender_prg_obligation_id;
  ELSE
    DELETE FROM offender_prg_obligation_hty
     WHERE offender_prg_obligation_id = OLD.offender_prg_obligation_id;
    DELETE FROM offender_prg_obligations
     WHERE offender_prg_obligation_id = OLD.offender_prg_obligation_id;
  END IF;
IF TG_OP = 'DELETE' THEN
        RETURN OLD;
ELSE
        RETURN NEW;
END IF;

END
$BODY$
 LANGUAGE 'plpgsql';

CREATE TRIGGER v_offender_prg_obligations_ti
        INSTEAD OF INSERT OR DELETE OR UPDATE ON v_offender_prg_obligations  for EACH ROW
        EXECUTE PROCEDURE v_offender_prg_obligations_ti();
			