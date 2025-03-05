
OIIPCLOC_FIND_RGCONTAINERCODE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION , REF_CODE.CODE  CODE , REF_CODE.LIST_SEQ  LIST_SEQ FROM   REFERENCE_CODES REF_CODE WHERE   (DOMAIN='PPTY_CNTNR'  AND ((ACTIVE_FLAG='Y' AND EXPIRED_DATE IS NULL )   OR  ('' = 'QUERY' ) ) ) ORDER BY  REF_CODE.LIST_SEQ        ,REF_CODE.DESCRIPTION
}

OIIPCLOC_FIND_RGDESCRIPTION { 
select * from (select
	ail.description,
	ail.internal_location_code,
	ail.parent_internal_location_id,
	ail.internal_location_id,
	to_char(ail.internal_location_id) code,
	ail.active_flag,
	case
		when (
		select
			COUNT(*)
		from
			int_loc_usage_locations a,
			internal_location_usages b,
			agency_internal_locations c
		where
			a.internal_location_usage_id = b.internal_location_usage_id
			and b.internal_location_usage = 'PROP'
			and 
            b.agy_loc_id in (
			select
				cal.agy_loc_id
			from
				caseload_agency_locations cal
			where
				cal.caseload_id = :CASELOADID
				and cal.agy_loc_id not in ( 'OUT', 'TRN' ))
			and a.internal_location_id = c.internal_location_id
			and ((c.active_flag = 'Y'
				and c.deactivate_date is null)
			or ('ENTER-QUERY' = 'ENTER-QUERY'))
			and not exists (
			select
				1
			from
				int_loc_usage_locations
			where
				parent_usage_location_id = a.usage_location_id)
			and c.internal_location_id = ail.internal_location_id
) > 0 then true
		else false
	end canDisplay
from
	agency_internal_locations ail
order by
	list_seq)CD
	where CD.canDisplay is true
}

OIIPCLOC_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS {
 	SELECT  * FROM OFFENDER_PPTY_CONTAINERS 
}

OIIPCLOC_CGFKCHK_OFF_CON_OFF_CON_V_PHE {
	SELECT V_PHEAD.OFFENDER_ID_DISPLAY ,V_PHEAD.LAST_NAME ,V_PHEAD.FIRST_NAME ,V_PHEAD.AGY_LOC_ID FROM   V_HEADER_BLOCK_FN(:USERID) V_PHEAD WHERE  V_PHEAD.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIIPCLOC_CGFKLKP_OFF_CON_OFF_CON_PPTY {
	SELECT PPTY_STG.PROPERTY_STORAGE_ID ,PPTY_STG.USER_DESC FROM   PROPERTY_STORAGES PPTY_STG WHERE  PPTY_STG.DESCRIPTION = :DSPDESCRIPTION2 AND     PPTY_STG.AGY_LOC_ID IN ( SELECT AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = ( SELECT CASELOAD_ID FROM CASELOADS WHERE ACCESS_PROPERTY_FLAG = 'Y' AND CASELOAD_ID = ( SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = USER))) AND NOT EXISTS (SELECT 'X' FROM PROPERTY_STORAGES P WHERE P.PARENT_STORAGE_ID = PPTY_STG.PROPERTY_STORAGE_ID)
}

OIIPCLOC_CGWHEN_NEW_FORM_INSTANCE {
	SELECT  CURRENT_TIMESTAMP , UPPER(USER) FROM DUAL
}

OIIPCLOC_CGFKCHK_OFF_CON_OFF_CON_PPTY {
	SELECT A.DESCRIPTION  ,A.INTERNAL_LOCATION_CODE FROM   AGENCY_INTERNAL_LOCATIONS A WHERE  A.INTERNAL_LOCATION_ID = :INTERNALLOCATIONID
}
