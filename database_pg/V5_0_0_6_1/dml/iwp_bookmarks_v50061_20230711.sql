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
	'CUS_INT_PART',
	'Y',
	'COMP',
	'Custodial Interested Parties',
	'SELECT PARTY_TYPE ,PARTY_DESCRIPTION ,PARTY_COMMENT FROM OFFENDER_INTERESTED_PARTIES where OFFENDER_BOOK_ID =?  and RECORD_ID=? and RECORD_TYPE =?',
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
		bookmark_name = 'CUS_INT_PART');

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
	'OFF_OBS_PERI',
	'Y',
	'COMP',
	'Offender Observation Periods',
	'select
	oop.obs_period_id as OBS_OOPOBID,
	(select
		description
from
		reference_codes rc
where
		"domain" = ''OBSRVATN_TYP''
	and code = (
	select
		observation_type
	from
		offender_observation_types
	where
		OBS_TYPE_VERSION_ID = oop.obs_type_version_id) )as OBS_OOPOTYP,
		oot.frequency as OBS_OOPFREQ,
	oot.notification_flag as OBS_OOPNTFY,
	oot.notification_timing as OBS_OOPNBUF,
	to_char(oop.start_datetime, ''DD-MM-YYYY'') as  OBS_OOPSDTE,
	to_char(oop.start_datetime, ''HH24:MI'') as   OBS_OOPSTME,
	(select description from reference_codes where "domain"=''END_RSN_CODE'' and code=oop.end_reason_code)as OBS_OOPERSN,
	to_char(oop.end_datetime, ''DD-MM-YYYY'') as  OBS_OOPEDTE,
	to_char(oop.end_datetime, ''HH24:MI'') as   OBS_OOPETME,
	oop.status_code as OBS_OOPSTAT
from
	off_observation_periods oop,
	offender_observation_types oot
where
	obs_period_id =?	and oop.obs_type_version_id = oot.obs_type_version_id',
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
		bookmark_name = 'OFF_OBS_PERI');

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
	'OFF_OBS_CHEC',
	'Y',
	'COMP',
	'Offender Observation Periods Child Block',
	'select
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
		obs_type_version_id =? ) as OBS_OOPCFREQ,
	(
	select
		last_name || '','' || '' '' || first_name as user_id
	from
		staff_members sm
	where
		staff_id = obp.performing_staff_id ) as OBS_OOPCSTAF
from
	OFF_OBS_PERIOD_CHECKS obp
where
	OBS_PERIOD_ID =?
order by
	check_id desc
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
		bookmark_name = 'OFF_OBS_CHEC');

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
	'OFFEN_VISITS',
	'Y',
	'COMP',
	'Offender Visits',
	'select 
to_char(ov.visit_date, ''DD-MM-YYYY'') as VIS_CVTSDTE,
avs.time_slot_seq as VIS_CVTSSLOT,
to_char(ov.start_time, ''HH24:MI'') as   VIS_CVTSSTRT,
to_char(ov.end_time, ''HH24:MI'') as    VIS_CVTSEND,
(select description from reference_codes where "domain"=''VISIT_TYPE'' and code=ov.visit_type)as  VIS_CVTSVTYP,
ov.comment_text as VIS_CVTSCMNT,
(select description from agency_internal_locations ail where internal_location_id=ov.visit_internal_location_id) as VIS_CVTSLOCA,
case when (coalesce(ov.event_outcome, ''ATT''::character varying)) =''ATT'' then ''Y''::text
		else ''N''::text end as  VIS_CVTSATND ,
		(select description from reference_codes where "domain"=''VIS_COMPLETE'' and code=ovv.event_status)as VIS_CVTSCOMP,
		(select description from reference_codes where "domain"=''MOVE_CANC_RS'' and code=ovv.outcome_reason_code)as VIS_CVTSCRSN
		
		from 
		offender_visits ov,offender_visit_visitors ovv,AGENCY_VISIT_SLOTS avs
	where ov.offender_visit_id = ovv.offender_visit_id and 
