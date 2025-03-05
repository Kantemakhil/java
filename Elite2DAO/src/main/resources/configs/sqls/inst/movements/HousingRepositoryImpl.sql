GET_HOUSING_NAVIGATION {	
  SELECT IMG.IMAGE_ID, FP.FLOOR_PLAN_ID,FP.ROOT_FLOOR_PLAN,ILH.internal_loc_hotspot_id,ILH.X_coordinate,
 ILH.Y_coordinate,ILH.FLOOR_PLAN_NEXT_ID,IL.internal_location_type,IL.description,IL.internal_location_code,IL.internal_location_id,
 IL.parent_internal_location_id,IL.capacity,IL.operation_capacity,IL.no_of_occupant
  FROM oms_owner.FLOOR_PLAN FP, AGENCY_INTERNAL_LOCATIONS IL, oms_owner.AGENCY_INTERNAL_LOC_HOTSPOT ILH, IMAGES IMG
  WHERE  FP.IMAGE_ID=IMG.IMAGE_ID
        AND ILH.internal_location_id=IL.internal_location_id 
         AND il.ACTIVE_FLAG = 'Y'
        AND FP.FLOOR_PLAN_ID=ILH.FLOOR_PLAN_ID  

}

GET_BED_INFO {	
SELECT img.image_id,img.IMAGE_THUMBNAIL,off.last_Name, off.first_Name, off.offender_id_display,off.offender_id,ail.internal_location_type,ail.description
FROM AGENCY_INTERNAL_LOCATIONS ail,offenders off,offender_bookings offBkg 
left outer join images img on offBkg.offender_book_id=IMG.IMAGE_OBJECT_ID 
AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE'
where offBkg.offender_id=off.offender_id and 
ail.internal_location_id=offBkg.living_unit_id 
and ail.parent_internal_location_id =:internalLocationId
}

GET_OPR_CONFLICTS {
	select tag_int_loc_operation_flag(:internalLocationId) from dual
}

GET_NON_ASSOCIATE_CONFLICTS {
	select non_association_check_non_association(:offenderId,:internalLocationId) from dual
}

GET_SICURITY_CONFLICTS {
	select non_association_check_security(:offenderBookId,:internalLocationId) from dual
}

GET_CELL_CONFLICTS {
	select omuavbed_chk_cs_conflict(:offenderBookId,:internalLocationId,:allocatedBeds) from dual
}
GET_ALL_IMAGES {
   select FLOOR_PLAN_ID,IMAGE_ID,ROOT_FLOOR_PLAN,PARENT_FLOOR_PLAN,AGY_LOC_ID from floor_plan 
}

GET_ALLOCATED_OFF_DETAILS{
SELECT off.last_Name, off.first_Name,off.offender_id_display, off.offender_id,img.image_id,img.image_thumbnail,ail.description,ail.capacity
FROM offenders off,AGENCY_INTERNAL_LOCATIONS ail,offender_bookings offBkg
left outer join images img on offBkg.offender_book_id=img.IMAGE_OBJECT_ID
AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE'
where offBkg.offender_id=off.offender_id 
and ail.internal_location_id=offBkg.living_unit_id
and offBkg.living_unit_id=:internalLocationId
}

