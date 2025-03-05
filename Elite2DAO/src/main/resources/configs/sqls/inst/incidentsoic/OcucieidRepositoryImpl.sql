OCUCIEID_CHECK_FOR_INSERT_OR_UPDATE_EXTERNAL_INVST{
select count(*) from staff_member_roles where staff_id =( select staff_id from staff_members where user_id = :userId) and role_code = 'ADV_EXT_INVEST'
}

OCUCIEID_CHECK_FOR_DELETE_EXTERNAL_INVST{
select count(*) from staff_member_roles where staff_id =( select staff_id from staff_members where user_id = :userId) and role_code = 'EXT_INVEST_DLT'
}
OCUCIEID_GET_ALL_EXTERNAL_INVST_DETAILS
{
select * from agency_incident_charges_ext_inv_detls where agency_incident_id=:agencyIncidentId and charge_seq=:chargeSeq order by create_datetime desc
}
OCUCIEID_INSERT_EXTERNAL_INVST_DETAILS
{
insert into agency_incident_charges_ext_inv_detls (agency_incident_id, charge_seq, eid_seq, external_id, contact_datetime, ext_inv_status, ext_inv_comment, create_user_id, create_datetime, MODIFY_DATETIME) values ( :agencyIncidentId, :chargeSeq, :eidSeq, :externalId, :contactDate, :extInvStatus, :extInvComment, :createUserId, current_timestamp, null )
}
OCUCIEID_UPDATE_EXTERNAL_INVST_DETAILS
{
update agency_incident_charges_ext_inv_detls set external_id=:externalId, contact_datetime=:contactDate, ext_inv_status=:extInvStatus, ext_inv_comment=:extInvComment, modify_user_id=:createUserId, modify_datetime =current_timestamp where agency_incident_id=:agencyIncidentId and charge_seq=:chargeSeq and eid_seq=:eidSeq
}
OCUCIEID_DELETE_EXTERNAL_INVST_DETAILS
{
delete from agency_incident_charges_ext_inv_detls where agency_incident_id=:agencyIncidentId and charge_seq=:chargeSeq  and eid_seq=:eidSeq
}
OCUCIEID_GET_EID_SEQ{
select coalesce(max(eid_seq)+1,1) from agency_incident_charges_ext_inv_detls
}





