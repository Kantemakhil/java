
OIDCHLOC_FIND_RGASSIGNMENTREASON {
 	SELECT  CODE, DESCRIPTION    FROM REFERENCE_CODES   WHERE DOMAIN = 'CHG_HOUS_RSN' AND ACTIVE_FLAG ='Y' order by list_seq 
}

OIDCHLOC_BEDAH_FIND_BED_ASSIGNMENT_HISTORIES {
 	SELECT OFFENDER_BOOK_ID ,BED_ASSIGN_SEQ ,LIVING_UNIT_ID ,ASSIGNMENT_DATE ,ASSIGNMENT_TIME ,ASSIGNMENT_REASON ,ASSIGNMENT_END_DATE ,ASSIGNMENT_END_TIME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM BED_ASSIGNMENT_HISTORIES  WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID AND BED_ASSIGN_SEQ = :BEDASSIGNSEQ
}
OIDCHLOC_BEDAH_INSERT_BED_ASSIGNMENT_HISTORIES {
	INSERT INTO BED_ASSIGNMENT_HISTORIES(OFFENDER_BOOK_ID ,BED_ASSIGN_SEQ ,LIVING_UNIT_ID ,ASSIGNMENT_DATE ,
	ASSIGNMENT_TIME ,ASSIGNMENT_REASON ,ASSIGNMENT_END_DATE ,ASSIGNMENT_END_TIME ,CREATE_DATETIME ,
	CREATE_USER_ID ,MODIFY_DATETIME  ,SEAL_FLAG ) VALUES(:offenderBookId ,:bedAssignSeq ,:livingUnitId ,
	:assignmentDate ,:assignmentTime ,:assignmentReason ,:assignmentEndDate ,:assignmentEndTime ,current_timestamp ,
	:createUserId ,null,:sealFlag )
}

OIDCHLOC_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = :PROFILETYPE and PROFILE_CODE = :PROFILECODE
}

