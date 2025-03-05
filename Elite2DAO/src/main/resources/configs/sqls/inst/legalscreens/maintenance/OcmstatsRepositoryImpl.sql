OCMSTATS_INSERT_ORDERS_DATA{
insert into legal_update_reasons (update_reason_code, description, effective_date, reason_category, active_type, list_seq, expiry_date, active_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values(:updateReasonCode, :description, :effectiveDate, :reasonCategory, :activeType, :listSeq, :expiryDate, :activeFlag, current_timestamp, :createUserId, NULL, :modifyUserId, :sealFlag)
}

OCMSTATS_INSERT_STATUES_DATA{
insert into legal_update_usages (legal_class, update_reason_code, active_flag, expiry_date, list_seq, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values(:legalClass, :updateReasonCode, :activeFlag, :expiryDate, :listSeq, current_timestamp, :createUserId, current_timestamp, :modifyUserId, :sealFlag)
}

OCMSTATS_GET_ORDERS_DATA{
select distinct lr.update_reason_code , lr.description, lr.active_type , lr.list_seq , lr.expiry_date , lr.active_flag,lr.create_datetime from legal_update_usages lu full outer join legal_update_reasons lr on lu.update_reason_code = lr.update_reason_code order by create_datetime DESC 
}

OCMSTATS_GET_STATUS_DATA{
select luu.*, rc.description from legal_update_usages luu, reference_codes rc where update_reason_code = :updateReasonCode and rc.code = luu.legal_class and rc.domain = 'LEGAL_BLOCK'
}

UPDATE_ORDERS_DATA{
update legal_update_reasons set description = :description , active_type = :activeType, list_seq = :listSeq , expiry_date = :expiryDate , active_flag = :activeFlag , modify_datetime = current_timestamp, modify_user_id = :modifyUserId where update_reason_code = :updateReasonCode 
}

UPDATE_STATUS_DATA{
update legal_update_usages set active_flag = :activeFlag , modify_datetime = current_timestamp , modify_user_id = :modifyUserId where legal_class =:legalClass and update_reason_code = :updateReasonCode
}

GET_DOMAIN_DATA{
SELECT "domain", code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag FROM oms_owner.reference_codes WHERE "domain"='LEGAL_BLOCK'
}

