
OCDIPLAN_FIND_RGCASEPLANASS {
 	SELECT DESCRIPTION , CODE FROM REFERENCE_CODES WHERE DOMAIN = 'CASEPLAN_ASS' AND (  ACTIVE_FLAG = 'Y' ) ORDER BY DESCRIPTION , CODE
}

OCDIPLAN_FIND_RGCASEINFO {
 	SELECT -1 ,1 ,1 ,NULL ,1 FROM DUAL
}

OCDIPLAN_FIND_CGFKCASPLNDSPDESCRIPTION {
 	SELECT REF_CODE1.DESCRIPTION  DSP_DESCRIPTION  /* CG$FK */        ,REF_CODE1.LIST_SEQ  DSP_LIST_SEQ        ,REF_CODE1.CODE  CASE_PLAN_STATUS FROM   REFERENCE_CODES REF_CODE1 WHERE   (DOMAIN='CASEPLAN_STS' AND (ACTIVE_FLAG = 'Y' ) /*AND CODE != 'REVISED'*/ ) ORDER BY  REF_CODE1.LIST_SEQ ASC         ,REF_CODE1.DESCRIPTION ASC
}

OCDIPLAN_FIND_CGFKCASPLNDSPDESCRIPTION4 {
 	SELECT AL.DESCRIPTION  DSP_DESCRIPTION4  /* CG$FK */        ,AL.LIST_SEQ  DSP_LIST_SEQ3        ,AL.AGY_LOC_ID  AGY_LOC_ID FROM   AGENCY_LOCATIONS AL WHERE   AL.AGY_LOC_ID IN      (SELECT CAL.AGY_LOC_ID       FROM CASELOAD_AGENCY_LOCATIONS CAL               ,CASELOADS CAS      WHERE CAS.CASELOAD_ID = CAL.CASELOAD_ID      AND CAL.CASELOAD_ID = :CASELOADID      AND (CAL.UPDATE_ALLOWED_FLAG =  'Y' ) ) AND AGY_LOC_ID NOT IN ('OUT' ,'TRN' ) ORDER BY  AL.LIST_SEQ ASC
}

OCDIPLAN_FIND_CGFKCASPLNDSPSTAFFNAME {
 	SELECT VSM2.STAFF_NAME DSP_STAFF_NAME /* CG$FK */  ,VSM2.SAC_STAFF_ID SAC_STAFF_ID  ,VSM2.CAL_AGY_LOC_ID CAL_AGY_LOC_ID  ,VSM2.FROM_DATE FROM_DATE  ,VSM2.ROLE   ,VSM2.POSITION  FROM STAFF_MEMBERS_V2 VSM2 WHERE VSM2.CAL_AGY_LOC_ID = :AGYLOCID AND (DATE_TO IS NULL  )
}

OCDIPLAN_FIND_RGCRIMNEEDSSTS {
 	SELECT RC.DESCRIPTION , RC.CODE FROM REFERENCE_CODES RC WHERE RC.DOMAIN = 'CP_FACT_STS' AND RC.EXPIRED_DATE IS NULL
}

OCDIPLAN_FIND_RGCASEWORK {
 	SELECT RC.DESCRIPTION , RC.CODE FROM REFERENCE_CODES RC WHERE RC.DOMAIN = 'CASEPLAN_STP' AND RC.EXPIRED_DATE IS NULL
}

OCDIPLAN_FIND_RGPRGCATEGORY {
 	SELECT REF_CD_PS_CAT.DESCRIPTION  DESCRIPTION               ,REF_CD_PS_CAT.CODE         CODE  FROM  
 REFERENCE_CODES   REF_CD_PS_CAT        ,REFERENCE_CODES   REF_CD_PLAN_ACT      WHERE  REF_CD_PLAN_ACT.DOMAIN  = 'PLAN_ACT_PRG' 
  AND  (REF_CD_PLAN_ACT.ACTIVE_FLAG = 'Y' OR '' <> 'NORMAL' )   AND  REF_CD_PLAN_ACT.PARENT_DOMAIN  = REF_CD_PS_CAT.DOMAIN  
  AND  REF_CD_PLAN_ACT.PARENT_CODE    = REF_CD_PS_CAT.CODE      AND  (REF_CD_PS_CAT.ACTIVE_FLAG = 'Y' OR '' <> 'NORMAL' )  
 AND  REF_CD_PS_CAT.DOMAIN = 'PS_CATEGORY'    AND 
 REF_CD_PLAN_ACT.PARENT_CODE = REF_CD_PLAN_ACT.CODE  ORDER BY  REF_CD_PLAN_ACT.LIST_SEQ  ASC  ,REF_CD_PS_CAT.DESCRIPTION ASC
}

OCDIPLAN_FIND_RGPROGRAMID {
SELECT PS.DESCRIPTION ,         to_char(PS.PROGRAM_ID) CODE ,        PS.PROGRAM_CODE ,        PS.PROGRAM_CATEGORY   FROM PROGRAM_SERVICES PS  WHERE ((PS.ACTIVE_FLAG = 'Y' AND PS.EXPIRY_DATE IS NULL ) OR        '' = 'ENTER-QUERY' )    AND PS.PROGRAM_CATEGORY = :programCategory  ORDER BY DESCRIPTION ASC
}

