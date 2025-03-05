create or replace function oms_owner.v_name_search_fn (username text )
 returns table (last_name text, first_name text, active_flag text, offender_id bigint, offender_book_id bigint, booking_no text, offender_id_display text, birth_date timestamp, agy_loc_id text, agy_loc_name text, living_unit_description text, in_out_status text, middle_name text)
as
$body$
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
    off_name.middle_name
   FROM offenders off_name,
    offender_bookings off_bkg,
    living_units liv_unit,
    caseload_agency_locations c,
    staff_members s,
    agency_locations agy_loc
  WHERE off_name.offender_id = off_bkg.offender_id AND agy_loc.agy_loc_id::text = off_bkg.agy_loc_id::text AND liv_unit.living_unit_id = off_bkg.living_unit_id AND agy_loc.agy_loc_id::text = c.agy_loc_id::text AND s.working_caseload_id::text = c.caseload_id::text AND s.user_id::text = upper(username::text) AND (c.agy_loc_id::text <> ALL (ARRAY['OUT'::character varying, 'TRN'::character varying]::text[]));
  $body$
language sql;

CREATE OR REPLACE FUNCTION oms_owner.oms_intake_get_caseload_type_U(p_username text)
 RETURNS character varying
 LANGUAGE plpgsql
 SECURITY DEFINER
AS $function$
DECLARE

      lv_caseload_type caseloads.caseload_type%TYPE;
      get_caseload_type_cur CURSOR FOR
         SELECT caseload_type
           FROM staff_members sm, caseloads cl
          WHERE sm.user_id = upper(p_username)
            AND sm.working_caseload_id = cl.caseload_id;

BEGIN
      OPEN get_caseload_type_cur;
      FETCH get_caseload_type_cur
         INTO lv_caseload_type;
      CLOSE get_caseload_type_cur;
      RETURN(lv_caseload_type);
   END;
   --
   -- To copy a substance abuse to another booking
$function$
;

CREATE OR REPLACE FUNCTION oms_owner.oms_intake_get_offender_supervision_U(p_offender_book_id bigint,p_username text)
 RETURNS character varying
 LANGUAGE plpgsql
 SECURITY DEFINER
AS $function$
DECLARE

      lv_level_code        varchar(12);
      lv_domain            reference_codes.domain%TYPE;
      lv_caseload_type     caseloads.caseload_type%TYPE;
      lv_level_description varchar(40);
      lv_value1            system_profiles.profile_value%TYPE;
      lv_value2            system_profiles.profile_value_2%TYPE;

      ref_cur CURSOR(p_sup_level text, p_domain text) FOR
         SELECT description
           FROM reference_codes
          WHERE domain = lv_domain
            AND code = p_sup_level;

      sys_cur CURSOR FOR
         SELECT profile_value, profile_value_2
           FROM system_profiles
          WHERE profile_type = 'CLIENT'
            AND profile_code = 'PENDING_STAT';

   
BEGIN
      lv_level_code    := pims_weight_get_sup_level(p_offender_book_id);
      lv_caseload_type := oms_intake_get_caseload_type_U(p_username);
      --IF lv_caseload_type = 'COMM' THEN
      --lv_domain := 'COM_SUP_LVLS';
      --ELSE
      --lv_domain := 'SUP_LVL_TYPE';
      --END IF;
      lv_domain := 'SUP_LVL_TYPE';
      OPEN ref_cur(lv_level_code, lv_domain);
      FETCH ref_cur
         INTO lv_level_description;
      CLOSE ref_cur;
      IF coalesce(lv_level_description::text, '') = ''
      THEN
         OPEN sys_cur;
         FETCH sys_cur
            INTO lv_value1, lv_value2;
         CLOSE sys_cur;
         IF (lv_value2 IS NOT NULL AND lv_value2::text <> '')
         THEN
            lv_level_description := lv_value2;
         END IF;
      END IF;
      RETURN lv_level_description;
   END;
   --
   /*
   -- To copy the health problem to another booking.
   FUNCTION copy_health_problem(
   p_old_offender_book_id  NUMBER ,
   p_new_offender_book_id  NUMBER ,
   p_old_problem_seq  VARCHAR2 )
   RETURN NUMBER IS
     --
     --
     l_problem_seq NUMBER;
   BEGIN
      SELECT nvl(max(PROBLEM_SEQ),0) + 1
      INTO l_problem_seq
      FROM   OFFENDER_HEALTH_PROBLEMS
      WHERE  OFFENDER_BOOK_ID = p_new_offender_book_id;
     INSERT INTO offender_health_problems
        (offender_book_id,
        problem_seq,
        problem_type,
        problem_code,
        onset_date,
        partial_onset_date_flag,
        inactive_date,
        partial_inactive_date_flag,
        comment_text,
        create_date,
        problem_status,
        modify_datetime,
        modify_user_id)
     SELECT p_new_offender_book_id,
                    l_problem_seq,
                    problem_type,
                    problem_code,
                    onset_date,
                    partial_onset_date_flag,
                    inactive_date,
                    partial_inactive_date_flag,
                    comment_text,
                    SYSDATE,
                    problem_status,
                    SYSDATE,
                    USER
     FROM    offender_health_problems
     WHERE offender_book_id = p_old_offender_book_id
     AND      problem_seq = p_old_problem_seq;
     RETURN(l_problem_seq);
     EXCEPTION
        WHEN OTHERS THEN
           RAISE_APPLICATION_ERROR(
              -20000,
              oms_utils_display_user_message(
                 10, 'OMS', 'OMPIOSAU', SQLERRM )
           );
          RETURN(NULL);
   END;
   
   */
