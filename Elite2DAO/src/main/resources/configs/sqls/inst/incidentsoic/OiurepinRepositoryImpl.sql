OIUREPIN_GET_REPORTABLE_INCIDENT_DETAILS_DATA {
select incident_reportable_id,agency_incident_id,
party_seq,
reportable_incident_type,
comment_text,
reportable_staff_id,
reportable_datetime,
create_datetime,
create_user_id,
modify_user_id,
modify_datetime,
seal_flag,(select
	last_name || ',' || ' ' || first_name as user_id
from
	staff_members sm
where
	staff_id = reportable_staff_id ) as user_name from INCIDENT_REPORTABLE_DETAILS  where AGENCY_INCIDENT_ID=:agencyIncedentId and PARTY_SEQ=:partySeq
}

OIUREPIN_INSERT_REPORTABLE_INCIDENT_DETAILS_DATA {
insert into INCIDENT_REPORTABLE_DETAILS(INCIDENT_REPORTABLE_ID, AGENCY_INCIDENT_ID, PARTY_SEQ, REPORTABLE_INCIDENT_TYPE, COMMENT_TEXT, REPORTABLE_STAFF_ID, REPORTABLE_DATETIME, CREATE_DATETIME, CREATE_USER_ID, seal_flag) values (nextval('incident_reportable_id_seq'), :agencyIncidentId, :partySeq, :reportableIncidentType, :commentText, :reportableStaffId, current_timestamp, current_timestamp, :createUserId, null)
}

OIUREPIN_UPDATE_REPORTABLE_INCIDENT_DETAILS_DATA {
update INCIDENT_REPORTABLE_DETAILS set REPORTABLE_INCIDENT_TYPE=:reportableIncidentType,COMMENT_TEXT=:commentText,REPORTABLE_STAFF_ID=:reportableStaffId,
REPORTABLE_DATETIME=current_timestamp , MODIFY_USER_ID=:modifyUserId, MODIFY_DATETIME=current_timestamp where INCIDENT_REPORTABLE_ID=:incidentReportableId
}

OIUREPIN_DELETE_REPORTABLE_INCIDENT_DETAILS_DATA {
delete from INCIDENT_REPORTABLE_DETAILS where INCIDENT_REPORTABLE_ID=:incidentReportableId
}
OIUREPIN_GET_USERNAME_BASED_LOGGED_USER {
select
	last_name || ',' || ' ' || first_name as user_name
from
	staff_members sm where user_id =:userNameLog
}