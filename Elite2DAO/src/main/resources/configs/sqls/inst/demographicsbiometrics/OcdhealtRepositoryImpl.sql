OCDHEALT_GET_HEAL_USER_ROLE_ID {
select role_id from oms_roles where role_code=:roleCode
}
OCDHEALT_INSERT_OFF_HEALTH_RECORDS_DATA {
insert into OFF_HEALTH_RECORDS(off_health_rec_id, offender_book_id, health_type, health_sub_type, description, from_date, to_date, health_status, record_create_datetime, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values(nextval('off_health_rec_id_seq'), :offenderBookId, :healthType, :healthSubType, :description, :fromDate, :toDate, :healthStatus, current_timestamp, current_timestamp, :createUserId, NULL, NULL, :sealFlag) 
}
OCDHEALT_UPDATE_OFF_HEALTH_RECORDS_DATA{
update OFF_HEALTH_RECORDS set health_type =:healthType, health_sub_type =:healthSubType, description =:description, from_date =:fromDate, to_date =:toDate, health_status =:healthStatus, record_create_datetime = current_timestamp, modify_datetime = current_timestamp, modify_user_id =:modifyUserId where off_health_rec_id =:offHealthRecId
}
OCDHEALT_RETRIVE_OFF_HEALTH_RECORDS_DATA{
select * from OFF_HEALTH_RECORDS where offender_book_id=:offenderBookId order by modify_datetime desc
}
OCDHEALT_DELETE_OFF_HEALTH_RECORDS_DATA{
delete from OFF_HEALTH_RECORDS where off_health_rec_id=:offHealthRecId
}
OCDHEALT_INSERT_OFF_HEALTH_RECORD_DTLS_DATA{
insert into OFF_HEALTH_RECORD_DTLS (off_health_rec_id, health_treat_type, health_provider, description, from_date, to_date, comment_text, record_create_datetime, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values(:offHealthRecId, :healthTreatType, :healthProvider, :description, :fromDate, :toDate, :commentText, current_timestamp, current_timestamp, :createUserId, NULL, NULL, :sealFlag) 
}
OCDHEALT_UPDATE_OFF_HEALTH_RECORD_DTLS_DATA{
update OFF_HEALTH_RECORD_DTLS set health_treat_type =:healthTreatType, health_provider =:healthProvider, description =:description, from_date =:fromDate, to_date =:toDate, comment_text =:commentText, record_create_datetime = current_timestamp, modify_datetime = current_timestamp, modify_user_id =:modifyUserId where OFF_HEALTH_REC_DTL_ID =:offHealthRecDtlId
}
OCDHEALT_DELETE_OFF_HEALTH_RECORD_DTLS_DATA {
delete from OFF_HEALTH_RECORD_DTLS where OFF_HEALTH_REC_DTL_ID =:offHealthRecDtlId
}
OCDHEALT_RETRIEVE_OFF_HEALTH_RECORD_DTLS_DATA{
select * from OFF_HEALTH_RECORD_DTLS where off_health_rec_id=:offHealthRecId order by modify_datetime desc
}