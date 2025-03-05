update
	iwp_bookmarks
set
	sql_text = 'select 
cust.displayno as LEG_CUSOFNM,
to_char(nullif(cust.ordereddate,'''')::timestamp,''DD-MM-YYYY'')as LEG_CUSODTE,
cust.matter as LEG_CUSCMTN,
cust.court as LEG_CUSCOURT,
(select description from SENTENCE_CALC_TYPES rc where sentence_calc_type =cust.type)as LEG_CUSOTYP ,
 (select description from reference_codes rc where domain=''LO_REL_TYPE'' and code=cust.commencetype)as LEG_CUSCTYP,
cust.relatedto as LEG_CUSLNKT,
 to_char(nullif(cust.commencedate,'''')::timestamp,''DD-MM-YYYY'') as LEG_CUSCDTE,
cust.terms as LEG_CUSTTYL,
(select description from legal_update_reasons lr where update_reason_code = cust.status) as  LEG_CUSOSTS,
case when (cust.holdexpirydate ='''' or cust.holdexpirydate =''Indefinite'') then cust.holdexpirydate else 
to_char(nullif(cust.holdexpirydate,'''')::timestamp,''DD-MM-YYYY'') end as LEG_CUSHEDT
from 
(select * from (select
		jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
		jsonb_array_elements(legal_summary::jsonb)->>''orderedDate'' as "ordereddate",
		jsonb_array_elements(legal_summary::jsonb)->>''matter'' as "matter",
		jsonb_array_elements(legal_summary::jsonb)->>''court'' as "court",
		jsonb_array_elements(legal_summary::jsonb)->>''sentenceCalcType'' as "type",
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
			offenderbookid=? and orderType =''CUST'')A)A where displayno=?)cust',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_CUST';

update
	iwp_bookmarks
set
	sql_text = 'select
	charge_book.matter as LEG_CUSMTNM,
	(select description from OFFENCES where OFFENCE_ID::text = charge_book.offenceid  ) as LEG_CUSCHRG,
	(select description from OFFENCE_RESULT_CODES where result_code = charge_book.outcome ) as LEG_CUSOTCM,
	(select description from reference_codes rc where domain =''OFFENCE_TYPE'' and code = charge_book.type) as LEG_CUSTTYPE
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
					and orderType = ''CUST'')B)NCUS_CHARGES  where displayno  = ?) as a
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
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_CUS_CHRG';

update
	iwp_bookmarks
set
	sql_text = 'select
(select description from reference_codes rc where domain = ''PLEA_STATUS'' and code = plea)as LEG_CUSPLEA,
to_char(nullif(incidentdate,'''')::timestamp,''DD-MM-YYYY'')   as LEG_CUSINDT,
to_char(nullif(range,'''')::timestamp,''DD-MM-YYYY'')   as  LEG_CUSRAN,
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
)A where offenderbookid=? and chargeid=?',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_CHRG_DET';
