update
	iwp_bookmarks
set
	sql_text = 'select prison_location from v_header_block where offender_id =( select offender_id from offender_bookings where offender_book_id =?)',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'A_OFDE012';