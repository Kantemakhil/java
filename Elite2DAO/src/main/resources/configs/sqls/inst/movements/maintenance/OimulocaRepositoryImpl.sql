
OIMULOCA_FIND_RGLOCATIONUSAGE {
 	SELECT   DESCRIPTION ,          CODE     FROM REFERENCE_CODES    WHERE DOMAIN = 'ILOC_USG'      AND ((ACTIVE_FLAG = 'Y'       AND EXPIRED_DATE IS NULL )          OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIMULOCA_FIND_RGAGYLOC {
 	SELECT   DESCRIPTION , AL.AGY_LOC_ID CODE, AL.ACTIVE_FLAG FROM     AGENCY_LOCATIONS AL WHERE           AGENCY_LOCATION_TYPE = 'INST' AND     (          ( ACTIVE_FLAG = 'Y' AND DEACTIVATION_DATE IS NULL  ) OR '' = 'ENTER-QUERY' ) AND      AL.AGY_LOC_ID IN (                     SELECT CA.AGY_LOC_ID                       FROM CASELOAD_AGENCY_LOCATIONS CA                      WHERE CA.CASELOAD_ID = :CASELOADID                            ) AND AL.AGY_LOC_ID NOT IN ('TRN' , 'OUT' ) ORDER BY LIST_SEQ
}

OIMULOCA_FIND_RGLEVELTYPE {
 	SELECT   DESCRIPTION ,  CODE , ACTIVE_FLAG    FROM REFERENCE_CODES    WHERE DOMAIN = 'ILOC_TYPE'      AND ((ACTIVE_FLAG = 'Y'       AND EXPIRED_DATE IS NULL )          OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIMULOCA_USAGES_FIND_INTERNAL_LOCATION_USAGES {
 	select
	*
from
	INTERNAL_LOCATION_USAGES
where
	AGY_LOC_ID = :AGY_LOC_ID
order by
	TAG_INTERNAL_LOCATIONS_SORT_NON_ASSOCIATION('ILOC_USG',INTERNAL_LOCATION_USAGE)
}

OIMULOCA_USAGES_INSERT_INTERNAL_LOCATION_USAGES {
insert into INTERNAL_LOCATION_USAGES(INTERNAL_LOCATION_USAGE_ID, AGY_LOC_ID, INTERNAL_LOCATION_USAGE, EVENT_SUB_TYPE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) values( nextval('internal_location_usage_id'), :agyLocId, :internalLocationUsage, :eventSubType, CURRENT_TIMESTAMP, :createUserId, NULL, :sealFlag) 
}

OIMULOCA_USAGES_UPDATE_INTERNAL_LOCATION_USAGES {
update INTERNAL_LOCATION_USAGES set AGY_LOC_ID = :agyLocId, INTERNAL_LOCATION_USAGE =:internalLocationUsage, EVENT_SUB_TYPE =:eventSubType, MODIFY_DATETIME =CURRENT_TIMESTAMP, MODIFY_USER_ID =:modifyUserId, SEAL_FLAG =:sealFlag where INTERNAL_LOCATION_USAGE_ID =:internalLocationUsageId 
}

OIMULOCA_INTLOCL1_FIND_INT_LOC_USAGE_LOCATIONS {
 	SELECT  * FROM INT_LOC_USAGE_LOCATIONS WHERE
}
OIMULOCA_INTLOCL1_INSERT_INT_LOC_USAGE_LOCATIONS {
insert into INT_LOC_USAGE_LOCATIONS(INTERNAL_LOCATION_USAGE_ID, INTERNAL_LOCATION_ID, CAPACITY, USAGE_LOCATION_TYPE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, LIST_SEQ, USAGE_LOCATION_ID, PARENT_USAGE_LOCATION_ID) values(:internalLocationUsageId, :internalLocationId, :capacity, :usageLocationType, CURRENT_TIMESTAMP, :createUserId, NULL, :listSeq, nextval('usage_location_id'), :parentUsageLocationId) 
}

OIMULOCA_INTLOCL1_UPDATE_INT_LOC_USAGE_LOCATIONS {
update INT_LOC_USAGE_LOCATIONS set INTERNAL_LOCATION_USAGE_ID =:internalLocationUsageId, INTERNAL_LOCATION_ID =:internalLocationId, CAPACITY =:capacity, USAGE_LOCATION_TYPE =:usageLocationType, MODIFY_DATETIME = CURRENT_TIMESTAMP , MODIFY_USER_ID =:modifyUserId, LIST_SEQ =:listSeq, PARENT_USAGE_LOCATION_ID =:parentUsageLocationId where USAGE_LOCATION_ID =:usageLocationId 
}

OIMULOCA_INTLOCL1_DELETE_INT_LOC_USAGE_LOCATIONS { 
	DELETE FROM INT_LOC_USAGE_LOCATIONS  WHERE USAGE_LOCATION_ID = :usageLocationId
}


OIMULOCA_USAGES_POSTQUERY {
select
	case
		when PARENT_CODE = 'INT_MOV_USG' then 'Y'
		else 'N'
	end CODE
from
	REFERENCE_CODES
where
	domain = 'ILOC_USG'  AND CODE = :INTERNALLOCATIONUSAGE
}

OIMULOCA_USAGES_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM INT_LOC_USAGE_LOCATIONS I WHERE I.INTERNAL_LOCATION_USAGE_ID = :INTERNALLOCATIONUSAGEID
}

OIMULOCA_USAGES_PREUPDATE {
select
	case
		when PARENT_CODE = 'INT_MOV_USG' then 'Y'
		else 'N'
	end CODE
from
	REFERENCE_CODES
where
	domain = 'ILOC_USG'  AND CODE = :INTERNALLOCATIONUSAGE
}

OIMULOCA_INT_LOC_L1_ONCHECKDELETEMASTER {
	SELECT Count(*) FROM INT_LOC_USAGE_LOCATIONS I WHERE I.PARENT_USAGE_LOCATION_ID IN (:USAGELOCATIONID)
}

OIMULOCA_INT_LOC_L2_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM INT_LOC_USAGE_LOCATIONS I WHERE I.PARENT_USAGE_LOCATION_ID = :USAGELOCATIONID
}

OIMULOCA_INT_LOC_L3_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM INT_LOC_USAGE_LOCATIONS I WHERE I.PARENT_USAGE_LOCATION_ID = :USAGELOCATIONID
}
USAGES_PRE_INSERT {
 update REFERENCE_CODES set PARENT_CODE = 'INT_MOV_USG', MODIFY_DATETIME = CURRENT_TIMESTAMP , MODIFY_USER_ID =:modifyUserId where domain = 'ILOC_USG' and CODE = :internalLocationUsage 
}
USAGES_PRE_UPDATE {
update
	REFERENCE_CODES
set
	PARENT_CODE =
	case
		when :chkPermMov = 'Y' then 'INT_MOV_USG'
		else null
	end ,
	MODIFY_USER_ID = :modifyUserId ,
	MODIFY_DATETIME = CURRENT_TIMESTAMP
where
	domain = 'ILOC_USG'
	and CODE = :internalLocationUsage
}
 AGY_INT_LOC_POST_QUERY {
 SELECT INTERNAL_LOCATION_CODE,
             DESCRIPTION,
             USER_DESC,
             INTERNAL_LOCATION_TYPE
        FROM AGENCY_INTERNAL_LOCATIONS
       WHERE INTERNAL_LOCATION_ID = :P_INTERNAL_LOCATION_ID
       }

       
OIMULOCA_INTLOC_ONE_GETVALUES {
	 	with recursive cte as (
select
	internal_location_code,
	description,
	1 as level,
	internal_location_id::varchar code,
	internal_location_id,
	parent_internal_location_id,
	unit_type,
	USER_DESC,
	INTERNAL_LOCATION_TYPE,
	agy_loc_id,
	active_flag 
from
	agency_internal_locations
where
	coalesce(parent_internal_location_id::text, '') = ''
union all
select
	AIL.internal_location_code,
	AIL.description,
	(c.level + 1) location_level,
	AIL.internal_location_id::varchar code,
	AIL.internal_location_id,
	AIL.parent_internal_location_id,
	AIL.unit_type,
	AIL.USER_DESC,
	AIL.INTERNAL_LOCATION_TYPE,
	AIL.agy_loc_id,
	AIL.active_flag 
from
	agency_internal_locations AIL
join cte c on
	(c.internal_location_id = AIL.parent_internal_location_id) )
select
	*
from
	cte
where
	agy_loc_id = :agy_loc_id
order by
	3,
	1
}
OIMULOCA_INTLOC_TWO_GETVALUES {
	select
	internal_location_code,
	description,
	null,
	internal_location_id::text code,
	parent_internal_location_id,
	unit_type,
	active_flag,
	USER_DESC
           INTERNAL_LOCATION_TYPE
from
	agency_internal_locations
where
	parent_internal_location_id = :internal_location_id
	and agy_loc_id = :agy_loc_id
order by
	1
}