$function$
;

create or replace function v_property_header_block_fn(username text)
returns table(offender_id bigint,
alias_offender_id bigint,
offender_id_display text,
last_name text,
first_name text,
middle_name text,
suffix text,
birth_date timestamp,
offender_book_id bigint,
ppty_agy_loc_id text,
booking_no text,
booking_begin_date timestamp,
booking_end_date timestamp,
agy_loc_id text,
living_unit_id bigint,
disclosure_flag text,
active_flag text,
booking_status text,
living_unit_description text,
in_out_status text,
status_display text,
root_offender_id bigint,
age bigint,
gender text,
officer text,
prison_location text,
off_alerts text,
status_1 text,
status_2 text,
status_3 text,
movement_reason text,
off_sup_level text)
as
$body$
SELECT off_name.offender_id,
    off_name.alias_offender_id,
    off_name.offender_id_display,
    off_name.last_name,
    off_name.first_name,
    off_name.middle_name,
    off_name.suffix,
    off_name.birth_date,
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
 $body$
language sql;

create or replace function v_program_providers_fn(username text)
returns table(PARTY_ID bigint,PARTY_CODE text,PARTY_NAME text,PARTY_CLASS text,LAST_NAME text,FIRST_NAME text)
as
$body$
SELECT persons.person_id AS party_id,
    NULL::text AS party_code,
    (persons.last_name::text || ', '::text) || persons.first_name::text AS party_name,
    'PER'::text AS party_class,
    persons.last_name,
    persons.first_name
   FROM persons
UNION ALL
 SELECT staff_members.staff_id AS party_id,
    NULL::text AS party_code,
    (staff_members.last_name::text || ', '::text) || staff_members.first_name::text AS party_name,
    'STF'::text AS party_class,
    staff_members.last_name,
    staff_members.first_name
   FROM staff_members
UNION ALL
 SELECT NULL::bigint AS party_id,
    agency_locations.agy_loc_id AS party_code,
    agency_locations.description AS party_name,
    'AGY'::text AS party_class,
    NULL::character varying AS last_name,
    NULL::character varying AS first_name
   FROM agency_locations
  WHERE agency_locations.agy_loc_id::text <> ALL (ARRAY['OUT'::character varying, 'TRN'::character varying]::text[])
UNION ALL
 SELECT t.team_id AS party_id,
    t.team_code AS party_code,
    t.description AS party_name,
    'TEAM'::text AS party_class,
    NULL::character varying AS last_name,
    NULL::character varying AS first_name
   FROM teams t
  WHERE (EXISTS ( SELECT 'x'::text
           FROM staff_members sm,
            team_members tm
          WHERE tm.active_flag::text = 'Y'::text AND upper(sm.user_id::text) = upper(username) AND sm.staff_id = tm.staff_id AND t.team_id = tm.team_id))
UNION ALL
 SELECT corporates.corporate_id AS party_id,
    NULL::text AS party_code,
    corporates.corporate_name AS party_name,
    'CORP'::text AS party_class,
    NULL::character varying AS last_name,
    NULL::character varying AS first_name
   FROM corporates;
  $body$
language sql;   

