MERGE_OFFENDER_TRUST_CHECK_FOR_TRUST_ACCOUNT{
SELECT COUNT (offender_id) FROM offender_trust_accounts ota WHERE ota.offender_id = :cpRootOffenderId
}