INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_IN_CSTDY', 'Y', 'COMP', 'Offenses In Custody', 'SELECT
    agency_incident_id  as OIC_INCDNUM,
    to_char(incident_date, ''MM-DD-YYYY'') as OIC_INCDTE1,
    to_char(incident_date, ''DD-MM-YYYY'') as OIC_INCDTE2,
	to_char(incident_date, ''YYYY-MM-DD'') as OIC_INCDTE3,
	
	to_char(report_date, ''MM-DD-YYYY'') as OIC_INCRPDT1,
    to_char(report_date, ''DD-MM-YYYY'') as OIC_INCRPDT2,
	to_char(report_date, ''YYYY-MM-DD'') as OIC_INCRPDT3,
	to_char(incident_time, ''HH24:MI'') as OIC_INCTME,
    initcap(incident_type_desc) as OIC_INCTYP,
    int_loc_description as OIC_INCLOCA,
    oic_incident_id as OIC_OICNUM,
    incident_details as OIC_INCDETL,
    (SELECT STAFF1.LAST_NAME || '', '' ||STAFF1.FIRST_NAME DESCRIPTION  FROM STAFF_MEMBERS STAFF1 where STAFF1.STAFF_ID = reported_staff_id)as OIC_RPTUSNMN
FROM
    v_oic_incidents
WHERE
    AGENCY_INCIDENT_ID= ?
	order by  INCIDENT_DATE desc, INCIDENT_TIME desc',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL 
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_IN_CSTDY');
	
	
	
	INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_IN_CHARG', 'Y', 'COMP', 'Offenses in Custody Charges', 'SELECT
  offenc.oic_offence_code as OIC_INCCHRG,
    offenc.description as OIC_INCCHDS,
    oms_miscellaneous_getdesccode(''OIC_OFN_TYPE'', offenc.oic_offence_type)  as OIC_INCCHTYP,
    offenc.oic_offence_category as OIC_INCCHCT,
    agc.guilty_evidence_text as OIC_INCCHEV  ,
    (select description from reference_codes rc where "domain" =''CHAR_DISP'' and code =agc.evidence_dispose_text) as OIC_INCDISPO
FROM
    agency_incident_charges agc,OIC_OFFENCES offenc
where
    agc.charged_oic_offence_id =offenc.oic_offence_id and
    AGENCY_INCIDENT_ID = ?
    AND CHARGE_SEQ = ?
ORDER BY
    charge_seq',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_IN_CHARG');
	
	
	INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_SCH_HEAR', 'Y', 'COMP', 'Offence in Custody  Hearing Schedule', 'select
	
	case
		when oic_hearing_type = ''INITIAL'' then to_char(oi.hearing_date, ''DD-MM-YYYY'')
	end OIC_INCHRDT1,
	case
		when oic_hearing_type = ''REVIEW'' then to_char(oi.hearing_date, ''DD-MM-YYYY'')
	end OIC_INCHRDT2,
	case
		when oic_hearing_type = ''DISC'' then to_char(oi.hearing_date, ''DD-MM-YYYY'')
	end OIC_INCHRDT3,
	case
		when oic_hearing_type = ''APP'' then to_char(oi.hearing_date, ''DD-MM-YYYY'')
	end OIC_INCHRDT4,
	case
		when oic_hearing_type = ''INITIAL'' then to_char(hearing_time, ''HH24:MI'')
	end OIC_INCHTME1,
	case
		when oic_hearing_type = ''REVIEW'' then to_char(hearing_time, ''HH24:MI'')
	end OIC_INCHTME2,
	case
		when oic_hearing_type = ''DISC'' then to_char(hearing_time, ''HH24:MI'')
	end OIC_INCHTME3,
	case
		when oic_hearing_type = ''APP'' then to_char(hearing_time, ''HH24:MI'')
	end OIC_INCHTME4,
	case
		when oic_hearing_type = ''INITIAL'' then (
		select
			description
		from
			reference_codes rc
		where
			"domain" = ''OIC_HEAR''
			and code = oi.oic_hearing_type)
	end OIC_INCHTYP1,
	case
		when oic_hearing_type = ''REVIEW'' then (
		select
			description
		from
			reference_codes rc
		where
			"domain" = ''OIC_HEAR''
			and code = oi.oic_hearing_type)
	end OIC_INCHTYP2,
	case
		when oic_hearing_type = ''DISC'' then (
		select
			description
		from
			reference_codes rc
		where
			"domain" = ''OIC_HEAR''
			and code = oi.oic_hearing_type)
	end OIC_INCHTYP3,
	case
		when oic_hearing_type = ''APP'' then (
		select
			description
		from
			reference_codes rc
		where
			"domain" = ''OIC_HEAR''
			and code = oi.oic_hearing_type)
	end OIC_INCHTYP4,
	case
		when oic_hearing_type = ''INITIAL'' then (
		select
			description
		from
			agency_internal_locations
		where
			internal_location_id = oi.internal_location_id)
	end OIC_INCHLOC1,
	case
		when oic_hearing_type = ''REVIEW'' then (
		select
			description
		from
			agency_internal_locations
		where
			internal_location_id = oi.internal_location_id)
	end OIC_INCHLOC2,
	case
		when oic_hearing_type = ''DISC'' then (
		select
			description
		from
			agency_internal_locations
		where
			internal_location_id = oi.internal_location_id)
	end OIC_INCHLOC3,
	case
		when oic_hearing_type = ''APP'' then (
		select
			description
		from
			agency_internal_locations
		where
			internal_location_id = oi.internal_location_id)
	end OIC_INCHLOC4,
	case
		when oic_hearing_type = ''INITIAL'' then comment_text
	end OIC_INCHCMT1,
	case
		when oic_hearing_type = ''REVIEW'' then comment_text
	end OIC_INCHCMT2,
	case
		when oic_hearing_type = ''DISC'' then comment_text
	end OIC_INCHCMT3,
	case
		when oic_hearing_type = ''APP'' then comment_text
	end OIC_INCHCMT4
