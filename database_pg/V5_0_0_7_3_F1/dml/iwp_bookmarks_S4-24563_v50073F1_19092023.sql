update
	oms_owner.iwp_bookmarks
set
	sql_text = 'select
	ncus.displayno as LEG_NCODFNM,
	to_char(nullif(ncus.ordereddate,'''')::timestamp,	''DD-MM-YYYY'')as LEG_NCODODTE,
	ncus.matter as LEG_NCODCMTN,
	(select description from agency_locations al where agy_loc_id = ncus.court) as LEG_NCODCRT,
	(select	description from SENTENCE_CALC_TYPES where sentence_calc_type = ncus.type and sentence_category = ''NCUS'')as LEG_NCODOTYP ,
	(select	description	from reference_codes rc	where domain = ''LO_REL_TYPE'' and code = ncus.commencetype)as LEG_NCODCTYP,
	ncus.relatedto as LEG_NCODLNKT,
	to_char(nullif(ncus.commencedate,'''')::timestamp,''DD-MM-YYYY'') as LEG_NCODCDTE,
	ncus.terms as LEG_NCODDTN,
	case when (ncus.expirydate ='''' or ncus.expirydate =''Indefinite'') then ncus.expirydate else 
to_char(nullif(ncus.expirydate,'''')::timestamp,''DD-MM-YYYY'') end as LEG_NCODEDTE,
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
		displayno = ?)ncus',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_NCUS';

update
	oms_owner.iwp_bookmarks
set
	sql_text = 'select
	A.displayno as LEG_POAONMB,
	(Select	description	from sentence_calc_types sct where sentence_calc_type = A.type) as LEG_POAOTYPE,
	A.terms as LEG_POAOTERM, A.relatedTo as LEG_POAORLTD,
	to_char(nullif(A.commenceDate,'''')::timestamp,''DD-MM-YYYY'') as LEG_POAOCMDT,
	case when (B.erd ='''' or B.erd =''Indefinite'') then B.erd else 
	to_char(nullif(B.erd,'''')::timestamp,''DD-MM-YYYY'') end as LEG_POAOERD,
	to_char(nullif(B.ped,'''')::timestamp,''DD-MM-YYYY'') as LEG_POAOPED,
	case when (B.lrd ='''' or B.lrd =''Indefinite'') then B.lrd else 
	to_char(nullif(B.lrd,'''')::timestamp,''DD-MM-YYYY'') end as LEG_POAOLRD,
	(select	description	from legal_update_reasons lr where update_reason_code = A.status) as LEG_POAOSTS
from
	(select
		orderType,
		jsonb_array_elements(Cust_Orders::jsonb)->>''displayNo'' as displayno,
		jsonb_array_elements(Cust_Orders::jsonb)->>''sentenceCalcType'' as type,
		jsonb_array_elements(Cust_Orders::jsonb)->>''termTypeAndLength'' as terms,
		jsonb_array_elements(Cust_Orders::jsonb)->>''relatedTo'' as relatedTo,
		jsonb_array_elements(Cust_Orders::jsonb)->>''commenceDate'' as commenceDate,
		jsonb_array_elements(Cust_Orders::jsonb)->>''status'' as status
	from
		(select
			orderType,
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
			and orderType = ''CUST'')as custOrd) as A
join
    (select
		displayno,		
		MAX((case when Legal_summary_data.dateType = ''erd'' then
		case when Legal_summary_data.indefinite = ''true'' then ''Indefinite'' else Legal_summary_data.datevalue end end))as erd,
		MAX((case when Legal_summary_data.dateType = ''ped'' then Legal_summary_data.datevalue end)) as ped,
		MAX((case when Legal_summary_data.dateType = ''lrd'' then
		case when Legal_summary_data.indefinite = ''true'' then ''Indefinite'' else Legal_summary_data.datevalue end end))as lrd
	from
		(select
			displayno,
			dateType,
			indefinite,
			MAX(datevalue) as datevalue
		from
			(select
				displayno,
				jsonb_array_elements(sentorderdate::jsonb)->>''dateType'' as dateType,
				jsonb_array_elements(sentorderdate::jsonb)->>''indefinite'' as indefinite,
				jsonb_array_elements(sentorderdate::jsonb)->>''effectiveValue'' as datevalue
			from
				(select
					Cust_Orders,
					jsonb_array_elements(Cust_Orders::jsonb)->>''displayNo'' as displayno,
					jsonb_array_elements(Cust_Orders::jsonb)->>''sentenceOrderDates'' as sentorderdate
				from
					(select
						offenderBookId,
						items ->> 2 as Cust_Orders
					from
						(select
							form_identifier::jsonb->>''offenderBookId'' as offenderbookid,
							jsonb_path_query_array(encode(form_info_json, ''escape'')::jsonb,''$.*'') as items
						from
							ocdlegls_data) as t
					where offenderbookid = ?) as LEG_SUM) as LEG_SUM_1) as LEG_SUM_2
		where
			datetype in (''erd'', ''lrd'', ''ped'')
		group by
			displayno,dateType,indefinite
) as Legal_summary_data
	group by
		displayno) as B on
	A.displayno = B.displayno
where
	A.displayno in (
	select
		trim(translate(unnest(string_to_array(affectedOrders, '','' , '''')), ''[]"'', '''')) as affectedOrders
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
				from Ocdleglo_data)as t
			where offenderbookid = ? and orderType = ''PAR'')A)B where displayno = ?)',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_PAR_AFFE';
