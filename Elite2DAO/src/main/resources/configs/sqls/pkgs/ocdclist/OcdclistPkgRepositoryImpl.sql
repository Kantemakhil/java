COURT_LIST_DATA_SELECT{
select 	vce.EVENT_ID,	vce.LAST_NAME,	vce.FIRST_NAME,	vce.MIDDLE_NAME,	vce.BIRTH_DATE,	vce.OFFENDER_ID_DISPLAY,	vce.START_TIME,	vce.EVENT_DATE,	vce.CASE_INFO_PREFIX,
	vce.CASE_INFO_NUMBER,	vce.COURT_EVENT_TYPE,	vce.COURT_EVENT_TYPE_DESC,	vce.OFFENDER_BOOK_ID,	vce.AGY_LOC_ID,	vce.CASE_ID,	vce.CHECK_SUM, vce.APPEARANCE_LOCATION, vce.APPEARANCE_TYPE ,
	vce.outcome_reason_code,vce.event_status as eventStatus,
	(select ce.matter from COURT_EVENTS ce where ce.event_id = vce.event_id) matter from	V_COURT_EVENTS vce where
}
