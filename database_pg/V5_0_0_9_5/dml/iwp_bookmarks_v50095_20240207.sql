update
	iwp_bookmarks
set
	sql_text = 'select
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
	and order_type =?
	and sentence_seq =?',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'CRT_ACTIONS';

update
	iwp_bookmarks
set
	sql_text = 'select a.obs_oopcid,
a.obs_oopcsdte,
a.obs_oopcvar,
a.obs_oopcstme,
a.obs_oopcdte,
a.obs_oopctme,
a.obs_oopcfreq,
a.obs_oopcstaf,
a.comment_text,
case when a.activity is null then
(select string_agg(ooad.detail_code, '','') FILTER (WHERE ooad.detail_type = ''ACTIVITY'') from off_obs_add_details ooad
where ooad.obs_type_version_id = ( select obs_type_version_id
	from off_observation_periods oop where obs_period_id = ?)) else a.activity end as activity,
	case when a.cell_cnditns is null then
(select string_agg(ooad.detail_code, '','') FILTER (WHERE ooad.detail_type = ''ACTIVITY'') from off_obs_add_details ooad
where ooad.obs_type_version_id = ( select obs_type_version_id
	from off_observation_periods oop where obs_period_id = ?)) else a.cell_cnditns end as cell_cnditns,
	case when a.com_det_cat is null then
(select string_agg(ooad.detail_code, '','') FILTER (WHERE ooad.detail_type = ''CELL_CNDITNS'') from off_obs_add_details ooad
where ooad.obs_type_version_id = ( select obs_type_version_id
	from off_observation_periods oop where obs_period_id = ?)) else a.com_det_cat end as com_det_cat,
	case when a.not_in_cell is null then
(select string_agg(ooad.detail_code, '','') FILTER (WHERE ooad.detail_type = ''NOT_IN_CELL'') from off_obs_add_details ooad
where ooad.obs_type_version_id = ( select obs_type_version_id
	from off_observation_periods oop where obs_period_id = ?)) else a.not_in_cell end as not_in_cell
 from (select
	obp.check_id as OBS_OOPCID,
	to_char(obp.schedule_datetime, ''DD-MM-YYYY'') as  OBS_OOPCSDTE,
	greatest((extract(epoch
from
	check_datetime)* 1000-extract(epoch
from
	obp.schedule_datetime)* 1000-(
	select
		frequency
	from
		offender_observation_types
	where
		obs_type_version_id =?))/ 60000,0) as OBS_OOPCVAR ,
	to_char(obp.schedule_datetime, ''HH24:MI'') as   OBS_OOPCSTME,
	to_char(obp.check_datetime , ''DD-MM-YYYY'') as  OBS_OOPCDTE,
	to_char(obp.check_datetime, ''HH24:MI'') as   OBS_OOPCTME,
	(
	select
		frequency 	
	from
		offender_observation_types
	where
		obs_type_version_id =?) as OBS_OOPCFREQ,
	(
	select
		last_name || '','' || '' '' || first_name as user_id
	from
		staff_members sm
	where
		staff_id = obp.performing_staff_id ) as OBS_OOPCSTAF,
		obp.comment_text ,
	string_agg(ooacd.detail_code, '','') FILTER (WHERE ooacd.detail_type = ''ACTIVITY'') AS ACTIVITY,
  	string_agg(ooacd.detail_code, '','') FILTER (WHERE ooacd.detail_type = ''CELL_CNDITNS'') AS CELL_CNDITNS,
  	string_agg(ooacd.detail_code, '','') FILTER (WHERE ooacd.detail_type = ''COM_DET_CAT'') AS COM_DET_CAT,
  	string_agg(ooacd.detail_code, '','') FILTER (WHERE ooacd.detail_type = ''NOT_IN_CELL'') AS NOT_IN_CELL
from
	OFF_OBS_PERIOD_CHECKS obp
	LEFT join off_obs_add_check_details ooacd
	on  obp.check_id = ooacd.check_id  
where
	OBS_PERIOD_ID =?
GROUP BY
	obp.check_id ) as a', 
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_OBS_CHEC';

update
	iwp_bookmarks
