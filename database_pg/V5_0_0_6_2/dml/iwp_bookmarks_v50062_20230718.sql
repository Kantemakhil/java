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
	'LEG_NCUS',
	'Y',
	'COMP',
	'Non Custodial Order',
	'select
	ncus.displayno as LEG_NCODFNM,
	to_char(nullif(ncus.ordereddate,'''')::timestamp,	''DD-MM-YYYY'')as LEG_NCODODTE,
	ncus.matter as LEG_NCODCMTN,
	(select description from agency_locations al where agy_loc_id = ncus.court) as LEG_NCODCRT,
	(select	description from SENTENCE_CALC_TYPES where sentence_calc_type = ncus.type and sentence_category = ''NCUS'')as LEG_NCODOTYP ,
	(select	description	from reference_codes rc	where domain = ''LO_REL_TYPE'' and code = ncus.commencetype)as LEG_NCODCTYP,
	ncus.relatedto as LEG_NCODLNKT,
	to_char(nullif(ncus.commencedate,'''')::timestamp,''DD-MM-YYYY'') as LEG_NCODCDTE,
	ncus.terms as LEG_NCODDTN,
	ncus.expirydate as LEG_NCODEDTE,
	  (select description from legal_update_reasons lr where update_reason_code = ncus.status) as LEG_NCODSTS
from
	(
	select
		*
	from
		(
		select
			jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
			jsonb_array_elements(legal_summary::jsonb)->>''orderedDate'' as "ordereddate",
			jsonb_array_elements(legal_summary::jsonb)->>''matter'' as "matter",
			jsonb_array_elements(legal_summary::jsonb)->>''court'' as "court",
			jsonb_array_elements(legal_summary::jsonb)->>''sentenceCalcType'' as "type",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceType'' as "commencetype",
			jsonb_array_elements(legal_summary::jsonb)->>''relatedTo'' as "relatedto",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceDate'' as "commencedate",
			jsonb_array_elements(legal_summary::jsonb)->>''termTypeAndLength'' as "terms",
			jsonb_array_elements(legal_summary::jsonb)->>''expiryDate'' as "expirydate",
			jsonb_array_elements(legal_summary::jsonb)->>''status'' as "status"			
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
				offenderbookid = ?
				and orderType = ''NONCUST'')A)A
	where
		displayno =?)ncus',
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
		bookmark_name = 'LEG_NCUS');

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
	'LEG_NCUS_CHG',
	'Y',
	'COMP',
	'Legal Non Custodial Charges',
	'select
	charge_book.matter as LEG_NCODTNM,
	(select description from OFFENCES where OFFENCE_ID::text = charge_book.offenceid  ) as LEG_NCODCHRG,
	(select description from OFFENCE_RESULT_CODES where result_code = charge_book.outcome ) as LEG_NCODOTCM,
	(select description from reference_codes rc where domain =''OFFENCE_TYPE'' and code = charge_book.type) as LEG_NCODTYPE
from
	(
	select
		A.offenderbookid,
		A.outcome,
		A.chargeid,
		B.chargeid,
		b.matter,
		b.type,
		b.offenceid
	from
		(
		select
			offenderBookId,
			displayno,
			jsonb_array_elements(charges::jsonb)->>''chargeId'' as "chargeid",
			jsonb_array_elements(charges::jsonb)->>''outcome'' as "outcome"
		from
			(
			select
				offenderBookId,
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
						ocdleglo_data) as t
				where
					offenderbookid = ?
					and orderType = ''NONCUST'')B)NCUS_CHARGES  where displayno  = ?) as a
	join (
		select
			jsonb_array_elements(forminfojson::jsonb)->>''offenceId'' as offenceId,
			jsonb_array_elements(forminfojson::jsonb)->>''chargeId'' as chargeid,
			jsonb_array_elements(forminfojson::jsonb)->>''matter'' as matter,
			jsonb_array_elements(forminfojson::jsonb)->>''type'' as type
		from
			(
			select
				offenderBookId,
				encode(form_info_json, ''escape'') as forminfojson
			from
				(
				select
					form_identifier::jsonb->> ''offenderBookId'' as offenderbookid,
					form_info_json
				from
					ocdchgsu_data od ) as t
			where
				offenderbookid = ?) CHARGE_SUMMARY) as b on
		A.chargeid = B.chargeid) charge_book',
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
		bookmark_name = 'LEG_NCUS_CHG');

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
	'LEG_NCHG_DET',
	'Y',
	'COMP',
	'Non Custodial Charge Details',
	'select
