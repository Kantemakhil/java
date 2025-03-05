INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'LEG_CRT_REPT', 'Y', 'COMP', 'Legal Court Reports', 'select
	(
	select
		description
	from
		order_types
	where
		order_type = od.order_type ) as LEG_CRTYPE,
	(
	select
		agy_loc.description
	from
		agency_locations agy_loc
	where
		agy_loc_id = od.issuing_agy_loc_id) as LEG_CRCRT,
	to_char(court_date, ''MM-DD-YYYY'') as LEG_CRDREQ,
	to_char(due_date, ''MM-DD-YYYY'') as LEG_CRDTDU,
	(case
		when coalesce(od.team_id, '''') <> '''' then (
		select
			description
		from
			automation_teams at2
		where
			team_id = od.team_id::bigint)
		else ''''
	end) as LEG_CRTRES,
	(case
		when coalesce(od.team_id, '''') = ''''  then (
		select
			SM.LAST_NAME || '' , '' || SM.FIRST_NAME
		from
			staff_members SM
		where
			staff_id = od.staff_member_id::bigint)
		else 
     (
		select
			SM.LAST_NAME || '' , '' || SM.FIRST_NAME
		from
			team_staff_members tsm ,
			staff_members SM
		where
			tsm.staff_id = SM.staff_id
			and tsm.team_id = od.team_id::bigint
			and team_member_id = od.staff_member_id::bigint)
	end) LEG_CRASOF,
	
	(select
		description
	from
		reference_codes rc
	where
		"domain" = ''REP_REQ_STS''
		and code = oD.order_status) as LEG_CRSTAT,
	
	to_char(status_date::timestamp,''YYYY-MM-DD'')  as LEG_CRSTDT,
	comment_text as LEG_CRAUCOM 

from
	orders od
where
	order_id = ?', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
	
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'LEG_CRT_REPT');


INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CRT_INT_PART', 'Y', 'COMP', 'Court Reports Intrested Parties', 'select
	(select
		description
	from
		reference_codes rc
	where
		"domain" = ''INT_PARTIES''
		and code =  party_type) as LEG_CRIPTY,
	party_description as LEG_CRIPDES,
	party_comment as LEG_CRIPCOM
from
	OFFENDER_INTERESTED_PARTIES
where
	record_id = ?
	and record_type = ''CRTREP''
	and offender_book_id = ? order by MODIFY_DATETIME desc', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL

WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CRT_INT_PART');

INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CRT_RPT_CHRG', 'Y', 'COMP', 'Court Report Charges', 'select
	CHARGE_BOOK.MATTER as LEG_CRCHMA,
	(select	DESCRIPTION from OFFENCES where OFFENCE_ID::text = CHARGE_BOOK.OFFENCEID ) as LEG_CRCHDES,
	(select	DESCRIPTION	from OFFENCE_RESULT_CODES where	RESULT_CODE = CHARGE_BOOK.OUTCOME ) as LEG_CRCHOC,
	(select	S.DESCRIPTION from statutes S where S.statute_code  in ((select	O.statute_code from OFFENCES O where O.OFFENCE_ID::text = CHARGE_BOOK.OFFENCEID ))) as LEG_CRCHAC,
	(select	offence_code from OFFENCES where OFFENCE_ID::text = CHARGE_BOOK.OFFENCEID ) as LEG_CRCHCO
from
	(
	select
		A.CHARGEID,
		B.CHARGEID,
		B.MATTER,
		B.TYPE,
		B.OFFENCEID,
		B.OUTCOME
	from
		(
		select
			to_char(CHARGE_ID)  as CHARGEID
		from
			OFF_COURT_REPORT_CHARGES
		where
			OFFENDER_BOOK_ID = ?
			and ORDER_ID = ?) as A
	join (
		select
			JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''offenceId'' as OFFENCEID,
			JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''chargeId'' as CHARGEID,
			JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''matter'' as MATTER,
			JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''type'' as type,
			JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''outcome'' as OUTCOME
		from
			(
			select
				OFFENDER_BOOK_ID,
				ENCODE(FORM_INFO_JSON, ''ESCAPE'') as FORMINFOJSON
			from
				(
				select
					FORM_IDENTIFIER::JSONB->> ''offenderBookId'' as OFFENDER_BOOK_ID,
					FORM_INFO_JSON
				from
					OCDCHGSU_DATA OD ) as T
			where
				OFFENDER_BOOK_ID =?) CHARGE_SUMMARY) as B on
		A.CHARGEID = B.CHARGEID) CHARGE_BOOK',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CRT_RPT_CHRG');



INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CRT_REP_PROP', 'Y', 'COMP', 'Court Reports Proposal', 'select

	(select
	
	sct.description
from
	SENTENCE_CALC_TYPES sct
where
     sct.sentence_calc_type=order_proposal_code and
	active_flag = ''Y''
	and sentence_category = ''NCUS'') as LEG_CRRPDSP,
	
	NOT_SUITABLE_FLAG as LEG_CRRPNS,
		(select
		description
	from
		reference_codes rc
	where
		"domain" = ''CRT_REP_UNS''
		and code = NOT_SUITABLE_REASON) as LEG_CRRPNSR,

	comment_text as LEG_CRRPCOM
from
	order_proposals
where
	order_id =?
order by
	oumagloc_sort_on_description(''RPT_PPSL'',
	order_proposal_code)',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CRT_REP_PROP');
	
	
	INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CRT_RPT_COND', 'Y', 'COMP', 'Court Report Condition Details', 'SELECT
   (SELECT description  FROM PROGRAM_SERVICES  WHERE program_code=condition_activity_code  and PROGRAM_CATEGORY= ''ACP'' AND EXPIRY_DATE IS NULL) as LEG_CRCDACT,
    comment_text as LEG_CRCDCOM
   FROM
    order_ppsl_cond_activities where order_id=?', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL

WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CRT_RPT_COND');



INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CRT_ACT_ORDR', 'Y', 'COMP', 'Court Action Orders', 'select
	ncus.displayno as LEG_CAONO,
	case when ncus.hearingdate is null then to_char(nullif(ncus.ordereddate,'''')::timestamp,	''DD-MM-YYYY'') else to_char(nullif(ncus.hearingdate,'''')::timestamp,''DD-MM-YYYY'') end as LEG_CAOOD,
	ncus.matter as LEG_CAOMAT,
	case when ncus.authority is null then (select description from agency_locations al where agy_loc_id = ncus.court) else (select description from agency_locations al where agy_loc_id = ncus.authority) end as LEG_CAOCRT,
	case when ncus.ordercategory = ''NONCUST'' then (select	description from SENTENCE_CALC_TYPES where sentence_calc_type = ncus.type and sentence_category = ''NCUS'') else (select	description from SENTENCE_CALC_TYPES where sentence_calc_type = ncus.type and sentence_category = ''PAR'') end as LEG_CAOTYP ,
	(select	description	from reference_codes rc	where domain = ''LO_REL_TYPE'' and code = ncus.commencetype)as LEG_CAOCTYP	,
	case when ncus.affectedorders is null then ncus.relatedto else trim(translate(affectedOrders,''[]"'',''''))  end as LEG_CAOREL,
	to_char(nullif(ncus.commencedate,'''')::timestamp,''DD-MM-YYYY'') as LEG_CAOCOMD,
	case when ncus.duration is null then ncus.terms else ncus.duration end as LEG_CAODUR,
	case when (ncus.expirydate ='''' or ncus.expirydate =''Indefinite'') then ncus.expirydate else 
to_char(nullif(ncus.expirydate,'''')::timestamp,''DD-MM-YYYY'') end as LEG_CAOEXP,
	  (select description from legal_update_reasons lr where update_reason_code = ncus.status) as LEG_CAOSTAT
from
	(
	select
		*
	from
		(
		select
			jsonb_array_elements(legal_summary::jsonb)->>''displayNo'' as displayno,
			jsonb_array_elements(legal_summary::jsonb)->>''orderedDate'' as "ordereddate",
			jsonb_array_elements(legal_summary::jsonb)->>''hearingDate'' as "hearingdate",
			jsonb_array_elements(legal_summary::jsonb)->>''matter'' as "matter",
			jsonb_array_elements(legal_summary::jsonb)->>''court'' as "court",
			jsonb_array_elements(legal_summary::jsonb)->>''authority'' as "authority",
			
			jsonb_array_elements(legal_summary::jsonb)->>''sentenceCalcType'' as "type",
			jsonb_array_elements(legal_summary::jsonb)->>''commenceType'' as "commencetype",
			jsonb_array_elements(legal_summary::jsonb)->>''relatedTo'' as "relatedto",
			jsonb_array_elements(legal_summary::jsonb)->>''affectedOrders'' as "affectedorders",
			
			jsonb_array_elements(legal_summary::jsonb)->>''commenceDate'' as "commencedate",
			
			jsonb_array_elements(legal_summary::jsonb)->>''termTypeAndLength'' as "terms",
			jsonb_array_elements(legal_summary::jsonb)->>''duration'' as "duration",
			
			jsonb_array_elements(legal_summary::jsonb)->>''expiryDate'' as "expirydate",
			jsonb_array_elements(legal_summary::jsonb)->>''status'' as "status",
			jsonb_array_elements(legal_summary::jsonb)->>''orderType'' as "ordertype",
			A.ordertype as ordercategory
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
					form_identifier::jsonb->> ''orderType'' as ordertype,
					form_info_json,
					jsonb_path_query_array( encode(form_info_json,
					''escape'')::jsonb,
					''$.*'') as items
				from
					ocdleglo_data
) as t
			where
				offenderbookid=?
				and ordertype =?)A)A
	where
		displayno =?)ncus',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL,NUll, NULL, 'N', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CRT_ACT_ORDR');


INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CRT_ACTIONS', 'Y', 'COMP', 'Court Actions', 'select
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
	offender_book_id =?
	and order_type =?',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CRT_ACTIONS');