CREATE OR REPLACE FUNCTION oms_owner.tag_header_get_prison_location_u(p_active_flag character varying, p_comm_active_flag character varying, p_comm_agy_loc_id character varying, p_agy_loc_id character varying, p_liv_unit_id bigint, p_agy_iml_id bigint, p_offender_book_id bigint,username text)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_LOC_DESCRIPTION   AGENCY_LOCATIONS.DESCRIPTION%TYPE;
      LV_OUT_DESCRIPTION   AGENCY_LOCATIONS.DESCRIPTION%TYPE;
      LV_RETURN_STRING     VARCHAR (200);
      LV_OFFICE_STRING     VARCHAR (115);
      LV_CASELOAD_TYPE     STAFF_MEMBERS.WORKING_CASELOAD_ID%TYPE;
      LV_MULTI_LOC_COUNT   BIGINT;
      LV_AGY_ID            OFFENDER_BOOKINGS.INTAKE_AGY_LOC_ID%TYPE = NULL;

      GET_DESC_CUR CURSOR(LV_AGY_LOC_ID VARCHAR)
      FOR
         SELECT INITCAP (DESCRIPTION)
           FROM AGENCY_LOCATIONS
          WHERE AGY_LOC_ID = LV_AGY_LOC_ID;

      CHECK_MULTI_LOCS_CUR CURSOR
      FOR
         SELECT COUNT(0)
           FROM OFFENDER_BOOKING_AGY_LOCS OBA
          WHERE OBA.REMOVED_DATE IS NULL 
            AND OBA.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID;
            
      COMM_AGY_CUR CURSOR 
      FOR
       SELECT CP.AGY_LOC_ID
         FROM CASE_PLANS CP
        WHERE CP.CASE_PLAN_STATUS = 'ACTIVE'
          AND CP.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID;
   BEGIN            

      OPEN  GET_DESC_CUR(P_AGY_LOC_ID);
      FETCH GET_DESC_CUR INTO LV_LOC_DESCRIPTION;
      CLOSE GET_DESC_CUR;

      IF P_LIV_UNIT_ID IS NOT NULL THEN
         LV_RETURN_STRING := concat(P_AGY_LOC_ID,' ['
                                          ,TAG_INT_LOC_INT_LOC_PATH(P_LIV_UNIT_ID)
                                          ,']');
         IF P_AGY_IML_ID IS NOT NULL THEN
            LV_RETURN_STRING := concat(LV_RETURN_STRING,'-['
                                                 ,TAG_INT_LOC_INT_LOC_PATH(P_AGY_IML_ID)
                                                 ,']');
         END IF;
      ELSE
         LV_RETURN_STRING := LV_LOC_DESCRIPTION;
      END IF;

      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE_u(username);

      IF LV_CASELOAD_TYPE = 'COMM' THEN

         IF P_COMM_ACTIVE_FLAG = 'Y' THEN  
         
            OPEN  COMM_AGY_CUR;
            FETCH COMM_AGY_CUR INTO LV_AGY_ID;
            CLOSE COMM_AGY_CUR;
                 
            IF P_COMM_AGY_LOC_ID IS NOT NULL THEN
               IF LV_AGY_ID IS NOT NULL THEN
                  OPEN  GET_DESC_CUR(LV_AGY_ID);
                  FETCH GET_DESC_CUR INTO LV_LOC_DESCRIPTION;
                  CLOSE GET_DESC_CUR;           
               ELSE
                  OPEN  GET_DESC_CUR(P_COMM_AGY_LOC_ID);
                  FETCH GET_DESC_CUR INTO LV_LOC_DESCRIPTION;
                  CLOSE GET_DESC_CUR;
               END IF;   
            ELSE
               LV_LOC_DESCRIPTION := P_COMM_AGY_LOC_ID;
            END IF;

            LV_OFFICE_STRING := tag_header_GET_OFFICER(P_OFFENDER_BOOK_ID);
            IF LV_OFFICE_STRING IS NOT NULL THEN
               LV_OFFICE_STRING := concat('[',LV_OFFICE_STRING,']');
            END IF;

            OPEN  CHECK_MULTI_LOCS_CUR;
            FETCH CHECK_MULTI_LOCS_CUR INTO LV_MULTI_LOC_COUNT;
            CLOSE CHECK_MULTI_LOCS_CUR;
         
            IF LV_MULTI_LOC_COUNT > 1 THEN
               LV_OFFICE_STRING := concat(LV_OFFICE_STRING,' MULTI');
            END IF;
         
         
            LV_RETURN_STRING := concat(LV_LOC_DESCRIPTION,LV_OFFICE_STRING);

            IF P_AGY_LOC_ID IS NOT NULL THEN 
              LV_RETURN_STRING := concat(LV_LOC_DESCRIPTION
                                 ,LV_OFFICE_STRING
                                 ,' ; '
                                 ,P_AGY_LOC_ID);
            END IF;
         ELSIF P_COMM_ACTIVE_FLAG = 'N' THEN 
            OPEN  GET_DESC_CUR('OUT');
            FETCH GET_DESC_CUR INTO LV_OUT_DESCRIPTION;
            CLOSE GET_DESC_CUR;
            
            IF P_AGY_LOC_ID IS NOT NULL THEN 
               IF P_AGY_LOC_ID <> 'OUT' THEN  
                  LV_RETURN_STRING := concat(LV_OUT_DESCRIPTION,';',P_AGY_LOC_ID);
               ELSE
                  LV_RETURN_STRING := LV_OUT_DESCRIPTION;
               END IF;
            ELSE
               LV_RETURN_STRING := LV_OUT_DESCRIPTION;
            END IF;
            
         END IF; 
                  
      ELSE

         IF P_COMM_ACTIVE_FLAG = 'Y' THEN
		 
            
			
			
			OPEN  COMM_AGY_CUR;
            FETCH COMM_AGY_CUR INTO LV_AGY_ID;
            CLOSE COMM_AGY_CUR;
                           
            	
			IF LV_AGY_ID IS NOT NULL
			THEN
                LV_RETURN_STRING := concat(LV_RETURN_STRING
                                ,' ; '
                                ,LV_AGY_ID);			
			
			ELSE
            
				LV_RETURN_STRING := concat(LV_RETURN_STRING
                                ,' ; '
                                ,P_COMM_AGY_LOC_ID);
            END IF;	

		 
         END IF;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION oms_owner.v_pims_name_search_fn(username text)
 RETURNS TABLE(offender_id bigint, offender_id_display text, offender_book_id bigint, last_name text, first_name text, middle_name text, birth_date timestamp, active_flag text, living_unit_id bigint, root_offender_id bigint, prison_location text)
 LANGUAGE sql
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
    substr(tag_header_get_prison_location_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.intake_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id, off_bkg.offender_book_id,username)::text, 0, 105) AS prison_location
   FROM offender_bookings off_bkg,
    offenders off_name,
    caseload_agency_locations cal,
    staff_members staff,
    offender_booking_agy_locs obal
  WHERE off_name.offender_id = off_bkg.offender_id AND cal.caseload_id::text = staff.working_caseload_id::text AND upper(staff.user_id::text) = upper(username::text) AND obal.agy_loc_id::text = cal.agy_loc_id::text AND COALESCE(obal.removed_date::text, ''::text) = ''::text AND obal.offender_book_id = off_bkg.offender_book_id;
    $function$
