
OTINAMES_VTHA_FIND_V_TRUST_HEADER {
select 
  A.TRUST_CASELOAD_ID, A.CASELOAD_ID, A.COMMISSARY_TRUST_CASELOAD, 
  A.COMMUNITY_TRUST_CASELOAD, A.OFFENDER_ID, A.ALIAS_OFFENDER_ID, 
  A.OFFENDER_ID_DISPLAY, A.LAST_NAME, A.FIRST_NAME, A.MIDDLE_NAME, A.SUFFIX, 
  A.BIRTH_DATE, A.OFFENDER_BOOK_ID, A.BOOKING_NO, A.BOOKING_TYPE, 
  A.BOOKING_BEGIN_DATE, A.BOOKING_END_DATE, A.AGY_LOC_ID, A.LIVING_UNIT_ID, 
  A.DISCLOSURE_FLAG, A.ACTIVE_FLAG, A.BOOKING_STATUS, A.LIVING_UNIT_DESCRIPTION, 
  A.IN_OUT_STATUS, A.STATUS_DISPLAY, A.ROOT_OFFENDER_ID, A.INTAKE_AGY_LOC_ID, 
  A.COMMUNITY_ACTIVE_FLAG, A.CREATE_INTAKE_AGY_LOC_ID, A.COMMUNITY_STATUS, 
  A.STATUS_REASON, A.HEADER_STATUS, A.AGY_LOC_TYPE, A.AGE, A.GENDER, A.OFFICER, 
  A.PRISON_LOCATION, A.OFF_ALERTS, A.STATUS_1, A.STATUS_2, A.STATUS_3, 
  A.ETHNICITY, A.ACCOUNT_CLOSED_FLAG, A.MOVEMENT_REASON, A.OFF_SUP_LEVEL, 
  A.INDIGENT_FLAG, B.CURRENT_BALANCE, B.ACCOUNT_CLOSED_FLAG,A.SEX
from 
  (SELECT 
     trust_caseload_id, caseload_id, commissary_trust_caseload, 
     community_trust_caseload, offender_id, alias_offender_id, 
     offender_id_display, last_name, first_name, middle_name, suffix, 
     birth_date, offender_book_id, booking_no, booking_type, booking_begin_date, 
     booking_end_date, agy_loc_id, living_unit_id, disclosure_flag, active_flag, 
     booking_status, living_unit_description, in_out_status, status_display, 
     root_offender_id, intake_agy_loc_id, community_active_flag, 
     create_intake_agy_loc_id, community_status, status_reason, header_status, 
     agy_loc_type, age, gender, officer, prison_location, off_alerts, status_1, 
     status_2, status_3, ethnicity, account_closed_flag, movement_reason, 
     off_sup_level, indigent_flag,sex
   FROM v_trust_header v_trust_header 
    }
OTINAMES_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTINAMES_CGFKCHK_V_THA_V_THA_OFF_TA_F {
	SELECT OFF_TA.CURRENT_BALANCE, OFF_TA.ACCOUNT_CLOSED_FLAG FROM OFFENDER_TRUST_ACCOUNTS OFF_TA WHERE OFF_TA.OFFENDER_ID = :ROOTOFFENDERID AND EXISTS (SELECT '1' FROM CASELOADS CSLD WHERE CSLD.CASELOAD_ID = :CASELOADID AND (   (    CSLD.COMMISSARY_FLAG = 'Y' AND CSLD.COMMISSARY_TRUST_CASELOAD IS NOT NULL AND OFF_TA.CASELOAD_ID = CSLD.COMMISSARY_TRUST_CASELOAD AND CSLD.CASELOAD_ID = :CASELOADID) OR (    CSLD.COMMISSARY_FLAG = 'Y' AND CSLD.COMMISSARY_TRUST_CASELOAD IS NULL AND OFF_TA.CASELOAD_ID IN (SELECT CSLD1.CASELOAD_ID FROM CASELOADS CSLD1, CASELOAD_AGENCY_LOCATIONS CA WHERE CSLD1.TRUST_COMMISSARY_CASELOAD = CSLD.CASELOAD_ID AND CA.CASELOAD_ID = CSLD1.CASELOAD_ID AND CA.AGY_LOC_ID = :AGYLOCID) AND CSLD.CASELOAD_ID = :CASELOADID) OR (    CSLD.TRUST_ACCOUNTS_FLAG = 'Y' AND OFF_TA.CASELOAD_ID = CSLD.CASELOAD_ID AND CSLD.CASELOAD_ID = :CASELOADID)))
}

OTINAMES_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OTINAMES_DIALOG_DATA {
/*SELECT OFF_TA.CURRENT_BALANCE, OFF_TA.ACCOUNT_CLOSED_FLAG
              FROM OFFENDER_TRUST_ACCOUNTS OFF_TA
             WHERE OFF_TA.OFFENDER_ID = :ROOTOFFENDERID
               AND EXISTS (SELECT '1'
                             FROM CASELOADS CSLD
                            WHERE CSLD.CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = USER)
                              AND (   (    CSLD.COMMISSARY_FLAG = 'Y'
                                       AND CSLD.COMMISSARY_TRUST_CASELOAD IS NOT NULL
                                       AND OFF_TA.CASELOAD_ID = CSLD.COMMISSARY_TRUST_CASELOAD
                                       AND CSLD.CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = USER))
                                   OR (    CSLD.COMMISSARY_FLAG = 'Y'
                                       AND CSLD.COMMISSARY_TRUST_CASELOAD IS NULL
                                       AND OFF_TA.CASELOAD_ID IN (SELECT CSLD1.CASELOAD_ID
                                                                    FROM CASELOADS CSLD1, CASELOAD_AGENCY_LOCATIONS CA
                                                                   WHERE CSLD1.TRUST_COMMISSARY_CASELOAD = CSLD.CASELOAD_ID
                                                                     AND CA.CASELOAD_ID = CSLD1.CASELOAD_ID
                                                                     AND CA.AGY_LOC_ID = :AGYLOCID)
                                       AND CSLD.CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = USER))
                                   OR (    CSLD.TRUST_ACCOUNTS_FLAG = 'Y'
                                       AND OFF_TA.CASELOAD_ID = CSLD.CASELOAD_ID
                                       AND CSLD.CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = USER))))*/

SELECT OFF_TA.CURRENT_BALANCE, OFF_TA.ACCOUNT_CLOSED_FLAG
              FROM OFFENDER_TRUST_ACCOUNTS OFF_TA
             WHERE OFF_TA.OFFENDER_ID = :ROOTOFFENDERID
               AND EXISTS (SELECT '1'
                             FROM CASELOADS CSLD
                            WHERE CSLD.CASELOAD_ID = :CASELOADID
                              AND (   (    CSLD.COMMISSARY_FLAG = 'Y'
                                       AND CSLD.COMMISSARY_TRUST_CASELOAD IS NOT NULL
                                       AND OFF_TA.CASELOAD_ID = CSLD.COMMISSARY_TRUST_CASELOAD
                                       AND CSLD.CASELOAD_ID = :CASELOADID)
                                   OR (    CSLD.COMMISSARY_FLAG = 'Y'
                                       AND CSLD.COMMISSARY_TRUST_CASELOAD IS NULL
                                       AND OFF_TA.CASELOAD_ID IN (SELECT CSLD1.CASELOAD_ID
                                                                    FROM CASELOADS CSLD1, CASELOAD_AGENCY_LOCATIONS CA
                                                                   WHERE CSLD1.TRUST_COMMISSARY_CASELOAD = CSLD.CASELOAD_ID
                                                                     AND CA.CASELOAD_ID = CSLD1.CASELOAD_ID
                                                                     AND CA.AGY_LOC_ID = :AGYLOCID)
                                       AND CSLD.CASELOAD_ID = :CASELOADID)
                                   OR (    CSLD.TRUST_ACCOUNTS_FLAG = 'Y'
                                       AND OFF_TA.CASELOAD_ID = CSLD.CASELOAD_ID
                                       AND CSLD.CASELOAD_ID = :CASELOADID)))
}
OTINAMES_SC_MT_CUR {
select count(*)
        from caseloads c1
        where c1.commissary_flag = 'Y'
         and c1.caseload_id = :v_caseload_id
         and c1.Trust_Accounts_Flag = 'N'
         and c1.commissary_trust_caseload is null
         and exists (select 1
                       from caseloads c2
                       where c2.Trust_Accounts_Flag = 'Y'
                         and c2.trust_commissary_caseload = :v_caseload_id)
}