DROP FUNCTION v_header_block_fn(text);
CREATE OR REPLACE FUNCTION oms_owner.v_header_block_fn(username text)
 RETURNS TABLE(offender_id bigint, alias_offender_id bigint, offender_id_display text, last_name text, first_name text, middle_name text, suffix text, birth_date timestamp without time zone, seal_flag text,offender_book_id bigint, booking_no text, booking_begin_date timestamp without time zone, booking_end_date timestamp without time zone, agy_loc_id text, agency_iml_id bigint, living_unit_id bigint, disclosure_flag text, active_flag text, booking_status text, living_unit_description text, in_out_status text, status_display text, root_offender_id bigint, assigned_staff_id bigint, agy_loc_type text, create_agy_loc_id text, booking_type text, booking_created_date timestamp without time zone, location_code text, liv_unit_desc text, intake_agy_loc_id text, community_active_flag text, create_intake_agy_loc_id text, community_status text, status_reason text, header_status text, age bigint, sex text, gender text, officer text, prison_location text, off_alerts text, status_1 text, status_2 text, status_3 text, ethnicity text, movement_reason text, off_sup_level text)
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
    off_bkg.booking_no,
    off_bkg.booking_begin_date,
    off_bkg.booking_end_date,
    off_bkg.agy_loc_id,
    off_bkg.agency_iml_id,
    off_bkg.living_unit_id,
    off_bkg.disclosure_flag,
    off_bkg.active_flag,
    off_bkg.booking_status,
    substr(tag_header_get_header_location_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.community_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.comm_staff_role, off_bkg.comm_staff_id::bigint,username)::text, 0, 100) AS living_unit_description,
    off_bkg.in_out_status,
    substr(tag_header_get_header_status_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.status_reason, mov_rsn.header_status_flag, off_bkg.comm_status, off_bkg.in_out_status, off_bkg.root_offender_id, off_bkg.offender_book_id,username)::text, 0, 100) AS status_display,
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
    substr(oms_miscellaneous_getdesccode('SEX'::character varying, off_name.sex_code)::text, 0, 10) AS sex,
    substr(oms_miscellaneous_getdesccode('GENDER'::character varying, off_name.gender_code)::text, 0, 10) AS gender,
    substr(tag_header_get_officer_u(off_bkg.offender_book_id,username)::text, 0, 105) AS officer,
    substr(tag_header_get_prison_location_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.intake_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id, off_bkg.offender_book_id,username)::text, 0, 105) AS prison_location,
    substr(omkhead_get_alerts(off_bkg.offender_book_id)::text, 0, 40) AS off_alerts,
    substr(tag_header_get_status_1_u(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status, off_bkg.status_reason,username)::text, 0, 40) AS status_1,
    substr(tag_header_get_status_2_u(off_bkg.offender_book_id,username)::text, 0, 40) AS status_2,
    substr(tag_header_get_status_3(off_bkg.offender_book_id)::text, 0, 40) AS status_3,
    substr(oms_miscellaneous_getdesccode('ETHNICITY'::character varying, off_name.race_code)::text, 0, 40) AS ethnicity,
    ( SELECT oem.movement_reason_code
           FROM offender_external_movements oem
          WHERE oem.offender_book_id = off_bkg.offender_book_id AND oem.movement_seq = (( SELECT max(oem2.movement_seq) AS max
                   FROM offender_external_movements oem2
                  WHERE oem2.offender_book_id = oem.offender_book_id)) AND (EXISTS ( SELECT 'X'::text AS text
                   FROM movement_reasons mr
                  WHERE mr.movement_type::text = oem.movement_type::text AND mr.movement_reason_code::text = oem.movement_reason_code::text AND mr.header_status_flag::text = 'Y'::text))) AS movement_reason,
    oms_intake_get_offender_supervision_u(off_bkg.offender_book_id,username) AS off_sup_level
   FROM offenders off_name,
    offender_bookings off_bkg
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE off_name.offender_id = off_bkg.offender_id;
    $function$
;