;
 
create or replace function caseload_current_accounts_fn (username text)
returns table(caseload_id  text,account_code bigint,account_period_id bigint,current_balance bigint,bank_account_type text,bank_account_number text,account_party_type text,payee_person_id bigint,payee_corporate_id bigint,modify_user_id text,modify_date timestamp,list_seq bigint,routing_number bigint)
as
$body$
SELECT ccab.caseload_id,
    ccab.account_code,
    max(ccat.account_period_id) AS account_period_id,
    sum(COALESCE(ccat.current_balance, 0::numeric)) AS current_balance,
    ccab.bank_account_type,
    ccab.bank_account_number,
    ccab.account_party_type,
    ccab.payee_person_id,
    ccab.payee_corporate_id,
    upper(username) AS modify_user_id,
    max(ccat.modify_date) AS modify_date,
    max(ccat.list_seq) AS list_seq,
    ccab.routing_number
   FROM caseload_current_accounts_txns ccat,
    caseload_current_accounts_base ccab
  WHERE ccat.caseload_id::text = ccab.caseload_id::text AND ccat.account_code = ccab.account_code
  GROUP BY ccab.caseload_id, ccab.account_code, ccab.bank_account_type, ccab.bank_account_number, ccab.account_party_type, ccab.payee_person_id, ccab.payee_corporate_id, ccab.routing_number;
  $body$
language sql;

create or replace function v_name_search1_fn (username text)
returns table(last_name text,first_name text,active_flag text,offender_id bigint,offender_book_id bigint,offender_id_display text,agy_loc_id text,agy_loc_name text,living_unit_description text,in_out_status text)
as
$body$
 SELECT off_name.last_name,
    off_name.first_name,
    off_bkg.active_flag,
    off_name.offender_id,
    off_bkg.offender_book_id,
    off_name.offender_id_display,
    agy_loc.agy_loc_id,
    agy_loc.abbreviation AS agy_loc_name,
        CASE
            WHEN agy_loc.agency_location_type::text = 'OFF'::text THEN ((staff.first_name::text || ', '::text) || staff.last_name::text)::character varying
            ELSE liv_unit.description
        END AS living_unit_description,
    off_bkg.in_out_status
   FROM offenders off_name,
    agency_locations agy_loc,
    offender_bookings off_bkg
     LEFT JOIN staff_members staff ON off_bkg.assigned_staff_id = staff.staff_id
     LEFT JOIN living_units liv_unit ON off_bkg.living_unit_id = liv_unit.living_unit_id
  WHERE off_name.offender_id = off_bkg.offender_id AND agy_loc.agy_loc_id::text = off_bkg.agy_loc_id::text AND (agy_loc.agy_loc_id::text IN ( SELECT c.agy_loc_id
           FROM caseload_agency_locations c,
            staff_members s
          WHERE s.working_caseload_id::text = c.caseload_id::text AND s.user_id::text = upper(username::text) AND (c.agy_loc_id::text <> ALL (ARRAY['OUT'::character varying, 'TRN'::character varying]::text[]))));
  $body$
language sql;

create or replace function v_offender_visit_fn  (username text)
returns table(start_time TIMESTAMP,end_time TIMESTAMP,surname text,given text,id bigint,offender_id_display text,housing_loc text,visit_type text,"location"  text,living_unit_id bigint,visit_date TIMESTAMP,offender_book_id bigint,movement_seq bigint,cancel_flag text,cancel_reason text)
as
$body$
SELECT
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
                                 AND upper(sm.user_id) = upper(username))
                                        
UNION ALL
(SELECT oem.offender_book_id
                                 FROM offender_external_movements oem,
                                      staff_members sm,
                                      caseload_agency_locations cal
                                WHERE oem.offender_book_id = ob.offender_book_id
                                  AND oem.from_agy_loc_id = cal.agy_loc_id
                                  AND cal.caseload_id = sm.working_caseload_id
                                  AND upper(sm.user_id) = upper(username)
                                  AND NOT EXISTS (SELECT NULL
                                                    FROM offender_bookings off_bkg1
                                         WHERE off_bkg1.root_offender_id = ob.root_offender_id
                                  AND     off_bkg1.active_flag = 'Y')))
 
 
