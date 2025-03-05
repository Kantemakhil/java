OFFENDER_INTER_MVMT_LOC_T1_UPDATE{
update
	offender_bookings
set modify_user_id =:modifyUserId,
modify_datetime =current_timestamp ,
	agency_iml_id = CASE  :agencyImlId
	 WHEN (
	select
		internal_location_id
	from
		agency_internal_locations ail
	where
		ail.internal_location_type = 'RTU'
		and ail.internal_location_code = 'RTU'
		and ail.agy_loc_id = :agencyLocationId ) THEN 
	null
	 ELSE :agencyImlId  END
where
	offender_book_id = :offenderBookId
}