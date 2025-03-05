OUMTRNBKPKG_COUNT_OFF_BOOKINGS{
select count(*) from offender_bookings where root_offender_id = :p_root_off_id
}

OUMTRNBKPKG_GET_INST_BOOK_ACTIVE{
SELECT COUNT (1)FROM offender_bookings WHERE offender_book_id = :p_off_book_id  AND (active_flag = 'Y' OR community_active_flag = 'Y')
}

OUMTRNBKPKG_GET_TO_BOOK_ACTIVE{
 SELECT COUNT (1) FROM offender_bookings WHERE root_offender_id = :p_root_off_id_to AND (active_flag = 'Y' OR community_active_flag = 'Y')
}

OUMTRNBKPKG_GET_ACTIVE_BOOKING_CUR{
 SELECT COUNT (*) FROM offender_bookings WHERE root_offender_id = :p_root_off_id  AND (active_flag = 'Y' OR community_active_flag = 'Y') AND booking_begin_date = (SELECT MAX (booking_begin_date) FROM offender_bookings WHERE root_offender_id =:p_root_off_id);
}
