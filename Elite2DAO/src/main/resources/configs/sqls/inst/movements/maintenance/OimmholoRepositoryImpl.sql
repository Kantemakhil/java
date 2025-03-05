
OIMMHOLO_FIND_RGAGYLOCLOV {
 SELECT agy_loc.description, agy_loc.agy_loc_id code , agy_loc.active_flag FROM agency_locations agy_loc WHERE agy_loc.agy_loc_id IN ( SELECT ca.agy_loc_id FROM caseload_agency_locations ca WHERE ca.caseload_id = ( SELECT working_caseload_id FROM staff_members WHERE user_id = :userId ) ) AND agy_loc_id NOT IN ( 'TRN', 'OUT' )
}

OIMMHOLO_FIND_RGDEACTLURSN {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION        ,REF_CODE.CODE  CODE, REF_CODE.list_seq FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'LIV_UN_RSN' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ORDER BY LIST_SEQ
}

OIMMHOLO_FIND_RGUSEDFOR {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION        ,REF_CODE.CODE  CODE, REF_CODE.list_seq FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'HOU_USED_FOR' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ORDER BY LIST_SEQ
}

OIMMHOLO_FIND_RGHOUUNITATT {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION        ,REF_CODE.CODE  CODE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'HOU_UNIT_ATT' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ORDER BY LIST_SEQ
}

OIMMHOLO_FIND_RGNONASSOTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION        ,REF_CODE.CODE  CODE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'NON_ASSO_TYP'  AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ORDER BY LIST_SEQ
}

OIMMHOLO_FIND_RGSUPLVLTYPE {
    SELECT DISTINCT oms_miscellaneous_getdesccode('SUP_LVL_TYPE', a.supervision_level_type) description, a.supervision_level_type code, a.active_flag FROM assessment_results a WHERE a.expiry_date IS NULL AND a.active_flag = 'Y' AND EXISTS ( SELECT assessment_id FROM assessments aa WHERE determine_sup_level_flag = 'Y' AND a.assessment_id = aa.assessment_id )
}

OIMMHOLO_FIND_RGHOUUNTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION        ,REF_CODE.CODE,  REF_CODE.list_seq  CODE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'HOU_UN_TYPE' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ORDER BY LIST_SEQ
}

OIMMHOLO_LIVUNITS_FIND_LIVING_UNITS {
 	SELECT LIVING_UNIT_ID ,AGY_LOC_ID ,LIVING_UNIT_TYPE ,LIVING_UNIT_CODE ,DESCRIPTION ,LEVEL_1_CODE ,LEVEL_2_CODE ,LEVEL_3_CODE ,LEVEL_4_CODE ,USER_DESC ,ACA_CAP_RATING ,SECURITY_LEVEL_CODE ,LIST_SEQ ,PARENT_LIVING_UNIT_ID ,HOUSING_UNIT_TYPE ,ACTIVE_FLAG ,CONTROL_ACTIVE_FLAG ,CNA_NO ,CAPACITY ,OPERATION_CAPACITY ,CERTIFIED_FLAG ,DEACTIVATE_DATE ,REACTIVATE_DATE ,DEACTIVATE_REASON_CODE ,COMMENT_TEXT ,LOWEST_LEVEL_FLAG ,REACH_OPER_CAPACITY_FLAG ,NO_OF_OCCUPANT   FROM LIVING_UNITS WHERE AGY_LOC_ID = :AGYLOCID AND PARENT_LIVING_UNIT_ID IS NULL ORDER BY LIST_SEQ,DESCRIPTION
}
OIMMHOLO_LIVUNITS_FIND_LIVING_UNITS_ONE {
select LIVING_UNIT_ID , AGY_LOC_ID , LIVING_UNIT_TYPE , LIVING_UNIT_CODE , DESCRIPTION , LEVEL_1_CODE , LEVEL_2_CODE , LEVEL_3_CODE , LEVEL_4_CODE , USER_DESC , ACA_CAP_RATING , SECURITY_LEVEL_CODE , LIST_SEQ , PARENT_LIVING_UNIT_ID , HOUSING_UNIT_TYPE , ACTIVE_FLAG , CONTROL_ACTIVE_FLAG , CNA_NO , CAPACITY , OPERATION_CAPACITY , CERTIFIED_FLAG , DEACTIVATE_DATE , REACTIVATE_DATE , DEACTIVATE_REASON_CODE , COMMENT_TEXT , LOWEST_LEVEL_FLAG , REACH_OPER_CAPACITY_FLAG , NO_OF_OCCUPANT, ( select SUM(no_of_occupant) from agency_internal_locations where internal_location_id in (with recursive cte as ( select internal_location_id from agency_internal_locations where internal_location_id = LIVING_UNIT_ID union all select lu.internal_location_id from agency_internal_locations lu join cte c on (c.internal_location_id = lu.parent_internal_location_id) ) select * from cte) ) as numbers from LIVING_UNITS where PARENT_LIVING_UNIT_ID = :LIVINGUNITID order by LIST_SEQ,DESCRIPTION
}
OIMMHOLO_LIVUNITS_INSERT_LIVING_UNITS {
insert into LIVING_UNITS(LIVING_UNIT_ID , AGY_LOC_ID , LIVING_UNIT_TYPE , LIVING_UNIT_CODE , DESCRIPTION , USER_DESC , ACA_CAP_RATING , SECURITY_LEVEL_CODE , LIST_SEQ , PARENT_LIVING_UNIT_ID , HOUSING_UNIT_TYPE , ACTIVE_FLAG , CNA_NO , CAPACITY , OPERATION_CAPACITY , CERTIFIED_FLAG , DEACTIVATE_DATE , REACTIVATE_DATE , DEACTIVATE_REASON_CODE , COMMENT_TEXT , NO_OF_OCCUPANT, create_user_id, create_datetime,modify_datetime ) values(:livingUnitId , :agyLocId , :livingUnitType , :livingUnitCode , :description , :userDesc , :acaCapRating , :securityLevelCode , :listSeq , :parentLivingUnitId , :housingUnitType , :activeFlag , :cnaNo , :capacity , :operationCapacity , :certifiedFlag , :deactivateDate , :reactivateDate , :deactivateReasonCode , :commentText , 0 , :createUserId, current_timestamp,null)
}

