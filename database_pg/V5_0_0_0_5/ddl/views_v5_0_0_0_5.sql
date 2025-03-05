CREATE OR REPLACE VIEW oms_owner.living_units_new
AS SELECT ail.internal_location_id AS living_unit_id,
    ail.agy_loc_id,
    ail.internal_location_type AS living_unit_type,
    ail.internal_location_code AS living_unit_code,
    ail.description,
    ail.user_desc,
    ail.aca_cap_rating,
    ail.security_level_code,
    ail.list_seq,
    ail.parent_internal_location_id AS parent_living_unit_id,
    ail.unit_type AS housing_unit_type,
    ail.active_flag,
    substr(tag_int_loc_active_flag(ail.internal_location_id)::text, 1, 1) AS control_active_flag,
    ail.cna_no,
    ail.capacity,
    ail.operation_capacity,
    ail.certified_flag,
    ail.deactivate_date,
    ail.reactivate_date,
    ail.deactivate_reason_code,
    ail.comment_text,
    ( SELECT
                CASE
                    WHEN count(*) = 0 THEN 'Y'::text
                    ELSE 'N'::text
                END AS "case"
           FROM agency_internal_locations ail2
          WHERE ail2.parent_internal_location_id = ail.internal_location_id) AS lowest_level_flag,
    substr(tag_int_loc_operation_flag(ail.internal_location_id)::text, 1, 1) AS reach_oper_capacity_flag,
    ail.no_of_occupant
   FROM agency_internal_locations ail
  WHERE ail.unit_type IS NOT NULL AND ail.unit_type::text <> ''::text;
  

CREATE OR REPLACE VIEW oms_owner.v_offenders_new
AS SELECT off_name.offender_id,
    off_name.sex_code,
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
    off_bkg.living_unit_id,
    off_bkg.disclosure_flag,
    off_bkg.active_flag,
    off_bkg.booking_status,
    liv_unit.description,
    off_bkg.in_out_status,
    (
        CASE
            WHEN off_bkg.active_flag::text = 'Y'::text THEN 'ACTIVE'::text
            ELSE 'INACTIVE'::text
        END || '-'::text) || off_bkg.in_out_status::text AS status_display
   FROM offenders off_name,
    offender_bookings off_bkg
     LEFT JOIN living_units_new liv_unit ON off_bkg.living_unit_id = liv_unit.living_unit_id
  WHERE off_bkg.offender_id = off_name.offender_id;
  
CREATE OR REPLACE VIEW oms_owner.v_offender_all_schedules_new
AS SELECT sch.event_id,
    sch.offender_book_id,
    bkg.in_out_status,
    bkg.booking_no,
    bkg.active_flag AS booking_active_flag,
    bkg.offender_id,
    off.offender_id_display,
    off.last_name AS offender_last_name,
    off.first_name AS offender_first_name,
    sch.event_date,
    sch.start_time,
    sch.end_time,
    sch.event_class,
    sch.event_type,
    sch.event_sub_type,
    sch.event_status,
    sch.event_outcome,
    sch.outcome_reason_code,
    sch.reference_id,
    sch.application_date,
    sch.application_time,
    sch.return_date,
    sch.return_time,
    sch.comment_text,
    sch.details,
    sch.agy_loc_id,
    agy.description AS agy_loc_desc,
    bkg.living_unit_id,
    lu.description AS living_unit_desc,
    bkg.agency_iml_id,
    ail2.description AS agency_iml_desc,
    sch.to_agy_loc_id,
    agy2.description AS to_agy_loc_desc,
    sch.to_agy_loc_id AS to_loc,
    agy2.description AS to_loc_desc,
    sch.escort_code,
    sch.direction_code,
    sch.schedule_movement_time,
    sch.from_city_code,
    sch.to_city_code,
    sch.to_internal_location_id,
    ail.description AS to_internal_location_desc,
    ail.user_desc AS to_int_loc_user_desc,
    sch.credited_hours,
    sch.engagement_code,
    sch.understanding_code,
    sch.piece_work,
    sch.in_time,
    sch.out_time,
    sch.performance_code,
    sch.transport_code,
    sch.sick_note_expiry_date,
    sch.sick_note_received_date,
    sch.unexcused_absence_flag,
    sch.unpaid_work_action,
    sch.unpaid_work_behaviour,
    sch.agreed_travel_hour,
    sch.check_box_1,
    sch.check_box_2,
    sch.hidden_comment_text,
    sch.in_charge_staff_id,
    sch.off_prgref_id,
    sch.contact_person_name,
    sch.to_address_owner_class,
    sch.to_address_id,
    sch.unpaid_work_supervisor,
    sch.ta_id,
    sch.record_source,
    sch.check_sum,
    sch.prov_state_code,
    sch.scheduled_trip_id
   FROM offenders off,
    v_offender_all_schedules_2 sch
     LEFT JOIN agency_locations agy ON sch.agy_loc_id::text = agy.agy_loc_id::text
     LEFT JOIN agency_locations agy2 ON sch.to_agy_loc_id::text = agy2.agy_loc_id::text
     LEFT JOIN agency_internal_locations ail ON sch.to_internal_location_id = ail.internal_location_id
     LEFT JOIN staff_members stf ON sch.in_charge_staff_id = stf.staff_id,
    offender_bookings bkg
     LEFT JOIN agency_internal_locations ail2 ON bkg.agency_iml_id = ail2.internal_location_id
     LEFT JOIN living_units_new lu ON bkg.living_unit_id = lu.living_unit_id
  WHERE sch.offender_book_id = bkg.offender_book_id AND bkg.offender_id = off.offender_id;


CREATE OR REPLACE VIEW oms_owner.v_stg_location_members_new
AS SELECT osa.stg_id,
    lu.agy_loc_id,
    lu.living_unit_id,
    lu.description,
    lu.list_seq,
    ob.offender_id,
    ob.offender_book_id,
    o.offender_id_display,
    o.last_name,
    o.first_name
   FROM offender_stg_affiliations osa,
    offender_bookings ob,
    offenders o,
    living_units_new lu
  WHERE ob.offender_book_id = osa.offender_book_id AND o.offender_id = ob.offender_id AND ob.living_unit_id = lu.living_unit_id AND osa.active_flag::text = 'Y'::text;  