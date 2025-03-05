
OCDTAPOW_FIND_CGFKSTAFFLRDSPDESCRIPTION {
SELECT agy_loc.description description, agy_loc.agency_location_type dsp_agency_location_type, agy_loc.agy_loc_id code FROM agency_locations agy_loc WHERE  EXISTS ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal WHERE cal.caseload_id = :caseLoad AND cal.agy_loc_id = agy_loc.agy_loc_id) ORDER BY agy_loc.description
}

OCDTAPOW_FIND_CGFKVOFFDETPOSITION {
 	select
	POS.DESCRIPTION DSP_DESCRIPTION6 ,
	POS.CODE as position /* CG$FK */
from
	REFERENCE_CODES POS
where
	domain = 'STAFF_POS'
	and coalesce (EXPIRED_DATE::timestamp, '2020-12-12')= '2020-12-12' 

}

OCDTAPOW_FIND_CGFKVOFFDETROLE {
 	select
	ROLE.DESCRIPTION DSP_DESCRIPTION5 ,
	ROLE.CODE as role
from
	REFERENCE_CODES role
where
	domain = 'STAFF_ROLE'
	and ACTIVE_FLAG = 'Y'
order by
	LIST_SEQ ,
	DESCRIPTION ,
	CODE
}

OCDTAPOW_FIND_STARTDATE{
select
	Max(start_date)
from
	case_plans
where
	offender_book_id = :OFFENDERBOOKID
	and case_plan_status = 'ACTIVE'
}

OCDTAPOW_CASEPLAN_UPDATE{
update case_plans set case_plan_status = 'REVISED' , end_date = current_timestamp, MODIFY_DATETIME= CURRENT_TIMESTAMP, MODIFY_USER_ID = :modifyUserId where offender_book_id = :offenderBookId and case_plan_id = :casePlanId
}

OCDTAPOW_GET_PREVIOSWORK_DATA {
SELECT 'Y' FROM assignment_transfers asst, offender_work_assignments owass WHERE asst.offass_id = owass.offass_id AND (owass.offender_book_id IN (SELECT b.offender_book_id FROM offender_bookings a, offender_bookings b WHERE a.root_offender_id = b.root_offender_id AND a.offender_book_id = :offenderBookId) OR owass.offender_book_id_request IN (SELECT b.offender_book_id FROM offender_bookings a, offender_bookings b WHERE a.root_offender_id = b.root_offender_id AND a.offender_book_id = :offenderBookId)) AND asst.sac_staff_id = :staffId UNION SELECT 'Y' FROM case_plans WHERE offender_book_id IN (SELECT b.offender_book_id FROM offender_bookings a, offender_bookings b WHERE a.root_offender_id = b.root_offender_id AND a.offender_book_id = :offenderBookId) AND sac_staff_id = :staffId
}
OCDTAPOW_GET_CASE_OFFICERID {
SELECT case_officer_id FROM offender_bookings WHERE offender_book_id =:offBookId
}

OCDTAPOW_FIND_CGFKVOFFDETSEXCODE {
select
	SEX_CODE.DESCRIPTION DSP_DESCRIPTION3 ,
	SEX_CODE.CODE SEX_CODE
from
	REFERENCE_CODES SEX_CODE
where
	domain = 'SEX'
	and ACTIVE_FLAG = 'Y'
order by
	LIST_SEQ ,
	DESCRIPTION ,
	CODE
}

OCDTAPOW_FIND_CGFKVOFFDETSCHEDULETYPE {
 	select
	SCHEDULE_TYPE.CODE SCHEDULE_TYPE /* CG$FK */
	,
	SCHEDULE_TYPE.DESCRIPTION DSP_DESCRIPTION4
from
	REFERENCE_CODES SCHEDULE_TYPE
where
	domain = 'SCHEDULE_TYP'
	and ACTIVE_FLAG = 'Y'
	and coalesce (EXPIRED_DATE::timestamp, '2020-12-12')= '2020-12-12' 
}

