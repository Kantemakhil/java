GET_PARENT_LOCATION_CUR{
SELECT ail.internal_location_id FROM agency_internal_locations ail WHERE EXISTS ( SELECT 'Y' FROM agy_int_loc_profiles ailp WHERE ailp.internal_location_id = ail.internal_location_id AND ailp.int_loc_profile_type = 'NON_ASSO_TYP' AND ( ( ailp.int_loc_profile_code != 'STG' AND :flag = 'N' ) OR ( ailp.int_loc_profile_code = 'STG' AND :flag = 'Y' ) ) ) START WITH internal_location_id = :pInternalLocId CONNECT BY PRIOR parent_internal_location_id = internal_location_id
}

GET_NA_TYPE_CUR{
SELECT internal_location_id, int_loc_profile_code FROM agy_int_loc_profiles WHERE internal_location_id = :internalLocationId AND ( ( int_loc_profile_code != 'STG' AND :flag = 'N' ) OR ( int_loc_profile_code = 'STG' AND :flag = 'Y' ) )
}

GET_NON_ASS_CUR{
SELECT 'Y' na_exis, ona.ns_type, off.offender_id_display offender_id_display, off.last_name last_name, off.first_name first_name FROM offender_bookings bkg, offender_na_details ona, offenders off WHERE bkg.offender_id = off.offender_id AND bkg.root_offender_id = ona.ns_offender_id AND ona.offender_id = ( SELECT DISTINCT root_offender_id FROM offenders WHERE offender_id = :pOffenderId ) AND trunc(sysdate) BETWEEN ona.ns_effective_date AND nvl(ona.ns_expiry_date, TO_DATE('01/01/3000', 'DD/MM/YYYY')) AND bkg.agency_iml_id IN ( SELECT DISTINCT ( ail.internal_location_id ) FROM agency_internal_locations ail, agy_int_loc_profiles ailp WHERE ail.internal_location_id = ailp.internal_location_id (+) AND nvl(ailp.int_loc_profile_type, 'NON_ASSO_TYP') = 'NON_ASSO_TYP' AND ona.ns_type IN ( :intLocProfileCode, ailp.int_loc_profile_code ) START WITH ail.internal_location_id = :internalLocationId CONNECT BY PRIOR ail.internal_location_id = ail.parent_internal_location_id )
}
GET_CHECK_ENEMY_CUR{
SELECT off.offender_id_display offender_id_display, off.last_name last_name, off.first_name first_name FROM offender_stg_affiliations osa, offender_stg_affiliations osa_enemy, stg_relationships sr, offender_bookings ob, agency_internal_locations ail, offenders off WHERE osa.offender_book_id = :pOffenderBookId AND ob.offender_id = off.offender_id AND osa.active_flag = 'Y' AND osa.stg_id = sr.stg_id AND sr.relationship_type = 'ENEMY' AND sr.active_flag = 'Y' AND sr.related_stg_id = osa_enemy.stg_id AND osa_enemy.offender_book_id = ob.offender_book_id AND osa_enemy.active_flag = 'Y' AND ob.active_flag = 'Y' AND ob.agency_iml_id = ail.internal_location_id AND ob.agency_iml_id IN ( SELECT DISTINCT ( ail.internal_location_id ) FROM agency_internal_locations ail, agy_int_loc_profiles ailp WHERE ail.internal_location_id = ailp.internal_location_id (+) AND nvl(ailp.int_loc_profile_type, 'NON_ASSO_TYP') = 'NON_ASSO_TYP' AND 'STG' IN ( :nsType, ailp.int_loc_profile_code ) START WITH ail.internal_location_id = :internalLocationId CONNECT BY PRIOR ail.internal_location_id = ail.parent_internal_location_id )
}

