INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
SELECT 'OFF_CARE_PLC', 'Y', 'COMP', 'Offender Care in Placement', 'select
	(select description  from reference_codes rc where domain = ''PLACE_TYPE'' and code = PLACEMENT_TYPE) CIP_TYPE,
	(select description  from reference_codes rc where domain = ''PLACE_RSN'' and code = PLACEMENT_REASON_CODE) CIP_REAS ,
	(select description  from agency_locations al where al.agy_loc_id = ocd.AGY_LOC_ID) CIP_FACIL,
	(select description  from reference_codes rc where domain = ''CIP_REQ_BY'' and code = REQ_BY_PER_CODE) CIP_REQBY ,
	(select description  from reference_codes rc where domain = ''CIP_AUTH'' and code = AUTH_BY_PER_CODE) CIP_AUTH ,
	AUTH_BY_PER_NAME CIP_AUTHNAM,
	to_char(EFFECTIVE_DATE, ''DD-MM-YYYY'') as CIP_EFFDAT,
	to_char(EFFECTIVE_TIME, ''HH24:MI'') as CIP_EFFTIM,
	DURATION_TYPE CIP_DURTYP,
	DURATION CIP_DUR,
	to_char(REVIEW_DATE, ''DD-MM-YYYY'') as CIP_REVDAT,
	to_char(EXPIRY_DATE, ''DD-MM-YYYY'') as CIP_EXPDAT,
	to_char(EXPIRY_TIME, ''HH24:MI'') as CIP_EXPTIM,
	COMMENT_TEXT CIP_COM,
	(select description  from reference_codes rc where domain = ''CIP_AUTH'' and code = REL_BY_PER_CODE) CIP_RELBY ,
	REL_BY_PER_NAME CIP_RELBYNAM,
	to_char(RELEASE_DATE, ''DD-MM-YYYY'') as CIP_RELDAT,
	to_char(RELEASE_TIME, ''HH24:MI'') as CIP_RELTIM,
	(SELECT  FLOOR((select
	round(((TO_DATE(TO_CHAR(RELEASE_TIME::timestamp, ''DD-MON-YYYY HH24:MI''), ''DD-MON-YYYY HH24:MI'')-TO_DATE(TO_CHAR(EFFECTIVE_TIME::timestamp, ''DD-MON-YYYY HH24:MI''), ''DD-MON-YYYY HH24:MI''))* 24), 2)HOURS
from
	dual)/24) FROM DUAL) CIP_DAYS,
	(SELECT LPAD(LTRIM( (FLOOR((SELECT ROUND((MOD((select
	round(((TO_DATE(TO_CHAR(RELEASE_TIME::timestamp, ''DD-MON-YYYY HH24:MI''), ''DD-MON-YYYY HH24:MI'')-TO_DATE(TO_CHAR(EFFECTIVE_TIME::timestamp, ''DD-MON-YYYY HH24:MI''), ''DD-MON-YYYY HH24:MI''))* 24), 2)HOURS
from
	dual)::numeric,24)* 60),2) FROM DUAL)::numeric/60))::text),2,''0'')||'':''||LPAD(LTRIM((ROUND(MOD((SELECT ROUND((MOD((select
	round(((TO_DATE(TO_CHAR(RELEASE_TIME::timestamp, ''DD-MON-YYYY HH24:MI''), ''DD-MON-YYYY HH24:MI'')-TO_DATE(TO_CHAR(EFFECTIVE_TIME::timestamp, ''DD-MON-YYYY HH24:MI''), ''DD-MON-YYYY HH24:MI''))* 24), 2)HOURS
from
	dual)::numeric,24)* 60),2) FROM DUAL)::numeric,60)))::text),2,''0'') FROM DUAL) CIP_HOURS
from
	OFFENDER_CIP_DETAILS ocd
where
	OFFENDER_BOOK_ID = ?
	and placement_seq =? 
order by
	EFFECTIVE_DATE desc,
	EFFECTIVE_TIME desc', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL
	WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmarks WHERE bookmark_name = 'OFF_CARE_PLC');