OCDTAPOW_UPDATE_OFFENDER_BOOKINGS{
update offender_bookings set COMM_STAFF_ROLE=:staffRole , COMMUNITY_AGY_LOC_ID=:agyLocId , COMM_STAFF_ID=:staffId, MODIFY_DATETIME= CURRENT_TIMESTAMP, MODIFY_USER_ID = :modifyUserId where offender_id=:offId
}

OCDTAPOW_FIND_CGFKVOFFDETSKILLTYPE {
 	select
	SKILL_TYPE.CODE SKILL_TYPE /* CG$FK */
	,
	SKILL_TYPE.DESCRIPTION DSP_DESCRIPTION2
from
	REFERENCE_CODES SKILL_TYPE
where
	domain = 'STAFF_SKILLS'
	and ACTIVE_FLAG = 'Y'
	and coalesce (EXPIRED_DATE::timestamp, '2020-12-12')= '2020-12-12' 
}

OCDTAPOW_FIND_CGFKVOFFDETSKILLSUBTYPE {
 	select
	SUB_TYPE.CODE SKILL_SUB_TYPE /* CG$FK */
	,
	SUB_TYPE.DESCRIPTION DSP_DESCRIPTION
from
	REFERENCE_CODES SUB_TYPE
where
	domain = 'SKL_SUB_TYPE'
	and ACTIVE_FLAG = 'Y'
	and coalesce (EXPIRED_DATE::timestamp, '2020-12-12')= '2020-12-12' 
}

OCDTAPOW_FIND_CGFKSTAFFLRDSPLASTNAME {
 	select
	STAFF.LAST_NAME DSP_LAST_NAME /* CG$FK */
	,
	STAFF.FIRST_NAME DSP_FIRST_NAME ,
	STAFF.STAFF_ID SAC_STAFF_ID
from
	STAFF_MEMBERS STAFF
where
	STAFF.STAFF_ID in (
	select
		SL.SAC_STAFF_ID
	from
		STAFF_LOCATION_ROLES SL
	where
		SL.CAL_AGY_LOC_ID = :CALAGYLOCID
		and coalesce (SL.TO_DATE::timestamp, '2020-12-12')= '2020-12-12'  )
		
order by
	STAFF.LAST_NAME asc
}

OCDTAPOW_STAFFLR_FIND_STAFF_LOCATION_ROLES {
 	select
	distinct TO_CHAR(staff_id) as staff_id,
	name,
	position,
	role,
	sex_code,
	schedule_type,
	hours_per_week,
	cal_agy_loc_id,
	from_date supervisor_from_date
from
	v_officer_details vof
where
	vof.cal_agy_loc_id =:agyLocId
	and vof.staff_id != :staffId
order by
	name,
	role asc
}

OCDTAPOW_GET_CASE_PLANID {
select
	coalesce( MAX(case_plan_id),
	0)
from
	case_plans
where
	offender_book_id = :offBookId
}

OCDTAPOW_FIND_FROMDATE {
select from_date from staff_location_roles where sac_staff_id = :staffId and cal_agy_loc_id = :agyLocId and position = :position and role = :role and coalesce (to_date::timestamp, '2020-12-12')= '2020-12-12' 
}

OCDTAPOW_FIND_REVIEWPERIOD {
SELECT crp.review_period FROM case_review_periods crp WHERE crp.supervision_level = :supervisionLevel
}

OCDTAPOW_CASEPLAN_INSERT {
insert into case_plans ( offender_book_id, case_plan_id, from_date, position, role, sac_staff_id, cal_agy_loc_id, agy_loc_id, creation_date, creation_user, end_date, supervision_level, changes, next_review_date, start_date, case_plan_status,CREATE_USER_ID,CREATE_DATETIME,MODIFY_DATETIME) values ( :offenderBookId, :casePlanId, :fromDate, :position, :role, :sacStaffId, :agyLocId , :agyLocId, current_timestamp , :creationUser, null, :supervisionLevel , case :casePlanId when 0 then null else 'Re-Assigned' end , :nextReviewDate, current_timestamp, 'ACTIVE' ,:createUserId,CURRENT_TIMESTAMP,null) 
}

