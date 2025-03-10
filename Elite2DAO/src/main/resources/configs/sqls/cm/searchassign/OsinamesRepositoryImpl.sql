
OSINAMES_NAMESRCH_FIND_V_NAME_SEARCH2 {
 	SELECT OFFENDER_ID ,ALIAS_OFFENDER_ID ,OFFENDER_ID_DISPLAY ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,SUFFIX ,BIRTH_DATE ,
 	OFFENDER_BOOK_ID ,BOOKING_NO ,BOOKING_BEGIN_DATE ,BOOKING_END_DATE ,AGY_LOC_ID ,AGENCY_IML_ID ,LIVING_UNIT_ID ,
 	DISCLOSURE_FLAG ,ACTIVE_FLAG ,BOOKING_STATUS ,LIVING_UNIT_DESCRIPTION ,IN_OUT_STATUS ,STATUS_DISPLAY ,ROOT_OFFENDER_ID ,
 	ASSIGNED_STAFF_ID ,AGY_LOC_TYPE ,CREATE_AGY_LOC_ID ,BOOKING_TYPE ,BOOKING_CREATED_DATE ,LOCATION_CODE ,STAFF_FIRST_NAME ,
 	STAFF_LAST_NAME ,LIV_UNIT_DESC ,INTAKE_AGY_LOC_ID ,COMMUNITY_ACTIVE_FLAG ,
 	CREATE_INTAKE_AGY_LOC_ID ,COMMUNITY_STATUS ,AGY_LOC_NAME ,COMMUNITY_AGY_LOC_ID,SEAL_FLAG FROM V_NAME_SEARCH2_FN(:userId) 
} 

OSINAMES_CGFDGET_NAME_SRCH_DRV_ACTIVE__ {
	select
	case
		when :P_ACTIVE_FLAG = 'Y' then 'A'
		else 'I'
	end from dual

}

OSINAMES_NAMESRCH_AGENCY_DESCR{
 select description  from agency_locations al  where agy_loc_id  = :agyLocId 

}

