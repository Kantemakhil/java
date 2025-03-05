update
	iwp_bookmarks
set
	active_flag = 'Y',
	bookmark_type = 'COMP',
	description = 'Community Service',
	sql_text = '
select 
*,
(select TO_CHAR(interval ''1 minute'' * ((ocduproj.conditionLength- ocduproj.CRED) * 60), ''HH24:MI'')) as UPW_REMAIN
from (
select
	orders.*,
	to_char(osc.start_date, ''DD-MM-YYYY'') as UPW_STDAT,
	to_char(osc.expiry_date, ''DD-MM-YYYY'') as UPW_ENDAT,
	osc.length || '' '' ||osc.length_unit as UPW_LENG,
	(select description from reference_codes rc where code = ''NCUS''and "domain"  = ''CATEGORY'') as UPW_CAT,
	(select TO_CHAR(interval ''1 minute'' * ((select SUM(coalesce(CREDIT_WORK_HOURS, 0)) CREDIT_WORK_HOURS from ( select SUM(coalesce(CREDIT_WORK_HOURS, 0)) CREDIT_WORK_HOURS from OFFENDER_PROGRAM_PROFILES where OFFENDER_BOOK_ID = osc.offender_book_id and OFFENDER_SENT_CONDITION_ID = osc.offender_sent_condition_id and SENTENCE_SEQ = osc.sentence_seq union all select SUM(coalesce(case ADJUSTMENT_TYPE when ''C'' then ADJUSTMENT_MINUTES::DECIMAL / 60 when ''D'' then -1 * ADJUSTMENT_MINUTES::DECIMAL / 60 else 0 end, 0)) CREDIT_WORK_HOURS from OFFENDER_UNPAID_WORK_ADJ where OFFENDER_BOOK_ID = osc.offender_book_id and SENTENCE_SEQ = osc.sentence_seq and OFFENDER_SENT_CONDITION_ID  = osc.offender_sent_condition_id )S) * 60), ''HH24:MI'')) as UPW_CRED,
	(select SUM(coalesce(CREDIT_WORK_HOURS, 0)) CREDIT_WORK_HOURS from ( select SUM(coalesce(CREDIT_WORK_HOURS, 0)) CREDIT_WORK_HOURS from OFFENDER_PROGRAM_PROFILES where OFFENDER_BOOK_ID = osc.offender_book_id and OFFENDER_SENT_CONDITION_ID = osc.offender_sent_condition_id and SENTENCE_SEQ = osc.sentence_seq union all select SUM(coalesce(case ADJUSTMENT_TYPE when ''C'' then ADJUSTMENT_MINUTES::DECIMAL / 60 when ''D'' then -1 * ADJUSTMENT_MINUTES::DECIMAL / 60 else 0 end, 0)) CREDIT_WORK_HOURS from OFFENDER_UNPAID_WORK_ADJ where OFFENDER_BOOK_ID = osc.offender_book_id and SENTENCE_SEQ = osc.sentence_seq and OFFENDER_SENT_CONDITION_ID  = osc.offender_sent_condition_id )S) as CRED,
	osc.offender_sent_condition_id ,
	(select description  from legal_update_reasons where update_reason_code = osc.condition_status) as UPW_STAT,
	osc.length as conditionLength
from
	offender_sent_conditions osc,
	(select
	ncus.displayno as UPW_LINE,
	ncus.orderno,
	ncus.matter as UPW_CASNO,
	(select description from agency_locations al where agy_loc_id = ncus.court) as UPW_COURT,
	(select	description from SENTENCE_CALC_TYPES where sentence_calc_type = ncus.type and sentence_category = ''NCUS'')as UPW_LEGOR 
from
	(
	select
		*
	from
		(
		select
			jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
			jsonb_array_elements(legal_summary::jsonb)->>''orderNo'' as orderno,
			jsonb_array_elements(legal_summary::jsonb)->>''matter'' as "matter",
			jsonb_array_elements(legal_summary::jsonb)->>''court'' as "court",
			jsonb_array_elements(legal_summary::jsonb)->>''sentenceCalcType'' as "type",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceType'' as "commencetype"
		from
			(
			select
				offender_book_id,
				orderType,
				form_info_json,
				items ->> 0 as Legal_Summary
			from
				(
				select
					form_identifier::jsonb->> ''offenderBookId'' as offender_book_id,
					form_identifier::jsonb->> ''orderType'' as orderType,
					form_info_json,
					jsonb_path_query_array( encode(form_info_json,
					''escape'')::jsonb,
					''$.*'') as items
				from
					ocdleglo_data) as t
			where
				offender_book_id = ?
				and orderType = ''NONCUST'')A)A)ncus) orders
				where orders.orderno  = to_char(osc.sentence_seq) and 
		osc.offender_book_id = ?) ocduproj where offender_sent_condition_id = ?',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'COMM_UPW';

