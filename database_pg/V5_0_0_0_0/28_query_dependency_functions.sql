CREATE OR REPLACE FUNCTION oms_owner.OMS_MISCELLANEOUS_get_profile_value ( P_PROFILE_TYPE text ,P_PROFILE_CODE text ) RETURNS varchar AS $body$
DECLARE

        L_PROFILE_VALUE SYSTEM_PROFILES.PROFILE_VALUE%TYPE;

BEGIN
        SELECT  PROFILE_VALUE
        INTO STRICT     L_PROFILE_VALUE
        FROM    SYSTEM_PROFILES
        WHERE   PROFILE_TYPE = P_PROFILE_TYPE
        AND     PROFILE_CODE = P_PROFILE_CODE;
        RETURN(L_PROFILE_VALUE);
   EXCEPTION
      WHEN no_data_found THEN RETURN NULL;
      WHEN too_many_rows THEN RETURN NULL;
      WHEN OTHERS        THEN RETURN NULL;
   END;
$body$
LANGUAGE PLPGSQL
;


CREATE OR REPLACE FUNCTION oms_utils_get_staff_id () RETURNS bigint 
LANGUAGE plpgsql
AS $function$
DECLARE

   staff_cur CURSOR FOR
      SELECT staff_id
        FROM staff_members
       WHERE user_id = upper(user);
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
CREATE OR REPLACE FUNCTION oidadmis_booking_no_format_ok (P_IN_FORMAT text) RETURNS boolean AS $body$
DECLARE

    LV_CHECK_FORMAT SYSTEM_PROFILES.PROFILE_VALUE%TYPE;
    LV_FORMAT       SYSTEM_PROFILES.PROFILE_VALUE%TYPE;
    LV_LENGTH       bigint;
    LV_NEW_YEAR     varchar(1);
    LV_NINE         bigint;
    LV_Y            bigint;

BEGIN
    LV_FORMAT := UPPER(P_IN_FORMAT);
    LV_LENGTH := LENGTH(LV_FORMAT);
    IF LENGTH(LV_FORMAT) > 14
    THEN
      RAISE EXCEPTION '%', 'Booking Number Format too long. Check System Profiles FORMAT/BOOKING_NO' USING ERRCODE = '45001';
      RETURN(FALSE);
    ELSE
      LV_NINE := position('9' in LV_FORMAT);
      LV_Y := position('Y' in LV_FORMAT);
      LV_CHECK_FORMAT := TRANSLATE(LV_FORMAT, '9Y-', '***');
      LV_CHECK_FORMAT := replace(LV_CHECK_FORMAT, '*', '');
      IF LENGTH(LV_CHECK_FORMAT) != 0
      THEN
        RAISE EXCEPTION '%', 'Booking Number format contains invalid characters.  Check System Profiles FORMAT/BOOKING_NO' USING ERRCODE = '45002';
      ELSIF LV_NINE = 0
      THEN
        RAISE EXCEPTION '%', 'Format does not have a sequence portion (represented by ''9'')' USING ERRCODE = '45003';
      ELSIF LV_Y > LV_NINE
      THEN
        RAISE EXCEPTION '%', 'The Year ''Y'' Portion has to be in front of the Sequence ''9''' ||
                                'Check System Profiles FORMAT/BOOKING_NO' USING ERRCODE = '45004';
      ELSE
        RETURN(TRUE);
      END IF;
    END IF;
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION OIIPROLL_get_unit_parent (p_living_unit_id bigint ) RETURNS bigint AS $body$
DECLARE

                c_p CURSOR FOR SELECT parent_living_unit_id
                              from v_living_unit_summaries
                              where LIVING_UNIT_ID = p_living_unit_id;
                 l_living_unit_id bigint;

BEGIN
                open c_p;
                fetch c_p into l_living_unit_id;
                if not found
                then
                                close c_p;
                                return(null);
                end if;
                close c_p;
                return(l_living_unit_id);
    EXCEPTION
       WHEN OTHERS
       THEN
              raise '% %',sqlerrm,sqlstate;
              return(null);
END;
$body$
LANGUAGE PLPGSQL
;


CREATE OR REPLACE FUNCTION OIDADMIS_get_new_booking_no() RETURNS character varying
 LANGUAGE plpgsql
AS 
$$
DECLARE 
    
    
    
    LV_DEFAULT_FORMAT VARCHAR(10) = '9999999999';
    LV_NEXT_BOOK_NO   DECIMAL(20);
    LV_BOOK_NO_FORMAT OFFENDER_BOOKINGS.BOOKING_NO%TYPE;
    LV_DASH_POS       INT;
    LV_BOOK_NO        OFFENDER_BOOKINGS.BOOKING_NO%TYPE;
    LV_NINE_COUNT     INT;
    LV_BOOK_NO_YEAR   OFFENDER_BOOKINGS.BOOKING_NO%TYPE;
    LV_NO_OF_YS       INT;
    
    
    
    BOOK_NO_FORMAT_CUR CURSOR FOR
      SELECT COALESCE(PROFILE_VALUE, LV_DEFAULT_FORMAT)
        FROM SYSTEM_PROFILES
       WHERE PROFILE_TYPE = 'FORMAT'
         AND PROFILE_CODE = 'BOOKING_NO';
    
    
    
    NEXT_BOOK_NO_C CURSOR FOR
      SELECT nextval('BOOKING_NO') FROM DUAL;
    
    
    
    NO_OF_NINES_CUR CURSOR FOR
      SELECT LENGTH(SUBSTR(LV_BOOK_NO_FORMAT,
                           (INSTR(LV_BOOK_NO_FORMAT, '9', 1))))
        FROM DUAL;

    
    CUR_BKG_DISPLAY CURSOR FOR
      SELECT PROFILE_VALUE
        FROM SYSTEM_PROFILES
       WHERE PROFILE_TYPE = 'CLIENT'
         AND PROFILE_CODE = 'BKG_DISPLAY';

    V_BKG_DISPLAY SYSTEM_PROFILES.PROFILE_VALUE%TYPE = NULL;
  BEGIN

    
    
    
    OPEN CUR_BKG_DISPLAY;
    FETCH CUR_BKG_DISPLAY
      INTO V_BKG_DISPLAY;
    CLOSE CUR_BKG_DISPLAY;

    IF V_BKG_DISPLAY != 'Y'
    THEN
      
      
      
      OPEN BOOK_NO_FORMAT_CUR;
      FETCH BOOK_NO_FORMAT_CUR
        INTO LV_BOOK_NO_FORMAT;
      CLOSE BOOK_NO_FORMAT_CUR;
      
      
      
      IF LV_BOOK_NO_FORMAT IS NULL
      THEN
        LV_BOOK_NO_FORMAT := LV_DEFAULT_FORMAT;
      END IF;
      
      
      
      IF OIDADMIS_BOOKING_NO_FORMAT_OK(LV_BOOK_NO_FORMAT)
      THEN
        
        
        
        LV_DASH_POS := INSTR(LV_BOOK_NO_FORMAT, '-', 1, 1);

        BEGIN
          OPEN NO_OF_NINES_CUR;
          FETCH NO_OF_NINES_CUR
            INTO LV_NINE_COUNT;
          CLOSE NO_OF_NINES_CUR;
        END;

        IF INSTR(LV_BOOK_NO_FORMAT, 'Y', -1, 1) > 0
        THEN
          BEGIN
            OPEN NEXT_BOOK_NO_C;
            FETCH NEXT_BOOK_NO_C
              INTO LV_NEXT_BOOK_NO;
            CLOSE NEXT_BOOK_NO_C;
          END;
          BEGIN
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            SELECT INSTR(LV_BOOK_NO_FORMAT,
                         'Y',
                         1,
                         CASE INSTR(LV_BOOK_NO_FORMAT, '-')
                                 WHEN 0 THEN 
                                INSTR(LV_BOOK_NO_FORMAT, '9')
                                 ELSE INSTR(LV_BOOK_NO_FORMAT, '-') END - 1)
              INTO LV_NO_OF_YS
              FROM DUAL;
            IF LV_NO_OF_YS = 4
            THEN
              LV_BOOK_NO_YEAR := TO_CHAR(CURRENT_TIMESTAMP, 'YYYY');
            ELSIF LV_NO_OF_YS = 3
            THEN
              LV_BOOK_NO_YEAR := TO_CHAR(CURRENT_TIMESTAMP, 'YYY');
            ELSIF LV_NO_OF_YS = 2
            THEN
              LV_BOOK_NO_YEAR := TO_CHAR(CURRENT_TIMESTAMP, 'YY');
            ELSIF LV_NO_OF_YS = 1
            THEN
              LV_BOOK_NO_YEAR := TO_CHAR(CURRENT_TIMESTAMP, 'Y');
            END IF;
          END;
          IF LV_DASH_POS = 0
          THEN
            
            LV_BOOK_NO := LV_BOOK_NO_YEAR ||SUBSTR(LPAD(LV_NEXT_BOOK_NO, LENGTH(LV_BOOK_NO_FORMAT) +1, '0'), LV_NINE_COUNT); 
          ELSE
            LV_BOOK_NO := LV_BOOK_NO_YEAR || '-' ||SUBSTR(LPAD(LV_NEXT_BOOK_NO, LENGTH(LV_BOOK_NO_FORMAT), '0'), LV_NINE_COUNT);
          END IF;
        ELSE
          BEGIN
            OPEN NEXT_BOOK_NO_C;
            FETCH NEXT_BOOK_NO_C
              INTO LV_NEXT_BOOK_NO;
            CLOSE NEXT_BOOK_NO_C;
          END;
          LV_BOOK_NO := LPAD(LV_NEXT_BOOK_NO::text, LV_NINE_COUNT, '0');
        END IF;
      ELSE
        RAISE EXCEPTION '%', 'Error selecting booking number.' USING ERRCODE = '45000';
      END IF;
    ELSE
      LV_BOOK_NO := NULL;
    END IF;
    RETURN(LV_BOOK_NO);

  END;
$$;

CREATE OR REPLACE FUNCTION userenv_number(
	parameter character varying)
    RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
begin
  if upper(parameter) = ('SESSIONID') then
    return pg_backend_pid();
  else 
    return NULL;
  end if;
end;
$BODY$;

CREATE OR REPLACE FUNCTION oms_owner.trust_trust_community_csld (p_csld_id text) RETURNS varchar AS $body$
DECLARE

      trust_cty_csld CURSOR FOR
         SELECT coalesce(community_trust_caseload, caseload_id)
           FROM caseloads
          WHERE caseload_id = p_csld_id;
      lv_cty_trust_csld caseloads.caseload_id%TYPE;

BEGIN
      OPEN trust_cty_csld;
      FETCH trust_cty_csld
         INTO lv_cty_trust_csld;
      CLOSE trust_cty_csld;
      RETURN(lv_cty_trust_csld);
   EXCEPTION
      WHEN OTHERS THEN
                 RAISE EXCEPTION '%', 'Error when processing transaction fees. ' USING ERRCODE = '45000';
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE FUNCTION to_text(integer) RETURNS text LANGUAGE sql IMMUTABLE STRICT AS
$$SELECT CASE WHEN $1<1 THEN NULL
              WHEN $1=1 THEN 'ONE'
              WHEN $1=2 THEN 'TWO'
              WHEN $1=3 THEN 'THREE'
              WHEN $1=4 THEN 'FOUR'
              WHEN $1=5 THEN 'FIVE'
              WHEN $1=6 THEN 'SIX'
              WHEN $1=7 THEN 'SEVEN'
              WHEN $1=8 THEN 'EIGHT'
              WHEN $1=9 THEN 'NINE'
              WHEN $1=10 THEN 'TEN'
              WHEN $1=11 THEN 'ELEVEN'
              WHEN $1=12 THEN 'TWELVE'
              WHEN $1=13 THEN 'THIRTEEN'
              WHEN $1=14 THEN 'FOURTEEN'
              WHEN $1=15 THEN 'FIFTEEN'
              WHEN $1=16 THEN 'SIXTEEN'
              WHEN $1=17 THEN 'SEVENTEEN'
              WHEN $1=18 THEN 'EIGHTEEN'
              WHEN $1=19 THEN 'NINETEEN'
              WHEN $1<100 THEN CASE
                 WHEN $1/10=2 THEN 'TWENTY' || COALESCE(' ' || to_text($1%10), '')
                 WHEN $1/10=3 THEN 'THIRTY' || COALESCE(' ' || to_text($1%10), '')
                 WHEN $1/10=4 THEN 'FOURTY' || COALESCE(' ' || to_text($1%10), '')
                 WHEN $1/10=5 THEN 'FIFTY' || COALESCE(' ' || to_text($1%10), '')
                 WHEN $1/10=6 THEN 'SIXTY' || COALESCE(' ' || to_text($1%10), '')
                 WHEN $1/10=7 THEN 'SEVENTY' || COALESCE(' ' || to_text($1%10), '')
                 WHEN $1/10=8 THEN 'EIGHTY' || COALESCE(' ' || to_text($1%10), '')
                 WHEN $1/10=9 THEN 'NINETY' || COALESCE(' ' || to_text($1%10), '')
              END
              WHEN $1<1000
                 THEN to_text($1/100) || ' HUNDRED' ||
                      COALESCE(' AND ' || to_text($1%100), '')
              WHEN $1<1000000
                 THEN to_text($1/1000) || ' THOUSAND' ||
                      CASE WHEN $1%1000 < 100
                         THEN COALESCE(' AND ' || to_text($1%1000), '')
                         ELSE COALESCE(' ' || to_text($1%1000), '')
                      END
              WHEN $1<1000000000
                 THEN to_text($1/1000000) || ' MILLION' ||
                      CASE WHEN $1%1000000 < 100
                         THEN COALESCE(' AND ' || to_text($1%1000000), '')
                         ELSE COALESCE(' ' || to_text($1%1000000), '')
                      END
              ELSE NULL
         END$$;

