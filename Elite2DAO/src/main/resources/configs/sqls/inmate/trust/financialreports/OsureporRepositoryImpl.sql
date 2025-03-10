
OSUREPOR_FIND_RG1C {
 	SELECT NULL DESCRIPTION FROM DUAL
}

OSUREPOR_FIND_RG2C {
 	SELECT NULL DESCRIPTION , NULL CODE FROM DUAL
}

OSUREPOR_FIND_RG3C {
 	SELECT NULL COL1 , NULL COL2 , NULL COL3 FROM DUAL
}

OSUREPOR_FIND_RG4C {
 	SELECT NULL STAFFID , NULL LAST_NAME , NULL FIRST_NAME , NULL LOCATION FROM DUAL
}

OSUREPOR_FIND_RG5C {
 	SELECT NULL INVOICE , NULL SDATE , NULL EDATE , NULL CORPORATE , NULL DESCRIPTION FROM DUAL
}

OSUREPOR_FIND_RG5C1 {
 	SELECT NULL INVOICE , NULL SDATE , NULL EDATE , NULL CORPORATE , NULL DESCRIPTION FROM DUAL
}

OSUREPOR_FIND_RGPRINTER {
 	SELECT DESCRIPTION , PRINTER_ALIAS_CODE CODE FROM PRINTER_ALIASES WHERE ACTIVE_FLAG = 'Y'   AND EXPIRY_DATE IS  NULL ORDER BY LIST_SEQ
}


OSUREPOR_OMS_MP_WHENNEWRECORDINSTANCE_ {
	SELECT PARAMETER_TYPE,PARAMETER_LOV_SELECT, PARAMETER_DOMAIN FROM OMS_MODULE_PARAMETERS WHERE PARAMETER_NAME = :NBTPARAMETERNAME AND MODULE_NAME =:NBTMODULENAME
}

OSUREPOR_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OSUREPOR_POPULATE_RECORDS {
	SELECT PARAMETER_SEQ ,PARAMETER_NAME ,COMMENT_TEXT ,PARAMETER_TYPE ,PARAMETER_DOMAIN ,OPTIONAL_FLAG, PARAMETER_LOV_SELECT, MODULE_NAME, PARENT_LOV FROM OMS_MODULE_PARAMETERS WHERE MODULE_NAME = :MODULENAME ORDER BY PARAMETER_SEQ
}

OSUREPOR_POPULATE_RECORDStemp {
	SELECT COUNT(MODULE_NAME) INTO V3 FROM OMS_MODULE_PARAMETERS WHERE MODULE_NAME = V1
}

OSUREPOR_POPULATE_RECORDStemp {
	SELECT PROFILE_VALUE INTO P_DESC FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'OFF_ID_CODE'
}

OSUREPOR_POPULATE_RECORDStemp {
	SELECT 'FROM ' || PROFILE_VALUE INTO P_DESC FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'OFF_ID_CODE'
}

OSUREPOR_POPULATE_RECORDStemp {
	SELECT 'TO ' || PROFILE_VALUE INTO P_DESC FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'OFF_ID_CODE'
}

OSUREPOR_SUBMIT_REQUESTSUBMIT_REQUEST {
	SELECT APPLN_CODE FROM OMS_MODULES WHERE MODULE_NAME = :MODULENAME
}

OSUREPOR_SAVE_AS_DEFAULT_PRINTER_ {
	SELECT COUNT(*) FROM STF_MOD_PRINTERS WHERE USER_ID = USER AND REPORT_ID = :MODULENAME AND MODULE_NAME = LV_FORM_NAME
}