CHK_NA_PRG_SRV_CONFLICT_RT_NON_ASS_CUR{  
SELECT OFF.offender_id_display offender_id_display, OFF.last_name last_name, OFF.first_name first_name FROM offender_bookings bkg, offender_na_details ona, offenders OFF WHERE bkg.offender_id = OFF.offender_id AND bkg.root_offender_id = ona.ns_offender_id AND ( (bkg.active_flag = 'Y') OR ( bkg.active_flag = 'N' AND bkg.offender_book_id = (SELECT MAX (ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = bkg.root_offender_id AND NOT EXISTS ( SELECT 'X' FROM offender_bookings ob3 WHERE ob3.root_offender_id = bkg.root_offender_id AND ob3.active_flag = 'Y')) ) ) AND ona.offender_id = (SELECT DISTINCT root_offender_id FROM offenders WHERE offender_id = :p_offender_id) AND TRUNC (SYSDATE) BETWEEN ona.ns_effective_date AND NVL (ona.ns_expiry_date, TO_DATE ('01/01/3000', 'DD/MM/YYYY')) AND EXISTS ( SELECT '1' FROM v_offender_program_profiles opp WHERE opp.crs_acty_id = :p_crs_acty_id AND opp.offender_program_status = 'ALLOC' AND ( :p_offender_start_date IS NULL OR offender_start_date >= TRUNC (:p_offender_start_date) AND NVL (TRUNC (:p_offender_end_date), offender_start_date) >= offender_start_date OR TRUNC (:p_offender_start_date) BETWEEN offender_start_date AND NVL (offender_end_date, TRUNC (:p_offender_start_date) ) ) AND OFF.root_offender_id = (SELECT DISTINCT root_offender_id FROM offenders WHERE offender_id = opp.offender_id)) UNION SELECT OFF.offender_id_display offender_id_display, OFF.last_name last_name, OFF.first_name first_name FROM offender_bookings bkg, offender_na_details ona, offenders OFF WHERE bkg.offender_id = OFF.offender_id AND bkg.root_offender_id = ona.ns_offender_id AND NVL (bkg.community_active_flag, 'N') = 'Y' AND ona.offender_id = (SELECT DISTINCT root_offender_id FROM offenders WHERE offender_id = :p_offender_id) AND TRUNC (SYSDATE) BETWEEN ona.ns_effective_date AND NVL (ona.ns_expiry_date, TO_DATE ('01/01/3000', 'DD/MM/YYYY')) AND EXISTS ( SELECT '1' FROM v_offender_program_profiles opp WHERE opp.crs_acty_id = :p_crs_acty_id AND ( :p_offender_start_date IS NULL OR offender_start_date >= TRUNC (:p_offender_start_date) AND NVL (TRUNC (:p_offender_end_date), offender_start_date) >= offender_start_date OR TRUNC (:p_offender_start_date) BETWEEN offender_start_date AND NVL (offender_end_date, TRUNC (:p_offender_start_date) ) ) AND OFF.root_offender_id = (SELECT DISTINCT root_offender_id FROM offenders WHERE offender_id = opp.offender_id)) UNION SELECT OFF.offender_id_display offender_id_display, OFF.last_name last_name, OFF.first_name first_name FROM offender_bookings bkg, offender_na_details ona, offenders OFF WHERE bkg.offender_id = OFF.offender_id AND bkg.root_offender_id = ona.ns_offender_id AND ( (bkg.active_flag = 'Y') OR ( bkg.active_flag = 'N' AND bkg.offender_book_id = (SELECT MAX (ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = bkg.root_offender_id AND NOT EXISTS ( SELECT 'X' FROM offender_bookings ob3 WHERE ob3.root_offender_id = bkg.root_offender_id AND ob3.active_flag = 'Y')) ) ) AND ona.offender_id = (SELECT DISTINCT root_offender_id FROM offenders WHERE offender_id = :p_offender_id) AND TRUNC (SYSDATE) BETWEEN ona.ns_effective_date AND NVL (ona.ns_expiry_date, TO_DATE ('01/01/3000', 'DD/MM/YYYY')) AND EXISTS (SELECT '1' FROM programs_non_assoc_tmp opp WHERE OFF.root_offender_id = (SELECT DISTINCT root_offender_id FROM offenders WHERE offender_id = opp.offender_id)) 
}         

CHK_NA_PRG_SRV_CONFLICT_RT_GET_STG_NA_PRG_SRV{
SELECT OFF.offender_id_display offender_id_display, OFF.last_name last_name, OFF.first_name first_name FROM offender_stg_affiliations osa, offender_stg_affiliations osa_enemy, stg_relationships sr, offender_bookings ob, offenders OFF WHERE osa.offender_book_id = :p_offender_book_id AND ob.offender_id = OFF.offender_id AND osa.active_flag = 'Y' AND osa.stg_id = sr.stg_id AND sr.relationship_type = 'ENEMY' AND sr.active_flag = 'Y' AND sr.related_stg_id = osa_enemy.stg_id AND osa_enemy.offender_book_id = ob.offender_book_id AND osa_enemy.active_flag = 'Y' AND ob.active_flag = 'Y' AND EXISTS ( SELECT '1' FROM v_offender_program_profiles opp WHERE opp.crs_acty_id = :p_crs_acty_id AND opp.offender_program_status = 'ALLOC' AND opp.offender_book_id = ob.offender_book_id) 
} 


GET_OFF_BKG_CNT_CUR{
SELECT no_of_occupant FROM agency_internal_locations WHERE internal_location_id = :p_living_unit_id
}

GET_REV_BED_CNT_CUR{
SELECT COUNT(*) FROM reserve_bed_locations rbl WHERE rbl.living_unit_id = :p_living_unit_id AND rbl.reserve_until_date >= CURRENT_TIMESTAMP AND rbl.remove_reason IS NULL     
}

GET_LAST_SEQ{
-- SELECT NVL (MAX (LINE), 0) + 1  FROM PROGRAMS_NON_ASSOC_TMP
 SELECT coalesce (MAX (LINE), 0) + 1  FROM PROGRAMS_NON_ASSOC_TMP
 }
 
INSERT_PROGRAMS_NON_ASSOC_TMP{
--INSERT INTO PROGRAMS_NON_ASSOC_TMP (LINE, OFFENDER_ID, OFFENDER_BOOK_ID, PROGRAM_ID, CRS_ACTY_ID ) VALUES (:LV_SEQ + 1, :P_OFFENDER_ID, :P_OFFENDER_BOOK_ID, :P_PRG_ID, :P_CRS_ACTY_ID )
insert into PROGRAMS_NON_ASSOC_TMP (LINE, OFFENDER_ID, OFFENDER_BOOK_ID, PROGRAM_ID, CRS_ACTY_ID ) values (:LV_SEQ + 1, :P_OFFENDER_ID, :P_OFFENDER_BOOK_ID, :P_PRG_ID, :P_CRS_ACTY_ID)
}                  

NA_CUR_SELECT_OPERATION{
--SELECT bkg.living_unit_id living_unit_id, bkg.offender_book_id offender_book_id, off.offender_id_display offender_id_display, off.last_name last_name,
--off.first_name first_name, ona.ns_type ns_type FROM offender_bookings bkg, offender_na_details ona, offenders off WHERE bkg.offender_id = off.offender_id 
--AND bkg.root_offender_id = ona.ns_offender_id AND ona.offender_id = ( SELECT root_offender_id FROM offenders WHERE offender_id = :offenderId )
--AND trunc(sysdate) BETWEEN ona.ns_effective_date AND nvl(ona.ns_expiry_date, TO_DATE('01/01/3000', 'DD/MM/YYYY'))
SELECT bkg.living_unit_id living_unit_id, bkg.offender_book_id offender_book_id, off.offender_id_display offender_id_display, off.last_name last_name,
off.first_name first_name, ona.ns_type ns_type FROM offender_bookings bkg, offender_na_details ona, offenders off WHERE bkg.offender_id = off.offender_id 
AND bkg.root_offender_id = ona.ns_offender_id AND ona.offender_id = ( SELECT root_offender_id FROM offenders WHERE offender_id = :offenderId )
AND current_timestamp BETWEEN ona.ns_effective_date AND coalesce (ona.ns_expiry_date, TO_DATE('01/01/3000', 'DD/MM/YYYY'))
}

CHK_NA_LIV_UNIT_CONFLICT{
--SELECT COUNT(*) FROM agency_internal_locations ail WHERE EXISTS ( SELECT 'Y' FROM agy_int_loc_profiles WHERE internal_location_id = ail.internal_location_id AND int_loc_profile_type = 'NON_ASSO_TYP' AND int_loc_profile_code = :nameType ) AND ail.internal_location_id IN ( SELECT olu.internal_location_id FROM agency_internal_locations olu START WITH olu.internal_location_id = :offenderId CONNECT BY PRIOR olu.parent_internal_location_id = olu.internal_location_id ) START WITH internal_location_id = :livingUnitId CONNECT BY PRIOR parent_internal_location_id = internal_location_id
with recursive cte as (
select
	parent_internal_location_id,
	internal_location_id
from
	agency_internal_locations ail
where
	internal_location_id = :livingUnitId
union all
select
	ail.parent_internal_location_id,
	ail.internal_location_id
from
	agency_internal_locations ail
join cte c on
	(c.parent_internal_location_id = ail.internal_location_id) )
select
	count(*)

from
	cte
where
	exists (
	select
		'Y'
	from
		agy_int_loc_profiles
	where
		internal_location_id = cte.internal_location_id
		and int_loc_profile_type = 'NON_ASSO_TYP'
		and int_loc_profile_code = :nameType )
	and cte.internal_location_id in (with recursive cte as (
	select
		olu.internal_location_id,olu.parent_internal_location_id
	from
		agency_internal_locations olu
	where
		olu.internal_location_id = :offenderId
union all
	select
		olu.internal_location_id,olu.parent_internal_location_id
	from
		agency_internal_locations olu
	join cte c on
		(c.parent_internal_location_id = olu.internal_location_id ) )
	select
		internal_location_id
	from
		cte )
}

OFFENDER_CUR{ 
SELECT bkg.offender_book_id offender_book_id FROM offender_bookings bkg, offenders off WHERE off.offender_id = :offId AND bkg.offender_id = off.offender_id AND ( ( bkg.active_flag = 'Y' ) OR ( bkg.active_flag = 'N' AND bkg.offender_book_id = ( SELECT MAX(ob.offender_book_id) FROM offender_bookings ob WHERE ob.root_offender_id = bkg.root_offender_id AND NOT EXISTS ( SELECT 'X' FROM offender_bookings ob3 WHERE ob3.root_offender_id = bkg.root_offender_id AND ob3.active_flag = 'Y' ) ) ) )
}
PARENT_LOCATION_CUR{
--SELECT olu.internal_location_id FROM agency_internal_locations olu START WITH olu.internal_location_id = :livingUnitId CONNECT BY PRIOR olu.parent_internal_location_id = olu.internal_location_id
with recursive cte as (
select
	olu.internal_location_id,
	parent_internal_location_id
from
	agency_internal_locations olu
where
	olu.internal_location_id = :livingUnitId
union all
select
	olu.internal_location_id,
	olu.parent_internal_location_id
from
	agency_internal_locations olu
join cte c on
	(c.parent_internal_location_id = olu.internal_location_id) )
select
	internal_location_id
from
	cte 
}

RESTRICTIONS_CUR{
SELECT internal_location_id FROM agy_int_loc_profiles WHERE internal_location_id = :internalLocationId AND int_loc_profile_type = 'NON_ASSO_TYP' AND int_loc_profile_code = 'STG'; 
}

CHECK_ENEMY_CUR{
--SELECT off.offender_id_display offender_id_display, off.last_name last_name, off.first_name first_name FROM offender_stg_affiliations osa, 
--offender_stg_affiliations osa_enemy, stg_relationships sr, offender_bookings ob, offenders off, living_units lu WHERE 
--osa.offender_book_id = :offBookId AND ob.offender_id = off.offender_id AND osa.active_flag = 'Y' AND osa.stg_id = sr.stg_id AND 
--sr.relationship_type = 'ENEMY' AND sr.active_flag = 'Y' AND sr.related_stg_id = osa_enemy.stg_id AND osa_enemy.offender_book_id =
--ob.offender_book_id AND osa_enemy.active_flag = 'Y' AND ob.active_flag = 'Y' AND ob.living_unit_id = lu.living_unit_id AND ob.living_unit_id IN 
--(SELECT living_unit_id FROM living_units lu CONNECT BY PRIOR lu.living_unit_id = lu.parent_living_unit_id START WITH lu.living_unit_id =
--:parentLocationId
select
	off.offender_id_display offender_id_display,
	off.last_name last_name,
	off.first_name first_name
from
	offender_stg_affiliations osa,
	offender_stg_affiliations osa_enemy,
	stg_relationships sr,
	offender_bookings ob,
	offenders off,
	living_units lu
where
	osa.offender_book_id = :offBookId
	and ob.offender_id = off.offender_id
	and osa.active_flag = 'Y'
	and osa.stg_id = sr.stg_id
	and sr.relationship_type = 'ENEMY'
	and sr.active_flag = 'Y'
	and sr.related_stg_id = osa_enemy.stg_id
	and osa_enemy.offender_book_id = ob.offender_book_id
	and osa_enemy.active_flag = 'Y'
	and ob.active_flag = 'Y'
	and ob.living_unit_id = lu.living_unit_id
	and ob.living_unit_id in (with recursive cte as (
	select
		living_unit_id
	from
		living_units lu
	where
		lu.living_unit_id = :parentLocationId
union all
	select
		lu.living_unit_id
	from
		living_units lu
	join cte c on
		(c.living_unit_id = lu.parent_living_unit_id ) )
	select
		*
	from
		cte)
}

CLEAR_TEMP_LIST{
 DELETE FROM PROGRAMS_NON_ASSOC_TMP
 }
 
PROGRAMS_NON_ASSOC_TMP{ 
--DELETE FROM programs_non_assoc_tmp WHERE offender_id = :p_offender_id AND offender_book_id = :p_offender_book_id AND NVL (program_id, 0) = NVL (:p_prg_id, 0) AND NVL (crs_acty_id, 0) = NVL (:p_crs_acty_id, 0)
DELETE FROM programs_non_assoc_tmp WHERE offender_id = :p_offender_id AND offender_book_id = :p_offender_book_id AND coalesce (program_id, 0) = coalesce (:p_prg_id, 0) AND coalesce (crs_acty_id, 0) = coalesce (:p_crs_acty_id, 0)
}

L_OFFENDER_CUR{
  select bkg.offender_book_id offender_book_id from offender_bookings bkg, offenders off where off.offender_id = :p_offender_id and bkg.offender_id = off.offender_id and ((bkg.active_flag = 'Y') or (bkg.active_flag = 'N' and bkg.offender_book_id = ( select MAX(ob.offender_book_id) from offender_bookings ob where ob.root_offender_id = bkg.root_offender_id and not exists ( select 'X' from offender_bookings ob3 where ob3.root_offender_id = bkg.root_offender_id and ob3.active_flag = 'Y'))))
 }
 
 L_PARENT_LOCATION_CUR{
 --SELECT olu.internal_location_id FROM agency_internal_locations olu START WITH olu.internal_location_id = :p_living_unit_id CONNECT BY PRIOR olu.parent_internal_location_id = olu.internal_location_id
 with recursive cte as (
select
	olu.internal_location_id,
	parent_internal_location_id
from
	agency_internal_locations olu
where
	olu.internal_location_id = :p_living_unit_id
union all
select
	olu.internal_location_id,
	olu.parent_internal_location_id
from
	agency_internal_locations olu
join cte c on
	(c.parent_internal_location_id = olu.internal_location_id) )
select
	internal_location_id
from
	cte
 }
 
 L_RESTRICTIONS_CUR{
 SELECT internal_location_id FROM agy_int_loc_profiles WHERE internal_location_id = :p_internal_location_id AND int_loc_profile_type = 'NON_ASSO_TYP' AND int_loc_profile_code = 'STG'
 }
 
 L_CHECK_ENEMY_CUR{
-- SELECT 'Y' FROM living_units lu WHERE
with recursive cte as (
select
	'Y',
	living_unit_id
from
	living_units lu
where
	living_unit_id = :p_parent_living_unit_id
union all
select
	'Y',
	lu.living_unit_id
from
	living_units lu
join cte c on
	(c.living_unit_id = lu.parent_living_unit_id) )
select
	'Y'
from
	(
	select
		'Y',
		living_unit_id
	from
		cte
	where
		exists (
		select
			1
		from
			offender_stg_affiliations osa,
			offender_stg_affiliations osa_enemy,
			stg_relationships sr,
			offender_bookings ob
		where
			osa.offender_book_id = :p_offender_book_id
			and osa.active_flag = 'Y'
			and osa.stg_id = sr.stg_id
			and sr.relationship_type = 'ENEMY'
			and sr.active_flag = 'Y'
			and sr.related_stg_id = osa_enemy.stg_id
			and osa_enemy.offender_book_id = ob.offender_book_id
			and osa_enemy.active_flag = 'Y'
			and ob.active_flag = 'Y'
			and ob.living_unit_id = cte.living_unit_id ))A
}

 GET_REV_SUP_LEVEL_CUR{
 SELECT off_assmnt.review_sup_level_type
           FROM offender_assessments off_assmnt, assessments assmnt
          WHERE off_assmnt.offender_book_id = :p_off_book_id
            AND off_assmnt.evaluation_result_code = 'APP'
            AND off_assmnt.assessment_type_id = assmnt.assessment_id
            AND assmnt.caseload_type = 'INST'
            AND assmnt.determine_sup_level_flag = 'Y'
     ORDER BY off_assmnt.assessment_seq DESC
 }
 
 CHECK_LIV_UNIT_SECURITY_CUR{
 select COUNT(*) from living_unit_profiles where living_unit_id = :p_living_unit_id and int_loc_profile_type = 'SUP_LVL_TYPE' and int_loc_profile_code = :lv_sup_level
 }
 
 
 CHECK_NA_CUR{
 SELECT 'Y' FROM OFFENDER_NON_ASSOCIATIONS ONA, OFFENDER_NA_DETAILS OND WHERE ( (ONA.OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND ONA.NS_OFFENDER_BOOK_ID = :P_VIS_OFFENDER_BOOK_ID) OR (ONA.OFFENDER_BOOK_ID = :P_VIS_OFFENDER_BOOK_ID AND ONA.NS_OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID) ) AND OND.OFFENDER_BOOK_ID = ONA.OFFENDER_BOOK_ID AND OND.NS_OFFENDER_BOOK_ID = ONA.NS_OFFENDER_BOOK_ID AND SYSDATE() >= OND.NS_EFFECTIVE_DATE AND (OND.NS_EXPIRY_DATE IS NULL OR SYSDATE() <= OND.NS_EFFECTIVE_DATE)
 }
 
 CHECK_STG_CUR{
 SELECT 'Y' FROM OFFENDER_STG_AFFILIATIONS OSA, OFFENDER_STG_AFFILIATIONS OSA_ENEMY, STG_RELATIONSHIPS SR WHERE OSA.OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND OSA.ACTIVE_FLAG = 'Y' AND OSA.STG_ID = SR.STG_ID AND SR.RELATIONSHIP_TYPE = 'ENEMY' AND SR.ACTIVE_FLAG = 'Y' AND SR.RELATED_STG_ID = OSA_ENEMY.STG_ID AND OSA_ENEMY.OFFENDER_BOOK_ID = :P_VIS_OFFENDER_BOOK_ID AND OSA_ENEMY.ACTIVE_FLAG = 'Y'
 }
 
NONASSOCIATIONS_GET_NONASSOCIATION_OFFENDERS{
	SELECT *  FROM OFFENDER_NON_ASSOCIATIONS ona
	where ona.offender_book_id=:offenderBookId and ona.NS_OFFENDER_ID in 
	(SELECT  nad.NS_OFFENDER_ID FROM OFFENDER_NA_DETAILS nad 
	where  nad.offender_book_id=:offenderBookId and 
	current_date < coalesce(nad.ns_expiry_date, current_date +1)  and current_date >= nad.ns_effective_date)
}

NONASSOCIATIONS_GET_NONASSOCIATION_CONFIG_FOR_LOCATION
{
SELECT
internal_location_id,
int_loc_profile_type,
int_loc_profile_code
FROM
agy_int_loc_profiles
WHERE
internal_location_id = :internalLocationId
AND int_loc_profile_type = 'NON_ASSO_TYP'
}

NONASSOCIATIONS_GET_NONASSOCIATION_CONFIG_FOR_LOCATION_CODE
{
SELECT
internal_location_id,
int_loc_profile_type,
int_loc_profile_code
FROM
agy_int_loc_profiles
WHERE
internal_location_id in (select
		internal_location_id
	from
		agency_internal_locations
	where
		internal_location_code =:locationCode)
AND int_loc_profile_type = 'NON_ASSO_TYP'
}

NONASSOCIATIONS_SCH_NONASSOCIATION_OFFENDERS{
SELECT
	ona.*
FROM
	OFFENDER_NON_ASSOCIATIONS ona
WHERE
	ona.offender_book_id =:offenderBookId
	AND
	ona.NS_OFFENDER_ID IN 
	(
	SELECT
		nad.NS_OFFENDER_ID
	FROM
		OFFENDER_NA_DETAILS nad
	WHERE
		nad.offender_book_id =:offenderBookId
		AND 
	current_date < COALESCE(nad.ns_expiry_date,
		current_date + 1)
			AND current_date >= nad.ns_effective_date
			AND nad.ns_type in(:profileCodeList))
 }
 
NONASSOCIATIONS_OFFENDERS_OF_NONASSOCIATION_GROUPS{
	select distinct offender_book_id from OFFENDER_STG_AFFILIATIONS osa 
	where osa.active_flag = 'Y' 
	and osa.offender_book_id <> :offenderBookId
	and osa.stg_id in (
	select sr.related_stg_id from STG_RELATIONSHIPS sr 
	where active_flag = 'Y' 
	and sr.stg_id in (
	select osa1.stg_id 
	from OFFENDER_STG_AFFILIATIONS osa1
	where osa1.offender_book_id = :offenderBookId
	and active_flag = 'Y'))
 }
 NONASSOCIATIONS_DETAILS_WHILE_INTAKE_FOR_OFFENDER
 {
 select details from ( select concat(o.last_name , ', ', o.first_name, ', ', o.offender_id_display) as details from offenders o where o.offender_id in( select offender_id from offender_bookings ob where offender_id in ( select ona.ns_offender_id from OFFENDER_NON_ASSOCIATIONS ona where ona.offender_id =:offenderBookId and ona.NS_OFFENDER_ID in ( select nad.NS_OFFENDER_ID from OFFENDER_NA_DETAILS nad where nad.offender_id =:offenderBookId and nad.ns_type in ( select code from reference_codes rc where "domain" = 'NON_ASSO_TYP' and rc.description in( select OMS_MISCELLANEOUS_GETDESCCODE(INT_LOC_PROFILE_TYPE, INT_LOC_PROFILE_CODE) DESCRIPTION from LIVING_UNIT_PROFILES where LIVING_UNIT_ID = :livingUnitId and INT_LOC_PROFILE_TYPE = 'NON_ASSO_TYP')) and current_date < coalesce(nad.ns_expiry_date, current_date + 1) and current_date >= nad.ns_effective_date) ) and ob.living_unit_id =:livingUnitId) union select concat(o.last_name , ', ', o.first_name, ', ', o.offender_id_display) as details from offenders o where o.offender_id in( select offender_id from offender_bookings ob where (living_unit_id in (with recursive cte as ( select lu1.living_unit_id, lu1.parent_living_unit_id from living_units as lu1 where lu1.living_unit_id = (case when ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) is not null then ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) else :livingUnitId end) union select lu2.living_unit_id, lu2.parent_living_unit_id from living_units lu2 join cte on cte.living_unit_id = lu2.parent_living_unit_id ) select cte.living_unit_id from cte) and living_unit_id != :livingUnitId and offender_id in ( select ona.ns_offender_id from OFFENDER_NON_ASSOCIATIONS ona where ona.offender_id =:offenderBookId and ona.NS_OFFENDER_ID in ( select nad.NS_OFFENDER_ID from OFFENDER_NA_DETAILS nad where nad.offender_id =:offenderBookId and nad.ns_type in ( select code from reference_codes rc where "domain" = 'NON_ASSO_TYP' and rc.description in( select OMS_MISCELLANEOUS_GETDESCCODE(INT_LOC_PROFILE_TYPE, INT_LOC_PROFILE_CODE) DESCRIPTION from LIVING_UNIT_PROFILES where LIVING_UNIT_ID = :livingUnitId and INT_LOC_PROFILE_TYPE = 'NON_ASSO_TYP')) and current_date < coalesce(nad.ns_expiry_date, current_date + 1) and current_date >= nad.ns_effective_date) )))) A
 }
 NONASSOCIATIONS_DETAILS_HOUSEING_LOCATION_EXCHANGE_FOR_OFFENDER
 {
	select details from ( select concat(o.last_name , ', ', o.first_name, ', ', o.offender_id_display) as details from offenders o where o.offender_id != :exhangeOffenderId and o.offender_id in( select offender_id from offender_bookings ob where (living_unit_id = :livingUnitId and offender_id in ( select ona.ns_offender_id from OFFENDER_NON_ASSOCIATIONS ona where ona.offender_id =:offenderBookId and ona.NS_OFFENDER_ID in ( select nad.NS_OFFENDER_ID from OFFENDER_NA_DETAILS nad where nad.offender_id =:offenderBookId and nad.ns_type in ( select int_loc_profile_code from agy_int_loc_profiles where internal_location_id = :livingUnitId and int_loc_profile_type = 'NON_ASSO_TYP') and current_date < coalesce(nad.ns_expiry_date, current_date + 1) and current_date >= nad.ns_effective_date) ))) union select concat(o.last_name , ', ', o.first_name, ', ', o.offender_id_display) as details from offenders o where o.offender_id != :exhangeOffenderId and o.offender_id in( select offender_id from offender_bookings ob where (living_unit_id in (with recursive cte as ( select lu1.living_unit_id, lu1.parent_living_unit_id from living_units as lu1 where lu1.living_unit_id = (case when ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) is not null then ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) else :livingUnitId end) union select lu2.living_unit_id, lu2.parent_living_unit_id from living_units lu2 join cte on cte.living_unit_id = lu2.parent_living_unit_id ) select cte.living_unit_id from cte) and living_unit_id != :livingUnitId and offender_id in ( select ona.ns_offender_id from OFFENDER_NON_ASSOCIATIONS ona where ona.offender_id =:offenderBookId and ona.NS_OFFENDER_ID in ( select nad.NS_OFFENDER_ID from OFFENDER_NA_DETAILS nad where nad.offender_id =:offenderBookId and nad.ns_type in ( select int_loc_profile_code from agy_int_loc_profiles where internal_location_id =( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) and int_loc_profile_type = 'NON_ASSO_TYP') and current_date < coalesce(nad.ns_expiry_date, current_date + 1) and current_date >= nad.ns_effective_date) )))) A
 }
 
 
 CHECK_AGY_NON_ASSOCIATION_NA_CUR{
 SELECT bkg.living_unit_id living_unit_id, bkg.offender_book_id offender_book_id, OFF.offender_id_display offender_id_display, OFF.last_name last_name, OFF.first_name first_name, ona.ns_type ns_type FROM offender_bookings bkg, offender_na_details ona, offenders OFF WHERE bkg.offender_id = OFF.offender_id AND bkg.root_offender_id = ona.ns_offender_id AND ona.offender_id = (SELECT root_offender_id FROM offenders WHERE offender_id = :p_offender_id) AND date_TRUNC('day', current_date) BETWEEN ona.ns_effective_date AND coalesce (ona.ns_expiry_date, TO_DATE ('01/01/3000', 'DD/MM/YYYY'));
 }
 
 CHK_AGY_CONFLICT{
 SELECT agy_loc_id  FROM offender_bookings   WHERE offender_book_id = :p_na_offender_book_id
 }
 
