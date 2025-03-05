
 OSIOSEAR_FIND_RGSEARCHTYPE {
    select 
	CODE ,
	DESCRIPTION,
	LIST_SEQ
from
	REFERENCE_CODES
where
	domain = 'SEARCH_TYPE'
	and ACTIVE_FLAG = 'Y'
	and EXPIRED_DATE is null
order by
	LIST_SEQ
	   }

 OSIOSEAR_FIND_RGIDENTIFIERTYPE {
     select
	DESCRIPTION,
	LIST_SEQ,
	CODE
from
	REFERENCE_CODES
where
	domain = 'ID_TYPE'
	and ACTIVE_FLAG = 'Y'
	and EXPIRED_DATE is null
order by
	LIST_SEQ
}
 OSIOSEAR_FIND_RGGENDER
{
select
	CODE ,
	DESCRIPTION ,
	LIST_SEQ
from
	REFERENCE_CODES
where
	domain = 'SEX'
	and ACTIVE_FLAG = 'Y'
	and EXPIRED_DATE is null
order by
	LIST_SEQ
}

 OSIOSEAR_FIND_RGCRTLOCATION
{
select
	DESCRIPTION ,
	AGY_LOC_ID TO_AGY_LOC_ID ,
	LIST_SEQ
from
	AGENCY_LOCATIONS
where
	AGY_LOC_ID in (
	select
		AGY_LOC_ID
	from
		CASELOAD_AGENCY_LOCATIONS
	where
		CASELOAD_ID = :CASELOAD_ID )
	and AGENCY_LOCATION_TYPE = 'CRT'
	and DEACTIVATION_DATE is null
order by
	LIST_SEQ ,
	DESCRIPTION
}

 FIND_REFERENCE_CODES_DESC_BY_LISTSEQ_CODE {
    SELECT
  CODE, DESCRIPTION
FROM REFERENCE_CODES
WHERE
  DOMAIN = :DOMAIN AND
  (ACTIVE_FLAG = 'Y')
ORDER BY
  LIST_SEQ, CODE

}

 OSIOSEAR_IMAGE_FIND_IMAGES
{
select
	I.IMAGE_ID,
	I.CAPTURE_DATE,
	I.IMAGE_OBJECT_TYPE,
	I.IMAGE_OBJECT_ID,
	I.IMAGE_OBJECT_SEQ,
	I.IMAGE_VIEW_TYPE,
	I.IMAGE_THUMBNAIL,
	I.ACTIVE_FLAG,
	I.ORIENTATION_TYPE,
	I.SEAL_FLAG,
	I.CREATE_DATETIME,
	I.CREATE_USER_ID,
	I.MODIFY_DATETIME,
	I.MODIFY_USER_ID
from
	IMAGES I
where
}
 OSIOSEAR_SEARCH_RESULTS_POSTQUERY_NAME_TYPE_CUR
{
select
	OMS_MISCELLANEOUS_GETDESCCODE('NAME_TYPE',
	ALIAS_NAME_TYPE)
from
	OFFENDERS
where
	OFFENDER_ID =:OFFENDER_ID
}

 OSIOSEAR_SEARCH_RESULTS_WHENNEWRECORDINSTANCE_C_OFF_FINGER_PRINTS
{
SELECT * FROM  OFFENDER_FINGERPRINTS WHERE ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID
}
 OSIOSEAR_SEARCH_RESULTS_WHENNEWITEMINSTANCE_LV_OBI
{
SELECT OFFENDER_BOOK_ID FROM OFFENDER_BOOKINGS  WHERE OFFENDER_ID = :OFFENDER_ID
}
 FIND_PROFILEVALUE_SYSTEM_PROFILES
{
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE='CLIENT' AND PROFILE_CODE='IMAGING_FORM'
}
  FIND_IMAGES_GETTING_1
{
 SELECT 1  FROM IMAGES WHERE IMAGE_OBJECT_ID  = :IMAGE_OBJECT_ID   AND IMAGE_OBJECT_TYPE=  :IMAGE_OBJECT_TYPE AND ACTIVE_FLAG='Y'
}

 FIND_IMAGES_GETTING_1_PASS3_PARAMS
{
 SELECT 1 FROM IMAGES WHERE IMAGE_OBJECT_ID  =:IMAGE_OBJECT_ID  AND IMAGE_OBJECT_TYPE=:IMAGE_OBJECT_TYPE AND IMAGE_OBJECT_SEQ = :IMAGE_OBJECT_SEQ AND ACTIVE_FLAG='Y'
}
 OSIOSEAR_CREATE_FORM_GLOBALS
{
SELECT DESCRIPTION  FROM OMS_MODULES WHERE MODULE_NAME = :MODULE_NAME
}

 OSIOSEAR_POPULATE_OFF_DETAILS_BLOCK_NAME_TYPE_CUR
{
select
	OMS_MISCELLANEOUS_GETDESCCODE('ETHNICITY',
	RACE_CODE) RACE_CODE
from
	OFFENDERS
where
	ROOT_OFFENDER_ID =:ROOT_OFFENDER_ID
	and OFFENDER_ID =:OFFENDER_ID
}

 OSIOSEAR_SET_INITIAL_SEARCH_TYPE_SEARCH_CUR
{
SELECT CODE, DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'SEARCH_TYPE' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL AND LIST_SEQ = 1
}
 OSIOSEAR_GET_LATEST_BOOKING
{
SELECT MAX ( OFFENDER_BOOK_ID )  FROM OFFENDER_BOOKINGS WHERE ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID 
}
 
 OSIOSEAR_PROF_TYPE_DESCP_C
{
SELECT DESCRIPTION,PROFILE_TYPE FROM   PROFILE_TYPES  WHERE PROFILE_TYPE =:PROFILE_TYPE 
}
 OSIOSEAR_PROF_CODE_DESCP
{
SELECT 'Y'  FROM PROFILE_TYPES WHERE PROFILE_TYPE = :PROFILE_TYPE AND   CODE_VALUE_TYPE = 'TEXT' 
}
 FIND_CODE_DESCRIPTION_PROFILECODES
{
SELECT DESCRIPTION FROM   PROFILE_CODES  WHERE  PROFILE_CODE =:PROFILE_CODE 
}

 OSIOSEAR_GET_OFFENDER_BOOK_ID_GET_BOOK_ID
{
select
	OFFENDER_BOOK_ID
from
	OFFENDER_BOOKINGS
where
	OFFENDER_ID in (
	select
		OFFENDER_ID
	from
		OFFENDERS
	where
		ROOT_OFFENDER_ID = ROOT_OFFENDER_ID )
	and TO_DATE(TO_CHAR(BOOKING_BEGIN_DATE, 'MM/DD/YYYY:HH24MI'), 'MM/DD/YYYY:HH24MI') = (
	select
		TO_DATE(TO_CHAR(MAX(BOOKING_BEGIN_DATE), 'MM/DD/YYYY:HH24MI'), 'MM/DD/YYYY:HH24MI')
	from
		OFFENDER_BOOKINGS
	where
		ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID )
}
 OSIOSEAR_CALL_TO_SHOW_FINGERPRINT_OLD
{
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE='CLIENT' AND PROFILE_CODE='FINGER_URL'
}

 FIND_V_HEADER_BLOCK{
SELECT
  V.SEAL_FLAG,
  V.OFFENDER_ID,
  V.ALIAS_OFFENDER_ID,
  V.OFFENDER_ID_DISPLAY,
  V.LAST_NAME,
  V.FIRST_NAME,
  V.MIDDLE_NAME,
  V.SUFFIX,
  V.BIRTH_DATE,
  V.OFFENDER_BOOK_ID,
  V.BOOKING_NO,
  V.BOOKING_BEGIN_DATE,
  V.BOOKING_END_DATE,
  V.AGY_LOC_ID,
  V.AGENCY_IML_ID,
  V.LIVING_UNIT_ID,
  V.DISCLOSURE_FLAG,
  V.ACTIVE_FLAG,
  V.BOOKING_STATUS,
  V.LIVING_UNIT_DESCRIPTION,
  V.IN_OUT_STATUS,
  V.STATUS_DISPLAY,
  V.ROOT_OFFENDER_ID,
  V.ASSIGNED_STAFF_ID,
  V.AGY_LOC_TYPE,
  V.CREATE_AGY_LOC_ID,
  V.BOOKING_TYPE,
  V.BOOKING_CREATED_DATE,
  V.LOCATION_CODE,
  V.LIV_UNIT_DESC,
  V.INTAKE_AGY_LOC_ID,
  V.COMMUNITY_ACTIVE_FLAG,
  CREATE_INTAKE_AGY_LOC_ID,
  V.COMMUNITY_STATUS,
  V.STATUS_REASON,
  V.HEADER_STATUS,
  V.AGE,
  V.GENDER,
  V.OFFICER,
  V.PRISON_LOCATION,
  V.OFF_ALERTS,
  V.STATUS_1,
  V.STATUS_2,
  V.STATUS_3,
  V.ETHNICITY,
  V.MOVEMENT_REASON,
  V.OFF_SUP_LEVEL,
  V.SEX
FROM V_HEADER_BLOCK_FN(:userId)  V WHERE

}
FIND_V_PROPERTY_HEADER_BLOCK_FN {
SELECT OFFENDER_ID,ALIAS_OFFENDER_ID,OFFENDER_ID_DISPLAY,LAST_NAME,FIRST_NAME,MIDDLE_NAME,SUFFIX,BIRTH_DATE,SEAL_FLAG,OFFENDER_BOOK_ID,PPTY_AGY_LOC_ID,BOOKING_NO,BOOKING_BEGIN_DATE,BOOKING_END_DATE,AGY_LOC_ID,LIVING_UNIT_ID,DISCLOSURE_FLAG,
ACTIVE_FLAG,BOOKING_STATUS,LIVING_UNIT_DESCRIPTION,IN_OUT_STATUS,STATUS_DISPLAY,ROOT_OFFENDER_ID,AGE,GENDER,OFFICER,PRISON_LOCATION,OFF_ALERTS,STATUS_1,STATUS_2,STATUS_3,MOVEMENT_REASON,OFF_SUP_LEVEL FROM V_PROPERTY_HEADER_BLOCK_FN(:userId) 
}

 CHECK_OFFENDERS_BY{
	SELECT 1 FROM OFFENDERS O WHERE O.ROOT_OFFENDER_ID = :ROOTOFFENDERID
}

 CHECK_OFFENDERSIDENTIFIERS_BY{
	SELECT 1 FROM OFFENDER_IDENTIFIERS O WHERE O.OFFENDER_ID = :OFFENDERID
}

 CHECK_OFFENDERSIDENTIFIERS_BY_PNC{
	SELECT COUNT (*) FROM OFFENDER_IDENTIFIERS OFF_IND WHERE OFF_IND.IDENTIFIER_TYPE = 'PNC' AND OFF_IND.IDENTIFIER = 'IDENTIFIER' AND OFF_IND.ROOT_OFFENDER_ID = :ROOTOFFENDERID
	
}
 OSIOSEAR_POPULATE_OFF_DETAILS_BLOCK_ATTR_CUR
{
SELECT HEIGHT_CM, WEIGHT_KG FROM OFFENDER_PHYSICAL_ATTRIBUTES WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID::NUMERIC 
}
 OSIOSEAR_OFFPROFDTLS_FIND_OFFENDER_PROFILE_DETAILS {
 SELECT OFFENDER_BOOK_ID ,PROFILE_SEQ ,PROFILE_TYPE ,PROFILE_CODE ,LIST_SEQ ,COMMENT_TEXT ,CASELOAD_TYPE ,MODIFY_USER_ID ,MODIFY_DATETIME ,CREATE_DATETIME ,CREATE_USER_ID ,SEAL_FLAG   FROM OFFENDER_PROFILE_DETAILS WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID AND PROFILE_CODE IS NOT NULL ORDER BY LIST_SEQ
 
}
FIND_V_HEADER_BLOCK2
{
select *, case when ( select count(1) from ( select off_bkg.booking_no, off_name.OFFENDER_ID_DISPLAY from offender_trust_accounts off_ta, offenders off_name, caseloads cl, offender_bookings off_bkg left join staff_members staff on off_bkg.comm_staff_id = staff.staff_id left join agency_locations agy_loc on off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text left join agency_internal_locations agency_il on off_bkg.agency_iml_id = agency_il.internal_location_id left join ( select * from agency_internal_locations ail where ail.unit_type is not null and ail.unit_type::text <> ''::text) liv_unit on off_bkg.living_unit_id = liv_unit.internal_location_id left join movement_reasons mov_rsn on substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text and substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text where off_ta.offender_id = off_name.root_offender_id and (off_ta.caseload_id::text = coalesce(cl.community_trust_caseload, cl.caseload_id)::text or off_ta.caseload_id::text = cl.commissary_trust_caseload::text or off_ta.caseload_id::text = cl.payroll_trust_caseload::text or off_ta.caseload_id::text = cl.trust_caseload_id::text or (off_ta.caseload_id::text in ( select cal.agy_loc_id from caseload_agency_locations cal 
where cal.caseload_id::text = cl.caseload_id::text)) or (off_ta.caseload_id::text in ( select clx.caseload_id from caseloads clx where clx.trust_accounts_flag::text = 'Y'::text and clx.trust_commissary_caseload::text = cl.caseload_id::text))) and (exists ( select 'x'::text as text from offender_sub_accounts off_sa where off_sa.caseload_id::text = off_ta.caseload_id::text and off_sa.offender_id = off_ta.offender_id)) and off_name.offender_id = off_bkg.offender_id)B where OFFENDER_ID_DISPLAY = A.OFFENDER_ID_DISPLAY and BOOKING_NO = A.BOOKING_NO)>1 then 'Y' else 'N' end trust_flag from ( select OFFENDER_ID, ALIAS_OFFENDER_ID, OFFENDER_ID_DISPLAY, LAST_NAME, FIRST_NAME, MIDDLE_NAME, SUFFIX, BIRTH_DATE, OFFENDER_BOOK_ID, BOOKING_NO, BOOKING_BEGIN_DATE, BOOKING_END_DATE, AGY_LOC_ID, AGENCY_IML_ID, LIVING_UNIT_ID, DISCLOSURE_FLAG, ACTIVE_FLAG, BOOKING_STATUS, LIVING_UNIT_DESCRIPTION, IN_OUT_STATUS, STATUS_DISPLAY_1, ROOT_OFFENDER_ID, ASSIGNED_STAFF_ID, AGY_LOC_TYPE, CREATE_AGY_LOC_ID, BOOKING_TYPE, BOOKING_CREATED_DATE, LOCATION_CODE, STAFF_FIRST_NAME, STAFF_LAST_NAME, LIV_UNIT_DESC, INTAKE_AGY_LOC_ID, COMMUNITY_ACTIVE_FLAG, CREATE_INTAKE_AGY_LOC_ID, COMMUNITY_STATUS, STATUS_REASON, HEADER_STATUS, seal_flag, ( select description from reference_codes rc where "domain" = 'SEX' and code = ( select sex_code from OFFENDERS where OFFENDER_ID = V_HEADER_BLOCK2_FN.OFFENDER_ID)) as sex from 
( select off_name.offender_id, off_name.alias_offender_id, off_name.offender_id_display, off_name.last_name, off_name.first_name, off_name.middle_name, off_name.suffix, off_name.birth_date, coalesce(off_bkg.offender_book_id, 0::bigint) as offender_book_id, off_bkg.booking_no, coalesce(off_bkg.seal_flag , 'N') as seal_flag, off_bkg.booking_begin_date, off_bkg.booking_end_date, off_bkg.agy_loc_id, off_bkg.agency_iml_id, off_bkg.living_unit_id, off_bkg.disclosure_flag, off_bkg.active_flag, off_bkg.booking_status, case when cl.caseload_type::text = 'COMM'::text then ((((((( case when off_bkg.community_active_flag::text = 'Y'::text then off_bkg.community_agy_loc_id else null::character varying end::text || ' - '::text) || staff.last_name::text) || ', '::text) || staff.first_name::text) || ' ; '::text) || off_bkg.comm_staff_role::text) || ' : '::text) || off_bkg.agy_loc_id::text else concat(liv_unit.description, ';', agency_iml.internal_location_code, ' : ', off_bkg.community_agy_loc_id) end as living_unit_description, off_bkg.in_out_status, case when cl.caseload_type::text = 'COMM'::text then case when off_bkg.community_active_flag::text = 'Y'::text then oms_system_profile('CLIENT'::character varying, 'B/C_STATUS'::character varying, 1)::text || case when oms_system_profile('C_KER'::character varying, 'ACT_SUP_STS'::character varying, 1)::text = 'Y'::text then '-'::text || ((( select offender_comm_sup_histories.status_code from offender_comm_sup_histories where offender_comm_sup_histories.offender_book_id = off_bkg.offender_book_id and 
offender_comm_sup_histories.status_seq = (( select max(offender_comm_sup_histories_1.status_seq) as max from offender_comm_sup_histories offender_comm_sup_histories_1 where offender_comm_sup_histories_1.offender_book_id = off_bkg.offender_book_id and offender_comm_sup_histories_1.effective_date <= LOCALTIMESTAMP))))::text) when oms_system_profile('C_KER'::character varying, 'ACT_SUP_STS'::character varying, 1)::text = 'N'::text then '-'::text || off_bkg.comm_status::text else '-'::text || off_bkg.comm_status::text end when off_bkg.community_active_flag::text = 'N'::text then oms_system_profile('CLIENT'::character varying, 'B/C_STATUS'::character varying, 2)::text else null::text end else case when off_bkg.active_flag::text = 'Y'::text then 'ACTIVE-'::text when off_bkg.active_flag::text = 'N'::text then 'INACTIVE-'::text else null::text end || case when off_bkg.in_out_status::text = 'OUT'::text then case when mov_rsn.header_status_flag::text = 'Y'::text then substr(off_bkg.status_reason::text, "position"(off_bkg.status_reason::text, '-'::text) + 1)::character varying else off_bkg.in_out_status end else off_bkg.in_out_status end::text end as status_display_1, off_name.root_offender_id, staff.staff_id as assigned_staff_id, agy_loc.agency_location_type as agy_loc_type, off_bkg.create_agy_loc_id, off_bkg.booking_type, off_bkg.booking_created_date, agency_iml.internal_location_code as location_code, staff.first_name as staff_first_name, staff.last_name as staff_last_name, liv_unit.description as liv_unit_desc, 
off_bkg.intake_agy_loc_id, off_bkg.community_active_flag, off_bkg.create_intake_agy_loc_id, off_bkg.comm_status as community_status, off_bkg.status_reason, mov_rsn.header_status_flag as header_status from staff_members stf, caseloads cl, offenders off_name left join offender_bookings off_bkg on off_name.offender_id = off_bkg.offender_id left join living_units liv_unit on off_bkg.living_unit_id = liv_unit.living_unit_id left join staff_members staff on off_bkg.comm_staff_id = staff.staff_id left join agency_internal_locations agency_iml on off_bkg.agency_iml_id = agency_iml.internal_location_id left join agency_locations agy_loc on off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text and off_bkg.intake_agy_loc_id::text = agy_loc.agy_loc_id::text left join movement_reasons mov_rsn on substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text and substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text where stf.user_id::text = upper(:username::text) and cl.caseload_id::text = stf.working_caseload_id::text) as V_HEADER_BLOCK2_FN where

}
FIND_V_HEADER_BLOCK2_IDS
{
SELECT V.OFFENDER_ID,V.ALIAS_OFFENDER_ID,OFFENDER_ID_DISPLAY,LAST_NAME,FIRST_NAME,MIDDLE_NAME,SUFFIX,BIRTH_DATE,
OFFENDER_BOOK_ID,BOOKING_NO,BOOKING_BEGIN_DATE,BOOKING_END_DATE,AGY_LOC_ID,AGENCY_IML_ID,LIVING_UNIT_ID,DISCLOSURE_FLAG,
V.ACTIVE_FLAG,BOOKING_STATUS,LIVING_UNIT_DESCRIPTION,IN_OUT_STATUS,V.STATUS_DISPLAY,ROOT_OFFENDER_ID,ASSIGNED_STAFF_ID,AGY_LOC_TYPE,
CREATE_AGY_LOC_ID,BOOKING_TYPE,BOOKING_CREATED_DATE,LOCATION_CODE,STAFF_FIRST_NAME,STAFF_LAST_NAME,LIV_UNIT_DESC,INTAKE_AGY_LOC_ID,
COMMUNITY_ACTIVE_FLAG,CREATE_INTAKE_AGY_LOC_ID,COMMUNITY_STATUS,STATUS_REASON,HEADER_STATUS FROM V_HEADER_BLOCK2_FN(:userId) V Where 
}



OSIOSEAR_CASELOAD_TYPE {
SELECT CASELOAD_TYPE, CASELOAD_ID FROM CASELOADS WHERE CASELOAD_ID = :caseLoadId
}
OSIOSEAR_FIND_VHEADERBLOCK_DATA {
	SELECT * FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK  WHERE OFFENDER_BOOK_ID = :offenderBookId
}
OSIOSEAR_FIND_VHEADERBLOCK_DATA_ADMISSSION {
	SELECT * FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK
}
OSIOSEAR_FIND_VHEADERBLOCK_DATA_OFFENDER_ID {
	SELECT OFFENDER_ID, SEX_CODE AS GENDER  FROM OFFENDERS  WHERE OFFENDER_ID = :offenderId
}

OSIOSEAR_GET_CAPTURE_IMAGE{
SELECT PROFILE_VALUE
      FROM SYSTEM_PROFILES
     WHERE PROFILE_TYPE='CLIENT'
       AND PROFILE_CODE='IMAGING_FORM'
       }
OSIOSEAR_OFFBKG_VTRUSTHEAD_GLOBAL_QUERY {
	SELECT TRUST_CASELOAD_ID,CASELOAD_ID,COMMISSARY_TRUST_CASELOAD,COMMUNITY_TRUST_CASELOAD,OFFENDER_ID,ALIAS_OFFENDER_ID,OFFENDER_ID_DISPLAY,LAST_NAME,FIRST_NAME,MIDDLE_NAME,SUFFIX,BIRTH_DATE,SEAL_FLAG,OFFENDER_BOOK_ID,BOOKING_NO,BOOKING_TYPE,BOOKING_BEGIN_DATE,BOOKING_END_DATE,AGY_LOC_ID,LIVING_UNIT_ID,DISCLOSURE_FLAG,ACTIVE_FLAG,BOOKING_STATUS,LIVING_UNIT_DESCRIPTION,IN_OUT_STATUS,STATUS_DISPLAY,ROOT_OFFENDER_ID,INTAKE_AGY_LOC_ID,COMMUNITY_ACTIVE_FLAG,CREATE_INTAKE_AGY_LOC_ID,COMMUNITY_STATUS,STATUS_REASON,HEADER_STATUS,AGY_LOC_TYPE,AGE,GENDER,OFFICER,(select PRISON_LOCATION from v_header_block_fn(:USER_ID) vhbf where vhbf.offender_book_id = v_trust_header.offender_book_id) as PRISON_LOCATION,OFF_ALERTS,STATUS_1,STATUS_2,STATUS_3,ETHNICITY,ACCOUNT_CLOSED_FLAG,MOVEMENT_REASON,OFF_SUP_LEVEL,INDIGENT_FLAG,SEX
	,(SELECT IMAGE_ID FROM IMAGES WHERE IMAGE_OBJECT_ID = V_TRUST_HEADER.OFFENDER_BOOK_ID and IMAGE_OBJECT_TYPE = 'OFF_BKG' AND ACTIVE_FLAG = 'Y' AND IMAGE_THUMBNAIL is not null limit 1) IMAGE_ID
	FROM V_TRUST_HEADER v_trust_header WHERE 
}

OSIOSEAR_GET_DESCRIPTION{
select DESCRIPTION from AGENCY_LOCATIONS where AGENCY_LOCATION_TYPE = ( select CASELOAD_TYPE from CASELOADS where CASELOAD_ID =:caseloadId ) and AGY_LOC_ID in ( select AGY_LOC_ID from CASELOAD_AGENCY_LOCATIONS where CASELOAD_ID = :caseloadId ) and AGY_LOC_ID not in ( select AGY_LOC_ID from OFFENDER_BOOKINGS where OFFENDER_ID = :offenderId and ACTIVE_FLAG = 'Y' and BOOKING_TYPE = ( select CASELOAD_TYPE from CASELOADS where CASELOAD_ID = :caseloadId ) ) and coalesce (DEACTIVATION_DATE::timestamp, '2020-12-12')= '2020-12-12' and AGY_LOC_ID = :code order by LIST_SEQ , DESCRIPTION
}

GET_OFFENDER_IMAGE_DETAILS{
select *
from
(SELECT vh.OFFENDER_ID,
vh.offender_book_id, vh.first_name, vh.last_name, vh.OFFENDER_ID_DISPLAY,
im.image_id, im.image_thumbnail, im.image_object_id
FROM V_HEADER_BLOCK_FN(:USERID) vh
left outer join images im on
vh.offender_book_id = im.image_object_id AND
im.IMAGE_OBJECT_TYPE = 'OFF_BKG' and
im.active_flag='Y') a
where
offender_book_id = :OFFENDER_BOOK_ID



}

OSIOSEAR_GET_OFFENDER_BOOKINGS {
SELECT * FROM OFFENDER_BOOKINGS  WHERE OFFENDER_BOOK_ID =:offenderBookId limit 1
}
OSIOSEAR_GET_ASSIGNED_CONDITIONS_OFFENDERS{
SELECT
    A.*,
    B.IMAGE_ID,
    case
		when
(select
	count(1)
from
	(select 
    off_bkg.booking_no,
    off_name.OFFENDER_ID_DISPLAY
   FROM offender_trust_accounts off_ta,
    offenders off_name,
    caseloads cl,
    offender_bookings off_bkg
     LEFT JOIN staff_members staff ON off_bkg.comm_staff_id = staff.staff_id
     LEFT JOIN agency_locations agy_loc ON off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text
     LEFT JOIN agency_internal_locations agency_il ON off_bkg.agency_iml_id = agency_il.internal_location_id
     LEFT JOIN (select * FROM agency_internal_locations ail
  WHERE ail.unit_type IS NOT NULL AND ail.unit_type::text <> ''::text) liv_unit ON off_bkg.living_unit_id = liv_unit.internal_location_id
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE off_ta.offender_id = off_name.root_offender_id AND (off_ta.caseload_id::text = COALESCE(cl.community_trust_caseload, cl.caseload_id)::text OR off_ta.caseload_id::text = cl.commissary_trust_caseload::text OR off_ta.caseload_id::text = cl.payroll_trust_caseload::text OR off_ta.caseload_id::text = cl.trust_caseload_id::text OR (off_ta.caseload_id::text IN ( SELECT cal.agy_loc_id
           FROM caseload_agency_locations cal
          WHERE cal.caseload_id::text = cl.caseload_id::text)) OR (off_ta.caseload_id::text IN ( SELECT clx.caseload_id
           FROM caseloads clx
          WHERE clx.trust_accounts_flag::text = 'Y'::text AND clx.trust_commissary_caseload::text = cl.caseload_id::text))) AND (EXISTS ( SELECT 'x'::text AS text
           FROM offender_sub_accounts off_sa
          WHERE off_sa.caseload_id::text = off_ta.caseload_id::text AND off_sa.offender_id = off_ta.offender_id)) AND off_name.offender_id = off_bkg.offender_id)B
where
	OFFENDER_ID_DISPLAY = A.OFFENDER_ID_DISPLAY
	and BOOKING_NO = A.BOOKING_NO)>1 then 'Y'
	else 'N'
end trust_flag
FROM
    (
        select distinct
            V.SEAL_FLAG,
            V.OFFENDER_ID,
            V.ALIAS_OFFENDER_ID,
            V.OFFENDER_ID_DISPLAY,
            V.LAST_NAME,
            V.FIRST_NAME,
            V.MIDDLE_NAME,
            V.SUFFIX,
            V.BIRTH_DATE,
            V.OFFENDER_BOOK_ID,
            V.BOOKING_NO,
            V.BOOKING_BEGIN_DATE,
            V.BOOKING_END_DATE,
            V.AGY_LOC_ID,
            V.AGENCY_IML_ID,
            V.LIVING_UNIT_ID,
            V.DISCLOSURE_FLAG,
            V.ACTIVE_FLAG,
            V.BOOKING_STATUS,
            V.LIVING_UNIT_DESCRIPTION,
            V.IN_OUT_STATUS,
            V.STATUS_DISPLAY,
            V.ROOT_OFFENDER_ID,
            V.ASSIGNED_STAFF_ID,
            V.AGY_LOC_TYPE,
            V.CREATE_AGY_LOC_ID,
            V.BOOKING_TYPE,
            V.BOOKING_CREATED_DATE,
            V.LOCATION_CODE,
            V.LIV_UNIT_DESC,
            V.INTAKE_AGY_LOC_ID,
            V.COMMUNITY_ACTIVE_FLAG,
            CREATE_INTAKE_AGY_LOC_ID,
            V.COMMUNITY_STATUS,
            V.STATUS_REASON,
            V.HEADER_STATUS,
            V.AGE,
            V.GENDER,
            V.OFFICER,
            V.PRISON_LOCATION,
            V.OFF_ALERTS,
            V.STATUS_1,
            V.STATUS_2,
            V.STATUS_3,
            V.ETHNICITY,
            V.MOVEMENT_REASON,
            V.OFF_SUP_LEVEL,
            V.SEX
        FROM
            (
                SELECT
                    A.CON_TRANSFER_ID,
                    A.OFFENDER_BOOK_ID,
                    A.OFFENDER_SENT_CONDITION_ID,
                    A.STAFF_ID,
                    A.TEAM_ID,
                    A.TEAM_MEMBER_ID,
                    A.AGY_LOC_ID,
                    A.TO_TEAM_ID,
                    A.TO_AGY_LOC_ID,
                    A.CONDI_STATUS,
                    A.CREATE_DATETIME,
                    A.CREATE_USER_ID,
                    A.MODIFY_DATETIME,
                    A.MODIFY_USER_ID,
                    A.SEAL_FLAG,
                    A.TO_STAFF_ID,
                    A.TO_TEAM_MEMBER_ID,
                    A.PARENT_CON_TRANSFER_ID
                FROM
                    ( SELECT MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID FROM OFFENDER_COND_TRANSFER GROUP BY OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID  )  B,
                    OFFENDER_COND_TRANSFER A
                WHERE
                        A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID
                    AND A.OFFENDER_SENT_CONDITION_ID = B.OFFENDER_SENT_CONDITION_ID
                    AND A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID
                    AND ( A.AGY_LOC_ID IN (SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS CSLD WHERE CAL.CASELOAD_ID = :AGY_LOC_ID AND CSLD.CASELOAD_TYPE = 'COMM' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID )
                    OR A.TO_AGY_LOC_ID IN ( SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS CSLD WHERE CAL.CASELOAD_ID = :AGY_LOC_ID AND CSLD.CASELOAD_TYPE = 'COMM' AND CSLD.CASELOAD_ID = CAL.CASELOAD_ID ) )
                    AND (( coalesce(A.STAFF_ID,0) = 0 and coalesce(A.TO_STAFF_ID,0) = 0 and (( coalesce(A.TEAM_ID,0) = 0 AND A.TO_TEAM_ID IN ( SELECT TEAM_ID FROM TEAM_STAFF_MEMBERS WHERE STAFF_ID = ( SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :userId ) ) )or 
                        ( coalesce(A.TO_TEAM_ID,0) = 0  and A.TEAM_ID IN ( SELECT TEAM_ID FROM TEAM_STAFF_MEMBERS WHERE STAFF_ID = ( SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :userId )))))
                    or ( coalesce(A.TEAM_ID,0) = 0 and coalesce(A.TO_TEAM_ID,0) = 0 and (( coalesce(A.STAFF_ID,0) = 0 AND A.TO_STAFF_ID = ( SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :userId )) or 
                        ( coalesce(A.TO_STAFF_ID,0) = 0  and A.STAFF_ID  = ( SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :userId ) ))  ))
            )              OCT,
            V_HEADER_BLOCK_FN(:userId) V
        WHERE
                V.OFFENDER_BOOK_ID = OCT.OFFENDER_BOOK_ID
            AND V.COMMUNITY_ACTIVE_FLAG = 'Y' AND 
}


OSIOSEAR_GET_V_TRUST_HEADER_COUNT{
case
		when
(select
	count(1)
from
	(select 
    off_bkg.booking_no,
    off_name.OFFENDER_ID_DISPLAY
   FROM offender_trust_accounts off_ta,
    offenders off_name,
    caseloads cl,
    offender_bookings off_bkg
     LEFT JOIN staff_members staff ON off_bkg.comm_staff_id = staff.staff_id
     LEFT JOIN agency_locations agy_loc ON off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text
     LEFT JOIN agency_internal_locations agency_il ON off_bkg.agency_iml_id = agency_il.internal_location_id
     LEFT JOIN (select * FROM agency_internal_locations ail
  WHERE ail.unit_type IS NOT NULL AND ail.unit_type::text <> ''::text) liv_unit ON off_bkg.living_unit_id = liv_unit.internal_location_id
     LEFT JOIN movement_reasons mov_rsn ON substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text AND substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
  WHERE off_ta.offender_id = off_name.root_offender_id AND (off_ta.caseload_id::text = COALESCE(cl.community_trust_caseload, cl.caseload_id)::text OR off_ta.caseload_id::text = cl.commissary_trust_caseload::text OR off_ta.caseload_id::text = cl.payroll_trust_caseload::text OR off_ta.caseload_id::text = cl.trust_caseload_id::text OR (off_ta.caseload_id::text IN ( SELECT cal.agy_loc_id
           FROM caseload_agency_locations cal
          WHERE cal.caseload_id::text = cl.caseload_id::text)) OR (off_ta.caseload_id::text IN ( SELECT clx.caseload_id
           FROM caseloads clx
          WHERE clx.trust_accounts_flag::text = 'Y'::text AND clx.trust_commissary_caseload::text = cl.caseload_id::text))) AND (EXISTS ( SELECT 'x'::text AS text
           FROM offender_sub_accounts off_sa
          WHERE off_sa.caseload_id::text = off_ta.caseload_id::text AND off_sa.offender_id = off_ta.offender_id)) AND off_name.offender_id = off_bkg.offender_id)B
where
	OFFENDER_ID_DISPLAY = A.OFFENDER_ID_DISPLAY
	and BOOKING_NO = A.BOOKING_NO)>1 then 'Y'
	else 'N'
end trust_flag
}

OSIOSEAR_GET_BOOING_ID_OF_OFFENDER{
select MAX(booking_no) from v_header_block_FN(:USER_ID) where offender_id_display =:OFFENDER_ID_DISPLAY
}

OSIOSEAR_GET_OFFENDER_DETAILS{
select
	A.*,
	B.IMAGE_ID,
	case
		when
(
		select
			count(1)
		from
			(
			select
				off_bkg.booking_no,
				off_name.OFFENDER_ID_DISPLAY
			from
				offender_trust_accounts off_ta,
				offenders off_name,
				caseloads cl,
				offender_bookings off_bkg
			left join staff_members staff on
				off_bkg.comm_staff_id = staff.staff_id
			left join agency_locations agy_loc on
				off_bkg.agy_loc_id::text = agy_loc.agy_loc_id::text
			left join agency_internal_locations agency_il on
				off_bkg.agency_iml_id = agency_il.internal_location_id
			left join (
				select
					*
				from
					agency_internal_locations ail
				where
					ail.unit_type is not null
					and ail.unit_type::text <> ''::text) liv_unit on
				off_bkg.living_unit_id = liv_unit.internal_location_id
			left join movement_reasons mov_rsn on
				substr(off_bkg.status_reason::text,
				1::numeric,
				instr(off_bkg.status_reason::text,
				'-'::text,
				1::numeric) - 1::numeric) = mov_rsn.movement_type::text
				and substr(off_bkg.status_reason::text,
				instr(off_bkg.status_reason::text,
				'-'::text,
				1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text
			where
				off_ta.offender_id = off_name.root_offender_id
				and (off_ta.caseload_id::text = coalesce(cl.community_trust_caseload, cl.caseload_id)::text
					or off_ta.caseload_id::text = cl.commissary_trust_caseload::text
					or off_ta.caseload_id::text = cl.payroll_trust_caseload::text
					or off_ta.caseload_id::text = cl.trust_caseload_id::text
					or (off_ta.caseload_id::text in (
					select
						cal.agy_loc_id
					from
						caseload_agency_locations cal
					where
						cal.caseload_id::text = cl.caseload_id::text))
					or (off_ta.caseload_id::text in (
					select
						clx.caseload_id
					from
						caseloads clx
					where
						clx.trust_accounts_flag::text = 'Y'::text
						and clx.trust_commissary_caseload::text = cl.caseload_id::text)))
				and (exists (
				select
					'x'::text as text
				from
					offender_sub_accounts off_sa
				where
					off_sa.caseload_id::text = off_ta.caseload_id::text
					and off_sa.offender_id = off_ta.offender_id))
				and off_name.offender_id = off_bkg.offender_id)B
		where
			OFFENDER_ID_DISPLAY = A.OFFENDER_ID_DISPLAY
			and BOOKING_NO = A.BOOKING_NO)>=1 then 'Y'
		else 'N'
	end trust_flag
from
	(
	select
		V.OFFENDER_ID,
		V.ALIAS_OFFENDER_ID,
		V.OFFENDER_ID_DISPLAY,
		V.LAST_NAME,
		V.FIRST_NAME,
		V.MIDDLE_NAME,
		V.SUFFIX,
		V.BIRTH_DATE,
		V.OFFENDER_BOOK_ID,
		V.BOOKING_NO,
		V.BOOKING_BEGIN_DATE,
		V.BOOKING_END_DATE,
		V.AGY_LOC_ID,
		V.AGENCY_IML_ID,
		V.LIVING_UNIT_ID,
		V.DISCLOSURE_FLAG,
		V.ACTIVE_FLAG,
		V.BOOKING_STATUS,
		V.LIVING_UNIT_DESCRIPTION,
		V.IN_OUT_STATUS,
		V.STATUS_DISPLAY,
		V.ROOT_OFFENDER_ID,
		V.ASSIGNED_STAFF_ID,
		V.AGY_LOC_TYPE,
		V.CREATE_AGY_LOC_ID,
		V.BOOKING_TYPE,
		V.BOOKING_CREATED_DATE,
		V.LOCATION_CODE,
		V.LIV_UNIT_DESC,
		V.INTAKE_AGY_LOC_ID,
		V.COMMUNITY_ACTIVE_FLAG,
		CREATE_INTAKE_AGY_LOC_ID,
		V.COMMUNITY_STATUS,
		V.STATUS_REASON,
		V.HEADER_STATUS,
		V.AGE,
		V.GENDER,
		V.OFFICER,
		V.PRISON_LOCATION,
		V.OFF_ALERTS,
		V.STATUS_1,
		V.STATUS_2,
		V.STATUS_3,
		V.ETHNICITY,
		V.MOVEMENT_REASON,
		V.OFF_SUP_LEVEL,
		V.SEX
	from
		V_HEADER_BLOCK_FN(:USER_ID) V
	where
		BOOKING_NO = :BOOKING_NO
		and LTRIM(OFFENDER_ID_DISPLAY::text, '0') like LTRIM(:OFFENDER_ID_DISPLAY::text, '0')
	order by
			LAST_NAME asc,
			FIRST_NAME asc )A
left join (
	select
		IMAGE_ID,
		IMAGE_OBJECT_ID
	from
		IMAGES
	where
		ACTIVE_FLAG = 'Y'
		and IMAGE_OBJECT_TYPE = 'OFF_BKG'
		and IMAGE_VIEW_TYPE = 'FACE')B on
	A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID
}

OSIOSEAR_GET_INT_CORRELATION_ID_SEQ{
select coalesce(max(int_correlation_id )+1,1) from external_system_response
}

OSIOSEAR_GET_DATA_FROM_JIS_COMMON_SYSTEM{
select response_payload responseData ,int_correlation_id from external_system_response where int_correlation_id = :intCorrelationId
}

OSIOSEAR_GET_IDENTIFIER_DATA{
select identifier from offender_identifiers where offender_id =:offenderId and identifier_type = 'PIN' limit 1
}

OSIOSEAR_REMOVE_DATA_EXTERNAL_SYSTEM_RESPONSE{
delete from external_system_response where module_name in('OSIOSEAR','OSIPSEAR') and request_sent < current_timestamp + interval '-1 DAY'
}

OSIOSEAR_GET_CORRELATION_ID {
	select nextval('correlation_id') correlation_id
}

OSIOSEAR_GET_RECENT_OFFENDERS{
select 'Y' from current_offenders co where offender_book_id = :offenderBookId and user_id = :userId limit 1
}
OSIOSEAR_SEARCH_TYPE_CODE{
select code from reference_codes where domain = 'SEARCH_TYPE' and active_flag = 'Y' order by LIST_SEQ, CODE FETCH FIRST ROW ONLY
}
