DOMAIN_CUR{
SELECT description FROM reference_codes
WHERE domain = :p_domain AND code =:p_ref_code
}
    
GET_DESC_CODE{
SELECT description FROM reference_codes WHERE domain = :p_domain AND code = :p_ref_code
}

DEFAULT_DOMAIN{
SELECT CODE FROM REFERENCE_CODES WHERE DOMAIN = :P_DOMAIN AND LIST_SEQ = 1 AND ACTIVE_FLAG = 'Y'
}