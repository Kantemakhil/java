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
(select description from legal_update_reasons lr where update_reason_code=cust.status)as LEG_CUSOSTS,
to_char(nullif(cust.holdexpirydate,'''')::timestamp,''DD-MM-YYYY'')as LEG_CUSHEDT
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
			offenderbookid=? and orderType =''CUST'')A)A where displayno=?)cust',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_CUST';

update
	iwp_bookmarks
set
	sql_text = 'select 
chrg.matter as LEG_CUSMTNM,
chrg.description as LEG_CUSCHRG ,
(select description from OFFENCE_RESULT_CODES rc where result_code =chrg.outcome)as LEG_CUSOTCM,
(select description from reference_codes rc where domain=''OFFENCE_TYPE'' and code=chrg.type)as LEG_CUSTYPE
from
(select 
jsonb_array_elements(charges::jsonb)->>''matter'' as "matter",
jsonb_array_elements(charges::jsonb)->>''description'' as "description",
	 jsonb_array_elements(charges::jsonb)->>''outcome'' as "outcome",
	 jsonb_array_elements(charges::jsonb)->>''type'' as "type",
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
			offenderbookid=? and orderType =''CUST'')A)A where displayno=?)chrg',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_CUS_CHRG';
