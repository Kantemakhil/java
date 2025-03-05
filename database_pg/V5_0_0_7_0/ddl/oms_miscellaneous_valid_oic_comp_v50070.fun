CREATE OR REPLACE FUNCTION oms_owner.oms_miscellaneous_valid_oic_comp ( P_SANCTION_CODE text ,P_HEARING_TYPE text ,P_MONTHS bigint ,P_DAYS bigint ,P_EFFECTIVE_DATE timestamp ) RETURNS varchar AS $body$
DECLARE

      L_NO_DAYS bigint := 0;
      L_NO_MONTHS bigint := 0;

BEGIN
      SELECT MAX_MONTH, MAX_DAYS
        INTO STRICT L_NO_MONTHS, L_NO_DAYS
        FROM OIC_SANCTION_LIMITS
       WHERE OIC_SANCTION_CODE = P_SANCTION_CODE
         AND OIC_HEARING_TYPE = P_HEARING_TYPE;
      IF P_EFFECTIVE_DATE + coalesce(P_DAYS, 0) + coalesce(P_MONTHS, 0)*'1 month'::interval >
         P_EFFECTIVE_DATE + coalesce(L_NO_DAYS, 0) + coalesce(L_NO_MONTHS, 0)*'1 month'::interval
      THEN
         RETURN('N');
      ELSE
         RETURN('Y');
      END IF;
      RETURN 'Y';
   EXCEPTION
        WHEN no_data_found THEN
            RETURN '0';
        WHEN OTHERS THEN
          RAISE EXCEPTION '%', oms_utils_display_user_message(
                10,
                'OMS',
                'OMFVCPC0',
                SQLERRM
            )
         USING ERRCODE = '45000';
   END;
$body$
LANGUAGE PLPGSQL
;