OCDIPLAN_FIND_RGPROGRAMID2 {
SELECT PS.DESCRIPTION ,        PS.PROGRAM_ID ,        PS.PROGRAM_CODE ,        PS.PROGRAM_CATEGORY   FROM PROGRAM_SERVICES PS  WHERE ((PS.ACTIVE_FLAG = 'Y' AND PS.EXPIRY_DATE IS NULL ) )    AND PS.PROGRAM_CATEGORY = :PROGRAMCATEGORY  ORDER BY DESCRIPTION ASC
}

OCDIPLAN_CASPLN_FIND_CASE_PLANS {
select
	cp.OFFENDER_BOOK_ID ,
	cp.CASE_PLAN_ID ,
	cp.FROM_DATE ,
	cp.position ,
	cp.role ,
	cp.SAC_STAFF_ID ,
	cp.CAL_AGY_LOC_ID ,
	cp.AGY_LOC_ID ,
	cp.CASE_PLAN_STATUS as status,
	cp.CREATION_DATE ,
	cp.CREATION_USER ,
	cp.END_DATE ,
	(select description  from reference_codes rc  where "domain" ='SUP_LVL_TYPE' and code =cp.SUPERVISION_LEVEL) as supLevel ,
	cp.CHANGES ,
	cp.NEXT_REVIEW_DATE ,
	cp.START_DATE ,
	cp.CASELOAD_TYPE ,
	cp.VERIFIED_FLAG ,
	cp.CREATE_DATETIME ,
	cp.CREATE_USER_ID ,
	cp.MODIFY_DATETIME ,
	cp.MODIFY_USER_ID ,
	cp.INST_SAC_STAFF_ID ,
	cp.INST_CAL_AGY_LOC_ID ,
	cp.INST_FROM_DATE ,
	cp.INST_POSITION ,
	cp.INST_ROLE ,
	cp.AUTO_ASSESS_MODIFY_DATETIME ,
	cp.AUTO_CONDITION_MODIFY_DATETIME ,
	cp.SEAL_FLAG,
	(
	select
		description
	from
		AGENCY_LOCATIONS
	where
		agy_loc_id = cp.cal_agy_loc_id) as communityAgyLoc,
	(
	select
		concat (last_name || ', ' || first_name)
	from
		staff_members
	where
		staff_id = cp.sac_staff_id) communityOfficer,
	(
	select
		user_id
	from
		staff_members
	where
		staff_id = cp.sac_staff_id ) communityUser,
	(
	select
		description
	from
		AGENCY_LOCATIONS
	where
		agy_loc_id = cp.inst_cal_agy_loc_id) as custodialAgyLoc,
	case
	when (
	select
		count(*)
	from
		case_plan_staff_roles ooad
	where
		offender_book_id =cp.offender_book_id
	and case_plan_id =cp.CASE_PLAN_ID and cp_owner='Y') > 1 then 'Multiple Officers'
	else (
	select
		concat (last_name || ', ' || first_name)
	from
		staff_members sm,case_plan_staff_roles ooad
	where
		sm.staff_id = ooad.staff_id and ooad.cp_owner='Y' and offender_book_id =cp.offender_book_id
	and case_plan_id =cp.CASE_PLAN_ID ) 
end as staffName,
	(
	select
		user_id
	from
		staff_members
	where
		staff_id = cp.inst_sac_staff_id ) custodialUser,
	(
	select
		work_action_code
	from
		work_flow_logs
	where
		work_flow_id =(
		select
			max(work_flow_id)
		from
			work_flows
		where
			object_id = cp.offender_book_id)
		and 
work_flow_seq =(
		select
			max(work_flow_seq)
		from
			work_flow_logs
		where
			work_flow_id =(
			select
				max(work_flow_id)
			from
				work_flows
			where
				object_id = cp.offender_book_id))) as reviewFlag,
	(
	select
		work_flow_status
	from
		work_flow_logs
	where
		work_flow_id =(
		select
			max(work_flow_id)
		from
			work_flows
		where
			object_id = cp.offender_book_id)
		and 
work_flow_seq =(
		select
			max(work_flow_seq)
		from
			work_flow_logs
		where
			work_flow_id =(
			select
				max(work_flow_id)
			from
				work_flows
			where
				object_id = cp.offender_book_id))) as workFlowStatus,
				
				(
	select
		STRING_AGG (
		staff_id::text,
		',') as staff_id
	from
		case_plan_staff_roles
	where
		offender_book_id = cp.offender_book_id
		and case_plan_id = cp.CASE_PLAN_ID
		and cp_owner = 'Y') as staff_list
from
	CASE_PLANS cp
where
	cp.offender_book_id = :offenderBookId
order by
	CASE_PLAN_ID desc
}
OCDIPLAN_CASPLN_UPDATE_CASE_PLANS {
	UPDATE CASE_PLANS set OFFENDER_BOOK_ID  = :offenderBookId ,CASE_PLAN_ID  = :casePlanId ,FROM_DATE  = :fromDate ,POSITION  = :position ,ROLE  = :role ,SAC_STAFF_ID  = :sacStaffId ,CAL_AGY_LOC_ID  = :calAgyLocId ,AGY_LOC_ID  = :agyLocId ,CASE_PLAN_STATUS  = :casePlanStatus ,CREATION_DATE  = :creationDate ,CREATION_USER  = :creationUser ,END_DATE  = :endDate ,SUPERVISION_LEVEL  = :supervisionLevel ,CHANGES  = :changes ,NEXT_REVIEW_DATE  = :nextReviewDate ,START_DATE  = :startDate ,CASELOAD_TYPE  = :caseloadType ,VERIFIED_FLAG  = :verifiedFlag, INST_SAC_STAFF_ID  = :instSacStaffId ,INST_CAL_AGY_LOC_ID  = :instCalAgyLocId ,INST_FROM_DATE  = :instFromDate ,INST_POSITION  = :instPosition ,INST_ROLE  = :instRole ,AUTO_ASSESS_MODIFY_DATETIME  = :autoAssessModifyDatetime ,AUTO_CONDITION_MODIFY_DATETIME  = :autoConditionModifyDatetime ,SEAL_FLAG  = :sealFlag, MODIFY_USER_ID = :modifyUserId,modify_datetime=current_timestamp  where OFFENDER_BOOK_ID  = :offenderBookId and CASE_PLAN_ID  = :casePlanId 
}

