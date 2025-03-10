DROP FUNCTION v_property_header_block_fn(text);
CREATE OR REPLACE FUNCTION oms_owner.v_property_header_block_fn(username text)
 RETURNS TABLE(offender_id bigint, alias_offender_id bigint, offender_id_display text, last_name text, first_name text, middle_name text, suffix text, birth_date timestamp without time zone, seal_flag text,offender_book_id bigint, ppty_agy_loc_id text, booking_no text, booking_begin_date timestamp without time zone, booking_end_date timestamp without time zone, agy_loc_id text, living_unit_id bigint, disclosure_flag text, active_flag text, booking_status text, living_unit_description text, in_out_status text, status_display text, root_offender_id bigint, age bigint, gender text, officer text, prison_location text, off_alerts text, status_1 text, status_2 text, status_3 text, movement_reason text, off_sup_level text)
 LANGUAGE sql
 STABLE
AS $function$
SELECT off_name.offender_id,
    off_name.alias_offender_id,
    off_name.offender_id_display,
    off_name.last_name,
    off_name.first_name,
    off_name.middle_name,
    off_name.suffix,
    off_name.birth_date,
    coalesce(off_bkg.seal_flag , 'N'), 
    off_bkg.offender_book_id,
    off_bkg.agy_loc_id AS ppty_agy_loc_id,
    off_bkg.booking_no,
    off_bkg.booking_begin_date,
    off_bkg.booking_end_date,
    off_bkg.agy_loc_id,
    off_bkg.living_unit_id,
    off_bkg.disclosure_flag,
    off_bkg.active_flag,
    off_bkg.booking_status,
    substr(tag_header_get_header_location(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.community_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.comm_staff_role, off_bkg.comm_staff_id::bigint)::text, 0, 100) AS living_unit_description,
    off_bkg.in_out_status,
    substr(tag_header_get_header_status(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.status_reason, NULL::character varying, off_bkg.comm_status, off_bkg.in_out_status, off_bkg.root_offender_id, off_bkg.offender_book_id)::text, 0, 100) AS status_display,
    off_name.root_offender_id,
    floor(months_between(date_trunc('day'::text, LOCALTIMESTAMP), date_trunc('day'::text, off_name.birth_date)) / 12::numeric) AS age,
    substr(oms_miscellaneous_getdesccode('SEX'::character varying, off_name.sex_code)::text, 0, 10) AS gender,
    substr(tag_header_get_officer(off_bkg.offender_book_id)::text, 0, 105) AS officer,
    substr(tag_header_get_prison_location(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.intake_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id, off_bkg.offender_book_id)::text, 0, 105) AS prison_location,
    substr(omkhead_get_alerts(off_bkg.offender_book_id)::text, 0, 40) AS off_alerts,
    substr(tag_header_get_status_1(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status, off_bkg.status_reason)::text, 0, 40) AS status_1,
    substr(tag_header_get_status_2(off_bkg.offender_book_id)::text, 0, 40) AS status_2,
    substr(tag_header_get_status_3(off_bkg.offender_book_id)::text, 0, 40) AS status_3,
    ( SELECT oem.movement_reason_code
           FROM offender_external_movements oem
          WHERE oem.offender_book_id = off_bkg.offender_book_id AND oem.movement_seq = (( SELECT max(oem2.movement_seq) AS max
                   FROM offender_external_movements oem2
                  WHERE oem2.offender_book_id = oem.offender_book_id)) AND (EXISTS ( SELECT 'X'::text
                   FROM movement_reasons mr
                  WHERE mr.movement_type::text = oem.movement_type::text AND mr.movement_reason_code::text = oem.movement_reason_code::text AND mr.header_status_flag::text = 'Y'::text))) AS movement_reason,
    oms_intake_get_offender_supervision_U(off_bkg.offender_book_id,username) AS off_sup_level
   FROM offenders off_name,
    offender_bookings off_bkg,
    staff_members staff,
    staff_accessible_caseloads staff_ac,
    caseloads csld
  WHERE off_name.offender_id = off_bkg.offender_id AND staff.user_id::text = upper(username::text) AND staff_ac.staff_id = staff.staff_id AND staff_ac.caseload_id::text = staff.working_caseload_id::text AND csld.caseload_id::text = staff_ac.caseload_id::text AND csld.caseload_type::text = 'INST'::text AND ((off_bkg.agy_loc_id::text IN ( SELECT cal.agy_loc_id
           FROM caseload_agency_locations cal
          WHERE cal.caseload_id::text = staff.working_caseload_id::text AND (cal.agy_loc_id::text <> ALL (ARRAY['OUT'::character varying, 'TRN'::character varying]::text[])))) AND off_bkg.active_flag::text = 'Y'::text OR (off_bkg.offender_book_id IN ( SELECT opi2.offender_book_id
           FROM offender_ppty_items opi2,
            offender_bookings off_bkg2
          WHERE opi2.offender_book_id = off_bkg2.offender_book_id AND off_bkg2.offender_book_id = off_bkg.offender_book_id AND off_bkg2.active_flag::text = 'Y'::text AND (opi2.agy_loc_id::text IN ( SELECT cal2.agy_loc_id
                   FROM caseload_agency_locations cal2
                  WHERE cal2.caseload_id::text = staff.working_caseload_id::text AND cal2.agy_loc_id::text <> 'TRN'::text)))) OR off_bkg.offender_book_id = (( SELECT max(opi3.offender_book_id) AS max
           FROM offender_ppty_items opi3,
            offender_bookings off_bkg3
          WHERE opi3.offender_book_id = off_bkg3.offender_book_id AND off_bkg3.root_offender_id = off_bkg.root_offender_id AND (opi3.agy_loc_id::text IN ( SELECT cal3.agy_loc_id
                   FROM caseload_agency_locations cal3
                  WHERE cal3.caseload_id::text = staff.working_caseload_id::text AND cal3.agy_loc_id::text <> 'TRN'::text)) AND NOT (EXISTS ( SELECT NULL::text
                   FROM offender_bookings off_bkg4
                  WHERE off_bkg4.root_offender_id = off_bkg3.root_offender_id AND off_bkg4.active_flag::text = 'Y'::text)))) OR (off_bkg.offender_book_id IN ( SELECT off_bkg5.offender_book_id
           FROM offender_bookings off_bkg5
          WHERE off_bkg5.root_offender_id = off_bkg.root_offender_id AND off_bkg5.active_flag::text = 'N'::text)));
 $function$
;