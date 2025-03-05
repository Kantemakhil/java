
OIINAMES_NAMESRCH_FIND_V_NAME_SEARCH_FN {
select
	LAST_NAME,
	FIRST_NAME,
	ACTIVE_FLAG,
	OFFENDER_ID,
	OFFENDER_BOOK_ID,
	BOOKING_NO,
	OFFENDER_ID_DISPLAY,
	BIRTH_DATE,
	(case when (select caseload_type from caseloads c where caseload_id =:caseloadId) = 'INST' then 
			AGY_LOC_ID  else  INTAKE_AGY_LOC_ID end ) as AGY_LOC_ID,
	LIVING_UNIT_DESCRIPTION,
	IN_OUT_STATUS,
	MIDDLE_NAME,
	ACTIVE_FLAG,
	SEAL_FLAG
	from
		(
	select
		off.first_name,
		off.middle_name,
		off.last_name,
		off_bkg.active_flag,
		off_bkg.offender_id,
		off_bkg.OFFENDER_BOOK_ID,
		off_bkg.BOOKING_NO,
		off.offender_id_display,
		off.BIRTH_DATE,
		substr(tag_header_get_header_location_u(off_bkg.active_flag,
		off_bkg.community_active_flag,
		off_bkg.community_agy_loc_id,
		off_bkg.agy_loc_id,
		off_bkg.living_unit_id,
		off_bkg.comm_staff_role,
		off_bkg.comm_staff_id::bigint,
		:userId)::text,
		0,
		100) as living_unit_description,
		off_bkg.in_out_status,
		coalesce(off_bkg.seal_flag ,
		'N') seal_flag,
		off_bkg.AGY_LOC_ID,
		off_bkg.intake_agy_loc_id,
		off_bkg.booking_begin_date
	from
		offenders off,
		offender_bookings off_bkg
	where
		off.offender_id = off_bkg.offender_id) VHB 
}
OIINAMES_NAMESRCH_INSERT_V_NAME_SEARCH {
	INSERT INTO V_NAME_SEARCH(LAST_NAME ,FIRST_NAME ,ACTIVE_FLAG ,OFFENDER_ID ,OFFENDER_BOOK_ID ,BOOKING_NO ,OFFENDER_ID_DISPLAY ,BIRTH_DATE ,AGY_LOC_ID ,AGY_LOC_NAME ,LIVING_UNIT_DESCRIPTION ,IN_OUT_STATUS ,MIDDLE_NAME ) VALUES(:lastName ,:firstName ,:activeFlag ,:offenderId ,:offenderBookId ,:bookingNo ,:offenderIdDisplay ,:birthDate ,:agyLocId ,:agyLocName ,:livingUnitDescription ,:inOutStatus ,:middleName )
} 

OIINAMES_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  
}

OIINAMES_CGFDGET_NAME_SRCH_DRV_ACTIVE {
	SELECT 
  CASE
    WHEN :P_ACTIVE_FLAG='Y' THEN 'A'
    ELSE 'I' END from dual

}

OIINAMES_CGWHEN_NEW_FORM_INSTANCE {
	SELECT  SYSDATE(), upper(USER) FROM DUAL
}

OIINAMES_AGY_LOC_ID{
SELECT distinct AGY_LOC_ID FROM V_NAME_SEARCH_FN(:userId)
}

OIINAMES_LIVING_UNIT_DESCRIPTION {
SELECT distinct LIVING_UNIT_DESCRIPTION FROM V_NAME_SEARCH_FN(:userId)
}
OIINAMESFIND_V_PROPERTY_HEADER_BLOCK {
SELECT * FROM V_PROPERTY_HEADER_BLOCK_FN(:userId) WHERE  
}

OIINAMES_ACTIVE_FLAG {
SELECT distinct ACTIVE_FLAG FROM V_NAME_SEARCH
}

FIND_AGYLOCID_LIST_LOV {
SELECT CASELOAD_ID CODE, DESCRIPTION FROM CASELOADS
}