OCDIPLAN_OFFCRINEEDS_FIND_OFFENDER_CRIMINOGENIC_NEEDS {
 	select OFF_CRIM_NEED_ID , OFFENDER_BOOK_ID , CASE_PLAN_ID , ASSESSED_NEED_CODE , OBJECTIVE , TARGET_DATE , END_DATE , STATUS_CODE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , MODIFY_USER_ID , SEAL_FLAG, row_number() over() row_id from OFFENDER_CRIMINOGENIC_NEEDS where OFFENDER_BOOK_ID = :offenderBookId and CASE_PLAN_ID = :casePlanId order by STATUS_CODE, END_DATE nulls first
}
OCDIPLAN_OFFCRINEEDS_INSERT_OFFENDER_CRIMINOGENIC_NEEDS {
	insert into OFFENDER_CRIMINOGENIC_NEEDS(OFF_CRIM_NEED_ID , OFFENDER_BOOK_ID , CASE_PLAN_ID , ASSESSED_NEED_CODE , OBJECTIVE , TARGET_DATE , END_DATE , STATUS_CODE , SEAL_FLAG,create_datetime,modify_datetime,create_user_id ) values(:offCrimNeedId, :offenderBookId , :casePlanId , :assessedNeedCode , :objective , :targetDate , :endDate , :statusCode , :sealFlag,current_timestamp,null,:createUserId )
}

OCDIPLAN_OFFCRINEEDS_UPDATE_OFFENDER_CRIMINOGENIC_NEEDS {
	UPDATE OFFENDER_CRIMINOGENIC_NEEDS set OFF_CRIM_NEED_ID  = :offCrimNeedId ,OFFENDER_BOOK_ID  = :offenderBookId ,CASE_PLAN_ID  = :casePlanId ,ASSESSED_NEED_CODE  = :assessedNeedCode ,OBJECTIVE  = :objective ,TARGET_DATE  = :targetDate ,END_DATE  = :endDate ,STATUS_CODE  = :statusCode ,SEAL_FLAG  = :sealFlag, MODIFY_USER_ID = :modifyUserId,modify_datetime=current_timestamp  where OFF_CRIM_NEED_ID  = :offCrimNeedId  and OFFENDER_BOOK_ID  = :offenderBookId and CASE_PLAN_ID  = :casePlanId 
}