update
	iwp_bookmarks
set
	active_flag = 'Y',
	bookmark_type = 'COMP',
	description = 'Project Assignments',
	sql_text = 'select 
	(select code from course_activities where crs_acty_id = a.crs_acty_id and active_flag = ''Y'') as UPW_PROJCOD,
	(select description  from course_activities where crs_acty_id = a.crs_acty_id and active_flag = ''Y'') as UPW_PROJDESC,
	(select tm.description from automation_teams tm where tm.team_id=(select provider_party_id from course_activities where crs_acty_id = a.crs_acty_id and active_flag = ''Y'')) as UPW_PROJTEAM,
	(select capacity from course_activities where crs_acty_id = a.crs_acty_id and active_flag = ''Y'') as UPW_PROJMAXCAP, 
	to_char(a.offender_start_date, ''DD-MM-YYYY'') as UPW_PROJSTDAT,
	a.agreed_travel_fare as UPW_PROJTRVFAR,
	(select TO_CHAR(interval ''1 minute'' * (a.agreed_travel_hour * 60), ''HH24:MI''))  as UPW_PROJTRVTI,
	to_char(a.offender_end_date, ''DD-MM-YYYY'') as UPW_PROJENDAT,
	(select description  from reference_codes rc where domain = ''OFF_PRG_STS'' and code = a.offender_program_status) as UPW_PROJSTAT from
	 (SELECT * , (
	select
		count(*)
	from
		INTERNET_ADDRESSES
	where
		owner_id = (
		select
			ROOT_OFFENDER_ID
		from
			v_header_block
		where
			offender_book_id = ?)) email_address_count,
	(
	select
		count(*)
	from
		PHONES
	where
		owner_id = (
		select
			ROOT_OFFENDER_ID
		from
			v_header_block
		where
			offender_book_id = ?) and phone_type =''MOB'' ) phone_number_count
FROM
    offender_program_profiles
WHERE
    coalesce(offender_prg_obligation_id::text, '''') = ''''
    AND offender_book_id = ?
     AND OFFENDER_SENT_CONDITION_ID = ?) a',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'UPW_ASSIGN';

update
	iwp_bookmarks
set
	active_flag = 'Y',
	bookmark_type = 'COMP',
	description = 'Credit',
	sql_text = 'select 
     to_char(a.adjustment_date, ''DD-MM-YYYY'') as UPW_CRDDAT,
     TO_CHAR((a.adjustment_minutes || '' minutes'')::interval, ''HH24:MI'') as UPW_CRDADJ,
     case when  a.adjustment_type = ''C'' then ''Credit'' else ''Debit'' end UPW_CRDDBCR,
     (select description  from reference_codes rc where domain = ''PS_ADJ_REA'' and code = a.reason_code ) as UPW_CRDREA,
     a.comment_text as UPW_CRDCOM,
     (select last_name || '',''|| first_name from staff_members sm where user_id = a.create_user_id) as UPW_CRDCREA from
     (SELECT *
FROM OFFENDER_UNPAID_WORK_ADJ
where
  OFFENDER_BOOK_ID=? and
  offender_sent_condition_id =?
order by ADJUSTMENT_DATE desc) a',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'UPW_CREDIT';

update
	iwp_bookmarks
set
	sql_text = 'select
		to_char(EVENT_DATE, ''MM-DD-YYYY'') as SCH_TARELD1,
		to_char(EVENT_DATE, ''DD-MM-YYYY'') as SCH_TARELD2,
		to_char(EVENT_DATE, ''YYYY-MM-DD'') as SCH_TARELD3,
		to_char(START_TIME, ''HH24:MI'') as SCH_TARELT,
		 (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''MOVE_TYPE'' AND code = EVENT_TYPE) as  SCH_TASEVENT,
		initcap(EVENT_STATUS_DESC) as SCH_TASTAT,
		(select  DESCRIPTION from movement_reasons mr where movement_type=EVENT_TYPE and movement_reason_code=EVENT_SUB_TYPE) as SCH_TAREAS,
		to_char(APPLICATION_DATE, ''MM-DD-YYYY'') as SCH_TAAPDT1,
		to_char(APPLICATION_DATE, ''DD-MM-YYYY'') as SCH_TAAPDT2,
		to_char(APPLICATION_DATE, ''YYYY-MM-DD'') as SCH_TAAPDT3,
		to_char(APPLICATION_TIME, ''HH24:MI'') as SCH_TAAPTM,
		to_char(RETURN_DATE, ''MM-DD-YYYY'') as SCH_TARETD1,
		to_char(RETURN_DATE, ''DD-MM-YYYY'') as SCH_TARETD2,
		to_char(RETURN_DATE, ''YYYY-MM-DD'') as SCH_TARETD3,
		to_char(RETURN_TIME, ''HH24:MI'') as SCH_TARETT,
		RETURN_DATE::date-EVENT_DATE::date as SCH_TADAYO,
		CASE 
    WHEN RETURN_TIME IS NOT NULL 
      THEN TO_CHAR(INTERVAL ''1 minute'' * (EXTRACT(EPOCH FROM (RETURN_TIME - START_TIME)) / 3600 * 60), ''HH24:MI'') 
    ELSE ''0'' 
    END AS SCH_TAHRO,
		COMMENT_TEXT as SCH_TACOM,
		
		initcap(ESCORT_DESC) as SCH_TAESC,
		(
		select
			initcap(description)
		from
			reference_codes rc
		where
			domain = ''TA_TRANSPORT''
			and
        code = TRANSPORT_CODE) as SCH_TATRANS
	from
		V_OFFENDER_ALL_SCHEDULES voas
	where
		EVENT_ID =?
	',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'SCHEDULES';

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
	'SCH_TEMP_AGY',
	'Y',
	'COMP',
	'Schedule Temp Absence Agency Location',
	'SELECT
    A.*,
    C.SCH_TAAGPT,
    C.SCH_TAAGPN,
    C.SCH_TAAGEX,
    C.SCH_TAAGPF 
FROM
    (
        SELECT
            (
                SELECT
                    al.description
                FROM
                    v_agency_addresses vaa,
                    agency_locations al
                WHERE
                    vaa.address_id = va.to_address_id
                    AND vaa.agy_loc_id = al.agy_loc_id
            ) AS SCH_TAAGLOC,
            va.contact_person_name as SCH_TAAGCPER,
            suite_number as SCH_TAAGSUI,
            address_line1 as SCH_TAAGSTR,
            COALESCE(
                (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''CITY'' AND code = a.city_code),
                a.city_code
            ) AS SCH_TAAGCIT,
            COALESCE(
                (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''PROV_STATE'' AND code = a.prov_state_code),
                a.prov_state_code
            ) AS SCH_TAAGSTA,
            COALESCE(
                (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNTRY'' AND code = a.country_code),
                a.country_code
            ) AS SCH_TAAGCOU,
            zip_postal_code as SCH_TAAGPOST,
            va.to_address_id
        FROM
            addresses a,
            v_offender_all_schedules va
        WHERE
            address_id = va.to_address_id
            AND a.owner_class = ''AGY''
            AND event_id =?
    ) A
LEFT JOIN
    (
        SELECT
            STRING_AGG(
                (
                    SELECT INITCAP(description)
                    FROM reference_codes rc
                    WHERE domain = ''PHONE_USAGE'' AND code = PHONE_TYPE
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TAAGPT,
            STRING_AGG(
                TO_CHAR(
                    REPLACE(PHONE_NO, '' ''::TEXT, ''''::TEXT)::BIGINT,
                    (
                        SELECT maskformat
                        FROM (
                            SELECT
                                jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskFormat'' AS maskformat,
                                jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' AS maskingcode
                            FROM
                                system_settings
                            WHERE
                                setting_type = ''PHONEFORMAT''
                        ) a
                        WHERE maskingCode = format
                    )::TEXT
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TAAGPN,
            STRING_AGG(EXT_NO, '','' ORDER BY OWNER_ID) AS SCH_TAAGEX,
            STRING_AGG(
                (
                    SELECT maskingDescription
                    FROM (
                        SELECT
                            jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingDescription'' AS maskingDescription,
                            jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' AS maskingCode
                        FROM
                            system_settings
                        WHERE
                            setting_type = ''PHONEFORMAT''
                    ) a
                    WHERE maskingCode = format
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TAAGPF,
            OWNER_ID
        FROM
            V_PHONES
        WHERE
            OWNER_CLASS = ''ADDR''
        GROUP BY
            OWNER_ID
    ) C ON A.to_address_id = C.OWNER_ID',
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
		bookmark_name = 'SCH_TEMP_AGY');

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
	'SCH_TEM_BUSI',
	'Y',
	'COMP',
	'Schedule Temap Absence Business Location',
	'SELECT
    A.*,
    C.SCH_TABLPT,
    C.SCH_TABLPN,
    C.SCH_TABLEX,
    C.SCH_TABLPF 
FROM
   ( select
     c.corporate_name AS SCH_TABLOC,
	va.contact_person_name as SCH_TABLCP,
	suite_number as SCH_TABLSUI,
	address_line1 as SCH_TABLSTR,
	COALESCE((SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''CITY'' AND code = a.city_code), a.city_code ) AS SCH_TABLCIT,
	COALESCE((SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''PROV_STATE'' AND code = a.prov_state_code), a.prov_state_code ) AS SCH_TABLSTA,
	COALESCE((SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNTRY'' AND code = a.country_code), a.country_code ) AS SCH_TABLCOU,
	zip_postal_code	as SCH_TABLPOST,
	va.to_address_id
from
	addresses a,
	corporates c,
	v_offender_all_schedules va
where
    c.corporate_id =a.owner_id and 
   address_id = va.to_address_id  and a.owner_class =''CORP'' and 
  event_id =?)A
  LEFT JOIN
    (
        SELECT
            STRING_AGG(
                (
                    SELECT INITCAP(description)
                    FROM reference_codes rc
                    WHERE domain = ''PHONE_USAGE'' AND code = PHONE_TYPE
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TABLPT,
            STRING_AGG(
                TO_CHAR(
                    REPLACE(PHONE_NO, '' ''::TEXT, ''''::TEXT)::BIGINT,
                    (
                        SELECT maskformat
                        FROM (
                            SELECT
                                jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskFormat'' AS maskformat,
                                jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' AS maskingcode
                            FROM
                                system_settings
                            WHERE
                                setting_type = ''PHONEFORMAT''
                        ) a
                        WHERE maskingCode = format
                    )::TEXT
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TABLPN,
            STRING_AGG(EXT_NO, '','' ORDER BY OWNER_ID) AS SCH_TABLEX,
            STRING_AGG(
                (
                    SELECT maskingDescription
                    FROM (
                        SELECT
                            jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingDescription'' AS maskingDescription,
                            jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' AS maskingCode
                        FROM
                            system_settings
                        WHERE
                            setting_type = ''PHONEFORMAT''
                    ) a
                    WHERE maskingCode = format
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TABLPF,
            OWNER_ID
        FROM
            V_PHONES
        WHERE
            OWNER_CLASS = ''ADDR''
        GROUP BY
            OWNER_ID
    ) C ON A.to_address_id = C.OWNER_ID',
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
		bookmark_name = 'SCH_TEM_BUSI');

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
	'SCH_TEMP_OTH',
	'Y',
	'COMP',
	'Schedule Temp Absence for Other Location',
	'SELECT
    A.*,
    C.SCH_TAOLPT,
    C.SCH_TAOLPN,
    C.SCH_TAOLEX,
    C.SCH_TAOLPF 
FROM
    (
        SELECT
            (SUBSTR(va.offender_last_name || '', '' || va.offender_first_name ,1 ,100) ) AS SCH_TAOLOC,
            va.contact_person_name as SCH_TAOLCP,
            suite_number as SCH_TAOLSUI,
            address_line1 as SCH_TAOLSTR,
            COALESCE(
                (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''CITY'' AND code = a.city_code),
                a.city_code
            ) AS SCH_TAOLCIT,
            COALESCE(
                (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''PROV_STATE'' AND code = a.prov_state_code),
                a.prov_state_code
            ) AS SCH_TAOLSTA,
            COALESCE(
                (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNTRY'' AND code = a.country_code),
                a.country_code
            ) AS SCH_TAOLCOU,
            zip_postal_code as SCH_TAOLPOST,
            va.to_address_id
        FROM
            addresses a,
            v_offender_all_schedules va,
			 address_usages au
        WHERE
            a.address_id = va.to_address_id and
			 au.address_id = va.to_address_id and 
            au.address_usage =''TAP''
            AND event_id=?
    ) A
LEFT JOIN
    (
        SELECT
            STRING_AGG(
                (
                    SELECT INITCAP(description)
                    FROM reference_codes rc
                    WHERE domain = ''PHONE_USAGE'' AND code = PHONE_TYPE
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TAOLPT,
         
            STRING_AGG(
                TO_CHAR(
                  REPLACE(PHONE_NO, '' ''::TEXT, ''''::TEXT)::BIGINT,
                    (
                        SELECT maskformat
                        FROM (
                            SELECT
                                jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskFormat'' AS maskformat,
                                jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' AS maskingcode
                            FROM
                                system_settings
                            WHERE
                                setting_type = ''PHONEFORMAT''
                        ) a
                        WHERE maskingCode = format
                    )::TEXT
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TAOLPN,
            STRING_AGG(EXT_NO, '','' ORDER BY OWNER_ID) AS SCH_TAOLEX,
            STRING_AGG(
                (
                    SELECT maskingDescription
                    FROM (
                        SELECT
                            jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingDescription'' AS maskingDescription,
                            jsonb_array_elements(encode(setting_value, ''escape'')::JSONB) ->> ''maskingCode'' AS maskingCode
                        FROM
                            system_settings
                        WHERE
                            setting_type = ''PHONEFORMAT''
                    ) a
                    WHERE maskingCode = format
                ),
                '','' ORDER BY OWNER_ID
            ) AS SCH_TAOLPF,
            OWNER_ID
        FROM
            V_PHONES
        WHERE
            OWNER_CLASS = ''ADDR''
        GROUP BY
            OWNER_ID
    ) C ON A.to_address_id = C.OWNER_ID',
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
		bookmark_name = 'SCH_TEMP_OTH');

update
	iwp_bookmarks
set
	active_flag = 'Y',
	bookmark_type = 'COMP',
	description = 'Offenses in Custody  Charges',
	sql_text = 'SELECT
  offenc.oic_offence_code as  OIC_INCCHRG,
    offenc.description as OIC_INCCHDS,
    oms_miscellaneous_getdesccode(''OIC_OFN_TYPE'', offenc.oic_offence_type)  as OIC_INCCHTYP,
    (select description from reference_codes rc where "domain" =''OIC_OFN_CAT'' and code =offenc.oic_offence_category) as OIC_INCCHCT,
    agc.guilty_evidence_text as OIC_INCCHEV  ,
    (select description from reference_codes rc where "domain" =''CHAR_DISP'' and code =agc.evidence_dispose_text) as OIC_INCDISPO
FROM
    agency_incident_charges agc,OIC_OFFENCES offenc
where
    agc.charged_oic_offence_id =offenc.oic_offence_id and
    AGENCY_INCIDENT_ID = ?
    AND CHARGE_SEQ = ?
ORDER BY
    charge_seq',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_IN_CHARG';

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
	'SAN_VIO_ORDR',
	'Y',
	'COMP',
	'Sanction and Violation COMM Orders',
	'select
	ncus.orderno as CRT_NO,
	ncus.matter as CRT_MAT,
	(select	description from SENTENCE_CALC_TYPES where sentence_calc_type = ncus.type and sentence_category = ''NCUS'')as CRT_TYPE,
	to_char(nullif(ncus.commencedate,'''')::timestamp,''DD-MM-YYYY'') as CRT_COM_DATE,
 case when (ncus.expirydate ='''' or ncus.expirydate =''Indefinite'') then ncus.expirydate else 
to_char(nullif(ncus.expirydate,'''')::timestamp,''DD-MM-YYYY'') end as CRT_EXP_DATE,
  ncus.sanctioncount as CRT_SANC_COUNT,
  ncus.adjournedcount as CRT_ADSANC
	 
from
	(
	select
		*
	from
		(
		select
			jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
			jsonb_array_elements(legal_summary::jsonb)->>''orderNo'' as orderno,
			jsonb_array_elements(legal_summary::jsonb)->>''sentenceCalcType'' as "type",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceType'' as "commencetype",
			jsonb_array_elements(legal_summary::jsonb)->>''matter'' as "matter",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceDate'' as "commencedate",
			jsonb_array_elements(legal_summary::jsonb)->>''expiryDate'' as "expirydate",
			jsonb_array_elements(legal_summary::jsonb)->>''sanctionCount'' as "sanctioncount",
			jsonb_array_elements(legal_summary::jsonb)->>''adjournedCount'' as "adjournedcount"
			
					
		from
			(
			select
				offenderBookId,
				orderType,
				form_info_json,
				items ->> 0 as Legal_Summary
			from
				(
				select
					form_identifier::jsonb->> ''offenderBookId'' as offenderbookid,
					form_identifier::jsonb->> ''orderType'' as orderType,
					form_info_json,
					jsonb_path_query_array( encode(form_info_json,
					''escape'')::jsonb,
					''$.*'') as items
				from
					ocdleglo_data
) as t
			where
				offenderbookid =?
				and orderType = ''NONCUST'')A)A
	where
		orderno=?)ncus',
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
		bookmark_name = 'SAN_VIO_ORDR');

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
	'SAN_VIO_CRT',
	'Y',
	'COMP',
	'Sanctions and Violations-Court Events',
	'select
(SELECT DESCRIPTION FROM MOVEMENT_REASONS WHERE MOVEMENT_TYPE=''CRT'' and MOVEMENT_REASON_CODE=court_event_type) as CRT_CE_HEARRSN,
	to_char(event_date , ''MM-DD-YYYY'') as CRT_CE_DATE,
	to_char(start_time, ''HH24:MI'') as CRT_CE_TIME,
	(SELECT   al.description FROM agency_locations al WHERE agency_location_type = ''CRT'' and  al.agy_loc_id=cve.agy_loc_id  AND al.agy_loc_id NOT IN (''TRN'', ''OUT'') ) as CRT_CE_COURT,
	(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''CRT_APP_TYPE'' AND code = appearance_type)  as CRT_CE_APPTYPE,
	comment_text as CRT_CE_COMMENT,
	recommended_sanction_count as CRT_CE_RECSANCOUNT,
	recommended_reward_count as CRT_CE_RECREWCOU,
	matter as CRT_CE_MATT,
	additional_counts_comment as CRT_CE_ADDCOUCOMM,
	(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''CRT_CAN_RSN'' AND code = outcome_reason_code)  as CRT_CE_CANCELREAS,
	case when event_status = ''CANC'' then ''Y'' else ''N'' end as CRT_CE_CANCEL	
from
	COURT_EVENTS cve
where
	event_id =?',
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
		bookmark_name = 'SAN_VIO_CRT');

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
	'SAN_PROG_OUT',
	'Y',
	'COMP',
	'Sanctions and Violation-Program Outcome',
	'select B.*,
cesd.record_sanction_reward_count as CRT_PO_RECSANCREWCOUNT,
(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNT_TYPE'' AND code = cesd.count_type)  as CRT_PO_COUNTTYPE,

cesd.adjourned_flag as CRT_PO_ADJ,
cesd.link_flag as CRT_PO_LINKCOEV,
case when B.court_event_id is not null  then  to_char((select event_date from court_events ce where event_id=?) , ''MM-DD-YYYY'')  else '''' end as CRT_PO_COEVDATE,
cesd.comment_text as CRT_PO_COMMENT
from (select * from(
select 
va.event_date,
to_char(va.event_date , ''MM-DD-YYYY'') as CRT_PO_DATE,
	to_char(va.start_time, ''HH24:MI'') as CRT_PO_STARTTIME,
	to_char(va.end_time, ''HH24:MI'') as CRT_PO_ENDTIME ,
(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''OUTCOMES'' AND code = va.event_outcome) as CRT_PO_ATT,
va.event_id ,
(select court_event_id from court_evnt_sanctions_details where record_event_id = va.event_id  and link_flag = ''Y'') as court_event_id,
(select description from program_services ps where program_id in 
(select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id =opp.offender_prg_obligation_id)
) as CRT_PO_PROG,
(SELECT description FROM COURSE_ACTIVITIES WHERE crs_acty_id in (SELECT parent_crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id = va.crs_acty_id)) as 	CRT_PO_OCCODE
from V_offender_course_attendances va, event_measures em,OFFENDER_PROGRAM_PROFILES opp where 
em.sanctions_flag =''Y'' and
va.event_type = em.event_type and va.event_sub_type = em.event_sub_type 
and va.event_outcome is not null 
and va.event_date::date <= (select event_date from court_events ce where event_id=?)
and va.offender_book_id =? and opp.off_prgref_id  = va.off_prgref_id and opp.profile_class =''CRS'' and opp.offender_program_status != ''CANC'')a
	where  court_event_id is null or court_event_id =? order by event_date)B left join court_evnt_sanctions_details cesd on cesd.record_event_id = B.event_id
	',
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
		bookmark_name = 'SAN_PROG_OUT');

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
	'SAN_APP_OUT',
	'Y',
	'COMP',
	'Sanction and Violation-Appointment Outcome',
	'select B.*,
cesd.record_sanction_reward_count as CRT_APPOUT_RECSANCREWCOUNT,
(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNT_TYPE'' AND code = cesd.count_type)  as CRT_APPOUT_COUNTTYPE,

cesd.adjourned_flag as CRT_APPOUT_ADJ,
cesd.link_flag as CRT_APPOUT_LINKCOEV,
case when B.court_event_id is not null  then to_char((select event_date from court_events ce where event_id=?) , ''MM-DD-YYYY'')  else '''' end as CRT_APPOUT_EVDATE,
cesd.comment_text as CRT_APPOUT_APPCOMM

from (select
	*
from
	(
	select
		(
		select
			court_event_id
		from
			court_evnt_sanctions_details
		where
			record_event_id = va.event_id
			and link_flag = ''Y'') as court_event_id,
			va.event_date ,
			va.event_id,
  to_char(va.event_date , ''MM-DD-YYYY'') as CRT_APPOUT_DATE,
	to_char(va.start_time, ''HH24:MI'') as CRT_APPOUT_STARTTIME,
	to_char(va.end_time, ''HH24:MI'') as CRT_APPOUT_ENDTIME ,
(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''OUTCOMES'' AND code = va.event_outcome) as CRT_APPOUT_OUTCOME,
			va.event_class ,
			(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''EVENTS'' AND code = va.event_type) as CRT_APPOUT_SCHTYPE,
			(select ref_code.description from reference_codes ref_code where domain in(''EVENTS'', ''MOVE_RSN'', ''INT_SCH_RSN'') and parent_code = va.event_type and ref_code.code=va.event_sub_type) as CRT_APPOUT_SCHSUBTYPE,
			 	va.to_agy_loc_id as CRT_APPOUT_LOC
			
		
	from
		v_offender_all_schedules_2 va,
		event_measures em
	where
		em.sanctions_flag = ''Y''
		and va.event_type = em.event_type
		and va.event_sub_type = em.event_sub_type
		and va.event_outcome is not null
		and va.event_date::date<= (select event_date from court_events ce where event_id=?)
		and offender_book_id =?
		and va.event_id in(
		select
			event_id
		from
			offender_ind_schedules ois
		where
			offender_book_id =?))a
where
	court_event_id is null
	or court_event_id=?
order by
	event_date)B left join court_evnt_sanctions_details cesd on cesd.record_event_id = B.event_id',
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
		bookmark_name = 'SAN_APP_OUT');

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
	'SAN_PROG_APP',
	'Y',
	'COMP',
	'Sanctions and Violations-Program Appointment',
	'select B.*,
cesd.record_sanction_reward_count as CRT_PA_RECSANCREWCOUNT,
(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNT_TYPE'' AND code = cesd.count_type)  as CRT_PA_COUNTTYPE,

cesd.adjourned_flag as CRT_PA_ADJ,
cesd.link_flag as CRT_PA_LINKCOEV,
case when B.court_event_id is not null  then  to_char((select event_date from court_events ce where event_id=?) , ''MM-DD-YYYY'') else '''' end as CRT_PA_COEVDATE,
cesd.comment_text as CRT_PA_COMMENT

from (select 
a.programDesc as CRT_PA_TYPE,
to_char(a.event_date , ''MM-DD-YYYY'') as CRT_PA_DATE,
	to_char(a.start_time, ''HH24:MI'') as CRT_PA_STARTTIME,
	to_char(a.end_time, ''HH24:MI'') as CRT_PA_ENDTIME ,
a.EVENT_ID,
a.court_event_id,
(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''OUTCOMES'' AND code = a.event_outcome) as CRT_PA_ATT

from(
select 
(select court_event_id from court_evnt_sanctions_details where record_event_id = va.event_id  and link_flag = ''Y'') as court_event_id,
va.* ,
 TAG_REFERENCE_CODES_GETDESCCODE ( ''EVENTS'' ,va.event_sub_type )  programDesc
from V_offender_course_attendances va, event_measures em where 
va.event_outcome <> '''' and em.sanctions_flag =''Y'' and
va.event_type = em.event_type and va.event_sub_type = em.event_sub_type 
and va.event_date::date <= (select event_date from court_events ce where event_id=?)
and va.EVENT_ID IN(select EVENT_ID FROM OFFENDER_COURSE_ATTENDANCES off_crs_att
where OFFENDER_PRG_OBLIGATION_ID in (SELECT OFFENDER_PRG_OBLIGATION_ID FROM V_OFFENDER_PRG_OBLIGATIONS VOPO WHERE OFFENDER_BOOK_ID =? AND EVENT_TYPE = ''ACP'')
	and CRS_ACTY_ID is null))a where  court_event_id is null or court_event_id =? order by event_date)B left join court_evnt_sanctions_details cesd on cesd.record_event_id = B.event_id
',
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
		bookmark_name = 'SAN_PROG_APP');

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
	'SAN_COMM_SER',
	'Y',
	'COMP',
	'Sanction and Violations-Community Service',
	'select B.*,
cesd.record_sanction_reward_count as CRT_CS_RECSANCREWCOUNT,
(SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''COUNT_TYPE'' AND code = cesd.count_type)  as CRT_CS_COUNTTYPE,

cesd.adjourned_flag as CRT_CS_ADJ,
cesd.link_flag as CRT_CS_LINKCOEV,
case when B.court_event_id is not null  then to_char((select event_date from court_events  where event_id=?) , ''MM-DD-YYYY'')  else '''' end as CRT_CS_COEVDATE,
cesd.comment_text as CRT_CS_COMMENT

from (select * from (
select 
(select court_event_id from court_evnt_sanctions_details where record_event_id = va.event_id  and link_flag = ''Y'') as court_event_id,
va.event_id ,
va.description as CRT_CS_PROJDESC ,
va.event_date,
to_char(va.event_date , ''MM-DD-YYYY'') as CRT_CS_DATE,
	to_char(va.start_time, ''HH24:MI'') as CRT_CS_STARTTIME,
	to_char(va.end_time, ''HH24:MI'') as CRT_CS_ENDTIME ,
	 
	 (SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE domain = ''OUTCOMES'' AND code = va.event_outcome) as  CRT_CS_ATT
	
from
	V_OFFENDER_COURSE_EVENTS va
where
	OFFENDER_BOOK_ID =? and va.event_outcome is not null and va.event_date::date <= (select event_date from court_events where event_id=?)
	and OFF_PRGREF_ID in( SELECT off_prgref_id
FROM  offender_program_profiles opp, offender_sent_conditions osc
where opp.offender_book_id = va.offender_book_id AND opp.sentence_seq = osc.sentence_seq AND opp.OFFENDER_SENT_CONDITION_ID = osc.offender_sent_condition_id 
 and opp.offender_book_id  = osc.offender_book_id and osc.comm_condition_code IN(SELECT comm_condition_code FROM COMMUNITY_CONDITIONS WHERE program_method  = ''UW'')
     ) AND EVENT_STATUS IN (''SCH'',''EXP'',''COMP'',''CANC'')) a where  court_event_id is null or court_event_id =? order by event_date)B left join court_evnt_sanctions_details cesd on cesd.record_event_id = B.event_id
	',
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
		bookmark_name = 'SAN_COMM_SER');
