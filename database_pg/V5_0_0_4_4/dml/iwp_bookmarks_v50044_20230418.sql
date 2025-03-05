INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'REQ_GRIEVANC', 'Y', 'COMP', 'Request and Grievances Issue', 'select
	og.GRIEVANCE_ID as ISS_RGISSID,
	 to_char(og.REPORT_DATE, ''MM-DD-YYYY'') as ISS_RGISDTE1,
	 
    to_char(og.REPORT_DATE, ''DD-MM-YYYY'') as ISS_RGISDTE2,
	to_char(og.REPORT_DATE, ''YYYY-MM-DD'') as ISS_RGISDTE3,
	(
	select
		DESCRIPTION
	from
		GRIEVANCE_TYPES
	where
		griev_type = og.GRIEV_TYPE) as ISS_RGISTYP,
	(
	select
		DESCRIPTION
	from
		GRIEVANCE_REASONS
	where
		griev_reason_code = og.GRIEV_REASON_CODE) as ISS_RGISSRSN,
	AGENCY_INCIDENT_ID as ISS_RGOICNUM ,
	CLAIM_AMOUNT as ISS_RGCLAMNT ,
	(
	select
		DESCRIPTION
	from
		AGENCY_LOCATIONS
	where
		AGY_LOC_ID = og.AGY_LOC_ID) as ISS_RGFLLOC,
	COMMENT_TEXT as ISS_RGCOMNT,
	
	 (
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''GRIEV_LEVEL''
		and code = ogt.GRIEV_LEVEL) as ISS_RGCURLVL ,
	  (
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''GRIEV_STATUS''
		and code = ogt.STATUS) as ISS_RGCURSTS
from
	OFFENDER_GRIEVANCES og,
	OFFENDER_GRIEVANCE_TXNS ogt
where
	og.grievance_id = ogt.grievance_id
	and
	og.GRIEVANCE_ID =?
order by
	og.REPORT_DATE desc,
	og.GRIEVANCE_ID desc', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'REQ_GRIEVANC');



INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'REQ_GRI_TRNS', 'Y', 'COMP', 'Request and Grievances Issue Transaction  ', 'select
	 to_char(og.START_DATE, ''MM-DD-YYYY'') as ISS_RGTRDTE1,
	 
    to_char(og.START_DATE, ''DD-MM-YYYY'') as ISS_RGTRDTE2,
	to_char(og.START_DATE, ''YYYY-MM-DD'') as ISS_RGTRDTE3,

	 (
	select
		DESCRIPTION
	from
		GRIEVANCE_TXNS
	where
		griev_type = og.GRIEV_TYPE
		and txn_type = og.TXN_TYPE) as ISS_RGTRNACT,
	 (
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''GRIEV_FINDIN''
		and code = og.FINDING) as ISS_RGTFIND ,
	 (
	select
		STAFF1.LAST_NAME || '', '' || STAFF1.FIRST_NAME DESCRIPTION
	from
		STAFF_MEMBERS STAFF1
	where
		STAFF1.STAFF_ID = og.ASSIGNED_STAFF_ID)as ISS_RGTASSGN,
	 (
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''GRIEV_LEVEL''
		and code = og.GRIEV_LEVEL) as ISS_RGTRNLVL ,
	 
	CREATE_USER_ID as ISS_RGTUID,
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''GRIEV_STATUS''
		and code = og.STATUS) as ISS_RGTSTS ,
	
	PROPOSED_RESPONSE as ISS_RGTPRCOM ,
	OFFICIAL_RESPONSE as ISS_RGTOFRSP,
	case
		when (
		select
			count(*)
		from
			OFFENDER_GRIEVANCE_TXNS
		where
			GRIEVANCE_ID = og.grievance_id
			and STATUS = ''I'') > 0 then null
		else (
		select
			DAYS_RESPOND
		from
			GRIEVANCE_TXNS
		where
			GRIEV_TYPE = og.GRIEV_TYPE
			and TXN_TYPE = og.TXN_TYPE)
	end as ISS_RGTDYLFT
from
	OFFENDER_GRIEVANCE_TXNS og
where
	GRIEVANCE_ID =?
order by
	TXN_SEQ desc',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'OMS_OWNER', NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'REQ_GRI_TRNS');
	
	
	
INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'REQ_GRI_STAF', 'Y', 'COMP', 'Request and Grievances Issue Staff Involved', 'select
	
	STAFF_ID as ISS_RGSINVID,
	(
	select
		concat ( STAFF1.LAST_NAME,
		'','',
		STAFF1.FIRST_NAME,
		'','',
		STAFF1.middle_name )
	from
		STAFF_MEMBERS STAFF1
	where
		STAFF1.STAFF_ID = ogs.STAFF_ID)as ISS_RGSINSNM
from
	OFFENDER_GRIEV_STAFFS ogs
where
	GRIEVANCE_ID =?
', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'REQ_GRI_STAF');


UPDATE iwp_bookmarks
SET  sql_text='select
case when statement_type = ''BEHAV'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY1,
case when statement_type = ''WITNESS'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY2,
case when statement_type = ''VICTIM'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY3,
case when statement_type = ''WEAP'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY4,
case when statement_type = ''PHOTO'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY5,
case when statement_type = ''EVI_BAG'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY6,
case when statement_type = ''OTHER'' then (select description from reference_codes rc where "domain" =''OIC_STMT_TYP'' and code =statement_type) end OIC_INCEVTY7,
case when statement_type = ''BEHAV'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'')  end OIC_INCEVDT1,
case when statement_type = ''WITNESS'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT2,
case when statement_type = ''VICTIM'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT3,
case when statement_type = ''WEAP'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT4,
case when statement_type = ''PHOTO'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT5,
case when statement_type = ''EVI_BAG'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT6,
case when statement_type = ''OTHER'' then to_char(date_of_statement_taken, ''DD-MM-YYYY'') end OIC_INCEVDT7,


case when statement_type = ''BEHAV'' then statement_detail end OIC_INCEVDL1,
case when statement_type = ''WITNESS'' then statement_detail end OIC_INCEVDL2,
case when statement_type = ''VICTIM'' then statement_detail end OIC_INCEVDL3,
case when statement_type = ''WEAP'' then statement_detail end OIC_INCEVDL4,
case when statement_type = ''PHOTO'' then statement_detail end OIC_INCEVDL5,
case when statement_type = ''EVI_BAG'' then statement_detail end OIC_INCEVDL6,
case when statement_type = ''OTHER'' then statement_detail end OIC_INCEVDL7

FROM
    agy_inc_inv_statements
WHERE
   AGY_INC_INVESTIGATION_ID = ?', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER'
WHERE bookmark_name='OFF_CUS_EVID';

UPDATE iwp_bookmarks
SET sql_text='SELECT
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
    AGENCY_INCIDENT_ID=?
    and OIC_INCIDENT_ID=?
	order by  INCIDENT_DATE desc, INCIDENT_TIME desc
	', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER'
WHERE bookmark_name='OFF_IN_CSTDY';


UPDATE iwp_bookmarks
SET  sql_text='select
	og.GRIEVANCE_ID as ISS_RGISSID,
	 to_char(og.REPORT_DATE, ''MM-DD-YYYY'') as ISS_RGISDTE1,
	 
    to_char(og.REPORT_DATE, ''DD-MM-YYYY'') as ISS_RGISDTE2,
	to_char(og.REPORT_DATE, ''YYYY-MM-DD'') as ISS_RGISDTE3,
	(
	select
		DESCRIPTION
	from
		GRIEVANCE_TYPES
	where
		griev_type = og.GRIEV_TYPE) as ISS_RGISTYP,
	(
	select
		DESCRIPTION
	from
		GRIEVANCE_REASONS
	where
		griev_reason_code = og.GRIEV_REASON_CODE) as ISS_RGISSRSN,
	AGENCY_INCIDENT_ID as ISS_RGOICNUM ,
	CLAIM_AMOUNT as ISS_RGCLAMNT ,
	(
	select
		DESCRIPTION
	from
		AGENCY_LOCATIONS
	where
		AGY_LOC_ID = og.AGY_LOC_ID) as ISS_RGFLLOC,
	COMMENT_TEXT as ISS_RGCOMNT,
	
		(
	select
		(select description from REFERENCE_CODES where code= ogt.GRIEV_LEVEL and domain = ''GRIEV_LEVEL'') as description  
	from
		OFFENDER_GRIEVANCE_TXNS ogt
	where
		ogt.grievance_id = og.GRIEVANCE_ID
		and exists (
		select
			''1''
		from
			REFERENCE_CODES
		where
			domain = ''GRIEV_STATUS''
			and CODE = ogt.STATUS
			and PARENT_CODE = ''ACTIVE''))as ISS_RGCURLVL ,
		
		(
	select 
		(select description from REFERENCE_CODES where code= ogt.STATUS and domain = ''GRIEV_STATUS'') as description 
	from
		OFFENDER_GRIEVANCE_TXNS ogt
	where
		ogt.grievance_id = og.GRIEVANCE_ID
		and exists (
		select
			''1''
		from
			REFERENCE_CODES
		where
			domain = ''GRIEV_STATUS''
			and CODE = ogt.STATUS
			and PARENT_CODE = ''ACTIVE''))as ISS_RGCURSTS
from
	OFFENDER_GRIEVANCES og
where
	
	og.GRIEVANCE_ID =?
order by
	og.REPORT_DATE desc,
	og.GRIEVANCE_ID desc', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER', expiry_date=NULL, sql_verified_flag='N', seal_flag=NULL
WHERE bookmark_name='REQ_GRIEVANC';