OCDTAPOW_INSERT_ASSESSMENT_SUMMARIES {
INSERT INTO assessment_summaries ( offender_book_id, case_plan_id, assessment_seq, category, summary ,CREATE_USER_ID,CREATE_DATETIME,MODIFY_DATETIME) SELECT offender_book_id, (case_plan_id), assessment_seq, category, summary,:createUserId,CURRENT_TIMESTAMP,null FROM assessment_summaries WHERE offender_book_id = :offBookId AND case_plan_id =:casePlanId
}

OCDTAPOW_INSERT_PLAN_DETAILS {
INSERT INTO plan_details (offender_book_id ,case_plan_id ,detail_seq ,goal_type ,goal_sub_type ,target_date ,pla_det_comment ,off_cc_id,CREATE_USER_ID,CREATE_DATETIME,MODIFY_DATETIME) SELECT offender_book_id ,(case_plan_id) ,detail_seq ,goal_type ,goal_sub_type ,target_date ,pla_det_comment ,off_cc_id,:createUserId,CURRENT_TIMESTAMP,null FROM plan_details WHERE offender_book_id = :offBookId AND case_plan_id = :casePlanId
}

OCDTAPOW_INSERTCASE_WORKSTEPS{
INSERT INTO casework_steps(offender_book_id, case_plan_id, detail_seq, casework_seq, planned_step, cas_ste_comment,CREATE_USER_ID,CREATE_DATETIME,MODIFY_DATETIME) SELECT offender_book_id, (case_plan_id), detail_seq, casework_seq, planned_step, cas_ste_comment,:createUserId,CURRENT_TIMESTAMP,null FROM casework_steps WHERE offender_book_id =:offBookId AND case_plan_id = :casePlanId
}

OCDTAPOW_GET_PRE_WORK_ASSIGNEMET_DET{
SELECT OWA.FROM_DATE ,OWA.POSITION ,OWA.ROLE ,OWA.CAL_AGY_LOC_ID ,OWA.STATUS ,OWA.OFFASS_ID FROM OFFENDER_WORK_ASSIGNMENTS OWA WHERE (OWA.OFFENDER_BOOK_ID = :offenderBookId OR OWA.OFFENDER_BOOK_ID_REQUEST = :offenderBookId) AND OWA.SAC_STAFF_ID = :staffId ORDER BY OWA.CREATION_DATE DESC
}

OCDTAPOW_INSERT_ASSIGNMENT_TRANSFERS{
INSERT INTO assignment_transfers (asstra_id, from_date_from, position_from, role_from, sac_staff_id_from, cal_agy_loc_id_from, status_from, agy_loc_id, from_date, position, role, sac_staff_id, cal_agy_loc_id, status_to, agy_loc_id_to, transfer_date ,transfer_date_to, creation_date, creation_user, offass_id,CREATE_USER_ID,CREATE_DATETIME,MODIFY_DATETIME) VALUES
		(:assId, :fromDateFrom, :positionFrom, :roleFrom, :sacStaffIdFrom, :calAgyLocId, :statusFrom ,:agyLocId, :fromDate, :position, :role, :sacStaffId, :calAgyLocId, :statusTo, :agyLocId, SYSDATE ,SYSDATE, SYSDATE, USER, asstra_seq.nextval,:createUserId,CURRENT_TIMESTAMP,null)
}

OCDTAPOW_UPDATEWORK_ASSIGMENTS{
UPDATE offender_work_assignments offass SET offass.from_date =:fromDate ,offass.position = :position ,offass.role = :role ,offass.sac_staff_id = :sac_staffId ,offass.cal_agy_loc_id = :calAgyLocId ,offass.status = :statusTo, MODIFY_DATETIME= CURRENT_TIMESTAMP, MODIFY_USER_ID = :modifyUserId WHERE offass.offass_id = :offasId
}