from
	oic_hearings oi
where
	OIC_HEARING_ID = ?
order by
	HEARING_DATE desc,
	HEARING_TIME desc', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_SCH_HEAR');
	
	
	
	
	INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_SH_NOTIF', 'Y', 'COMP', 'Offenses In Custody Hearing Notification', 'select
	case
		when oi.oic_hearing_type = ''INITIAL'' then to_char(ohn.delivery_date, ''DD-MM-YYYY'')
	end OIC_INCNTDT1,
	case
		when oi.oic_hearing_type = ''REVIEW'' then to_char(ohn.delivery_date, ''DD-MM-YYYY'')
	end OIC_INCNTDT2,
	case
		when oi.oic_hearing_type = ''DISC'' then to_char(ohn.delivery_date, ''DD-MM-YYYY'')
	end OIC_INCNTDT3,
	case
		when oi.oic_hearing_type = ''APP'' then to_char(ohn.delivery_date, ''DD-MM-YYYY'')
	end OIC_INCNTDT4,
	case
		when oic_hearing_type = ''INITIAL'' then to_char(ohn.delivery_time , ''HH24:MI'')
	end OIC_INCNTTM1,
	case
		when oic_hearing_type = ''REVIEW'' then to_char(ohn.delivery_time, ''HH24:MI'')
	end OIC_INCNTTM2,
	case
		when oic_hearing_type = ''DISC'' then to_char(ohn.delivery_time, ''HH24:MI'')
	end OIC_INCNTTM3,
	case
		when oic_hearing_type = ''APP'' then to_char(ohn.delivery_time, ''HH24:MI'')
	end OIC_INCNTTM4,
	case
		when oic_hearing_type = ''INITIAL'' then (
		select
			STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
		from
			STAFF_MEMBERS STAFF1
		where
			STAFF1.STAFF_ID = delivery_staff_id)
	end OIC_INCNTDB1,
	case
		when oic_hearing_type = ''REVIEW'' then (
		select
			STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
		from
			STAFF_MEMBERS STAFF1
		where
			STAFF1.STAFF_ID = delivery_staff_id)
	end OIC_INCNTDB2,
	case
		when oic_hearing_type = ''DISC'' then (
		select
			STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
		from
			STAFF_MEMBERS STAFF1
		where
			STAFF1.STAFF_ID = delivery_staff_id)
	end OIC_INCNTDB3,
	case
		when oic_hearing_type = ''APP'' then (
		select
			STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
		from
			STAFF_MEMBERS STAFF1
		where
			STAFF1.STAFF_ID = delivery_staff_id)
	end OIC_INCNTDB4,
	case
		when oic_hearing_type = ''INITIAL'' then ohn.comment_text
	end OIC_INCNCMT1,
	case
		when oic_hearing_type = ''REVIEW'' then ohn.comment_text
	end OIC_INCNCMT2,
	case
		when oic_hearing_type = ''DISC'' then ohn.comment_text
	end OIC_INCNCMT3,
	case
		when oic_hearing_type = ''APP'' then ohn.comment_text
	end OIC_INCNCMT4
from
	oic_hearing_notices ohn,
	oic_hearings oi
where
	ohn.oic_hearing_id = oi.oic_hearing_id
	and 
    
    ohn.OIC_HEARING_ID =?