OCDIPLAN_OFFACTIONPLANSV1_FIND_OFF_AP_V1 {
 	SELECT OFF_ACTION_PLAN_ID ,CASEWORK_TYPE ,CASEWORK_TYPE_DESC ,OFF_CRIM_NEED_ID ,OFF_CASE_COND_ID ,to_char(PROGRAM_ID) PROGRAM_DESC ,NOTES ,START_DATE ,END_DATE ,PROGRAM_CATEGORY ,PRG_CATEGORY_DESC   FROM OFF_AP_V1   where  OFF_CRIM_NEED_ID = :offCrimNeedId
}
OCDIPLAN_OFFACTIONPLANSV1_INSERT_OFF_AP_V1 {
	insert
	into
	OFF_AP_V1(OFF_ACTION_PLAN_ID ,
	CASEWORK_TYPE ,
	CASEWORK_TYPE_DESC ,
	OFF_CRIM_NEED_ID ,
	OFF_CASE_COND_ID ,
	PROGRAM_ID ,
	PROGRAM_DESC ,
	NOTES ,
	START_DATE ,
	END_DATE ,
	PROGRAM_CATEGORY ,
	PRG_CATEGORY_DESC )
values((NEXTVAL('OFF_ACTION_PLAN_ID') ,
:caseworkType ,
:caseworkTypeDesc ,
:offCrimNeedId ,
:offCaseCondId ,
:programId ,
:programDesc ,
:notes ,
:startDate ,
:endDate ,
:programCategory ,
:prgCategoryDesc )
}

OCDIPLAN_OFFACTIONPLANSV1_UPDATE_OFF_AP_V1 {
	UPDATE OFF_AP_V1 set OFF_ACTION_PLAN_ID  = :offActionPlanId ,CASEWORK_TYPE  = :caseworkType ,CASEWORK_TYPE_DESC  = :caseworkTypeDesc ,OFF_CRIM_NEED_ID  = :offCrimNeedId ,OFF_CASE_COND_ID  = :offCaseCondId ,PROGRAM_ID  = :programId ,PROGRAM_DESC  = :programDesc ,NOTES  = :notes ,START_DATE  = :startDate ,END_DATE  = :endDate ,PROGRAM_CATEGORY  = :programCategory ,PRG_CATEGORY_DESC  = :prgCategoryDesc  where OFF_ACTION_PLAN_ID  = :offActionPlanId and OFF_CRIM_NEED_ID  = :offCrimNeedId
}

OCDIPLAN_OFFACTIONPLANSV1_DELETE_OFF_AP_V1 { 
	DELETE FROM OFF_AP_V1 where OFF_ACTION_PLAN_ID  = :offActionPlanId and OFF_CRIM_NEED_ID  = :offCrimNeedId
}

OCDIPLAN_OFFCASECONDS_FIND_OFFENDER_CASE_CONDITIONS {
select occ.*,( select description from automation_teams where team_id = occ.team_id ) team_name, case when (team_id is null) then ( select (SM.LAST_NAME || ' , ' || SM.FIRST_NAME)::varchar from STAFF_MEMBERS SM where sm.staff_id = occ.staff_id) else ( select (SM.LAST_NAME || ' , ' || SM.FIRST_NAME)::varchar from STAFF_MEMBERS SM where sm.staff_id = ( select staff_id from TEAM_STAFF_MEMBERS tm where tm.team_id = team_id::bigint and occ.team_member_id = tm.team_member_id )) end as staff_Name from(select occ.off_case_cond_id, occ.offender_book_id, occ.case_plan_id, occ.offender_sent_condition_id, occ.objective, occ.comm_condition_type, occ.comm_condition_code, occ.length, occ.length_unit, occ.start_date, occ.end_date, occ.condition_status, occ.category_type, occ.create_datetime, occ.create_user_id, occ.modify_datetime, occ.modify_user_id, occ.seal_flag, coalesce(occ.team_id , (select coalesce(A.team_id, A.to_team_id) from ( select MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID from OFFENDER_COND_TRANSFER group by OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID) B, OFFENDER_COND_TRANSFER A where A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.OFFENDER_SENT_CONDITION_ID = B.OFFENDER_SENT_CONDITION_ID and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID and A.OFFENDER_SENT_CONDITION_ID = OCC.OFFENDER_SENT_CONDITION_ID) ) as team_id, coalesce(occ.team_member_id , ( select coalesce(A.team_Member_Id, A.to_Team_Member_Id) from ( select MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID from OFFENDER_COND_TRANSFER group by OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID) B, OFFENDER_COND_TRANSFER A where A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.OFFENDER_SENT_CONDITION_ID = B.OFFENDER_SENT_CONDITION_ID and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID and A.OFFENDER_SENT_CONDITION_ID = OCC.OFFENDER_SENT_CONDITION_ID ) ) as team_member_id, coalesce(occ.staff_id , ( select coalesce(A.staff_id, A.to_staff_id) from ( select MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID from OFFENDER_COND_TRANSFER group by OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID) B, OFFENDER_COND_TRANSFER A where A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.OFFENDER_SENT_CONDITION_ID = B.OFFENDER_SENT_CONDITION_ID and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID and A.OFFENDER_SENT_CONDITION_ID = OCC.OFFENDER_SENT_CONDITION_ID ) ) as staff_id from offender_case_conditions occ where occ.offender_book_id = :offenderBookId and occ.case_plan_id = :casePlanId ) as occ}OCDIPLAN_OFFCASECONDS_UPDATE_OFFENDER_CASE_CONDITIONS 
{ 
update OFFENDER_CASE_CONDITIONS set OFF_CASE_COND_ID = :offCaseCondId , OFFENDER_BOOK_ID = :offenderBookId , CASE_PLAN_ID = :casePlanId , OFFENDER_SENT_CONDITION_ID = :offenderSentConditionId , OBJECTIVE = :objective , COMM_CONDITION_TYPE = :commConditionType , COMM_CONDITION_CODE = :commConditionCode , LENGTH = :length , LENGTH_UNIT = :lengthUnit , START_DATE = :startDate , END_DATE = :endDate , CONDITION_STATUS = :conditionStatus , CATEGORY_TYPE = :categoryType  , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where OFF_CASE_COND_ID = :offCaseCondId and OFFENDER_BOOK_ID = :offenderBookId and CASE_PLAN_ID = :casePlanId
}

OCDIPLAN_OFFACTIONPLANSV2_FIND_OFF_AP_V2 {
 	SELECT OFF_ACTION_PLAN_ID ,CASEWORK_TYPE ,CASEWORK_TYPE_DESC ,OFF_CRIM_NEED_ID ,OFF_CASE_COND_ID ,to_char(PROGRAM_ID) PROGRAM_DESC ,PROGRAM_DESC ,NOTES ,START_DATE ,END_DATE ,PROGRAM_CATEGORY ,PRG_CATEGORY_DESC   FROM OFF_AP_V2   where OFF_CASE_COND_ID = :offCaseCondId 
}
OCDIPLAN_OFFACTIONPLANSV2_INSERT_OFF_AP_V2 {
	INSERT INTO OFF_AP_V2(OFF_ACTION_PLAN_ID ,CASEWORK_TYPE ,CASEWORK_TYPE_DESC ,OFF_CRIM_NEED_ID ,OFF_CASE_COND_ID ,PROGRAM_ID ,PROGRAM_DESC ,NOTES ,START_DATE ,END_DATE ,PROGRAM_CATEGORY ,PRG_CATEGORY_DESC ) VALUES((NEXTVAL('OFF_ACTION_PLAN_ID') ,:caseworkType ,:caseworkTypeDesc ,:offCrimNeedId ,:offCaseCondId ,:programId ,:programDesc ,:notes ,:startDate ,:endDate ,:programCategory ,:prgCategoryDesc )
}

OCDIPLAN_OFFACTIONPLANSV2_UPDATE_OFF_AP_V2 {
	UPDATE OFF_AP_V2 set OFF_ACTION_PLAN_ID  = :offActionPlanId ,CASEWORK_TYPE  = :caseworkType ,CASEWORK_TYPE_DESC  = :caseworkTypeDesc ,OFF_CRIM_NEED_ID  = :offCrimNeedId ,OFF_CASE_COND_ID  = :offCaseCondId ,PROGRAM_ID  = :programId ,PROGRAM_DESC  = :programDesc ,NOTES  = :notes ,START_DATE  = :startDate ,END_DATE  = :endDate ,PROGRAM_CATEGORY  = :programCategory ,PRG_CATEGORY_DESC  = :prgCategoryDesc  where OFF_ACTION_PLAN_ID  = :offActionPlanId
	
}

OCDIPLAN_OFFACTIONPLANSV2_DELETE_OFF_AP_V2 { 
	DELETE FROM OFF_AP_V2  where OFF_ACTION_PLAN_ID  = :offActionPlanId
}

OCDIPLAN_VSUMMARYCASEPLAN_FIND_V_SUMMARY_CASE_PLAN {
 	SELECT TYPE ,OFFENDER_BOOK_ID ,CASE_PLAN_ID ,ISSUE ,CASEWORK_TYPE ,CASEWORK_TYPE_DESC ,PROGRAM_DESC ,NOTES ,START_DATE ,END_DATE   FROM V_SUMMARY_CASE_PLAN   WHERE  OFFENDER_BOOK_ID = :offenderBookId and CASE_PLAN_ID=:caseplanId ORDER BY END_DATE NULLS FIRST, START_DATE
}

OCDIPLAN_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM CASE_PLANS C WHERE C.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDIPLAN_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_SUMMARY_CASE_PLAN V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDIPLAN_CAS_PLN_POSTQUERY_SAC_STAFF_ID {
	SELECT VSM2.STAFF_NAME FROM   STAFF_MEMBERS_V2 VSM2 WHERE     
	VSM2.SAC_STAFF_ID   = :sacStaffId AND    
	VSM2.CAL_AGY_LOC_ID = :calAgyLocId AND (date_to is null  or '' <> 'NORMAL')
}

OCDIPLAN_CAS_PLN_POSTQUERY_CALAGYLOCID {
	SELECT AL.DESCRIPTION FROM   AGENCY_LOCATIONS AL WHERE  AL.AGY_LOC_ID = :calAgyLocId
}

OCDIPLAN_CAS_PLN_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_CRIMINOGENIC_NEEDS O WHERE O.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND O.CASE_PLAN_ID = :CASEPLANID
}

OCDIPLAN_CAS_PLN_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_CASE_CONDITIONS O WHERE O.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND O.CASE_PLAN_ID = :CASEPLANID
}

