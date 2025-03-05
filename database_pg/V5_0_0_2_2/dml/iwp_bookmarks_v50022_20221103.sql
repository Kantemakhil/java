Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_VALU','Y','COMP','Property VALU composite bookmark','select
 initcap((select description from reference_codes rc where domain = ''PPTY_TYPE'' and rc.code = PROPERTY_TYPE)) as PROP_ITMTYPV,
 initcap(PROPERTY_DESCRIPTION) as PROP_ITMDESV,
 QUANTITY as PROP_ITMQTYV,
 initcap((select description from reference_codes rc where domain = ''PPTY_REC_FRM'' and rc.code = received_from)) as PROP_ITMRECV,
 coalesce(initcap((select description from reference_codes rc where domain = ''PPTY_COLOR'' and rc.code = COLOR)), ''N/A'')as PROP_ITMCLRV,
 initcap((select description from reference_codes rc where domain = ''PPTY_CONDIT'' and rc.code = condition_code)) as PROP_ITMCONV,
 coalesce(initcap(MAKE), ''N/A'') as PROP_ITMMKV,
 coalesce(SERIAL_NO , ''N/A'') as PROP_ITMSNV
from
 OFFENDER_PPTY_ITEMS opi
where
 OFFENDER_BOOK_ID = ?
 and AGY_LOC_ID in (
 select
  CAL.AGY_LOC_ID
 from
  CASELOAD_AGENCY_LOCATIONS CAL
 where
  CAL.AGY_LOC_ID not in ( ''OUT'', ''TRN'' ))
 and property_container_id in (
 select
  off.PROPERTY_CONTAINER_ID
 from
  OFFENDER_PPTY_CONTAINERS off,
  agency_internal_locations ag,
  reference_codes ref
 where
  ref.code = off.CONTAINER_CODE
  and ref.domain = ''PPTY_CNTNR''
  and off.OFFENDER_BOOK_ID = opi.OFFENDER_BOOK_ID
  and off.AGY_LOC_ID not in (''OUT'')
   and off.INTERNAL_LOCATION_ID = ag.INTERNAL_LOCATION_ID
   and off.CONTAINER_CODE = ''VALU''
  order by
   off.PROPERTY_CONTAINER_ID desc)',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);
  
Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_SCRIPTS','Y','COMP','property scripts composite bookmark','select
 initcap((select description from reference_codes rc where domain = ''PPTY_TYPE'' and rc.code = PROPERTY_TYPE)) as PROP_ITMTYPS,
 initcap(PROPERTY_DESCRIPTION) as PROP_ITMDESS,
 QUANTITY as PROP_ITMQTYS,
 initcap((select description from reference_codes rc where domain = ''PPTY_REC_FRM'' and rc.code = received_from)) as PROP_ITMRECS,
 coalesce(initcap((select description from reference_codes rc where domain = ''PPTY_COLOR'' and rc.code = COLOR)), ''N/A'')as PROP_ITMCLRS,
 initcap((select description from reference_codes rc where domain = ''PPTY_CONDIT'' and rc.code = condition_code)) as PROP_ITMCONS,
 coalesce(initcap(MAKE), ''N/A'') as PROP_ITMMKS,
 coalesce(SERIAL_NO , ''N/A'') as PROP_ITMSNS
from
 OFFENDER_PPTY_ITEMS opi
