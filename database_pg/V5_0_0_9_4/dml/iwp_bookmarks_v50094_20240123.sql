insert
	into
	iwp_bookmarks (bookmark_name,
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
	'COMM_UPW',
	'Y',
	'COMP',
	'Community Service',
	'
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
				and orderType = ''NONCUST'')A)A where orderno = ''1'')ncus) orders
				where orders.orderno  = to_char(osc.sentence_seq) and 
		osc.offender_book_id = ?) ocduproj where offender_sent_condition_id = ?',
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
		bookmark_name = 'COMM_UPW');

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
	'UPW_ASSIGN',
	'Y',
	'COMP',
	'Project Assignments',
	'select 
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
     AND sentence_seq = 1
     AND OFFENDER_SENT_CONDITION_ID = ?) a',
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
		bookmark_name = 'UPW_ASSIGN');

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
	'UPW_CREDIT',
	'Y',
	'COMP',
	'Credit',
	'select 
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
  SENTENCE_SEQ=1 and 
  offender_sent_condition_id =?
order by ADJUSTMENT_DATE desc) a',
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
		bookmark_name = 'UPW_CREDIT');
