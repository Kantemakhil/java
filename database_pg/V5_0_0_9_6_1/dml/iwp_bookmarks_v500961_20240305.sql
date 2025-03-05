insert
	into
	iwp_bookmarks
(bookmark_name,
	active_flag,
	bookmark_type,
	description,
	sql_text,
	date_created,
	user_created,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	expiry_date,
	sql_verified_flag,
	seal_flag)
select
	'ORD_CRT_ACT',
	'Y',
	'COMP',
	'Court action Data',
	'select
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''PROCEEDING''
		and code = proceeding_type) as LEG_ACPROC,
	to_char(start_date, ''MM-DD-YYYY'') as LEG_ACSDAT,
	(
	select
		description
	from
		agency_locations
	where
		agency_location_type = ''CRT''
		and agy_loc_id = proceeding_agy_loc_id) as LEG_ACHBOD,
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''CRT_ACT_PUR''
		and code = proceeding_pursuant_act) as LEG_CAPURTO ,
		
		(select
			description
		from
			automation_teams at2
		where
			team_id = opc.team_id) as LEG_ACRESP,
		
	case when to_char(team_id) is null then (select
			SM.LAST_NAME || '' , '' || SM.FIRST_NAME
		from
			staff_members SM
		where
			staff_id = opc.staff_responsible) else (select
			SM.LAST_NAME || '' , '' || SM.FIRST_NAME
		from
			team_staff_members tsm ,
			staff_members SM
		where
			tsm.staff_id = SM.staff_id
			and tsm.team_id = opc.team_id
			and team_member_id = opc.staff_responsible) end as LEG_ACSRES,
		
		comment_text as LEG_ACCOMM,
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''PROCEED_STS''
		and code = proceeding_status) as LEG_ACSTAT,
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''CRT_ACT_REC''
		and code = crt_action_recommendation) as LEG_CARECDOC,		

to_char(outcome_date, ''MM-DD-YYYY'') as LEG_ACODATE
	from
	offender_proceedings opc
where
	offender_proceeding_id=?',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'N',
	null
where
	not exists (
	select
		1
	from
		iwp_bookmarks
	where
		bookmark_name = 'ORD_CRT_ACT');

update
	iwp_bookmarks