GET_BASE_IMAGE{
select FLOOR_PLAN_ID from oms_owner.floor_plan where parent_floor_plan is null and agy_loc_id=:agyLocId  

}
GET_BREADCRUMB_FOR_LANDING_PAGE{
select FLOOR_PLAN_ID,IMAGE_ID,ROOT_FLOOR_PLAN,PARENT_FLOOR_PLAN,AGY_LOC_ID from oms_owner.floor_plan where parent_floor_plan is null and root_floor_plan='Y'
}
GET_BREADCRUMB_DETAIL{ 
SELECT FLOOR_PLAN_ID,IMAGE_ID,ROOT_FLOOR_PLAN,PARENT_FLOOR_PLAN,AGY_LOC_ID ,BREAD_CRUMB_NAV_ID  INTERNAL_LOCATION_ID FROM FLOOR_PLAN WHERE FLOOR_PLAN_ID=:floorId  
}
GET_BREADCRUMB{
with recursive cte as
(
select
ail.agy_loc_id,
ail.internal_location_id,
ail.internal_location_code,
floor_plan.floor_plan_id,
floor_plan.IMAGE_ID,
floor_plan.parent_floor_plan,
floor_plan.bread_crumb_nav_id
from
agency_internal_locations ail,
oms_owner.floor_plan
where
floor_plan.bread_crumb_nav_id = ail.internal_location_id
and
floor_plan.floor_plan_id = :floorPlanId
union all
select
A.*
from
(
select
ail.agy_loc_id,
ail.internal_location_id,
ail.internal_location_code,
floor_plan.floor_plan_id,
floor_plan.IMAGE_ID,
floor_plan.parent_floor_plan,
floor_plan.bread_crumb_nav_id
from
agency_internal_locations ail,
oms_owner.floor_plan
where
floor_plan.bread_crumb_nav_id = ail.internal_location_id)A
join cte c on
A.floor_plan_id = A.parent_floor_plan
and
A.parent_floor_plan != A.floor_plan_id)
select
agy_loc_id,
internal_location_id,
internal_location_code,
floor_plan_id,
IMAGE_ID
from
cte
order by
bread_crumb_nav_id
}

GET_CELL_PARENT_CHILD_REL {
SELECT PARENT_INTERNAL_LOCATION_ID, INTERNAL_LOCATION_ID FROM AGENCY_INTERNAL_LOCATIONS 
where agy_loc_id=:agyLocId AND  INTERNAL_LOCATION_Id  NOT IN (Select PARENT_INTERNAL_LOCATION_ID from AGENCY_INTERNAL_LOCATIONS WHERE  agy_loc_id=:agyLocId and PARENT_INTERNAL_LOCATION_ID is not null)
AND PARENT_INTERNAL_LOCATION_ID is not null
GROUP BY PARENT_INTERNAL_LOCATION_ID,internal_location_id
ORDER BY PARENT_INTERNAL_LOCATION_ID
}

PARENT_LOCATION_COUNT {
select count(*) from AGENCY_INTERNAL_LOCATIONS where PARENT_INTERNAL_LOCATION_ID=:internalLocationId
}

GET_BED_INFO_FOR_SELECTED_HOTSPOT{
SELECT img.image_id,img.IMAGE_THUMBNAIL,off.last_Name, off.first_Name, off.offender_id,ail.internal_location_type,ail.description
FROM AGENCY_INTERNAL_LOCATIONS ail,offenders off,offender_bookings offBkg 
left outer join images img on offBkg.offender_book_id=IMG.IMAGE_OBJECT_ID 
AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE'
where offBkg.offender_id=off.offender_id and 
ail.internal_location_id=offBkg.living_unit_id 
and ail.internal_location_id =:internalLocationId
}

GET_INFO_FOR_FLOOR_PLAN{
 SELECT IMG.IMAGE_ID, FP.FLOOR_PLAN_ID,FP.ROOT_FLOOR_PLAN,ILH.internal_loc_hotspot_id,ILH.X_coordinate,
 ILH.Y_coordinate,ILH.FLOOR_PLAN_NEXT_ID,IL.internal_location_type,IL.description,IL.internal_location_code,IL.internal_location_id,
 IL.parent_internal_location_id,IL.capacity,IL.operation_capacity,IL.no_of_occupant
  FROM oms_owner.FLOOR_PLAN FP, AGENCY_INTERNAL_LOCATIONS IL, oms_owner.AGENCY_INTERNAL_LOC_HOTSPOT ILH, IMAGES IMG
  WHERE  FP.FLOOR_PLAN_ID = IMG.IMAGE_OBJECT_ID
        AND ILH.internal_location_id=IL.internal_location_id 
        AND FP.FLOOR_PLAN_ID=ILH.FLOOR_PLAN_ID AND IL.internal_location_id =:internalLocationId
}

ASSIGN_DEFUALT_LOCATION {
update offender_bookings 
set LIVING_UNIT_ID=6095 where offender_book_id in (
select offender_book_id from V_HEADER_BLOCK_FN(:USERID) v_header_block where first_Name not in ('SHERLOCK','MICROFT','JOHN','JIM') 
AND BIRTH_DATE != '15-08-1983' and agy_loc_id='STAG')
}

