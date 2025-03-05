CREATE OR REPLACE FUNCTION oms_owner.tag_header_get_header_location_u(p_active_flag character varying, p_comm_active_flag character varying, p_comm_agy_loc_id character varying, p_agy_loc_id character varying, p_liv_unit_id bigint, p_comm_staff_role character varying, p_comm_staff_id bigint, username character varying)
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

      	COMM_STAFF_REC     GET_COMM_STAFF_CUR_type;
   BEGIN
      LV_CASELOAD_TYPE := tag_header_get_caseload_type_u(username);

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


CREATE OR REPLACE FUNCTION oms_owner.tag_header_get_status_2_u(p_offender_book_id bigint, username character varying)
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
      lv_caseload_type := tag_header_GET_CASELOAD_TYPE_u(username);

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


CREATE OR REPLACE FUNCTION oms_owner.tag_header_get_officer_u(p_offender_book_id bigint, username character varying)
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

                                  
      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE_u(username);

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


CREATE OR REPLACE FUNCTION oms_owner.tag_header_get_header_status_u(p_active_flag character varying, p_comm_active_flag character varying, p_status_reason character varying, p_header_status_flag character varying, p_comm_status character varying, p_in_out_status character varying, p_root_offender_id bigint, p_offender_book_id bigint, username character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE 

      LV_CASELOAD_TYPE   CASELOADS.CASELOAD_TYPE%TYPE;
      LV_RETURN_STRING   VARCHAR (100);
      LV_PROFILE_VALUE_1     SYSTEM_PROFILES.PROFILE_VALUE%TYPE:= oms_system_profile ('CLIENT', 'B/C_STATUS', 1);
   

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

      LV_CASELOAD_TYPE := tag_header_GET_CASELOAD_TYPE_u(username);

      IF LV_CASELOAD_TYPE = 'COMM'
      THEN
         IF P_COMM_ACTIVE_FLAG = 'Y' THEN
            LV_RETURN_STRING := LV_PROFILE_VALUE_1;
            V_CASE_NOTE_TYPE := OMS_MISCELLANEOUS_GET_PROFILE_VALUE('CLIENT','COMM_STATUS2');
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