set
	active_flag = 'Y',
	bookmark_type = 'COMP',
	description = 'Court Report Charges',
	sql_text = 'select * from ( select CHARGE_BOOK.MATTER as LEG_CRCHMA, ( select DESCRIPTION from OFFENCES where OFFENCE_ID::text = CHARGE_BOOK.OFFENCEID ) as LEG_CRCHDES, ( select DESCRIPTION from OFFENCE_RESULT_CODES where RESULT_CODE = CHARGE_BOOK.OUTCOME ) as LEG_CRCHOC, ( select S.DESCRIPTION from statutes S where S.statute_code in (( select O.statute_code from OFFENCES O where O.OFFENCE_ID::text = CHARGE_BOOK.OFFENCEID ))) as LEG_CRCHAC, ( select offence_code from OFFENCES where OFFENCE_ID::text = CHARGE_BOOK.OFFENCEID ) as LEG_CRCHCO, CHARGE_BOOK.CHARGEID, CHARGE_BOOK.OFFENCEID, CHARGE_BOOK.OFFENDER_BOOK_ID from ( select A.CHARGEID, B.MATTER, B.TYPE, B.OFFENCEID, B.OUTCOME, B.OFFENDER_BOOK_ID from ( select to_char(CHARGE_ID) as CHARGEID from OFF_COURT_REPORT_CHARGES where OFFENDER_BOOK_ID =? and ORDER_ID =?) as A join ( select JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''offenceId'' as OFFENCEID, JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''chargeId'' as CHARGEID, JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''matter'' as MATTER, JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''type'' as type, JSONB_ARRAY_ELEMENTS(FORMINFOJSON::JSONB)->>''outcome'' as OUTCOME, OFFENDER_BOOK_ID from ( select OFFENDER_BOOK_ID, ENCODE(FORM_INFO_JSON, ''ESCAPE'') as FORMINFOJSON from ( select FORM_IDENTIFIER::JSONB->> ''offenderBookId'' as OFFENDER_BOOK_ID, FORM_INFO_JSON from OCDCHGSU_DATA OD ) as T where OFFENDER_BOOK_ID =?) CHARGE_SUMMARY) as B on A.CHARGEID = B.CHARGEID) CHARGE_BOOK)A left join ( select ( select description from reference_codes rc where domain = ''PLEA_STATUS'' and code = plea) as LEG_NCODPLEA, to_char(nullif(incidentdate, '''')::timestamp, ''DD-MM-YYYY'') as LEG_NCODINDT, to_char(nullif(range, '''')::timestamp, ''DD-MM-YYYY'') as LEG_NCODRAN, particulars as LEG_NCODPART, chargeid, OFFENDER_BOOK_ID from ( select jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''plea'' as plea, jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''Range'' as range, jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''incidentDate'' as incidentdate, jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''particulars'' as particulars, jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''chargeId'' as chargeid, form_identifier::jsonb->>''offenderBookId'' as OFFENDER_BOOK_ID from ocdchgsu_data )A where OFFENDER_BOOK_ID=?)B on A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.CHARGEID = B.CHARGEID left join (SELECT oi.offence_id, ARRAY_TO_STRING(array_agg(rc.description), '', '') AS LEG_CUSINCD FROM OFFENCE_INDICATORS oi JOIN reference_codes rc ON oi.INDICATOR_CODE = rc.code WHERE rc."domain" = ''OFFENCE_IND'' GROUP BY oi.offence_id )C on A.OFFENCEID::bigint = C.offence_id left join ( select CODE, ( select DESCRIPTION from HO_CODES where ho_code = a.HO_CODE and ACTIVE_FLAG = ''Y'' and EXPIRY_DATE is null) as LEG_NCODCAT, ( select DESCRIPTION from reference_codes rc where domain = ''SEVERE_RANK'' and CODE = SEVERITY_RANKING) as LEG_NCODSEV, OFFENCEID from ( select OFF.OFFENCE_CODE CODE, OFF.HO_CODE, off.SEVERITY_RANKING, off.OFFENCE_ID as OFFENCEID from ( select OFFENCE_CODE, STATUTE_CODE, HO_CODE, SEVERITY_RANKING, OFFENCE_ID from OFFENCES )off, ( select STATUTE_CODE, LEGISLATIVE_BODY, DESCRIPTION from ( select STATUTE_CODE, DESCRIPTION, LEGISLATING_BODY_CODE from STATUTES) SS, ( select CODE, DESCRIPTION LEGISLATIVE_BODY from REFERENCE_CODES where domain = ''LEGISL_BODY'') ref where SS.LEGISLATING_BODY_CODE = REF.CODE )STAT where STAT.STATUTE_CODE = OFF.STATUTE_CODE) a) D on A.LEG_CRCHCO = D.code and A.OFFENCEID::bigint = D.OFFENCEID',
	date_created = '2024-01-06 12:52:10.000',
	user_created = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'CRT_RPT_CHRG';