GET_UNIT_PLAN_IMAGE_ID {
 SELECT FP.IMAGE_ID 
 FROM oms_owner.FLOOR_PLAN FP
 WHERE FP.AGY_LOC_ID=:AGY_LOC_ID 
        AND FP.BREAD_CRUMB_NAV_ID =:UNIT_ID 
        AND FP.ROOT_FLOOR_PLAN= 'N'
}

GET_FACILITY_PLAN_IMAGE_ID {
 SELECT FP.IMAGE_ID 
 FROM oms_owner.FLOOR_PLAN FP
 WHERE FP.AGY_LOC_ID=:AGY_LOC_ID 
        AND FP.ROOT_FLOOR_PLAN= 'Y'
}

GET_HOTSPOTS_FOR_FACILITY_PLAN {
 SELECT IL.INTERNAL_LOCATION_ID, IL.INTERNAL_LOCATION_CODE, IL.PARENT_INTERNAL_LOCATION_ID, IL.INTERNAL_LOCATION_TYPE,
 IL.ACTIVE_FLAG, ILH.INTERNAL_LOC_HOTSPOT_ID, ILH.X_COORDINATE, ILH.Y_COORDINATE 
  FROM oms_owner.FLOOR_PLAN FP 
  INNER JOIN AGENCY_INTERNAL_LOC_HOTSPOT ILH ON FP. FLOOR_PLAN_ID = ILH.FLOOR_PLAN_ID 
  INNER JOIN AGENCY_INTERNAL_LOCATIONS IL ON IL.INTERNAL_LOCATION_ID = ILH.INTERNAL_LOCATION_ID 
  WHERE FP.AGY_LOC_ID=:AGY_LOC_ID 
}

GET_HOTSPOTS_FOR_FLOOR_PLAN {
select
IL.INTERNAL_LOCATION_ID,
IL.INTERNAL_LOCATION_CODE,
IL.PARENT_INTERNAL_LOCATION_ID,
IL.INTERNAL_LOCATION_TYPE,
IL.ACTIVE_FLAG,
ILH.INTERNAL_LOC_HOTSPOT_ID,
ILH.X_COORDINATE,
ILH.Y_COORDINATE
from
oms_owner.FLOOR_PLAN FP
inner join AGENCY_INTERNAL_LOC_HOTSPOT ILH on
FP. FLOOR_PLAN_ID = ILH.FLOOR_PLAN_ID
inner join AGENCY_INTERNAL_LOCATIONS IL on
IL.INTERNAL_LOCATION_ID = ILH.INTERNAL_LOCATION_ID
where
FP.AGY_LOC_ID = :AGY_LOC_ID
and
FP.BREAD_CRUMB_NAV_ID in (WITH RECURSIVE cte AS (
select
PARENT_INTERNAL_LOCATION_ID, INTERNAL_LOCATION_ID
from AGENCY_INTERNAL_LOCATIONS
where
PARENT_INTERNAL_LOCATION_ID =:UNIT_ID
UNION ALL
select
ail.PARENT_INTERNAL_LOCATION_ID, ail.INTERNAL_LOCATION_ID
from AGENCY_INTERNAL_LOCATIONS ail
JOIN cte c ON c.INTERNAL_LOCATION_ID = ail.PARENT_INTERNAL_LOCATION_ID )
SELECT PARENT_INTERNAL_LOCATION_ID FROM cte

)
and
FP.ROOT_FLOOR_PLAN = 'N'
 
}

GET_CHILD_HOTSPOTS_FOR_FLOOR{ 
with recursive cte as (
select
 PARENT_INTERNAL_LOCATION_ID,
 INTERNAL_LOCATION_CODE,
 INTERNAL_LOCATION_TYPE,
 ACTIVE_FLAG,
 INTERNAL_LOCATION_ID,
 array[ row_number() over (
 order by PARENT_INTERNAL_LOCATION_ID) ] as hierarchy
from
 AGENCY_INTERNAL_LOCATIONS
where
 PARENT_INTERNAL_LOCATION_ID = :internalLocationId
union all
select
 ail.PARENT_INTERNAL_LOCATION_ID,
 ail.INTERNAL_LOCATION_CODE,
 ail.INTERNAL_LOCATION_TYPE,
 ail.ACTIVE_FLAG,
 ail.INTERNAL_LOCATION_ID,
 array_append(c.hierarchy, row_number() over (order by ail.PARENT_INTERNAL_LOCATION_ID)) as hierarchy
from
 AGENCY_INTERNAL_LOCATIONS ail
join cte c on
 (c.INTERNAL_LOCATION_ID = ail.PARENT_INTERNAL_LOCATION_ID )

)
select
 *
from
 cte
order by
 hierarchy;
}