;
  $body$
language sql;

create or replace function v_tag_header_block_fn(username text)
returns table(offender_id bigint,
alias_offender_id bigint,
offender_id_display text,
last_name text,
first_name text,
middle_name text,
suffix text,
birth_date timestamp,
offender_book_id bigint,
booking_no text,
booking_begin_date timestamp,
booking_end_date timestamp,
agency text,
agy_loc_id text,
agency_iml_id bigint,
living_unit_id bigint,
disclosure_flag text,
active_flag text,
booking_status text,
living_unit_description text,
in_out_status text,
status_display text,
root_offender_id bigint,
assigned_staff_id bigint,
create_agy_loc_id text,
booking_type text,
booking_created_date timestamp,
location_code text,
intake_agy_loc_id text,
community_active_flag text,
create_intake_agy_loc_id text,
status_reason text,
header_status text) 
as
$body$
select
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
	off_bkg.offender_book_id,
	off_bkg.booking_no,
	off_bkg.booking_begin_date,
	off_bkg.booking_end_date,
	off_bkg.agy_loc_id,
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
		else liv_unit.description || ';' || agency_iml.INTERNAL_LOCATION_CODE || ' : ' || off_bkg.community_agy_loc_id
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
						and effective_date <= LOCALTIMESTAMP))
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
	off_bkg.create_agy_loc_id,
	off_bkg.booking_type,
	off_bkg.booking_created_date,
	agency_iml.INTERNAL_LOCATION_CODE,
	off_bkg.intake_agy_loc_id,
	off_bkg.community_active_flag,
	off_bkg.create_intake_agy_loc_id,
	off_bkg.status_reason,
	mov_rsn.header_status_flag
from
	staff_members stf,
	offenders off_name,
	caseloads cl,
	offender_bookings off_bkg
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
	(SUBSTR( off_bkg.status_reason,
	1,
	position('-' in off_bkg.status_reason) - 1 ) = mov_rsn.movement_type
		and SUBSTR( off_bkg.status_reason,
		position('-' in off_bkg.status_reason) + 1 ) = mov_rsn.movement_reason_code)
where
	off_name.offender_id = off_bkg.offender_id
	and upper(stf.user_id) = upper(username)
	and cl.caseload_id = stf.working_caseload_id;
	$body$
language sql;

create or replace function v_header_block2_fn(username text)
returns table(offender_id  bigint,alias_offender_id bigint,offender_id_display text,last_name text,first_name text,middle_name text,suffix text,birth_date timestamp,offender_book_id bigint,booking_no text,booking_begin_date timestamp,booking_end_date timestamp,agy_loc_id text,agency_iml_id bigint,living_unit_id bigint,disclosure_flag text,active_flag text,booking_status text,living_unit_description text,in_out_status text,status_display text,root_offender_id bigint,assigned_staff_id bigint,agy_loc_type text,create_agy_loc_id text,booking_type text,booking_created_date timestamp,location_code text,staff_first_name text,staff_last_name text,liv_unit_desc text,intake_agy_loc_id text,community_active_flag text,create_intake_agy_loc_id text,community_status text,status_reason text,header_status text) 
as
$body$
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
	$body$
language sql;

CREATE OR REPLACE function v_property_header_block_2_fn(username text)
returns table(offender_id bigint, alias_offender_id bigint, offender_id_display text, last_name text, first_name text, middle_name text, suffix text, birth_date timestamp, offender_book_id bigint, ppty_agy_loc_id text, booking_no text, booking_begin_date timestamp, booking_end_date timestamp, agy_loc_id text, living_unit_id bigint, disclosure_flag text, active_flag text, booking_status text, living_unit_description text, in_out_status text, status_display text, root_offender_id bigint) 
as
$body$
SELECT /*+ Index(offender_bookings_pk, offender_bookings_ni2) */       OFF_NAME.OFFENDER_ID

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
WHERE OFF_NAME.OFFENDER_ID = OFF_BKG.OFFENDER_ID  AND upper(STAFF.USER_ID) = upper(username::text) AND STAFF_AC.CASELOAD_ID = STAFF.WORKING_CASELOAD_ID AND STAFF_AC.STAFF_ID = STAFF.STAFF_ID AND STAFF_AC.CASELOAD_ID = CSLD.CASELOAD_ID AND CSLD_AL.CASELOAD_ID = CSLD.CASELOAD_ID AND CSLD_AL.AGY_LOC_ID NOT IN ('OUT', 'TRN');
	$body$
language sql;


CREATE OR REPLACE function comm_caseload_current_accounts_fn (username text) 
returns table(caseload_id text, account_code bigint, account_period_id bigint, current_balance bigint, bank_account_type text, bank_account_number text, account_party_type text, payee_person_id bigint, payee_corporate_id bigint, modify_user_id text, modify_date timestamp, list_seq bigint)
as
$body$
SELECT
 CCAB.CASELOAD_ID