CREATE OR REPLACE FUNCTION oms_owner.TAG_HEADER_GET_AGE ( P_BIRTH_DATE OFFENDERS.BIRTH_DATE%TYPE ) RETURNS bigint AS $body$
DECLARE

      LV_YEARS bigint;
          LV_AGE   bigint    := NULL;

BEGIN
      IF (P_BIRTH_DATE IS NOT NULL AND P_BIRTH_DATE::text <> '') THEN
         LV_AGE := TRUNC(MONTHS_BETWEEN(date_trunc('day', sysdate()), date_trunc('day', P_BIRTH_DATE))/12);
      END IF;
          RETURN LV_AGE;
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.OMS_SEARCH_check_offender ( p_LastName text , p_BirthDate timestamp ) RETURNS bigint AS $body$
DECLARE

    --
    --
    ret_Exists numeric;

BEGIN
    SELECT     1
        INTO STRICT      ret_Exists
        FROM     offenders
        WHERE  last_name = p_LastName
        AND         birth_date::date =
                       p_BirthDate::date;
    RETURN ret_Exists;
    EXCEPTION
        WHEN no_data_found THEN
            RETURN 0;
        WHEN too_many_rows THEN
            RETURN 1;
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION OMS_SEARCH_GET_OFFENDER_ID () RETURNS bigint AS $body$
DECLARE

    --
    --
    l_off_id numeric;

BEGIN
    SELECT    nextval('offender_id')
        INTO STRICT      l_off_id
;
    RETURN l_off_id;
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION OMS_SEARCH_check_offender_id_display ( p_offender_id_display text ) RETURNS bigint AS $body$
DECLARE

    --
    --
    ret_Exists bigint;

BEGIN
    SELECT     1
        INTO STRICT      ret_Exists
        FROM     offenders
        WHERE  offender_id_display = p_offender_id_display  LIMIT 1;
    RETURN ret_Exists;
    EXCEPTION
        WHEN no_data_found THEN
            RETURN 0;
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION TAG_SERVICE_sort_earliest_weekday ( p_monday_flag COURSE_SCHEDULE_RULES.monday_flag%TYPE, p_tuesday_flag COURSE_SCHEDULE_RULES.tuesday_flag%TYPE, p_wednesday_flag COURSE_SCHEDULE_RULES.wednesday_flag%TYPE, p_thursday_flag COURSE_SCHEDULE_RULES.thursday_flag%TYPE, p_friday_flag COURSE_SCHEDULE_RULES.friday_flag%TYPE, p_saturday_flag COURSE_SCHEDULE_RULES.saturday_flag%TYPE, p_sunday_flag COURSE_SCHEDULE_RULES.sunday_flag%TYPE ) RETURNS bigint AS $body$
DECLARE

      v_sort_order   bigint := 0;

BEGIN
      IF coalesce( p_monday_flag, 'N' ) = 'Y'
      THEN
         v_sort_order := 1;
      ELSIF coalesce( p_tuesday_flag, 'N' ) = 'Y'
      THEN
         v_sort_order := 2;
      ELSIF coalesce( p_wednesday_flag, 'N' ) = 'Y'
      THEN
         v_sort_order := 3;
      ELSIF coalesce( p_thursday_flag, 'N' ) = 'Y'
      THEN
         v_sort_order := 4;
      ELSIF coalesce( p_friday_flag, 'N' ) = 'Y'
      THEN
         v_sort_order := 5;
      ELSIF coalesce( p_saturday_flag, 'N' ) = 'Y'
      THEN
         v_sort_order := 6;
      ELSIF coalesce( p_sunday_flag, 'N' ) = 'Y'
      THEN
         v_sort_order := 7;
      ELSE
         v_sort_order := 0;
      END IF;
      RETURN( v_sort_order );
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_date_time_compare_date_time ( P_DATE_1 text, P_TIME_1 text, P_DATE_2 text, P_TIME_2 text ) RETURNS bigint AS $body$
DECLARE

   V_DT_1   timestamp;
   V_TM_1   varchar(64);
   V_DT_2   timestamp;
   V_TM_2   varchar(64);

BEGIN
   IF (P_DATE_1 IS NOT NULL AND P_DATE_1::text <> '') AND (P_DATE_2 IS NOT NULL AND P_DATE_2::text <> '')
   THEN
      V_DT_1 := TRUNC(TO_DATE(P_DATE_1, 'DD-MM-RR HH24:MI:SS'));
      V_DT_2 := TRUNC(TO_DATE(P_DATE_2, 'DD-MM-RR HH24:MI:SS'));
   ELSE
      V_DT_1 := TO_DATE('01-01-80','DD-MM-RR HH24:MI:SS');
      V_DT_2 := TO_DATE('01-01-80','DD-MM-RR HH24:MI:SS');
   END IF;
   IF (P_TIME_1 IS NOT NULL AND P_TIME_1::text <> '') AND (P_TIME_2 IS NOT NULL AND P_TIME_2::text <> '')
   THEN
      V_TM_1 := SUBSTR(P_TIME_1, position(' ' in P_TIME_1) + 1, 8);
      V_TM_2 := SUBSTR(P_TIME_2, position(' ' in P_TIME_2) + 1, 8);
   ELSE
      V_TM_1 := '00:00:00';
      V_TM_2 := '00:00:00';
   END IF;
   IF V_DT_1 < V_DT_2
   THEN
      RETURN -1;
   ELSIF V_DT_1 = V_DT_2
   THEN
      IF V_TM_1 < V_TM_2
      THEN
         RETURN -1;
      ELSIF V_TM_1 = V_TM_2
      THEN
         RETURN 0;
      ELSE
         RETURN 1;
      END IF;
   ELSE
      RETURN 1;
   END IF;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION ocmteams_sort_on_function_desc ( P_CODE REFERENCE_CODES.CODE%TYPE ) RETURNS varchar AS $body$
DECLARE

     LV_DESCRIPTION  REFERENCE_CODES.DESCRIPTION%TYPE;
     REFERENCE_CODES_C CURSOR FOR
        SELECT DESCRIPTION
          FROM REFERENCE_CODES
         WHERE DOMAIN = 'FUNCTION'
           AND CODE = P_CODE;

BEGIN
     OPEN REFERENCE_CODES_C;
     FETCH REFERENCE_CODES_C INTO LV_DESCRIPTION;
     CLOSE REFERENCE_CODES_C;
     RETURN LV_DESCRIPTION;
  EXCEPTION
     WHEN OTHERS
     THEN
     raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION OIDADMIS_get_new_book_id () RETURNS bigint AS $body$
DECLARE

    LV_BOOK_ID OFFENDER_BOOKINGS.OFFENDER_BOOK_ID%TYPE;
    BOOK_ID_CUR CURSOR FOR
      SELECT nextval('offender_book_id');

BEGIN
    OPEN BOOK_ID_CUR;
    FETCH BOOK_ID_CUR
      INTO LV_BOOK_ID;
    IF  not FOUND
    THEN
      CLOSE BOOK_ID_CUR;
      RAISE EXCEPTION '%', 'ERROR - Could not get next offender_book_id' USING ERRCODE = '45014';
    END IF;
    CLOSE BOOK_ID_CUR;
    RETURN(LV_BOOK_ID);
  EXCEPTION
    WHEN OTHERS THEN
      RAISE EXCEPTION '%', 'Error selecting book_id -' || SQLERRM USING ERRCODE = '45015';
  END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION NON_ASSOCIATION_get_count_for_liv_unit (p_living_unit_id living_units.living_unit_id%TYPE) RETURNS bigint AS $body$
DECLARE

      lv_ob_cnt    bigint;
      lv_rbd_cnt   bigint;
      get_off_bkg_cnt_cur CURSOR FOR
         SELECT no_of_occupant
           FROM agency_internal_locations
          WHERE internal_location_id = p_living_unit_id;
      get_rev_bed_cnt_cur CURSOR FOR
         SELECT COUNT(1)
           FROM reserve_bed_locations rbl
          WHERE rbl.living_unit_id = p_living_unit_id AND rbl.reserve_until_date >= clock_timestamp()
                AND coalesce(rbl.remove_reason::text, '') = '';

BEGIN
      OPEN get_off_bkg_cnt_cur;
      FETCH get_off_bkg_cnt_cur
       INTO lv_ob_cnt;
      CLOSE get_off_bkg_cnt_cur;
      OPEN get_rev_bed_cnt_cur;
      FETCH get_rev_bed_cnt_cur
       INTO lv_rbd_cnt;
      CLOSE get_rev_bed_cnt_cur;
      RETURN(coalesce(lv_ob_cnt, 0) + coalesce(lv_rbd_cnt, 0));
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION OCDPERSO_getpersonname (p_person_id persons.person_id%TYPE) RETURNS varchar AS $body$
DECLARE

      get_names_cur CURSOR FOR
         SELECT last_name || first_name
           FROM persons
          WHERE person_id = p_person_id;
      lv_name   varchar(72);

BEGIN
      OPEN get_names_cur;
      FETCH get_names_cur
       INTO lv_name;
      CLOSE get_names_cur;
      RETURN lv_name;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_establishment_get_agy_loc_desc ( p_agy_loc_id agency_locations.agy_loc_id%TYPE ) RETURNS varchar AS $body$
DECLARE

      l_description   agency_locations.description%TYPE;
      agy_cur CURSOR FOR
         SELECT description
           FROM agency_locations
          WHERE agy_loc_id = p_agy_loc_id;