GET_CHILD_HOTSPOT_FOR_PARENT_FLOOR { 
select agy_loc_id, PARENT_INTERNAL_LOCATION_ID, INTERNAL_LOCATION_CODE,INTERNAL_LOCATION_TYPE,ACTIVE_FLAG,INTERNAL_LOCATION_ID from agency_internal_locations WHERE  agy_loc_id = :agyLocId
    AND active_flag = 'Y' AND unit_type IS NOT NULL
    AND deactivate_date IS NULL
    
}

GET_CHILD_HOTSPOT_FOR_PARENT_LIVING_UNIT{ 

SELECT  agy_loc_id,
    living_unit_id,
    parent_living_unit_id,
    substr(living_unit_desc, length(:agyLocId) + 2) living_unit_desc,
    user_desc,
    level_1_code,
    level_2_code,
    level_3_code,
    level_4_code
    FROM v_living_unit_summaries 
    WHERE  agy_loc_id = :agyLocId
    AND active_flag = 'Y'
    AND deactivate_date IS NULL
ORDER BY
    list_seq,
    living_unit_desc
}




SET_HOTSPOTS {
 UPDATE AGENCY_INTERNAL_LOC_HOTSPOT 
 SET X_COORDINATE=:X_COORDINATE , Y_COORDINATE=:Y_COORDINATE ,modify_user_id=:modifyUserId,create_datetime=current_timestamp
 WHERE INTERNAL_LOC_HOTSPOT_ID=:HOTSPOT_ID 
       AND INTERNAL_LOCATION_ID=:LOCATION_ID
}
INSERT_HOTSPOTS{ 
insert
 into
 AGENCY_INTERNAL_LOC_HOTSPOT(INTERNAL_LOC_HOTSPOT_ID,
 INTERNAL_LOCATION_ID,
 X_COORDINATE,
 Y_COORDINATE,
 FLOOR_PLAN_ID,
 FLOOR_PLAN_NEXT_ID,
 create_datetime,
 create_user_id
 )
values(NEXTVAL('AGN_INTERNAL_LOC_HOTSPOT_SEQ'),
:internalLocationId,
:xCoordinate,
:yCoordinate,
:floorPlanId,
:floorPlanNextId,
current_timestamp,
:createUserId)
}
GET_HOTSPOTA_FOR_INTERNAL_LOCATION{ 
SELECT COUNT(*) FROM AGENCY_INTERNAL_LOC_HOTSPOT WHERE INTERNAL_LOCATION_ID=:internalLocationId AND FLOOR_PLAN_ID=:floorPlanId
}

GET_INT_LOCATION_ID{ 
SELECT MAX(INTERNAL_LOC_HOTSPOT_ID) FROM AGENCY_INTERNAL_LOC_HOTSPOT WHERE FLOOR_PLAN_ID=:floorPlanId
}
UPDATE_PLAN_IMAGE {
 UPDATE IMAGES IMG 
 SET IMAGE_THUMBNAIL=:IMAGE_THUMBNAIL_DATA 
 WHERE IMAGE_ID=:IMAGE_ID
}