, CCAB.ACCOUNT_CODE
, MAX(CCAT.ACCOUNT_PERIOD_ID)
, SUM(coalesce(CCAT.CURRENT_BALANCE,0))
, CCAB.BANK_ACCOUNT_TYPE
, CCAB.BANK_ACCOUNT_NUMBER
, CCAB.ACCOUNT_PARTY_TYPE
, CCAB.PAYEE_PERSON_ID
, CCAB.PAYEE_CORPORATE_ID
, username
, MAX(CCAT.MODIFY_DATE)
, MAX(CCAT.LIST_SEQ)
 FROM com_csld_current_accounts_TXNS CCAT, com_csld_current_accounts_base CCAB
 WHERE CCAT.caseload_id  = CCAB.caseload_id
 AND   CCAT.account_code  = CCAB.account_code
GROUP BY CCAB.CASELOAD_ID, CCAB.ACCOUNT_CODE,
	 CCAB.BANK_ACCOUNT_TYPE, CCAB.BANK_ACCOUNT_NUMBER, CCAB.ACCOUNT_PARTY_TYPE,
	 CCAB.PAYEE_PERSON_ID, CCAB.PAYEE_CORPORATE_ID;
	$body$
language sql;	

CREATE OR REPLACE function oms_owner.v_name_search2_fn(username text)
returns table(offender_id bigint,alias_offender_id bigint,offender_id_display text,last_name text,first_name text,middle_name text,suffix text,birth_date timestamp,offender_book_id bigint	,booking_no text,booking_begin_date timestamp,booking_end_date timestamp,agy_loc_id text,agency_iml_id bigint,living_unit_id bigint,disclosure_flag text,active_flag text,booking_status text,living_unit_description text,in_out_status text,status_display text,root_offender_id bigint,assigned_staff_id bigint,agy_loc_type text,create_agy_loc_id text,booking_type text,booking_created_date timestamp,location_code text,staff_first_name text,staff_last_name text,liv_unit_desc text,intake_agy_loc_id text,community_active_flag text,create_intake_agy_loc_id text,community_status text,agy_loc_name text,community_agy_loc_id text)
as
$body$
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
     LEFT JOIN living_units liv_unit ON off_bkg.living_unit_id = liv_unit.living_unit_id
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
         $body$
language sql;  

CREATE OR REPLACE FUNCTION pims_weight_get_caseload_type_U(username text)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
     LV_CASELOAD_TYPE CASELOADS.CASELOAD_TYPE%TYPE;
     GET_CASELOAD_TYPE_CUR CURSOR FOR SELECT CASELOAD_TYPE
                                       FROM STAFF_MEMBERS SM,
                                            CASELOADS CL
                                      WHERE SM.USER_ID= username
                                        AND SM.WORKING_CASELOAD_ID = CL.CASELOAD_ID;
  BEGIN
     OPEN   GET_CASELOAD_TYPE_CUR;
     FETCH  GET_CASELOAD_TYPE_CUR INTO LV_CASELOAD_TYPE;
     CLOSE  GET_CASELOAD_TYPE_CUR;
     RETURN(LV_CASELOAD_TYPE);
  END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_caseload_type_u(username text)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 



      LV_RETURN_STRING   CASELOADS.CASELOAD_TYPE%TYPE;

      GET_TYPE_CUR CURSOR
      FOR
         SELECT CASELOAD_TYPE
           FROM CASELOADS
          WHERE CASELOAD_ID = (SELECT WORKING_CASELOAD_ID
                                 FROM STAFF_MEMBERS
                                WHERE USER_ID = username);
   BEGIN
      OPEN GET_TYPE_CUR;

      FETCH GET_TYPE_CUR
       INTO LV_RETURN_STRING;

      IF NOT FOUND
      THEN
         LV_RETURN_STRING := NULL;
      END IF;

      CLOSE GET_TYPE_CUR;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION oms_utils_get_staff_id_u (username text) RETURNS bigint 
LANGUAGE plpgsql
AS $function$
DECLARE

   staff_cur CURSOR FOR
      SELECT staff_id
        FROM staff_members
       WHERE user_id = upper(username);
   v_staff_id   staff_members.staff_id%TYPE   := NULL;

BEGIN
   OPEN staff_cur;
   FETCH staff_cur INTO v_staff_id;
   CLOSE staff_cur;
   RETURN v_staff_id;
  
EXCEPTION
      WHEN OTHERS
      THEN
        raise '% %',sqlerrm,sqlstate;
   END ;
$function$
;

create or replace
function oms_owner.IWP_10G_is_template_accessible_u (p_template_id iwp_templates.template_id%type ,
p_module_name iwp_template_modules.module_name%type,username text) returns varchar as $body$ declare
--@@@Surya 12-Jul-2007:User Admin Security User id impacted fix.
 get_access cursor for
select
	'Y'
from
	iwp_template_roles itr ,
	iwp_template_modules itm ,
	iwp_templates it
