GET_OFF_DETAILS_BY_BOOKID{
SELECT o.offender_id_display, o.last_name, o.first_name FROM offender_bookings ob, offenders o WHERE ob.offender_book_id = :p_offender_book_id AND ob.offender_id = o.offender_id
}