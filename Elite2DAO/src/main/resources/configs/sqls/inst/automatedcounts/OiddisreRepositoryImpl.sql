
OIDDISRE_FIND_CGFKDISCREPRSN {
 	SELECT CODE ,DESCRIPTION  FROM REFERENCE_CODES  WHERE DOMAIN = 'DISCREP_RSN'  AND ACTIVE_FLAG = 'Y'  ORDER BY LIST_SEQ
}

OIDDISRE_AGENCYCOUNTS_FIND_AGENCY_COUNTS {
 	SELECT   FROM AGENCY_COUNTS  /* where  */
}
OIDDISRE_AGENCYCOUNTS_INSERT_AGENCY_COUNTS {
	INSERT INTO AGENCY_COUNTS() VALUES(:)
}

OIDDISRE_AGENCYCOUNTS_UPDATE_AGENCY_COUNTS {
	 UPDATE AGENCY_COUNTS
         SET TOTAL_ACTUAL = :totalActual,
             TOTAL_MALE = :totalMale,
             TOTAL_FEMALE = :totalFemale,
             TOTAL_OTHER = :totalOther,
             TOTAL_REPORTED = :totalReported,
             out_total = :outTotal,
             TOTAL_MALE_OUT = :totalMaleOut,
             TOTAL_FEMALE_OUT = :totalFemaleOut,
             TOTAL_OTHER_OUT = :totalOtherOut,
             COMPLETION_DATE = SYSDATE(),
             OUTCOME = 'CLEARED',
             COUNT_IN_PROGRESS = 'N',

             COMMENT_TEXT =:commentText,
             DISCREP_RSN_CODE = :discrepRsnCode,
             modify_user_id =:modifyUserId,
             modify_datetime = current_timestamp
       WHERE REPORTING_LOC_ID = :reportingLocId
         AND COUNT_TYPE_ID = :countTypeId
         AND INITIATED_DATE::date = 
                   (SELECT locked_date::date
                      FROM locked_modules
                     WHERE module_name = 'OIDCOUNT'
                       AND session_id = :sessionId
                       AND user_id = :createUserId)
}

OIDDISRE_AGENCYCOUNTS_DELETE_AGENCY_COUNTS { 
	DELETE FROM LOCKED_MODULES WHERE SESSION_ID = :sessionId
}


OIDDISRE_CREATE_FORM_GLOBALSCREATE_FORM_GLOBALS {
	SELECT DESCRIPTION  FROM OMS_MODULES WHERE MODULE_NAME = :V_FORM_NAME
}

OIDDISRE_PRINT_LIST_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OIDDISRE_PRINT_LIST_ {
	SELECT * FROM SALES_MAINTENANCES WHERE CASELOAD_ID = :P_CASELOAD_ID AND STOCK_LOC_ID = :P_STOCK_ID
}

OIDDISRE_PRINT_LIST_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

OIDDISRE_DEFAULT_AGY_LOCDEFAULT_AGY_LOC {
	SELECT ALAL.AGY_LOC_ID, ALAL.DESCRIPTION DSP_DESCRIPTION2  FROM AGENCY_LOCATIONS ALAL WHERE ALAL.AGENCY_LOCATION_TYPE = 'INST' AND ALAL.AGY_LOC_ID IN ( SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID = :P_GLOBAL_CASELOAD_ID ) AND ALAL.AGY_LOC_ID NOT IN ( 'TRN', 'OUT' )
	}