OCDTAPOW_GET_OFFENDER_FILE_DETAILS{
SELECT offender_file_seq, holding_staff_id, non_officer_status FROM offender_community_files WHERE offender_id = :offenderId
}

OCDTAPOW_GETPROFILEVALUE{
select PROFILE_VALUE from  system_profiles where profile_type = 'FILE_TRANS' and profile_code = 'FILE'
}

OCDTAPOW_UPDATE_COMMUNITY_FINANCEFILES{ 
UPDATE offender_community_files SET non_officer_status = NULL, holding_staff_id = :holdingStaffId, storage = NULL, holding_agy_loc_id = :holdingAgyLocId, MODIFY_DATETIME= CURRENT_TIMESTAMP, MODIFY_USER_ID = :modifyUserId WHERE offender_id = :offenderId AND offender_file_seq = :offFileSeq
}

OCDTAPOW_GETOFFASID_SEQ{
select
	NEXTVAL('ASSTRA_SEQ')
from
	DUAL
}

OCDTAPOW_STAFFLR_INSERT_STAFF_LOCATION_ROLES {
	INSERT INTO STAFF_LOCATION_ROLES() VALUES(:)
}

OCDTAPOW_STAFFLR_UPDATE_STAFF_LOCATION_ROLES {
	UPDATE STAFF_LOCATION_ROLES set /* where */
}

OCDTAPOW_OFFBKG1_FIND_OFFENDER_BOOKINGS {
 select offender_book_id,
    booking_begin_date,
    booking_end_date,
    booking_no,
    offender_id,
    booking_status, 
    root_offender_id,    
    case_officer_id,   
    status_reason,
    total_unexcused_absences,
    record_user_id,
    intake_agy_loc_assign_date,
    assigned_staff_id
FROM offender_bookings WHERE offender_bookings.booking_status = 'O' AND
community_active_flag = 'Y'  AND EXISTS 
                            ( SELECT '1' 
                                 FROM OFFENDER_BOOKING_AGY_LOCS OBAL 
                                WHERE OBAL.OFFENDER_BOOK_ID = OFFENDER_BOOKINGS.OFFENDER_BOOK_ID 
                                 AND OBAL.REMOVED_DATE IS NULL 
                                  AND OBAL.AGY_LOC_ID = :agyLocId ) AND OFFENDER_BOOK_ID IN ( SELECT OFFENDER_BOOK_ID FROM CASE_PLANS
CP1 WHERE CP1.SAC_STAFF_ID =:staffId AND CP1.POSITION = :position AND cp1.role = :role AND coalesce (CP1.END_DATE::timestamp, '2020-12-12')= '2020-12-12' AND  cp1.case_plan_status = 'ACTIVE')
}
OCDTAPOW_OFFBKG1_UPDATE_OFFENDER_BOOKINGS {
	UPDATE OFFENDER_BOOKINGS set /* where */
}


OCDTAPOW_CGFKCHK_STAFF_LR_STAFF_LR_AGY_ {
	SELECT AGY_LOC.DESCRIPTION ,AGY_LOC.AGENCY_LOCATION_TYPE FROM   AGENCY_LOCATIONS AGY_LOC WHERE  AGY_LOC.AGY_LOC_ID = :CALAGYLOCID AND     AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM'
}

OCDTAPOW_CGFKLKP_STAFF_LR_STAFF_LR_AGY_ {
	SELECT AGY_LOC.AGY_LOC_ID ,AGY_LOC.AGENCY_LOCATION_TYPE FROM   AGENCY_LOCATIONS AGY_LOC WHERE  (AGY_LOC.DESCRIPTION = :DSPDESCRIPTION OR ( coalesce(AGY_LOC.DESCRIPTION::text, '') = '' ) AND  coalesce(:DSPDESCRIPTION::text, '') = '' )) AND     AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM'
}

