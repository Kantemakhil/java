
OIUINCRE_AGENCYINCIDENTS_FIND_AGENCY_INCIDENTS {
 	SELECT REPORTED_STAFF_ID ,AGENCY_INCIDENT_ID ,INCIDENT_DATE ,INTERNAL_LOCATION_ID ,INCIDENT_TIME ,INCIDENT_TYPE ,INCIDENT_STATUS ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_USER_ID ,MODIFY_DATETIME ,LOCK_FLAG ,INCIDENT_DETAILS ,REPORT_DATE ,REPORT_TIME ,AGY_LOC_ID ,LEVEL_CODE ,LOG_NO ,INCIDENT_TEXT ,SEAL_FLAG   FROM AGENCY_INCIDENTS  
 	 where  AGENCY_INCIDENT_ID in  (SELECT AIP.AGENCY_INCIDENT_ID FROM AGENCY_INCIDENT_PARTIES AIP, OFFENDER_BOOKINGS OFB 
WHERE AIP.OFFENDER_BOOK_ID = OFB.OFFENDER_BOOK_ID
AND OFB.ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID)
}

OIUINCRE_CREATE_FORM_GLOBALSCREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}
