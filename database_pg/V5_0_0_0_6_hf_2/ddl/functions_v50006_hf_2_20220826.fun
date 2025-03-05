alter function v_program_providers_fn stable;
alter function v_name_search_fn stable;
alter function v_name_search1_fn stable;
alter function v_property_header_block_fn stable;
alter function v_tag_header_block_fn stable;
alter function v_offender_visit_fn stable;
alter function caseload_current_accounts_fn stable;
alter function comm_caseload_current_accounts_fn stable;
alter function v_header_block_fn stable;
alter function v_header_block2_fn stable;
alter function v_property_header_block_2_fn stable;
alter function v_pims_name_search_fn stable;
CREATE OR REPLACE FUNCTION oms_owner.v_name_search2_fn(username text)
 RETURNS TABLE(offender_id bigint, alias_offender_id bigint, offender_id_display text, last_name text, first_name text, middle_name text, suffix text, birth_date timestamp without time zone, offender_book_id bigint, booking_no text, booking_begin_date timestamp without time zone, booking_end_date timestamp without time zone, agy_loc_id text, agency_iml_id bigint, living_unit_id bigint, disclosure_flag text, active_flag text, booking_status text, living_unit_description text, in_out_status text, status_display text, root_offender_id bigint, assigned_staff_id bigint, agy_loc_type text, create_agy_loc_id text, booking_type text, booking_created_date timestamp without time zone, location_code text, staff_first_name text, staff_last_name text, liv_unit_desc text, intake_agy_loc_id text, community_active_flag text, create_intake_agy_loc_id text, community_status text, agy_loc_name text, community_agy_loc_id text)
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
        CASE
            WHEN cl.caseload_type::text = 'COMM'::text THEN concat(
            CASE
                WHEN off_bkg.community_active_flag::text = 'Y'::text THEN off_bkg.community_agy_loc_id::text || ' - '::text
                ELSE NULL::text
            END,
            CASE
                WHEN COALESCE(staff.last_name::text, ''::text) = ''::text THEN NULL::text
                ELSE staff.last_name::text || ', '::text
            END,
            CASE
                WHEN COALESCE(staff.first_name::text, ''::text) = ''::text THEN NULL::text
                ELSE staff.first_name::text || ' ; '::text
            END,
            CASE
                WHEN COALESCE(off_bkg.comm_staff_role::text, ''::text) = ''::text THEN NULL::text
                ELSE off_bkg.comm_staff_role::text || ' : '::text
            END, off_bkg.agy_loc_id::text)
            ELSE
            concat(
            CASE
                WHEN COALESCE(liv_unit.description::text, ''::text) = ''::text THEN NULL::text
                ELSE liv_unit.description::text || ';'::text
            END,
            CASE
                WHEN COALESCE(agency_iml.internal_location_code::text, ''::text) = ''::text THEN NULL::text
                ELSE agency_iml.internal_location_code::text || ' : '::text
            END, off_bkg.community_agy_loc_id::text)
        END AS living_unit_description,
    off_bkg.in_out_status,
        CASE
            WHEN cl.caseload_type::text = 'COMM'::text THEN concat(
            CASE
                WHEN off_bkg.community_active_flag::text = 'Y'::text THEN 'Active-'::text
                WHEN off_bkg.community_active_flag::text = 'N'::text THEN 'Inactive-'::text
                ELSE NULL::text
            END, off_bkg.comm_status::text)
            ELSE concat(
            CASE
                WHEN off_bkg.active_flag::text = 'Y'::text THEN 'Active-'::text
                WHEN off_bkg.active_flag::text = 'N'::text THEN 'Inactive-'::text
                ELSE NULL::text
            END, off_bkg.in_out_status::text)
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
    agy_loc.abbreviation AS agy_loc_name,
    off_bkg.community_agy_loc_id
   FROM staff_members stf,
    offenders off_name,
    caseloads cl,
    offender_bookings off_bkg
     LEFT JOIN agency_internal_locations liv_unit ON off_bkg.living_unit_id = liv_unit.internal_location_id
     LEFT JOIN staff_members staff ON off_bkg.comm_staff_id = staff.staff_id
     LEFT JOIN agency_locations agy_loc ON off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text
     LEFT JOIN agency_internal_locations agency_iml ON off_bkg.agency_iml_id = agency_iml.internal_location_id
  WHERE off_name.offender_id = off_bkg.offender_id AND stf.user_id::text = upper(username::text) AND cl.caseload_id::text = stf.working_caseload_id::text AND (off_bkg.offender_book_id = (( SELECT max(ob1.offender_book_id) AS max
           FROM offender_bookings ob1
          WHERE ob1.root_offender_id = off_bkg.root_offender_id)) AND NOT (EXISTS ( SELECT NULL::text AS text
           FROM offender_bookings off_bkg1
          WHERE off_bkg1.root_offender_id = off_bkg.root_offender_id AND (COALESCE(off_bkg1.active_flag, 'N'::character varying)::text = 'Y'::text OR COALESCE(off_bkg1.community_active_flag, 'N'::character varying)::text = 'Y'::text))) OR COALESCE(off_bkg.active_flag, 'N'::character varying)::text = 'Y'::text OR COALESCE(off_bkg.community_active_flag, 'N'::character varying)::text = 'Y'::text AND NOT (EXISTS ( SELECT NULL::text AS text
           FROM offender_bookings off_bkg1
          WHERE off_bkg1.root_offender_id = off_bkg.root_offender_id AND COALESCE(off_bkg1.active_flag, 'N'::character varying)::text = 'Y'::text)));
         $function$
;