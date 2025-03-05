
OIDTPCON_FIND_CGFKOFFCONTRNTOAGYLOCID {
select
	AGY_LOC.AGY_LOC_ID ,
	AGY_LOC.DESCRIPTION ,
	AGY_LOC.LIST_SEQ,
	AGY_LOC.ACTIVE_FLAG
from
	AGENCY_LOCATIONS AGY_LOC
where
	(DEACTIVATION_DATE is null
		or DEACTIVATION_DATE > current_timestamp )
	and AGY_LOC_ID in (
	select
		distinct D.AGY_LOC_ID
	from
		INT_LOC_USAGE_LOCATIONS B ,
		INTERNAL_LOCATION_USAGES C ,
		AGENCY_INTERNAL_LOCATIONS D
	where
		B.INTERNAL_LOCATION_USAGE_ID = C.INTERNAL_LOCATION_USAGE_ID
		and B.INTERNAL_LOCATION_ID = D.INTERNAL_LOCATION_ID
		and C.INTERNAL_LOCATION_USAGE = 'PROP'
		and D.ACTIVE_FLAG = 'Y'
		and D.DEACTIVATE_DATE is null
		and D.AGY_LOC_ID not in ('OUT' , 'TRN' ) )
	and AGY_LOC_ID <> :AGYLOCID
	and AGY_LOC.AGENCY_LOCATION_TYPE = 'INST'
order by
	AGY_LOC.LIST_SEQ ,
	AGY_LOC.DESCRIPTION

}

OIDTPCON_FIND_RGSELECTALL {
select
	AGY_LOC.AGY_LOC_ID ,
	AGY_LOC.DESCRIPTION ,
	AGY_LOC.LIST_SEQ,
	AGY_LOC.ACTIVE_FLAG
from
	AGENCY_LOCATIONS AGY_LOC
where
	(DEACTIVATION_DATE is null
		or DEACTIVATION_DATE > current_timestamp )
	and AGY_LOC_ID in (
	select
		distinct D.AGY_LOC_ID
	from
		INT_LOC_USAGE_LOCATIONS B ,
		INTERNAL_LOCATION_USAGES C ,
		AGENCY_INTERNAL_LOCATIONS D
	where
		B.INTERNAL_LOCATION_USAGE_ID = C.INTERNAL_LOCATION_USAGE_ID
		and B.INTERNAL_LOCATION_ID = D.INTERNAL_LOCATION_ID
		and C.INTERNAL_LOCATION_USAGE = 'PROP'
		and D.ACTIVE_FLAG = 'Y'
		and D.DEACTIVATE_DATE is null
		and D.AGY_LOC_ID not in ('OUT' , 'TRN' ) )
	and AGY_LOC.AGENCY_LOCATION_TYPE = 'INST'
order by
	AGY_LOC.LIST_SEQ ,
	AGY_LOC.DESCRIPTION

}

OIDTPCON_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS {
 	select * from (select B.property_only_flag, B.property_container_id, B.offender_book_id, B.agy_loc_id, B.active_flag, B.proposed_disposal_date, B.comment_text, B.internal_location_id, B.container_code, B.expiry_date, B.seal_mark, B.trn_from_agy_loc_id, B.trn_to_agy_loc_id, B.create_datetime, B.create_user_id, B.modify_datetime, B.modify_user_id, B.property_storage_id, B.disposed_to, B.disposed_to_name, B.seal_flag from (select count(1),property_container_id from offender_ppty_items where offender_book_id = :OFFENDERBOOKID and STATUS_CODE <> 'DISPOSED' and property_container_id is not null group by property_container_id)A left outer join(SELECT property_only_flag, property_container_id, offender_book_id, agy_loc_id, active_flag, proposed_disposal_date, comment_text, internal_location_id, container_code, expiry_date, seal_mark, trn_from_agy_loc_id, trn_to_agy_loc_id, create_datetime, create_user_id, modify_datetime, modify_user_id, property_storage_id, disposed_to, disposed_to_name, seal_flag FROM offender_ppty_containers WHERE offender_book_id = :OFFENDERBOOKID AND agy_loc_id IN ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal WHERE cal.agy_loc_id NOT IN ( 'OUT', 'TRN' ) AND cal.caseload_id = :caseLoadId ) AND active_flag = 'Y')B ON A.property_container_id=B.property_container_id)A WHERE (
select
internal_location_code
from
agency_internal_locations ail
where
internal_location_id = A.internal_location_id)<> 'TR'
}
OIDTPCON_OFFCON_UPDATE_OFFENDER_PPTY_CONTAINERS {
	UPDATE OFFENDER_PPTY_CONTAINERS set INTERNAL_LOCATION_ID = :internalLocationId ,AGY_LOC_ID  = :agyLocId ,TRN_FROM_AGY_LOC_ID  = :trnFromAgyLocId ,TRN_TO_AGY_LOC_ID  = :trnToAgyLocId ,MODIFY_DATETIME =CURRENT_TIMESTAMP , MODIFY_USER_ID = :modifyUserId where PROPERTY_CONTAINER_ID  = :propertyContainerId 
}

