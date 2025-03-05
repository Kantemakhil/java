OIMCUSTAS_FETCH_DATA{
select status_code,status_description,status_rank,intake_flag,release_flag,always_display_flag,active_flag,expiry_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag from legal_custody_statuses order by status_rank asc 
}
OIMCUSTAS_SAVE_DATA{
insert into legal_custody_statuses(status_code,status_description,status_rank,intake_flag,release_flag,always_display_flag,active_flag,expiry_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values(:statusCode,:statusDescription,:statusRank,:intakeFlag,:releaseFlag,:alwaysDisplayFlag,:activeFlag,:expiryDate,current_timestamp,:createUserId,NULL,NULL,NULL)
}
OIMCUSTAS_UPDATE_DATA{
update legal_custody_statuses set status_description =:statusDescription, status_rank =:statusRank, intake_flag =:intakeFlag, release_flag =:releaseFlag, always_display_flag =:alwaysDisplayFlag, active_flag =:activeFlag, expiry_date = :expiryDate , modify_datetime = current_timestamp, modify_user_id =:modifyUserId where status_code=:statusCode
}