AGENCY_INCIDENTS_TWF_AGENCY_INCIDENTS_GET{
SELECT REPORTED_STAFF_ID,AGENCY_INCIDENT_ID,INCIDENT_DATE,INTERNAL_LOCATION_ID,INCIDENT_TIME,INCIDENT_TYPE,INCIDENT_STATUS,CREATE_DATETIME,CREATE_USER_ID,MODIFY_USER_ID,MODIFY_DATETIME,LOCK_FLAG,INCIDENT_DETAILS,REPORT_DATE,REPORT_TIME,AGY_LOC_ID,LEVEL_CODE,LOG_NO,INCIDENT_TEXT,SEAL_FLAG FROM AGENCY_INCIDENTS WHERE AGENCY_INCIDENT_ID = :agencyIncidentId
}
AGENCY_INCIDENTS_TWF_INCIDENT_PARTIES_CUR{
SELECT offender_book_id, party_seq FROM agency_incident_parties WHERE action_code = oms_miscellaneous_get_profile_value('CLIENT', 'CREATE_OIC') AND agency_incident_id = :agencyIncidentId
}
AGENCY_INCIDENTS_TWF_STG_PARTIES_CUR{
select
	(
	select
		stg.description
	from
		security_threat_groups stg
	where
		stg.stg_id = ostg.stg_id
		limit 1) stg_desc
from
	OFFENDER_STG_AFFILIATIONS ostg
where
	ostg.offender_book_id = :offenderBookId
	and ostg.active_flag = 'Y'
}