order by
	DELIVERY_DATE desc,
	DELIVERY_TIME desc',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_SH_NOTIF');
	
	
	INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_CUS_INVE', 'Y', 'COMP', 'Offenses in Custody Investigation', 'SELECT
   to_char(assigned_date, ''DD-MM-YYYY'') as OIC_INCDTASN,
   
   (SELECT STAFF1.LAST_NAME || '', '' ||STAFF1.FIRST_NAME DESCRIPTION  FROM STAFF_MEMBERS STAFF1 where STAFF1.STAFF_ID = investigator_id)as OIC_INCINVST,
    comment_text as OIC_INCINCMT
    
FROM
    agy_inc_investigations
WHERE
   AGY_INC_INVESTIGATION_ID =?
ORDER BY
    assigned_date desc', current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_CUS_INVE');
	
	
	INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_CUS_EVID', 'Y', 'COMP', 'Offenses In Custody Evidence', 'select
case when statement_type = ''BEHAV'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY1,
case when statement_type = ''WITNESS'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY2,
case when statement_type = ''VICTIM'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY3,
case when statement_type = ''WEAP'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY4,
case when statement_type = ''PHOTO'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY5,
case when statement_type = ''EVI_BAG'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY6,
case when statement_type = ''OTHER)'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY7,
case when statement_type = ''BEHAV'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'')  end OIC_INCEVDT1,
case when statement_type = ''WITNESS'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT2,
case when statement_type = ''VICTIM'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT3,
case when statement_type = ''WEAP'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT4,
case when statement_type = ''PHOTO'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT5,
case when statement_type = ''EVI_BAG'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT6,
case when statement_type = ''OTHER)'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT7,


case when statement_type = ''BEHAV'' then statement_detail end OIC_INCEVDL1,
case when statement_type = ''WITNESS'' then statement_detail end OIC_INCEVDL2,
case when statement_type = ''VICTIM'' then statement_detail end OIC_INCEVDL3,
case when statement_type = ''WEAP'' then statement_detail end OIC_INCEVDL4,
case when statement_type = ''PHOTO'' then statement_detail end OIC_INCEVDL5,
case when statement_type = ''EVI_BAG'' then statement_detail end OIC_INCEVDL6,
case when statement_type = ''OTHER)'' then statement_detail end OIC_INCEVDL7

FROM
    agy_inc_inv_statements
WHERE
   AGY_INC_INVESTIGATION_ID = ?', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'N', NULL
   WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_CUS_EVID');
   
   INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_IN_H&P', 'Y', 'COMP', 'Offense In Custody Hearing and Penalties', 'select
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''OIC_HEAR''
		and code = oic_hearing_type) as OIC_INCHPTYP,
	to_char(hearing_date, ''DD-MM-YYYY'') as OIC_INCHPDTE,
	to_char(hearing_time, ''HH24:MI'') as OIC_INCHTME,
	(
	select
		description
	from
		agency_internal_locations
	where
		internal_location_id = oi.internal_location_id) as OIC_INCHLOC,
	(
	select
		STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
	from
		STAFF_MEMBERS STAFF1
	where
		STAFF1.STAFF_ID = hearing_staff_id)as OIC_INCHHEAB,
	representative_text as OIC_INCHOTHR,
	comment_text as OIC_INCHCMNT
from
	oic_hearings oi
where
	OIC_HEARING_ID =?
order by
	hearing_date desc,
	hearing_time desc',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL, 'OMS_OWNER', NULL, 'N', NULL
	 WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_IN_H&P');
   
   INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_IN_H_RES', 'Y', 'COMP', 'Offense In Custody Hearing-Results', 'select
	offe.oic_offence_code as OIC_INCROCHG,
	offe.description as OIC_INCROCTY,
	oms_miscellaneous_getdesccode(''OIC_OFN_TYPE'',
	offe.oic_offence_type) as OIC_INCRODSC,
	offd.oic_offence_code as OIC_INCRRCHG,
	offd.description as OIC_INCRRTYP,
	oms_miscellaneous_getdesccode(''OIC_OFN_TYPE'',
	offd.oic_offence_type) as OIC_INCRRODS,
	
	 (
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''OIC_PLEA''
		and code = ohr.plea_finding_code) as OIC_INCRPLEA,
	 (
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''OIC_FINDING''
		and code = ohr.finding_code) as OIC_INCRFNDG
from
	oic_hearing_results ohr,
	OIC_OFFENCES offe,
	OIC_OFFENCES offd
