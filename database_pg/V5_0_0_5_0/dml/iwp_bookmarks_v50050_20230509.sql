UPDATE iwp_bookmarks set  sql_text='select
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
		griev_reason_code = og.GRIEV_REASON_CODE and 
		griev_type = og.GRIEV_TYPE) as ISS_RGISSRSN,
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
	og.GRIEVANCE_ID desc',  modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' WHERE bookmark_name='REQ_GRIEVANC';
