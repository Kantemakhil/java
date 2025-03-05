OCUSTFAS_FIND_RGSTAFFNAME {
select distinct VSM2.LAST_NAME LAST_NAME , VSM2.FIRST_NAME FIRST_NAME , VSM2.LAST_NAME || ', ' || VSM2.FIRST_NAME OFFICER , VSM2.SAC_STAFF_ID SAC_STAFF_ID , VSM2.CAL_AGY_LOC_ID CAL_AGY_LOC_ID , VSM2.FROM_DATE FROM_DATE , VSM2.ROLE as role , VSM2.position as position , VSM2.USER_ID USER_ID from STAFF_MEMBERS_V2 VSM2 where VSM2.CAL_AGY_LOC_ID = :AGYLOCID and VSM2.SUSPENDED_FLAG = 'N' and (VSM2.DATE_TO is null or '' <> 'NORMAL' ) order by VSM2.LAST_NAME , VSM2.FIRST_NAME
}

OCUSTFAS_CASEPLANS_FIND_CASE_PLANS {
 	SELECT OFFENDER_BOOK_ID ,CASE_PLAN_ID ,FROM_DATE ,POSITION ,ROLE ,SAC_STAFF_ID ,CAL_AGY_LOC_ID ,AGY_LOC_ID ,CASE_PLAN_STATUS ,CREATION_DATE ,CREATION_USER ,END_DATE ,SUPERVISION_LEVEL ,CHANGES ,NEXT_REVIEW_DATE ,START_DATE ,CASELOAD_TYPE ,VERIFIED_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,INST_SAC_STAFF_ID ,INST_CAL_AGY_LOC_ID ,INST_FROM_DATE ,INST_POSITION ,INST_ROLE ,AUTO_ASSESS_MODIFY_DATETIME ,AUTO_CONDITION_MODIFY_DATETIME ,SEAL_FLAG   FROM CASE_PLANS WHERE OFFENDER_BOOK_ID  = :offenderBookId ORDER BY START_DATE DESC 
}
OCUSTFAS_CASEPLANS_INSERT_CASE_PLANS {
insert
	into
	CASE_PLANS(OFFENDER_BOOK_ID ,
	CASE_PLAN_ID ,
	FROM_DATE ,
	position ,
	role ,
	SAC_STAFF_ID ,
	CAL_AGY_LOC_ID ,
	AGY_LOC_ID ,
	CASE_PLAN_STATUS ,
	CREATION_DATE ,
	CREATION_USER ,
	END_DATE ,
	SUPERVISION_LEVEL ,
	CHANGES ,
	NEXT_REVIEW_DATE ,
	START_DATE ,
	CASELOAD_TYPE ,
	VERIFIED_FLAG ,
	CREATE_DATETIME ,
	CREATE_USER_ID ,
	MODIFY_DATETIME ,
	INST_SAC_STAFF_ID ,
	INST_CAL_AGY_LOC_ID ,
	INST_FROM_DATE ,
	INST_POSITION ,
	INST_ROLE ,
	AUTO_ASSESS_MODIFY_DATETIME ,
	AUTO_CONDITION_MODIFY_DATETIME ,
	SEAL_FLAG )
values(:offenderBookId ,
:casePlanId ,
(select from_date  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
(select position from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)),
(select role from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
(select sac_staff_id  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
(select cal_agy_loc_id  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
:agyLocId ,
:casePlanStatus ,
:creationDate ,
:creationUser ,
:endDate ,
:supervisionLevel ,
:changes ,
:nextReviewDate ,
:startDate ,
:caseloadType ,
:verifiedFlag ,
current_timestamp ,
:createUserId ,
null ,
:instSacStaffId ,
:instCalAgyLocId ,
:instFromDate ,
:instPosition ,
:instRole ,
:autoAssessModifyDatetime ,
:autoConditionModifyDatetime ,
:sealFlag )
}
}

OCUSTFAS_CASEPLANS_UPDATE_CASE_VALUES {
}


OCUSTFAS_CASE_PLANS_PREINSERT_CASE_PLAN_ID {
	
SELECT COALESCE(MAX(CASE_PLAN_ID),0) + 1 FROM   CASE_PLANS WHERE  OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCUSTFAS_CASE_PLANS_PREINSERT {
	SELECT  REVIEW_PERIOD FROM  CASE_REVIEW_PERIODS WHERE  SUPERVISION_LEVEL = :supervisionLevel
}

OCUSTFAS_CHECK_REASSIGNMENT_ {
	select '1' from CASE_PLANS where OFFENDER_BOOK_ID = :V_OFF_BOOK_ID and CASE_PLAN_STATUS = 'ACTIVE' and AGY_LOC_ID = :P_AGY_LOC_ID and SAC_STAFF_ID = :P_STAFF_ID
}
OCUSTFAS_CASE_PLANS_STAFF_ID_PREINSERT {
	SELECT STAFF_ID FROM STAFF_MEMBERS WHERE LAST_NAME = :lastName and FIRST_NAME = :firstName and USER_ID = :userId
}
OCUSTFAS_CASE_PLANS_OFFICER_PREINSERT {
	SELECT STAFF.LAST_NAME ,STAFF.FIRST_NAME FROM   STAFF_MEMBERS STAFF WHERE  STAFF_ID = :staffId
}
OCUSTFAS_CASE_PLANS_UN_CLASS {

	select
		code
	from
		reference_codes rc
	where
	 rc."domain" ='SUP_LVL_TYPE'
		and rc.description  =(
		select
			profile_value
		from
			SYSTEM_PROFILES
		where
			PROFILE_TYPE = 'CLIENT'
			and PROFILE_CODE = 'PENDING_STAT')
			
}
OCUSTFAS_CASE_PLANS_MAX_ASS_SEQ {
SELECT MAX(off_ass.assessment_seq) FROM offender_assessments off_ass,  assessments ass WHERE off_ass.offender_book_id = :offenderBookId
                                     AND off_ass.assessment_type_id = ass.assessment_id
                                     AND ass.caseload_type = :caseloadType
                                     AND ass.determine_sup_level_flag ='Y'
                                     AND off_ass.review_sup_level_type IS NOT NULL
}
OCUSTFAS_CASE_PLANS_REVIEW_SUP_LEVEL_TYPE {
SELECT off_ass.review_sup_level_type
                                    FROM offender_assessments off_ass
                                   WHERE off_ass.offender_book_id = :offenderBookId
                                     AND off_ass.assessment_seq = :assessmentSeq
}
OCUSTFAS_AGENCY_LOCATIONS {
SELECT DESCRIPTION FROM AGENCY_LOCATIONS WHERE AGY_LOC_ID = :AGYLOCID
}
OCUSTFAS_CASE_PLANS_COUNT {
SELECT  COUNT(0)
   	   FROM  case_plans
   	  WHERE  offender_book_id = :OFFENDERBOOKID
}
OCUSTFAS_CASE_PLANS_INST_FROM_DATE {
SELECT * FROM STAFF_MEMBERS_V2 WHERE SAC_STAFF_ID = :SACSTAFFID
AND CAL_AGY_LOC_ID = :CALAGYLOCID
}

OCUSTFAS_GET_OLD_DATAOFFENDERCRIMINOGENICNEEDS{
select * from OFFENDER_CRIMINOGENIC_NEEDS ocn where offender_book_id =:offenderBookId and case_plan_id = (select max(case_plan_id) from OFFENDER_CRIMINOGENIC_NEEDS where offender_book_id = ocn.offender_book_id);
}

OCUSTFAS_GET_OLD_DATAPlanOfAction{
select
	*
from
	OFF_AP_V1 
where
	OFF_CRIM_NEED_ID = :offCrimNeedId
}

OCUSTFAS_GET_CONDITIONS{
select * from offender_sent_conditions where object_id =(  
select
	release_plan_id
from
	RELEASE_PLANS RP	
where
	RP.OFFENDER_BOOK_ID = :offenderBookId
	and release_plan_id  =(
	select
		max(release_plan_id)
	from
		RELEASE_PLANS)
)
}


OCUSTFAS_CASEPLANS_UPDATE_CASE_PLANS_STATUS{
update
	CASE_PLANS
set
	END_DATE = current_timestamp,
	CASE_PLAN_STATUS = 'CLOSED',
	--VERIFIED_FLAG = 'N',
	modify_datetime = current_timestamp,
	modify_user_id = :modifyUserId
where
	offender_book_id = :offender_book_id
	and case_plan_id != (select max(case_plan_id)  from case_plans where offender_book_id =:offender_book_id) 

}


OCUSTFAS_CASEPLANS_UPDATE_CASE_PLANS{
update
	CASE_PLANS
set
	inst_sac_staff_id=:instSacStaffId,
	modify_datetime = current_timestamp,
	modify_user_id = :modifyUserId,
	next_review_date=:nextReviewDate,
	inst_cal_agy_loc_id =:instCalAgyLocId,
	inst_from_date=(SELECT from_date  FROM STAFF_MEMBERS_V2 WHERE SAC_STAFF_ID = :instSacStaffId and CAL_AGY_LOC_ID = :instCalAgyLocId limit 1),
	inst_position=(SELECT position FROM STAFF_MEMBERS_V2 WHERE SAC_STAFF_ID = :instSacStaffId and CAL_AGY_LOC_ID = :instCalAgyLocId limit 1),
	inst_role=(SELECT role FROM STAFF_MEMBERS_V2 WHERE SAC_STAFF_ID = :instSacStaffId and CAL_AGY_LOC_ID = :instCalAgyLocId limit 1)
where
	OFFENDER_BOOK_ID = :offenderBookId
	and CASE_PLAN_ID=:casePlanId
}

OCUSTFAS_CASE_PLANS_COMM_CLASS {

	select
		code
	from
		reference_codes rc
	where
	 rc."domain" ='SUP_LVL_TYPE'
		and rc.description  =(
		select
			PROFILE_VALUE_2
		from
			SYSTEM_PROFILES
		where
			PROFILE_TYPE = 'CLIENT'
			and PROFILE_CODE = 'PENDING_STAT')
			
}

