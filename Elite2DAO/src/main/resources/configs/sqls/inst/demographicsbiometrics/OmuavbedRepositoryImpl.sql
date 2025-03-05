
OMUAVBED_FIND_RGLIVINGUNIT_PAGY {
 SELECT LIV_UNIT.LIVING_UNIT_CODE ,
 LIV_UNIT.LIVING_UNIT_ID, LIV_UNIT.ACTIVE_FLAG
 FROM   LIVING_UNITS LIV_UNIT
 WHERE  LIV_UNIT.AGY_LOC_ID=:AGY_LOC_ID AND
 LIV_UNIT.ACTIVE_FLAG='Y' AND
 LIV_UNIT.PARENT_LIVING_UNIT_ID = :LEVEL1ID
 ORDER BY LIST_SEQ , LIVING_UNIT_CODE
}
	OMUAVBED_FIND_RGLIVINGUNIT_LOCID {
 SELECT LIV_UNIT.LIVING_UNIT_CODE ,
 LIV_UNIT.LIVING_UNIT_ID, LIV_UNIT.ACTIVE_FLAG
 FROM   LIVING_UNITS LIV_UNIT
 WHERE  LIV_UNIT.AGY_LOC_ID=:AGY_LOC_ID AND
 LIV_UNIT.ACTIVE_FLAG='Y' AND
 LIV_UNIT.PARENT_LIVING_UNIT_ID =:LEVEL2ID
 ORDER BY LIST_SEQ , LIVING_UNIT_CODE
}
	OMUAVBED_FIND_RGLIVINGUNIT_LEVELID {
 SELECT LIV_UNIT.LIVING_UNIT_CODE,
 LIV_UNIT.LIVING_UNIT_ID, LIV_UNIT.ACTIVE_FLAG
 FROM   LIVING_UNITS LIV_UNIT
 WHERE   LIV_UNIT.AGY_LOC_ID=:AGY_LOC_ID AND
 LIV_UNIT.ACTIVE_FLAG='Y' AND
 LIV_UNIT.PARENT_LIVING_UNIT_ID = :LEVEL3ID
 ORDER BY LIST_SEQ , LIVING_UNIT_CODE
}
	OMUAVBED_FIND_RGLIVINGUNIT {  
 SELECT LIV_UNIT.LIVING_UNIT_CODE,
 LIV_UNIT.LIVING_UNIT_ID, LIV_UNIT.ACTIVE_FLAG
 FROM   LIVING_UNITS LIV_UNIT
 WHERE  LIV_UNIT.AGY_LOC_ID=:AGY_LOC_ID AND
 LIV_UNIT.ACTIVE_FLAG='Y' AND
 LIV_UNIT.PARENT_LIVING_UNIT_ID IS NULL
 ORDER BY LIST_SEQ , LIVING_UNIT_CODE
}
	OMUAVBED_FIND_RGLIVINGUNITTYPE {
 SELECT REF_CODE.DESCRIPTION  DESCRIPTION ,
 REF_CODE.CODE  CODE
 FROM   REFERENCE_CODES REF_CODE
 WHERE  DOMAIN = 'HOU_UN_TYPE'  AND
 ACTIVE_FLAG = 'Y'
 ORDER BY LIST_SEQ , DESCRIPTION , CODE
}
	OMUAVBED_FIND_RGUSEDFOR {
 SELECT REF_CODE.DESCRIPTION  DESCRIPTION    ,REF_CODE.CODE  CODE FROM   REFERENCE_CODES REF_CODE WHERE  DOMAIN = 'HOU_USED_FOR'  AND    ACTIVE_FLAG = 'Y'  AND    EXPIRED_DATE IS NULL ORDER BY LIST_SEQ , DESCRIPTION , CODE
}

