OFFENDER_EXTERNAL_MOVEMENTS_T1_INSERTING{
update offender_bookings set status_reason = :movementType || '-' || :movementReasonCode, modify_datetime = current_timestamp, modify_user_id = :modifyUserId where offender_book_id = :offenderBookId
}
OFFENDER_EXTERNAL_MOVEMENTS_T1_UPDATING{
update offender_bookings set modify_datetime = current_timestamp, modify_user_id = :modifyUserId, status_reason = coalesce(:newMovementType,:oldMovementType ) || '-' || coalesce(:newMovementReasonCode,:oldMovementReasonCode) where offender_book_id = :oldOffenderBookId
}