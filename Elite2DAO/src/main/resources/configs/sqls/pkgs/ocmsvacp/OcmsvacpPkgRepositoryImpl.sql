GET_PROG_NAME{
SELECT DESCRIPTION FROM PROGRAM_SERVICES WHERE PROGRAM_ID = :P_PROGRAM_ID
}

OCMSVACP_DEFAULT_ADDR_W_AGY {
SELECT * FROM V_ADDRESSES  WHERE OWNER_CLASS = 'AGY' AND OWNER_CODE = :P_PROVIDER_CODE  AND PRIMARY_FLAG = 'Y'
}

OCMSVACP_DEFAULT_ADDR_W_CORP {
SELECT * FROM V_ADDRESSES  WHERE OWNER_CLASS = 'CORP' AND OWNER_ID = :P_PROVIDER_ID AND SERVICES_FLAG = 'Y'
}
OCMSVACP_GET_CODE_UNIQUE_CNT_CUR {
SELECT COUNT (CA.CRS_ACTY_ID) FROM COURSE_ACTIVITIES CA, PROGRAM_SERVICES PS
 WHERE ((CA.PROVIDER_PARTY_ID = :P_TEAM_ID AND PROVIDER_PARTY_ID IS NOT NULL) OR (CA.PROVIDER_PARTY_CODE = :P_TEAM_CODE  AND PROVIDER_PARTY_CODE IS NOT NULL ))
AND CA.CODE = :P_CODE AND CA.PROGRAM_ID = PS.PROGRAM_ID AND (PROGRAM_CATEGORY IS NULL OR PS.PROGRAM_CATEGORY = :P_CATEGORY)
}
V_COURSE_MODULES_INSERT{
INSERT INTO v_course_modules(course_module_id, course_phase_id, program_module_id, list_seq, caseload_type, no_of_sessions, session_length,  services_address_id, internal_location_id)SELECT nextval('crs_acty_id'), :coursePhaseId, program_module_id,list_seq, :caseloadType, no_of_sessions, session_length,:servicesAddressId, :internalLocationId FROM v_program_modules WHERE program_phase_id = :programPhaseId
}
OCMSVACP_PKG_V_PROGRAM_MODULES{
SELECT NEXTVAL('crs_acty_id') crs_acty_id, program_module_id,list_seq, no_of_sessions, session_length  FROM v_program_modules  WHERE program_phase_id = :programPhaseId
}