where
	offe.oic_offence_id = ohr.oic_offence_id
	and
   offd.oic_offence_id = ohr.oic_result_offence_id
	and
   ohr.OIC_HEARING_ID =?',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL, 'OMS_OWNER', NULL, 'N', NULL
   WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_IN_H_RES');
   
   INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_IN_C_PEN', 'Y', 'COMP', 'Offense In Custody Penalties', 'select
	sanction_seq as OIC_INCPDMLN,
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''OIC_SANCT''
		and code = oic_sanction_code) as OIC_INCCDMTP,
	sanction_months as OIC_INCCDMMO,
	sanction_days as OIC_INCCDMDY,
	compensation_amount as OIC_INCCDRES,
	to_char(effective_date, ''DD-MM-YYYY'') as OIC_INCCDEDT,
	consecutive_sanction_seq as OIC_INCCDCLN,
	comment_text as OIC_INCCDCMT,
	(
	select
		oic_incident_id
	from
		OIC_HEARINGS
	where
		oic_hearing_id = oos.oic_hearing_id) as OIC_INCCDOIN,
	status as OIC_INCCDSTS,
	to_char(status_date, ''DD-MM-YYYY'') as OIC_INCCDSDT
from
	offender_oic_sanctions oos
where
	OFFENDER_BOOK_ID = ?
	and SANCTION_SEQ = ?
order by
	effective_date desc', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER',NULL, 'OMS_OWNER', NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_IN_C_PEN');
	
INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CMG_ST_SAMPL', 'Y', 'COMP', 'Substance Testing-sample', 'select
	to_char(sample_date, ''MM-DD-YYYY'') as CMG_STDTTKN1,
	to_char(sample_date, ''DD-MM-YYYY'') as CMG_STDTTKN2,
	to_char(sample_date, ''YYYY-MM-DD'') as CMG_STDTTKN3,
	(select description from reference_codes rc where "domain" =''SUB_TES_TYPE'' and code =sample_type)  as CMG_STSTYPE,
	(select description from reference_codes rc where "domain" =''SUB_TES_RSN'' and code =sample_reason) as CMG_STSRSN,
	(
	select
		STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
	from
		STAFF_MEMBERS STAFF1
	where
		STAFF1.STAFF_ID = witness_staff_id)as CMG_STWIT,
	(
	select
		STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
	from
		STAFF_MEMBERS STAFF1
	where
		STAFF1.STAFF_ID = taken_staff_id)as CMG_STTKNBY,
	to_char(sample_test_date, ''MM-DD-YYYY'') as CMG_STDTTST1,
	to_char(sample_test_date, ''DD-MM-YYYY'') as CMG_STDTTST2,
	to_char(sample_test_date, ''YYYY-MM-DD'') as CMG_STDTTST3,
	external_test_agency_flag as CMG_STEXAGY,
	case
		when external_test_agency_flag = ''Y'' then (
		select
			CORPORATE_NAME
		from
			CORPORATES
		where
			CORPORATE_ID = TEST_CORPORATE_ID)
		else (
		select
			STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
		from
			STAFF_MEMBERS STAFF1
		where
			STAFF1.STAFF_ID = TEST_STAFF_ID)
	end as CMG_STTSTBY
,
	case
		when (
		select
			count(*)
		from
			OFFENDER_SAMPLE_SUBSTANCES
		where
			OFFENDER_SAMPLE_ID = os.offender_sample_id
			and RESULT_CODE = ''P'' )> 0 then ''Y''
		else ''N''
	end
    as CMG_STTSPOS,
	comment_text as CMG_STCOMNT
from
	OFFENDER_SAMPLES os
where
	OFFENDER_SAMPLE_ID=?
order by
	SAMPLE_DATE desc', current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL, 'OMS_OWNER', NULL, 'N', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CMG_ST_SAMPL');

INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'CMG_ST_TESTE', 'Y', 'COMP', 'Suabstance Testing-Tested', 'select
	(select description from reference_codes rc where "domain" =''SUBSTANCE'' and code =substance_code) as CMG_STSUBS,
	(select description from reference_codes rc where "domain" =''SUB_TES_RSLT'' and code =result_code) as CMG_STRSLT, 
	(select description from reference_codes rc where "domain" =''SUB_TES_DISP'' and code =disposition_code) as CMG_STDISPO, 
	comment_text as CMG_STTSCOM
from
	OFFENDER_SAMPLE_SUBSTANCES
where
	OFFENDER_SAMPLE_ID =?
order by
	case
		when result_code =(
		select
			code
		from
			reference_codes
		where
			domain = ''SUB_TES_RSLT''
			and code = result_code
			and parent_code = ''P'') then 1
	end ,
	oms_miscellaneous_getdesccode(''SUBSTANCE'',
	substance_code),
	SUBSTANCE_CODE', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'N', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'CMG_ST_TESTE');	