BEGIN
      OPEN agy_cur;
      FETCH agy_cur
       INTO l_description;
      CLOSE agy_cur;
      RETURN(l_description);
   EXCEPTION
      WHEN OTHERS
      THEN
        raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_service_sort_assessment_details(p_assessment_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE

      c_p CURSOR FOR
         SELECT assessment_code
           FROM ASSESSMENTS
          WHERE assessment_id = p_assessment_id;
      l_assessment_code   varchar(12 );

BEGIN
      OPEN c_p;
      FETCH c_p
       INTO l_assessment_code;
      if not FOUND
      THEN
         CLOSE c_p;
         RETURN( NULL );
      ELSE
         CLOSE c_p;
         RETURN( l_assessment_code );
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
       raise '% %',sqlerrm,sqlstate;
   END;
$function$
;

CREATE OR REPLACE FUNCTION NON_ASSOCIATION_GET_COUNT_FOR_LIV_UNIT (p_living_unit_id living_units.living_unit_id%TYPE) RETURNS bigint AS $body$
DECLARE

      lv_ob_cnt    bigint;
      lv_rbd_cnt   bigint;
      get_off_bkg_cnt_cur CURSOR FOR
         SELECT no_of_occupant
           FROM agency_internal_locations
          WHERE internal_location_id = p_living_unit_id;
      get_rev_bed_cnt_cur CURSOR FOR
         SELECT COUNT(1)
           FROM reserve_bed_locations rbl
          WHERE rbl.living_unit_id = p_living_unit_id AND rbl.reserve_until_date >= clock_timestamp()
                AND coalesce(rbl.remove_reason::text, '') = '';

BEGIN
      OPEN get_off_bkg_cnt_cur;
      FETCH get_off_bkg_cnt_cur
       INTO lv_ob_cnt;
      CLOSE get_off_bkg_cnt_cur;
      OPEN get_rev_bed_cnt_cur;
      FETCH get_rev_bed_cnt_cur
       INTO lv_rbd_cnt;
      CLOSE get_rev_bed_cnt_cur;
      RETURN(coalesce(lv_ob_cnt, 0) + coalesce(lv_rbd_cnt, 0));
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.OCMWORKS_GET_TEMPLATE_DESC (p_template_id iwp_templates.template_id%TYPE) RETURNS varchar AS $body$
DECLARE

        -- Cursor Defination
        get_template_desc_cur CURSOR FOR
            SELECT description
            FROM   iwp_templates
            WHERE  template_id = p_template_id;
        v_template_desc  iwp_templates.description%TYPE;

BEGIN
        OPEN get_template_desc_cur;
        FETCH get_template_desc_cur
            INTO v_template_desc;
        CLOSE get_template_desc_cur;
        RETURN(v_template_desc);
        EXCEPTION
            WHEN OTHERS
            THEN
                RETURN(v_template_desc);
                raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_INTERNAL_LOCATIONS_SORT_NON_ASSOCIATION ( p_domain reference_codes.domain%TYPE, p_code reference_codes.code%TYPE) RETURNS REFERENCE_CODES.DESCRIPTION%TYPE AS $body$
DECLARE

   desc_cur CURSOR FOR
      SELECT description
        FROM reference_codes
       WHERE domain = p_domain
         AND code = p_code;
   v_description   reference_codes.description%TYPE   := NULL;

BEGIN
   OPEN desc_cur;
   FETCH desc_cur  INTO v_description;
   CLOSE desc_cur;
   RETURN v_description;
EXCEPTION
   WHEN OTHERS
   THEN
      raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.TAG_INTERNAL_LOCATIONS_get_internal_location_usage_id () RETURNS INTERNAL_LOCATION_USAGES.INTERNAL_LOCATION_USAGE_ID%TYPE AS $body$
DECLARE

   id_cur CURSOR FOR
      SELECT nextval('internal_location_usage_id')
;
   v_id   internal_location_usages.internal_location_usage_id%TYPE;

BEGIN
   OPEN id_cur;
   FETCH id_cur INTO v_id;
   CLOSE id_cur;
   RETURN v_id;
EXCEPTION
   WHEN OTHERS
   THEN
      raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;

create or replace
function oms_owner.TAG_INTERNAL_LOCATIONS_SORT_USAGE_LOCATIONS ( p_internal_location_id agency_internal_locations.internal_location_id%type ) returns AGENCY_INTERNAL_LOCATIONS.INTERNAL_LOCATION_CODE%type as $body$ declare code_cur cursor for
select
	internal_location_code
from
	agency_internal_locations
where
	internal_location_id = p_internal_location_id;

v_code agency_internal_locations.internal_location_code%type;

begin open code_cur;

fetch code_cur
into
	v_code;

close code_cur;

return v_code;

exception
when others then raise '% %',sqlerrm,sqlstate;
end;

$body$ language PLPGSQL ;

create or replace
procedure NON_ASSOCIATION_check_stg_non_association ( p_living_unit_id bigint,
p_offender_id bigint,
p_enemy_found_flag inout text ) as $body$ declare
-- The parameters for this proc comes from non_assosiation proc
-- and it passes offender_id, though, for stg purpose we need offender_book_id
 l_offender_cur cursor(p_offender_id offenders.offender_id%type) for
select
	bkg.offender_book_id offender_book_id
from
	offender_bookings bkg,
	offenders off
where
	OFF.offender_id = p_offender_id
	and bkg.offender_id = OFF.offender_id
	and ( (bkg.active_flag = 'Y')
	or ( bkg.active_flag = 'N'
	and bkg.offender_book_id = (
	select
		MAX(ob.offender_book_id)
	from
		offender_bookings ob
	where
		ob.root_offender_id = bkg.root_offender_id
		and not exists (
		select
			'X'
		from
			offender_bookings ob3
		where
			ob3.root_offender_id = bkg.root_offender_id
			and ob3.active_flag = 'Y'))));

-- Retrieve Parent levels of location from setup
 l_parent_location_cur cursor(p_living_unit_id living_units.living_unit_id%type) for
with recursive cte as (
select
	olu.internal_location_id,parent_internal_location_id
from
	agency_internal_locations olu
where
	olu.internal_location_id = p_living_unit_id
union all
select
	olu.internal_location_id,olu.parent_internal_location_id
from
	agency_internal_locations olu
join cte c on
	(c.parent_internal_location_id = olu.internal_location_id) )
select
	internal_location_id
from
	cte;

-- Retrieve STG restriction from setup for the above cursor
 l_restrictions_cur cursor(p_internal_location_id agy_int_loc_profiles.internal_location_id%type) for
select
	internal_location_id
from
	agy_int_loc_profiles
where
	internal_location_id = p_internal_location_id
	and int_loc_profile_type = 'NON_ASSO_TYP'
	and int_loc_profile_code = 'STG';

-- Check if offender has an enemy assigned to a unit under the restriction setup
 l_check_enemy_cur cursor( p_offender_book_id offender_bookings.offender_book_id%type,
p_parent_living_unit_id living_units.living_unit_id%type ) for
with recursive cte as (
select
	'Y',living_unit_id
from
	living_units lu
where
	living_unit_id = p_parent_living_unit_id
union all
select
	'Y',lu.living_unit_id
from
	living_units lu
join cte c on
	(c.living_unit_id = lu.parent_living_unit_id) )
select 'Y' from (select
	'Y',living_unit_id
from
	cte
where
	exists (
	select
		1
	from
		offender_stg_affiliations osa,
		offender_stg_affiliations osa_enemy,
		stg_relationships sr,
		offender_bookings ob
	where
		osa.offender_book_id = p_offender_book_id
		and osa.active_flag = 'Y'
		and osa.stg_id = sr.stg_id
		and sr.relationship_type = 'ENEMY'
		and sr.active_flag = 'Y'
		and sr.related_stg_id = osa_enemy.stg_id
		and osa_enemy.offender_book_id = ob.offender_book_id
		and osa_enemy.active_flag = 'Y'
		and ob.active_flag = 'Y'
		and ob.living_unit_id =living_unit_id))A;

l_parent_location_id agy_int_loc_profiles.internal_location_id%type := null;

lv_parent_location_id agy_int_loc_profiles.internal_location_id%type := null;

l_offender_book_id offender_bookings.offender_book_id%type := null;

v_found_flag boolean := false;

l_enemy_found_flag varchar(1) := null;

begin if (p_offender_id is not null
and p_offender_id::text <> '') then open l_offender_cur(p_offender_id);

fetch l_offender_cur
into
	l_offender_book_id;

close l_offender_cur;
end if;

if (l_offender_book_id is not null
and l_offender_book_id::text <> '') then
-- l_parent_location_cur retrieves all parents of offender's location
-- and base on the logic behind the START WITH CONNECT BY PRIOR it retrieves
-- by order from child to parent so in the loop from child to parent. IF there
-- is a stg set up against the location,it will sit in l_parent_location_id and
-- the last one IS the parent location which has STG set up
 for all_rec in l_parent_location_cur(p_living_unit_id) loop open l_restrictions_cur(all_rec.internal_location_id);

fetch l_restrictions_cur
into
	lv_parent_location_id;

if found then

v_found_flag := true;

end if;

close l_restrictions_cur;

if v_found_flag = true then l_parent_location_id := lv_parent_location_id;
end if;
end loop;
-- When there is a location with STG restriction
 if (l_parent_location_id is not null
and l_parent_location_id::text <> '') then
-- Check if there is any Gang members under that location whome are enemy of
-- this offender
 open l_check_enemy_cur(l_offender_book_id,
l_parent_location_id);

fetch l_check_enemy_cur
into
	l_enemy_found_flag;

close l_check_enemy_cur;

if l_enemy_found_flag = 'Y' then p_enemy_found_flag := 'Y';
else p_enemy_found_flag := 'N';
end if;
-- When there is no STG set up under the current offender's agency location
 elsif coalesce(l_parent_location_id::text, '') = '' then p_enemy_found_flag := 'N';
end if;
else
-- when offender is not active the proc will assume that he has not have
-- any enemies
 p_enemy_found_flag := 'N';
end if;

exception
when others then raise '% %',
sqlerrm,
sqlstate;
end;

$body$ language PLPGSQL ;


create or replace
function oms_owner.non_association_check_non_association ( p_offender_id bigint,
p_liv_unit_id bigint ) returns varchar as $body$ declare lv_conflict boolean := false;

l_enemy_found_flag text := null;

lv_return varchar(1);

lv1_return varchar(1) := null;

lv2_return varchar(1) := null;

na_cur cursor(p_offender_id offenders.offender_id%type) for
select
	bkg.living_unit_id living_unit_id,
	bkg.offender_book_id offender_book_id,
	OFF.offender_id_display offender_id_display,
	OFF.last_name last_name,
	OFF.first_name first_name,
	ona.ns_type ns_type
from
	offender_bookings bkg,
	offender_na_details ona,
	offenders off
where
	bkg.offender_id = OFF.offender_id
	and bkg.root_offender_id = ona.ns_offender_id
	and ona.offender_id = (
	select
		root_offender_id
	from
		offenders
	where
		offender_id = p_offender_id)
	and date_trunc('day', clock_timestamp()) between ona.ns_effective_date and coalesce(ona.ns_expiry_date, TO_DATE('01/01/3000', 'DD/MM/YYYY'));


begin 
for na_rec in na_cur(p_offender_id) loop if non_association_chk_na_liv_unit_conflict(p_liv_unit_id,
na_rec.living_unit_id,
na_rec.ns_type) then lv_conflict := true;

exit;
end if;
end loop;

if lv_conflict then lv1_return := 'N';
else lv1_return := 'Y';
end if;
-- Sarah: called check_stg_non_asociation in check_non_association
if p_offender_id is not null and p_offender_id::text <> '' then CALL NON_ASSOCIATION_check_stg_non_association(p_liv_unit_id,p_offender_id,l_enemy_found_flag);
end if;

if l_enemy_found_flag = 'Y' then lv2_return := 'N';
else lv2_return := 'Y';
end if;
-- The reason when lv_conflict is true or l_enemy_found_flag = 'Y'
-- the return value is NO :
-- the way that this function is calling  likes
-- DECODE(non_association.check_non_association(:p_offender_id, lu.living_unit_id),'Y','N','Y'))
-- And the final result decides the offender conflict flag should be
-- checked or unchecked
 if lv2_return = 'Y'
and lv1_return = 'Y' then
-- when there is no non_association and no STG conflicts
 lv_return := 'Y';

elsif lv2_return = 'Y'
and lv1_return = 'N'
or lv2_return = 'N'
and lv1_return = 'N'
or lv2_return = 'N'
and lv1_return = 'Y' then lv_return := 'N';
end if;

return(lv_return);
--Added exception
 exception
when others then raise '% %',sqlerrm,sqlstate;
end;

$body$ language PLPGSQL ;

CREATE OR REPLACE FUNCTION oms_owner.omuavbed_check_security (P_OFF_BOOK_ID OFFENDER_BOOKINGS.OFFENDER_BOOK_ID%TYPE ,P_LIVING_UNIT_ID LIVING_UNITS.LIVING_UNIT_ID%TYPE) RETURNS varchar AS $body$
DECLARE

      LV_SUP_LEVEL      OFFENDER_ASSESSMENTS.REVIEW_SUP_LEVEL_TYPE%TYPE;
      LV_ASSESSMENT_SEQ OFFENDER_ASSESSMENTS.ASSESSMENT_SEQ%TYPE;
      CHECK_LIV_UNIT_SECURITY_CUR CURSOR FOR
         SELECT COUNT(*)
           FROM LIVING_UNIT_PROFILES
          WHERE LIVING_UNIT_ID = P_LIVING_UNIT_ID
            AND INT_LOC_PROFILE_TYPE = 'SUP_LVL_TYPE'
            AND INT_LOC_PROFILE_CODE = LV_SUP_LEVEL;
      GET_REV_SUP_LEVEL_CUR CURSOR FOR
         SELECT MAX(OFF_ASSMNT.ASSESSMENT_SEQ)
           FROM OFFENDER_ASSESSMENTS OFF_ASSMNT, ASSESSMENTS ASSMNT
          WHERE OFF_ASSMNT.OFFENDER_BOOK_ID = P_OFF_BOOK_ID
            AND OFF_ASSMNT.ASSESS_STATUS = 'A'
            AND OFF_ASSMNT.EVALUATION_RESULT_CODE = 'APP'
            AND OFF_ASSMNT.ASSESSMENT_TYPE_ID = ASSMNT.ASSESSMENT_ID
            AND ASSMNT.CASELOAD_TYPE = 'INST';
      LV_PASS_SECURITY bigint;
      GET_SUP_LEVEL_CUR CURSOR(CP_ASS_SEQ bigint) FOR
         SELECT OFF_ASS.REVIEW_SUP_LEVEL_TYPE
           FROM OFFENDER_ASSESSMENTS OFF_ASS
          WHERE OFF_ASS.OFFENDER_BOOK_ID = P_OFF_BOOK_ID
            AND OFF_ASS.ASSESSMENT_SEQ = CP_ASS_SEQ;

BEGIN
      OPEN GET_REV_SUP_LEVEL_CUR;
      FETCH GET_REV_SUP_LEVEL_CUR
         INTO LV_ASSESSMENT_SEQ;
      CLOSE GET_REV_SUP_LEVEL_CUR;
      OPEN GET_SUP_LEVEL_CUR(LV_ASSESSMENT_SEQ);
      FETCH GET_SUP_LEVEL_CUR
         INTO LV_SUP_LEVEL;
      CLOSE GET_SUP_LEVEL_CUR;
      IF coalesce(LV_SUP_LEVEL::text, '') = ''
      THEN
         RETURN 'Y';
      ELSE
         OPEN CHECK_LIV_UNIT_SECURITY_CUR;
         FETCH CHECK_LIV_UNIT_SECURITY_CUR
            INTO LV_PASS_SECURITY;
         CLOSE CHECK_LIV_UNIT_SECURITY_CUR;
         IF LV_PASS_SECURITY > 0
         THEN
            RETURN 'Y';
         ELSE
            RETURN 'N';
         END IF;
      END IF;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_ASSESSMENT_chk_bkg_csa ( p_offender_book_id OFFENDER_BOOKINGS.offender_book_id%TYPE ) RETURNS varchar AS $body$
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
      RETURN( lv_conflict );
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;



CREATE OR REPLACE FUNCTION oms_owner.TAG_ASSESSMENT_chk_occupant_csa ( p_liv_unit_id living_units.living_unit_id%TYPE ) RETURNS varchar AS $body$
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
      RETURN( lv_conflict );
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.omuavbed_chk_cs_conflict (P_OFFENDER_BOOK_ID OFFENDER_BOOKINGS.OFFENDER_BOOK_ID%TYPE ,P_LIV_UNIT_ID LIVING_UNITS.LIVING_UNIT_ID%TYPE ,P_NO_OCCUPANTS bigint) RETURNS varchar AS $body$
DECLARE

      LV_CONFLICT varchar(1) := 'N';

BEGIN
      IF P_NO_OCCUPANTS > 0
      THEN
         LV_CONFLICT := TAG_ASSESSMENT_CHK_BKG_CSA(P_OFFENDER_BOOK_ID);
         IF LV_CONFLICT = 'N'
         THEN
            LV_CONFLICT := TAG_ASSESSMENT_CHK_OCCUPANT_CSA(P_LIV_UNIT_ID);
         END IF;
      END IF;
      RETURN(LV_CONFLICT);
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.tag_int_loc_level_code (p_description text, p_level bigint) RETURNS varchar AS $body$
DECLARE