OMUAVBED_LIVINGUNITSTYPE_FIND_LIVING_UNITS {
 SELECT LIVING_UNIT_ID ,AGY_LOC_ID ,LIVING_UNIT_TYPE ,LIVING_UNIT_CODE ,DESCRIPTION ,LEVEL_1_CODE ,LEVEL_2_CODE ,LEVEL_3_CODE ,LEVEL_4_CODE ,USER_DESC ,ACA_CAP_RATING ,SECURITY_LEVEL_CODE ,LIST_SEQ ,PARENT_LIVING_UNIT_ID ,HOUSING_UNIT_TYPE ,ACTIVE_FLAG ,CONTROL_ACTIVE_FLAG ,CNA_NO ,CAPACITY ,OPERATION_CAPACITY ,CERTIFIED_FLAG ,DEACTIVATE_DATE ,REACTIVATE_DATE ,DEACTIVATE_REASON_CODE ,COMMENT_TEXT ,LOWEST_LEVEL_FLAG ,REACH_OPER_CAPACITY_FLAG ,NO_OF_OCCUPANT   FROM LIVING_UNITS  /* where  */
}

OMUAVBED_LIVINGUNITSTYPE_INSERT_LIVING_UNITS {
INSERT INTO LIVING_UNITS(LIVING_UNIT_ID ,AGY_LOC_ID ,LIVING_UNIT_TYPE ,LIVING_UNIT_CODE ,DESCRIPTION ,LEVEL_1_CODE ,LEVEL_2_CODE ,LEVEL_3_CODE ,LEVEL_4_CODE ,USER_DESC ,ACA_CAP_RATING ,SECURITY_LEVEL_CODE ,LIST_SEQ ,PARENT_LIVING_UNIT_ID ,HOUSING_UNIT_TYPE ,ACTIVE_FLAG ,CONTROL_ACTIVE_FLAG ,CNA_NO ,CAPACITY ,OPERATION_CAPACITY ,CERTIFIED_FLAG ,DEACTIVATE_DATE ,REACTIVATE_DATE ,DEACTIVATE_REASON_CODE ,COMMENT_TEXT ,LOWEST_LEVEL_FLAG ,REACH_OPER_CAPACITY_FLAG ,NO_OF_OCCUPANT ) VALUES(:livingUnitId ,:agyLocId ,:livingUnitType ,:livingUnitCode ,:description ,:level1Code ,:level2Code ,:level3Code ,:level4Code ,:userDesc ,:acaCapRating ,:securityLevelCode ,:listSeq ,:parentLivingUnitId ,:housingUnitType ,:activeFlag ,:controlActiveFlag ,:cnaNo ,:capacity ,:operationCapacity ,:certifiedFlag ,:deactivateDate ,:reactivateDate ,:deactivateReasonCode ,:commentText ,:lowestLevelFlag ,:reachOperCapacityFlag ,:noOfOccupant )
}

OMUAVBED_LIVINGUNITSTYPE_UPDATE_LIVING_UNITS {
UPDATE LIVING_UNITS set LIVING_UNIT_ID  = :livingUnitId ,AGY_LOC_ID  = :agyLocId ,LIVING_UNIT_TYPE  = :livingUnitType ,LIVING_UNIT_CODE  = :livingUnitCode ,DESCRIPTION  = :description ,LEVEL_1_CODE  = :level1Code ,LEVEL_2_CODE  = :level2Code ,LEVEL_3_CODE  = :level3Code ,LEVEL_4_CODE  = :level4Code ,USER_DESC  = :userDesc ,ACA_CAP_RATING  = :acaCapRating ,SECURITY_LEVEL_CODE  = :securityLevelCode ,LIST_SEQ  = :listSeq ,PARENT_LIVING_UNIT_ID  = :parentLivingUnitId ,HOUSING_UNIT_TYPE  = :housingUnitType ,ACTIVE_FLAG  = :activeFlag ,CONTROL_ACTIVE_FLAG  = :controlActiveFlag ,CNA_NO  = :cnaNo ,CAPACITY  = :capacity ,OPERATION_CAPACITY  = :operationCapacity ,CERTIFIED_FLAG  = :certifiedFlag ,DEACTIVATE_DATE  = :deactivateDate ,REACTIVATE_DATE  = :reactivateDate ,DEACTIVATE_REASON_CODE  = :deactivateReasonCode ,COMMENT_TEXT  = :commentText ,LOWEST_LEVEL_FLAG  = :lowestLevelFlag ,REACH_OPER_CAPACITY_FLAG  = :reachOperCapacityFlag ,NO_OF_OCCUPANT  = :noOfOccupant /* where */
}

