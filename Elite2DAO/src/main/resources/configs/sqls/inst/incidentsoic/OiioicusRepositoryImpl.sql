
OIIOICUS_FIND_RGOICHEARINGTYPE {
 SELECT    DESCRIPTION  ,CODE FROM REFERENCE_CODES WHERE DOMAIN = 'OIC_HEAR' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR ('ENTER-QUERY' = 'ENTER-QUERY' ) ) ORDER BY DESCRIPTION ,  LIST_SEQ
}
OIIOICUS_FIND_RGINCIDENTTYPE {
 SELECT  DESCRIPTION  ,  CODE     FROM REFERENCE_CODES REF_CODE WHERE DOMAIN = 'INC_TYPE' AND ((ACTIVE_FLAG = 'Y'       AND EXPIRED_DATE IS NULL ) OR 'ENTER-QUERY' = 'ENTER-QUERY' ) ORDER BY DESCRIPTION , LIST_SEQ
}
OIIOICUS_FIND_RGOFFENCETYPE {
 SELECT DESCRIPTION ,         OIC_OFFENCE_CODE OIC_OFFENCE_TYPE FROM OIC_OFFENCES OC WHERE (ACTIVE_FLAG='Y'       OR 'ENTER-QUERY' = 'ENTER-QUERY' ) AND OC.START_DATE <= :INCIDENTDATE AND NVL(OC.END_DATE , TO_DATE ('01/01/9000' , 'DD/MM/YYYY' ) ) >= :INCIDENTDATE ORDER BY DESCRIPTION ,           LIST_SEQ
}
OIIOICUS_FIND_RGSANCTIONCODE {
 SELECT   DESCRIPTION  ,CODE FROM REFERENCE_CODES WHERE DOMAIN = 'OIC_SANCT' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL )  OR ('ENTER-QUERY' = 'ENTER-QUERY' ) ) ORDER BY DESCRIPTION , LIST_SEQ
}
OIIOICUS_VOICINCI_FIND_V_OIC_INCIDENTS {
 SELECT OFFENDER_BOOK_ID ,REPORTED_STAFF_ID ,BOOKING_NO ,AGENCY_INCIDENT_ID ,PARTY_SEQ ,INCIDENT_DATE ,INT_LOC_DESCRIPTION ,INCIDENT_TIME ,INCIDENT_TYPE ,INCIDENT_TYPE_DESC ,INCIDENT_DETAILS ,REPORT_DATE ,AGY_LOC_ID ,OIC_INCIDENT_ID ,OIC_CHARGE_FLAG   FROM V_OIC_INCIDENTS
}

OIIOICUS_VOICHEAR_FIND_V_OIC_HEARINGS {
 SELECT OIC_HEARING_ID ,OIC_HEARING_TYPE ,OIC_HEARING_TYPE_DESC ,HEARING_DATE ,HEARING_TIME ,HEARING_STAFF_NAME ,COMMENT_TEXT ,INT_LOC_DESCRIPTION ,REPRESENTATIVE_TEXT ,OIC_INCIDENT_ID   FROM V_OIC_HEARINGS
}

OIIOICUS_VOICHEARRES_FIND_V_OIC_HEARING_RESULTS {
 SELECT OIC_HEARING_ID ,RESULT_SEQ ,OIC_CHARGE_ID ,OIC_OFFENCE_CATEGORY ,OIC_OFFENCE_CODE ,OIC_OFFENCE_TYPE ,OIC_OFFENCE_DESCRIPTION ,PLEA_DESCRIPTION ,FINDING_DESCRIPTION ,OIC_OFN_TYPE_DESC ,RESULT_OIC_OFFENCE_CATEGORY ,RESULT_OIC_OFFENCE_CODE ,RESULT_OFFENCE_TYPE ,RESULT_OIC_OFFENCE_DESCRIPTION ,RESULT_OIC_OFN_TYPE_DESC   FROM V_OIC_HEARING_RESULTS 
}

OIIOICUS_VOFFOICSANCT_FIND_V_OFFENDER_OIC_SANCTIONS {
 SELECT OFFENDER_BOOK_ID ,SANCTION_SEQ ,OIC_SANCTION_DESC ,COMPENSATION_AMOUNT ,SANCTION_MONTHS ,SANCTION_DAYS ,COMMENT_TEXT ,EFFECTIVE_DATE ,CONSECUTIVE_SANCTION_SEQ ,OIC_HEARING_ID ,OIC_SANCTION_CODE ,STATUS_DESCRIPTION ,RESULT_SEQ ,STATUS_DATE ,OIC_INCIDENT_ID   FROM V_OFFENDER_OIC_SANCTIONS
}

OIIOICUS_OFF_BKG_ONCHECKDELETEMASTER_V_OIC_INCI_CUR {
 SELECT 1 FROM V_OIC_INCIDENTS V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIIOICUS_V_OIC_INCI_ONCHECKDELETEMASTER_V_OIC_HEAR_CUR {
 SELECT 1 FROM V_OIC_HEARINGS V WHERE V.OIC_INCIDENT_ID = :OICINCIDENTID
}

OIIOICUS_V_OIC_HEAR_ONCHECKDELETEMASTER_V_OIC_HEAR_RES_CUR {
 SELECT 1 FROM V_OIC_HEARING_RESULTS V WHERE V.OIC_HEARING_ID = :OICHEARINGID
}

OIIOICUS_V_OIC_HEAR_RES_ONCHECKDELETEMASTER_V_OFF_OIC_SANCT_CUR {
 SELECT 1 FROM V_OFFENDER_OIC_SANCTIONS V WHERE V.OIC_HEARING_ID = :OICHEARINGID AND V.RESULT_SEQ = :RESULTSEQ
}

OIIOICUS_GET_PROFILE_VALUE_2_VS_PROFVAL_CUR {
 SELECT PROFILE_VALUE_2 FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = :P_PROFILE_TYPE AND PROFILE_CODE = :P_PROFILE_CODE
}
OIIOICUS_GET_HEARING_STAFF_NAME_LIST {
 SELECT DISTINCT HEARING_STAFF_NAME  FROM V_OIC_HEARINGS
}
OIIOICUS_GET_HEARING_RESULTS_OIC_OFFENCE_DES {
 SELECT DISTINCT OIC_OFFENCE_DESCRIPTION  FROM V_OIC_HEARING_RESULTS
}
OIIOICUS_GET_HEARING_RESULTS_TYPE {
 SELECT DISTINCT OIC_OFN_TYPE_DESC  FROM V_OIC_HEARING_RESULTS
}
OIIOICUS_GET_DISC_OIC_SANCTION_DESC {
 SELECT DISTINCT OIC_SANCTION_DESC FROM V_OFFENDER_OIC_SANCTIONS
}
OIIOICUS_GET_DISC_STATUS_DESC {
 SELECT DISTINCT STATUS_DESCRIPTION FROM V_OFFENDER_OIC_SANCTIONS
}
