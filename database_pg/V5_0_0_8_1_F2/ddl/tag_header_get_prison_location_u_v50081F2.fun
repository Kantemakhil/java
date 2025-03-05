CREATE OR REPLACE FUNCTION oms_owner.tag_header_get_prison_location_u(p_active_flag character varying, p_comm_active_flag character varying, p_comm_agy_loc_id character varying, p_agy_loc_id character varying, p_liv_unit_id bigint, p_agy_iml_id bigint, p_offender_book_id bigint, username text)
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
       SELECT CP.CAL_AGY_LOC_ID
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

         IF (P_COMM_ACTIVE_FLAG = 'Y') THEN  
         
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

            LV_OFFICE_STRING := tag_header_GET_OFFICER_u(P_OFFENDER_BOOK_ID,username);
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
