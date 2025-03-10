
OCDCLIST_FIND_RGAGYLOC {
 	SELECT AGY_LOC.DESCRIPTION  DESCRIPTION , AGY_LOC.AGY_LOC_ID  CODE  FROM   AGENCY_LOCATIONS AGY_LOC WHERE AGENCY_LOCATION_TYPE ='CRT'   AND ACTIVE_FLAG ='Y' ORDER BY LIST_SEQ ,DESCRIPTION
}

OCDCLIST_FIND_RGHEARING {
SELECT  CASE PARENT_CODE WHEN 'CRT' THEN 1 ELSE 0 END SEQVALUE, DESCRIPTION , CODE FROM REFERENCE_CODES WHERE DOMAIN = 'MOVE_RSN'  AND  (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OCDCLIST_CTLLST_INSERT_OCDCLIST.COURT_LIST_QUERY {
	INSERT INTO OCDCLIST.COURT_LIST_QUERY() VALUES(:)
}

OCDCLIST_CTLLST_UPDATE_OCDCLIST.COURT_LIST_QUERY {
	UPDATE OCDCLIST.COURT_LIST_QUERY set 
}

OCDCLIST_CTLUN_FIND_V_COURT_EVENTS {
 	SELECT EVENT_ID ,EVENT_CLASS ,EVENT_TYPE ,CASE_ID ,CASE_INFO_PREFIX ,CASE_INFO_NUMBER ,OFFENDER_ID_DISPLAY ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,BIRTH_DATE ,OFFENDER_BOOK_ID ,BOOKING_ACTIVE_FLAG ,LIVING_UNIT_ID ,LEVEL_1_CODE ,LEVEL_2_CODE ,LEVEL_3_CODE ,LEVEL_4_CODE ,OFFENDER_AGY_LOC_ID ,OFFENDER_AGY_LOC_DESC ,EVENT_DATE ,START_TIME ,END_TIME ,COURT_EVENT_TYPE ,COURT_EVENT_TYPE_DESC ,MOVEMENT_REASON_CODE ,MOVEMENT_REASON_DESC ,JUDGE_NAME ,EVENT_STATUS ,RESULT_CODE ,PARENT_EVENT_ID ,AGY_LOC_ID ,AGY_LOC_ID_NAME ,COMMENT_TEXT ,EVENT_OUTCOME ,CHECK_SUM  
 	FROM V_COURT_EVENTS  WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID AND EXISTS(SELECT 1 FROM OFFENDER_CHARGES WHERE OFFENDER_BOOK_ID = V_COURT_EVENTS.OFFENDER_BOOK_ID
 	AND CASE_ID = V_COURT_EVENTS.CASE_ID AND coalesce(RESULT_CODE_1_INDICATOR, '0') <> 'F') ORDER BY EVENT_DATE DESC, START_TIME DESC
}

OCDCLIST_CTL_LST_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_COURT_EVENTS V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDCLIST_GET_PROFILE_VALUE_2_ {
	SELECT PROFILE_VALUE_2 FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = P_PROFILE_TYPE AND PROFILE_CODE = P_PROFILE_CODE
}
OCDCLIST_CTLLST_UPDATE_COURT_LIST_QUERY {
update COURT_EVENTS set START_TIME = :pStartTime, COURT_EVENT_TYPE = :pCourtEventType, EVENT_STATUS = :pEventStatus, modify_user_id =:modifyUserId, modify_datetime =current_timestamp where EVENT_ID = :pEventId
}