NON_ASSOCIATION_L_OFFENDER_CUR_OFF_NA_DET_CUR_STG_NON_ASSO_CUR{
	select
	bkg.offender_book_id offender_book_id,
	o.root_offender_id root_offender_id
from
	offender_bookings bkg,
	offenders o
where
	o.offender_id = :p_offender_id
	and bkg.offender_id = o.offender_id
	and ( (bkg.active_flag = 'Y')
		or ( bkg.active_flag = 'N'
			and bkg.offender_book_id =
                            (
			select
				MAX (ob.offender_book_id)
			from
				offender_bookings ob
			where
				ob.root_offender_id = bkg.root_offender_id
				and not exists (
				select
					'X'
				from
					offender_bookings ob3
				where
					ob3.root_offender_id = bkg.root_offender_id
					and ob3.active_flag = 'Y'))
                    )
                )
}
OFF_NA_DET_CUR{
	select
		count(*)
	from
		offender_na_details
	where
		offender_id = :root_offender_id 
		and (ns_expiry_date is null
			or date_trunc('day',
			ns_expiry_date) >= date_trunc('day',
			current_timestamp::date))
}
STG_NON_ASSO_CUR{
	select
	count(*)
from
	stg_relationships
where
	relationship_type = 'ENEMY'
	and stg_id in (
	select
		STG_ID
	from
		OFFENDER_STG_AFFILIATIONS
	where
		OFFENDER_BOOK_ID = :p_offender_book_id
		and ACTIVE_FLAG = 'Y')
	and active_flag = 'Y'
}

