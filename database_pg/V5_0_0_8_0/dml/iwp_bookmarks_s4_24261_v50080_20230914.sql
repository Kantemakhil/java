update
	iwp_bookmarks
set
	sql_text = 'select
	address_line1 as address_type1,
	address_line2 as address_type2,
	address_line3 as address_type3,
	"comment",
	primary_flag,
	active,
	validated,
	phone_type,
	phone_number,
	"extension",
	address_type,
	From_date,
	to_date
from
	(
	select
		concat_ws('','', SUITE_NUMBER, ADDRESS_LINE1)as Address_line1,
		case
			when is_address_valid = ''Y'' then concat_ws('','', city_code, prov_state_code)
			else concat_ws('','', city_name, prov_state_desc)
		end as Address_line2,
		concat_ws('','', country_desc , zip_postal_code) as Address_line3,
		COMMENT_TEXT as "comment",
		PRIMARY_FLAG as primary_flag,
		active_flag as Active,
		IS_ADDRESS_VALID as validated,
		owner_id,
		(
		select
			string_agg((select description from reference_codes rc where domain = ''ADDRESS_TYPE'' and code = address_usage), '','')
		from
			address_usages au
		where
			au.address_ID = va.address_id)as address_type,
		to_char(start_date, ''MM/YYYY'')as From_date,
		to_char(end_date, ''MM/YYYY'')as To_date
	from
		V_ADDRESSES va
	where
		OWNER_ID = (
		select
			root_offender_id
		from
			offender_bookings
		where
			offender_book_id = ?)
	order by
		PRIMARY_FLAG desc,
		MAIL_FLAG desc,
		ACTIVE_FLAG desc,
		coalesce(START_DATE, ''01-JAN-1900'') desc) A
left join (
	select
		STRING_AGG((select description from reference_codes rc where domain = ''PHONE_USAGE'' and code = PHONE_TYPE), '','') as phone_type,
		STRING_AGG(PHONE_NO, '','')as phone_number,
		STRING_AGG(EXT_NO, '','')as "extension",
		owner_id
	from
		phones
	group by
		owner_id) b on
	A.owner_id = B.owner_id',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFFEN_ADDRES';