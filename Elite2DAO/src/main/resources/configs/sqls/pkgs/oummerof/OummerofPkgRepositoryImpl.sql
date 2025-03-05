OUMMEROF_GET_ROOT_OFFENDER_ID_RECORD_COUNT {
SELECT count(*)
           FROM offender_bookings
          WHERE root_offender_id = :rootOffenderId
            and (active_flag ='Y')
}