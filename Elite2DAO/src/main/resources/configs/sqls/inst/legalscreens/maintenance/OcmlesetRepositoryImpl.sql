OCMLESET_FETCH_DATA{
select ls.description setting, ls.create_datetime, ls.create_user_id, ls.modify_datetime, ls.modify_user_id, ls.value,ls.code  from legal_settings ls  
}

OCMLESET_UPDATE_DATA{
update legal_settings set value =:value , modify_datetime = current_timestamp, modify_user_id =:modifyUserId  where code =( select ls.code from legal_settings ls where ls.description =:setting) 
}

OCMLESET_FETCH_MOVEMENT_TYPES{
select MOVEMENT_REASON_CODE code, DESCRIPTION from MOVEMENT_REASONS where movement_type ='REL'  and active_flag ='Y' and expiry_date is null
}

OCMLESET_FETCH_OMS_ROLES{
select role_code code,role_name description from oms_roles order by role_seq asc 
}
OCMLESET_GET_LEGAL_SETTING_VALUE{
select ls.value from legal_settings ls where code=:code
}
