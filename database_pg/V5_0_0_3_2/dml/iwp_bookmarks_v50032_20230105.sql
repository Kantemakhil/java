INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
VALUES('INT_APPOINT', 'Y', 'COMP', 'Composite', 'select
	to_char(EVENT_DATE, ''DD-MM-YYYY'') as DATE1,
	to_char(EVENT_DATE, ''MM-DD-YYYY'') as DATE2,
	to_char(EVENT_DATE, ''YYYY-MM-DD'') as DATE3,
	to_char(START_TIME, ''hh24:mi'') as TIME,
	EVENT_SUB_TYPE_DESC as SCHEDULE_REASON,
	(
	select
		C.DESCRIPTION description
	from
		INT_LOC_USAGE_LOCATIONS A,
		INTERNAL_LOCATION_USAGES B,
		AGENCY_INTERNAL_LOCATIONS C
	where
		A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID
		and B.INTERNAL_LOCATION_USAGE = ''APP''
		and A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID
		and not exists (
		select
			1
		from
			INT_LOC_USAGE_LOCATIONS
		where
			PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID )
		and C.INTERNAL_LOCATION_CODE <> ''RTU''
		and C.INTERNAL_LOCATION_ID = TO_INTERNAL_LOCATION_ID) as location,
	comment_text as comment
from
	V_OFFENDER_ALL_SCHEDULES
where
	OFFENDER_BOOK_ID = ?
	and EVENT_TYPE = ''APP''
	and EVENT_CLASS = ''INT_MOV''
	and EVENT_STATUS = ''SCH''
	and EVENT_SUB_TYPE != ''VIS''
	and BOOKING_ACTIVE_FLAG = ''Y''
order by
	EVENT_DATE desc,
	START_TIME desc',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL);
	
	
INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
VALUES('OFFEN_ADDRES', 'Y', 'COMP', 'Composite', 'select address_type1, address_type2, address_type3, "comment", primary_flag, active, validated, phone_type, phone_number, "extension" from ( select concat_ws('','', SUITE_NUMBER, ADDRESS_LINE1)as Address_type1, case when is_address_valid = ''Y'' then concat_ws('','', city_code, prov_state_code) else concat_ws('','', city_name, prov_state_desc) end as Address_type2, concat_ws('','', country_desc , zip_postal_code) as Address_type3, COMMENT_TEXT as "comment", PRIMARY_FLAG as primary_flag, active_flag as Active, IS_ADDRESS_VALID as validated, address_id from V_ADDRESSES where OWNER_ID = ( select root_offender_id from offender_bookings where offender_book_id = ?) order by PRIMARY_FLAG desc, MAIL_FLAG desc, ACTIVE_FLAG desc, coalesce(START_DATE, ''01-JAN-1900'') desc) A left join ( select STRING_AGG((select description from reference_codes rc where domain = ''PHONE_USAGE'' and code = PHONE_TYPE), '','') as phone_type, STRING_AGG(PHONE_NO, '','')as phone_number, STRING_AGG(EXT_NO, '','')as "extension", owner_id from phones group by owner_id) b on A.address_id = B.owner_id', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL);


INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
VALUES('OFFEN_ALERT', 'Y', 'COMP', 'Composite bookmark', 'select
	(
	select
		description
	from
		reference_codes rc
	where
		code = OID.ALERT_TYPE
		and domain = ''ALERT'') as Alert_Type,
	(
	select
		description
	from
		reference_codes rc
	where
		code = OID.ALERT_CODE
		and domain = ''ALERT_CODE'') as Alert,
	OID.ALERT_STATUS as status,
	to_char(OID.ALERT_DATE,''MM-DD-YYYY'') as Effective_date1,
	to_char(OID.ALERT_DATE,''DD-MM-YYYY'') as Effective_date2,
    to_char(OID.ALERT_DATE,''YYYY-MM-DD'') as Effective_date3,
	OID.EXPIRY_DATE,
	OID.VERIFIED_FLAG as Verified,
	OID.AUTHORIZE_PERSON_TEXT as Authorized_by,
	OID.COMMENT_TEXT as comment
from
	OFFENDER_ALERTS OID
where
	offender_book_id=?',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL);
	
	
INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag)
VALUES('OFFENDER_BOOK_ID', 'S', 'offender_book_id', 'OFFEN_ADDRES', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL);
INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag)
VALUES('OFFENDER_BOOK_ID', 'S', 'OFFENDER_BOOK_ID', 'INT_APPOINT', current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL);
INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag)
VALUES('OFFENDER_BOOK_ID', 'S', 'offender_book_id', 'OFFEN_ALERT', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL);


Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','ADDRESS_TYPE1',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','ADDRESS_TYPE2',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','ADDRESS_TYPE3',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','COMMENT',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','PRIMARY',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','ACTIVE',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','VALIDATED',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','PHONE_TYPE',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','PHONE_NUMBER',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ADDRES','EXTENSION',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('INT_APPOINT','DATE1',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('INT_APPOINT','DATE2',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('INT_APPOINT','DATE3',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('INT_APPOINT','TIME',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('INT_APPOINT','SCHEDULE_REASON',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('INT_APPOINT','LOCATION',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('INT_APPOINT','COMMENT',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','ALERT_TYPE',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','ALERT',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','STATUS',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','EFFECTIVE_DATE1',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','EFFECTIVE_DATE2',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','EFFECTIVE_DATE3',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','EXPIRY_DATE',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','VERIFIED',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','AUTHORIZED_BY',current_timestamp,'OMS_OWNER', NULL,null,null);
Insert into iwp_bookmark_out_parameters(bookmark_name,parameter_name,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) values('OFFEN_ALERT','COMMENT',current_timestamp,'OMS_OWNER', NULL,null,null);	