OIDTPCON_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES   where PROFILE_TYPE =:PROFILETYPE and PROFILE_CODE =:PROFILECODE
}

OIDTPCON_V_PHEAD_ONCHECKDELETEMASTER {
	SELECT 1 FROM OFFENDER_PPTY_CONTAINERS O WHERE O.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDTPCON_OFF_CON_POSTQUERY {
	SELECT INT_LOC.DESCRIPTION FROM   AGENCY_INTERNAL_LOCATIONS INT_LOC WHERE  INT_LOC.INTERNAL_LOCATION_ID = :INTERNALLOCATIONID
}

OIDTPCON_OFF_CON_WHENNEWRECORDINSTANCE {
	SELECT ILU.AGY_LOC_ID FROM INTERNAL_LOCATION_USAGES ILU, INT_LOC_USAGE_LOCATIONS  ILUL WHERE ILU.INTERNAL_LOCATION_USAGE_ID = ILUL.INTERNAL_LOCATION_USAGE_ID AND ILUL.INTERNAL_LOCATION_ID = :INTERNALLOCATIONID
}

OIDTPCON_CGFKCHK_OFF_CON_OFF_CON_REF_C {
	SELECT REF_CODE.DESCRIPTION ,REF_CODE.LIST_SEQ FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :CONTAINERCODE AND     DOMAIN='PPTY_CNTNR' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL) OR '' = 'QUERY')
}

OIDTPCON_CGFKCHK_OFF_CON_OFF_CON_AGY_L {
select
	AGY_LOC.DESCRIPTION ,
	AGY_LOC.LIST_SEQ
from
	AGENCY_LOCATIONS AGY_LOC
where
	AGY_LOC.AGY_LOC_ID = :TRNTOAGYLOCID
	and (DEACTIVATION_DATE is null
		or DEACTIVATION_DATE > current_timestamp)
	and AGY_LOC_ID not in ('OUT', 'TRN')
	and AGY_LOC_ID != :PPTYAGYLOCID
	and AGY_LOC_ID in (
	select
		AGY_LOC_ID
	from
		CASELOAD_AGENCY_LOCATIONS
	where
		CASELOAD_ID in (
		select
			CASELOAD_ID
		from
			CASELOADS
		where
			ACCESS_PROPERTY_FLAG = 'Y'))

}

OIDTPCON_CGFKCHK_OFF_CON_OFF_CON_PPTY {
	SELECT PPTY_STG.DESCRIPTION FROM   PROPERTY_STORAGES PPTY_STG WHERE  PPTY_STG.PROPERTY_STORAGE_ID = :PROPERTYSTORAGEID
}

OIDTPCON_CGWHEN_NEW_FORM_INSTANCE {
	SELECT  CURRENT_TIMESTAMP , :userName FROM  DUAL
}
OIDTPCON_GET_ITEM_STATUS {
SELECT 
STATUS_CODE
 FROM OFFENDER_PPTY_ITEMS  WHERE 
OFFENDER_BOOK_ID = :OFFENDERBOOKID AND AGY_LOC_ID = :AGYLOCID AND STATUS_CODE IN ('CELL','MISSING','REGISTERED') 
}
GETTING_OLD_OFFENDER_PPT_YITE_MSDATA1{
 select * from offender_ppty_items where PROPERTY_CONTAINER_ID = :property_container_id LIMIT 1
 }
 
 GET_OFF_PEN_PROP_CONTAINER_DETAils{ 
 SELECT property_only_flag, property_container_id, 
 offender_book_id, agy_loc_id, active_flag, proposed_disposal_date, comment_text, internal_location_id, container_code, expiry_date,
 seal_mark, trn_from_agy_loc_id, trn_to_agy_loc_id, create_datetime, create_user_id, modify_datetime, modify_user_id, property_storage_id,
 disposed_to, disposed_to_name, seal_flag FROM offender_ppty_containers WHERE offender_book_id = :OFFENDERBOOKID AND 
 agy_loc_id IN ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal WHERE cal.agy_loc_id ='OUT' AND 
 cal.caseload_id = :caseLoadId ) AND active_flag = 'Y' and trn_to_agy_loc_id is not null
 }
