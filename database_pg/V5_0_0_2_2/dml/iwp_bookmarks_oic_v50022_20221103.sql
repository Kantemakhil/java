Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('OIC_APP','Y','COMP','OIC Incident APP composite bookmark','select
	to_char(hearing_date, ''DD-MM-YYYY'') as OIC_INCHRDT4,
	to_char(hearing_time, ''HH24:MI'') as OIC_INCHTME4,
	(
	select
		description
	from
		(
		select
			C.DESCRIPTION ,
			C.INTERNAL_LOCATION_ID
		from
			INT_LOC_USAGE_LOCATIONS A ,
			INTERNAL_LOCATION_USAGES B ,
			AGENCY_INTERNAL_LOCATIONS C
		where
			A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID
			and B.INTERNAL_LOCATION_USAGE = ''OIC''
			and A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID
			and not exists (
			select
				1
			from
				INT_LOC_USAGE_LOCATIONS
			where
				PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID )
		order by
			C.LIST_SEQ)A
	where
		A.internal_location_id = OH.internal_location_id)as OIC_INCHLOC4,
	(
	select
		description
	from
		reference_codes rc
	where
		domain = ''OIC_HEAR''
		and code = oh.oic_hearing_type) as OIC_INCHTYP4,
	oh.comment_text as OIC_INCHCMT4,
	(
	select
		STRING_AGG(to_char(delivery_date, ''DD-MM-YYYY''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES ohn
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTDT4,
		(
	select
		STRING_AGG(to_char(delivery_time, ''HH24:MI''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTTM4,
	(
	select
		initcap(string_agg((STAFF1.LAST_NAME || ''-'' || STAFF1.FIRST_NAME) , '','' order by oic_hearing_id))as Description
	from
		STAFF_MEMBERS STAFF1
	where
		staff_id in(
		select
			delivery_staff_id
		from
			OIC_HEARING_NOTICES
		where
			oic_hearing_id = oh.oic_hearing_id)) as OIC_INCNTDB4,
	(
	select
		STRING_AGG(comment_text, '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id) as OIC_INCNCMT4
from
	OIC_HEARINGS OH,
	agency_incident_parties aip
where
	oh.oic_incident_id = aip.oic_incident_id
	and aip.offender_book_id =?
	and
	oic_hearing_type = ''APP''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL,NULL,NULL,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('OIC_DISC','Y','COMP','OIC incident for DISC composite bookmark','select
	to_char(hearing_date, ''DD-MM-YYYY'') as OIC_INCHRDT3,
	to_char(hearing_time, ''HH24:MI'') as OIC_INCHTME3,
	(
	select
		description
	from
		(
		select
			C.DESCRIPTION ,
			C.INTERNAL_LOCATION_ID
		from
			INT_LOC_USAGE_LOCATIONS A ,
			INTERNAL_LOCATION_USAGES B ,
			AGENCY_INTERNAL_LOCATIONS C
		where
			A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID
			and B.INTERNAL_LOCATION_USAGE = ''OIC''
			and A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID
			and not exists (
			select
				1
			from
				INT_LOC_USAGE_LOCATIONS
			where
				PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID )
		order by
			C.LIST_SEQ)A
	where
		A.internal_location_id = OH.internal_location_id)as OIC_INCHLOC3,
	(
	select
		description
	from
		reference_codes rc
	where
		domain = ''OIC_HEAR''
		and code = oh.oic_hearing_type) as OIC_INCHTYP3,
	oh.comment_text as OIC_INCHCMT3,
	(
	select
		STRING_AGG(to_char(delivery_date, ''DD-MM-YYYY''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES ohn
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTDT3,
		(
	select
		STRING_AGG(to_char(delivery_time, ''HH24:MI''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTTM3,
	(
	select
		initcap(string_agg((STAFF1.LAST_NAME || ''-'' || STAFF1.FIRST_NAME) , '','' order by oic_hearing_id))as Description
	from
		STAFF_MEMBERS STAFF1
	where
		staff_id in(
		select
			delivery_staff_id
		from
			OIC_HEARING_NOTICES
		where
			oic_hearing_id = oh.oic_hearing_id)) as OIC_INCNTDB3,
	(
	select
		STRING_AGG(comment_text, '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id) as OIC_INCNCMT3
from
	OIC_HEARINGS OH,
	agency_incident_parties aip
where
	oh.oic_incident_id = aip.oic_incident_id
	and aip.offender_book_id =?
	and
	oic_hearing_type = ''DISC''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL,NULL,NULL,'N',NULL);

Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('OIC_REVIEW','Y','COMP','OIC incident Review composite bookmark','select
	to_char(hearing_date, ''DD-MM-YYYY'') as OIC_INCHRDT2,
	to_char(hearing_time, ''HH24:MI'') as OIC_INCHTME2,
	(
	select
		description
	from
		(
		select
			C.DESCRIPTION ,
			C.INTERNAL_LOCATION_ID
		from
			INT_LOC_USAGE_LOCATIONS A ,
			INTERNAL_LOCATION_USAGES B ,
			AGENCY_INTERNAL_LOCATIONS C
		where
			A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID
			and B.INTERNAL_LOCATION_USAGE = ''OIC''
			and A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID
			and not exists (
			select
				1
			from
				INT_LOC_USAGE_LOCATIONS
			where
				PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID )
		order by
			C.LIST_SEQ)A
	where
		A.internal_location_id = OH.internal_location_id)as OIC_INCHLOC2,
	(
	select
		description
	from
		reference_codes rc
	where
		domain = ''OIC_HEAR''
		and code = oh.oic_hearing_type) as OIC_INCHTYP2,
	oh.comment_text as OIC_INCHCMT2,
	(
	select
		STRING_AGG(to_char(delivery_date, ''DD-MM-YYYY''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES ohn
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTDT2,
		(
	select
		STRING_AGG(to_char(delivery_time, ''HH24:MI''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTTM2,
	(
	select
		initcap(string_agg((STAFF1.LAST_NAME || ''-'' || STAFF1.FIRST_NAME) , '','' order by oic_hearing_id))as Description
	from
		STAFF_MEMBERS STAFF1
	where
		staff_id in(
		select
			delivery_staff_id
		from
			OIC_HEARING_NOTICES
		where
			oic_hearing_id = oh.oic_hearing_id)) as OIC_INCNTDB2,
	(
	select
		STRING_AGG(comment_text, '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id) as OIC_INCNCMT2
from
	OIC_HEARINGS OH,
	agency_incident_parties aip
where
	oh.oic_incident_id = aip.oic_incident_id
	and aip.offender_book_id =?
	and
	oic_hearing_type = ''REVIEW''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL,NULL,NULL,'N',NULL);


Insert into iwp_bookmarks(bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) values('OIC_INITIAL','Y','COMP','OIC Incident Initial composite bookmark','select
	to_char(hearing_date, ''DD-MM-YYYY'') as OIC_INCHRDT1,
	to_char(hearing_time, ''HH24:MI'') as OIC_INCHTME1,
	(
	select
		description
	from
		(
		select
			C.DESCRIPTION ,
			C.INTERNAL_LOCATION_ID
		from
			INT_LOC_USAGE_LOCATIONS A ,
			INTERNAL_LOCATION_USAGES B ,
			AGENCY_INTERNAL_LOCATIONS C
		where
			A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID
			and B.INTERNAL_LOCATION_USAGE = ''OIC''
			and A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID
			and not exists (
			select
				1
			from
				INT_LOC_USAGE_LOCATIONS
			where
				PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID )
		order by
			C.LIST_SEQ)A
	where
		A.internal_location_id = OH.internal_location_id)as OIC_INCHLOC1,
	(
	select
		description
	from
		reference_codes rc
	where
		domain = ''OIC_HEAR''
		and code = oh.oic_hearing_type) as OIC_INCHTYP1,
	oh.comment_text as OIC_INCHCMT1,
	(
	select
		STRING_AGG(to_char(delivery_date, ''DD-MM-YYYY''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES ohn
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTDT1,
		(
	select
		STRING_AGG(to_char(delivery_time, ''HH24:MI''), '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id)as OIC_INCNTTM1,
	(
	select
		initcap(string_agg((STAFF1.LAST_NAME || ''-'' || STAFF1.FIRST_NAME) , '','' order by oic_hearing_id))as Description
	from
		STAFF_MEMBERS STAFF1
	where
		staff_id in(
		select
			delivery_staff_id
		from
			OIC_HEARING_NOTICES
		where
			oic_hearing_id = oh.oic_hearing_id)) as OIC_INCNTDB1,
	(
	select
		STRING_AGG(comment_text, '','' order by oic_hearing_id)
	from
		OIC_HEARING_NOTICES
	where
		oic_hearing_id = oh.oic_hearing_id) as OIC_INCNCMT1
from
	OIC_HEARINGS OH,
	agency_incident_parties aip
where
	oh.oic_incident_id = aip.oic_incident_id
	and aip.offender_book_id =?
	and
	oic_hearing_type = ''INITIAL''',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL,NULL,NULL,'N',NULL);