avs.agency_visit_slot_id =ov.agency_visit_slot_id and
	ovv.event_id > 0  
	and  not(coalesce(ovv.offender_book_id::text,''''::text) =''''::text) and ovv.offender_book_id =? and 
ov.offender_visit_id =?',
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
		bookmark_name = 'OFFEN_VISITS');

insert
	into
	oms_owner.iwp_bookmarks
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
	'OFFN_VISTORS',
	'Y',
	'COMP',
	'Offender  Visitors',
	'select
	vo.PERSON_ID as VIS_CVTSVUID,
	p.last_name as VIS_CVTSVLN,
	p.first_name as VIS_CVTSVFN,
	(
	select
		description
	from
		reference_codes
	where
		"domain" = ''CONTACTS''
		and code = ocp.contact_type)as VIS_CVTSVCT,
			(
	select
		description
	from
		reference_codes
	where
		"domain" = ''RELATIONSHIP''
		and code = ocp.relationship_type)as VIS_CVTSVREL,
	extract(year
from
	age(ov.visit_date, p.birthdate)) as VIS_CVTSVAGE,
	
vo.COMMENT_TEXT as VIS_CVTSVCOM,
	case when (coalesce(vo.EVENT_OUTCOME, ''ATT''::character varying)) =''ATT'' then ''Y''::text
		else ''N''::text end as  VIS_CVTSVATD  ,
	(
	select
		RC.PARENT_CODE
	from
		VISITOR_RESTRICTIONS VR,
		REFERENCE_CODES RC
	where
		RC.DOMAIN = ''VST_RST_TYPE''
		and RC.CODE = VR.VISIT_RESTRICTION_TYPE
		and VR.PERSON_ID = vo.person_id
		and VR.EFFECTIVE_DATE <= ov.visit_date
		and (VR.EXPIRY_DATE is null
			or VR.EXPIRY_DATE > ov.visit_date)) as  VIS_CVTSVBAN,
			(
	select
		rc.parent_code
	from
		offender_person_restricts ocr,
		reference_codes rc,
		offender_contact_persons ocp
	where
		rc.domain = ''VST_RST_TYPE''
		and
rc.code = ocr.restriction_type
		and ocr.offender_contact_person_id = ocp.offender_contact_person_id
		and ocp.person_id = vo.person_id and
ocp.offender_book_id = vo.VISIT_OFFENDER_BOOK_ID
		and ocr.restriction_effective_date <= ov.visit_date
		and (ocr.restriction_expiry_date is null
			or 
ocr.restriction_expiry_date >ov.visit_date)) as VIS_CVTSVRES
from
	V_OFFENDER_VISIT_VISITORS vo,
	persons p,
	offender_visits ov,
	offender_contact_persons ocp
where
	vo.OFFENDER_BOOK_ID is null
	and vo.PERSON_ID is not null
	and p.person_id = vo.person_id
	and 
	ov.offender_visit_id = vo.offender_visit_id
	and  
	ocp.offender_book_id = vo.VISIT_OFFENDER_BOOK_ID
	and 
	ocp.person_id = vo.PERSON_ID
	and ( ( vo.OFFENDER_VISIT_ID is not null
		and vo.OFFENDER_VISIT_ID=? 
		and vo.VISIT_OFFENDER_BOOK_ID=?  ) )
order by
	GROUP_LEADER_FLAG desc,
	tag_visits_visitor_lastname_firstname(vo.PERSON_ID)',
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
		bookmark_name = 'OFFN_VISTORS');

insert
	into
	oms_owner.iwp_bookmarks
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
	'VISIT_OFFEND',
	'Y',
	'COMP',
	'Visit Offenders ',
	'select
	voff.offender_id_display as VIS_CVTSVOID,
	voff.last_name as VIS_CVTSVOLN,
	voff.first_name as VIS_CVTSVOFN,
	voff.living_unit_description as VIS_CVTSVOFA,
     case
		when (coalesce(ovv.EVENT_OUTCOME, ''ATT''::character varying)) = ''ATT'' then ''Y''::text
		else ''N''::text
	end as VIS_CVTSVOAT ,
	(
	select
		description
	from
		reference_codes
	where
		"domain" = ''CONTACTS''
		and code = ocp.contact_type)as VIS_CVTSVOCT,
			(
	select
		description
	from
		reference_codes
	where
		"domain" = ''RELATIONSHIP''
		and code = ocp.relationship_type)as VIS_CVTSVORL,
		(
	select
		rc.parent_code
	from
		offender_restrictions ors,
		reference_codes rc
	where
		ors.offender_book_id = ovv.OFFENDER_BOOK_ID
		and rc.domain = ''VST_RST_TYPE''
		and rc.code = ors.restriction_type
		and ors.effective_date::date <= ov.visit_date
		and (ors.expiry_date is null
			or ors.expiry_date::date >ov.visit_date)
	order by
		rc.parent_code)as VIS_CVTSVORS