OIMMHOLO_LIVUNITS_UPDATE_LIVING_UNITS {
update LIVING_UNITS set LIST_SEQ = :listSeq , HOUSING_UNIT_TYPE = :housingUnitType , ACTIVE_FLAG = :activeFlag , CAPACITY = :capacity , OPERATION_CAPACITY = :operationCapacity , DEACTIVATE_DATE = :deactivateDate , DEACTIVATE_REASON_CODE = :deactivateReasonCode , COMMENT_TEXT = :commentText, modify_datetime = current_timestamp, modify_user_id =:modifyUserId where LIVING_UNIT_ID = :livingUnitId and AGY_LOC_ID = :agyLocId 
}

OIMMHOLO_USEDFOR_FIND_AGY_INT_LOC_PROFILES {
SELECT internal_location_id, int_loc_profile_type, int_loc_profile_code, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, row_id FROM agy_int_loc_profiles
}
OIMMHOLO_USEDFOR_INSERT_AGY_INT_LOC_PROFILES {
	INSERT INTO AGY_INT_LOC_PROFILES(INTERNAL_LOCATION_ID ,INT_LOC_PROFILE_TYPE ,INT_LOC_PROFILE_CODE ,CREATE_DATETIME, modify_datetime, CREATE_USER_ID ,SEAL_FLAG ) VALUES(:internalLocationId ,:intLocProfileType ,:intLocProfileCode ,current_timestamp, null, :createUserId , :sealFlag )
}

OIMMHOLO_USEDFOR_UPDATE_AGY_INT_LOC_PROFILES {
	UPDATE AGY_INT_LOC_PROFILES set INTERNAL_LOCATION_ID  = :internalLocationId ,INT_LOC_PROFILE_TYPE  = :intLocProfileType ,
	INT_LOC_PROFILE_CODE  = :intLocProfileCode ,MODIFY_DATETIME  = current_timestamp ,MODIFY_USER_ID  = :modifyUserId ,SEAL_FLAG  = :sealFlag WHERE INTERNAL_LOCATION_ID  = :internalLocationId AND INT_LOC_PROFILE_TYPE  = :intLocProfileType AND ROW_ID = :rowId
}

OIMMHOLO_USEDFOR_DELETE_AGY_INT_LOC_PROFILES { 
	DELETE FROM AGY_INT_LOC_PROFILES WHERE INTERNAL_LOCATION_ID  = :internalLocationId AND INT_LOC_PROFILE_TYPE  = :intLocProfileType AND INT_LOC_PROFILE_CODE  = :intLocProfileCode
}

