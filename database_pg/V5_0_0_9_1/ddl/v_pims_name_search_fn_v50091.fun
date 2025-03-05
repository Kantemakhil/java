drop function v_pims_name_search_fn(text);
CREATE OR REPLACE FUNCTION oms_owner.v_pims_name_search_fn(username text)
 RETURNS TABLE(offender_id bigint, offender_id_display text, offender_book_id bigint, last_name text, first_name text, middle_name text, birth_date timestamp without time zone, active_flag text, living_unit_id bigint, root_offender_id bigint,seal_flag text, prison_location text)
 LANGUAGE sql
 STABLE
AS $function$
SELECT DISTINCT off_name.offender_id,
    off_name.offender_id_display,
    off_bkg.offender_book_id,
    off_name.last_name,
    off_name.first_name,
    off_name.middle_name,
    off_name.birth_date,
    off_bkg.active_flag,
    off_bkg.living_unit_id,
    off_name.root_offender_id,
    coalesce(off_bkg.seal_flag , 'N'),
    substr(tag_header_get_prison_location_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.intake_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id, off_bkg.offender_book_id,username)::text, 0, 105) AS prison_location
   FROM offender_bookings off_bkg,
    offenders off_name,
    caseload_agency_locations cal,
    staff_members staff,
    offender_booking_agy_locs obal
  WHERE off_name.offender_id = off_bkg.offender_id AND cal.caseload_id::text = staff.working_caseload_id::text AND upper(staff.user_id::text) = upper(username::text) AND obal.agy_loc_id::text = cal.agy_loc_id::text AND COALESCE(obal.removed_date::text, ''::text) = ''::text AND obal.offender_book_id = off_bkg.offender_book_id;
    $function$
;