--
-- To Return the level code of the internal location description
-- MODIFICATION HISTORY
-- Person      Date        Comments
-- ---------   ------      -------------------------------------------
-- David Ng    14 Dec 2005 First Draft
      lv_desc    varchar(100);
      lv_count   bigint;
      lv_pos     bigint;

BEGIN
      lv_desc := p_description;
      FOR lv_count IN 1 .. p_level
      LOOP
         lv_pos := position('-' in lv_desc);
         IF (lv_pos = 0)
         THEN
            lv_desc := NULL;
         ELSE
            lv_desc := SUBSTR(lv_desc, lv_pos + 1, LENGTH(lv_desc) - lv_pos);
         END IF;
      END LOOP;
      lv_pos := position('-' in lv_desc);
      IF (lv_pos > 0)
      THEN
         lv_desc := SUBSTR(lv_desc, 1, lv_pos - 1);
      END IF;
      RETURN lv_desc;
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.non_association_check_security ( p_off_book_id offender_bookings.offender_book_id%TYPE, p_living_unit_id living_units.living_unit_id%TYPE ) RETURNS varchar AS $body$
DECLARE

      --
      -- Work Variables
      --
      lv_sup_level       offender_assessments.review_sup_level_type%TYPE;
      --
      -- Cursor
      --
      check_liv_unit_security_cur CURSOR FOR
         SELECT COUNT(*)
           FROM living_unit_profiles
          WHERE living_unit_id = p_living_unit_id
            AND int_loc_profile_type = 'SUP_LVL_TYPE'
            AND int_loc_profile_code = lv_sup_level;
      get_rev_sup_level_cur CURSOR FOR
         SELECT off_assmnt.review_sup_level_type
           FROM offender_assessments off_assmnt, assessments assmnt
          WHERE off_assmnt.offender_book_id = p_off_book_id
            AND off_assmnt.assess_status = 'A'
            AND off_assmnt.evaluation_result_code = 'APP'
            AND off_assmnt.assessment_type_id = assmnt.assessment_id
            AND assmnt.caseload_type = 'INST';
      lv_pass_security   bigint;

BEGIN
      OPEN get_rev_sup_level_cur;
      FETCH get_rev_sup_level_cur
       INTO lv_sup_level;
      CLOSE get_rev_sup_level_cur;
      IF coalesce(lv_sup_level::text, '') = ''
      THEN
         RETURN 'Y';
      ELSE
         OPEN check_liv_unit_security_cur;
         FETCH check_liv_unit_security_cur
          INTO lv_pass_security;
         CLOSE check_liv_unit_security_cur;
         IF lv_pass_security > 0
         THEN
            RETURN 'Y';
         ELSE
            RETURN 'N';
         END IF;
      END IF;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.OMUAVBED_get_profile_id (P_LIVING_UNIT_ID LIVING_UNITS.LIVING_UNIT_ID%TYPE) RETURNS LIVING_UNITS.LIVING_UNIT_ID%TYPE AS $body$
DECLARE

      PROFILE_ID_CUR CURSOR FOR
         SELECT PROFILE_ID
           FROM LIVING_UNIT_PROFILES
          WHERE LIVING_UNIT_ID = P_LIVING_UNIT_ID;
      LV_PROFILE_ID LIVING_UNITS.LIVING_UNIT_ID%TYPE;

BEGIN
      OPEN PROFILE_ID_CUR;
      FETCH PROFILE_ID_CUR
         INTO LV_PROFILE_ID;
      CLOSE PROFILE_ID_CUR;
      RETURN LV_PROFILE_ID;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_SERVICE_SCHED_NO_SESSIONS_DONE (p_crs_acty_id course_activities.crs_acty_id%TYPE) RETURNS integer AS $body$
DECLARE

      lv_max_session   integer := 0;
      lv_start_session integer := 1;
      max_cur CURSOR FOR
         SELECT coalesce(MAX(session_no), 0)
           FROM course_schedules
          WHERE coalesce(catch_up_crs_sch_id::text, '') = ''
            AND schedule_status = 'SCH'
            AND crs_acty_id IN (WITH RECURSIVE cte AS (
SELECT crs_acty_id
                   FROM course_activities WHERE crs_acty_id = p_crs_acty_id
  UNION ALL
SELECT ca.crs_acty_id
                   FROM course_activities ca JOIN cte c ON (c.crs_acty_id = ca.parent_crs_acty_id)

) SELECT * FROM cte
);
      start_cur CURSOR FOR
         SELECT tag_service_sched.get_start_session_number(crs_acty_id,
                                                           course_class,
                                                           list_seq,
                                                           parent_crs_acty_id)
           FROM course_activities
          WHERE crs_acty_id = p_crs_acty_id;

BEGIN
      OPEN max_cur;
      FETCH max_cur
         INTO lv_max_session;
      CLOSE max_cur;
      OPEN start_cur;
      FETCH start_cur
         INTO lv_start_session;
      CLOSE start_cur;
      IF lv_max_session = 0
      THEN
         RETURN(0);
      ELSE
         RETURN(lv_max_session - lv_start_session + 1);
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
       raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.TAG_VISITS_IS_ADULT (p_visitor offender_visit_visitors.offender_visit_id%TYPE, p_type text, p_limit bigint, p_visit_date timestamp) RETURNS bigint AS $body$
DECLARE

      l_birthdate timestamp;
      c_per CURSOR FOR
         SELECT birthdate
           FROM persons
          WHERE person_id = p_visitor;
      c_off CURSOR FOR
         SELECT b.birth_date
           FROM offenders         b,
                offender_bookings ob
          WHERE ob.offender_book_id = p_visitor
            AND ob.offender_id = b.offender_id;

BEGIN
      IF coalesce(p_visitor::text, '') = '' THEN
         RETURN(0);
      END IF;
      IF p_type = 'PER' THEN
         OPEN c_per;
         FETCH c_per
            INTO l_birthdate;
         CLOSE c_per;
      ELSE
         OPEN c_off;
         FETCH c_off
            INTO l_birthdate;
         CLOSE c_off;
      END IF;
      IF (coalesce(l_birthdate::text, '') = '') OR (trunc(months_between(date_trunc('day', p_visit_date), date_trunc('day', l_birthdate)) / 12) >=
         p_limit) THEN
         RETURN(1);
      ELSE
         RETURN(0);
      END IF;
   END; --Function
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION soundex(input text) RETURNS text
IMMUTABLE STRICT COST 500 LANGUAGE plpgsql
AS $$
DECLARE
  soundex text = '';
  char text;
  symbol text;
  last_symbol text = '';
  pos int = 1;
BEGIN
  WHILE length(soundex) < 4 LOOP
    char = upper(substr(input, pos, 1));
    pos = pos + 1;
    CASE char
    WHEN '' THEN
      -- End of input string
      IF soundex = '' THEN
        RETURN '';
      ELSE
        RETURN rpad(soundex, 4, '0');
      END IF;
    WHEN 'B', 'F', 'P', 'V' THEN
      symbol = '1';
    WHEN 'C', 'G', 'J', 'K', 'Q', 'S', 'X', 'Z' THEN
      symbol = '2';
    WHEN 'D', 'T' THEN
      symbol = '3';
    WHEN 'L' THEN
      symbol = '4';
    WHEN 'M', 'N' THEN
      symbol = '5';
    WHEN 'R' THEN
      symbol = '6';
    ELSE
      -- Not a consonant; no output, but next similar consonant will be re-recorded
      symbol = '';
    END CASE;

    IF soundex = '' THEN
      -- First character; only accept strictly English ASCII characters
      IF char ~>=~ 'A' AND char ~<=~ 'Z' THEN
        soundex = char;
        last_symbol = symbol;
      END IF;
    ELSIF last_symbol != symbol THEN
      soundex = soundex || symbol;
      last_symbol = symbol;
    END IF;
  END LOOP;

  RETURN soundex;
END;
$$;
CREATE OR REPLACE FUNCTION TAG_WORKFLOW_ADM_sort_by_team_name ( P_TEAM_ID TEAMS.TEAM_ID%TYPE ) RETURNS varchar AS $body$
DECLARE

      L_TEAM_NAME   TEAMS.DESCRIPTION%TYPE;
      L_NUM         bigint;

BEGIN
      IF (P_TEAM_ID IS NOT NULL AND P_TEAM_ID::text <> '')
      THEN
         SELECT DESCRIPTION
           INTO STRICT L_TEAM_NAME
           FROM TEAMS
          WHERE TEAM_ID = P_TEAM_ID;
      END IF;
      RETURN( L_TEAM_NAME );
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;
CREATE OR REPLACE FUNCTION oms_owner.OMS_UTILS_GET_STAFF_NAME (p_staff_id staff_members.staff_id%TYPE) RETURNS varchar AS $body$
DECLARE

   staff_cur CURSOR FOR
      SELECT last_name || ',' || first_name
        FROM staff_members
       WHERE staff_id = p_staff_id;
   v_name   varchar(75);

BEGIN
   OPEN staff_cur;
   FETCH staff_cur INTO v_name;
   CLOSE staff_cur;
   RETURN v_name;
EXCEPTION
   WHEN OTHERS
   THEN
      raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_ESTABLISHMENT_GET_AGY_LOC_DESC ( p_agy_loc_id agency_locations.agy_loc_id%TYPE ) RETURNS varchar AS $body$
DECLARE

      l_description   agency_locations.description%TYPE;
      agy_cur CURSOR FOR
         SELECT description
           FROM agency_locations
          WHERE agy_loc_id = p_agy_loc_id;

BEGIN
      OPEN agy_cur;
      FETCH agy_cur
       INTO l_description;
      CLOSE agy_cur;
      RETURN(l_description);
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.OSINAMES_GET_OFFENDER_NAME ( P_OFFENDER_BOOK_ID V_HEADER_BLOCK.OFFENDER_BOOK_ID%TYPE ) RETURNS varchar AS $body$
DECLARE

   C1 CURSOR FOR
      SELECT LAST_NAME || ', ' || FIRST_NAME || ' ' || OFFENDER_ID_DISPLAY
        FROM V_HEADER_BLOCK
       WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID;
   V_NAME   varchar(100);

BEGIN
   OPEN C1;
   FETCH C1
    INTO V_NAME;
   CLOSE C1;
   RETURN V_NAME;
EXCEPTION
   WHEN OTHERS
   THEN
      raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_unpaid_work_get_pqs_value ( p_offender_id OFFENDER_PLACEMENT_SCORES.root_offender_id%TYPE, p_crs_acty_id COURSE_ACTIVITY_REVIEWS.crs_acty_id%TYPE ) RETURNS bigint AS $body$
DECLARE

      v_pqs_value     bigint                                           := 0;
      v_off_root_id   OFFENDER_PLACEMENT_SCORES.root_offender_id%TYPE;
      v_lifestyle     OFFENDER_PLACEMENT_SCORES.lifestyle_score%TYPE;
      v_emotional     OFFENDER_PLACEMENT_SCORES.emotional_score%TYPE;
      v_attitude      OFFENDER_PLACEMENT_SCORES.attitude_score%TYPE;
      v_behaviour     OFFENDER_PLACEMENT_SCORES.behaviour_score%TYPE;
      v_eduaction     OFFENDER_PLACEMENT_SCORES.education_score%TYPE;
      v_beneficiary   COURSE_ACTIVITY_REVIEWS.beneficiary_score%TYPE;
      v_usefulness    COURSE_ACTIVITY_REVIEWS.usefulness_score%TYPE;
      v_empathy       COURSE_ACTIVITY_REVIEWS.empathy_score%TYPE;
      v_cognitive     COURSE_ACTIVITY_REVIEWS.cognitive_score%TYPE;
      v_employment    COURSE_ACTIVITY_REVIEWS.employment_score%TYPE;
      c_offender_id CURSOR(
         p_off_book_id      v_header_block.offender_book_id%TYPE
      )
      FOR
         SELECT root_offender_id
           FROM v_header_block
          WHERE offender_book_id = p_off_book_id;
      c_off_scores CURSOR(
         p_offender_id      OFFENDER_PLACEMENT_SCORES.root_offender_id%TYPE
      )
      FOR
         SELECT lifestyle_score, emotional_score, attitude_score,
                behaviour_score, education_score
           FROM OFFENDER_PLACEMENT_SCORES opa1
          WHERE opa1.root_offender_id = p_offender_id
            AND assessment_date =
                        (SELECT MAX(assessment_date)
                           FROM OFFENDER_PLACEMENT_SCORES opa2
                          WHERE opa2.root_offender_id = opa1.root_offender_id);
      c_proj_scores CURSOR(
         p_crs_acty_id      COURSE_ACTIVITY_REVIEWS.crs_acty_id%TYPE
      )
      FOR
         SELECT beneficiary_score, usefulness_score, empathy_score,
                cognitive_score, employment_score
           FROM COURSE_ACTIVITY_REVIEWS ocr1
          WHERE ocr1.crs_acty_id = p_crs_acty_id
            AND review_date = (SELECT MAX(review_date)
                                 FROM COURSE_ACTIVITY_REVIEWS ocr2
                                WHERE ocr2.crs_acty_id = ocr1.crs_acty_id);

