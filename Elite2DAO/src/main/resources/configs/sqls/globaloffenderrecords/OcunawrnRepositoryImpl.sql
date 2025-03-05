OCUNAWARN_CHK_NONASSOCIATIONS {
	SELECT
	ona.*,
	(select last_name  from offenders o, offender_bookings ob where ob.offender_book_id  = ona.ns_offender_book_id  and ob.offender_id = o.offender_id) as last_name ,
	(select first_name  from offenders o, offender_bookings ob where ob.offender_book_id  = ona.ns_offender_book_id  and ob.offender_id = o.offender_id) as first_name,
	lpad(ona.offender_id::text , 10, '0') as offender_id_display
FROM
	OFFENDER_NON_ASSOCIATIONS ona
WHERE
	ona.offender_book_id =:offenderBookId
	AND ona.NS_OFFENDER_ID IN 
	(
	SELECT
		nad.NS_OFFENDER_ID
	FROM
		OFFENDER_NA_DETAILS nad
	WHERE
		nad.offender_book_id =:offenderBookId
		AND 
	current_date < COALESCE(nad.ns_expiry_date,
		current_date + 1)
			AND current_date >= nad.ns_effective_date)
 }
 
OCUNAWARN_CHECK_SCHEDULE_CONFLICTS{
SELECT * FROM OFFENDER_IND_SCHEDULES WHERE OFFENDER_BOOK_ID = :offenderBookID AND EVENT_DATE = :eventDate AND TO_AGY_LOC_ID = :toAgyLocId and event_status = 'SCH'
}