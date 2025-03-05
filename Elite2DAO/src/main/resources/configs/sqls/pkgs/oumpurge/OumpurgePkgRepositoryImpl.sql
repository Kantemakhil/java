GET_OFFENDER_ID{
SELECT offender_id FROM offenders WHERE root_offender_id = :p_root_offender_id
}
GET_OFF_BOOK_ID{
SELECT offender_book_id FROM offender_bookings WHERE offender_id = :p_off_id
}




