GL_TRANSACTIONS_T1_V_PROFILE_VALUE{
SELECT profile_value FROM system_profiles  WHERE profile_type = 'CLIENT' AND profile_code = 'TRUST_AUDITS'
}
GL_TRANSACTIONS_T1_V_MODULE_NAME{
SELECT module_name  FROM   trust_audits_tmp WHERE  sid = :vSessionid
}
GL_TRANSACTIONS_T1_TRUST_AUDITS_INSERT{
INSERT INTO trust_audits(module_name, txn_id,create_datetime,modify_datetime,create_user_id) VALUES (:moduleName, :txnId,:createDatetime,:modifyDatetime,:createUserId)
}