OIMMHOLO_LUPROF_FIND_LIVING_UNIT_PROFILES {
 	SELECT LIVING_UNIT_ID ,PROFILE_ID ,AGY_LOC_ID ,DESCRIPTION ,INT_LOC_PROFILE_TYPE ,INT_LOC_PROFILE_CODE   FROM LIVING_UNIT_PROFILES  
}
OIMMHOLO_LUPROF_INSERT_LIVING_UNIT_PROFILES {
	INSERT INTO LIVING_UNIT_PROFILES(LIVING_UNIT_ID ,PROFILE_ID ,AGY_LOC_ID ,DESCRIPTION ,INT_LOC_PROFILE_TYPE ,INT_LOC_PROFILE_CODE )
	VALUES(:livingUnitId ,:profileId ,:agyLocId ,:description ,:intLocProfileType ,:intLocProfileCode )
}

OIMMHOLO_LUPROF_UPDATE_LIVING_UNIT_PROFILES {
	UPDATE LIVING_UNIT_PROFILES set LIVING_UNIT_ID  = :livingUnitId ,PROFILE_ID  = :profileId ,AGY_LOC_ID  = :agyLocId ,DESCRIPTION  = :description ,INT_LOC_PROFILE_TYPE  = :intLocProfileType ,INT_LOC_PROFILE_CODE  = :intLocProfileCode 
}

OIMMHOLO_LUPROF_DELETE_LIVING_UNIT_PROFILES { 
	DELETE FROM LIVING_UNIT_PROFILES
}


OIMMHOLO_LIV_UNITS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM LIVING_UNIT_PROFILES L WHERE L.LIVING_UNIT_ID = :LIVINGUNITID
}

OIMMHOLO_LIV_UNITS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM LIVING_UNIT_PROFILES L WHERE L.LIVING_UNIT_ID = :LIVINGUNITID
}

OIMMHOLO_LIV_UNITS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM LIVING_UNIT_PROFILES L WHERE L.LIVING_UNIT_ID = :LIVINGUNITID
}

OIMMHOLO_LIV_UNITS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM LIVING_UNIT_PROFILES L WHERE L.LIVING_UNIT_ID = :LIVINGUNITID
}

OIMMHOLO_DEFAULT_AGYRSN_ {
	SELECT COUNT(0) FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGY_LOC_ID  IN (  SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID  = V_GLOBAL_CASELOAD_ID) AND AGY_LOC_ID NOT IN ('TRN', 'OUT')
}

OIMMHOLO_DEFAULT_AGYRSN_ {
	SELECT AGY_LOC.AGY_LOC_ID  AGY_LOC_ID ,AGY_LOC.DESCRIPTION  DSP_DESCRIPTION2 FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGY_LOC_ID  IN ( SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID  = V_GLOBAL_CASELOAD_ID) AND AGY_LOC_ID NOT IN ('TRN', 'OUT')
}

OIMMHOLO_CELL_BLOCK_DATA {
       SELECT OMS_MISCELLANEOUS_GETDESCCODE ( 'LIVING_UNIT', :CELLBLOCK) FROM DUAL

}

OIMMHOLO_GET_NEXT_LU_ID {
select NEXTVAL('internal_location_id')
}

OIMMHOLO_GET_CURRENT_LEVEL {
 WITH recursive cte AS ( SELECT living_unit_id,parent_living_unit_id FROM living_units WHERE living_unit_id = :LIVINGUNITID UNION ALL SELECT lu.living_unit_id,lu.parent_living_unit_id FROM living_units lu JOIN cte c ON (c.parent_living_unit_id = lu.living_unit_id)

) SELECT count(*)+1 FROM cte
}

OIMMHOLO_GET_NEW_LU_TYPE {
       SELECT case :p_level when 1 then housing_lev_1_code when 2 then housing_lev_2_code when 3 then housing_lev_3_code when 4 then housing_lev_4_code end FROM agency_locations WHERE agy_loc_id = :AGYLOCID
}