(select description from reference_codes rc where domain = ''PLEA_STATUS'' and code = plea) as LEG_NCODPLEA,
incidentdate as LEG_NCODINDT,
range as LEG_NCODRAN,
particulars as LEG_NCODPART,
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
)A where offenderbookid=? and chargeid=?',
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
		bookmark_name = 'LEG_NCHG_DET');

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
	'LEG_NCHG_IND',
	'Y',
	'COMP',
	'Non Cust Charge Indicators',
	'select
	(
	select
		description
	from
		reference_codes rc
	where
		code = INDICATOR_CODE
		and "domain" = ''OFFENCE_IND'') as  LEG_NCODINCD
from
	OFFENCE_INDICATORS
where
	OFFENCE_ID=?',
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
		bookmark_name = 'LEG_NCHG_IND');

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
	'LEG_NCHG_CAT',
	'Y',
	'COMP',
	'Non Cust Charge Category',
	'select
	CODE,
	(SELECT DESCRIPTION   FROM HO_CODES where ho_code=a.HO_CODE and ACTIVE_FLAG = ''Y'' AND EXPIRY_DATE IS NULL) as LEG_NCODCAT,
	(select DESCRIPTION from reference_codes rc where domain = ''SEVERE_RANK'' and CODE = SEVERITY_RANKING) AS LEG_NCODSEV,
	OFFENCEID