OCDIPLAN_OFF_CRI_NEEDS_PREINSERT_ {
	SELECT NEXTVAL('OFF_CRIM_NEED_ID') FROM DUAL
}

OCDIPLAN_OFF_CRI_NEEDS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFF_AP_V1 O WHERE O.OFF_CRIM_NEED_ID = :OFFCRIMNEEDID
}

OCDIPLAN_OFF_CRI_NEEDS_POSTQUERY_CP_FACT_STS {
	SELECT RC.DESCRIPTION FROM REFERENCE_CODES RC WHERE RC.DOMAIN = 'CP_FACT_STS' AND RC.CODE = :statusCode
}

OCDIPLAN_OFF_CRI_NEEDS_POSTQUERY_CASEPLAN_ASS {
	SELECT RC.DESCRIPTION FROM REFERENCE_CODES RC WHERE RC.DOMAIN = 'CASEPLAN_ASS' AND RC.CODE = :assessedNeedCode
}

OCDIPLAN_OFF_ACTION_PLANS_V1_PREINSERT_ {
	SELECT NEXTVAL('OFF_ACTION_PLAN_ID') FROM DUAL
}

OCDIPLAN_OFF_CASE_CONDS_POSTQUERY_FOR_CODE {
	SELECT CC.DESCRIPTION FROM COMMUNITY_CONDITIONS CC WHERE COMM_CONDITION_TYPE = :commConditioonType AND COMM_CONDITION_CODE = :commConditionCode AND CATEGORY_TYPE       = :categoryType
}

OCDIPLAN_OFF_CASE_CONDS_POSTQUERY_TO_GET_LATEST_DATE {
	SELECT MAX(MODIFY_DATETIME) MODIFY_DATETIME FROM OFFENDER_CASE_CONDITIONS WHERE CASE_PLAN_ID = :casePlanId AND OFFENDER_BOOK_ID = :offenderBookId
}

OCDIPLAN_OFF_CASE_CONDS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFF_AP_V2 O WHERE O.OFF_CASE_COND_ID = :OFFCASECONDID
}

OCDIPLAN_OFF_ACTION_PLANS_V2_PREINSERT_ {
	SELECT NEXTVAL('OFF_ACTION_PLAN_ID') FROM DUAL
}

OCDIPLAN_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION FROM OMS_MODULES WHERE MODULE_NAME = :V_FORM_NAME
}

OCDIPLAN_GET_STAFF_ID{
	SELECT STAFF_ID, NAME FROM STAFF_MEMBERS_V1 WHERE USER_ID = :P_USER_ID
}