from
	OFFENDER_VISIT_VISITORS ovv,
	v_name_search2_fn(:USER_ID) voff,
	offender_contact_persons ocp,
	offender_bookings ob,
	offender_visits ov
where
	ovv.OFFENDER_BOOK_ID is not null
	and ovv.PERSON_ID is null
	and ovv.offender_book_id = voff.offender_book_id
	and ov.offender_visit_id = ovv.offender_visit_id
	and ob.offender_book_id = ovv.offender_book_id
	and ocp.contact_root_offender_id = ob.root_offender_id
	and ovv.OFFENDER_BOOK_ID != ocp.offender_book_id
	and ocp.offender_book_id =?
	and ovv.OFFENDER_VISIT_ID =?
order by ovv.CREATE_DATETIME desc',
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
		bookmark_name = 'VISIT_OFFEND');

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
	'RELEASE_PLAN',
	'Y',
	'COMP',
	'Release Plan Details ',
	'select to_char(RP.CREATE_DATETIME, ''DD-MM-YYYY'') CMG_RPCRDTE, ( select LAST_NAME || '', '' || FIRST_NAME from staff_members sm where staff_id = RP.CASE_MANAGER_ID) CMG_RPCAMNGR, ( select LAST_NAME || '', '' || FIRST_NAME from staff_members sm where staff_id = RP.PAROLE_AGENT_ID) CMG_RPPAAGNT, ( select description from reference_codes where "domain" = ''RPLAN_STS'' and code = RP.PLAN_STATUS) CMG_RPPLSTS, to_char(RP.MODIFY_DATETIME, ''DD-MM-YYYY'') as CMG_RPUPDTE, RP.UPDATED_BY CMG_RPUPDBY, RP.HOUSING_COMMENT CMG_RPPDRCOM, (select description from reference_codes where "domain"=''EMPLOY_CRA'' and code=RP.EMPLOYMENT_STATUS) CMG_RPPDEMST, RP.EMPLOYMENT_COMMENT CMG_RPPDECOM, RP.AGENT_RECOMMEND CMG_RPPDAREC, case when ASSESSMENT_SEQ is null and plan_status = ''INPRG'' then ( select REVIEW_SUP_LEVEL_TYPE from ASSESSMENTS ASS, OFFENDER_ASSESSMENTS OA where ASS.ASSESSMENT_ID = OA.ASSESSMENT_TYPE_ID and OFFENDER_BOOK_ID = RP.OFFENDER_BOOK_ID and ASSESSMENT_CODE = ( select PROFILE_VALUE_2 from SYSTEM_PROFILES where PROFILE_TYPE = ''CLIENT'' and PROFILE_CODE = ''RPLAN_ASSESS'') and ASSESS_STATUS = ''A'') else( select REVIEW_SUP_LEVEL_TYPE from OFFENDER_ASSESSMENTS where OFFENDER_BOOK_ID = RP.OFFENDER_BOOK_ID and ASSESSMENT_SEQ = RP.ASSESSMENT_SEQ ) end CMG_RPPDCMRA, case when ASSESSMENT_SEQ is null and plan_status = ''INPRG'' then ( select to_char(ASSESSMENT_DATE, ''DD-MM-YYYY'') from ASSESSMENTS ASS, OFFENDER_ASSESSMENTS OA where ASS.ASSESSMENT_ID = OA.ASSESSMENT_TYPE_ID and OFFENDER_BOOK_ID = RP.OFFENDER_BOOK_ID and ASSESSMENT_CODE = ( select PROFILE_VALUE_2 from SYSTEM_PROFILES where PROFILE_TYPE = ''CLIENT'' and PROFILE_CODE = ''RPLAN_ASSESS'') and ASSESS_STATUS = ''A'') else( select to_char(ASSESSMENT_DATE, ''DD-MM-YYYY'') from OFFENDER_ASSESSMENTS where OFFENDER_BOOK_ID = RP.OFFENDER_BOOK_ID and ASSESSMENT_SEQ = RP.ASSESSMENT_SEQ ) end CMG_RPPDADTE, case when plan_status = ''INPRG'' then (select (coalesce(street_information, '''') || '' '' || '' #'' || coalesce(suite_number, '''') || '' '' || coalesce(city_name, '''') || '' '' || coalesce(prov_state_desc, '''') || '' '' || coalesce(zip_postal_code, '''')) as proposedHousing from v_addresses va where va.address_id = RP.address_id) else ( SELECT street_information||'' ''|| '' #''||suite_number||'' ''|| city_name||'' ''|| prov_state_desc||'' ''|| zip_postal_code PROPOSED_HOUSING FROM v_addresses va WHERE owner_class = ''OFF'' AND owner_id = BKG.root_offender_id AND primary_flag = ''Y'')end as CMG_RPPDPHOU, (SELECT EMPLOYER_NAME||'' - ''|| OMS_MISCELLANEOUS_GETDESCCODE(''OCCUPATION'', OCCUPATIONS_CODE)||'' - ''|| OMS_MISCELLANEOUS_GETDESCCODE(''EMPLOY_STS'', EMPLOYMENT_POST_CODE) PROPOSED_EMPLOYMENT FROM OFFENDER_EMPLOYMENTS WHERE OFFENDER_BOOK_ID = RP.OFFENDER_BOOK_ID AND EMPLOY_SEQ = RP.employ_seq)as CMG_RPPDPEMP, case when RP.address_id is not null then (select RC.DESCRIPTION from ADDRESS_USAGES AU, REFERENCE_CODES RC where AU.ADDRESS_ID = RP.address_id and AU.ACTIVE_FLAG = ''Y'' and RC.DOMAIN = ''ADDRESS_TYPE'' and RC.CODE = AU.ADDRESS_USAGE and coalesce(AU.MODIFY_DATETIME, AU.CREATE_DATETIME) = ( select MAX(coalesce(AU1.MODIFY_DATETIME, AU1.CREATE_DATETIME)) from ADDRESS_USAGES AU1 where AU.ADDRESS_ID = AU1.ADDRESS_ID)) else(select RC.DESCRIPTION from ADDRESS_USAGES AU, REFERENCE_CODES RC where AU.ADDRESS_ID = (select address_id FROM v_addresses va WHERE owner_class = ''OFF'' AND owner_id = BKG.root_offender_id AND primary_flag = ''Y'') and AU.ACTIVE_FLAG = ''Y'' and RC.DOMAIN = ''ADDRESS_TYPE'' and RC.CODE = AU.ADDRESS_USAGE and coalesce(AU.MODIFY_DATETIME, AU.CREATE_DATETIME) = ( select MAX(coalesce(AU1.MODIFY_DATETIME, AU1.CREATE_DATETIME)) from ADDRESS_USAGES AU1 where AU.ADDRESS_ID = AU1.ADDRESS_ID)) end as CMG_RPPDADTY, case when (SELECT count(*) FROM OFFENDER_SENT_CONDITIONS WHERE OFFENDER_BOOK_ID = RP.OFFENDER_BOOK_ID AND OBJECT_ID = RP.RELEASE_PLAN_ID AND OBJECT_TYPE = ''RELEASE'')>0 then ''Y'' else ''N'' end as CMG_RPPDCOND from RELEASE_PLANS RP,OFFENDER_BOOKINGS BKG where RP.OFFENDER_BOOK_ID = BKG.OFFENDER_BOOK_ID and RP.RELEASE_PLAN_ID =?',
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
		bookmark_name = 'RELEASE_PLAN');