from
	(
	select
		OFF.OFFENCE_CODE CODE,
		OFF.HO_CODE,
		off.SEVERITY_RANKING,
		off.OFFENCE_ID as OFFENCEID
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
		STAT.STATUTE_CODE = OFF.STATUTE_CODE) a where code= ?
			and OFFENCEID =?',
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
		bookmark_name = 'LEG_NCHG_CAT');

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
	'LEG_BAIL',
	'Y',
	'COMP',
	'Bail Order',
	'select
	bail.displayno as LEG_BAILONMF,
	to_char(nullif(bail.ordereddate,'''')::timestamp,''DD-MM-YYYY'')as LEG_BAILODTE,
	bail.matter as LEG_BAILCMTN,
	bail.court as  LEG_BAILCRT,
	(select	description	from SENTENCE_CALC_TYPES rc	where sentence_calc_type = bail.type and sentence_category = ''BAIL'')as LEG_BAILOTYP ,
	(select description from statutes where statute_code =legislation) as LEG_BAILLGTN,
	to_char(nullif(bail.commencedate,'''')::timestamp,''DD-MM-YYYY'') as LEG_BAILCMDT,
	to_char(nullif(bail.expirydate,'''')::timestamp,''DD-MM-YYYY'') as LEG_BAILEXDT,
	signaturerequired as LEG_BAILSGRQ,
	(select description from legal_update_reasons lr where update_reason_code=bail.status)as LEG_BAILOSTS
from(
		select
			jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
			jsonb_array_elements(legal_summary::jsonb)->>''orderedDate'' as "ordereddate",
			jsonb_array_elements(legal_summary::jsonb)->>''matter'' as "matter",
			jsonb_array_elements(legal_summary::jsonb)->>''court'' as "court",
			jsonb_array_elements(legal_summary::jsonb)->>''legislation'' as "legislation",
			jsonb_array_elements(legal_summary::jsonb)->>''sentenceCalcType'' as "type",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceType'' as "commencetype",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceDate'' as "commencedate",
			jsonb_array_elements(legal_summary::jsonb)->>''signaturerequired'' as "signaturerequired",
			jsonb_array_elements(legal_summary::jsonb)->>''status'' as "status",
			jsonb_array_elements(legal_summary::jsonb)->>''expiryDate'' as "expirydate"
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
				offenderbookid = ?
				and orderType = ''BAIL'')A)BAIL
	where
		displayno = ?',
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
		bookmark_name = 'LEG_BAIL');

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
	'LEG_BAIL_CHG',
	'Y',
	'COMP',
	'Legal Bail Charges',
	'select
	charge_book.matter as LEG_BAILMTNM,
	(select description from OFFENCES where OFFENCE_ID::text = charge_book.offenceid  ) as LEG_BAILCHRG,
	(select description from OFFENCE_RESULT_CODES where result_code = charge_book.outcome ) as LEG_BAILOTCM,
	(select description from reference_codes rc where domain =''OFFENCE_TYPE'' and code = charge_book.type) as LEG_BAILTYPE
from
	(
	select
		A.offenderbookid,
		A.outcome,
		A.chargeid,
		B.chargeid,
		b.matter,
		b.type,
		b.offenceid
	from
		(
		select
			offenderBookId,
			displayno,
			jsonb_array_elements(charges::jsonb)->>''chargeId'' as "chargeid",
			jsonb_array_elements(charges::jsonb)->>''outcome'' as "outcome"
		from
			(
			select
				offenderBookId,
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
						ocdleglo_data) as t
				where
					offenderbookid = ?
					and orderType =''BAIL'')B)BAIL  where displayno  = ?) as a
	join (
		select
			jsonb_array_elements(forminfojson::jsonb)->>''offenceId'' as offenceId,
			jsonb_array_elements(forminfojson::jsonb)->>''chargeId'' as chargeid,
			jsonb_array_elements(forminfojson::jsonb)->>''matter'' as matter,
			jsonb_array_elements(forminfojson::jsonb)->>''type'' as type
		from
			(
			select
				offenderBookId,
				encode(form_info_json, ''escape'') as forminfojson
			from
				(
				select
					form_identifier::jsonb->> ''offenderBookId'' as offenderbookid,
					form_info_json
				from
					ocdchgsu_data od ) as t
			where
				offenderbookid = ?) CHARGE_SUMMARY) as b on
		A.chargeid = B.chargeid) charge_book',
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
		bookmark_name = 'LEG_BAIL_CHG');

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
	'LEG_BCHG_DET',
	'Y',
	'COMP',
	'Bail Charge Details',
	'select
(select description from reference_codes rc where domain = ''PLEA_STATUS'' and code = plea) as LEG_BAILPLEA,
incidentdate as LEG_BAILINDT,
range as LEG_BAILRAN,
particulars as LEG_BAILPAR,
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
)A where offenderbookid=? and chargeid=?',
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
		bookmark_name = 'LEG_BCHG_DET');

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
	'LEG_PAR',
	'Y',
	'COMP',
	'Parole Order',
	'select
	displayno as LEG_PAROFNM,
	hearingdate as LEG_PAROODTE,
	(select description from agency_locations al where agy_loc_id = authority) as LEG_PAROCMTN,
	(select	description	from SENTENCE_CALC_TYPES rc	where sentence_calc_type = type)as LEG_PAROOTYP ,
	(select description from reference_codes rc where domain=''LO_REL_TYPE'' and code=commencetype)as LEG_PAROCTYP,
	duration as LEG_PARODUTN,
	to_char(nullif(commencedate,'''')::timestamp,''DD-MM-YYYY'') as LEG_PAROCDTE,
	to_char(nullif(expirydate,'''')::timestamp,''DD-MM-YYYY'') as LEG_PAROEXDT,
	(select description from legal_update_reasons lr where update_reason_code= status)as LEG_PAROOSTS
from
	( select
			jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
			jsonb_array_elements(legal_summary::jsonb)->>''hearingDate'' as "hearingdate",
			jsonb_array_elements(legal_summary::jsonb)->>''authority'' as "authority",
			jsonb_array_elements(legal_summary::jsonb)->>''sentenceCalcType'' as "type",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceType'' as "commencetype",
			jsonb_array_elements(legal_summary::jsonb)->>''duration'' as "duration",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceDate'' as "commencedate",
			jsonb_array_elements(legal_summary::jsonb)->>''expiryDate'' as "expirydate",
			jsonb_array_elements(legal_summary::jsonb)->>''status'' as "status"			
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
				offenderbookid = ?
				and orderType = ''PAR'')A)A
	where
		displayno = ?',
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
		bookmark_name = 'LEG_PAR');

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
	'LEG_PAR_AFFE',
	'Y',
	'COMP',
	'Parole Affected Orders',
	'select
	A.displayno as LEG_POAONMB,
	(Select	description	from sentence_calc_types sct where	sentence_calc_type = A.type) as LEG_POAOTYPE,
	A.terms as LEG_POAOTERM,
	A.relatedTo as LEG_POAORLTD,
	A.commenceDate as LEG_POAOCMDT,
	B.erd as LEG_POAOERD,
	B.ped as LEG_POAOPED,
	B.lrd as LEG_POAOLRD,
	(select	description	from legal_update_reasons lr where update_reason_code = A.status) as LEG_POAOSTS
from
	(select
		orderType,
		formInfoJson,
		jsonb_array_elements(Cust_Orders::jsonb)->>''displayNo'' as displayno,
		jsonb_array_elements(Cust_Orders::jsonb)->>''sentenceCalcType'' as type,
		jsonb_array_elements(Cust_Orders::jsonb)->>''termTypeAndLength'' as terms,
		jsonb_array_elements(Cust_Orders::jsonb)->>''relatedTo'' as relatedTo,
		jsonb_array_elements(Cust_Orders::jsonb)->>''commenceDate'' as commenceDate,
		jsonb_array_elements(Cust_Orders::jsonb)->>''status'' as status
	from
		(select
			orderType,
			form_info_json as formInfoJson,
			items ->> 0 as Cust_Orders
		from
			(select
				form_identifier::jsonb->>''offenderBookId'' as offenderbookid,
				form_identifier::jsonb->>''orderType'' as orderType,
				form_info_json,
				jsonb_path_query_array(encode(form_info_json, ''escape'')::jsonb,''$.*'') as items
			from ocdleglo_data) as t
		where
			offenderbookid = ?
			and orderType = ''CUST''
            ) as custOrd
    ) as A
join
    (
    select
		displayno,
		MAX((case when Legal_summary_data.dateType = ''lrd'' then Legal_summary_data.datevalue end)) as lrd,
		MAX((case when Legal_summary_data.dateType = ''erd'' then Legal_summary_data.datevalue end)) as erd,
		MAX((case when Legal_summary_data.dateType = ''ped'' then Legal_summary_data.datevalue end)) as ped
	from
		(select
			displayno,
			dateType,
			MAX(datevalue) as datevalue
		from
			(select
				displayno,
				jsonb_array_elements(sentorderdate::jsonb)->>''dateType'' as dateType,
				jsonb_array_elements(sentorderdate::jsonb)->>''effectiveValue'' as datevalue
			from
				(select
					Cust_Orders,
					jsonb_array_elements(Cust_Orders::jsonb)->>''displayNo'' as displayno,
					jsonb_array_elements(Cust_Orders::jsonb)->>''sentenceOrderDates'' as sentorderdate
				from
					(select
						offenderBookId,
						form_info_json as formInfoJson,
						items ->> 2 as Cust_Orders
					from
						(select
							form_identifier::jsonb->>''offenderBookId'' as offenderbookid,
							form_info_json,
							jsonb_path_query_array(encode(form_info_json, ''escape'')::jsonb,''$.*'') as items
						from
							ocdlegls_data) as t
					where
						offenderbookid = ?
      ) as LEGAL_SUMMARY
    ) as LEGAL_SUMMARY_1
  ) as LEGAL_SUMMARY_2
		where
			datetype in (''erd'', ''lrd'', ''ped'')
		group by
			displayno,
			dateType
) as Legal_summary_data
	group by
		displayno) as B on
	A.displayno = B.displayno
where
	A.displayno in (
	select
		trim(translate(unnest(string_to_array(affectedOrders, '','' ,'''')), ''[]"'', '''')) as affectedOrders
	from
		(select
			jsonb_array_elements(Par_Orders::jsonb)->>''displayNo'' as displayno,
			jsonb_array_elements(Par_Orders::jsonb)->>''affectedOrders'' as affectedOrders
		from
			(select
				orderType,
				form_info_json as formInfoJson,
				items ->> 0 as Par_Orders
			from
				(select
					form_identifier::jsonb->>''offenderBookId'' as offenderbookid,
					form_identifier::jsonb->>''orderType'' as orderType,
					form_info_json,
					jsonb_path_query_array(encode(form_info_json, ''escape'')::jsonb,''$.*'') as items
				from
					ocdleglo_data ) as t
			where
				offenderbookid = ?
				and orderType = ''PAR'' ) A)B
	where
		displayno = ?)',
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
		bookmark_name = 'LEG_PAR_AFFE');

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
	'LEG_PAR_DUR',
	'Y',
	'COMP',
	'Parole Order Duration',
	'select
	(select	description	from reference_codes rc	where domain = ''SENT_TERM''	and code = termType) as LEG_PAROTYPE,
	years as LEG_PAROYRS,
	months as LEG_PAROMTHS,
	weeks as LEG_PAROWKS,
	days as LEG_PARODAYS
from
	(
	select
		displayno,
		jsonb_array_elements(duration::jsonb)->>''termType'' as termType,
		jsonb_array_elements(duration::jsonb)->>''years'' as years ,
		jsonb_array_elements(duration::jsonb)->>''months'' as months,
		jsonb_array_elements(duration::jsonb)->>''weeks'' as weeks,
		jsonb_array_elements(duration::jsonb)->>''days'' as days
	from
		(
		select
			jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as "displayno",
			jsonb_array_elements(legal_summary::jsonb)->>''terms'' as "duration"
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
				offenderbookid = ?
				and orderType = ''PAR'')PAR_ORD)LEG_PAR where displayno = ?) orderDuration',
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
		bookmark_name = 'LEG_PAR_DUR');

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
	'OFF_ASSESS',
	'Y',
	'COMP',
	'Offender Assessments',
	'select
	(select	description from assessments where assessment_id = oa.assessment_type_id) as CLS_ATYPE,
	(select	description from reference_codes rc	where domain = ''ASSESS_STATS'' and code = oa.assess_status) as CLS_ASTAT,
	score as CLS_ASCORE,
	override_score as CLS_AOSCORE,
	(select	description	from reference_codes rc	where domain = ''SUP_LVL_TYPE'' and code = oa.calc_sup_level_type) as CLS_ACRSLT,
	(select	description from reference_codes rc	where domain = ''SUP_LVL_TYPE'' and code = oa.review_sup_level_type) as CLS_AAPRSLT,
	(select	description	from reference_codes rc	where domain = ''SUP_LVL_TYPE'' and code = oa.overrided_sup_level_type) as CLS_AORSLT,
	(select	description	from agency_locations al where al.agy_loc_id = oa.assessment_create_location) as CLS_ADFAC, 
	(select	last_name || '','' || first_name	from staff_members sm where	sm.staff_id = oa.assess_staff_id) as CLS_ADASSESR,
	(select	description	from reference_codes rc	where domain = ''ASSESS_COMM'' and code = oa.assess_committe_code) as CLS_ADAUTH,
	to_char(nullif(create_datetime::timestamp,''2023-01-01'')::timestamp,''DD-MM-YYYY'') as CLS_ADCRDTE,
	to_char(nullif(assessment_date::timestamp,''2023-01-01'')::timestamp,''DD-MM-YYYY'') as CLS_ADSBDTE,
	to_char(nullif(next_review_date::timestamp,''2023-01-01'')::timestamp,''DD-MM-YYYY'') as CLS_ADRADTE,
	assess_comment_text as CLS_ADACOM,
	(select	description from reference_codes rc where domain = ''SUP_LVL_TYPE'' and code = oa.overrided_sup_level_type) as CLS_OAORRSLT ,
	(select	description from reference_codes rc	where domain = ''OVERRIDE_RSN'' and code = oa.override_reason) as CLS_OAORRSN,
	override_user_id as CLS_OAORUID,
	(select description from	agency_locations al	where al.agy_loc_id = oa.place_agy_loc_id) as CLS_OARECPLA,
	override_comment_text as CLS_OAORCOM,
	to_char(nullif(assessment_date::timestamp,''2023-01-01'')::timestamp,''DD-MM-YYYY'') as  CLS_APARDTE,
	(select	description from assessments where assessment_id = oa.assessment_type_id) as CLS_APARTYP,
	(select	description	from reference_codes rc	where domain = ''ASSESS_COMM'' and code = oa.assess_committe_code) as CLS_APARATH,
	score CLS_APARSCR,
	(select	description	from reference_codes rc	where domain = ''SUP_LVL_TYPE'' and code = oa.calc_sup_level_type) as CLS_APARCAL,
	(select	description	from reference_codes rc	where domain = ''SUP_LVL_TYPE'' and code = oa.overrided_sup_level_type) as CLS_APARORD,
	(select	description from reference_codes rc	where domain = ''SUP_LVL_TYPE'' and code = oa.review_sup_level_type) as CLS_APARAPR,
	(select case when oa.review_committe_code =''SYSTEM'' then ''SYSTEM APPROVED'' else (select description from reference_codes rc where domain = ''ASSESS_COMM'' and code = oa.review_committe_code) end) as CLS_APARAPA,
	to_char(nullif(evaluation_date::timestamp,''2023-01-01'')::timestamp,''DD-MM-YYYY'') as CLS_APADTEAP,
	(select case when oa.review_committe_code =''SYSTEM'' then ''SYSTEM APPROVED'' else (select description from reference_codes rc where domain = ''ASSESS_COMM'' and code = oa.review_committe_code) end) as CLS_APAAUTH,
	(select	description	from reference_codes rc	where domain = ''ASSESS_RSLT'' and code = oa.evaluation_result_code)  as CLS_APAAPPD,
	(select	description from reference_codes rc	where domain = ''SUP_LVL_TYPE'' and code = oa.review_sup_level_type) as CLS_APAAPRS,
	review_sup_level_text as CLS_APACOM,
	modify_user_id as CLS_APAUID,
	(select	description	from agency_locations al where al.agy_loc_id = oa.review_place_agy_loc_id) as  CLS_APAAPPLC,
	review_placement_text as  CLS_APACOM2,
	to_char(nullif(next_review_date::timestamp,''2023-01-01'')::timestamp,''DD-MM-YYYY'') as  CLS_APANRD,
	committe_comment_text as CLS_APACOM3
from
	offender_assessments oa
where
	oa.assessment_seq =?
	and oa.offender_book_id =?',
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
		bookmark_name = 'OFF_ASSESS');