OCDIPLAN_SET_CAS_PLN_DEFAULTS_ {
	SELECT CRP.REVIEW_PERIOD FROM CASE_REVIEW_PERIODS CRP WHERE  CRP.SUPERVISION_LEVEL = :V_SUPERVISION_LEVEL
}

OCDIPLAN_SET_CAS_PLN_DEFAULTS_ {
	SELECT REF_CODE.DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.DOMAIN = :P_DOMAIN AND    REF_CODE.CODE = :P_CODE
}

OCDIPLAN_SET_CAS_PLN_DEFAULTS_ {
	SELECT AL.DESCRIPTION FROM AGENCY_LOCATIONS AL WHERE AL.AGY_LOC_ID = P_AGY_LOC_ID
}

OCDIPLAN_SET_CAS_PLN_DEFAULTS_ {
	SELECT INTAKE_AGY_LOC_ID FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
}

OCDIPLAN_CGFKLKP_CAS_PLN_CAS_PLN_STATU_ {
	SELECT REF_CODE1.CODE ,REF_CODE1.LIST_SEQ FROM   REFERENCE_CODES REF_CODE1 WHERE  (REF_CODE1.DESCRIPTION = :DSPDESCRIPTION OR (REF_CODE1.DESCRIPTION IS NULL AND :DSPDESCRIPTION IS NULL )) AND     (DOMAIN='CASEPLAN_STS' AND (ACTIVE_FLAG = 'Y' OR :MODE <> 'NORMAL'))
}

OCDIPLAN_CGFKLKP_CAS_PLN_CAS_PLN_AL_FK_ {
	SELECT AL.AGY_LOC_ID ,AL.LIST_SEQ FROM   AGENCY_LOCATIONS AL WHERE  (AL.DESCRIPTION = :DSPDESCRIPTION4 OR (AL.DESCRIPTION IS NULL AND :DSPDESCRIPTION4 IS NULL ))
}

OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_AL_FK {
	SELECT AL.LIST_SEQ ,AL.DESCRIPTION FROM   AGENCY_LOCATIONS AL WHERE  AL.AGY_LOC_ID = :instCalAgyLocId
}

OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_STATU {
	SELECT REF_CODE1.DESCRIPTION ,REF_CODE1.LIST_SEQ FROM   REFERENCE_CODES REF_CODE1 WHERE  REF_CODE1.CODE = :casePlanStatus AND     (DOMAIN='CASEPLAN_STS' AND (ACTIVE_FLAG = 'Y' OR '' <> 'NORMAL'))
}

OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_VSM2 {
select
	VSM2.STAFF_NAME
from
	STAFF_MEMBERS_V2 VSM2
where
	
	 VSM2.SAC_STAFF_ID = :instSacStaffId
	and VSM2.CAL_AGY_LOC_ID = :instCalAgyLocId
	and (DATE_TO is null
		or '' <> 'NORMAL')
}

OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_SUPER {
	SELECT REF_CODE.DESCRIPTION ,REF_CODE.LIST_SEQ FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :supervisionLevel AND REF_CODE.DOMAIN = 'SUP_LVL_TYPE'
}

OCDIPLAN_GET_SEQUENCE_NO_ {
	 select coalesce(MAX(DETAIL_SEQ), 0) + 1 from PLAN_DETAILS where OFFENDER_BOOK_ID = :V_OFFENDER_BOOK_ID and CASE_PLAN_ID = :P_CASE_PLAN_ID
}

OCDIPLAN_GET_SEQUENCE_NO_ {
	SELECT COALESCE(MAX(ASSESSMENT_SEQ),0) + 1 FROM   ASSESSMENT_SUMMARIES WHERE  OFFENDER_BOOK_ID = :V_OFFENDER_BOOK_ID AND    CASE_PLAN_ID = :P_CASE_PLAN_ID
}

OCDIPLAN_GET_SEQUENCE_NO_ {
SELECT COALESCE(MAX(CASEWORK_SEQ),0) + 1 FROM   CASEWORK_STEPS WHERE  OFFENDER_BOOK_ID = :V_OFFENDER_BOOK_ID AND    CASE_PLAN_ID = :P_CASE_PLAN_ID
}

OCDIPLAN_CHECK_REVIEW_DATE_ {
	SELECT CRP.REVIEW_PERIOD FROM CASE_REVIEW_PERIODS CRP WHERE CRP.SUPERVISION_LEVEL = V_SUPERVISION_LEVEL
}

OCDIPLAN_GET_CNOTE_ALLUPD_PROFILE_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'CNOTE_ALLUPD'
}

OCDIPLAN_NEXT_REVIEW_DATE_ {
	SELECT  REVIEW_PERIOD FROM  CASE_REVIEW_PERIODS WHERE  SUPERVISION_LEVEL = V_SUP_LEVEL
}

OCDIPLAN_VERIFICATIONVERIFICATION {
	SELECT MAX(WORK_FLOW_ID) INTO WRK_FL_ID FROM WORK_FLOWS WHERE OBJECT_ID  = OFF_BK_ID AND OBJECT_SEQ = V_CASE_PLAN_ID AND OBJECT_CODE = 'CASE_PLANS'
}

OCDIPLAN_VERIFICATIONVERIFICATION {
	SELECT COALESCE(MAX(WORK_FLOW_SEQ),0) + 1 INTO WRK_SEQ FROM WORK_FLOW_LOGS WHERE WORK_FLOW_ID = :WRK_FL_ID
}

OCDIPLAN_INSERT_CASEPLAN_RECORDINSERT_CASEPLAN_RECORD {
	SELECT COALESCE(MAX(CASE_PLAN_ID),0) + 1 FROM CASE_PLANS WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID
}

