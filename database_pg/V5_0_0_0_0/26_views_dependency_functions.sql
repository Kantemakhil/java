CREATE OR REPLACE FUNCTION tag_int_loc_level_code(p_description character varying, p_level integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      lv_desc    VARCHAR (100);
      lv_count   INT;
      lv_pos     INT;
   BEGIN
      lv_desc := p_description;

      FOR lv_count IN 1 .. p_level
      LOOP
         lv_pos := INSTR (lv_desc, '-');

         IF (lv_pos = 0)
         THEN
            lv_desc := NULL;
         ELSE
            lv_desc := SUBSTR (lv_desc, lv_pos + 1, LENGTH (lv_desc) - lv_pos);
         END IF;
      END LOOP;

      lv_pos := INSTR (lv_desc, '-');

      IF (lv_pos > 0)
      THEN
         lv_desc := SUBSTR (lv_desc, 1, lv_pos - 1);
      END IF;

      RETURN lv_desc;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_active_flag(p_int_loc_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_active_flag         VARCHAR (1);
      lv_parent_int_loc_id   agency_internal_locations.parent_internal_location_id%TYPE;
   BEGIN
          SELECT active_flag, parent_internal_location_id
        INTO lv_active_flag, lv_parent_int_loc_id
        FROM agency_internal_locations ail
       WHERE internal_location_id = p_int_loc_id
         AND EXISTS (
                SELECT 'x'
                  FROM agency_locations al
                 WHERE ail.agy_loc_id = al.agy_loc_id
                   AND (   ail.internal_location_type = al.housing_lev_1_code
                        OR ail.internal_location_type = al.housing_lev_2_code
                        OR ail.internal_location_type = al.housing_lev_3_code
                        OR ail.internal_location_type = al.housing_lev_4_code
                       ));

      IF (lv_active_flag = 'N')
      THEN
         RETURN 'N';
      ELSIF lv_parent_int_loc_id IS NULL
      THEN
         RETURN 'Y';
      ELSE
         RETURN tag_int_loc_active_flag (lv_parent_int_loc_id);
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN NULL;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_operation_flag(p_int_loc_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_no_of_occupant       BIGINT ;
      lv_operation_capacity   BIGINT ;
      lv_parent_int_loc_id    agency_internal_locations.internal_location_id%TYPE;
   BEGIN
            SELECT parent_internal_location_id, operation_capacity, no_of_occupant
        INTO lv_parent_int_loc_id, lv_operation_capacity, lv_no_of_occupant
        FROM agency_internal_locations ail
       WHERE internal_location_id = p_int_loc_id
         AND EXISTS (
                SELECT 'x'
                  FROM agency_locations al
                 WHERE ail.agy_loc_id = al.agy_loc_id
                   AND (   ail.internal_location_type = al.housing_lev_1_code
                        OR ail.internal_location_type = al.housing_lev_2_code
                        OR ail.internal_location_type = al.housing_lev_3_code
                        OR ail.internal_location_type = al.housing_lev_4_code
                       ));

 IF NOT FOUND then
  RETURN p_int_loc_id;
 else
      IF  (lv_operation_capacity IS NOT NULL)
         AND (lv_operation_capacity <= lv_no_of_occupant)
      THEN
         RETURN 'Y';
      ELSE
         IF lv_parent_int_loc_id IS NULL
         THEN
            RETURN 'N';
         ELSE
            RETURN tag_int_loc_operation_flag (lv_parent_int_loc_id);
         END IF;
      END IF;
     end if;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN p_int_loc_id;
   END;
$function$
;

CREATE OR REPLACE VIEW living_units
AS SELECT ail.internal_location_id AS living_unit_id,
    ail.agy_loc_id,
    ail.internal_location_type AS living_unit_type,
    ail.internal_location_code AS living_unit_code,
    ail.description,
    substr(tag_int_loc_level_code(ail.description, 1)::text, 1, 40) AS level_1_code,
    substr(tag_int_loc_level_code(ail.description, 2)::text, 1, 40) AS level_2_code,
    substr(tag_int_loc_level_code(ail.description, 3)::text, 1, 40) AS level_3_code,
    substr(tag_int_loc_level_code(ail.description, 4)::text, 1, 40) AS level_4_code,
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

  

CREATE OR REPLACE FUNCTION elite_biometrics_get_afis_result_seq(p_subject_id bigint)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      GET_SEQ CURSOR
      FOR
         SELECT SEQ
           FROM AFIS_SEARCH_RESULTS
          WHERE SUBJECT_ID = P_SUBJECT_ID;

      LV_SEQ   INT;
   BEGIN
      OPEN GET_SEQ;

      FETCH GET_SEQ
       INTO LV_SEQ;

      CLOSE GET_SEQ;

      RETURN LV_SEQ;
   EXCEPTION
      WHEN OTHERS
      THEN
        raise '% %',sqlerrm,sqlstate;
   END ;
$function$
;

CREATE OR REPLACE FUNCTION elite_biometrics_get_last_scan_date(p_subject_id bigint, p_position bigint)
 RETURNS timestamp without time zone
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      LV_SCAN_DATE   TIMESTAMP ( 6 );

      GET_LATEST_DATE_CUR CURSOR
      FOR
         SELECT MAX (BS.SCAN_DATE)
           FROM BIO_SCANS BS, BIO_FP_SAMPLES BFS
          WHERE BS.SUBJECT_ID = BFS.SUBJECT_ID
            AND BS.SCAN_ID = BFS.SCAN_ID
            AND BS.SUBJECT_ID = P_SUBJECT_ID
            AND BFS.POSITION = P_POSITION;
   BEGIN
      OPEN GET_LATEST_DATE_CUR;

      FETCH GET_LATEST_DATE_CUR
       INTO LV_SCAN_DATE;

      CLOSE GET_LATEST_DATE_CUR;

      RETURN LV_SCAN_DATE;
   END;
$function$
;

CREATE OR REPLACE FUNCTION elite_biometrics_get_last_scan_date(p_subject_id bigint, p_position integer)
 RETURNS timestamp without time zone
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      LV_SCAN_DATE   TIMESTAMP ( 6 );

      GET_LATEST_DATE_CUR CURSOR
      FOR
         SELECT MAX (BS.SCAN_DATE)
           FROM BIO_SCANS BS, BIO_FP_SAMPLES BFS
          WHERE BS.SUBJECT_ID = BFS.SUBJECT_ID
            AND BS.SCAN_ID = BFS.SCAN_ID
            AND BS.SUBJECT_ID = P_SUBJECT_ID
            AND BFS.POSITION = P_POSITION;
   BEGIN
      OPEN GET_LATEST_DATE_CUR;

      FETCH GET_LATEST_DATE_CUR
       INTO LV_SCAN_DATE;

      CLOSE GET_LATEST_DATE_CUR;

      RETURN LV_SCAN_DATE;
   END;
$function$
;

CREATE OR REPLACE FUNCTION indigent_ind_flagformula(p_off_id bigint, p_csld_id character varying, p_agyloc_id character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
   
      CSLD_TYPE              ACCOUNT_CODES.CASELOAD_TYPE%TYPE;
      LV_TRUST_ACCOUNT_CODE  ACCOUNT_CODES.ACCOUNT_CODE%TYPE;
      LV_CSLD_ID_EXISTS      VARCHAR(1);
      LV_INDIGENCY_LEVEL     SYSTEM_PROFILES.PROFILE_VALUE%TYPE;
      LV_INDIGENT_DAYS_LIMIT SYSTEM_PROFILES.PROFILE_VALUE%TYPE;
      LV_SUB_ACCT_BALANCE    OFFENDER_SUB_ACCOUNTS.BALANCE%TYPE;
      LV_INDIGENT_DATE       OFFENDER_SUB_ACCOUNTS.IND_DATE%TYPE;
      CHK_IND_AC_C CURSOR FOR
         SELECT TRUST_ACCOUNT_CODE
           FROM OFFENDER_SUB_ACCOUNTS
          WHERE OFFENDER_ID = P_OFF_ID
            AND CASELOAD_ID = P_CSLD_ID
            AND TRUST_ACCOUNT_CODE IN
                (SELECT ACCOUNT_CODE
                   FROM INSTITUTION_MINI_BALANCES
                  WHERE CASELOAD_ID = P_CSLD_ID)
            AND TRUST_ACCOUNT_CODE IN
                (SELECT ACCOUNT_CODE
                   FROM ACCOUNT_CODES
                  WHERE CASELOAD_TYPE = CSLD_TYPE)
         UNION
         SELECT TRUST_ACCOUNT_CODE
           FROM OFFENDER_SUB_ACCOUNTS
          WHERE OFFENDER_ID = P_OFF_ID
            AND CASELOAD_ID = P_CSLD_ID
            AND MINIMUM_BALANCE IS NOT NULL
            AND TRUST_ACCOUNT_CODE IN
                (SELECT ACCOUNT_CODE
                   FROM ACCOUNT_CODES
                  WHERE CASELOAD_TYPE = CSLD_TYPE);
   
      CUR_CHK_IND CURSOR(P_TRUST_ACCT_CODE OFFENDER_SUB_ACCOUNTS.TRUST_ACCOUNT_CODE%TYPE) FOR
         SELECT
    osa.ind_date,
    coalesce(osa.ind_days, coalesce(imb.ind_days, 0))
FROM
    offender_sub_accounts      osa
    LEFT JOIN institution_mini_balances  imb ON imb.account_code = osa.trust_account_code
                                               AND imb.caseload_id = osa.caseload_id and
                                               imb.agy_loc_id = CASE p_agyloc_id
        WHEN 'TRN' THEN
            'OUT'
        ELSE
            p_agyloc_id
                             END
WHERE
        osa.caseload_id = p_csld_id
    AND osa.offender_id = p_off_id
    AND osa.trust_account_code = p_trust_acct_code;
   
   
      CHK_CASELOAD_C CURSOR FOR
         SELECT MIN('X')
           FROM INSTITUTION_MINI_BALANCES
          WHERE CASELOAD_ID = P_CSLD_ID;
   
      SYSTEM_PROFILE_C CURSOR FOR
         SELECT PROFILE_VALUE
               ,PROFILE_VALUE_2
           FROM SYSTEM_PROFILES
          WHERE PROFILE_TYPE = 'CLIENT'
            AND PROFILE_CODE = 'IND_CON';
   
      GET_TRUST_ACCOUNT_CODE_C CURSOR FOR
         SELECT ACCOUNT_CODE
           FROM ACCOUNT_CODES
          WHERE SUB_ACCOUNT_TYPE = 'REG';
   
      SUM_OFF_SUB_ACT_BAL_C CURSOR FOR
         SELECT SUM(OSA.BALANCE)
           FROM OFFENDER_SUB_ACCOUNTS OSA
          WHERE OFFENDER_ID = P_OFF_ID
            AND TRUST_ACCOUNT_CODE = LV_TRUST_ACCOUNT_CODE
            AND NOT EXISTS
          (SELECT DISTINCT CASELOAD_ID
                   FROM INSTITUTION_MINI_BALANCES IMB
                  WHERE IMB.CASELOAD_ID = OSA.CASELOAD_ID);
   
      MAX_IND_DATE_C CURSOR FOR
         SELECT MAX(IND_DATE)
           FROM OFFENDER_SUB_ACCOUNTS OSA
          WHERE OFFENDER_ID = P_OFF_ID
            AND TRUST_ACCOUNT_CODE = LV_TRUST_ACCOUNT_CODE
            AND NOT EXISTS
          (SELECT DISTINCT CASELOAD_ID
                   FROM INSTITUTION_MINI_BALANCES IMB
                  WHERE IMB.CASELOAD_ID = OSA.CASELOAD_ID);
   
      INDDATE  TIMESTAMP(0);
      INDDAYS  INT;
      IND_FLAG VARCHAR(1);
   BEGIN
   
          OPEN CHK_CASELOAD_C;
      FETCH CHK_CASELOAD_C
         INTO LV_CSLD_ID_EXISTS;
      CLOSE CHK_CASELOAD_C;
   
      IF LV_CSLD_ID_EXISTS IS NOT NULL
      THEN
         
         
         
         
         FOR IND_AC IN CHK_IND_AC_C LOOP
            OPEN CUR_CHK_IND(IND_AC.TRUST_ACCOUNT_CODE);
            FETCH CUR_CHK_IND
               INTO INDDATE, INDDAYS;
            CLOSE CUR_CHK_IND;
         
            IF INDDATE IS NULL
               OR (INDDAYS - (TRUNC(CURRENT_TIMESTAMP) - TRUNC(INDDATE))) > 0
            THEN
               IND_FLAG := 'N';
               EXIT;
            ELSE
               IND_FLAG := 'Y';
            END IF;
         END LOOP;
      ELSE
         
         
         OPEN SYSTEM_PROFILE_C;
         FETCH SYSTEM_PROFILE_C
            INTO LV_INDIGENCY_LEVEL, LV_INDIGENT_DAYS_LIMIT;
         CLOSE SYSTEM_PROFILE_C;
         
         OPEN GET_TRUST_ACCOUNT_CODE_C;
         FETCH GET_TRUST_ACCOUNT_CODE_C
            INTO LV_TRUST_ACCOUNT_CODE;
         CLOSE GET_TRUST_ACCOUNT_CODE_C;
         
         
         OPEN SUM_OFF_SUB_ACT_BAL_C;
         FETCH SUM_OFF_SUB_ACT_BAL_C
            INTO LV_SUB_ACCT_BALANCE;
         CLOSE SUM_OFF_SUB_ACT_BAL_C;
      
         IF LV_SUB_ACCT_BALANCE::character varying <= LV_INDIGENCY_LEVEL::character varying
         THEN
         
            OPEN MAX_IND_DATE_C;
            FETCH MAX_IND_DATE_C
               INTO LV_INDIGENT_DATE;
            CLOSE MAX_IND_DATE_C;
         
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            SELECT MAX(OSA.IND_DAYS) 
              INTO INDDAYS
              FROM OFFENDER_SUB_ACCOUNTS OSA
             WHERE OFFENDER_ID = P_OFF_ID
               AND TRUST_ACCOUNT_CODE = LV_TRUST_ACCOUNT_CODE
               AND IND_DATE = LV_INDIGENT_DATE
               AND NOT EXISTS
             (SELECT DISTINCT CASELOAD_ID
                      FROM INSTITUTION_MINI_BALANCES IMB
                     WHERE IMB.CASELOAD_ID = OSA.CASELOAD_ID);
         
            IF LV_INDIGENT_DATE IS NULL
            THEN
               
               LV_INDIGENT_DATE := TRUNC(CURRENT_TIMESTAMP);
            END IF;
         
            INDDATE := LV_INDIGENT_DATE;
            IF INDDAYS != 0 
            THEN
               INDDAYS := LV_INDIGENT_DAYS_LIMIT;
            END IF;
         ELSE
            INDDATE := NULL;
            INDDAYS := 0;
         END IF;
      
         IF (INDDATE IS NULL)
            OR (TRUNC(CURRENT_TIMESTAMP) - INDDATE < INDDAYS AND INDDAYS != 0) 
         THEN
            IND_FLAG := 'N';
         ELSIF TRUNC(CURRENT_TIMESTAMP) - INDDATE >= INDDAYS
         THEN
            IND_FLAG := 'Y';
         END IF;
      END IF;
      RETURN COALESCE(IND_FLAG, 'N');
   
   EXCEPTION
      WHEN OTHERS THEN
        raise notice '% %',sqlerrm,sqlstate;
      
   END;
$function$
;

CREATE OR REPLACE FUNCTION ind_indigent_flagformula(p_off_id bigint, p_csld_id character varying, p_agyloc_id character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
   
      CSLD_TYPE              ACCOUNT_CODES.CASELOAD_TYPE%TYPE;
      LV_TRUST_ACCOUNT_CODE  ACCOUNT_CODES.ACCOUNT_CODE%TYPE;
      LV_CSLD_ID_EXISTS      VARCHAR(1);
      LV_INDIGENCY_LEVEL     SYSTEM_PROFILES.PROFILE_VALUE%TYPE;
      LV_INDIGENT_DAYS_LIMIT SYSTEM_PROFILES.PROFILE_VALUE%TYPE;
      LV_SUB_ACCT_BALANCE    OFFENDER_SUB_ACCOUNTS.BALANCE%TYPE;
      LV_INDIGENT_DATE       OFFENDER_SUB_ACCOUNTS.IND_DATE%TYPE;
      CHK_IND_AC_C CURSOR FOR
         SELECT TRUST_ACCOUNT_CODE
           FROM OFFENDER_SUB_ACCOUNTS
          WHERE OFFENDER_ID = P_OFF_ID
            AND CASELOAD_ID = P_CSLD_ID
            AND TRUST_ACCOUNT_CODE IN
                (SELECT ACCOUNT_CODE
                   FROM INSTITUTION_MINI_BALANCES
                  WHERE CASELOAD_ID = P_CSLD_ID)
            AND TRUST_ACCOUNT_CODE IN
                (SELECT ACCOUNT_CODE
                   FROM ACCOUNT_CODES
                  WHERE CASELOAD_TYPE = CSLD_TYPE)
         UNION
         SELECT TRUST_ACCOUNT_CODE
           FROM OFFENDER_SUB_ACCOUNTS
          WHERE OFFENDER_ID = P_OFF_ID
            AND CASELOAD_ID = P_CSLD_ID
            AND MINIMUM_BALANCE IS NOT NULL
            AND TRUST_ACCOUNT_CODE IN
                (SELECT ACCOUNT_CODE
                   FROM ACCOUNT_CODES
                  WHERE CASELOAD_TYPE = CSLD_TYPE);
   
      CUR_CHK_IND CURSOR(P_TRUST_ACCT_CODE OFFENDER_SUB_ACCOUNTS.TRUST_ACCOUNT_CODE%TYPE) FOR
         SELECT
    osa.ind_date,
    coalesce(osa.ind_days, coalesce(imb.ind_days, 0))
FROM
    offender_sub_accounts      osa
    LEFT JOIN institution_mini_balances  imb ON imb.account_code = osa.trust_account_code
                                               AND imb.caseload_id = osa.caseload_id and
                                               imb.agy_loc_id = CASE p_agyloc_id
        WHEN 'TRN' THEN
            'OUT'
        ELSE
            p_agyloc_id
                             END
WHERE
        osa.caseload_id = p_csld_id
    AND osa.offender_id = p_off_id
    AND osa.trust_account_code = p_trust_acct_code;
   
   
      CHK_CASELOAD_C CURSOR FOR
         SELECT MIN('X')
           FROM INSTITUTION_MINI_BALANCES
          WHERE CASELOAD_ID = P_CSLD_ID;
   
      SYSTEM_PROFILE_C CURSOR FOR
         SELECT PROFILE_VALUE
               ,PROFILE_VALUE_2
           FROM SYSTEM_PROFILES
          WHERE PROFILE_TYPE = 'CLIENT'
            AND PROFILE_CODE = 'IND_CON';
   
      GET_TRUST_ACCOUNT_CODE_C CURSOR FOR
         SELECT ACCOUNT_CODE
           FROM ACCOUNT_CODES
          WHERE SUB_ACCOUNT_TYPE = 'REG';
   
      SUM_OFF_SUB_ACT_BAL_C CURSOR FOR
         SELECT SUM(OSA.BALANCE)
           FROM OFFENDER_SUB_ACCOUNTS OSA
          WHERE OFFENDER_ID = P_OFF_ID
            AND TRUST_ACCOUNT_CODE = LV_TRUST_ACCOUNT_CODE
            AND NOT EXISTS
          (SELECT DISTINCT CASELOAD_ID
                   FROM INSTITUTION_MINI_BALANCES IMB
                  WHERE IMB.CASELOAD_ID = OSA.CASELOAD_ID);
   
      MAX_IND_DATE_C CURSOR FOR
         SELECT MAX(IND_DATE)
           FROM OFFENDER_SUB_ACCOUNTS OSA
          WHERE OFFENDER_ID = P_OFF_ID
            AND TRUST_ACCOUNT_CODE = LV_TRUST_ACCOUNT_CODE
            AND NOT EXISTS
          (SELECT DISTINCT CASELOAD_ID
                   FROM INSTITUTION_MINI_BALANCES IMB
                  WHERE IMB.CASELOAD_ID = OSA.CASELOAD_ID);
   
      INDDATE  TIMESTAMP(0);
      INDDAYS  INT;
      IND_FLAG VARCHAR(1);
   BEGIN
   
          OPEN CHK_CASELOAD_C;
      FETCH CHK_CASELOAD_C
         INTO LV_CSLD_ID_EXISTS;
      CLOSE CHK_CASELOAD_C;
   
      IF LV_CSLD_ID_EXISTS IS NOT NULL
      THEN
         
         
         
         
         FOR IND_AC IN CHK_IND_AC_C LOOP
            OPEN CUR_CHK_IND(IND_AC.TRUST_ACCOUNT_CODE);
            FETCH CUR_CHK_IND
               INTO INDDATE, INDDAYS;
            CLOSE CUR_CHK_IND;
         
            IF INDDATE IS NULL
               OR (INDDAYS - (date_trunc('day',CURRENT_TIMESTAMP) - date_TRUNC('day',INDDATE))) > 0
            THEN
               IND_FLAG := 'N';
               EXIT;
            ELSE
               IND_FLAG := 'Y';
            END IF;
         END LOOP;
      ELSE
         
         
         OPEN SYSTEM_PROFILE_C;
         FETCH SYSTEM_PROFILE_C
            INTO LV_INDIGENCY_LEVEL, LV_INDIGENT_DAYS_LIMIT;
         CLOSE SYSTEM_PROFILE_C;
         
         OPEN GET_TRUST_ACCOUNT_CODE_C;
         FETCH GET_TRUST_ACCOUNT_CODE_C
            INTO LV_TRUST_ACCOUNT_CODE;
         CLOSE GET_TRUST_ACCOUNT_CODE_C;
         
         
         OPEN SUM_OFF_SUB_ACT_BAL_C;
         FETCH SUM_OFF_SUB_ACT_BAL_C
            INTO LV_SUB_ACCT_BALANCE;
         CLOSE SUM_OFF_SUB_ACT_BAL_C;
      
         IF LV_SUB_ACCT_BALANCE::character varying <= LV_INDIGENCY_LEVEL::character varying
         THEN
            
            
         
            
            
         
            
            
            
            
         
            OPEN MAX_IND_DATE_C;
            FETCH MAX_IND_DATE_C
               INTO LV_INDIGENT_DATE;
            CLOSE MAX_IND_DATE_C;
         
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            SELECT MAX(OSA.IND_DAYS) 
              INTO INDDAYS
              FROM OFFENDER_SUB_ACCOUNTS OSA
             WHERE OFFENDER_ID = P_OFF_ID
               AND TRUST_ACCOUNT_CODE = LV_TRUST_ACCOUNT_CODE
               AND IND_DATE = LV_INDIGENT_DATE
               AND NOT EXISTS
             (SELECT DISTINCT CASELOAD_ID
                      FROM INSTITUTION_MINI_BALANCES IMB
                     WHERE IMB.CASELOAD_ID = OSA.CASELOAD_ID);
         
            IF LV_INDIGENT_DATE IS NULL
            THEN
               
               LV_INDIGENT_DATE := date_trunc('day',CURRENT_TIMESTAMP);
            END IF;
         
            INDDATE := LV_INDIGENT_DATE;
            IF INDDAYS != 0 
            THEN
               INDDAYS := LV_INDIGENT_DAYS_LIMIT;
            END IF;
         ELSE
            INDDATE := NULL;
            INDDAYS := 0;
         END IF;
      
         IF (INDDATE IS NULL)
            OR (date_trunc('day',CURRENT_TIMESTAMP) - INDDATE < interval '1 day'*INDDAYS AND INDDAYS != 0) 
         THEN
            IND_FLAG := 'N';
         ELSIF date_trunc('day',CURRENT_TIMESTAMP) - INDDATE >= interval '1 day' * INDDAYS
         THEN
            IND_FLAG := 'Y';
         END IF;
      END IF;
      RETURN COALESCE(IND_FLAG, 'N');
   
   EXCEPTION
      WHEN OTHERS THEN
        raise notice '% %',sqlerrm,sqlstate;
      
   END;
$function$
;

CREATE OR REPLACE FUNCTION instr(p_str text, p_substr text, p_pos numeric DEFAULT 1, p_occurrence numeric DEFAULT 1)
 RETURNS numeric
 LANGUAGE sql
 IMMUTABLE PARALLEL SAFE STRICT
AS $function$
SELECT
        CASE

            WHEN LENGTH($1) = 0 OR LENGTH($2) = 0 THEN NULL::NUMERIC

            WHEN TRUNC($4) = 0 THEN 1/TRUNC($4)

            WHEN $4 < 0 THEN SQRT($4)

            ELSE

                (

                    WITH RECURSIVE t(str, shift, pos, tail, o, n) AS 
                    (
                        SELECT CASE WHEN TRUNC($3) < 0 THEN REVERSE($1) ELSE $1 END AS str,
                            0 AS shift,
                            CASE WHEN TRUNC($3) < 0 THEN -1 * TRUNC($3)::INT ELSE TRUNC($3)::INT END AS pos,
                            CASE WHEN TRUNC($3) < 0 THEN REVERSE($1) ELSE $1 END AS tail,
                            0 AS o,
                            CASE WHEN TRUNC($3) < 0 THEN REVERSE($2) ELSE $2 END AS n
                        UNION ALL
                        SELECT str,
                            shift + pos AS shift,
                            STRPOS(SUBSTR(str, shift + pos), n) AS pos,
                            SUBSTR(str, shift + pos) AS tail,
                            o + 1 AS o,
                            n
                        FROM t
                        WHERE pos <> 0
                    )
                    ,r AS
                    (
                        SELECT t.str,
                            t.shift,
                            t.pos,
                            t.tail,
                            t.o,
                            CASE
                                WHEN TRUNC($3) > 0 THEN
                                    t.pos + t.shift - 1
                                ELSE
                                    LENGTH(t.str) - t.pos - t.shift + 2
                            END cc
                        FROM t
                        WHERE t.o = TRUNC($4)
                        AND t.pos <> 0
                    )
                    SELECT COALESCE
                    (
                        (
                            SELECT r.cc
                            FROM r
                        ),
                        0
                    )::NUMERiC
                )

        END;
$function$
;

CREATE OR REPLACE FUNCTION ocdsreqs_get_workflow_team(p_off_bkg_id bigint, p_workflow_id numeric)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE	
      TEAM_CUR CURSOR
      FOR
         SELECT TMS.DESCRIPTION
           FROM TASK_ASSIGNMENT_HTY TAH, TEAMS TMS
          WHERE TAH.OFFENDER_BOOK_ID = P_OFF_BKG_ID
            AND TAH.WORKFLOW_HISTORY_ID = P_WORKFLOW_ID::bigint
            AND TMS.TEAM_ID = TAH.TEAM_ID
            AND TAH.TASK_ASSIGNMENT_HTY_ID =
                   ( SELECT  MAX ( TASK_ASSIGNMENT_HTY_ID )
                        FROM TASK_ASSIGNMENT_HTY
                       WHERE OFFENDER_BOOK_ID = P_OFF_BKG_ID
                         AND WORKFLOW_HISTORY_ID =P_WORKFLOW_ID::bigint
                    GROUP BY OFFENDER_BOOK_ID );

      V_TEAM_DESC   TEAMS.DESCRIPTION%TYPE;
   BEGIN
      OPEN TEAM_CUR;

      FETCH TEAM_CUR
       INTO V_TEAM_DESC;

      CLOSE TEAM_CUR;

      RETURN ( V_TEAM_DESC );
   END ;
$function$
;

CREATE OR REPLACE FUNCTION oms_owner.OMKHEAD_get_alerts ( P_OFFENDER_BOOK_ID bigint ) RETURNS varchar AS $body$
DECLARE

    OMCALERT CURSOR FOR
    SELECT  DISTINCT(SUBSTR(A.ALERT_TYPE,1,1)) ALERT_TYPE, R.LIST_SEQ LIST_SEQ
     FROM    OFFENDER_ALERTS A, REFERENCE_CODES R
     WHERE  A.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
       AND ( coalesce(A.EXPIRY_DATE::text, '') = ''   OR date_trunc('day', A.EXPIRY_DATE) > date_trunc('day', clock_timestamp()))
       AND (date_trunc('day', A.ALERT_DATE) <= date_trunc('day', clock_timestamp()))
       AND   R.DOMAIN='ALERT'
       AND   R.CODE = A.ALERT_TYPE
       AND   R.ACTIVE_FLAG = 'Y'
       AND   coalesce(R.EXPIRED_DATE::text, '') = ''
    ORDER BY LIST_SEQ;
    P_ALERT_DISPLAY_VALUE varchar(54);

BEGIN
     FOR OMCALERT_REC IN OMCALERT
           LOOP
             P_ALERT_DISPLAY_VALUE := concat(P_ALERT_DISPLAY_VALUE,OMCALERT_REC.ALERT_TYPE);
          END LOOP;
     RETURN(P_ALERT_DISPLAY_VALUE);
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_owner.OMKHEAD_get_alerts ( P_OFFENDER_BOOK_ID integer ) RETURNS varchar AS $body$
DECLARE

    OMCALERT CURSOR FOR
    SELECT  DISTINCT(SUBSTR(A.ALERT_TYPE,1,1)) ALERT_TYPE, R.LIST_SEQ LIST_SEQ
     FROM    OFFENDER_ALERTS A, REFERENCE_CODES R
     WHERE  A.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
       AND ( coalesce(A.EXPIRY_DATE::text, '') = ''   OR date_trunc('day', A.EXPIRY_DATE) > date_trunc('day', clock_timestamp()))
       AND (date_trunc('day', A.ALERT_DATE) <= date_trunc('day', clock_timestamp()))
       AND   R.DOMAIN='ALERT'
       AND   R.CODE = A.ALERT_TYPE
       AND   R.ACTIVE_FLAG = 'Y'
       AND   coalesce(R.EXPIRED_DATE::text, '') = ''
    ORDER BY LIST_SEQ;
    P_ALERT_DISPLAY_VALUE varchar(54);

BEGIN
     FOR OMCALERT_REC IN OMCALERT
           LOOP
             P_ALERT_DISPLAY_VALUE := concat(P_ALERT_DISPLAY_VALUE,OMCALERT_REC.ALERT_TYPE);
          END LOOP;
     RETURN(P_ALERT_DISPLAY_VALUE);
  END;
$body$
LANGUAGE PLPGSQL
;

CREATE OR REPLACE FUNCTION oms_intake_get_offender_supervision(p_offender_book_id bigint)
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
      lv_caseload_type := oms_intake_get_caseload_type();
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

CREATE OR REPLACE FUNCTION oms_intake_get_offender_supervision(p_offender_book_id integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      lv_level_code        VARCHAR(12);
      lv_domain            reference_codes.domain%TYPE;
      lv_caseload_type     caseloads.caseload_type%TYPE;
      lv_level_description VARCHAR(40);
      lv_value1            system_profiles.profile_value%TYPE;
      lv_value2            system_profiles.profile_value_2%TYPE;
   
      ref_cur CURSOR(p_sup_level VARCHAR, p_domain VARCHAR) FOR
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
   
      lv_level_code    := pims_weight.get_sup_level(p_offender_book_id);
      lv_caseload_type := get_caseload_type;
      -- SQLINES DEMO *** e = 'COMM' THEN
      -- SQLINES DEMO *** SUP_LVLS';
      --ELSE
      -- SQLINES DEMO *** LVL_TYPE';
      --END IF;
      lv_domain := 'SUP_LVL_TYPE';
      OPEN ref_cur(lv_level_code, lv_domain);
      FETCH ref_cur
         INTO lv_level_description;
      CLOSE ref_cur;
      IF lv_level_description IS NULL
      THEN
         OPEN sys_cur;
         FETCH sys_cur
            INTO lv_value1, lv_value2;
         CLOSE sys_cur;
         IF lv_value2 IS NOT NULL
         THEN
            lv_level_description := lv_value2;
         END IF;
      END IF;
      RETURN lv_level_description;
   END;
$function$
;

CREATE OR REPLACE FUNCTION oms_miscellaneous_getdesccode(p_domain character varying, p_refcode character varying DEFAULT '0'::character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      RET_DESCRIPTION VARCHAR(40);
   BEGIN
      -- SQLINES LICENSE FOR EVALUATION USE ONLY
      SELECT DESCRIPTION
        INTO RET_DESCRIPTION
        FROM REFERENCE_CODES
       WHERE DOMAIN = P_DOMAIN
         AND CODE = P_REFCODE;
      RETURN RET_DESCRIPTION;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN RETURN '';
        WHEN OTHERS THEN RETURN SQLERRM;
  END;
$function$
;

CREATE OR REPLACE FUNCTION oms_miscellaneous_oms_getdesccode(p_domain character varying, p_refcode character varying DEFAULT '0'::character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      ret_description VARCHAR(40);
   BEGIN
      SELECT description
        INTO ret_description
        FROM REFERENCE_CODES
       WHERE domain = p_Domain
         AND code = p_RefCode;
      RETURN ret_description;
  END;
$function$
;

CREATE OR REPLACE FUNCTION oms_miscellaneous_staff_name(p_staff_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
   
      L_NAME VARCHAR(100) ;
   BEGIN

      -- SQLINES LICENSE FOR EVALUATION USE ONLY
      SELECT  LAST_NAME||', '||FIRST_NAME
        INTO  L_NAME
        FROM  STAFF_MEMBERS
        WHERE STAFF_ID = P_STAFF_ID ;

       RETURN L_NAME ;
    EXCEPTION
       WHEN NO_DATA_FOUND THEN
            RETURN ' ';
  END;
$function$
;

CREATE OR REPLACE FUNCTION oms_victims_get_name(p_type character varying, p_id character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
     LV_NAME VARCHAR(250) = NULL;
     
     AGENCY_NAME_C CURSOR (P_AGY_LOC_ID VARCHAR)
       FOR 
       SELECT DESCRIPTION
       FROM AGENCY_LOCATIONS
       WHERE AGY_LOC_ID = P_AGY_LOC_ID;
      
       PERSON_NAME_C CURSOR(P_PERSON_ID DOUBLE PRECISION)  
       FOR 
       SELECT LAST_NAME||', '||CASE  WHEN MIDDLE_NAME IS NULL THEN FIRST_NAME ELSE FIRST_NAME||', '||MIDDLE_NAME END PERSON_NAME
       FROM PERSONS
       WHERE PERSON_ID = P_PERSON_ID;
      
       CORPORATE_NAME_C CURSOR (P_CORPORATE_ID DOUBLE PRECISION)
       FOR 
       SELECT CORPORATE_NAME
       FROM CORPORATES
       WHERE CORPORATE_ID = P_CORPORATE_ID;
  BEGIN
     IF P_TYPE = 'AGENCY' THEN  
          OPEN AGENCY_NAME_C(P_ID);
          FETCH AGENCY_NAME_C INTO LV_NAME;
          CLOSE AGENCY_NAME_C;
     ELSIF P_TYPE = 'CORPORATE' THEN    
          OPEN CORPORATE_NAME_C(P_ID::bigint);
          FETCH CORPORATE_NAME_C INTO LV_NAME;
          CLOSE CORPORATE_NAME_C;
       ELSIF P_TYPE = 'PERSON' THEN    
            OPEN PERSON_NAME_C(P_ID::bigint);
            FETCH PERSON_NAME_C INTO LV_NAME;
        CLOSE PERSON_NAME_C;
       END IF;        
  
     RETURN LV_NAME;
     
  EXCEPTION
      WHEN OTHERS
      THEN
         raise notice '% %', SQLERRM, SQLSTATE;

  END;
$function$
;

CREATE OR REPLACE FUNCTION pims_weight_get_caseload_type()
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
     LV_CASELOAD_TYPE CASELOADS.CASELOAD_TYPE%TYPE;
     GET_CASELOAD_TYPE_CUR CURSOR FOR SELECT CASELOAD_TYPE
                                       FROM STAFF_MEMBERS SM,
                                            CASELOADS CL
                                      WHERE SM.USER_ID= CURRENT_USER
                                        AND SM.WORKING_CASELOAD_ID = CL.CASELOAD_ID;
  BEGIN
     OPEN   GET_CASELOAD_TYPE_CUR;
     FETCH  GET_CASELOAD_TYPE_CUR INTO LV_CASELOAD_TYPE;
     CLOSE  GET_CASELOAD_TYPE_CUR;
     RETURN(LV_CASELOAD_TYPE);
  END;
$function$
;

CREATE OR REPLACE FUNCTION pims_weight_get_sup_level(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
    GET_MAX_ASS_SEQ_CUR CURSOR(P_CASELOAD_TYPE VARCHAR) FOR
                                  SELECT MAX(OFF_ASS.ASSESSMENT_SEQ)
                                    FROM OFFENDER_ASSESSMENTS OFF_ASS,
                                         ASSESSMENTS ASS
                                   WHERE OFF_ASS.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
                                   
                                     AND OFF_ASS.ASSESSMENT_TYPE_ID = ASS.ASSESSMENT_ID
                                     AND ASS.CASELOAD_TYPE = P_CASELOAD_TYPE
                                     AND ASS.DETERMINE_SUP_LEVEL_FLAG ='Y'
                                     AND OFF_ASS.REVIEW_SUP_LEVEL_TYPE IS NOT NULL;
    GET_SUP_LEVEL_CUR CURSOR(CP_ASS_SEQ   DOUBLE PRECISION) FOR
                                  SELECT OFF_ASS.REVIEW_SUP_LEVEL_TYPE
                                    FROM OFFENDER_ASSESSMENTS OFF_ASS
                                   WHERE OFF_ASS.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
                                     AND OFF_ASS.ASSESSMENT_SEQ = CP_ASS_SEQ;
    LV_SUPLEVEL        REFERENCE_CODES.CODE%TYPE;
    LV_ASSESSMENT_SEQ  OFFENDER_ASSESSMENTS.ASSESSMENT_SEQ%TYPE;
    LV_CASELOAD_TYPE   CASELOADS.CASELOAD_TYPE%TYPE;
    LV_DET_FLAG        ASSESSMENTS.DETERMINE_SUP_LEVEL_FLAG%TYPE;
  BEGIN
    LV_CASELOAD_TYPE := PIMS_WEIGHT_GET_CASELOAD_TYPE();
    
    OPEN  GET_MAX_ASS_SEQ_CUR(LV_CASELOAD_TYPE);
    FETCH GET_MAX_ASS_SEQ_CUR  INTO LV_ASSESSMENT_SEQ;
    CLOSE GET_MAX_ASS_SEQ_CUR;
    
    OPEN  GET_SUP_LEVEL_CUR(LV_ASSESSMENT_SEQ);
    FETCH GET_SUP_LEVEL_CUR INTO LV_SUPLEVEL;
    CLOSE GET_SUP_LEVEL_CUR;
    RETURN LV_SUPLEVEL;
    
  END;
$function$
;

CREATE OR REPLACE FUNCTION substr(text, numeric)
 RETURNS text
 LANGUAGE sql
 IMMUTABLE STRICT
AS $function$
SELECT
        CASE
            WHEN ABS(TRUNC($2)::INTEGER) > LENGTH($1) THEN
                
                NULL::TEXT

            WHEN TRUNC($2)::INTEGER >= 0 THEN

                SUBSTR($1, CASE WHEN TRUNC($2)::INTEGER = 0 THEN 1 ELSE TRUNC($2)::INTEGER END)

            ELSE
                
                SUBSTR($1, LENGTH($1) + TRUNC($2)::INTEGER + 1)
        END;
$function$
;

CREATE OR REPLACE FUNCTION substr(text, numeric, numeric)
 RETURNS text
 LANGUAGE sql
 IMMUTABLE STRICT
AS $function$
SELECT
        CASE
            WHEN TRUNC($3)::INTEGER <= 0 THEN

                NULL::TEXT

            WHEN ABS(TRUNC($2)::INTEGER) > LENGTH($1) THEN
                
                NULL::TEXT

            WHEN TRUNC($2)::INTEGER >= 0 THEN

                SUBSTR($1, CASE WHEN TRUNC($2)::INTEGER = 0 THEN 1 ELSE TRUNC($2)::INTEGER END, TRUNC($3)::INTEGER)

            ELSE
                
                SUBSTR($1, LENGTH($1) + TRUNC($2)::INTEGER + 1, TRUNC($3)::INTEGER)
        END;
$function$
;

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

CREATE OR REPLACE FUNCTION tag_header_get_booking_count(p_root_offender_id bigint, p_offender_book_id bigint, p_caseload_type character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_BOOKING_COUNT   INT                                    = 0;
      LV_BOOK_ID         OFFENDER_BOOKINGS.OFFENDER_BOOK_ID%TYPE   = 0;
      LV_RETURN_STRING   VARCHAR (25)                             = NULL;
	  LV_PROFILE_VALUE_2     SYSTEM_PROFILES.PROFILE_VALUE%TYPE
                            := OMS_SYSTEM_PROFILE ('CLIENT', 'B/C_STATUS', 2);

      GET_COUNT_CUR CURSOR
      FOR
         SELECT   COUNT (ROOT_OFFENDER_ID)
             FROM OFFENDER_BOOKINGS
            WHERE ROOT_OFFENDER_ID = P_ROOT_OFFENDER_ID
         GROUP BY ROOT_OFFENDER_ID;

      CHECK_MAX_BOOKING CURSOR
      FOR
         SELECT MAX (OFFENDER_BOOK_ID)
           FROM OFFENDER_BOOKINGS
          WHERE ROOT_OFFENDER_ID = P_ROOT_OFFENDER_ID;
   BEGIN
      IF P_ROOT_OFFENDER_ID IS NOT NULL
      THEN
         OPEN GET_COUNT_CUR;

         FETCH GET_COUNT_CUR
          INTO LV_BOOKING_COUNT;

         CLOSE GET_COUNT_CUR;

         IF LV_BOOKING_COUNT = 1
         THEN
            IF P_CASELOAD_TYPE = 'INST'
            THEN
               LV_RETURN_STRING := 'INACTIVE';
            ELSE
               LV_RETURN_STRING := LV_PROFILE_VALUE_2;
            END IF;
         ELSE
            OPEN CHECK_MAX_BOOKING;

            FETCH CHECK_MAX_BOOKING
             INTO LV_BOOK_ID;

            IF NOT FOUND
            THEN
               LV_BOOK_ID := 0;
            END IF;

            CLOSE CHECK_MAX_BOOKING;

            IF LV_BOOK_ID = P_OFFENDER_BOOK_ID
            THEN

               IF P_CASELOAD_TYPE = 'INST'
               THEN
                  LV_RETURN_STRING := 'INACTIVE';
               ELSE
                  LV_RETURN_STRING := LV_PROFILE_VALUE_2;
               END IF;
            ELSE
               LV_RETURN_STRING := 'HISTORIC';
            END IF;
         END IF;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_breach_status(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 



      LV_RETURN_STRING   VARCHAR (40);




      
      CHECK_BREACH_CUR CURSOR
      FOR
         SELECT OP.PROCEEDING_TYPE
           FROM OFFENDER_PROCEEDINGS OP
          WHERE OP.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
            AND OP.PROCEEDING_TYPE IN ('BREACH','RECALL')
            AND OP.PROCEEDING_STATUS = 'ACTIVE'
          ORDER BY CASE OP.PROCEEDING_TYPE WHEN 'RECALL' THEN 0 ELSE 1 END;
   BEGIN
      IF P_OFFENDER_BOOK_ID IS NOT NULL
      THEN
         OPEN CHECK_BREACH_CUR;

         FETCH CHECK_BREACH_CUR
          INTO LV_RETURN_STRING;

         IF NOT FOUND
         THEN
            LV_RETURN_STRING := NULL;
         END IF;

         CLOSE CHECK_BREACH_CUR;
      ELSE
         LV_RETURN_STRING := NULL;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_caseload_type()
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
                                WHERE USER_ID = CURRENT_USER);
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

CREATE OR REPLACE FUNCTION tag_header_get_header_location(p_active_flag character varying, p_comm_active_flag character varying, p_comm_agy_loc_id character varying, p_agy_loc_id character varying, p_liv_unit_id bigint, p_comm_staff_role character varying, p_comm_staff_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_CASELOAD_TYPE     STAFF_MEMBERS.WORKING_CASELOAD_ID%TYPE;
      LV_CASELOAD_ID       CASELOADS.CASELOAD_ID%TYPE;
      LV_LIV_DESCRIPTION   LIVING_UNITS.DESCRIPTION%TYPE;
      LV_RETURN_STRING     VARCHAR (100);
      

       GET_COMM_STAFF_CUR CURSOR 
      IS
         (SELECT LAST_NAME, FIRST_NAME
           FROM STAFF_MEMBERS
          WHERE STAFF_ID = P_COMM_STAFF_ID);

      COMM_STAFF_REC       record;
   BEGIN
      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE();

      IF P_LIV_UNIT_ID IS NOT NULL
      THEN
         SELECT DESCRIPTION
           INTO LV_LIV_DESCRIPTION
           FROM LIVING_UNITS
          WHERE LIVING_UNIT_ID = P_LIV_UNIT_ID;
      ELSE
         LV_LIV_DESCRIPTION := NULL;
      END IF;

      IF LV_CASELOAD_TYPE = 'COMM'
      THEN
         IF P_COMM_ACTIVE_FLAG = 'Y'
         THEN
            IF P_COMM_STAFF_ID IS NOT NULL
            THEN
               OPEN GET_COMM_STAFF_CUR;

               FETCH GET_COMM_STAFF_CUR
                INTO COMM_STAFF_REC;

               CLOSE GET_COMM_STAFF_CUR;
            ELSE
               COMM_STAFF_REC := NULL;
            END IF;

            LV_RETURN_STRING :=
                  concat(P_COMM_AGY_LOC_ID
               ,' - '
                ,COMM_STAFF_REC.LAST_NAME
                ,', '
                ,COMM_STAFF_REC.FIRST_NAME
                ,' ; '
                ,P_COMM_STAFF_ROLE
                ,' : '
                ,P_AGY_LOC_ID);
         ELSIF P_COMM_ACTIVE_FLAG = 'N'
         THEN
            LV_RETURN_STRING :=
                      concat(LV_LIV_DESCRIPTION , ';' , ' : ' , P_COMM_AGY_LOC_ID);
         ELSE
            LV_RETURN_STRING :=
                      concat(LV_LIV_DESCRIPTION , ';' , ' : ' , P_COMM_AGY_LOC_ID);
         END IF;
      ELSE
         LV_RETURN_STRING :=
                      concat(LV_LIV_DESCRIPTION ,';' , ' : ' , P_COMM_AGY_LOC_ID);
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;


CREATE OR REPLACE FUNCTION tag_header_get_header_status(p_active_flag character varying, p_comm_active_flag character varying, p_status_reason character varying, p_header_status_flag character varying, p_comm_status character varying, p_in_out_status character varying, p_root_offender_id bigint, p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_CASELOAD_TYPE   CASELOADS.CASELOAD_TYPE%TYPE;
      LV_RETURN_STRING   VARCHAR (100);
   

      V_CASE_NOTE_TYPE   SYSTEM_PROFILES.PROFILE_VALUE%TYPE;
      V_CNOTE_SUB_TYPE   OFFENDER_CASE_NOTES.CASE_NOTE_SUB_TYPE%TYPE;
      CNOTE_CUR CURSOR (P_CASE_NOTE_TYPE SYSTEM_PROFILES.PROFILE_VALUE%TYPE)
      FOR 
      SELECT CASE_NOTE_SUB_TYPE 
        FROM OFFENDER_CASE_NOTES 
       WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID 
         AND NOTE_SOURCE_CODE = 'COMM' 
         AND CASE_NOTE_TYPE = P_CASE_NOTE_TYPE
       ORDER BY CONTACT_TIME DESC;
   BEGIN

      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE();

      IF LV_CASELOAD_TYPE = 'COMM'
      THEN
         IF P_COMM_ACTIVE_FLAG = 'Y' THEN
            LV_RETURN_STRING := LV_PROFILE_VALUE_1;
            V_CASE_NOTE_TYPE := OMS_MISCELLANEOUS.GET_PROFILE_VALUE('CLIENT','COMM_STATUS2');
            IF V_CASE_NOTE_TYPE IS NOT NULL THEN
              OPEN  CNOTE_CUR (V_CASE_NOTE_TYPE);
              FETCH CNOTE_CUR INTO V_CNOTE_SUB_TYPE;
              CLOSE CNOTE_CUR;
              IF V_CNOTE_SUB_TYPE IS NOT NULL THEN  
                 LV_RETURN_STRING := INITCAP (LV_PROFILE_VALUE_1) || '-' || V_CNOTE_SUB_TYPE;
              ELSE
                 LV_RETURN_STRING := INITCAP (LV_PROFILE_VALUE_1);
              END IF;
              RETURN LV_RETURN_STRING;
            END IF;
         ELSIF P_COMM_ACTIVE_FLAG = 'N' THEN
            LV_RETURN_STRING :=
               tag_header_GET_BOOKING_COUNT (P_ROOT_OFFENDER_ID,
                                  P_OFFENDER_BOOK_ID,
                                  LV_CASELOAD_TYPE
                                 );

         ELSE
            LV_RETURN_STRING := NULL;
         END IF;
      ELSE
         IF P_ACTIVE_FLAG = 'Y'
         THEN
            IF P_IN_OUT_STATUS = 'OUT' AND P_HEADER_STATUS_FLAG = 'Y'
            THEN
               LV_RETURN_STRING := 'ACTIVE';
            ELSE
               LV_RETURN_STRING := 'ACTIVE';
            END IF;
         ELSIF P_ACTIVE_FLAG = 'N'
         THEN
            IF    SUBSTR (P_STATUS_REASON, 5, 4) IN ('ESCP', 'UAL')
               OR P_IN_OUT_STATUS = 'TRN'
            THEN

                
                LV_RETURN_STRING := 'INACTIVE';
            ELSE
               LV_RETURN_STRING :=
                  tag_header_GET_BOOKING_COUNT (P_ROOT_OFFENDER_ID,
                                     P_OFFENDER_BOOK_ID,
                                     LV_CASELOAD_TYPE
                                    );
            END IF;

         ELSE
            LV_RETURN_STRING := NULL;
         END IF;
      END IF;

      RETURN INITCAP (LV_RETURN_STRING);
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_iep_level(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 




      LV_RETURN_STRING   VARCHAR (40);




      GET_IEP_CUR CURSOR
      FOR
         SELECT IEP_LEVEL
           FROM OFFENDER_IEP_LEVELS
          WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
            AND IEP_LEVEL_SEQ =
                   (SELECT MAX (IEP_LEVEL_SEQ)
                      FROM (SELECT *
                              FROM OFFENDER_IEP_LEVELS
                             WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
                               AND IEP_DATE =
                                      (SELECT MAX (IEP_DATE)
                                         FROM OFFENDER_IEP_LEVELS
                                        WHERE OFFENDER_BOOK_ID =
                                                            P_OFFENDER_BOOK_ID)) TMP
                     WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID);
   BEGIN
      IF P_OFFENDER_BOOK_ID IS NOT NULL
      THEN
         OPEN GET_IEP_CUR;

         FETCH GET_IEP_CUR
          INTO LV_RETURN_STRING;

         IF NOT FOUND
         THEN
            LV_RETURN_STRING := NULL;
         END IF;

         CLOSE GET_IEP_CUR;

         LV_RETURN_STRING :=
                 OMS_MISCELLANEOUS.GETDESCCODE ('IEP_LEVEL', LV_RETURN_STRING);
      ELSE
         LV_RETURN_STRING := NULL;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_int_location_code(p_agency_iml_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_RETURN_STRING   AGENCY_INTERNAL_LOCATIONS.DESCRIPTION%TYPE;

      GET_CODE_CUR CURSOR
      FOR
         SELECT DESCRIPTION
           FROM AGENCY_INTERNAL_LOCATIONS
          WHERE INTERNAL_LOCATION_ID = P_AGENCY_IML_ID;
   BEGIN
      IF P_AGENCY_IML_ID IS NOT NULL
      THEN
         OPEN GET_CODE_CUR;

         FETCH GET_CODE_CUR
          INTO LV_RETURN_STRING;

         IF NOT FOUND
         THEN
            LV_RETURN_STRING := NULL;
         END IF;

         CLOSE GET_CODE_CUR;
      ELSE
         LV_RETURN_STRING := NULL;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_living_desc(p_agy_loc_id character varying, p_liv_unit_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 



      LV_LIV_DESCRIPTION   LIVING_UNITS.DESCRIPTION%TYPE;
      LV_RETURN_STRING     VARCHAR (100);




      GET_DESC_CUR CURSOR
      FOR
         SELECT COALESCE (DESCRIPTION, NULL)
           FROM LIVING_UNITS
          WHERE LIVING_UNIT_ID = P_LIV_UNIT_ID;
   BEGIN
      IF P_LIV_UNIT_ID IS NOT NULL
      THEN
         OPEN GET_DESC_CUR;

         FETCH GET_DESC_CUR
          INTO LV_LIV_DESCRIPTION;

         CLOSE GET_DESC_CUR;

         IF LV_LIV_DESCRIPTION IS NOT NULL
         THEN
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            SELECT SUBSTR ((SUBSTR (LV_LIV_DESCRIPTION,
                                    INSTR (LV_LIV_DESCRIPTION, '-', 1)
                                   )
                           ),
                             INSTR (SUBSTR (LV_LIV_DESCRIPTION,
                                            INSTR (LV_LIV_DESCRIPTION, '-', 1)
                                           ),
                                    '-',
                                    1
                                   )
                           + 1
                          )
              INTO LV_RETURN_STRING
              FROM DUAL;
         END IF;
      ELSE
         LV_RETURN_STRING := NULL;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_living_desc(p_agy_loc_id character varying, p_liv_unit_id integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 



      LV_LIV_DESCRIPTION   LIVING_UNITS.DESCRIPTION%TYPE;
      LV_RETURN_STRING     VARCHAR (100);




      GET_DESC_CUR CURSOR
      FOR
         SELECT COALESCE (DESCRIPTION, NULL)
           FROM LIVING_UNITS
          WHERE LIVING_UNIT_ID = P_LIV_UNIT_ID;
   BEGIN
      IF P_LIV_UNIT_ID IS NOT NULL
      THEN
         OPEN GET_DESC_CUR;

         FETCH GET_DESC_CUR
          INTO LV_LIV_DESCRIPTION;

         CLOSE GET_DESC_CUR;

         IF LV_LIV_DESCRIPTION IS NOT NULL
         THEN
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            SELECT SUBSTR ((SUBSTR (LV_LIV_DESCRIPTION,
                                    INSTR (LV_LIV_DESCRIPTION, '-', 1)
                                   )
                           ),
                             INSTR (SUBSTR (LV_LIV_DESCRIPTION,
                                            INSTR (LV_LIV_DESCRIPTION, '-', 1)
                                           ),
                                    '-',
                                    1
                                   )
                           + 1
                          )
              INTO LV_RETURN_STRING
              FROM DUAL;
         END IF;
      ELSE
         LV_RETURN_STRING := NULL;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_location_type(p_agy_loc_id character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_RETURN_STRING   AGENCY_LOCATIONS.AGENCY_LOCATION_TYPE%TYPE;

      GET_TYPE_CUR CURSOR
      FOR
         SELECT AGENCY_LOCATION_TYPE
           FROM AGENCY_LOCATIONS
          WHERE AGY_LOC_ID = P_AGY_LOC_ID;
   BEGIN
      IF P_AGY_LOC_ID IS NOT NULL
      THEN
         OPEN GET_TYPE_CUR;

         FETCH GET_TYPE_CUR
          INTO LV_RETURN_STRING;

         IF NOT FOUND
         THEN
            LV_RETURN_STRING := NULL;
         END IF;

         CLOSE GET_TYPE_CUR;
      ELSE
         LV_RETURN_STRING := NULL;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_officer(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_RETURN_STRING   VARCHAR (115); 
      LV_CASELOAD_TYPE   CASELOADS.CASELOAD_TYPE%TYPE;

      GET_COMM_STAFF_CUR CURSOR
      FOR
         SELECT    INITCAP (SM.LAST_NAME)
                || ', '
                || INITCAP (SM.FIRST_NAME)
          
          
           FROM STAFF_MEMBERS SM, CASE_PLANS CP
          WHERE CP.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
            AND CP.CASE_PLAN_STATUS = 'ACTIVE'
            AND CP.END_DATE IS NULL

            AND SM.STAFF_ID = CP.SAC_STAFF_ID;

      GET_INST_STAFF_CUR CURSOR
      FOR
         SELECT INITCAP (SM.LAST_NAME) || ', ' || INITCAP (SM.FIRST_NAME)
           FROM STAFF_MEMBERS SM, CASE_PLANS CP
          WHERE CP.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
            AND CP.CASE_PLAN_STATUS = 'ACTIVE'
            AND CP.END_DATE IS NULL

            AND SM.STAFF_ID = CP.INST_SAC_STAFF_ID;
   BEGIN

                                  
      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE();

      IF LV_CASELOAD_TYPE = 'COMM'
      THEN
         OPEN GET_COMM_STAFF_CUR;

         FETCH GET_COMM_STAFF_CUR
          INTO LV_RETURN_STRING;

         CLOSE GET_COMM_STAFF_CUR;
      ELSE
         OPEN GET_INST_STAFF_CUR;

         FETCH GET_INST_STAFF_CUR
          INTO LV_RETURN_STRING;

         CLOSE GET_INST_STAFF_CUR;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_prison_location(p_agy_loc_id character varying, p_liv_unit_id bigint, p_agy_iml_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_LOC_DESCRIPTION   AGENCY_LOCATIONS.DESCRIPTION%TYPE;
      LV_RETURN_STRING     VARCHAR (200);

      GET_DESC_CUR CURSOR
      FOR
         SELECT INITCAP (DESCRIPTION)
           FROM AGENCY_LOCATIONS
          WHERE AGY_LOC_ID = P_AGY_LOC_ID;
   BEGIN
      OPEN GET_DESC_CUR;

      FETCH GET_DESC_CUR
       INTO LV_LOC_DESCRIPTION;

      CLOSE GET_DESC_CUR;

      IF P_LIV_UNIT_ID IS NOT NULL
      THEN
         LV_RETURN_STRING :=
               P_AGY_LOC_ID

            || ' ['
            || TAG_INT_LOC_INT_LOC_PATH(P_LIV_UNIT_ID)

            || ']';

         IF P_AGY_IML_ID IS NOT NULL
         THEN
            LV_RETURN_STRING :=
                  LV_RETURN_STRING
               || '-['
               || TAG_INT_LOC_INT_LOC_PATH(P_AGY_IML_ID)

               || ']';
         END IF;
      ELSE
         LV_RETURN_STRING := LV_LOC_DESCRIPTION;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_prison_location(p_active_flag character varying, p_comm_active_flag character varying, p_comm_agy_loc_id character varying, p_agy_loc_id character varying, p_liv_unit_id bigint, p_agy_iml_id bigint, p_offender_book_id bigint)
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
         LV_RETURN_STRING := P_AGY_LOC_ID || ' ['
                                          || TAG_INT_LOC_INT_LOC_PATH(P_LIV_UNIT_ID)
                                          || ']';
         IF P_AGY_IML_ID IS NOT NULL THEN
            LV_RETURN_STRING := LV_RETURN_STRING || '-['
                                                 || TAG_INT_LOC_INT_LOC_PATH(P_AGY_IML_ID)
                                                 || ']';
         END IF;
      ELSE
         LV_RETURN_STRING := LV_LOC_DESCRIPTION;
      END IF;

      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE();

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

            LV_OFFICE_STRING := GET_OFFICER(P_OFFENDER_BOOK_ID);
            IF LV_OFFICE_STRING IS NOT NULL THEN
               LV_OFFICE_STRING := '['|| LV_OFFICE_STRING || ']';
            END IF;

            OPEN  CHECK_MULTI_LOCS_CUR;
            FETCH CHECK_MULTI_LOCS_CUR INTO LV_MULTI_LOC_COUNT;
            CLOSE CHECK_MULTI_LOCS_CUR;
         
            IF LV_MULTI_LOC_COUNT > 1 THEN
               LV_OFFICE_STRING := LV_OFFICE_STRING ||' MULTI';
            END IF;
         
         
            LV_RETURN_STRING := LV_LOC_DESCRIPTION || LV_OFFICE_STRING;

            IF P_AGY_LOC_ID IS NOT NULL THEN 
              LV_RETURN_STRING := LV_LOC_DESCRIPTION
                                 || LV_OFFICE_STRING
                                 || ' ; '
                                 || P_AGY_LOC_ID;
            END IF;
         ELSIF P_COMM_ACTIVE_FLAG = 'N' THEN 
            OPEN  GET_DESC_CUR('OUT');
            FETCH GET_DESC_CUR INTO LV_OUT_DESCRIPTION;
            CLOSE GET_DESC_CUR;
            
            IF P_AGY_LOC_ID IS NOT NULL THEN 
               IF P_AGY_LOC_ID <> 'OUT' THEN  
                  LV_RETURN_STRING := LV_OUT_DESCRIPTION || ';' ||P_AGY_LOC_ID;
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
                LV_RETURN_STRING := LV_RETURN_STRING
                                || ' ; '
                                || LV_AGY_ID;			
			
			ELSE
            
				LV_RETURN_STRING := LV_RETURN_STRING
                                || ' ; '
                                || P_COMM_AGY_LOC_ID;
            END IF;	

		 
         END IF;
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_prison_location_short(p_agy_loc_id character varying, p_liv_unit_id bigint, p_agy_iml_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 



      LV_RETURN_STRING   VARCHAR (100);
   BEGIN
      IF P_LIV_UNIT_ID IS NOT NULL
      THEN
         LV_RETURN_STRING :=
               UPPER (P_AGY_LOC_ID)
            || ' ['
            || GET_LIVING_DESC (P_AGY_LOC_ID, P_LIV_UNIT_ID)
            || ']';

         IF P_AGY_IML_ID IS NOT NULL
         THEN
            LV_RETURN_STRING :=
                  LV_RETURN_STRING
               || '-['
               || GET_INT_LOCATION_CODE (P_AGY_IML_ID)
               || ']';
         END IF;
      ELSE
         LV_RETURN_STRING := UPPER (P_AGY_LOC_ID);
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_prison_location_short(p_agy_loc_id character varying, p_liv_unit_id integer, p_agy_iml_id integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 



      LV_RETURN_STRING   VARCHAR (100);
   BEGIN
      IF P_LIV_UNIT_ID IS NOT NULL
      THEN
         LV_RETURN_STRING :=
               UPPER (P_AGY_LOC_ID)
            || ' ['
            || GET_LIVING_DESC (P_AGY_LOC_ID, P_LIV_UNIT_ID)
            || ']';

         IF P_AGY_IML_ID IS NOT NULL
         THEN
            LV_RETURN_STRING :=
                  LV_RETURN_STRING
               || '-['
               || GET_INT_LOCATION_CODE (P_AGY_IML_ID)
               || ']';
         END IF;
      ELSE
         LV_RETURN_STRING := UPPER (P_AGY_LOC_ID);
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_status_1(p_offender_book_id bigint, p_in_out_status character varying, p_comm_status character varying, p_status_reason character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_return_string   VARCHAR (40);
      lv_caseload_type   VARCHAR(12);
   BEGIN
      lv_caseload_type := tag_header_get_caseload_type();

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
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_status_2(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_return_string   VARCHAR (40);
      lv_caseload_type   VARCHAR(12);

      get_report_cur CURSOR
      FOR
         SELECT order_type
           FROM orders
          WHERE offender_book_id = p_offender_book_id
            AND order_id =
                   (SELECT MAX (order_id)
                      FROM orders
                     WHERE offender_book_id = p_offender_book_id
                       AND order_type != 'AUTO'
                       AND order_status NOT IN ('COMPLETED', 'C'));
   BEGIN
      lv_caseload_type := tag_header_GET_CASELOAD_TYPE();

      IF lv_caseload_type = 'COMM'
      THEN
         OPEN get_report_cur;

         FETCH get_report_cur
          INTO lv_return_string;

         IF NOT FOUND
         THEN
            lv_return_string := NULL;
         END IF;

         CLOSE get_report_cur;
      ELSE
         lv_return_string := tag_header_get_offender_assessment (p_offender_book_id);
      END IF;

      RETURN lv_return_string;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_header_get_status_3(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_return_string   VARCHAR (40);
      lv_caseload_type   varchar(12);
   BEGIN
      lv_caseload_type := 'COMM';

      IF lv_caseload_type = 'COMM'
      THEN
         lv_return_string := tag_header_get_breach_status (p_offender_book_id);
      ELSE
         lv_return_string := tag_header_GET_IEP_LEVEL (p_offender_book_id);
      END IF;

      RETURN lv_return_string;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_active_flag(p_int_loc_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_active_flag         VARCHAR (1);
      lv_parent_int_loc_id   agency_internal_locations.parent_internal_location_id%TYPE;
   BEGIN
          SELECT active_flag, parent_internal_location_id
        INTO lv_active_flag, lv_parent_int_loc_id
        FROM agency_internal_locations ail
       WHERE internal_location_id = p_int_loc_id
         AND EXISTS (
                SELECT 'x'
                  FROM agency_locations al
                 WHERE ail.agy_loc_id = al.agy_loc_id
                   AND (   ail.internal_location_type = al.housing_lev_1_code
                        OR ail.internal_location_type = al.housing_lev_2_code
                        OR ail.internal_location_type = al.housing_lev_3_code
                        OR ail.internal_location_type = al.housing_lev_4_code
                       ));

      IF (lv_active_flag = 'N')
      THEN
         RETURN 'N';
      ELSIF lv_parent_int_loc_id IS NULL
      THEN
         RETURN 'Y';
      ELSE
         RETURN tag_int_loc_active_flag (lv_parent_int_loc_id);
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN NULL;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_int_loc_desc(p_internal_location_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      lv_desc   agency_internal_locations.description%TYPE;
   BEGIN
       SELECT description
        INTO lv_desc
        FROM agency_internal_locations
       WHERE internal_location_id = p_internal_location_id;

      RETURN lv_desc;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN ' ';
   END ;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_int_loc_path(p_internal_location_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_desc                agency_internal_locations.description%TYPE;
      lv_parent_int_loc_id   agency_internal_locations.parent_internal_location_id%TYPE;
   BEGIN
    
      SELECT internal_location_code, parent_internal_location_id
        INTO lv_desc, lv_parent_int_loc_id
        FROM agency_internal_locations
       WHERE internal_location_id = p_internal_location_id;

      IF lv_parent_int_loc_id IS NULL
      THEN
         RETURN lv_desc;
      ELSE
         RETURN tag_int_loc_int_loc_path (lv_parent_int_loc_id) || '-' || lv_desc;
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN ' ';
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_level_code(p_description character varying, p_level integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      lv_desc    VARCHAR (100);
      lv_count   INT;
      lv_pos     INT;
   BEGIN
      lv_desc := p_description;

      FOR lv_count IN 1 .. p_level
      LOOP
         lv_pos := INSTR (lv_desc, '-');

         IF (lv_pos = 0)
         THEN
            lv_desc := NULL;
         ELSE
            lv_desc := SUBSTR (lv_desc, lv_pos + 1, LENGTH (lv_desc) - lv_pos);
         END IF;
      END LOOP;

      lv_pos := INSTR (lv_desc, '-');

      IF (lv_pos > 0)
      THEN
         lv_desc := SUBSTR (lv_desc, 1, lv_pos - 1);
      END IF;

      RETURN lv_desc;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_location_profile_id(p_int_loc_id bigint, p_profile_type character varying)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_profile_id   BIGINT ;
      lv_count        BIGINT ;
      lv_parent_id    BIGINT ;
   BEGIN
      SELECT COUNT (*)
        INTO lv_count
        FROM agy_int_loc_profiles
       WHERE internal_location_id = p_int_loc_id
         AND (int_loc_profile_type = p_profile_type OR p_profile_type IS NULL
             );

      IF lv_count > 0
      THEN
         RETURN (p_int_loc_id);
      ELSE
         SELECT parent_internal_location_id
           INTO lv_parent_id
           FROM agency_internal_locations
          WHERE internal_location_id = p_int_loc_id;

         IF lv_parent_id IS NULL
         THEN
            RETURN NULL;
         ELSE
            RETURN tag_int_loc_location_profile_id (lv_parent_id, p_profile_type);
         END IF;
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN p_int_loc_id;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_int_loc_operation_flag(p_int_loc_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      lv_no_of_occupant       BIGINT ;
      lv_operation_capacity   BIGINT ;
      lv_parent_int_loc_id    agency_internal_locations.internal_location_id%TYPE;
   BEGIN
            SELECT parent_internal_location_id, operation_capacity, no_of_occupant
        INTO lv_parent_int_loc_id, lv_operation_capacity, lv_no_of_occupant
        FROM agency_internal_locations ail
       WHERE internal_location_id = p_int_loc_id
         AND EXISTS (
                SELECT 'x'
                  FROM agency_locations al
                 WHERE ail.agy_loc_id = al.agy_loc_id
                   AND (   ail.internal_location_type = al.housing_lev_1_code
                        OR ail.internal_location_type = al.housing_lev_2_code
                        OR ail.internal_location_type = al.housing_lev_3_code
                        OR ail.internal_location_type = al.housing_lev_4_code
                       ));

      IF     (lv_operation_capacity IS NOT NULL)
         AND (lv_operation_capacity <= lv_no_of_occupant)
      THEN
         RETURN 'Y';
      ELSE
         IF lv_parent_int_loc_id IS NULL
         THEN
            RETURN 'N';
         ELSE
            RETURN tag_int_loc_operation_flag (lv_parent_int_loc_id);
         END IF;
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN p_int_loc_id;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_legal_cases_get_consecutive_line_seq(p_offender_book_id bigint, p_consec_to_sentence_seq integer)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
declare 
   v_line_seq offender_sentences.line_seq%TYPE;

      line_seq_cur CURSOR  FOR
         SELECT line_seq
           FROM offender_sentences
          WHERE offender_book_id = p_offender_book_id
            AND sentence_seq = p_consec_to_sentence_seq;
   BEGIN
      OPEN line_seq_cur;
      FETCH line_seq_cur
         INTO v_line_seq;
      CLOSE line_seq_cur;

      RETURN(v_line_seq);
   EXCEPTION
      WHEN OTHERS THEN
         raise notice '% %',SQLERRM,SQLSTATE;
   END ; 
   $function$
;

CREATE OR REPLACE FUNCTION tag_licence_check_sum(p_timestamp timestamp without time zone)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
   BEGIN
      RETURN cast(TO_CHAR(p_timestamp,
                               'DDHH24MISSFF4') as bigint);
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_course_vacancy(p_course_activity_id bigint)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      lv_capacity     bigint;
      lv_registration bigint;
   BEGIN
        SELECT capacity
        INTO lv_capacity
        FROM course_activities
       WHERE crs_acty_id = p_course_activity_id;

      -- SQLINES LICENSE FOR EVALUATION USE ONLY
      SELECT COUNT(*)
        INTO lv_registration
        FROM offender_program_profiles
       WHERE crs_acty_id = p_course_activity_id
         AND offender_program_status = 'ALLOC';

      RETURN lv_capacity - lv_registration;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_course_vacancy(p_course_activity_id integer)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      lv_capacity     bigint;
      lv_registration bigint;
   BEGIN
        SELECT capacity
        INTO lv_capacity
        FROM course_activities
       WHERE crs_acty_id = p_course_activity_id;

      -- SQLINES LICENSE FOR EVALUATION USE ONLY
      SELECT COUNT(*)
        INTO lv_registration
        FROM offender_program_profiles
       WHERE crs_acty_id = p_course_activity_id
         AND offender_program_status = 'ALLOC';

      RETURN lv_capacity - lv_registration;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_credit_hours(p_offender_book_id bigint, p_sentence_seq bigint, p_offender_sent_condition_id bigint)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      l_hours INT;
   BEGIN
      IF (p_offender_sent_condition_id IS NULL) THEN

       
         SELECT SUM(coalesce(credit_work_hours, 0))
           INTO l_hours
           FROM (SELECT SUM(coalesce(credit_work_hours, 0))  credit_work_hours
                    FROM offender_program_profiles
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq
                  UNION ALL
                  SELECT SUM(coalesce(case adjustment_type  when 'C' then  round(adjustment_minutes / 60, 2)  when 'D' then  -1 *
                                         round(adjustment_minutes / 60, 2)  else 0 end, 0)) credit_work_hours
                    FROM offender_unpaid_work_adj
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq)A;

      ELSE

         -- SQLINES LICENSE FOR EVALUATION USE ONLY
         SELECT SUM(coalesce(credit_work_hours, 0)) into l_hours
           FROM (SELECT SUM(coalesce(credit_work_hours, 0)) credit_work_hours
                    FROM offender_program_profiles
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq
                  UNION ALL
                  SELECT SUM(coalesce(case adjustment_type  when 'C' then  round(adjustment_minutes / 60, 2)  when 'D' then  -1 *
                                         round(adjustment_minutes / 60, 2)  else 0 end, 0)) AS credit_work_hours
                    FROM offender_unpaid_work_adj
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq)as A;
      END IF;

      RETURN l_hours;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN 0;
   END;
   $function$
;

CREATE OR REPLACE FUNCTION tag_prg_credit_hours(p_offender_book_id integer, p_sentence_seq integer, p_offender_sent_condition_id integer)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      l_hours INT;
   BEGIN
      IF (p_offender_sent_condition_id IS NULL) THEN

       
         SELECT SUM(coalesce(credit_work_hours, 0))
           INTO l_hours
           FROM (SELECT SUM(coalesce(credit_work_hours, 0))  credit_work_hours
                    FROM offender_program_profiles
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq
                  UNION ALL
                  SELECT SUM(coalesce(case adjustment_type  when 'C' then  round(adjustment_minutes / 60, 2)  when 'D' then  -1 *
                                         round(adjustment_minutes / 60, 2)  else 0 end, 0)) credit_work_hours
                    FROM offender_unpaid_work_adj
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq)A;

      ELSE

         -- SQLINES LICENSE FOR EVALUATION USE ONLY
         SELECT SUM(coalesce(credit_work_hours, 0)) into l_hours
           FROM (SELECT SUM(coalesce(credit_work_hours, 0)) credit_work_hours
                    FROM offender_program_profiles
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq
                  UNION ALL
                  SELECT SUM(coalesce(case adjustment_type  when 'C' then  round(adjustment_minutes / 60, 2)  when 'D' then  -1 *
                                         round(adjustment_minutes / 60, 2)  else 0 end, 0)) AS credit_work_hours
                    FROM offender_unpaid_work_adj
                   WHERE offender_book_id = p_offender_book_id
                     AND sentence_seq = p_sentence_seq)as A;
      END IF;

      RETURN l_hours;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN 0;
   END;
   $function$
;

CREATE OR REPLACE FUNCTION tag_prg_external_movement_time(p_event_id bigint, p_direction_code character varying)
 RETURNS timestamp without time zone
 LANGUAGE plpgsql
AS $function$
 DECLARE 

      l_move_time offender_external_movements.movement_time%TYPE;
   BEGIN


     IF p_direction_code = 'OUT' THEN
        
        SELECT MAX(movement_time)
          INTO l_move_time
          FROM offender_external_movements
         WHERE event_ID = p_event_ID
           AND direction_code = p_direction_code ;
     ELSIF p_direction_code = 'IN' THEN
       
        SELECT MAX(movement_time)
          INTO l_move_time
          FROM offender_external_movements
         WHERE parent_event_ID = p_event_ID
           AND direction_code = p_direction_code ;
     END IF ;

     RETURN l_move_time ;

   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;

   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_obligation_length(p_offender_book_id bigint, p_sentence_seq integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      l_years  INT;
      l_months INT;
      l_weeks  INT;
      l_days   INT;
      l_hours  INT;
      l_length VARCHAR(40) = NULL;
   BEGIN
      -- SQLINES LICENSE FOR EVALUATION USE ONLY
      SELECT years,
             months,
             weeks,
             days,
             hours
        INTO l_years,
             l_months,
             l_weeks,
             l_days,
             l_hours
        FROM offender_sentence_terms
       WHERE offender_book_id = p_offender_book_id
         AND sentence_seq = p_sentence_seq
        limit 1;

      IF l_years IS NOT NULL THEN
         l_length := l_years || ' Y ';
      END IF;

      IF l_months IS NOT NULL THEN
         l_length := l_length || l_months || ' M ';
      END IF;

      IF l_weeks IS NOT NULL THEN
         l_length := l_length || l_weeks || ' W ';
      END IF;

      IF l_days IS NOT NULL THEN
         l_length := l_length || l_days || ' D ';
      END IF;

      IF l_hours IS NOT NULL THEN
         l_length := l_length || l_hours || ' H ';
      END IF;

      RETURN l_length;
   END ;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_offender_offence_type(p_offender_book_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      l_offence_type VARCHAR(12);
      DECLARE
         off_off_ind CURSOR FOR
            SELECT
    oi.indicator_code
FROM
    offender_charges    os
    LEFT JOIN offence_indicators  oi ON os.statute_code = oi.statute_code
                                       AND os.offence_code = oi.offence_code
WHERE
    oi.indicator_code IN ( 'V', 'S', 'D' )
    AND os.offender_book_id = p_offender_book_id
GROUP BY
    oi.indicator_code
ORDER BY
    oi.indicator_code;
   BEGIN
      BEGIN
         l_offence_type := '';

         FOR rec_ooi IN off_off_ind
         LOOP
            l_offence_type := l_offence_type || rec_ooi.indicator_code;
         END LOOP;

         CLOSE off_off_ind;

         RETURN l_offence_type;
      EXCEPTION
         WHEN OTHERS THEN
            RETURN l_offence_type;
      END;

      RETURN l_offence_type;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_offender_offence_type(p_offender_book_id integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      l_offence_type VARCHAR(12);
      DECLARE
         off_off_ind CURSOR FOR
            SELECT
    oi.indicator_code
FROM
    offender_charges    os
    LEFT JOIN offence_indicators  oi ON os.statute_code = oi.statute_code
                                       AND os.offence_code = oi.offence_code
WHERE
    oi.indicator_code IN ( 'V', 'S', 'D' )
    AND os.offender_book_id = p_offender_book_id
GROUP BY
    oi.indicator_code
ORDER BY
    oi.indicator_code;
   BEGIN
      BEGIN
         l_offence_type := '';

         FOR rec_ooi IN off_off_ind
         LOOP
            l_offence_type := l_offence_type || rec_ooi.indicator_code;
         END LOOP;

         CLOSE off_off_ind;

         RETURN l_offence_type;
      EXCEPTION
         WHEN OTHERS THEN
            RETURN l_offence_type;
      END;

      RETURN l_offence_type;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_offender_release_date(p_offender_book_id bigint)
 RETURNS timestamp without time zone
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      l_rel_date TIMESTAMP(0);
      l_count    INT;
   BEGIN
          SELECT MAX(release_date),
             COUNT(*)
        INTO l_rel_date,
             l_count
        FROM offender_release_details
       WHERE offender_book_id = p_offender_book_id;

      IF (l_count > 0) THEN
         RETURN l_rel_date;
      ELSE
               SELECT MAX(coalesce(sed_overrided_date, sed_calculated_date)),
                COUNT(*)
           INTO l_rel_date,
                l_count
           FROM offender_sent_calculations
          WHERE offender_book_id = p_offender_book_id;

         RETURN l_rel_date;
      END IF;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_offender_release_date(p_offender_book_id integer)
 RETURNS timestamp without time zone
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      l_rel_date TIMESTAMP(0);
      l_count    INT;
   BEGIN
          SELECT MAX(release_date),
             COUNT(*)
        INTO l_rel_date,
             l_count
        FROM offender_release_details
       WHERE offender_book_id = p_offender_book_id;

      IF (l_count > 0) THEN
         RETURN l_rel_date;
      ELSE
               SELECT MAX(coalesce(sed_overrided_date, sed_calculated_date)),
                COUNT(*)
           INTO l_rel_date,
                l_count
           FROM offender_sent_calculations
          WHERE offender_book_id = p_offender_book_id;

         RETURN l_rel_date;
      END IF;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_prg_event_class(p_off_prgref_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 

      l_offender_book_id     offender_program_profiles.offender_book_id%TYPE;
      l_crs_acty_id          offender_program_profiles.crs_acty_id%TYPE;
      l_internal_location_id course_activities.internal_location_id%TYPE;
      l_provider_party_class course_activities.provider_party_class%TYPE;
      l_provider_party_code  course_activities.provider_party_code%TYPE;
      l_agy_loc_id           offender_bookings.agy_loc_id%TYPE;
      l_agency_location_type agency_locations.agency_location_type%TYPE;
      l_acp_event_class      offender_course_attendances.event_class%TYPE;
      l_in_out_status        offender_bookings.in_out_status%TYPE;
      l_to_agy_loc_id        agency_internal_locations.agy_loc_id%TYPE;
   BEGIN


      SELECT
    opp.offender_book_id,
    opp.crs_acty_id,
    ca.internal_location_id,
    ca.provider_party_class,
    ca.provider_party_code,
    bkg.agy_loc_id,
    al.agency_location_type,
    ail.agy_loc_id,
    bkg.in_out_status
    INTO l_offender_book_id,
             l_crs_acty_id,
             l_internal_location_id,
             l_provider_party_class,
             l_provider_party_code,
             l_agy_loc_id,
             l_agency_location_type,
             l_to_agy_loc_id,
             l_in_out_status
FROM
         offender_program_profiles opp
    INNER JOIN course_activities          ca ON opp.crs_acty_id = ca.crs_acty_id
    INNER JOIN offender_bookings          bkg ON opp.offender_book_id = bkg.offender_book_id
    LEFT JOIN agency_locations           al ON bkg.agy_loc_id = al.agy_loc_id
    LEFT JOIN agency_internal_locations  ail ON ca.internal_location_id = ail.internal_location_id
WHERE
    opp.off_prgref_id = p_off_prgref_id;

      IF ((l_agy_loc_id = 'OUT') OR (l_agy_loc_id IS NULL)) THEN
         l_acp_event_class := 'COMM';
      ELSE
         IF (coalesce(l_to_agy_loc_id, l_agy_loc_id) = l_agy_loc_id) AND
             (l_Provider_Party_Class = 'AGY') THEN                      -- SQLINES DEMO *** GY' for provider class
            l_acp_event_class := 'INT_MOV';
         ELSE
            l_acp_event_class := 'EXT_MOV';
         END IF;
      END IF;

      RETURN l_acp_event_class;

   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;

   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_prg_event_class(p_off_prgref_id integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 

      l_offender_book_id     offender_program_profiles.offender_book_id%TYPE;
      l_crs_acty_id          offender_program_profiles.crs_acty_id%TYPE;
      l_internal_location_id course_activities.internal_location_id%TYPE;
      l_provider_party_class course_activities.provider_party_class%TYPE;
      l_provider_party_code  course_activities.provider_party_code%TYPE;
      l_agy_loc_id           offender_bookings.agy_loc_id%TYPE;
      l_agency_location_type agency_locations.agency_location_type%TYPE;
      l_acp_event_class      offender_course_attendances.event_class%TYPE;
      l_in_out_status        offender_bookings.in_out_status%TYPE;
      l_to_agy_loc_id        agency_internal_locations.agy_loc_id%TYPE;
   BEGIN


      SELECT
    opp.offender_book_id,
    opp.crs_acty_id,
    ca.internal_location_id,
    ca.provider_party_class,
    ca.provider_party_code,
    bkg.agy_loc_id,
    al.agency_location_type,
    ail.agy_loc_id,
    bkg.in_out_status
    INTO l_offender_book_id,
             l_crs_acty_id,
             l_internal_location_id,
             l_provider_party_class,
             l_provider_party_code,
             l_agy_loc_id,
             l_agency_location_type,
             l_to_agy_loc_id,
             l_in_out_status
FROM
         offender_program_profiles opp
    INNER JOIN course_activities          ca ON opp.crs_acty_id = ca.crs_acty_id
    INNER JOIN offender_bookings          bkg ON opp.offender_book_id = bkg.offender_book_id
    LEFT JOIN agency_locations           al ON bkg.agy_loc_id = al.agy_loc_id
    LEFT JOIN agency_internal_locations  ail ON ca.internal_location_id = ail.internal_location_id
WHERE
    opp.off_prgref_id = p_off_prgref_id;

      IF ((l_agy_loc_id = 'OUT') OR (l_agy_loc_id IS NULL)) THEN
         l_acp_event_class := 'COMM';
      ELSE
         IF (coalesce(l_to_agy_loc_id, l_agy_loc_id) = l_agy_loc_id) AND
             (l_Provider_Party_Class = 'AGY') THEN                      -- SQLINES DEMO *** GY' for provider class
            l_acp_event_class := 'INT_MOV';
         ELSE
            l_acp_event_class := 'EXT_MOV';
         END IF;
      END IF;

      RETURN l_acp_event_class;

   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;

   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_prg_event_type(p_program_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 

      l_program_category  program_services.program_category%TYPE;
      l_parent_program_id program_services.parent_program_id%TYPE;
   BEGIN


    
      SELECT program_category,
             parent_program_id
        INTO l_program_category,
             l_parent_program_id
        FROM program_services
       WHERE program_id = p_program_id;

      IF (l_program_category IS NOT NULL) THEN
         RETURN l_program_category;
      ELSE
         RETURN prg_event_type(l_parent_program_id);
      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;

   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_provider_name(p_provider_class character varying, p_provider_id bigint, p_provider_code character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      lv_name VARCHAR(80);
   BEGIN
      IF p_provider_class = 'TEAM' THEN
                 SELECT description
           INTO lv_name
           FROM teams
          WHERE team_id = p_provider_id;
      ELSIF p_provider_class = 'PER' THEN
                 SELECT last_name || ' ,' first_name
           INTO lv_name
           FROM persons
          WHERE person_id = p_provider_id;
      ELSIF p_provider_class = 'CORP' THEN
                 SELECT corporate_name
           INTO lv_name
           FROM corporates
          WHERE corporate_id = p_provider_id;
      ELSIF p_provider_class = 'AGY' THEN
                 SELECT description
           INTO lv_name
           FROM agency_locations
          WHERE agy_loc_id = p_provider_code;
      END IF;

      RETURN lv_name;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_provider_name(p_provider_class character varying, p_provider_id integer, p_provider_code character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      lv_name VARCHAR(80);
   BEGIN
      IF p_provider_class = 'TEAM' THEN
                 SELECT description
           INTO lv_name
           FROM teams
          WHERE team_id = p_provider_id;
      ELSIF p_provider_class = 'PER' THEN
                 SELECT last_name || ' ,' first_name
           INTO lv_name
           FROM persons
          WHERE person_id = p_provider_id;
      ELSIF p_provider_class = 'CORP' THEN
                 SELECT corporate_name
           INTO lv_name
           FROM corporates
          WHERE corporate_id = p_provider_id;
      ELSIF p_provider_class = 'AGY' THEN
                 SELECT description
           INTO lv_name
           FROM agency_locations
          WHERE agy_loc_id = p_provider_code;
      END IF;

      RETURN lv_name;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_requirement_desc(p_offender_sent_condition_id bigint, p_offender_book_id bigint, p_sentence_seq bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      l_desc VARCHAR(200);
   BEGIN
      IF (p_offender_sent_condition_id IS NOT NULL) THEN
         SELECT cc.description
           INTO l_desc
           FROM offender_sent_conditions osc,
                community_conditions     cc
          WHERE osc.comm_condition_type = cc.comm_condition_type
            AND osc.comm_condition_code = cc.comm_condition_code
            AND osc.offender_sent_condition_id =
                p_offender_sent_condition_id;
      ELSE
         SELECT scc.description
           INTO l_desc
           FROM offender_sentences  os,
                sentence_calc_types scc
          WHERE os.sentence_calc_type = scc.sentence_calc_type
            AND os.sentence_category = scc.sentence_category
            AND os.offender_book_id = p_offender_book_id
            AND os.sentence_seq = p_sentence_seq;
      END IF;

      RETURN l_desc;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN '';
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_prg_root_program_profile(p_off_prgref_id bigint)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
 DECLARE 

      l_parent_off_prgref_id  offender_program_profiles.parent_off_prgref_id%TYPE;
      l_program_off_prgref_id offender_program_profiles.program_off_prgref_id%TYPE;
   BEGIN


      SELECT program_off_prgref_id,
             parent_off_prgref_id
        INTO l_program_off_prgref_id,
             l_parent_off_prgref_id
        FROM offender_program_profiles
       WHERE off_prgref_id = p_off_prgref_id;

      IF (l_program_off_prgref_id IS NOT NULL) THEN
         RETURN root_program_profile(l_program_off_prgref_id);
      ELSIF (l_parent_off_prgref_id IS NOT NULL) THEN
         RETURN root_program_profile(l_parent_off_prgref_id);
      ELSE
         RETURN p_off_prgref_id;
      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;

   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_reference_codes_getdesccode(p_domain character varying, p_refcode character varying DEFAULT '0'::character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      ret_description VARCHAR(40);
   BEGIN
      SELECT description
        INTO ret_description
        FROM REFERENCE_CODES
       WHERE domain = p_Domain
         AND code = p_RefCode;
      RETURN ret_description;
  END;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_busy_date_flag(p_offender_book_id bigint, p_event_date timestamp without time zone)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      V_COUNT   bigint;
   BEGIN

      SELECT COUNT (*)
        INTO V_COUNT
        FROM V_OFFENDER_ALL_SCHEDULES_2
       WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
         AND EVENT_STATUS = 'SCH'
         AND EVENT_DATE = P_EVENT_DATE;

      IF V_COUNT > 1
      THEN
         RETURN 'Y';
      ELSE
         RETURN 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
            END ;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_busy_date_flag(p_offender_book_id character varying, p_event_date timestamp without time zone)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      V_COUNT   bigint;
   BEGIN

      SELECT COUNT (*)
        INTO V_COUNT
        FROM V_OFFENDER_ALL_SCHEDULES_2
       WHERE OFFENDER_BOOK_ID::character varying = P_OFFENDER_BOOK_ID::character varying
         AND EVENT_STATUS = 'SCH'
         AND EVENT_DATE = P_EVENT_DATE;

      IF V_COUNT > 1
      THEN
         RETURN 'Y';
      ELSE
         RETURN 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
            END ;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_busy_date_flag(p_offender_book_id numeric, p_event_date timestamp without time zone)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 
      V_COUNT   bigint;
   BEGIN

      SELECT COUNT (*)
        INTO V_COUNT
        FROM V_OFFENDER_ALL_SCHEDULES_2
       WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
         AND EVENT_STATUS = 'SCH'
         AND EVENT_DATE = P_EVENT_DATE;

      IF V_COUNT > 1
      THEN
         RETURN 'Y';
      ELSE
         RETURN 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE NOTICE '% %', SQLERRM,SQLSTATE;
            END ;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_check_sum(p_timestamp timestamp without time zone)
 RETURNS bigint
 LANGUAGE plpgsql
AS $function$
BEGIN

      RETURN cast(TO_CHAR(P_TIMESTAMP, 'YYYYMMDDHH24MISSFF4') as bigint) ;

   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_event_sub_type_desc(p_event_class character varying, p_event_type character varying, p_event_sub_type character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      LV_DESC     REFERENCE_CODES.DESCRIPTION%TYPE;
      LV_DOMAIN   REFERENCE_CODES.DOMAIN%TYPE;
   BEGIN
      CASE P_EVENT_CLASS
         WHEN 'EXT_MOV'
         THEN
            LV_DOMAIN := 'MOVE_RSN';
         WHEN 'INT_MOV'
         THEN
            LV_DOMAIN := 'INT_SCH_RSN';
         WHEN 'COMM'
         THEN
            LV_DOMAIN := 'EVENTS';
         ELSE
            LV_DOMAIN := 'EVENTS';
      END CASE;

     IF P_EVENT_TYPE = 'CRT' THEN
        LV_DOMAIN := 'MOVE_RSN';
     END IF ;
     
      -- SQLINES LICENSE FOR EVALUATION USE ONLY
      SELECT DESCRIPTION
        INTO LV_DESC
        FROM REFERENCE_CODES
       WHERE CODE = P_EVENT_SUB_TYPE AND DOMAIN = LV_DOMAIN;

      RETURN LV_DESC;
   EXCEPTION
      
      
      WHEN NO_DATA_FOUND
      THEN
         RETURN NULL;
      WHEN OTHERS 
      THEN
         RAISE NOTICE '% %', SQLERRM,sqlstate;
		 END;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_event_type_desc(p_event_class character varying, p_event_type character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      LV_DESC     REFERENCE_CODES.DESCRIPTION%TYPE;
      LV_DOMAIN   REFERENCE_CODES.DOMAIN%TYPE;
   BEGIN
      CASE P_EVENT_CLASS
         WHEN 'EXT_MOV'
         THEN
            LV_DOMAIN := 'MOVE_TYPE';
         WHEN 'INT_MOV'
         THEN
            LV_DOMAIN := 'INT_SCH_TYPE';
         WHEN 'COMM'
         THEN
            LV_DOMAIN := 'EVENTS';
         ELSE
            LV_DOMAIN := 'EVENTS';
      END CASE;
     
      -- SQLINES LICENSE FOR EVALUATION USE ONLY
      SELECT DESCRIPTION
        INTO LV_DESC
        FROM REFERENCE_CODES
       WHERE CODE = P_EVENT_TYPE AND DOMAIN = LV_DOMAIN;

      RETURN LV_DESC;
   EXCEPTION
      
      
      WHEN NO_DATA_FOUND
      THEN
         RETURN NULL;
      WHEN OTHERS
      THEN
         RAISE NOTICE '% %', SQLERRM,sqlstate;
         
   END ;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_schedule_date()
 RETURNS timestamp without time zone
 LANGUAGE plpgsql
AS $function$
   DECLARE 
       CURRENT_SCHEDULE_DATE    DATE;
      L_SCHEDULE_DATE TIMESTAMP(0) ;
   BEGIN
      IF (CURRENT_SCHEDULE_DATE IS NOT NULL)
      THEN
         RETURN CURRENT_SCHEDULE_DATE;
      ELSE
           SELECT  cast(PROFILE_VALUE as date)
      INTO    L_SCHEDULE_DATE
      FROM    SYSTEM_PROFILES
      WHERE   PROFILE_TYPE = 'CLIENT'
      AND     PROFILE_CODE = 'SCH_DATE' ;

      CURRENT_SCHEDULE_DATE := L_SCHEDULE_DATE ;
      RETURN CURRENT_SCHEDULE_DATE ;

      END IF;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_schedule_view_check_sum(p_timestamp timestamp without time zone)
 RETURNS bigint
 LANGUAGE plpgsql
AS $function$
BEGIN

      RETURN cast(TO_CHAR(P_TIMESTAMP, 'YYYYMMDDHH24MISSFF4') as bigint) ;

   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_utils_get_ref_desc(p_domain character varying, p_code character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      L_DESC REFERENCE_CODES.DESCRIPTION%TYPE = '';
   BEGIN
   
      
         SELECT MAX(DESCRIPTION)
        INTO L_DESC
        FROM REFERENCE_CODES
       WHERE DOMAIN = P_DOMAIN
         AND CODE = P_CODE;
   
      RETURN L_DESC;
   EXCEPTION
      WHEN OTHERS THEN
         raise notice '% %',SQLERRM,SQLSTATE;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_utils_get_ref_descp(p_domain character varying, p_code character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      V_DESCP REFERENCE_CODES.DESCRIPTION%TYPE = '';
      REF_DESCP_CUR CURSOR FOR
         SELECT DESCRIPTION
           FROM REFERENCE_CODES
          WHERE DOMAIN = P_DOMAIN
            AND CODE = P_CODE
            AND ACTIVE_FLAG = 'Y'
            AND EXPIRED_DATE IS NULL;
   BEGIN
   
      OPEN REF_DESCP_CUR;
      FETCH REF_DESCP_CUR
         INTO V_DESCP;
      CLOSE REF_DESCP_CUR;
      RETURN V_DESCP;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$function$
;

CREATE OR REPLACE FUNCTION tag_utils_get_sys_profile(p_ptype character varying, p_pcode character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      V_VALUE SYSTEM_PROFILES.PROFILE_VALUE%TYPE = '';
      SYS_PROFILE_CUR CURSOR FOR
         SELECT PROFILE_VALUE
           FROM SYSTEM_PROFILES
          WHERE PROFILE_TYPE = P_PTYPE
            AND PROFILE_CODE = P_PCODE;
   BEGIN
      OPEN SYS_PROFILE_CUR;
      FETCH SYS_PROFILE_CUR
         INTO V_VALUE;
      CLOSE SYS_PROFILE_CUR;
      RETURN V_VALUE;
   EXCEPTION
      WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$function$
;

CREATE OR REPLACE FUNCTION trust_get_caseload_type(csld_id character varying, INOUT csld_type character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE
      csld_type_c CURSOR FOR
         SELECT caseload_type FROM caseloads WHERE caseload_id = csld_id;
   BEGIN

      OPEN csld_type_c;
      FETCH csld_type_c
         INTO csld_type;
      CLOSE csld_type_c;
   EXCEPTION
      WHEN OTHERS THEN
raise notice '% %',sqlerrm,sqlstate;
   END;
$function$
;
CREATE OR REPLACE FUNCTION tag_header_get_header_location(p_active_flag character varying, p_comm_active_flag character varying, p_comm_agy_loc_id character varying, p_agy_loc_id character varying, p_liv_unit_id integer, p_comm_staff_role character varying, p_comm_staff_id integer)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_CASELOAD_TYPE     STAFF_MEMBERS.WORKING_CASELOAD_ID%TYPE;
      LV_CASELOAD_ID       CASELOADS.CASELOAD_ID%TYPE;
      LV_LIV_DESCRIPTION   LIVING_UNITS.DESCRIPTION%TYPE;
      LV_RETURN_STRING     VARCHAR (100);
      

       GET_COMM_STAFF_CUR CURSOR 
      IS
         (SELECT LAST_NAME, FIRST_NAME
           FROM STAFF_MEMBERS
          WHERE STAFF_ID = P_COMM_STAFF_ID);

      COMM_STAFF_REC       record;
   BEGIN
      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE();

      IF P_LIV_UNIT_ID IS NOT NULL
      THEN
         SELECT DESCRIPTION
           INTO LV_LIV_DESCRIPTION
           FROM LIVING_UNITS
          WHERE LIVING_UNIT_ID = P_LIV_UNIT_ID;
      ELSE
         LV_LIV_DESCRIPTION := NULL;
      END IF;

      IF LV_CASELOAD_TYPE = 'COMM'
      THEN
         IF P_COMM_ACTIVE_FLAG = 'Y'
         THEN
            IF P_COMM_STAFF_ID IS NOT NULL
            THEN
               OPEN GET_COMM_STAFF_CUR;

               FETCH GET_COMM_STAFF_CUR
                INTO COMM_STAFF_REC;

               CLOSE GET_COMM_STAFF_CUR;
            ELSE
               COMM_STAFF_REC := NULL;
            END IF;

            LV_RETURN_STRING :=
                  concat(P_COMM_AGY_LOC_ID
               ,' - '
                ,COMM_STAFF_REC.LAST_NAME
                ,', '
                ,COMM_STAFF_REC.FIRST_NAME
                ,' ; '
                ,P_COMM_STAFF_ROLE
                ,' : '
                ,P_AGY_LOC_ID);
         ELSIF P_COMM_ACTIVE_FLAG = 'N'
         THEN
            LV_RETURN_STRING :=
                      concat(LV_LIV_DESCRIPTION , ';' , ' : ' , P_COMM_AGY_LOC_ID);
         ELSE
            LV_RETURN_STRING :=
                      concat(LV_LIV_DESCRIPTION , ';' , ' : ' , P_COMM_AGY_LOC_ID);
         END IF;
      ELSE
         LV_RETURN_STRING :=
                      concat(LV_LIV_DESCRIPTION ,';' , ' : ' , P_COMM_AGY_LOC_ID);
      END IF;

      RETURN LV_RETURN_STRING;
   END;
$function$
;

CREATE OR REPLACE FUNCTION oms_system_profile(
	p_profile_type character varying,
	p_profile_code character varying,
	p_value_no integer)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE 
   v_Profile_value      System_Profiles.Profile_Value%TYPE ;
   v_Profile_value_2    System_Profiles.Profile_Value_2%TYPE ;
   BEGIN
        -- SQLINES LICENSE FOR EVALUATION USE ONLY
        SELECT  Profile_value, Profile_Value_2
        INTO    v_Profile_Value, v_Profile_value_2
        FROM    System_Profiles
        WHERE   profile_type = p_Profile_type
        AND     Profile_Code = p_Profile_code ;
        IF p_Value_no = 1
           THEN RETURN v_Profile_Value ;
        ELSIF p_Value_no = 2
           THEN RETURN v_Profile_Value_2 ;
        ELSE RETURN NULL ;
        END IF ;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 'Not found '||p_Profile_type||'-'||p_Profile_code;
        WHEN OTHERS THEN
            RETURN SQLERRM;
   END ;
$BODY$;

ALTER FUNCTION oms_system_profile(character varying, character varying, integer)
    OWNER TO oms_owner;
	

CREATE OR REPLACE FUNCTION oms_system_profile(
	p_profile_type text,
	p_profile_code text,
	p_value_no numeric)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE SECURITY DEFINER PARALLEL UNSAFE
AS $BODY$
DECLARE

   v_Profile_value      System_Profiles.Profile_Value%TYPE;
   v_Profile_value_2    System_Profiles.Profile_Value_2%TYPE;

BEGIN
        SELECT  Profile_value, Profile_Value_2
        INTO STRICT    v_Profile_Value, v_Profile_value_2
        FROM    System_Profiles
        WHERE   profile_type = p_Profile_type
        AND     Profile_Code = p_Profile_code;
        IF p_Value_no = 1
           THEN RETURN v_Profile_Value;
        ELSIF p_Value_no = 2
           THEN RETURN v_Profile_Value_2;
        ELSE RETURN NULL;
        END IF;
        EXCEPTION
        WHEN no_data_found THEN
            RETURN 'Not found '||p_Profile_type||'-'||p_Profile_code;
        WHEN OTHERS THEN
            RETURN SQLERRM;
   END;
------------------------------------------------------------------------------
$BODY$;

ALTER FUNCTION oms_system_profile(text, text, numeric)
    OWNER TO oms_owner;	
	
CREATE OR REPLACE FUNCTION oms_intake_get_caseload_type()
 RETURNS character varying
 LANGUAGE plpgsql
 SECURITY DEFINER
AS $function$
DECLARE

      lv_caseload_type caseloads.caseload_type%TYPE;
      get_caseload_type_cur CURSOR FOR
         SELECT caseload_type
           FROM staff_members sm, caseloads cl
          WHERE sm.user_id = upper(user)
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

ALTER FUNCTION oms_intake_get_caseload_type()
    OWNER TO oms_owner;	
	
CREATE OR REPLACE FUNCTION tag_header_get_offender_assessment(
	p_offender_book_id bigint)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE

      LV_DOMAIN              REFERENCE_CODES.DOMAIN%TYPE;
      GET_MAX_ASS_SEQ_CUR CURSOR(P_CASELOAD_TYPE text)
      FOR
         SELECT coalesce(MAX(OFF_ASS.ASSESSMENT_SEQ), 0)
           FROM OFFENDER_ASSESSMENTS OFF_ASS, ASSESSMENTS ASS
          WHERE OFF_ASS.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
            AND OFF_ASS.ASSESSMENT_TYPE_ID = ASS.ASSESSMENT_ID
            AND ASS.CASELOAD_TYPE = P_CASELOAD_TYPE
            AND ASS.DETERMINE_SUP_LEVEL_FLAG = 'Y'
            AND OFF_ASS.EVALUATION_RESULT_CODE = 'APP';
      GET_SUP_LEVEL_CUR CURSOR(CP_ASS_SEQ bigint)
      FOR
         SELECT OFF_ASS.REVIEW_SUP_LEVEL_TYPE
           FROM OFFENDER_ASSESSMENTS OFF_ASS
          WHERE OFF_ASS.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
            AND OFF_ASS.ASSESSMENT_SEQ = CP_ASS_SEQ;
      LV_SUPLEVEL            REFERENCE_CODES.CODE%TYPE;
      LV_ASSESSMENT_SEQ      OFFENDER_ASSESSMENTS.ASSESSMENT_SEQ%TYPE;
      LV_CASELOAD_TYPE       CASELOADS.CASELOAD_TYPE%TYPE;
      LV_LEVEL_DESCRIPTION   REFERENCE_CODES.DESCRIPTION%TYPE;
      REF_CUR CURSOR(
         P_SUP_LEVEL   text,
         P_DOMAIN      REFERENCE_CODES.DOMAIN%TYPE
      )
      FOR
         SELECT DESCRIPTION
           FROM REFERENCE_CODES
          WHERE DOMAIN = P_DOMAIN AND CODE = P_SUP_LEVEL;

BEGIN
      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE();
      OPEN GET_MAX_ASS_SEQ_CUR(LV_CASELOAD_TYPE);
      FETCH GET_MAX_ASS_SEQ_CUR
       INTO LV_ASSESSMENT_SEQ;
      CLOSE GET_MAX_ASS_SEQ_CUR;
      IF LV_ASSESSMENT_SEQ = 0
      THEN
         LV_LEVEL_DESCRIPTION :=
                             OMS_SYSTEM_PROFILE('CLIENT', 'PENDING_STAT', 2);
      ELSE
         OPEN GET_SUP_LEVEL_CUR(LV_ASSESSMENT_SEQ);
         FETCH GET_SUP_LEVEL_CUR
          INTO LV_SUPLEVEL;
         CLOSE GET_SUP_LEVEL_CUR;
         IF LV_CASELOAD_TYPE = 'COMM'
         THEN
            LV_DOMAIN := 'COM_SUP_LVLS';
         ELSE
            LV_DOMAIN := 'SUP_LVL_TYPE';
         END IF;
         OPEN REF_CUR(LV_SUPLEVEL, LV_DOMAIN);
         FETCH REF_CUR
          INTO LV_LEVEL_DESCRIPTION;
         CLOSE REF_CUR;
      END IF;
      RETURN LV_LEVEL_DESCRIPTION;
   END;
$BODY$;

ALTER FUNCTION tag_header_get_offender_assessment(bigint)
    OWNER TO oms_owner;	
CREATE OR REPLACE FUNCTION round(
	date)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT $1;
$BODY$;

ALTER FUNCTION round(date)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(date)
    IS 'Round dates according to the specified format';
	
CREATE OR REPLACE FUNCTION round(
	value date,
	fmt text)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1::TIMESTAMP(0), $2)::DATE;
$BODY$;

ALTER FUNCTION round(date, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(date, text)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp with time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone,
	fmt text)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT NULL::TIMESTAMP WITH TIME ZONE;
$BODY$;

ALTER FUNCTION round(timestamp with time zone, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone, text)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp without time zone)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp without time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp without time zone)
    IS 'Round dates according to the specified format';
	
CREATE OR REPLACE FUNCTION round(
	date)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT $1;
$BODY$;

ALTER FUNCTION round(date)
    OWNER TO oms_owner;
COMMENT ON FUNCTION round(date)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone,
	fmt text)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT NULL::TIMESTAMP WITH TIME ZONE;
$BODY$;

ALTER FUNCTION round(timestamp with time zone, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone, text)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp without time zone)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp without time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp without time zone)
    IS 'Round dates according to the specified format';
CREATE OR REPLACE FUNCTION round(
	value date,
	fmt text)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1::TIMESTAMP(0), $2)::DATE;
$BODY$;

ALTER FUNCTION round(date, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(date, text)
    IS 'Round dates according to the specified format';
CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp with time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone)
    IS 'Round dates according to the specified format';	
CREATE OR REPLACE FUNCTION last_day(
	pdate timestamp with time zone)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT MAKE_TIMESTAMPTZ
    (
        EXTRACT('YEAR' FROM pDate)::INT,
        EXTRACT('MONTH' FROM pDate)::INT,
        EXTRACT('DAY' FROM (DATE_TRUNC('MONTH', pDate) +'1MONTH'::INTERVAL -'1 DAY'::INTERVAL))::INT,
        EXTRACT('HOUR' FROM PDATE)::INT,
        EXTRACT('MIN' FROM PDATE)::INT,
        EXTRACT('SEC' FROM PDATE)
    );
$BODY$;

ALTER FUNCTION last_day(timestamp with time zone)
    OWNER TO oms_owner;

CREATE OR REPLACE FUNCTION last_day(
	pdate timestamp without time zone)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT MAKE_TIMESTAMP
    (
        EXTRACT('YEAR' FROM pDate)::INT,
        EXTRACT('MONTH' FROM pDate)::INT,
        EXTRACT('DAY' FROM (DATE_TRUNC('MONTH', pDate) + '1MONTH'::INTERVAL - '1 DAY'::INTERVAL))::INT,
        EXTRACT('HOUR' FROM pDate)::INT,
        EXTRACT('MIN' FROM pDate)::INT,
        EXTRACT('SEC' FROM pDate)
    );
$BODY$;

ALTER FUNCTION last_day(timestamp without time zone)
    OWNER TO oms_owner;

CREATE OR REPLACE FUNCTION last_day(
	pdate date)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT (
        DATE_TRUNC('month', pDate) +'1month'::INTERVAL - '1 day'::INTERVAL
    )::DATE;
$BODY$;

ALTER FUNCTION last_day(date)
    OWNER TO oms_owner;
CREATE OR REPLACE FUNCTION months_between(
	enddate1 timestamp without time zone,
	startdate2 timestamp without time zone)
    RETURNS numeric
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT
    CASE
        WHEN DATE_TRUNC('DAY', $1) = DATE_TRUNC('DAY', $2) THEN
        
            0::NUMERIC

        WHEN EXTRACT(DAY FROM $1) = EXTRACT(DAY FROM $2) THEN

            ROUND
            (
                EXTRACT(YEARS FROM AGE($1, $2)) * 12 +
                EXTRACT(MONTHS FROM AGE($1, $2))  +
                EXTRACT(DAYS FROM AGE($1, $2)) /31 +
                EXTRACT(HOURS FROM AGE($1, $2)) /31 /24 +
                EXTRACT(SECONDS FROM AGE($1, $2)) /86400 /31
            )::NUMERIC

        WHEN DATE_TRUNC('DAY', last_day($1)) = DATE_TRUNC('DAY', $1) AND
            DATE_TRUNC('DAY', last_day($2)) = DATE_TRUNC('DAY', $2) THEN

            FLOOR
            (
                EXTRACT(YEARS FROM AGE($1, $2)) * 12 +
                EXTRACT(MONTHS FROM AGE($1, $2))  +
                EXTRACT(DAYS FROM AGE($1, $2)) /31 +
                EXTRACT(HOURS FROM AGE($1, $2)) /31 /24 +
                EXTRACT(SECONDS FROM AGE($1, $2)) /86400 /31
            )::NUMERIC

        ELSE

            (
                EXTRACT(YEARS FROM AGE($1, $2)) * 12 +
                EXTRACT(MONTHS FROM AGE($1, $2))  +
                EXTRACT(DAYS FROM AGE($1, $2)) /31 +
                EXTRACT(HOURS FROM AGE($1, $2)) /31 /24 +
                EXTRACT(SECONDS FROM AGE($1, $2)) /86400 /31
            )::NUMERIC

    END;
$BODY$;

ALTER FUNCTION months_between(timestamp without time zone, timestamp without time zone)
    OWNER TO oms_owner;
	
CREATE OR REPLACE FUNCTION TRUST_GET_CASELOAD_TYPE(csld_id   IN character varying
                              ,csld_type IN OUT character varying) RETURNS character varying 
                               LANGUAGE plpgsql

AS 
$$
 DECLARE
      csld_type_c CURSOR FOR
         SELECT caseload_type FROM caseloads WHERE caseload_id = csld_id;
   BEGIN

      OPEN csld_type_c;
      FETCH csld_type_c
         INTO csld_type;
      CLOSE csld_type_c;
   EXCEPTION
      WHEN OTHERS THEN
raise notice '% %',sqlerrm,sqlstate;
   END;
$$;



		