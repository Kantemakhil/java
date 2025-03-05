CREATE OR REPLACE FUNCTION oms_owner.indigent_ind_flagformula(p_off_id bigint, p_csld_id character varying, p_agyloc_id character varying)
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
                  WHERE CASELOAD_TYPE = (SELECT caseload_type FROM caseloads WHERE caseload_id = P_CSLD_ID))
         UNION
         SELECT TRUST_ACCOUNT_CODE
           FROM OFFENDER_SUB_ACCOUNTS
          WHERE OFFENDER_ID = P_OFF_ID
            AND CASELOAD_ID = P_CSLD_ID
            AND MINIMUM_BALANCE IS NOT NULL
            AND TRUST_ACCOUNT_CODE IN
                (SELECT ACCOUNT_CODE
                   FROM ACCOUNT_CODES
                  WHERE CASELOAD_TYPE = (SELECT caseload_type FROM caseloads WHERE caseload_id = P_CSLD_ID));
   
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
--   call TRUST_GET_CASELOAD_TYPE(P_CSLD_ID, CSLD_TYPE);
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
               OR (INDDAYS - case when (date_trunc('day',CURRENT_TIMESTAMP) - INDDATE::date)::text='00:00:00' then translate('0 days','d,a,y,s','')::int
    else translate((date_trunc('day',CURRENT_TIMESTAMP) - INDDATE::date)::text,'d,a,y,s','')::int end) > 0
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
            OR (date_trunc('day',CURRENT_TIMESTAMP) - INDDATE < interval '1 day'* INDDAYS AND INDDAYS != 0) 
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