OCDIPLAN_INSERT_CASEPLAN_RECORDINSERT_CASEPLAN_RECORD {
	SELECT  COUNT(0) FROM  CASE_PLANS WHERE  OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
}

OCDIPLAN_AUTHORIZED_USER_ {
	SELECT SM.STAFF_ID FROM STAFF_MEMBERS SM WHERE SM.USER_ID = upper(user) 

}

OCDIPLAN_AUTHORIZED_USER_ {
SELECT COALESCE(CP.SAC_STAFF_ID,0),COALESCE(CP.INST_SAC_STAFF_ID,0) FROM CASE_PLANS CP WHERE CP.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND CP.CASE_PLAN_STATUS = 'ACTIVE'
}

OCDIPLAN_AUTHORIZED_USER_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'CPLAN_REVIEW'
}

OCDIPLAN_AUTHORIZED_USER_ {
	SELECT COUNT(0) FROM STAFF_MEMBERS      SM, STAFF_MEMBER_ROLES SMR, OMS_ROLES          OMR WHERE SM.USER_ID = USER AND SM.STAFF_ID = SMR.STAFF_ID AND SMR.ROLE_ID = OMR.ROLE_ID AND OMR.ROLE_ID = :V_VALUE
}

OCDIPLAN_DEFAULT_STAFF_ID {
	SELECT SM.STAFF_ID FROM STAFF_MEMBERS SM WHERE SM.USER_ID = :userId
}

OCDIPLAN_AUTHORIZED_USER {
SELECT  COALESCE(CP.SAC_STAFF_ID,0) SAC_STAFF_ID,COALESCE(CP.INST_SAC_STAFF_ID,0) INST_SAC_STAFF_ID FROM CASE_PLANS CP WHERE CP.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND CP.CASE_PLAN_STATUS = 'ACTIVE'
}

OCDIPLAN_DEFAULT_STAFF_VALIDATION_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'CPLAN_REVIEW'
}

OCDIPLAN_DEFAULT_STAFF_VALIDATION_ {
	SELECT COUNT(0) FROM STAFF_MEMBERS      SM, STAFF_MEMBER_ROLES SMR, OMS_ROLES          OMR WHERE SM.USER_ID = USER AND SM.STAFF_ID = SMR.STAFF_ID AND SMR.ROLE_ID = OMR.ROLE_ID AND OMR.ROLE_ID = V_VALUE
}