OIMMHOLO_ACTIVE_FLAG_VALIDATION {
select
	SUM(no_of_occupant)
from
	agency_internal_locations
where
	internal_location_id in (with recursive cte as (
	select
		internal_location_id
	from
		agency_internal_locations
	where
		internal_location_id = :LIVINGUNITID
union all
	select
		lu.internal_location_id
	from
		agency_internal_locations lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id) )
	select
		*
	from
		cte)
}
OIMMHOLO_NO_OF_OCCUPANT {
SELECT NO_OF_OCCUPANT
           FROM LIVING_UNITS
          WHERE LIVING_UNIT_ID = :LIVINGUNITID
            AND LOWEST_LEVEL_FLAG = 'Y'
}
OIMMHOLO_LIVING_UNIT_PROFILES_EDITABLE {
SELECT COUNT(*)
           FROM DUAL
          WHERE EXISTS (SELECT NULL
                   FROM LIVING_UNIT_PROFILES
                  WHERE LIVING_UNIT_ID = :LIVINGUNITID
                    AND LIVING_UNIT_ID <> PROFILE_ID)
}
OIMMHOLO_OMS_MISCELLANEOUS_P_REF_DOMAIN {
   SELECT OMS_MISCELLANEOUS_GETDESCCODE(INT_LOC_PROFILE_TYPE,
                                              INT_LOC_PROFILE_CODE) DESCRIPTION
           FROM LIVING_UNIT_PROFILES
          WHERE LIVING_UNIT_ID = :LIVINGUNITID
            AND INT_LOC_PROFILE_TYPE = :REFDOMAIN
}
OIMMHOLO_CONSTRAINT_VALIDATIONS {
 SELECT TABLE_NAME
        FROM ALL_CONS_COLUMNS
       WHERE CONSTRAINT_NAME = :P_CONSTRAINT_NAME
}
GETTING_AGYINTLOCPROFILEST1{
SELECT * FROM AGY_INT_LOC_AMENDMENTS WHERE AGY_INT_LOC_AMENDMENT_ID =:AGY_INT_LOC_AMENDMENT_ID
}
OIMMHOLO_GETTING_OLD_DATA{
select * from AGENCY_INTERNAL_LOCATIONS where INTERNAL_LOCATION_ID=:internalLocationId
}

OIMMHOLO_LIVUNITS_FIND{ 

SELECT LIVING_UNIT_ID ,AGY_LOC_ID ,LIVING_UNIT_TYPE ,LIVING_UNIT_CODE ,DESCRIPTION ,LEVEL_1_CODE ,LEVEL_2_CODE ,LEVEL_3_CODE ,LEVEL_4_CODE ,PARENT_LIVING_UNIT_ID ,HOUSING_UNIT_TYPE ,ACTIVE_FLAG ,CONTROL_ACTIVE_FLAG    FROM LIVING_UNITS WHERE LIVING_UNIT_ID=:livingUnitId 
}

OIMMHOLO_GETTING_OLD_DATA_AGY_INT_LOC_AMENDMENTS{
select * from AGY_INT_LOC_PROFILES where row_id =:row_id;
}

OIMMHOL_AGY_INT_LOC_PROFILES_T1_INSERT_AGY_INT_LOC_AMENDMENTS{
UPDATE agy_int_loc_amendments set action_code  = :actionCode ,amend_user_id  = :amendUserId ,modify_datetime  =current_timestamp ,modify_user_id  = :modifyUserId ,column_name=:columnName,deactivate_reason_code =:deactivateReasonCode where internal_location_id=:internalLocationId

}

OIMMHOL_AGY_INT_LOC_AMENDMENTS{
 SELECT *  FROM AGY_INT_LOC_AMENDMENTS WHERE AGY_INT_LOC_AMENDMENT_ID =:agyIntLocmendent
}

OIMMHOLO_INSERT_AGENCY_INTERNAL_LOCATIONS{
INSERT INTO AGENCY_INTERNAL_LOCATIONS(INTERNAL_LOCATION_ID, INTERNAL_LOCATION_CODE, AGY_LOC_ID, INTERNAL_LOCATION_TYPE, DESCRIPTION, SECURITY_LEVEL_CODE, CAPACITY, CREATE_USER_ID, PARENT_INTERNAL_LOCATION_ID, ACTIVE_FLAG, LIST_SEQ, CREATE_DATETIME, MODIFY_DATETIME, CNA_NO, CERTIFIED_FLAG, DEACTIVATE_DATE, REACTIVATE_DATE, DEACTIVATE_REASON_CODE, COMMENT_TEXT, USER_DESC, ACA_CAP_RATING, UNIT_TYPE, OPERATION_CAPACITY, NO_OF_OCCUPANT, TRACKING_FLAG, SEAL_FLAG)
	VALUES(:internalLocationId, :internalLocationCode, :agyLocId, :internalLocationType, :description, :securityLevelCode, :capacity, :createUserId, :parentInternalLocationId, :activeFlag, :listSeq, current_timestamp, null,:cnaNo, :certifiedFlag, :deactivateDate, :reactivateDate, :deactiveReasonCode, :commentText, :userDesc, :acaCapRating, :unitType, :operationCapacity, :noOfOccupant, :trackingFlag, :sealFlag)
}