BEGIN
      OPEN c_offender_id(p_offender_id);
      FETCH c_offender_id
       INTO v_off_root_id;
      CLOSE c_offender_id;
      OPEN c_off_scores(v_off_root_id);
      FETCH c_off_scores
       INTO v_lifestyle, v_emotional, v_attitude, v_behaviour, v_eduaction;
      CLOSE c_off_scores;
      OPEN c_proj_scores(p_crs_acty_id);
      FETCH c_proj_scores
       INTO v_beneficiary, v_usefulness, v_empathy, v_cognitive,
            v_employment;
      CLOSE c_proj_scores;
      v_pqs_value :=
         calc_pqs(v_lifestyle,
                   v_emotional,
                   v_attitude,
                   v_behaviour,
                   v_eduaction,
                   v_beneficiary,
                   v_usefulness,
                   v_empathy,
                   v_cognitive,
                   v_employment
                  );
      RETURN ROUND(v_pqs_value);
   EXCEPTION
      WHEN OTHERS
      THEN
raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_unpaid_work_calc_pqs ( p_off_lifestyle bigint, p_off_emotion bigint, p_off_attitude bigint, p_off_behaviour bigint, p_off_employability bigint, p_proj_benefactor bigint, p_proj_usful_work bigint, p_proj_empathy bigint, p_proj_cognitive_skill bigint, p_proj_employment_skill bigint ) RETURNS bigint AS $body$
DECLARE

      -- ICMS Weighted Scores.
      icms_score_1   CONSTANT bigint := -10;
      icms_score_2   CONSTANT bigint := 45;
      icms_score_3   CONSTANT bigint := 100;
      icms_score_4   CONSTANT bigint := 98;
      icms_score_5   CONSTANT bigint := 96;
      combinations   CONSTANT bigint := 13;
      proj_scores             bigint[];
      off_scores              bigint[];
      v_total_score           bigint := 0;
      v_value                 bigint := NULL;

BEGIN
      proj_scores[1] := p_proj_benefactor;
      proj_scores[2] := p_proj_usful_work;
      proj_scores[3]:= p_proj_empathy;
      proj_scores[4] := p_proj_cognitive_skill;
      proj_scores[5] := p_proj_employment_skill;
      off_scores[1] := p_off_lifestyle;
      off_scores[2] := p_off_emotion;
      off_scores[3] := p_off_attitude;
      off_scores[4] := p_off_behaviour;
      off_scores[5] := p_off_employability;
      FOR v_proj_count IN 1 .. 5
      LOOP
         FOR v_off_count IN 1 .. 5
         LOOP
            IF (v_proj_count = 1 AND v_off_count = 1)
               OR (v_proj_count = 1 AND v_off_count = 2)
               OR (v_proj_count = 1 AND v_off_count = 3)
               OR (v_proj_count = 1 AND v_off_count = 4)
               OR (v_proj_count = 2 AND v_off_count = 1)
               OR (v_proj_count = 2 AND v_off_count = 2)
               OR (v_proj_count = 2 AND v_off_count = 3)
               OR (v_proj_count = 3 AND v_off_count = 1)
               OR (v_proj_count = 3 AND v_off_count = 2)
               OR (v_proj_count = 3 AND v_off_count = 3)
               OR (v_proj_count = 3 AND v_off_count = 4)
               OR (v_proj_count = 4 AND v_off_count = 4)
               OR (v_proj_count = 5 AND v_off_count = 5)
            THEN
               v_value :=
                         proj_scores[v_proj_count]
                         - off_scores[v_off_count];
               CASE v_value
                  WHEN -2
                  THEN
                     v_total_score := v_total_score + icms_score_1;
                  WHEN -1
                  THEN
                     v_total_score := v_total_score + icms_score_2;
                  WHEN 0
                  THEN
                     v_total_score := v_total_score + icms_score_3;
                  WHEN 1
                  THEN
                     v_total_score := v_total_score + icms_score_4;
                  WHEN 2
                  THEN
                     v_total_score := v_total_score + icms_score_5;
                  ELSE
                     NULL;
               END CASE;
            ELSE
               NULL;
            END IF;
         END LOOP;
      END LOOP;
      RETURN(v_total_score / combinations);
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.osinames_getoffid (P_OFF_BOOK_ID OFFENDER_BOOKINGS.OFFENDER_BOOK_ID%TYPE) RETURNS OFFENDER_BOOKINGS.OFFENDER_ID%TYPE AS $body$
DECLARE

   GET_OFF_ID_CUR CURSOR FOR
      SELECT OFFENDER_ID
        FROM OFFENDER_BOOKINGS
       WHERE OFFENDER_BOOK_ID = P_OFF_BOOK_ID;
   LV_OFF_ID   OFFENDERS.OFFENDER_ID%TYPE;

BEGIN
   OPEN GET_OFF_ID_CUR;
   FETCH GET_OFF_ID_CUR
    INTO LV_OFF_ID;
   CLOSE GET_OFF_ID_CUR;
   RETURN LV_OFF_ID;
EXCEPTION
   WHEN OTHERS
   THEN
      raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.osinames_getoffname (P_OFF_ID OFFENDERS.OFFENDER_ID%TYPE) RETURNS varchar AS $body$
DECLARE

   GET_OFF_NAME_CUR CURSOR FOR
      SELECT LAST_NAME || ', ' || FIRST_NAME
        FROM OFFENDERS
       WHERE OFFENDER_ID = P_OFF_ID;
   LV_OFF_NAME   varchar(72);

BEGIN
   OPEN GET_OFF_NAME_CUR;
   FETCH GET_OFF_NAME_CUR
    INTO LV_OFF_NAME;
   CLOSE GET_OFF_NAME_CUR;
   RETURN LV_OFF_NAME;
EXCEPTION
   WHEN OTHERS
   THEN
      raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_service_sched_get_start_session_number (p_crs_acty_id course_activities.crs_acty_id%TYPE ,p_course_class course_activities.course_class%TYPE ,p_list_seq course_activities.list_seq%TYPE ,p_parent_crs_acty_id course_activities.parent_crs_acty_id%TYPE) RETURNS integer AS $body$
DECLARE

      lv_last_session_no integer := 0;
      ssn_cur CURSOR FOR
         SELECT coalesce(SUM(no_of_sessions), 0)
           FROM course_activities
          WHERE parent_crs_acty_id = p_parent_crs_acty_id
            AND list_seq < p_list_seq;

BEGIN
      IF p_course_class = 'CRS_MOD'
      THEN
         OPEN ssn_cur;
         FETCH ssn_cur
            INTO lv_last_session_no;
         CLOSE ssn_cur;
      END IF;
      RETURN(lv_last_session_no + 1);
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;
-- REVOKE ALL ON FUNCTION oms_owner.tag_service_sched_get_start_session_number (p_crs_acty_id course_activities.crs_acty_id%TYPE ,p_course_class course_activities.course_class%TYPE ,p_list_seq course_activities.list_seq%TYPE ,p_parent_crs_acty_id course_activities.parent_crs_acty_id%TYPE) FROM PUBLIC;



CREATE OR REPLACE FUNCTION oms_owner.tag_service_sched_no_sessions_done (p_crs_acty_id course_activities.crs_acty_id%TYPE) RETURNS integer AS $body$
DECLARE

      lv_max_session   integer := 0;
      lv_start_session integer := 1;
      max_cur CURSOR FOR
         SELECT coalesce(MAX(session_no), 0)
           FROM course_schedules
          WHERE coalesce(catch_up_crs_sch_id::text, '') = ''
            AND schedule_status = 'SCH'
            AND crs_acty_id IN (WITH RECURSIVE cte AS (
SELECT crs_acty_id
                   FROM course_activities WHERE crs_acty_id = p_crs_acty_id
  UNION ALL
SELECT ca.crs_acty_id
                   FROM course_activities ca JOIN cte c ON (c.crs_acty_id = parent_crs_acty_id)

) SELECT * FROM cte
);
      start_cur CURSOR FOR
         SELECT tag_service_sched_get_start_session_number(crs_acty_id,
                                                           course_class,
                                                           list_seq,
                                                           parent_crs_acty_id)
           FROM course_activities
          WHERE crs_acty_id = p_crs_acty_id;

BEGIN
      OPEN max_cur;
      FETCH max_cur
         INTO lv_max_session;
      CLOSE max_cur;
      OPEN start_cur;
      FETCH start_cur
         INTO lv_start_session;
      CLOSE start_cur;
      IF lv_max_session = 0
      THEN
         RETURN(0);
      ELSE
         RETURN(lv_max_session - lv_start_session + 1);
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
        raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_service_sched_get_start_session_number (p_crs_acty_id course_activities.crs_acty_id%TYPE ,p_course_class course_activities.course_class%TYPE ,p_list_seq course_activities.list_seq%TYPE ,p_parent_crs_acty_id course_activities.parent_crs_acty_id%TYPE) RETURNS integer AS $body$
DECLARE

      lv_last_session_no integer := 0;
      ssn_cur CURSOR FOR
         SELECT coalesce(SUM(no_of_sessions), 0)
           FROM course_activities
          WHERE parent_crs_acty_id = p_parent_crs_acty_id
            AND list_seq < p_list_seq;

BEGIN
      IF p_course_class = 'CRS_MOD'
      THEN
         OPEN ssn_cur;
         FETCH ssn_cur
            INTO lv_last_session_no;
         CLOSE ssn_cur;
      END IF;
      RETURN(lv_last_session_no + 1);
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

create or replace
function oms_owner.IWP_10G_is_template_accessible (p_template_id iwp_templates.template_id%type ,
p_module_name iwp_template_modules.module_name%type) returns varchar as $body$ declare
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
		and sm.user_id = upper(user))
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

CREATE OR REPLACE FUNCTION oms_owner.TAG_UTILS_get_staff_id (P_USER_ID STAFF_MEMBERS.USER_ID%TYPE) RETURNS STAFF_MEMBERS.STAFF_ID%TYPE AS $body$
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

CREATE OR REPLACE FUNCTION TAG_SCHEDULE_CHECK_SCHEDULE_CONFLICT ( P_OFFENDER_BOOK_ID OFFENDER_BOOKINGS.OFFENDER_BOOK_ID%TYPE, P_EVENT_DATE timestamp ) RETURNS bigint AS $body$
DECLARE

      V_COUNT   bigint;

BEGIN
      SELECT COUNT(*)
        INTO STRICT V_COUNT
        FROM V_OFFENDER_ALL_SCHEDULES_2
       WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
         AND EVENT_STATUS = 'SCH'
         AND EVENT_DATE = P_EVENT_DATE;
      RETURN V_COUNT;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_service_sort_reference_code ( p_code REFERENCE_CODES.code%TYPE, p_domain REFERENCE_CODES.domain%TYPE ) RETURNS varchar AS $body$
DECLARE

      c_p CURSOR FOR
         SELECT description, list_seq
           FROM REFERENCE_CODES
          WHERE code = p_code
            AND domain = p_domain;
      l_description   REFERENCE_CODES.description%TYPE;
      l_list_seq      REFERENCE_CODES.list_seq%TYPE;

BEGIN
      OPEN c_p;
      FETCH c_p
       INTO l_description, l_list_seq;
      IF not found
      THEN
         CLOSE c_p;
         RETURN( NULL );
      ELSE
         CLOSE c_p;
         RETURN(    LPAD( (coalesce( l_list_seq, 0 ) )::varchar, 12, '0' )
                  || RPAD( l_description, 40, ' ' )
                  || p_code );
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_INCIDENTS_SORT_AGENCY_DESC (p_internal_location_id agency_internal_locations.internal_location_id%TYPE) RETURNS varchar AS $body$
DECLARE

      c_desc CURSOR FOR
         SELECT al.description
           FROM agency_internal_locations agy_intl, agency_locations al
          WHERE agy_intl.internal_location_id = p_internal_location_id
            AND al.agy_loc_id = agy_intl.agy_loc_id
            AND al.agency_location_type = 'INST';
      l_description agency_locations.description%TYPE;

BEGIN
      OPEN c_desc;
      FETCH c_desc
         INTO l_description;
      IF not found 
      THEN
         CLOSE c_desc;
         RETURN(NULL);
      ELSE
         CLOSE c_desc;
         RETURN(rpad(l_description, 40, ' '));
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

create or replace
function non_association_chk_na_liv_unit_conflict( p_off_liv_unit_id in agency_internal_locations.internal_location_id%type,
p_occ_liv_unit_id in agency_internal_locations.internal_location_id%type,
p_ns_type in offender_na_details.ns_type%type ) returns BOOLEAN language plpgsql as $function$ declare conflict_cur cursor for with recursive cte as (
select
	'Y',
	ail.parent_internal_location_id
from
	agency_internal_locations ail
where
	internal_location_id = p_off_liv_unit_id
union all
select
	'Y',
	ail.parent_internal_location_id
from
	agency_internal_locations ail
join cte c on
	(c.parent_internal_location_id = ail.internal_location_id) )
select
	'Y'
from
	cte
where
	exists (
	select
		'Y'
	from
		agy_int_loc_profiles ail
	where
		internal_location_id = ail.internal_location_id
		and int_loc_profile_type = 'NON_ASSO_TYP'
		and int_loc_profile_code = p_ns_type
		and ail.internal_location_id in(with recursive cte as (
		select
			olu.internal_location_id,
			olu.parent_internal_location_id
		from
			agency_internal_locations olu
		where
			olu.internal_location_id = p_occ_liv_unit_id
	union all
		select
			olu.internal_location_id,
			olu.parent_internal_location_id
		from
			agency_internal_locations olu
		join cte c on
			(c.parent_internal_location_id = olu.internal_location_id) )
		select
			internal_location_id
		from
			cte));