OCDTAPOW_CGFKCHK_STAFF_LR_STAFF_LR_STA_ {
	SELECT STAFF.LAST_NAME ,STAFF.FIRST_NAME FROM   STAFF_MEMBERS STAFF WHERE  STAFF.STAFF_ID = :SACSTAFFID AND     STAFF.STAFF_ID  IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE  SL.CAL_AGY_LOC_ID = :CALAGYLOCID AND SL.TO_DATE IS NULL)
}

OCDTAPOW_CGFKCHK_OFF_BKG_OFF_BKG_OFF_N_ {
SELECT OFF_NAME.OFFENDER_ID_DISPLAY ,OFF_NAME.OFFENDER_ID ,OFF_NAME.LAST_NAME ,OFF_NAME.FIRST_NAME  
FROM OFFENDERS OFF_NAME,
OFFENDER_BOOKINGS OB
WHERE
OB.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
AND OFF_NAME.OFFENDER_ID = OB.OFFENDER_ID
ORDER by OFF_NAME.LAST_NAME, OFF_NAME.FIRST_NAME
}

OCDTAPOW_CGFKCHK_V_OFF_DET_V_OFF_DET_R_ {
	SELECT SUB_TYPE.DESCRIPTION FROM   REFERENCE_CODES SUB_TYPE WHERE  SUB_TYPE.CODE = :SKILLSUBTYPE AND     DOMAIN = 'SKL_SUB_TYPE' AND EXPIRED_DATE IS NULL
}

OCDTAPOW_CGFKCHK_V_OFF_DET_V_OFF_DE2_ {
	SELECT SKILL_TYPE.DESCRIPTION FROM   REFERENCE_CODES SKILL_TYPE WHERE  SKILL_TYPE.CODE = :SKILLTYPE AND     DOMAIN = 'STAFF_SKILLS' AND EXPIRED_DATE IS NULL
}

OCDTAPOW_CGFKCHK_V_OFF_DET_V_OFF_DE3_ {
	SELECT SEX_CODE.DESCRIPTION FROM   REFERENCE_CODES SEX_CODE WHERE  SEX_CODE.CODE = :SEXCODE AND     DOMAIN = 'SEX' AND EXPIRED_DATE IS NULL
}

OCDTAPOW_CGFKCHK_V_OFF_DET_V_OFF_DE4_ {
	SELECT SCHEDULE_TYPE.DESCRIPTION FROM   REFERENCE_CODES SCHEDULE_TYPE WHERE  SCHEDULE_TYPE.CODE = :SCHEDULETYPE AND     DOMAIN = 'SCHEDULE_TYP' AND EXPIRED_DATE IS NULL
}

OCDTAPOW_CGFKCHK_V_OFF_DET_V_OFF_DE5_ {
	SELECT ROLE.DESCRIPTION FROM   REFERENCE_CODES ROLE WHERE  ROLE.CODE = :ROLE AND     DOMAIN = 'STAFF_ROLE' AND EXPIRED_DATE IS NULL
}

OCDTAPOW_CGFKCHK_V_OFF_DET_V_OFF_DE6_ {
	SELECT POS.DESCRIPTION FROM   REFERENCE_CODES POS WHERE  POS.CODE = :POSITION AND     DOMAIN = 'STAFF_POS' AND EXPIRED_DATE IS NULL
}

OCDTAPOW_CGWHEN_NEW_FORM_INSTANCE_ {
	select
	SYSDATE(),
	upper(user)
from
	DUAL
}

OCDTAPOW_GET_CREATE_USER {
SELECT creation_user
    FROM case_plans
   WHERE offender_book_id = :p_offender_book_id
     AND case_plan_id = :v_id
}

OCDTAPOW_GET_ALIAS_OFFENDER_ID {
    select ALIAS_OFFENDER_ID from v_header_block where OFFENDER_BOOK_ID=:offBookId
}