set
	sql_text = 'SELECT A.*, C.SCH_TAAGPT, C.SCH_TAAGPN, C.SCH_TAAGEX, C.SCH_TAAGPF FROM ( SELECT ( SELECT al.description FROM v_agency_addresses vaa, agency_locations al WHERE vaa.address_id = va.to_address_id AND vaa.agy_loc_id = al.agy_loc_id ) AS SCH_TAAGLOC, va.contact_person_name as SCH_TAAGCPER, suite_number as SCH_TAAGSUI, address_line1 as SCH_TAAGSTR, COALESCE( (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''CITY'' AND code = a.city_code), a.city_code ) AS SCH_TAAGCIT, COALESCE( (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''PROV_STATE'' AND code = a.prov_state_code), a.prov_state_code ) AS SCH_TAAGSTA, COALESCE( (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNTRY'' AND code = a.country_code), a.country_code ) AS SCH_TAAGCOU, zip_postal_code as SCH_TAAGPOST, va.to_address_id FROM addresses a, v_offender_all_schedules va WHERE address_id = va.to_address_id AND a.owner_class = ''AGY'' AND event_id=? ) A LEFT JOIN ( select string_agg(SCH_TABLPT, '','' order by owner_id) as SCH_TAAGPT , string_agg(SCH_TABLEX, '','' order by owner_id) as SCH_TAAGEX, string_agg(SCH_TABLPF, '','' order by owner_id)as SCH_TAAGPF, string_agg(case when format = ''UNF'' then phone_no::text else to_char( replace(PHONE_NO, '' ''::text, ''''::text)::BIGINT, ( select maskformat from ( select jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskFormat'' as maskformat, jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' as maskingcode from system_settings where setting_type = ''PHONEFORMAT'' ) a where maskingCode = format )::text ) end, '','' order by owner_id) as SCH_TAAGPN, owner_id from ( select ( select INITCAP(description) from reference_codes rc where domain = ''PHONE_USAGE'' and code = PHONE_TYPE ) as SCH_TABLPT, PHONE_NO , EXT_NO as SCH_TABLEX, ( select maskingDescription from ( select jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingDescription'' as maskingDescription, jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' as maskingCode from system_settings where setting_type = ''PHONEFORMAT'' ) a where maskingCode = format ) as SCH_TABLPF, OWNER_ID, format from V_PHONES where OWNER_CLASS = ''ADDR'')A group by OWNER_ID) C ON A.to_address_id = C.OWNER_ID',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'SCH_TEMP_AGY';

update
	iwp_bookmarks
set
	sql_text = 'select A.*, C.SCH_TABLPT, C.SCH_TABLPN, C.SCH_TABLEX, C.SCH_TABLPF from ( select c.corporate_name as SCH_TABLOC, va.contact_person_name as SCH_TABLCP, suite_number as SCH_TABLSUI, address_line1 as SCH_TABLSTR, coalesce(( select REF_CODE.DESCRIPTION from REFERENCE_CODES REF_CODE where domain = ''CITY'' and code = a.city_code), a.city_code ) as SCH_TABLCIT, coalesce(( select REF_CODE.DESCRIPTION from REFERENCE_CODES REF_CODE where domain = ''PROV_STATE'' and code = a.prov_state_code), a.prov_state_code ) as SCH_TABLSTA, coalesce(( select REF_CODE.DESCRIPTION from REFERENCE_CODES REF_CODE where domain = ''COUNTRY'' and code = a.country_code), a.country_code ) as SCH_TABLCOU, zip_postal_code as SCH_TABLPOST, va.to_address_id from addresses a, corporates c, v_offender_all_schedules va where c.corporate_id = a.owner_id and address_id = va.to_address_id and a.owner_class = ''CORP'' and event_id =?)A left join ( select string_agg(SCH_TABLPT, '','' order by owner_id) as SCH_TABLPT , string_agg(SCH_TABLEX, '','' order by owner_id) as SCH_TABLEX, string_agg(SCH_TABLPF, '','' order by owner_id)as SCH_TABLPF, string_agg(case when format = ''UNF'' then phone_no::text else to_char( replace(PHONE_NO, '' ''::text, ''''::text)::BIGINT, ( select maskformat from ( select jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskFormat'' as maskformat, jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' as maskingcode from system_settings where setting_type = ''PHONEFORMAT'' ) a where maskingCode = format )::text ) end, '','' order by owner_id) as SCH_TABLPN, owner_id from ( select ( select INITCAP(description) from reference_codes rc where domain = ''PHONE_USAGE'' and code = PHONE_TYPE ) as SCH_TABLPT, PHONE_NO , EXT_NO as SCH_TABLEX, ( select maskingDescription from ( select jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingDescription'' as maskingDescription, jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' as maskingCode from system_settings where setting_type = ''PHONEFORMAT'' ) a where maskingCode = format ) as SCH_TABLPF, OWNER_ID, format from V_PHONES where OWNER_CLASS = ''ADDR'')A group by OWNER_ID) C on A.to_address_id = C.OWNER_ID',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'SCH_TEM_BUSI';

update
	iwp_bookmarks
set
	sql_text = 'SELECT A.*, C.SCH_TAOLPT, C.SCH_TAOLPN, C.SCH_TAOLEX, C.SCH_TAOLPF FROM ( SELECT (SUBSTR(va.offender_last_name || '', '' || va.offender_first_name ,1 ,100) ) AS SCH_TAOLOC, va.contact_person_name as SCH_TAOLCP, suite_number as SCH_TAOLSUI, address_line1 as SCH_TAOLSTR, COALESCE( (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''CITY'' AND code = a.city_code), a.city_code ) AS SCH_TAOLCIT, COALESCE( (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''PROV_STATE'' AND code = a.prov_state_code), a.prov_state_code ) AS SCH_TAOLSTA, COALESCE( (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNTRY'' AND code = a.country_code), a.country_code ) AS SCH_TAOLCOU, zip_postal_code as SCH_TAOLPOST, va.to_address_id FROM addresses a, v_offender_all_schedules va, address_usages au WHERE a.address_id = va.to_address_id and au.address_id = va.to_address_id and au.address_usage =''TAP'' AND event_id=? ) A LEFT JOIN ( select string_agg(SCH_TABLPT, '','' order by owner_id) as SCH_TAOLPT , string_agg(SCH_TABLEX, '','' order by owner_id) as SCH_TAOLEX, string_agg(SCH_TABLPF, '','' order by owner_id)as SCH_TAOLPF, string_agg(case when format = ''UNF'' then phone_no::text else to_char( replace(PHONE_NO, '' ''::text, ''''::text)::BIGINT, ( select maskformat from ( select jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskFormat'' as maskformat, jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' as maskingcode from system_settings where setting_type = ''PHONEFORMAT'' ) a where maskingCode = format )::text ) end, '','' order by owner_id) as SCH_TAOLPN, owner_id from ( select ( select INITCAP(description) from reference_codes rc where domain = ''PHONE_USAGE'' and code = PHONE_TYPE ) as SCH_TABLPT, PHONE_NO , EXT_NO as SCH_TABLEX, ( select maskingDescription from ( select jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingDescription'' as maskingDescription, jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' as maskingCode from system_settings where setting_type = ''PHONEFORMAT'' ) a where maskingCode = format ) as SCH_TABLPF, OWNER_ID, format from V_PHONES where OWNER_CLASS = ''ADDR'')A group by OWNER_ID) C ON A.to_address_id = C.OWNER_ID',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'SCH_TEMP_OTH';
	
UPDATE iwp_bookmarks
SET sql_text='with CHARGE_DATA as ( select offenc.oic_offence_code as OIC_INCCHRG, offenc.description as OIC_INCCHDS, oms_miscellaneous_getdesccode(''OIC_OFN_TYPE'', offenc.oic_offence_type) as OIC_INCCHTYP, offenc.oic_offence_category as OIC_INCCHCT, agc.guilty_evidence_text as OIC_INCCHEV, AGENCY_INCIDENT_ID, CHARGE_SEQ, ( select description from reference_codes rc where "domain" = ''CHAR_DISP'' and code = agc.evidence_dispose_text ) as OIC_INCDISPO from agency_incident_charges agc, OIC_OFFENCES offenc where agc.charged_oic_offence_id = offenc.oic_offence_id and AGENCY_INCIDENT_ID =? and party_seq =? order by charge_seq ) select CHARGE_DATA.*, B.* from CHARGE_DATA left join ( select B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ, STRING_AGG(external_id, '','' order by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ) as OIC_CHEXID, STRING_AGG(TO_CHAR(contact_datetime, ''MM-DD-YYYY''), '','' order by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ) as OIC_CHEXCD, STRING_AGG(TO_CHAR(contact_datetime, ''HH24:MI''), '','' order by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ) as OIC_CHEXCT, STRING_AGG( ( select description from reference_codes rc where domain = ''EXT_INV_STAT'' and code = ext_inv_status ), '','' order by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ ) as OIC_CHEXSTA, STRING_AGG(ext_inv_comment, '','' order by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ) as OIC_CHEXCOM, STRING_AGG(A.OIC_INCCHRG, '','' order by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ) as OIC_CHEXCHA, STRING_AGG(A.OIC_INCCHDS, '','' order by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ) as OIC_CHEXCHADES from agency_incident_charges_ext_inv_detls B left join CHARGE_DATA A on A.AGENCY_INCIDENT_ID = B.AGENCY_INCIDENT_ID and A.CHARGE_SEQ = B.CHARGE_SEQ group by B.AGENCY_INCIDENT_ID, B.CHARGE_SEQ ) B on CHARGE_DATA.AGENCY_INCIDENT_ID = B.AGENCY_INCIDENT_ID and CHARGE_DATA.CHARGE_SEQ = B.CHARGE_SEQ',  modify_datetime=current_timestamp, modify_user_id='OMS_OWNER'
WHERE bookmark_name='OFF_IN_CHARG';	