lv_dummy VARCHAR (1) ;

begin lv_dummy := null ;

open conflict_cur ;

fetch conflict_cur
into
	lv_dummy ;

close conflict_cur ;

if lv_dummy = 'Y' then return (true) ;
else return (false) ;
end if ;
end;

$function$ ;	

CREATE OR REPLACE FUNCTION oms_owner.tag_visits_get_offender_cont_person_id (p_offender_book_id offender_bookings.offender_book_id%TYPE, p_person_id persons.person_id%TYPE) RETURNS OFFENDER_CONTACT_PERSONS.OFFENDER_CONTACT_PERSON_ID%TYPE AS $body$
DECLARE

      offender_contact_cur CURSOR FOR
         SELECT offender_contact_person_id
           FROM offender_contact_persons
          WHERE offender_book_id = p_offender_book_id
            AND person_id = p_person_id;
      v_contact_person_id offender_contact_persons.offender_contact_person_id%TYPE;

BEGIN
      OPEN offender_contact_cur;
      FETCH offender_contact_cur
         INTO v_contact_person_id;
      CLOSE offender_contact_cur;
      RETURN v_contact_person_id;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;


CREATE OR REPLACE FUNCTION oms_owner.TAG_VISITS_GET_STAFF_ID () RETURNS bigint AS $body$
DECLARE

      staff_cur CURSOR FOR
         SELECT staff_id
           FROM staff_members
          WHERE user_id = upper(USER);
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

CREATE OR REPLACE FUNCTION oms_owner.tag_visits_sort_on_visitor_firstname (p_person_id persons.person_id%TYPE) RETURNS varchar AS $body$
DECLARE

      p_first_name persons.first_name%TYPE;
      name_c CURSOR FOR
         SELECT first_name
           FROM persons
          WHERE person_id = p_person_id;

BEGIN
      -- Get the description for the passed in code.
      OPEN name_c;
      FETCH name_c
         INTO p_first_name;
      CLOSE name_c;
      RETURN p_first_name;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;
-- REVOKE ALL ON FUNCTION oms_owner.sort_on_visitor_firstname (p_person_id persons.person_id%TYPE) FROM PUBLIC;



CREATE OR REPLACE FUNCTION oms_owner.tag_visits_sort_on_visitor_lastname (p_person_id persons.person_id%TYPE) RETURNS varchar AS $body$
DECLARE

      p_last_name persons.last_name%TYPE;
      name_c CURSOR FOR
         SELECT last_name
           FROM persons
          WHERE person_id = p_person_id;

BEGIN
      -- Get the description for the passed in code.
      OPEN name_c;
      FETCH name_c
         INTO p_last_name;
      CLOSE name_c;
      RETURN p_last_name;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_UTILS_GET_STAFF_NAME (P_STAFF_ID STAFF_MEMBERS.STAFF_ID%TYPE) RETURNS varchar AS $body$
DECLARE

      P_STAFF_NAME varchar(72);
      GET_STAFF_CUR CURSOR FOR
         SELECT LAST_NAME || ', ' || FIRST_NAME
           FROM STAFF_MEMBERS
          WHERE STAFF_ID = P_STAFF_ID;

BEGIN
      OPEN GET_STAFF_CUR;
      FETCH GET_STAFF_CUR
         INTO P_STAFF_NAME;
      CLOSE GET_STAFF_CUR;
      RETURN P_STAFF_NAME;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE EXCEPTION '%', 'Error in tag_utils.get_staff_name: ' ||
                                 SQLERRM USING ERRCODE = '45999';
   END;
$body$
LANGUAGE PLPGSQL
;
-- REVOKE ALL ON FUNCTION oms_owner.get_staff_name (P_STAFF_ID STAFF_MEMBERS.STAFF_ID%TYPE) FROM PUBLIC;



CREATE OR REPLACE FUNCTION oms_owner.OUMTRNBK_GET_BOOKING_NO (p_off_book_id offender_bookings.offender_book_id%TYPE) RETURNS OFFENDER_BOOKINGS.BOOKING_NO%TYPE AS $body$
DECLARE

      get_booking_no_cur CURSOR FOR
         SELECT booking_no
           FROM offender_bookings
          WHERE offender_book_id = p_off_book_id;
      lv_result   offender_bookings.booking_no%TYPE;

BEGIN
      OPEN get_booking_no_cur;
      FETCH get_booking_no_cur
       INTO lv_result;
      CLOSE get_booking_no_cur;
      RETURN lv_result;
      EXCEPTION WHEN OTHERS
      THEN
        raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_VISITS_GET_CONTACT_OFFENDER_BOOK_ID (p_contact_root_offender_id offenders.root_offender_id%TYPE) RETURNS OFFENDER_BOOKINGS.OFFENDER_BOOK_ID%TYPE AS $body$
DECLARE

      offender_book_cur CURSOR FOR
         SELECT offender_book_id
           FROM v_name_search2
          WHERE root_offender_id = p_contact_root_offender_id;
      v_contact_offender_book_id offender_bookings.offender_book_id%TYPE;

BEGIN
      OPEN offender_book_cur;
      FETCH offender_book_cur
         INTO v_contact_offender_book_id;
      CLOSE offender_book_cur;
      RETURN v_contact_offender_book_id;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_VISITS_SORT_ON_OFFENDER_FIRSTNAME (p_offender_id offenders.offender_id%TYPE) RETURNS varchar AS $body$
DECLARE

      p_first_name offenders.first_name%TYPE;
      name_c CURSOR FOR
         SELECT first_name
           FROM offenders off
          WHERE off.offender_id = p_offender_id;

BEGIN
      -- Get the description for the passed in code.
      OPEN name_c;
      FETCH name_c
         INTO p_first_name;
      CLOSE name_c;
      RETURN p_first_name;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END; -- Function
$body$
LANGUAGE PLPGSQL
;
-- REVOKE ALL ON FUNCTION oms_owner.sort_on_offender_firstname (p_offender_id offenders.offender_id%TYPE) FROM PUBLIC;



CREATE OR REPLACE FUNCTION oms_owner.TAG_VISITS_SORT_ON_OFFENDER_LASTNAME (p_offender_id offenders.offender_id%TYPE) RETURNS varchar AS $body$
DECLARE

      p_last_name offenders.last_name%TYPE;
      name_c CURSOR FOR
         SELECT last_name
           FROM offenders off
          WHERE off.offender_id = p_offender_id;

BEGIN
      -- Get the description for the passed in code.
      OPEN name_c;
      FETCH name_c
         INTO p_last_name;
      CLOSE name_c;
      RETURN p_last_name;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END; -- Function
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_visits_visitor_lastname_firstname (p_person_id persons.person_id%TYPE) RETURNS varchar AS $body$
DECLARE

      p_first_name persons.first_name%TYPE;
      p_last_name  persons.last_name%TYPE;
      name_c CURSOR FOR
         SELECT first_name,
                last_name
           FROM persons
          WHERE person_id = p_person_id;

BEGIN
      -- Get the description for the passed in code.
      OPEN name_c;
      FETCH name_c
         INTO p_first_name,
              p_last_name;
      CLOSE name_c;
      RETURN p_last_name || ',' || p_first_name;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END; --Function
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_VISITS_GET_ROOT_OFFENDER_ID (p_offender_id_display offenders.offender_id_display%TYPE) RETURNS OFFENDERS.ROOT_OFFENDER_ID%TYPE AS $body$
DECLARE

      l_root_offender_id offenders.root_offender_id%TYPE;
      v_no_data          bigint := 1;
      get_root_offender_id CURSOR FOR
         SELECT o.root_offender_id
           FROM offenders o
          WHERE o.offender_id_display = p_offender_id_display;

BEGIN
      OPEN get_root_offender_id;
      FETCH get_root_offender_id
         INTO l_root_offender_id;
      CLOSE get_root_offender_id;
      RETURN(l_root_offender_id);
   EXCEPTION
      WHEN no_data_found THEN
         RETURN v_no_data;
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END; --Function
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_visits_is_adult (p_visitor offender_visit_visitors.offender_visit_id%TYPE, p_type text, p_limit bigint, p_visit_date timestamp) RETURNS bigint AS $body$
DECLARE

      l_birthdate timestamp;
      c_per CURSOR FOR
         SELECT birthdate
           FROM persons
          WHERE person_id = p_visitor;
      c_off CURSOR FOR
         SELECT b.birth_date
           FROM offenders         b,
                offender_bookings ob
          WHERE ob.offender_book_id = p_visitor
            AND ob.offender_id = b.offender_id;

BEGIN
      IF coalesce(p_visitor::text, '') = '' THEN
         RETURN(0);
      END IF;
      IF p_type = 'PER' THEN
         OPEN c_per;
         FETCH c_per
            INTO l_birthdate;
         CLOSE c_per;
      ELSE
         OPEN c_off;
         FETCH c_off
            INTO l_birthdate;
         CLOSE c_off;
      END IF;
      IF (coalesce(l_birthdate::text, '') = '') OR (trunc(months_between(date_trunc('day', p_visit_date), date_trunc('day', l_birthdate)) / 12) >=
         p_limit) THEN
         RETURN(1);
      ELSE
         RETURN(0);
      END IF;
   END;
$body$
LANGUAGE PLPGSQL
;
create or replace
function oms_owner.TAG_SECURITY_GET_GROUP_PRIVILEGE ( p_module_name text ) returns varchar as $body$
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
	staff_members.user_id = user
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

CREATE OR REPLACE FUNCTION oms_owner.oumagloc_sort_on_description ( p_domain reference_codes.domain%TYPE, p_code reference_codes.code%TYPE ) RETURNS varchar AS $body$
DECLARE

     lv_description  reference_codes.description%TYPE;
     reference_codes_c CURSOR FOR
        SELECT description
          FROM reference_codes
         WHERE domain = p_domain
           AND code = p_code;

BEGIN
     -- Get the description for the passed in code.
     OPEN reference_codes_c;
     FETCH reference_codes_c INTO lv_description;
     CLOSE reference_codes_c;
     RETURN lv_description;
  EXCEPTION
     WHEN OTHERS
     THEN
        RAISE EXCEPTION '%', 'Error in OUMAGLOC.sort_on_description: '|| SQLERRM USING ERRCODE = '45000';
  END;
$body$
LANGUAGE PLPGSQL
;


CREATE OR REPLACE FUNCTION oms_owner.TAG_COURT_REPORTS_get_requirement_desc ( p_comm_condition_type text, p_comm_condition_code text ) RETURNS COMMUNITY_CONDITIONS.DESCRIPTION%TYPE AS $body$
DECLARE

       requirement_desc_c CURSOR FOR
          SELECT description
            FROM community_conditions
           WHERE comm_condition_type = p_comm_condition_type
             AND comm_condition_code = p_comm_condition_code;
       lv_description   community_conditions.description%TYPE;

BEGIN
       OPEN requirement_desc_c;
       FETCH requirement_desc_c INTO lv_description;
       CLOSE requirement_desc_c;
       RETURN lv_description;
    EXCEPTION
       WHEN OTHERS
       THEN
          IF requirement_desc_c%ISOPEN
          THEN
             CLOSE requirement_desc_c;
          END IF;
         raise '% %',sqlerrm,sqlstate;
    END;
$body$
LANGUAGE PLPGSQL
;

create or replace
function oms_owner.SJS_LIB_get_staff_id_from_user () returns bigint as $body$
declare

    GET_STAFF_ID_CUR cursor for
    select
	STAFF_ID
from
	STAFF_MEMBERS
where
	USER_ID = upper(user);

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

CREATE OR REPLACE FUNCTION oms_owner.TAG_UNPAID_WORK_sort_by_offender ( p_off_book_id v_header_block.offender_book_id%TYPE ) RETURNS varchar AS $body$
DECLARE

      lv_name   varchar(90) := NULL;
      c_name CURSOR(p_off_book_id  v_header_block.offender_book_id%TYPE)
      FOR
         SELECT o.last_name || ', ' || o.first_name
           FROM OFFENDER_BOOKINGS ob, OFFENDERS o
          WHERE ob.offender_book_id = p_off_book_id
            AND ob.offender_id = o.offender_id;

BEGIN
      OPEN c_name(p_off_book_id);
      FETCH c_name
       INTO lv_name;
      CLOSE c_name;
      RETURN lv_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;



CREATE OR REPLACE FUNCTION oms_owner.TAG_UNPAID_WORK_sort_by_project ( p_crs_acty_id OFFENDER_COURSE_ATTENDANCES.crs_acty_id%TYPE ) RETURNS COURSE_ACTIVITIES.DESCRIPTION%TYPE AS $body$
DECLARE

      lv_proj_desc   COURSE_ACTIVITIES.description%TYPE   := NULL;
      get_project_dtls_cur CURSOR(
         p_crs_acty_id      OFFENDER_COURSE_ATTENDANCES.crs_acty_id%TYPE
      )
      FOR
         SELECT ca.description
           FROM COURSE_ACTIVITIES ca
          WHERE ca.course_activity_type = 'WS'
            AND ca.course_class = 'COURSE'
            AND ca.active_flag = 'Y'
            AND ca.provider_party_class = 'TEAM'
            AND ca.crs_acty_id = p_crs_acty_id;

BEGIN
      OPEN get_project_dtls_cur(p_crs_acty_id);
      FETCH get_project_dtls_cur
       INTO lv_proj_desc;
      CLOSE get_project_dtls_cur;
      RETURN(lv_proj_desc);
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