OIMMHOLO_AGENCY_INTERNAL_LOCATIONS_OLD_DATA{
 SELECT * FROM AGENCY_INTERNAL_LOCATIONS WHERE INTERNAL_LOCATION_ID=:internalLocId
}

OIMMHOLO_IEP_LEVEL_COMMIT{
insert into living_unit_iep_levels(internal_location_id, iep_level_code, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values(:internalLocationId, :iepLevelCode, current_timestamp, :createUserId, null, null, :sealFlag)
}

OIMMHOLO_FETCH_IEP_DETAILS{
select internal_location_id, iep_level_code,(select iep_level_description from incentives_earn_privs iep where iep_level_code =lvl.iep_level_code ) as iepLeveldescription from living_unit_iep_levels lvl where internal_location_id =:internalLocationId
}

OIMMHOLO_UPDATE_IEP_CODE{
update living_unit_iep_levels set iep_level_code =:iepLevelCode, MODIFY_DATETIME=current_timestamp ,MODIFY_USER_ID=:modifyUserId where internal_location_id =:internalLocationId 
}


OIMMHOLO_DELETE_IEP_CODE{
delete from living_unit_iep_levels where internal_location_id =:internalLocationId 
}

OIMMHOLO_GET_PARENT_LIVING_UNIT_ID{
select parent_living_unit_id from living_units lu where living_unit_id =:internalLocationId
}

OIMMHOLO_FETCH_IEP_LEVEL_CODE{
select 	concat(( select iep_level_description from incentives_earn_privs iep where iep.iep_level_code = lu.iep_level_code),',',iep_level_code) iep_level_description
 from living_unit_iep_levels lu where internal_location_id =:internalLocationId
}

OIMMHOLO_GET_AGY_LOC_ID{
select agy_loc_id from agency_internal_locations ail where internal_location_id =:internalLocationId
}

OIMMHOLO_FETCH_FACILITY_IEP_LEVEL{
select iep_level_code,(select iep_level_description from incentives_earn_privs iep where iep_level_code =alil.iep_level_code) iep_leveldescription from agy_loc_iep_levels alil where agy_loc_id =:agyLocId
}

OIMMHOLO_LIVUNITS_UPDATE_CHILD_LIVING_UNITS{
	update LIVING_UNITS set ACTIVE_FLAG = :activeFlag , DEACTIVATE_DATE = :deactivateDate , DEACTIVATE_REASON_CODE = :deactivateReasonCode,modify_user_id =:modifyUserId, modify_datetime = current_timestamp where LIVING_UNIT_ID in (with recursive cte as ( select lu1.living_unit_id,lu1.parent_living_unit_id from living_units as lu1 where lu1.living_unit_id = :livingUnitId union select lu2.living_unit_id,lu2.parent_living_unit_id from living_units lu2 join cte on cte.living_unit_id = lu2.parent_living_unit_id ) select cte.living_unit_id from cte) and AGY_LOC_ID = :agyLocId
}

OIMMHOLO_GET_LIVEINGUNITID_IEP_CODE
{
select iep_level_code from living_unit_iep_levels where internal_location_id in(with recursive cte as ( select lu.internal_location_id, lu.parent_internal_location_id from agency_internal_locations lu where lu.internal_location_id =:internalLocationId union all select lu1.internal_location_id, lu1.parent_internal_location_id from agency_internal_locations lu1 join cte on cte.parent_internal_location_id = lu1.internal_location_id ) select internal_location_id from cte order by internal_location_id desc) order by internal_location_id desc limit 1
}

OIMMHOLO_GET_AGENCY_IEP_CODE{
select iep_level_code  from agy_loc_iep_levels where agy_loc_id =:agyLocId 
}

OIMMHOLO_GET_DEFAULT_IEP_CODE{
select iep_level_code from incentives_earn_privs iep where intake_iep ='Y' limit 1
}