where
	itm.template_id = itr.template_id
	and itr.template_id = it.template_id
	--@@@ Sarah: 09-Dec-08: Tr#7679: Used p_template_id as a condition to filter the lov
	and it.template_id = p_template_id
	and itm.module_name = p_module_name
	---@@@ Nasir 01-DEC-2010:  Add to fit Tag security.
	and exists (
	select
		'X'
	from
		staff_member_roles a,
		staff_members sm,
		oms_roles b
	where
		b.role_code = itr.role_code
		and b.role_id = a.role_id
		and sm.staff_id = a.staff_id
		and sm.user_id = upper(username))
limit 1;
--@@@Eric 23-Jul-2008: Modified to fit Tag security datamodel
 /*
                WHERE itr.template_id = p_template_id
             AND EXISTS (SELECT 'X'
                           FROM user_caseload_roles a,
                        oms_roles b
                  WHERE b.role_code = itr.role_code
                    AND b.role_id = a.role_id
                    AND a.caseload_id = nomis_context.get_caseload_id
                    AND a.username = USER)
                  AND itm.template_id = itr.template_id
      */
lv_have_access varchar(1);

begin open get_access;

fetch get_access
into
	lv_have_access;

close get_access;

return lv_have_access;

exception
when others then return 'N';
end;

$body$ language PLPGSQL ;


CREATE OR REPLACE FUNCTION oms_owner.TAG_VISITS_GET_STAFF_ID_u (username text) RETURNS bigint AS $body$
DECLARE

      staff_cur CURSOR FOR
         SELECT staff_id
           FROM staff_members
          WHERE user_id = upper(username);
      v_staff_id staff_members.staff_id%TYPE := NULL;

BEGIN
      OPEN staff_cur;
      FETCH staff_cur
         INTO v_staff_id;
      CLOSE staff_cur;
      RETURN v_staff_id;
   EXCEPTION
      WHEN OTHERS THEN
        raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

create or replace
function oms_owner.TAG_SECURITY_GET_GROUP_PRIVILEGE_u ( p_module_name text,username text ) returns varchar as $body$
declare
--
   --
l_access_priv varchar(1);

begin
   /*
      * Note the user may have several different roles and therefore
      * different access privileges to a module (Q (query) or A (All)).
      * The user should have All privilege if one of his roles
      * allows this.
   */
      select
	MIN(module_privileges.access_privilege)
        into
	strict l_access_priv
from
	module_privileges,
	staff_member_roles,
	staff_members
where
	uppeR(staff_members.user_id) = upper(username)
	and staff_member_roles.staff_id = staff_members.staff_id
	and module_privileges.role_id = staff_member_roles.role_id
	and module_privileges.module_name = p_module_name;

return(l_access_priv);

exception
when no_data_found then
           return(null);
when others then
--           FAIL(oms_utils.display_user_message(
--              10, 'OMS', 'GET_GROUP_PRIVILEGE', SQLERRM));
           raise exception '%',
'GET_GROUP_PRIVILEGE: ' || sqlerrm
	using ERRCODE = '45001';
end;

$body$
language PLPGSQL
;

create or replace
function oms_owner.SJS_LIB_get_staff_id_from_user_u (username text) returns bigint as $body$
declare

    GET_STAFF_ID_CUR cursor for
    select
	STAFF_ID
from
	STAFF_MEMBERS
where
	USER_ID = upper(username);

L_STAFF_ID bigint;

begin
       open GET_STAFF_ID_CUR;
fetch
       GET_STAFF_ID_CUR
    into
	L_STAFF_ID
;
CLOSE GET_STAFF_ID_CUR;
return(L_STAFF_ID);

exception
when others then
          close GET_STAFF_ID_CUR;

raise exception '%',
OMS_UTILS.DISPLAY_USER_MESSAGE(
                10,
                'OMS',
                'OMFGTFAG',
                sqlerrm
            )
	using ERRCODE = '45000';
end;

$body$
language PLPGSQL
;


CREATE OR REPLACE FUNCTION oms_owner.TAG_UTILS_get_staff_id_u (P_USER_ID STAFF_MEMBERS.USER_ID%TYPE) RETURNS STAFF_MEMBERS.STAFF_ID%TYPE AS $body$
DECLARE

      P_STAFF_ID STAFF_MEMBERS.STAFF_ID%TYPE;
      GET_STAFF_CUR CURSOR FOR
         SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = P_USER_ID;

BEGIN
      OPEN GET_STAFF_CUR;
      FETCH GET_STAFF_CUR
         INTO P_STAFF_ID;
      CLOSE GET_STAFF_CUR;
      RETURN P_STAFF_ID;
   EXCEPTION
      WHEN OTHERS THEN
        raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;