NONASSOCIATIONS_GET_GANG_CONFLIT {
	select concat(o.last_name , ', ', o.first_name, ', ', o.offender_id_display) as details from offenders o where o.offender_id in( select offender_id from offender_bookings ob where (living_unit_id in (with recursive cte as ( select lu1.living_unit_id, lu1.parent_living_unit_id from living_units as lu1 where lu1.living_unit_id = (case when ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) is not null then ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) else :livingUnitId end) union select lu2.living_unit_id, lu2.parent_living_unit_id from living_units lu2 join cte on cte.living_unit_id = lu2.parent_living_unit_id ) select cte.living_unit_id from cte) and offender_id in ( select offender_id from offender_bookings ob where offender_book_id in( select distinct offender_book_id from OFFENDER_STG_AFFILIATIONS osa where osa.active_flag = 'Y' and osa.offender_book_id <> :offenderBookId and osa.stg_id in ( select sr.related_stg_id from STG_RELATIONSHIPS sr where active_flag = 'Y' and sr.stg_id in ( select osa1.stg_id from OFFENDER_STG_AFFILIATIONS osa1 where osa1.offender_book_id = :offenderBookId and active_flag = 'Y')) ))))
}

NONASSOCIATIONS_GET_GANG_CONFLIT_DATA_FOR_EXCHANGE {
	select concat(o.last_name , ', ', o.first_name, ', ', o.offender_id_display) as details from offenders o where o.offender_id in( select offender_id from offender_bookings ob where (living_unit_id in (with recursive cte as ( select lu1.living_unit_id, lu1.parent_living_unit_id from living_units as lu1 where lu1.living_unit_id = (case when ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) is not null then ( select parent_living_unit_id from living_units lu where living_unit_id =:livingUnitId) else :livingUnitId end) union select lu2.living_unit_id, lu2.parent_living_unit_id from living_units lu2 join cte on cte.living_unit_id = lu2.parent_living_unit_id ) select cte.living_unit_id from cte) and offender_id in ( select offender_id from offender_bookings ob where offender_book_id in( select distinct offender_book_id from OFFENDER_STG_AFFILIATIONS osa where osa.active_flag = 'Y' and osa.offender_book_id <> :offenderBookId and osa.stg_id in ( select sr.related_stg_id from STG_RELATIONSHIPS sr where active_flag = 'Y' and sr.stg_id in ( select osa1.stg_id from OFFENDER_STG_AFFILIATIONS osa1 where osa1.offender_book_id = :offenderBookId and active_flag = 'Y')) )))) and o.offender_id != :exhangeOffenderId
}