where
 OFFENDER_BOOK_ID =?
 and AGY_LOC_ID in (
 select
  CAL.AGY_LOC_ID
 from
  CASELOAD_AGENCY_LOCATIONS CAL
 where
  CAL.AGY_LOC_ID not in ( ''OUT'', ''TRN'' ))
 and property_container_id in (
 select
  off.PROPERTY_CONTAINER_ID
 from
  OFFENDER_PPTY_CONTAINERS off,
  agency_internal_locations ag,
  reference_codes ref
 where
  ref.code = off.CONTAINER_CODE
  and ref.domain = ''PPTY_CNTNR''
  and off.OFFENDER_BOOK_ID = opi.OFFENDER_BOOK_ID
  and off.AGY_LOC_ID not in (''OUT'')
   and off.INTERNAL_LOCATION_ID = ag.INTERNAL_LOCATION_ID
   and off.CONTAINER_CODE = ''SCRIPTS''
  order by
   off.PROPERTY_CONTAINER_ID desc)',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,null,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_DES','Y','COMP','Property DES composite bookmark','select
 initcap((select description from reference_codes rc where domain = ''PPTY_TYPE'' and rc.code = PROPERTY_TYPE)) as PROP_ITMTYPD,
 initcap(PROPERTY_DESCRIPTION)as PROP_ITMDESD ,
 QUANTITY as PROP_ITMQTYD,
 initcap((select description from reference_codes rc where domain = ''PPTY_REC_FRM'' and rc.code = received_from)) as PROP_ITMRECD,
 coalesce(initcap((select description from reference_codes rc where domain = ''PPTY_COLOR'' and rc.code = COLOR)), ''N/A'')as PROP_ITMCLRD,
 initcap((select description from reference_codes rc where domain = ''PPTY_CONDIT'' and rc.code = condition_code)) as PROP_ITMCOND,
 coalesce(initcap(MAKE), ''N/A'') as PROP_ITMMKD,
 coalesce(SERIAL_NO , ''N/A'') as PROP_ITMSND
from
 OFFENDER_PPTY_ITEMS opi
where
 OFFENDER_BOOK_ID =?
 and AGY_LOC_ID in (
 select
  CAL.AGY_LOC_ID
 from
  CASELOAD_AGENCY_LOCATIONS CAL
 where
  CAL.AGY_LOC_ID not in ( ''OUT'', ''TRN'' ))
 and property_container_id in (
 select
  off.PROPERTY_CONTAINER_ID
 from
  OFFENDER_PPTY_CONTAINERS off,
  agency_internal_locations ag,
  reference_codes ref
 where
  ref.code = off.CONTAINER_CODE
  and ref.domain = ''PPTY_CNTNR''
  and off.OFFENDER_BOOK_ID = opi.OFFENDER_BOOK_ID
  and off.AGY_LOC_ID not in (''OUT'')
   and off.INTERNAL_LOCATION_ID = ag.INTERNAL_LOCATION_ID
   and off.CONTAINER_CODE = ''DES''
  order by
   off.PROPERTY_CONTAINER_ID desc)',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);  
   
Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_CO','Y','COMP','Property confiscated composite bookmark','select
 initcap((select description from reference_codes rc where domain = ''PPTY_TYPE'' and rc.code = PROPERTY_TYPE)) as PROP_ITMTYPC,
 initcap(PROPERTY_DESCRIPTION) as PROP_ITMDESC,
 QUANTITY as PROP_ITMQTYC,
 initcap((select description from reference_codes rc where domain = ''PPTY_REC_FRM'' and rc.code = received_from)) as PROP_ITMRECC,
 coalesce(initcap((select description from reference_codes rc where domain = ''PPTY_COLOR'' and rc.code = COLOR)), ''N/A'')as PROP_ITMCLRC,
 initcap((select description from reference_codes rc where domain = ''PPTY_CONDIT'' and rc.code = condition_code)) as PROP_ITMCONC,
 coalesce(initcap(MAKE), ''N/A'') as PROP_ITMMKC,
 coalesce(SERIAL_NO , ''N/A'') as PROP_ITMSNC
from
 OFFENDER_PPTY_ITEMS opi
where
 OFFENDER_BOOK_ID = ?
 and AGY_LOC_ID in (
 select
  CAL.AGY_LOC_ID
 from
  CASELOAD_AGENCY_LOCATIONS CAL
 where
 CAL.AGY_LOC_ID not in ( ''OUT'', ''TRN'' ))
 and property_container_id in (
 select
  off.PROPERTY_CONTAINER_ID
 from
  OFFENDER_PPTY_CONTAINERS off,
  agency_internal_locations ag,
  reference_codes ref
 where
  ref.code = off.CONTAINER_CODE
  and ref.domain = ''PPTY_CNTNR''
  and off.OFFENDER_BOOK_ID = opi.OFFENDER_BOOK_ID
  and off.AGY_LOC_ID not in (''OUT'')
   and off.INTERNAL_LOCATION_ID = ag.INTERNAL_LOCATION_ID
   and off.CONTAINER_CODE = ''CO''
  order by
   off.PROPERTY_CONTAINER_ID desc)',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_BULK','Y','COMP','property bulk composite bookmark','select 
 initcap((select description from reference_codes rc where domain = ''PPTY_TYPE'' and rc.code = PROPERTY_TYPE)) as PROP_ITMTYPB,
 initcap(PROPERTY_DESCRIPTION)as PROP_ITMDESB ,
 QUANTITY as PROP_ITMQTYB,
 initcap((select description from reference_codes rc where domain = ''PPTY_REC_FRM'' and rc.code = received_from)) as PROP_ITMRECB,
 coalesce(initcap((select description from reference_codes rc where domain = ''PPTY_COLOR'' and rc.code = COLOR)), ''N/A'')as PROP_ITMCLRB,
 initcap((select description from reference_codes rc where domain = ''PPTY_CONDIT'' and rc.code = condition_code)) as PROP_ITMCONB,
 coalesce(initcap(MAKE), ''N/A'') as PROP_ITMMKB,
 coalesce(SERIAL_NO , ''N/A'') as PROP_ITMSNB