CREATE OR REPLACE FUNCTION tag_header_get_status_1_u(p_offender_book_id bigint, p_in_out_status character varying, p_comm_status character varying, p_status_reason character varying,username text)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_return_string   VARCHAR (40);
      lv_caseload_type   VARCHAR(12);
   BEGIN
      lv_caseload_type := tag_header_get_caseload_type_u(username);

      IF lv_caseload_type = 'COMM'
      THEN
         IF p_comm_status = 'NEW'
         THEN
            lv_return_string := NULL;
         ELSE
            lv_return_string := p_comm_status;
         END IF;
      ELSE
         IF SUBSTR (p_status_reason, 5, 4) = 'ESCP'
         THEN
            lv_return_string := 'UAL';
         ELSIF SUBSTR (p_status_reason, 5, 4) = 'UAL'
         THEN
            lv_return_string := 'UAL';
         ELSE
            IF p_in_out_status = 'TRN'
            THEN
               lv_return_string := 'In Transit';
            ELSE
               lv_return_string := INITCAP (p_in_out_status);
            END IF;
         END IF;
      END IF;

      RETURN lv_return_string;
   END;
$function$;



CREATE OR REPLACE function v_header_block_fn(username text) 
returns table(offender_id bigint,alias_offender_id bigint,offender_id_display text,last_name text,first_name text,middle_name text,suffix text,birth_date TIMESTAMP,offender_book_id bigint,booking_no text,booking_begin_date TIMESTAMP,booking_end_date TIMESTAMP,agy_loc_id text,agency_iml_id bigint,living_unit_id bigint,disclosure_flag text,active_flag text,booking_status text,living_unit_description text,in_out_status text,status_display text,root_offender_id bigint,assigned_staff_id bigint,agy_loc_type text,create_agy_loc_id text,booking_type text,booking_created_date TIMESTAMP,location_code text,liv_unit_desc text,intake_agy_loc_id text,community_active_flag text,create_intake_agy_loc_id text,community_status text,status_reason text,header_status text,age bigint,gender text,officer text,prison_location text,off_alerts text,status_1 text,status_2 text,status_3 text,ethnicity text,movement_reason text,off_sup_level text)
as
$body$
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
    substr(tag_header_get_status_1_u(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status, off_bkg.status_reason,username)::text, 0, 40) AS status_1,
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
    oms_intake_get_offender_supervision_u(off_bkg.offender_book_id,username) AS off_sup_level
   FROM offenders off_name,
    offender_bookings off_bkg
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE off_name.offender_id = off_bkg.offender_id;
    $body$
language sql;

CREATE OR REPLACE function v_header_block_fn(username text) 
returns table(offender_id bigint,alias_offender_id bigint,offender_id_display text,last_name text,first_name text,middle_name text,suffix text,birth_date timestamp,offender_book_id bigint,booking_no text,booking_begin_date timestamp,booking_end_date timestamp,agy_loc_id text,agency_iml_id bigint,living_unit_id bigint,disclosure_flag text,active_flag text,booking_status text,living_unit_description text,in_out_status text,status_display text,root_offender_id bigint,assigned_staff_id bigint,agy_loc_type text,create_agy_loc_id text,booking_type text,booking_created_date timestamp,location_code text,liv_unit_desc text,intake_agy_loc_id text,community_active_flag text,create_intake_agy_loc_id text,community_status text,status_reason text,header_status text,age bigint,gender text,officer text,prison_location text,off_alerts text,status_1 text,status_2 text,status_3 text,ethnicity text,movement_reason text,off_sup_level text)
as
$body$
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
    substr(tag_header_get_status_1_u(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status, off_bkg.status_reason,username)::text, 0, 40) AS status_1,
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
    oms_intake_get_offender_supervision_u(off_bkg.offender_book_id,username) AS off_sup_level
   FROM offenders off_name,
    offender_bookings off_bkg
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE off_name.offender_id = off_bkg.offender_id;
    $body$
language sql;

CREATE OR REPLACE FUNCTION oms_owner.tag_assessment_chk_bkg_csa(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE

      obcs_cur CURSOR FOR
         SELECT 'Y'
           FROM OFFENDER_BOOKING_DETAILS
          WHERE cell_sharing_alert_flag = 'Y'
            AND offender_book_id = p_offender_book_id;
      lv_conflict   varchar(1 ) := 'N';

BEGIN
      OPEN obcs_cur;
      FETCH obcs_cur
       INTO lv_conflict;
      CLOSE obcs_cur;
     if lv_conflict is null then
     return 'N';
     else
      RETURN( lv_conflict );
     end if;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$function$
;

CREATE OR REPLACE FUNCTION oms_owner.tag_assessment_chk_occupant_csa(p_liv_unit_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE

      occs_cur CURSOR FOR
         select
	'Y'
from
	DUAL
where
	exists (
	select
		'Y'
	from
		OFFENDER_BOOKING_DETAILS
	where
		cell_sharing_alert_flag = 'Y'
		and offender_book_id in (
		select
			offender_book_id
		from
			OFFENDER_BOOKINGS
		where
			living_unit_id = p_liv_unit_id ) );
      lv_conflict   varchar(1 ) := 'N';

BEGIN
      OPEN occs_cur;
      FETCH occs_cur
       INTO lv_conflict;
      CLOSE occs_cur;
     if lv_conflict is null then
     return 'N';
     else
      RETURN( lv_conflict );
     end if;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$function$
;