OSUREPOR_SAVE_AS_DEFAULT_PRINTERcheck_default_printer_cur {
	DELETE STF_MOD_PRINTERS WHERE USER_ID = USER AND REPORT_ID = :MODULENAME AND MODULE_NAME = LV_FORM_NAME
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.CASELOAD_ID', '''' || :GLOBAL.CASELOAD_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.OFFENDER_ID', '''' || :GLOBAL.OFFENDER_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.OFF_ID', '''' || :GLOBAL.OFF_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.OFFENDER_BOOK_ID', '''' || :GLOBAL.OFFENDER_BOOK_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.OFFENDER_ID_DISPLAY', '''' || :GLOBAL.OFFENDER_ID_DISPLAY || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.ROOT_OFFENDER_ID', '''' || :GLOBAL.ROOT_OFFENDER_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.CG$CORP_CORPORATE_ID', '''' || :GLOBAL.CG$CORP_CORPORATE_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.AGENCY', '''' || :GLOBAL.AGENCY || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.CG$AGY_LOC_AGY_LOC_ID', '''' || :GLOBAL.CG$AGY_LOC_AGY_LOC_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.CASELOAD_TYPE', '''' || :GLOBAL.CASELOAD_TYPE || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.ACCOUNT_CODE', '''' || :GLOBAL.ACCOUNT_CODE || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.CANTEEN_ID', '''' || :GLOBAL.CANTEEN_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.COD', '''' || :GLOBAL.COD || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.SUB_CATEGORY_ID', '''' || :GLOBAL.SUB_CATEGORY_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.AGY_LOC_ID', '''' || :GLOBAL.AGY_LOC_ID || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_PASSING_GLOBAL_VALUESPASSING_GLOBAL_VALUES {
	SELECT REPLACE(UPPER(P_SELECT), ':GLOBAL.AGY_LOC', '''' || :GLOBAL.AGY_LOC || '''') INTO P_SELECT FROM DUAL
}

OSUREPOR_CHK_OTRACCSLCHK_OTRACCSL {
	SELECT 'YES' INTO YES FROM DUAL WHERE EXISTS (SELECT OFFENDER_ID FROM V_TRUST_HEADER_A VA WHERE -- (ROOT_OFFENDER_ID = :OFFENDER_ID OR :OFFENDER_ID IS NULL) --- @@@ JOE WONG, 19-MAR-98. -- @@@ SIMON TAM, DECEMBER 2, 1998 -- COMMENTED OUT: :FROM_OFF_ID AND :TO_OFF_ID NO LONGER USED --   VA.ROOT_OFFENDER_ID BETWEEN NVL(:FROM_OFF_ID, 0) AND NVL(:TO_OFF_ID, 9999999999) CASELOAD_ID = CSLD AND BOOKING_BEGIN_DATE IN (SELECT MAX(BOOKING_BEGIN_DATE) FROM V_TRUST_HEADER_A VA2 WHERE VA.ROOT_OFFENDER_ID = VA2.ROOT_OFFENDER_ID) --- @@@ JOE WONG, 19-MAR-98. AND VA.OFFENDER_ID IN (SELECT OFFENDER_ID FROM OFFENDER_BOOKINGS WHERE (LCNT IS NULL AND CELL_BK IS NULL AND TIER IS NULL) OR LIVING_UNIT_ID IN (SELECT LIVING_UNIT_ID FROM LIVING_UNITS WHERE (DESCRIPTION LIKE DECODE(LCNT, NULL, '%', LCNT) || DECODE(CELL_BK, NULL, '%', '-' || CELL_BK) || DECODE(TIER, NULL, '%', '-' || TIER) || '%'))) AND EXISTS (SELECT OFFENDER_ID FROM OFFENDER_TRANSACTIONS OT WHERE (TRUNC(TXN_ENTRY_DATE) >= SDATE OR SDATE IS NULL) AND (TRUNC(TXN_ENTRY_DATE) <= EDATE OR EDATE IS NULL) AND OT.OFFENDER_ID = VA.ROOT_OFFENDER_ID AND OT.CASELOAD_ID = CSLD AND OT.OFFENDER_ID IS NOT NULL))
}

OSUREPOR_OCRCNOTE_VALIDATIONOCRCNOTE_VALIDATION {
	SELECT DISTINCT ROOT_OFFENDER_ID INTO ROOT_OFF_ID FROM V_TAG_HEADER_BLOCK V_TAG WHERE V_TAG.OFFENDER_ID_DISPLAY = OFF_ID_DISPLAY AND V_TAG.ROOT_OFFENDER_ID IN  (SELECT DISTINCT ROOT_OFFENDER_ID --INTO   ROOT_OFF_ID FROM V_TAG_HEADER_BLOCK V1 WHERE V1.OFFENDER_ID_DISPLAY = OFF_ID_DISPLAY AND V1.OFFENDER_BOOK_ID IN ((SELECT VHB2.OFFENDER_BOOK_ID FROM V_TAG_HEADER_BLOCK VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID AND VHB2.ACTIVE_FLAG = 'Y' AND EXISTS (SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS                 CSLD WHERE CSLD.CASELOAD_TYPE = 'INST' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = VHB2.AGENCY))) OR (V1.OFFENDER_BOOK_ID = (SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM V_TAG_HEADER_BLOCK VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID AND EXISTS (SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS                 CSLD WHERE CSLD.CASELOAD_TYPE = 'INST' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = VHB2.AGENCY) AND NOT EXISTS (SELECT '1' FROM OFFENDER_BOOKINGS         BK, CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS                 CSLD WHERE BK.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID AND BK.ACTIVE_FLAG = 'Y' AND CSLD.CASELOAD_TYPE = 'INST' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = BK.AGY_LOC_ID))) OR (V1.OFFENDER_BOOK_ID = (SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM V_TAG_HEADER_BLOCK VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID AND (VHB2.COMMUNITY_ACTIVE_FLAG = 'Y' OR (NOT EXISTS (SELECT 'X' FROM V_HEADER_BLOCK VHB3 WHERE VHB3.COMMUNITY_ACTIVE_FLAG = 'Y' AND VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID))) AND ((VHB2.INTAKE_AGY_LOC_ID <> 'CLOSE' AND EXISTS (SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID AND LOCS.REMOVED_DATE IS NULL)) OR (VHB2.INTAKE_AGY_LOC_ID = 'CLOSE' AND EXISTS (SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID))))))
}

OSUREPOR_OCRCNOTE_VALIDATIONOCRCNOTE_VALIDATION {
	SELECT DISTINCT ROOT_OFFENDER_ID INTO ROOT_OFF_ID FROM V_TAG_HEADER_BLOCK V_TAG WHERE V_TAG.OFFENDER_ID_DISPLAY = OFF_ID_DISPLAY AND V_TAG.ROOT_OFFENDER_ID IN  (SELECT DISTINCT ROOT_OFFENDER_ID --INTO   ROOT_OFF_ID FROM V_TAG_HEADER_BLOCK V1 WHERE V1.OFFENDER_ID_DISPLAY = OFF_ID_DISPLAY AND V1.OFFENDER_BOOK_ID IN ((SELECT VHB2.OFFENDER_BOOK_ID FROM V_TAG_HEADER_BLOCK VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID AND VHB2.ACTIVE_FLAG = 'Y' AND EXISTS (SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS                 CSLD WHERE CAL.CASELOAD_ID = V_CASELOAD_ID AND CSLD.CASELOAD_TYPE = 'INST' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = VHB2.AGENCY AND V_CASELOAD_TYPE = 'INST'))) OR (V1.OFFENDER_BOOK_ID = (SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM V_TAG_HEADER_BLOCK VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID AND EXISTS (SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS                 CSLD WHERE CAL.CASELOAD_ID = V_CASELOAD_ID AND CSLD.CASELOAD_TYPE = 'INST' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = VHB2.AGENCY AND V_CASELOAD_TYPE = 'INST') AND NOT EXISTS (SELECT '1' FROM OFFENDER_BOOKINGS         BK, CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS                 CSLD WHERE BK.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID AND BK.ACTIVE_FLAG = 'Y' AND CSLD.CASELOAD_TYPE = 'INST' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = BK.AGY_LOC_ID AND V_CASELOAD_TYPE = 'INST'))) OR (V1.OFFENDER_BOOK_ID = (SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM V_TAG_HEADER_BLOCK VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID AND (VHB2.COMMUNITY_ACTIVE_FLAG = 'Y' OR (NOT EXISTS (SELECT 'X' FROM V_HEADER_BLOCK VHB3 WHERE VHB3.COMMUNITY_ACTIVE_FLAG = 'Y' AND VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID))) AND ((VHB2.INTAKE_AGY_LOC_ID <> 'CLOSE' AND EXISTS (SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.CASELOAD_ID = V_CASELOAD_ID AND CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID AND LOCS.REMOVED_DATE IS NULL AND V_CASELOAD_TYPE = 'COMM')) OR (VHB2.INTAKE_AGY_LOC_ID = 'CLOSE' AND EXISTS (SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.CASELOAD_ID = V_CASELOAD_ID AND CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID AND V_CASELOAD_TYPE = 'COMM'))))))
}

OSUREPOR_MODULE_NAME_VALIDATIONMODULE_NAME_VALIDATION {
	SELECT END_DATE INTO P_ACCOUNT_PERIOD FROM ACCOUNT_PERIODS WHERE ACCOUNT_PERIOD_ID IN (SELECT MAX(ACCOUNT_PERIOD_ID) FROM COMM_CASELOAD_ACCOUNT_PERIODS WHERE CASELOAD_ID = P_CASELOAD1 AND ACCOUNT_PERIOD_STATUS = 'C')
}

OSUREPOR_MODULE_NAME_VALIDATIONMODULE_NAME_VALIDATION {
	SELECT END_DATE INTO P_ACCOUNT_PERIOD FROM ACCOUNT_PERIODS WHERE ACCOUNT_PERIOD_ID IN (SELECT MAX(ACCOUNT_PERIOD_ID) FROM CASELOAD_ACCOUNT_PERIODS WHERE CASELOAD_ID = P_CASELOAD1 AND ACCOUNT_PERIOD_STATUS = 'C')
}

OSUREPOR_STAFF_VALIDATION_ {
	SELECT 1 FROM STAFF_LOCATION_ROLES WHERE EXISTS (SELECT 1 FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = P_CASELOAD AND AGY_LOC_ID = STAFF_LOCATION_ROLES.CAL_AGY_LOC_ID) AND SAC_STAFF_ID = P_STAFF
}

OSUREPOR_VALUE_VALIDATION_ {
	SELECT COUNT(*) FROM BANK_RECON_AUDITS WHERE RECON_ID = :NBTVALUE AND STATUS IN ('R','O')
}

OSUREPOR_COMMISARY_VALUE_VALIDATIONCOMMISARY_VALUE_VALIDATION {
	SELECT COUNT(*) INTO LV_COUNTER FROM SALES_ORDERS WHERE CASELOAD_ID = P_CASELOAD AND STOCK_LOC_ID = P_CANTEEN AND (OFFENDER_ID = OFF_ID OR OFF_ID IS NULL) AND SALES_ORDER_ID = SOID
}

OSUREPOR_CHECK_AGY_LOCCHECK_AGY_LOC {
	SELECT ROOT_OFFENDER_ID INTO TMP_ID FROM V_HEADER_BLOCK WHERE ROOT_OFFENDER_ID = OFF_ID AND AGY_LOC_ID IN (SELECT AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = P_CASELOAD_ID)
}

OSUREPOR_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OSUREPOR_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

OSUREPOR_RUN_REPORT_ {
	SELECT LTRIM(RTRIM(DESCRIPTION)) FROM SYSTEM_PROFILES WHERE PROFILE_CODE = P_PROFILE_CODE AND PROFILE_TYPE = P_PROFILE_TYPE
}

OSUREPOR_RUN_REPORTrole_cur {
	SELECT PRINT_FORMAT_CODE INTO P_PRINT_FORMAT FROM OMS_MODULES WHERE MODULE_NAME = REP_VAR.LV_RNAME
}

OSUREPOR_RUN_REPORTrole_cur {
	DELETE OFFENDER_BILLING_DETAILS_TMP WHERE CASELOAD_ID = CSLD_ID
}

OSUREPOR_RUN_REPORTrole_cur {
	SELECT REPORT_BATCH_ID.NEXTVAL INTO :GLOBAL.REPORT_BATCH_ID FROM DUAL
}

OSUREPOR_POPULATE_REPORT_NAME_ {
	SELECT MODULE_NAME, DESCRIPTION, OUTPUT_TYPE FROM OMS_MODULES WHERE ((UPPER(DESCRIPTION) LIKE LV_REPORT_DESC) OR (UPPER(MODULE_NAME) LIKE LV_REPORT_DESC)) AND MODULE_TYPE = 'REPORT' AND EXISTS (SELECT 1 FROM MODULE_PRIVILEGES P WHERE P.MODULE_NAME = OMS_MODULES.MODULE_NAME AND EXISTS (SELECT 2 FROM STAFF_MEMBER_ROLES R WHERE R.ROLE_ID = P.ROLE_ID AND EXISTS (SELECT 3 FROM STAFF_MEMBERS S WHERE S.STAFF_ID = R.STAFF_ID AND S.USER_ID = USER)))
}

OSUREPOR_PRE_INV_INSERT_ {
	IS SELECT DISTINCT OFFENDER_BOOKING_ID, EFFECTIVE_DATE_START,EFFECTIVE_DATE_END FROM OFFENDER_BILLING_PROFILES WHERE AGENCY_ID = P_AGENCY_ID AND CASELOAD_ID = P_CASELOAD_ID AND BILLING_TYPE = P_BILLING_TYPE AND TRUNC (EFFECTIVE_DATE_START) <= TRUNC (TO_DATE(P_BIL_TO_DATE,NVL(OMS_MISCELLANEOUS.GET_PROFILE_VALUE('DISPLAY','DATE'),'MM/DD/YYYY'))) AND (   EFFECTIVE_DATE_END IS NULL OR TRUNC (EFFECTIVE_DATE_END) >= TRUNC (TO_DATE(P_BIL_FROM_DATE,NVL(OMS_MISCELLANEOUS.GET_PROFILE_VALUE('DISPLAY','DATE'),'MM/DD/YYYY'))) ) AND NVL(EFFECTIVE_DATE_END, SYSDATE) >= EFFECTIVE_DATE_START ORDER BY OFFENDER_BOOKING_ID,EFFECTIVE_DATE_START
}

OSUREPOR_SET_LOVSET_LOV {
	SELECT PARAMETER_LOV_SELECT, PARAMETER_LOV_TITLE, PARAMETER_TYPE INTO LC$SELECT, LC$TITLE, LC$TYPE FROM OMS_MODULE_PARAMETERS WHERE MODULE_NAME = :OMS_REQUEST.MODULE_NAME AND PARAMETER_NAME = LC$COL
}

OSUREPOR_REP_VAR_ {
	SELECT DISTINCT DPL.PARAMETER_NAME FROM V_DEPENDENT_PARAMETER_LIST DPL CONNECT BY DPL.PARENT_PARAMETER_NAME =  PRIOR DPL.PARAMETER_NAME START WITH DPL.MODULE_NAME         = L_MODULE_NAME AND DPL.PARENT_PARAMETER_NAME    = P_PARAM_NAME
}
OSUREPOR_PROFILE_VALUE {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'OFF_ID_CODE'
}
OSUREPOR_PROFILE_VALUE_ONE {
	SELECT 'FROM ' || PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'OFF_ID_CODE'
}
OSUREPOR_PROFILE_VALUE_TWO {
	SELECT 'TO ' || PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'OFF_ID_CODE'
}
OSUREPOR_MATSERDATA {
select substr(cast(account_code as varchar(100)),1,1) accd1,
account_code, upper(account_name) account_name
from account_codes
where substr(cast(account_code as varchar(100)),2,3) = '000'
and posting_status_flag ='N'
and caseload_type = (select caseload_type from caseloads
		   where caseload_id = :caseloadId)
order by account_code
}
OSUREPOR_CHILDDATA {
select 	 substr(to_char(account_code),1,1) accd11,  
	substr(to_char(account_code),1,2) accd2,  	
	account_code acc_code,
	upper(account_name) account_name,
	parent_account_code
from 	account_codes
where 	posting_status_flag ='N'
and caseload_type = (select caseload_type from caseloads
		   where caseload_id = :caseloadId)  AND PARENT_ACCOUNT_CODE = :parentAccountCode
order by 	account_code
}
OSUREPOR_SUBCHILDDATA {
select 	  
	account_code,upper(account_name) account_name, parent_account_code
from 	account_codes
where      parent_account_code = :accountCode

and 
caseload_type = (select caseload_type from caseloads
		   where caseload_id = :caseloadId)
order by 	account_code

}
OSUREPOR_GETTEMPCLOSEBALANCE {
SELECT      close_balance
     FROM 	caseload_account_summaries
     WHERE 	account_period_id = (	SELECT MAX(account_period_id)
	     		        	FROM   caseload_account_periods
		 	        	WHERE  caseload_id = :caseloadId
			        	AND    account_period_status ='C'
				    ) 
     AND 	caseload_id       = :caseloadId
     AND    	account_code      = :accountCode
}
OSUREPOR_GETTEMPTXNAMOUNT {
SELECT       SUM(coalesce (CSLD_AS.PERIOD_TXN_AMOUNT,0)) 
     FROM    CASELOAD_ACCOUNT_SUMMARIES CSLD_AS
     WHERE   CSLD_AS.CASELOAD_ID = :caseloadId
     AND     CSLD_AS.ACCOUNT_PERIOD_ID  > (SELECT coalesce(MAX(account_period_id),0)
					   FROM   caseload_account_periods
					   WHERE  caseload_id = :caseloadId
					   AND    account_period_status ='C')  
     AND     CSLD_AS.account_period_id 	< :accountPeriod
     AND     account_code      		= :accountCode
}
OSUREPOR_GETTEMPTXNMONAMOUNT {
 SELECT       SUM(CASE a.TXN_POST_USAGE WHEN 'DR' THEN CASE b.TXN_POSTING_TYPE
                                                    WHEN 'DR' THEN COALESCE(a.TXN_ENTRY_AMOUNT,0)
                                                    WHEN 'CR' THEN COALESCE(a.TXN_ENTRY_AMOUNT,0) * -1 END
                                       WHEN 'CR' THEN CASE b.TXN_POSTING_TYPE
                                                    WHEN 'CR' THEN COALESCE(a.TXN_ENTRY_AMOUNT,0)
                                                    WHEN 'DR' THEN COALESCE(a.TXN_ENTRY_AMOUNT,0) * -1 END END) TEMP_TXN_MON_AMOUNT
         FROM gl_transactions a,
              account_codes   b  
           WHERE a.account_code      = b.account_code
             AND a.account_period_id = :accountPeriod
             AND a.caseload_id       = :caseloadId
             AND a.account_code      = :accountCode
             AND a.txn_entry_date    <= :dbDate
}
 

OSUREPOR_GETOPENBALANCE {
 SELECT open_balance
     FROM   caseload_account_summaries
     WHERE  account_period_id = :accountPeriod
     AND    caseload_id       = :caseloadId 
     AND    account_code      = :accountCode  
}
OSUREPOR_GETCTEMPTXNAMOUNT {
SELECT SUM(DECODE(a.TXN_POST_USAGE,'DR',DECODE(b.TXN_POSTING_TYPE,
                                                   'DR',NVL(a.TXN_ENTRY_AMOUNT,0),
                                                   'CR',NVL(a.TXN_ENTRY_AMOUNT,0) * -1),
                                        'CR',DECODE(b.TXN_POSTING_TYPE,
                                                   'CR',NVL(a.TXN_ENTRY_AMOUNT,0),
                                                   'DR',NVL(a.TXN_ENTRY_AMOUNT,0) * -1))) TEMP_TXN_AMOUNT
         FROM gl_transactions  a,
              account_codes    b
           WHERE a.account_code    = b.account_code
             AND a.account_period_id = :accountPeriod
             AND a.caseload_id       = :caseloadId 
             AND a.account_code      = :accountCode 
             AND a.txn_entry_date    <= :dbDate
}
OSUREPOR_GETACCOUNTPERIOD {
select account_period_id 
    from   account_periods
    where to_date(:USERDATE::TEXT,'YYYY/MM/DD')  between to_date(start_date::TEXT,'YYYY/MM/DD') and to_date(end_date::TEXT,'YYYY/MM/DD')
}
OSUREPOR_GETBANKRECONCILIATIONREPORT {
SELECT  BRA.BANK_BALANCE DISPLAY_AMNT_BNK,
	COALESCE(CAST(BRA.AMNT_PLUS_ADJ AS FLOAT),0) AMNT_P_ADJ,
	COALESCE(CAST(BRA.AMNT_LESS_ADJ AS FLOAT),0) AMNT_L_ADJ,
	(CAST(BRA.BANK_BALANCE AS FLOAT) + COALESCE(CAST(BRA.AMNT_PLUS_ADJ AS FLOAT),0) - 
	COALESCE(CAST(BRA.AMNT_LESS_ADJ AS FLOAT),0)) AMNT_ADJ_BLNC,
	BRA.ADJUSTMENT_COMMENT,
	SUBSTR(AC.ACCOUNT_NAME,1,46) DESC_ACNT_C,
                AC.ACCOUNT_CODE
FROM	BANK_RECON_AUDITS BRA,
	ACCOUNT_CODES AC
WHERE date_TRUNC('day',BRA.BANK_STATEMENT_DATE) = TO_DATE(:userDate::text, 'DD/MM/YYYY')
	AND   BRA.ACCOUNT_CODE      = :accountCode
	AND   BRA.CASELOAD_ID        =:caseloadId
	AND   AC.ACCOUNT_CODE     = :accountCode
}

OSUREPOR_GETQUERY3LIST {
select
	GLT.txn_id CR_TX,
	case
		when coalesce(R.cheque_number::text, '') = '' then GLT.txn_entry_date
		else case
			when r.cheque_status = 'VOID' then (
			select
				g1.txn_entry_date
			from
				gl_transactions g1
			where
				reversed_txn_id = glt.txn_id)
			else R.transaction_date
		end
	end CR_DATE,
	'CHK' CR_TYPE,
	coalesce(R.cheque_number, GLT.txn_id) CR_RFRNC,
	case
		when coalesce(R.cheque_number::text, '') = '' then GLT.txn_entry_desc
		else D.payee_name_text
	end CR_PYE,
	coalesce(GLT.txn_entry_amount, 0) CR_AMT_TEMP
from
	gl_transactions glt
left outer join bank_cheque_registers r on
	(GLT.txn_id = R.txn_id
		and GLT.caseload_id = R.caseload_id)
left outer join bank_cheque_data d on
	(GLT.txn_id = D.txn_id)
where
	GLT.caseload_id like UPPER(:caseloadId)
	and GLT.account_code = :accountCode
	and glt.txn_reversed_flag = 'N'
	and GLT.txn_post_usage = 'CR'
	and (GLT.recon_clear_flag != 'Y'
		or GLT.bank_statement_date > TO_DATE(:userDate::text, 'DD/MM/YYYY'))
order by
	CR_DATE,
	GLT.txn_id;
}

OSUREPOR_GETQUERY4LIST {
select SUM(txn_entry_amount) CR_AMNT
from GL_TRANSACTIONS GL
WHERE  GL.txn_post_usage = 'CR'
AND    GL.account_code        = :accountCode
AND    GL.txn_id         = :crTxn
}

OSUREPOR_GETQUERY5LIST {
SELECT GLT.txn_entry_date DR_DATE,
	CASE  WHEN GLT.txn_reference_number IS NULL THEN 'TRN' ELSE 'REF' END DR_TYPE,                
	CASE  WHEN GLT.txn_reference_number IS NULL THEN TO_CHAR(GLT.txn_id) ELSE GLT.txn_reference_number END DR_RFRNC,
	GLT.txn_entry_desc DR_DESC,
	SUM(COALESCE(GLT.txn_entry_amount, 0))  DR_AMNT
FROM   GL_TRANSACTIONS GLT
	WHERE  account_code = :accountCode
	AND    GLT.caseload_id LIKE UPPER(:caseloadId)
	AND    txn_post_usage = 'DR'
                AND GLT.txn_reversed_flag = 'N'
               AND (GLT.recon_clear_flag != 'Y' OR GLT.bank_statement_date > TO_DATE(:userDate::text,'DD/MM/YYYY'))
GROUP BY
	GLT.txn_entry_date,
	CASE  WHEN GLT.txn_reference_number IS NULL THEN 'TRN' ELSE 'REF' END,
	CASE  WHEN GLT.txn_reference_number IS NULL THEN TO_CHAR(GLT.txn_id) ELSE GLT.txn_reference_number END,
	GLT.txn_id,	
	GLT.txn_entry_desc
ORDER BY 
	GLT.txn_entry_date,GLT.txn_id
	}
OSUREPOR_GETTOTALCRAMNT {
select
	SUM(CR_AMT_TEMP) CR_AMT_TEMP
from
	(
	select
		GLT.txn_id CR_TX,
		case
			when coalesce(R.cheque_number::text, '') = '' then GLT.txn_entry_date
			else case
				when r.cheque_status = 'VOID' then (
				select
					g1.txn_entry_date
				from
					gl_transactions g1
				where
					reversed_txn_id = glt.txn_id)
				else R.transaction_date
			end
		end CR_DATE,
		'CHK' CR_TYPE,
		coalesce(R.cheque_number, GLT.txn_id) CR_RFRNC,
		case
			when coalesce(R.cheque_number::text, '') = '' then GLT.txn_entry_desc
			else D.payee_name_text
		end CR_PYE,
		coalesce(GLT.txn_entry_amount, 0) CR_AMT_TEMP
	from
		gl_transactions glt
	left outer join bank_cheque_registers r on
		(GLT.txn_id = R.txn_id
			and GLT.caseload_id = R.caseload_id)
	left outer join bank_cheque_data d on
		(GLT.txn_id = D.txn_id)
	where
		GLT.caseload_id like UPPER(:caseloadId)
		and GLT.account_code = :accountCode
		and glt.txn_reversed_flag = 'N'
		and GLT.txn_post_usage = 'CR'
		and (GLT.recon_clear_flag != 'Y'
			or GLT.bank_statement_date > TO_DATE(:userDate::text, 'DD/MM/YYYY'))
	order by
		CR_DATE,
		GLT.txn_id )A
}
OSUREPOR_GETTOTALDRAMNT {
select sum(DR_AMNT) DR_AMNT from (SELECT GLT.txn_entry_date DR_DATE,
	CASE  WHEN GLT.txn_reference_number IS NULL THEN 'TRN' ELSE 'REF' END DR_TYPE,                
	CASE  WHEN GLT.txn_reference_number IS NULL THEN TO_CHAR(GLT.txn_id) ELSE GLT.txn_reference_number END DR_RFRNC,
	GLT.txn_entry_desc DR_DESC,
	SUM(COALESCE(GLT.txn_entry_amount, 0))  DR_AMNT
FROM   GL_TRANSACTIONS GLT
	WHERE  account_code = :accountCode
	AND    GLT.caseload_id LIKE UPPER(:caseloadId)
	AND    txn_post_usage = 'DR'
                AND GLT.txn_reversed_flag = 'N'
               AND (GLT.recon_clear_flag != 'Y' OR GLT.bank_statement_date > TO_DATE(:userDate::text,'DD/MM/YYYY'))
GROUP BY
	GLT.txn_entry_date,
	CASE  WHEN GLT.txn_reference_number IS NULL THEN 'TRN' ELSE 'REF' END,
	CASE  WHEN GLT.txn_reference_number IS NULL THEN TO_CHAR(GLT.txn_id) ELSE GLT.txn_reference_number END,
	GLT.txn_id,	
	GLT.txn_entry_desc
ORDER BY 
	GLT.txn_entry_date,GLT.txn_id) as amount
}

OSUREPOR_GETQUERYFOURMAINLIST {
SELECT glt.TXN_ENTRY_DATE,glt.TXN_ID,glt.TXN_ENTRY_DESC,coalesce (glt.TXN_ENTRY_AMOUNT,0) amount
FROM GL_TRANSACTIONS glt,offender_deductions od, deduction_types dt 
WHERE   od.OFFENDER_ID=glt.OFFENDER_ID
AND   od.DEDUCTION_TYPE=dt.DEDUCTION_TYPE
AND   glt.TXN_TYPE=dt.DEDUCTION_TYPE
AND   dt.DEDUCTION_CATEGORY='CROB'
AND   dt.ACTIVE_FLAG='Y'
AND glt.TXN_POST_USAGE='DR' 
AND glt.TXN_REVERSED_FLAG='N' 
AND glt.TXN_ENTRY_DESC NOT LIKE '%SAV%'
AND (GLT.recon_clear_flag != 'Y' OR GLT.bank_statement_date >  TO_DATE(:userDate::text,'DD/MM/YYYY'))
order by TXN_ID

}
OSUREPOR_QUERYFOURSUBTOTALAMNT {
select (sum(glt.TXN_ENTRY_AMOUNT) - sum(od.adjustment_amount)) TOTAL_CREDIT 
FROM GL_TRANSACTIONS glt,offender_deductions od, deduction_types dt 
WHERE   od.OFFENDER_ID=glt.OFFENDER_ID
AND   od.DEDUCTION_TYPE=dt.DEDUCTION_TYPE
AND   glt.TXN_TYPE=dt.DEDUCTION_TYPE
AND   dt.DEDUCTION_CATEGORY='CROB'
AND   dt.ACTIVE_FLAG='Y'
AND glt.TXN_POST_USAGE='DR' 
AND glt.TXN_REVERSED_FLAG='N' 
AND glt.TXN_ENTRY_DESC NOT LIKE '%SAV%'
AND (GLT.recon_clear_flag != 'Y' OR GLT.bank_statement_date >  current_timestamp)
}
OSUREPOR_GETFSYSBAL {
SELECT SUM(case txn_post_usage  when 'DR' then  1  else -1 end * txn_entry_amount) amt
  FROM gl_transactions gts
 WHERE caseload_id = :caseloadId
   AND account_code = :accountCode 
   and TXN_ENTRY_date <=  TO_DATE(:userDate::text,'DD/MM/YYYY')
      and not exists (select 1 from gl_transactions gt, bank_cheque_registers bcr
            where gt.reversed_txn_id = BCR.Txn_id 
              and gts.txn_id = gt.reversed_txn_id
              and gt.txn_entry_date > TO_DATE(:userDate::text,'DD/MM/YYYY'))
}
OSUREPOR_GETFSYSBALN {
SELECT SUM(case txn_post_usage  when 'DR' then  1  else -1 end * txn_entry_amount) amt
  FROM gl_transactions
 WHERE caseload_id = :caseloadId
   AND account_code = :accountCode
}
OSUREPOR_GETPROFILEVAL {
SELECT profile_value 
           FROM system_profiles 
          WHERE profile_code = 'FUTURE_TXN'
            AND profile_type = 'CLIENT'
}
OSUREPOR_QUERYFOURADJUSTMENTAMOUNT {
select sum(od.adjustment_amount) TOTAL_ADJUSTMENT 
FROM GL_TRANSACTIONS glt,offender_deductions od, deduction_types dt 
WHERE   od.OFFENDER_ID=glt.OFFENDER_ID
AND   od.DEDUCTION_TYPE=dt.DEDUCTION_TYPE
AND   glt.TXN_TYPE=dt.DEDUCTION_TYPE
AND   dt.DEDUCTION_CATEGORY='CROB'
AND   dt.ACTIVE_FLAG='Y'
AND glt.TXN_POST_USAGE='DR' 
AND glt.TXN_REVERSED_FLAG='N' 
AND glt.TXN_ENTRY_DESC NOT LIKE '%SAV%'
AND (GLT.recon_clear_flag != 'Y' OR GLT.bank_statement_date >  TO_DATE(:userDate::text,'DD/MM/YYYY'))

}

OSUREPOR_GET_USER_NAME {
	SELECT USER FROM  DUAL
}

OSUREPOR_CF_CASELOAD_DESC {
  SELECT description FROM   caseloads WHERE  caseload_id = :CASELOAD
}

OSUREPOR_GET_ADDRESS_ONE_TWO_DETAILS {
select * from v_agency_addresses where agy_loc_ID=:agyLocId and ADDRESS_TYPE=:addrssType
}

OSUREPOR_GET_CLIENT_NAME_FOR_RECEIPT_REPORT {
select LAST_NAME||' ,'||FIRST_NAME from v_header_block where offender_book_id=:offenderBookId 
}

OSUREPOR_GET_LONGEST_SUPV_EXPIRE_DATE{
SELECT LONGEST_SUPV_EXP_DATE FROM OFF_SUPERVISION_DETAILS  WHERE OFFENDER_BOOK_ID = :offenderBookId
}
OSUREPOR_GETTING_ACCOUNT_BALANCE_OFF_BILLING_STATEMENTS {
select ROOT_OFFENDER_ID,offender_book_id,BILLING_STATEMENT_ID,BILLING_CYCLE_START_DATE,BILLING_CYCLE_END_DATE, begining_balance_amount, payments_credits_amount, billings_amount,  ending_balance_amount 
 from off_billing_statements OBS where 
  (
to_date(:START_DATETIME::text,'YY/MM/DD') <= to_date(billing_cycle_start_date::text,'YY/MM/DD') 
and to_date(:END_DATETIME::text,'YY/MM/DD') >= to_date(billing_cycle_end_date::text,'YY/MM/DD') 
)
and caseload_id  in (select CASELOAD_ID from caseloads where 
caseload_type = 'COMM' and trust_accounts_flag = 'Y' AND
 CASELOAD_ID IN (SELECT CASELOAD_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE agy_loc_id IN(select CAL_AGY_LOC_ID from STAFF_LOCATION_ROLES where role = 'POCLD' AND position = 'POCLD'
)))
}
OSUREPOR_GET_BILLING_STATEMENTS_WITH_CASELOAD {

SELECT SUM(payments_credits_amount) payments_credits_amount, SUM(billings_amount) billings_amount, EBA ending_balance_amount,bba begining_balance_amount,BCE BILLING_CYCLE_END_DATE
FROM(
 select BILLING_STATEMENT_ID,BILLING_CYCLE_START_DATE,BILLING_CYCLE_END_DATE, begining_balance_amount, payments_credits_amount, billings_amount,  ending_balance_amount ,
  (SELECT min(begining_balance_amount) FROM off_billing_statements WHERE ROOT_OFFENDER_ID = OBS.ROOT_OFFENDER_ID and CASELOAD_ID =:CASELODID) BBA,
   (SELECT MAX(BILLING_CYCLE_END_DATE) FROM off_billing_statements WHERE ROOT_OFFENDER_ID = OBS.ROOT_OFFENDER_ID and CASELOAD_ID =:CASELODID) BCE,
   (SELECT max(ending_balance_amount) FROM off_billing_statements WHERE ROOT_OFFENDER_ID = OBS.ROOT_OFFENDER_ID and CASELOAD_ID =:CASELODID) EBA
 from off_billing_statements OBS where root_offender_id = :OFFENDERID 
AND (
to_date(:START_DATETIME,'DD/MM/YY') <= to_date(billing_cycle_start_date,'DD/MM/YY') 
and to_date(:END_DATETIME,'DD/MM/YY') >= to_date(billing_cycle_end_date,'DD/MM/YY') and CASELOAD_ID =:CASELODID
)
order by BILLING_CYCLE_END_DATE asc
) group by BBA,BCE,EBA
}
OSUREPOR_GETTING_PAYMENT_AND_CREDITS_FOR_ACCOUNTS {
SELECT  OFP.FEE_CODE feeCode,
 OFBT.BILL_TXN_AMOUNT paymentAmount,
 case  when off_adj_txn_id is not null AND original_off_adj_txn_id is  null then 'CREDITS'
       when off_adj_txn_id is not null AND original_off_adj_txn_id is not null then 'CREDITS REVERSAL'
          WHEN ORIGINAL_BILL_ID IS NOT NULL  THEN 'PAYMENT REVERSAL' 
          WHEN  trust_txn_id IS NOT NULL THEN 'PAYMENT' 
     ELSE BILL_TXN_TYPE END caseloadDescription
FROM OFF_FEE_BILLS OFB, OFF_FEE_ACCOUNT_PROFILE OFP, OFF_FEE_BILL_TRANSACTIONS OFBT
WHERE OFBT.BILL_ID=OFB.BILL_ID AND OFP.OFFENDER_FEE_ID=OFB.OFFENDER_FEE_ID AND 
  ofp.offender_book_id = :OFFENDERBOOKID AND OFBT.BILLING_STATEMENT_ID =:BILLING_STATEMENT_ID AND
  OFBT.BILL_TXN_TYPE NOT IN ('CBILL','BILLOD','BILLOI')
}

OSUREPOR_GETTING_PAYMENT_AND_CREDITS_SUM_AMOUNT {
 select coalesce(sum(abs(billAmount)),0)-coalesce(sum(abs(creditAmount)),0) BILL_TXN_AMOUNT from(
  select CASELOAD_ID,BILL_ID,trust_txn_id, ORIGINAL_BILL_ID,BILL_TXN_AMOUNT,ORIGINAL_OFF_ADJ_TXN_ID,off_adj_txn_id,
    CASE WHEN (ORIGINAL_BILL_ID IS NOT NULL or ORIGINAL_OFF_ADJ_TXN_ID is not null ) THEN BILL_TXN_AMOUNT ELSE null END creditAmount,
    CASE WHEN (ORIGINAL_BILL_ID IS NULL )  THEN BILL_TXN_AMOUNT ELSE null END billAmount
  from (
  SELECT  OFP.FEE_CODE CASELOAD_ID, OFBT.BILL_TXN_TYPE BILL_ID,OFBT.ORIGINAL_BILL_ID,ofbt.ORIGINAL_OFF_ADJ_TXN_ID,ofbt.off_adj_txn_id,ofbt.trust_txn_id,
  OFBT.BILL_TXN_AMOUNT ,
 ABS(OFBT.BILL_TXN_AMOUNT -(SELECT BILL_TXN_AMOUNT from OFF_FEE_BILL_TRANSACTIONS where BILL_TXN_NO=OFBT.BILL_TXN_NO-1 and BILL_ID = OFBT.BILL_ID)) AMOUNT
FROM OFF_FEE_BILLS OFB, OFF_FEE_ACCOUNT_PROFILE OFP, OFF_FEE_BILL_TRANSACTIONS OFBT
WHERE OFBT.BILL_ID=OFB.BILL_ID AND OFP.OFFENDER_FEE_ID=OFB.OFFENDER_FEE_ID AND  OFBT.BILLING_STATEMENT_ID =:BILLING_STATEMENT_ID AND
  ofp.offender_book_id = :OFFENDERBOOKID AND OFBT.BILL_TXN_TYPE NOT IN ('CBILL','BILLOD','BILLOI') ORDER BY OFBT.BILL_ID,ofbt.BILL_TXN_NO)  
  )
  }
  OSUREPOR_GETTING_BILLING_FOR_ACCOUNT_SECTION {
select FEE_CODE feeCode,BA+BI-BD paymentAmount
FROM(
SELECT DISTINCT(OFP.OFFENDER_FEE_ID),OFP.FEE_CODE
,(SELECT coalesce(SUM(OFB.BILL_GENERATE_AMOUNT),0) FROM  OFF_FEE_BILLS OFB WHERE OFFENDER_FEE_ID=OFP.OFFENDER_FEE_ID and BILLING_STATEMENT_ID = :BILLING_STATEMENT_ID) AS BA
,(SELECT coalesce(SUM(BILL_TXN_AMOUNT),0) FROM off_fee_bill_transactions OBT WHERE  BILLING_STATEMENT_ID = :BILLING_STATEMENT_ID and BILL_ID in (select bill_id from off_fee_bills where offender_fee_id in(OFP.OFFENDER_FEE_ID)) AND
EXISTS(SELECT 1 FROM TRANSACTION_TYPES WHERE TXN_TYPE=OBT.BILL_TXN_TYPE AND TXN_USAGE=(SELECT CODE FROM REFERENCE_CODES WHERE DOMAIN='AC_TXN_USG' AND CODE='BI'))) AS BI
,(SELECT coalesce(SUM(BILL_TXN_AMOUNT),0) FROM off_fee_bill_transactions OBT WHERE  BILLING_STATEMENT_ID = :BILLING_STATEMENT_ID and BILL_ID in (select bill_id from off_fee_bills where offender_fee_id in(OFP.OFFENDER_FEE_ID)) AND
EXISTS(SELECT 1 FROM TRANSACTION_TYPES WHERE TXN_TYPE=OBT.BILL_TXN_TYPE AND TXN_USAGE=(SELECT CODE FROM REFERENCE_CODES WHERE DOMAIN='AC_TXN_USG' AND CODE='BD'))) AS BD
FROM OFF_FEE_ACCOUNT_PROFILE OFP WHERE OFP.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
 
  }

OSUREPOR_GETTING_SUMMARY_OF_FEE_ACCOUNT {
SELECT   OFP.FEE_CODE CASELOAD_ID, (SELECT FREQUENCY_TYPE|| ' ' ||FREQUENCY_CODE  FROM CASELOAD_DEDUCTION_PROFILES WHERE CASELOAD_ID=OFP.CASELOAD_ID 
  AND DEDUCTION_TYPE=OFP.FEE_CODE) AS CREATE_USER_ID, OFP.AMOUNT BILLINGS_AMOUNT,
  OFP.OFFENDER_FEE_ID ROOT_OFFENDER_ID, OFBT.BILL_ID, OFBT.BILL_TXN_TYPE MODIFY_USER_ID
  FROM OFF_FEE_BILLS OFB, OFF_FEE_ACCOUNT_PROFILE OFP, OFF_FEE_BILL_TRANSACTIONS OFBT
  WHERE OFBT.BILL_ID=OFB.BILL_ID AND OFP.OFFENDER_FEE_ID=OFB.OFFENDER_FEE_ID AND 
  ofbt.bill_txn_no IN (SELECT MAX(bill_txn_no) FROM OFF_FEE_BILL_TRANSACTIONS WHERE BILL_ID=OFB
  .BILL_ID ) AND
  ofb.bill_id in (select max(bill_id) from off_fee_bills where offender_fee_id = OFP.OFFENDER_FEE_ID ) AND ofp.offender_book_id = :OFFENDERBOOKID
}

OSUREPOR_GET_CLIENT_NAME_FOR_RECEIPT_REPORT {
select LAST_NAME||' ,'||FIRST_NAME from v_header_block where offender_book_id=:offenderBookId 
}
OSUREPOR_GET_F_DATE_ONE {
select booking_begin_date from v_header_block where offender_book_id=:offenderBookId
}
OSUREPOR_GET_MSG_VALUE {
select msg_value from system_labels where msg_key = LOWER(:MODULENAME||'.global')
}
OSUREPOR_GET_COUNTY_MESSAGE {
select msg_value from system_labels where msg_key = LOWER(:MESSAGEKEY)
}
OSUREPOR_GET_FROM_BILLINGCYCLE_LOVDATA {
  SELECT DISTINCT
  description,code
FROM
  (select distinct
     BILLING_CYCLE_START_DATE,
 to_char((to_date(extract(YEAR from BILLING_CYCLE_START_DATE)||'-'||extract(MONTH from BILLING_CYCLE_START_DATE),'YYYY/MM'))::TIMESTAMP,'YYYY/MM/DD') code,
    extract(YEAR from BILLING_CYCLE_START_DATE)|| trim(TO_CHAR((TO_DATE(extract(MONTH from BILLING_CYCLE_START_DATE)::text, 'MM'))::TIMESTAMP, 'MONTH')) description
   from off_billing_statements 
where (extract(MONTH from BILLING_CYCLE_START_DATE) <extract(MONTH from current_date) 
and extract(year from BILLING_CYCLE_START_DATE)= extract(year from current_date)) 
or extract(year from BILLING_CYCLE_START_DATE)< extract(year from current_date)
   order by BILLING_CYCLE_START_DATE desc)billStrDate 
   order by code desc
}
OSUREPOR_GET_TO_BILLINGCYCLE_LOVDATA {
  SELECT DISTINCT
  description, code
FROM
  (SELECT DISTINCT
     BILLING_CYCLE_END_DATE,
    to_char(TO_DATE((EXTRACT(YEAR FROM BILLING_CYCLE_END_DATE)||'-'||EXTRACT(MONTH FROM BILLING_CYCLE_END_DATE)||'-'||extract(DAY from LAST_DAY(BILLING_CYCLE_END_DATE)))::text,'YYYY/MM/DD')::TIMESTAMP,'YYYY/MM/DD') code,
     trim(TO_CHAR((TO_DATE((EXTRACT(MONTH FROM BILLING_CYCLE_END_DATE))::text, 'MM'))::TIMESTAMP, 'MONTH'))||EXTRACT(YEAR FROM BILLING_CYCLE_END_DATE) description
   FROM OFF_BILLING_STATEMENTS
   where (extract(MONTH from BILLING_CYCLE_END_DATE) <=extract(MONTH from current_date) and extract(year from BILLING_CYCLE_END_DATE)= extract(year from current_date)) or extract(year from BILLING_CYCLE_END_DATE)< extract(year from current_date)
   ORDER BY BILLING_CYCLE_END_DATE DESC)billEndDate
ORDER by code desc
}

OSUREPOR_GET_BAD_ADDRESS_DATA {
SELECT * FROM V_ADDRESS_USAGES  WHERE OWNER_ID = :OWNER_ID AND ADDRESS_USAGE ='BAD'  ORDER BY ADDRESS_ID desc limit 1
}

OSUREPOR_GET_PO_OFFICER_NAME {
SELECT cp.*, (select STAFF_NAME from STAFF_MEMBERS_V2 where SAC_STAFF_ID = cp.SAC_STAFF_ID and CAL_AGY_LOC_ID = cp.CAL_AGY_LOC_ID and FROM_DATE = cp.FROM_DATE and POSITION = cp.POSITION and ROLE = cp.role) officer
    FROM CASE_PLANS  cp WHERE   OFFENDER_BOOK_ID = :offenderBookId  order by CASE_PLAN_ID DESC
}

OSUREPOR_GET_PO_CASELODS {
select LAST_NAME CODE, LAST_NAME DESCRIPTION from staff_members where
staff_id in (select sac_staff_id from staff_location_roles where position = 'POCLD' AND ROLE = 'POCLD'  AND
CAL_AGY_LOC_ID IN(SELECT AGY_LOC_ID FROM caseload_agency_locations WHERE CASELOAD_ID = :CASELOAD_ID))
}

OSUREPOR_GET_FOR_PERIOD_ENDINGDATE {
select to_date(ENDDATE::text,'YY/MM/DD') ENDDATEVAL FROM ( select case when PF::integer > extract(DAY from LAST_DAY(enddate::date)) then to_date((extract(YEAR from enddate::date)||'-'||extract(MONTH from enddate::date)||'-'||extract(DAY from LAST_DAY(enddate::date)))::text,'YYYY/MM/DD') ELSE to_date((extract(YEAR from enddate::date) ||'-'||extract(MONTH from enddate::date)||'-'||PF)::text,'YYYY/MM/DD') END ENDDATE from (SELECT PROFILE_VALUE pf,to_date(:enddate::text,'YYYY/MM/DD') enddate from system_profiles where profile_code='BILL_END_DAY'and profile_type='CLIENT')pfEndDate)endDate
}