from
 OFFENDER_PPTY_ITEMS opi
where
 OFFENDER_BOOK_ID=?
 and AGY_LOC_ID in (
 select
  CAL.AGY_LOC_ID
 from
  CASELOAD_AGENCY_LOCATIONS CAL
 where
  CAL.AGY_LOC_ID not in ( ''OUT'', ''TRN'' ))
 and property_container_id in (
 select
  off.PROPERTY_CONTAINER_ID
 from
  OFFENDER_PPTY_CONTAINERS off,
  agency_internal_locations ag,
  reference_codes ref
 where
  ref.code = off.CONTAINER_CODE
  and ref.domain = ''PPTY_CNTNR''
  and off.OFFENDER_BOOK_ID = opi.OFFENDER_BOOK_ID
  and off.AGY_LOC_ID not in (''OUT'')
   and off.INTERNAL_LOCATION_ID = ag.INTERNAL_LOCATION_ID
   and off.CONTAINER_CODE = ''BULK''
  order by
   off.PROPERTY_CONTAINER_ID desc)',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) 
  values('PROP_REG','Y','COMP','Property Registration composite bookmark','select
 initcap((select description from reference_codes rc where domain = ''PPTY_TYPE'' and rc.code = PROPERTY_TYPE)) as PROP_ITMTYPR,
 initcap(PROPERTY_DESCRIPTION) as PROP_ITMDESR,
 QUANTITY as PROP_ITMQTYR,
 initcap((select description from reference_codes rc where domain = ''PPTY_REC_FRM'' and rc.code = received_from)) as PROP_ITMRECR,
 coalesce(initcap((select description from reference_codes rc where domain = ''PPTY_COLOR'' and rc.code = COLOR)), ''N/A'')as PROP_ITMCLRR,
 initcap((select description from reference_codes rc where domain = ''PPTY_CONDIT'' and rc.code = condition_code)) as PROP_ITMCONR,
 coalesce(initcap(MAKE), ''N/A'') as PROP_ITMMKR,
 coalesce(SERIAL_NO , ''N/A'') as PROP_ITMSNR
from
 OFFENDER_PPTY_ITEMS
where
 OFFENDER_BOOK_ID = ?
 and STATUS_CODE = ''REGISTERED''
 and AGY_LOC_ID in (
 select
  CAL.AGY_LOC_ID
 from
  CASELOAD_AGENCY_LOCATIONS CAL
 where 
  CAL.AGY_LOC_ID not in ( ''OUT'', ''TRN'' ))',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) 
 values('HEADER_BLOCK','Y','COMP','composite bookmark for header block','select
 OFFENDER_ID_DISPLAY as HDBLK_PJNUM,
 initcap(concat_ws('', '', LAST_NAME, FIRST_NAME, MIDDLE_NAME)) as HDBLK_PFNAME,
 to_char(BIRTH_DATE, ''MM-DD-YYYY'') as HDBLK_PDOB1,
 to_char(BIRTH_DATE, ''DD-MM-YYYY'') as HDBLK_PDOB2,
 to_char(BIRTH_DATE, ''YYYY-MM-DD'') as HDBLK_PDOB3,
 AGE as HDBLK_PCAGE,
 initcap(SEX) as HDBLK_PSEX,
 BOOKING_NO as HDBLK_BOOKNM,
 substr(PRISON_LOCATION,1,instr(PRISON_LOCATION,'';'')-1) as HDBLK_LOCUST,
 substr(PRISON_LOCATION,instr(PRISON_LOCATION,'';'')+1) as HDBLK_LOCOM,
 initcap(OFF_SUP_LEVEL) as HDBLK_PSECLV from v_header_block where offender_book_id =?',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) 
 values('SCHEDULES','Y','COMP','composite bookmark for schedules','select 
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
  left outer join ( select AGY_LOC_ID, ADDRESS_ID, SUITE_NUMBER as SCH_TAPDSSTN, STREET_INFORMATION as SCH_TAPDSSTR, CITY_NAME as SCH_TAPDSCTY, PROV_STATE_DESC as SCH_TAPDSST, ZIP_POSTAL_CODE as SCH_TAPDSPC, (select initcap(description)
      from reference_codes rc
      where
        domain = ''COUNTRY'' and
        code = COUNTRY_CODE) as SCH_TAPDSCCO from V_AGENCY_ADDRESSES)B on
    A.To_ADDRESS_ID = B.ADDRESS_ID 
  left join ( select STRING_AGG((select initcap(description)
      from reference_codes rc
      where
        domain = ''PHONE_USAGE'' and
        code = PHONE_TYPE), '','' order by OWNER_ID) as SCH_TAPDSPHT, STRING_AGG(PHONE_NO, '','' order by OWNER_ID) as SCH_TAPDSPHN, STRING_AGG(EXT_NO, '','' order by owner_ID) as SCH_TAPDSPXT, OWNER_ID from V_PHONES where OWNER_CLASS = ''ADDR'' group by OWNER_ID)C on
    B.ADDRESS_ID = C.OWNER_ID',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_REGIMG','Y','BINARY','Property REG image composite bookmark','select
	img.image_thumbnail
from images img, OFFENDER_PPTY_ITEMS OPI
where OPI.offender_book_id = img.image_object_id and OPI.property_item_seq = img.image_object_seq and OPI.offender_book_id =? and opi.status_code = ''REGISTERED''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);
Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_COIMG','Y','BINARY','Property CO image composite bookmark','select
	img.image_thumbnail
from images img, OFFENDER_PPTY_ITEMS OPI, offender_ppty_containers opc
where OPI.offender_book_id = img.image_object_id and OPI.property_item_seq = img.image_object_seq and opi.property_container_id =opc.property_container_id and OPI.offender_book_id =? and opc.container_code = ''CO''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);
Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_VALUIMG','Y','BINARY','property valu image composite bookmark','select
	img.image_thumbnail
from images img, OFFENDER_PPTY_ITEMS OPI, offender_ppty_containers opc
where OPI.offender_book_id = img.image_object_id and OPI.property_item_seq = img.image_object_seq and opi.property_container_id =opc.property_container_id and OPI.offender_book_id =? and opc.container_code = ''VALU''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);
Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_BULKIMG','Y','BINARY','Property Bulk Image composite bookmark','select
	img.image_thumbnail
from images img, OFFENDER_PPTY_ITEMS OPI, offender_ppty_containers opc
where OPI.offender_book_id = img.image_object_id and OPI.property_item_seq = img.image_object_seq and opi.property_container_id =opc.property_container_id and OPI.offender_book_id =? and opc.container_code = ''BULK''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);
Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_DESIMG','Y','BINARY','Property DES image composite bookmark','select
	img.image_thumbnail
from images img, OFFENDER_PPTY_ITEMS OPI, offender_ppty_containers opc
where OPI.offender_book_id = img.image_object_id and OPI.property_item_seq = img.image_object_seq and opi.property_container_id =opc.property_container_id and OPI.offender_book_id =? and opc.container_code = ''DES''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);
Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('PROP_SCRIMG','Y','BINARY','Property Scripts image composite bookmark','select
	img.image_thumbnail
from images img, OFFENDER_PPTY_ITEMS OPI, offender_ppty_containers opc
where OPI.offender_book_id = img.image_object_id and OPI.property_item_seq = img.image_object_seq and opi.property_container_id =opc.property_container_id and OPI.offender_book_id =? and opc.container_code = ''SCRIPT''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL,'N',NULL);

	
	
 