create or replace
function oms_owner.tag_utils_get_default_agency (P_CASELOAD CASELOADS.CASELOAD_ID%type) returns AGENCY_LOCATIONS.AGY_LOC_ID%type as $body$
declare

      P_AGY_ID AGENCY_LOCATIONS.AGY_LOC_ID%type;

 DEFAULT_AGY_CUR cursor for
         SELECT AGY_LOC_ID
           FROM CASELOAD_AGENCY_LOCATIONS
          WHERE CASELOAD_ID = P_CASELOAD
            AND AGY_LOC_ID NOT IN ('OUT', 'TRN')
            AND 1 = (SELECT COUNT(*)
                       FROM CASELOAD_AGENCY_LOCATIONS
                      WHERE CASELOAD_ID = P_CASELOAD
                        AND AGY_LOC_ID NOT IN ('OUT', 'TRN'));

begin
      open DEFAULT_AGY_CUR;

fetch DEFAULT_AGY_CUR
         into
	P_AGY_ID;

close DEFAULT_AGY_CUR;

return P_AGY_ID;

exception
when others then
         raise exception '%',
'Error in tag_utils_get_default_agency: ' ||
                                 sqlerrm
	using ERRCODE = '45999';
end;

$body$
language PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.oms_utils_get_staff_name (p_staff_id staff_members.staff_id%TYPE) RETURNS varchar AS $body$
DECLARE

   staff_cur CURSOR FOR
      SELECT last_name || ',' || first_name
        FROM staff_members
       WHERE staff_id = p_staff_id;
   v_name   varchar(75);

BEGIN
   OPEN staff_cur;
   FETCH staff_cur INTO v_name;
   CLOSE staff_cur;
   RETURN v_name;
EXCEPTION
   WHEN OTHERS
   THEN
     raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_generate_case_id () RETURNS OFFENDER_CASES.CASE_ID%TYPE AS $body$
DECLARE

      id_cur CURSOR FOR
         SELECT nextval('case_id')
;
      v_case_id offender_cases.case_id%TYPE := NULL;

BEGIN
      OPEN id_cur;
      FETCH id_cur
         INTO v_case_id;
      CLOSE id_cur;
      RETURN(v_case_id);
   EXCEPTION
      WHEN OTHERS THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_generate_case_seq (p_offender_book_id offender_bookings.offender_book_id%TYPE) RETURNS OFFENDER_CASES.CASE_SEQ%TYPE AS $body$
DECLARE

      v_case_seq offender_cases.case_seq%TYPE := NULL;

BEGIN
      SELECT coalesce(MAX(case_seq), 0) + 1
        INTO STRICT v_case_seq
        FROM offender_cases
       WHERE offender_book_id = p_offender_book_id;
      RETURN v_case_seq;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_generate_event_id () RETURNS COURT_EVENTS.EVENT_ID%TYPE AS $body$
DECLARE

      id_cur CURSOR FOR
         SELECT nextval('event_id')
;
      v_event_id court_events.event_id%TYPE := NULL;

BEGIN
      OPEN id_cur;
      FETCH id_cur
         INTO v_event_id;
      CLOSE id_cur;
      RETURN(v_event_id);
   EXCEPTION
      WHEN OTHERS THEN
       RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_generate_sentence_line_seq (p_offender_book_id offender_bookings.offender_book_id%TYPE) RETURNS OFFENDER_SENTENCES.LINE_SEQ%TYPE AS $body$
DECLARE

      line_seq_cur CURSOR FOR
         SELECT coalesce(MAX(line_seq), 0) + 1
           FROM offender_sentences
          WHERE offender_book_id = p_offender_book_id;
      v_line_seq offender_sentences.sentence_seq%TYPE := NULL;

BEGIN
      OPEN line_seq_cur;
      FETCH line_seq_cur
         INTO v_line_seq;
      CLOSE line_seq_cur;
      RETURN v_line_seq;
   EXCEPTION
      WHEN OTHERS THEN
        RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_generate_sentence_seq (p_offender_book_id offender_bookings.offender_book_id%TYPE) RETURNS OFFENDER_SENTENCES.SENTENCE_SEQ%TYPE AS $body$
DECLARE

      sentence_seq_cur CURSOR FOR
         SELECT coalesce(MAX(sentence_seq), 0) + 1
           FROM offender_sentences
          WHERE offender_book_id = p_offender_book_id;
      v_sentence_seq offender_sentences.sentence_seq%TYPE := NULL;

BEGIN
      OPEN sentence_seq_cur;
      FETCH sentence_seq_cur
         INTO v_sentence_seq;
      CLOSE sentence_seq_cur;
      RETURN v_sentence_seq;
   EXCEPTION
      WHEN OTHERS THEN
        RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_generate_term_seq (p_offender_book_id offender_bookings.offender_book_id%TYPE, p_sentence_seq offender_sentences.sentence_seq%TYPE) RETURNS OFFENDER_SENTENCE_TERMS.TERM_SEQ%TYPE AS $body$
DECLARE

      seq_cur CURSOR FOR
         SELECT coalesce(MAX(term_seq), 0) + 1
           FROM offender_sentence_terms
          WHERE offender_book_id = p_offender_book_id
            AND sentence_seq = p_sentence_seq;
      v_term_seq offender_sentence_terms.term_seq%TYPE := NULL;

BEGIN
      OPEN seq_cur;
      FETCH seq_cur
         INTO v_term_seq;
      CLOSE seq_cur;
      RETURN v_term_seq;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_get_booking_start_date (p_offender_book_id offender_bookings.offender_book_id%TYPE) RETURNS timestamp AS $body$
DECLARE

      date_cur CURSOR FOR
         SELECT booking_begin_date
           FROM offender_bookings
          WHERE offender_book_id = p_offender_book_id;
      v_date offender_bookings.booking_begin_date%TYPE := NULL;

BEGIN
      OPEN date_cur;
      FETCH date_cur
         INTO v_date;
      CLOSE date_cur;
      RETURN(v_date);
   EXCEPTION
      WHEN OTHERS THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_get_event_charges_count (p_offender_charge_id offender_charges.offender_charge_id%TYPE) RETURNS bigint AS $body$
DECLARE

      v_count bigint := 0;

BEGIN
      SELECT COUNT(DISTINCT event_id)
        INTO STRICT v_count
        FROM court_event_charges
       WHERE offender_charge_id = p_offender_charge_id
         AND (result_code_1 IS NOT NULL AND result_code_1::text <> '');
      RETURN(v_count);
   EXCEPTION
      WHEN OTHERS THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;







CREATE OR REPLACE FUNCTION oms_owner.tag_establishment_get_active_agy_loc_desc ( p_agy_loc_id agency_locations.agy_loc_id%TYPE ) RETURNS varchar AS $body$
DECLARE

      lv_description   agency_locations.description%TYPE;
      agy_cur CURSOR FOR
         SELECT description
           FROM agency_locations
          WHERE agy_loc_id = p_agy_loc_id
            AND active_flag = 'Y'
            AND coalesce(deactivation_date::text, '') = '';

BEGIN
      OPEN agy_cur;
      FETCH agy_cur
       INTO lv_description;
      CLOSE agy_cur;
      RETURN(lv_description);
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;



CREATE OR REPLACE FUNCTION oms_owner.tag_reference_codes_default_domain ( P_DOMAIN REFERENCE_CODES.DOMAIN%TYPE ) RETURNS REFERENCE_CODES.CODE%TYPE AS $body$
DECLARE

      DOMAIN_CUR CURSOR FOR
         SELECT CODE
           FROM REFERENCE_CODES
          WHERE DOMAIN = P_DOMAIN
            AND LIST_SEQ = 1
            AND ACTIVE_FLAG = 'Y';
      LV_CODE   REFERENCE_CODES.CODE%TYPE;

BEGIN
      OPEN DOMAIN_CUR;
      FETCH DOMAIN_CUR
       INTO LV_CODE;
      CLOSE DOMAIN_CUR;
      RETURN( LV_CODE );
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE TYPE g_agy_rec AS (
agy_loc_id    varchar(6),
description   varchar(40));

CREATE OR REPLACE FUNCTION oms_owner.tag_legal_cases_get_latest_hearing_agency (p_offender_book_id offender_bookings.offender_book_id%TYPE) RETURNS G_AGY_REC AS $body$
DECLARE

      agy_cur CURSOR FOR
         SELECT a.agy_loc_id,
                a.description
           FROM agency_locations a,
                court_events     b
          WHERE b.offender_book_id = p_offender_book_id
            AND (b.case_id IS NOT NULL AND b.case_id::text <> '')
            AND (b.event_date, b.start_time) IN (SELECT MAX(event_date),
                        MAX(start_time)
                   FROM court_events
                  WHERE offender_book_id = p_offender_book_id
                    AND (case_id IS NOT NULL AND case_id::text <> ''))
            AND b.agy_loc_id = a.agy_loc_id
            AND a.active_flag = 'Y'
            AND coalesce(a.deactivation_date::text, '') = '';
      v_agy_rec g_agy_rec;

BEGIN
      OPEN agy_cur;
      FETCH agy_cur
         INTO v_agy_rec;
      CLOSE agy_cur;
      RETURN v_agy_rec;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_LEGAL_CASES_get_case_prefix (p_case_type reference_codes.parent_code%TYPE) RETURNS varchar AS $body$
DECLARE

      cnt_case_type_cur CURSOR FOR
         SELECT COUNT(1)
           FROM reference_codes
          WHERE domain = 'CASE_ID_TYPE'
            AND parent_code = p_case_type
            AND active_flag = 'Y';
      lv_count bigint := NULL;
      case_id_type_cur CURSOR FOR
         SELECT code
           FROM reference_codes
          WHERE domain = 'CASE_ID_TYPE'
            AND parent_code = p_case_type
            AND active_flag = 'Y';
      lv_case_prefix reference_codes.code%TYPE := NULL;

BEGIN
      -- @@@
      -- @@@ Check if multiple prefixes exist for case type
      -- @@@
      OPEN cnt_case_type_cur;
      FETCH cnt_case_type_cur
         INTO lv_count;
      CLOSE cnt_case_type_cur;
      IF lv_count > 1 THEN
         return('Multiple prefix values for this case type. Please contact Support.');
      ELSE
         --@@@
         --@@@ Get prefix
         --@@@
         OPEN case_id_type_cur;
         FETCH case_id_type_cur
            INTO lv_case_prefix;
         CLOSE case_id_type_cur;
      END IF;
      RETURN(lv_case_prefix);
   EXCEPTION
      WHEN others THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
   END;
$body$
LANGUAGE PLPGSQL
;	
CREATE OR REPLACE FUNCTION oms_owner.tag_prg_prg_appt_event_class (p_offender_book_id offender_bookings.offender_book_id%TYPE, p_agy_loc_id offender_bookings.agy_loc_id%TYPE) RETURNS varchar AS $body$
DECLARE

      l_offender_book_id     offender_program_profiles.offender_book_id%TYPE;
      l_agy_loc_id           offender_bookings.agy_loc_id%TYPE;
      l_agency_location_type agency_locations.agency_location_type%TYPE;
      l_acp_appt_event_class offender_course_attendances.event_class%TYPE;
      l_in_out_status        offender_bookings.in_out_status%TYPE;

BEGIN
      SELECT bkg.agy_loc_id,
             al.agency_location_type,
             bkg.in_out_status
        INTO STRICT l_agy_loc_id,
             l_agency_location_type,
             l_in_out_status
        FROM offender_bookings bkg
LEFT OUTER JOIN agency_locations al ON (bkg.agy_loc_id = al.agy_loc_id)
WHERE bkg.offender_book_id = p_offender_book_id;
      IF ((l_agy_loc_id = 'OUT') OR (coalesce(l_agy_loc_id::text, '') = '')) THEN
         l_acp_appt_event_class := 'COMM';
      ELSE
         IF (l_agy_loc_id = p_agy_loc_id) THEN
            l_acp_appt_event_class := 'INT_MOV';
         ELSE
            l_acp_appt_event_class := 'EXT_MOV';
         END IF;
      END IF;
      RETURN l_acp_appt_event_class;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;
   END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.tag_programmes_get_prg_profile ( P_OFFENDER_PRG_OBLIGATION_ID OFFENDER_PRG_OBLIGATIONS.OFFENDER_PRG_OBLIGATION_ID%TYPE, P_PROGRAM_ID OFFENDER_PROGRAM_PROFILES.PROGRAM_ID%TYPE ) RETURNS OFFENDER_PROGRAM_PROFILES.OFF_PRGREF_ID%TYPE AS $body$
DECLARE

      LV_OFF_PRGREF_ID   OFFENDER_PROGRAM_PROFILES.OFF_PRGREF_ID%TYPE;
      GPP_CUR CURSOR FOR
         SELECT OFF_PRGREF_ID
           FROM OFFENDER_PROGRAM_PROFILES
          WHERE OFFENDER_PRG_OBLIGATION_ID = P_OFFENDER_PRG_OBLIGATION_ID
            AND PROGRAM_ID = P_PROGRAM_ID
            AND PROFILE_CLASS = 'PRG';

BEGIN
      OPEN GPP_CUR;
      FETCH GPP_CUR
       INTO LV_OFF_PRGREF_ID;
      CLOSE GPP_CUR;
      RETURN(LV_OFF_PRGREF_ID);
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION tag_service_sched_get_mod_start_session_number (p_module_instance_id course_activities.crs_acty_id%TYPE ,p_module_list_seq course_activities.list_seq%TYPE ,p_phase_instance_id course_activities.crs_acty_id%TYPE) RETURNS integer AS $body$
BEGIN
      RETURN(tag_service_sched_get_start_session_number(p_module_instance_id,
                                                        'CRS_MOD',
                                                        p_module_list_seq,
                                                        p_phase_instance_id));
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TAG_SENTENCE_CALC_days_between (p_start_date timestamp, p_end_date timestamp) RETURNS bigint AS $body$
DECLARE

   v_term_period   numeric;

