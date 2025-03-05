CREATE OR REPLACE FUNCTION oms_owner.v_name_search_fn(username text)
 RETURNS TABLE(last_name text, first_name text, active_flag text, offender_id bigint, offender_book_id bigint, booking_no text, offender_id_display text, birth_date timestamp without time zone, agy_loc_id text, agy_loc_name text, living_unit_description text, in_out_status text, middle_name text, seal_flag text)
 LANGUAGE sql
 STABLE
AS $function$
SELECT off_name.last_name,
    off_name.first_name,
    off_bkg.active_flag,
    off_name.offender_id,
    off_bkg.offender_book_id,
    off_bkg.booking_no,
    off_name.offender_id_display,
    off_name.birth_date,
    agy_loc.agy_loc_id,
    agy_loc.abbreviation AS agy_loc_name,
    liv_unit.description AS living_unit_description,
    off_bkg.in_out_status,
    off_name.middle_name,
   coalesce(off_bkg.seal_flag , 'N')
   FROM offenders off_name,
    offender_bookings off_bkg,
    (SELECT ail.internal_location_id AS living_unit_id,
    ail.description
   FROM agency_internal_locations ail
  WHERE ail.unit_type IS NOT NULL AND ail.unit_type::text <> ''::text) liv_unit,
    caseload_agency_locations c,
    staff_members s,
    agency_locations agy_loc
  WHERE off_name.offender_id = off_bkg.offender_id AND agy_loc.agy_loc_id::text = off_bkg.agy_loc_id::text AND liv_unit.living_unit_id = off_bkg.living_unit_id AND agy_loc.agy_loc_id::text = c.agy_loc_id::text AND s.working_caseload_id::text = c.caseload_id::text AND s.user_id::text = upper(username::text) AND (c.agy_loc_id::text <> ALL (ARRAY['OUT'::character varying, 'TRN'::character varying]::text[]));
  $function$
;