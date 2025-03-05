INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'LEG_CUST', 'Y', 'COMP', 'Custodial Order ', 'select 
cust.displayno as LEG_CUSOFNM,
cust.ordereddate as LEG_CUSODTE,
cust.matter as LEG_CUSCMTN,
cust.court as LEG_CUSCOURT,
(select description from SENTENCE_CALC_TYPES rc where sentence_calc_type =cust.type)as LEG_CUSOTYP ,
 (select description from reference_codes rc where domain=''LO_REL_TYPE'' and code=cust.commencetype)as LEG_CUSCTYP,
cust.relatedto as LEG_CUSLNKT,
cust.commencedate as LEG_CUSCDTE,
cust.terms as LEG_CUSTTYL,
cust.status as LEG_CUSOSTS,
cust.holdexpirydate as LEG_CUSHEDT
from 
(select * from (select
		jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
		jsonb_array_elements(legal_summary::jsonb)->>''orderedDate'' as "ordereddate",
		jsonb_array_elements(legal_summary::jsonb)->>''matter'' as "matter",
		jsonb_array_elements(legal_summary::jsonb)->>''court'' as "court",
		jsonb_array_elements(legal_summary::jsonb)->>''type'' as "type",
		jsonb_array_elements(legal_summary::jsonb)->>''commenceType'' as "commencetype",
		jsonb_array_elements(legal_summary::jsonb)->>''relatedTo'' as "relatedto",
		jsonb_array_elements(legal_summary::jsonb)->>''commenceDate'' as "commencedate",
		jsonb_array_elements(legal_summary::jsonb)->>''termTypeAndLength'' as "terms",
		jsonb_array_elements(legal_summary::jsonb)->>''status'' as "status",
		jsonb_array_elements(legal_summary::jsonb)->>''holdExpiryDate'' as "holdexpirydate"
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
				jsonb_path_query_array( encode(form_info_json, ''escape'')::jsonb,
				''$.*'') as items
			from
				ocdleglo_data
) as t
		where
			offenderbookid=? and orderType =''CUST'')A)A where displayno=?)cust', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'N', NULL 
				WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'LEG_CUST');




INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'LEG_CUS_CHRG', 'Y', 'COMP', 'Legal Custodial Charges', 'select 
chrg.matter as LEG_CUSMTNM,
chrg.description as LEG_CUSCHRG ,
(select description from OFFENCE_RESULT_CODES rc where result_code =chrg.outcome)as LEG_CUSOTCM 
from
(select 
jsonb_array_elements(charges::jsonb)->>''matter'' as "matter",
jsonb_array_elements(charges::jsonb)->>''description'' as "description",
	 jsonb_array_elements(charges::jsonb)->>''outcome'' as "outcome",
	 displayno
	 from (select
	 jsonb_array_elements(legal_summary::jsonb)->>''charges'' as "charges",
	 jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno
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
				jsonb_path_query_array( encode(form_info_json, ''escape'')::jsonb,
				''$.*'') as items
		from
				ocdleglo_data
) as t
	where
			offenderbookid=? and orderType =''CUST'')A)A where displayno=?)chrg',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'N', NULL
			WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'LEG_CUS_CHRG');


INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'LEG_CHRG_DET', 'Y', 'COMP', 'Custodial Charge Details', 'select
(select description from reference_codes rc where domain = ''PLEA_STATUS'' and code = plea)as LEG_CUSPLEA,
incidentdate as LEG_CUSINDT,
range as LEG_CUSRAN,
particulars as LEG_CUSPAR,
chargeid,
offenderbookid
from 
(select
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''plea'' as plea,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''Range'' as range,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''incidentDate'' as incidentdate,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''particulars'' as particulars,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''chargeId'' as chargeid,
	form_identifier::jsonb->>''offenderBookId'' as offenderbookid
from
	ocdchgsu_data
)A where offenderbookid=? and chargeid=?', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL, 'N', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'LEG_CHRG_DET');

INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'LEG_CHRG_IND', 'Y', 'COMP', 'Charge Detail Indicators', 'select
	(
	select
		description
	from
		reference_codes rc
	where
		code = INDICATOR_CODE
		and "domain" = ''OFFENCE_IND'') as LEG_CUSINCD
from
	OFFENCE_INDICATORS
where
	OFFENCE_ID=?',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'LEG_CHRG_IND');
	
	
	INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'LEG_CHRG_CAT', 'Y', 'COMP', 'Legal Charge Category details', 'select
	CODE,
	(SELECT DESCRIPTION   FROM HO_CODES where ho_code=a.HO_CODE and ACTIVE_FLAG = ''Y'' AND EXPIRY_DATE IS NULL) as LEG_CUSCAT,
	SEVERITY_RANKING LEG_CUSSEV,
	OFFENCE_ID
from
	(
	select
		OFF.OFFENCE_CODE CODE,
		OFF.HO_CODE,
		off.SEVERITY_RANKING,
		off.OFFENCE_ID
	from
		(
		select
			OFFENCE_CODE,
			STATUTE_CODE,
			HO_CODE,
			SEVERITY_RANKING,
			OFFENCE_ID
		from
			OFFENCES )off,
		(
		select
			STATUTE_CODE,
			LEGISLATIVE_BODY,
			DESCRIPTION
		from
			(
			select
				STATUTE_CODE,
				DESCRIPTION,
				LEGISLATING_BODY_CODE
			from
				STATUTES) SS,
			(
			select
				CODE,
				DESCRIPTION LEGISLATIVE_BODY
			from
				REFERENCE_CODES
			where
				domain = ''LEGISL_BODY'') ref
		where
			SS.LEGISLATING_BODY_CODE = REF.CODE )STAT
	where
		STAT.STATUTE_CODE = OFF.STATUTE_CODE) a where code=?
',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'N', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'LEG_CHRG_CAT');