OCDIPLAN_FETCH_LAST_DATETIME_ {
	SELECT MAX(MODIFY_DATETIME) FROM OFFENDER_CRIMINOGENIC_NEEDS WHERE CASE_PLAN_ID = :CASEPLANID AND OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDIPLAN_GET_STATUS_DESC{
SELECT description
        FROM REFERENCE_CODES
       WHERE domain = 'ACTIVE_TYPE'
         AND code = :code
}
OCDIPLAN_CASE_PLANS_OFFICER_PREINSERT {
	SELECT STAFF.LAST_NAME ,STAFF.FIRST_NAME FROM   STAFF_MEMBERS STAFF WHERE  STAFF_ID = :staffId
}

OCDIPLAN_OFF_CRIM_NEEDS_POSTQUERY_TO_GET_LATEST_DATE {
SELECT MAX(MODIFY_DATETIME) MODIFY_DATETIME
        FROM offender_criminogenic_needs
       WHERE case_plan_id = :casePlanId
         AND offender_book_id = :offenderBookId
}
OCDIPLAN_GET_ROLE_VALUE {
 SELECT profile_value
         FROM system_profiles
        WHERE profile_type = 'CLIENT'
          AND profile_code = 'CPLAN_REVIEW'
}

OCDIPLAN_VERIFY_USERROLE {
SELECT COUNT(0)
        FROM staff_members      sm,
             staff_member_roles smr,
             oms_roles          omr
       WHERE sm.user_id = :userId
         AND sm.staff_id = smr.staff_id
         AND smr.role_id = omr.role_id
         AND omr.role_id = :profileVal
}

OCDIPLAN_WORK_FLOW_ID {
SELECT max(work_flow_id)  workFlowId
        FROM work_flows
       WHERE object_id  = :off_bk_id
         AND object_seq = :v_case_plan_id
         AND object_code = 'CASE_PLANS'
}

OCDIPLAN_WORKFL_FIND_WORK_FLOW_LOGS {
 	select WORK_FLOW_ID , WORK_FLOW_SEQ , WORK_ACTION_CODE , WORK_ACTION_DATE , ACTION_USER_ID , WORK_FLOW_STATUS , CREATE_DATE , LOCATE_AGY_LOC_ID , 
 	( select concat(last_name || ', ' || first_name) from staff_members where user_id = wfl.CREATE_USER_ID) as CREATE_USER_ID, wfl.CREATE_DATETIME , wfl.MODIFY_DATETIME , wfl.MODIFY_USER_ID , wfl.SEAL_FLAG , comment 
 	from WORK_FLOW_LOGS wfl where WORK_FLOW_ID = :workFlowId order by WORK_FLOW_SEQ desc
}

OCDIPLAN_CASPLN_UPDATE_OLD_CASE_PLANS{
UPDATE case_plans
            SET end_date = current_timestamp,
                case_plan_status = 'CLOSED',
                verified_flag = 'Y',
                MODIFY_USER_ID = :modifyUserId,
                MODIFY_DATETIME=current_timestamp
          WHERE offender_book_id = :offenderBookId
            AND case_plan_status = 'ACTIVE'
}

OCDIPLAN_GET_NXT_CASEPLAN_ID{
SELECT COALESCE(MAX(case_plan_id),0) + 1 casePlanId
       FROM case_plans
      WHERE offender_book_id = :offenderBookId

}

OCDIPLAN_CASPLN_INSERT_CASE_PLANS{
INSERT INTO case_plans (offender_book_id
                            , case_plan_id
                            , from_date
                            , position
                            , role
                            , sac_staff_id
                            , cal_agy_loc_id
                            , agy_loc_id
                            , case_plan_status
                            , creation_date
                            , start_date
                            , creation_user
                            , end_date
                            , supervision_level
                            , changes
                            , next_review_date
                            , caseload_type
                            , VERIFIED_FLAG
                            , INST_SAC_STAFF_ID
                            , INST_CAL_AGY_LOC_ID
                            , INST_FROM_DATE
                            , INST_POSITION
                            , INST_ROLE
                            , AUTO_ASSESS_MODIFY_DATETIME
                            , AUTO_CONDITION_MODIFY_DATETIME
                            , CREATE_USER_ID
                            , CREATE_DATETIME
                            , MODIFY_DATETIME)
      						 				VALUES (:offenderBookId
 											          , :casePlanId
            										, :fromDate
            										, :position
            										, :role
            										, :sacStaffId
            										, :calAgyLocId
            										, :agyLocId
            										, 'ACTIVE'
            										, current_timestamp
            										, current_timestamp
            										, :createUserId
            										, null
            										, :supervisionLevel
            										, :changes
            										, :nextReviewDate
            										, :caseloadType
            										, 'Y'
            										, :instSacStaffId
            										, :instCalAgyLocId
            										, :instFromDate
            										, :instPosition
            										, :instRole
            										, :autoAssessModifyDatetime
            										, :autoConditionModifyDatetime
            										, :createUserId
            										, current_timestamp
            										, null)

}
OCDIPLAN_CASPLN_INSERT_CASE_PLANS_NEXT_REVIEW_DATE {
UPDATE case_plans
            SET VERIFIED_FLAG    = 'N',
                MODIFY_USER_ID = :modifyUserId,
                MODIFY_DATETIME=current_timestamp
          WHERE offender_book_id = :offenderBookId
            AND case_plan_status = 'ACTIVE'
}


GET_REVIEW_PERIOD_FROM_CASE_REVIEW_PERIODS {
SELECT REVIEW_PERIOD FROM CASE_REVIEW_PERIODS  WHERE  SUPERVISION_LEVEL = :V_SUPERVISION_LEVEL
}

GET_DESCRIPTION_FROM_REFERENCE_CODES {
SELECT DESCRIPTION FROM   REFERENCE_CODES  WHERE  DOMAIN = :P_DOMAIN  AND   CODE = :P_CODE
}
GET_DESCRIPTION_FROM_AGENCY_LOCATIONS {
    SELECT AL.DESCRIPTION
       FROM AGENCY_LOCATIONS AL
      WHERE AL.AGY_LOC_ID = :P_AGY_LOC_ID
}
GET_INTAKE_AGY_LOC_ID_FROM_V_HEADER_BLOCK {
      SELECT INTAKE_AGY_LOC_ID
        FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK
       WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID
 }
 
GET_OFFENDER_SENT_CONDITIONS_CASEPLAN {
select
	*
from
	offender_sent_conditions osc
where
	offender_book_id = :OFFENDER_BOOK_ID 
	and 'Y' = (select cc.case_plan_flag from community_conditions cc where comm_condition_type= osc.comm_condition_type
	and comm_condition_code = osc.comm_condition_code and category_type = osc.category_type)
}


GET_OFFENDER_SENT_CONDITIONS_CASEPLAN_TEAM_WITH_STAFF{
select (SM.LAST_NAME || ' , ' || SM.FIRST_NAME)  from STAFF_MEMBERS SM where staff_id = (select staff_id from team_staff_members tsm where team_id =:teamId and team_member_id = :officerId)
}

OCDIPLAN_GET_SEQ{
select NEXTVAL('OFF_CRIM_NEED_ID') from dual
}
OCDIPLAN_GET_STAFF_DETAILS
{
select * from case_plan_staff_roles   where  case_plan_id=:casePlanId and offender_book_id=:offenderBookId 
}
OCDIPLAN_GET_STAFF_NAME
{
select concat(last_name || ', ' || first_name) from staff_members where user_id = :userId
}
OCDIPLAN_GET_USER_ID_OF_ASSIGNED_STAFF{
select sm.user_id from oms_owner.case_plan_staff_roles cp,staff_members sm where offender_book_id =:offenderBookId and case_plan_id =:casePlanId and sm.staff_id = cp.staff_id and cp.cn_officer = 'Y'
}

OCDIPLAN_GET_USER_ID_OF_ASSIGNED_STAFF_FOR_CP_OWN{
select sm.user_id from oms_owner.case_plan_staff_roles cp, staff_members sm where offender_book_id =:offenderBookId and case_plan_id =:casePlanId and sm.staff_id = cp.staff_id and cp.cp_owner = 'Y'
}

OCDIPLAN_CASPLN_FIND_CASE_PLAN_DETAILS_MAX_CASEPLAN_ID {
select * from case_plans cp where offender_book_id =:offenderBookId and case_plan_id =:casePlanId
}