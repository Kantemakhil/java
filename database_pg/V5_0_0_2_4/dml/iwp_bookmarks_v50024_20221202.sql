update iwp_bookmarks set    modify_datetime = current_timestamp
       ,modify_user_id = 'OMS_OWNER' 
       ,sql_text='select 
  sch_taprldt1, sch_taprldt2, sch_taprldt3, sch_taprltme, sch_tapstat, 
  sch_taprsn, sch_tapadte1, sch_tapadte2, sch_tapadte3, sch_tapaptme, 
  sch_taprdte1, sch_taprdte2, sch_taprdte3, sch_taprttme, SCH_TAPDYOUT, 
  SCH_TAPHROUT, sch_tapcomnt, sch_tapdstnm, scg_tapesct, sch_taptsp, 
  sch_tapdstcp, sch_tapdsstn, sch_tapdsstr, sch_tapdscty, sch_tapdsst, 
  sch_tapdspc, sch_tapdscco, sch_tapdspht, sch_tapdsphn, sch_tapdspxt
from 
  (select 
     event_id, to_char(EVENT_DATE, ''MM-DD-YYYY'') as SCH_TAPRLDT1, 
     to_char(EVENT_DATE, ''DD-MM-YYYY'') as SCH_TAPRLDT2, 
     to_char(EVENT_DATE, ''YYYY-MM-DD'') as SCH_TAPRLDT3, 
     to_char(START_TIME, ''HH24:MI'') as SCH_TAPRLTME, 
     initcap(EVENT_STATUS_DESC) as SCH_TAPSTAT, 
     initcap(EVENT_SUB_TYPE_DESC) as SCH_TAPRSN, 
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
     COMMENT_TEXT as SCH_TAPCOMNT, TO_AGY_LOC_DESC as SCH_TAPDSTNM, 
     initcap(ESCORT_DESC) as SCG_TAPESCT, 
     (select initcap(description)
      from reference_codes rc
      where
        domain = ''TA_TRANSPORT'' and
        code = TRANSPORT_CODE) as SCH_TAPTSP, 
     CONTACT_PERSON_NAME as SCH_TAPDSTCP, TO_ADDRESS_ID
   from V_OFFENDER_ALL_SCHEDULES
   where
     OFFENDER_BOOK_ID = ? and
     EVENT_Type = ''TAP'' and
     RETURN_DATE is not null 
   order by 
     APPLICATION_DATE desc, APPLICATION_TIME desc)A
  left outer join ( select ADDRESS_ID, SUITE_NUMBER as SCH_TAPDSSTN, STREET_INFORMATION as SCH_TAPDSSTR, CITY_NAME as SCH_TAPDSCTY, PROV_STATE_DESC as SCH_TAPDSST, ZIP_POSTAL_CODE as SCH_TAPDSPC, (select initcap(description)
      from reference_codes rc
      where
        domain = ''COUNTRY'' and
        code = COUNTRY_CODE) as SCH_TAPDSCCO from v_addresses)B on
    A.To_ADDRESS_ID = B.ADDRESS_ID
  left join ( select STRING_AGG((select initcap(description)
      from reference_codes rc
      where
        domain = ''PHONE_USAGE'' and
        code = PHONE_TYPE), '','' order by OWNER_ID) as SCH_TAPDSPHT, STRING_AGG((select profile_value from system_profiles where profile_code = ''PHONE_PREFIX'')::text||''''||to_char(replace(PHONE_NO,'' ''::text,''''::text)::bigint,(select profile_value from system_profiles where profile_code = ''PHONE'')::text), '','' order by OWNER_ID) as SCH_TAPDSPHN, STRING_AGG(EXT_NO, '','' order by owner_ID) as SCH_TAPDSPXT, OWNER_ID from V_PHONES where OWNER_CLASS = ''ADDR'' group by OWNER_ID)C on
    B.ADDRESS_ID = C.OWNER_ID' where bookmark_name='SCHEDULES';
	
update iwp_bookmarks  set    modify_datetime = current_timestamp
       ,modify_user_id = 'OMS_OWNER' 
       ,sql_text ='select
	OFFENDER_ID_DISPLAY as HDBLK_PJNUM,
	initcap(concat_ws('', '', LAST_NAME, FIRST_NAME, MIDDLE_NAME)) as HDBLK_PFNAME,
	to_char(BIRTH_DATE, ''MM-DD-YYYY'') as HDBLK_PDOB1,
	to_char(BIRTH_DATE, ''DD-MM-YYYY'') as HDBLK_PDOB2,
	to_char(BIRTH_DATE, ''YYYY-MM-DD'') as HDBLK_PDOB3,
	agy_loc_id,
	AGE as HDBLK_PCAGE,
	initcap(SEX) as HDBLK_PSEX,
	BOOKING_NO as HDBLK_BOOKNM,
	case
		when substr(PRISON_LOCATION,
		instr(PRISON_LOCATION,
		'';''),
		1)= '';'' then substr(PRISON_LOCATION,
		1,
		instr(PRISON_LOCATION,
		'';'')-1)
		when agy_loc_type = ''INST'' then PRISON_LOCATION
	end HDBLK_LOCUST,
	case
		when substr(PRISON_LOCATION,
		instr(PRISON_LOCATION,
		'';''),
		1)= '';'' then substr(PRISON_LOCATION,
		instr(PRISON_LOCATION,
		'';'')+ 1)
		when agy_loc_type = ''COMM'' or agy_loc_type is null then PRISON_LOCATION
	end HDBLK_LOCOM ,
	initcap(OFF_SUP_LEVEL) as HDBLK_PSECLV
from
	v_header_block_fn(:USER_ID)
where
	offender_book_id = ?' where bookmark_name='HEADER_BLOCK';	