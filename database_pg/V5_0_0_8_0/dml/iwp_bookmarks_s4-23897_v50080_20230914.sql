update
	iwp_bookmarks
set
	sql_text = 'select
	charge_book.matter as LEG_CUSMTNM,
	(select description from OFFENCES where OFFENCE_ID::text = charge_book.offenceid  ) as LEG_CUSCHRG,
	(select description from OFFENCE_RESULT_CODES where result_code = charge_book.outcome ) as LEG_CUSOTCM,
	(select description from reference_codes rc where domain =''OFFENCE_TYPE'' and code = charge_book.type) as LEG_CUSTYPE
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
	modify_datetime = '2023-08-16 11:41:28.073',
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_CUS_CHRG';