BEGIN
   IF p_end_date < p_start_date
   THEN
      RETURN 1;
   ELSE
      v_term_period := p_end_date::date - p_start_date::date + 1;
      RETURN coalesce(v_term_period, 0);
   END IF;
EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_licence_get_requirement ( p_comm_condition_code COMMUNITY_CONDITIONS.comm_condition_code%TYPE, p_comm_condition_type COMMUNITY_CONDITIONS.comm_condition_type%TYPE, p_category_type COMMUNITY_CONDITIONS.category_type%TYPE ) RETURNS varchar AS $body$
DECLARE

      get_desc_cur CURSOR FOR
         SELECT description
           FROM COMMUNITY_CONDITIONS
          WHERE comm_condition_code = p_comm_condition_code
            AND comm_condition_type = p_comm_condition_type  --@@@ Erin 12-Jun-2007 #6968: Dynamically pass in TYPE
            AND category_type = p_category_type;  --@@@ Sarah : Dynamically pass in TYPE
      lv_desc COMMUNITY_CONDITIONS.description%TYPE;

BEGIN
      OPEN get_desc_cur;
      FETCH get_desc_cur
         INTO lv_desc;
      CLOSE get_desc_cur;
      RETURN(lv_desc);
   EXCEPTION
      WHEN OTHERS THEN
          raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.OIIPROLL_get_int_loc_parent (p_internal_location_id bigint ) RETURNS bigint AS $body$
DECLARE

                c_p CURSOR FOR SELECT parent_internal_location_id
                              from v_int_loc_summaries
                              where internal_location_id = p_internal_location_id;
                 l_internal_location_id bigint;

BEGIN
                open c_p;
                fetch c_p into l_internal_location_id;
                if not found
                then
                                close c_p;
                                return(null);
                end if;
                close c_p;
                return(l_internal_location_id);
    EXCEPTION
       WHEN OTHERS
       THEN
                 IF EXISTS(SELECT * FROM pg_cursors WHERE name = 'c_p')
                  then
                          close c_p;
                  end if;
             raise '% %',sqlerrm,sqlstate;
              return(null);
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TRUST_GJ_GET_ACCOUNT_PERIOD (P_DATE timestamp) RETURNS ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE AS $body$
DECLARE

    ACCOUNT_PERIOD_CUR CURSOR FOR
      SELECT ACCOUNT_PERIOD_ID
        FROM ACCOUNT_PERIODS
       WHERE P_DATE BETWEEN START_DATE AND END_DATE;
    LV_PERIOD_ID ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE;

BEGIN
    OPEN ACCOUNT_PERIOD_CUR;
    FETCH ACCOUNT_PERIOD_CUR
      INTO LV_PERIOD_ID;
    CLOSE ACCOUNT_PERIOD_CUR;
    RETURN LV_PERIOD_ID;
  EXCEPTION
    WHEN OTHERS THEN
    raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;





CREATE OR REPLACE FUNCTION oms_owner.trust_gj_get_last_closed_period (P_CASELOAD_ID CASELOADS.CASELOAD_ID%TYPE, P_ACCCOUNT_PERIOD_ID CASELOAD_ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE DEFAULT NULL) RETURNS CASELOAD_ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE AS $body$
DECLARE

    LAST_CLOSED_PERIOD_CUR CURSOR FOR
      SELECT MAX(ACCOUNT_PERIOD_ID)
        FROM CASELOAD_ACCOUNT_PERIODS
       WHERE CASELOAD_ID = P_CASELOAD_ID
         AND ACCOUNT_PERIOD_STATUS IN ('C', 'R')
         AND (ACCOUNT_PERIOD_ID < P_ACCCOUNT_PERIOD_ID OR
             coalesce(P_ACCCOUNT_PERIOD_ID::text, '') = '');
    LV_PERIOD_ID CASELOAD_ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE;

BEGIN
    OPEN LAST_CLOSED_PERIOD_CUR;
    FETCH LAST_CLOSED_PERIOD_CUR
      INTO LV_PERIOD_ID;
    CLOSE LAST_CLOSED_PERIOD_CUR;
    RETURN LV_PERIOD_ID;
  EXCEPTION
    WHEN OTHERS THEN
     raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.trust_gj_get_period_end_date (P_ACCOUNT_PERIOD_ID ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE) RETURNS timestamp AS $body$
DECLARE

    GET_END_DATE_CUR CURSOR FOR
      SELECT END_DATE
        FROM ACCOUNT_PERIODS
       WHERE ACCOUNT_PERIOD_ID = P_ACCOUNT_PERIOD_ID;
    LV_END_DATE timestamp;

BEGIN
    OPEN GET_END_DATE_CUR;
    FETCH GET_END_DATE_CUR
      INTO LV_END_DATE;
    CLOSE GET_END_DATE_CUR;
    RETURN LV_END_DATE;
  EXCEPTION
    WHEN OTHERS THEN
     raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.trust_gj_get_period_start_date (P_ACCOUNT_PERIOD_ID ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE) RETURNS timestamp AS $body$
DECLARE

    GET_START_DATE_CUR CURSOR FOR
      SELECT START_DATE
        FROM ACCOUNT_PERIODS
       WHERE ACCOUNT_PERIOD_ID = P_ACCOUNT_PERIOD_ID;
    LV_START_DATE timestamp;

BEGIN
    OPEN GET_START_DATE_CUR;
    FETCH GET_START_DATE_CUR
      INTO LV_START_DATE;
    CLOSE GET_START_DATE_CUR;
    RETURN LV_START_DATE;
  EXCEPTION
    WHEN OTHERS THEN
      raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.trust_gj_get_defined_back_dated_period () RETURNS integer AS $body$
DECLARE

    LV_ALLOWED_PERIOD integer;

BEGIN
    LV_ALLOWED_PERIOD := floor(coalesce (TAG_UTILS_GET_SYS_PROFILE('CLIENT','T_OP_CL_PER')::integer,1))::numeric ;
    RETURN LV_ALLOWED_PERIOD;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 1; -- Default value to 1.
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.trust_gj_get_allowed_back_dated_period (P_CASELOAD_ID CASELOADS.CASELOAD_ID%TYPE) RETURNS CASELOAD_ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE AS $body$
DECLARE

    LV_PERIOD    integer;
    LV_PERIOD_ID CASELOAD_ACCOUNT_PERIODS.ACCOUNT_PERIOD_ID%TYPE;

BEGIN
    LV_PERIOD := trust_gj_get_defined_back_dated_period();
    FOR I IN 1 .. LV_PERIOD LOOP
      LV_PERIOD_ID := coalesce(trust_gj_GET_LAST_CLOSED_PERIOD(P_CASELOAD_ID,
                                                 LV_PERIOD_ID),
                          LV_PERIOD_ID);
      --  Patrick Defect 1668.added NVL clause.
    END LOOP;
    /*    IF LV_PERIOD = 0 THEN
          LV_PERIOD_ID := GET_ACCOUNT_PERIOD(TRUNC(SYSDATE));
        END IF;
    */
    --MODIFIED BY MAYANK TO CHECK IF THE SYSTEM PROFILE=0
    IF LV_PERIOD = 0 THEN
      SELECT MAX(ACCOUNT_PERIOD_ID) + 1
        INTO STRICT LV_PERIOD_ID
        FROM CASELOAD_ACCOUNT_PERIODS
       WHERE CASELOAD_ID = P_CASELOAD_ID
         AND ACCOUNT_PERIOD_STATUS = 'C';
    END IF;
    RETURN LV_PERIOD_ID;
  EXCEPTION
    WHEN OTHERS THEN
     raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.TRUST_GJ_GET_CORPORATE_NAME (P_CORP_ID CORPORATES.CORPORATE_ID%TYPE) RETURNS CORPORATES.CORPORATE_NAME%TYPE AS $body$
DECLARE

    C_CORP CURSOR FOR
      SELECT CORPORATE_NAME FROM CORPORATES WHERE CORPORATE_ID = P_CORP_ID;
    LV_CORP_NAME CORPORATES.CORPORATE_NAME%TYPE;

BEGIN
    OPEN C_CORP;
    FETCH C_CORP
      INTO LV_CORP_NAME;
    CLOSE C_CORP;
    RETURN LV_CORP_NAME;
  EXCEPTION
    WHEN OTHERS THEN
     raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;




CREATE OR REPLACE FUNCTION oms_owner.TRUST_GJ_GET_PERSON_NAME (P_PERSON_ID PERSONS.PERSON_ID%TYPE) RETURNS varchar AS $body$
DECLARE

    GET_NAMES_CUR CURSOR FOR
      SELECT concat(LAST_NAME,', ',FIRST_NAME)
        FROM PERSONS
       WHERE PERSON_ID = P_PERSON_ID;
    LV_NAME varchar(72);

BEGIN
    OPEN GET_NAMES_CUR;
    FETCH GET_NAMES_CUR
      INTO LV_NAME;
    CLOSE GET_NAMES_CUR;
    RETURN LV_NAME;
  EXCEPTION
    WHEN OTHERS THEN
     raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.oms_date_time_compare_date_time ( P_DATE_1 text, P_TIME_1 text, P_DATE_2 text, P_TIME_2 text ) RETURNS bigint AS $body$
DECLARE

   V_DT_1   timestamp;
   V_TM_1   varchar(64);
   V_DT_2   timestamp;
   V_TM_2   varchar(64);

BEGIN
   IF (P_DATE_1 IS NOT NULL AND P_DATE_1::text <> '') AND (P_DATE_2 IS NOT NULL AND P_DATE_2::text <> '')
   THEN
      V_DT_1 := P_DATE_1::date;
      V_DT_2 := P_DATE_2::date;
   ELSE
      V_DT_1 := TO_DATE('01-01-80','DD-MM-RR HH24:MI:SS');
      V_DT_2 := TO_DATE('01-01-80','DD-MM-RR HH24:MI:SS');
   END IF;
   IF (P_TIME_1 IS NOT NULL AND P_TIME_1::text <> '') AND (P_TIME_2 IS NOT NULL AND P_TIME_2::text <> '')
   THEN
      V_TM_1 := SUBSTR (P_TIME_1, INSTR (P_TIME_1, ' ') + 1, 8);
      V_TM_2 := SUBSTR (P_TIME_2, INSTR (P_TIME_2, ' ') + 1, 8);
   ELSE
      V_TM_1 := '00:00:00';
      V_TM_2 := '00:00:00';
   END IF;
   IF V_DT_1 < V_DT_2
   THEN
      RETURN -1;
   ELSIF V_DT_1 = V_DT_2
   THEN
      IF V_TM_1 < V_TM_2
      THEN
         RETURN -1;
      ELSIF V_TM_1 = V_TM_2
      THEN
         RETURN 0;
      ELSE
         RETURN 1;
      END IF;
   ELSE
      RETURN 1;
   END IF;
END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.sort_on_staff_last_name ( p_staff_id bigint ) RETURNS varchar AS $body$
DECLARE

  /* =========================================================
   Version Number = 2.1  Date Created = 09/06/2006
   =========================================================
   MODIFICATION HISTORY
   Person      Date         Version       Comments
   ---------   -----------  ------------  ----------------------------------------------------------
   Claus       09/06/2006   2.1           D# 2554. Corrected definition of lv_staff_name
   Rajshree    10/01/2005   2.0           Created this procedure for SORT on OCMTEAMS
*/
     lv_staff_name varchar;
     staff_name_cur CURSOR FOR
        SELECT last_name||','||first_name
              FROM staff_members
             WHERE staff_id = p_staff_id;

BEGIN
     -- Get the last_name and first name for the staff_member
     OPEN staff_name_cur;
     FETCH staff_name_cur INTO lv_staff_name;
     CLOSE staff_name_cur;
     RETURN lv_staff_name;
  EXCEPTION
     WHEN OTHERS
     THEN
        raise '% %',sqlerrm,sqlstate;
  END;
$body$
LANGUAGE PLPGSQL
;


CREATE OR REPLACE FUNCTION oms_owner.tag_visits_sort_on_offender_lastname (p_offender_id offenders.offender_id%TYPE) RETURNS varchar AS $body$
DECLARE

      p_last_name offenders.last_name%TYPE;
      name_c CURSOR FOR
         SELECT last_name
           FROM offenders off
          WHERE off.offender_id = p_offender_id;

BEGIN
      -- Get the description for the passed in code.
      OPEN name_c;
      FETCH name_c
         INTO p_last_name;
      CLOSE name_c;
      RETURN p_last_name;
   EXCEPTION
      WHEN OTHERS THEN
        raise '% %',sqlerrm,sqlstate;
   END; -- Function
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.tag_visits_get_offender_from_book_id (p_offender_book_id offender_bookings.offender_book_id%TYPE) RETURNS OFFENDERS.OFFENDER_ID%TYPE AS $body$
DECLARE

      l_offender_id offenders.offender_id%TYPE;
      get_offender_id CURSOR FOR
         SELECT o.offender_id
           FROM v_header_block o
          WHERE o.offender_book_id = p_offender_book_id
          ORDER BY last_name ASC;

BEGIN
      OPEN get_offender_id;
      FETCH get_offender_id
         INTO l_offender_id;
      CLOSE get_offender_id;
      RETURN(l_offender_id);
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END; --Function
$body$
LANGUAGE PLPGSQL
;