OIDCHLOC_OFF_BKG_ONCHECKDELETEMASTER {
	SELECT 1 FROM BED_ASSIGNMENT_HISTORIES B WHERE B.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDCHLOC_BED_AH_PREINSERT {
	SELECT COALESCE(MAX(BED_ASSIGN_SEQ),0) + 1 FROM BED_ASSIGNMENT_HISTORIES WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID

}

OIDCHLOC_CGFKCHK_BED_AH_BED_AH_V_LIV_U {
	SELECT  DESCRIPTION FROM  LIVING_UNITS WHERE  LIVING_UNIT_ID = :LIVINGUNITID AND  AGY_LOC_ID = :AGYLOCID
}
OIDCHLOC_UPDATE_OFFENDER_BOOKINGS {
UPDATE OFFENDER_BOOKINGS SET LIVING_UNIT_ID = :livingUnitId,MODIFY_DATETIME=CURRENT_TIMESTAMP ,MODIFY_USER_ID =:createuserId WHERE OFFENDER_BOOK_ID=:offenderBookId
}
OIDCHLOC_BEDAH_UPDATE_BED_ASSIGNMENT_HISTORIES {
UPDATE BED_ASSIGNMENT_HISTORIES SET ASSIGNMENT_END_DATE = :assignmentEndDate ,ASSIGNMENT_END_TIME = :assignmentEndTime,MODIFY_DATETIME=CURRENT_TIMESTAMP ,MODIFY_USER_ID =:modifyUserId
WHERE OFFENDER_BOOK_ID = :offenderBookId AND BED_ASSIGN_SEQ = :bedAssignSeq
}
GET_LATEST_BED_ASS_REC_CUR {
select
	BAH.ASSIGNMENT_DATE::date ASSIGNMENT_DATE,
	BAH.ASSIGNMENT_TIME ASSIGNMENT_TIME
from
	BED_ASSIGNMENT_HISTORIES BAH
where
	BAH.OFFENDER_BOOK_ID = :OFFENDERBOOKID
	and BAH.BED_ASSIGN_SEQ =
                          (
	select
		MAX (BAH1.BED_ASSIGN_SEQ)
	from
		BED_ASSIGNMENT_HISTORIES BAH1
	where
		BAH1.OFFENDER_BOOK_ID = :OFFENDERBOOKID)
}

OIDCHLOC_GET_OLD_DATA_OFFENDER_BOOKINGS{
SELECT * FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :offenderBookId 
}

OIDCHLOC_GET_IEP_DESCRIPTION{
select iep_level_code  from off_incentives_earn_privs oiep where offender_book_id =:offenderBookId and create_datetime = 
(select max(create_datetime) from off_incentives_earn_privs oiep2 where offender_book_id=:offenderBookId)
}

OIDCHLOC_INSERT_IEP_DATA{
insert into oms_owner.off_incentives_earn_privs (iep_level_seq, offender_book_id, iep_level_code, assigned_date, approved_staff_id, next_review_date, review_comment, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values(nextval('iep_level_seq'), :offenderBookId, :iepLevelCode, :assignedDate, :approvedStaffId, :nextReviewDate, :reviewComment, current_timestamp, :createUserId, :modifyDatetime, :modifyUserId, :sealFlag)
}

OIDCHLOC_FETCH_IEP_LEVEL_CODE{
select iep_level_code from living_unit_iep_levels iep  where internal_location_id =:livingUnitId
}

OIDCHLOC_FETCH_NEXT_REVIEW_DATE{
select current_timestamp::date +( select review_days from incentives_earn_privs ilm where intake_iep = 'Y')* interval '1 day' from dual
}

OIDCHLOC_GET_STAFF_ID{
select staff_id from staff_members sm where user_id ='SYSTEM'
}

OIDCHLOC_GET_LIVING_UNIT_ID{
select living_unit_id from bed_assignment_histories bah where offender_book_id =:offenderBookId order by create_datetime desc limit 1
}
OIDCHLOC_GET_IEP_DESCRIPTION_AFTER{
select
	(
	select
		iep_level_code
	from
		living_unit_iep_levels luil
	where
		internal_location_id = a.living_unit_id) as iep_level_code
from
	(with recursive cte as (
	select
		lu1.living_unit_id,lu1.parent_living_unit_id
	from
		living_units as lu1
	where
		lu1.living_unit_id = (select living_unit_id from bed_assignment_histories bah where offender_book_id =:offenderBookId 
order by create_datetime desc limit 1)
union
	select
		lu2.living_unit_id,lu2.parent_living_unit_id
	from
		living_units lu2
	join cte on
		cte.parent_living_unit_id = lu2.living_unit_id
    )
	select
		*
	from
		cte) as a 
}
OIDCHLOC_GET_IEP_CODE_FOR_AGY_LOC
{
select iep_level_code from agy_loc_iep_levels alil where agy_loc_id =:agyLocId
}
OIDCHLOC_GET_NONASSOCATION_OFFENDER_DETAILS{
 select concat(o.last_name ,',',o.first_name,',',o.offender_id_display) as details
from offenders o where o.offender_id in(
select offender_id  from offender_bookings ob where living_unit_id =:livingUnitId and offender_id in (
SELECT ona.ns_offender_id  FROM OFFENDER_NON_ASSOCIATIONS ona
	where ona.offender_id=:offenderId and ona.NS_OFFENDER_ID in 
	(SELECT  nad.NS_OFFENDER_ID FROM OFFENDER_NA_DETAILS nad 
	where  nad.offender_id=:offenderId and  nad.ns_reason_code in (SELECT OMS_MISCELLANEOUS_GETDESCCODE(INT_LOC_PROFILE_TYPE,  INT_LOC_PROFILE_CODE) DESCRIPTION
           FROM LIVING_UNIT_PROFILES
          WHERE LIVING_UNIT_ID = :livingUnitId
            AND INT_LOC_PROFILE_TYPE = 'NON_ASSO_TYP')  and 
	current_date < coalesce(nad.ns_expiry_date, current_date +1)  and current_date >= nad.ns_effective_date)))
}
OIDCHLOC_GET_OFFENDER_DETAILS{
select concat(last_name,', ',first_name,', ',offender_id_display)  from offenders where offender_id =:offenderId
}
OIDCHLOC_GET_OFFENDER_ID_FROM_OFFENDER_BOOK_ID{
select offender_id  from offender_bookings where offender_book_id =:offenderBookId
}
OIDCHLOC_MOVE_ADMIN_ROLE_FOR_USER
{
select case when
(select count(*) from staff_member_roles where staff_id =(select staff_id  from staff_members where user_id=:userId) and role_code ='MOVE_ADMIN')!=0 
then 'Y' else 'N' end 
}

OIDCHLOC_GET_AGYLOCID_IEPLEVEL{
select iep_level_code  from agy_loc_iep_levels  where agy_loc_id =:agyLocId
}

OIDCHLOC_OFFENDER_NAME {
	select  concat(last_name || ',' || first_name) as offenderName, offender_id_display as offenderIdDisplay from v_header_block where offender_Book_Id =:offenderBookId
}
