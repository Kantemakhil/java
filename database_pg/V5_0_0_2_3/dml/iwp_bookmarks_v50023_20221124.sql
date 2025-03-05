update
	iwp_bookmarks
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,sql_text = 'select OFFENDER_ID_DISPLAY as HDBLK_PJNUM, initcap(concat_ws('', '', LAST_NAME, FIRST_NAME, MIDDLE_NAME)) as HDBLK_PFNAME, to_char(BIRTH_DATE, ''MM-DD-YYYY'') as HDBLK_PDOB1, to_char(BIRTH_DATE, ''DD-MM-YYYY'') as HDBLK_PDOB2, to_char(BIRTH_DATE, ''YYYY-MM-DD'') as HDBLK_PDOB3, AGE as HDBLK_PCAGE, initcap(SEX) as HDBLK_PSEX, BOOKING_NO as HDBLK_BOOKNM, case when substr(PRISON_LOCATION, instr(PRISON_LOCATION, '';''), 1)= '';'' then substr(PRISON_LOCATION, 1, instr(PRISON_LOCATION, '';'')-1) when agy_loc_type = ''INST'' then PRISON_LOCATION end HDBLK_LOCUST, case when substr(PRISON_LOCATION, instr(PRISON_LOCATION, '';''), 1)= '';'' then substr(PRISON_LOCATION, instr(PRISON_LOCATION, '';'')+ 1) when agy_loc_type = ''COMM'' then PRISON_LOCATION end HDBLK_LOCOM , initcap(OFF_SUP_LEVEL) as HDBLK_PSECLV from v_header_block where offender_book_id = ?'
where
	bookmark_name = 'HEADER_BLOCK';