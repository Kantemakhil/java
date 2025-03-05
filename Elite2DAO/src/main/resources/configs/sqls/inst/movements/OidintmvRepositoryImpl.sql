
OIDINTMV_FIND_RGESTABLISHMENT {
 	SELECT AGY_LOC.DESCRIPTION DESCRIPTION              , AGY_LOC.AGY_LOC_ID CODE  FROM AGENCY_LOCATIONS AGY_LOC  WHERE ((ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' )    AND AGY_LOC.AGY_LOC_ID IN (SELECT AGY_LOC_ID                                 FROM CASELOAD_AGENCY_LOCATIONS                                WHERE CASELOAD_ID = :caseLoadId ) AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) ORDER BY AGY_LOC.DESCRIPTION
}

OIDINTMV_FIND_RGFROMHLOCLEVEL1 {
SELECT LEVEL_1_CODE DESCRIPTION ,LIVING_UNIT_ID, ACTIVE_FLAG  FROM LIVING_UNITS WHERE AGY_LOC_ID = :agyLocId AND ((ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' ) AND PARENT_LIVING_UNIT_ID IS NULL ORDER BY LIST_SEQ ,LEVEL_1_CODE
}

OIDINTMV_FIND_RGFROMHLOCLEVEL2 {
 	SELECT LEVEL_2_CODE  DESCRIPTION, LIVING_UNIT_ID, ACTIVE_FLAG FROM   LIVING_UNITS WHERE AGY_LOC_ID = :agyLocId AND ((ACTIVE_FLAG='Y' ) OR '' = 'ENTER-QUERY' ) AND PARENT_LIVING_UNIT_ID = :fromLocLevelOne ORDER BY LIST_SEQ ,LEVEL_2_CODE
}

OIDINTMV_FIND_RGFROMHLOCLEVEL3 {
 	SELECT LEVEL_3_CODE DESCRIPTION, LIVING_UNIT_ID, ACTIVE_FLAG FROM   LIVING_UNITS WHERE AGY_LOC_ID = :agyLocId AND ((ACTIVE_FLAG='Y' ) OR '' = 'ENTER-QUERY' ) AND PARENT_LIVING_UNIT_ID = :fromLocLevelTwo ORDER BY LIST_SEQ ,LEVEL_3_CODE
}

OIDINTMV_FIND_RGFROMILOCLEVEL1 {
 	SELECT  internal_location_desc DESCRIPTION, INTERNAL_LOCATION_CODE CODE, INTERNAL_LOCATION_ID, ACTIVE_FLAG  FROM V_INT_LOC_SUMMARIES WHERE AGY_LOC_ID = :agyLocId AND PARENT_INTERNAL_LOCATION_ID IS NULL AND ((ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' ) AND INTERNAL_LOCATION_ID IN (SELECT INTERNAL_LOCATION_ID FROM V_INT_LOC_USAGE_LOCATIONS ,REFERENCE_CODES WHERE AGY_LOC_ID = :agyLocId AND INTERNAL_LOCATION_USAGE = CODE AND PARENT_CODE = 'INT_MOV_USG' AND DOMAIN = 'ILOC_USG' AND ACTIVE_FLAG = 'Y' ) AND INTERNAL_LOCATION_CODE <> 'RTU' ORDER BY LIST_SEQ ,INTERNAL_LOCATION_DESC
}

OIDINTMV_FIND_RGFROMILOCLEVEL2 {
 select
	internal_location_desc  DESCRIPTION ,
	INTERNAL_LOCATION_CODE CODE ,
	INTERNAL_LOCATION_ID,
	ACTIVE_FLAG
from
	V_INT_LOC_SUMMARIES VILS
where
	AGY_LOC_ID = :agyLocId
	and PARENT_INTERNAL_LOCATION_ID = :fromILocLevelOneId
	and ((ACTIVE_FLAG = 'Y' )
		or '' = 'ENTER-QUERY' )
	and exists (
	select
		'1'
	from
		V_INT_LOC_USAGE_LOCATIONS VIU ,
		REFERENCE_CODES
	where
		VIU.INTERNAL_LOCATION_ID = VILS.INTERNAL_LOCATION_ID
		and VIU.INTERNAL_LOCATION_USAGE = CODE
		and PARENT_CODE = 'INT_MOV_USG'
		and domain = 'ILOC_USG'
		and ACTIVE_FLAG = 'Y' )
order by
	LIST_SEQ ,
	INTERNAL_LOCATION_DESC

}

OIDINTMV_FIND_RGFROMILOCLEVEL3 {
 	SELECT internal_location_desc  DESCRIPTION , INTERNAL_LOCATION_CODE CODE , INTERNAL_LOCATION_ID, ACTIVE_FLAG  FROM V_INT_LOC_SUMMARIES VILS  WHERE AGY_LOC_ID = :agyLocId    AND PARENT_INTERNAL_LOCATION_ID = :fromILocLevelTwoId    AND ((ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' )    AND EXISTS       ( SELECT '1'           FROM V_INT_LOC_USAGE_LOCATIONS VIU , REFERENCE_CODES          WHERE VIU.INTERNAL_LOCATION_ID = VILS.INTERNAL_LOCATION_ID            AND VIU.INTERNAL_LOCATION_USAGE = CODE            AND PARENT_CODE = 'INT_MOV_USG'            AND DOMAIN = 'ILOC_USG'            AND ACTIVE_FLAG = 'Y' )  ORDER BY LIST_SEQ ,INTERNAL_LOCATION_DESC
}

OIDINTMV_FIND_RGTOILOCLEVEL1 {
 	select
	USER_DESC DESCRIPTION ,
	INTERNAL_LOCATION_CODE CODE ,
	INTERNAL_LOCATION_ID,
	ACTIVE_FLAG
from
	V_INT_LOC_SUMMARIES
where
	AGY_LOC_ID = :agyLocId
	and coalesce (PARENT_INTERNAL_LOCATION_ID ,0) =0  
	and ((INTERNAL_LOCATION_ID != :fromILocLevelOneId)
		or coalesce (:fromILocLevelOneId , 0) = 0 )
	and ((INTERNAL_LOCATION_CODE != 'RTU'
		and coalesce(:fromHLocLevelOne,0) != 0 )
	or (coalesce (:fromHLocLevelOne,0) = 0 ) )
	and ((ACTIVE_FLAG = 'Y' )
		or '' = 'ENTER-QUERY' )
	and INTERNAL_LOCATION_ID in (
	select
		INTERNAL_LOCATION_ID
	from
		V_INT_LOC_USAGE_LOCATIONS ,
		REFERENCE_CODES
	where
		AGY_LOC_ID = :agyLocId
		and INTERNAL_LOCATION_USAGE = CODE
		and PARENT_CODE = 'INT_MOV_USG'
		and domain = 'ILOC_USG'
		and ACTIVE_FLAG = 'Y' )
order by
	LIST_SEQ ,
	INTERNAL_LOCATION_DESC

}

OIDINTMV_FIND_RGSCHTYPE {
select
	DESCRIPTION ,
	CODE
from
	REFERENCE_CODES
where
	domain = 'INT_SCH_TYPE'
	and ((ACTIVE_FLAG = 'Y'
		and EXPIRED_DATE is null )
 )
order by
	LIST_SEQ ,
	DESCRIPTION

}

OIDINTMV_FIND_RGSCHREASON {
 	SELECT DESCRIPTION ,CODE FROM REFERENCE_CODES WHERE DOMAIN = 'INT_SCH_RSN' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL )   ) ORDER BY LIST_SEQ ,DESCRIPTION

}

OIDINTMV_OFFBLK_INSERT_OIDINTMV {
insert into offender_inter_mvmt_locations (offender_iml_id, offender_book_id, agency_location_id, agency_iml_id, movement_date, movement_time, from_agency_iml_id, movement_type, movement_reason_code, CREATE_DATETIME, MODIFY_DATETIME, event_id, create_user_id ) values ( :offenderImlId, :offenderBookId, :agencyLocationId, :agencyImlId, :movementDate, :movementTime, :fromAgencyImlId, :movementType, :movementReasonCode, current_timestamp, null, :eventId, :createUserId )
}

OIDINTMV_OFFBLK_UPDATE_OIDINTMV.GET_SCHEDULES {
	UPDATE OIDINTMV.GET_SCHEDULES set 
}


OIDINTMV_GET_PROFILE_VALUE_2_ {
	SELECT PROFILE_VALUE_2 FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = P_PROFILE_TYPE AND PROFILE_CODE = P_PROFILE_CODE
}

OIDINTMV_GET_OFFENDER_DETAILS {
	SELECT DESCRIPTION FROM AGENCY_INTERNAL_LOCATIONS WHERE INTERNAL_LOCATION_ID = :agyImlId
}

OIDINTMV_GET_UNSCHEDULES {
select
	OFFENDER_ID,
	OFFENDER_BOOK_ID,
	OFFENDER_ID_DISPLAY,
	LAST_NAME,
	FIRST_NAME,
	LIVING_UNIT_ID,
	LIVING_UNIT_DESC,
	AGENCY_IML_ID,
	AGENCY_IML_DESC,
	:P_AGY_LOC_ID as AGY_LOC_ID ,
	'N' as CONFIRM
from
	V_OFFENDER_BOOKINGS
}

OIDINTMV_GET_SCHEDULES {
select a.* from (
SELECT   EVENT_ID, 
CASE
   WHEN vOffSch.event_type ::text = 'CRT'::text THEN 'Y'
    ELSE 'N'
   END AS courtEventApp,
OFFENDER_ID, OFFENDER_BOOK_ID,vOffSch.AGY_LOC_ID,
                  OFFENDER_ID_DISPLAY, OFFENDER_LAST_NAME,
                  OFFENDER_FIRST_NAME, LIVING_UNIT_ID, LIVING_UNIT_DESC,
                  EVENT_TYPE, EVENT_TYPE_DESC, EVENT_SUB_TYPE,
                  EVENT_SUB_TYPE_DESC, AGENCY_IML_ID, AGENCY_IML_DESC,
                  TO_INT_LOC_USER_DESC,
                  REFERENCE_ID,
                  START_TIME,
                  EVENT_STATUS,
                  record_source,
                  to_agy_loc_id,
                  appearance_location,
 (case when voffsch.event_type='CRT' then agyIntLoc.internal_location_id else TO_INTERNAL_LOCATION_ID end) internal_location_id,
                  voffsch.off_prgref_id,
                  voffsch.comment_text
             FROM V_OFFENDER_ALL_SCHEDULES vOffSch
                 ,AGENCY_INTERNAL_LOCATIONS agyIntLoc 
}

OIDINTMV_LIVING_UNIT_ID_DESC{
SELECT LEVEL_1_CODE, LEVEL_2_CODE, LEVEL_3_CODE   FROM LIVING_UNITS WHERE LIVING_UNIT_ID = :livingUnitId::bigInt
}

OIDINTMV_INTERNAL_LOCATION_DESC{
SELECT INTERNAL_LOCATION_CODE FROM V_INT_LOC_SUMMARIES WHERE INTERNAL_LOCATION_ID = :internalLocationId::bigInt
}

OIDINTMV_OFFBLK_UPDATE_OIDINTMV {
update v_offender_all_schedules_2
            set    event_status = 'COMP'
            where  event_date = TO_DATE(:eventDate::text, 'DD/MM/YYYY'::text) 
            and    offender_book_id = :offenderBookId
            and    reference_id = :referenceId
}

OIDINTMV_OFFBLK_UPDATE_OIDINTMV_ONE {
update v_offender_all_schedules_2
            set    event_status = 'COMP'
            where  event_date = TO_DATE(:eventDate::text, 'DD/MM/YYYY'::text) 
            and    offender_book_id = :offenderBookId
            and    event_id = :eventId
}

OIDINTMV_OFFBLK_GET_EVENTID{
SELECT event_id
         FROM v_offender_all_schedules_2
         where  event_date = :eventDate
           and    offender_book_id = :offenderBookId
           and    reference_id = :referenceId
}

OIDINTMV_OFFBLK_GET_NXT_OFFENDER_IML_ID{
SELECT coalesce (max(OFFENDER_IML_ID),0) + 1  FROM  OFFENDER_INTER_MVMT_LOCATIONS

}

OIDINTMV_GET_OFFENDER_FULL_DETAILS {
SELECT DISTINCT o.offender_id offender_id,
                      ob.offender_book_id offender_book_id,
                      o.last_name last_name, o.first_name first_name,
                      o.middle_name middle_name, ob.agy_loc_id agy_loc_id,
                      ob.living_unit_id living_unit_id,
                      lu.description description,
                      ob.agency_iml_id agency_iml_id
                 FROM offenders o, offender_bookings ob, living_units lu
                WHERE o.offender_id_display = :offenderIdDisplay
                  AND o.offender_id = ob.offender_id
                  AND ob.active_flag = 'Y'
                  AND lu.living_unit_id = ob.living_unit_id
                  AND EXISTS (
                         SELECT 1
                           FROM caseload_agency_locations b
                          WHERE b.caseload_id = :caseLoadId
                            AND b.agy_loc_id = ob.agy_loc_id
                            AND (  :agyLocId IS NULL
                                 OR b.agy_loc_id = :agyLocId
                                ))
}
OIDINTMV_GET_OFFENDER_NSTYPE_DETAILS {
select
	ona.ns_type
from
	offender_bookings bkg,
	offender_na_details ona,
	offenders off
where
	bkg.offender_id = off.offender_id
	and bkg.root_offender_id = ona.ns_offender_id
	and ona.offender_id = (
	select
		distinct root_offender_id
	from
		offenders
	where
		offender_id = :offenderId)
	and ona.ns_offender_id = (
	select
		root_offender_id
	from
		offenders
	where
		offender_id = :offenderIdOne)
	and date_trunc('D',current_timestamp) between ona.ns_effective_date and coalesce (ona.ns_expiry_date,
	to_date('01/01/3000', 'DD/MM/YYYY'))

}
OIDINTMV_GET_PARENT_INTERNAL_LOCATION_DETAILS {
with recursive cte as (
select
	olu.internal_location_id,olu.parent_internal_location_id
from
	agency_internal_locations olu
where
	olu.internal_location_id = :internalLocationId
union all
select
	olu.internal_location_id,olu.parent_internal_location_id
from
	agency_internal_locations olu
join cte c on
	(c.parent_internal_location_id = olu.internal_location_id) )
select
	*
from
	cte
}
OIDINTMV_GET_RESTRICT_INTERNAL_LOCATION_DETAILS {
SELECT internal_location_id, int_loc_profile_code FROM agy_int_loc_profiles WHERE internal_location_id = :internalLocationId AND int_loc_profile_type = 'NON_ASSO_TYP' AND ((int_loc_profile_code != 'STG' AND :pstgIndicator = 'N') OR (int_loc_profile_code = 'STG' AND :pstgIndicator = 'Y'))
}
OIDINTMV_GET_OFFENDER_STG_DETAILS {
SELECT 'Y' FROM offender_stg_affiliations osa, offender_stg_affiliations osa_enemy, stg_relationships sr, offender_bookings ob WHERE osa.offender_book_id = :offenderBookId AND osa.active_flag = 'Y' AND osa.stg_id = sr.stg_id AND sr.relationship_type = 'ENEMY' AND sr.active_flag = 'Y' AND sr.related_stg_id = osa_enemy.stg_id AND osa_enemy.offender_book_id = ob.offender_book_id AND osa_enemy.active_flag = 'Y' AND ob.active_flag = 'Y' AND osa_enemy.offender_book_id = :offenderBookIdOne
}
OIDINTMV_CKECK_CONFLICT_GET_OFF_DETAILS{
SELECT o.offender_id_display offender_id_display,
			    o.last_name           last_name,
			    o.first_name          first_name
           FROM offenders o
          WHERE o.offender_id = :offenderId
}
OIDINTMV_GET_PARENTLOCATIONSERVER {
with recursive cte as (
select
	ail.internal_location_id,ail.parent_internal_location_id
from
	agency_internal_locations ail
where
	internal_location_id = :internalLocationId
union all
select
	ail.internal_location_id,ail.parent_internal_location_id
from
	agency_internal_locations ail
join cte c on
	(c.parent_internal_location_id = ail.internal_location_id) )
select
	internal_location_id
from
	cte c
where
	exists (
	select
		'Y'
	from
		agy_int_loc_profiles ailp
	where
		ailp.internal_location_id = c.internal_location_id
		and ailp.int_loc_profile_type = 'NON_ASSO_TYP'
		and ((ailp.int_loc_profile_code != 'STG'
		and :pStgIndecator = 'N')
		or (ailp.int_loc_profile_code = 'STG'
		and :pStgIndecator = 'Y')));
}
OIDINTMV_GET_PARENT_LOCATIONSTG_LIST{
SELECT internal_location_id, int_loc_profile_code FROM agy_int_loc_profiles WHERE internal_location_id = :internalLocationId AND ((int_loc_profile_code != 'STG' AND :pStgIndecator = 'N') OR (int_loc_profile_code = 'STG' AND :pStgIndecator = 'Y'))
}
OIDINTMV_GET_PROFILECODE_INTLOC_OFFDER_DETAILS{
select
	off.offender_id_display offender_id_display,
	off.last_name last_name,
	off.first_name first_name,
	ona.ns_type
from
	offender_bookings bkg,
	offender_na_details ona,
	offenders off
where
	bkg.offender_id = off.offender_id
	and bkg.root_offender_id = ona.ns_offender_id
	and ona.offender_id = (
	select
		distinct root_offender_id
	from
		offenders
	where
		offender_id = :offenderId)
	and date_trunc('day', localtimestamp) between ona.ns_effective_date and coalesce(ona.ns_expiry_date, to_date('01/01/3000', 'DD/MM/YYYY'))
	and bkg.agency_iml_id in (with recursive cte as (
	select
		distinct(ail.internal_location_id),
		ailp.int_loc_profile_code
	from
		agency_internal_locations ail
	left outer join agy_int_loc_profiles ailp on
		(ail.internal_location_id = ailp.internal_location_id)
	where
		ail.internal_location_id = :internalLocationId
union all
	select
		distinct(ail.internal_location_id),
		ailp.int_loc_profile_code
	from
		agency_internal_locations ail
	left outer join agy_int_loc_profiles ailp on
		(ail.internal_location_id = ailp.internal_location_id
		and coalesce(ailp.int_loc_profile_type, 'NON_ASSO_TYP') = 'NON_ASSO_TYP')
	join cte c on
		(c.internal_location_id = ail.parent_internal_location_id ) )
	select
		internal_location_id
	from
		cte
	where
		ona.ns_type in (:intLocProfileCode, int_loc_profile_code) )
}
OIDINTMV_GET_PROFILECODE_INTLOC_STG_OFFDER_DETAILS {
SELECT off.offender_id_display offender_id_display, off.last_name last_name, off.first_name first_name FROM offender_stg_affiliations osa, offender_stg_affiliations osa_enemy, stg_relationships sr, offender_bookings ob, agency_internal_locations ail, offenders off WHERE osa.offender_book_id = :offenderBookId AND ob.offender_id = off.offender_id AND osa.active_flag = 'Y' AND osa.stg_id = sr.stg_id AND sr.relationship_type = 'ENEMY' AND sr.active_flag = 'Y' AND sr.related_stg_id = osa_enemy.stg_id AND osa_enemy.offender_book_id = ob.offender_book_id AND osa_enemy.active_flag = 'Y' AND ob.active_flag = 'Y' AND ob.agency_iml_id = ail.internal_location_id AND ob.agency_iml_id IN (SELECT DISTINCT (ail.internal_location_id) FROM agency_internal_locations ail, agy_int_loc_profiles ailp WHERE ail.internal_location_id = ailp.internal_location_id(+) AND nvl(ailp.int_loc_profile_type, 'NON_ASSO_TYP') = 'NON_ASSO_TYP' AND 'STG' in (":internalLocationProfileCode, ailp.int_loc_profile_code) START WITH ail.internal_location_id = :internalLocationId CONNECT BY PRIOR ail.internal_location_id = ail.parent_internal_location_id )
}
OIDINTMV_FIND_RGTOILOCLEVEL2ONE {
 	SELECT USER_DESC  DESCRIPTION, INTERNAL_LOCATION_CODE CODE, INTERNAL_LOCATION_ID, ACTIVE_FLAG  FROM V_INT_LOC_SUMMARIES VILS  WHERE AGY_LOC_ID = :agyLocId    AND PARENT_INTERNAL_LOCATION_ID = :toILocLevelOneId::bigInt    AND ((ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' )    AND EXISTS  (SELECT '1'           FROM V_INT_LOC_USAGE_LOCATIONS VIU ,                REFERENCE_CODES          WHERE VIU.INTERNAL_LOCATION_ID = VILS.INTERNAL_LOCATION_ID            AND VIU.INTERNAL_LOCATION_USAGE = CODE            AND PARENT_CODE = 'INT_MOV_USG'            AND DOMAIN = 'ILOC_USG'            AND ACTIVE_FLAG = 'Y' )  ORDER BY LIST_SEQ ,           INTERNAL_LOCATION_DESC
}

OIDINTMV_FIND_RGTOILOCLEVEL3ONE {
 	SELECT USER_DESC  DESCRIPTION, INTERNAL_LOCATION_CODE CODE, INTERNAL_LOCATION_ID, ACTIVE_FLAG   FROM V_INT_LOC_SUMMARIES VILS  WHERE AGY_LOC_ID = :agyLocId    AND PARENT_INTERNAL_LOCATION_ID = :toILocLevelTwoId    AND ((ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' ) AND EXISTS         (SELECT '1'           FROM V_INT_LOC_USAGE_LOCATIONS VIU ,                REFERENCE_CODES          WHERE VIU.INTERNAL_LOCATION_ID = VILS.INTERNAL_LOCATION_ID            AND VIU.INTERNAL_LOCATION_USAGE = CODE            AND PARENT_CODE = 'INT_MOV_USG'            AND DOMAIN = 'ILOC_USG'            AND ACTIVE_FLAG = 'Y' )     ORDER BY LIST_SEQ ,           INTERNAL_LOCATION_DESC
}
OIDINTMV_COURT_LOCATIONS
{
select
	a.description description,a.ili as staffId
from
	(
	select
		ail.INTERNAL_LOCATION_CODE code,
		description description,
		ail.ili
	from
		int_loc_usage_locations ilul,
		(
		select
			internal_location_usage_id iluid
		from
			internal_location_usages
		where
			internal_location_usage = 'CEL'
			and agy_loc_id = :AGY_LOC_ID ) ilu,
		(
		select
			internal_location_id ili,
			INTERNAL_LOCATION_CODE ,
			description
		from
			agency_internal_locations ) ail
	where
		ilul.internal_location_usage_id = ilu.iluid
		and ilul.internal_location_id = ail.ili ) a where a.ili =:locationCode::bigint
}