GET_UNIT_PROFILES {
 SELECT ILP.INTERNAL_LOCATION_ID, ILP.INT_LOC_PROFILE_TYPE, ILP.INT_LOC_PROFILE_CODE, ILP.SEAL_FLAG 
 FROM oms_owner.AGY_INT_LOC_PROFILES ILP 
 WHERE ILP.INTERNAL_LOCATION_ID=:UNIT_ID
}
INSERT_IMAGES {
	INSERT INTO IMAGES(IMAGE_ID ,CAPTURE_DATE ,IMAGE_OBJECT_TYPE ,IMAGE_OBJECT_ID ,IMAGE_OBJECT_SEQ ,IMAGE_VIEW_TYPE ,IMAGE_THUMBNAIL ,ACTIVE_FLAG ,ORIENTATION_TYPE ,SEAL_FLAG ,
 CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ) VALUES(:imageId,:captureDate,:imageObjectType,:imageObjectId ,:imageObjectSeq,:imageViewType,:imageThumbnail,
 :activeFlag,:orientationType,:sealFlag ,:createDatetime,:createUserId,:modifyDatetime,:modifyUserId)
	
}
INSERT_FLOOR_PLAN { 
INSERT INTO FLOOR_PLAN(FLOOR_PLAN_ID,IMAGE_ID,ROOT_FLOOR_PLAN,PARENT_FLOOR_PLAN,AGY_LOC_ID,BREAD_CRUMB_NAV_ID,CREATE_DATETIME,CREATE_USER_ID) VALUES(:floorPlanId,:imageId,:rootFloorPlan,:parentFloorPlan,:agyLocId,:unitInternalLocationId,current_timestamp,:createUserId)

}
GET_FLOOR_PLAN_ID{ 
SELECT FLOOR_PLAN_ID FROM FLOOR_PLAN WHERE AGY_LOC_ID=:agyLocId AND BREAD_CRUMB_NAV_ID=:unitInternalLocationId
}
GET_FLOOR_ID{ 
SELECT FLOOR_PLAN_ID FROM FLOOR_PLAN WHERE BREAD_CRUMB_NAV_ID=:unitInternalLocationId
}

GET_FLOOR_DETAILS{ 
SELECT FLOOR_PLAN_ID,PARENT_FLOOR_PLAN FROM FLOOR_PLAN WHERE BREAD_CRUMB_NAV_ID=:unitInternalLocationId AND AGY_LOC_ID=:agyLocId
}
UPDATE_AGY_LOC_INT_HOTSPOT{ 
 UPDATE AGENCY_INTERNAL_LOC_HOTSPOT 
 SET FLOOR_PLAN_NEXT_ID=:floorPlanNextId,modify_user_id=:userName,modify_datetime=current_timestamp
 WHERE INTERNAL_LOCATION_ID=:unitInternalLocationId
}
IMAGE_ID_SEQ{ 
select NEXTVAL('IMAGES_IMAGE_ID_SEQ') from dual
}
FLOOR_PLNA_ID_SEQ { 
select NEXTVAL('FLOOR_PLAN_FLOOR_PLAN_ID_SEQ') from dual
}
UPDATE_FLOOR_PLAN_DETAILS { 
UPDATE FLOOR_PLAN 
 SET IMAGE_ID=:imageId
 WHERE FLOOR_PLAN_ID=:floorPlanId
}
GET_AGENCY_LOCATION_DETAILS { 
SELECT PARENT_INTERNAL_LOCATION_ID, INTERNAL_LOCATION_CODE,INTERNAL_LOCATION_TYPE,ACTIVE_FLAG,INTERNAL_LOCATION_ID FROM AGENCY_INTERNAL_LOCATIONS
 WHERE  AGY_LOC_ID=:agyLocId AND INTERNAL_LOCATION_ID=:unitInternalLocationId
}
UPDATE_HOTSPOTS {
 UPDATE AGENCY_INTERNAL_LOC_HOTSPOT 
 SET X_COORDINATE=:xCoordinate , Y_COORDINATE=:yCoordinate,modify_user_id=:modifyUserId,modify_datetime=current_timestamp
 WHERE INTERNAL_LOC_HOTSPOT_ID=:internalLocHotspotId 
       AND INTERNAL_LOCATION_ID=:internalLocationId
}
GET_GET_FLOOR_ID_BY_IMAGES {
   SELECT FLOOR_PLAN_ID FROM FLOOR_PLAN WHERE IMAGE_ID=:imageId 
}

RESET_HOTSPOTS {
 UPDATE AGENCY_INTERNAL_LOC_HOTSPOT 
 SET X_COORDINATE=:X_COORDINATE , Y_COORDINATE=:Y_COORDINATE,modify_user_id=:userName,modify_datetime=current_timestamp
 WHERE FLOOR_PLAN_ID=:floorPlanId
}
