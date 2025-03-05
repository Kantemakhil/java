UPDATE oms_owner.iwp_bookmarks
SET sql_text='select
	sch_taprldt1,
	sch_taprldt2,
	sch_taprldt3,
	sch_taprltme,
	sch_tapstat,
	sch_taprsn,
	sch_tapadte1,
	sch_tapadte2,
	sch_tapadte3,
	sch_tapaptme,
	sch_taprdte1,
	sch_taprdte2,
	sch_taprdte3,
	sch_taprttme,
	SCH_TAPDYOUT,
	SCH_TAPHROUT,
	sch_tapcomnt,
	sch_tapdstnm,
	scg_tapesct,
	sch_taptsp,
	sch_tapdstcp,
	sch_tapdsstn,
	sch_tapdsstr,
	sch_tapdscty,
	sch_tapdsst,
	sch_tapdspc,
	sch_tapdscco,
	sch_tapdspht,
	sch_tapdsphn,
	sch_tapdspxt
from
	(
	select
		event_id,
		to_char(EVENT_DATE, ''MM-DD-YYYY'') as SCH_TAPRLDT1,
		to_char(EVENT_DATE, ''DD-MM-YYYY'') as SCH_TAPRLDT2,
		to_char(EVENT_DATE, ''YYYY-MM-DD'') as SCH_TAPRLDT3,
		to_char(START_TIME, ''HH24:MI'') as SCH_TAPRLTME,
		initcap(EVENT_STATUS_DESC) as SCH_TAPSTAT,
		(select DESCRIPTION  from reference_codes where domain = ''MOVE_TYPE'' and code = EVENT_SUB_TYPE) as SCH_TAPRSN,
		to_char(APPLICATION_DATE, ''MM-DD-YYYY'') as SCH_TAPADTE1,
		to_char(APPLICATION_DATE, ''DD-MM-YYYY'') as SCH_TAPADTE2,
		to_char(APPLICATION_DATE, ''YYYY-MM-DD'') as SCH_TAPADTE3,
		to_char(APPLICATION_TIME, ''HH24:MI'') as SCH_TAPAPTME,
		to_char(RETURN_DATE, ''MM-DD-YYYY'') as SCH_TAPRDTE1,
		to_char(RETURN_DATE, ''DD-MM-YYYY'') as SCH_TAPRDTE2,
		to_char(RETURN_DATE, ''YYYY-MM-DD'') as SCH_TAPRDTE3,
		to_char(RETURN_TIME, ''HH24:MI'') as SCH_TAPRTTME,
		RETURN_DATE::date-EVENT_DATE::date as SCH_TAPDYOUT,
		(RETURN_DATE::date-EVENT_DATE::date)* 24 as SCH_TAPHROUT,
		COMMENT_TEXT as SCH_TAPCOMNT,
		(
		select
			coalesce((select al.description from v_agency_addresses vaa, agency_locations al where vaa.address_id = voas.to_address_id and vaa.agy_loc_id = al.agy_loc_id),(select c.corporate_name from corporates c, v_corporate_addresses vca where c.corporate_id = vca.corporate_id and vca.address_id = voas.to_address_id ),(select
	SUBSTR(LAST_NAME || '', '' || FIRST_NAME ,
	1 ,
	100 ) DESCRIPTION
from
	OFFENDERS O ,
	V_ADDRESS_USAGES V
where
	O.OFFENDER_ID = V.OWNER_ID
	and V.OWNER_ID = (
	select
		root_offender_id
	from
		offender_bookings ob
	where
		offender_book_id = voas.offender_book_id)
	and ADDRESS_USAGE = ''TAP''))
		from
			dual) as SCH_TAPDSTNM,
		initcap(ESCORT_DESC) as SCG_TAPESCT,
		(
		select
			initcap(description)
		from
			reference_codes rc
		where
			domain = ''TA_TRANSPORT''
			and
        code = TRANSPORT_CODE) as SCH_TAPTSP,
		CONTACT_PERSON_NAME as SCH_TAPDSTCP,
		TO_ADDRESS_ID
	from
		V_OFFENDER_ALL_SCHEDULES voas
	where
		EVENT_ID = ? and
     EVENT_Type = ''TAP'' and
     RETURN_DATE is not null
	order by
		APPLICATION_DATE desc,
		APPLICATION_TIME desc)A
left outer join (
	select
		ADDRESS_ID,
		SUITE_NUMBER as SCH_TAPDSSTN,
		STREET_INFORMATION as SCH_TAPDSSTR,
		case when CITY_NAME is null then CITY_CODE ELSE CITY_NAME end as SCH_TAPDSCTY,
		PROV_STATE_DESC as SCH_TAPDSST,
		ZIP_POSTAL_CODE as SCH_TAPDSPC,
		(
		select
			initcap(description)
		from
			reference_codes rc
		where
			domain = ''COUNTRY''
			and
        code = COUNTRY_CODE) as SCH_TAPDSCCO
	from
		v_addresses)B on
	A.To_ADDRESS_ID = B.ADDRESS_ID
left join (
	select
		STRING_AGG((select initcap(description)
      from reference_codes rc
      where
        domain = ''PHONE_USAGE'' and
        code = PHONE_TYPE), '','' order by OWNER_ID) as SCH_TAPDSPHT,
		STRING_AGG(to_char(replace(PHONE_NO, '' ''::text, ''''::text)::bigint,(select maskformat from (select jsonb_array_elements(encode(setting_value, ''escape'')::JSONB ) ->>''maskFormat'' as maskformat, jsonb_array_elements(encode(setting_value, ''escape'')::JSONB ) ->>''maskingCode'' as maskingcode from system_settings where setting_type = ''PHONEFORMAT'')a where maskingCode = format  )::text), '','' order by OWNER_ID) as SCH_TAPDSPHN,
		STRING_AGG(EXT_NO, '','' order by owner_ID) as SCH_TAPDSPXT,
		OWNER_ID
	from
		V_PHONES
	where
		OWNER_CLASS = ''ADDR''
	group by
		OWNER_ID)C on
	B.ADDRESS_ID = C.OWNER_ID', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER'
WHERE bookmark_name='SCHEDULES';
