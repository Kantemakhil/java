set search_path to oms_owner;

CREATE OR REPLACE VIEW dual
 AS
 SELECT 'X'::character varying AS dummy;

ALTER TABLE dual
    OWNER TO oms_owner;
	

CREATE OR REPLACE VIEW v_offender_requirements (offender_book_id, sentence_seq, offender_sent_condition_id, list_seq, comm_condition_type, comm_condition_code, condition_description, condition_length, length_unit_description, condition_start_date, status_update_date, condition_status, condition_status_desc, team_description, condition_comment_text, curfew_provider_description, curfew_start_time, curfew_end_time, curfew_review_description, curfew_tagging_flag, details_text, residency_address_id, exclusion_code, mental_health_provider, attendance_centre, appointment_person_name, supervisor_name, report_date, report_time, review_code_description, personal_relationship_flag, vehicle_details_flag, no_work_with_under_age, no_work_with_under_age_of, no_access_to_internet, no_user_of_computer, prohibited_contact, restricted_child_age_of, restricted_approval_person, non_associated_offenders, drug_testing, other_program, alcohol_treatment_provider, comment_text, curfew_provider, workflow_id, activity_code, program_id) AS SELECT
/* =========================================================
   Version Number = 10.2.1.0  Date Modified = 14/01/2010
   ========================================================= */
/* MODIFICATION HISTORY
Author         Date               Version    Description
---------      ----------         --------   --------------------------------
Erin           14/01/2010         10.2.1.0   Migrate to Elite
Claus          11/07/2007         2.5        D# 6826. Peer-review fix. Removed team ID and call to get_team_name.
Claus          10/07/2007         2.4        D# 6826. Fixed to get the correct team.
Claus          09/07/2007         2.3        D# 6826. Derive team from function OCDSREQS.get_workflow_team
GJC            01/05/2007         2.2        Changed for CR230
Surya          02/10/2006         2.1        Termination date should only populate
                                             for inactive status.
Surya          26/09/2006         2.0        Created View for module OCDSREQS
*/
            osc.offender_book_id, osc.sentence_seq,
            osc.offender_sent_condition_id, coalesce( cc.list_seq, 0 ),
            cc.comm_condition_type, cc.comm_condition_code,
            cc.description condition_description, osc.LENGTH condition_length,
            tag_reference_codes_getdesccode( 'UNIT',
                                      osc.length_unit )
                                                      length_unit_description,
            osc.start_date condition_start_date,
            CASE WHEN  osc.condition_status='I' THEN  osc.status_update_date  ELSE NULL END  status_update_date,
            osc.condition_status,
            tag_reference_codes_getdesccode( 'ACTIVE_TYPE',
                                   osc.condition_status )
                                                        condition_status_desc,
            ocdsreqs_get_workflow_team( osc.offender_book_id,
                                         osc.workflow_id ) team_description,
            osc.long_comment_text condition_comment_text,
            tag_reference_codes_getdesccode( 'COND_CURFEW',
                              osc.curfew_provider )
                                                  curfew_provider_description,
            osc.curfew_start_time, osc.curfew_end_time,
            tag_reference_codes_getdesccode( 'COND_REVIEW',
                                    osc.review_code )
                                                    curfew_review_description,
            osc.curfew_tagging_flag, osc.details_text,
            osc.residency_address_id, osc.exclusion_code,
            osc.mental_health_provider, osc.attendance_centre,
            osc.appointment_person_name, osc.supervisor_name, osc.report_date,
            osc.report_time,
            tag_reference_codes_getdesccode( 'COND_REVIEW',
                                      osc.review_code )
                                                      review_code_description,
            osc.personal_relationship_flag, osc.vehicle_details_flag,
            osc.no_work_with_under_age, osc.no_work_with_under_age_of,
            osc.no_access_to_internet, osc.no_user_of_computer,
            osc.prohibited_contact, osc.restricted_child_age_of,
            osc.restricted_approval_person, osc.non_associated_offenders,
            osc.drug_testing, osc.other_program,
            osc.alcohol_treatment_provider, osc.comment_text,
            osc.curfew_provider, osc.workflow_id, osc.activity_code,
            osc.program_id
       FROM offender_sent_conditions osc, community_conditions cc
      WHERE osc.comm_condition_type = cc.comm_condition_type
        AND osc.comm_condition_code = cc.comm_condition_code
   ORDER BY condition_status_desc, cc.list_seq, cc.description

;

CREATE OR REPLACE VIEW v_ptr_details (ptr_id, ptr_detail_type, ptr_detail_code, ptr_detail_text, type, description, format_type, list_seq) AS SELECT a.ptr_id ptr_id,
          op.ptr_detail_type ptr_detail_type,
          op.ptr_detail_code ptr_detail_code,
          op.ptr_detail_text ptr_detail_text,
          a.TYPE,
          a.description,
          a.format_type,
          a.list_seq
     FROM (SELECT *
             FROM ptr_detail_types, offender_ptr) a
LEFT OUTER JOIN offender_ptr_details op ON (a.ptr_id = op.ptr_id AND a.TYPE = op.ptr_detail_type)
WHERE (a.active_flag = 'Y'
               OR (a.active_flag = 'N' AND (op.ptr_id IS NOT NULL AND op.ptr_id::text <> '')))

;

CREATE OR REPLACE VIEW v_merge_transaction_processes (merge_transaction_id, transaction_type, process_id, process_name, process_description, transfer_flag, timeframe_flag, begin_date, begin_time, end_date, end_time, mandatory_on_flag, default_on_flag) AS select MT.merge_transaction_id,
MT.transaction_type,
MP.process_id,
MP.process_name,
MP.description,
'Y',
MTP.timeframe_flag,
MTP.begin_date,
MTP.begin_date,
MTP.end_date,
MTP.end_date,
MP.MANDATORY_ON_FLAG,
MP.DEFAULT_ON_FLAG
FROM  merge_transactions MT, merge_processes MP,
      merge_transaction_processes MTP
WHERE MT.merge_transaction_id = MTP.merge_transaction_ID
AND   MP.process_id           = MTP.process_id
AND   MP.active_flag = 'Y'

UNION

select MT.merge_transaction_id,
MT.transaction_type,
MP.process_id,
MP.process_name,
MP.description,
'N',
MP.timeframe_required_flag,
NULL,
NULL,
NULL,
NULL,
MP.MANDATORY_ON_FLAG,
MP.DEFAULT_ON_FLAG
FROM  merge_transactions MT,
      merge_processes MP
WHERE MP.transaction_type  = MT.transaction_type
AND   MP.transaction_type = 'TRANSFER'
AND   MP.active_flag = 'Y'
AND   NOT EXISTS (SELECT 1 FROM merge_transaction_processes MTP
      WHERE  MT.merge_transaction_id = MTP.merge_transaction_ID
      AND    MP.Process_ID           = MTP.Process_ID)

;

CREATE OR REPLACE VIEW v_dependent_parameter_list (module_name, parameter_seq, parameter_name, parent_parameter_name) AS Select
/*====================================================================================
  Version Number = 1.0          Date Modified = 19-MAR-2012
--
-- MODIFICATION HISTORY
-- Person      Date             Version      Comments
-- ---------   -------------    ------------ -----------------------------------------
--  Firas      19-MAR-2012         1.0       Initial creation for HPQC#13830
=====================================================================================*/
       Module_Name,
       Parameter_Seq,
       Parameter_Name,
       Substr(Parent_Parameter_Name, Length('$LOV$.') + 1) Parent_Parameter_Name
  FROM (Select Distinct OMP1.Module_Name,
               OMP1.Parameter_Seq,
               OMP1.Parameter_Name,
               Case
                 When position('$LOV$.' || OMP2.Parameter_Name in OMP1.Parameter_Lov_Select) = 0 Then
                 Null
               Else
                 Case
                   When Upper(OMP1.parameter_lov_select) Like '%$LOV$.%' Then
                     Substr(OMP1.parameter_lov_select,
                            position('$LOV$.' || OMP2.Parameter_Name in OMP1.parameter_lov_select),
                                    Length('$LOV$.' || OMP2.Parameter_Name))
               End
               End Parent_Parameter_Name
          From Oms_Module_Parameters OMP1,
               Oms_Module_Parameters OMP2
         Where OMP1.Module_Name = OMP2.Module_Name) alias7

;

CREATE OR REPLACE VIEW v_offender_program_profiles (off_prgref_id, offender_book_id, offender_id, offender_id_display, age, first_name, last_name, offender_name, sex_code, race_code, offender_alert, offence_types, offender_program_status, program_id, offender_start_date, offender_end_date, crs_acty_id, suspended_flag) AS select
   /* =========================================================
    Version Number = 10.2.0.2 Date Modified = 06/04/2012
    ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version   Comments
   ---------    ------     -------  ------------------------------
   Nirmal       04/06/2012 2.3      Added suspended_flag column for HPQC#11714.
   David Ng     18/08/2006 2.2      Add ' for the name
   David Ng     03/04/2006 2.1      NOMIS project(10.2.0)
*/
   opp.OFF_PRGREF_ID,
   opp.Offender_Book_ID,
   BKG.Offender_ID,
   OFF.Offender_ID_DISPLAY,
   Round((Months_Between(date_trunc('day', LOCALTIMESTAMP),OFF.Birth_date)-0.5)/12) AGE,
   OFF.First_Name,
   OFF.Last_name,
   OFF.Last_Name||', '||OFF.First_NAme Offender_Name,
   OFF.Sex_Code,
   OFF.RACE_CODE,
   SUBSTR(omkhead_get_alerts(BKG.offender_book_id),
               0,
               40
              ) Offender_Alert,
   SUbstr(TAG_PRG_Offender_Offence_Type(opp.Offender_BOok_ID),1,12) Offence_types,
   opp.Offender_Program_status,
   opp.Program_ID,
   opp.Offender_start_date,
   opp.Offender_End_date,
   opp.Crs_Acty_ID,
   opp.suspended_flag --Added by Nirmal for #11714.
FROM  Offender_program_profiles opp, Offender_bookings BKG,
      Offenders OFF
WHERE opp.Offender_Book_ID   = BKG.Offender_Book_ID
AND   BKG.Offender_ID        = OFF.Offender_ID

;

CREATE OR REPLACE VIEW v_questionnaire_categories (questionnaire_category, description, list_seq, active_flag, expired_date) AS select
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     01/10/2005  2.0           NOMIS project (10.2.0)
*/
code, description, list_seq, active_flag, expired_date
FROM reference_codes
where domain = 'QUE_CAT'

 
;

CREATE OR REPLACE VIEW v_incidents (agency_incident_id, incident_datetime, incident_status, lock_status, agy_loc_id, agy_loc_desc, internal_location_id, internal_location_desc, incident_type, incident_type_desc, incident_details, repairs_required, reported_staff_id, reported_staff_first_name, reported_staff_last_name, reported_datetime) AS SELECT
/* =========================================================
   Version Number = 1.0  Date Modified = 10-DEC-2020
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date      Version        Comments
   ---------    ------     ---------     ------------------------------
   Syscon     10-DEC-2020  1.0           Incident Report
*/
ai.agency_incident_id,
ai.incident_time as incident_datetime,
ai.incident_status,
CASE WHEN ai.lock_flag='Y' THEN  'YES'  ELSE 'NO' END  as lock_status,
ai.agy_loc_id,
ag.description as agy_loc_desc,
ai.internal_location_id,
ail.description as internal_location_desc,
ai.incident_type,
rfc_i_typ.description as incident_type_desc,
ai.incident_details,
(SELECT CASE WHEN COUNT(*)=0 THEN  'NO'  ELSE 'YES' END
  FROM agency_incident_repairs air
  WHERE  ai.agency_incident_id = air.agency_incident_id) as repairs_required,
ai.reported_staff_id,
sm.first_name as reported_staff_first_name,
sm.last_name as reported_staff_last_name,
ai.report_time as reported_datetime
FROM agency_incidents ai, staff_members sm, agency_locations ag,
     agency_internal_locations ail, reference_codes rfc_i_typ
WHERE sm.staff_id = ai.reported_staff_id
  AND ail.internal_location_id = ai.internal_location_id
  AND ai.agy_loc_id = ag.agy_loc_id
  AND ai.incident_type = rfc_i_typ.code
  AND rfc_i_typ.domain = 'INC_TYPE';

CREATE OR REPLACE VIEW v_offender_sentence_charges (offender_book_id, case_id, case_info_number, sentence_seq, sentence_calc_type, start_date, end_date, sed_calculated_date, fine_amount, sentence_status, offence_code, statute_code, offender_charge_id, offence_date) AS SELECT
/* =========================================================
     Version Number = 2.2  Date Modified = 14-MAR-2006
   ========================================================= */
/* =========================================================================
|| MODIFICATION HISTORY
|| Person      Date           Version       Comments
|| ---------   -----------    ------------  ------------------------------
|| Aditya      14-MAR-2006    2.2           More Formatting
|| Aditya      08-MAR-2006    2.1           After Peer Review comments
|| Vikas       06-FEB-2006    2.0           Create the View .
   ========================================================================= */
          off_cas.offender_book_id, off_cas.case_id, off_cas.case_info_number,
          off_sent.sentence_seq, off_sent.sentence_calc_type,
          off_sent.start_date, off_sent.end_date,
          off_sent.sed_calculated_date, off_sent.fine_amount,
          off_sent.sentence_status, off_chg.offence_code,
          off_chg.statute_code, off_chg.offender_charge_id,
          off_chg.offence_date
     FROM offender_cases off_cas,
          offender_sentences off_sent,
          offender_charges off_chg,
          offender_sentence_charges osc
    WHERE off_cas.case_id 		 = off_sent.case_id
      AND off_sent.offender_book_id  = osc.offender_book_id
      AND off_sent.sentence_seq 	 = osc.sentence_seq
      AND off_chg.offender_charge_id = osc.offender_charge_id

 
;

CREATE OR REPLACE VIEW v_bank_cheque_beneficiaries (create_date, cheque_number, cheque_status, cheque_paid_date, cheque_txn_id, txn_entry_date, txn_entry_time, txn_entry_desc, txn_post_usage, cheque_amount, person_id, corporate_id) AS SELECT
       DISTINCT date_trunc('day', BCR.CREATE_DATE)
     , BCR.CHEQUE_NUMBER
     , BCR.CHEQUE_STATUS
     , BCR.CHEQUE_PAID_DATE
     , BCB.CHEQUE_TXN_ID
     , GL.TXN_ENTRY_DATE
     , GL.TXN_ENTRY_TIME
     , GL.TXN_ENTRY_DESC
     , GL.TXN_POST_USAGE                -- @@@ Herbert 23-OCT-2000 : Use to select either CR or DR only
     , BCB.CHEQUE_AMOUNT
     , BCB.PERSON_ID
     , BCB.CORPORATE_ID
     -- ,BCB.OFFENDER_ID                -- @@@ Herbert 23-OCT-2000 : comment out as not used in OCUTRAHI
     -- BCB.OFFENDER_DEDUCTION_ID
 FROM gl_transactions gl, bank_cheque_beneficiaries bcb
LEFT OUTER JOIN bank_cheque_registers bcr ON (BCB.CHEQUE_TXN_ID = BCR.TXN_ID)
WHERE GL.TXN_ID = BCB.CHEQUE_TXN_ID -- AND    GL.TXN_POST_USAGE = 'CR'
  GROUP BY
       date_trunc('day', BCR.CREATE_DATE)
     , BCR.CREATE_DATE
     , BCR.CHEQUE_NUMBER
     , BCR.CHEQUE_STATUS
     , BCR.CHEQUE_PAID_DATE
     , BCB.CHEQUE_TXN_ID
     , GL.TXN_ENTRY_DATE
     , GL.TXN_ENTRY_TIME
     , GL.TXN_ENTRY_DESC
     , GL.TXN_POST_USAGE
     , BCB.CHEQUE_AMOUNT
     , BCB.PERSON_ID
     , BCB.CORPORATE_ID

 
;

CREATE OR REPLACE VIEW v_prison_activities (crs_acty_id, service, program_id, activity, agy_loc_id, agy_loc_desc, schedule_start_date, schedule_end_date, internal_location_id, internal_location_desc, active_flag, list_seq, program_code) AS SELECT
/*
============================================================
  Version Number = 2.8          Date Modified = 11/14/2011
============================================================
MODIFICATION HISTORY
     Person   Date          Version   Comments
     ------   -----------   -------   ----------------------
     Abhishek 14-NOV-2011   2.8        Add PS.PROGRAM_CODE column in the query.
     Dinesh   28-OCT-2011   2.7        Removed drop and create public synonyms statements.
     Abhishek 20-OCT-2011   2.6        Add PS.active_flag,PS.list_seq columns in the query.
     Niraj    04-July-2007  2.5        changed  from 'PRISON_ACT' TO 'INST_ACT' FOR PS.program_category
     Sonny    11-Dec-2006   2.4        removed space in between parenthesis
     Krishna  22-Nov-2006   2.3        Removed the commented line 'AND CA.schedule_start_date <= SYSDATE' from the where clause
     Krishna  17-Nov-2006   2.2        Added Trunc when checking the end_date >= sysdate
     Erin     3-Apr-2006    2.1        schedule_start_date must be <= SYSDATE
     David Ng 30-Mar-2006   2.0        Standard Format
     Erin     18-Mar-2006   1.1        Initial version
*/
 CA.crs_acty_id,
 PS.description service,
 PS.program_id,
 CA.description activity,
 CA.agy_loc_id,
 AL.description agy_loc_desc,
 CA.schedule_start_date,
 CA.schedule_end_date,
 CA.internal_location_id,
 AIL.description internal_location_desc,
 PS.active_flag,
 PS.list_seq,
 PS.program_code
  FROM program_services          PS,
       course_activities         CA,
       agency_locations          AL,
       agency_internal_locations AIL
 WHERE PS.program_id = CA.program_id
   AND CA.agy_loc_id = AL.agy_loc_id
   AND AIL.internal_location_id = CA.internal_location_id
   AND PS.program_class = 'PRG' AND PS.program_category = 'INST_ACT'
   AND (coalesce(CA.schedule_end_date::text, '') = '' OR CA.schedule_end_date >= date_trunc('day', LOCALTIMESTAMP))

;

CREATE OR REPLACE VIEW v_invoice_payable (caseload_id, vendor_corp_id, inv_count, inv_amount, max_inv_enter_date, min_inv_enter_date) AS SELECT
      ship.caseload_id
     ,ship.VENDOR_CORP_ID
     ,count(ship.invoice_id)
     ,SUM(ship.SHIPMENT_TOTAL)
     ,MAX(INVOICE_ENTER_DATE)
     ,MIN(INVOICE_ENTER_DATE)
 FROM
     shipments  ship
  WHERE ship.check_generated_flag = 'N'
  group by ship.caseload_id, ship.vendor_corp_id

 
;

CREATE OR REPLACE VIEW v_images (image_id, capture_date, set_name, image_object_type, image_object_id, image_object_seq, image_view_type, create_user_id, image_thumbnail, image_size, active_flag, orientation_type, modify_user_id) AS select
/* =========================================================
   Version Number = 1.0  Date Modified = 28-SEP-2010
   =========================================================
-- MODIFICATION HISTORY
-- Person      Date                    Version       Comments
-- ---------  ------------------      ------------   ------------------------------
--  NIKO      28-SEP-2010             1.0            Modified the view as per new image data model
--
========================================================= */
     IMAGE_ID
      ,CAPTURE_DATE
      ,NULL SET_NAME
      ,IMAGE_OBJECT_TYPE
      ,IMAGE_OBJECT_ID
      ,IMAGE_OBJECT_SEQ
      ,IMAGE_VIEW_TYPE
      ,CREATE_USER_ID
      ,IMAGE_THUMBNAIL
      ,NULL IMAGE_SIZE
      ,ACTIVE_FLAG
      ,ORIENTATION_TYPE
      ,MODIFY_USER_ID
FROM images

;

CREATE OR REPLACE VIEW v_offender_visit_visitors (offender_visit_order_id, offender_visit_id, visit_offender_book_id, offender_visit_visitor_id, offender_book_id, person_id, group_leader_flag, comment_text, event_outcome, outcome_reason_code, event_id, event_status) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   Vikas Grover 30/Mar/2007 2.1.1.0 	 TAG10gR2 : Deleted columns which were not required for TAG
   		 								 	Table offender_vo_visitors deleted therefore select not rqd from this table
   David Ng     03/06/2006 2.1           NOMIS project(10.2.0)
*/
NULL
,ov.OFFENDER_VISIT_ID
,ov.Offender_Book_ID           Visit_Offender_Book_ID
,ovv.OFFENDER_VISIT_VISITOR_ID
,ovv.Offender_Book_ID
,ovv.PERSON_ID
,ovv.GROUP_LEADER_FLAG
-- ,OVV.ASSISTED_VISIT_FLAG	  -- @@@ Vikas : TAG10gR2 : Not required for TAG
,ovv.COMMENT_TEXT
,ovv.EVENT_OUTCOME
,ovv.OUTCOME_REASON_CODE
,ovv.EVENT_ID
,ovv.EVENT_STATUS
FROM   OFFENDER_VISIT_VISITORS ov, OFFENDER_VISIT_VISITORS ovv
WHERE  (ov.Offender_Book_ID IS NOT NULL AND ov.Offender_Book_ID::text <> '')
AND    ov.Offender_Visit_ID = ovv.Offender_visit_ID
/* UNION ALL
SELECT
OVV.Offender_Visit_Order_ID
,NULL -- OFFENDER_VISIT_ID
,NULL -- Offender_Book_ID
,NULL -- OFFENDER_VISIT_VISITOR_ID
,NULL -- Offender_Book_ID
,OVV.PERSON_ID
,OVV.GROUP_LEADER_FLAG
--,NULL -- OVV.ASSISTED_VISIT_FLAG  -- @@@ Vikas : TAG10gR2 : Not required for TAG
,NULL -- OOV.COMMENT_TEXT
,NULL -- OOV.EVENT_OUTCOME
,NULL -- OUTCOME_REASON_CODE
,NULL -- EVENT_ID
,NULL -- EVENT_STATUS
FROM   OFFENDER_VO_VISITORS OvV
WHERE  NOT EXISTS ( SELECT 'X'
                   FROM OFFENDER_VISITS ov1
                   WHERE ov1.offender_visit_order_id = ovv.offender_visit_order_id
       AND   0 != (
       SELECT COUNT(*)
        FROM   OFFENDER_VISIT_VISITORS ovv2,
               OFFENDER_VISITS ov2
        WHERE  ov2.offender_visit_id = ovv2.offender_visit_id
          AND  ov2.offender_visit_order_id = ov1.offender_visit_order_id
          AND  ovv2.outcome_reason_code IS NULL )
        ) */
 
;

CREATE OR REPLACE VIEW v_offender_course_events_2 (event_id, offender_book_id, event_class, event_type, event_sub_type, off_prgref_id, reference_id, crs_sch_id, crs_appt_id, program_id, course_code, description, crs_acty_id, weekday, event_date, start_time, end_time, in_time, out_time, event_status, event_outcome, agy_loc_id, to_internal_location_id, comment_text, outcome_reason_code, piece_work, engagement_code, understanding_code, credited_hours, agreed_travel_hour, behaviour_code, action_code, sick_note_received_date, sick_note_expiry_date, performance_code, to_agy_loc_id, to_address_id, performance_desc, event_outcome_desc, supervisor_staff_id, unexcused_absence_flag, direction_code, ext_move_out_time, ext_move_in_time, schedule_movement_time, session_no, record_source, check_sum) AS SELECT
/* =========================================================
    Version Number = 1.0 Date Modified = 04/08/2013
   ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   David Ng     15/10/2005  2.0          NOMIS project(10.2.0)
   David Ng     12/01/2006  2.1          Replace Course_activity_id by Code
   David Ng     23/01/2006  2.2          Add course appts
   David Ng     30/01/2006  2.3          Event-Sub type <- Course_acitivty_type
   David Ng     07/06/2006  2.4          Add session no for schedules
   David Ng     27/06/2006  2.5          Make sure event_class and event_sub_type is
                                         set up properly
   David Ng     27/07/2006  2.6          Add In-Charge Staff ID
   David Ng     04/08/2006  2.7          Dervise the Event Class Of Accedited Program
   David Ng     08/08/2006  2.8          Dervise the Event Type for module
                                         and To_Agy_Loc_ID from address_ID
   David Ng     11/08/2006  2.9          Filter out Catch Up Session
   Davud Bg     18/08/2006  2.10         remove filter AGY from TO_agy_loc_id
                                         add session no to Offender Course Attendances
   David Ng     07/08/2009  2.11         Default In_time and Out_time for Non-Community Schedules ONLY
   David Ng     02/09/2009  2.12         Virtual Schedules for external movements records (EXT_MOV)
                                         with direction_code added with external movement in/out time
                                         align the columns of insert trigger
   Jason Xu     08/04/2013  1.0          copy from V_OFFENDER_COURSE_EVENTS 2.12 for SD form OIIWLTWJ
                                         performance issue
*/
   OCA.event_id,
   OCA.offender_book_id,
   OCA.event_class, -- event_class,
   OCA.event_type, -- event_type,
   OCA.event_sub_type,
   OCA.off_prgref_id,
   OCA.Reference_ID,
   OCA.crs_sch_id,
   OCA.crs_appt_id,
   OPP.program_id,
   CA.code,
   CA.description,
   OCA.crs_acty_id,
   TO_CHAR(OCA.event_date, 'DY'), -- weekday,
   OCA.event_date, -- schedule_date,
   OCA.start_time,
   OCA.end_time,
   OCA.in_time,
   OCA.Out_time,
   OCA.Event_Status, -- status,
   OCA.Event_Outcome,
   OCA.agy_loc_id,
   OCA.to_Internal_location_id,
   OCA.comment_text,
   OCA.outcome_reason_code,
   OCA.piece_work,
   OCA.engagement_code,
   OCA.understanding_code,
   OCA.credited_hours,
   OCA.agreed_travel_hour,
   OCA.behaviour_code,
   OCA.action_code,
   OCA.Sick_note_received_date,
   OCA.sick_note_expiry_date,
   OCA.Performance_code,
   OCA.to_agy_loc_ID,
   OCA.TO_address_ID,
   SUBSTR(oms_miscellaneous_getdesccode('PERFORMANCE',
                                                 OCA.performance_code),
                  1,
                  40
                 ),
   SUBSTR(oms_miscellaneous_getdesccode('OUTCOMES',
                                                 OCA.event_outcome),
                  1,
                  40
                 ),
   OCA.supervisor_staff_id,
   OCA.unexcused_absence_flag,
   OCA.direction_code,
   CASE WHEN OCA.Event_class='EXT_MOV' THEN  tag_prg_external_movement_time(OCA.event_id, 'OUT')  ELSE NULL END , -- Ext_Mov_Out_Time
   CASE WHEN OCA.Event_class='EXT_MOV' THEN  tag_prg_external_movement_time(OCA.event_id, 'IN')  ELSE NULL END , -- Ext_Mov_in_Time
   CASE WHEN OCA.Direction_code='OUT' THEN  OCA.start_Time WHEN OCA.Direction_code='IN' THEN  OCA.end_time  END , -- Schedule_Movement_Time
   OCA.Session_no,
   'CRS_ATT',
   Tag_schedule_check_sum(coalesce(OCA.modify_datetime, OCA.create_datetime))
FROM offender_ind_sch_wait_lists wl, offender_bookings bkg, offender_course_attendances oca
LEFT OUTER JOIN offender_program_profiles opp ON (OCA.Off_prgref_ID = OPP.off_prgref_id)
LEFT OUTER JOIN course_activities ca ON (OPP.crs_acty_id = CA.crs_acty_id)
WHERE OCA.Offender_Book_ID = BKG.Offender_Book_ID   AND Event_Status <> 'DEL' AND OCA.event_id = WL.event_id

;

CREATE OR REPLACE VIEW person_address_v (person_id, address_id, owner_class, owner_id, owner_code, owner_seq, suite_number, street_number, street, street_direction, zip_postal_code, city_code, country_code, prov_state_code, city_desc, state_desc, country_desc, mail_care_of, primary_flag, mail_flag) AS SELECT
/* =========================================================
   Version Number = 1.5  Date Modified = 22-APR-2008
   ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      							Version   Comments
   ---------         ------         	 ---------  ------------------------------
  Johnny           22/04/2008           1.5       Added 2 new columns mail_flag and primary_flag
  Niko             29/10/2007           1.4       Added the selection mail logic to Persons
  Niko             27/10/2007           1.3       Added new column - MAIL_CARE_OF
  Niko             19/10/2007           1.2       Modified the domain - 'PROV_STATE'
  Niko             26/01/2007           1.1       Added more columns
  Niko             26/01/2007           1.0       Created for Trust OTRCHECK form
*/
 p.PERSON_ID
         ,a.ADDRESS_ID
		 ,a.OWNER_CLASS
		 ,a.OWNER_ID
		 ,a.OWNER_CODE
		 ,a.OWNER_SEQ
         ,a.suite_number
         , a.street_number
	       , a.street
	       , a.street_direction
	       ,a.zip_postal_code
		   ,a.CITY_CODE
		   ,a.COUNTRY_CODE
		   ,a.PROV_STATE_CODE
	       ,(SELECT description
	           FROM REFERENCE_CODES
			      WHERE domain = 'CITY'
			        AND code = a.city_code) city_desc
         ,(SELECT description
	           FROM REFERENCE_CODES
			      WHERE domain = 'PROV_STATE'
			        AND code = a.PROV_STATE_CODE) state_desc
			   ,(SELECT description
	           FROM REFERENCE_CODES
			      WHERE domain = 'COUNTRY'
			        AND code = a.country_code) country_desc
        , a.mail_care_of
        , a.primary_flag
        , a.mail_flag
	    FROM persons p
LEFT OUTER JOIN (SELECT *
        FROM addresses a
       WHERE a.owner_class = 'PER'
         AND (   (a.primary_flag = 'Y' AND coalesce(a.end_date::text, '') = '')
                    OR (    a.mail_flag = 'Y'
                            AND coalesce(a.end_date::text, '') = ''
                        AND a.primary_flag <> 'Y'
                    )
       )) a ON (p.person_id = a.OWNER_ID);

CREATE OR REPLACE VIEW v_offender_visits (offender_visit_id, visit_offender_book_id, comment_text, raised_incident_type, raised_incident_number, visit_date, start_time, end_time, visit_type, visit_status, visit_internal_location_id, agency_visit_slot_id, agy_loc_id, offender_visit_visitor_id, offender_book_id, offender_id, offender_last_name, offender_first_name, offender_id_display, visit_owner_flag, event_status, event_outcome, outcome_reason_code, visitor_comment_text, event_id, check_sum) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   Vikas Grover 30/Mar/07   2.1.1.0   	 TAG10gR2 : Few Columns are dropped from table, not required in TAG
   		 								  Association with offender_visit_orders is dropped because this table no longer exists
   David Ng     07/08/2006 2.2           Add Offender Details
   David Ng     27/06/2006 2.1           NOMIS project(10.2.0)
*/
--   OVO.Offender_Visit_Order_ID,		 		 -- @@@ Vikas : TAG10gR2 : Table Deleted
   OV.offender_visit_id,
--   OVO.Offender_Book_ID,
   OV.Offender_Book_ID Visit_Offender_Book_ID,
   OV.comment_text,
-- @@@ Vikas : TAG10gR2 : These columns are dropped from tables therefore not required in TAG
--   OV.override_ban_staff_id,		-- @@ TAG10gR2
--   OV.search_type,				-- @@ TAG10gR2
   OV.raised_incident_type,
   OV.raised_incident_number,
--   OV.visitor_concern_text,		-- @@ TAG10gR2
   OV.visit_date,
   OV.start_time,
   OV.end_time,
   OV.visit_type,
   OV.visit_status,
   OV.visit_internal_location_id,
   OV.agency_visit_slot_id,
   OV.agy_loc_id,
-- @@ TAG10gR2 : Table offender_visit_orders deleted
--   OVO.VISIT_ORDER_NUMBER,		-- @@ TAG10gR2
--   OVO.COMMENT_TEXT,
--   OVO.ISSUE_DATE,
--   OVO.VISIT_ORDER_TYPE,			-- @@ TAG10gR2
--   OVO.STATUS,
--   OVO.EXPIRY_DATE,
--   OVO.OUTCOME_REASON_CODE,
--   OVO.MAILED_DATE,
   OVV.Offender_Visit_Visitor_ID,
   OVV.offender_book_id,
   Off.Offender_ID,
   Off.Last_name,
   Off.First_name,
   Off.Offender_ID_DIsplay,
   CASE WHEN OVV.offender_book_id=OV.Offender_Book_ID THEN  'Y'  ELSE 'N' END ,
   OVV.EVENT_STATUS,
   coalesce(OVV.Event_Outcome,'ATT'),
   OVV.outcome_reason_code,
   OVV.Comment_text,
   OVV.event_id,
   Tag_Schedule_check_sum(coalesce(GREATEST(OVV.MODIFY_DATETIME,OV.MODIFY_DATETIME),OVV.CREATE_DATETIME))
FROM offender_visits ov, offenders off, -- OFFENDER_VISIT_ORDERS OVO,				  -- @@@ Vikas : TAG : Table deleted in TAG
       offender_visit_visitors ovv
LEFT OUTER JOIN offender_bookings bkg ON (OVV.Offender_Book_ID = BKG.Offender_Book_ID)
WHERE OV.Offender_Visit_id 		= OVV.Offender_visit_id -- AND   OVO.Offender_Visit_Order_ID(+) 	= OV.Offender_visit_Order_ID
  AND BKG.Offender_ID                   = Off.Offender_ID AND OVV.Event_ID 			> 0 AND NOT(coalesce(OVV.Offender_Book_ID::text, '') = '' AND (OV.Visit_Date IS NOT NULL AND OV.Visit_Date::text <> '')) -- @@@ Vikas : TAG10gR2 : Table offender_visit_orders Deleted
/* UNION ALL
SELECT
   OVO.Offender_Visit_Order_ID,
   NULL, -- OV.offender_visit_id,
   OVO.Offender_Book_ID,
   OVO.Offender_Book_ID, -- Visit_Offender_Book_ID,
   NULL, -- OV.comment_text,
--   NULL, -- OV.override_ban_staff_id,			   	-- @@ TAG10gR2
--   NULL, -- OV.search_type,							-- @@ TAG10gR2
   NULL, -- OV.raised_incident_type,
   NULL, -- OV.raised_incident_number,
--   NULL, -- OV.visitor_concern_text,				-- @@ TAG10gR2
   NULL, -- OV.visit_date,
   NULL, -- OV.start_time,
   NULL, -- OV.end_time,
   NULL, -- OV.visit_type,
   NULL, -- OV.visit_status,
   NULL, -- OV.visit_internal_location_id,
   NULL, -- OV.agency_visit_slot_id,
   NULL, -- OV.agy_loc_id,
--   OVO.VISIT_ORDER_NUMBER,		-- @@@ Vikas : TAG10gR2 : This table is dropped, not required in TAG
   OVO.COMMENT_TEXT,
   OVO.ISSUE_DATE,
   OVO.VISIT_ORDER_TYPE,
   OVO.STATUS,
   OVO.EXPIRY_DATE,
   OVO.OUTCOME_REASON_CODE,
   OVO.MAILED_DATE,
   NULL, --ovv.Offender_Visit_Visitor_ID,
   OVO.offender_book_id,
   OFF.Offender_ID,
   OFF.Last_name,
   OFF.First_name,
   OFF.Offender_ID_DIsplay,
   NULL, -- DECODE(OVV.offender_book_id, OV.Offender_Book_ID, 'Y', 'N'),
   NULL, -- OVV.EVENT_STATUS,
   NULL, -- OVV.Event_Outcome,
   NULL, -- OVv.outcome_reason_code,
   NULL, -- ovv.Comment_text,
   NULL, -- OVV.event_id,
   Tag_Schedule.check_sum(NVL(GREATEST(OVO.MODIFY_DATETIME,OVO.MODIFY_DATETIME),OVO.CREATE_DATETIME))
FROM  OFFENDER_VISIT_ORDERS OVO, Offender_Bookings BKG, OffenderS Off
WHERE OVO.STATUS = 'A'
AND   OVO.Offender_Book_ID 		= BKG.Offender_Book_ID(+)
AND   BKG.Offender_ID                   = OFF.Offender_ID
AND   NOT EXISTS
      (SELECT 'X'
       FROM   OFFENDER_VISITS OV
       WHERE  OV.Offender_Visit_ORder_ID = OVO.Offender_Visit_Order_ID
       AND    ov.visit_status IN ('SCH', 'COMP') ) */
;

CREATE OR REPLACE VIEW v_offender_team_assign_hty (offender_book_id, function_type, function_type_desc, team_id, team_code, team_description, expiry_date, assign_date, offender_team_assign_hty_id) AS select
/* =========================================================
   TAG DB Version Number = 2.0  Date Modified = 08/03/2006
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   Krishna      08/03/2006  2.0          NOMIS project
*/
otah.offender_book_id               OFFENDER_BOOK_ID,
otah.function_type                  FUNCTION_TYPE,
ref_codes.description               FUNCTION_TYPE_DESC,
otah.team_id                        TEAM_ID,
teams.team_code                     TEAM_CODE,
teams.description                   TEAM_DESCRIPTION,
otah.expiry_date                    EXPIRY_DATE,
otah.assign_date                    ASSIGN_DATE,
otah.offender_team_assign_hty_id    OFFENDER_TEAM_ASSIGN_HTY_ID
FROM offender_team_assign_hty otah,
     teams  teams,
     reference_codes ref_codes
where otah.team_id = teams.team_id
and otah.function_type = ref_codes.code
and ref_codes.domain = 'FUNCTION'

 
;

CREATE OR REPLACE VIEW v_offender_test_selections (offender_book_id, rtp_id, test_selection_type, test_selection_no, tested_flag, reason_not_tested, notes, offender_id_display, last_name, first_name, middle_name, active_flag, prison_location, in_out_status, probable_release_date) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   Patrick      08-AUG-2006  2.3        Repleace Probable_release_date from Offender_Legal_Sentence_Aggs to
                                        release_date of offender_release_details
*/
       ots.offender_book_id,
       ots.rtp_id,
       ots.test_selection_type,
       ots.test_selection_no,
       ots.tested_flag,
       ots.reason_not_tested,
       ots.notes,
       off.offender_id_display,
       off.last_name,
       off.first_name,
       off.middle_name,
       ob.active_flag,
	   CASE WHEN ob.active_flag='Y' THEN        SUBSTR( tag_header_get_prison_location_short( ob.agy_loc_id,                                    ob.living_unit_id,                                    ob.agency_iml_id                                  ),               0,               105             )  ELSE NULL END  prison_location,
       CASE WHEN ob.active_flag='Y' THEN  ob.in_out_status  ELSE NULL END ,
       CASE WHEN ob.active_flag='Y' THEN  ord.release_date  ELSE NULL END
FROM offender_test_selections ots, offenders off, offender_bookings ob
LEFT OUTER JOIN offender_release_details ord ON (ob.offender_book_id = ord.offender_book_id)
WHERE ots.offender_book_id = ob.offender_book_id AND off.offender_id = ob.offender_id;

CREATE OR REPLACE VIEW v_template_cursor_selections (template_code, table_name, column_name) AS select TEMPLATE_CODE,
         TABLE_NAME,
         COLUMN_NAME
    FROM TEMPLATE_CURSOR_SELECTIONS


 
;
CREATE OR REPLACE VIEW v_addresses (address_id, address_type, address_type_desc, owner_class, owner_id, owner_seq, owner_code, start_date, end_date, active_flag, full_address, house, street, area, country, suite_number, street_number, street_direction, street_direction_desc, street_information, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, country_desc, capacity, comment_text, primary_flag, mail_flag, services_flag, no_fixed_address_flag, special_needs, validated_flag, contact_person_name, business_hour, mail_care_of) AS SELECT
/* =========================================================
   Version Number = 2.4.2.5 Date Modified = 30-Jun-2011
   ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      			Version           Comments
   ---------   ------      	    ---------      ------------------------------
   Nancy        30/06/2010      2.4.2.5          added / for the script
   Nancy        28/06/2011      2.4.2.4          Modified the street_information to remove the space has been created in the front
   Sarah        05/01/2009      2.4.2.2          Added decode for street_information
   Niko   	    27/10/2007   		2.4.2.1 		     Added a new column - MAIL_CARE_OF
   Vikas  	    02/13/2007   		2.4.2.0 		     Column added street_information and domain is changed from STATE to PROV_STATE
   Niko         26/01/2007      2.4.1.0          Modified the following columns
                                                 suite_number,street_number,street_direction,prov_state_code,zip_postal_code
   David Ng     19/09/2005       2.4             Fixed the no fixed addresses
   David Ng     07/27/2005       2.1             Adding City Name
   David Ng     06/23/2005       1.11            NOMIS new phones table
                                                 rename from_date to start_date
                                                 to_date to end_date
*/
a.ADDRESS_ID
,a.ADDRESS_TYPE
,SUBSTR(CASE WHEN coalesce(a.address_type::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('ADDR_TYPE',a.address_type) END ,1,40)
,OWNER_CLASS
,OWNER_ID
,OWNER_SEQ
,OWNER_CODE
,a.start_date
,a.end_date
,CASE WHEN coalesce(a.end_date::text, '') = '' THEN  'Y'  ELSE CASE WHEN LEAST(a.end_date::date,LOCALTIMESTAMP::date)=LOCALTIMESTAMP::date THEN 'Y'  ELSE 'N' END  END  ACTIVE_FLAG
,CASE WHEN NO_FIXED_ADDRESS_FLAG='Y' THEN  'No fixed address'  ELSE SUBSTR(CASE WHEN coalesce(SUITE_NUMBER::text, '') = '' THEN  NULL  ELSE SUITE_NUMBER||'  ' END ||CASE WHEN coalesce(STREET_NUMBER::text, '') = '' THEN  NULL  ELSE LTRIM(STREET_NUMBER, ' ')||'  ' END || CASE WHEN coalesce(STREET::text, '') = '' THEN  NULL  ELSE LTRIM(STREET, ' ')||'  ' END ||CASE WHEN coalesce(STREET_DIRECTION::text, '') = '' THEN  NULL  ELSE STREET_DIRECTION||'  ' END || CASE WHEN coalesce(city_name::text, '') = '' THEN  Oms_Miscellaneous_GETDESCCODE('CITY',CITY_CODE)  ELSE city_name END ||'  '|| CASE WHEN coalesce(PROV_STATE_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('PROV_STATE',PROV_STATE_CODE)||'  ' END ,1,200)|| CASE WHEN coalesce(ZIP_POSTAL_CODE::text, '') = '' THEN  NULL  ELSE ZIP_POSTAL_CODE||'  ' END || SUBSTR(CASE WHEN coalesce(COUNTRY_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('COUNTRY',COUNTRY_CODE) END ,1,40) END  FULL_ADDRESS
,CASE WHEN coalesce(NO_FIXED_ADDRESS_FLAG, 'N')='N' THEN CASE WHEN coalesce(SUITE_NUMBER::text, '') = '' THEN  NULL  ELSE SUITE_NUMBER||'  ' END ||CASE WHEN coalesce(STREET_NUMBER::text, '') = '' THEN  NULL  ELSE LTRIM(STREET_NUMBER, ' ') END   ELSE 'No fixed address' END  HOUSE
,CASE WHEN coalesce(NO_FIXED_ADDRESS_FLAG, 'N')='N' THEN CASE WHEN coalesce(STREET::text, '') = '' THEN  NULL  ELSE LTRIM(STREET, ' ')||'  ' END   ELSE ' ' END  STREET
,CASE WHEN coalesce(NO_FIXED_ADDRESS_FLAG, 'N')='N' THEN  SUBSTR(CASE WHEN coalesce(STREET_DIRECTION::text, '') = '' THEN  NULL  ELSE STREET_DIRECTION||'  ' END || CASE WHEN coalesce(city_name::text, '') = '' THEN  Oms_Miscellaneous_GETDESCCODE('CITY',CITY_CODE)  ELSE city_name END ||'  '|| CASE WHEN coalesce(PROV_STATE_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('PROV_STATE',PROV_STATE_CODE) END ,1,80)  ELSE ' ' END  AREA
,SUBSTR(CASE WHEN coalesce(COUNTRY_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('COUNTRY',COUNTRY_CODE) END ,1,40) COUNTRY
,SUITE_NUMBER
,STREET_NUMBER
,STREET_DIRECTION
,SUBSTR(Oms_Miscellaneous_GETDESCCODE('STREET_DIR',STREET_DIRECTION),1,40) STREET_DIRECTION_DESC
--,DECODE(NO_FIXED_ADDRESS_FLAG, 'Y', 'No fixed address',SUBSTR(STREET_NUMBER || ' ' || STREET || ' ' || Oms_Miscellaneous.GETDESCCODE('STREET_DIR',STREET_DIRECTION),1,254)) STREET_INFORMATION
,case
		when NO_FIXED_ADDRESS_FLAG = 'Y' then 'No fixed address'
		else SUBSTR
		(concat(case when coalesce(STREET_NUMBER::text, '') = '' then null
		else concat(LTRIM(STREET_NUMBER, ' '),' ')
	end ,
	case
		when coalesce(STREET::text, '') = '' then null
		else concat(LTRIM(STREET, ' '),'  ')
	end, Oms_Miscellaneous_GETDESCCODE('STREET_DIR',
	STREET_DIRECTION)),
	1,
	254)
	end STREET_INFORMATION
,CITY_CODE
,SUBSTR(CASE WHEN coalesce(city_name::text, '') = '' THEN  Oms_Miscellaneous_GETDESCCODE('CITY',CITY_CODE)  ELSE city_name END ,1,40) CITY_NAME
,PROV_STATE_CODE
,SUBSTR(Oms_Miscellaneous_GETDESCCODE('PROV_STATE',PROV_STATE_CODE),1,40) PROV_STATE_DESC
,ZIP_POSTAL_CODE
,COUNTRY_CODE
,SUBSTR(Oms_Miscellaneous_GETDESCCODE('COUNTRY',COUNTRY_CODE),1,40) COUNTRY_DESC
,CAPACITY
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,SERVICES_FLAG
,NO_FIXED_ADDRESS_FLAG
,SUBSTR(CASE WHEN coalesce(SPECIAL_NEEDS_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('PS_NEEDS',SPECIAL_NEEDS_CODE) END ,1,40)
,VALIDATED_PAF_flag
,CONTACT_PERSON_NAME
,BUSINESS_HOUR
,mail_care_of
FROM ADDRESSES a

;

CREATE OR REPLACE VIEW v_offender_sent_conditions (offender_book_id, sentence_seq, comm_condition_type, comm_condition_code, cond_description, list_seq, apply_flag, cond_start_date, condition_status, cond_status_date, cond_expiry_date, cond_comment_text, curfew_start_time, curfew_end_time, condition_recommended_flag, governor_condition_flag, details_text, offender_sent_condition_id, curfew_provider, exclusion_code, residency_address_id, mental_health_provider, alcohol_treatment_provider, attendance_centre, condition_required_flag, condition_applied_flag, appointment_person_name, review_code, supervisor_name, report_time, report_date, personal_relationship_flag, vehicle_details_flag, non_associated_offenders, drug_testing, status_reason_code, no_resident_under_age_of, prohibited_contact, restricted_child_age_of, restricted_approval_person, curfew_tagging_flag, other_program, curfew_provider_desc, review_code_desc, curfew_review_desc, no_work_with_under_age, no_work_with_under_age_of, no_access_to_internet, no_user_of_computer, checksum, default_flag, status_update_reason, status_update_comment, status_update_date, status_update_staff_id) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   GJC          13/04/2006 2.1           Add more columns
   David Ng     03/04/2006 2.0           NOMIS project(10.2.0)
*/
           osc.offender_book_id, osc.sentence_seq, cc.comm_condition_type
          ,cc.comm_condition_code, cc.description cond_description
          ,cc.list_seq
          ,CASE WHEN coalesce(osc.comm_condition_type::text, '') = '' THEN  'N'                    ELSE 'Y' END  apply_flag, osc.start_date cond_start_date
          ,osc.condition_status, osc.status_date cond_status_date
          ,osc.expiry_date cond_expiry_date
          ,osc.long_comment_text cond_comment_text, osc.curfew_start_time
          ,osc.curfew_end_time, osc.condition_recommended_flag
          ,osc.governor_condition_flag, osc.details_text
          ,osc.offender_sent_condition_id, osc.curfew_provider
          ,osc.exclusion_code, osc.residency_address_id
          ,osc.mental_health_provider, osc.alcohol_treatment_provider
          ,osc.attendance_centre, osc.condition_required_flag
          ,osc.condition_applied_flag, osc.appointment_person_name
          ,osc.review_code, osc.supervisor_name, osc.report_time
          ,osc.report_date, osc.personal_relationship_flag
          ,osc.vehicle_details_flag, osc.non_associated_offenders
          ,osc.drug_testing
          ,osc.status_reason_code, no_resident_under_age_of
          ,prohibited_contact, restricted_child_age_of
          ,restricted_approval_person, curfew_tagging_flag, other_program
          ,rf1.description, rf2.description
          ,rf2.description curfew_review_desc, osc.no_work_with_under_age
          ,osc.no_work_with_under_age_of, osc.no_access_to_internet
          ,osc.no_user_of_computer
          ,Tag_Licence_check_sum(coalesce(osc.modify_datetime
                                      ,osc.create_datetime)) checksum
          ,NULL, osc.status_update_reason, osc.status_update_comment
          ,osc.status_update_date, osc.status_update_staff_id
      FROM community_conditions cc, offender_sent_conditions osc
LEFT OUTER JOIN reference_codes rf1 ON (osc.curfew_provider = rf1.code AND 'COND_CURFEW' = rf1.domain)
LEFT OUTER JOIN reference_codes rf2 ON (osc.review_code = rf2.code AND 'COND_REVIEW' = rf2.domain)
WHERE cc.comm_condition_type = osc.comm_condition_type AND cc.comm_condition_code = osc.comm_condition_code AND cc.comm_condition_type = 'LICENCE'
UNION ALL

    SELECT os.offender_book_id, os.sentence_seq, cc.comm_condition_type
          ,cc.comm_condition_code, cc.description, cc.list_seq, 'N'
          ,os.start_date, NULL, NULL, NULL, NULL, NULL, NULL, 'N', 'N', NULL
          ,NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
          ,NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
          ,NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
          ,NULL, cc.default_flag, NULL, NULL, NULL, NULL
      FROM OFFENDER_SENTENCES os, COMMUNITY_CONDITIONS cc
     WHERE cc.active_flag = 'Y'
       AND cc.comm_condition_type = 'LICENCE'
       AND NOT EXISTS (
               SELECT 'X'
                 FROM OFFENDER_SENT_CONDITIONS osc
                WHERE osc.offender_book_id = os.offender_book_id
                  AND osc.sentence_seq = os.sentence_seq
                  AND cc.comm_condition_type = osc.comm_condition_type
                  AND cc.comm_condition_code = osc.comm_condition_code
                  AND cc.comm_condition_type = 'LICENCE')
 
 
;

CREATE OR REPLACE VIEW v_property_header_block (offender_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, ppty_agy_loc_id, booking_no, booking_begin_date, booking_end_date, agy_loc_id, living_unit_id, disclosure_flag, active_flag, booking_status, living_unit_description, in_out_status, status_display, root_offender_id, age, gender, officer, prison_location, off_alerts, status_1, status_2, status_3, movement_reason, off_sup_level) AS SELECT
	/* =========================================================
  Version Number = 2.3.1.4   Date Modified = 06-Mar-2015
  --
  -- MODIFICATION  HISTORY
  -- Person        Date               Version      Comments
  -- ---------     ------------------ ------------ ------------------------------
  --  Jagdeep      06-Mar-2015        2.3.1.4      HPQC#25316 - Removed the table movement_reasons from view.
  --  Patrick      23-NOV-2012        2.3.1.3      Defect 18050. Aligned prison_location as the one in V_HEADER_BLOCK view.
  --  NIKO         04-JAN-2008        2.3.1.2      Modified the property header based on the property header logic
  --                                               Removed the Access_property_flag as it is not required for property
  --  NIKO         21-SEP-2007        2.3.1.1      Added 2 new columns - movement_reason and off_sup_leve
  --
  ========================================================= */
	off_name.offender_id,
	off_name.alias_offender_id,
	off_name.offender_id_display,
	off_name.last_name,
	off_name.first_name,
	off_name.middle_name,
	off_name.suffix,
	off_name.birth_date,
	off_bkg.offender_book_id,
	off_bkg.agy_loc_id ppty_agy_loc_id,
	off_bkg.booking_no,
	off_bkg.booking_begin_date,
	off_bkg.booking_end_date,
	off_bkg.agy_loc_id,
	off_bkg.living_unit_id,
	off_bkg.disclosure_flag,
	off_bkg.active_flag,
	off_bkg.booking_status,
	SUBSTR(tag_header_get_header_location(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.community_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.comm_staff_role, off_bkg.comm_staff_id), 0, 100) living_unit_description,
	off_bkg.in_out_status,
	/* --- Rajshree 15/05/2006
  DECODE ( off_bkg.active_flag,
  'Y', '  ACTIVE',
  'INACTIVE'
  )*/
	SUBSTR(tag_header_get_header_status(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.status_reason, NULL /*mov_rsn.header_status_flag*/
, off_bkg.comm_status, off_bkg.in_out_status, off_bkg.root_offender_id, off_bkg.offender_book_id), 0, 100) status_display,
	off_name.root_offender_id,
	(FLOOR(MONTHS_BETWEEN(date_trunc('day', LOCALTIMESTAMP), date_trunc('day', off_name.birth_date)) / 12)) age,
	SUBSTR(oms_miscellaneous_getdesccode('SEX', off_name.sex_code), 0, 10) gender,
	SUBSTR(tag_header_get_officer(off_bkg.offender_book_id), 0, 105) officer,
	SUBSTR(Tag_Header_get_prison_location(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.intake_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id, off_bkg.offender_book_id), 0, 105) prison_location,
	SUBSTR(omkhead_get_alerts(off_bkg.offender_book_id), 0, 40) off_alerts,
	SUBSTR(tag_header_get_status_1(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status, off_bkg.status_reason), 0, 40) status_1,
	SUBSTR(tag_header_get_status_2(off_bkg.offender_book_id), 0, 40) status_2,
	SUBSTR(tag_header_get_status_3(off_bkg.offender_book_id), 0, 40) status_3,
	(
		SELECT OEM.MOVEMENT_REASON_CODE
		FROM OFFENDER_EXTERNAL_MOVEMENTS OEM
		WHERE OEM.OFFENDER_BOOK_ID = off_bkg.offender_book_id
			AND OEM.MOVEMENT_SEQ = (
				SELECT MAX(MOVEMENT_SEQ)
				FROM OFFENDER_EXTERNAL_MOVEMENTS OEM2
				WHERE OEM2.OFFENDER_BOOK_ID = OEM.OFFENDER_BOOK_ID
				)
			AND EXISTS (
				SELECT 'X'
				FROM MOVEMENT_REASONS MR
				WHERE MR.MOVEMENT_TYPE = OEM.MOVEMENT_TYPE
					AND MR.MOVEMENT_REASON_CODE = OEM.MOVEMENT_REASON_CODE
					AND MR.HEADER_STATUS_FLAG = 'Y'
				)
		) MOVEMENT_REASON,
	Oms_Intake_GET_OFFENDER_SUPERVISION(off_bkg.offender_book_id) off_sup_level
FROM offenders off_name,
	offender_bookings off_bkg,
	staff_members staff,
	staff_accessible_caseloads staff_ac,
	caseloads csld/*,
	movement_reasons mov_rsn*/
         --HPQC#25316 Jagdeep Coomented it out
WHERE off_name.offender_id = off_bkg.offender_id
	AND staff.user_id = upper(USER)
	AND staff_ac.staff_id = staff.staff_id
	AND staff_ac.caseload_id = staff.working_caseload_id
	AND csld.caseload_id = staff_ac.caseload_id
	AND csld.caseload_type = 'INST'
	-- AND csld.access_property_flag = 'Y'
   /*
   AND SUBSTR(off_bkg.status_reason, 1, INSTR(off_bkg.status_reason, '-', 1) - 1) = mov_rsn.movement_type(+)
	AND SUBSTR(off_bkg.status_reason, INSTR(off_bkg.status_reason, '-', 1) + 1) = mov_rsn.movement_reason_code(+)
	*/
     --HPQC#25316 Jagdeep Coomented it out
	AND (
		(
			off_bkg.agy_loc_id IN (
				SELECT cal.agy_loc_id
				FROM caseload_agency_locations cal
				WHERE cal.caseload_id = staff.working_caseload_id
					AND cal.agy_loc_id NOT IN (
						'OUT',
						'TRN'
						)
				)
			AND off_bkg.active_flag = 'Y'
			)
		OR (
			off_bkg.offender_book_id IN (
				SELECT opi2.offender_book_id
				FROM offender_ppty_items opi2,
					offender_bookings off_bkg2
				WHERE opi2.offender_book_id = off_bkg2.offender_book_id
					AND off_bkg2.offender_book_id = off_bkg.offender_book_id
					AND off_bkg2.active_flag = 'Y'
					AND opi2.agy_loc_id IN (
						SELECT cal2.agy_loc_id
						FROM caseload_agency_locations cal2
						WHERE cal2.caseload_id = staff.working_caseload_id
							AND cal2.agy_loc_id NOT IN ('TRN')
						)
				)
			)
		OR (
			off_bkg.offender_book_id = (
				SELECT MAX(opi3.offender_book_id)
				FROM offender_ppty_items opi3,
					offender_bookings off_bkg3
				WHERE opi3.offender_book_id = off_bkg3.offender_book_id
               AND off_bkg3.root_offender_id = off_bkg.root_offender_id
               AND opi3.agy_loc_id IN (
                  SELECT cal3.agy_loc_id
                  FROM caseload_agency_locations cal3
                  WHERE cal3.caseload_id = staff.working_caseload_id
                     AND cal3.agy_loc_id NOT IN ('TRN')
                  )
               AND NOT EXISTS (
                  SELECT NULL
                  FROM offender_bookings off_bkg4
                  WHERE off_bkg4.root_offender_id = off_bkg3.root_offender_id
                     AND active_flag = 'Y'
                  )
            )
         )
      OR (
         off_bkg.offender_book_id IN (
            SELECT off_bkg5.offender_book_id
            FROM offender_bookings off_bkg5
            WHERE off_bkg5.root_offender_id = off_bkg.root_offender_id
               AND off_bkg5.active_flag = 'N'
            )
         )
      )
;

CREATE OR REPLACE VIEW v_module_accessible_doc (module_name, template_code, create_user_id, modify_user_id, active_flag, expiry_date) AS select MODULE_NAME,
         TEMPLATE_CODE,
         CREATE_USER_ID,
         MODIFY_USER_ID,
         ACTIVE_FLAG,
         EXPIRY_DATE
    FROM MODULE_ACCESSIBLE_DOCUMENTS


 
;

CREATE OR REPLACE VIEW v_offender_inst_responses (off_inst_response_id, off_inst_req_id, response_type, response_date, response_authority_code, disposition_code, authrized_by, refered_to, offender_book_id, response_auth_member_id, response_auth_time_id, response_auth_loc_id, in_person_flag, response_comment, response_status_code, response_status, result_code, result_reason_code, effective_date, general_comment, confidatial_comment, last_change_flag, create_date, modify_user_id, schedule_id) AS SELECT /*+ FIRST_ROWS */OIRS.OFF_INST_RESPONSE_ID
,OIRS.OFF_INST_REQ_ID
,OIRS.RESPONSE_TYPE
,OIRS.RESPONSE_DATE
,OIRS.RESPONSE_AUTHORITY_CODE
,OIRS.DISPOSITION_CODE
,OIRS.AUTHRIZED_BY
,OIRS.REFERED_TO
,OIRQ.OFFENDER_BOOKING_ID
,OIRS.RESPONSE_AUTH_MEMBER_ID
,OIRS.RESPONSE_AUTH_TIME_ID
,OIRS.RESPONSE_AUTH_LOC_ID
,OIRS.IN_PERSON_FLAG
,OIRS.RESPONSE_COMMENT
,OIRS.RESPONSE_STATUS_CODE
,OIRS.RESPONSE_STATUS
,OIRS.RESULT_CODE
,OIRS.RESULT_REASON_CODE
,OIRS.EFFECTIVE_DATE
,OIRS.GENERAL_COMMENT
,OIRS.CONFIDATIAL_COMMENT
,OIRS.LAST_CHANGE_FLAG
,OIRS.CREATE_DATE
,OIRS.MODIFY_USER_ID
,OIRS.SCHEDULE_ID
FROM   OFFENDER_INST_RESPONSES OIRS,
 OFFENDER_INST_REQUESTS  OIRQ
WHERE   OIRS.RESPONSE_TYPE             = 'REVIEW'
       AND     OIRQ.REQUEST_TYPE        = 'RLRV'
       AND     OIRS.OFF_INST_REQ_ID     = OIRQ.OFF_INST_REQ_ID
       AND     OIRQ.OFFENDER_BOOKING_ID > 0

 
;

CREATE OR REPLACE VIEW v_route_flow (route_name, leg_id, leg_seq, agy_loc_id, from_ovnt_flag, to_dtl, to_agy, count_flag, ovnt_flag) AS SELECT  route_name, leg_id, leg_seq, agy_loc_id, from_ovnt_flag, to_dtl,
	substr(to_dtl,1, position('-' in to_dtl)-1) to_agy,
	substr(to_dtl, position('-' in to_dtl)+1, 1) count_flag,
	substr(to_dtl, INSTR(to_dtl,'-',1,2)+1, 1) ovnt_flag
FROM (
	SELECT route_name, leg_id, leg_seq, agy_loc_id,
		overnight_flag from_ovnt_flag,
		(SELECT agy_loc_id || '-' || count_flag || '-' || overnight_flag
		   FROM route_stop_details
		  WHERE route_name = rsd.route_name
		    AND leg_id * 1000 + leg_seq > rsd.leg_id * 1000 + rsd.leg_seq
		    AND active_flag = 'Y'   LIMIT 1) to_dtl
 	  FROM route_stop_details rsd
	 WHERE active_flag = 'Y' ) alias7

;

CREATE OR REPLACE VIEW v_offender_act_attendances (event_outcome_description, performance_description, full_name, offender_id_display, piece_work, event_class, event_id, event_date, start_time, end_time, event_sub_type, event_status, event_outcome, event_type, comment_text, to_internal_location_id, crs_sch_id, in_time, out_time, performance_code, crs_acty_id, agy_loc_id) AS SELECT
/*
============================================================
  Version Number = 1.1          Date Modified = 18/03/2006
============================================================
MODIFICATION HISTORY
     Person   Date          Version   Comments
     GJC      18-Mar-2006   1.1       Initial version
*/
 rc1.description event_outcome_description
,rc2.description performamce_description
,o.last_name ||','|| o.first_name full_name
,o.offender_id_display
,ocA.piece_work
,ocA.event_class
,ocA.event_id
,ocA.event_date
,ocA.start_time
,ocA.end_time
,ocA.event_sub_type
,ocA.event_status
,ocA.event_outcome
,ocA.event_type
,ocA.comment_text
,ocA.to_internal_location_id
,ocA.crs_sch_id
,ocA.in_time
,ocA.out_time
,ocA.performance_code
,ocA.crs_acty_id
,ocA.agy_loc_id
FROM offender_bookings ob, offenders o, offender_course_attendances oca
LEFT OUTER JOIN reference_codes rc1 ON (ocA.event_outcome = rc1.code AND 'PS_PA_OC' = rc1.domain)
LEFT OUTER JOIN reference_codes rc2 ON (ocA.performance_code = rc2.code AND 'PS_ACT_PERF' = rc2.domain)
WHERE ob.offender_id = o.offender_id AND ob.active_flag = 'Y' AND ob.offender_book_id = ocA.offender_book_id

 
;

CREATE OR REPLACE VIEW v_hot_page_requirement (offender_book_id, course_activity_desc, start_date, end_date, location, program_id) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   Erin         01/09/2006 2.5          Change UNION query to DECODE
   Erin         01/09/2006 2.4          Fix version number
   GJC          29/08/2006 2.3          Rename column TYPE to COURSE_ACTIVITY_DESC
   Erin         21/08/2006 2.2          Change SQL structure
   Erin         17/08/2006 2.1          Added PROGRAM_ID column
   David Ng     10/08/2006 2.0          NOMIS project(10.2.0)
*/
          opp.offender_book_id, ca.description course_activity_desc,
          opp.offender_start_date start_date, opp.offender_end_date end_date,
          CASE WHEN coalesce(ca.agy_loc_id::text, '') = '' THEN (SELECT c.corporate_name                                         FROM corporates c                                        WHERE ca.placement_corporate_id = c.corporate_id)                            	       ELSE (SELECT al.description                                         FROM agency_locations al                                        WHERE ca.agy_loc_id = al.agy_loc_id) END  LOCATION1,
          opp.program_id
     FROM course_activities ca, offender_program_profiles opp
    WHERE ca.crs_acty_id = opp.crs_acty_id
      AND opp.offender_program_status = 'ALLOC'

;

CREATE OR REPLACE VIEW staff_members_v2 (staff_name, user_id, last_name, first_name, birthdate, cal_agy_loc_id, sac_staff_id, from_date, position, role, date_to, schedule_type, hours_per_week, suspended_flag) AS SELECT
 /* =========================================================
   Version Number = 10.2.1.1  Date Modified = 19-Apr-2011
   =========================================================
   MODIFICATION HISTORY
   Author      Date        Version     Description
   ---------  ----------  ----------  ------------------------------
   Sarah     19-Apr-2011   10.2.1.1    D#5992: added suspended_flag to the view
   Sowmya    04-Apr-2002   6.1.0.0     Tr#11590:Added columns Last_name,First_name, Birthdate,Schedule_type and hours_per_week
*/
     sm.LAST_NAME||', '||sm.FIRST_NAME
    ,sm.USER_ID
    ,sm.LAST_NAME
    ,sm.FIRST_NAME
    ,sm.BIRTHDATE
    ,slr.CAL_AGY_LOC_ID
    ,slr.SAC_STAFF_ID
    ,slr.FROM_DATE
    ,slr.POSITION
    ,slr.ROLE
    ,slr.TO_DATE
    ,slr.SCHEDULE_TYPE
    ,slr.HOURS_PER_WEEK
    ,sm.SUSPENDED_FLAG
FROM
    staff_location_roles  slr,
    staff_members  sm
 WHERE slr.SAC_STAFF_ID = sm.STAFF_ID

;

CREATE OR REPLACE VIEW v_offender_visit_details (offender_visit_id, offender_id_display, offender_book_id, last_name, first_name, living_unit_desc, visit_status, visit_date, start_time, end_time, internal_location_desc, visit_type) AS SELECT
/*==========================================================
Version Number = 2.1    Date Modified = 17/07/2006
==========================================================*/
/*MODIFICATION HISTORY
     Person      Date           Version         Comments
     ---------   ------------   -------------   ------------------------------
	 Erin        17/07/2006		2.1				Visits datamodel changes
     GJC         28/02/2006     1.0             Initial Draft.
*/
   off_vis.offender_visit_id, OFF.offender_id_display,
   off_bkg.offender_book_id, OFF.last_name, OFF.first_name,
   SUBSTR(tag_header_get_header_location(off_bkg.active_flag,
                                              off_bkg.community_active_flag,
                                              off_bkg.community_agy_loc_id,
                                              off_bkg.agy_loc_id,
                                              off_bkg.living_unit_id,
                                              off_bkg.comm_staff_role,
                                              off_bkg.comm_staff_id
                                             ),
          0,
          100
          ) living_unit_description,
   SUBSTR(oms_miscellaneous_getdesccode('VIS_COMPLETE', off_vis.Visit_status),
          1,
          40
         ) visit_status,
   off_vis.visit_date, off_vis.start_time, off_vis.end_time,
   ail.description,
   SUBSTR(oms_miscellaneous_getdesccode('VISIT_TYPE', off_vis.Visit_Type),
          1,
          40
          ) VISIT_TYPE
     FROM offenders OFF,
          offender_bookings off_bkg,
          offender_visits off_vis,
          agency_internal_locations ail
    WHERE OFF.offender_id = off_bkg.offender_id
      AND off_bkg.offender_book_id = off_vis.offender_book_id
      AND ail.internal_location_id = off_vis.visit_internal_location_id
      AND (off_vis.visit_date IS NOT NULL AND off_vis.visit_date::text <> '')

 
;

CREATE OR REPLACE VIEW v_oiusstri (scheduled_trip_id, departure_date, est_departure_time, act_departure_time, est_comp_time, route_name, description, status) AS SELECT
       st.scheduled_trip_id
       ,st.departure_date
       ,st.est_departure_time
       ,st.act_departure_time
       ,CASE WHEN tag_utils_get_sys_profile('CLIENT','SEGMENT_TYPE')='Y' THEN st.est_completion_time  ELSE NULL END  est_comp_time
       ,st.route_name
       ,t.description
       ,TAG_UTILS_get_ref_descp('SCH_TRIP_ST',st.STATUS) STATUS
  FROM scheduled_trips st
       ,trips t
 where t.trip_type = 'LOCAL'
   AND st.trip_code = t.trip_code
   AND st.cancel_flag = 'N'
   AND t.active_flag = 'Y'

;

CREATE OR REPLACE VIEW staff_work_assignments_v1 (staff_id, staff_name, offender_id_display, offender_id, offender_name, last_name, first_name, book_id, line, order_type, order_code, description, component, agy_loc_id, order_req_expiry, sup_exp_rpt_due, view_order, cal_agy_loc_id, sac_staff_id, position, role, from_date, offender_book_id, sentence_seq, offender_book_id_request, charge_seq, request_seq, status, offass_id) AS SELECT/*==========================================================
  	    Version Number = 2.1	Date Modified = 24/01/2006
  	    ==========================================================*/
	 /*MODIFICATION HISTORY
   	  Person      Date             Version        Comments
   	  Surya       24-Jan-2006      2.1	       Modified to make the view get validated, as
					               it was invalidated because of the new sentences
 						       and Requirements data model changes.
	 */
      SM.STAFF_ID
     , SM.LAST_NAME||', '||SM.FIRST_NAME
     , OFF.OFFENDER_ID_DISPLAY
     , OFF.OFFENDER_ID
     , OFF.LAST_NAME||', '||OFF.FIRST_NAME
      ,OFF.LAST_NAME
      ,OFF.FIRST_NAME
     , OFF_SENT.OFFENDER_BOOK_ID
     , OFF_SENT.SENTENCE_SEQ
     ,null ORDER_TYPE
     ,null ORDER_CODE
     , POT.DESCRIPTION
     , OFFASS.COMPONENT
     , OFFASS.AGY_LOC_ID
     ,NULL SENTENCE_EXPIRY_DATE
     ,NULL SUPERVISION_EXPIRY_DATE
     ,NULL view_order
     , OFFASS.CAL_AGY_LOC_ID
     , OFFASS.SAC_STAFF_ID
     , OFFASS.POSITION
     , OFFASS.ROLE
     , OFFASS.FROM_DATE
     , OFFASS.OFFENDER_BOOK_ID
     , OFFASS.SENTENCE_SEQ
     , OFFASS.OFFENDER_BOOK_ID_REQUEST
     , OFFASS.CHARGE_SEQ
     , OFFASS.REQUEST_SEQ
     , OFFASS.STATUS
     , OFFASS.OFFASS_ID
 FROM probation_order_types pot, offender_sentences off_sent, offenders off, offender_bookings ob, offender_work_assignments offass
LEFT OUTER JOIN staff_members sm ON (OFFASS.SAC_STAFF_ID = SM.STAFF_ID)
WHERE OFFASS.OFFENDER_BOOK_ID   = OFF_SENT.OFFENDER_BOOK_ID AND OFFASS.SENTENCE_SEQ       = OFF_SENT.SENTENCE_SEQ AND OFF_SENT.OFFENDER_BOOK_ID = OB.OFFENDER_BOOK_ID AND OB.OFFENDER_ID            = OFF.OFFENDER_ID AND OFF_SENT.SENTENCE_STATUS != 'D'

UNION

  SELECT SM.STAFF_ID
        ,SM.LAST_NAME||', '||SM.FIRST_NAME
        ,OFF.OFFENDER_ID_DISPLAY
       , OFF.OFFENDER_ID
        ,OFF.LAST_NAME||', '||OFF.FIRST_NAME
        ,OFF.LAST_NAME
        ,OFF.FIRST_NAME
        ,OFF_REQ.OFFENDER_BOOK_ID
        ,OFF_REQ.REQUEST_SEQ
        ,OFF_REQ.ORDER_TYPE
        ,OFF_REQ.ORDER_CODE
        ,POT.DESCRIPTION
        ,OFFASS.COMPONENT
        ,OFFASS.AGY_LOC_ID
        ,OFF_REQ.SENTENCE_EXPIRY_DATE
        ,OFF_REQ.REPORT_DUE_DATE
        ,LEAST(coalesce(OFF_REQ.SENTENCE_EXPIRY_DATE
                              , OFF_REQ.REPORT_DUE_DATE)
                      ,coalesce(OFF_REQ.REPORT_DUE_DATE
                              ,OFF_REQ.SENTENCE_EXPIRY_DATE))
       , OFFASS.CAL_AGY_LOC_ID
       , OFFASS.SAC_STAFF_ID
       , OFFASS.POSITION
       , OFFASS.ROLE
       , OFFASS.FROM_DATE
       , OFFASS.OFFENDER_BOOK_ID
       , OFFASS.SENTENCE_SEQ
       , OFFASS.OFFENDER_BOOK_ID_REQUEST
       , OFFASS.CHARGE_SEQ
       , OFFASS.REQUEST_SEQ
       , OFFASS.STATUS
       , OFFASS.OFFASS_ID
  FROM probation_order_types pot, offender_requests off_req, offenders off, offender_bookings ob, offender_work_assignments offass
LEFT OUTER JOIN staff_members sm ON (OFFASS.SAC_STAFF_ID = SM.STAFF_ID)
WHERE OFFASS.OFFENDER_BOOK_ID_REQUEST   = OFF_REQ.OFFENDER_BOOK_ID AND OFFASS.REQUEST_SEQ                = OFF_REQ.REQUEST_SEQ AND OFFASS.CHARGE_SEQ                 = OFF_REQ.CHARGE_SEQ AND OFF_REQ.ORDER_TYPE                = POT.ORDER_TYPE AND OFF_REQ.ORDER_CODE                = POT.ORDER_CODE AND OFF_REQ.OFFENDER_BOOK_ID          = OB.OFFENDER_BOOK_ID AND OB.OFFENDER_ID                    = OFF.OFFENDER_ID AND OFF_REQ.REQUEST_STATUS != 'D'
 
 
;

CREATE OR REPLACE VIEW offender_address_v (root_offender_id, offender_id, first_name, middle_name, last_name, offender_id_display, address_id, address_type, owner_class, owner_id, owner_code, owner_seq, suite_number, street_number, street, street_direction, zip_postal_code, city_code, prov_state_code, country_code, city_desc, prov_state_desc, country_desc, mail_care_of, mail_flag, primary_flag) AS SELECT DISTINCT o.root_offender_id,
								o.offender_id,
								o.first_name,
								o.middle_name,
								o.last_name,
								o.offender_id_display,
                a.address_id,
                a.address_type,
                a.owner_class,
                a.owner_id,
                a.owner_code,
                a.owner_seq,
                a.suite_number,
                a.street_number,
                a.street,
                a.street_direction,
                a.zip_postal_code,
                a.city_code,
                a.prov_state_code,
                a.country_code,
                (SELECT description
                   FROM reference_codes
                  WHERE domain = 'CITY'
                    AND code = a.city_code) city_desc,
                (SELECT description
                   FROM reference_codes
                  WHERE domain = 'PROV_STATE'
                    AND code = a.prov_state_code) prov_state_desc,
                (SELECT description
                   FROM reference_codes
                  WHERE domain = 'COUNTRY'
                    AND code = a.country_code) country_desc,
                a.mail_care_of,
								a.mail_flag,
								a.primary_flag
  FROM offenders o
LEFT OUTER JOIN (SELECT *
          FROM addresses a
         WHERE a.owner_class = 'OFF'
           AND ((a.primary_flag = 'Y' AND coalesce(a.end_date::text, '') = '') OR (a.mail_flag = 'Y' AND coalesce(a.end_date::text, '') = '' AND
               a.primary_flag <> 'Y'))) a ON (o.root_offender_id = a.owner_id) ORDER BY primary_flag

 
;

CREATE OR REPLACE VIEW corporate_address_v (corporate_id, corporate_name, caseload_id, contact_person_name, suspended_date, suspended_flag, address_id, address_type, owner_class, owner_id, owner_code, owner_seq, suite_number, street_number, street, street_direction, zip_postal_code, city_code, prov_state_code, country_code, city_desc, prov_state_desc, country_desc, mail_care_of, primary_flag, mail_flag) AS SELECT
/* MODIFICATION HISTORY
   Person       Date       Version       Comments
   ---------    ------     ---------    ------------------------------
   Johnny      22/04/2008  1.4           Added 2 new columns mail_flag and primary_flag
   Niko        27/10/2007  1.3           Added a new column - MAIL_CARE_OF
   Niko        17/10/2007  1.2           Modified the domain - PROV_STATE
   Niko        17/10/2007  1.1           Modified the inline view
*/
DISTINCT c.corporate_id
, c.corporate_name
, c.caseload_id
, c.contact_person_name
, c.suspended_date
, c.suspended_flag
, a.address_id
, a.address_type
, a.owner_class
, a.owner_id
, a.owner_code
, a.owner_seq
, a.suite_number
, a.street_number
, a.street
, a.street_direction
, a.zip_postal_code
, a.city_code
, a.prov_state_code
, a.country_code
, (SELECT description
     FROM reference_codes
    WHERE domain = 'CITY' AND code = a.city_code) city_desc
, (SELECT description
     FROM reference_codes
    WHERE domain = 'PROV_STATE'
      AND code = a.prov_state_code) prov_state_desc
, (SELECT description
     FROM reference_codes
    WHERE domain = 'COUNTRY'
      AND code = a.country_code) country_desc
, a.mail_care_of
, a.primary_flag
, a.mail_flag
FROM corporates c
LEFT OUTER JOIN (SELECT *
        FROM addresses a
       WHERE a.owner_class = 'CORP'
         AND (   (a.primary_flag = 'Y' AND coalesce(a.end_date::text, '') = '')
                    OR (    a.mail_flag = 'Y'
                            AND coalesce(a.end_date::text, '') = ''
                        AND a.primary_flag <> 'Y'
                    )
       )) a ON (c.corporate_id = a.owner_id);

CREATE OR REPLACE VIEW v_qm_pc (process_id, name, name_desc, description, execution_type, location_type, active_flag, expiry_date, event_type, composition_id, managing_agy_loc_id, event_type_agy_loc_id, qmc_active_flag, qmc_expiry_date, qmc_event_type) AS SELECT
/* MODIFICATION HISTORY
   Person       Date         Version       Comments
   ---------    ------       ---------     ------------------------------
   Steve        12-Jan-2010  1.1           Call tag_reference_codes for NAME_DESC
   Niko         15/12/2009   1.0           Created
*/
       qmp.process_id
      ,qmp.name
      ,tag_reference_codes_getdesccode('QM_PROCESS', qmp.name) NAME_DESC
      ,qmp.description
      ,qmp.execution_type
      ,qmp.location_type
      ,qmp.active_flag
      ,qmp.expiry_date
      ,qmp.event_type
      ,qmc.composition_id
      ,qmc.managing_agy_loc_id
      ,qmc.event_type_agy_loc_id
      ,qmc.active_flag qmc_active_flag
      ,qmc.expiry_date qmc_expiry_date
      ,qmc.event_type qmc_event_type
FROM  qm_processes qmp, qm_compositions qmc
where qmp.process_id = qmc.process_id


;
CREATE OR REPLACE VIEW v_off_inter_mvmt_locations (offender_id_display, last_name, first_name, movement_date, movement_time, comment_text, agency_location_id, agency_iml_id) AS SELECT o.offender_id_display,
       o.last_name,
       o.first_name,
       oiml.movement_date,
       oiml.movement_time,
       oiml.comment_text,
       oiml.agency_location_id,
       ob.agency_iml_id
  FROM offenders o,
       offender_bookings ob,
       offender_inter_mvmt_locations oiml
 WHERE o.root_offender_id = ob.root_offender_id
   AND o.offender_id      = ob.offender_id
   AND ob.active_flag     = 'Y'
   AND oiml.offender_book_id = ob.offender_book_id
   AND ob.agency_iml_id         = oiml.agency_iml_id
   AND oiml.offender_iml_id     = (SELECT MAX(oiml2.offender_iml_id)
                                     FROM offender_inter_mvmt_locations oiml2
                                    WHERE oiml.offender_book_id = oiml2.offender_book_id)

 
;

CREATE OR REPLACE VIEW v_off_exm (offender_book_id, movement_seq, movement_date, movement_time, internal_schedule_type, internal_schedule_reason_code, movement_type, movement_reason_code, direction_code, arrest_agency_loc_id, to_prov_stat_code, escort_code, from_agy_loc_id, to_agy_loc_id, active_flag, escort_text, comment_text, reporting_date, to_city, from_city, reporting_time) AS SELECT
                                   /* ========================================================
                                      VIEW: CASELOAD_CURRENT_ACCOUNTS
                                      Version Number = 4.11.0.0  Date Modified = 31/05/2001
                                    ========================================================*/
                                  OFFENDER_BOOK_ID
                                , MOVEMENT_SEQ
                                , MOVEMENT_DATE
                                , MOVEMENT_TIME
                                , INTERNAL_SCHEDULE_TYPE
                                , INTERNAL_SCHEDULE_REASON_CODE
                                , MOVEMENT_TYPE
                                , MOVEMENT_REASON_CODE
                                , DIRECTION_CODE
                                , ARREST_AGENCY_LOC_ID
                                , TO_PROV_STAT_CODE
                                , ESCORT_CODE
                                , FROM_AGY_LOC_ID
                                , TO_AGY_LOC_ID
                                ,ACTIVE_FLAG
                                ,ESCORT_TEXT
                                ,COMMENT_TEXT
                                ,REPORTING_DATE
                                ,TO_CITY
                                ,FROM_CITY
                                ,REPORTING_TIME
                           FROM OFFENDER_EXTERNAL_MOVEMENTS

 
;

CREATE OR REPLACE VIEW v_ext_ownership_transfer (offender_id_display, offender_last_name, offender_first_name, ass_staff_id, staff_last_name, staff_first_name, agy_loc_id_to, offender_book_id, ext_transfer_id, agy_loc_id_from, transfer_date, transfer_flag) AS SELECT
/* =========================================================
   Version Number = 4.12.0.0  Date Modified = 05/31/2002
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date       	Version     	 Comments
   ---------     ------     	------------  	 ------------------------------
   Sowmya        05/31/2002     4.12.0.0         Tr#12640:Created new view to be used in OCITTPOW and OCRORASS modules.
*/
       OFF.OFFENDER_ID_DISPLAY,
       OFF.LAST_NAME OFFENDER_LAST_NAME,
       OFF.FIRST_NAME OFFENDER_FIRST_NAME,
       ET.ASS_STAFF_ID,
       SM.LAST_NAME STAFF_LAST_NAME,
       SM.FIRST_NAME STAFF_FIRST_NAME,
       ET.AGY_LOC_ID_TO,
       ET.OFFENDER_BOOK_ID,
       ET.EXT_TRANSFER_ID,
       ET.AGY_LOC_ID_FROM,
       ET.TRANSFER_DATE,
       ET.TRANSFER_FLAG
  FROM EXT_OWNERSHIP_TRANSFER ET, OFFENDERS OFF, OFFENDER_BOOKINGS OB, STAFF_MEMBERS SM
 WHERE OFF.OFFENDER_ID = OB.OFFENDER_ID
   AND OB.OFFENDER_BOOK_ID = ET.OFFENDER_BOOK_ID
   AND ET.ASS_STAFF_ID = SM.STAFF_ID
   AND ET.TRANSFER_FLAG = 'N'

 
;

CREATE OR REPLACE VIEW v_officer_details (staff_id, name, sex_code, cal_agy_loc_id, position, role, schedule_type, hours_per_week, skill_type, skill_sub_type, from_date, supervisor_staff_id, supervisor_position, supervisor_role, supervisor_from_date, supervisor_agy_loc_id) AS SELECT
/* =========================================================
   Version Number = 6.1.0.0  Date Modified = 12/20/2001
   ========================================================= */
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   ---------   ------      ------------  ------------------------------
   KenM        12/07/2000  4.11.0.0      added termination date in where clause
   Snezana     02/01/2001  4.11.0.1      slr.to_date IS NULL condition taken out from where clause
   Snezana     12/20/2001  6.1.0.0       do not retrieve roles that are no longer active
*/
       sm.staff_id,
       sm.last_name || ', ' || sm.first_name,
       sm.sex_code,
       slr.cal_agy_loc_id,
       slr.position,
       slr.role,
       slr.schedule_type,
       slr.hours_per_week,
       ss.skill_type,
       ss.sub_type,
       slr.from_date,
       slr.supervisor_staff_id,
       slr.supervisor_position,
       slr.supervisor_role,
       slr.supervisor_from_date,
       slr.supervisor_agy_loc_id
  FROM staff_location_roles slr, staff_members sm
LEFT OUTER JOIN staff_skills ss ON (sm.staff_id = ss.staff_id)
WHERE slr.sac_staff_id = sm.staff_id  AND coalesce(slr.to_date::text, '') = '' AND coalesce(sm.termination_date::text, '') = ''

 
;

CREATE OR REPLACE VIEW v_program_providers (party_id, party_code, party_name, party_class, last_name, first_name) AS (SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     03/01/2006 2.0           NOMIS project(10.2.0)
*/
           person_id, NULL, last_name || ', ' || first_name, 'PER', last_name,
           first_name
      FROM persons

UNION ALL

    SELECT staff_id, NULL, last_name || ', ' || first_name, 'STF', last_name,
           first_name
      FROM staff_members
    
UNION ALL

    SELECT NULL, agy_loc_id, description, 'AGY', NULL, NULL
      FROM agency_locations
     WHERE agy_loc_id NOT IN ('OUT', 'TRN')
    
UNION ALL

    SELECT t.team_id, t.team_code, t.description, 'TEAM', NULL, NULL
      FROM teams t
     WHERE EXISTS (
              SELECT 'x'
                FROM staff_members sm, team_members tm
               WHERE tm.active_flag = 'Y'
                 AND sm.user_id = USER
                 AND sm.staff_id = tm.staff_id
                 AND t.team_id = tm.team_id)
    
UNION ALL

    SELECT corporate_id, NULL, corporate_name, 'CORP', NULL, NULL
      FROM corporates)
 
 
;

CREATE OR REPLACE VIEW v_external_moves (off_name, last_name, first_name, offender_id_display, root_offender_id, offender_id, sex_code, race_code, birth_date, offender_book_id, living_unit_id, movement_seq, detail_seq, from_agy_loc_id, to_agy_loc_id, movement_type, movement_reason, event_date, move_by_date, move_allow_date, comment_text, inm_comment, ssn, scheduled_trip_id, recorded_date, status_code, txn_status, curr_agy_id, priority_code, alternate_agy_loc_id, tmp_group_id) AS SELECT 	ofd.last_name || ', ' || ofd.first_name off_name,
		ofd.last_name,
		ofd.first_name,
		ofd.offender_id_display,
		ofd.root_offender_id,
		ofd.offender_id,
		ofd.sex_code,
		(select z.race_code
      FROM offenders z
     WHERE z.offender_id = ofd.root_offender_id) race_code,
		ofd.birth_date,
	    obk.offender_book_id,
		obk.living_unit_id,
		opm.movement_seq,
		omd.detail_seq,
		opm.from_agy_loc_id,
		opm.to_agy_loc_id,
		opm.movement_type,
		opm.movement_reason,
		opm.event_date,
		opm.move_by_date,
		opm.move_allow_date,
		opm.comment_text,
		NULL inm_comment,
		NULL ssn,
		opm.scheduled_trip_id,
		omd.recorded_date,
		omd.status_code,
		omd.txn_status,
		obk.agy_loc_id curr_agy_id,
		opm.priority_code,
		opm.alternate_agy_loc_id,
		opm.tmp_group_id
  FROM 	offenders ofd,
       	offender_bookings obk,
       	offender_proposed_mvmnts opm,
		offender_movement_details omd
 WHERE 	ofd.offender_id = obk.offender_id
   AND 	obk.offender_book_id = opm.offender_book_id
   AND  opm.offender_book_id = omd.offender_book_id
   AND  opm.movement_seq     = omd.movement_seq

UNION

SELECT  last_name || ', ' || first_name,
		last_name,
		first_name,
		NULL,
		nadm.non_adm_inmate_id,
		nadm.non_adm_inmate_id,
		sex_code,
		race_code,
		birth_date,
		NULL,
		NULL,
		NULL,
		nadmdt.detail_seq,
		from_agy_loc_id,
		to_agy_loc_id,
		'ADM',
		mvmt_reason_code,
		NULL,
		NULL,
		NULL,
		movement_comment_text,
		nadm.inmate_comment_text,
		ssn_id,
		nadm.scheduled_trip_id,
		nadmdt.recorded_date,
		nadmdt.status_code,
		nadmdt.txn_status,
		from_agy_loc_id, NULL,
		nadm.alternate_agy_loc_id,
		nadm.tmp_group_id
  FROM  non_admitted_inmate_mvmts nadm,
 		non_adm_inm_mvmt_details  nadmdt
 WHERE  nadm.non_adm_inmate_id = nadmdt.non_adm_inmate_id
 ORDER BY last_name DESC, first_name
 
;

CREATE OR REPLACE VIEW v_team_members (team_id, staff_id, last_name, first_name, staff_name, sex_code, loc_role_from_date, position, role, team_member_id, agy_loc_id, active_flag, expiry_date, schedule_type, hours_per_week, team_code, description, function_type) AS SELECT
/* =========================================================
   Version Number = 2.0  Date Modified = 14/03/2006
   ========================================================= */
/* MODIFICATION HISTORY
|| Person      Date         Version       Comments
|| ---------   -----------  ------------  ------------------------------------
|| David Ng    14/03/2006   2.0           For OM Staff/Team Assignment
|| ===========================================================================
*/
TM.TEAM_ID
,TM.STAFF_ID
,SM.Last_Name
,SM.First_Name
,SM.Last_Name||', '||SM.First_Name
,SM.SEX_CODE
,TM.LOC_ROLE_FROM_DATE
,TM.POSITION
,TM.ROLE
,TM.TEAM_MEMBER_ID
,TM.AGY_LOC_ID
,TM.ACTIVE_FLAG
,TM.EXPIRY_DATE
,SLR.SCHEDULE_TYPE
,SLR.HOURS_PER_WEEK
,T.TEAM_CODE
,T.DESCRIPTION
,TF.FUNCTION_TYPE
FROM TEAM_MEMBERS TM, TEAMS T, TEAM_FUNCTIONS TF, STAFF_Members SM,
     STAFF_LOCATION_ROLES SLR
WHERE TM.Team_ID = T.Team_ID
AND   TF.Team_ID = T.Team_ID
AND   TM.Staff_ID = SM.Staff_ID
AND   TM.Active_Flag = 'Y'
AND   TM.Staff_ID           = SLR.sac_staff_id
AND   TM.agy_loc_id         = SLR.cal_agy_loc_id
AND   TM.loc_role_from_date = SLR.from_date
AND   TM.ROLE               = SLR.role
AND   TM.position           = SLR.position

 
;

CREATE OR REPLACE VIEW v_offender_book (offender_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, booking_no, booking_begin_date, booking_end_date, agy_loc_id, active_flag) AS SELECT
     off_name.OFFENDER_ID
    ,off_name.ALIAS_OFFENDER_ID
    ,off_name.OFFENDER_ID_DISPLAY
    ,off_name.LAST_NAME
    ,off_name.FIRST_NAME
    ,off_name.MIDDLE_NAME
    ,off_name.SUFFIX
    ,off_name.BIRTH_DATE
    ,off_bkg.OFFENDER_BOOK_ID
    ,off_bkg.BOOKING_NO
    ,off_bkg.BOOKING_BEGIN_DATE
    ,off_bkg.BOOKING_END_DATE
    ,off_bkg.AGY_LOC_ID
    ,off_bkg.ACTIVE_FLAG
FROM
    offenders  off_name,
    offender_bookings  off_bkg
 WHERE off_name.offender_id = off_bkg.offender_id

 
;

CREATE OR REPLACE VIEW v_offender_restrictions (offender_book_id, offender_restriction_id, effective_date, expiry_date, restriction_type, restriction_desc, entered_staff_id, entered_staff_name, authorised_staff_id, authroised_staff_name, comment_text) AS SELECT
/*==========================================================
  Version Number = 1.0  Date Modified = 19/01/2005
  ==========================================================*/
/*MODIFICATION HISTORY
   Person      Date           Version         Comments
   Surya       19-Jan-2005 1.0      Initial Draft.
   David Ng    13-Feb-2006      2.0             rework
*/
            off_rest.offender_book_id, off_rest.offender_restriction_id,
            off_rest.effective_date, off_rest.expiry_date, ref_code.code,
            ref_code.description, off_rest.entered_staff_id,
            SUBSTR(oms_miscellaneous_staff_name(off_rest.entered_staff_id),
                    1,
                    80
                   ),
            off_rest.authorised_staff_id,
            SUBSTR(oms_miscellaneous_staff_name(off_rest.authorised_staff_id),
                   1,
                   80
                  ),
            off_rest.comment_text
       FROM offender_restrictions off_rest, reference_codes ref_code
      WHERE off_rest.restriction_type = ref_code.code
        AND ref_code.domain = 'VST_RST_TYPE'
        AND ref_code.parent_code IN ('BAN', 'RESTRICTION')
   ORDER BY off_rest.offender_book_id DESC,
            off_rest.effective_date DESC,
            off_rest.expiry_date DESC

 
;

CREATE OR REPLACE VIEW v_distinct_linked_offenders (offender_id_display, last_name, first_name, middle_name, contact_type, contact_type_description, relationship_type, relationship_type_description, person_id, root_offender_id) AS SELECT DISTINCT
/*
      ============================================================
      Version Number = 1.0 ONDOC Date Modified = 10-JUN-2013
      ============================================================
      MODIFICATION HISTORY
      Person       Date        Version      Comments
      -----------  ----------  -----------  -------------------------------
      Girish       06/10/2013    1.0 ONDOC   Created for showing distinct linked offenders on OCUCLOFF
                                             Using root_offender_id from booking to obtain offender name as
                                             it is possible to have different working names for an offender across different booking.
                                             This ensures offender is displayed only once in such scenarios
*/
           ofndr.offender_id_display,
           ofndr.last_name,
           ofndr.first_name,
           ofndr.middle_name,
           off_cont.contact_type,
           SUBSTR(oms_miscellaneous_getdesccode('CONTACTS',off_cont.contact_type),1,40) contact_type_description,
           off_cont.relationship_type,
           SUBSTR(oms_miscellaneous_getdesccode('RELATIONSHIP',off_cont.relationship_type),1,40) relationship_type_description,
           off_cont.person_id,
           off_bkg.root_offender_id
      FROM offender_contact_persons off_cont,
           offender_bookings off_bkg,
           offenders ofndr
     WHERE off_cont.offender_book_id = off_bkg.offender_book_id
       AND off_bkg.root_offender_id = ofndr.offender_id
  ORDER BY ofndr.last_name, ofndr.first_name
;

CREATE OR REPLACE VIEW living_units (living_unit_id, agy_loc_id, living_unit_type, living_unit_code, description, level_1_code, level_2_code, level_3_code, level_4_code, user_desc, aca_cap_rating, security_level_code, list_seq, parent_living_unit_id, housing_unit_type, active_flag, control_active_flag, cna_no, capacity, operation_capacity, certified_flag, deactivate_date, reactivate_date, deactivate_reason_code, comment_text, lowest_level_flag, reach_oper_capacity_flag, no_of_occupant) AS SELECT
/* =========================================================
   TAG DB Version Number = 10.2.0  Date Modified = 19/07/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version  	 Comments
   David NG     11/03/2006 2.0        Use Living Unit Flag for filtering
   David Ng     19/07/2005 1.0        NOMIS project
*/
INTERNAL_LOCATION_ID        LIVING_UNIT_ID
,AGY_LOC_ID
,INTERNAL_LOCATION_TYPE     LIVING_UNIT_TYPE
,INTERNAL_LOCATION_CODE     LIVING_UNIT_CODE
,DESCRIPTION
,substr(tag_int_loc_level_code(description,1),1,40)
,substr(tag_int_loc_level_code(description,2),1,40)
,substr(tag_int_loc_level_code(description,3),1,40)
,substr(tag_int_loc_level_code(description,4),1,40)
,USER_DESC
,ACA_CAP_RATING
,SECURITY_LEVEL_CODE
,LIST_SEQ
,PARENT_INTERNAL_LOCATION_ID  PARENT_LIVING_UNIT_ID
,UNIT_TYPE
,ACTIVE_FLAG
,substr(tag_int_loc_active_flag(internal_location_id),1,1)
,CNA_NO
,CAPACITY
,OPERATION_CAPACITY
,CERTIFIED_FLAG
,DEACTIVATE_DATE
,REACTIVATE_DATE
,DEACTIVATE_REASON_CODE
,COMMENT_TEXT
,(SELECT CASE WHEN COUNT(*)=0 THEN  'Y'  ELSE 'N' END  FROM agency_internal_locations ail2
  WHERE  ail2.parent_internal_location_id = ail.internal_location_id)
,substr(tag_int_loc_operation_flag(internal_location_id),1,1)
,No_Of_Occupant
FROM  agency_internal_locations ail
WHERE (UNIT_TYPE IS NOT NULL AND UNIT_TYPE::text <> '')

 
;

CREATE OR REPLACE VIEW v_off_non_associations (active_flag, offender_id, ns_offender_id, recip_ns_reason_code, ns_offender_book_id, transport_flag, internal_location_flag, offender_book_id, ns_reason_code, ns_level_code) AS SELECT
    /*
    date        developer   ver           comments
    ============================================
    12-MAR-2013  Rose       1.1 NVSTDOC   HPQC#19326, Moved Dynamic query to
    view V_OFF_NON_ASSOCIATIONS as the form OIDONONA block OFF_NA base table.
    */
    'Y' Active_flag,
    OFFENDER_ID,
    NS_OFFENDER_ID,
    RECIP_NS_REASON_CODE,
    NS_OFFENDER_BOOK_ID,
    TRANSPORT_FLAG,
    INTERNAL_LOCATION_FLAG,
    OFFENDER_BOOK_ID,
    NS_REASON_CODE,
    NS_LEVEL_CODE
  FROM
    OFFENDER_NON_ASSOCIATIONS ONA
  WHERE
    ONA.NS_OFFENDER_ID IN (
      SELECT
        OND.NS_OFFENDER_ID
      FROM
        OFFENDER_NA_DETAILS OND
      WHERE
        OND.OFFENDER_ID = ONA.OFFENDER_ID
    )

UNION

  SELECT
    'N' Active_flag,
    OFFENDER_ID,
    NS_OFFENDER_ID,
    RECIP_NS_REASON_CODE,
    NS_OFFENDER_BOOK_ID,
    TRANSPORT_FLAG,
    INTERNAL_LOCATION_FLAG,
    OFFENDER_BOOK_ID,
    NS_REASON_CODE,
    NS_LEVEL_CODE
  FROM
    OFFENDER_NON_ASSOCIATIONS ONA
  WHERE
    ONA.NS_OFFENDER_ID NOT IN (
      SELECT
        OND.NS_OFFENDER_ID
      FROM
        OFFENDER_NA_DETAILS OND
      WHERE
        OND.OFFENDER_ID = ONA.OFFENDER_ID
    )
  ORDER BY
    Active_flag DESC
;

CREATE OR REPLACE VIEW select_vehicles_v1 (vehicle_id, type, make, model_no, asset_id, description, optimum_capacity, physical_capacity, status) AS SELECT
/* ===================================================================
   Version Number = 1.0 CODCSO  Date Created = 25/08/2009 (dd/mm/yyyy)
   =================================================================== */
/* MODIFICATION HISTORY
   Person     	 Date       	Version     	 Comments
   ---------     ------     	------------  	 ------------------------------
   Meeta         25/08/2009     1.0              Created for Denver Local Transportation
*/
       v.vehicle_id
	   ,fa.Asset_type
	   ,fa.make
	   ,fa.model
	   ,fa.asset_id
	   ,fa.description
	   ,v.optimal_capacity optimum
	   ,v.capacity,
       fa.status
FROM fixed_Assets	fa
	 , vehicles v
		where fa.asset_id = v.ASSET_ID
			  and fa.asset_class = 'VEHICLE'

;

CREATE OR REPLACE VIEW location_work_v1 (cal_agy_loc_id, sac_staff_id, from_date, to_date, name, last_name, first_name, position, role, schedule_type, hours_per_week, non_off_code) AS SELECT /* =========================================================
       || Version Number = 6.1.0.0  Date Modified = 07/03/2002
          ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date       	 Version      Comments
   -------------------------------------------------------------------------------------------------------
   Snezana       03-JUL-2002     6.1.0.0      Two separate colunms for Last_name and First_name added to the view
                                              and performance fix
*/
       /*+ RULE */
       slr.CAL_AGY_LOC_ID
      ,slr.SAC_STAFF_ID
      ,slr.FROM_DATE
      ,slr.TO_DATE
      ,sm.LAST_NAME || ', ' || sm.FIRST_NAME NAME1
      ,sm.LAST_NAME
      ,sm.FIRST_NAME
      ,slr.POSITION
      ,slr.ROLE
      ,slr.SCHEDULE_TYPE
      ,slr.HOURS_PER_WEEK
      ,NULL
 FROM staff_location_roles  slr,
      staff_members  sm
WHERE slr.sac_staff_id = sm.staff_id
  AND coalesce(slr.to_date::text, '') = ''
  AND EXISTS ( SELECT   'X'
               FROM   offender_work_assignments vswa
              WHERE   vswa.cal_agy_loc_id  = slr.cal_agy_loc_id
                AND   vswa.sac_staff_id    = slr.sac_staff_id
                AND   vswa.position        = slr.position
                AND   vswa.role            = slr.role
                AND   vswa.from_date       = slr.from_date
                AND   NOT EXISTS ( SELECT   'X'
                                   FROM   assignment_transfers ass
                                  WHERE   ass.offass_id = vswa.offass_id
                                    AND   coalesce(ass.status_to::text, '') = ''
                                    AND   coalesce(ass.sac_staff_id::text, '') = '' ) )

UNION

 SELECT  /*+ RULE */         agy_loc.agy_loc_id cal_agy_loc_id
       , (NULL)::numeric     sac_staff_id
       , NULL      from_date
       , NULL      to_date
       , NULL               name1
       , NULL               last_name
       , NULL               first_name
       , NULL               position1
       , NULL               role1
       , NULL               schedule_type
       , (NULL)::numeric     hours_per_week
       , ref_code.code      non_off_code
  FROM   reference_codes ref_code
       , agency_locations agy_loc
 WHERE   ref_code.domain = 'WRK_ASS_STS'
   AND EXISTS ( SELECT   1
                FROM   offender_work_assignments vswa1
               WHERE   vswa1.status     = ref_code.code
                 AND   vswa1.agy_loc_id = agy_loc.agy_loc_id
                 AND NOT EXISTS ( SELECT   1
                                  FROM   assignment_transfers ass1
                                 WHERE   ass1.offass_id = vswa1.offass_id
                                   AND   coalesce(ass1.status_to::text, '') = ''
                                   AND   coalesce(ass1.sac_staff_id::text, '') = '' ) )
 
 
;

CREATE OR REPLACE VIEW v_pmor_off_med_order_type (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag) AS Select
/* =========================================================
   Version Number = 1.0  Date Modified = 18-DEC-2011
   =========================================================
*/
   -- MODIFICATION HISTORY
   --  Person        Date        Version       Comments
   -- ---------   -----------    --------	   -------------------------------------------
   -- Firas M.    18-DEC-2011      1.0  	 	Initial Version For PMOR
   -- ---------   -----------    --------      -------------------------------------------
       RC.Domain,
       RC.Code,
       RC.Description,
       RC.List_Seq,
       RC.Active_Flag,
       RC.System_Data_Flag,
       RC.Modify_User_id,
       RC.Expired_Date,
       RC.New_Code,
       RC.Parent_Code,
       RC.Parent_Domain,
       RC.Create_Datetime,
       RC.Create_User_Id,
       RC.Modify_Datetime,
       RC.Seal_Flag
  FROM Reference_Codes   RC
 Where RC.Domain = 'OFFMEDORDTYP'

Union

Select RC.Domain,
       'DC' code,
       'Discontinued' Description,
       4 list_seq,
       'N' active_flag,
       RC.System_Data_Flag,
       RC.Modify_User_Id,
       RC.Create_Datetime Expired_Date,
       Null New_Code,
       Null Parent_Code,
       Null Parent_Domain,
       RC.Create_Datetime,
       RC.Create_User_Id,
       RC.Modify_Datetime,
       Null Seal_Flag
  From Reference_Codes   rc
 Where RC.Domain = 'OFFMEDORDTYP'
   and RC.Code   = 'NW'
 
;



CREATE OR REPLACE VIEW v_int_loc_usage_locations (agy_loc_id, internal_location_usage, event_sub_type, internal_location_usage_id, internal_location_id, description, internal_location_code, capacity, usage_location_type, list_seq, usage_location_id, parent_usage_location_id, user_desc, int_loc_deactivate_date, lowest_level_flag) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
          ilu.agy_loc_id, ilu.internal_location_usage, ilu.event_sub_type,
          ul.internal_location_usage_id, ul.internal_location_id,
          ail.description, ail.internal_location_code, ul.capacity,
          ul.usage_location_type, ul.list_seq, ul.usage_location_id,
          ul.parent_usage_location_id, ail.user_desc, ail.deactivate_date,
          (SELECT CASE WHEN COUNT(*)=0 THEN  'Y'  ELSE 'N' END
             FROM int_loc_usage_locations ul1
            WHERE ul1.internal_location_usage_id =
                                                 ul.internal_location_usage_id
              AND ul1.parent_usage_location_id = ul.usage_location_id)
     FROM int_loc_usage_locations ul,
          internal_location_usages ilu,
          agency_internal_locations ail
    WHERE ilu.internal_location_usage_id = ul.internal_location_usage_id
      AND ail.internal_location_id = ul.internal_location_id
 
 
;

CREATE OR REPLACE VIEW v_clear_account_payables (account_code, corporate_id, person_id, corporate_name, total_amount, caseload_id) AS SELECT
/* =========================================================
   Version Number = 6.1.0.0  Date Created = 14-Feb-2002
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date          Version      Comments
   -------------------------------------------------------------------------------
   Johnny        26-NOV-2008   10.2.1.0      Modified so that it will bring up the same records as the screen/package is trying to process (OCDCBENE).
                                             Added criteria on locked_modules.
   Abu           14-Feb-2002     6.1.0.0      #11090: New view on which module
                                              OCDCBENE is based.
*/
 ac.account_code account_code,
 co.corporate_id corporate_id,
 (NULL)::numeric  person_id,
 co.corporate_name corporate_name,
 SUM(CASE WHEN bt.txn_post_usage='DR' THEN             CASE WHEN ac.txn_posting_type='DR' THEN                    coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='CR' THEN                    coalesce(bt.txn_entry_amount, 0) * -1 END  WHEN bt.txn_post_usage='CR' THEN             CASE WHEN ac.txn_posting_type='CR' THEN                    coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='DR' THEN                    coalesce(bt.txn_entry_amount, 0) * -1 END  END ) total_amount,
 bt.caseload_id caseload_id
  FROM beneficiary_transactions bt, account_codes ac, corporates co
 WHERE (bt.corporate_id IS NOT NULL AND bt.corporate_id::text <> '')
   AND bt.corporate_id = co.corporate_id
   AND bt.account_code = ac.account_code
   AND bt.txn_entry_time <
       (SELECT locked_date
          FROM locked_modules
         WHERE module_name = 'OCDCBENE'
           AND caseload_id = bt.caseload_id)
   AND (bt.beneficiary_cleared_flag = 'N' OR
       coalesce(bt.beneficiary_cleared_flag::text, '') = '')
   AND bt.txn_entry_date <=
       (( SELECT LOCALTIMESTAMP - '1 day'::interval * COALESCE(max(s1.profile_value::numeric), 0::numeric)::double precision
          FROM system_profiles s1, system_profiles s2
         WHERE s1.profile_type = 'TRUST_INF'
           AND s1.profile_code = 'CHECK_AGING'
           AND s2.profile_type = 'CHECK_AGING'
           AND s2.profile_code = bt.receipt_txn_type))
 GROUP BY ac.account_code,
          co.corporate_id,
          (NULL)::numeric ,
          co.corporate_name,
          bt.caseload_id

UNION ALL

SELECT ac.account_code account_code,
       (NULL)::numeric  corporate_id,
       pe.person_id person_id,
       pe.last_name || ', ' || first_name,
       SUM(CASE WHEN bt.txn_post_usage='DR' THEN                   CASE WHEN ac.txn_posting_type='DR' THEN                          coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='CR' THEN                          coalesce(bt.txn_entry_amount, 0) * -1 END  WHEN bt.txn_post_usage='CR' THEN                   CASE WHEN ac.txn_posting_type='CR' THEN                          coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='DR' THEN                          coalesce(bt.txn_entry_amount, 0) * -1 END  END ) total_amount,
       bt.caseload_id caseload_id
  FROM beneficiary_transactions bt, account_codes ac, persons pe
 WHERE (bt.person_id IS NOT NULL AND bt.person_id::text <> '')
   AND bt.person_id = pe.person_id
   AND bt.account_code = ac.account_code
   AND (bt.beneficiary_cleared_flag = 'N' OR
       coalesce(bt.beneficiary_cleared_flag::text, '') = '')
   AND bt.txn_entry_time <
       (SELECT locked_date
          FROM locked_modules
         WHERE module_name = 'OCDCBENE'
           AND caseload_id = bt.caseload_id)
   AND bt.txn_entry_date <=
       (( SELECT LOCALTIMESTAMP - '1 day'::interval * COALESCE(max(s1.profile_value::numeric), 0::numeric)::double precision
          FROM system_profiles s1, system_profiles s2
         WHERE s1.profile_type = 'TRUST_INF'
           AND s1.profile_code = 'CHECK_AGING'
           AND s2.profile_type = 'CHECK_AGING'
           AND s2.profile_code = bt.receipt_txn_type))
 GROUP BY ac.account_code,
          pe.person_id,
          (NULL)::numeric ,
          pe.last_name || ', ' || pe.first_name,
          bt.caseload_id

;

CREATE OR REPLACE VIEW v_offender_ta_schedules (offender_book_id, agy_loc_id, to_agy_loc_id, event_type, event_sub_type, out_date, out_time, in_date, in_time, city_code, escort_code, reference_id, ta_id) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
   David Ng     06/01/2006  2.1           Add escort Code
*/
OTS.Offender_Book_ID
,BKG.AGY_LOC_ID
,TAG.AGY_LOC_ID
,'TAP'
,TAG.REASON_CODE
,TATT.OUT_DATE
,TATT.OUT_TIME
,TATT.IN_DATE
,TATT.IN_TIME
,TATT.CITY_CODE
,TAG.ESCORT_CODE
,TATT.TEMP_ABS_SCH_ID
,TAG.TA_ID
FROM   Offender_ta_schedules OTS, Temp_absence_Groups TAG,
       Temp_absence_time_tables TATT, Offender_Bookings BKG
WHERE  OTS.TA_ID = TAG.TA_ID
AND    TAG.TA_ID = TATT.TA_ID
AND    OTS.Offender_Book_ID = BKG.Offender_Book_ID
AND    coalesce(OTS.CANCEL_REASON_CODE::text, '') = ''
AND    coalesce(TAG.CANCEL_REASON_CODE::text, '') = ''
AND    coalesce(TATT.CANCEL_REASON_CODE::text, '') = ''
AND    TATT.OUT_DATE >= tag_schedule_schedule_date()
AND    NOT EXISTS (SELECT *
        FROM   Offender_IND_Schedules OIS
        WHERE  OIS.Offender_Book_ID = OTS.OFFENDER_BOOK_ID
        AND    OIS.TEMP_ABS_SCH_ID  = TATT.TEMP_ABS_SCH_ID)

 
;

CREATE OR REPLACE VIEW v_victim_notifications (offender_id, offender_id_display, offender_book_id, root_offender_id, off_name, notif_recipient_id, notif_recipient_type, notif_recipient_name, associated_party_id, party_id, party_type, relationship_code, notification_code, event_date, trg_event_id, case_info_number, case_id, notif_complete_flag) AS SELECT
   /* =========================================================
    Version Number = 10.2.0.4 Date Modified = 25-Apr-2012
    ========================================================= */
   -- MODIFICATION HISTORY
   -- Person      Date          Version      Comments
   -- Sanjeeva   25-Apr-2012    10.2.0.4     HPQC#11888, Added one space after comma and before first_name, middle name
   -- Ruxandra   04-Mar-2011    10.2.0.3     added column for notification complete flag
   -- Ruxandra   23/09/2010     10.2.0.2     fix for primary contact issue and renaming of some of view's columns for clarity
   -- unknown    06/28/2010     10.2.0.1
   -- ---------   ------      -------------------------------------------
       OFF.offender_id,
       OFF.offender_id_display,
       off_bkg.offender_book_id,
       off_bkg.root_offender_id,
       OFF.last_name||', '||CASE WHEN coalesce(OFF.middle_name::text, '') = '' THEN OFF.first_name  ELSE OFF.first_name||', '||OFF.middle_name END  off_name,
       oapc.person_id::varchar notif_recipient_id,
       'PERSON' notif_recipient_type,
       oms_victims_get_name('PERSON', oapc.person_id::varchar) notif_recipient_name,
       oap.associated_party_id,
       oap.party_id,
       oap.party_type,
       oap.relationship_code,
       oapen.notification_code,
       oapen.event_date,
       oapen.trg_event_id,
     oc.case_info_number,
     oc.case_id,
     'N' notif_complete_flag
FROM OFFENDERS OFF,
     OFFENDER_BOOKINGS off_bkg,
     OFFENDER_ASSOCIATED_PARTIES oap,
     OFFENDER_ASSOC_PRTY_CONTACTS oapc,
     OFFENDER_ASSOC_P_CNT_NOTIFS oapcn,
     OFFENDER_ASSOC_P_EVENT_NOTIFS oapen,
     OFFENDER_CASES oc
WHERE OFF.offender_id = off_bkg.offender_id
AND   OFF.root_offender_id = off_bkg.root_offender_id
AND   off_bkg.offender_book_id = oap.offender_book_id
AND   oap.associated_party_id = oapc.associated_party_id
AND   oapc.associated_party_id = oapcn.associated_party_id
AND   oapc.party_seq = oapcn.party_seq
AND   oapcn.notification_code = oapen.notification_code
AND   oap.offender_book_id = oapen.offender_book_id
AND   oapc.notification_flag = 'Y'
AND   NOT EXISTS (SELECT ovnh.offender_book_id
                  FROM  off_victim_notifications_hty ovnh
                   WHERE ovnh.associated_party_id   = oapcn.associated_party_id
                     AND   ovnh.offender_book_id     = oapen.offender_book_id
                     AND   ovnh.notif_recipient_id   = oapc.person_id::varchar
                     AND   ovnh.notif_recipient_type = 'PERSON'
                     AND   ovnh.notification_code    = oapen.notification_code
                     AND   ovnh.trg_event_id         = oapen.trg_event_id)
AND   oap.case_id = oc.case_id

UNION

--send the victim notifications to the primary contact/s when primary contacts exist
SELECT
       OFF.offender_id,
       OFF.offender_id_display,
       off_bkg.offender_book_id,
       off_bkg.root_offender_id,
       OFF.last_name||', '||CASE WHEN coalesce(OFF.middle_name::text, '') = '' THEN OFF.first_name  ELSE OFF.first_name||', '||OFF.middle_name END  off_name,
       oapc.person_id::varchar notif_recipient_id,
       'PERSON' notif_recipient_type,
       oms_victims_get_name('PERSON', oapc.person_id::varchar) notif_recipient_name,
       oap.associated_party_id,
       oap.party_id,
       oap.party_type,
       oap.relationship_code,
       oapen.notification_code,
       oapen.event_date,
       oapen.trg_event_id,
       oc.case_info_number,
       oc.case_id,
       'N' notif_complete_flag
FROM OFFENDERS OFF,
     OFFENDER_BOOKINGS off_bkg,
     OFFENDER_ASSOCIATED_PARTIES oap,
     OFFENDER_ASSOC_PRTY_CONTACTS oapc,
     OFFENDER_ASSOC_P_NOTIFS oapn,
     OFFENDER_ASSOC_P_EVENT_NOTIFS oapen,
     OFFENDER_CASES oc
WHERE OFF.offender_id = off_bkg.offender_id
AND   OFF.root_offender_id = off_bkg.root_offender_id
AND   off_bkg.offender_book_id = oap.offender_book_id
AND   oap.associated_party_id = oapc.associated_party_id
AND   oapc.primary_contact_flag = 'Y'
AND   oapc.associated_party_id = oapn.associated_party_id
AND   oapn.notification_code = oapen.notification_code
AND   oap.offender_book_id = oapen.offender_book_id
AND   NOT EXISTS (SELECT ovnh.offender_book_id
                  FROM  off_victim_notifications_hty ovnh
                   WHERE ovnh.associated_party_id    = oapn.associated_party_id
                     AND   ovnh.offender_book_id     = oapen.offender_book_id
                     AND   ovnh.notif_recipient_id   = oapc.person_id::varchar
                     AND   ovnh.notif_recipient_type = 'PERSON'
                     AND   ovnh.notification_code    = oapen.notification_code
                     AND   ovnh.trg_event_id         = oapen.trg_event_id)
AND   oap.case_id = oc.case_id

UNION

--get the victim notifications when no primary contact exists
SELECT OFF.offender_id,
       OFF.offender_id_display,
       off_bkg.offender_book_id,
       off_bkg.root_offender_id,
       OFF.last_name||', '||CASE WHEN coalesce(OFF.middle_name::text, '') = '' THEN OFF.first_name  ELSE OFF.first_name||', '||OFF.middle_name END  off_name,
       oap.party_id notif_recipient_id,
       oap.party_type notif_recipient_type,
       oms_victims_get_name(oap.party_type, oap.party_id) notif_recipient_name,
       oap.associated_party_id,
       oap.party_id,
       oap.party_type,
       oap.relationship_code,
       oapen.notification_code,
       oapen.event_date,
       oapen.trg_event_id,
     oc.case_info_number,
     oc.case_id,
     'N' notif_complete_flag
FROM OFFENDERS OFF,
     OFFENDER_BOOKINGS off_bkg,
     OFFENDER_ASSOCIATED_PARTIES oap,
     OFFENDER_ASSOC_P_NOTIFS oapn,
     OFFENDER_ASSOC_P_EVENT_NOTIFS oapen,
     OFFENDER_CASES oc
WHERE OFF.offender_id = off_bkg.offender_id
AND   OFF.root_offender_id = off_bkg.root_offender_id
AND   off_bkg.offender_book_id = oap.offender_book_id
AND   oap.associated_party_id = oapn.associated_party_id
AND   oapn.notification_code = oapen.notification_code
AND   oap.offender_book_id = oapen.offender_book_id
AND   NOT EXISTS (SELECT '1'
               FROM OFFENDER_ASSOC_PRTY_CONTACTS
              WHERE associated_party_id = oap.associated_party_id
              AND primary_contact_flag = 'Y')
AND   NOT EXISTS (SELECT ovnh.offender_book_id
                  FROM  off_victim_notifications_hty ovnh
                  WHERE ovnh.associated_party_id    = oapn.associated_party_id
                    AND   ovnh.offender_book_id     = oapen.offender_book_id
                    AND   ovnh.notif_recipient_id   = oap.party_id
                    AND   ovnh.notif_recipient_type = oap.party_type
                    AND   ovnh.notification_code    = oapen.notification_code
                    AND   ovnh.trg_event_id         = oapen.trg_event_id  )
AND   oap.case_id = oc.case_id

;

CREATE OR REPLACE VIEW stg_search_v1 (stg_id, seq, code, description, source) AS select stg_id,identifier_seq,profile_type,detail,'C' FROM stg_identifiers

union

select stg_id,word_seq,code,description,'I' from stg_identifying_words

 
;
CREATE OR REPLACE VIEW v_int_loc_offenders_2 (agy_loc_id, root_internal_location_id, offender_book_id, offender_id, last_name, first_name, offender_id_display, internal_location_id, internal_location_desc, parent_internal_location_id, in_out_status, living_unit_id) AS SELECT
/* MODIFICATION HISTORY
   Person        Date      Version        Comments
   Patrick      14-Jan-2010 2.2          Fixed Defect 488.  Need to change the tree walk as a in-line view to make it works correctly.
   Patrick      06-Jan-2010 2.1          Fixed Defect 488.  Changed tree walking code as it was not correctly implemented.
                                         Added START WITH clause and changed CONNECT BY clause
   David Ng     03/01/2006 2.0           NOMIS project(10.2.0)
*/
       ail.agy_loc_id,
       ail.root_internal_location_id,
       bkg.offender_book_id,
       bkg.offender_id,
       OFF.last_name,
       OFF.first_name,
       OFF.offender_id_display,
       ail.internal_location_id,
       ail.description,
       ail.parent_internal_location_id,
       bkg.in_out_status,
       bkg.living_unit_id
  FROM offenders off, (WITH RECURSIVE cte AS (
SELECT     distinct
                   agy_loc_id, internal_location_id root_internal_location_id,internal_location_id,description,parent_internal_location_id
              FROM agency_internal_locations  UNION ALL
SELECT     distinct
                   A.agy_loc_id, C.internal_location_id root_internal_location_id,A.internal_location_id,A.description,A.parent_internal_location_id
              FROM agency_internal_locations A JOIN cte c ON (c.internal_location_id = A.parent_internal_location_id)

) SELECT * FROM cte
) ail
LEFT OUTER JOIN offender_bookings bkg ON (ail.internal_location_id = bkg.agency_iml_id)
WHERE bkg.offender_id = OFF.offender_id

;

CREATE OR REPLACE VIEW v_off_appt_days (offender_course_appt_grp_id, off_prgref_id, offender_course_appt_rule_id, start_date, end_date, start_time, end_time, week_day) AS SELECT
   /* =========================================================
    Version Number = 10.2.2.1.1 Date Modified = 19-OCT-2011
    ========================================================= */
   /* MODIFICATION HISTORY
    Date         Version          Person    Comments
    --------     ---------------  --------- -------------------------------------------
    19-OCT-2011  10.2.2.1.1       Brad      HPQC# 9063 Rewritten for performance as per
                                            Graham C's optimized version 20-NOV-2006
    original comments follow
    Person Date Version Comments
    GJC 20/11/2006 2.2 Invalid comments fixed
    GJC 20/11/2006 2.1 Rewritten due to performance */
    g.offender_course_appt_grp_id,
    g.off_prgref_id ,
    r.offender_course_appt_rule_id,
    g.start_date,
    g.end_date ,
    r.start_time,
    r.end_time,
    r.flag_type
  FROM OFFENDER_COURSE_APPT_GRPS g,
    (SELECT 'MON' flag_type,
      r.offender_course_appt_rule_id,
      r.start_time,
      r.end_time,
      r.offender_course_appt_grp_id
    FROM OFFENDER_COURSE_APPT_RULES r
    WHERE r.monday_flag = 'Y'

UNION ALL

    SELECT 'TUE' flag_type,
      r.offender_course_appt_rule_id,
      r.start_time,
      r.end_time,
      r.offender_course_appt_grp_id
    FROM OFFENDER_COURSE_APPT_RULES r
    WHERE r.tuesday_flag = 'Y'
    
UNION ALL

    SELECT 'WED' flag_type,
      r.offender_course_appt_rule_id,
      r.start_time,
      r.end_time,
      r.offender_course_appt_grp_id
    FROM OFFENDER_COURSE_APPT_RULES r
    WHERE r.wednesday_flag = 'Y'
    
UNION ALL

    SELECT 'THU' flag_type,
      r.offender_course_appt_rule_id,
      r.start_time,
      r.end_time,
      r.offender_course_appt_grp_id
    FROM OFFENDER_COURSE_APPT_RULES r
    WHERE r.thursday_flag = 'Y'
    
UNION ALL

    SELECT 'FRI' flag_type,
      r.offender_course_appt_rule_id,
      r.start_time,
      r.end_time,
      r.offender_course_appt_grp_id
    FROM OFFENDER_COURSE_APPT_RULES r
    WHERE r.friday_flag = 'Y'
    
UNION ALL

    SELECT 'SAT' flag_type,
      r.offender_course_appt_rule_id,
      r.start_time,
      r.end_time,
      r.offender_course_appt_grp_id
    FROM OFFENDER_COURSE_APPT_RULES r
    WHERE r.saturday_flag = 'Y'
    
UNION ALL

    SELECT 'SUN' flag_type,
      r.offender_course_appt_rule_id,
      r.start_time,
      r.end_time,
      r.offender_course_appt_grp_id
    FROM OFFENDER_COURSE_APPT_RULES r
    WHERE r.sunday_flag = 'Y'
    ) r
  WHERE g.start_date                < (date_trunc('day'::text, LOCALTIMESTAMP) + '90 days'::interval)
  AND ( g.end_date                 >= date_trunc('day', LOCALTIMESTAMP)
  OR coalesce(g.end_date::text, '') = '')
  AND r.offender_course_appt_grp_id = g.offender_course_appt_grp_id
 
;

CREATE OR REPLACE VIEW v_agency_incidents (agency_incident_id, reported_staff_id, staff_first_name, staff_last_name, incident_date, incident_time, internal_location_id, incident_type, incident_type_desc, incident_status, report_date, report_time, agy_loc_id, int_loc_description, repair_flag) AS SELECT
/* =========================================================
   Version Number = 10.2.0  Date Modified = 01/09/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     01/09/2005  2.0           NOMIS project
*/
AI.AGENCY_INCIDENT_ID,
AI.REPORTED_STAFF_ID,
SM.FIRST_NAME,
SM.LAST_NAME,
AI.INCIDENT_DATE,
AI.INCIDENT_TIME,
AI.INTERNAL_LOCATION_ID,
AI.INCIDENT_TYPE,
(SELECT DESCRIPTION
   FROM REFERENCE_CODES
  WHERE DOMAIN = 'INC_TYPE'
    AND CODE = AI.INCIDENT_TYPE) INC_TYPE_DESC,
AI.INCIDENT_STATUS,
AI.REPORT_DATE,
AI.REPORT_TIME,
AI.AGY_LOC_ID,
AIL.description,
(SELECT CASE WHEN COUNT(*)=0 THEN  'N'  ELSE 'Y' END
  FROM AGENCY_INCIDENT_REPAIRS AIR
  WHERE  AI.AGENCY_INCIDENT_ID = AIR.AGENCY_INCIDENT_ID) REPAIR_FLAG
FROM  STAFF_MEMBERS SM, AGENCY_INCIDENTS AI, AGENCY_INTERNAL_LOCATIONS AIL
WHERE SM.STAFF_ID = AI.REPORTED_STAFF_ID
AND AIL.INTERNAL_LOCATION_ID = AI.INTERNAL_LOCATION_ID
 
 
;

CREATE OR REPLACE VIEW v_menu_secs (menu_id, parent_menu_id, menu_item, module_name, sort_order, description, module_type, appln_code) AS SELECT
/* =========================================================
   Version Number = 1.0  Date Modified = Feb/16/2009
   =========================================================
   MODIFICATION HISTORY
   Person       Date              Version   Comments
   ---------    -----            ---------  ------------------------------
   Niko         Feb/16/2009       1.0       Created
*/
       ms.menu_id
      ,ms.parent_menu_id
      ,ms.menu_item
      ,ms.module_name
      ,ms.sort_order
      ,om.description
      ,om.module_type
      ,om.appln_code
  FROM menu_securities ms
LEFT OUTER JOIN oms_modules om ON (ms.module_name = om.module_name);

CREATE OR REPLACE VIEW v_off_images (offender_image_id, offender_book_id, capture_datetime, orientation_type, full_size_image, thumbnail_image, image_object_type, image_view_type, image_object_id, image_object_seq, active_flag, create_user_id, modify_user_id) AS select OFFENDER_IMAGE_ID
      ,OFFENDER_BOOK_ID
      ,CAPTURE_DATETIME
      ,ORIENTATION_TYPE
      ,FULL_SIZE_IMAGE
      ,THUMBNAIL_IMAGE
      ,IMAGE_OBJECT_TYPE
      ,IMAGE_VIEW_TYPE
      ,IMAGE_OBJECT_ID
      ,IMAGE_OBJECT_SEQ
      ,ACTIVE_FLAG
      ,CREATE_USER_ID
      ,MODIFY_USER_ID
FROM offender_images


 
;

CREATE OR REPLACE VIEW v_offender_alerts (alert_date, offender_book_id, root_offender_id, alert_seq, alert_type, alert_code, authorize_person_text, create_date, alert_status, verified_flag, expiry_date, comment_text, caseload_id, modify_user_id, modify_datetime, caseload_type) AS SELECT /* ========================================================
             VIEW: V_OFFENDER_ALERTS
             Version Number = 4.11.0.0  Date Modified = 05/31/2001
           ========================================================*/
          alert_date, offender_book_id, root_offender_id, alert_seq, alert_type, alert_code, authorize_person_text, create_date,
          alert_status, verified_flag, expiry_date, comment_text, caseload_id, modify_user_id, modify_datetime, caseload_type
     FROM offender_alerts


 
;

CREATE OR REPLACE VIEW v_incident_types (questionnaire_id, questionnaire_category, incident_type, description, list_seq, active_flag, expiry_date) AS SELECT
/* =========================================================
   Version Number = 10.2.0  Date Modified = 01/09/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     01/10/2005  2.0           NOMIS project
   David Ng     08/02/2006  2.1           User quesitonnaires' setup rather than ref Codes
*/
QUE.QUESTIONNAIRE_ID
,QUE.QUESTIONNAIRE_CATEGORY
,QUE.CODE
,QUE.DESCRIPTION
,QUE.LIST_SEQ
,QUE.ACTIVE_FLAG
,QUE.EXPIRY_DATE
FROM QUESTIONNAIRES QUE
WHERE QUE.QUESTIONNAIRE_CATEGORY = 'IR_TYPE'

 
;

CREATE OR REPLACE VIEW v_offender_assigned (offender_book_id, agy_loc_id, sac_staff_id, offender_last_name, offender_first_name, offender_id_display, staff_last_name, staff_first_name) AS SELECT
/* =========================================================
   Version Number = 4.11.0.0  Date Modified = 06/06/2001
   ========================================================= */
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   ---------   ------      ------------  ------------------------------
   Snezana     06/06/2001  4.11.0.0      performance: v_header_block replaced with offenders and offender_bookings
                                         and outer join cp.offender_book_id(+) taken out
*/
       off_bkg.offender_book_id offender_book_id,
       cp.agy_loc_id agy_loc_id,
       cp.sac_staff_id sac_staff_id,
       off_name.last_name offender_last_name,
       off_name.first_name offender_first_name,
       off_name.offender_id_display offender_id_display,
       sm.last_name staff_last_name,
       sm.first_name staff_first_name
  FROM offenders off_name, offender_bookings off_bkg, case_plans cp
LEFT OUTER JOIN staff_members sm ON (cp.sac_staff_id = sm.staff_id)
WHERE coalesce(cp.end_date::text, '') = ''  AND off_bkg.offender_id = off_name.offender_id AND off_bkg.offender_book_id = cp.offender_book_id;

CREATE OR REPLACE VIEW v_iwp_template_data (template_id, bookmark_name, bookmark_type, sql_text) AS SELECT qq.template_id template_id, UPPER( q.bookmark_name ) bookmark_name, q.bookmark_type bookmark_type,
       UPPER( q.sql_text ) sql_text
  FROM iwp_bookmarks q, iwp_bookmarks_groups qq
 WHERE q.bookmark_name = qq.bookmark_name
   AND q.active_flag = 'Y'

;

CREATE OR REPLACE VIEW v_address_usages (address_id, address_type, address_usage, owner_class, owner_id, owner_seq, owner_code, start_date, end_date, address_active_flag, usage_active_flag, active_flag, full_address, house, street, area, country, suite_number, street_number, street_direction, street_information, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, capacity, comment_text, primary_flag, mail_flag, validated_flag, create_datetime, create_user_id, modify_datetime, modify_user_id) AS SELECT
/* =========================================================
   Version Number = 1.6.1.1 Date Modified = 12-mar-2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person         Date                       Version       Comments
   ---------             ------                         ---------    ------------------------------
   VIKAS GROVER      12-MAR-2007 		 1.6.1.1 	   New Column Prov_state_desc, city_name
   NIKO              26-JAN-2007         1.6.1.0      Replace the columns in V_STF_ADDR block:
                                          SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     06/21/2005               1.6            NOMIS project
*/
a.ADDRESS_ID
,a.ADDRESS_TYPE
,au.ADDRESS_USAGE
,OWNER_CLASS
,OWNER_ID
,OWNER_SEQ
,OWNER_CODE
,a.start_date
,a.end_date
,CASE WHEN coalesce(a.end_date::text, '') = '' THEN  'Y'  ELSE CASE WHEN LEAST(a.end_date, ROUND(LOCALTIMESTAMP))=ROUND(LOCALTIMESTAMP) THEN 'Y'  ELSE 'N' END  END  ADDRESS_Active_FLAG
,CASE WHEN au.ACTIVE_FLAG='N' THEN  'N'  ELSE 'Y' END  USAGE_ACTIVE_FLAG
,CASE WHEN au.ACTIVE_FLAG='N' THEN  'N'  ELSE CASE WHEN coalesce(a.end_date::text, '') = '' THEN  'Y'  ELSE CASE WHEN LEAST(a.end_date, ROUND(LOCALTIMESTAMP))=ROUND(LOCALTIMESTAMP) THEN 'Y'  ELSE 'N' END  END  END  ACTIVE_FLAG
,SUBSTR(CASE WHEN coalesce(SUITE_NUMBER::text, '') = '' THEN  NULL  ELSE SUITE_NUMBER||', ' END ||CASE WHEN coalesce(STREET_NUMBER::text, '') = '' THEN  NULL  ELSE STREET_NUMBER||', ' END ||
 CASE WHEN coalesce(STREET::text, '') = '' THEN  NULL  ELSE STREET||', ' END ||CASE WHEN coalesce(STREET_DIRECTION::text, '') = '' THEN  NULL  ELSE STREET_DIRECTION||', ' END ||
 CASE WHEN coalesce(CITY_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('CITY',CITY_CODE)||', ' END ||
 CASE WHEN coalesce(PROV_STATE_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('STATE',PROV_STATE_CODE)||', ' END ,1,1000)
,CASE WHEN coalesce(SUITE_NUMBER::text, '') = '' THEN  NULL  ELSE SUITE_NUMBER||'  ' END ||STREET_NUMBER HOUSE
,STREET
,SUBSTR(CASE WHEN coalesce(STREET_DIRECTION::text, '') = '' THEN  NULL  ELSE STREET_DIRECTION||'  ' END ||
 CASE WHEN coalesce(CITY_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('CITY',CITY_CODE)||'  ' END ||
 CASE WHEN coalesce(PROV_STATE_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('STATE',PROV_STATE_CODE) END ,1,1000) AREA
,SUBSTR(CASE WHEN coalesce(COUNTRY_CODE::text, '') = '' THEN  NULL  ELSE Oms_Miscellaneous_GETDESCCODE('COUNTRY',COUNTRY_CODE) END ,1,40) COUNTRY
,SUITE_NUMBER
,STREET_NUMBER
,STREET_DIRECTION
,SUBSTR(STREET_NUMBER || ' ' || STREET || ' ' || Oms_Miscellaneous_GETDESCCODE('STREET_DIR',STREET_DIRECTION),1,254) STREET_INFORMATION
,CITY_CODE
,SUBSTR(CASE WHEN coalesce(city_name::text, '') = '' THEN  Oms_Miscellaneous_GETDESCCODE('CITY',CITY_CODE)  ELSE city_name END ,1,40) CITY_NAME
,PROV_STATE_CODE
,SUBSTR(Oms_Miscellaneous_GETDESCCODE('PROV_STATE',PROV_STATE_CODE),1,40) PROV_STATE_DESC
,ZIP_POSTAL_CODE
,COUNTRY_CODE
,CAPACITY
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,VALIDATED_PAF_flag
,a.CREATE_DATETIME
,a.CREATE_USER_ID
,a.MODIFY_DATETIME
,a.MODIFY_USER_ID
FROM addresses a
LEFT OUTER JOIN address_usages au ON (a.address_id = au.address_id);

CREATE OR REPLACE VIEW v_cash_rec_gl_transactions (txn_reversed_flag, txn_entry_date, txn_entry_time, txn_id, txn_entry_seq, offender_id, account_code, txn_entry_amount, receipt_number, txn_entry_desc, gl_entry_seq, account_period_id, txn_type, txn_post_usage, caseload_id, txn_reference_number, bank_statement_date, recon_clear_flag, reversed_txn_id, payee_person_id, reversed_txn_entry_seq, reversed_gl_entry_seq, payee_corporate_id, payee_name_text, txn_object_code, list_seq, txn_object_id, create_user_id, create_date, info_number, deduction_id, reversal_reason_code, txn_loc_id, payee_clear_flag, offender_book_id, from_user_id, to_user_id) AS SELECT
     /* ========================================================
           VIEW: v_cash_rec_gl_transactions
           Version Number = 1.2  Date Modified = 10/13/2011
         ========================================================*/
           txn_reversed_flag,
           gl.txn_entry_date,
           gl.txn_entry_time,
           gl.txn_id,
           gl.txn_entry_seq,
           offender_id,
           account_code,
           gl.txn_entry_amount,
           receipt_number,
           txn_entry_desc,
           gl_entry_seq,
           account_period_id,
           gl.txn_type,
           gl.txn_post_usage,
           gl.caseload_id,
           txn_reference_number,
           bank_statement_date,
           recon_clear_flag,
           reversed_txn_id,
           payee_person_id,
           reversed_txn_entry_seq,
           reversed_gl_entry_seq,
           payee_corporate_id,
           payee_name_text,
           txn_object_code,
           list_seq,
           txn_object_id,
           ct.create_user_id,
           ct.create_datetime create_date,
           info_number,
           deduction_id,
           reversal_reason_code,
           txn_loc_id,
           payee_clear_flag,
           offender_book_id,
           ct.from_user_id,
           ct.to_user_id
      FROM gl_transactions gl
LEFT OUTER JOIN cash_transfer_txns ct ON (gl.txn_id = ct.txn_id AND gl.caseload_id = ct.caseload_id);

CREATE OR REPLACE VIEW v_off_contact_persons (offender_id_display, last_name, first_name, middle_name, contact_type, contact_type_description, relationship_type, relationship_type_description, person_id, offender_book_id, offender_id, approved_visitor_flag, contact_root_offender_id, comment_text, offender_contact_person_id, person_last_name, person_first_name, caseload_type, modify_datetime, modify_user_id, check_sum, active_flag) AS SELECT
            /*
            ============================================================
            Version Number = 2.1.1.1  Date Modified = 6-Jan-2014
            ============================================================
            MODIFICATION HISTORY
            Person       Date        Version      Comments
            -----------  ----------  -----------  -------------------------------
			Patrick       6/Jan/2014   2.1.1.1     HPQC#24196. Added ACTIVE_FLAG to allow filtering.
            Vikas Grover  23/Feb/2007  2.1.1.0   TAG10g Product: Delete Title Column from View
            Erin         10/07/2006    2.1          Modified for Offender Vists datamodel changes
            Surya        07/09/2005    2.0          Created for Person Search.
            */
            OFF.offender_id_display,
			OFF.last_name,
			OFF.first_name,
			OFF.middle_name,
			off_cont.contact_type,
            SUBSTR(oms_miscellaneous_getdesccode('CONTACTS', off_cont.contact_type), 1, 40) contact_type_description,
			off_cont.relationship_type,
            SUBSTR(oms_miscellaneous_getdesccode('RELATIONSHIP', off_cont.relationship_type), 1, 40) relationship_type_description,
			per.person_id,
            off_bkg.offender_book_id,
			OFF.offender_id,
			off_cont.approved_visitor_flag,
			off_cont.contact_root_offender_id,
			off_cont.comment_text,
            off_cont.offender_contact_person_id,
			per.last_name person_last_name,
			per.first_name person_first_name,
			off_cont.caseload_type,
            off_cont.modify_datetime,
			off_cont.modify_user_id,
            (TO_CHAR(coalesce(off_cont.modify_datetime, off_cont.create_datetime), 'DDHH24MISSFF4'))::numeric  modify_datetime,
			off_cont.active_flag
       FROM persons per, offenders OFF, offender_bookings off_bkg, offender_contact_persons off_cont
      WHERE per.person_id = off_cont.person_id AND off_cont.offender_book_id = off_bkg.offender_book_id AND off_bkg.offender_id = OFF.offender_id
   ORDER BY OFF.last_name, OFF.first_name
;

CREATE OR REPLACE VIEW v_reference_codes (domain, code, description, list_seq, active_flag, modify_user_id, expiry_date, new_code, parent_code) AS SELECT
     ref_code.DOMAIN
    ,ref_code.CODE
    ,ref_code.DESCRIPTION
    ,ref_code.LIST_SEQ
    ,ref_code.ACTIVE_FLAG
    ,ref_code.MODIFY_USER_ID
    ,ref_code.EXPIRED_DATE
    ,ref_code.NEW_CODE
    ,ref_code.PARENT_CODE
FROM
    reference_codes  ref_code

 
;

CREATE OR REPLACE VIEW caseload_current_accounts (caseload_id, account_code, account_period_id, current_balance, bank_account_type, bank_account_number, account_party_type, payee_person_id, payee_corporate_id, modify_user_id, modify_date, list_seq, routing_number) AS SELECT
 CCAB.CASELOAD_ID
, CCAB.ACCOUNT_CODE
, MAX(CCAT.ACCOUNT_PERIOD_ID)
, SUM(coalesce(CCAT.CURRENT_BALANCE,0))
, CCAB.BANK_ACCOUNT_TYPE
, CCAB.BANK_ACCOUNT_NUMBER
, CCAB.ACCOUNT_PARTY_TYPE
, CCAB.PAYEE_PERSON_ID
, CCAB.PAYEE_CORPORATE_ID
, USER
, MAX(CCAT.MODIFY_DATE)
, MAX(CCAT.LIST_SEQ)
, CCAB.ROUTING_NUMBER
 FROM caseload_current_accounts_TXNS CCAT, caseload_current_accounts_base CCAB
 WHERE CCAT.caseload_id  = CCAB.caseload_id
 AND   CCAT.account_code  = CCAB.account_code
GROUP BY CCAB.CASELOAD_ID, CCAB.ACCOUNT_CODE,
	 CCAB.BANK_ACCOUNT_TYPE, CCAB.BANK_ACCOUNT_NUMBER, CCAB.ACCOUNT_PARTY_TYPE,
	 CCAB.PAYEE_PERSON_ID, CCAB.PAYEE_CORPORATE_ID, CCAB.ROUTING_NUMBER

;

CREATE OR REPLACE VIEW v_trip_segments (route_name, from_agy, from_seq, to_agy, to_seq) AS SELECT rsd1.route_name, rsd1.from_agy, rsd1.from_seq, rsd2.agy_loc_id to_agy, rsd1.to_seq
  FROM (
    SELECT route_name,
         agy_loc_id from_agy,
         leg_id*1000 + leg_seq from_seq,
            (SELECT MIN(leg_id*1000 + leg_seq)
          FROM route_stop_details b
         WHERE a.route_name = b.route_name
           AND b.leg_id*1000 + b.leg_seq > a.leg_id*1000 + a.leg_seq) to_seq
      FROM route_stop_details a
      ) rsd1, route_stop_details rsd2
WHERE (to_seq IS NOT NULL AND to_seq::text <> '')
AND rsd1.route_name = rsd2.route_name AND rsd1.to_seq = rsd2.leg_id*1000 + leg_seq

;

CREATE OR REPLACE VIEW v_offender_temp_abs (event_id, offender_book_id, agy_loc_id, event_date, start_time, end_time, event_class, event_type, event_sub_type, event_reason_code, event_status, cancel_reason_code, comment_text, reference_id, application_date, application_time, return_date, return_time) AS select
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
NULL EVENT_ID
,ots.OFFENDER_BOOK_ID
,tag.AGY_LOC_ID
,OUT_DATE        EVENT_DATE
,OUT_TIME        START_TIME
,NULL            END_TIME
,'EXT_MOV'       EVENT_CLASS
,'TAP'           EVENT_TYPE
,'JAIL'          EVENT_SUB_TYPE
,tag.REASON_CODE EVENT_REASON_CODE
,'SCH'           EVENT_STATUS
,NULL            CANCEL_REASON_CODE
,NULL            COMMENT_TEXT
,NULL            REFERENCE_ID
,NULL            APPLICATION_DATE
,NULL            APPLICATION_TIME
,IN_DATE         RETURN_DATE
,IN_TIME         RETURN_TIME
FROM temp_absence_groups tag, offender_ta_schedules ots,
     temp_absence_time_tables tatt
where tag.ta_id = ots.ta_id
and   tag.ta_id = tatt.ta_id

 
;

CREATE OR REPLACE VIEW v_offender_prg_obligations (offender_prg_obligation_id, offender_id, offender_agy_loc_id, offender_community_agy_loc_id, offender_id_display, age, first_name, last_name, offender_name, sex_code, sex_desc, race_code, race_desc, risk_code, offence_types, special_need_flag, availability_code, availability_desc, legal_end_date, status, status_change_date, status_change_reason, case_info_number, court_event_id, court_agy_loc_id, court_name, order_id, offender_book_id, sentence_seq, sentence_calc_type, sentence_category, sentence_status, sentence_start_date, sentence_end_date, sentence_desc, service_obligation_code, sentence_condition_desc, length, length_unit, program_length, credited_units, activity_desc, activity_code, program_id, start_date, end_date, event_type, event_sub_type, offender_sent_cond_act_id, offender_sent_condition_id, comm_condition_type, comm_condition_code, category_type, comment_text, obligation_source, obligation_source_desc, referral_date, wait_days, program_category, active_flag, community_active_flag, check_sum) AS select
/* MODIFICATION HISTORY
   Person        Date      Version        Comments
   ---------    ------     ---------     ------------------------------
   Erin         26-Feb-2010 2.7           QC#447: removed booking_status field
   Erin         22-Feb-2010 2.6           QC#447: added active_flag and community_active_flag
   Erin         01-Feb-2010 2.5           QC#447: added booking_status field
   Sarah        09/29/2008  2.4           Added trigger to the view.
   Sarah        09/26/2008  2.3           Added category type to the view
   David Ng     01/08/2006  2.2           Add Offender Community Agy Loc ID
   David Ng     03/04/2006  2.1           NOMIS project(10.2.0)
*/
   opo.OFFENDER_PRG_OBLIGATION_ID,
   BKG.Offender_ID,
   BKG.Agy_loc_ID,
   BKG.COMMUNITY_Agy_loc_ID,
   OFF.Offender_ID_DISPLAY,
   Round((Months_Between(date_trunc('day', LOCALTIMESTAMP),OFF.Birth_date)-0.5)/12),
   OFF.First_Name,
   OFF.Last_name,
   OFF.Last_Name||' '||OFF.First_NAme,
   OFF.Sex_Code,
   SUBSTR(oms_miscellaneous_getdesccode('SEX',
           OFF.Sex_code),
                  1,
                  40
                 ),
   OFF.RACE_CODE,
   SUBSTR(oms_miscellaneous_getdesccode('ETHNICITY',
           OFF.Race_code),
                  1,
                  40
                 ),
   SUBSTR(omkhead_get_alerts(BKG.offender_book_id),
               0,
               40
              ),
   SUBSTR(TAG_PRG_Offender_Offence_Type(opo.Offender_BOok_ID),1,12), -- Offence_types,
   opo.Special_Need_flag,
   opo.Availability_code,
   SUBSTR(oms_miscellaneous_getdesccode('PS_PRG_AVAIL',
           opo.availability_code),
                  1,
                  40
                 ), -- Availability_desc
   TAG_PRG_Offender_Release_Date(opo.Offender_BOok_ID), -- Legal_End_date,
   opo.status,
   opo.status_change_Date,
   opo.status_change_reason,
   OC.case_info_number,
   ORD.event_id,
   ORD.issuing_agy_loc_id,
   AL.Description, -- court_name,
   OS.order_id,
   opo.offender_book_id,
   opo.sentence_seq,
   OS.sentence_calc_type,
   OS.sentence_category,
   OS.sentence_status,
   OS.start_date,
   OS.end_date,
   sct.Description,
   sct.Program_Method,
   CASE WHEN coalesce(opo.Offender_Sent_Condition_ID::text, '') = '' THEN  sct.Description  ELSE CC.Description END ,
   opo.length,
   opo.length_unit,
   CASE WHEN coalesce(opo.Offender_Sent_condition_ID::text, '') = '' THEN  TAG_PRG_OBLIGATION_LENGTH(OS.Offender_book_ID, OS.Sentence_seq)  ELSE opo.length::varchar||' '||opo.Length_Unit END ,
   NULL, -- credited_units,
   PS.description,
   PS.program_code,
   opo.Program_ID,
   opo.start_date,
   opo.End_date,
   opo.Event_type,
   opo.Event_sub_type,
   opo.Offender_Sent_Cond_act_ID,
   opo.Offender_Sent_Condition_ID,
   OSC.comm_condition_type,
   OSC.Comm_Condition_code,
   OSC.Category_Type,
   opo.Comment_text,
   opo.obligation_source,
   SUBSTR(oms_miscellaneous_getdesccode('PS_PRG_WHO',
           opo.obligation_source),
                  1,
                  40
                 ), -- Obligation_source_desc
   opo.REFERRAL_DATE,
   LOCALTIMESTAMP::date- opo.referral_date::date,
   PS.Program_category,
   BKG.active_flag,
   BKG.community_active_flag,
   Tag_schedule_check_sum(coalesce(opo.MODIFY_DATETIME, opo.CREATE_DATETIME))
FROM offenders off, offender_bookings bkg, offender_prg_obligations opo
LEFT OUTER JOIN program_services ps ON (opo.Program_ID = PS.Program_ID)
LEFT OUTER JOIN offender_sentences os ON (opo.Offender_Book_ID = OS.Offender_Book_ID AND opo.Sentence_seq = OS.Sentence_Seq)
LEFT OUTER JOIN offender_sent_conditions osc ON (opo.Offender_Sent_Condition_ID = OSC.Offender_Sent_Condition_ID)
LEFT OUTER JOIN offender_cases oc ON (OS.Case_ID = OC.Case_ID)
LEFT OUTER JOIN orders ord ON (OS.Order_ID = ORD.Order_ID)
LEFT OUTER JOIN sentence_calc_types sct ON (OS.Sentence_calc_type = sct.Sentence_calc_type AND OS.Sentence_category = sct.Sentence_category)
LEFT OUTER JOIN agency_locations al ON (ORD.Issuing_agy_Loc_ID = AL.Agy_Loc_ID)
LEFT OUTER JOIN community_conditions cc ON (OSC.Comm_Condition_type = CC.Comm_condition_type AND OSC.Comm_Condition_code = CC.Comm_condition_code AND OSC.Category_Type = CC.Category_Type)
WHERE opo.Offender_Book_ID   = BKG.Offender_Book_ID AND BKG.Offender_ID        = OFF.Offender_ID;

CREATE OR REPLACE VIEW v_oms_request (request_id, module_name, number_of_copy, request_status, printer_id, display_flag, request_date, request_user_id, create_user_id, modify_user_id) AS select REQUEST_ID,
         MODULE_NAME,
         NUMBER_OF_COPY,
         REQUEST_STATUS,
         PRINTER_ID,
         DISPLAY_FLAG,
         REQUEST_DATE,
         REQUEST_USER_ID,
         CREATE_USER_ID,
         MODIFY_USER_ID
    FROM oms_requests


 
;

CREATE OR REPLACE VIEW v_person_fingerprints (bio_id, person_id, position, description, score, mrs_updated, status, status_desc, consent_given, last_scan_date) AS SELECT /* MODIFICATION HISTORY
   Person     	   Date         Version     	 Comments
   Patrick Cheung  16/10/2009   2.1              Fixed defect 1586.  To add column LAST_SCAN_DATE
   Patrick Cheung  01/10/2009   2.0              Created for biometrics development
*/
biometric.subject_id, person_finger.person_id, person_finger.POSITION1 , person_finger.description,
       biometric.sample_score, biometric.mrs_updated, CASE WHEN coalesce(biometric.sample_score::text, '') = '' THEN  'UNDEFINED'  ELSE 'USABLE' END ,
       oms_miscellaneous_getdesccode('FINGER_STS', CASE WHEN coalesce(biometric.sample_score::text, '') = '' THEN  'UNDEFINED'  ELSE 'USABLE' END ),
       biometric.consent_given, elite_biometrics_get_last_scan_date(biometric.subject_id, person_finger.POSITION1::bigint)
  FROM (SELECT rc.code, rc.description,  (rc.code)::numeric  POSITION1, p.person_id
          FROM reference_codes rc, persons p
         WHERE rc.domain = 'FINGER_ENR' AND rc.active_flag = 'Y') person_finger
LEFT OUTER JOIN (SELECT bs.subject_id subject_id, bs.person_id person_id, bm.sample_score sample_score,
               bs.ntemplate_update mrs_updated, bm.POSITION POSITION1, bs.consent_given
          FROM bio_subjects bs, bio_fp_samples bm
         WHERE bs.subject_id = bm.subject_id AND (bs.person_id IS NOT NULL AND bs.person_id::text <> '') AND bm.mrs_flag = 'Y') biometric ON (person_finger.POSITION1 = biometric.POSITION1 AND person_finger.person_id = biometric.person_id);

CREATE OR REPLACE VIEW v_tag_tab_columns  AS select table_name, column_name from information_schema.columns  where table_schema='oms_owner' and table_name not like '%_JN';

CREATE OR REPLACE VIEW v_int_loc_rolls_2 (agy_loc_id, root_internal_location_id, internal_location_id, in_locations) AS SELECT
/* MODIFICATION HISTORY
   Person        Date      Version       Comments
   Patrick      14-Jan-2010 2.2          Fixed Defect 488.  Need to change the tree walk as a in-line view to make it works correctly.
   David Ng     25/05/2006 2.1           Performance tuning
   David Ng     15/10/2005 2.0           NOMIS project(10.2.0)
*/
ail.agy_loc_id
,ail.root_internal_location_id
,ail.internal_location_ID
,CASE WHEN coalesce(bkg.AGENCY_IML_ID::text, '') = '' THEN  0  ELSE CASE WHEN bkg.IN_OUT_STATUS='IN' THEN  1  ELSE 0 END  END
 FROM (WITH RECURSIVE cte AS (
SELECT     distinct agy_loc_id, internal_location_id root_internal_location_id,internal_location_id,description,parent_internal_location_id
              FROM agency_internal_locations  UNION ALL
SELECT     distinct A.agy_loc_id, C.internal_location_id root_internal_location_id,A.internal_location_id,A.description,C.parent_internal_location_id
              FROM agency_internal_locations A JOIN cte c ON (c.internal_location_id = A.parent_internal_location_id)

) SELECT * FROM cte
) ail
LEFT OUTER JOIN offender_bookings bkg ON (ail.internal_location_id = bkg.agency_iml_id);

CREATE OR REPLACE VIEW v_course_activity_parties (crs_acty_party_id, crs_acty_id, party_role, party_role_desc, person_id, staff_id, registration_text, contact_text, first_name, last_name, party_role_text, seal_flag) AS SELECT /* ========================================================
             Version Number = 1.1      Date Modified = 29-JUL-2009
          ======================================================== */
/* MODIFICATION HISTORY
   Person         Date          Version         Comments
   ---------     ------        ------------      ------------------------------
   David Ng      29-Jul-2009            1.1      View Created
*/
crs_acty_party_id
,crs_acty_id
,party_role
,substr(tag_utils_get_ref_desc('PS_ROLE', party_Role),1,40) party_role_desc
,null person_id
,null staff_id
,registration_text
,contact_text
,first_name
,last_name
,party_role_text
,seal_flag
FROM  course_activity_parties
WHERE (last_name IS NOT NULL AND last_name::text <> '')

UNION ALL

SELECT
crs_acty_party_id
,crs_acty_id
,party_role
,substr(tag_utils_get_ref_desc('PS_ROLE', CAP.party_Role),1,40) party_role_desc
,CAP.person_id
,NULL staff_id
,registration_text
,contact_text
,PER.first_name
,PER.last_name
,party_role_text
,cap.seal_flag
FROM  course_activity_parties cap,
      Persons PER
WHERE coalesce(CAP.last_name::text, '') = ''
AND   PER.person_id = CAP.person_id

;

CREATE OR REPLACE VIEW v_living_unit_rolls_2 (agy_loc_id, root_living_unit_id, living_unit_id, parent_living_unit_id, allocated, in_living_units, out_of_living_units, out_of_agy, reserved_beds) AS WITH RECURSIVE cte AS (
SELECT
   /* ==============================================================================================================================
    Version Number = 2.4 Date Modified = 12/04/2012
    ================================================================================================================================ */
/* MODIFICATION HISTORY
   Person       Date        Version       Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
   David Ng     25/05/2006  2.1           Performance tuning
   GJC          31/10/2011  2.2           Performance tuning
   Bahadur      03/04/2012  2.3           HPQC# 12426 - Changes made in the view to display Reserve Beds field on prison roll screen.
   Nasir        12/04/2012  2.4           HPQC# 12426 - Remove reseve_bed_id in sub query.
*/
ail.agy_loc_id, ail.internal_location_id root_living_unit_id,ail.internal_location_id,ail.parent_internal_location_id,CASE WHEN coalesce(bkg.living_unit_id::text, '') = '' THEN  0  ELSE 1 END,CASE WHEN coalesce(bkg.AGENCY_IML_ID::text, '') = '' THEN  CASE WHEN bkg.IN_OUT_STATUS='IN' THEN  1  ELSE 0 END   ELSE 0 END,CASE WHEN coalesce(bkg.AGENCY_IML_ID::text, '') = '' THEN  0  ELSE CASE WHEN bkg.IN_OUT_STATUS='IN' THEN  1  ELSE 0 END  END,CASE WHEN bkg.IN_OUT_STATUS='OUT' THEN  1  ELSE 0 END,coalesce(res.res_count,0)
FROM --( select reserve_bed_id, count(*) res_count                  -- Commented by Bahadur on 03-Apr-2012 for HPQC#12426
                  -- Added by Bahadur on 03-Apr-2012 for HPQC#12426
 agency_internal_locations ail
LEFT OUTER JOIN offender_bookings bkg ON (ail.internal_location_id = bkg.living_unit_id AND ail.agy_loc_id = bkg.AGY_LOC_ID)
LEFT OUTER JOIN ( SELECT living_unit_id, count(*) res_count    -- Added by Bahadur on 03-Apr-2012 for HPQC#12426
from reserve_bed_locations
where RESERVE_UNTIL_DATE >= date_trunc('day', LOCALTIMESTAMP)
--group by reserve_bed_id ) res                                -- Commented by Bahadur on 03-Apr-2012 for HPQC#12426
GROUP BY living_unit_id ) res ON (ail.internal_location_id = res.living_unit_id)
WHERE (ail.unit_type IS NOT NULL AND ail.unit_type::text <> '') --AND ail.internal_location_id = res.reserve_bed_id(+)         -- Commented by Bahadur on 03-Apr-2012 for HPQC#12426
             -- Added by Bahadur on 03-Apr-2012 for HPQC#12426
  UNION ALL
SELECT
   
ail.agy_loc_id, ail.internal_location_id root_living_unit_id,ail.internal_location_id,ail.parent_internal_location_id,CASE WHEN coalesce(bkg.living_unit_id::text, '') = '' THEN  0  ELSE 1 END,CASE WHEN coalesce(bkg.AGENCY_IML_ID::text, '') = '' THEN  CASE WHEN bkg.IN_OUT_STATUS='IN' THEN  1  ELSE 0 END   ELSE 0 END,CASE WHEN coalesce(bkg.AGENCY_IML_ID::text, '') = '' THEN  0  ELSE CASE WHEN bkg.IN_OUT_STATUS='IN' THEN  1  ELSE 0 END  END,CASE WHEN bkg.IN_OUT_STATUS='OUT' THEN  1  ELSE 0 END,coalesce(res.res_count,0)
FROM 

 agency_internal_locations ail
LEFT OUTER JOIN offender_bookings bkg ON (ail.internal_location_id = bkg.living_unit_id AND ail.agy_loc_id = bkg.AGY_LOC_ID)
LEFT OUTER JOIN ( SELECT living_unit_id, count(*) res_count
from reserve_bed_locations
where RESERVE_UNTIL_DATE >= date_trunc('day', LOCALTIMESTAMP)

GROUP BY living_unit_id ) res ON (ail.internal_location_id = res.living_unit_id)
and  (ail.unit_type IS NOT NULL AND ail.unit_type::text <> '') 
  JOIN cte c ON c.internal_location_id =ail.parent_internal_location_id
) SELECT * FROM cte;


CREATE OR REPLACE VIEW v_qm_at (process_id, activity_id, name, name_desc, description, sequence, allocated_time, active_flag, expiry_date, man_act_team_id, composition_id, team_id, team_desc, qmca_allocated_time, qmca_expiry_date) AS select
/* MODIFICATION HISTORY
   Person       Date         Version      Comments
   ---------    ------       ---------    ------------------------------
   Steve        12-Jan-2010  1.1          Call tag_reference_codes for NAME_DESC
   Niko         17/12/2009   1.0          Created
*/
      qma.process_id
      ,qma.activity_id
      ,qma.name
      ,tag_reference_codes_getdesccode('QM_ACTIVITY', qma.name) NAME_DESC
      ,qma.description
      ,qma.sequence
      ,qma.allocated_time
      ,qma.active_flag
      ,qma.expiry_date
      ,qmca.man_act_team_id
      ,qmca.composition_id
      ,qmca.team_id
      ,(SELECT description
          FROM TEAMS
         WHERE team_id = qmca.team_id) team_desc
      ,qmca.allocated_time  qmca_allocated_time
      ,qmca.expiry_date     qmca_expiry_date
from qm_activities qma, qm_con_activity_teams qmca
where qma.activity_id = qmca.activity_id


;

CREATE OR REPLACE VIEW v_prop_storages (property_storage_id, agy_loc_id, capacity, level_1_label, level_1_location, level_2_label, level_2_location, level_3_label, level_3_location, description, level_1_seq, level_2_seq, level_3_seq) AS SELECT
       coalesce(PS3.PROPERTY_STORAGE_ID, coalesce(PS2.PROPERTY_STORAGE_ID,
        PS1.PROPERTY_STORAGE_ID))
     , coalesce(PS3.AGY_LOC_ID, coalesce(PS2.AGY_LOC_ID, PS1.AGY_LOC_ID))
     , coalesce(PS3.CAPACITY, coalesce(PS2.CAPACITY, PS1.CAPACITY))
     , PS1.PROPERTY_STORAGE_TYPE
     , PS1.PROPERTY_STORAGE_CODE
     , PS2.PROPERTY_STORAGE_TYPE
     , PS2.PROPERTY_STORAGE_CODE
     , PS3.PROPERTY_STORAGE_TYPE
     , PS3.PROPERTY_STORAGE_CODE
     , coalesce(PS3.DESCRIPTION, coalesce(PS2.DESCRIPTION, PS1.DESCRIPTION))
     ,  PS1.LIST_SEQ
     ,  PS2.LIST_SEQ
     ,  PS3.LIST_SEQ
 FROM agency_locations agy_loc1, property_storages ps1
LEFT OUTER JOIN property_storages ps2 ON (PS1.PROPERTY_STORAGE_ID = PS2.PARENT_STORAGE_ID)
LEFT OUTER JOIN agency_locations agy_loc2 ON (PS2.AGY_LOC_ID = AGY_LOC2.AGY_LOC_ID AND PS2.PROPERTY_STORAGE_TYPE = AGY_LOC2.PROPERTY_LEV_2_CODE)
LEFT OUTER JOIN property_storages ps3 ON (PS2.PROPERTY_STORAGE_ID = PS3.PARENT_STORAGE_ID)
LEFT OUTER JOIN agency_locations agy_loc3 ON (PS3.AGY_LOC_ID = AGY_LOC3.AGY_LOC_ID AND PS3.PROPERTY_STORAGE_TYPE = AGY_LOC3.PROPERTY_LEV_3_CODE)
WHERE PS1.AGY_LOC_ID = AGY_LOC1.AGY_LOC_ID AND PS1.PROPERTY_STORAGE_TYPE = AGY_LOC1.PROPERTY_LEV_1_CODE;

CREATE OR REPLACE VIEW v_offender_tap_roster (offender_id_display, offender_book_id, last_name, first_name, external_location, movement_date, movement_datetime, movement_type, movement_reason_code, agy_loc_id) AS SELECT
/* =========================================================
   Version Number = 1.3  Date Modified = 01/19/2007
   =========================================================
*/
/* MODIFICATION  HISTORY
   Person        Date        Version       Comments
   ---------    ------      ------------  ------------------------------
   NIRAJ       19-JAN-2007   1.1         Added Grant and Synonym.
   VISHWA      10-JAN-2007   1.0         Created the view for the screen (OIIOTARO).
*/
          o.offender_id_display offender_id_display,
          ob.offender_book_id offender_book_id,
          o.last_name last_name,
          o.first_name first_name,
          coalesce(rc.description, al.description) external_location,
          oem.movement_date movement_date,
          TO_DATE(   TO_CHAR(oem.movement_date, 'MMDDYYYY')
                   || TO_CHAR(oem.movement_time, 'HH24MISS'),
                   'MMDDYYYYHH24MISS'
                  ) AS movement_datetime,
          oem.movement_type movement_type,
          oem.movement_reason_code movement_reason_code,
          ob.agy_loc_id agy_loc_id
     FROM offender_bookings ob, offenders o, offender_external_movements oem
LEFT OUTER JOIN reference_codes rc ON (oem.to_city = rc.code AND 'CITY' = rc.domain)
LEFT OUTER JOIN agency_locations al ON (oem.to_agy_loc_id = al.agy_loc_id)
WHERE ob.booking_status = 'O' AND ob.offender_book_id = oem.offender_book_id AND ob.offender_id = o.offender_id AND ob.root_offender_id = o.root_offender_id AND oem.active_flag = 'Y' AND oem.movement_type = 'TAP' AND oem.direction_code = 'OUT';

CREATE OR REPLACE VIEW v_staff_location_roles (staff_name, last_name, first_name, birthdate, status, cal_agy_loc_id, sac_staff_id, from_date, position, role, date_to, schedule_type, hours_per_week) AS SELECT
/* =========================================================
   Version Number = 2.0   Date Modified = 05/07/2007
   ========================================================= */
/* MODIFICATION HISTORY
Author      Date        Version     Description
---------   ------      ----------  -----------------------------------
Surya       04-Jul-2007 2.0         User Admin Security - Intial Draft.
*/
          sm.last_name || ', ' || sm.first_name,
          sm.last_name,
          sm.first_name,
          sm.birthdate,
          sm.status,
          slr.cal_agy_loc_id,
          slr.sac_staff_id,
          slr.from_date,
          slr.position,
          slr.role,
          slr.to_date,
          slr.schedule_type,
          slr.hours_per_week
     FROM staff_location_roles slr,
          staff_members sm
    WHERE slr.sac_staff_id = sm.staff_id

 
;

	CREATE OR REPLACE VIEW v_schd_prison_activities (crs_acty_id, crs_sch_id, service, program_id, activity, start_time, end_time, schedule_date, agy_loc_id, agy_loc_desc, schedule_start_date, schedule_end_date, internal_location_id, internal_location_desc) AS SELECT
	/*
	============================================================
	  Version Number = 1.3          Date Modified = 08/Jul/2007
	============================================================
	MODIFICATION HISTORY
		 Person   Date          Version   Comments
		Sarah   08-JUL-2007   1.3      Changed program_category from PRISON_ACT to INST_ACT
		GJC      13-APR-2006   1.2       SYSDATE and start date condition fixed
		Erin     18-Mar-2006   1.1       Initial version
	*/
			  course_activities.crs_acty_id, course_schedules.crs_sch_id
			  ,program_services.description service, program_services.program_id
			  ,course_activities.description activity
			  ,(   TO_CHAR(date_trunc('day', LOCALTIMESTAMP),'DD-MON-YYYY')
						|| ' '
						|| TO_CHAR(course_schedules.start_time
								   , 'hh24:mi'))::timestamp start_time
			  ,(   TO_CHAR(date_trunc('day', LOCALTIMESTAMP),'DD-MON-YYYY')
						|| ' '
						|| TO_CHAR(course_schedules.end_time, 'hh24:mi' ))::timestamp
																		 end_time
			  ,course_schedules.schedule_date, course_activities.agy_loc_id
			  ,agency_locations.description agy_loc_desc
			  ,course_activities.schedule_start_date
			  ,course_activities.schedule_end_date
			  ,course_activities.internal_location_id
			  ,agency_internal_locations.description internal_location_desc
		  FROM program_services, course_activities
	LEFT OUTER JOIN course_schedules ON (course_activities.crs_acty_id = course_schedules.crs_acty_id)
	LEFT OUTER JOIN agency_locations ON (course_activities.agy_loc_id = agency_locations.agy_loc_id)
	LEFT OUTER JOIN agency_internal_locations ON (course_activities.internal_location_id = agency_internal_locations.internal_location_id)
	WHERE program_services.program_id = course_activities.program_id    AND program_services.program_class = 'PRG' AND program_services.program_category = 'INST_ACT' AND course_activities.schedule_start_date <= LOCALTIMESTAMP AND (   coalesce(course_activities.schedule_end_date::text, '') = ''
				OR course_activities.schedule_end_date > LOCALTIMESTAMP
			   )

	 
	;

CREATE OR REPLACE VIEW v_book_admin (offender_id_display, offender_id, root_offender_id, last_name, first_name, middle_name, suffix, birth_date) AS SELECT
     off_name.OFFENDER_ID_DISPLAY
    ,off_name.OFFENDER_ID
    ,off_name.ROOT_OFFENDER_ID
    ,off_name.LAST_NAME
    ,off_name.FIRST_NAME
    ,off_name.MIDDLE_NAME
    ,off_name.SUFFIX
    ,off_name.BIRTH_DATE
FROM
 offenders  off_name
 where exists (SELECT null  FROM offender_bookings off_book  WHERE
 off_book.offender_id = off_name.offender_id)

 
;

CREATE OR REPLACE VIEW v_staff_member (staff_id, assigned_caseload_id, working_stock_loc_id, working_caseload_id, user_id, badge_id, last_name, first_name, middle_name, abbreviation, position, birthdate, termination_date, update_allowed_flag, default_printer_id, suspended_flag, supervisor_staff_id, comm_receipt_printer_id, personnel_type, as_of_date, emergency_contact, role, sex_code, status, suspension_date, suspension_reason, force_password_change_flag, last_password_change_date, license_code, license_expiry_date, create_user_id, modify_user_id, title, name_sequence, queue_cluster_id, first_logon_flag, suffix) AS SELECT STAFF_ID,ASSIGNED_CASELOAD_ID
,WORKING_STOCK_LOC_ID,WORKING_CASELOAD_ID
,USER_ID,BADGE_ID,LAST_NAME,FIRST_NAME
,MIDDLE_NAME,ABBREVIATION,POSITION,BIRTHDATE
,TERMINATION_DATE,UPDATE_ALLOWED_FLAG,DEFAULT_PRINTER_ID
,SUSPENDED_FLAG,SUPERVISOR_STAFF_ID,COMM_RECEIPT_PRINTER_ID
,PERSONNEL_TYPE,AS_OF_DATE,EMERGENCY_CONTACT
,ROLE,SEX_CODE,STATUS,SUSPENSION_DATE
,SUSPENSION_REASON,FORCE_PASSWORD_CHANGE_FLAG
,LAST_PASSWORD_CHANGE_DATE,LICENSE_CODE,LICENSE_EXPIRY_DATE
,CREATE_USER_ID,MODIFY_USER_ID
,TITLE,NAME_SEQUENCE,QUEUE_CLUSTER_ID
,FIRST_LOGON_FLAG,SUFFIX
FROM STAFF_MEMBERS


 
;

CREATE OR REPLACE VIEW v_acp_schedules (program_id, program_code, program_desc, program_instance_id, program_instance_code, program_instance_desc, phase_code, phase_description, phase_provider_party_class, phase_provider_party_id, phase_provider_party_code, phase_provider_name, phase_instance_code, phase_instance_id, phase_list_seq, phase_session_length, phase_instance_desc, module_instance_id, module_list_seq, module_instance_desc, crs_sch_id, schedule_date, start_time, end_time, session_no, catch_up_session_flag, internal_location_desc, schedule_status) AS SELECT
/* =========================================================
   Version Number = 2.3 Date Modified = 17/06/2011
 ========================================================= */
-- MODIFICATION HISTORY
-- Person      Date        Comments
-- ---------   ------      -------------------------------------------
/* MODIFICATION HISTORY
   Person       Date      Version Comments
   ---------    ------     ------ ------------------------------
   Manjul       17/06/2011 2.3    Modified query for HPQC# 8431
   GJC          08/09/2006 2.2    Add column program_id
   David Ng     03/06/2006 2.1    NOMIS project(10.2.1)
   David Ng     24/08/2006 2.2    Add phase Provider info
*/
PS.program_id
,PS.Program_code
,PS.Description
,AP.crs_acty_ID       Program_instance_ID
,AP.Code
,AP.Description      Program_instance_desc
,PHPS.Program_code
,PHPS.Description
,PH.Provider_Party_Class
,PH.Provider_Party_ID
,PH.Provider_Party_Code
,Tag_Prg_Provider_Name(PH.Provider_Party_Class, PH.Provider_Party_ID, PH.Provider_Party_Code)
,PH.Code             Phase_code
,PH.crs_acty_id      Phase_instance_ID
,PH.list_seq         Phase_list_seq
,PH.SESSION_LENGTH   Phase_Session_Length
,PHPS.Description    phase_instance_desc
,MOD.crs_acty_ID     Module_instance_ID
,MOD.list_seq        Module_list_seq
,MODPS.description   module_instance_desc
,CS.CRS_SCH_ID
,CS.SCHEDULE_DATE
,CS.START_TIME
,CS.END_TIME
,CS.SESSION_NO
,CASE WHEN coalesce(CS.Catch_up_CRS_SCH_ID::text, '') = '' THEN  'N'  ELSE 'Y' END
,AIL.Description
,CS.SCHEDULE_STATUS
FROM program_services ps, program_services phps, course_activities ph, program_services modps, course_activities mod, course_schedules cs, course_activities ap
LEFT OUTER JOIN agency_internal_locations ail ON (AP.Internal_Location_ID = AIL.Internal_location_ID)
WHERE AP.crs_acty_id = PH.parent_crs_acty_ID --AND    PH.Internal_Location_ID = AIL.Internal_location_ID(+) --Commented by Manjul, HPQC# 8431
   --Commented by Manjul, HPQC# 8431
  AND PH.crs_acty_id = MOD.parent_crs_acty_id AND CS.crs_acty_ID = MOD.crs_acty_id AND PHPS.Program_id = PH.Program_id AND MODPS.Program_id = MOD.Program_id AND PS.Program_ID  = AP.Program_ID AND PS.program_category = 'ACP'
 
UNION ALL

SELECT
PS.program_id
,PS.Program_code
,PS.Description
,AP.crs_acty_ID       Program_instance_ID
,AP.Code
,AP.Description      Program_instance_desc
,PHPS.Program_code
,PHPS.Description
,PH.Provider_Party_Class
,PH.Provider_Party_ID
,PH.Provider_Party_Code
,Tag_Prg_Provider_Name(PH.Provider_Party_Class, PH.Provider_Party_ID, PH.Provider_Party_Code)
,PH.Code             Phase_code
,PH.crs_acty_id      Phase_instance_ID
,PH.list_seq         Phase_list_seq
,PH.SESSION_LENGTH   Phase_SESSION_LENGTH
,PHPS.Description    phase_instance_desc
,NULL                Module_instance_ID
,NULL                module_list_seq
,NULL                module_instance_desc
,CS.CRS_SCH_ID
,CS.SCHEDULE_DATE
,CS.START_TIME
,CS.END_TIME
,CS.SESSION_NO
,CASE WHEN coalesce(CS.Catch_up_CRS_SCH_ID::text, '') = '' THEN  'N'  ELSE 'Y' END 
,AIL.Description
,CS.SCHEDULE_STATUS
FROM program_services ps, program_services phps, course_activities ph, course_schedules cs, course_activities ap
LEFT OUTER JOIN agency_internal_locations ail ON (AP.Internal_Location_ID = AIL.Internal_location_ID)
WHERE AP.crs_acty_id = PH.parent_crs_acty_ID --AND    PH.Internal_Location_ID = AIL.Internal_location_ID(+) --Commented by Manjul, HPQC# 8431
   --Commented by Manjul, HPQC# 8431
  AND CS.crs_acty_ID = PH.crs_acty_id AND PHPS.Program_id = PH.Program_id AND PS.Program_ID  = AP.Program_ID AND PS.program_category = 'ACP'
 
;

CREATE OR REPLACE VIEW v_offender_case_identifiers (last_name, first_name, middle_name, birth_date, offender_id_display, root_offender_id, offender_id, offender_book_id, case_id, identifier_type, identifier_no, comment_text) AS SELECT
/* =========================================================
   Version Number = 4.12.0.1  Date Modified = 02/28/2002
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date       	 Version      Comments
   Jagdeep       21-FEB-2002     4.12.0.0     TR#10204 Created new view for fixing sort on OSICASID screen.
   Jagdeep       28-FEB-2002     4.12.0.1     TR#10204 Performance Issues.
   David Ng      16-JAN-2006     2.0          View with offender_cases.offender_id
*/
       offs.last_name,
       offs.first_name,
       offs.middle_name,
       offs.birth_date,
       offs.offender_id_display,
       offs.root_offender_id,
       offs.offender_id,
       off_cas.offender_book_id,
       off_ci.case_id,
       off_ci.identifier_type,
       off_ci.identifier_no,
       off_ci.identifier_no
FROM   offenders offs,
       offender_case_identifiers off_ci,
       offender_cases off_cas,
       Offender_Bookings bkg
WHERE  offs.offender_id = bkg.offender_id
AND    bkg.offender_book_id = off_cas.offender_book_ID
AND    off_cas.case_id =  off_ci.case_id

 
;
CREATE OR REPLACE VIEW v_ass_off_needs (assessment_id, off_ass_need_id, assessed_need_code, objective, target_date, active_flag, expiry_date, ass_off_code_desc) AS SELECT
/* =========================================================
   Version Number = 1.0  Date Modified = 08/11/2008
   =========================================================
   MODIFICATION HISTORY
   Person       Date              Version   Comments
   ---------    -----            ---------  ------------------------------
   Niko         08/11/2008        1.0       Created
*/
assessment_id
,off_ass_need_id
,assessed_need_code
,objective
,target_date
,active_flag
,expiry_date
,(SELECT rc.description
    FROM reference_codes rc
   WHERE DOMAIN = 'CASEPLAN_ASS'
     AND code = assessed_need_code  LIMIT 1) ass_off_code_desc
FROM assessed_offender_needs

;

CREATE OR REPLACE VIEW v_iwp_parameter_data (template_id, bookmark_name, parameter_name, parameter_type, parameter_description, parameter_data_type, parameter_value) AS SELECT g.template_id template_id, UPPER( g.bookmark_name ) bookmark_name, UPPER( q.parameter_name ) parameter_name,
       q.parameter_type parameter_type, q.description parameter_description, q.parameter_data_type parameter_data_type,
       ( SELECT parameter_value
          FROM iwp_parameter_values
         WHERE template_id = g.template_id
           AND bookmark_name = g.bookmark_name ) parameter_value
  FROM iwp_bookmarks i, iwp_bookmarks_groups g, iwp_bookmark_parameters q
 WHERE i.bookmark_name = g.bookmark_name
   AND i.bookmark_name = q.bookmark_name
   AND i.active_flag = 'Y'

;

CREATE OR REPLACE VIEW off_ap_v2 (off_action_plan_id, casework_type, casework_type_desc, off_crim_need_id, off_case_cond_id, program_id, program_desc, notes, start_date, end_date, program_category, prg_category_desc) AS SELECT
/* =========================================================
   Version Number = 1.2  Date Modified = 08/09/2010
   =========================================================
   MODIFICATION HISTORY
   Person       Date              Version   Comments
   ---------    -----            ---------  ------------------------------
   Ruxandra     08/09/2010        1.2       corrected error; outer join is needed
   Ruxandra     07/12/2010        1.1       added modified view to add columns program_category and prg_category_desc
   Niko         08/01/2008        1.0       Created
*/
oap.off_action_plan_id
,oap.casework_type
,(SELECT rc.description
    FROM reference_codes rc
   WHERE rc.domain = 'CASEPLAN_STP'
 	   AND rc.code = casework_type   LIMIT 1) casework_type_desc
,oap.off_crim_need_id
,oap.off_case_cond_id
,oap.program_id
,ps.description program_desc
,oap.notes
,oap.start_date
,oap.end_date
,ps.program_category program_category
,(SELECT reference_codes.description
    FROM reference_codes
   WHERE reference_codes.domain = 'PS_CATEGORY'
     AND reference_codes.code = ps.program_category
 ) prg_category_desc
FROM offender_action_plans oap
LEFT OUTER JOIN program_services ps ON (oap.program_id = ps.program_id)
WHERE (off_case_cond_id IS NOT NULL AND off_case_cond_id::text <> '')

;

CREATE OR REPLACE VIEW v_staff_location (staff_id, name, sex_code, cal_agy_loc_id, position, role, schedule_type, hours_per_week, from_date, supervisor_staff_id, supervisor_position, supervisor_role, supervisor_from_date, supervisor_agy_loc_id) AS SELECT
/* =========================================================
   Version Number = 4.11.0.0  Date Modified = 06/20/2001
   ========================================================= */
/* MODIFICATION HISTORY
|| Person      Date         Version       Comments
|| ---------   -----------  ------------  ------------------------------------
|| Vipul       20-JUN-2001  4.11.0.0      Created this new view for OCIPOWLO
||                                        for performance fix.
|| David Ng    20-APR-2006  10.2          remove RULE hint
|| ===========================================================================
*/
      SM.STAFF_ID,
      SM.LAST_NAME||', '||SM.FIRST_NAME ,
      SM.SEX_CODE,
      SLR.CAL_AGY_LOC_ID,
      SLR.POSITION,
      SLR.ROLE,
      SLR.SCHEDULE_TYPE,
      SLR.HOURS_PER_WEEK,
      SLR.FROM_DATE,
      SLR.SUPERVISOR_STAFF_ID,
      SLR.SUPERVISOR_POSITION,
      SLR.SUPERVISOR_ROLE,
      SLR.SUPERVISOR_FROM_DATE,
      SLR.SUPERVISOR_AGY_LOC_ID
 FROM STAFF_MEMBERS SM,
      STAFF_LOCATION_ROLES SLR
WHERE SLR.SAC_STAFF_ID = SM.STAFF_ID
  AND coalesce(SLR.TO_DATE::text, '') = ''
  AND coalesce(SM.TERMINATION_DATE::text, '') = ''


 
;

CREATE OR REPLACE VIEW v_staff_work_assignments (staff_work_id, assigned_date, workflow_type, last_name, first_name, offender_id_display, work_type, work_sub_type, details, due_date, staff_id, completed_flag, offender_book_id, team_code, modify_user_id, modify_datetime, create_date, create_user_id, team_member_id, complete_reason_code, complete_comment_text, complete_date, complete_user_id) AS SELECT
/* =========================================================
   Version Number = 2.2  Date Modified = 04/07/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     04/07/2005  2.2      NOMIS project
*/
SWA.STAFF_WORK_ID,
date_trunc('day', SWA.CREATE_DATE),
WFT.WORKFLOW_TYPE,
O.LAST_NAME,
O.FIRST_NAME,
O.OFFENDER_ID_DISPLAY,
WFT.WORK_TYPE,
WFT.WORK_SUB_TYPE,
SW.DETAILS,
SW.DUE_DATE,
TM.STAFF_ID,
SWA.COMPLETED_FLAG,
SW.OFFENDER_BOOK_ID,
SW.TEAM_CODE,
SWA.MODIFY_USER_ID,
SWA.MODIFY_DATETIME,
SWA.CREATE_DATE,
SWA.CREATE_USER_ID,
SWA.TEAM_MEMBER_ID,
SWA.COMPLETE_REASON_CODE,
SWA.COMPLETE_COMMENT_TEXT,
SWA.COMPLETE_DATE,
SWA.COMPLETE_USER_ID
FROM STAFF_WORK_ASSIGNMENTS SWA, STAFF_WORKS SW, OFFENDERS O, OFFENDER_BOOKINGS OB,   WORKFLOW_WORK_TYPES WFT,
     TEAM_MEMBERS TM
WHERE SWA.team_member_id = TM.team_member_id
AND SWA.staff_work_id = SW.staff_work_id
AND SW.WORKFLOW_TYPE = WFT.WORKFLOW_TYPE
AND SW.work_type = WFT.work_type
AND SW.work_sub_type = WFT.work_sub_type
AND SW.OFFENDER_BOOK_ID = OB.OFFENDER_BOOK_ID
AND OB.OFFENDER_ID = O.offender_id

 
;

CREATE OR REPLACE VIEW v_oic_incidents (offender_book_id, reported_staff_id, booking_no, agency_incident_id, party_seq, incident_date, int_loc_description, incident_time, incident_type, incident_type_desc, incident_details, report_date, agy_loc_id, oic_incident_id, oic_charge_flag) AS SELECT
/* =========================================================
   Version Number = 10.2.2 Date Modified = 09/10/2009
   ========================================================= */
/* MODIFICATION HISTORY
   Person          Date      Version          Comments
   ---------    ------     ---------       ------------------------------
   Johnny       09/10/2009     10.2.2       Changed ail.agy_loc_id||'  '||ail.description to just ail.description
    Neeraj Kt    22/09/2009     10.2.1        Modified location description,discarted table agency_internal_locations ail2
                                              The version = 10.2.0  Date Modified = 04/07/2005
    David Ng     30/08/2005     2.0            NOMIS project
*/
         aip.offender_book_id,
          ai.reported_staff_id,
          ob.booking_no,
          ai.agency_incident_id,
          aip.party_seq,
          ai.incident_date,
          --ail.agy_loc_id||'  '||ail.description,--@@ Modified by Neeraj,Ontime Issue 1329
          ail.description,
          ai.incident_time,
          ai.incident_type,
          SUBSTR(oms_miscellaneous_getdesccode('INC_TYPE', ai.incident_type),
                  1,
                  40
                 ),
          ai.incident_details,
          ai.report_date,
          ai.agy_loc_id,
          aip.oic_incident_id,
          (SELECT CASE WHEN COUNT(*)=0 THEN  'N'  ELSE 'Y' END
             FROM agency_incident_charges aic
            WHERE aic.agency_incident_id = aip.agency_incident_id
              AND aic.party_seq = aip.party_seq
              AND (aic.oic_charge_id IS NOT NULL AND aic.oic_charge_id::text <> ''))
     FROM agency_incidents ai,
          agency_incident_parties aip,
          agency_internal_locations ail,
          offender_bookings ob
    WHERE ai.agency_incident_id = aip.agency_incident_id
      AND ob.offender_book_id = aip.offender_book_id
      AND (aip.offender_book_id IS NOT NULL AND aip.offender_book_id::text <> '')
      AND ail.internal_location_id = ai.internal_location_id
      AND (aip.oic_incident_id IS NOT NULL AND aip.oic_incident_id::text <> '')
 
;

CREATE OR REPLACE VIEW v_om_team_members (staff_id, last_name, first_name, staff_name, sex_code, from_date, position, schedule_type, schedule_type_desc, role, cal_agy_loc_id) AS SELECT
/* =========================================================
   Version Number = 2.1  Date Modified = 09/10/2014
   ========================================================= */
/* MODIFICATION HISTORY
|| Person      Date         Version       Comments
|| ---------   -----------  ------------  ------------------------------------
|| Rajshree    09-OCT-2014  2.1           Removed validation to team
|| David Ng    02/06/2006   2.0           For OM Staff/Team Assignment
|| ===========================================================================
*/
SM.STAFF_ID
,SM.Last_Name
,SM.First_Name
,SM.Last_Name||', '||SM.First_Name
,SM.SEX_CODE
,SLR.FROM_DATE
,SLR.POSITION
,SLR.SCHEDULE_TYPE
,SUBSTR(oms_miscellaneous_getdesccode('SCHEDULE_TYP',
                                                 SLR.SCHEDULE_TYPE
                                                ),
                  1,
                  40
                 )
,SLR.ROLE
,SLR.CAL_AGY_LOC_ID
FROM STAFF_Members SM, STAFF_LOCATION_ROLES SLR
WHERE SM.Staff_ID           = SLR.sac_staff_id
AND (coalesce(SLR.TO_Date::text, '') = '' OR SLR.TO_DATE >= date_trunc('day', LOCALTIMESTAMP) )
/*AND   Exists (SELECT 'x'
              FROM   TEAM_MEMBERS TM, TEAM_FUNCTIONS TF
              WHERE TM.Team_ID            = TF.Team_ID
              AND   TF.function_type      = 'OM'
              AND   TM.Active_Flag = 'Y'
              AND   TM.Staff_ID           = SLR.sac_staff_id
              AND   TM.agy_loc_id         = SLR.cal_agy_loc_id
              AND   TM.ROLE               = SLR.role
              AND   TM.position           = SLR.position
)*/
;

CREATE OR REPLACE VIEW v_imprison_statuses (offender_book_id, imprisonment_status) AS SELECT
     off_imp.offender_book_id
    ,off_imp.imprisonment_status
FROM
    offender_imprison_statuses  off_imp
 WHERE
 off_imp. imprison_status_seq = (SELECT MAX(imprison_status_seq)
 			      FROM offender_imprison_statuses off_imp1
 			      where off_imp1.offender_book_id =off_imp.offender_book_id)

 
;

COMMENT ON VIEW v_imprison_statuses  IS E'Used to get the latest imprisonment status for an offender';

CREATE OR REPLACE VIEW v_offender_file_deliveries (transaction_id, creation_date, creation_user, file_trans_type, transaction_date, transfer_date, confirmed, file_trans_comment, agy_loc_id_from, agy_loc_id_to, staff_id_from, staff_id_to, non_officer_from, non_officer_to, offender_file_seq, offender_id, file_type, file_sub_type, offender_file_num, volume_seq, holding_staff_id, holding_agy_loc_id, non_officer_status, close_file_no, offender_id_display, offender_last_name, offender_first_name, offender_name, offender_book_id) AS SELECT
       /* =========================================================
       || Version Number = 6.1.0.0  Date Modified = 05-JUL-2002
          ========================================================= */
       oft.transaction_id
     , oft.creation_date
     , oft.creation_user
     , oft.file_trans_type
     , oft.transaction_date
     , oft.transfer_date
     , oft.confirmed
     , oft.file_trans_comment
     , oft.agy_loc_id_from
     , oft.agy_loc_id_to
     , oft.staff_id_from
     , oft.staff_id_to
     , oft.non_officer_from
     , oft.non_officer_to
     , ofc.offender_file_seq
     , ofc.offender_id
     , ofc.file_type
     , ofc.file_sub_type
     , ofc.offender_file_num
     , ofc.volume_seq
     , ofc.holding_staff_id
     , ofc.holding_agy_loc_id
     , ofc.non_officer_status
     , ofc.close_file_no
     , off.offender_id_display offender_id_display
     , off.last_name  offender_last_name
     , off.first_name offender_first_name
     , off.last_name || CASE WHEN coalesce(off.first_name::text, '') = '' THEN  NULL  ELSE ', ' || off.first_name END  offender_name
     , off_bkg.offender_book_id
  FROM offender_community_files   ofc
      ,offender_file_transactions oft
      ,offenders  off
      ,offender_bookings off_bkg
 WHERE oft.offender_id  = ofc.offender_id
   AND oft.offender_file_seq = ofc.offender_file_seq
   AND off.root_offender_id  = ofc.offender_id
   AND off_bkg.offender_id  = off.offender_id
   AND off_bkg.offender_book_id =
     ( SELECT MAX(ob.offender_book_id)
         FROM offender_bookings ob
        WHERE ob.offender_id = off_bkg.offender_id)


 
;

CREATE OR REPLACE VIEW v_offender_course_attendances (event_id, offender_book_id, offender_id, offender_id_display, first_name, last_name, offender_name, event_date, start_time, end_time, event_sub_type, event_status, comment_text, hidden_comment_text, to_internal_location_id, to_internal_location_desc, crs_sch_id, outcome_reason_code, piece_work, engagement_code, understanding_code, details, credited_hours, agreed_travel_hour, supervisor_name, behaviour_code, action_code, sick_note_received_date, sick_note_expiry_date, off_prgref_id, in_time, out_time, performance_code, reference_id, to_address_owner_class, to_address_id, event_outcome, off_crs_sch_ref_id, supervisor_staff_id, crs_appt_id, offender_course_appt_rule_id, crs_acty_id, event_type, agy_loc_id, event_class, sentence_seq, offender_sent_condition_id, program_id, program_category, program_category_desc, performance_desc, event_outcome_desc) AS select
 /* =========================================================
    Version Number = 2.3 Date Modified = 14/02/2012
    ========================================================= */
/* MODIFICATION HISTORY
   Person       Date             Version       Comments
   ---------    ------------     ---------     ------------------------------------------------------------------
   Rana         17/08/2009          2.3        HPQC# 11713, Changed  the domain name from PS_OUTCOME to PS_PA_OC for event_outcome_desc.
   David Ng     17/08/2009          2.2        Resolve key-preserved problem due to
                                               schema change
   David Ng     03/05/2006          2.1        Add Sentence Info
   David Ng     03/04/2006          2.0        NOMIS project(10.2.0)
*/
   OCA.event_id,
   OCA.offender_book_id,
   OFF.Offender_id,
   OFF.Offender_ID_DISPLAY,
   OFF.First_name,
   OFF.Last_name,
   OFF.Last_name||', '||OFF.First_name,
   OCA.event_date,
   OCA.start_time,
   OCA.end_time,
   OCA.event_sub_type,
   OCA.event_status,
   OCA.comment_text,
   OCA.hidden_comment_text,
   OCA.to_internal_location_id,
   tag_int_loc_int_loc_desc(OCA.to_internal_location_id),
   OCA.crs_sch_id,
   OCA.outcome_reason_code,
   OCA.piece_work,
   OCA.engagement_code,
   OCA.understanding_code,
   OCA.details,
   OCA.credited_hours,
   OCA.agreed_travel_hour,
   OCA.supervisor_name,
   OCA.behaviour_code,
   OCA.action_code,
   OCA.sick_note_received_date,
   OCA.sick_note_expiry_date,
   OCA.off_prgref_id,
   OCA.in_time,
   OCA.out_time,
   OCA.performance_code,
   OCA.reference_id,
   OCA.to_address_owner_class,
   OCA.to_address_id,
   OCA.event_outcome,
   OCA.off_crs_sch_ref_id,
   OCA.supervisor_staff_id,
   OCA.crs_appt_id,
   OCA.offender_course_appt_rule_id,
   OCA.crs_acty_id,
   OCA.event_type,
   OCA.agy_loc_id,
   OCA.event_class,
   OPP.Sentence_Seq,
   OPP.OFFENDER_SENT_CONDITION_ID,
   OPP.Program_ID,
   PS.Program_Category,
   SUBSTR(oms_miscellaneous_getdesccode('PS_CATEGORY',
                                                 PS.Program_Category),
                  1,
                  40
                 ),
   SUBSTR(oms_miscellaneous_getdesccode('PERFORMANCE',
                                                 OCA.performance_code),
                  1,
                  40
                 ),
   SUBSTR(oms_miscellaneous_getdesccode('PS_PA_OC',
                                                 OCA.event_outcome),
                  1,
                  40
                 )
FROM  offender_course_attendances OCA, Offender_Bookings OB, Offenders OFF,
      Offender_Program_Profiles OPP,
      Program_services PS
WHERE OCA.offender_book_id = OB.Offender_book_ID
AND   OB.Offender_id = OFF.Offender_ID
AND   OCA.off_prgref_id = OPP.off_prgref_ID
AND   OPP.Program_ID = PS.Program_ID
;

CREATE OR REPLACE VIEW v_offender_documents (offender_document_template_id, offender_book_id, module_name, document_status, description, template_code, create_datetime, create_user_id, modify_datetime, active_flag, expiry_date, list_seq, security_lock_user_id, srctype, srclocation, srcname, updatetime, local, height, width, contentlength, fileformat, contentformat, compressionformat, mimetype) AS select /* =========================================================
          Version Number = 4.11.0.0  Date Modified = 02/05/2001
          ========================================================= */
/* MODIFICATION HISTORY
   Person      Date       	Version       		Comments
   ---------       ------       	 ------------     		------------------------------
   Girish       05-FEB-2001.    4.11.0.0. 02/05/2001           Corrected the column name (FFENDER_BOOK_ID) to                                                                                     OFFENDER_BOOK_ID
*/
OFFENDER_DOCUMENT_TEMPLATE_ID
,OFFENDER_BOOK_ID
,MODULE_NAME
,DOCUMENT_STATUS
,DESCRIPTION
,TEMPLATE_CODE
,CREATE_DATETIME
,CREATE_USER_ID
,MODIFY_DATETIME
,ACTIVE_FLAG
,EXPIRY_DATE
,LIST_SEQ
,security_lock_user_id
,SRCTYPE
,SRCLOCATION
,SRCNAME
,UPDATETIME
,LOCAL
,HEIGHT
,WIDTH
,CONTENTLENGTH
,FILEFORMAT
,CONTENTFORMAT
,COMPRESSIONFORMAT
,MIMETYPE
 FROM offender_document_templates

 
;

CREATE OR REPLACE VIEW v_offender_victims (associated_party_id, offender_book_id, case_info_number, offender_id, offender_id_display, last_name, first_name, race_code, party_type, party_id, case_status) AS SELECT/* =========================================================
   Version Number = 1.2  Date Modified = 07/07/2009.
   ========================================================= */
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   ---------   ------      ------------  ------------------------------
   Niko        07-JUL-2009 1.2            Data Model change:
                                          Replaced offender_booking_id with offender_book_id
   Niko        07-DEC-2006 1.1            Modified the query by replacing the case_info_number with case_id
   Nasir       27-JUL-2006 1.0            Intial Creation
*/
 oap.associated_party_id
       , oap.offender_book_id
       , oc.case_info_number
       , o.offender_id
       , o.offender_id_display
       , o.last_name
       , o.first_name
       , o.race_code
       , oap.party_type
       , oap.party_id
       , oc.case_status
  FROM offender_associated_parties oap
       , offender_bookings ob
       , offenders o
       , offender_cases oc
 WHERE oap.offender_book_id = ob.offender_book_id
   AND ob.offender_id = o.offender_id
   AND oap.case_id    = oc.case_id
   AND oap.offender_book_id = oc.offender_book_id
   AND oap.offender_book_id = (SELECT MAX(oap1.offender_book_id)
                                 FROM offender_associated_parties oap1, offender_bookings ob1
                                WHERE oap1.party_id         = oap.party_id
                                  AND oap1.offender_book_id = ob1.offender_book_id
                                  AND ob1.root_offender_id  = ob.root_offender_id)

;

CREATE OR REPLACE VIEW v_offender_visit_schedules
AS SELECT ov.offender_visit_id,
    ov.offender_book_id AS visit_offender_book_id,
    ov.comment_text,
    ov.raised_incident_type,
    ov.raised_incident_number,
    ov.visit_date,
    ov.start_time,
    ov.end_time,
    ov.visit_type,
    ov.visit_status,
    ov.visit_internal_location_id,
    ov.agency_visit_slot_id,
    ov.agy_loc_id,
    ovv.offender_visit_visitor_id,
    ovv.offender_book_id,
        CASE
            WHEN ovv.offender_book_id = ov.offender_book_id THEN 'Y'::text
            ELSE 'N'::text
        END AS visit_owner_flag,
    ovv.event_status,
    ovv.event_outcome,
    ovv.outcome_reason_code,
    ovv.comment_text AS visitor_comment_text,
    ovv.event_id,
    tag_schedule_view_check_sum(COALESCE(GREATEST(ovv.modify_datetime, ov.modify_datetime), ovv.create_datetime)) AS check_sum,
    ov.create_datetime,
    ov.create_user_id
   FROM offender_visit_visitors ovv
     LEFT JOIN offender_visits ov ON ovv.offender_visit_id = ov.offender_visit_id
  WHERE ovv.event_id IS NOT NULL AND ovv.event_id::text <> ''::text AND COALESCE(ovv.offender_book_id, 0::bigint) > 0 AND ov.visit_date IS NOT NULL AND ov.visit_date::text <> ''::text;

CREATE OR REPLACE VIEW v_documents (template_code, template_name, create_date, create_user_id, active_flag, expiry_date, update_allowed_flag, list_seq, security_lock_user_id, srctype, srclocation, srcname, updatetime, local, height, width, contentlength, fileformat, contentformat, compressionformat, mimetype, template_type) AS select
/* =========================================================
   Version Number = 10.2.1.1  Date Modified = 14-AUG-2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person         Date      	      Version       Comments
   ---------      ------              ---------     ------------------------------
   Niko           14-AUG-2007         10.2.1.1      Added a column - TEMPLATE_TYPE
*/
  TEMPLATE_CODE
  ,TEMPLATE_NAME
  ,CREATE_DATE
  ,CREATE_USER_ID
  ,ACTIVE_FLAG
  ,EXPIRY_DATE
  ,UPDATE_ALLOWED_FLAG
  ,LIST_SEQ
  ,security_lock_user_id
  ,SRCTYPE
  ,SRCLOCATION
  ,SRCNAME
  ,UPDATETIME
  ,LOCAL
  ,HEIGHT
  ,WIDTH
  ,CONTENTLENGTH
  ,FILEFORMAT
  ,CONTENTFORMAT
  ,COMPRESSIONFORMAT
  ,MIMETYPE
  ,TEMPLATE_TYPE
  FROM document_templates

 
;

CREATE OR REPLACE VIEW v_offender_prg_wr_obligations (offender_prg_obligation_id, offender_book_id, offender_id, offender_id_display, last_name, first_name, middle_name, program_id, status, status_desc, start_date, end_date, event_type, event_sub_type, referral_date, referral_priority, referral_priority_desc, comment_text, decision_date) AS SELECT/* ========================================================
             Version Number = 1.1      Date Modified = 29-JUL-2009
          ======================================================== */
/* MODIFICATION HISTORY
   Person         Date          Version         Comments
   ---------     ------        ------------      ------------------------------
   David Ng      29-Jul-2009            1.0      View Created
*/
offender_prg_obligation_id
,OPO.offender_book_id
,BKG.offender_id
,OFR.Offender_id_display
,last_name
,first_name
,middle_name
,program_id
,OPO.status
,substr(tag_utils_get_ref_desc('OPO_WR_STAT', OPO.status), 1,40) status_desc
,start_date
,end_date
,event_type
,event_sub_type
,referral_date
,referral_priority
,substr(tag_utils_get_ref_desc('PS_PRIORITY', OPO.referral_priority),1,40) referral_priority_desc
,OPO.comment_text
,decision_date
FROM  offender_prg_obligations OPO,
      Offender_bookings BKG,
      Offenders OFR
WHERE OPO.Offender_Book_ID = BKG.Offender_book_id
AND   BKG.Offender_ID      = OFR.Offender_ID
AND   Event_Type = 'WR'

;



CREATE OR REPLACE VIEW v_work_assignment_history (task_assignment_hty_id, task_assignment_id, work_id, detail, offender_book_id, assignment_date, assignment_status, team_member_id, staff_id, staff_last_name, staff_name, staff_position, staff_role, team_id, team_code, team_description, workflow_history_id) AS SELECT
   /* =========================================================
    Version Number = 10.2.0.2 Date Modified = 09/07/2010
    ========================================================= */
   -- MODIFICATION HISTORY
   -- Person      Date        Comments
   -- ---------   ------      -------------------------------------------
       tah.task_assignment_hty_id,
       tah.task_assignment_id,
       tah.work_id,
       tah.details,
       tah.offender_book_id,
       tah.assignment_date,
       tah.assignment_status,
       tah.team_member_id,
       tah.staff_id,
       sm.last_name,
       sm.first_name || ' ' || sm.middle_name first_name,
       oms_miscellaneous_GetDescCode('STAFF_POS', tm.position) position1,
       oms_miscellaneous_GetDescCode('STAFF_ROLE', tm.role) role1,
       tah.team_id,
       t.team_code,
       t.description,
       tah.workflow_history_id
  FROM task_assignment_hty tah
LEFT OUTER JOIN staff_members sm ON (tah.staff_id = sm.staff_id)
LEFT OUTER JOIN teams t ON (tah.team_id = t.team_id)
LEFT OUTER JOIN team_members tm ON (tah.team_id = tm.team_id AND tah.staff_id = tm.staff_id);

CREATE OR REPLACE VIEW v_oms_modules (module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_user_id, modify_user_id) AS select MODULE_NAME,
         DESCRIPTION,
         MODULE_TYPE,
         PRINT_FORMAT_CODE,
         PREVIEW_FLAG,
         DEFAULT_COPY,
         APPLN_CODE,
         HELP_DIRECTORY,
         CREATE_USER_ID,
         MODIFY_USER_ID
    FROM oms_modules


 
;

CREATE OR REPLACE VIEW v_stg_set (stg_desc, stg_id) AS select STG_DESC,STG_ID
FROM (select st1.description stg_desc, st1.stg_id stg_id
from security_threat_groups st1
where st1.stg_level = 'SET'
and st1.active_flag = 'Y') alias0

 
;

CREATE OR REPLACE VIEW v_partial_search (offender_id, last_name_key, last_name_soundex, first_name_key, middle_name_key, birth_date, year_range) AS SELECT
     off_name.OFFENDER_ID
    ,off_name.LAST_NAME_KEY
    ,off_name.LAST_NAME_SOUNDEX
    ,off_name.FIRST_NAME_KEY
    ,off_name.MIDDLE_NAME_KEY
    ,to_date('01-JAN-70'::text, 'DD/MON/YYYY'::text) AS birth_date
    ,1
FROM
    offenders  off_name

 
;

CREATE OR REPLACE VIEW v_payment_plan_histories (payment_plan_id, payment_plan_seq, information_number, group_id, group_code, start_date, end_date, frequency, payment_closed_date, payment_date, transaction_date, payment_amount, offender_id, paid_amount) AS SELECT
       OPP.PAYMENT_PLAN_ID
     , OPP.PAYMENT_PLAN_SEQ
     , OPP.INFORMATION_NUMBER
     , OPP.GROUP_ID
     , OG.GROUP_CODE
     , OPP.START_DATE
     , OPP.END_DATE
     , OPP.FREQUENCY
     , OPP.PAYMENT_CLOSED_DATE
     , OPS.PAYMENT_DATE
     , PPT.TRANSACTION_DATE
     , OPS.PAYMENT_AMOUNT
     , OPP.OFFENDER_ID
     , SUM(PPT.TRANSACTION_AMOUNT) PAID_AMOUNT
 FROM --     OFFENDER_DEDUTIONS OD,
       offender_payment_plans opp, obligation_groups og, offender_payment_schedules ops
LEFT OUTER JOIN payment_plan_transactions ppt ON (OPS.PAYMENT_PLAN_ID = PPT.PAYMENT_PLAN_ID AND OPS.PAYMENT_PLAN_SEQ = PPT.PAYMENT_PLAN_SEQ)
WHERE OPP.PAYMENT_PLAN_ID = OPS.PAYMENT_PLAN_ID  AND OPP.PAYMENT_PLAN_SEQ = OPS.PAYMENT_PLAN_SEQ  AND OPS.PAYMENT_DATE = PPT.PAYMENT_DATE AND OPP.GROUP_ID = OG.GROUP_ID GROUP BY OPP.PAYMENT_PLAN_ID, OPP.PAYMENT_PLAN_SEQ,
  OPP.INFORMATION_NUMBER, OPP.GROUP_ID, OG.GROUP_CODE, OPP.START_DATE,
         OPP.END_DATE, OPP.FREQUENCY, OPP.PAYMENT_CLOSED_DATE,
  OPS.PAYMENT_DATE, PPT.TRANSACTION_DATE, OPS.PAYMENT_AMOUNT,
  OPP.OFFENDER_ID

;

COMMENT ON VIEW v_payment_plan_histories  IS E'A view of the payment plan"s transactions which inculdes : schedule date, actual paid date, schedule amount and paid a';

COMMENT ON COLUMN v_payment_plan_histories.end_date IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.group_code IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.group_id IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.frequency IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.transaction_date IS E'actual paid date';
COMMENT ON COLUMN v_payment_plan_histories.payment_plan_id IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.payment_plan_seq IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.information_number IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.start_date IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.payment_date IS E'scheduled paid date';
COMMENT ON COLUMN v_payment_plan_histories.payment_closed_date IS E' - Retrofitted';
COMMENT ON COLUMN v_payment_plan_histories.payment_amount IS E' Scheduled payment amount';
COMMENT ON COLUMN v_payment_plan_histories.offender_id IS E' - Retrofitted';
CREATE OR REPLACE VIEW v_offender_associated_parties (associated_party_id, offender_book_id, party_id, party_type, relationship_code, case_info_number, victim_email_addr, age, ethnicity, agy_corp_id, agy_corp_name, person_id, last_name, first_name, middle_name) AS SELECT /* =========================================================
   Version Number = 1.5  Date Modified = 07/07/2009.
   ========================================================= */
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   ---------   ------      ------------  ------------------------------
   Niko        07-JUL-2007 1.5           Data Model change:
                                         Replace offender_booking_id with offender_book_id
   Niko        11-DEC-2006 1.4           Replace case_info_number with case_id as per requested
   Nasir       10-OCT-2006 1.3           Use 'CORPORATE' Instead of 'CORPORATION' in where clause
   Nasir       03-OCT-2006 1.2           Make null agy_corp_id when fetching the record for person.
   Nasir       03-OCT-2006 1.1           Use union instead of decode
   Nasir       27-JUL-2006 1.0           Intial Creation
*/
oap.associated_party_id
,oap.offender_book_id
,oap.party_id
,oap.party_type
,oap.relationship_code
,oc.case_info_number
,oap.victim_email_addr
,oap.age
,oap.ethnicity
,null agy_corp_id
,null agy_corp_name
,oap.party_id person_id
,per.last_name last_name
,per.first_name first_name
,per.middle_name middle_name
FROM offender_associated_parties oap
     , persons per
	 , offender_cases oc
WHERE oap.party_id = per.person_id::varchar
AND oap.case_id    = oc.case_id
AND oap.offender_book_id = oc.offender_book_id
AND oap.party_type = 'PERSON'

union

SELECT
oap.associated_party_id
,oap.offender_book_id
,oap.party_id
,oap.party_type
,oap.relationship_code
,oc.case_info_number
,oap.victim_email_addr
,oap.age
,oap.ethnicity
,oap.party_id
,cor.corporate_name
,null
,null
,null
,null
FROM offender_associated_parties oap
   , corporates cor
   , offender_cases oc
WHERE oap.party_id = cor.corporate_id::varchar
AND oap.case_id    = oc.case_id
AND oap.offender_book_id = oc.offender_book_id
AND oap.party_type = 'CORPORATE'

union

SELECT
oap.associated_party_id
,oap.offender_book_id
,oap.party_id
,oap.party_type
,oap.relationship_code
,oc.case_info_number
,oap.victim_email_addr
,oap.age
,oap.ethnicity
,oap.party_id
,al.description
,null
,null
,null
,null
FROM offender_associated_parties oap
     , agency_locations al
   , offender_cases oc
WHERE oap.party_id = al.agy_loc_id
AND oap.case_id    = oc.case_id
AND oap.offender_book_id = oc.offender_book_id
AND oap.party_type = 'AGENCY'

;

CREATE OR REPLACE VIEW v_hot_page_alerts (offender_book_id, alert_type, alert_sub_type, required_facilities, comment_text, effective_date) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   Erin         01/09/2006 2.2           Fix version number
   GJC          29/08/2006 2.1           Rename column TYPE and SUBTYPE
   David Ng     10/08/2006 2.0           NOMIS project(10.2.0)
*/
       alerts.offender_book_id,
       oms_miscellaneous_getdesccode('ALERT', alerts.alert_type) ALERT_TYPE,
       oms_miscellaneous_getdesccode('ALERT_CODE', alerts.alert_code) ALERT_SUB_TYPE,
       '' REQUIRED_FACILITIES,
	   alerts.comment_text,
	   alerts.alert_date effective_date
  FROM offender_alerts alerts
 WHERE alerts.alert_status = 'ACTIVE'
   AND coalesce(alerts.expiry_date::text, '') = ''
/*UNION ALL
SELECT ohp.offender_book_id,
       oms_miscellaneous.getdesccode ('HEALTH', ohp.problem_type) ALERT_TYPE,
       oms_miscellaneous.getdesccode ('HEALTH_PBLM', ohp.problem_code) ALERT_SUB_TYPE,
       oms_miscellaneous.getdesccode ('HEALTH_TREAT', omt.treatment_code) REQUIRED_FACILITIES,
       omt.comment_text,
	   ohp.start_date effective_date
  FROM offender_health_problems ohp, offender_medical_treatments omt
 WHERE ohp.offender_health_problem_id = omt.offender_health_problem_id
   AND omt.end_date IS NULL
   AND ohp.problem_type = 'DISAB'*/
;

CREATE OR REPLACE VIEW v_module_table_columns (domain, ref_tables, module_name, description) AS SELECT mtc.domain,
       mtc.ref_tables,
       mt.module_name,
       om.description
FROM  oms_modules om, module_tables mt, module_tab_columns mtc
WHERE om.module_name = mt.module_name
AND mt.module_tab_id = mtc.module_tab_id

 
;

CREATE OR REPLACE VIEW v_victim_off_details (offender_booking_id, party_id, case_info_number, offence_code, county_code, description) AS SELECT DISTINCT /* =========================================================
   Version Number = 1.2  Date Modified = 09/02/2009.
   ========================================================= */
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   ---------   ------      ------------  ------------------------------
   Johnny      02-SEP-2009 1.2           Data structure changes for 10g.
   Nasir       11-OCT-2006 1.1
   Nasir       05-OCT-2006 1.0
*/
              off_case.offender_book_id,
                oap.party_id,
                off_case.case_info_number,
                off_chg.offence_code,
                ord.ISSUING_AGY_LOC_ID county_code,
                off.description
          FROM --offender_case_identifiers oci,
               orders ord, offender_cases off_case, offender_associated_parties oap, offender_charges off_chg
LEFT OUTER JOIN offences off ON (off_chg.offence_code = off.offence_code AND off_chg.statute_code = off.statute_code)
WHERE off_case.offender_book_id = ord.offender_book_id --AND off_case.case_id = oci.case_id
           --AND oci.identifier_type = 'CASE/INFO#'
  AND off_case.case_id = ord.case_id AND ord.order_type = 'AUTO' AND ord.offender_book_id = off_chg.offender_book_id --AND ord.order_id = off_chg.order_id
  AND ord.case_id = off_chg.case_id AND off_case.offender_book_id = oap.offender_book_id;

CREATE OR REPLACE VIEW v_payroll_batch_summaries_new (payroll_batch_id, offender_id, offender_book_id, compensation_code, pay_range_code, summary_number_of_units, first_name, last_name, summary_payroll_amount) AS SELECT
/* =========================================================
   Version Number = 6.1.0.0  Date Modified = 13-MAR-2002
   ========================================================= */
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   ---------   ------      ------------  ------------------------------
   Herbert Lee 13-MAR-2002 6.1.0.0       View creation script extracted from developmennt database
                                         Same script is originated from
                                         X:\TAG411\RELEASE\RMN\RMN411-0100\version35_to_49\jvpbs_nj.sql
   Joe Wong    21-FEB-2000               Remove V_PAYROLL_HEADER as join table/view because Last Name
                                         and First Name are added to Offender_Works table.
   Joe Wong    25-JAN-2000
*/
       off_wrk.payroll_batch_id, off_wrk.offender_id, off_wrk.offender_book_id, off_wrk.compensation_code, off_wrk.pay_range_code,
       SUM(off_wrk.number_of_units), off_wrk.first_name , off_wrk.last_name, SUM(off_wrk.payroll_amount)
  FROM offender_works off_wrk
 WHERE processed_flag = 'N'
   AND coalesce(summary_offender_work_id::text, '') = ''
   AND payroll_batch_id > 0
 GROUP BY off_wrk.payroll_batch_id,
          off_wrk.offender_id,
          off_wrk.first_name,
          off_wrk.last_name,
          off_wrk.offender_book_id,
          off_wrk.compensation_code,
          off_wrk.pay_range_code

 
;

CREATE OR REPLACE VIEW v_assigned_offenders (offender_id_display, offender_book_id, last_name, first_name, middle_name, sex_code, supervision_level, offender_status, start_date, position, role, sac_staff_id, case_plan_status, end_date, cal_agy_loc_id) AS SELECT /* =========================================================
       || Version Number = 4.11.0.0  Date Modified = 06/20/2001
          ========================================================= */
       O.OFFENDER_ID_DISPLAY
     , OB.OFFENDER_BOOK_ID
     , O.LAST_NAME
     , O.FIRST_NAME
     , O.MIDDLE_NAME
     , O.SEX_CODE
     , SUBSTR(OMS_INTAKE_GET_OFFENDER_SUPERVISION(CP.OFFENDER_BOOK_ID),1,40)
     , OBAL.OFFENDER_STATUS
     , CP.START_DATE
     , CP.POSITION
     , CP.ROLE
     , CP.SAC_STAFF_ID
     , CP.CASE_PLAN_STATUS
     , CP.END_DATE
     , CP.CAL_AGY_LOC_ID
 FROM  OFFENDER_BOOKING_AGY_LOCS OBAL, OFFENDERS O,
       OFFENDER_BOOKINGS OB,
       CASE_PLANS CP
WHERE  coalesce(OBAL.REMOVED_DATE::text, '') = ''
  AND  OBAL.AGY_LOC_ID  = CP.AGY_LOC_ID
  AND  OBAL.OFFENDER_BOOK_ID  = CP.OFFENDER_BOOK_ID
  AND  OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID
  AND  OB.OFFENDER_BOOK_ID = OBAL.OFFENDER_BOOK_ID
  AND  CP.CASE_PLAN_STATUS = 'ACTIVE'
  AND  O.OFFENDER_ID = OB.OFFENDER_ID


 
;

CREATE OR REPLACE VIEW v_program_modules (program_module_id, list_seq, description, program_phase_id, no_of_sessions, session_length, start_flag, active_flag) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
          program_id, list_seq, description, parent_program_id,
          no_of_sessions, session_length, start_flag, active_flag
     FROM program_services
    WHERE program_class = 'PRG_MOD'

 
;

CREATE OR REPLACE VIEW v_grievance_inquiry (off_name, offender_id_display, offender_id, offender_book_id, grievance_id, report_date, griev_type, agy_loc_id, assigned_staff_id, griev_level, days_rem) AS SELECT 	ofd.last_name || ', ' || ofd.first_name off_name,
	ofd.offender_id_display,
	ofd.offender_id,
	obk.offender_book_id,
	ofg.grievance_id,
	ofg.report_date,
	ofg.griev_type,
	ofg.agy_loc_id,
	( SELECT assigned_staff_id
	    FROM offender_grievance_txns ogt1
	   WHERE ogt1.grievance_id = ofg.grievance_id
	     AND (assigned_staff_id IS NOT NULL AND assigned_staff_id::text <> '')
	     AND txn_seq = ( SELECT max(txn_seq)
		 	       FROM offender_grievance_txns ogt2
			      WHERE ogt2.grievance_id = ofg.grievance_id
				AND (assigned_staff_id IS NOT NULL AND assigned_staff_id::text <> '')
				AND ogt2.status IN ( SELECT code
						       FROM reference_codes
						      WHERE domain = 'GRIEV_STATUS'
							AND parent_code = 'ACTIVE'
							AND active_flag = 'Y' )
			   )
	 ) assigned_staff_id,
	 ( SELECT griev_level
	     FROM offender_grievance_txns ogt3
	    WHERE ogt3.grievance_id = ofg.grievance_id
	      AND (griev_level IS NOT NULL AND griev_level::text <> '')
	      AND txn_seq = ( SELECT max(txn_seq)
			        FROM offender_grievance_txns ogt4
			       WHERE ogt4.grievance_id = ofg.grievance_id
				 AND (griev_level IS NOT NULL AND griev_level::text <> '')
				 AND ogt4.status IN ( SELECT code
						       FROM reference_codes
						      WHERE domain = 'GRIEV_STATUS'
							AND parent_code = 'ACTIVE'
							AND active_flag = 'Y' )
			   )
	 ) griev_level,
	 ( SELECT date_trunc('day', end_date)::date-date_trunc('day', LOCALTIMESTAMP)::date
	     FROM offender_grievance_txns ogt5
	    WHERE ogt5.grievance_id = ofg.grievance_id
	      AND (end_date IS NOT NULL AND end_date::text <> '')
	      AND txn_seq = ( SELECT max(txn_seq)
			        FROM offender_grievance_txns ogt6
			       WHERE ogt6.grievance_id = ofg.grievance_id
				 AND (end_date IS NOT NULL AND end_date::text <> '')
				 AND ogt6.status IN ( SELECT code
						       FROM reference_codes
						      WHERE domain = 'GRIEV_STATUS'
							AND parent_code = 'ACTIVE'
							AND active_flag = 'Y' )
			   )
	 ) days_rem
  FROM 	offenders ofd,
       	offender_bookings obk,
       	offender_grievances ofg
 WHERE 	ofd.offender_id = obk.offender_id
   AND 	obk.offender_book_id = ofg.offender_book_id
   AND  obk.active_flag = 'Y'
 ORDER BY days_rem

 
;

CREATE OR REPLACE VIEW v_offender_sentences (offender_book_id, sentence_seq, case_id, case_info_prefix, case_info_number, sentence_calc_type, sentence_calc_type_desc, sentence_category, sentence_category_desc, agy_loc_id, agy_loc_desc, total_length, consec_to_sentence_seq, start_date, end_date, no_of_unexcused_absence, sentence_status, sentence_status_desc) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   Surya        22/04/2006  2.2           Changed the Sentence status domain as per the design change.
   David Ng     07/03/2006  2.1           User domain SENTENCE_STS
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
	  OS.offender_book_id,
          OS.sentence_seq,
          oc.case_id,
          oc.case_info_prefix,
          oc.case_infO_Number,
          OS.sentence_calc_type,
          sct.description sentence_calc_type_desc,
          sct.sentence_category,
          substr(oms_miscellaneous_getDescCode('CATEGORY', sct.sentence_category),1,40),
          oc.agy_loc_id,
          al.description agy_loc_desc,
          OS.comment_text total_length,
          OS.consec_to_sentence_seq,
          OS.start_date,
          OS.end_date,
          coalesce(OS.NO_OF_UNEXCUSED_ABSENCE,0),
          OS.sentence_status,
          substr(oms_miscellaneous_getDescCode('ACTIVE_TYPE',OS.sentence_status),1,40)
     FROM sentence_calc_types sct, offender_sentences os
LEFT OUTER JOIN offender_cases oc ON (OS.case_id = oc.case_id)
LEFT OUTER JOIN orders ord ON (OS.order_ID = ord.order_ID)
LEFT OUTER JOIN agency_locations al ON (oc.agy_loc_id = al.agy_loc_id)
WHERE OS.sentence_calc_type   = sct.sentence_calc_type;

CREATE OR REPLACE VIEW v_stg_racial_makeup (stg_id, race_description, race_count) AS select
/* =========================================================
   Version Number = 1.0  Date Modified = 20/AUG/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date         Version     	 Comments
   ---------    ------        ---------  	 ------------------------------
   NIKO         20-AUG-2007   1.0          Replaced domain: ETHNICITY instead of ADD_INFO
*/
osa.stg_id,
rc.DESCRIPTION,
count(*)
FROM offenders ofd,
offender_bookings ofb,
offender_stg_affiliations osa,
reference_codes rc
where osa.active_flag = 'Y'
and osa.offender_book_id = ofb.offender_book_id
and ofb.active_flag = 'Y'
and ofb.root_offender_id = ofd.offender_id
and ofd.race_code = rc.code
and rc.domain = 'ETHNICITY'
group by osa.stg_id, rc.description

 
;

CREATE OR REPLACE VIEW v_gang_lov (gang_code, faction_code, gang_name, faction_name, active_flag, list_seq, code, description) AS SELECT
     g.gang_code
    ,f.gang_code
    ,g.gang_name
    ,f.gang_name
    ,coalesce(f.active_flag,g.active_flag)
    ,coalesce(f.list_seq,g.list_seq)
    ,coalesce(f.gang_code,g.gang_code)
    ,g.gang_name||' '||f.gang_name
FROM gangs f
LEFT OUTER JOIN gangs g ON (f.parent_gang_code = g.gang_code);

CREATE OR REPLACE VIEW v_off_grouped_payment_plans (offender_id, information_number, group_id, payment_plan_id, payment_closed_flag, min_deduction_id) AS SELECT
/* ====================================================================================
    Version Number = 1.0 Date Modified = 06/21/2001
   ==================================================================================== */
DISTINCT od.offender_id, od.information_number, od.group_id, opp.payment_plan_id, opp.payment_closed_flag,
                MIN(od.offender_deduction_id)
  FROM offender_deductions od
LEFT OUTER JOIN offender_payment_plans opp ON (od.offender_id = opp.offender_id AND od.information_number = opp.information_number AND od.group_id = opp.group_id)
WHERE EXISTS (SELECT 1
                 FROM deduction_types dt
                WHERE dt.deduction_type = od.deduction_type
                  AND dt.deduction_category IN ('FXOB', 'CROB')
                  AND dt.caseload_code IN ('BOTH', 'COMM')) --   AND od.offender_id IN (5839862, 1006205)
 GROUP BY od.offender_id, od.information_number, od.group_id, opp.payment_plan_id, opp.payment_closed_flag
 ORDER BY opp.payment_closed_flag, MIN(od.offender_deduction_id)

 
;

CREATE OR REPLACE VIEW v_offass_ass (offender_book_id, assessment_seq, assessment_date, assessment_type_id, score, assess_status, calc_sup_level_type, assess_staff_id, assess_comment_text, override_reason_text, place_agy_loc_id, overrided_sup_level_type, override_comment_text, override_staff_id, evaluation_date, next_review_date, evaluation_result_code, review_sup_level_type, review_placement_text, review_committe_code, committe_comment_text, review_place_agy_loc_id, review_sup_level_text, modify_user_id, assess_committe_code, creation_date, creation_user, approved_sup_level_type, caseload_type, medical_flag, require_approval_flag) AS SELECT OFF_ASS.OFFENDER_BOOK_ID,
       OFF_ASS.ASSESSMENT_SEQ,
       OFF_ASS.ASSESSMENT_DATE,
       OFF_ASS.ASSESSMENT_TYPE_ID,
       OFF_ASS.SCORE,
       OFF_ASS.ASSESS_STATUS,
       OFF_ASS.CALC_SUP_LEVEL_TYPE,
       OFF_ASS.ASSESS_STAFF_ID,
       OFF_ASS.ASSESS_COMMENT_TEXT,
       OFF_ASS.OVERRIDE_REASON_TEXT,
       OFF_ASS.PLACE_AGY_LOC_ID,
       OFF_ASS.OVERRIDED_SUP_LEVEL_TYPE,
       OFF_ASS.OVERRIDE_COMMENT_TEXT,
       OFF_ASS.OVERRIDE_STAFF_ID,
       OFF_ASS.EVALUATION_DATE,
       OFF_ASS.NEXT_REVIEW_DATE,
       OFF_ASS.EVALUATION_RESULT_CODE,
       OFF_ASS.REVIEW_SUP_LEVEL_TYPE,
       OFF_ASS.REVIEW_PLACEMENT_TEXT,
       OFF_ASS.REVIEW_COMMITTE_CODE,
       OFF_ASS.COMMITTE_COMMENT_TEXT,
       OFF_ASS.REVIEW_PLACE_AGY_LOC_ID,
       OFF_ASS.REVIEW_SUP_LEVEL_TEXT,
       OFF_ASS.MODIFY_USER_ID,
       OFF_ASS.ASSESS_COMMITTE_CODE,
       OFF_ASS.CREATION_DATE,
       OFF_ASS.CREATION_USER,
       OFF_ASS.APPROVED_SUP_LEVEL_TYPE,
       ASS.CASELOAD_TYPE,
       ASS.MEDICAL_FLAG,
       ASS.REQUIRE_APPROVAL_FLAG -- @@@ Bharathi 01/22/2015, HPQC24907: Added this column
  FROM OFFENDER_ASSESSMENTS OFF_ASS,
       ASSESSMENTS ASS
 WHERE OFF_ASS.ASSESSMENT_TYPE_ID = ASS.ASSESSMENT_ID
;

CREATE OR REPLACE VIEW v_historical_bookings (root_offender_id, offender_book_id, in_movement_seq, in_date, in_time, in_movement_type, in_movement_reason_code, agy_loc_id, out_date, out_time, out_movement_seq, out_movement_type, out_movement_reason_code, booking_no) AS
 SELECT
     /* =========================================================
        Version Number =1.2  Date Modified = 05/12/04
        ========================================================= */
     /* MODIFICATION HISTORY
        Person          Date           Version            Comments
        ---------      ------        ------------      ------------------------------
        Wilma                     12-May-2004      1.2             Modified grant statement and added the create public synonym statement.
        Sowmya	   01-Feb-2001       4.11.0.0     Tracking# 6005:Modified the view for proper sorting by time.
	  Sowmya	   02-May-2001	   4.11.0.1     Tracking# 4853:Truncated out_date as the form is not querying based on that column.
	  Joe	         05/30/2001	   4.11.0.2     Tracking# 7791:Add RULE Hint text so that proper INDEXES are used to speed up performance
        Girish       12/10/2002        4.14.1.1     Tracking# 14619 New Jersey performance issue due to use of hint RULE.
                                                    Removed RULE Hint which forced the use of index , but not the right ones.
     */
      ob1.root_offender_id
     , a.offender_book_id
     , a.movement_seq
     , date_trunc('day', a.movement_date)
     , to_timestamp(concat('01-JAN-2001 ',to_char(a.movement_time,'HH24:MI:SS')),'DD-MON-YYYY HH24:MI:SS')
     , a.movement_type
     , a.movement_reason_code
     , a.to_agy_loc_id
     , date_trunc('day', b.movement_date)
     , b.movement_time
     , b.movement_seq
     , b.movement_type
     , b.movement_reason_code
     , ob1.booking_no
 FROM offender_external_movements a, offender_external_movements b
 , offender_bookings ob1
 WHERE a.offender_book_id=b.offender_book_id
 AND a.movement_type = 'ADM'
 AND (b.movement_type = 'REL' OR
 b.movement_type = 'TRN')
 AND b.movement_seq = (SELECT MIN(c.movement_seq)
 FROM offender_external_movements c
 WHERE c.offender_book_id = a.offender_book_id
 AND (c.movement_type = 'REL' OR
 c.movement_type = 'TRN')
 AND c.movement_seq > a.movement_seq)
 AND ob1.offender_book_id = a.offender_book_id

UNION

 SELECT ob2.root_offender_id
 , d.offender_book_id
 , d.movement_seq
 , date_trunc('day', d.movement_date)
 , to_timestamp(concat('01-JAN-2001 ',to_char(d.movement_time,'HH24:MI:SS')),'DD-MON-YYYY HH24:MI:SS')
 , d.movement_type
 , d.movement_reason_code
 , d.to_agy_loc_id
 , CASE WHEN 1=0 THEN  date_trunc('day', LOCALTIMESTAMP)  ELSE NULL END 
 , CASE WHEN 1=0 THEN  localtimestamp ELSE NULL END 
 , CASE WHEN 1=0 THEN  0  ELSE null END 
 , ''
 , ''
 , ob2.booking_no
 FROM offender_external_movements d, offender_bookings ob2
 WHERE d.movement_type = 'ADM'
 AND NOT EXISTS (SELECT '1'
 FROM offender_external_movements e
 WHERE e.offender_book_id = d.offender_book_id
 AND (e.movement_type = 'REL'
 OR e.movement_type = 'TRN')
 AND e.movement_seq > d.movement_seq)
 AND ob2.offender_book_id = d.offender_book_id
 
UNION

 SELECT ob3.root_offender_id
 , f.offender_book_id
 , CASE WHEN 1=0 THEN  0  ELSE NULL END 
 , date_trunc('day', f.addition_date)
 , to_timestamp(concat('01-JAN-2001 ',to_char(f.addition_date,'HH24:MI:SS')),'DD-MON-YYYY HH24:MI:SS')
 , ''
 , f.reason_code
 , f.agy_loc_id
 , date_trunc('day', f.removed_date)
 , f.removed_date
 , CASE WHEN 1=0 THEN  0  ELSE NULL END 
 , ''
 , f.removed_reason_code
 , ob3.booking_no
 FROM offender_booking_agy_locs f, offender_bookings ob3
 WHERE ob3.offender_book_id = f.offender_book_id;

CREATE OR REPLACE VIEW v_pims_name_search (offender_id, offender_id_display, offender_book_id, last_name, first_name, middle_name, birth_date, active_flag, living_unit_id, root_offender_id, prison_location) AS SELECT DISTINCT
      /* =========================================================
          Version Number = 6.1.0.0  Date Modified = 02/26/2002
          ========================================================= */
      /* MODIFICATION HISTORY
        Person     	 Date       	Version     	   Comments
        ---------    ------     	------------  	 ------------------------------
        Steve        25-Nov-2008  6.1.0.2          Tr#5518 Replaced living unit description
                                                   with prison location.
        Steve        19-Nov-2008  6.1.0.1          Tr#5518 Added living unit description
        Jagdeep      26-FEB-2002  6.1.0.0          TR#11293.Duplicate records on OCINAMES.
                                                   Added distinct clause in the select statement
     */
        OFF_NAME.OFFENDER_ID
       ,OFF_NAME.OFFENDER_ID_DISPLAY
       ,OFF_BKG.OFFENDER_BOOK_ID
       ,OFF_NAME.LAST_NAME
       ,OFF_NAME.FIRST_NAME
       ,OFF_NAME.MIDDLE_NAME
       ,OFF_NAME.BIRTH_DATE
       ,OFF_BKG.ACTIVE_FLAG
       ,OFF_BKG.LIVING_UNIT_ID
       ,OFF_NAME.ROOT_OFFENDER_ID
       ,SUBSTR(Tag_Header_get_prison_location( OFF_BKG.active_flag,
                                                OFF_BKG.community_active_flag,
                                                OFF_BKG.intake_agy_loc_id,
                                                OFF_BKG.agy_loc_id,
                                                OFF_BKG.living_unit_id,
                                                OFF_BKG.agency_iml_id,
                                                OFF_BKG.offender_book_id ),
                                                0, 105 ) prison_location
   FROM offender_bookings  OFF_BKG,
        offenders  OFF_NAME,
        caseload_agency_locations CAL,
        staff_members STAFF,
        offender_booking_agy_locs OBAL
  WHERE OFF_NAME.offender_id  =  OFF_BKG.offender_id
    AND CAL.caseload_id = STAFF.working_caseload_Id
    AND STAFF.USER_ID = USER
    AND OBAL.agy_loc_id = CAL.agy_loc_id
    AND coalesce(OBAL.removed_date::text, '') = ''
    AND OBAL.Offender_book_ID = OFF_BKG.Offender_Book_ID

;

CREATE OR REPLACE VIEW v_offender_external_movements (offender_book_id, offender_id_display, last_name, first_name, movement_seq, movement_date, movement_time, internal_schedule_type, internal_schedule_reason_code, movement_type, movement_reason_code, direction_code, arrest_agency_loc_id, to_prov_stat_code, escort_code, from_agy_loc_id, to_agy_loc_id, active_flag, escort_text, comment_text, reporting_date, to_city, from_city, reporting_time) AS SELECT
/* =========================================================
   Version Number = 4.11.0.0  Date Modified = 06/06/2001
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date       	Version     	 Comments
   Jagdeep       06-JUN-2001     4.11.0.0       Craeted new view for performance issues on OIITROUT Form.
*/
OFF_EM.OFFENDER_BOOK_ID,
O.OFFENDER_ID_DISPLAY,
O.LAST_NAME,
O.FIRST_NAME,
OFF_EM.MOVEMENT_SEQ,
OFF_EM.MOVEMENT_DATE,
OFF_EM.MOVEMENT_TIME,
OFF_EM.INTERNAL_SCHEDULE_TYPE,
OFF_EM.INTERNAL_SCHEDULE_REASON_CODE,
OFF_EM.MOVEMENT_TYPE,
OFF_EM.MOVEMENT_REASON_CODE,
OFF_EM.DIRECTION_CODE,
OFF_EM.ARREST_AGENCY_LOC_ID,
OFF_EM.TO_PROV_STAT_CODE,
OFF_EM.ESCORT_CODE,
OFF_EM.FROM_AGY_LOC_ID,
OFF_EM.TO_AGY_LOC_ID,
OFF_EM.ACTIVE_FLAG,
OFF_EM.ESCORT_TEXT,
OFF_EM.COMMENT_TEXT,
OFF_EM.REPORTING_DATE,
OFF_EM.TO_CITY,
OFF_EM.FROM_CITY,
OFF_EM.REPORTING_TIME
  FROM offender_external_movements OFF_EM,
       offender_bookings OB,
       offenders O
 WHERE OFF_EM.offender_book_id = OB.offender_book_id
 AND   O.offender_id = OB.offender_id
 AND   O.root_offender_id = OB.root_offender_id


 
;

CREATE OR REPLACE VIEW v_offender_profile_statistics (ag_loc, profile_type, profile_code, male_count, female_count, other_count, total_count) AS SELECT
       OFF_BK.AGY_LOC_ID
     , OFF_PD.PROFILE_TYPE
     , OFF_PD.PROFILE_CODE
     , SUM(CASE WHEN OFF.SEX_CODE='M' THEN 1  ELSE 0 END )
     , SUM(CASE WHEN OFF.SEX_CODE='F' THEN 1  ELSE 0 END )
     , SUM(CASE WHEN OFF.SEX_CODE='F' THEN 0 WHEN OFF.SEX_CODE='M' THEN  0  ELSE 1 END )
     , COUNT(*)
 FROM
  OFFENDER_BOOKINGS OFF_BK,
  OFFENDERS OFF,
  OFFENDER_PROFILE_DETAILS OFF_PD
  WHERE   OFF_BK.ACTIVE_FLAG = 'Y' AND
   OFF_BK.OFFENDER_BOOK_ID = OFF_PD.OFFENDER_BOOK_ID AND
          OFF_BK.OFFENDER_ID  = OFF.OFFENDER_ID
  GROUP BY OFF_BK.AGY_LOC_ID, OFF_PD.PROFILE_TYPE,OFF_PD.PROFILE_CODE

 
;

CREATE OR REPLACE VIEW v_oic_hearing_results (oic_hearing_id, result_seq, oic_charge_id, oic_offence_category, oic_offence_code, oic_offence_type, oic_offence_description, plea_description, finding_description, oic_ofn_type_desc, result_oic_offence_category, result_oic_offence_code, result_offence_type, result_oic_offence_description, result_oic_ofn_type_desc) AS SELECT /* =========================================================
             Version Number = 10.2.2  Date Modified = 19/09/2014
             ========================================================= */
          /* MODIFICATION HISTORY
             Person       Date      Version       Comments
             ---------    ------     ---------    ------------------------------
             Kumar        19/09/2014  2.2           Added oic_result_offence_id and related fields
             Vikas Gupta  07/04/2007  2.1           Changes as per 10g compliance
             David Ng     01/09/2005  2.0           NOMIS project
          */
          ohr.oic_hearing_id,
          ohr.result_seq,
          aic.oic_charge_id,
          oo1.oic_offence_category,
          oo1.oic_offence_code,
          oo1.oic_offence_type,
          oo1.description,
          SUBSTR(
             oms_miscellaneous_getdesccode('OIC_FINDING',
                                            ohr.plea_finding_code),
             1,
             40)
             plea_description,
          SUBSTR(
             oms_miscellaneous_getdesccode('OIC_FINDING', ohr.finding_code),
             1,
             40)
             finding_description,
          SUBSTR(
             oms_miscellaneous_getdesccode('OIC_OFN_TYPE',
                                            oo1.oic_offence_type),
             1,
             40)
             oic_ofn_type_desc,
          oo2.oic_offence_category result_oic_offence_category,
          oo2.oic_offence_code result_oic_offence_code,
          oo2.oic_offence_type result_oic_offence_type,
          oo2.description result_description,
          SUBSTR(
             oms_miscellaneous_getdesccode('OIC_OFN_TYPE',
                                            oo2.oic_offence_type),
             1,
             40)
             result_oic_ofn_type_desc
     FROM oic_hearing_results ohr,
          agency_incident_charges aic,
          oic_offences oo1,
          oic_offences oo2
    WHERE     ohr.agency_incident_id = aic.agency_incident_id
          AND ohr.charge_seq = aic.charge_seq
          AND oo1.oic_offence_id = ohr.oic_offence_id
          AND oo2.oic_offence_id = ohr.oic_result_offence_id
;
CREATE OR REPLACE VIEW v_incident_off_participants (agency_incident_id, offender_id_display, offender_book_id, booking_no, offender_incident_role, offender_incident_role_desc, party_added_datetime, action_code, action_desc, comment_text) AS SELECT
/* =========================================================
   Version Number = 1.0  Date Modified = 10-DEC-2020
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date      Version        Comments
   ---------    ------     ---------     ------------------------------
   Syscon     10-DEC-2020  1.0           Incident Report: incident offenders participants
*/
ai.agency_incident_id,
offs.offender_id_display,
bkg.offender_book_id,
bkg.booking_no,
aip.incident_role as offender_incident_role,
rfc_off_role.description as offender_incident_role_desc,
aip.party_added_date as party_added_datetime,
aip.action_code,
rfc_action.description as action_desc,
aip.comment_text
FROM agency_incidents ai, agency_incident_parties aip,
     offender_bookings bkg, offenders offs,
     reference_codes rfc_off_role, reference_codes rfc_action
WHERE ai.agency_incident_id = aip.agency_incident_id
  AND aip.offender_book_id = bkg.offender_book_id
  AND bkg.offender_id = offs.offender_id
  AND aip.incident_role = rfc_off_role.code
  AND rfc_off_role.domain = 'INC_OFF_PAR'
  AND aip.action_code = rfc_action.code
  AND rfc_action.domain = 'INC_DECISION';

CREATE OR REPLACE VIEW v_offender_deductions (offender_deduction_id, deduction_type, caseload_id, offender_id, fifo_flag, unlimited_deduction, max_total_amount, max_monthly_amount, internal_priority_no, external_priority_no, deduction_priority, deduction_amount, credit_limit, deduction_status, adjustment_reason_code, effective_date, information_number, payee_person_id, payee_corporate_id, account_code, delay_recapture, pay_deduction_flag, max_recursive_amount, profile_caseload_id, indigent_mandatory_flag, adjustment_amount) AS SELECT
/* ====================================================================
   Version Number =1.1   Date Modified = 05/16/2003
   -- Patrick      16-MAY-2003    Added ADJUSTMENT_AMOUNT column
   ==================================================================== */
       OD.OFFENDER_DEDUCTION_ID
     , OD.DEDUCTION_TYPE
     , OD.CASELOAD_ID
     , OD.OFFENDER_ID
     , CDP.FIFO_FLAG
     , CASE WHEN coalesce(OD.max_total_amount::text, '') = '' THEN                       CASE WHEN coalesce(OD.max_monthly_amount::text, '') = '' THEN     	               CASE WHEN coalesce(OD.max_recursive_amount::text, '') = '' THEN 'Y'  ELSE 'N' END   ELSE 'N' END   ELSE 'N' END
     , OD.MAX_TOTAL_AMOUNT
     , OD.MAX_MONTHLY_AMOUNT
     , CDP.INTERNAL_PRIORITY_NO
     , CDP.EXTERNAL_PRIORITY_NO
     , OD.DEDUCTION_PRIORITY
     , OD.DEDUCTION_AMOUNT
     , OD.CREDIT_LIMIT
     , OD.DEDUCTION_STATUS
     , OD.ADJUSTMENT_REASON_CODE
     , OD.EFFECTIVE_DATE
     , OD.INFORMATION_NUMBER
     , OD.PAYEE_PERSON_ID
     , OD.PAYEE_CORPORATE_ID
     , CDP.ACCOUNT_CODE
     ,CDP.DELAY_RECAPTURE
     ,OD.PAY_DEDUCTION_FLAG
     ,OD.MAX_RECURSIVE_AMOUNT
     ,CDP.CASELOAD_ID profile_caseload_id
     , coalesce(indigent_mandatory_flag,'N') indigent_mandatory_flag
     , OD.ADJUSTMENT_AMOUNT
 FROM CASELOAD_DEDUCTION_PROFILES CDP,
   OFFENDER_DEDUCTIONS OD
  WHERE  CDP.DEDUCTION_TYPE = OD.DEDUCTION_TYPE
 
 
;

CREATE OR REPLACE VIEW v_offender_fingerprints (bio_id, root_offender_id, position, sequence, description, score, mrs_updated, status, status_desc, last_scan_date) AS SELECT
   /* =========================================================
    Version Number = 2.3 Date Modified = 01-June-2013
    ========================================================= */
/* MODIFICATION HISTORY
Person     	   Date         Version     	 Comments
Patrick Cheung  01-Jun-2013  2.3           Added sequence column back in the veiw.
Steven Chang    11/07/2011   2.2           HPQC# 9006 Added Sequence for the order of display on the Forms
Patrick Cheung  16-10-2009   2.1           Fixed defect 1586.  To add column LAST_SCAN_DATE
Patrick Cheung  01-10-2009   2.0           Created for biometrics development   */
biometric.subject_id, off_finger.root_offender_id, off_finger.POSITION1, off_finger.SEQUENCE1, off_finger.description,
       biometric.sample_score, biometric.mrs_updated, CASE WHEN coalesce(biometric.sample_score::text, '') = '' THEN  'UNDEFINED'  ELSE 'USABLE' END ,
       oms_miscellaneous_getdesccode('FINGER_STS', CASE WHEN coalesce(biometric.sample_score::text, '') = '' THEN  'UNDEFINED'  ELSE 'USABLE' END ),
       elite_biometrics_get_last_scan_date(biometric.subject_id, off_finger.POSITION1::bigint)
 FROM (SELECT rc.code, rc.description, (rc.code)::numeric  POSITION1, (rc.list_seq)::numeric  SEQUENCE1, o.root_offender_id, o.offender_id
          FROM reference_codes rc, offenders o
         WHERE rc.domain = 'FINGER_ENR' AND rc.active_flag = 'Y' AND o.root_offender_id = o.offender_id) off_finger
LEFT OUTER JOIN (SELECT bs.subject_id subject_id, bs.root_offender_id root_offender_id, bs.ntemplate_update mrs_updated,
               bm.sample_score sample_score, bm.POSITION POSITION1
          FROM bio_subjects bs, bio_fp_samples bm
         WHERE bs.subject_id = bm.subject_id AND (bs.root_offender_id IS NOT NULL AND bs.root_offender_id::text <> '') AND bm.mrs_flag = 'Y') biometric ON (off_finger.POSITION1 = biometric.POSITION1 AND off_finger.root_offender_id = biometric.root_offender_id);

CREATE OR REPLACE VIEW v_summary_case_plan (type, offender_book_id, case_plan_id, issue, casework_type, casework_type_desc, program_desc, notes, start_date, end_date) AS SELECT
/* =========================================================
   Version Number = 1.1  Date Modified = Jan/01/2009
   =========================================================
   MODIFICATION HISTORY
   Person       Date              Version   Comments
   ---------    -----            ---------  ------------------------------
   Niko         Jan/28/2009       1.1       Bug fixed on issue#470
                                            Summary View Of Action Plans: Issue field is populated with code.
   Niko         Aug/01/2008       1.0       Created
*/
       CASE WHEN coalesce(cc_off_case_cond_id::text, '') = '' THEN 'Criminogenic Need'  ELSE 'Condition' END  TYPE1
      ,CASE WHEN coalesce(cc_offender_book_id::text, '') = '' THEN cn_offender_book_id  ELSE cc_offender_book_id END  offender_book_id
      ,CASE WHEN coalesce(cc_case_plan_id::text, '') = '' THEN cn_case_plan_id  ELSE cc_case_plan_id END  case_plan_id
      ,CASE WHEN coalesce(cn_assessed_need_code::text, '') = '' THEN (SELECT cc.description			                                      FROM community_conditions cc    	                                  	 WHERE comm_condition_type = cc_comm_condition_type		    	                                   AND comm_condition_code = cc_comm_condition_code			                                       AND category_type       = cc_comm_category_type)                                         ELSE (SELECT DESCRIPTION                                            FROM REFERENCE_CODES                                           WHERE DOMAIN = 'CASEPLAN_ASS'                                             AND CODE = cn_assessed_need_code   LIMIT 1) END  issue
      ,CASE WHEN coalesce(ap_cn_casework_type::text, '') = '' THEN ap_cc_casework_type  ELSE ap_cn_casework_type END  casework_type
      ,CASE WHEN coalesce(ap_cc_casework_type::text, '') = '' THEN (SELECT rc.description                                            FROM reference_codes rc                                           WHERE rc.domain = 'CASEPLAN_STP'                                             AND rc.code = ap_cn_casework_type)                                          ELSE (SELECT rc.description                                            FROM reference_codes rc                                           WHERE rc.domain = 'CASEPLAN_STP'                                             AND rc.code = ap_cc_casework_type) END  casework_type_desc
      ,CASE WHEN coalesce(ap_cc_program_id::text, '') = '' THEN (SELECT ps.description                                        FROM program_services ps                                       WHERE Ps.active_flag ='Y'                                         AND ps.program_id = ap_cn_program_id )                                      ELSE (SELECT ps.description                                        FROM program_services ps                                       WHERE ps.active_flag ='Y'                                         AND ps.program_id = ap_cc_program_id ) END  program_desc
      ,CASE WHEN coalesce(cc_off_case_cond_id::text, '') = '' THEN ap_cn_notes  ELSE ap_cc_notes END  notes
      ,CASE WHEN coalesce(cc_off_case_cond_id::text, '') = '' THEN ap_cn_start_date  ELSE ap_cc_start_date END  start_date
      ,CASE WHEN coalesce(cc_off_case_cond_id::text, '') = '' THEN ap_cn_end_date  ELSE ap_cc_end_date END  end_date
FROM (SELECT occ.off_case_cond_id           cc_off_case_cond_id
            ,null                           cn_off_crim_need_id
            ,occ.offender_book_id           cc_offender_book_id
            ,occ.case_plan_id               cc_case_plan_id
            ,null                           cn_offender_book_id
            ,null                           cn_case_plan_id
            ,null                           cn_assessed_need_code
            ,occ.offender_sent_condition_id cc_offender_sent_condition_id
            ,occ.objective                  cc_objective
            ,null                           cn_objective
            ,occ.comm_condition_type        cc_comm_condition_type
            ,occ.comm_condition_code        cc_comm_condition_code
            ,occ.category_type              cc_comm_category_type
            ,occ.start_date                 cc_start_date
            ,null                           cn_target_date
            ,occ.end_date                   cc_end_date
            ,null                           cn_end_date
            ,null                           cn_status_code
            ,occ.condition_status           cc_condition_status
            ,oap.off_action_plan_id         ap_cc_off_action_plan_id
            ,oap.off_crim_need_id           ap_cc_off_crim_need_id
            ,oap.off_case_cond_id           ap_cc_off_case_cond_id
            ,oap.casework_type              ap_cc_casework_type
            ,oap.program_id                 ap_cc_program_id
            ,oap.notes                      ap_cc_notes
            ,oap.start_date                 ap_cc_start_date
            ,oap.end_date                   ap_cc_end_date
            ,null                           ap_cn_off_action_plan_id
            ,null                           ap_cn_off_crim_need_id
            ,null                           ap_cn_off_case_cond_id
            ,null                           ap_cn_casework_type
            ,null                           ap_cn_program_id
            ,null                           ap_cn_notes
            ,null                           ap_cn_start_date
            ,null                           ap_cn_end_date
        FROM offender_case_conditions occ
LEFT OUTER JOIN offender_action_plans oap ON (occ.off_case_cond_id = oap.off_case_cond_id)
WHERE coalesce(oap.off_crim_need_id::text, '') = ''

UNION

   SELECT    NULL
            ,ocn.off_crim_need_id          cn_off_crim_need_id
            ,NULL
            ,NULL
            ,ocn.offender_book_id          cn_offender_book_id
            ,ocn.case_plan_id              cn_case_plan_id
            ,ocn.assessed_need_code        cn_assessed_need_code
            ,NULL
            ,NULL
            ,ocn.objective                 cn_objective
            ,NULL
            ,NULL
            ,NULL
            ,NULL
            ,ocn.target_date               cn_target_date
            ,NULL
            ,ocn.end_date                  cn_end_date
            ,ocn.status_code               cn_status_code
            ,NULL
            ,NULL
            ,NULL
            ,NULL
            ,NULL
            ,NULL
            ,NULL
            ,NULL
            ,NULL
            ,oap1.off_action_plan_id       ap_cn_off_action_plan_id
            ,oap1.off_crim_need_id         ap_cn_off_crim_need_id
            ,oap1.off_case_cond_id         ap_cn_off_case_cond_id
            ,oap1.casework_type            ap_cn_casework_type
            ,oap1.program_id               ap_cn_program_id
            ,oap1.notes                    ap_cn_notes
            ,oap1.start_date               ap_cn_start_date
            ,oap1.end_date                 ap_cn_end_date
FROM offender_criminogenic_needs ocn
LEFT OUTER JOIN offender_action_plans oap1 ON (ocn.off_crim_need_id = oap1.off_crim_need_id)
WHERE coalesce(oap1.off_case_cond_id::text, '') = '' ) alias18
 
;


CREATE OR REPLACE VIEW v_course_modules (course_module_id, course_phase_id, program_module_id, module_course_activity_type, list_seq, schedule_start_date, schedule_end_date, services_address_id, internal_location_id, comment_text, caseload_type, no_of_sessions, session_length) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version  Comments
   David Ng     09/08/2006 2.2     Add To_internal_location_ID and Address ID
   David Ng     04/08/2006 2.1     Add Module course activity Type
   David Ng     03/01/2006 2.0     NOMIS project(10.2.0)
*/
          crs_acty_id, parent_crs_acty_id, program_id, course_activity_type, list_seq,
          schedule_start_date, schedule_end_date,
          Services_Address_ID, Internal_location_ID,
          comment_text, caseload_type,
          no_of_sessions, session_length
     FROM COURSE_ACTIVITIES
    WHERE course_class = 'CRS_MOD'

 
;

CREATE OR REPLACE VIEW v_phones (phone_id, owner_class, owner_id, owner_seq, owner_code, phone_type, phone_area, phone_no, ext_no) AS SELECT
/* =========================================================
   Version Number = 1.3  Date Modified = 06/21/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     06/21/2005  1.3      NOMIS new addresses table
*/
PHONE_ID
,OWNER_CLASS
,OWNER_ID,OWNER_SEQ
,OWNER_CODE
,PHONE_TYPE
,null
,PHONE_NO
,EXT_NO
FROM PHONES

 
;

CREATE OR REPLACE VIEW v_offender_sent_cond_acts (program_activity_status, case_info_number, event_id, court_agy_loc_id, court_name, order_id, offender_book_id, sentence_seq, sentence_calc_type, sentence_category, sentence_status, sentence_start_date, sentence_end_date, sentence_status_desc, sentence_program_method, sentence_desc, offender_sent_condition_id, condition_start_date, condition_end_date, comm_condition_type, comm_condition_code, comm_program_method, length, length_unit, condition_length, credited_units, activity_desc, activity_code, condition_desc, sent_cond_desc, program_id, program_category, check_sum, record_source, condition_status) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   Sarah        18/08/2008 2.9          Added Category type
   GJC          29/08/2007 2.8          Defect 7508
   GJC          30/05/2007 2.7          Defect 6857
   GJC          25/04/2007 2.6          Remove offender_sent_cond_acts
   EBates       05/04/2007 2.5          Added condition_status field
   Mcox         12/03/2007 2.4          Added FORCE and fixed version info.
   David Ng     03/04/2006 2.1          NOMIS project(10.2.0)
*/
 osc.activity_status
,oc.case_info_number
,ce.event_ID
,ce.agy_loc_id  COURT_AGY_LOC_ID
,al.description COURT_NAME
,o.Order_ID
,os.Offender_Book_ID
,os.Sentence_seq
,os.Sentence_Calc_type
,os.Sentence_category
,os.Sentence_Status
,os.Start_date Sentence_Start_Date
,os.End_Date   Sentence_End_date
,os.Sentence_Status Sentence_status_Desc
,sct.Program_method
,sct.Description Sentence_desc
,osc.offender_sent_condition_id
,CASE WHEN os.sentence_category = '1991' AND (coalesce(sct.Program_method,'**') = 'UW' OR coalesce(CC.program_method,'**') = 'UW')
THEN
 os.start_date
ELSE
 osc.start_date
END condition_start_date
,osc.expiry_date  Condition_End_Date
,CC.comm_condition_type
,CC.comm_condition_code
,CC.Program_method
,osc.LENGTH
,osc.Length_UNIT
,CASE
WHEN os.sentence_category = '1991' AND (coalesce(sct.Program_method,'**') = 'UW' OR coalesce(CC.program_method,'**') = 'UW')
THEN
 TAG_PRG_OBLIGATION_LENGTH(os.Offender_book_ID, os.Sentence_seq)
ELSE
 CASE WHEN coalesce(osc.Offender_Sent_condition_ID::text, '') = '' THEN  TAG_PRG_OBLIGATION_LENGTH(os.Offender_book_ID, os.Sentence_seq)  ELSE osc.LENGTH::varchar||' '||osc.Length_Unit END
END condition_length
,tag_prg_credit_hours(os.Offender_book_id, os.sentence_seq, osc.offender_sent_condition_id) Credited_Units
,SUBSTR(oms_miscellaneous_getdesccode('COND_ACT', osc.Activity_code),1,40)
,osc.Activity_code
,CC.description
,coalesce(CC.description,sct.Description)
,osc.Program_ID
,PS.Program_category
,Tag_schedule_check_sum(coalesce(osc.MODIFY_DATETIME, osc.CREATE_DATETIME))
,'OFF_SCO'
,osc.condition_status
FROM sentence_calc_types sct, agency_locations al, offender_sentences os
LEFT OUTER JOIN offender_sent_conditions osc ON (os.offender_book_id = osc.Offender_book_id AND os.sentence_seq = osc.sentence_seq)
LEFT OUTER JOIN orders o ON (os.order_id = o.order_ID)
LEFT OUTER JOIN community_conditions cc ON (osc.comm_condition_type = CC.comm_condition_type AND osc.comm_condition_code = CC.comm_condition_code AND osc.category_type = CC.category_type)
LEFT OUTER JOIN program_services ps ON (osc.Program_ID = PS.Program_ID)
LEFT OUTER JOIN court_events ce ON (o.event_id = ce.event_id)
LEFT OUTER JOIN offender_cases oc ON (o.case_ID = oc.case_ID)
WHERE ce.agy_loc_id         = al.agy_loc_id AND os.Sentence_calc_type = sct.sentence_calc_type AND os.sentence_category  = sct.sentence_category;

CREATE OR REPLACE VIEW journal_table_view (table_name, status) AS SELECT table_name,
          status
   FROM  all_triggers
   WHERE trigger_name LIKE '%JN'
;

CREATE OR REPLACE VIEW v_oms_module_parameers (module_name, parameter_seq, parameter_code, parameter_name, parameter_type, parameter_domain, optional_flag, comment_text, multivalue_flag, create_user_id, modify_user_id) AS select MODULE_NAME,
         PARAMETER_SEQ,
         PARAMETER_CODE,
         PARAMETER_NAME,
         PARAMETER_TYPE,
         PARAMETER_DOMAIN,
         OPTIONAL_FLAG,
         COMMENT_TEXT,
         MULTIVALUE_FLAG,
         CREATE_USER_ID,
         MODIFY_USER_ID
    FROM oms_module_parameters


 
;

CREATE OR REPLACE VIEW v_oic_hearings (oic_hearing_id, oic_hearing_type, oic_hearing_type_desc, hearing_date, hearing_time, hearing_staff_name, comment_text, int_loc_description, representative_text, oic_incident_id) AS SELECT
/* =========================================================
   Version Number = 10.2.0.1.2  Date Modified = 10/19/2009
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date      Version       Comments
   ---------    ------     ---------     ------------------------------
   Johnny       10/19/2009  10.2.0.1.2    QC#1646 same as QC# 1329, Fixed agy location to display the loweset description only without displaying parent locations.
   Rose Zhang   12/14/2007  10.2.0.1.1    #4416 Fixed bug that Comma displayed in 'Heard by Staff Name' column when no staff name exists.
   David Ng     01/09/2005  2.0           NOMIS project
*/
OH.OIC_HEARING_ID
,OH.OIC_HEARING_TYPE
,substr(oms_miscellaneous_getDescCode('OIC_HEAR',OH.OIC_HEARING_TYPE),1,40) OIC_HEARING_TYPE_DESC
,OH.HEARING_DATE
,OH.HEARING_TIME
,STF.LAST_NAME||CASE WHEN coalesce(STF.LAST_NAME::text, '') = '' THEN  NULL  ELSE CASE WHEN coalesce(STF.FIRST_NAME::text, '') = '' THEN  NULL  ELSE ',' END  END ||STF.FIRST_NAME HEARING_STAFF_NAME -- Rose, #4416
,OH.COMMENT_TEXT
/*,ail.Agy_loc_id||'  '||decode(ail2.description, NULL, NULL,ail2.description||'')||ail.description INT_LOC_DESCRIPTION*/
 -- Johnny QC# 1329, commented out and use description only
,AIL.description int_loc_description
,OH.REPRESENTATIVE_TEXT
,OH.OIC_INCIDENT_ID
FROM agency_internal_locations ail, oic_hearings oh
LEFT OUTER JOIN staff_members stf ON (OH.HEARING_STAFF_ID = STF.STAFF_ID)
WHERE AIL.INTERNAL_LOCATION_ID = OH.INTERNAL_LOCATION_ID

;

CREATE OR REPLACE VIEW v_offender_offences (offender_book_id, sentence_seq, offender_charge_id, offence_description, most_serious_flag, plea_code, property_value, offence_date, offence_range_date) AS SELECT
/* =========================================================
   Version Number = 10.2.1.0  Date Modified = 14/01/2010
   ========================================================= */
/* MODIFICATION HISTORY
Author         Date               Version    Description
---------      ----------         --------   --------------------------------
Erin           14/01/2010         10.2.1.0   Migrate to Elite
Surya          26/09/2006         2.0        Created View for module OCDSREQS
*/
	    a.offender_book_id,
          a.sentence_seq,
          a.offender_charge_id,
          c.description,
          b.most_serious_flag,
          d.description plea_code,
          b.property_value,
          b.offence_date,
          b.offence_range_date
     FROM offender_sentence_charges a, offender_charges b
LEFT OUTER JOIN offences c ON (b.offence_code = c.offence_code AND b.statute_code = c.statute_code)
LEFT OUTER JOIN reference_codes d ON (b.plea_code = d.code AND 'PLEA_STATUS' = d.domain)
WHERE a.offender_book_id = b.offender_book_id AND a.offender_charge_id = b.offender_charge_id     ORDER BY 1,2,3

;

CREATE OR REPLACE VIEW v_offender_visit_schedules_2 (offender_visit_id, visit_offender_book_id, comment_text, raised_incident_type, raised_incident_number, visit_date, start_time, end_time, visit_type, visit_status, visit_internal_location_id, agency_visit_slot_id, agy_loc_id, offender_visit_visitor_id, offender_book_id, visit_owner_flag, event_status, event_outcome, outcome_reason_code, visitor_comment_text, event_id, check_sum, create_datetime, create_user_id) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   Jason Xu     5/April/2013 1.0         copy from V_OFFENDER_VISIT_SCHEDULES 2.3.1.0 for SD form
                                         OIIWLTWJ performance issue
   Vikas Grover 30/Mar/2007 2.3.1.0      TAG10gR2 : Columns dropped from Table which are not rqd. in TAG
   GJC          15/01/2007 2.3           Defect 4473 add create_datetime and create_user_id
   GJC          13/12/2006 2.2           Replace tag_schedule with tag_schedule_view
   GJC          20/11/2006 2.1           Added NVL around OVV.Offender_Book_ID in where clause
   David Ng     07/07/2006 2.0           NOMIS project(10.2.0)
*/
   OV.offender_visit_id,
   OV.Offender_Book_ID Visit_Offender_Book_ID,
   OV.comment_text,
   OV.raised_incident_type,
   OV.raised_incident_number,
   OV.visit_date,
   OV.start_time,
   OV.end_time,
   OV.visit_type,
   OV.visit_status,
   OV.visit_internal_location_id,
   OV.agency_visit_slot_id,
   OV.agy_loc_id,
   OVV.Offender_Visit_Visitor_ID,
   OVV.offender_book_id,
   CASE WHEN OVV.offender_book_id=OV.Offender_Book_ID THEN  'Y'  ELSE 'N' END ,
   OVV.EVENT_STATUS,
   OVV.Event_Outcome,
   OVV.outcome_reason_code,
   OVV.Comment_text,
   OVV.event_id,
   Tag_Schedule_view_check_sum(coalesce(GREATEST(OVV.MODIFY_DATETIME,OV.MODIFY_DATETIME),OVV.CREATE_DATETIME)),
   OV.create_datetime,
   OV.create_user_id
FROM offender_ind_sch_wait_lists wl, offender_visit_visitors ovv
LEFT OUTER JOIN offender_visits ov ON (OVV.Offender_visit_id = OV.Offender_Visit_id)
WHERE (OVV.Event_ID IS NOT NULL AND OVV.Event_ID::text <> '') AND coalesce(OVV.Offender_Book_ID,0) > 0 AND (OV.Visit_Date IS NOT NULL AND OV.Visit_Date::text <> '') AND OVV.event_id = WL.event_id

;

CREATE OR REPLACE VIEW v_member_skills (staff_id, last_name, first_name, sex_code, agy_loc_id, position, role, schedule_type, status) AS SELECT
/* =========================================================
   Version Number = 1.6  Date Modified = 25/APR/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   Sarah          25/04/2007  1.6       separated phone and internet tables from the view
   David Ng     16/06/2005  1.5       NOMIS new phones table
*/
     sm.STAFF_ID
    ,sm.LAST_NAME
    ,sm.FIRST_NAME
    ,sm.SEX_CODE
    ,slr.CAL_AGY_LOC_ID
    ,slr.POSITION
    ,slr.ROLE
    ,slr.SCHEDULE_TYPE
    ,CASE WHEN coalesce(slr.TO_DATE::text, '') = '' THEN 'Y'  ELSE 'N' END
FROM staff_members sm
LEFT OUTER JOIN staff_location_roles slr ON (sm.staff_id = slr.sac_staff_id);

CREATE OR REPLACE VIEW v_offender_payment_schedules (payment_plan_id, payment_plan_seq, payment_date, payment_amount, recursive_amount, paid_amount, paid_recursive_amount, information_number, offender_deduction_id, group_id, modify_user_id, modify_datetime, payment_closed_flag) AS SELECT
/* ====================================================================================
    Version Number = 4.10.0.0  Date Modified = 06/18/2001
   ==================================================================================== */
       ops.payment_plan_id,
   ops.payment_plan_seq,
   ops.payment_date,
   ops.payment_amount,
   ops.recursive_amount,
   ops.paid_amount,
   ops.paid_recursive_amount,
   ops.information_number,
   ops.offender_deduction_id,
   ops.group_id,
   ops.modify_user_id,
   ops.modify_datetime,
   opp.payment_closed_flag
  FROM offender_payment_schedules ops, offender_payment_plans opp
 WHERE opp.payment_plan_id = ops.payment_plan_id
   AND opp.payment_plan_seq = ops.payment_plan_seq

 
;

CREATE OR REPLACE VIEW v_sanction_notices
AS SELECT DISTINCT o.root_offender_id AS offender_id,
    go.sanction_notice_code,
    cp.sac_staff_id,
    o.offender_id_display,
    ob.offender_book_id,
    o.last_name,
    o.first_name,
    oc.case_id,
    go.group_id,
    oc.case_info_number AS information_number
   FROM grouped_obligations go,
    offender_cases oc,
    case_plans cp,
    offenders o,
    offender_bookings ob,
    offender_deductions od,
    offender_trust_accounts ota,
    case_ord_chg_sent_statuses cocss
  WHERE o.offender_id = ob.offender_id AND ob.offender_book_id = oc.offender_book_id AND cp.offender_book_id = oc.offender_book_id AND cp.case_plan_status::text = 'ACTIVE'::text AND od.deduction_type::text = go.deduction_type::text AND od.offender_id = o.root_offender_id AND od.group_id = go.group_id AND od.offender_deduction_id = (( SELECT max(odx.offender_deduction_id) AS max
           FROM offender_deductions odx
          WHERE odx.offender_id = o.root_offender_id AND odx.deduction_type::text = od.deduction_type::text)) AND ota.caseload_id::text = od.caseload_id::text AND ota.offender_id = o.root_offender_id AND ota.offender_id = od.offender_id AND ota.account_closed_flag::text <> 'Y'::text AND (EXISTS ( SELECT 1
           FROM offender_deductions od_1,
            offender_payment_plans opp,
            offender_payment_schedules ops
          WHERE opp.offender_id = o.root_offender_id AND opp.case_id = oc.case_id AND od_1.offender_id = o.root_offender_id AND opp.payment_plan_id = ops.payment_plan_id AND opp.payment_plan_seq = ops.payment_plan_seq AND opp.payment_closed_flag::text = 'N'::text AND ops.payment_date < LOCALTIMESTAMP AND ops.paid_amount < ops.payment_amount AND opp.case_id = od_1.case_id AND opp.group_id = od_1.group_id AND od_1.information_number::text = opp.information_number::text AND od_1.deduction_type::text = go.deduction_type::text AND od_1.group_id = go.group_id AND od_1.case_id = oc.case_id AND od_1.offender_id = o.root_offender_id AND date_trunc('day'::text, LOCALTIMESTAMP - ops.payment_date) >= (( SELECT min(snx.late_days)::double precision * '1 day'::interval
                   FROM sanction_notices snx
                  WHERE snx.sanction_notice_code::text = go.sanction_notice_code::text)))) AND cocss.status_code::text = oc.case_status::text AND cocss.status_type::text = 'CASE'::text AND cocss.status_flag::text = 'Y'::text AND NOT (EXISTS ( SELECT 1
           FROM offender_applications oa,
            offender_appl_sentences oas
          WHERE oa.offender_book_id = oc.offender_book_id AND oa.application_status::text = 'PENDING'::text AND COALESCE(oa.disposition_status::text, ''::text) = ''::text AND oas.offender_book_id = oa.offender_book_id));

CREATE OR REPLACE VIEW v_route_locations (route_name, from_agy_loc_id, to_agy_loc_id, from_seq, to_seq, segment_length) AS SELECT
/**********************************************************
 Developer     Date        Version     Description
 --------------------------------------------------------
 Abu          20-Aug-2009  1.0         Denver Local Transportation.
                                       View returns From and To Agency Locations
                                       pertaining to a segment of a route.
 */
       f.route_name,
       f.agy_loc_id from_agy_loc_id,
       t.agy_loc_id to_agy_loc_id,
       lpad(f.leg_id::varchar, 4, '0')
          || lpad(f.leg_seq::varchar, 4, '0') from_seq,
       lpad(t.leg_id::varchar, 4, '0')
          || lpad(t.leg_seq::varchar, 4, '0') to_seq,
       asl.segment_length
  FROM route_stop_details f,
       route_stop_details t,
       agency_segment_lengths asl
 WHERE f.route_name = t.route_name
   AND f.active_flag = 'Y'
   AND t.active_flag = 'Y'
   AND lpad(t.leg_id::varchar, 4, '0')
         || lpad(t.leg_seq::varchar, 4, '0') =
       (SELECT MIN(lpad(rx.leg_id::varchar, 4, '0')
                    || lpad(rx.leg_seq::varchar, 4, '0')
                  )
          FROM route_stop_details rx
         WHERE rx.route_name = t.route_name
           AND lpad(rx.leg_id::varchar, 4, '0')
                 || lpad(rx.leg_seq::varchar, 4, '0') >
                       lpad(f.leg_id::varchar, 4, '0')
                          || lpad(f.leg_seq::varchar, 4, '0')
        )
   AND ((f.agy_loc_id = asl.from_agy_loc_id
         AND t.agy_loc_id = asl.to_agy_loc_id
        )
        OR (f.agy_loc_id = asl.to_agy_loc_id
           AND t.agy_loc_id = asl.from_agy_loc_id
           )
       )
   ORDER BY 1,4,5

;

CREATE OR REPLACE VIEW v_offender_case_sentences (offender_book_id, sentence_seq, sentence_category, sentence_calc_type, sentence_calc_type_desc, case_info_prefix, case_info_number, agy_loc_id, agy_loc_desc, consec_to_sentence_seq, start_date, sentence_expiry_date, termination_date, sentence_status, sentence_status_desc, no_of_unexcused_absence, team_description, workflow_id, line_seq, consec_to_line_seq) AS SELECT
/* =========================================================
   Version Number = 10.2.1.0  Date Modified = 14/01/2010
   ========================================================= */
/* MODIFICATION HISTORY
Author         Date               Version    Description
---------      ----------         --------   --------------------------------
Erin           14/01/2010         10.2.1.0   Migrate to Elite
Claus          11/07/2007         2.3        D# 6826. Get team desc from ocdsreqs.get_workflow_team.
Surya          02/04/2007         2.2        TD5315:Addition of Line_Seq.
Surya          02/10/2006         2.1        Termination date should display only for inactive sentences.
Surya          22/09/2006         2.0        Created View for module OCDSREQS
*/
          os.offender_book_id, os.sentence_seq, os.sentence_category,
          os.sentence_calc_type, sct.description sentence_calc_type_desc,
          oc.case_info_prefix, oc.case_info_number, oc.agy_loc_id,
          al.description agy_loc_desc, os.consec_to_sentence_seq,
          os.start_date, os.end_date,
          CASE WHEN  os.sentence_status='I' THEN  os.status_update_date  ELSE NULL END  termination_date,
          os.sentence_status,
          SUBSTR( oms_miscellaneous_getdesccode( 'ACTIVE_TYPE',
                                               os.sentence_status ),
               0,
               40 ) sentence_status_desc,
          os.no_of_unexcused_absence,
          ocdsreqs_get_workflow_team( os.offender_book_id,
                                       os.workflow_id ) team_description,
          os.workflow_id, os.line_seq,
          tag_legal_cases_get_consecutive_line_seq( os.offender_book_id,
                                 os.consec_to_sentence_seq )
                                                           consec_to_line_seq
     FROM sentence_calc_types sct, offender_sentences os
LEFT OUTER JOIN offender_cases oc ON (os.case_id = oc.case_id)
LEFT OUTER JOIN agency_locations al ON (oc.agy_loc_id = al.agy_loc_id)
WHERE os.sentence_category = sct.sentence_category AND os.sentence_calc_type = sct.sentence_calc_type AND os.sentence_level != 'AGG'

;

CREATE OR REPLACE VIEW v_program_phases (program_phase_id, program_id, list_seq, phase_type, phase_type_desc, description, comment_text, no_of_sessions, capacity, session_length, module_flag, module_type, module_type_desc, active_flag, expiry_date, break_allowed_flag) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
          program_id, parent_program_id, list_seq, phase_type,
          SUBSTR(oms_miscellaneous_getdesccode('PS_PHS_TYPE', phase_type),
                  1,
                  40
                 ),
          description, comment_text, no_of_sessions, capacity, session_length,
          module_flag, module_type,
          SUBSTR(oms_miscellaneous_getdesccode('PS_MOD_TYPE', module_type),
                  1,
                  40
                 ),
          active_flag, expiry_date, break_allowed_flag
     FROM program_services
    WHERE program_class = 'PRG_PH'

 
;

CREATE OR REPLACE VIEW v_offender_sentence_events
AS SELECT oca.event_id,
    oca.event_date,
    oca.start_time,
    oca.end_time,
    oca.in_time,
    oca.out_time,
    oca.event_class,
    oca.event_type,
    substr(tag_schedule_event_type_desc(oca.event_class, oca.event_type)::text, 1, 40) AS event_type_desc,
    oca.event_sub_type,
    substr(tag_schedule_event_sub_type_desc(oca.event_class, oca.event_type, oca.event_sub_type)::text, 1, 40) AS event_sub_type_desc,
    oca.crs_acty_id,
    oca.event_status,
    oca.event_outcome,
    substr(oms_miscellaneous_getdesccode('OUTCOMES'::character varying, oca.event_outcome)::text, 1, 40) AS event_outcome_desc,
    oca.comment_text,
    oca.agy_loc_id,
    al.description AS agy_loc_desc,
    oca.offender_book_id,
    COALESCE(opp.sentence_seq, opo.sentence_seq) AS sentence_seq,
    opp.offender_sent_condition_id,
    'OFF_CRS_ATT'::text AS record_source,
    NULL::character varying AS location,
    NULL::character varying AS condition,
    opo.program_id
   FROM offender_course_attendances oca
     LEFT JOIN agency_locations al ON oca.agy_loc_id::text = al.agy_loc_id::text
     LEFT JOIN agency_internal_locations ail ON oca.to_internal_location_id = ail.internal_location_id
     LEFT JOIN offender_program_profiles opp ON oca.offender_book_id = opp.offender_book_id AND oca.off_prgref_id = opp.off_prgref_id AND opp.sentence_seq IS NOT NULL
     LEFT JOIN offender_prg_obligations opo ON oca.offender_book_id = opo.offender_book_id AND oca.offender_prg_obligation_id = opo.offender_prg_obligation_id AND opo.sentence_seq IS NOT NULL
  WHERE (oca.event_type::text = ANY (ARRAY['DRR'::character varying, 'UW'::character varying, 'ACP'::character varying]::text[])) AND tag_prg_root_program_profile(oca.off_prgref_id) =
        CASE oca.event_type
            WHEN 'ACP'::text THEN oca.off_prgref_id
            ELSE opp.off_prgref_id
        END AND
        CASE oca.event_type
            WHEN 'ACP'::text THEN opo.offender_book_id
            ELSE opp.offender_book_id
        END IS NOT NULL
UNION ALL
 SELECT ois.event_id,
    ois.event_date,
    ois.start_time,
    ois.end_time,
    ois.in_time,
    ois.out_time,
    ois.event_class,
    ois.event_type,
    substr(tag_schedule_event_type_desc(ois.event_class, ois.event_type)::text, 1, 40) AS event_type_desc,
    ois.event_sub_type,
    substr(tag_schedule_event_sub_type_desc(ois.event_class, ois.event_type, ois.event_sub_type)::text, 1, 40) AS event_sub_type_desc,
    NULL::bigint AS crs_acty_id,
    ois.event_status,
    ois.event_outcome,
    substr(oms_miscellaneous_getdesccode('OUTCOMES'::character varying, ois.event_outcome)::text, 1, 40) AS event_outcome_desc,
    ois.comment_text,
    ois.to_agy_loc_id AS agy_loc_id,
    al.description AS agy_loc_desc,
    ois.offender_book_id,
    oiss.sentence_seq,
    opo.offender_sent_condition_id,
    'OFF_IND_SCH'::text AS record_source,
    al.description AS location,
    tag_prg_requirement_desc(opo.offender_sent_condition_id, opo.offender_book_id, opo.sentence_seq::bigint) AS condition,
    opo.program_id
   FROM offender_ind_schedules ois
     LEFT JOIN offender_prg_obligations opo ON ois.offender_prg_obligation_id = opo.offender_prg_obligation_id
     LEFT JOIN agency_locations al ON ois.to_agy_loc_id::text = al.agy_loc_id::text
     LEFT JOIN agency_internal_locations ail ON ois.to_internal_location_id = ail.internal_location_id
     JOIN offender_ind_sch_sents oiss ON ois.event_id = oiss.event_id;

CREATE OR REPLACE VIEW v_offender_oic_sanctions (offender_book_id, sanction_seq, oic_sanction_desc, compensation_amount, sanction_months, sanction_days, comment_text, effective_date, consecutive_sanction_seq, oic_hearing_id, oic_sanction_code, status_description, result_seq, status_date, oic_incident_id) AS select
/* =========================================================
   Version Number = 10.2.0  Date Modified = 01/09/2005
   =========================================================
   MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     01/09/2005  2.0           NOMIS project
*/
   OOS.offender_book_id,
   OOS.sanction_seq,
   SUBSTR(OMS_MISCELLANEOUS_getDescCode('OIC_SANCT', OOS.oic_sanction_code),1,40),
   OOS.compensation_amount,
   OOS.sanction_months,
   OOS.sanction_days,
   OOS.comment_text,
   OOS.effective_date,
   OOS.consecutive_sanction_seq,
   OOS.oic_hearing_id,
   OOS.OIC_SANCTION_CODE,
   SUBSTR(OMS_MISCELLANEOUS_getDescCode('OIC_SANCT_ST', OOS.status),1,40),
   OOS.result_seq,
   OOS.status_date ,
   OH.oic_incident_id
FROM   offender_oic_sanctions OOS, OIC_HEARING_RESULTS OHR,
       OIC_HEARINGS OH
WHERE  OOS.OIC_HEARING_ID = OHR.OIC_HEARING_ID
AND    OOS.RESULT_SEQ     = OHR.RESULT_SEQ
AND    OH.OIC_HEARING_ID  = OHR.OIC_HEARING_ID

 
;

CREATE OR REPLACE VIEW comm_caseload_current_accounts (caseload_id, account_code, account_period_id, current_balance, bank_account_type, bank_account_number, account_party_type, payee_person_id, payee_corporate_id, modify_user_id, modify_date, list_seq) AS SELECT
 CCAB.CASELOAD_ID
, CCAB.ACCOUNT_CODE
, MAX(CCAT.ACCOUNT_PERIOD_ID)
, SUM(coalesce(CCAT.CURRENT_BALANCE,0))
, CCAB.BANK_ACCOUNT_TYPE
, CCAB.BANK_ACCOUNT_NUMBER
, CCAB.ACCOUNT_PARTY_TYPE
, CCAB.PAYEE_PERSON_ID
, CCAB.PAYEE_CORPORATE_ID
, USER
, MAX(CCAT.MODIFY_DATE)
, MAX(CCAT.LIST_SEQ)
 FROM com_csld_current_accounts_TXNS CCAT, com_csld_current_accounts_base CCAB
 WHERE CCAT.caseload_id  = CCAB.caseload_id
 AND   CCAT.account_code  = CCAB.account_code
GROUP BY CCAB.CASELOAD_ID, CCAB.ACCOUNT_CODE,
	 CCAB.BANK_ACCOUNT_TYPE, CCAB.BANK_ACCOUNT_NUMBER, CCAB.ACCOUNT_PARTY_TYPE,
	 CCAB.PAYEE_PERSON_ID, CCAB.PAYEE_CORPORATE_ID

 
;

CREATE OR REPLACE VIEW v_offender_case_notes (offender_book_id, case_note_id, offender_id_display, last_name, first_name, contact_date, contact_time, case_note_type, contact_type, case_note_sub_type, contact_sub_type, staff_id, staff_name, amendment_flag, check_box1, check_box2, check_box3, check_box4, check_box5, case_note_text, event_id, note_source_code, note_source, iwp_flag, date_creation, time_creation) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     15/10/2005  2.0          NOMIS project(10.2.0)
   David Ng     03/01/2006  2.0          Combine Task to case notes
                                         replace domain casenote with task
   David Ng     17/01/2006  2.0          Add Date creation and Time Creation
*/
          ocn.offender_book_id, ocn.case_note_id, o.offender_id_display,
          o.last_name, o.first_name, ocn.contact_date, ocn.contact_time,
          ocn.case_note_type,
          SUBSTR(oms_miscellaneous_getdesccode('TASK_TYPE',
                                                 ocn.case_note_type
                                                ),
                  1,
                  40
                 ),
          ocn.case_note_sub_type,
          SUBSTR(oms_miscellaneous_getdesccode('TASK_SUBTYPE',
                                                 ocn.case_note_sub_type
                                                ),
                  1,
                  40
                 ),
          ocn.staff_id,
          SUBSTR(oms_miscellaneous_staff_name(ocn.staff_id), 1, 40),
          ocn.amendment_flag, ocn.check_box1, ocn.check_box2, ocn.check_box3,
          ocn.check_box4, ocn.check_box5, case_note_text, ocn.event_id,
          note_source_code,
          SUBSTR(oms_miscellaneous_getdesccode('NOTE_SOURCE',
                                                 ocn.note_source_code
                                                ),
                  1,
                  40
                 ),
          iwp_flag, date_creation, time_creation
     FROM offender_case_notes ocn, offenders o, offender_bookings ob
    WHERE o.offender_id = ob.offender_id
      AND ob.offender_book_id = ocn.offender_book_id

 
;

CREATE OR REPLACE VIEW v_stg_location_presence (stg_id, agy_loc_id, location_description, location_count) AS select osa.stg_id,
coalesce(al.AGY_LOC_ID, 'Undefined'),
coalesce(al.DESCRIPTION, 'Undefined'),
count(*)
FROM offender_stg_affiliations osa, offender_bookings ofb
LEFT OUTER JOIN agency_locations al ON (ofb.agy_loc_id = al.agy_loc_id)
WHERE osa.active_flag = 'Y' and osa.offender_book_id = ofb.offender_book_id and ofb.active_flag = 'Y'  group by osa.stg_id, al.agy_loc_id, al.description

 
;

CREATE OR REPLACE VIEW v_ass_treat_prots (off_ass_need_id, treatment_id, casework_type_desc, casework_type, program_id, program_desc, max_score, min_score, referral_flag, active_flag, expiry_date, program_category, prg_category_desc) AS SELECT
/* =========================================================
   Version Number = 1.2  Date Modified = 08/09/2010
   =========================================================
   MODIFICATION HISTORY
   Person       Date              Version   Comments
   ---------    -----            ---------  ------------------------------
   Ruxandra     08/08/2010        1.2       corrected error; outer join is needed
   Ruxandra     07/12/2010        1.1       modified view to add columns program_category and prg_category_desc
   Niko         08/12/2008        1.0       Created
*/
ATP.OFF_ASS_NEED_ID
,ATP.TREATMENT_ID
,(SELECT rc.description
    FROM reference_codes rc
   WHERE rc.domain = 'CASEPLAN_STP'
     AND rc.code = casework_type  LIMIT 1) casework_type_desc
,ATP.CASEWORK_TYPE
,ATP.PROGRAM_ID
,ps.description program_desc
,ATP.MAX_SCORE
,ATP.MIN_SCORE
,ATP.REFERRAL_FLAG
,ATP.ACTIVE_FLAG
,ATP.EXPIRY_DATE
,ps.program_category program_category
,(SELECT reference_codes.description
    FROM reference_codes
   WHERE reference_codes.domain = 'PS_CATEGORY'
     AND reference_codes.code = ps.program_category
 ) prg_category_desc
FROM assessed_treatment_protocols atp
LEFT OUTER JOIN program_services ps ON (atp.program_id = ps.program_id);

CREATE OR REPLACE VIEW v_stg_gang_set (stg_desc, stg_id) AS select st1.description stg_desc, st1.stg_id stg_id
FROM security_threat_groups st1
where st1.stg_level = 'GANG'
and st1.active_flag = 'Y'

UNION

select st2.description||'-'||st1.description stg_desc, st1.stg_id stg_id
from security_threat_groups st1, security_threat_groups st2
where st1.stg_level = 'SET'
and st1.active_flag = 'Y'
and st1.PARENT_STG_ID = st2.STG_ID

 
;

CREATE OR REPLACE VIEW assessment_section_scores_v1 (offender_book_id, assessment_seq, parent_assessment_id, effective_date, assessment_id, section, description, score) AS SELECT
     off_ass.OFFENDER_BOOK_ID
    ,off_ass.ASSESSMENT_SEQ
    ,off_ass.ASSESSMENT_TYPE_ID
    ,off_ass.ASSESSMENT_DATE
    ,ass_sec.ASSESSMENT_ID
    ,ass_sec.ASSESSMENT_CODE
    ,ass_sec.DESCRIPTION
    ,SUM(off_ait.score)
FROM
    offender_assessments  off_ass,
    offender_assessment_items  off_ait,
    assessments  ass_ind,
    assessments  ass_cat,
    assessments  ass_sec
 WHERE off_ass.assessment_type_id = ass_sec.parent_assessment_id
 AND   off_ass.offender_book_id   = off_ait.offender_book_id
 AND   off_ass.assessment_seq     = off_ait.assessment_seq
 AND   ass_ind.assessment_id      = off_ait.assessment_id
 AND   ass_cat.assessment_id      = ass_ind.parent_assessment_id
 AND   ass_sec.assessment_id      = ass_cat.parent_assessment_id
 AND   ass_sec.assessment_class   = 'SECT'
 GROUP BY off_ass.offender_book_id
        , off_ass.assessment_seq
        , off_ass.assessment_type_id
        , off_ass.assessment_date
        , ass_sec.assessment_id
        , ass_sec.assessment_code
        , ass_sec.description

 
;

CREATE OR REPLACE VIEW v_header_block
AS SELECT off_name.offender_id,
    off_name.alias_offender_id,
    off_name.offender_id_display,
    off_name.last_name,
    off_name.first_name,
    off_name.middle_name,
    off_name.suffix,
    off_name.birth_date,
    off_bkg.offender_book_id,
    off_bkg.booking_no,
    off_bkg.booking_begin_date,
    off_bkg.booking_end_date,
    off_bkg.agy_loc_id,
    off_bkg.agency_iml_id,
    off_bkg.living_unit_id,
    off_bkg.disclosure_flag,
    off_bkg.active_flag,
    off_bkg.booking_status,
    substr(tag_header_get_header_location(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.community_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.comm_staff_role, off_bkg.comm_staff_id::bigint)::text, 0, 100) AS living_unit_description,
    off_bkg.in_out_status,
    substr(tag_header_get_header_status(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.status_reason, mov_rsn.header_status_flag, off_bkg.comm_status, off_bkg.in_out_status, off_bkg.root_offender_id, off_bkg.offender_book_id)::text, 0, 100) AS status_display,
    off_name.root_offender_id,
    off_bkg.assigned_staff_id,
    substr(tag_header_get_location_type(off_bkg.agy_loc_id)::text, 0, 12) AS agy_loc_type,
    off_bkg.create_agy_loc_id,
    off_bkg.booking_type,
    off_bkg.booking_created_date,
    substr(tag_header_get_int_location_code(off_bkg.agency_iml_id)::text, 0, 10) AS location_code,
    substr(tag_header_get_living_desc(off_bkg.agy_loc_id, off_bkg.living_unit_id)::text, 0, 100) AS liv_unit_desc,
    off_bkg.intake_agy_loc_id,
    off_bkg.community_active_flag,
    off_bkg.create_intake_agy_loc_id,
    off_bkg.comm_status AS community_status,
    off_bkg.status_reason,
    mov_rsn.header_status_flag AS header_status,
    tag_header_get_age(off_name.birth_date) AS age,
    substr(oms_miscellaneous_getdesccode('SEX'::character varying, off_name.sex_code)::text, 0, 10) AS gender,
    substr(tag_header_get_officer(off_bkg.offender_book_id)::text, 0, 105) AS officer,
    substr(tag_header_get_prison_location(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.intake_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id, off_bkg.offender_book_id)::text, 0, 105) AS prison_location,
    substr(omkhead_get_alerts(off_bkg.offender_book_id)::text, 0, 40) AS off_alerts,
    substr(tag_header_get_status_1(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status, off_bkg.status_reason)::text, 0, 40) AS status_1,
    substr(tag_header_get_status_2(off_bkg.offender_book_id)::text, 0, 40) AS status_2,
    substr(tag_header_get_status_3(off_bkg.offender_book_id)::text, 0, 40) AS status_3,
    substr(oms_miscellaneous_getdesccode('ETHNICITY'::character varying, off_name.race_code)::text, 0, 40) AS ethnicity,
    ( SELECT oem.movement_reason_code
           FROM offender_external_movements oem
          WHERE oem.offender_book_id = off_bkg.offender_book_id AND oem.movement_seq = (( SELECT max(oem2.movement_seq) AS max
                   FROM offender_external_movements oem2
                  WHERE oem2.offender_book_id = oem.offender_book_id)) AND (EXISTS ( SELECT 'X'::text AS text
                   FROM movement_reasons mr
                  WHERE mr.movement_type::text = oem.movement_type::text AND mr.movement_reason_code::text = oem.movement_reason_code::text AND mr.header_status_flag::text = 'Y'::text))) AS movement_reason,
    oms_intake_get_offender_supervision(off_bkg.offender_book_id) AS off_sup_level
   FROM offenders off_name,
    offender_bookings off_bkg
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE off_name.offender_id = off_bkg.offender_id;
CREATE OR REPLACE VIEW v_incident_staff_participants (agency_incident_id, staff_id, staff_first_name, staff_last_name, staff_incident_role, staff_incident_role_desc, party_added_datetime, comment_text) AS SELECT
/* =========================================================
   Version Number = 1.0  Date Modified = 10-DEC-2020
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date      Version        Comments
   ---------    ------     ---------     ------------------------------
   Syscon     10-DEC-2020  1.0           Incident Report: incident staff participants
*/
ai.agency_incident_id,
aip.staff_id,
sm.first_name as staff_first_name,
sm.last_name as staff_last_name,
aip.incident_role as staff_incident_role,
rfc_staff_role.description as staff_incident_role_desc,
aip.party_added_date as party_added_datetime,
aip.comment_text
FROM agency_incidents ai, agency_incident_parties aip,
     staff_members sm, reference_codes rfc_staff_role
WHERE ai.agency_incident_id = aip.agency_incident_id
  AND aip.staff_id = sm.staff_id
  AND aip.incident_role = rfc_staff_role.code
  AND rfc_staff_role.domain = 'INC_STAF_PAR';

CREATE OR REPLACE VIEW transferred_work_v1 (asstra_id, offender_id_display, offender_id, offender_name, line, order_type, order_code, description, component, agy_loc_id, agy_loc_id_to, transfer_date, transferred_by, sac_staff_id, status_to, offender_book_id, sentence_seq, offender_book_id_request, charge_seq, request_seq, staff_from) AS SELECT
          /*==========================================================
              Version Number = 2.1   Date Modified = 24/01/2006
              ==========================================================*/
           /*MODIFICATION HISTORY
               Person      Date             Version         Comments
               Surya       24-Jan-2006      2.1             Modified to make the view get validated, as
			                                    it was invalidated because of the new sentences
                            			            and Requirements data model changes.
           */
          asstra.asstra_id,
          OFF.offender_id_display,
          OFF.offender_id,
          OFF.last_name || ', ' || OFF.first_name,
          off_sent.sentence_seq,
          NULL order_type,
          NULL order_code,
          pot.description,
          offass.component,
          asstra.agy_loc_id,
          asstra.agy_loc_id_to,
          asstra.transfer_date,
          sm.last_name || ', ' || sm.first_name,
          asstra.sac_staff_id,
          asstra.status_to,
          offass.offender_book_id,
          offass.sentence_seq,
          offass.offender_book_id_request,
          offass.charge_seq,
          offass.request_seq,
          sm2.last_name || ', ' || sm.first_name
     FROM assignment_transfers asstra,
          offender_work_assignments offass,
          offender_sentences off_sent,
          probation_order_types pot,
          offender_bookings ob,
          offenders OFF,
          staff_members sm,
          staff_members sm2
    WHERE asstra.offass_id = offass.offass_id
      AND offass.offender_book_id = off_sent.offender_book_id
      AND offass.sentence_seq = off_sent.sentence_seq
      AND off_sent.offender_book_id = ob.offender_book_id
      AND ob.offender_id = OFF.offender_id
      AND asstra.creation_user = sm.user_id
      AND asstra.sac_staff_id_from = sm2.staff_id

UNION

   SELECT asstra.asstra_id,
          OFF.offender_id_display,
          OFF.offender_id,
          OFF.last_name || ', ' || OFF.first_name,
          off_req.request_seq,
          off_req.order_type,
          off_req.order_code,
          pot.description,
          offass.component,
          asstra.agy_loc_id,
          asstra.agy_loc_id_to,
          asstra.transfer_date,
          sm.last_name || ', ' || sm.first_name,
          asstra.sac_staff_id,
          asstra.status_to,
          offass.offender_book_id,
          offass.sentence_seq,
          offass.offender_book_id_request,
          offass.charge_seq,
          offass.request_seq,
          sm2.last_name || ', ' || sm.first_name
     FROM assignment_transfers asstra,
          offender_work_assignments offass,
          offender_requests off_req,
          probation_order_types pot,
          offender_bookings ob,
          offenders OFF,
          staff_members sm,
          staff_members sm2
    WHERE asstra.offass_id = offass.offass_id
      AND offass.offender_book_id_request = off_req.offender_book_id
      AND offass.request_seq = off_req.request_seq
      AND offass.charge_seq = off_req.charge_seq
      AND off_req.order_type = pot.order_type
      AND off_req.order_code = pot.order_code
      AND off_req.offender_book_id = ob.offender_book_id
      AND ob.offender_id = OFF.offender_id
      AND asstra.creation_user = sm.user_id
      AND asstra.sac_staff_id_from = sm2.staff_id
 
 
;

CREATE OR REPLACE VIEW v_course_phase_offerings_2 (program_id, program_phase_id, course_id, caseload_type, provider_party_class, provider_party_id, provider_party_code, crs_services_address_id, crs_internal_location_id, program_list_seq, program_description, program_capacity, program_no_of_sessions, program_session_length, program_module_flag) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     03/06/2006 2.1           NOMIS project(10.2.0)
*/
ps.Parent_program_ID,
ps.program_id,
CRS.Crs_acty_ID,
CRS.Caseload_type,
CRS.PROVIDER_PARTY_CLASS,
CRS.PROVIDER_PARTY_ID,
CRS.PROVIDER_PARTY_CODE,
CRS.services_Address_ID,
CRS.INternal_location_ID,
ps.list_seq,
ps.description,
ps.capacity,
ps.no_of_sessions,
ps.session_length,
ps.module_flag
FROM PROGRAM_SERVICES ps, COurse_ACTIVITIES CRS
WHERE ps.Program_Class = 'PRG_PH'
AND   CRS.Course_Class = 'COURSE'
AND   CRS.Program_ID   = ps.Parent_Program_ID

 
;

CREATE OR REPLACE VIEW v_stg_group (stg_desc, stg_id) AS select STG_DESC,STG_ID
FROM (select st1.description stg_desc, st1.stg_id stg_id
from security_threat_groups st1
where st1.stg_level = 'NATION'
and st1.active_flag = 'Y'

UNION

select st2.description||'-'||st1.description stg_desc, st1.stg_id stg_id
from security_threat_groups st1, security_threat_groups st2
where st1.stg_level = 'GANG'
and st1.active_flag = 'Y'
and st1.PARENT_STG_ID = st2.STG_ID

UNION

select st3.description||'-'||st2.description||'-'||st1.description stg_desc, st1.stg_id stg_id
from security_threat_groups st1, security_threat_groups st2, security_threat_groups st3
where st1.stg_level = 'SET'
and st1.active_flag = 'Y'
and st1.PARENT_STG_ID = st2.STG_ID
and st2.parent_stg_id = st3.stg_id) alias0

 
;

CREATE OR REPLACE VIEW v_prison_total (total_count, male_count, female_count, agy_loc_id) AS SELECT
   /* ========================================================
   Version Number = 1.1 Date Modified = 21-JAN-2010
   ========================================================= */
/* MODIFICATION HISTORY
   Person          Date      Version          Comments
   ---------    ------     ---------     ------------------------------
    Neeraj Kt    21-JAN-2010    1.1     HPQC #471 Commented active_flag for calculating all references codes.
    Neeraj Kt    07-OCT-2009    1.0     HPQC #471 Added table offender_imprison_statuses for matching records
 */
          count(*),
          sum(CASE WHEN off.sex_code='M' THEN  1  ELSE 0 END ),
          sum(CASE WHEN off.sex_code='F' THEN  1  ELSE 0 END ), off_bk.agy_loc_id
       FROM offenders off,                    --@@Added by Neeraj,HPQC No 471
 offender_bookings off_bk
LEFT OUTER JOIN offender_imprison_statuses act_is ON (off_bk.offender_book_id = act_is.offender_book_id)
WHERE off_bk.active_flag = 'Y' AND off_bk.offender_id = off.offender_id  --@@Added by Neeraj,HPQC No 471
  AND (     TO_CHAR(act_is.effective_date, 'YYYYMMDD')
                || TO_CHAR(act_is.effective_time, 'HH24MI') =
                   (SELECT MAX(   TO_CHAR(i.effective_date, 'YYYYMMDD')
                                || TO_CHAR(i.effective_time, 'HH24MI')
                               )
                      FROM offender_imprison_statuses i
                     WHERE i.offender_book_id = off_bk.offender_book_id)
             OR (coalesce(effective_date::text, '') = '')
            ) AND (act_is.imprisonment_status IN (SELECT code --@@Added by Neeraj,HPQC No 471
                                             FROM reference_codes
                                            WHERE domain = 'IMP_STS'
                                             -- AND active_flag ='Y --@@@Commented by Neeraj,HPQC No 471
                                           )
                OR coalesce(act_is.imprisonment_status::text, '') = '') GROUP BY off_bk.agy_loc_id

;

CREATE OR REPLACE VIEW v_course_phases (course_phase_id, course_phase_name, parent_crs_acty_id, program_phase_id, caseload_type, caseload_type_desc, capacity, schedule_start_date, schedule_end_date, active_flag, expiry_date, comment_text, provider_party_class, provider_party_id, provider_party_code, services_address_id, placement_corporate_id, internal_location_id, internal_location_desc) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     03/01/2006 2.0           NOMIS project(10.2.0)
   David Ng     23/01/2006 2.1          Rename Crs_acty_id to parent_crs_acty_ID
*/
          cp.crs_acty_id, PS.description, cp.parent_crs_acty_id, cp.program_id,
          cp.caseload_type,
          SUBSTR(oms_miscellaneous_getdesccode('CSLD_CODE',
                                                 ca.caseload_type
                                                ),
                  1,
                  40
                 ),
          cp.capacity, cp.schedule_start_date, cp.schedule_end_date,
          cp.active_flag, cp.expiry_date, cp.comment_text,
          cp.provider_party_class, cp.provider_party_id,
          cp.provider_party_code, cp.services_address_id,
          cp.placement_corporate_id, cp.internal_location_id,
          tag_int_loc_int_loc_desc(cp.internal_location_id)
     FROM course_activities cp, course_activities ca, Program_services PS
    WHERE cp.course_class = 'CRS_PH'
      AND cp.parent_crs_acty_id = ca.crs_acty_id
      AND cp.Program_ID = PS.program_id

 
;

CREATE OR REPLACE VIEW v_gang_non_associations (gang, faction, gang_code, reason, internal_location_flag, transport_flag) AS SELECT
     coalesce(gang.PARENT_GANG_CODE, gang.GANG_CODE)
    ,CASE WHEN coalesce(gang.PARENT_GANG_CODE::text, '') = '' THEN  NULL  ELSE gang.GANG_CODE END
    ,gang_na.GANG_CODE
    ,gang_na.NS_REASON_CODE
    ,gang_na.INTERNAL_LOCATION_FLAG
    ,gang_na.TRANSPORT_FLAG
FROM
    gangs  gang,
    gang_non_associations  gang_na
 WHERE
 gang_na.NS_GANG_CODE = gang.GANG_CODE
 
 
;

CREATE OR REPLACE VIEW off_ap_v1 (off_action_plan_id, casework_type, casework_type_desc, off_crim_need_id, off_case_cond_id, program_id, program_desc, notes, start_date, end_date, program_category, prg_category_desc) AS SELECT
/* =========================================================
   Version Number = 1.4  Date Modified = 08/09/2010
   =========================================================
   MODIFICATION HISTORY
   Person       Date              Version   Comments
   ---------    -----            ---------  ------------------------------
   Ruxandra     08/09/2010        1.4       corrected error, ; outer join is needed
   Ruxandra     07/12/2010        1.3       modified view to add columns program_category and prg_category_desc
   Niko         08/21/2008        1.2       Added alias name to the table
   Niko         08/20/2008        1.1       Modified the view
   Niko         08/01/2008        1.0       Created
*/
oap.off_action_plan_id
,oap.casework_type
,(SELECT rc.description
    FROM reference_codes rc
   WHERE rc.domain = 'CASEPLAN_STP'
     AND rc.code = oap.casework_type  LIMIT 1) casework_type_desc
,oap.off_crim_need_id
,oap.off_case_cond_id
,oap.program_id
,ps.description program_desc
,oap.notes
,oap.start_date
,oap.end_date
,ps.program_category program_category
,(SELECT reference_codes.description
    FROM reference_codes
   WHERE reference_codes.domain = 'PS_CATEGORY'
     AND reference_codes.code = ps.program_category
 ) prg_category_desc
 FROM offender_action_plans oap
LEFT OUTER JOIN program_services ps ON (oap.program_id = ps.program_id)
WHERE (off_crim_need_id IS NOT NULL AND off_crim_need_id::text <> '')

;
CREATE OR REPLACE VIEW v_prison_status_count (total_count, male_count, female_count, agy_loc_id, imprisonment_status) AS SELECT
/* =========================================================
   Version Number = 1.1 Date Modified = 10-NOV-2014
   ========================================================= */
/* MODIFICATION HISTORY
   Person       Date            Version           Comments
   ---------   ------      	    ---------      ------------------------------
   Edward       10/11/2014      1.1            Added error_flag as not defined imprisonment status.
*/
     count(*) total_count,
     sum(male) male_count,
     sum(female) female_count,
     agy_loc_id,
     imprisonment_status
FROM
(SELECT
     CASE WHEN off.sex_code='M' THEN 1  ELSE 0 END  male,
     CASE WHEN off.sex_code='F' THEN 1  ELSE 0 END  female,
     off_bk.agy_loc_id agy_loc_id,
     CASE WHEN act_is.error_flag='Y' THEN NULL  ELSE act_is.imprisonment_status END  imprisonment_status
FROM offenders off, offender_bookings off_bk
LEFT OUTER JOIN offender_imprison_statuses act_is ON (off_bk.offender_book_id = act_is.offender_book_id)
WHERE off_bk.active_flag = 'Y' AND off_bk.offender_id  = off.offender_id  AND (TO_CHAR(act_is.effective_date, 'YYYYMMDD') ||
  TO_CHAR(act_is.effective_time, 'HH24MI')
  = (SELECT max(TO_CHAR(i.effective_date, 'YYYYMMDD')||
                TO_CHAR(i.effective_time, 'HH24MI'))
       FROM offender_imprison_statuses i
      WHERE i.offender_book_id = off_bk.offender_book_id)
  OR (coalesce(act_is.effective_date::text, '') = ''))
 ) alias12
GROUP BY agy_loc_id, imprisonment_status
;

CREATE OR REPLACE VIEW staff_members_v1 (staff_id, user_id, name, last_name, first_name) AS SELECT
     staff.STAFF_ID
    ,staff.USER_ID
    ,staff.LAST_NAME||', '||staff.FIRST_NAME
    ,staff.LAST_NAME
    ,staff.FIRST_NAME
FROM
    staff_members  staff

 
;

CREATE OR REPLACE VIEW v_offender_proceeding_sents (offender_proceeding_id, offender_book_id, proceeding_type, proceeding_agy_loc_id, comment_text, proceeding_status, outcome_date, proceeding_sentence_flag, sentence_seq, sentence_calc_type, sentence_category, sentence_category_desc, sentence_desc, no_of_unexcused_absence, start_date, end_date, case_id, case_info_prefix, case_info_number, case_court_desc) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     03/04/2006 2.1           NOMIS project(10.2.0)
*/
OP.OFFENDER_PROCEEDING_ID
,OP.OFFENDER_BOOK_ID
,OP.PROCEEDING_TYPE
,OP.PROCEEDING_AGY_LOC_ID
,OP.COMMENT_TEXT
,OP.PROCEEDING_STATUS
,OP.OUTCOME_DATE
,(SELECT CASE WHEN COUNT(*)=0 THEN  'N'  ELSE 'Y' END
  FROM   Offender_Proceeding_Sents OPS
  WHERE  OP.Offender_Proceeding_ID = OPS.Offender_Proceeding_ID
  AND   OS.Offender_Book_ID       = OPS.Offender_Book_ID
  AND   OS.Sentence_Seq           = OPS.Sentence_Seq ) PROCEEDING_SENTENCE_FLAG
,OS.SENTENCE_SEQ
,OS.SENTENCE_CALC_TYPE
,OS.SENTENCE_CATEGORY
,SUBSTR(oms_miscellaneous_getdesccode('CATEGORY',OS.SENTENCE_CATEGORY),1,40)
,SCT.Description Sentence_desc
,OS.NO_OF_UNEXCUSED_ABSENCE
,OS.Start_Date
,OS.End_Date
,OS.Case_ID
,OC.CASE_INFO_PREFIX
,OC.CASE_INFO_NUMBER
,AL.Description        CASE_COURT_DESC
FROM sentence_calc_types sct, offender_proceedings op, offender_sentences os
LEFT OUTER JOIN offender_cases oc ON (OS.Case_ID = OC.Case_ID)
LEFT OUTER JOIN agency_locations al ON (OC.Agy_Loc_ID = AL.Agy_loc_ID)
WHERE OS.Offender_book_ID       = OP.Offender_book_ID  AND OS.Sentence_calc_type     = SCT.Sentence_calc_type AND OS.sentence_category      = SCT.Sentence_category  
UNION ALL

SELECT
0                            -- OP.OFFENDER_PROCEEDING_ID
,OS.Offender_Book_ID
,NULL                        -- OP.PROCEEDING_TYPE
,NULL                        -- OP.PROCEEDING_AGY_LOC_ID
,NULL                        -- OP.COMMENT_TEXT
,NULL                        -- OP.PROCEEDING_STATUS
,NULL                        -- OP.OUTCOME_DATE
,'N'                        -- PROCEEDING_SENTENCE_FLAG
,OS.SENTENCE_SEQ
,OS.SENTENCE_CALC_TYPE
,OS.SENTENCE_CATEGORY
,SUBSTR(oms_miscellaneous_getdesccode('CATEGORY',OS.SENTENCE_CATEGORY),1,40)
,SCT.Description Sentence_desc
,OS.NO_OF_UNEXCUSED_ABSENCE
,OS.Start_Date
,OS.End_Date
,OS.Case_ID
,OC.CASE_INFO_PREFIX
,OC.CASE_INFO_NUMBER
,AL.Description        CASE_COURT_DESC
FROM sentence_calc_types sct, offender_sentences os
LEFT OUTER JOIN offender_cases oc ON (OS.Case_ID = OC.Case_ID)
LEFT OUTER JOIN agency_locations al ON (OC.Agy_Loc_ID = AL.Agy_loc_ID)
WHERE OS.Sentence_calc_type     = SCT.Sentence_calc_type AND OS.sentence_category      = SCT.Sentence_category;

CREATE OR REPLACE VIEW v_bail_info (offender_book_id, case_id, offender_charge_id, offence_code, statute_code, offence_desc, active_flag) AS SELECT  DISTINCT
-- =========================================================
--   Version Number = 1.3  Date Modified = 26-Mar-2008
--   =========================================================
--
-- MODIFICATION HISTORY
-- Person      Date         Version       Comments
-- ---------   ----------   --------  ------------------------------
--
--
-- Sarah       26-Mar-2008   1.3       Added case_id condition
-- Niko/Karl   08-01-2007    1.2       Modified the view by adding a offender_cases table
-- Jason Xu    06-19-2006    1.1       change sort by active_status and pcn_id,pcn_seq
-- Ruxandra    04-12-2006    1.0       created V_BAIL_INFO for use on OIDBAILD
--
--
        offender_charges.offender_book_id,
        --orders.case_id,
        offender_cases.case_id,
        offender_charges.offender_charge_id,
        offender_charges.offence_code,
        offender_charges.statute_code,
        offences.description offence_desc,
        CASE WHEN offender_charges.CHARGE_STATUS='A' THEN 1  ELSE 2 END  active_flag
   FROM offender_charges, offences, offender_cases
  WHERE offender_cases.offender_book_id = offender_charges.offender_book_id
   AND  offender_charges.offence_code     = offences.offence_code
   AND  offender_charges.statute_code     = offences.statute_code
   AND  offender_cases.case_id            = offender_charges.case_id
   order by active_flag,offender_charge_id

 
;

CREATE OR REPLACE VIEW v_acp_progress (offender_prg_obligation_id, off_prgref_id, profile_needed_flag, profile_completion_date, profile_comment_text, program_id, program_class, program_list_seq, program_description) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   NDB          24/07/2006 2.1           NOMIS project(10.2.0)
*/
opp.offender_prg_obligation_id,
opp.off_prgref_id,
opp.needed_flag,
opp.completion_date,
opp.comment_text,
ps.program_id,
ps.program_class,
ps.list_seq,
ps.description
FROM  offender_program_profiles opp,
      program_services ps
WHERE opp.program_id = ps.program_id
AND opp.profile_class = 'PRG'

 
;

CREATE OR REPLACE VIEW v_offender_authorised_visitors (offender_book_id, visitor_offender_id_display, contact_type, relationship_type, contact_person_type, age, contact_root_offender_id, contact_person_id, visitor_last_name, visitor_first_name, offender_contact_person_id) AS SELECT
/*==========================================================
Version Number = 1.1 Date Modified = 04/02/2005
==========================================================*/
/*MODIFICATION HISTORY
     Person      Date           Version         Comments
     Surya       25-Jan-2005    1.0             Initial Draft.
     Surya       04-Feb-2005    1.1             Removed the Rult hint.
     David Ng    21-Feb-2006    2.0             User Offender Contact Persons table
*/
          off_aut.offender_book_id, vis.offender_id_display,
          ref_code.description, ref_code1.description, 'OFF',
          TRUNC(MONTHS_BETWEEN(date_trunc('day', LOCALTIMESTAMP), date_trunc('day', vis.birth_date)) / 12),
          off_aut.contact_root_offender_id, NULL, vis.last_name,
          vis.first_name, off_aut.offender_contact_person_id
     FROM offenders vis, offender_contact_persons off_aut
LEFT OUTER JOIN reference_codes ref_code ON (off_aut.contact_type = ref_code.code AND 'CONTACTS' = ref_code.domain)
LEFT OUTER JOIN reference_codes ref_code1 ON (off_aut.relationship_type = ref_code1.code AND 'RELATIONSHIP' = ref_code1.domain)
WHERE off_aut.approved_visitor_flag = 'Y' AND vis.offender_id = off_aut.contact_root_offender_id     AND (off_aut.contact_root_offender_id IS NOT NULL AND off_aut.contact_root_offender_id::text <> '')

UNION ALL

   SELECT off_aut.offender_book_id, NULL, ref_code.description,
          ref_code1.description, 'PER',
          TRUNC(MONTHS_BETWEEN(date_trunc('day', LOCALTIMESTAMP), date_trunc('day', per.birthdate)) / 12),
          NULL, off_aut.person_id, per.last_name, per.first_name,
          off_aut.offender_contact_person_id
     FROM persons per, offender_contact_persons off_aut
LEFT OUTER JOIN reference_codes ref_code ON (off_aut.contact_type = ref_code.code AND 'CONTACTS' = ref_code.domain)
LEFT OUTER JOIN reference_codes ref_code1 ON (off_aut.relationship_type = ref_code1.code AND 'RELATIONSHIP' = ref_code1.domain)
WHERE off_aut.person_id = per.person_id AND off_aut.approved_visitor_flag = 'Y'     AND (off_aut.person_id IS NOT NULL AND off_aut.person_id::text <> '')
 
 
;

CREATE OR REPLACE VIEW v_name_search1 (last_name, first_name, active_flag, offender_id, offender_book_id, offender_id_display, agy_loc_id, agy_loc_name, living_unit_description, in_out_status) AS SELECT
     off_name.LAST_NAME
    ,off_name.FIRST_NAME
    ,off_bkg.ACTIVE_FLAG
    ,off_name.OFFENDER_ID
    ,off_bkg.OFFENDER_BOOK_ID
    ,off_name.OFFENDER_ID_DISPLAY
    ,agy_loc.AGY_LOC_ID
    ,agy_loc.ABBREVIATION
    ,CASE WHEN agy_loc.AGENCY_LOCATION_TYPE='OFF' THEN  STAFF.FIRST_NAME||', '||STAFF.LAST_NAME  ELSE liv_unit.DESCRIPTION END
    ,off_bkg.in_out_status
FROM offenders off_name, agency_locations agy_loc, offender_bookings off_bkg
LEFT OUTER JOIN staff_members staff ON (off_bkg.assigned_staff_id = STAFF.staff_id)
LEFT OUTER JOIN living_units liv_unit ON (off_bkg.LIVING_UNIT_ID = liv_unit.LIVING_UNIT_ID)
WHERE off_name.OFFENDER_ID  =  off_bkg.OFFENDER_ID and agy_loc.AGY_LOC_ID  =  off_bkg.AGY_LOC_ID   and agy_loc.AGY_LOC_ID IN (select C.AGY_LOC_ID
                   from   CASELOAD_AGENCY_LOCATIONS C,
                               STAFF_MEMBERS S
                  where S.WORKING_CASELOAD_ID = C.CASELOAD_ID
                  and      S.USER_ID =  upper(USER)
                  and   C.AGY_LOC_ID NOT IN ('OUT', 'TRN'))
 
 
;


CREATE OR REPLACE VIEW v_living_unit_off_bkgs (agy_loc_id, living_unit_id, living_unit_desc, parent_living_unit_id, root_living_unit_id, in_living_unit_id, in_internal_location_id, in_out_status, active_flag, offender_id, offender_book_id) AS SELECT
    /* MODIFICATION HISTORY
    Person       Date      Version       Comments
    David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
    GC           1-may-012   2.1
    */
  lu.agy_loc_id ,
  lu.living_unit_id ,
  lu.description ,
  lu.parent_living_unit_id ,
  lu.root_living_unit_id ,
  bkg.living_unit_id ,
  bkg.AGENCY_IML_ID ,
  bkg.IN_OUT_STATUS ,
  bkg.Active_flag ,
  bkg.offender_ID ,
  bkg.Offender_Book_ID
   FROM (WITH RECURSIVE cte AS (
    select
    lu.agy_loc_id, lu.living_unit_id ROOT_LIVING_UNIT_ID,lu.living_unit_id                 LIVING_UNIT_ID,lu.parent_living_unit_id          PARENT_LIVING_UNIT_ID,lu.description                    DESCRIPTION
    FROM
    Living_Units lu  UNION ALL
    select
    lu.agy_loc_id, lu.living_unit_id ROOT_LIVING_UNIT_ID,lu.living_unit_id                 LIVING_UNIT_ID,lu.parent_living_unit_id          PARENT_LIVING_UNIT_ID,lu.description                    DESCRIPTION
    FROM
    Living_Units lu JOIN cte c ON (c.Living_unit_ID = lu.parent_Living_unit_ID )

) SELECT * FROM cte
) lu
LEFT OUTER JOIN offender_bookings bkg ON (lu.Living_unit_ID = bkg.living_unit_id AND lu.agy_loc_id = bkg.AGY_LOC_ID);

CREATE OR REPLACE VIEW v_off_ext_movements (offender_book_id, offender_id_display, last_name, first_name, movement_seq, movement_date, movement_time, internal_schedule_type, internal_schedule_reason_code, movement_type, movement_reason_code, direction_code, arrest_agency_loc_id, to_prov_stat_code, escort_code, from_agy_loc_id, from_agy_loc_desc, to_agy_loc_id, to_agy_loc_desc, active_flag, escort_text, comment_text, reporting_date, to_city, to_city_desc, from_city, from_city_desc, reporting_time, to_address_id, to_address_desc, from_address_id, from_address_desc) AS SELECT
/* =========================================================
   Version Number = 10.2.1.0  Date Modified = 03/18/2008
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date           Version          Comments
   ---------     ------         ------------     ------------------------------
   Rose          18-MAR-2008    10.2.1.0         #4428: Added FROM_AGY_LOC_DESC, TO_AGY_LOC_DESC, TO_CITY_DESC, FROM_CITY_DESC, TO_ADDRESS_ID, TO_ADDRESS_DESC, FROM_ADDRESS_ID, FROM_ADDRESS_DESC columns to the view
   Girish        22-FEB-2001    4.11.0.0         Added OFFENDER_ID_DISPLAY column to the view
*/
OFF_EM.OFFENDER_BOOK_ID,
O.OFFENDER_ID_DISPLAY,
O.LAST_NAME,
O.FIRST_NAME,
OFF_EM.MOVEMENT_SEQ,
OFF_EM.MOVEMENT_DATE,
OFF_EM.MOVEMENT_TIME,
OFF_EM.INTERNAL_SCHEDULE_TYPE,
OFF_EM.INTERNAL_SCHEDULE_REASON_CODE,
OFF_EM.MOVEMENT_TYPE,
OFF_EM.MOVEMENT_REASON_CODE,
OFF_EM.DIRECTION_CODE,
OFF_EM.ARREST_AGENCY_LOC_ID,
OFF_EM.TO_PROV_STAT_CODE,
OFF_EM.ESCORT_CODE,
OFF_EM.FROM_AGY_LOC_ID,
(SELECT DESCRIPTION
 FROM AGENCY_LOCATIONS
 WHERE AGY_LOC_ID = OFF_EM.FROM_AGY_LOC_ID) FROM_AGY_LOC_DESC,
OFF_EM.TO_AGY_LOC_ID,
(SELECT DESCRIPTION
 FROM AGENCY_LOCATIONS
 WHERE AGY_LOC_ID = OFF_EM.TO_AGY_LOC_ID) TO_AGY_LOC_DESC,
OFF_EM.ACTIVE_FLAG,
OFF_EM.ESCORT_TEXT,
OFF_EM.COMMENT_TEXT,
OFF_EM.REPORTING_DATE,
OFF_EM.TO_CITY,
(SELECT DESCRIPTION
 FROM REFERENCE_CODES
 WHERE CODE = OFF_EM.TO_CITY
 AND DOMAIN = 'CITY') TO_CITY_DESC,
OFF_EM.FROM_CITY,
(SELECT DESCRIPTION
 FROM REFERENCE_CODES
 WHERE CODE = OFF_EM.FROM_CITY
 AND DOMAIN = 'CITY') FROM_CITY_DESC,
OFF_EM.REPORTING_TIME,
OFF_EM.TO_ADDRESS_ID,
(SELECT FULL_ADDRESS
 FROM V_ADDRESSES
 WHERE ADDRESS_ID = OFF_EM.TO_ADDRESS_ID) TO_ADDRESS_DESC,
OFF_EM.FROM_ADDRESS_ID,
(SELECT FULL_ADDRESS
 FROM V_ADDRESSES
 WHERE ADDRESS_ID = OFF_EM.FROM_ADDRESS_ID) FROM_ADDRESS_DESC
  FROM offender_external_movements OFF_EM,
       offender_bookings OB,
       offenders O
 WHERE OFF_EM.offender_book_id = OB.offender_book_id
 AND   O.offender_id = OB.offender_id
 AND   O.root_offender_id = OB.root_offender_id

 
;

CREATE OR REPLACE VIEW v_offender_addresses (address_id, address_type, address_type_desc, offender_id, offender_book_id, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_information, street_direction, street_direction_desc, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, validated_flag) AS SELECT
/* =========================================================
   Version Number = 1.5.1.2 Date Modified = 20/Mar/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date                          Version   Comments
   ---------           ------                            ---------  	 ------------------------------
   Sarah                 20-Mar-2007             1.5.1.2        Amended where clause, the view  used to retrieve just  addresses associated with
                                                                             working name not aliases.Hence  instead of  BKG.OFFENDER_ID(+)--> BKG.ROOT_OFFENDER_ID(+)
   VIKAS GROVER  16-FEB-20007    		1.5.1.1 		New Columns added
   NIKO              26-JAN-2007         1.5.1.0      Replace the columns in V_STF_ADDR block:
                                                                                SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     06/21/2005 10.1.01       NOMIS new addresses table
   David Ng     06/22/2005 1.4           have both Offender_id and offender_book_id
   David Ng     06/23/2005 1.5           address description for Address_type
*/
addr.ADDRESS_ID
,addr.ADDRESS_TYPE
,addr.ADDRESS_TYPE_DESC
,addr.OWNER_ID
,Offender_Book_ID
,addr.start_date
,addr.end_date
,addr.ACTIVE_FLAG
,addr.HOUSE
,addr.STREET
,addr.AREA
,addr.COUNTRY
,addr.SUITE_NUMBER
,addr.STREET_NUMBER
,addr.STREET_INFORMATION
,addr.STREET_DIRECTION
,addr.STREET_DIRECTION_DESC
,addr.CITY_CODE
,addr.CITY_NAME
,addr.PROV_STATE_CODE
,addr.PROV_STATE_DESC
,addr.ZIP_POSTAL_CODE
,addr.COUNTRY_CODE
,addr.COMMENT_TEXT
,addr.PRIMARY_FLAG
,addr.MAIL_FLAG
,addr.VALIDATED_FLAG
FROM offender_bookings bkg, v_addresses addr
WHERE OWNER_CLASS = 'OFF';

CREATE OR REPLACE VIEW v_merge_transaction_rules (merge_transaction_id, transaction_type, process_id, process_name, transfer_flag, timeframe_flag, begin_date, end_date, rule_id, rule_desc, table_name, application_code, root_flag, completed_flag, transfer_seq) AS select
VMTP.merge_transaction_id
,VMTP.transaction_type
,VMTP.process_id
,VMTP.process_name
,VMTP.transfer_flag
,VMTP.timeframe_flag
,VMTP.begin_date
,VMTP.end_date
,MPR.rule_id
,MPR.description rule_desc
,TBT.table_name
,TBT.application_code
,TBT.root_flag
,TBT.completed_flag
,TBT.transfer_seq
FROM v_merge_transaction_processes vmtp, merge_process_rules mpr
LEFT OUTER JOIN transfer_booking_tables tbt ON (MPR.Application_code = TBT.Application_code)
WHERE VMTP.process_id = MPR.process_id  AND TBT.process_method <> 'CUSTOM'

;

CREATE OR REPLACE VIEW v_offender_movements (offender_book_id, movement_date, movement_time, movement_type, comment_text, assignment, agy_loc_id) AS SELECT
     off_em.OFFENDER_BOOK_ID,
     off_em.MOVEMENT_DATE,
     off_em.MOVEMENT_TIME,
     off_em.MOVEMENT_TYPE,
     off_em.COMMENT_TEXT,
     'FROM '||off_em.FROM_AGY_LOC_ID||' TO '||off_em.TO_AGY_LOC_ID,
     off_bkg.AGY_LOC_ID
FROM
    offender_bookings  off_bkg,
    offender_external_movements  off_em,
    reference_codes  ref_code
 Where 	off_em.movement_type 	= ref_code.code
 and	ref_code.domain 		= 'MOVE_TYPE'

UNION

 Select	bed.offender_book_id,
 	bed. assignment_date, bed.assignment_time,
 	'HOUSING_LOCATION', Null,
 	unit.description,
 	book.agy_loc_id
 From	bed_assignment_histories  bed,
 	 Living_units unit,
 	offender_bookings book
 where	bed.living_unit_id		= unit.living_unit_id
 and	book.offender_book_id 	= bed.offender_book_id
 
UNION

 Select	INT.Offender_book_id, movement_date,
 	INT.movement_start_time,
 	ref.Description,
 	INT. Comment_Text,
 	INT.visit_loc_id,
 	INT.agy_loc_id
 From	Offender_internal_movements INT,
 	Reference_codes	ref
 where	ref.domain 	= 'MOVE_TYPE'
 and	ref.code 		= INT.movement_type
 
UNION

 Select	ass.offender_book_id,
 	ass.assessment_date,
 	ass.assessment_date,
 	 'Assessment',
 	ref.description,
 	ass.place_agy_loc_id,
 	bkg.agy_loc_id
 From	Offender_assessments ass,
 	offender_bookings bkg,
 	reference_codes ref
 where	ass.offender_book_id 	= bkg.offender_book_id
 and	ref.domain 		= 'SUP_LVL_TYPE'
 and	ref.code = coalesce( REVIEW_SUP_LEVEL_TYPE,
 		       coalesce( OVERRIDED_SUP_LEVEL_TYPE,
 			CALC_SUP_LEVEL_TYPE))
 
 
;

CREATE OR REPLACE VIEW v_court_events (event_id, event_class, event_type, case_id, case_info_prefix, case_info_number, offender_id_display, last_name, first_name, middle_name, birth_date, offender_book_id, booking_active_flag, living_unit_id, level_1_code, level_2_code, level_3_code, level_4_code, offender_agy_loc_id, offender_agy_loc_desc, event_date, start_time, end_time, court_event_type, court_event_type_desc, movement_reason_code, movement_reason_desc, judge_name, event_status, result_code, parent_event_id, agy_loc_id, agy_loc_id_name, comment_text, event_outcome, check_sum) AS SELECT
/* MODIFICATION HISTORY 
   Person       Date      Version       Comments 
   David Ng     15/10/2005  2.0         NOMIS project(10.2.0) 
   David Ng     10/02/2006  2.1         Add Check sum column 
                                        Court_Event_type use domain (MOVE_RSN) with 
   David Ng     27/01/2006  2.2         User new check sum 
   David Ng     20/07/2006  2.3         Add Booking active flag 
*/
 
          ce.event_id, 
          SUBSTR(CASE WHEN bkg.active_flag='Y' THEN  'EXT_MOV'  ELSE 'COMM' END , 1, 12), 
          SUBSTR('CRT', 1, 12), ce.case_id, oc.case_info_prefix, 
          oc.case_info_number, OFF.offender_id_display, OFF.last_name, 
          OFF.first_name, OFF.middle_name, OFF.birth_date, 
          ce.offender_book_id, 
          CASE WHEN bkg.active_flag='Y' THEN 'Y'  ELSE CASE WHEN bkg.community_active_flag='Y' THEN 'Y'  ELSE 'N' END  END , 
          bkg.living_unit_id, lu.level_1_code, 
          lu.level_2_code, lu.level_3_code, lu.level_4_code, bkg.agy_loc_id, 
          al2.description, ce.event_date, ce.start_time, ce.end_time, 
          ce.court_event_type, 
          SUBSTR(oms_miscellaneous_getdesccode('MOVE_RSN', 
                                                 ce.court_event_type 
                                                ), 
                  1, 
                  40 
                 ), 
          ce.court_event_type, 
          SUBSTR(oms_miscellaneous_getdesccode('MOVE_RSN', 
                                                 ce.court_event_type 
                                                ), 
                  1, 
                  40 
                 ), 
         ce.judge_name, ce.event_status, ce.result_code, ce.parent_event_id, 
          ce.agy_loc_id, al.description, ce.comment_text, ce.EVENT_OUTCOME, 
          Tag_schedule_check_sum(coalesce(ce.MODIFY_DATETIME, ce.CREATE_DATETIME)) 
     FROM offenders off, agency_locations al, court_events ce
LEFT OUTER JOIN offender_cases oc ON (ce.case_id = oc.case_id)
, offender_bookings bkg
LEFT OUTER JOIN agency_locations al2 ON (bkg.agy_loc_id = al2.agy_loc_id)
LEFT OUTER JOIN living_units lu ON (bkg.living_unit_id = lu.living_unit_id)
WHERE ce.agy_loc_id = al.agy_loc_id AND ce.offender_book_id = bkg.offender_book_id   AND bkg.offender_id = OFF.offender_id;

CREATE OR REPLACE VIEW v_offenders (offender_id, sex_code, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, booking_no, booking_begin_date, booking_end_date, agy_loc_id, living_unit_id, disclosure_flag, active_flag, booking_status, description, in_out_status, status_display) AS SELECT /* =========================================================
       || Version Number = 1.3  Date Modified = 06/21/2005
          =========================================================
          Remove RULE hint */
       off_name.OFFENDER_ID
      ,off_name.SEX_CODE
      ,off_name.ALIAS_OFFENDER_ID
      ,off_name.OFFENDER_ID_DISPLAY
      ,off_name.LAST_NAME
      ,off_name.FIRST_NAME
      ,off_name.MIDDLE_NAME
      ,off_name.SUFFIX
      ,off_name.BIRTH_DATE
      ,off_bkg.OFFENDER_BOOK_ID
      ,off_bkg.BOOKING_NO
      ,off_bkg.BOOKING_BEGIN_DATE
      ,off_bkg.BOOKING_END_DATE
      ,off_bkg.AGY_LOC_ID
      ,off_bkg.LIVING_UNIT_ID
      ,off_bkg.DISCLOSURE_FLAG
      ,off_bkg.ACTIVE_FLAG
      ,off_bkg.BOOKING_STATUS
      ,liv_unit.DESCRIPTION
      ,off_bkg.IN_OUT_STATUS
      ,CASE WHEN off_bkg.ACTIVE_FLAG='Y' THEN 'ACTIVE'  ELSE 'INACTIVE' END ||'-'||off_bkg.IN_OUT_STATUS STATUS_DISPLAY
 FROM offenders off_name, offender_bookings off_bkg
LEFT OUTER JOIN living_units liv_unit ON (off_bkg.living_unit_id = liv_unit.living_unit_id)
WHERE off_bkg.offender_id = off_name.offender_id;

CREATE OR REPLACE VIEW v_per_agy_corp (person_id, agy_corp_id, party_id, party_type, agy_corp_name, last_name, first_name, middle_name, relationship_code) AS SELECT /* =========================================================
   Version Number = 1.0  Date Modified = 10/03/2006.
   ========================================================= */
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   ---------   ------      ------------  ------------------------------
   Nasir       27-JUL-2006 1.0            Intial Creation
*/
     DISTINCT voap.person_id
       , voap.agy_corp_id
       , voap.party_id
       , voap.party_type
       , voap.agy_corp_name
       , voap.last_name
       , voap.first_name
       , voap.middle_name
       , voap.relationship_code
FROM v_offender_associated_parties voap

;

CREATE OR REPLACE VIEW v_trip_agy
AS SELECT st.scheduled_trip_id AS trip_id,
    rf.route_name,
    rf.leg_id,
    rf.leg_seq,
    rf.agy_loc_id,
    rf.from_ovnt_flag,
    rf.to_dtl,
    rf.to_agy,
    rf.count_flag,
    rf.ovnt_flag,
    st.departure_date + '1 day'::interval * (( SELECT sum(
                CASE
                    WHEN rx.from_ovnt_flag::text = 'Y'::text THEN 1
                    ELSE 0
                END) AS count_ov
           FROM v_route_flow rx
          WHERE rx.route_name::text = rf.route_name::text AND (rx.leg_id * 1000 + rx.leg_seq) <= (rf.leg_id * 1000 + rf.leg_seq)))::double precision AS depart_date,
    st.trip_code,
    st.departure_date,
        CASE
            WHEN rf.from_ovnt_flag::text = 'Y'::text THEN rf.leg_id + 1
            ELSE rf.leg_id::integer
        END AS from_leg_id,
        CASE
            WHEN rf.from_ovnt_flag::text = 'Y'::text THEN 1
            ELSE rf.leg_seq + 1
        END +
        CASE
            WHEN
            CASE
                WHEN rf.from_ovnt_flag::text = 'Y'::text THEN rf.leg_id + 1
                ELSE rf.leg_id::integer
            END = 1 THEN '-1'::integer
            ELSE 0
        END AS from_leg_seq,
    st.cancel_flag AS trip_cancel_flag
   FROM v_route_flow rf,
    scheduled_trips st
  WHERE st.route_name::text = rf.route_name::text;

CREATE OR REPLACE VIEW v_housing_locations (living_unit_code1, living_unit_code2, living_unit_code3, living_unit_code4, agy_loc_id, offender_id, offender_id_display, last_name, first_name, middle_name, imprisonment_status, in_out_status) AS SELECT
/* =========================================================
   Version Number = 4.11.0.1  Date Modified = 11/07/2014
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	Date          Version     	      Comments
   --------- 	 ------         ------------  	    ------------------------------
   Edward      07-NOV-2014    4.11.0.1            HPQC#24671: Error_flag was added to find imprisonment_status
   Girish      22-FEB-2001    4.11.0.0 02/22/2001 Added OFFENDER_ID_DISPLAY column to the view
*/
     CASE WHEN coalesce(liv_unit1.LIVING_UNIT_CODE::text, '') = '' THEN        CASE WHEN coalesce(liv_unit2.LIVING_UNIT_CODE::text, '') = '' THEN           CASE WHEN coalesce(liv_unit3.LIVING_UNIT_CODE::text, '') = '' THEN              liv_unit4.LIVING_UNIT_CODE      ELSE liv_unit3.LIVING_UNIT_CODE END       ELSE liv_unit2.LIVING_UNIT_CODE END       ELSE liv_unit1.LIVING_UNIT_CODE END
    ,CASE WHEN coalesce(liv_unit1.LIVING_UNIT_CODE::text, '') = '' THEN        CASE WHEN coalesce(liv_unit2.LIVING_UNIT_CODE::text, '') = '' THEN           CASE WHEN coalesce(liv_unit3.LIVING_UNIT_CODE::text, '') = '' THEN              NULL      ELSE liv_unit4.LIVING_UNIT_CODE END       ELSE liv_unit3.LIVING_UNIT_CODE END       ELSE liv_unit2.LIVING_UNIT_CODE END 
    ,CASE WHEN coalesce(liv_unit1.LIVING_UNIT_CODE::text, '') = '' THEN        CASE WHEN coalesce(liv_unit2.LIVING_UNIT_CODE::text, '') = '' THEN           NULL      ELSE liv_unit4.LIVING_UNIT_CODE END       ELSE liv_unit3.LIVING_UNIT_CODE END 
    ,CASE WHEN coalesce(liv_unit1.LIVING_UNIT_CODE::text, '') = '' THEN        NULL      ELSE liv_unit4.LIVING_UNIT_CODE END 
    ,off_bkg.AGY_LOC_ID
    ,LPAD(coalesce(off_name.ALIAS_OFFENDER_ID,off_name.OFFENDER_ID)::varchar,10,'0')
    ,off_name.OFFENDER_ID_DISPLAY
    ,off_name.LAST_NAME
    ,off_name.FIRST_NAME
    ,off_name.MIDDLE_NAME
    ,CASE WHEN off_imp_sta.ERROR_FLAG='Y' THEN NULL  ELSE off_imp_sta.IMPRISONMENT_STATUS END 
    ,CASE WHEN off_bkg.IN_OUT_STATUS='IN' THEN 'I' WHEN off_bkg.IN_OUT_STATUS='OUT' THEN 'O' END 
FROM offenders off_name, living_units liv_unit4
LEFT OUTER JOIN living_units liv_unit3 ON (liv_unit4.parent_living_unit_id = liv_unit3.living_unit_id)
LEFT OUTER JOIN living_units liv_unit2 ON (liv_unit3.parent_living_unit_id = liv_unit2.living_unit_id)
LEFT OUTER JOIN living_units liv_unit1 ON (liv_unit2.parent_living_unit_id = liv_unit1.living_unit_id)
, offender_bookings off_bkg
LEFT OUTER JOIN offender_imprison_statuses off_imp_sta ON (off_bkg.offender_book_id = off_imp_sta.offender_book_id)
WHERE (off_imp_sta.imprison_status_seq =
       (SELECT MAX(off_imp1.imprison_status_seq)
          FROM offender_imprison_statuses off_imp1
         WHERE off_imp1.offender_book_id =
               off_imp_sta.offender_book_id)
      OR coalesce(off_imp_sta.imprison_status_seq::text, '') = '') AND off_bkg.living_unit_id         = liv_unit4.living_unit_id    AND off_bkg.active_flag = 'Y' AND off_bkg.offender_id = off_name.offender_id
;

CREATE OR REPLACE VIEW v_liv_unit (living_unit_id, agy_loc_id, capacity, level_1_type, level_1_desc, level_1_code, level_1_list_seq, level_2_type, level_2_desc, level_2_code, level_2_list_seq, level_3_type, level_3_desc, level_3_code, level_3_list_seq, level_4_type, level_4_desc, level_4_code, level_4_list_seq, description, active_flag) AS SELECT
          coalesce(liv_unit4.living_unit_id, coalesce(liv_unit3.living_unit_id, coalesce(liv_unit2.living_unit_id,liv_unit1.living_unit_id)))
         ,coalesce(liv_unit4.agy_loc_id, coalesce(liv_unit3.agy_loc_id, coalesce(liv_unit2.agy_loc_id,liv_unit1.agy_loc_id)))
         ,coalesce(liv_unit4.capacity, coalesce(liv_unit3.capacity,coalesce(liv_unit2.capacity,liv_unit1.capacity)))
         ,liv_unit1.LIVING_UNIT_TYPE
         ,ref1.description
         ,liv_unit1.living_unit_code
         ,liv_unit1.list_seq
         ,liv_unit2.LIVING_UNIT_TYPE
         ,ref2.description
         ,liv_unit2.living_unit_code
         ,liv_unit2.list_seq
         ,liv_unit3.LIVING_UNIT_TYPE
         ,ref3.description
         ,liv_unit3.living_unit_code
         ,liv_unit3.list_seq
         ,liv_unit4.LIVING_UNIT_TYPE
         ,ref4.description
         ,liv_unit4.living_unit_code
         ,liv_unit4.list_seq
         ,liv_unit4.description
         ,coalesce(liv_unit4.active_flag, coalesce(liv_unit3.active_flag,coalesce(liv_unit2.active_flag,liv_unit1.active_flag)))
FROM reference_codes ref1, agency_locations agy_loc1, living_units liv_unit1
LEFT OUTER JOIN living_units liv_unit2 ON (liv_unit1.living_unit_id = liv_unit2.parent_living_unit_id)
LEFT OUTER JOIN agency_locations agy_loc2 ON (liv_unit2.agy_loc_id = agy_loc2.agy_loc_id AND liv_unit2.living_unit_type = agy_loc2.housing_lev_2_code)
LEFT OUTER JOIN living_units liv_unit3 ON (liv_unit2.living_unit_id = liv_unit3.parent_living_unit_id)
LEFT OUTER JOIN reference_codes ref2 ON (agy_loc2.housing_lev_2_code = ref2.code AND 'LIVING_UNIT' = ref2.domain)
LEFT OUTER JOIN agency_locations agy_loc3 ON (liv_unit3.agy_loc_id = agy_loc3.agy_loc_id AND liv_unit3.living_unit_type = agy_loc3.housing_lev_3_code)
LEFT OUTER JOIN living_units liv_unit4 ON (liv_unit3.living_unit_id = liv_unit4.parent_living_unit_id)
LEFT OUTER JOIN reference_codes ref3 ON (agy_loc3.housing_lev_3_code = ref3.code AND 'LIVING_UNIT' = ref3.domain)
LEFT OUTER JOIN agency_locations agy_loc4 ON (liv_unit4.agy_loc_id = agy_loc4.agy_loc_id AND liv_unit4.living_unit_type = agy_loc4.housing_lev_4_code)
LEFT OUTER JOIN reference_codes ref4 ON (agy_loc4.housing_lev_4_code = ref4.code AND 'LIVING_UNIT' = ref4.domain)
WHERE liv_unit1.agy_loc_id 			= agy_loc1.agy_loc_id and liv_unit1.living_unit_type 		= agy_loc1.housing_lev_1_code and ref1.code				= agy_loc1.housing_lev_1_code and ref1.domain				= 'LIVING_UNIT';

CREATE OR REPLACE VIEW v_offender_visit (start_time, end_time, surname, given, id, offender_id_display, housing_loc, visit_type, location, living_unit_id, visit_date, offender_book_id, movement_seq, cancel_flag, cancel_reason) AS SELECT
     /* =========================================================
     || Version Number = 1.2  Date Modified = 06/11/2003
      ========================================================= */
     oim.MOVEMENT_START_TIME
    ,oim.MOVEMENT_END_TIME
    ,off.LAST_NAME
    ,off.FIRST_NAME
    ,off.OFFENDER_ID
    ,off.OFFENDER_ID_DISPLAY
    ,lu.DESCRIPTION
    ,oim.VISIT_TYPE
    ,oim.AGY_LOC_ID
    ,oim.LIVING_UNIT_ID
    ,oim.MOVEMENT_DATE
    ,ob.OFFENDER_BOOK_ID
    ,oim.MOVEMENT_SEQ
    ,CASE WHEN coalesce(oim.cancellation_reason_code::text, '') = '' THEN 'N'  ELSE 'Y' END
    ,oim.CANCELLATION_REASON_CODE
FROM
    living_units  lu,
    offenders  off,
    offender_internal_movements  oim,
    offender_bookings  ob
 WHERE lu.living_unit_id=oim.living_unit_id
 AND  off.offender_id=ob.offender_id
 AND oim.offender_book_id=ob.offender_book_id
 AND ob.offender_book_id IN  ((SELECT off_bkg.offender_book_id
                                FROM offender_bookings off_bkg,
                                     staff_members sm,
                                     caseload_agency_locations cal
                               WHERE off_bkg.root_offender_id = ob.root_offender_id
                                 AND off_bkg.active_flag ='Y'
                                 AND off_bkg.agy_loc_id = cal.agy_loc_id
                                 AND cal.caseload_id = sm.working_caseload_id
                                 AND sm.user_id = USER)
                                        
UNION ALL
(SELECT oem.offender_book_id
                                 FROM offender_external_movements oem,
                                      staff_members sm,
                                      caseload_agency_locations cal
                                WHERE oem.offender_book_id = ob.offender_book_id
                                  AND oem.from_agy_loc_id = cal.agy_loc_id
                                  AND cal.caseload_id = sm.working_caseload_id
                                  AND sm.user_id = USER
                                  AND NOT EXISTS (SELECT NULL
                                                    FROM offender_bookings off_bkg1
                                         WHERE off_bkg1.root_offender_id = ob.root_offender_id
                                  AND     off_bkg1.active_flag = 'Y')))
 
 
;

CREATE OR REPLACE VIEW v_int_loc_offenders (agy_loc_id, root_internal_location_id, offender_book_id, offender_id, last_name, first_name, offender_id_display, internal_location_id, internal_location_desc, parent_internal_location_id, in_out_status, living_unit_id, living_unit_desc, alert_flag) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     03/01/2006 2.0           NOMIS project(10.2.0)
*/
ilo.AGY_LOC_ID
,ilo.ROOT_Internal_Location_ID
,ilo.OFFENDER_BOOK_ID
,ilo.OFFENDER_ID
,ilo.LAST_NAME
,ilo.FIRST_NAME
,ilo.OFFENDER_ID_DISPLAY
,ilo.Internal_Location_ID
,ilo.Internal_location_desc
,ilo.PARENT_Internal_Location_ID
,ilo.in_out_status
,ilo.Living_unit_id
,lu.description
,(Select CASE WHEN count(*)=0 THEN  'N'  ELSE 'Y' END  FROM Offender_alerts oa
  Where  oa.offender_book_id = ilo.Offender_Book_id
  and    oa.alert_status = 'ACTIVE')
FROM v_int_loc_offenders_2 ilo
LEFT OUTER JOIN living_units lu ON (ilo.living_unit_id = lu.Living_unit_ID)
WHERE (Offender_book_id IS NOT NULL AND Offender_book_id::text <> '');

CREATE OR REPLACE VIEW v_offender_all_schedules_3 (event_id, offender_book_id, agy_loc_id, event_date, start_time, end_time, event_class, event_type, event_sub_type, event_status, event_outcome, confirm_flag, outcome_reason_code, comment_text, reference_id, application_date, application_time, return_date, return_time, to_agy_loc_id, escort_code, direction_code, schedule_movement_time, to_internal_location_id, from_city_code, to_city_code, credited_hours, piece_work, engagement_code, understanding_code, details, unpaid_work_behaviour, unpaid_work_action, sick_note_received_date, sick_note_expiry_date, unexcused_absence_flag, in_time, out_time, transport_code, performance_code, agreed_travel_hour, check_box_1, check_box_2, hidden_comment_text, in_charge_staff_id, off_prgref_id, contact_person_name, to_address_owner_class, to_address_id, to_corporate_id, unpaid_work_supervisor, ta_id, record_source, check_sum, prov_state_code, scheduled_trip_id) AS SELECT
   /* =========================================================
    Version Number = 1.0 Date Modified = 04/08/2013
    ========================================================= */
   -- MODIFICATION HISTORY
   -- Person      Date        Comments
   -- ---------   ------      -------------------------------------------
/* MODIFICATION HISTORY
   Person        Date              Version        Comments
   ---------    ------          ---------     ------------------------------------------------------------------------------
   David Ng     15/10/2005       2.0            NOMIS project(10.2.0)
   David Ng     17/01/2006       2.1            Add off_Prgref_ID column value
   David Ng     24/01/2006       2.2            Using TAG_Schedule check sum function
   David Ng     08/02/2006       2.3            Event Class of CRT court event depend on
                                                          In_Out_Status and Active_Flag
   David Ng     15/02/2006       2.4            Add Offender Visit and V_Offender_Course_Events
                                                          Drop out Offender_schedule_outcomes
   David Ng     07/07/2006       2.5            Use new Offender_Visit_schedules
   David Ng     08/08/2006       2.6            Add To_Agy_Loc_ID from V_offender_course_events
   Niko           MAR-13-2007    2.6.1.0       Added a new column - prov_state_code
                                                          Removed 2 columns - TO_COUNTRY_CODE and OJ_LOCATION_CODE
   Sarah        18-Mar-2008      2.6.1.1          Modified the direction code and eventclass for court movement
   Farhad       23-JULY-2009     2.6.1.2        Add a new column - scheduled_trip_id
   David Ng     03-SEP-2009      2.6.1.3          Give direction code for External Movement for v_offender_Course_events.
   Abu          13-Nov-2009      2.6.1.4        Modified to fix From Agy_Loc_ID in Court_Events and TAP
                                                                    (Offender_ind_schedules) sections
   Vikas Gupta  23-Mar-2011      2.6.1.5        Added schedules for Parole Heraings
   Vikas Gupta  21-Feb-2012      2.6.1.6        Fixed HPQC#13101- Added schedule type and reason for Parole Heraings
   Jason Xu     08-Apr-2013      1.0            copy from V_OFFENDER_ALL_SCHEDULES_2 2.6.1.6 for SD form
                                                OIIWLTWJ performance issue
*/
sch.EVENT_ID
,OFFENDER_BOOK_ID
,CASE WHEN coalesce(agy_loc_id::text, '') = '' THEN  CASE WHEN coalesce(parent_event_id::text, '') = '' THEN  agy_loc_id  ELSE CASE WHEN direction_code='IN' THEN (SELECT   schx.to_agy_loc_id                                              FROM   OFFENDER_IND_SCHEDULES schx                                             WHERE   schx.event_id = sch.parent_event_id                                               AND schx.offender_book_id = sch.offender_book_id                                            )  ELSE agy_loc_id END  END   ELSE agy_loc_id END  agy_loc_id
,EVENT_DATE
,START_TIME
,END_TIME
,EVENT_CLASS
,EVENT_TYPE
,event_sub_type
,EVENT_STATUS
,event_outcome
,confirm_flag
,sch.outcome_reason_code
,COMMENT_text
,REFERENCE_ID
,APPLICATION_DATE
,APPLICATION_TIME
,RETURN_DATE
,RETURN_TIME
,TO_AGY_LOC_ID
,ESCORT_CODE
,direction_code
,CASE WHEN direction_code='OUT' THEN  start_time WHEN direction_code='IN' THEN  End_time  ELSE NULL END  -- schedule_movement_time,
,To_Internal_location_ID
,From_City_Code
,To_City_Code
,CREDITED_HOURS
,piece_work
,engagement_code
,understanding_code
,Details
,unpaid_work_behaviour
,unpaid_work_action
,sick_note_received_date
,sick_note_expiry_date
,unexcused_absence_flag
,IN_TIME
,OUT_TIME
,TRANSPORT_CODE
,PERFORMANCE_CODE
--,OJ_LOCATION_CODE
--,TO_COUNTRY_CODE
,AGREED_TRAVEL_HOUR
,CHECK_BOX_1
,CHECK_BOX_2
,HIDDEN_COMMENT_TEXT
,IN_CHARGE_STAFF_ID
,OFF_PRGREF_ID
,contact_person_name
,TO_address_owner_class
,TO_address_ID
,TO_CORPORATE_ID
,UNPAID_WORK_SUPERVISOR
,TA_ID
,'SCH'
,Tag_Schedule_check_sum(coalesce(sch.MODIFY_DATETIME, sch.CREATE_DATETIME))
,PROV_STATE_CODE
,scheduled_trip_id
FROM OFFENDER_IND_SCHEDULES sch ,OFFENDER_IND_SCH_WAIT_LISTS WL
WHERE EVENT_STATUS <> 'DEL'
AND   sch.event_id = wl.event_id

UNION ALL

SELECT
   event_id,
   offender_book_id,
   AGY_LOC_ID,     -- agy_loc_id,
   event_date,
   start_time,
   end_time,
   event_class,
   event_type,
   event_sub_type,
   event_status,
   event_outcome,
   NULL,     --confirm_flag,
   outcome_reason_code,
   comment_text,
   reference_id,
   NULL,     -- application_date,
   NULL,     -- application_time,
   NULL,     -- return_date,
   NULL,     -- return_time,
   To_Agy_Loc_ID,  -- to_agy_loc_id,
   NULL,     -- escort_code,
   direction_code,     -- direction_code,
   schedule_movement_time, -- schedule_movement_time
   to_internal_location_id,
   NULL,     -- from_city_code,
   NULL,     -- to_city_code,
   credited_hours,
   piece_work,
   engagement_code,
   understanding_code,
   NULL,     -- details,
   Behaviour_code,  -- unpaid_work_behaviour,
   Action_Code,   -- unpaid_work_action,
   sick_note_received_date,
   sick_note_expiry_date,
   unexcused_absence_flag, -- unexcused_absence_flag,
   in_time,
   out_time,
   NULL,     -- transport_code,
   performance_code,
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
   agreed_travel_hour,
   NULL,     -- check_box_1,
   NULL,     -- check_box_2,
   NULL,     -- hidden_comment_text,
   SUPERVISOR_STAFF_ID, -- in charge Staff ID
   off_prgref_id,
   NULL,     -- contact_person_name,
   NULL,     -- to_address_owner_class,
   to_address_id,
   NULL,     -- to_corporate_id,
   NULL,     -- unpaid_work_supervisor,
   NULL,     -- ta_id,
   'V_OFF_CRS'
   ,check_sum
   ,NULL
   ,NULL
FROM v_offender_course_events_2

UNION ALL

SELECT
ce.event_id
,ce.offender_book_id
,CASE WHEN ce.direction_code='OUT' THEN  bkg.agy_loc_id  ELSE (SELECT   cex.agy_loc_id                   FROM   COURT_EVENTS cex                  WHERE   cex.event_id = ce.parent_event_id                ) END  agy_loc_id
,ce.event_date
,ce.start_time
,ce.end_time
,CASE WHEN bkg.in_out_status='IN' THEN  CASE WHEN bkg.active_flag='Y' THEN  'EXT_MOV'  ELSE 'COMM' END  WHEN bkg.in_out_status='OUT' THEN  CASE WHEN bkg.active_flag='Y' THEN 'EXT_MOV'  ELSE 'COMM' END   ELSE 'COMM' END
,'CRT'
,ce.court_event_type
,CASE WHEN coalesce(ce.event_status::text, '') = '' THEN  'SCH'  ELSE ce.event_status END 
,ce.event_outcome
,CASE WHEN ce.event_status='CONF' THEN  'Y'  ELSE 'N' END 
,ce.OUTCOME_REASON_CODE
,ce.comment_text
,ce.case_id          -- Reference ID
,NULL
,NULL
,NULL
,NULL
,ce.agy_loc_id
,NULL
,ce.direction_code
,ce.start_time --   schedule_movement_time,
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,'COURT'
,Tag_Schedule_check_sum(coalesce(CE.MODIFY_DATETIME, CE.CREATE_DATETIME))
,NULL
,ce.scheduled_trip_id
FROM  COURT_EVENTS ce, OFFENDER_BOOKINGS bkg,OFFENDER_IND_SCH_WAIT_LISTS WL
WHERE ce.offender_book_id = bkg.offender_book_id
AND   ce.event_id = wl.event_id

UNION ALL

SELECT
   event_id,
   offender_book_id,
   agy_loc_id,
   visit_date,    -- event_date,
   start_time,
   end_time,
   'INT_MOV',     -- event_class,
   'VISIT',     -- event_type,
   'VISIT',     -- event_sub_type,
   Event_status,    -- event_status,
   event_outcome,     -- event_outcome,
   NULL,     -- confirm_flag,
   outcome_reason_code,
   comment_text,
   NULL,          -- reference_id,
   NULL,     -- application_date,
   NULL,     -- application_time,
   NULL,     -- return_date,
   NULL,     -- return_time,
   AGY_LOC_ID,     -- to_agy_loc_id,
   NULL,     -- escort_code,
   NULL,     -- direction_code,
   NULL,     -- schedule_movement_time,
   Visit_Internal_location_ID, -- to_internal_location_id,
   NULL,     -- from_city_code,
   NULL,     -- to_city_code,
   NULL,     -- credited_hours,
   NULL,     -- piece_work,
   NULL,     -- engagement_code,
   NULL,     -- understanding_code,
   NULL,     -- details,
   NULL,     -- unpaid_work_behaviour,
   NULL,     -- unpaid_work_action,
   NULL,     -- sick_note_received_date,
   NULL,     -- sick_note_expiry_date,
   NULL,     -- unexcused_absence_flag,
   Start_Time,     -- in_time,
   End_time,     -- out_time,
   NULL,     -- transport_code,
   NULL,     -- performance_code,
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
   NULL,     -- agreed_travel_hour,
   NULL,     -- check_box_1,
   NULL,     -- check_box_2,
   NULL,     -- hidden_comment_text,
   NULL, -- Authorised_Staff_ID, -- in_charge_staff_id,
   NULL,     -- off_prgref_id,
   NULL,     -- contact_person_name,
   NULL,     -- to_address_owner_class,
   NULL,     -- to_address_id,
   NULL,     -- to_corporate_id,
   NULL,     -- unpaid_work_supervisor,
   NULL,     -- ta_id,
   'OFF_VIS'    -- record_source,
 ,Check_SUM            -- check_sum
 ,NULL
 ,NULL
FROM  v_Offender_visit_schedules_2 OV
WHERE (Event_ID IS NOT NULL AND Event_ID::text <> '') AND (Offender_Book_ID IS NOT NULL AND Offender_Book_ID::text <> '')

UNION ALL

SELECT
   OH.event_id,
   AIP.offender_book_id,
   AIL.agy_loc_id,
   OH.HEARING_date,     --event_date,
   OH.HEARING_time,     -- start_time,
   NULL,       -- end_time,
   'INT_MOV',       -- event_class,
   'OIC',       -- event_type,
   'OIC',       -- event_sub_type,
   OH.Event_Status,     -- event_status,
   NULL,       -- event_outcome,
   NULL,       -- confirm_flag,
   NULL,       -- outcome_reason_code,
   OH.comment_text,
   OH.OIC_Hearing_ID,    -- reference_id,
   NULL,       -- application_date,
   NULL,       -- application_time,
   NULL,       -- return_date,
   NULL,       -- return_time,
   AIL.Agy_Loc_ID,    -- to_agy_loc_id,
   NULL,       -- escort_code,
   NULL,       -- direction_code,
   NULL,       -- schedule_movement_time,
   OH.internal_location_id,
   NULL,      -- from_city_code,
   NULL,       -- to_city_code,
   NULL,       -- credited_hours,
   NULL,       -- piece_work,
   NULL,       -- engagement_code,
   NULL,       -- understanding_code,
   NULL,       -- details,
   NULL,       -- unpaid_work_behaviour,
   NULL,       -- unpaid_work_action,
   NULL,       -- sick_note_received_date,
   NULL,       -- sick_note_expiry_date,
   NULL,       -- unexcused_absence_flag,
   OH.Hearing_Time,     -- in_time,
   NULL,       -- out_time,
   NULL,       -- transport_code,
   NULL,       -- performance_code,
--   NULL,       -- oj_location_code,
--   NULL,       -- to_country_code,
   NULL,       -- agreed_travel_hour,
   NULL,       -- check_box_1,
   NULL,      -- check_box_2,
   NULL,       -- hidden_comment_text,
   OH.Hearing_Staff_ID,       -- in_charge_staff_id,
   NULL,       -- off_prgref_id,
   NULL,       -- contact_person_name,
   NULL,       -- to_address_owner_class,
   NULL,       -- to_address_id,
   NULL,       -- to_corporate_id,
   NULL,       -- unpaid_work_supervisor,
   NULL,       -- ta_id,
   'OIC_HEARING'
  ,Tag_Schedule_check_sum(coalesce(OH.MODIFY_DATETIME, OH.CREATE_DATETIME)) check_sum
 ,NULL
 ,NULL
FROM offender_ind_sch_wait_lists wl, agency_incident_parties aip, oic_hearings oh
LEFT OUTER JOIN agency_internal_locations ail ON (OH.Internal_Location_ID = AIL.Internal_location_id)
WHERE OH.OIC_Incident_ID = AIP.OIC_Incident_ID AND (OH.Hearing_date IS NOT NULL AND OH.Hearing_date::text <> '')  AND (AIP.Offender_Book_ID IS NOT NULL AND AIP.Offender_Book_ID::text <> '') AND OH.event_id = wl.event_id
 
UNION ALL

SELECT
   ORD.event_id,
   ORD.offender_book_id,
   BKG.agy_loc_id,
   ORD.Release_date,  -- EVENT_DATE
   NULL,              -- start_time,
   NULL,              -- end_time,
   'EXT_MOV',         -- event_class,
   ORD.Movement_Type,  -- event_type,
   ORD.MOvement_Reason_Code, -- event_sub_type,
   ORD.event_status,
   NULL,     -- event_outcome,
   NULL,     -- confirm_flag,
   NULL,     -- outcome_reason_code,
   ORD.comment_text,
   NULL,     -- reference_id,
   NULL,      -- application_date,
   NULL,     -- application_time,
   NULL,     -- return_date,
   NULL,     -- return_time,
   NULL,     -- to_agy_loc_id,
   NULL,     -- escort_code,
   NULL,     -- direction_code,
   NULL,     -- schedule_movement_time,
   NULL,     -- to_internal_location_id,
   NULL,     -- from_city_code,
   NULL,     -- to_city_code,
   NULL,     -- credited_hours,
   NULL,     -- piece_work,
   NULL,     -- engagement_code,
   NULL,     -- understanding_code,
   NULL,     -- details,
   NULL,     -- unpaid_work_behaviour,
   NULL,     -- unpaid_work_action,
   NULL,     -- sick_note_received_date,
   NULL,     -- sick_note_expiry_date,
   NULL,     -- unexcused_absence_flag,
   NULL,     -- in_time,
   NULL,     -- out_time,
   NULL,     -- transport_code,
   NULL,     -- performance_code,
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
   NULL,     -- agreed_travel_hour,
   NULL,     -- check_box_1,
   NULL,     -- check_box_2,
   NULL,     -- hidden_comment_text,
   NULL,     -- in_charge_staff_id,
   NULL,     -- off_prgref_id,
   NULL,     -- contact_person_name,
   NULL,     -- to_address_owner_class,
   NULL,     -- to_address_id,
   NULL,     -- to_corporate_id,
   NULL,     -- unpaid_work_supervisor,
   NULL,     -- ta_id,
   'OFF_REL'   -- record_source,
   ,Tag_Schedule_check_sum(coalesce(ORD.MODIFY_DATETIME, ORD.CREATE_DATETIME)) check_sum
   ,NULL
   ,NULL
FROM  OFFENDER_RELEASE_DETAILS ORD, OFFENDER_BOOKINGS BKG,OFFENDER_IND_SCH_WAIT_LISTS WL
WHERE ORD.OFFENDER_BOOK_ID = BKG.OFFENDER_BOOK_ID
AND   ORD.event_id = wl.event_id

;
create or replace
view oms_owner.v_name_search2
as
select
	off_name.offender_id,
	off_name.alias_offender_id,
	off_name.offender_id_display,
	off_name.last_name,
	off_name.first_name,
	off_name.middle_name,
	off_name.suffix,
	off_name.birth_date,
	off_bkg.offender_book_id,
	off_bkg.booking_no,
	off_bkg.booking_begin_date,
	off_bkg.booking_end_date,
	off_bkg.agy_loc_id,
	off_bkg.agency_iml_id,
	off_bkg.living_unit_id,
	off_bkg.disclosure_flag,
	off_bkg.active_flag,
	off_bkg.booking_status,
	case
		when cl.caseload_type::text = 'COMM'::text then concat((((
            case
                when off_bkg.community_active_flag::text = 'Y'::text then off_bkg.community_agy_loc_id::text || ' - '::text
                else null::text
            end ,
            case
                when coalesce(staff.last_name::text, ''::text) = ''::text then null::text
                else staff.last_name::text || ', '::text
            end),
            case
                when coalesce(staff.first_name::text, ''::text) = ''::text then null::text
                else staff.first_name::text || ' ; '::text
            end),
            case
                when coalesce(off_bkg.comm_staff_role::text, ''::text) = ''::text then null::text
                else off_bkg.comm_staff_role::text || ' : '::text
            end), off_bkg.agy_loc_id::text)
		else concat(
		case
			when coalesce(liv_unit.description::text, ''::text) = ''::text then null::text
			else liv_unit.description::text || ';'::text
		end ,
		case
			when coalesce(agency_iml.internal_location_code::text, ''::text) = ''::text then null::text
			else agency_iml.internal_location_code::text || ' : '::text
		end , off_bkg.community_agy_loc_id::text)
	end as living_unit_description,
	off_bkg.in_out_status,
	case
		when cl.caseload_type::text = 'COMM'::text then
		concat(case
			when off_bkg.community_active_flag::text = 'Y'::text then 'Active-'::text
			when off_bkg.community_active_flag::text = 'N'::text then 'Inactive-'::text
			else null::text
		end, off_bkg.comm_status::text)
		else
		concat(case
			when off_bkg.active_flag::text = 'Y'::text then 'Active-'::text
			when off_bkg.active_flag::text = 'N'::text then 'Inactive-'::text
			else null::text
		end, off_bkg.in_out_status::text)
	end as status_display,
	off_name.root_offender_id,
	staff.staff_id as assigned_staff_id,
	agy_loc.agency_location_type as agy_loc_type,
	off_bkg.create_agy_loc_id,
	off_bkg.booking_type,
	off_bkg.booking_created_date,
	agency_iml.internal_location_code as location_code,
	staff.first_name as staff_first_name,
	staff.last_name as staff_last_name,
	liv_unit.description as liv_unit_desc,
	off_bkg.intake_agy_loc_id,
	off_bkg.community_active_flag,
	off_bkg.create_intake_agy_loc_id,
	off_bkg.comm_status as community_status,
	agy_loc.abbreviation as agy_loc_name,
	off_bkg.community_agy_loc_id
from
	staff_members stf,
	offenders off_name,
	caseloads cl,
	offender_bookings off_bkg
left join living_units liv_unit on
	off_bkg.living_unit_id = liv_unit.living_unit_id
left join staff_members staff on
	off_bkg.comm_staff_id = staff.staff_id
left join agency_locations agy_loc on
	off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text
left join agency_internal_locations agency_iml on
	off_bkg.agency_iml_id = agency_iml.internal_location_id
where
	off_name.offender_id = off_bkg.offender_id
	and stf.user_id::text = upper(user::text)
	and cl.caseload_id::text = stf.working_caseload_id::text
	and (off_bkg.offender_book_id = ((
	select
		max(ob1.offender_book_id) as max
	from
		offender_bookings ob1
	where
		ob1.root_offender_id = off_bkg.root_offender_id))
	and not (exists (
	select
		null::text
	from
		offender_bookings off_bkg1
	where
		off_bkg1.root_offender_id = off_bkg.root_offender_id
		and (coalesce(off_bkg1.active_flag, 'N'::character varying)::text = 'Y'::text
			or coalesce(off_bkg1.community_active_flag, 'N'::character varying)::text = 'Y'::text)))
		or coalesce(off_bkg.active_flag, 'N'::character varying)::text = 'Y'::text
			or coalesce(off_bkg.community_active_flag, 'N'::character varying)::text = 'Y'::text
				and not (exists (
				select
					null::text
				from
					offender_bookings off_bkg1
				where
					off_bkg1.root_offender_id = off_bkg.root_offender_id
					and coalesce(off_bkg1.active_flag, 'N'::character varying)::text = 'Y'::text)));

CREATE OR REPLACE VIEW v_int_loc_rolls (agy_loc_id, internal_location_id, in_locations) AS select
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
AGY_LOC_ID
,ROOT_INTERNAL_LOCATION_ID
,sum(IN_LOCATIONS)
FROM  v_int_loc_rolls_2
group by agy_loc_id, root_internal_location_id

 
;

CREATE OR REPLACE VIEW v_tag_header_block (offender_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, booking_no, booking_begin_date, booking_end_date, agency, agy_loc_id, agency_iml_id, living_unit_id, disclosure_flag, active_flag, booking_status, living_unit_description, in_out_status, status_display, root_offender_id, assigned_staff_id, create_agy_loc_id, booking_type, booking_created_date, location_code, intake_agy_loc_id, community_active_flag, create_intake_agy_loc_id, status_reason, header_status) AS SELECT /* =========================================================
             Version Number = 10.2.1.0  Date Modified = 02-APR-2007
             =========================================================
-- MODIFICATION HISTORY
-- Person      Date                    Version       Comments
-- ---------         ------------------      ------------      ------------------------------
--  NIKO         02-APR-2007     10.2.1.0         Replaced table agency_internal_mvmt_locations with agency_internal_locations
--
========================================================= */
          off_name.offender_id, off_name.alias_offender_id,
          off_name.offender_id_display, off_name.last_name, off_name.first_name,
          off_name.middle_name, off_name.suffix, off_name.birth_date,
          off_bkg.offender_book_id, off_bkg.booking_no,
          off_bkg.booking_begin_date, off_bkg.booking_end_date,
          off_bkg.agy_loc_id, off_bkg.agy_loc_id, off_bkg.agency_iml_id,
          off_bkg.living_unit_id, off_bkg.disclosure_flag, off_bkg.active_flag,
          off_bkg.booking_status,
          CASE WHEN              cl.caseload_type='COMM' THEN                 CASE WHEN                    off_bkg.community_active_flag='Y' THEN                    off_bkg.community_agy_loc_id                 END              || ' - '             || staff.last_name             || ', '             || staff.first_name             || ' ; '             || off_bkg.comm_staff_role             || ' : '             || off_bkg.agy_loc_id  ELSE liv_unit.description             || ';'             || agency_iml.INTERNAL_LOCATION_CODE             || ' : '             || off_bkg.community_agy_loc_id END ,
          off_bkg.in_out_status,
          CASE WHEN              cl.caseload_type='COMM' THEN              CASE WHEN                 off_bkg.community_active_flag='Y' THEN     Oms_system_profile('CLIENT', 'B/C_STATUS', 1)                     || CASE WHEN                            Oms_system_profile('C_KER', 'ACT_SUP_STS', 1)='Y' THEN     '-'                                || (SELECT status_code                                      FROM OFFENDER_COMM_SUP_HISTORIES                                     WHERE offender_book_id = off_bkg.offender_book_id                                       AND status_seq = (SELECT MAX( status_seq )                                                           FROM OFFENDER_COMM_SUP_HISTORIES                                                          WHERE offender_book_id = off_bkg.offender_book_id                                                            AND effective_date <= LOCALTIMESTAMP)) WHEN                            Oms_system_profile('C_KER', 'ACT_SUP_STS', 1)='N' THEN  '-' || off_bkg.comm_status  ELSE '-' || off_bkg.comm_status END  WHEN                 off_bkg.community_active_flag='N' THEN  Oms_system_profile('CLIENT', 'B/C_STATUS', 2)  ELSE NULL END   ELSE CASE WHEN                    off_bkg.active_flag='Y' THEN  'ACTIVE-' WHEN                    off_bkg.active_flag='N' THEN  'INACTIVE-'  ELSE NULL END              || CASE WHEN                    off_bkg.in_out_status='OUT' THEN                    CASE WHEN                       mov_rsn.header_status_flag='Y' THEN                       SUBSTR(                         off_bkg.status_reason,                         position('-' in off_bkg.status_reason) + 1                      )  ELSE off_bkg.in_out_status END   ELSE off_bkg.in_out_status END  END ,
          off_name.root_offender_id, staff.staff_id, off_bkg.create_agy_loc_id,
          off_bkg.booking_type, off_bkg.booking_created_date,
          agency_iml.INTERNAL_LOCATION_CODE, off_bkg.intake_agy_loc_id,
          off_bkg.community_active_flag, off_bkg.create_intake_agy_loc_id,
          off_bkg.status_reason, mov_rsn.header_status_flag
     FROM staff_members stf, offenders off_name, caseloads cl, offender_bookings off_bkg
LEFT OUTER JOIN living_units liv_unit ON (off_bkg.living_unit_id = liv_unit.living_unit_id)
LEFT OUTER JOIN staff_members staff ON (off_bkg.comm_staff_id = staff.staff_id)
LEFT OUTER JOIN agency_internal_locations agency_iml ON (off_bkg.agency_iml_id = agency_iml.INTERNAL_LOCATION_ID)
LEFT OUTER JOIN agency_locations agy_loc ON (off_bkg.agy_loc_id = agy_loc.agy_loc_id AND off_bkg.intake_agy_loc_id = agy_loc.agy_loc_id)
LEFT OUTER JOIN movement_reasons mov_rsn ON (SUBSTR(  off_bkg.status_reason, 1, position('-' in off_bkg.status_reason) - 1 ) = mov_rsn.movement_type AND SUBSTR( off_bkg.status_reason, position('-' in off_bkg.status_reason) + 1 ) = mov_rsn.movement_reason_code)
WHERE off_name.offender_id = off_bkg.offender_id   AND stf.user_id = USER AND cl.caseload_id = stf.working_caseload_id;

CREATE OR REPLACE VIEW v_offender_bookings (offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, booking_no, booking_begin_date, booking_end_date, agy_loc_id, agency_iml_id, agency_iml_desc, agency_iml_level_1_code, agency_iml_level_2_code, agency_iml_level_3_code, living_unit_id, living_unit_desc, living_unit_level_1_code, living_unit_level_2_code, living_unit_level_3_code, living_unit_level_4_code, active_flag, booking_status, in_out_status) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
       off.offender_id,
       off.offender_id_display,
       off.last_name,
       off.first_name,
       off.middle_name,
       off.suffix,
       off.birth_date,
       bkg.offender_book_id,
       bkg.booking_no,
       bkg.booking_begin_date,
       bkg.booking_end_date,
       bkg.agy_loc_id,
       bkg.agency_iml_id,
       ail.description,
CASE WHEN instr(ail.description, '-',1,1)=0 THEN NULL  ELSE substr(ail.description, instr(ail.description, '-',1,1)+1,               CASE WHEN instr(ail.description, '-',1,2)=0 THEN length(ail.description)  ELSE instr(ail.description, '-',1,2)-instr(ail.description, '-',1,1)-1 END ) END  ,
CASE WHEN instr(ail.description, '-',1,2)=0 THEN NULL  ELSE substr(ail.description, instr(ail.description, '-',1,2)+1,               CASE WHEN instr(ail.description, '-',1,3)=0 THEN length(ail.description)  ELSE instr(ail.description, '-',1,3)-instr(ail.description, '-',1,2)-1 END ) END  ,
CASE WHEN instr(ail.description, '-',1,3)=0 THEN NULL  ELSE substr(ail.description, instr(ail.description, '-',1,3)+1,               CASE WHEN instr(ail.description, '-',1,4)=0 THEN length(ail.description)  ELSE instr(ail.description, '-',1,4)-instr(ail.description, '-',1,3)-1 END ) END  ,
       bkg.living_unit_id,
       lu.description,
       lu.level_1_code,
       lu.level_2_code,
       lu.Level_3_code,
       lu.Level_4_code,
       bkg.active_flag,
       bkg.booking_status,
       bkg.in_out_status
  FROM offenders off, offender_bookings bkg
LEFT OUTER JOIN living_units lu ON (bkg.living_unit_id = lu.living_unit_id)
LEFT OUTER JOIN agency_internal_locations ail ON (bkg.agency_iml_ID = ail.Internal_location_ID)
WHERE off.offender_id = bkg.offender_id;

CREATE OR REPLACE VIEW v_curfew_addresses (curfew_address_id, offender_book_id, offender_curfew_id, address_id, active_flag, electricity_flag, phone_flag, phone_no, ext_no, house, street, area, zip_postal_code, country, electricity_confirm_flag, phone_confirm_flag, phone_no_confirm_flag, primary_flag, mail_flag, address_active_flag) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date                          Version     	 Comments
   ----------------------------------------------------------------------------------------------------------------------------------------------
   NIKO              26-JAN-2007         2.0.1.0      Replace the columns in V_STF_ADDR block:
                                                                                SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     03/01/2006 2.0                         NOMIS project(10.2.0)
*/
          cur_addr.curfew_address_id,
          cur_addr.offender_book_id,
          cur_addr.offender_curfew_id,
          cur_addr.address_id,
          cur_addr.active_flag,
          cur_addr.electricity_flag,
          cur_addr.phone_flag,
          cur_addr.phone_no,
          cur_addr.ext_no,
          addr.house,
          addr.street,
          addr.area,
          addr.zip_postal_code,
          addr.country,
          cur_addr.electricity_confirm_flag,
          cur_addr.phone_confirm_flag,
          cur_addr.phone_no_confirm_flag,
	  addr.primary_flag,
          addr.mail_flag,
	  addr.active_flag
     FROM CURFEW_ADDRESSES cur_addr,
          v_addresses addr
    WHERE cur_addr.address_id = addr.address_id

 
;

CREATE OR REPLACE VIEW living_unit_profiles (living_unit_id, profile_id, agy_loc_id, description, int_loc_profile_type, int_loc_profile_code) AS select
/* =========================================================
   Version Number = 10.2.1  Date Modified = 22/07/2009
   ========================================================= */
/* MODIFICATION HISTORY
   Person         Date          Version      Comments
   ---------    ----------  ---------   ------------------------------
   David Ng     22/07/2009  2.1        Profile values inherited from parents By profile type.
   David Ng     19/07/2005  2.0        NOMIS project Profile values inherited from parents
*/
lu.living_unit_id
,tag_int_loc_location_profile_id(living_unit_id, rd.domain)
,lu.agy_loc_id
,lu.description
,p.INT_LOC_PROFILE_TYPE
,p.INT_LOC_PROFILE_CODE
FROM   living_units lu, agy_int_loc_profiles p,
       reference_domains rd
WHERE  p.INTERNAL_LOCATION_ID = tag_int_loc_location_profile_id(living_unit_id, rd.domain)
AND    p.int_loc_profile_type = rd.domain
AND    rd.parent_domain = 'INT_LOC_PFL'

;

COMMENT ON VIEW living_unit_profiles  IS E'The profiles of the living units';

COMMENT ON COLUMN living_unit_profiles.agy_loc_id IS E'The ID of the agency location. ';
COMMENT ON COLUMN living_unit_profiles.description IS E'The description text. ';
COMMENT ON COLUMN living_unit_profiles.living_unit_id IS E'The ID of the living unit record. ';
CREATE OR REPLACE VIEW v_person_addresses (address_id, address_type, address_type_desc, person_id, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_direction, street_information, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, validated_flag) AS SELECT
/* =========================================================
   Version Number = 1.6.1.1  Date Modified = 23/Feb/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   VIKAS   		23-FEB-2007  	1.6.1.1	  New Columns: Street_information, City_Name, Prov_State_Desc
   NIKO         26-JAN-2007     1.6.1.0      Replace the columns in V_STF_ADDR block:
                                                                                SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     06/21/2005              1.5            NOMIS new addresses table
   David Ng     06/23/2005              1.6            Add Address_type_desc
*/
ADDRESS_ID
,ADDRESS_TYPE
,ADDRESS_TYPE_DESC
,OWNER_ID        PERSON_ID
,start_date
,end_date
,ACTIVE_FLAG
,HOUSE
,STREET
,AREA
,COUNTRY
,SUITE_NUMBER
,STREET_NUMBER
,STREET_DIRECTION
,STREET_INFORMATION
,CITY_CODE
,CITY_NAME
,PROV_STATE_CODE
,PROV_STATE_DESC
,ZIP_POSTAL_CODE
,COUNTRY_CODE
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,VALIDATED_FLAG
FROM v_ADDRESSES
WHERE OWNER_CLASS = 'PER'

 
;

CREATE OR REPLACE VIEW v_off_observation_periods_res (row_id, check_id, offender_id_display, root_offender_id, offender_id, offender_book_id, last_name, first_name, birth_date, agy_loc_id, living_unit_description, liv_unit_desc, active_flag, living_unit_id, obs_period_id, observation_type, observation_type_desc, frequency, schedule_date) AS SELECT
   /* =========================================================
   Version Number = 11.1.1.1 Date Modified = 20-Oct-2019
   =========================================================
   MODIFICATION HISTORY
   Person      Date            Version       Comments
   ---------   --------------- ------------  ------------------------------
   Arun        20-Oct-2019     11.1.1.1       Query Modified 
   Arun        10-Oct-2019     11.1.1.0      Initial Version
*/
   oop.ctid,opc.check_id,
          vhb.OFFENDER_ID_DISPLAY,
          vhb.ROOT_OFFENDER_ID,
          vhb.OFFENDER_ID,
          vhb.OFFENDER_BOOK_ID,
          vhb.LAST_NAME,
          vhb.FIRST_NAME,
          vhb.BIRTH_DATE,
          vhb.AGY_LOC_ID,
          SUBSTR(vhb.LIVING_UNIT_DESCRIPTION,
                  1,
                  position(';' in vhb.LIVING_UNIT_DESCRIPTION) - 1)
             LIVING_UNIT_DESCRIPTION,
          LIV_UNIT_DESC,
          vhb.ACTIVE_FLAG,
          vhb.LIVING_UNIT_ID,
          oop.OBS_PERIOD_id,
          oop.OBSERVATION_TYPE,
          OMS_MISCELLANEOUS_GETDESCCODE('OO_OBS_TYP', oop.OBSERVATION_TYPE)
             OBS_TYPE_DESC,
          oop.FREQUENCY,
          opc.schedule_date
     FROM v_header_block vhb,
          off_observation_periods oop,
          OFF_OBS_PERIOD_CHECKS opc
    WHERE     vhb.OFFENDER_BOOK_ID = oop.OFFENDER_BOOK_ID
          AND vhb.active_flag = 'Y'
          AND oop.offender_book_id = opc.offender_book_id
          AND oop.OBS_PERIOD_id = opc.OBS_PERIOD_id
          AND oop.OBS_PERIOD_id =
                 (SELECT MAX(oop1.OBS_PERIOD_id)
                    FROM off_observation_periods oop1
                   WHERE oop1.OFFENDER_BOOK_ID = oop.offender_book_id
                   AND oop1.STATUS_CODE = 'A')
          AND opc.check_id =
                 (SELECT MAX(OBCH.check_id)
                    FROM OFF_OBS_PERIOD_CHECKS OBCH
                   WHERE OBCH.offender_book_id = oop.offender_book_id
                         AND OBCH.OBS_PERIOD_id = oop.OBS_PERIOD_id);

CREATE OR REPLACE VIEW v_stg_location_members (stg_id, agy_loc_id, living_unit_id, description, list_seq, offender_id, offender_book_id, offender_id_display, last_name, first_name) AS SELECT
      osa.stg_id
     ,lu.agy_loc_id
     ,lu.living_unit_id
     ,lu.description
     ,lu.list_seq
     ,ob.offender_id
     ,ob.offender_book_id
     ,o.offender_id_display
     ,o.last_name
     ,o.first_name
FROM offender_stg_affiliations osa
          ,offender_bookings ob
          ,offenders o
          ,living_units lu
WHERE ob.offender_book_id = osa.offender_book_id
AND o.offender_id = ob.offender_id
AND ob.living_unit_id = lu.living_unit_id
AND osa.active_flag = 'Y'

 
;

CREATE OR REPLACE VIEW v_person_search (person_id, last_name, first_name, middle_name, birthdate, phone_area, phone, ext_no, ssn) AS SELECT
/* =========================================================
   Version Number = 1.3  Date Modified = 06/21/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version    Comments
   ---------    ------     ---------  -----------------------------
   David Ng     06/21/2005  1.3        NOMIS new phones table
                                      Performnace - remove rule base
                                                  - break down phone fields
*/
 P.PERSON_ID,
 P.LAST_NAME,
 P.FIRST_NAME,
 P.MIDDLE_NAME,
 P.BIRTHDATE,
 PT.PHONE_AREA,
 PT.PHONE_no,
 PT.EXT_NO,
 I.IDENTIFIER
 FROM persons p
LEFT OUTER JOIN person_identifiers i ON (P.PERSON_ID = I.PERSON_ID AND 'SSN' = I.IDENTIFIER_TYPE)
LEFT OUTER JOIN v_phones pt ON (P.PERSON_ID = PT.OWNER_ID AND 'PER' = PT.OWNER_CLASS)
WHERE coalesce(P.ALIAS_PERSON_ID::text, '') = ''

 
;

CREATE OR REPLACE VIEW v_offender_employ_addresses (address_id, address_type, address_type_desc, offender_book_id, employment_seq, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_direction, city_code, prov_state_code, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, validated_flag, street_information, city_name, prov_state_desc) AS SELECT
/* =========================================================
   Version Number = 1.4.1.1   Date Modified = 26/02/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	       Date                     Version     	 Comments
   ---------            ------                     ---------  	 ------------------------------
   Sarah             26-Feb-2007         1.4.1.1      Added the following columns:
   					 					 			  		          STREET_INFORMATION ,CITY_NAME ,PROV_STATE_DESC
   NIKO              26-JAN-2007         1.4.1.0      Replace the columns in V_STF_ADDR block:
                                                                                SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     06/21/2005               1.3            NOMIS new addresses table
   David Ng     06/23/2005               1.4            add address_type_desc
*/
ADDRESS_ID
,ADDRESS_TYPE
,ADDRESS_TYPE_DESC
,OWNER_ID        Offender_book_ID
,OWNER_SEQ       employment_seq
,start_date
,end_date
,ACTIVE_FLAG
,HOUSE
,STREET
,AREA,COUNTRY,suite_number,street_number,street_direction
,CITY_CODE
,PROV_STATE_CODE
,ZIP_POSTAL_CODE
,COUNTRY_CODE
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,VALIDATED_FLAG
,STREET_INFORMATION
,CITY_NAME
,PROV_STATE_DESC
FROM v_ADDRESSES
WHERE OWNER_CLASS = 'OFF_EMP'

 
;

CREATE OR REPLACE VIEW v_header_block2 (offender_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, booking_no, booking_begin_date, booking_end_date, agy_loc_id, agency_iml_id, living_unit_id, disclosure_flag, active_flag, booking_status, living_unit_description, in_out_status, status_display, root_offender_id, assigned_staff_id, agy_loc_type, create_agy_loc_id, booking_type, booking_created_date, location_code, staff_first_name, staff_last_name, liv_unit_desc, intake_agy_loc_id, community_active_flag, create_intake_agy_loc_id, community_status, status_reason, header_status) AS select
	/* =========================================================
             Version Number = 10.2.1.0  Date Modified = 02-APR-2007
             =========================================================
-- MODIFICATION HISTORY
-- Person      Date                    Version       Comments
-- ---------         ------------------      ------------      ------------------------------
--  NIKO         02-APR-2007     10.2.1.0         Replaced table agency_internal_mvmt_locations with agency_internal_locations
--
========================================================= */
off_name.offender_id,
	off_name.alias_offender_id,
	off_name.offender_id_display,
	off_name.last_name,
	off_name.first_name,
	off_name.middle_name,
	off_name.suffix,
	off_name.birth_date,
	coalesce(off_bkg.offender_book_id, 0),
	off_bkg.booking_no,
	off_bkg.booking_begin_date,
	off_bkg.booking_end_date,
	off_bkg.agy_loc_id,
	off_bkg.agency_iml_id,
	off_bkg.living_unit_id,
	off_bkg.disclosure_flag,
	off_bkg.active_flag,
	off_bkg.booking_status,
	case
		when cl.caseload_type = 'COMM' then
		case
			when off_bkg.community_active_flag = 'Y' then off_bkg.community_agy_loc_id
		end || ' - ' || staff.last_name || ', ' || staff.first_name || ' ; ' || off_bkg.comm_staff_role || ' : ' || off_bkg.agy_loc_id
		else concat(liv_unit.description, ';', agency_iml.INTERNAL_LOCATION_CODE,' : ',off_bkg.community_agy_loc_id)
	end ,
	off_bkg.in_out_status,
	case
		when cl.caseload_type = 'COMM' then
		case
			when off_bkg.community_active_flag = 'Y' then Oms_system_profile('CLIENT',
			'B/C_STATUS',
			1) ||
			case
				when Oms_system_profile('C_KER',
				'ACT_SUP_STS',
				1)= 'Y' then '-' || (
				select
					status_code
				from
					OFFENDER_COMM_SUP_HISTORIES
				where
					offender_book_id = off_bkg.offender_book_id
					and status_seq = (
					select
						MAX(status_seq)
					from
						OFFENDER_COMM_SUP_HISTORIES
					where
						offender_book_id = off_bkg.offender_book_id
						and effective_date <= localtimestamp))
				when Oms_system_profile('C_KER',
				'ACT_SUP_STS',
				1)= 'N' then '-' || off_bkg.comm_status
				else '-' || off_bkg.comm_status
			end
			when off_bkg.community_active_flag = 'N' then Oms_system_profile('CLIENT',
			'B/C_STATUS',
			2)
			else null
		end
		else
		case
			when off_bkg.active_flag = 'Y' then 'ACTIVE-'
			when off_bkg.active_flag = 'N' then 'INACTIVE-'
			else null
		end ||
		case
			when off_bkg.in_out_status = 'OUT' then
			case
				when mov_rsn.header_status_flag = 'Y' then SUBSTR( off_bkg.status_reason,
				position('-' in off_bkg.status_reason) + 1 )
				else off_bkg.in_out_status
			end
			else off_bkg.in_out_status
		end
	end ,
	off_name.root_offender_id,
	staff.staff_id,
	agy_loc.agency_location_type,
	off_bkg.create_agy_loc_id,
	off_bkg.booking_type,
	off_bkg.booking_created_date,
	agency_iml.INTERNAL_LOCATION_CODE,
	staff.first_name,
	staff.last_name,
	liv_unit.description,
	off_bkg.intake_agy_loc_id,
	off_bkg.community_active_flag,
	off_bkg.create_intake_agy_loc_id,
	off_bkg.comm_status,
	off_bkg.status_reason,
	mov_rsn.header_status_flag
from
	staff_members stf,
	caseloads cl,
	offenders off_name
left outer join offender_bookings off_bkg on
	(off_name.offender_id = off_bkg.offender_id)
left outer join living_units liv_unit on
	(off_bkg.living_unit_id = liv_unit.living_unit_id)
left outer join staff_members staff on
	(off_bkg.comm_staff_id = staff.staff_id)
left outer join agency_internal_locations agency_iml on
	(off_bkg.agency_iml_id = agency_iml.INTERNAL_LOCATION_ID)
left outer join agency_locations agy_loc on
	(off_bkg.agy_loc_id = agy_loc.agy_loc_id
	and off_bkg.intake_agy_loc_id = agy_loc.agy_loc_id)
left outer join movement_reasons mov_rsn on
	(SUBSTR ( off_bkg.status_reason, 1, INSTR (off_bkg.status_reason, '-', 1) - 1 ) = mov_rsn.movement_type
	and SUBSTR ( off_bkg.status_reason, INSTR (off_bkg.status_reason, '-', 1) + 1 ) = mov_rsn.movement_reason_code)
where
	stf.user_id = upper(user)
	and cl.caseload_id = stf.working_caseload_id;

CREATE OR REPLACE VIEW v_payroll_header (offender_book_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, booking_no, booking_begin_date, booking_end_date, agy_loc_id, living_unit_id, disclosure_flag, active_flag, booking_status, living_unit_description, in_out_status, status_display, root_offender_id, assigned_staff_id, agy_loc_type, payroll_caseload_id, caseload_id, trust_caseload_id, offender_id, booking_type, intake_agy_loc_id, community_active_flag, create_intake_agy_loc_id, community_status, age, gender, header_status, off_alerts, officer, prison_location, status_1, status_2, status_3, status_reason, movement_reason, off_sup_level) as SELECT
       /* ========================================================
             Version Number = 1.4 Date Modified = 02-APR-2007
             ======================================================== */
       /* Modification History
	   MODIFICATION HISTORY
	   Person      Date                    Version            Comments
	   -----------     ----------                -----------   -------------------------------
	   Niko           02-APR-2007    1.4          Replaced table agency_internal_mvmt_locations with agency_internal_locations
	                                                                    Modified the select statements
	   Sarah         20-Feb-2007     1.3        Modified V_Payroll_Header view for 10gR2.
	   Girish         12-AUG-2004    1.2       The status_display field displays the same as
                                                                       the standard header block ( V_HEADER_BLOCK ).
                                                                        booking_type field displays the correct value
       */
       off_bkg.offender_book_id,
       off_name.alias_offender_id,
       off_name.offender_id_display,
       off_name.last_name,
       off_name.first_name,
       off_name.middle_name,
       off_name.suffix,
       off_name.birth_date,
       off_bkg.booking_no,
       off_bkg.booking_begin_date,
       off_bkg.booking_end_date,
       off_bkg.agy_loc_id,
       off_bkg.living_unit_id,
       off_bkg.disclosure_flag,
       off_bkg.active_flag,
       off_bkg.booking_status,
       CASE WHEN           csld.caseload_type='COMM' THEN  CASE WHEN off_bkg.community_active_flag='Y' THEN  off_bkg.community_agy_loc_id END  || ' - ' || staff.last_name || ', ' || staff.first_name || ' ; ' || off_bkg.comm_staff_role || ' : ' || off_bkg.agy_loc_id  ELSE liv_unit.description || ';' || agency_iml.internal_location_code || ' : ' || off_bkg.community_agy_loc_id END   LIVING_UNIT_DESCRIPTION,
       off_bkg.in_out_status,
       CASE WHEN              csld.caseload_type='COMM' THEN              CASE WHEN                 off_bkg.community_active_flag='Y' THEN     Oms_system_profile('CLIENT', 'B/C_STATUS', 1)                     || CASE WHEN                            Oms_system_profile('C_KER', 'ACT_SUP_STS', 1)='Y' THEN     '-'                                || (SELECT status_code                                      FROM OFFENDER_COMM_SUP_HISTORIES                                     WHERE offender_book_id = off_bkg.offender_book_id                                       AND status_seq = (SELECT MAX( status_seq )                                                           FROM OFFENDER_COMM_SUP_HISTORIES                                                          WHERE offender_book_id = off_bkg.offender_book_id                                                            AND effective_date <= LOCALTIMESTAMP)) WHEN                            Oms_system_profile('C_KER', 'ACT_SUP_STS', 1)='N' THEN  '-' || off_bkg.comm_status  ELSE '-' || off_bkg.comm_status END  WHEN                 off_bkg.community_active_flag='N' THEN  Oms_system_profile('CLIENT', 'B/C_STATUS', 2)  ELSE NULL END   ELSE CASE WHEN off_bkg.active_flag='Y' THEN  'ACTIVE-' WHEN off_bkg.active_flag='N' THEN   'INACTIVE-'  ELSE NULL END  || CASE WHEN off_bkg.in_out_status='OUT' THEN  CASE WHEN  mov_rsn.header_status_flag='Y' THEN                       SUBSTR(off_bkg.status_reason,position('-' in off_bkg.status_reason) + 1 )  ELSE off_bkg.in_out_status END   ELSE off_bkg.in_out_status END  END ,
       off_bkg.root_offender_id,
       off_bkg.assigned_staff_id,
       agy_loc.agency_location_type agy_loc_type,
       csld.caseload_id payroll_caseload_id,
       csld.caseload_id,
       coalesce(csld.payroll_trust_caseload, CSLD.CASELOAD_ID) trust_caseload_id,
       off_bkg.offender_id,
       off_bkg.booking_type,
       off_bkg.intake_agy_loc_id,
       off_bkg.community_active_flag,
       off_bkg.create_intake_agy_loc_id,
       off_bkg.comm_status community_status,
	   Tag_Header_get_age(off_name.birth_date) age,
	   SUBSTR(Oms_Miscellaneous_getdesccode('SEX', off_name.sex_code),0,10) gender,mov_rsn.header_status_flag header_status,
	   SUBSTR(Omkhead_get_alerts(off_bkg.offender_book_id),0,40) off_alerts,
	   SUBSTR(Tag_Header_get_officer(off_bkg.offender_book_id), 0, 105 ) officer,
       SUBSTR(Tag_Header_get_prison_location(off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id ), 0,105 ) prison_location,
       SUBSTR(Tag_Header_get_status_1(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status,off_bkg.status_reason ), 0,40) status_1,
       SUBSTR(Tag_Header_get_status_2(off_bkg.offender_book_id),0,40) status_2,
       SUBSTR(Tag_Header_get_status_3(off_bkg.offender_book_id),0,40) status_3,
	   off_bkg.status_reason,
	   (SELECT  oem.movement_reason_code
	    FROM   OFFENDER_EXTERNAL_MOVEMENTS oem
	    WHERE  oem.offender_book_id = off_bkg.offender_book_id
   	    AND oem.movement_seq = (SELECT MAX(movement_seq)
	                                                                     FROM OFFENDER_EXTERNAL_MOVEMENTS oem2
			  														      WHERE oem2.offender_book_id = oem.offender_book_id)
		AND  EXISTS ( SELECT 'X'
		  	                           FROM MOVEMENT_REASONS mr
          					       	  WHERE mr.movement_type = oem.movement_type
						              AND mr.movement_reason_code = oem.movement_reason_code
								      AND mr.header_status_flag = 'Y') ) movement_reason,
    Oms_Intake_get_offender_supervision(off_bkg.offender_book_id ) off_sup_level
  FROM offender_trust_accounts off_ta, offenders off_name, caseloads csld, offender_bookings off_bkg
LEFT OUTER JOIN staff_members staff ON (off_bkg.comm_staff_id = staff.staff_id)
LEFT OUTER JOIN agency_locations agy_loc ON (off_bkg.agy_loc_id = agy_loc.agy_loc_id)
LEFT OUTER JOIN agency_internal_locations agency_iml ON (off_bkg.agency_iml_id = agency_iml.internal_location_id)
LEFT OUTER JOIN living_units liv_unit ON (off_bkg.living_unit_id = liv_unit.living_unit_id)
LEFT OUTER JOIN movement_reasons mov_rsn ON (SUBSTR ( off_bkg.status_reason, 1, INSTR (off_bkg.status_reason, '-', 1) - 1 ) = mov_rsn.movement_type AND SUBSTR ( off_bkg.status_reason, INSTR (off_bkg.status_reason, '-', 1) + 1 ) = mov_rsn.movement_reason_code)
WHERE off_ta.caseload_id =  coalesce(csld.payroll_trust_caseload , csld.caseload_id)   AND off_ta.offender_id = off_name.root_offender_id  AND off_name.offender_id = off_bkg.offender_id    AND ( EXISTS (SELECT '1'
                   FROM OFFENDER_TRUST_ACCOUNTS OTA
                  WHERE OTA.OFFENDER_ID = off_name.root_offender_id
                    AND OTA.CASELOAD_ID = csld.caseload_id)
        OR EXISTS (SELECT '1'
                   FROM WORK_ASSIGNMENTS WA
                  WHERE WA.OFFENDER_ID = off_name.root_offender_id
                    AND WA.CASELOAD_ID = csld.caseload_id)
        OR EXISTS (SELECT '1'
                                  FROM CASELOAD_AGENCY_LOCATIONS CAL
                                 WHERE CAL.CASELOAD_ID = CSLD.CASELOAD_ID
                                  AND EXISTS (SELECT '1'
                                                               FROM OFFENDER_BOOKINGS OB
                                                               WHERE OB.agy_loc_id = cal.agy_loc_id
                                                               AND OB.ROOT_OFFENDER_ID = off_name.root_offender_id
                                                               AND OB.AGY_LOC_ID NOT IN ('TRN', 'OUT')
															   )
					                )
			)

 
;

CREATE OR REPLACE VIEW v_program_placements (corporate_id, corporate_name, address_id, address_type, full_address, house, street, area, country, suite_number, street_number, street_direction, city_code, city_name, prov_state_code, zip_postal_code, capacity, comment_text, primary_flag, mail_flag, services_flag) AS SELECT
/* =========================================================
   Version Number = 2.0.1.0  Date Modified = 26/01/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   -------------------------------------------------------------------------------------------------------------------------
   NIKO              26-JAN-2007         2.0.1.0      Replace the columns in V_STF_ADDR block:
                                                                                SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     15/10/2005               2.0           NOMIS project(10.2.0)
*/
          c.corporate_id, c.corporate_name, va.address_id, va.address_type,
          va.full_address, va.house, va.street, va.area, va.country, va.suite_number,
          va.street_number, va.street_direction, va.city_code, va.city_name,
          va.prov_state_code, va.zip_postal_code, va.capacity, va.comment_text,
          va.primary_flag, va.mail_flag, va.services_flag
     FROM CORPORATES c, v_addresses va
    WHERE c.corporate_id = va.owner_id
      AND va.owner_class = 'CORP'
      AND va.services_flag = 'Y'

 
;

CREATE OR REPLACE VIEW v_gang_affiliations (offender_id, last_name, first_name, offender_id_display, offender_book_id, active_flag, living_unit_description, root_offender_id, gang, faction, comment_text, gang_code) AS SELECT vhb.offender_id
     , vhb.last_name
     , vhb.first_name
     , vhb.offender_id_display
     , vhb.offender_book_id
     , vhb.active_flag
     , vhb.living_unit_description
     , vhb.root_offender_id
     , coalesce(gang.parent_gang_code, gang.gang_code)
     , CASE WHEN coalesce(gang.parent_gang_code::text, '') = '' THEN  null  ELSE gang.gang_code END
     , off_ga.comment_text
     , gang.gang_code
 FROM v_header_block vhb
     ,offender_gang_affiliations  off_ga
     ,gangs  gang
WHERE vhb.offender_book_id = off_ga.offender_book_id
  AND off_ga.gang_code     = gang.gang_code
 
 
;

CREATE OR REPLACE VIEW v_living_unit_rolls (agy_loc_id, living_unit_id, allocated, in_living_units, out_of_living_units, out_of_agy, reserved_beds) AS select
   /* =========================================================
    Version Number = 2.1 Date Modified = 04/12/2012
    ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     15/10/2005  2.0         NOMIS project(10.2.0)
   Nasir        12/04/2012  2.1         Fix HPQC# 12426
*/
agy_loc_id
,root_living_unit_id
,sum(allocated)
,sum(in_living_units)
,sum(out_of_living_units)
,sum(out_of_agy)
--,Sum(reserved_beds)  Commented by Nasir to fix HPQC# 12426
,max(reserved_beds)
FROM  v_living_unit_rolls_2
group by agy_loc_id, root_living_unit_id

;

CREATE OR REPLACE VIEW v_corporate_addresses (address_id, address_type, address_type_desc, corporate_id, address_seq, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_information, street_direction, street_direction_desc, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, services_flag, special_needs, validated_flag, contact_person_name, business_hour) AS SELECT
/* =========================================================
   Version Number = 1.7.1.2  Date Modified = 16-FEB-2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date         Version     	 Comments
   ---------    ------        ---------  	 ------------------------------
   Johnny       09-SEP-2008   1.7.1.2      address_seq added.
   VIKAS 		16-FEB-2007  1.7.1.1 	 New columns added
   NIKO              26-JAN-2007         1.7.1.0      Replace the columns in V_STF_ADDR block:
                                          			  		  	  		  	    SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     06/23/2005  			1.7          NOMIS new address table add
                                        			                          address type description
*/
ADDRESS_ID
,ADDRESS_TYPE
,ADDRESS_TYPE_DESC
,OWNER_ID        corporate_ID
,OWNER_SEQ       address_seq
,start_date
,end_date
,ACTIVE_FLAG
,HOUSE
,STREET
,AREA
,COUNTRY
,SUITE_NUMBER
,STREET_NUMBER
,STREET_INFORMATION
,STREET_DIRECTION
,STREET_DIRECTION_DESC
,CITY_CODE
,CITY_NAME
,PROV_STATE_CODE
,PROV_STATE_DESC
,ZIP_POSTAL_CODE
,COUNTRY_CODE
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,SERVICES_FLAG
,SPECIAL_NEEDS
,VALIDATED_FLAG
,CONTACT_PERSON_NAME
,BUSINESS_HOUR
FROM v_ADDRESSES
WHERE OWNER_CLASS = 'CORP'

;

CREATE OR REPLACE VIEW v_course_activities (course_class, crs_acty_id, course_activity_code, course_phase_id, program_category, program_category_desc, program_id, program_services_desc, phase_desc, course_activity_desc, environment, environment_desc, active_flag, provider_class, provider_id, provider_code, provider_name, placement_corporate_id, agency_contact, schedule_start_date, schedule_end_date, capacity, vacancy, pqs_number, comment_text, services_address_id, suite_number, street_information, city, state, postal_code, country, country_desc, agy_loc_id, agy_loc_desc, area_code, noms_region_code, internal_location_id, internal_location_desc) AS SELECT
/* =========================================================
 Version Number = 10.2.0.2 Date Modified = 07/22/2010
 ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   Rose         22/07/2010  10.2.0.2    HPQC#3257. Work Release, Using course_activities.agy_loc_id as agy_loc_id instead of provider_party_code.
   GJC          22/08/2006  2.4         Add VACANCY derivation by calling Tag_Prg.course_vacancy (ca.crs_acty_id)
   NDB          03/08/2006  2.3         Corrected call to Tag_Prg.course_vacancy
   David Ng     15/10/2005  2.0         NOMIS project(10.2.0)
   David Ng     17/01/2006  2.1         Add Course_Class
   David Ng     27/07/2006  2.2         Add Course Activity Code
*/
          ca.course_class, ca.crs_acty_id, ca.code, NULL, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, NULL, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CSLD_CODE',
                                                 ca.caseload_type),
                  1,
                  40
                 ),
          ca.active_flag, ca.provider_party_class, ca.provider_party_id, ca.provider_party_code,
          Tag_Prg_provider_name(ca.provider_party_class, ca.provider_party_ID, ca.provider_party_code),
          ca.placement_corporate_id, corp.corporate_name,
          ca.schedule_start_date, ca.schedule_end_date,
          ca.capacity, Tag_Prg_course_vacancy(ca.crs_acty_id) VACANCY
          , 0, ca.comment_text, ca.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc, ca.provider_party_code,
          al.description, al.area_code, al.noms_region_code,
          ca.internal_location_id, ail.description
     FROM program_services ps, course_activities ca
LEFT OUTER JOIN agency_internal_locations ail ON (ca.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (ca.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (ca.placement_corporate_id = corp.corporate_id)
LEFT OUTER JOIN agency_locations al ON (ca.provider_party_code = al.agy_loc_id)
WHERE ps.program_id = ca.program_id AND (ps.program_category <> 'WR' or coalesce(ps.program_category::text, '') = '')   -- @@@ Rose 22-JUL-2010 Work Release @@@ --
UNION ALL

   SELECT 'CRS_PH', cp.parent_crs_acty_id, ca.code, cp.course_phase_id, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, ph.description, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CLOAD_TYPE',
                                                 ca.caseload_type
                                                ),
                  1,
                  40
                 ),
          cp.active_flag, cp.provider_party_class, cp.provider_party_id, cp.provider_party_code,
          Tag_Prg_provider_name(cp.provider_party_class, cp.provider_party_ID, cp.provider_party_code),
          cp.placement_corporate_id, corp.corporate_name,
          cp.schedule_start_date, cp.schedule_end_date, cp.capacity,
          Tag_Prg_course_vacancy(cp.course_phase_id), 0, cp.comment_text,
          cp.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc,  ca.provider_party_code,
          al.description,
          al.area_code, al.noms_region_code, ca.internal_location_id,
          ail.description
     FROM program_services ps, v_program_phases ph, course_activities ca, v_course_phases cp
LEFT OUTER JOIN agency_internal_locations ail ON (cp.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (cp.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (cp.placement_corporate_id = corp.corporate_id)
LEFT OUTER JOIN agency_locations al ON (cp.provider_party_code = al.agy_loc_id)
WHERE cp.parent_crs_acty_id = ca.crs_acty_id AND cp.program_phase_id = ph.program_phase_id AND ps.program_id = ph.program_id AND (ps.program_category <> 'WR' or coalesce(ps.program_category::text, '') = '')   -- @@@ Rose 22-JUL-2010 Work Release @@@ --
     
UNION ALL

   SELECT
          ca.course_class, ca.crs_acty_id, ca.code, NULL, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, NULL, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CSLD_CODE',
                                                 ca.caseload_type),
                  1,
                  40
                 ),
          ca.active_flag, ca.provider_party_class, ca.provider_party_id, ca.provider_party_code,
          Tag_Prg_provider_name(ca.provider_party_class, ca.provider_party_ID, ca.provider_party_code),
          ca.placement_corporate_id, corp.corporate_name,
          ca.schedule_start_date, ca.schedule_end_date,
          ca.capacity, Tag_Prg_course_vacancy(ca.crs_acty_id) VACANCY
          , 0, ca.comment_text, ca.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc, ca.agy_loc_id,
          al.description, al.area_code, al.noms_region_code,
          ca.internal_location_id, ail.description
     FROM program_services ps, course_activities ca
LEFT OUTER JOIN agency_internal_locations ail ON (ca.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (ca.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (ca.placement_corporate_id = corp.corporate_id)
LEFT OUTER JOIN agency_locations al ON (ca.agy_loc_id = al.agy_loc_id)
WHERE ps.program_id = ca.program_id AND ps.program_category = 'WR' -- @@@ Rose 22-JUL-2010 Work Release @@@ --
     
UNION ALL

   SELECT 'CRS_PH', cp.parent_crs_acty_id, ca.code, cp.course_phase_id, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, ph.description, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CLOAD_TYPE',
                                                 ca.caseload_type
                                                ),
                  1,
                  40
                 ),
          cp.active_flag, cp.provider_party_class, cp.provider_party_id, cp.provider_party_code,
          Tag_Prg_provider_name(cp.provider_party_class, cp.provider_party_ID, cp.provider_party_code),
          cp.placement_corporate_id, corp.corporate_name,
          cp.schedule_start_date, cp.schedule_end_date, cp.capacity,
          Tag_Prg_course_vacancy(cp.course_phase_id), 0, cp.comment_text,
          cp.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc,  ca.agy_loc_id,
          al.description,
          al.area_code, al.noms_region_code, ca.internal_location_id,
          ail.description
     FROM program_services ps, v_program_phases ph, course_activities ca
LEFT OUTER JOIN agency_locations al ON (ca.agy_loc_id = al.agy_loc_id)
, v_course_phases cp
LEFT OUTER JOIN agency_internal_locations ail ON (cp.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (cp.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (cp.placement_corporate_id = corp.corporate_id)
WHERE cp.parent_crs_acty_id = ca.crs_acty_id AND cp.program_phase_id = ph.program_phase_id AND ps.program_id = ph.program_id AND ps.program_category = 'WR' -- @@@ Rose 22-JUL-2010 Work Release @@@ --
;

CREATE OR REPLACE VIEW v_agency_addresses (address_id, address_type, address_type_desc, agy_loc_id, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_direction, street_direction_desc, street_information, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, validated_flag, mail_care_of) AS SELECT
/* =========================================================
   Version Number = 1.6.4.0  Date Modified = 13/Dec/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 		Date      				 Version     	 Comments
   ---------      ------     			    ---------  	 ------------------------------
   NIKO          13-DEC-2007          1.6.4.0      Added a column mail_care_of
   VIKAS  		   13-FEB-2007   			  1.6.3.0 		 Street_Information column is created
   VIKAS 		     09-FEB-2007 	   		  1.6,2,0 		 Added City_Name and Prov_State_Name
   NIKO          26-JAN-2007          1.6.1.0      Replace the columns in V_STF_ADDR block:
                                           			   SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                  STREET_NUMBER (replaces PREMISE in the UK model)
                                                                  STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                  PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                  ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     06/23/2005             1.6            add address_type_desc column
   David Ng     06/21/2005  	         1.5            NOMIS new addresses table
*/
 ADDRESS_ID
,ADDRESS_TYPE
,ADDRESS_TYPE_DESC
,OWNER_code       agy_loc_ID
,start_date
,end_date
,ACTIVE_FLAG
,HOUSE
,STREET
,AREA
,COUNTRY
,SUITE_NUMBER
,STREET_NUMBER
,STREET_DIRECTION
,STREET_DIRECTION_DESC
,STREET_INFORMATION
,CITY_CODE
,CITY_NAME
,PROV_STATE_CODE
,PROV_STATE_DESC
,ZIP_POSTAL_CODE
,COUNTRY_CODE
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,VALIDATED_FLAG
,mail_care_of
FROM v_ADDRESSES
WHERE OWNER_CLASS = 'AGY'

 
;

CREATE OR REPLACE VIEW v_trust_header_b (caseload_id, offender_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, booking_no, booking_begin_date, booking_end_date, agy_loc_id, living_unit_id, disclosure_flag, active_flag, booking_status, living_unit_description, in_out_status, status_display) AS SELECT
     off_ta.CASELOAD_ID
    ,off_name.OFFENDER_ID
    ,off_name.ALIAS_OFFENDER_ID
    ,LPAD(coalesce(off_name.ALIAS_OFFENDER_ID,off_name.OFFENDER_ID)::varchar,10,'0')
    ,off_name.LAST_NAME
    ,off_name.FIRST_NAME
    ,off_name.MIDDLE_NAME
    ,off_name.SUFFIX
    ,off_name.BIRTH_DATE
    ,off_bkg.OFFENDER_BOOK_ID
    ,off_bkg.BOOKING_NO
    ,off_bkg.BOOKING_BEGIN_DATE
    ,off_bkg.BOOKING_END_DATE
    ,off_bkg.AGY_LOC_ID
    ,off_bkg.LIVING_UNIT_ID
    ,off_bkg.DISCLOSURE_FLAG
    ,off_bkg.ACTIVE_FLAG
    ,off_bkg.BOOKING_STATUS
    ,liv_unit.DESCRIPTION
    ,off_bkg.IN_OUT_STATUS
    ,CASE WHEN off_bkg.ACTIVE_FLAG='Y' THEN '  ACTIVE'  ELSE 'INACTIVE' END ||'-'||off_bkg.IN_OUT_STATUS
FROM offender_trust_accounts off_ta, offenders off_name
LEFT OUTER JOIN offender_bookings off_bkg ON (off_name.offender_id = off_bkg.offender_id)
LEFT OUTER JOIN living_units liv_unit ON (off_bkg.living_unit_id = liv_unit.living_unit_id)
WHERE off_ta.offender_id = off_name.offender_id AND off_ta.account_closed_flag = 'N' AND off_ta.offender_id in (
                 SELECT DISTINCT offender_id
                 FROM offender_sub_accounts );

CREATE OR REPLACE VIEW v_stg_membership_inquiry (offender_id_display, root_offender_id, offender_id, offender_book_id, last_name, first_name, active_flag, status_reason, description, stg_seq, stg_id, reason_code, stg_aff_active_flag, expired_by, expiry_date, expiry_reason_code, notified_date, notified_by, appeal_date, action_code, val_date) AS SELECT
/* =========================================================
   Version Number = 1.3         Date Modified = 04-APR-2012
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date         	   Version     	 Comments
   ---------     -----------       -----------   ------------------------------
   Patrick       04-APR-2012        1.3          Added root_offender_id to the view.
   NIKO          27-SEP-2006        1.2          Modified the view to select offender without validation
   NIKO          27-SEP-2006        1.1          Added
   NIKO          18-SEP-2006        1.0          Created
*/
      o.offender_id_display,
      o.root_offender_id
      ,ob.offender_id
      ,ob.offender_book_id
      ,o.last_name
      ,o.first_name
      ,ob.active_flag
      ,ob.status_reason
      ,lu.description
      ,ostga.stg_seq
      ,ostga.stg_id
      ,ostga.reason_code
      ,ostga.active_flag 	STG_AFF_ACTIVE_FLAG
      ,ostga.expired_by
      ,ostga.expiry_date
      ,ostga.expiry_reason_code
      ,ostga.notified_date
      ,ostga.notified_by
      ,ostga.appeal_date
      ,ostgd.action_code
      ,ostgd.val_date
FROM offenders o, offender_stg_affiliations ostga
LEFT OUTER JOIN (SELECT *
      FROM offender_stg_details ostgd
      WHERE ostgd.detail_seq = (SELECT max(detail_seq)
                                FROM offender_stg_details ostgd1
                                WHERE ostgd.offender_book_id = ostgd1.offender_book_id
					            AND ostgd.stg_seq = ostgd1.stg_seq)
      ORDER BY offender_book_id, stg_seq,detail_seq) ostgd ON (ostga.offender_book_id = ostgd.offender_book_id AND ostga.stg_seq = ostgd.stg_seq)
, offender_bookings ob
LEFT OUTER JOIN living_units lu ON (ob.living_unit_id = lu.living_unit_id)
WHERE ob.offender_book_id    = ostga.offender_book_id   AND o.offender_id          = ob.offender_id;

CREATE OR REPLACE VIEW v_course_phase_offerings (program_id, program_phase_id, course_id, course_caseload_type, provider_party_class, provider_party_id, provider_party_code, course_phase_id, offering_flag, ph_list_seq, ph_description, ph_capacity, ph_no_of_sessions, ph_session_length, ph_module_flag, cp_caseload_type, cp_caseload_type_desc, cp_internal_location_id, cp_internal_location_desc, cp_services_address_id, cp_list_seq, cp_active_flag, cp_expiry_date, cp_start_date, cp_end_date, cp_no_of_sessions, cp_session_length, cp_capacity, cp_placement_corporate_id, cp_comment_text, cp_course_activity_type, cp_check_sum) AS SELECT
/*
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   Neil         08/08/2006 2.1           Added cp_course_activity_type.
   David Ng     03/06/2006 2.1           NOMIS project(10.2.0)
*/
V.program_ID,
V.program_Phase_id,
V.COURSE_ID,
V.caseload_type,
V.PROVIDER_PARTY_CLASS,
V.PROVIDER_PARTY_ID,
V.PROVIDER_PARTY_CODE,
CP.Crs_ACty_ID,
CASE WHEN coalesce(CP.crs_acty_id::text, '') = '' THEN  'N'  ELSE 'Y' END ,
V.program_list_seq,
V.program_description,
V.program_capacity,
V.program_no_of_sessions,
V.program_session_length,
V.program_module_flag,
CP.caseload_type,
SUBSTR(Oms_Miscellaneous_getdesccode('CSLD_CODE',
                                        CP.caseload_type
                                                ),
                  1,
                  40
                 ),
CP.Internal_location_ID,
Tag_Int_Loc_int_loc_desc(CP.internal_location_id),
CP.SERVICES_ADDRESS_ID,
CP.list_seq,
CP.Active_Flag,
CP.Expiry_date,
CP.schedule_start_date,
CP.schedule_end_date,
CP.no_of_sessions,
CP.session_length,
CP.capacity,
CP.placement_corporate_ID,
CP.comment_text,
CP.course_activity_type,
Tag_Schedule_check_sum(coalesce(CP.modify_datetime, CP.create_datetime))
FROM v_course_phase_offerings_2 v
LEFT OUTER JOIN course_activities cp ON (V.program_Phase_id = CP.program_id AND V.COURSE_ID = CP.parent_crs_acty_ID);

CREATE OR REPLACE VIEW v_staff_addresses (address_id, address_type, address_type_desc, staff_id, last_name, first_name, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_direction, street_direction_desc, street_information, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, validated_flag) AS SELECT
/* =========================================================
   Version Number = 1.6.1.1 Date Modified = 02/13/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   Vikas Grover 02/13/2007  1.6.1.1        Columns added: Street, Street_Information, Prov_state_desc, city_name
   David Ng     06/23/2005  1.6            Add address_type_desc
   David Ng     06/21/2005  1.5            NOMIS new addresses table
*/
ADDRESS_ID
,ADDRESS_TYPE
,ADDRESS_TYPE_DESC
,addr.owner_ID        staff_ID
,last_name
,first_name
,start_date
,end_date
,ACTIVE_FLAG
,addr.HOUSE
,addr.STREET
,addr.AREA
,addr.COUNTRY
,addr.SUITE_NUMBER
,addr.STREET_NUMBER
,addr.STREET_DIRECTION
,addr.STREET_DIRECTION_DESC
,addr.STREET_INFORMATION
,addr.CITY_CODE
,addr.CITY_NAME
,addr.PROV_STATE_CODE
,addr.PROV_STATE_DESC
,addr.ZIP_POSTAL_CODE
,addr.COUNTRY_CODE
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,VALIDATED_FLAG
FROM staff_members stf
LEFT OUTER JOIN v_addresses addr ON (stf.staff_id = addr.owner_id)
WHERE OWNER_CLASS = 'STF';

CREATE OR REPLACE VIEW v_off_addresses (address_id, owner_id, address_type, address_type_desc, offender_id, offender_book_id, root_offender_id, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_information, street_direction, street_direction_desc, city_code, city_name, prov_state_code, prov_state_desc, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, validated_flag) AS SELECT
/* =========================================================
   Version Number = 1.0 Date Modified = 16/Mar/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date                          Version   Comments
   ---------           ------                            ---------  	 ------------------------------
   Sarah     16/Mar/2007   1.0           address description for Address_type
*/
addr.ADDRESS_ID
,addr.OWNER_ID
,addr.ADDRESS_TYPE
,addr.ADDRESS_TYPE_DESC
,addr.OWNER_ID
,Offender_Book_ID
,Root_Offender_ID
,addr.start_date
,addr.end_date
,addr.ACTIVE_FLAG
,addr.HOUSE
,addr.STREET
,addr.AREA
,addr.COUNTRY
,addr.SUITE_NUMBER
,addr.STREET_NUMBER
,addr.STREET_INFORMATION
,addr.STREET_DIRECTION
,addr.STREET_DIRECTION_DESC
,addr.CITY_CODE
,addr.CITY_NAME
,addr.PROV_STATE_CODE
,addr.PROV_STATE_DESC
,addr.ZIP_POSTAL_CODE
,addr.COUNTRY_CODE
,addr.COMMENT_TEXT
,addr.PRIMARY_FLAG
,addr.MAIL_FLAG
,addr.VALIDATED_FLAG
FROM offender_bookings bkg, v_addresses addr
WHERE OWNER_CLASS = 'OFF';

CREATE OR REPLACE VIEW v_housing_levels (location1_id, location1_desc, location2_id, location2_desc, location3_id, location3_desc, agy_loc_id, location_type) AS (SELECT DISTINCT arl.location1_id
       , lu.description
       , arl.location2_id
       , lu1.description
       , arl.location3_id
       , lu2.description
       , lu.agy_loc_id
       , arl.location_type
  FROM agency_reporting_locs arl
LEFT OUTER JOIN living_units lu ON (arl.location1_id = lu.living_unit_id)
LEFT OUTER JOIN living_units lu1 ON (arl.location2_id = lu1.living_unit_id)
LEFT OUTER JOIN living_units lu2 ON (arl.location3_id = lu2.living_unit_id) )

;

CREATE OR REPLACE VIEW v_name_search (last_name, first_name, active_flag, offender_id, offender_book_id, booking_no, offender_id_display, birth_date, agy_loc_id, agy_loc_name, living_unit_description, in_out_status, middle_name) AS SELECT
     /* =========================================================
        Version Number = 4.11.0.0  Date Modified = 06/05/2001                                                                                     ========================================================= */
     off_name.LAST_NAME
    ,off_name.FIRST_NAME
    ,off_bkg.ACTIVE_FLAG
    ,off_name.OFFENDER_ID
    ,off_bkg.OFFENDER_BOOK_ID
    ,off_bkg.BOOKING_NO
    ,off_name.OFFENDER_ID_DISPLAY
    ,off_name.BIRTH_DATE
    ,agy_loc.AGY_LOC_ID
    ,agy_loc.ABBREVIATION
    ,liv_unit.DESCRIPTION
    ,off_bkg.in_out_status
    ,off_name.MIDDLE_NAME
FROM
    offenders  off_name,
    offender_bookings  off_bkg,
    living_units  liv_unit,
    caseload_agency_locations C,
    staff_members S,
    agency_locations  agy_loc
 where off_name.OFFENDER_ID  =  off_bkg.OFFENDER_ID
  and   agy_loc.AGY_LOC_ID  =  off_bkg.AGY_LOC_ID
  and   liv_unit.LIVING_UNIT_ID  =  off_bkg.LIVING_UNIT_ID
  and    agy_loc.AGY_LOC_ID = C.AGY_LOC_ID
  AND  S.WORKING_CASELOAD_ID = C.CASELOAD_ID
  and      S.USER_ID =  upper(USER)
  and   C.AGY_LOC_ID NOT IN ('OUT', 'TRN')


 
;

CREATE OR REPLACE VIEW v_offender_course_appts_2 (event_id, offender_course_appt_grp_id, off_prgref_id, offender_course_appt_rule_id, crs_appt_id, start_date, end_date, schedule_date, event_status, week_day, start_time, end_time) AS SELECT
/* MODIFICATION HISTORY
   Person       Date       Version Comments
   ---------    ------     -----   ------------------------------
   David Ng     03/01/2006 2.0     NOMIS project(10.2.0)
   David Ng     30/01/2006 2.1     Add Course_schedule_ID
   David Ng     17/02/2006 2.2     Use phyical schedule date rather deduced one
   David Ng     19/05/2006 2.3     Add Holiday Flag
   David Ng     02/09/2009 2.4     Start Time and End Time have the correct date
*/
          NULL,
          voad.OFFENDER_COURSE_APPT_GRP_ID, voad.OFF_PRGREF_ID,
          voad.OFFENDER_COURSE_APPT_RULE_ID,
          voad.OFFENDER_COURSE_APPT_RULE_ID*1000000000+
                       (to_char(csd.schedule_date, 'YYYYMMDD'))::numeric ,
          voad.start_date, voad.end_date,
          csd.schedule_date, 'SCH',
          voad.week_day,
          to_date(to_char(csd.schedule_date, 'YYYYMMDD')||to_char(voad.start_time, 'HH24MISS'), 'YYYYMMDDHH24MISS'),
          to_date(to_char(csd.schedule_date, 'YYYYMMDD')||to_char(voad.end_time, 'HH24MISS'), 'YYYYMMDDHH24MISS')
--          voad.start_time,
--          voad.end_time
     FROM COURSE_SCHEDULE_DAYS csd, v_off_appt_days voad,
          Offender_Program_Profiles OPP
     WHERE csd.week_day = voad.week_day
       AND csd.schedule_date between voad.start_date AND coalesce(voad.end_date, to_date('01/01/3000', 'DD/MM/YYYY'))
       AND OPP.off_prgref_id = voad.off_prgref_id
       AND NOT EXISTS (SELECT 'x' FROM OFFENDER_COURSE_ATTENDANCES OCA
                       WHERE  OCA.Off_Prgref_ID = voad.Off_prgRef_ID
                       AND    OCA.Reference_id = voad.OFFENDER_COURSE_APPT_RULE_ID*1000000000+
                                                       (to_char(csd.schedule_date, 'YYYYMMDD'))::numeric )
       AND (OPP.Holiday_Flag = 'N' OR
           NOT EXISTS (SELECT 'x' FROM SYSTEM_EVENTS SE
                       WHERE  Event_type = 'HOL'
                       AND    csd.schedule_date between se.event_date and se.event_end_date ))

;

CREATE OR REPLACE VIEW v_agy_int_loc_amendments (living_unit_id, agy_loc_id, living_unit_type, living_unit_code, description, living_unit_description, level_1_code, level_2_code, level_3_code, level_4_code, agy_int_loc_amendment_id, internal_location_id, amend_date, column_name, old_value, new_value, deactivate_reason_code, action_code, amend_user_id) AS SELECT
/* =========================================================
   Version Number = 10.2.0  Date Modified = 04/07/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     04/07/2005  10.2.0      NOMIS project
*/
lu.LIVING_UNIT_ID
,lu.AGY_LOC_ID
,lu.LIVING_UNIT_TYPE
,lu.LIVING_UNIT_CODE
,lu.DESCRIPTION
,substr(description,instr(lu.description, '-',1,1)+1,length(lu.description)-instr(lu.description, '-',1,1)+1) LIVING_UNIT_DESCRIPTION
,lu.LEVEL_1_CODE
,lu.LEVEL_2_CODE
,lu.LEVEL_3_CODE
,lu.LEVEL_4_CODE
,a.AGY_INT_LOC_AMENDMENT_ID
,a.INTERNAL_LOCATION_ID
,a.AMEND_DATE
,a.COLUMN_NAME
,a.OLD_VALUE
,a.NEW_VALUE
,a.DEACTIVATE_REASON_CODE
,a.ACTION_CODE
,a.AMEND_USER_ID
FROM  agy_int_loc_amendments a, living_units lu
WHERE lu.living_unit_id = a.Internal_location_id

 
;

CREATE OR REPLACE VIEW v_offender_education_addresses (address_id, address_type, address_type_desc, offender_book_id, education_seq, start_date, end_date, active_flag, house, street, area, country, suite_number, street_number, street_direction, city_code, prov_state_code, zip_postal_code, country_code, comment_text, primary_flag, mail_flag, validated_flag, street_information, city_name, prov_state_desc) AS SELECT
/* =========================================================
   Version Number = 1.4.1.1  Date Modified = 26/02/2007
   ========================================================= */
/* MODIFICATION HISTORY
   Person            Date                 Version       Comments
   ---------      -----------              ---------        ------------------------------
   Sarah        26-Feb-2007         1.4.1.1        Added the following columns
                                                                   STREET_INFORMATION ,CITY_NAME ,PROV_STATE_DESC
   NIKO         26-JAN-2007         1.4.1.0        Replace the columns in V_STF_ADDR block:
                                                                                   SUITE_NUMBER  (replaces the FLAT in the UK model)
                                                                                   STREET_NUMBER (replaces PREMISE in the UK model)
                                                                                   STREET_DIRECTION    (replaces LOCALITY in the UK model)
                                                                                   PROV_STATE_CODE     (replaces COUNTY in the UK model)
                                                                                   ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
   David Ng     06/23/2005       1.4                     add address_type_desc
   David Ng     06/21/2005      1.3                     NOMIS new addresses table
*/
ADDRESS_ID
,ADDRESS_TYPE
,ADDRESS_TYPE_DESC
,OWNER_ID        Offender_book_ID
,OWNER_SEQ       education_seq
,start_DATE
,end_date
,ACTIVE_FLAG
,HOUSE
,STREET
,AREA
,COUNTRY,SUITE_NUMBER,STREET_NUMBER,STREET_DIRECTION
,CITY_CODE
,PROV_STATE_CODE
,ZIP_POSTAL_CODE
,COUNTRY_CODE
,COMMENT_TEXT
,PRIMARY_FLAG
,MAIL_FLAG
,VALIDATED_FLAG
,STREET_INFORMATION ,
CITY_NAME ,
PROV_STATE_DESC
FROM v_ADDRESSES
WHERE OWNER_CLASS = 'OFF_EDU'

 
;

CREATE OR REPLACE VIEW v_trust_header_a
AS SELECT off_ta.caseload_id AS trust_caseload_id,
    cl.caseload_id,
    cl.commissary_trust_caseload,
    off_name.offender_id,
    off_name.alias_offender_id,
    off_name.offender_id_display,
    off_name.last_name,
    off_name.first_name,
    off_name.middle_name,
    off_name.suffix,
    off_name.birth_date,
    off_bkg.offender_book_id,
    off_bkg.booking_no,
    off_bkg.booking_type,
    off_bkg.booking_begin_date,
    off_bkg.booking_end_date,
    off_bkg.agy_loc_id,
    off_bkg.living_unit_id,
    off_bkg.disclosure_flag,
    off_bkg.active_flag,
    off_bkg.booking_status,
        CASE
            WHEN cl.caseload_type::text = 'COMM'::text THEN (((((((
            CASE
                WHEN off_bkg.community_active_flag::text = 'Y'::text THEN off_bkg.community_agy_loc_id
                ELSE NULL::character varying
            END::text || ' - '::text) || staff.last_name::text) || ', '::text) || staff.first_name::text) || ' ; '::text) || off_bkg.comm_staff_role::text) || ' : '::text) || off_bkg.agy_loc_id::text
            ELSE (((liv_unit.description::text || ';'::text) || agency_iml.internal_location_code::text) || ' : '::text) || off_bkg.community_agy_loc_id::text
        END AS living_unit_description,
    off_bkg.in_out_status,
        CASE
            WHEN cl.caseload_type::text = 'COMM'::text THEN
            CASE
                WHEN off_bkg.community_active_flag::text = 'Y'::text THEN oms_system_profile('CLIENT'::character varying, 'B/C_STATUS'::character varying, 1)::text ||
                CASE
                    WHEN oms_system_profile('C_KER'::character varying, 'ACT_SUP_STS'::character varying, 1)::text = 'Y'::text THEN '-'::text || ((( SELECT offender_comm_sup_histories.status_code
                       FROM offender_comm_sup_histories
                      WHERE offender_comm_sup_histories.offender_book_id = off_bkg.offender_book_id AND offender_comm_sup_histories.status_seq = (( SELECT max(offender_comm_sup_histories_1.status_seq) AS max
                               FROM offender_comm_sup_histories offender_comm_sup_histories_1
                              WHERE offender_comm_sup_histories_1.offender_book_id = off_bkg.offender_book_id AND offender_comm_sup_histories_1.effective_date <= LOCALTIMESTAMP))))::text)
                    WHEN oms_system_profile('C_KER'::character varying, 'ACT_SUP_STS'::character varying, 1)::text = 'N'::text THEN '-'::text || off_bkg.comm_status::text
                    ELSE '-'::text || off_bkg.comm_status::text
                END
                WHEN off_bkg.community_active_flag::text = 'N'::text THEN oms_system_profile('CLIENT'::character varying, 'B/C_STATUS'::character varying, 2)::text
                ELSE NULL::text
            END
            ELSE
            CASE
                WHEN off_bkg.active_flag::text = 'Y'::text THEN 'ACTIVE-'::text
                WHEN off_bkg.active_flag::text = 'N'::text THEN 'INACTIVE-'::text
                ELSE NULL::text
            END ||
            CASE
                WHEN off_bkg.in_out_status::text = 'OUT'::text THEN
                CASE
                    WHEN mov_rsn.header_status_flag::text = 'Y'::text THEN substr(off_bkg.status_reason::text, "position"(off_bkg.status_reason::text, '-'::text) + 1)::character varying
                    ELSE off_bkg.in_out_status
                END
                ELSE off_bkg.in_out_status
            END::text
        END AS status_display,
    off_name.root_offender_id,
    off_bkg.intake_agy_loc_id,
    off_bkg.community_active_flag,
    off_bkg.create_intake_agy_loc_id,
    off_bkg.comm_status AS community_status,
    off_bkg.status_reason,
    mov_rsn.header_status_flag AS header_status
   FROM offender_trust_accounts off_ta,
    offenders off_name,
    caseloads cl,
    offender_bookings off_bkg
     LEFT JOIN staff_members staff ON off_bkg.comm_staff_id = staff.staff_id
     LEFT JOIN agency_locations agy_loc ON off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text
     LEFT JOIN agency_internal_locations agency_iml ON off_bkg.agency_iml_id = agency_iml.internal_location_id
     LEFT JOIN living_units liv_unit ON off_bkg.living_unit_id = liv_unit.living_unit_id
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE off_ta.offender_id = off_name.root_offender_id AND (EXISTS ( SELECT 'x'::text AS text
           FROM offender_sub_accounts off_sa
          WHERE off_sa.caseload_id::text = off_ta.caseload_id::text AND off_sa.offender_id = off_ta.offender_id)) AND off_name.offender_id = off_bkg.offender_id AND off_ta.caseload_id::text = COALESCE(cl.community_trust_caseload, cl.caseload_id)::text;

CREATE OR REPLACE VIEW v_offender_cip_inquiry (offender_id_display, offender_book_id, placement_seq, last_name, first_name, living_unit, placement_type, placement_type_desc, placement_reason_code, placement_reason_desc, expiry_date, review_date, rel_by_per_code, rel_by_per_name, release_date, release_time, effective_time, agy_loc_id) AS SELECT
  /* =========================================================
   Version Number = 11.2.1.2 Date Modified = 8-May-2020
   =========================================================
   MODIFICATION HISTORY
   Person      Date            Version       Comments
   ---------   --------------- ------------  ------------------------------
   Erin        11-May-2020     11.2.1.2      EF-4273: added check for active offenders only
   Erin        08-May-2020     11.2.1.1      EF-4268: added review_date
   Erin        21-Apr-2020     11.2.1.0      EF-4173: Initial Version
 */
       vhb.offender_id_display,
       ocd.offender_book_id,
       ocd.placement_seq,
       vhb.last_name, 
       vhb.first_name, 
       SUBSTR(vhb.living_unit_description,
                     1,
                     position(';' in vhb.living_unit_description) - 1) living_unit,
       ocd.placement_type,
       oms_miscellaneous_getdesccode('PLACE_TYPE',ocd.placement_type) placement_type_desc,
       ocd.placement_reason_code,
       oms_miscellaneous_getdesccode('PLACE_RSN',ocd.placement_reason_code) placement_reason_desc,
       ocd.expiry_date,
       ocd.review_date, --@@@ Erin 8-May-20 EF-4268: added
       ocd.rel_by_per_code,
       ocd.rel_by_per_name,
       ocd.release_date,
       ocd.release_time, 
       ocd.effective_time,
       ocd.agy_loc_id
  FROM offender_cip_details ocd, 
       v_header_block vhb
  WHERE ocd.offender_book_id = vhb.offender_book_id
  AND vhb.active_flag = 'Y';

CREATE OR REPLACE VIEW v_incident_cases (incident_case_id, reported_staff_id, incident_date, incident_time, incident_title, questionnaire_id, incident_type_desc, incident_status, incident_status_desc, report_date, report_time, agy_loc_id, agy_loc_id_desc, sub_area_code, area_code, noms_region_code, geographic_region_code, justice_area_code, local_authority_code, follow_up_date) AS SELECT
/* MODIFICATION HISTORY
   Person      Date        Version       Comments
   David Ng    08/08/2006  2.1           Add Follow up date
   David Ng    01/10/2005  2.0           NOMIS project (10.2.0)
*/
IC.INCIDENT_CASE_ID,
IC.REPORTED_STAFF_ID,
IC.INCIDENT_DATE,
IC.INCIDENT_TIME,
IC.INCIDENT_TITLE,
IC.questionnaire_id,
vit.DESCRIPTION,
IC.INCIDENT_STATUS,
ISTS.DESCRIPTION,
IC.REPORT_DATE,
IC.REPORT_TIME,
IC.AGY_LOC_ID,
AL.DESCRIPTION,
AL.sub_area_code,
AL.area_code,
AL.NOMS_REGION_CODE,
AL.geographic_region_code,
AL.JUSTICE_AREA_CODE,
ALA.LOCAL_AUTHORITY_CODE,
IC.follow_up_date
FROM v_incident_types vit, incident_statuses ists, incident_cases ic, agency_locations al
LEFT OUTER JOIN agency_location_authorities ala ON (AL.AGY_LOC_ID = ALA.AGY_LOC_ID)
WHERE AL.agy_loc_ID = IC.Agy_LOc_ID AND IC.INCIDENT_STATUS = ISTS.CODE AND IC.Questionnaire_ID = vit.Questionnaire_ID;

CREATE OR REPLACE VIEW v_offender_internal_schedules (offender_id_display, offender_book_id, root_offender_id, last_name, first_name, living_unit_description, event_date, event_time, internal_schedule_type, internal_schedule_reason, schedule_id, agy_loc_id, comment_text) AS SELECT o.offender_id_display,
          ob.offender_book_id,
     o.root_offender_id,
     o.last_name,
     o.first_name,
     lu.description,
     os.event_date,
     os.start_time,
     os.event_type,
     os.event_sub_type,
     os.event_id,
     os.agy_loc_id,
     os.comment_text
     FROM offenders o,
          offender_bookings ob,
     offender_ind_schedules os,
     living_units lu
    WHERE o.root_offender_id = ob.root_offender_id
      AND o.offender_id = ob.offender_id
      AND ob.booking_status = 'O'
      AND os.offender_book_id = ob.offender_book_id
      AND ob.living_unit_id = lu.living_unit_id

 
;

CREATE OR REPLACE VIEW v_personal_assignments
AS SELECT off.offender_id_display,
    off.offender_id,
    off.root_offender_id,
    off.last_name,
    off.first_name,
    off_bkg.offender_book_id,
    off_bkg.agy_loc_id,
    off_bkg.active_flag,
    liv_unit.living_unit_id,
    liv_unit.description AS living_unit_desc
   FROM offenders off
     JOIN offender_bookings off_bkg ON off.offender_id = off_bkg.offender_id
     LEFT JOIN living_units liv_unit ON off_bkg.living_unit_id = liv_unit.living_unit_id;

CREATE OR REPLACE VIEW v_property_header_block_2 (offender_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, ppty_agy_loc_id, booking_no, booking_begin_date, booking_end_date, agy_loc_id, living_unit_id, disclosure_flag, active_flag, booking_status, living_unit_description, in_out_status, status_display, root_offender_id) AS SELECT /*+ Index(offender_bookings_pk, offender_bookings_ni2) */       OFF_NAME.OFFENDER_ID
     , OFF_NAME.ALIAS_OFFENDER_ID
     , OFF_NAME.OFFENDER_ID_DISPLAY
     , OFF_NAME.LAST_NAME
     , OFF_NAME.FIRST_NAME
     , OFF_NAME.MIDDLE_NAME
     , OFF_NAME.SUFFIX
     , OFF_NAME.BIRTH_DATE
     , OFF_BKG.OFFENDER_BOOK_ID
     , CSLD_AL.AGY_LOC_ID
     , OFF_BKG.BOOKING_NO
     , OFF_BKG.BOOKING_BEGIN_DATE
     , OFF_BKG.BOOKING_END_DATE
     , OFF_BKG.AGY_LOC_ID
     , OFF_BKG.LIVING_UNIT_ID
     , OFF_BKG.DISCLOSURE_FLAG
     , OFF_BKG.ACTIVE_FLAG
     , OFF_BKG.BOOKING_STATUS
     , LIV_UNIT.DESCRIPTION
     , OFF_BKG.IN_OUT_STATUS
     , CASE WHEN OFF_BKG.ACTIVE_FLAG='Y' THEN '  ACTIVE'  ELSE 'INACTIVE' END ||'-'||
        OFF_BKG.IN_OUT_STATUS
     ,OFF_NAME.ROOT_OFFENDER_ID
 FROM staff_accessible_caseloads staff_ac, staff_members staff, offenders off_name, caseload_agency_locations csld_al, caseloads csld, offender_bookings off_bkg
LEFT OUTER JOIN living_units liv_unit ON (OFF_BKG.LIVING_UNIT_ID = LIV_UNIT.LIVING_UNIT_ID)
WHERE OFF_NAME.OFFENDER_ID = OFF_BKG.OFFENDER_ID  AND STAFF.USER_ID = USER AND STAFF_AC.CASELOAD_ID = STAFF.WORKING_CASELOAD_ID AND STAFF_AC.STAFF_ID = STAFF.STAFF_ID AND STAFF_AC.CASELOAD_ID = CSLD.CASELOAD_ID AND CSLD_AL.CASELOAD_ID = CSLD.CASELOAD_ID AND CSLD_AL.AGY_LOC_ID NOT IN ('OUT', 'TRN')

;
CREATE OR REPLACE VIEW v_corporate_address_phones (phone_id, owner_class, owner_id, owner_seq, owner_code, corporate_id, address_seq, phone_type, phone_area, phone_no, ext_no) AS SELECT
/* =========================================================
   Version Number = 1.0  Date Modified = 06/21/2005
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date      Version        Comments
   ---------    ------     ---------     ------------------------------
   Johnny       09/03/2008 1.0           Initial creation for getting all the address for a corporate.
*/
/* There will be duplicate for any global phones number for a corporate that associates to 2 addresses.  This duplicate
   is there to make sure that all addresses for this corporate is associated with this phone number too */
       p.phone_id,
       p.owner_class,
       p.owner_id,
       p.owner_seq,
       p.owner_code,
       corp_add.corporate_id,
       corp_add.address_seq,
       p.phone_type,
       substr(p.phone_no, 1, 3),
       substr(p.phone_no, 4),
       p.ext_no
  FROM phones p, v_corporate_addresses corp_add
 WHERE (p.owner_class = 'ADDR' AND p.owner_id = corp_add.address_id)
    OR (p.owner_class = 'CORP' AND p.owner_id = corp_add.corporate_id)

;

CREATE OR REPLACE VIEW v_int_loc_summaries (agy_loc_id, internal_location_id, parent_internal_location_id, internal_location_code, internal_location_desc, user_desc, tracking_flag, capacity, list_seq, active_flag, deactivate_date, internal_location_type, internal_location_type_desc, in_locations) AS select
/* MODIFICATION HISTORY
   Person     	 Date      Version  Comments
   David Ng     15/10/2005  2.0       NOMIS project(10.2.0)
   David Ng     11/01/2006  2.1       Only Tracking location show
*/
ail.agy_loc_id
,ail.internal_location_id
,ail.parent_internal_location_id
,ail.Internal_location_code
,ail.description
,ail.user_desc
,ail.tracking_flag
,ail.capacity
,ail.list_seq
,ail.active_flag
,ail.deactivate_date
,ail.internal_location_type
,substr(oms_miscellaneous_getDescCode('ILOC_TYPE',ail.Internal_location_type),1,40)
,vr.in_locations
FROM  agency_Internal_locations ail, v_int_loc_rolls vr
where ail.internal_location_id = vr.internal_location_id

 
;

CREATE OR REPLACE VIEW v_offender_all_visitors (visitor_id, visitor_type, last_name, first_name, middle_name, relationship, offender_visit_id) AS SELECT
/*
============================================================
 Version Number = 2.1    Date Modified = 14/07/2006
============================================================
MODIFICATION HISTORY
     Person      Date           Version         Comments
     Erin        14-Jul-2006    2.1             Change for new visits datamodel
     GJC         28-Feb-2006    1.1             Initial version
*/
          off_vis_vis.person_id::varchar visitor_id,
          'PERSON' vistor_type, per.last_name, per.first_name,
          per.middle_name, ref_code1.description, off_vis.offender_visit_id
     FROM persons per, offender_visit_visitors off_vis_vis, offender_visits off_vis, offender_contact_persons ocp
LEFT OUTER JOIN (SELECT code, description
             FROM reference_codes
            WHERE domain = 'RELATIONSHIP') ref_code1 ON (ocp.relationship_type = ref_code1.code)
WHERE per.person_id = off_vis_vis.person_id AND (off_vis_vis.person_id IS NOT NULL AND off_vis_vis.person_id::text <> '') AND off_vis_vis.person_id = ocp.person_id AND off_vis.offender_book_id = ocp.offender_book_id AND off_vis.offender_visit_id = off_vis_vis.offender_visit_id
UNION ALL

   SELECT vns.offender_id_display, 'OFFENDER' vistor_type, vns.last_name, vns.first_name, vns.middle_name, ref_code1.description,off_vis.offender_visit_id
     FROM v_name_search2 vns, offender_visit_visitors off_vis_vis, offender_visits off_vis, offender_bookings ob, offender_contact_persons ocp
LEFT OUTER JOIN (SELECT code, description
             FROM reference_codes
           WHERE domain = 'RELATIONSHIP') ref_code1 ON (ocp.relationship_type = ref_code1.code)
WHERE off_vis.offender_visit_id = off_vis_vis.offender_visit_id AND (off_vis_vis.offender_book_id IS NOT NULL AND off_vis_vis.offender_book_id::text <> '') AND ob.offender_book_id =off_vis_vis.offender_book_id AND ocp.contact_root_offender_id = ob.root_offender_id  AND ocp.offender_book_id = off_vis.offender_book_id AND vns.root_offender_id = ocp.contact_root_offender_id

 
 
;

CREATE OR REPLACE VIEW v_merge_transactions (merge_transaction_id, application_code, process_id, transfer_flag) AS SELECT merge_transaction_id, application_code, process_id, transfer_flag
FROM   v_merge_transaction_rules
group by  merge_transaction_id, application_code, process_id, transfer_flag

;

CREATE OR REPLACE VIEW v_living_unit_offenders (agy_loc_id, living_unit_id, living_unit_desc, parent_living_unit_id, root_living_unit_id, agency_iml_id, agency_iml_desc, in_out_status, active_flag, offender_book_id, offender_id, offender_id_display, last_name, first_name, alert_flag) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
*/
          LUB.agy_loc_id, LUB.living_unit_id, LUB.living_unit_desc,
          LUB.parent_living_unit_id, LUB.root_living_unit_id,
          LUB.in_internal_location_id, ail.description, LUB.in_out_status,
          LUB.active_flag, LUB.offender_book_id, LUB.offender_id,
          OFF.offender_id_display, OFF.last_name, OFF.first_name,
          (SELECT CASE WHEN COUNT(*)=0 THEN  'N'  ELSE 'Y' END
             FROM offender_alerts oa
            WHERE oa.offender_book_id = LUB.offender_book_id
              AND alert_status = 'ACTIVE')
     FROM v_living_unit_off_bkgs lub
LEFT OUTER JOIN offenders off ON (LUB.offender_id = OFF.offender_id)
LEFT OUTER JOIN agency_internal_locations ail ON (LUB.in_internal_location_id = ail.internal_location_id)
WHERE (in_living_unit_id IS NOT NULL AND in_living_unit_id::text <> '')
 
 
;

CREATE OR REPLACE VIEW v_living_unit_summaries (agy_loc_id, living_unit_id, parent_living_unit_id, living_unit_desc, user_desc, level_1_code, level_2_code, level_3_code, level_4_code, capacity, list_seq, active_flag, deactivate_date, living_unit_type, living_unit_type_desc, allocated, in_living_units, out_of_living_units, out_of_agy, reserved_beds, vacancy, filled_flag) AS select
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   David Ng     15/10/2005  2.0          NOMIS project(10.2.0)
   David Ng     27/06/2006  2.1          Use Living_unit domain
*/
lu.agy_loc_id
,lu.living_unit_id
,lu.parent_living_unit_id
,lu.description
,lu.user_desc
,lu.LEVEL_1_CODE
,lu.LEVEL_2_CODE
,lu.LEVEL_3_CODE
,lu.LEVEL_4_CODE
,lu.capacity
,lu.list_seq
,lu.active_flag
,lu.deactivate_date
,lu.Living_Unit_type
--,substr(oms_miscellaneous.getDescCode('ILOC_TYPE',lu.living_unit_type),1,40)
,substr(oms_miscellaneous_getDescCode('LIVING_UNIT',lu.living_unit_type),1,40)
,vr.allocated
,vr.in_living_units
,vr.out_of_living_units
,vr.out_of_agy
,reserved_beds
,lu.capacity-vr.allocated
,CASE WHEN greatest(lu.capacity-vr.allocated,0)=0 THEN  'Y'  ELSE 'N' END
FROM  Living_Units lu, v_living_unit_rolls vr
where lu.living_unit_id = vr.living_unit_id
 
 
;

CREATE OR REPLACE VIEW v_offender_all_schedules_4 (event_id, offender_book_id, in_out_status, booking_no, booking_active_flag, offender_id, offender_id_display, offender_last_name, offender_first_name, event_date, start_time, end_time, event_class, event_type, event_type_desc, event_sub_type, event_sub_type_desc, active_flag, event_status, event_status_desc, event_outcome, event_outcome_desc, busy_date_flag, outcome_reason_code, reference_id, application_date, application_time, return_date, return_time, comment_text, details, agy_loc_id, agy_loc_desc, living_unit_id, living_unit_desc, lu_level_1_code, lu_level_2_code, lu_level_3_code, lu_level_4_code, agency_iml_id, agency_iml_desc, agency_iml_level_1_code, agency_iml_level_2_code, agency_iml_level_3_code, to_agy_loc_id, to_agy_loc_desc, to_loc, to_loc_desc, escort_code, escort_desc, direction_code, schedule_movement_time, from_city_code, from_city_name, to_city_code, to_city_name, to_internal_location_id, to_internal_location_desc, to_int_loc_level_1_code, to_int_loc_level_2_code, to_int_loc_level_3_code, to_int_loc_user_desc, credited_hours, engagement_code, understanding_code, piece_work, in_time, out_time, performance_code, transport_code, sick_note_expiry_date, sick_note_received_date, unexcused_absence_flag, unpaid_work_action, unpaid_work_behaviour, agreed_travel_hour, check_box_1, check_box_2, hidden_comment_text, in_charge_staff_id, in_charge_staff_name, off_prgref_id, contact_person_name, to_address_owner_class, to_address_id, unpaid_work_supervisor, ta_id, record_source, check_sum, prov_state_code, prov_state_desc, scheduled_trip_id) AS SELECT
   /* =========================================================
    Version Number = 1.0 Date Modified = 04/08/2013
    ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
----------------------------------------------------------------------------------------------------------------------------------------------------
   David Ng     15/10/2005  2.0          NOMIS project(10.2.0)
   David Ng     11/01/2006  2.1          Court event reason based on MOVE_RSN Domain
   David Ng     13/01/2006  2.2          Add To_locaiton And description
   David Ng     13/01/2006  2.3          Add In_Out_Status
   David Ng     24/01/2006  2.4          Remove NSI_Flag
   Niko               MAR-13-2007   2.6.1.0     Added a new column - prov_state_code
                                                                          Removed 2 columns - TO_COUNTRY_CODE and OJ_LOCATION_CODE
   Farhad       23-JULY-2009 2.6.1.1     Added a new column - scheduled_trip_id
   David Ng     12/09/2009  2.6.1.2      Add Direction_code and schedule_movement_time for external  movements
   Jason Xu     08/04/2013  1.0          copy from V_OFFENDER_ALL_SCHEDULES 2.6.1.2 for SD form
                                         OIIWLTWJ performance issue
*/
          sch.event_id, sch.offender_book_id, bkg.in_out_status, bkg.booking_no, bkg.active_flag,
          bkg.offender_id, OFF.offender_id_display, OFF.last_name,
          OFF.first_name, sch.event_date, sch.start_time, sch.end_time,
          sch.event_class, sch.event_type,
          SUBSTR(Tag_Schedule_event_type_desc(sch.event_class, sch.event_type), 1, 40 ),
          sch.event_sub_type,
          SUBSTR(Tag_Schedule_event_sub_type_desc(sch.event_class,  sch.event_type, sch.event_sub_type ), 1, 40 ),
          CASE WHEN event_status='SCH' THEN  'Y'  ELSE 'N' END , event_status,
          SUBSTR(Oms_Miscellaneous_getdesccode('EVENT_STS',  sch.event_status), 1, 40 ),
          event_outcome,
          SUBSTR(Oms_Miscellaneous_getdesccode('OUTCOMES', sch.event_outcome),1, 40 ),
          SUBSTR(Tag_Schedule_busy_date_flag(sch.offender_book_id, sch.event_date ),1,1),
          sch.outcome_reason_code, sch.reference_id,
          sch.application_date, sch.application_time, sch.return_date,
          sch.return_time, sch.comment_text, sch.details, sch.agy_loc_id,
          agy.description, bkg.living_unit_id, lu.description,
          lu.level_1_code, lu.level_2_code, lu.level_3_code, lu.level_4_code,
          bkg.agency_iml_id, ail2.description,
          SUBSTR(Tag_Int_Loc_level_code(ail2.description, 1), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail2.description, 2), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail2.description, 3), 1, 40),
          sch.to_agy_loc_id, agy2.description,
          --NVL(sch.to_agy_loc_id, OJ_LOCATION_code),
		  sch.to_agy_loc_id,
          --NVL(agy2.description, SUBSTR (Oms_Miscellaneous.getdesccode ('OJ_LOCATION', oj_location_code),1,40 )),
		  agy2.description,
          sch.escort_code,
          SUBSTR(Oms_Miscellaneous_getdesccode('ESCORT', sch.escort_code), 1, 40 ),
          sch.direction_code, sch.schedule_movement_time, sch.from_city_code,
          SUBSTR(Oms_Miscellaneous_getdesccode('CITY', sch.from_city_code), 1, 40 ),
          sch.to_city_code,
          SUBSTR(Oms_Miscellaneous_getdesccode('CITY', sch.to_city_code), 1, 40),
          sch.to_internal_location_id, ail.description,
          SUBSTR(Tag_Int_Loc_level_code(ail.description, 1), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail.description, 2), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail.description, 3), 1, 40),
          ail.user_desc, sch.credited_hours, sch.engagement_code,
          sch.understanding_code, sch.piece_work, sch.in_time, sch.out_time,
          sch.performance_code, sch.transport_code,
--		  sch.oj_location_code,
--          SUBSTR (Oms_Miscellaneous.getdesccode ('OJ_LOCATION', oj_location_code ), 1,  40 ),
--          sch.to_country_code,
--          SUBSTR (Oms_Miscellaneous.getdesccode ('COUNTRY', to_country_code), 1, 40  ),
          sch.sick_note_expiry_date, sch.sick_note_received_date,
          sch.unexcused_absence_flag, sch.unpaid_work_action,
          sch.unpaid_work_behaviour, sch.agreed_travel_hour, sch.check_box_1,
          sch.check_box_2, sch.hidden_comment_text, sch.in_charge_staff_id,
          SUBSTR(CASE WHEN coalesce(in_charge_staff_id::text, '') = '' THEN  ' '  ELSE (stf.last_name || ', ' || stf.first_name  ) END , 1, 40 ),
          sch.off_prgref_id, sch.contact_person_name,
          sch.to_address_owner_class, sch.to_address_id,
          sch.unpaid_work_supervisor, sch.ta_id, sch.record_source
         ,sch.check_sum
		,prov_state_code
		,SUBSTR(Oms_Miscellaneous_getdesccode('PROV_STATE', prov_state_code ), 1,  40 ) prov_state_desc
         ,sch.scheduled_trip_id
     FROM offenders off, v_offender_all_schedules_3 sch
LEFT OUTER JOIN agency_locations agy ON (sch.agy_loc_id = agy.agy_loc_id)
LEFT OUTER JOIN agency_locations agy2 ON (sch.to_agy_loc_id = agy2.agy_loc_id)
LEFT OUTER JOIN agency_internal_locations ail ON (sch.to_internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN staff_members stf ON (sch.in_charge_staff_id = stf.staff_id)
, offender_bookings bkg
LEFT OUTER JOIN agency_internal_locations ail2 ON (bkg.agency_iml_id = ail2.internal_location_id)
LEFT OUTER JOIN living_units lu ON (bkg.living_unit_id = lu.living_unit_id)
WHERE sch.offender_book_id = bkg.offender_book_id AND bkg.offender_id = OFF.offender_id;

CREATE OR REPLACE VIEW v_payroll_batch_summaries (payroll_batch_id, offender_id, offender_book_id, summary_number_of_units, first_name, last_name, summary_payroll_amount) AS SELECT
     off_wrk.PAYROLL_BATCH_ID
    ,off_wrk.OFFENDER_ID
    ,off_wrk.OFFENDER_BOOK_ID
    ,SUM(off_wrk.NUMBER_OF_UNITS)
    ,v_payh.FIRST_NAME
    ,v_payh.LAST_NAME
    ,SUM(off_wrk.PAYROLL_AMOUNT)
FROM
    offender_works  off_wrk,
    v_payroll_header  v_payh
 WHERE  	off_wrk.offender_id = v_payh.root_offender_id
 AND           processed_flag = 'N'
 AND          coalesce(summary_offender_work_id::text, '') = ''
 AND	payroll_batch_id > 0
 AND          v_payh.payroll_caseload_id = off_wrk.caseload_id
-- @@@ Joe Wong, 25-JAN-2000.
-- AND          v_payh.booking_begin_date IN (SELECT
--                            MAX(PH.booking_begin_date)
--                            FROM v_payroll_header PH
--                            WHERE PH.offender_id = v_payh.offender_id
--                          AND      PH.payroll_caseload_id = v_payh.payroll_caseload_id)
  GROUP BY  off_wrk.payroll_batch_id,
 	off_wrk.offender_id,
 	v_payh.first_name,
 	v_payh.last_name,
                   off_wrk.offender_book_id

 
;

CREATE OR REPLACE VIEW v_offender_course_events
AS SELECT NULL::bigint AS event_id,
    opp.offender_book_id,
    substr(
        CASE
            WHEN rc.parent_code::text = 'BOTH'::text THEN tag_prg_prg_event_class(opp.off_prgref_id)
            ELSE rc.parent_code
        END::text, 1, 12) AS event_class,
    substr(COALESCE(ps.program_category, tag_prg_prg_event_type(ps.program_id))::text, 1, 12) AS event_type,
    ca.course_activity_type AS event_sub_type,
    opp.off_prgref_id,
    cs.crs_sch_id AS reference_id,
    cs.crs_sch_id,
    NULL::numeric AS crs_appt_id,
    opp.program_id,
    ca.code AS course_code,
    ca.description,
    cs.crs_acty_id,
    to_char(cs.schedule_date, 'DY'::text) AS weekday,
    cs.schedule_date AS event_date,
    cs.start_time,
    cs.end_time,
        CASE
            WHEN rc.parent_code::text = 'COMM'::text THEN cs.start_time
            ELSE NULL::timestamp without time zone
        END AS in_time,
        CASE
            WHEN rc.parent_code::text = 'COMM'::text THEN cs.end_time
            ELSE NULL::timestamp without time zone
        END AS out_time,
        CASE
            WHEN opp.suspended_flag::text = 'N'::text THEN 'SCH'::text
            ELSE 'CANC'::text
        END AS event_status,
    NULL::text AS event_outcome,
    opp.agy_loc_id,
    ca.internal_location_id AS to_internal_location_id,
    NULL::text AS comment_text,
    NULL::text AS outcome_reason_code,
    NULL::bigint AS piece_work,
    NULL::text AS engagement_code,
    NULL::text AS understanding_code,
    NULL::bigint AS credited_hours,
    opp.agreed_travel_hour,
    NULL::text AS behaviour_code,
    NULL::text AS action_code,
    NULL::date AS sick_note_received_date,
    NULL::date AS sick_note_expiry_date,
    NULL::character varying AS performance_code,
    addr.owner_code AS to_agy_loc_id,
    ca.services_address_id AS to_address_id,
    NULL::text AS performance_desc,
    NULL::text AS event_outcome_desc,
    NULL::bigint AS supervisor_staff_id,
    NULL::text AS unexcused_absence_flag,
        CASE
            WHEN
            CASE
                WHEN rc.parent_code::text = 'BOTH'::text THEN tag_prg_prg_event_class(opp.off_prgref_id)
                ELSE rc.parent_code
            END::text = 'EXT_MOV'::text THEN 'OUT'::text
            ELSE NULL::text
        END AS direction_code,
    NULL::timestamp without time zone AS ext_move_out_time,
    NULL::timestamp without time zone AS ext_move_in_time,
        CASE
            WHEN
            CASE
                WHEN rc.parent_code::text = 'BOTH'::text THEN tag_prg_prg_event_class(opp.off_prgref_id)
                ELSE rc.parent_code
            END::text = 'EXT_MOV'::text THEN cs.start_time
            ELSE NULL::timestamp without time zone
        END AS schedule_movement_time,
    cs.session_no,
    'V_CRS_GRP'::text AS record_source,
    0 AS check_sum
   FROM reference_codes rc,
    program_services ps,
    offender_program_profiles opp,
    offender_bookings ob,
    course_schedules cs,
    course_activities ca
     LEFT JOIN addresses addr ON ca.services_address_id = addr.address_id
  WHERE opp.crs_acty_id = cs.crs_acty_id AND ca.crs_acty_id = opp.crs_acty_id AND ca.course_activity_type IS NOT NULL AND ca.course_activity_type::text <> ''::text AND opp.offender_program_status::text = 'ALLOC'::text AND ca.program_id = ps.program_id AND rc.domain::text = 'PS_CATEGORY'::text AND rc.code::text = COALESCE(ps.program_category, tag_prg_prg_event_type(ps.program_id))::text AND (rc.parent_code::text = ANY (ARRAY['COMM'::character varying, 'INT_MOV'::character varying, 'BOTH'::character varying, 'EXT_MOV'::character varying]::text[])) AND ca.active_flag::text = 'Y'::text AND ob.offender_book_id = opp.offender_book_id AND COALESCE(cs.catch_up_crs_sch_id::text, ''::text) = ''::text AND (COALESCE(cs.session_no::text, ''::text) = ''::text OR COALESCE(opp.start_session_no::text, ''::text) = ''::text OR cs.session_no >= opp.start_session_no) AND COALESCE(opp.offender_start_date, cs.schedule_date) <= cs.schedule_date AND COALESCE(COALESCE(opp.offender_end_date, ca.schedule_end_date), cs.schedule_date) >= cs.schedule_date AND NOT (EXISTS ( SELECT 'x'::text
           FROM offender_course_attendances oca
          WHERE oca.offender_book_id = opp.offender_book_id AND oca.event_date = cs.schedule_date AND oca.crs_sch_id = cs.crs_sch_id)) AND cs.schedule_date > tag_schedule_schedule_date()
UNION ALL
 SELECT NULL::bigint AS event_id,
    opp.offender_book_id,
    substr(
        CASE
            WHEN rc.parent_code::text = 'BOTH'::text THEN tag_prg_prg_event_class(opp.off_prgref_id)
            ELSE rc.parent_code
        END::text, 1, 12) AS event_class,
    substr(COALESCE(ps.program_category, tag_prg_prg_event_type(ps.program_id))::text, 1, 12) AS event_type,
    ca.course_activity_type AS event_sub_type,
    opp.off_prgref_id,
    voca.crs_appt_id AS reference_id,
    NULL::bigint AS crs_sch_id,
    voca.crs_appt_id,
    opp.program_id,
    ca.code AS course_code,
    ca.description,
    opp.crs_acty_id,
    to_char(voca.schedule_date, 'DY'::text) AS weekday,
    voca.schedule_date AS event_date,
    voca.start_time,
    voca.end_time,
        CASE
            WHEN rc.parent_code::text = 'COMM'::text THEN voca.start_time
            ELSE NULL::date
        END AS in_time,
        CASE
            WHEN rc.parent_code::text = 'COMM'::text THEN voca.end_time
            ELSE NULL::date
        END AS out_time,
        CASE
            WHEN opp.suspended_flag::text = 'N'::text THEN 'SCH'::text
            ELSE 'CANC'::text
        END AS event_status,
    NULL::text AS event_outcome,
    opp.agy_loc_id,
    ca.internal_location_id AS to_internal_location_id,
    NULL::text AS comment_text,
    NULL::text AS outcome_reason_code,
    NULL::bigint AS piece_work,
    NULL::text AS engagement_code,
    NULL::text AS understanding_code,
    NULL::bigint AS credited_hours,
    NULL::numeric AS agreed_travel_hour,
    NULL::text AS behaviour_code,
    NULL::text AS action_code,
    NULL::date AS sick_note_received_date,
    NULL::date AS sick_note_expiry_date,
    NULL::character varying AS performance_code,
    addr.owner_code AS to_agy_loc_id,
    ca.services_address_id AS to_address_id,
    NULL::text AS performance_desc,
    NULL::text AS event_outcome_desc,
    NULL::bigint AS supervisor_staff_id,
    NULL::text AS unexcused_absence_flag,
        CASE
            WHEN
            CASE
                WHEN rc.parent_code::text = 'BOTH'::text THEN tag_prg_prg_event_class(opp.off_prgref_id)
                ELSE rc.parent_code
            END::text = 'EXT_MOV'::text THEN 'OUT'::text
            ELSE NULL::text
        END AS direction_code,
    NULL::timestamp without time zone AS ext_move_out_time,
    NULL::timestamp without time zone AS ext_move_in_time,
        CASE
            WHEN
            CASE
                WHEN rc.parent_code::text = 'BOTH'::text THEN tag_prg_prg_event_class(opp.off_prgref_id)
                ELSE rc.parent_code
            END::text = 'EXT_MOV'::text THEN voca.start_time
            ELSE NULL::date
        END AS schedule_movement_time,
    NULL::integer AS session_no,
    'V_CRS_APPT'::text AS record_source,
    0 AS check_sum
   FROM v_offender_course_appts_2 voca,
    reference_codes rc,
    program_services ps,
    offender_program_profiles opp,
    offender_bookings bkg,
    course_activities ca
     LEFT JOIN addresses addr ON ca.services_address_id = addr.address_id
  WHERE voca.off_prgref_id = opp.off_prgref_id AND voca.schedule_date > tag_schedule_schedule_date() AND opp.offender_program_status::text = 'ALLOC'::text AND voca.schedule_date >= opp.offender_start_date AND voca.schedule_date <= COALESCE(COALESCE(opp.offender_end_date, ca.schedule_end_date), to_date('30001231'::text, 'YYYYMMDD'::text)::timestamp without time zone) AND ca.crs_acty_id = opp.crs_acty_id AND ca.course_activity_type IS NOT NULL AND ca.course_activity_type::text <> ''::text AND ca.program_id = ps.program_id AND rc.domain::text = 'PS_CATEGORY'::text AND (rc.parent_code::text = ANY (ARRAY['COMM'::character varying, 'INT_MOV'::character varying, 'BOTH'::character varying, 'EXT_MOV'::character varying]::text[])) AND rc.code::text = COALESCE(ps.program_category, tag_prg_prg_event_type(ps.program_id))::text AND bkg.offender_book_id = opp.offender_book_id AND NOT (EXISTS ( SELECT 'x'::text
           FROM offender_course_attendances oca
          WHERE oca.offender_book_id = opp.offender_book_id AND oca.event_date = voca.schedule_date AND oca.crs_appt_id = voca.crs_appt_id))
UNION ALL
 SELECT oca.event_id,
    oca.offender_book_id,
    oca.event_class,
    oca.event_type,
    oca.event_sub_type,
    oca.off_prgref_id,
    oca.reference_id,
    oca.crs_sch_id,
    oca.crs_appt_id,
    opp.program_id,
    ca.code AS course_code,
    ca.description,
    oca.crs_acty_id,
    to_char(oca.event_date, 'DY'::text) AS weekday,
    oca.event_date,
    oca.start_time,
    oca.end_time,
    oca.in_time,
    oca.out_time,
    oca.event_status,
    oca.event_outcome,
    oca.agy_loc_id,
    oca.to_internal_location_id,
    oca.comment_text,
    oca.outcome_reason_code,
    oca.piece_work,
    oca.engagement_code,
    oca.understanding_code,
    oca.credited_hours,
    oca.agreed_travel_hour,
    oca.behaviour_code,
    oca.action_code,
    oca.sick_note_received_date,
    oca.sick_note_expiry_date,
    oca.performance_code,
    oca.to_agy_loc_id,
    oca.to_address_id,
    substr(oms_miscellaneous_getdesccode('PERFORMANCE'::character varying, oca.performance_code)::text, 1, 40) AS performance_desc,
    substr(oms_miscellaneous_getdesccode('OUTCOMES'::character varying, oca.event_outcome)::text, 1, 40) AS event_outcome_desc,
    oca.supervisor_staff_id,
    oca.unexcused_absence_flag,
    oca.direction_code,
        CASE
            WHEN oca.event_class::text = 'EXT_MOV'::text THEN tag_prg_external_movement_time(oca.event_id, 'OUT'::character varying)
            ELSE NULL::timestamp without time zone
        END AS ext_move_out_time,
        CASE
            WHEN oca.event_class::text = 'EXT_MOV'::text THEN tag_prg_external_movement_time(oca.event_id, 'IN'::character varying)
            ELSE NULL::timestamp without time zone
        END AS ext_move_in_time,
        CASE
            WHEN oca.direction_code::text = 'OUT'::text THEN oca.start_time
            WHEN oca.direction_code::text = 'IN'::text THEN oca.end_time
            ELSE NULL::timestamp without time zone
        END AS schedule_movement_time,
    oca.session_no,
    'CRS_ATT'::text AS record_source,
    tag_schedule_check_sum(COALESCE(oca.modify_datetime, oca.create_datetime)) AS check_sum
   FROM offender_bookings bkg,
    offender_course_attendances oca
     LEFT JOIN offender_program_profiles opp ON oca.off_prgref_id = opp.off_prgref_id
     LEFT JOIN course_activities ca ON opp.crs_acty_id = ca.crs_acty_id
  WHERE oca.offender_book_id = bkg.offender_book_id AND oca.event_status::text <> 'DEL'::text;

CREATE OR REPLACE VIEW agy_placements_v1 (program_code, description, cell) AS SELECT
/* ========================================================
     Version Number =   Date Modified =
     ========================================================*/
D.PROGRAM_CODE, C.DESCRIPTION,COUNT(*) CELL
      FROM agency_locations c
LEFT OUTER JOIN v_tag_header_block b ON (C.AGY_LOC_ID = B.AGY_LOC_ID)
, program_services d
LEFT OUTER JOIN offender_program_profiles a ON (D.PROGRAM_ID = A.PROGRAM_ID)
WHERE D.PROGRAM_ID IN (1,2,3,4,5,7,8,9,33,54,53)  AND A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID AND (coalesce(A.OFFENDER_END_DATE::text, '') = ''
		OR A.OFFENDER_END_DATE > date_trunc('day', LOCALTIMESTAMP)) AND C.AGY_LOC_ID LIKE '13%'  AND B.ACTIVE_FLAG = 'Y' GROUP BY D.PROGRAM_CODE, C.DESCRIPTION

 
;

CREATE OR REPLACE VIEW v_transfer_waiting_lists_2 (offender_book_id, offender_id_display, last_name, event_id, agency_location_to, active_flag, requested_date, wait_list_status, transfer_priority, outcome_reason_code, approved_flag, check_sum) AS SELECT
/* =========================================================
   Version Number = 2.3       Date Modified = 09-Apr-2013
   ========================================================= */
/* MODIFICATION HISTORY
   Person         Date          Version         Comments
   Girish        22-FEB-2001    4.11.0.0         02/22/2001 Added OFFENDER_ID_DISPLAY column to the view
   David Ng      03-JAN-2006    2.0              rename version no
   David Ng      03-Feb-2006    2.1              Map to_agy_loc_ID to agency_location_to
   Manjul        23-May-2012    2.2              Added column OUTCOME_REASON_CODE
   Jason Xu      09-Apr-2013    2.3              copy from V_TRANSFER_WAITING_LISTS 2.2 for SD form
                                                 OIIWLTWJ performance issue
*/
     SCH.OFFENDER_BOOK_ID
    ,OFF.OFFENDER_ID_DISPLAY
    ,OFF.LAST_NAME
    ,WL.Event_id
    ,SCH.TO_AGY_LOC_ID
    ,SCH.ACTIVE_FLAG
    ,WL.REQUEST_DATE
    ,WL.WAIT_LIST_STATUS
    ,WL.TRANSFER_PRIORITY
   ,WL.OUTCOME_REASON_CODE  -- Added by Manjul, HPQC# 15616(Product)/15615(Ontario)
    ,WL.APPROVED_FLAG
   ,Tag_Schedule_check_sum(coalesce(WL.MODIFY_DATETIME, WL.CREATE_DATETIME))
FROM v_offender_All_schedules_4  SCH,
     OFFENDER_IND_SCH_WAIT_LISTS WL,
     OFFENDERS OFF,
     OFFENDER_BOOKINGS BKG
WHERE SCH.Event_id = WL.Event_id
  AND SCH.offender_book_id = BKG.offender_book_id
  AND BKG.offender_id = OFF.offender_id

;

CREATE OR REPLACE VIEW v_offender_all_schedules_2 (event_id, offender_book_id, agy_loc_id, event_date, start_time, end_time, event_class, event_type, event_sub_type, event_status, event_outcome, confirm_flag, outcome_reason_code, comment_text, reference_id, application_date, application_time, return_date, return_time, to_agy_loc_id, escort_code, direction_code, schedule_movement_time, to_internal_location_id, from_city_code, to_city_code, credited_hours, piece_work, engagement_code, understanding_code, details, unpaid_work_behaviour, unpaid_work_action, sick_note_received_date, sick_note_expiry_date, unexcused_absence_flag, in_time, out_time, transport_code, performance_code, agreed_travel_hour, check_box_1, check_box_2, hidden_comment_text, in_charge_staff_id, off_prgref_id, contact_person_name, to_address_owner_class, to_address_id, to_corporate_id, unpaid_work_supervisor, ta_id, record_source, check_sum, prov_state_code, scheduled_trip_id) AS SELECT
/* MODIFICATION HISTORY
   Person        Date              Version        Comments
   ---------    ------          ---------     ------------------------------------------------------------------------------
   David Ng     15/10/2005       2.0            NOMIS project(10.2.0)
   David Ng     17/01/2006       2.1            Add off_Prgref_ID column value
   David Ng     24/01/2006       2.2            Using TAG_Schedule check sum function
   David Ng     08/02/2006       2.3            Event Class of CRT court event depend on
                                                          In_Out_Status and Active_Flag
   David Ng     15/02/2006       2.4            Add Offender Visit and V_Offender_Course_Events
                                                          Drop out Offender_schedule_outcomes
   David Ng     07/07/2006       2.5            Use new Offender_Visit_schedules
   David Ng     08/08/2006       2.6            Add To_Agy_Loc_ID from V_offender_course_events
   Niko           MAR-13-2007    2.6.1.0       Added a new column - prov_state_code
                                                          Removed 2 columns - TO_COUNTRY_CODE and OJ_LOCATION_CODE
   Sarah        18-Mar-2008      2.6.1.1          Modified the direction code and eventclass for court movement
   Farhad       23-JULY-2009     2.6.1.2        Add a new column - scheduled_trip_id
   David Ng     03-SEP-2009      2.6.1.3          Give direction code for External Movement for v_offender_Course_events.
   Abu          13-Nov-2009      2.6.1.4        Modified to fix From Agy_Loc_ID in Court_Events and TAP
                                                                    (Offender_ind_schedules) sections
*/
EVENT_ID
,OFFENDER_BOOK_ID
,CASE WHEN coalesce(agy_loc_id::text, '') = '' THEN  CASE WHEN coalesce(parent_event_id::text, '') = '' THEN  agy_loc_id  ELSE CASE WHEN direction_code='IN' THEN (SELECT   schx.to_agy_loc_id                                              FROM   offender_ind_schedules schx                                             WHERE   schx.event_id = sch.parent_event_id                                               AND schx.offender_book_id = sch.offender_book_id                                            )  ELSE agy_loc_id END  END   ELSE agy_loc_id END  agy_loc_id
,EVENT_DATE
,START_TIME
,END_TIME
,EVENT_CLASS
,EVENT_TYPE
,event_sub_type
,EVENT_STATUS
,event_outcome
,confirm_flag
,outcome_reason_code
,COMMENT_text
,REFERENCE_ID
,APPLICATION_DATE
,APPLICATION_TIME
,RETURN_DATE
,RETURN_TIME
,TO_AGY_LOC_ID
,ESCORT_CODE
,direction_code
,CASE WHEN direction_code='OUT' THEN  start_time WHEN direction_code='IN' THEN  End_time  ELSE NULL END  -- schedule_movement_time,
,To_Internal_location_ID
,From_City_Code
,To_City_Code
,CREDITED_HOURS
,piece_work
,engagement_code
,understanding_code
,Details
,unpaid_work_behaviour
,unpaid_work_action
,sick_note_received_date
,sick_note_expiry_date
,unexcused_absence_flag
,IN_TIME
,OUT_TIME
,TRANSPORT_CODE
,PERFORMANCE_CODE
--,OJ_LOCATION_CODE
--,TO_COUNTRY_CODE
,AGREED_TRAVEL_HOUR
,CHECK_BOX_1
,CHECK_BOX_2
,HIDDEN_COMMENT_TEXT
,IN_CHARGE_STAFF_ID
,OFF_PRGREF_ID
,contact_person_name
,TO_address_owner_class
,TO_address_ID
,TO_CORPORATE_ID
,UNPAID_WORK_SUPERVISOR
,TA_ID
,'SCH'
,Tag_Schedule_check_sum(coalesce(MODIFY_DATETIME, CREATE_DATETIME))
,PROV_STATE_CODE
,scheduled_trip_id
FROM OFFENDER_IND_SCHEDULES sch
WHERE EVENT_STATUS <> 'DEL'

UNION ALL

SELECT
   event_id,
   offender_book_id,
   AGY_LOC_ID,     -- agy_loc_id,
   event_date,
   start_time,
   end_time,
   event_class,
   event_type,
   event_sub_type,
   event_status,
   event_outcome,
   NULL,     --confirm_flag,
   outcome_reason_code,
   comment_text,
   reference_id,
   NULL,     -- application_date,
   NULL,     -- application_time,
   NULL,     -- return_date,
   NULL,     -- return_time,
   To_Agy_Loc_ID,  -- to_agy_loc_id,
   NULL,     -- escort_code,
   direction_code,     -- direction_code,
   schedule_movement_time, -- schedule_movement_time
   to_internal_location_id,
   NULL,     -- from_city_code,
   NULL,     -- to_city_code,
   credited_hours,
   piece_work,
   engagement_code,
   understanding_code,
   NULL,     -- details,
   Behaviour_code,  -- unpaid_work_behaviour,
   Action_Code,   -- unpaid_work_action,
   sick_note_received_date,
   sick_note_expiry_date,
   unexcused_absence_flag, -- unexcused_absence_flag,
   in_time,
   out_time,
   NULL,     -- transport_code,
   performance_code,
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
   agreed_travel_hour,
   NULL,     -- check_box_1,
   NULL,     -- check_box_2,
   NULL,     -- hidden_comment_text,
   SUPERVISOR_STAFF_ID, -- in charge Staff ID
   off_prgref_id,
   NULL,     -- contact_person_name,
   NULL,     -- to_address_owner_class,
   to_address_id,
   NULL,     -- to_corporate_id,
   NULL,     -- unpaid_work_supervisor,
   NULL,     -- ta_id,
   'V_OFF_CRS'
   ,check_sum
   ,NULL
   ,NULL
FROM v_offender_course_events

UNION ALL

SELECT
ce.event_id
,ce.offender_book_id
,CASE WHEN ce.direction_code='OUT' THEN  bkg.agy_loc_id  ELSE (SELECT   cex.agy_loc_id                   FROM   court_events cex                  WHERE   cex.event_id = ce.parent_event_id                ) END  agy_loc_id
,ce.event_date
,ce.start_time
,ce.end_time
,CASE WHEN bkg.in_out_status='IN' THEN  CASE WHEN bkg.active_flag='Y' THEN  'EXT_MOV'  ELSE 'COMM' END  WHEN bkg.in_out_status='OUT' THEN  CASE WHEN bkg.active_flag='Y' THEN  'EXT_MOV'  ELSE 'COMM' END   ELSE 'COMM' END
,'CRT'
,ce.court_event_type
,CASE WHEN coalesce(ce.event_status::text, '') = '' THEN  'SCH'  ELSE ce.event_status END 
,ce.event_outcome
,CASE WHEN ce.event_status='CONF' THEN  'Y'  ELSE 'N' END 
,OUTCOME_REASON_CODE
,ce.comment_text
,ce.case_id          -- Reference ID
,NULL
,NULL
,NULL
,NULL
,ce.agy_loc_id
,NULL
,ce.direction_code
,ce.start_time --   schedule_movement_time,
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,'COURT'
,Tag_Schedule_check_sum(coalesce(CE.MODIFY_DATETIME, CE.CREATE_DATETIME))
,NULL
,ce.scheduled_trip_id
FROM  COURT_EVENTS ce, OFFENDER_BOOKINGS bkg
WHERE ce.offender_book_id = bkg.offender_book_id

UNION ALL

SELECT
NULL
,OFFENDER_BOOK_ID
,agy_loc_id
,OUT_DATE
,OUT_TIME
,NULL
,'EXT_MOV'
,EVENT_TYPE
,EVENT_SUB_TYPE
,'SCH'
,NULL
,NULL
,NULL
,NULL
,REFERENCE_ID
,NULL
,NULL
,IN_DATE
,IN_TIME
,TO_AGY_LOC_ID
,ESCORT_CODE
,'OUT'
,NULL --   schedule_movement_time,
,NULL
,NULL
,CITY_CODE
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,NULL
,TA_ID
,'V_TEMP_ABS'
,0
,NULL
-- ,owa_opt_lock.checksum(to_char(Offender_book_ID)||to_char(REFERENCE_ID))
,NULL
FROM V_OFFENDER_TA_SCHEDULES

UNION ALL

SELECT
   event_id,
   offender_book_id,
   agy_loc_id,
   visit_date,    -- event_date,
   start_time,
   end_time,
   'INT_MOV',     -- event_class,
   'VISIT',     -- event_type,
   'VISIT',     -- event_sub_type,
   Event_status,    -- event_status,
   event_outcome,     -- event_outcome,
   NULL,     -- confirm_flag,
   outcome_reason_code,
   comment_text,
   NULL,          -- reference_id,
   NULL,     -- application_date,
   NULL,     -- application_time,
   NULL,     -- return_date,
   NULL,     -- return_time,
   AGY_LOC_ID,     -- to_agy_loc_id,
   NULL,     -- escort_code,
   NULL,     -- direction_code,
   NULL,     -- schedule_movement_time,
   Visit_Internal_location_ID, -- to_internal_location_id,
   NULL,     -- from_city_code,
   NULL,     -- to_city_code,
   NULL,     -- credited_hours,
   NULL,     -- piece_work,
   NULL,     -- engagement_code,
   NULL,     -- understanding_code,
   NULL,     -- details,
   NULL,     -- unpaid_work_behaviour,
   NULL,     -- unpaid_work_action,
   NULL,     -- sick_note_received_date,
   NULL,     -- sick_note_expiry_date,
   NULL,     -- unexcused_absence_flag,
   Start_Time,     -- in_time,
   End_time,     -- out_time,
   NULL,     -- transport_code,
   NULL,     -- performance_code,
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
   NULL,     -- agreed_travel_hour,
   NULL,     -- check_box_1,
   NULL,     -- check_box_2,
   NULL,     -- hidden_comment_text,
   NULL, -- Authorised_Staff_ID, -- in_charge_staff_id,
   NULL,     -- off_prgref_id,
   NULL,     -- contact_person_name,
   NULL,     -- to_address_owner_class,
   NULL,     -- to_address_id,
   NULL,     -- to_corporate_id,
   NULL,     -- unpaid_work_supervisor,
   NULL,     -- ta_id,
   'OFF_VIS'    -- record_source,
 ,Check_SUM            -- check_sum
 ,NULL
 ,NULL
FROM  v_Offender_visit_schedules OV
WHERE (Event_ID IS NOT NULL AND Event_ID::text <> '') AND (Offender_Book_ID IS NOT NULL AND Offender_Book_ID::text <> '')

UNION ALL

SELECT
   OH.event_id,
   AIP.offender_book_id,
   AIL.agy_loc_id,
   OH.HEARING_date,     --event_date,
   OH.HEARING_time,     -- start_time,
   NULL,       -- end_time,
   'INT_MOV',       -- event_class,
   'OIC',       -- event_type,
   'OIC',       -- event_sub_type,
   OH.Event_Status,     -- event_status,
   NULL,       -- event_outcome,
   NULL,       -- confirm_flag,
   NULL,       -- outcome_reason_code,
   OH.comment_text,
   OH.OIC_Hearing_ID,    -- reference_id,
   NULL,       -- application_date,
   NULL,       -- application_time,
   NULL,       -- return_date,
   NULL,       -- return_time,
   AIL.Agy_Loc_ID,    -- to_agy_loc_id,
   NULL,       -- escort_code,
   NULL,       -- direction_code,
   NULL,       -- schedule_movement_time,
   OH.internal_location_id,
   NULL,      -- from_city_code,
   NULL,       -- to_city_code,
   NULL,       -- credited_hours,
   NULL,       -- piece_work,
   NULL,       -- engagement_code,
   NULL,       -- understanding_code,
   NULL,       -- details,
   NULL,       -- unpaid_work_behaviour,
   NULL,       -- unpaid_work_action,
   NULL,       -- sick_note_received_date,
   NULL,       -- sick_note_expiry_date,
   NULL,       -- unexcused_absence_flag,
   OH.Hearing_Time,     -- in_time,
   NULL,       -- out_time,
   NULL,       -- transport_code,
   NULL,       -- performance_code,
--   NULL,       -- oj_location_code,
--   NULL,       -- to_country_code,
   NULL,       -- agreed_travel_hour,
   NULL,       -- check_box_1,
   NULL,      -- check_box_2,
   NULL,       -- hidden_comment_text,
   OH.Hearing_Staff_ID,       -- in_charge_staff_id,
   NULL,       -- off_prgref_id,
   NULL,       -- contact_person_name,
   NULL,       -- to_address_owner_class,
   NULL,       -- to_address_id,
   NULL,       -- to_corporate_id,
   NULL,       -- unpaid_work_supervisor,
   NULL,       -- ta_id,
   'OIC_HEARING'
  ,Tag_Schedule_check_sum(coalesce(OH.MODIFY_DATETIME, OH.CREATE_DATETIME)) check_sum
 ,NULL
 ,NULL
FROM agency_incident_parties aip, oic_hearings oh
LEFT OUTER JOIN agency_internal_locations ail ON (OH.Internal_Location_ID = AIL.Internal_location_id)
WHERE OH.OIC_Incident_ID = AIP.OIC_Incident_ID AND (OH.Hearing_date IS NOT NULL AND OH.Hearing_date::text <> '')  AND (AIP.Offender_Book_ID IS NOT NULL AND AIP.Offender_Book_ID::text <> '')
 
UNION ALL

SELECT
   ORD.event_id,
   ORD.offender_book_id,
   BKG.agy_loc_id,
   ORD.Release_date,  -- EVENT_DATE
   NULL,              -- start_time,
   NULL,              -- end_time,
   'EXT_MOV',         -- event_class,
   ORD.Movement_Type,  -- event_type,
   ORD.MOvement_Reason_Code, -- event_sub_type,
   ORD.event_status,
   NULL,     -- event_outcome,
   NULL,     -- confirm_flag,
   NULL,     -- outcome_reason_code,
   ORD.comment_text,
   NULL,     -- reference_id,
   NULL,      -- application_date,
   NULL,     -- application_time,
   NULL,     -- return_date,
   NULL,     -- return_time,
   NULL,     -- to_agy_loc_id,
   NULL,     -- escort_code,
   NULL,     -- direction_code,
   NULL,     -- schedule_movement_time,
   NULL,     -- to_internal_location_id,
   NULL,     -- from_city_code,
   NULL,     -- to_city_code,
   NULL,     -- credited_hours,
   NULL,     -- piece_work,
   NULL,     -- engagement_code,
   NULL,     -- understanding_code,
   NULL,     -- details,
   NULL,     -- unpaid_work_behaviour,
   NULL,     -- unpaid_work_action,
   NULL,     -- sick_note_received_date,
   NULL,     -- sick_note_expiry_date,
   NULL,     -- unexcused_absence_flag,
   NULL,     -- in_time,
   NULL,     -- out_time,
   NULL,     -- transport_code,
   NULL,     -- performance_code,
--   NULL,     -- oj_location_code,
--   NULL,     -- to_country_code,
   NULL,     -- agreed_travel_hour,
   NULL,     -- check_box_1,
   NULL,     -- check_box_2,
   NULL,     -- hidden_comment_text,
   NULL,     -- in_charge_staff_id,
   NULL,     -- off_prgref_id,
   NULL,     -- contact_person_name,
   NULL,     -- to_address_owner_class,
   NULL,     -- to_address_id,
   NULL,     -- to_corporate_id,
   NULL,     -- unpaid_work_supervisor,
   NULL,     -- ta_id,
   'OFF_REL'   -- record_source,
   ,Tag_Schedule_check_sum(coalesce(ORD.MODIFY_DATETIME, ORD.CREATE_DATETIME)) check_sum
   ,NULL
   ,NULL
FROM  OFFENDER_RELEASE_DETAILS ORD, OFFENDER_BOOKINGS BKG
WHERE ORD.OFFENDER_BOOK_ID = BKG.OFFENDER_BOOK_ID
 
;

CREATE OR REPLACE VIEW v_reporting_locations (agy_loc_id, count_type_id, count_type_code, scheduled_time, agy_seq, location_type, location1_id, location2_id, location3_id, list_seq, reporting_loc_id, date_submitted, actual_count, reported_count, conducted_by_userid, entered_by_userid, conducted_datetime, recount_rsn_code, discrep_rsn_code, rsn_code_userid, rsn_code_datetime, verified_user_id, verified_datetime, rcnt_conducted_by, rcnt_datetime, rcnt_in_progress_flag, recount_total, reported_total, location_description) AS SELECT
/* =========================================================
   Version Number = 1.0 Date Modified = 09/01/2008
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date      Version        Comments
   ---------    ------     ---------     ------------------------------
   NIKO         09/10/2008  1.0          Modified the view due to data model changes
                                         Replaced table agency_internal_mvmt_locations with v_int_loc_summaries vil
*/
act.AGY_LOC_ID
,act.COUNT_TYPE_ID
,act.COUNT_TYPE_CODE
,act.SCHEDULED_TIME
,ARL.AGY_SEQ
,ARL.LOCATION_TYPE
,ARL.LOCATION1_ID
,ARL.LOCATION2_ID
,ARL.LOCATION3_ID
,ARL.LIST_SEQ
,ALC.REPORTING_LOC_ID
,ALC.DATE_SUBMITTED
,ALC.ACTUAL_COUNT
,ALC.REPORTED_COUNT
,ALC.CONDUCTED_BY_USERID
,ALC.ENTERED_BY_USERID
,ALC.CONDUCTED_DATETIME
,ALC.RECOUNT_RSN_CODE
,ALC.DISCREP_RSN_CODE
,ALC.RSN_CODE_USERID
,ALC.RSN_CODE_DATETIME
,ALC.VERIFIED_USER_ID
,ALC.VERIFIED_DATETIME
,ALC.RCNT_CONDUCTED_BY
,ALC.RCNT_DATETIME
,ALC.RCNT_IN_PROGRESS_FLAG
,ALC.RECOUNT_TOTAL
,CASE WHEN coalesce(ALC.RECOUNT_TOTAL::text, '') = '' THEN ALC.REPORTED_COUNT  ELSE ALC.RECOUNT_TOTAL END  REPORTED_TOTAL
,CASE WHEN ARL.LOCATION_TYPE='INIT' THEN (SELECT vil.internal_location_desc                                     FROM v_int_loc_summaries vil                                    WHERE vil.internal_location_id  = arl.location1_id  LIMIT 1)  ELSE (CASE WHEN coalesce(ARL.LOCATION3_ID::text, '') = '' THEN (CASE WHEN coalesce(ARL.LOCATION2_ID::text, '') = '' THEN (SELECT description                                                         FROM LIVING_UNITS                                                        WHERE LIVING_UNIT_ID = ARL.LOCATION1_ID                                                       )  ELSE (SELECT description                                                         FROM LIVING_UNITS                                                        WHERE LIVING_UNIT_ID = ARL.LOCATION2_ID                                                       ) END                           )  ELSE (SELECT description                        FROM LIVING_UNITS                       WHERE LIVING_UNIT_ID = ARL.LOCATION3_ID                      ) END              ) END  LOCATION_DESCRIPTION
FROM AGENCY_LOCATION_COUNTS ALC,AGENCY_REPORTING_LOCS ARL,agency_count_types act
WHERE ARL.COUNT_TYPE_ID = ALC.COUNT_TYPE_ID
and ARL.COUNT_TYPE_ID = act.COUNT_TYPE_ID
AND ARL.AGY_SEQ = ALC.AGY_SEQ

;

CREATE OR REPLACE VIEW agy_placements_v2 (program_code, description, cell) AS SELECT A.PROGRAM_CODE, B.DESCRIPTION, coalesce( B.CELL, 0 ) CELL
      FROM program_services a
LEFT OUTER JOIN agy_placements_v1 b ON (A.PROGRAM_CODE = B.PROGRAM_CODE)
WHERE A.PROGRAM_ID IN ( 1,2,3,4,5,7,8,9,33,54,53 );

CREATE OR REPLACE VIEW v_offender_all_schedules (event_id, offender_book_id, in_out_status, booking_no, booking_active_flag, offender_id, offender_id_display, offender_last_name, offender_first_name, event_date, start_time, end_time, event_class, event_type, event_type_desc, event_sub_type, event_sub_type_desc, active_flag, event_status, event_status_desc, event_outcome, event_outcome_desc, busy_date_flag, outcome_reason_code, reference_id, application_date, application_time, return_date, return_time, comment_text, details, agy_loc_id, agy_loc_desc, living_unit_id, living_unit_desc, lu_level_1_code, lu_level_2_code, lu_level_3_code, lu_level_4_code, agency_iml_id, agency_iml_desc, agency_iml_level_1_code, agency_iml_level_2_code, agency_iml_level_3_code, to_agy_loc_id, to_agy_loc_desc, to_loc, to_loc_desc, escort_code, escort_desc, direction_code, schedule_movement_time, from_city_code, from_city_name, to_city_code, to_city_name, to_internal_location_id, to_internal_location_desc, to_int_loc_level_1_code, to_int_loc_level_2_code, to_int_loc_level_3_code, to_int_loc_user_desc, credited_hours, engagement_code, understanding_code, piece_work, in_time, out_time, performance_code, transport_code, sick_note_expiry_date, sick_note_received_date, unexcused_absence_flag, unpaid_work_action, unpaid_work_behaviour, agreed_travel_hour, check_box_1, check_box_2, hidden_comment_text, in_charge_staff_id, in_charge_staff_name, off_prgref_id, contact_person_name, to_address_owner_class, to_address_id, unpaid_work_supervisor, ta_id, record_source, check_sum, prov_state_code, prov_state_desc, scheduled_trip_id) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
----------------------------------------------------------------------------------------------------------------------------------------------------
   David Ng     15/10/2005  2.0          NOMIS project(10.2.0)
   David Ng     11/01/2006  2.1          Court event reason based on MOVE_RSN Domain
   David Ng     13/01/2006  2.2          Add To_locaiton And description
   David Ng     13/01/2006  2.3          Add In_Out_Status
   David Ng     24/01/2006  2.4          Remove NSI_Flag
   Niko               MAR-13-2007   2.6.1.0     Added a new column - prov_state_code
                                                                          Removed 2 columns - TO_COUNTRY_CODE and OJ_LOCATION_CODE
   Farhad       23-JULY-2009 2.6.1.1     Added a new column - scheduled_trip_id
   David Ng     12/09/2009  2.6.1.2      Add Direction_code and schedule_movement_time for external  movements
*/
          sch.event_id, sch.offender_book_id, bkg.in_out_status, bkg.booking_no, bkg.active_flag,
          bkg.offender_id, OFF.offender_id_display, OFF.last_name,
          OFF.first_name, sch.event_date, sch.start_time, sch.end_time,
          sch.event_class, sch.event_type,
          SUBSTR(Tag_Schedule_event_type_desc(sch.event_class, sch.event_type), 1, 40 ),
          sch.event_sub_type,
          SUBSTR(Tag_Schedule_event_sub_type_desc(sch.event_class,  sch.event_type, sch.event_sub_type ), 1, 40 ),
          CASE WHEN event_status='SCH' THEN  'Y'  ELSE 'N' END , event_status,
          SUBSTR(Oms_Miscellaneous_getdesccode('EVENT_STS',  sch.event_status), 1, 40 ),
          event_outcome,
          SUBSTR(Oms_Miscellaneous_getdesccode('OUTCOMES', sch.event_outcome),1, 40 ),
          SUBSTR(Tag_Schedule_busy_date_flag(sch.offender_book_id, sch.event_date ),1,1),
          sch.outcome_reason_code, sch.reference_id,
          sch.application_date, sch.application_time, sch.return_date,
          sch.return_time, sch.comment_text, sch.details, sch.agy_loc_id,
          agy.description, bkg.living_unit_id, lu.description,
          lu.level_1_code, lu.level_2_code, lu.level_3_code, lu.level_4_code,
          bkg.agency_iml_id, ail2.description,
          SUBSTR(Tag_Int_Loc_level_code(ail2.description, 1), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail2.description, 2), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail2.description, 3), 1, 40),
          sch.to_agy_loc_id, agy2.description,
          --NVL(sch.to_agy_loc_id, OJ_LOCATION_code),
		  sch.to_agy_loc_id,
          --NVL(agy2.description, SUBSTR (Oms_Miscellaneous.getdesccode ('OJ_LOCATION', oj_location_code),1,40 )),
		  agy2.description,
          sch.escort_code,
          SUBSTR(Oms_Miscellaneous_getdesccode('ESCORT', sch.escort_code), 1, 40 ),
          sch.direction_code, sch.schedule_movement_time, sch.from_city_code,
          SUBSTR(Oms_Miscellaneous_getdesccode('CITY', sch.from_city_code), 1, 40 ),
          sch.to_city_code,
          SUBSTR(Oms_Miscellaneous_getdesccode('CITY', sch.to_city_code), 1, 40),
          sch.to_internal_location_id, ail.description,
          SUBSTR(Tag_Int_Loc_level_code(ail.description, 1), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail.description, 2), 1, 40),
          SUBSTR(Tag_Int_Loc_level_code(ail.description, 3), 1, 40),
          ail.user_desc, sch.credited_hours, sch.engagement_code,
          sch.understanding_code, sch.piece_work, sch.in_time, sch.out_time,
          sch.performance_code, sch.transport_code,
--		  sch.oj_location_code,
--          SUBSTR (Oms_Miscellaneous.getdesccode ('OJ_LOCATION', oj_location_code ), 1,  40 ),
--          sch.to_country_code,
--          SUBSTR (Oms_Miscellaneous.getdesccode ('COUNTRY', to_country_code), 1, 40  ),
          sch.sick_note_expiry_date, sch.sick_note_received_date,
          sch.unexcused_absence_flag, sch.unpaid_work_action,
          sch.unpaid_work_behaviour, sch.agreed_travel_hour, sch.check_box_1,
          sch.check_box_2, sch.hidden_comment_text, sch.in_charge_staff_id,
          SUBSTR(CASE WHEN coalesce(in_charge_staff_id::text, '') = '' THEN  ' '  ELSE (stf.last_name || ', ' || stf.first_name  ) END , 1, 40 ),
          sch.off_prgref_id, sch.contact_person_name,
          sch.to_address_owner_class, sch.to_address_id,
          sch.unpaid_work_supervisor, sch.ta_id, sch.record_source
         ,sch.check_sum
		,prov_state_code
		,SUBSTR(Oms_Miscellaneous_getdesccode('PROV_STATE', prov_state_code ), 1,  40 ) prov_state_desc
         ,sch.scheduled_trip_id
     FROM offenders off, v_offender_all_schedules_2 sch
LEFT OUTER JOIN agency_locations agy ON (sch.agy_loc_id = agy.agy_loc_id)
LEFT OUTER JOIN agency_locations agy2 ON (sch.to_agy_loc_id = agy2.agy_loc_id)
LEFT OUTER JOIN agency_internal_locations ail ON (sch.to_internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN staff_members stf ON (sch.in_charge_staff_id = stf.staff_id)
, offender_bookings bkg
LEFT OUTER JOIN agency_internal_locations ail2 ON (bkg.agency_iml_id = ail2.internal_location_id)
LEFT OUTER JOIN living_units lu ON (bkg.living_unit_id = lu.living_unit_id)
WHERE sch.offender_book_id = bkg.offender_book_id AND bkg.offender_id = OFF.offender_id;

CREATE OR REPLACE VIEW v_offender_scheduled_trips (scheduled_trip_id, offender_id_display, offender_book_id, offender_last_name, offender_first_name, from_location_desc, agy_loc_id, to_agy_loc_id, event_date, start_time, event_status, event_type, event_sub_type, offender_id, event_id, record_source) AS SELECT
/* ===================================================================
   Version Number = 1.1 CODCSO  Date Created = 25/08/2009 (dd/mm/yyyy)
   =================================================================== */
/* MODIFICATION HISTORY
   Person     	 Date       	Version     	 Comments
   ---------     ------     	------------  	 ------------------------------
   Meeta         25/08/2009     1.0              Created for Denver Local Transportation
   Meeta	 02/10/2009     1.1              Modified for OIDSLTRI screen
*/
       oas.scheduled_trip_id,
       oas.offender_id_display,
       oas.offender_book_id,
       oas.offender_last_name,
       oas.offender_first_name,
       oas.living_unit_desc from_location_desc,
       oas.agy_loc_id,
       oas.to_agy_loc_id,
       oas.event_date,
       oas.start_time,
       oas.event_status,
       oas.event_type,
       oas.event_sub_type,
       /*(select (leg_id*1000) + leg_seq
          from route_stop_details rsd
         where rsd.route_name = st.route_name
           and rsd.agy_loc_id = oas.agy_loc_id) from_agy_seq,
       (select (leg_id*1000) + leg_seq
          from route_stop_details rsd
         where rsd.route_name = st.route_name
           and rsd.agy_loc_id = oas.to_agy_loc_id) to_agy_seq,*/
        oas.offender_id,
        oas.event_id,
        oas.record_source
  FROM v_offender_all_schedules oas/*,
       scheduled_trips        st*/
 WHERE /*oas.scheduled_trip_id = st.scheduled_trip_id
   AND */
oas.record_source IN ('SCH', 'COURT')
   /*and oas.event_type in ('SCH','PEND')*/

   and (oas.scheduled_trip_id IS NOT NULL AND oas.scheduled_trip_id::text <> '')

;

CREATE OR REPLACE VIEW v_local_trip_occupancy
AS SELECT st.scheduled_trip_id,
    v.route_name,
    v.from_seq,
    v.to_seq,
    v.from_agy_loc_id,
    v.to_agy_loc_id,
    v.segment_length,
    count(*) AS occupancy
   FROM v_route_locations v,
    ( SELECT v_offender_all_schedules.scheduled_trip_id,
            v_offender_all_schedules.agy_loc_id,
            v_offender_all_schedules.to_agy_loc_id
           FROM v_offender_all_schedules
          WHERE (v_offender_all_schedules.record_source = ANY (ARRAY['SCH'::text, 'COURT'::text])) AND (v_offender_all_schedules.event_status::text = ANY (ARRAY['SCH'::character varying, 'PEN'::character varying]::text[])) AND v_offender_all_schedules.scheduled_trip_id IS NOT NULL AND v_offender_all_schedules.scheduled_trip_id::text <> ''::text
        UNION ALL
         SELECT non_admitted_inmate_mvmts.scheduled_trip_id,
            non_admitted_inmate_mvmts.from_agy_loc_id,
            non_admitted_inmate_mvmts.to_agy_loc_id
           FROM non_admitted_inmate_mvmts
          WHERE non_admitted_inmate_mvmts.scheduled_trip_id IS NOT NULL AND non_admitted_inmate_mvmts.scheduled_trip_id::text <> ''::text) sch,
    scheduled_trips st
  WHERE st.scheduled_trip_id = sch.scheduled_trip_id AND st.route_name::text = v.route_name::text AND v.from_seq >= (( SELECT max(lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) AS max
           FROM route_stop_details rsd
          WHERE rsd.agy_loc_id::text = sch.agy_loc_id::text AND rsd.route_name::text = v.route_name::text AND rsd.active_flag::text = 'Y'::text AND (lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) < (( SELECT max(lpad(rsdx.leg_id::character varying::text, 4, '0'::text) || lpad(rsdx.leg_seq::character varying::text, 4, '0'::text)) AS max
                   FROM route_stop_details rsdx
                  WHERE rsdx.route_name::text = v.route_name::text AND rsdx.agy_loc_id::text = sch.to_agy_loc_id::text AND rsdx.active_flag::text = 'Y'::text)))) AND v.from_seq < (( SELECT min(lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) AS min
           FROM route_stop_details rsd
          WHERE rsd.agy_loc_id::text = sch.to_agy_loc_id::text AND rsd.route_name::text = v.route_name::text AND rsd.active_flag::text = 'Y'::text AND (lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) > (( SELECT max(lpad(rsd2.leg_id::character varying::text, 4, '0'::text) || lpad(rsd2.leg_seq::character varying::text, 4, '0'::text)) AS max
                   FROM route_stop_details rsd2
                  WHERE rsd2.agy_loc_id::text = sch.agy_loc_id::text AND rsd2.route_name::text = v.route_name::text AND rsd2.active_flag::text = 'Y'::text AND (lpad(rsd2.leg_id::character varying::text, 4, '0'::text) || lpad(rsd2.leg_seq::character varying::text, 4, '0'::text)) < (( SELECT max(lpad(rsdx.leg_id::character varying::text, 4, '0'::text) || lpad(rsdx.leg_seq::character varying::text, 4, '0'::text)) AS max
                           FROM route_stop_details rsdx
                          WHERE rsdx.route_name::text = v.route_name::text AND rsdx.agy_loc_id::text = sch.to_agy_loc_id::text AND rsdx.active_flag::text = 'Y'::text))))))
  GROUP BY st.scheduled_trip_id, v.route_name, v.from_seq, v.to_seq, v.from_agy_loc_id, v.to_agy_loc_id, v.segment_length;

CREATE OR REPLACE VIEW v_transfer_waiting_lists (offender_book_id, offender_id_display, last_name, event_id, agency_location_to, active_flag, requested_date, wait_list_status, transfer_priority, outcome_reason_code, approved_flag, check_sum) AS SELECT
/* =========================================================
   Version Number = 2.2       Date Modified = 23-May-2012
   ========================================================= */
/* MODIFICATION HISTORY
   Person     	 Date       	Version     	 Comments
   Girish        22-FEB-2001    4.11.0.0         02/22/2001 Added OFFENDER_ID_DISPLAY column to the view
   David Ng      03-JAN-2006    2.0              rename version no
   David Ng      03-Feb-2006    2.1              Map to_agy_loc_ID to agency_location_to
   Manjul        23-May-2012    2.2              Added column OUTCOME_REASON_CODE
*/
     SCH.OFFENDER_BOOK_ID
    ,OFF.OFFENDER_ID_DISPLAY
    ,OFF.LAST_NAME
    ,WL.Event_id
    ,SCH.TO_AGY_LOC_ID
    ,SCH.ACTIVE_FLAG
    ,WL.REQUEST_DATE
    ,WL.WAIT_LIST_STATUS
    ,WL.TRANSFER_PRIORITY
	,WL.OUTCOME_REASON_CODE  -- Added by Manjul, HPQC# 15616(Product)/15615(Ontario)
    ,WL.APPROVED_FLAG
   ,Tag_Schedule_check_sum(coalesce(WL.MODIFY_DATETIME, WL.CREATE_DATETIME))
FROM v_offender_All_schedules  SCH,
     OFFENDER_IND_SCH_WAIT_LISTS WL,
     OFFENDERS OFF,
     OFFENDER_BOOKINGS BKG
WHERE SCH.Event_id = WL.Event_id
  AND SCH.offender_book_id = BKG.offender_book_id
  AND BKG.offender_id = OFF.offender_id


;

CREATE OR REPLACE VIEW v_assign_offender_trips (scheduled_trip_id, event_id, event_status, offender_book_id, root_offender_id, offender_id, sex_code, offender_id_display, offender_last_name, offender_first_name, event_type, event_sub_type, lu_level_1_code, from_location, agy_loc_id, to_agy_loc_id, event_date, start_time, event_class, record_source, from_seq) AS SELECT
/**********************************************************
 Developer     Date        Version      Description
 ----------------------------------------------------------
 Abu          25-Nov-2009  1.3         Housing Location fix
 Abu          30-Sep-2009  1.2         Allow assignment even if sch Event date is different from
                                          Trip Departure Date
 Abu          02-Sep-2009  1.1         Fixes found during unit test.
 Abu          20-Aug-2009  1.0         Denver Local Transportation. View
                                       used in Oidaotst screen. View returns inmate schedule records
                                       that qualify to be assigned to a specific Scheduled Trip.
 */
       st.scheduled_trip_id,
       sch.event_id,
       sch.event_status,
       sch.offender_book_id,
       o.root_offender_id,
       o.offender_id,
       o.sex_code,
       sch.offender_id_display,
       sch.offender_last_name,
       sch.offender_first_name,
       sch.event_type,
       sch.event_sub_type,
       sch.lu_level_1_code,
        CASE WHEN ob.agy_loc_id=sch.agy_loc_id THEN           sch.agy_loc_id || '-'          || lu_level_1_code || CASE WHEN coalesce(lu_level_2_code::text, '') = '' THEN  null  ELSE '-' END           || lu_level_2_code || CASE WHEN coalesce(lu_level_3_code::text, '') = '' THEN  null  ELSE '-' END           || lu_level_3_code || CASE WHEN coalesce(lu_level_4_code::text, '') = '' THEN  null  ELSE '-' END           || lu_level_4_code  ELSE sch.agy_loc_id END  from_location,
       sch.agy_loc_id,
       sch.to_agy_loc_id,
       sch.event_date,
       sch.start_time,
       sch.event_class,
       sch.record_source,
       lpad(rsd_from.leg_id::varchar, 4, '0')
          || lpad(rsd_from.leg_seq::varchar, 4, '0') from_seq
  FROM v_offender_all_schedules sch,
       offenders o,
       offender_bookings ob,
       scheduled_trips        st,
       movement_reasons       mr,
       route_stop_details     rsd_from,
       route_stop_details     rsd_to
 WHERE sch.record_source in ('SCH', 'COURT')
   AND sch.event_class = 'EXT_MOV'
   AND sch.offender_id = o.offender_id
   AND sch.offender_book_id = ob.offender_book_id
   AND sch.event_type = mr.movement_type
   AND sch.event_sub_type = mr.movement_reason_code
   AND mr.transportation_flag = 'Y'
   AND coalesce(sch.scheduled_trip_id::text, '') = ''
   AND coalesce(sch.event_status, ' ') = 'SCH'
   --AND NVL(sch.booking_active_flag, 'Y') = 'Y'
   AND st.route_name = rsd_from.route_name
   AND st.route_name = rsd_to.route_name
   AND rsd_from.active_flag = 'Y'
   AND rsd_to.active_flag = 'Y'
   --AND sch.event_date = st.departure_date
   AND sch.agy_loc_id = rsd_from.agy_loc_id
   AND sch.to_agy_loc_id = rsd_to.agy_loc_id
   AND lpad(rsd_from.leg_id::varchar, 4, '0')
          || lpad(rsd_from.leg_seq::varchar, 4, '0')
       < lpad(rsd_to.leg_id::varchar, 4, '0')
          || lpad(rsd_to.leg_seq::varchar, 4, '0')
   and lpad(rsd_from.leg_id::varchar, 4, '0')
          || lpad(rsd_from.leg_seq::varchar, 4, '0') =
       (select max(lpad(rsdx_from.leg_id::varchar, 4, '0')|| lpad(rsdx_from.leg_seq::varchar, 4, '0'))
          from route_stop_details rsdx_from,
               route_stop_details rsdx_to
         where rsdx_from.route_name =st.route_name
           AND rsdx_to.route_name = st.route_name
           AND rsdx_from.active_flag = 'Y'
           AND rsdx_to.active_flag = 'Y'
           and rsdx_to.leg_id = rsd_to.leg_id and rsdx_to.leg_seq = rsd_to.leg_seq
           AND rsdx_from.agy_loc_id = sch.agy_loc_id
           AND rsdx_to.agy_loc_id = sch.to_agy_loc_id
           AND lpad(rsdx_from.leg_id::varchar, 4, '0')
                  || lpad(rsdx_from.leg_seq::varchar, 4, '0')
               < lpad(rsdx_to.leg_id::varchar, 4, '0')
                  || lpad(rsdx_to.leg_seq::varchar, 4, '0')
           and lpad(rsdx_to.leg_id::varchar, 4, '0')
                  || lpad(rsdx_to.leg_seq::varchar, 4, '0')
               =(select max(lpad(rsdy_to.leg_id::varchar, 4, '0')|| lpad(rsdy_to.leg_seq::varchar, 4, '0'))
                   from route_stop_details rsdy_to
                  where rsdy_to.route_name = st.route_name
                    and rsdy_to.active_flag = 'Y'
                    and rsdy_to.agy_loc_id = sch.to_agy_loc_id
                )
       )


;

CREATE OR REPLACE VIEW v_local_trip_offenders
AS SELECT st.scheduled_trip_id,
    v.route_name,
    sch.inmate_id,
    sch.event_id,
    sch.offender_book_id,
    sch.offender_id_display,
    sch.offender_last_name,
    sch.offender_first_name,
    sch.sex_code,
    sch.race_code,
    sch.event_type,
    sch.event_sub_type,
    sch.agy_loc_id AS offender_from_loc,
    sch.housing_location,
    sch.to_agy_loc_id AS offender_to_loc,
    sch.comment_text,
    sch.record_source,
    min(v.from_seq) AS from_seq,
    max(v.to_seq) AS to_seq
   FROM v_route_locations v,
    ( SELECT v_sch.offender_id AS inmate_id,
            v_sch.offender_id_display,
            v_sch.offender_book_id,
            v_sch.event_id,
            v_sch.offender_last_name,
            v_sch.offender_first_name,
            o.sex_code,
            o.race_code,
            v_sch.event_type,
            v_sch.event_sub_type,
            v_sch.scheduled_trip_id,
            v_sch.agy_loc_id,
                CASE
                    WHEN ob.agy_loc_id::text = v_sch.agy_loc_id::text THEN ((((((((v_sch.agy_loc_id::text || '-'::text) || v_sch.lu_level_1_code) ||
                    CASE
                        WHEN COALESCE(v_sch.lu_level_2_code, ''::text) = ''::text THEN NULL::text
                        ELSE '-'::text
                    END) || v_sch.lu_level_2_code) ||
                    CASE
                        WHEN COALESCE(v_sch.lu_level_3_code, ''::text) = ''::text THEN NULL::text
                        ELSE '-'::text
                    END) || v_sch.lu_level_3_code) ||
                    CASE
                        WHEN COALESCE(v_sch.lu_level_4_code, ''::text) = ''::text THEN NULL::text
                        ELSE '-'::text
                    END) || v_sch.lu_level_4_code)::character varying
                    ELSE v_sch.agy_loc_id
                END AS housing_location,
            v_sch.to_agy_loc_id,
            v_sch.comment_text,
            v_sch.record_source
           FROM v_offender_all_schedules v_sch,
            offenders o,
            offender_bookings ob
          WHERE (v_sch.record_source = ANY (ARRAY['SCH'::text, 'COURT'::text])) AND COALESCE(v_sch.event_status, ' '::character varying)::text <> 'EXP'::text AND v_sch.offender_id = o.offender_id AND v_sch.offender_book_id = ob.offender_book_id AND v_sch.scheduled_trip_id IS NOT NULL AND v_sch.scheduled_trip_id::text <> ''::text
        UNION ALL
         SELECT non_admitted_inmate_mvmts.non_adm_inmate_id AS inmate_id,
            non_admitted_inmate_mvmts.inmate_id,
            NULL::bigint,
            NULL::bigint,
            non_admitted_inmate_mvmts.last_name,
            non_admitted_inmate_mvmts.first_name,
            NULL::character varying,
            NULL::character varying,
            non_admitted_inmate_mvmts.mvmt_type,
            non_admitted_inmate_mvmts.mvmt_reason_code,
            non_admitted_inmate_mvmts.scheduled_trip_id,
            non_admitted_inmate_mvmts.from_agy_loc_id,
            non_admitted_inmate_mvmts.from_agy_loc_id,
            non_admitted_inmate_mvmts.to_agy_loc_id,
            (('Responsible Agency: '::text || upper(non_admitted_inmate_mvmts.responsible_agency::text)) || '  Emergency Contact: '::text) || upper(non_admitted_inmate_mvmts.emergency_contact::text),
            NULL::text
           FROM non_admitted_inmate_mvmts
          WHERE non_admitted_inmate_mvmts.scheduled_trip_id IS NOT NULL AND non_admitted_inmate_mvmts.scheduled_trip_id::text <> ''::text) sch,
    scheduled_trips st
  WHERE st.scheduled_trip_id = sch.scheduled_trip_id AND st.route_name::text = v.route_name::text AND v.from_seq >= (( SELECT max(lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) AS max
           FROM route_stop_details rsd
          WHERE rsd.agy_loc_id::text = sch.agy_loc_id::text AND rsd.route_name::text = v.route_name::text AND rsd.active_flag::text = 'Y'::text AND (lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) < (( SELECT max(lpad(rsdx.leg_id::character varying::text, 4, '0'::text) || lpad(rsdx.leg_seq::character varying::text, 4, '0'::text)) AS max
                   FROM route_stop_details rsdx
                  WHERE rsdx.route_name::text = v.route_name::text AND rsdx.agy_loc_id::text = sch.to_agy_loc_id::text AND rsdx.active_flag::text = 'Y'::text)))) AND v.from_seq < (( SELECT min(lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) AS min
           FROM route_stop_details rsd
          WHERE rsd.agy_loc_id::text = sch.to_agy_loc_id::text AND rsd.route_name::text = v.route_name::text AND rsd.active_flag::text = 'Y'::text AND (lpad(rsd.leg_id::character varying::text, 4, '0'::text) || lpad(rsd.leg_seq::character varying::text, 4, '0'::text)) > (( SELECT max(lpad(rsd2.leg_id::character varying::text, 4, '0'::text) || lpad(rsd2.leg_seq::character varying::text, 4, '0'::text)) AS max
                   FROM route_stop_details rsd2
                  WHERE rsd2.agy_loc_id::text = sch.agy_loc_id::text AND rsd2.route_name::text = v.route_name::text AND rsd2.active_flag::text = 'Y'::text AND (lpad(rsd2.leg_id::character varying::text, 4, '0'::text) || lpad(rsd2.leg_seq::character varying::text, 4, '0'::text)) < (( SELECT max(lpad(rsdx.leg_id::character varying::text, 4, '0'::text) || lpad(rsdx.leg_seq::character varying::text, 4, '0'::text)) AS max
                           FROM route_stop_details rsdx
                          WHERE rsdx.route_name::text = v.route_name::text AND rsdx.agy_loc_id::text = sch.to_agy_loc_id::text AND rsdx.active_flag::text = 'Y'::text))))))
  GROUP BY st.scheduled_trip_id, v.route_name, sch.inmate_id, sch.event_id, sch.offender_book_id, sch.offender_id_display, sch.offender_last_name, sch.offender_first_name, sch.sex_code, sch.race_code, sch.event_type, sch.event_sub_type, sch.agy_loc_id, sch.housing_location, sch.to_agy_loc_id, sch.comment_text, sch.record_source;

CREATE OR REPLACE VIEW v_route_occupancy (scheduled_trip_id, route_name, from_seq, from_agy_loc_id, to_agy_loc_id, occupancy) AS select occ.scheduled_trip_id,
       rsd.route_name,
       rsd.from_seq,
       rsd.from_agy_loc_id,
       rsd.to_agy_loc_id,
       coalesce(occ.occupancy, 0) occupancy
  FROM v_route_locations rsd,
       v_local_trip_occupancy occ
 where rsd.route_name =occ.route_name
   and rsd.from_seq = occ.from_seq

union all

select st.scheduled_trip_id,
       st.route_name,
       rsd.from_seq,
       rsd.from_agy_loc_id,
       rsd.to_agy_loc_id,
       0
  from v_route_locations rsd,
       scheduled_trips st
 where st.route_name = rsd.route_name
   and not exists (select 1
             from v_local_trip_occupancy occ
            where occ.scheduled_trip_id = st.scheduled_trip_id
              and occ.from_seq = rsd.from_seq
          )
 
;
create or replace VIEW V_QM_PAI ( PROCESS_INS_ID, DETAIL, START_TIME, QM_PROC_INS_STATUS_DOMAIN, QM_PROC_INS_STATUS_CODE, PROCESS_ID, COMPOSITION_ID, OBJECT_INS_ID, VALUE, LAST_NAME, FIRST_NAME, OFFENDER_ID_DISPLAY, OBJECT_ID, ACTIVITY_INS_ID, END_TIME, QM_ACT_INS_STATUS_DOMAIN, QM_ACT_INS_STATUS_CODE, TEAM_ID, STAFF_ID, ACTIVITY_ID, MANAGING_AGY_LOC_ID, EVENT_TYPE_AGY_LOC_ID, MAN_ACT_TEAM_ID ) AS
select
/* MODIFICATION HISTORY
   Person       Date       Version       Comments
   ---------    ------     ---------    ------------------------------
   Johnny       25/01/2010 1.3           Changed back to INS_ID
   Johnny       21/01/2010 1.2           Changed from INS_ID to INST_ID
   Johnny       24/12/2009 1.1           Added new view and new column vqmat.man_act_team_id
                                         Removed condition for qmai.end_time IS NULL, it will be filtered on form.
   Niko         18/12/2009 1.0           Created
*/
       qmpi.process_ins_id
      ,qmpi.detail
      ,qmpi.start_time
      ,qmpi.qm_proc_ins_status_domain
      ,qmpi.qm_proc_ins_status_code
      ,qmpi.process_id
      ,qmpi.composition_id
      ,qmoi.object_ins_id
      ,qmoi.value
      ,off_name.last_name last_name
      ,off_name.first_name first_name
      ,off_name.offender_id_display offender_id_display
      /*
      ,(SELECT off_name.last_name
          FROM offenders off_name, offender_bookings off_bkg
         WHERE off_name.offender_id = off_bkg.offender_id
           AND off_bkg.offender_book_id = qmoi.value
           AND rownum = 1) LAST_NAME
      ,(SELECT off_name.first_name
          FROM offenders off_name, offender_bookings off_bkg
         WHERE off_name.offender_id = off_bkg.offender_id
           AND off_bkg.offender_book_id = qmoi.value
           AND rownum = 1) FIRST_NAME
      ,(SELECT off_name.offender_id_display
          FROM offenders off_name, offender_bookings off_bkg
         WHERE off_name.offender_id = off_bkg.offender_id
           AND off_bkg.offender_book_id = qmoi.value
           AND rownum = 1) OFFENDER_ID_DISPLAY
      */
      ,qmoi.object_id
      ,qmai.activity_ins_id
      ,qmai.end_time
      ,qmai.qm_act_ins_status_domain
      ,qmai.qm_act_ins_status_code
      ,qmai.team_id
      ,qmai.staff_id
      ,qmai.activity_id
      ,vqmpc.managing_agy_loc_id
      ,vqmpc.event_type_agy_loc_id
      ,vqmat.man_act_team_id
  from v_qm_pc vqmpc,
       v_qm_at vqmat,
       qm_processes_ins qmpi,
       qm_object_ins qmoi,
       qm_activities_ins qmai,
       (SELECT qmo1.object_id
          FROM qm_objects qmo1
         WHERE qmo1.name      = 'OFFENDER_BOOK_ID'
           and qmo1.qm_data_type_code = 'NUMBER') qmo,
       (SELECT off.last_name, off.first_name, off.offender_id_display, off_bkg.offender_book_id
          FROM offenders OFF, offender_bookings off_bkg
         WHERE off.offender_id = off_bkg.offender_id) off_name
  WHERE vqmpc.process_id      = qmpi.process_id
    and vqmpc.composition_id  = qmpi.composition_id
    AND vqmat.activity_id     = qmai.activity_id
    AND vqmat.team_id         = qmai.team_id
    and qmpi.process_ins_id   = qmai.process_ins_id
    and qmpi.process_ins_id   = qmoi.process_ins_id
    and qmoi.object_id        = qmo.object_id
    AND qmoi.value            = off_name.offender_book_id::character varying;
	
CREATE OR REPLACE VIEW v_trust_header (trust_caseload_id, caseload_id, commissary_trust_caseload, community_trust_caseload, offender_id, alias_offender_id, offender_id_display, last_name, first_name, middle_name, suffix, birth_date, offender_book_id, booking_no, booking_type, booking_begin_date, booking_end_date, agy_loc_id, living_unit_id, disclosure_flag, active_flag, booking_status, living_unit_description, in_out_status, status_display, root_offender_id, intake_agy_loc_id, community_active_flag, create_intake_agy_loc_id, community_status, status_reason, header_status, agy_loc_type, age, gender, officer, prison_location, off_alerts, status_1, status_2, status_3, ethnicity, account_closed_flag, movement_reason, off_sup_level, indigent_flag) AS SELECT /*+ RULE */  /* ============================================================
             Version Number = 2.3.1.4  Date Modified = 18-April-2008
     ============================================================ */
  /* MODIFICATION HISTORY
          Person      Date           Version       Comments
          ---------   -----------    ------------  ------------------------------
          Srinivas    27-AUG-2014    2.3.1.5       Added a hint to resolve the Performance issue reported
          Abu /Nancy  04/18/2008     2.3.1.4       modified with Abu's peer review to make the view not too genaric by caseload.
          Nancy       12/13/2008     2.3.1.3       Merge 6i product version 1.5 with 10g product version 2.3.1.2 base on 6i product version 1.5,
                                                   so it could to solve:
                                                   1. v_trust_header has beed decided to merge with v_trust_header (for trust header)
                                                      and v_trust_header_a (for commissary header)
                                                   2. the view should cover all 3 different caseload seting seniario which include:
                                                      conneticate: single tust caseload and single commissary caseload
                                                      minnessota: multi trust caseload and single commissary caseload
                                                      Oregan: single trust caseload and multi commissary caseload
                                                   3. the view needs to cover 10g structure(new columns, removed tables, and so on)
  10g version comment:
       NIKO        28-JAN-2008            2.3.1.2      Added a column - INDIGENT_FLAG for MN
       NIKO        10-JAN-2008            2.3.1.1      Modified the caseload_id column
                                                      (SELECT CL.CASELOAD_ID
                                                         FROM CASELOADS CL, STAFF_MEMBERS STF
                                                        WHERE STF.USER_ID = USER
                                                          AND CL.CASELOAD_ID = STF.WORKING_CASELOAD_ID) CASELOAD_ID,
       NIKO        26-FEB-2006            2.3.1.0      Added 2 new columns - movement_reason and off_sup_levell
       Vipul       27/09/2005             2.1                   Modified V_Trust_Header view for UK.
       Venu         28/09/2005             2.2                   Added account_closed_flag to the view.
       Venu         19/05/2006             2.3                   CRFNL0045 - Age column is derived from birth date.
 6i product version comment:
         Joe         08/30/2004     1.5              Tr#20528. Wadoc 6i Upgrade.
                                                         Remove Hint Text 'RULE' for performance issues.
          Herbert     30-JAN-2003    1.4           Remove linkage between off_ta.caseload_id and cl.caseload_id (or cl.community_trust_caseload
                                                   for centralized community trust caseload) as caseload_id can be different from the
                                                   trust_caseload_id for Commissary's case.
                                                   This linkage will be handled in Trust_Main for flexibility to customize it for modules of different
                                                   application code.  CL.community_trust_caseload is SELECTED so that the column can be used from
                                                   Trust_Main
          Patrick     04-NOV-2002    1.3           Remove table staff_members stf from the FROM table list in the SQL statement
          Patrick     31-OCT-2002    1.2           Added code for the link of trust community caseload structure.
                                                   Removed code to link staff_members.working_caseload_id and cl.caseload_id since in financial a
                                                   global view is needed instead of a restricted view of the caseload.
          Vipul       29-OCT-2002    1.1           TR#13378: Modified the views to display Supervision Status.
          Vipul       01-OCT-2002    1.0.1.0       Modified code to display Supervision Status
          Herbert     22-JUL-2002    6.1.0.1 CO    - Merged into product version from oregon 6.1.0.0 OR
          Herbert     08-APR-2002    6.1.0.0 OR    - Changed query not to link offender trust account
                                                   with caseload, this link will be enhanced from
                                                   default_block_where.
                                                 - Select trust account caseload as caseload column
                                                   also select the caseload_id and
                                                   commissary_trust_caseload for a linking point from
                                                   TRUST_MAIN
        */
   off_ta.caseload_id trust_caseload_id,
   cl.caseload_id caseload_id,
   cl.commissary_trust_caseload,
   --cl.trust_commissary_caseload,
   cl.community_trust_caseload,
   off_name.offender_id,
   off_name.alias_offender_id,
   off_name.offender_id_display,
   off_name.last_name,
   off_name.first_name,
   off_name.middle_name,
   off_name.suffix,
   off_name.birth_date,
   off_bkg.offender_book_id,
   off_bkg.booking_no,
   off_bkg.booking_type,
   off_bkg.booking_begin_date,
   off_bkg.booking_end_date,
   off_bkg.agy_loc_id,
   off_bkg.living_unit_id,
   off_bkg.disclosure_flag,
   off_bkg.active_flag,
   off_bkg.booking_status,
   CASE WHEN cl.caseload_type='COMM' THEN CASE WHEN off_bkg.community_active_flag='Y' THEN off_bkg.community_agy_loc_id END                                   ||' - '                                  ||staff.last_name                                  ||', '                                  ||staff.first_name                                  ||' ; '                                  ||off_bkg.comm_staff_role                                  ||' : '                                  ||off_bkg.agy_loc_id  ELSE liv_unit.description                           ||';'                           ||agency_il.internal_location_code                           ||' : '                           ||off_bkg.community_agy_loc_id END  living_unit_description,
   off_bkg.in_out_status,
   CASE WHEN cl.caseload_type='COMM' THEN CASE WHEN off_bkg.community_active_flag='Y' THEN oms_SYSTEM_PROFILE('CLIENT','B/C_STATUS',1)                                                                           ||CASE WHEN oms_SYSTEM_PROFILE('C_KER','ACT_SUP_STS',1)='Y' THEN '-'                                                                                        ||(SELECT status_code                                                                                           FROM   offender_comm_sup_histories                                                                                           WHERE  offender_book_id = off_bkg.offender_book_id                                                                                                  AND status_seq = (SELECT MAX(status_seq)                                                                                                                    FROM   offender_comm_sup_histories                                                                                                                    WHERE  offender_book_id = off_bkg.offender_book_id                                                                                                                           AND effective_date <= LOCALTIMESTAMP)) WHEN oms_SYSTEM_PROFILE('C_KER','ACT_SUP_STS',1)='N' THEN '-'                                                                                        ||off_bkg.comm_status  ELSE '-'                                                                                    ||off_bkg.comm_status END  WHEN off_bkg.community_active_flag='N' THEN oms_SYSTEM_PROFILE('CLIENT','B/C_STATUS',2)  ELSE NULL END   ELSE CASE WHEN off_bkg.active_flag='Y' THEN 'ACTIVE-' WHEN off_bkg.active_flag='N' THEN 'INACTIVE-'  ELSE NULL END                            ||CASE WHEN off_bkg.in_out_status='OUT' THEN CASE WHEN mov_rsn.header_status_flag='Y' THEN SUBSTR(off_bkg.status_reason,position('-' in off_bkg.status_reason) + 1)  ELSE off_bkg.in_out_status END   ELSE off_bkg.in_out_status END  END  status_display,
   off_name.root_offender_id,
   off_bkg.intake_agy_loc_id,
   off_bkg.community_active_flag,
   off_bkg.create_intake_agy_loc_id,
   off_bkg.comm_status community_status,
   off_bkg.status_reason,
   mov_rsn.header_status_flag header_status,
   SUBSTR(agy_loc.agency_location_type,0,12) agy_loc_type,
   floor(EXTRACT(year FROM age(date_trunc('day',CURRENT_TIMESTAMP),off_name.birth_date))+EXTRACT(month FROM age(date_trunc('day',CURRENT_TIMESTAMP),off_name.birth_date))/12) age,
   SUBSTR(oms_miscellaneous_GETDESCCODE('SEX',off_name.sex_code),
          0,10) gender,
   SUBSTR(tag_header_GET_OFFICER(off_bkg.offender_book_id),
          0,105) officer,
/*
   SUBSTR(tag_header.GET_PRISON_LOCATION(off_bkg.agy_loc_id,off_bkg.living_unit_id,
                                         off_bkg.agency_iml_id),
          0,105)
*/
   SUBSTR(tag_header_GET_PRISON_LOCATION(off_bkg.active_flag,
              off_bkg.community_active_flag,
              off_bkg.community_agy_loc_id,
              off_bkg.agy_loc_id,
              off_bkg.living_unit_id,
              off_bkg.agency_iml_id,
              off_bkg.offender_book_id
              ),
          0,105)          prison_location,
   SUBSTR(omkhead_GET_ALERTS(off_bkg.offender_book_id),
          0,40) off_alerts,
   SUBSTR(tag_header_GET_STATUS_1(off_bkg.offender_book_id,off_bkg.in_out_status,
                                  off_bkg.comm_status,off_bkg.status_reason),
          0,40) status_1,
   SUBSTR(tag_header_GET_STATUS_2(off_bkg.offender_book_id),
          0,40) status_2,
   SUBSTR(tag_header_GET_STATUS_3(off_bkg.offender_book_id),
          0,40) status_3,
   SUBSTR((oms_miscellaneous_GETDESCCODE('ETHNICITY',off_name.race_code)),
          0,40) ethnicity,
   off_ta.account_closed_flag,
   (SELECT oem.movement_reason_code
    FROM   offender_external_movements oem
    WHERE  oem.offender_book_id = off_bkg.offender_book_id
           AND oem.movement_seq = (SELECT MAX(movement_seq)
                                   FROM   offender_external_movements oem2
                                   WHERE  oem2.offender_book_id = oem.offender_book_id)
           AND EXISTS (SELECT 'X'
                       FROM   movement_reasons mr
                       WHERE  mr.movement_type = oem.movement_type
                              AND mr.movement_reason_code = oem.movement_reason_code
                              AND mr.header_status_flag = 'Y')) movement_reason,
   oms_intake_GET_OFFENDER_SUPERVISION(off_bkg.offender_book_id) off_sup_level,
   indigent_ind_flagformula(off_bkg.root_offender_id,off_ta.caseload_id,
                            off_bkg.agy_loc_id) indigent_flag
  FROM offender_trust_accounts off_ta, offenders off_name, caseloads cl, --          agency_internal_mvmt_locations agency_iml,
          offender_bookings off_bkg
LEFT OUTER JOIN staff_members staff ON (off_bkg.comm_staff_id = staff.staff_id)
LEFT OUTER JOIN agency_locations agy_loc ON (off_bkg.agy_loc_id = agy_loc.agy_loc_id)
LEFT OUTER JOIN agency_internal_locations agency_il ON (off_bkg.agency_iml_id = agency_il.internal_location_id)
LEFT OUTER JOIN living_units liv_unit ON (off_bkg.living_unit_id = liv_unit.living_unit_id)
LEFT OUTER JOIN movement_reasons mov_rsn ON 
(SUBSTR(off_bkg.status_reason,1,INSTR(off_bkg.status_reason,'-',1) - 1) = mov_rsn.movement_type
AND SUBSTR(off_bkg.status_reason,INSTR(off_bkg.status_reason,'-',1) + 1) = mov_rsn.movement_reason_code)
WHERE off_ta.offender_id = off_name.root_offender_id -- MOdified base on abu's peer reivew
  AND ((off_ta.caseload_id = coalesce(cl.community_trust_caseload,cl.caseload_id))
               OR (off_ta.caseload_id = cl.commissary_trust_caseload)
               OR (off_ta.caseload_id = cl.payroll_trust_caseload)
               OR (off_ta.caseload_id = cl.trust_caseload_id)
               OR (off_ta.caseload_id IN (SELECT cal.agy_loc_id
                                          FROM   caseload_agency_locations cal
                                          WHERE  cal.caseload_id = cl.caseload_id))
               OR (off_ta.caseload_id IN (SELECT clx.caseload_id
                                          FROM   caseloads clx
                                          WHERE  clx.trust_accounts_flag = 'Y'
                                                 AND clx.trust_commissary_caseload = cl.caseload_id)))  AND EXISTS (SELECT 'x'
                     FROM   offender_sub_accounts off_sa
                     WHERE  off_sa.caseload_id = off_ta.caseload_id
                            AND off_sa.offender_id = off_ta.offender_id) AND off_name.offender_id = off_bkg.offender_id;
 
 
