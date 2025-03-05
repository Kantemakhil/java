GET_DESC_ND_ACTIVE{
SELECT description, active_type FROM legal_update_reasons WHERE update_reason_code = :p_code
}