OMUAVBED_LIVUPROFUFOR_FIND_TEMP_LIVING_UNIT_PROFILES {
 SELECT PROFILE_TYPE ,PROFILE_CODE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM TEMP_LIVING_UNIT_PROFILES
}

OMUAVBED_LIVUPROFUFOR_INSERT_TEMP_LIVING_UNIT_PROFILES {
INSERT INTO TEMP_LIVING_UNIT_PROFILES(PROFILE_TYPE ,PROFILE_CODE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ) VALUES(:profileType ,:profileCode ,:createDatetime ,:createUserId ,:modifyDatetime ,:modifyUserId )
} 

OMUAVBED_LIVUPROFUFOR_UPDATE_TEMP_LIVING_UNIT_PROFILES {
UPDATE TEMP_LIVING_UNIT_PROFILES set PROFILE_TYPE  = :profileType ,PROFILE_CODE  = :profileCode ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = :modifyDatetime ,MODIFY_USER_ID  = :modifyUserId /* where */
} 

	OMUAVBED_LIVUPROFUFOR_DELETE_TEMP_LIVING_UNIT_PROFILES {
DELETE FROM TEMP_LIVING_UNIT_PROFILES
} 

OMUAVBED_WHENNEWFORMINSTANCE_LEVEL_CUR{
 SELECT HOUSING_LEV_1_CODE, HOUSING_LEV_2_CODE, HOUSING_LEV_3_CODE, HOUSING_LEV_4_CODE FROM AGENCY_LOCATIONS WHERE AGY_LOC_ID = :pAgyLocId
}
	OMUAVBED_FIND_RGATTRIBUTES{
 SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'HOU_UNIT_ATT' AND
  ACTIVE_FLAG = 'Y' AND
  EXPIRED_DATE IS NULL
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}
OMUAVBED_FIND_LIVING_UNIT_PROFILES_HOU_USED_FOR {
--SELECT DESCRIPTION FROM LIVING_UNIT_PROFILES lp WHERE AGY_LOC_ID = :agyLocId AND lp.INT_LOC_PROFILE_TYPE = 'HOU_USED_FOR' and lp.INT_LOC_PROFILE_CODE = :usedFor
select
	B.DESCRIPTION
from
	(
	select
		lu.living_unit_id,
		tag_int_loc_location_profile_id(lu.living_unit_id,
		rd.domain) as profile_id,
		lu.agy_loc_id,
		lu.description,
		p.int_loc_profile_type,
		p.int_loc_profile_code
	from
		(
		select
			ail.internal_location_id as living_unit_id,
			ail.agy_loc_id,
			ail.description
		from
			agency_internal_locations ail
		where
			ail.unit_type is not null
			and ail.unit_type::text <> ''::text) lu,
		agy_int_loc_profiles p,
		reference_domains rd
	where
		p.internal_location_id = lu.living_unit_id
		and p.int_loc_profile_type::text = rd.domain::text
		and rd.parent_domain::text = 'INT_LOC_PFL'::text) A
left outer join (
	select
		lu.living_unit_id,
		tag_int_loc_location_profile_id(lu.living_unit_id,
		rd.domain) as profile_id,
		lu.agy_loc_id,
		lu.description,
		p.int_loc_profile_type,
		p.int_loc_profile_code
	from
		(
		select
			ail.internal_location_id as living_unit_id,
			ail.agy_loc_id,
			ail.description
		from
			agency_internal_locations ail
		where
			ail.unit_type is not null
			and ail.unit_type::text <> ''::text) lu,
		agy_int_loc_profiles p,
		reference_domains rd
	where
		p.internal_location_id = lu.living_unit_id
			and p.int_loc_profile_type::text = rd.domain::text
			and rd.parent_domain::text = 'INT_LOC_PFL'::text) B on
	A.living_unit_id = B.living_unit_id
where
	A.AGY_LOC_ID = :agyLocId
	and A.INT_LOC_PROFILE_TYPE = 'HOU_USED_FOR'
	and A.INT_LOC_PROFILE_CODE = :usedForOne
	and B.AGY_LOC_ID = :agyLocId
	and B.INT_LOC_PROFILE_TYPE = 'HOU_USED_FOR'
	and B.INT_LOC_PROFILE_CODE in (:usedFor)
	and B.DESCRIPTION is not null;
}
OMUAVBED_FIND_LIVING_UNIT_PROFILES_HOU_UNIT_ATT {
--SELECT DESCRIPTION FROM LIVING_UNIT_PROFILES lp WHERE AGY_LOC_ID = :agyLocId AND  INT_LOC_PROFILE_CODE = :attribute and INT_LOC_PROFILE_TYPE = 'HOU_UNIT_ATT'
select
	B.DESCRIPTION
from
	(
	select
		lu.living_unit_id,
		tag_int_loc_location_profile_id(lu.living_unit_id,
		rd.domain) as profile_id,
		lu.agy_loc_id,
		lu.description,
		p.int_loc_profile_type,
		p.int_loc_profile_code
	from
		(
		select
			ail.internal_location_id as living_unit_id,
			ail.agy_loc_id,
			ail.description
		from
			agency_internal_locations ail
		where
			ail.unit_type is not null
			and ail.unit_type::text <> ''::text) lu,
		agy_int_loc_profiles p,
		reference_domains rd
	where
		p.internal_location_id = lu.living_unit_id
		and p.int_loc_profile_type::text = rd.domain::text
		and rd.parent_domain::text = 'INT_LOC_PFL'::text) A
left outer join (
	select
		lu.living_unit_id,
		tag_int_loc_location_profile_id(lu.living_unit_id,
		rd.domain) as profile_id,
		lu.agy_loc_id,
		lu.description,
		p.int_loc_profile_type,
		p.int_loc_profile_code
	from
		(
		select
			ail.internal_location_id as living_unit_id,
			ail.agy_loc_id,
			ail.description
		from
			agency_internal_locations ail
		where
			ail.unit_type is not null
			and ail.unit_type::text <> ''::text) lu,
		agy_int_loc_profiles p,
		reference_domains rd
	where
		p.internal_location_id = lu.living_unit_id
			and p.int_loc_profile_type::text = rd.domain::text
			and rd.parent_domain::text = 'INT_LOC_PFL'::text) B on
	A.living_unit_id = B.living_unit_id
where
	A.AGY_LOC_ID = :agyLocId
	and A.INT_LOC_PROFILE_TYPE = 'HOU_UNIT_ATT'
	and A.INT_LOC_PROFILE_CODE = :attributeOne
	and B.AGY_LOC_ID = :agyLocId
	and B.INT_LOC_PROFILE_TYPE = 'HOU_UNIT_ATT'
	and B.INT_LOC_PROFILE_CODE in (:attribute)
	and B.DESCRIPTION is not null;
}

OMUAVBED_FIND_LIVING_UNIT_PROFILES_LIVING_UNIT_ID {
SELECT LIVING_UNIT_ID, PROFILE_ID, AGY_LOC_ID, DESCRIPTION, INT_LOC_PROFILE_TYPE, INT_LOC_PROFILE_CODE FROM LIVING_UNIT_PROFILES lp WHERE LIVING_UNIT_ID = :livingUnitId
}

OMUAVBED_FIND_GETDESCCODE {
	select oms_miscellaneous_GetDescCode('LIVING_UNIT', :BLOCK) from DUAL

}