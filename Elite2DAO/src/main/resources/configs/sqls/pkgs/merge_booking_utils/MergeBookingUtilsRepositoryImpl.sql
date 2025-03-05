
MERGE_BOOKING_UTILS_GET_BOOKING_VALS{
select ob.offender_id, o.offender_id_display, ob.booking_status, ob.active_flag, ob.community_active_flag, ob.agy_loc_id, o.last_name, o.first_name from offender_bookings ob, offenders o where ob.offender_book_id =:offenderBookId and o.offender_id = ob.offender_id
}