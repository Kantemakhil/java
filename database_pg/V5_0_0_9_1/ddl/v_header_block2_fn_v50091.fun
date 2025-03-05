DROP FUNCTION v_header_block2_fn(text);
CREATE OR REPLACE FUNCTION oms_owner.v_header_block2_fn(username text)
 RETURNS TABLE(offender_id bigint, alias_offender_id bigint, offender_id_display text, last_name text, first_name text, middle_name text, suffix text, birth_date timestamp without time zone, offender_book_id bigint, booking_no text,seal_flag text, booking_begin_date timestamp without time zone, booking_end_date timestamp without time zone, agy_loc_id text, agency_iml_id bigint, living_unit_id bigint, disclosure_flag text, active_flag text, booking_status text, living_unit_description text, in_out_status text, status_display text, root_offender_id bigint, assigned_staff_id bigint, agy_loc_type text, create_agy_loc_id text, booking_type text, booking_created_date timestamp without time zone, location_code text, staff_first_name text, staff_last_name text, liv_unit_desc text, intake_agy_loc_id text, community_active_flag text, create_intake_agy_loc_id text, community_status text, status_reason text, header_status text)
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
    COALESCE(off_bkg.offender_book_id, 0::bigint) AS offender_book_id,
    off_bkg.booking_no,
	coalesce(off_bkg.seal_flag , 'N'), 
    off_bkg.booking_begin_date,
    off_bkg.booking_end_date,
    off_bkg.agy_loc_id,
    off_bkg.agency_iml_id,
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
            ELSE concat(liv_unit.description, ';', agency_iml.internal_location_code, ' : ', off_bkg.community_agy_loc_id)
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
    staff.staff_id AS assigned_staff_id,
    agy_loc.agency_location_type AS agy_loc_type,
    off_bkg.create_agy_loc_id,
    off_bkg.booking_type,
    off_bkg.booking_created_date,
    agency_iml.internal_location_code AS location_code,
    staff.first_name AS staff_first_name,
    staff.last_name AS staff_last_name,
    liv_unit.description AS liv_unit_desc,
    off_bkg.intake_agy_loc_id,
    off_bkg.community_active_flag,
    off_bkg.create_intake_agy_loc_id,
    off_bkg.comm_status AS community_status,
    off_bkg.status_reason,
    mov_rsn.header_status_flag AS header_status
   FROM staff_members stf,
    caseloads cl,
    offenders off_name
     LEFT JOIN offender_bookings off_bkg ON off_name.offender_id = off_bkg.offender_id
     LEFT JOIN living_units liv_unit ON off_bkg.living_unit_id = liv_unit.living_unit_id
     LEFT JOIN staff_members staff ON off_bkg.comm_staff_id = staff.staff_id
     LEFT JOIN agency_internal_locations agency_iml ON off_bkg.agency_iml_id = agency_iml.internal_location_id
     LEFT JOIN agency_locations agy_loc ON off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text AND off_bkg.intake_agy_loc_id::text = agy_loc.agy_loc_id::text
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE stf.user_id::text = upper(username::text) AND cl.caseload_id::text = stf.working_caseload_id::text;
	$function$
;
