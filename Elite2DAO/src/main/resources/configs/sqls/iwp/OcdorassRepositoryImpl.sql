
OCDORASS_FIND_RGAGYLOCID {
SELECT  AGY_LOC2.DESCRIPTION    DESCRIPTION ,AGY_LOC2.AGY_LOC_ID     CODE FROM AGENCY_LOCATIONS AGY_LOC2 WHERE EXISTS (SELECT 'X' FROM CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.CASELOAD_ID = :CASELOADID AND CAL.AGY_LOC_ID = AGY_LOC2.AGY_LOC_ID) ORDER BY AGY_LOC2.DESCRIPTION, AGY_LOC2.AGY_LOC_ID
}

OCDORASS_FIND_RGPOSITION {
SELECT CODE , DESCRIPTION     FROM REFERENCE_CODES    WHERE     DOMAIN = 'STAFF_POS' ORDER BY LIST_SEQ , CODE , DESCRIPTION
}

OCDORASS_FIND_RGROLE {
 	SELECT CODE , DESCRIPTION     FROM REFERENCE_CODES    WHERE     DOMAIN = 'STAFF_ROLE' ORDER BY LIST_SEQ , CODE , DESCRIPTION
}

OCDORASS_FIND_RGSCHED ULETYPE {
 	SELECT CODE        ,DESCRIPTION  FROM   REFERENCE_CODES SCHEDULE_TYPE WHERE   DOMAIN = 'SCHEDULE_TYP' ORDER BY LIST_SEQ , CODE , DESCRIPTION
}

OCDORASS_FIND_RGSEXCODE {
 	SELECT CODE , DESCRIPTION  FROM   REFERENCE_CODES WHERE   DOMAIN = 'SEX'
}

OCDORASS_FIND_RGTEAM {
SELECT DESCRIPTION , team_id AS CODE FROM TEAMS WHERE ACTIVE_FLAG = 'Y' AND TEAM_ID IN (SELECT TM.TEAM_ID FROM TEAM_MEMBERS TM , TEAM_FUNCTIONS TF , STAFF_LOCATION_ROLES SLR WHERE TM.STAFF_ID = :STAFFID AND TM.ACTIVE_FLAG = 'Y' AND TF.FUNCTION_TYPE = 'OM' AND TF.TEAM_ID = TM.TEAM_ID AND TM.STAFF_ID = SLR.SAC_STAFF_ID AND TM.AGY_LOC_ID = SLR.CAL_AGY_LOC_ID AND TM.LOC_ROLE_FROM_DATE = SLR.FROM_DATE AND TM.POSITION = SLR.POSITION AND TM.ROLE = SLR.ROLE AND (TM.POSITION = :POSITION OR :POSITION IS NULL ) AND (TM.ROLE = :ROLE OR :ROLE IS NULL ) ) 
}

OCDORASS_OFFBKG1_FIND_OFFENDER_BOOKINGS {
 SELECT * FROM OFFENDER_BOOKINGS WHERE (OFFENDER_BOOKINGS.BOOKING_STATUS = 'O' AND OFFENDER_BOOKINGS.COMMUNITY_ACTIVE_FLAG = 'Y' AND OFFENDER_BOOKINGS.INTAKE_AGY_LOC_ID = :INTAKEAGYLOCID AND NOT EXISTS ( SELECT '1' FROM CASE_PLANS CP WHERE CP.OFFENDER_BOOK_ID = OFFENDER_BOOKINGS.OFFENDER_BOOK_ID AND CP.SAC_STAFF_ID IS NOT NULL AND CP.END_DATE IS NULL)) ORDER BY BOOKING_BEGIN_DATE ASC 
}
OCDORASS_OFFBKG1_UPDATE_OFFENDER_BOOKINGS {
    update OFFENDER_BOOKINGS set OFFENDER_BOOK_ID = :offenderBookId , BOOKING_BEGIN_DATE = :bookingBeginDate , BOOKING_END_DATE = :bookingEndDate , BOOKING_NO = :bookingNo , OFFENDER_ID = :offenderId , AGY_LOC_ID = :agyLocId , LIVING_UNIT_ID = :livingUnitId , DISCLOSURE_FLAG = :disclosureFlag , IN_OUT_STATUS = :inOutStatus , ACTIVE_FLAG = :activeFlag , BOOKING_STATUS = :bookingStatus , YOUTH_ADULT_CODE = :youthAdultCode , FINGER_PRINTED_STAFF_ID = :fingerPrintedStaffId , SEARCH_STAFF_ID = :searchStaffId , PHOTO_TAKING_STAFF_ID = :photoTakingStaffId , ASSIGNED_STAFF_ID = :assignedStaffId , CREATE_AGY_LOC_ID = :createAgyLocId , BOOKING_TYPE = :bookingType , BOOKING_CREATED_DATE = :bookingCreatedDate , ROOT_OFFENDER_ID = :rootOffenderId , AGENCY_IML_ID = :agencyImlId , SERVICE_FEE_FLAG = :serviceFeeFlag , EARNED_CREDIT_LEVEL = :earnedCreditLevel , EKSTRAND_CREDIT_LEVEL = :ekstrandCreditLevel , INTAKE_AGY_LOC_ID = :intakeAgyLocId , ACTIVITY_DATE = :activityDate , INTAKE_CASELOAD_ID = :intakeCaseloadId , INTAKE_USER_ID = :intakeUserId , CASE_OFFICER_ID = :caseOfficerId , CASE_DATE = :caseDate , CASE_TIME = :caseTime , COMMUNITY_ACTIVE_FLAG = :communityActiveFlag , CREATE_INTAKE_AGY_LOC_ID = :createIntakeAgyLocId , COMM_STAFF_ID = :commStaffId , COMM_STATUS = :commStatus , COMMUNITY_AGY_LOC_ID = :communityAgyLocId , NO_COMM_AGY_LOC_ID = :noCommAgyLocId , COMM_STAFF_ROLE = :commStaffRole , AGY_LOC_ID_LIST = :agyLocIdList , STATUS_REASON = :statusReason , TOTAL_UNEXCUSED_ABSENCES = :totalUnexcusedAbsences , REQUEST_NAME = :requestName , RECORD_USER_ID = :recordUserId , INTAKE_AGY_LOC_ASSIGN_DATE = :intakeAgyLocAssignDate , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where OFFENDER_BOOK_ID = :offenderBookId
}

OCDORASS_EXTOT_FIND_EXT_OWNERSHIP_TRANSFER {
    SELECT * FROM  EXT_OWNERSHIP_TRANSFER 
}
OCDORASS_EXTOT_UPDATE_EXT_OWNERSHIP_TRANSFER {
    update EXT_OWNERSHIP_TRANSFER set OFFENDER_BOOK_ID =:offenderBookId, EXT_TRANSFER_ID =:extTransferId, AGY_LOC_ID_FROM =:agyLocIdFrom, AGY_LOC_ID_TO =:agyLocIdTo, TRANSFER_DATE =:transferDate, ASS_STAFF_ID =:assStaffId, TRANSFER_FLAG =:transferFlag, PTR_ID =:ptrId, MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID =:modifyUserId where OFFENDER_BOOK_ID =:offenderBookId and EXT_TRANSFER_ID =:extTransferId 
}

OCDORASS_VOFFDET_FIND_V_OM_TEAM_MEMBERS {
SELECT staff_id,staff_name,position,role,sex_code,schedule_type, staff_loc_role_id,cal_agy_loc_id,from_date, ( SELECT count(*) FROM teams WHERE ACTIVE_FLAG = 'Y' AND TEAM_ID IN ( SELECT TM.TEAM_ID FROM TEAM_MEMBERS TM, TEAM_FUNCTIONS TF, STAFF_LOCATION_ROLES SLR WHERE TM.STAFF_ID = v_om_team_members.staff_id AND TM.ACTIVE_FLAG = 'Y' AND TF.FUNCTION_TYPE = 'OM' AND TF.TEAM_ID = TM.TEAM_ID AND TM.STAFF_ID = SLR.SAC_STAFF_ID AND TM.AGY_LOC_ID = SLR.CAL_AGY_LOC_ID AND TM.LOC_ROLE_FROM_DATE = SLR.FROM_DATE AND TM.POSITION = SLR.POSITION AND TM.ROLE = SLR.ROLE AND ( TM.POSITION = v_om_team_members.position OR v_om_team_members.position IS NULL ) AND ( TM.ROLE = v_om_team_members.role OR v_om_team_members.role IS NULL ))) as count FROM v_om_team_members v_om_team_members 
}

OCDORASS_OFFBKG1_OFFENDER_BOOKINGS_POST_QUERY {
 select off_name.offender_id, off_name.offender_id_display, off_name.last_name, off_name.first_name from offenders off_name, offender_bookings off_bkg where off_name.root_offender_id = :ROOTOFFENDERID and off_bkg.offender_id = off_name.offender_id order by case when off_bkg.MODIFY_DATETIME is null then TO_DATE('11/11/1111', 'DD/MM/YYYY') else off_bkg.MODIFY_DATETIME end desc 
}

OCDORASS_EXTOT_GET_COUNT_CASE_PLANS {
 SELECT COUNT (*)  FROM CASE_PLANS  WHERE CAL_AGY_LOC_ID =:CAL_AGY_LOC_ID  AND FROM_DATE =:FROM_DATE AND POSITION =:POSITION AND ROLE =:ROLE AND SAC_STAFF_ID =:SAC_STAFF_ID AND END_DATE IS NULL
}
   
OCDORASS_EXTOT_GET_LAST_NAME_FIRST_NAME {
 SELECT LAST_NAME DSP_LAST_NAME, FIRST_NAME DSP_FIRST_NAME FROM STAFF_MEMBERS  WHERE STAFF_ID = :STAFFID
 }
 OCDORASS_EXTOT_GET_AGY_LOC_DESC {
SELECT TAG_ESTABLISHMENT_GET_AGY_LOC_DESC (:AGYLOCIDFROM) FROM DUAL
}
 OCDORASS_EXTOT_GET_POST_QUERY {
 SELECT OFF.ROOT_OFFENDER_ID, OFF.OFFENDER_ID, OFF.OFFENDER_ID_DISPLAY, OFF.LAST_NAME, OFF.FIRST_NAME FROM OFFENDERS OFF, OFFENDER_BOOKINGS OFF_BKG WHERE OFF_BKG.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND OFF_BKG.OFFENDER_ID = OFF.OFFENDER_ID
}
OCDORASS_GET_COUNT_TEAM_MEMBERS {
SELECT COUNT(1) FROM team_staff_members WHERE staff_id = :staffid AND active_flag = 'Y' AND team_id IN ( SELECT t.team_id FROM automation_teams t, team_functions tf WHERE t.active_flag = 'Y' AND t.team_id = tf.team_id AND tf.function_type = 'OM' )
 }

 OCDORASS_GET_COUNT_TWO_TEAM_MEMBERS {
 SELECT t.team_id FROM team_staff_members t, team_functions tf WHERE t.team_id = tf.team_id AND tf.function_type = 'OM' AND staff_id = :staffid
 }
 OCDORASS_GET_TEAM_CODE_DESCRIPTION {
 SELECT TEAM_CODE, DESCRIPTION  FROM automation_teams WHERE TEAM_ID = :TEAMID
  }
  
 
 OCDORASS_GET_OM_TEAM_MAND {       
 SELECT OMS_MISCELLANEOUS_GET_PROFILE_VALUE('CLIENT','TEAM_REQ') FROM DUAL       
 }
 OCDORASS_GET_COUNT_CASE_PLANS{
  SELECT COUNT(0) FROM  CASE_PLANS WHERE  OFFENDER_BOOK_ID = :OFFENDERBOOKID
  }
OCDORASS_GET_REVIEW_PERIOD_CASE_REVIEW_PERIODS {
  SELECT CRP.REVIEW_PERIOD  FROM CASE_REVIEW_PERIODS CRP  WHERE CRP.SUPERVISION_LEVEL = :VSUPERVISIONLEVEL
}

OCDORASS_GET_MAX_CASE_PLAN_ID {
   SELECT coalesce( MAX( CASE_PLAN_ID ), 0) FROM CASE_PLANS   WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
}
  
OCDORASS_GET_FROM_DATE_STAFF_LOCATION_ROLES {
    SELECT FROM_DATE FROM STAFF_LOCATION_ROLES  WHERE SAC_STAFF_ID =:STAFFID  AND CAL_AGY_LOC_ID = :CALAGYLOCID AND POSITION =:POSITION AND ROLE =:ROLE AND TO_DATE IS NULL
}
 
OCDORASS_GET_UPDATE_CASE_PLANS {
        update CASE_PLANS set CASE_PLAN_STATUS = 'CLOSED', END_DATE = current_date, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFFENDER_BOOK_ID = :offenderBookId 
}
   
OCDORASS_GET_CREATION_USER_CASE_PLANS {
   SELECT CREATION_USER FROM CASE_PLANS  WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID  AND CASE_PLAN_ID = :CASEPLANID
}

OCDORASS_GET_INST_INFO_CASE_PLANS {
   SELECT CP.INST_FROM_DATE ,CP.INST_POSITION,CP.INST_ROLE,CP.INST_SAC_STAFF_ID   ,CP.INST_CAL_AGY_LOC_ID   ,CP.AUTO_ASSESS_MODIFY_DATETIME,CP.AUTO_CONDITION_MODIFY_DATETIME  FROM   CASE_PLANS CP  WHERE CP.OFFENDER_BOOK_ID = :OFFENDERBOOKID  AND CP.CASE_PLAN_ID = :CASEPLANID
}
   
OCDORASS_INSERT_CASE_PLANS {
insert
	into
	CASE_PLANS (OFFENDER_BOOK_ID,
	CASE_PLAN_ID,
	FROM_DATE,
	position,
	role,
	SAC_STAFF_ID,
	CAL_AGY_LOC_ID ,
	AGY_LOC_ID ,
	CASE_PLAN_STATUS ,
	CREATION_DATE ,
	CREATION_USER ,
	END_DATE ,
	SUPERVISION_LEVEL,
	CHANGES,
	NEXT_REVIEW_DATE ,
	START_DATE ,
	INST_FROM_DATE ,
	INST_POSITION ,
	INST_ROLE ,
	INST_SAC_STAFF_ID ,
	INST_CAL_AGY_LOC_ID ,
	AUTO_ASSESS_MODIFY_DATETIME ,
	AUTO_CONDITION_MODIFY_DATETIME ,
	VERIFIED_FLAG ,
	create_user_id,
	create_datetime,
	modify_datetime)
values (:offenderBookId ,
:casePlanId,
:fromDate ,
:position ,
:role ,
:staffId ,
:calAgyLocId ,
:agyLocId ,
:casePlanStatus,
current_date,
:createUserId ,
null ,
:vSupervisionLevel,
case
	:casePlanId when 0 then null
	else 'Re-Assigned'
end,
:vNextReviewDate ,
current_date ,
 (select inst_from_date  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
(select inst_position  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
(select inst_role  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
(select inst_sac_staff_id  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
(select inst_cal_agy_loc_id  from case_plans where offender_book_id =:offenderBookId and case_plan_id =(select max(case_plan_id) from case_plans where offender_book_id =:offenderBookId)) ,
:vAutoAssessModify ,
current_timestamp ,
:verifiedFlag,
:createUserId,
current_timestamp,
null)

}
 
OCDORASS_SELECT_OFFENDER_WORK_ASSIGNMENTS {
 SELECT OFF_WORK.FROM_DATE ,OFF_WORK.POSITION ,OFF_WORK.ROLE,OFF_WORK.SAC_STAFF_ID ,OFF_WORK.CAL_AGY_LOC_ID,OFF_WORK.STATUS,OFF_WORK.AGY_LOC_ID ,OFF_WORK.OFFASS_ID FROM OFFENDER_WORK_ASSIGNMENTS OFF_WORK WHERE OFF_WORK.AGY_LOC_ID = :AGYLOCID AND ( OFFENDER_BOOK_ID = :OFFENDERBOOKID OR OFFENDER_BOOK_ID_REQUEST = :OFFENDERBOOKID )
}
  
OCDORASS_GET_PREVIOUS_OFFENDER_WORK_ASSIGNMENTS {
  SELECT OWA.FROM_DATE ,OWA.POSITION ,OWA.ROLE ,OWA.SAC_STAFF_ID ,OWA.CAL_AGY_LOC_ID,OWA.STATUS ,OWA.AGY_LOC_ID ,OWA.OFFASS_ID FROM OFFENDER_WORK_ASSIGNMENTS OWA WHERE OWA.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND OWA.CREATION_DATE =( SELECT MAX(OWA1.CREATION_DATE)  FROM OFFENDER_WORK_ASSIGNMENTS OWA1 WHERE OWA1.OFFENDER_BOOK_ID = OWA.OFFENDER_BOOK_ID) ORDER BY  OWA.CREATION_DATE DESC
 }   
 
OCDORASS_GET_ASSTRA_SEQ_NEXTVAL {
 SELECT NEXTVAL('ASSTRA_SEQ')
 }
 
OCDORASS_INSERT_ASSIGNMENT_TRANSFERS {
  insert into ASSIGNMENT_TRANSFERS (ASSTRA_ID, FROM_DATE_FROM, POSITION_FROM, ROLE_FROM, SAC_STAFF_ID_FROM, CAL_AGY_LOC_ID_FROM, TRANSFER_DATE , TRANSFER_DATE_TO, CREATION_DATE, CREATION_USER, OFFASS_ID, create_user_id, create_datetime, modify_datetime) values (:asstraId, :fromDate, :position , :role , :staffId , :calAgyLocId, current_date, current_date, current_date, :createUserId , :vOffassId, :createUserId, current_timestamp, null )   
}
  OCDORASS_UPDATE_ASSIGNMENT_TRANSFERS {
    update OFFENDER_WORK_ASSIGNMENTS OFFASS set OFFASS.FROM_DATE = :fromDate , OFFASS.POSITION = :position, OFFASS.ROLE =:role, OFFASS.SAC_STAFF_ID = :staffId , OFFASS.CAL_AGY_LOC_ID = :calAgyLocId, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFFASS.OFFASS_ID = :asstraId 
   }
   
OCDORASS_UPDATE_EXT_OWNERSHIP_TRANSFER {
      update EXT_OWNERSHIP_TRANSFER set TRANSFER_FLAG = 'Y', modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFFENDER_BOOK_ID = :offenderBookId 
}
   
OCDORASS_COUNT_OFFENDER_BOOKING_AGY_LOCS {
   SELECT coalesce( COUNT( OFFENDER_BOOK_ID ),0 ) FROM OFFENDER_BOOKING_AGY_LOCS OBAL WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID  AND AGY_LOC_ID = :AGYLOCID  AND REMOVED_DATE IS NULL
}
   
OCDORASS_INSERT_INTO_OFFENDER_BOOKING_AGY_LOCS {
      insert into OFFENDER_BOOKING_AGY_LOCS(CASELOAD_ID , AGY_LOC_ID , OFFENDER_BOOK_ID , ADDITION_DATE , ADDITION_TIME , REASON_CODE , REMOVED_REASON_CODE , REMOVED_DATE , ADDITION_COMMENT , REMOVAL_COMMENT, create_user_id, create_datetime, modify_datetime) values ( :intakeCaseloadId , :agyLocId , :offenderBookId , current_timestamp , current_timestamp , 'TRANS_IN' , null , null , null , null , :createUserId, current_timestamp, null) 
}
   
OCDORASS_UPDATE_OFFENDER_BOOKING_AGY_LOCS {
     update OFFENDER_BOOKING_AGY_LOCS set REMOVED_DATE = current_date, REMOVED_REASON_CODE = 'TRANS_OUT', modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFFENDER_BOOK_ID =:offenderBookId and AGY_LOC_ID = :agyLocId and REMOVED_DATE is null 
}
   
OCDORASS_GET_EVENT_SEQ_OFFENDER_BOOKINGS_EVENTS {
   SELECT coalesce(MAX(EVENT_SEQ)+1,1)   FROM OFFENDER_BOOKING_EVENTS  WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
}
   
OCDORASS_INSERT_INTO_OFFENDER_BOOKING_EVENTS  {
      insert into OFFENDER_BOOKING_EVENTS (OFFENDER_BOOK_ID , EVENT_SEQ, BOOKING_EVENT_CODE, REASON_CODE, FROM_AGY_LOC_ID, TO_AGY_LOC_ID, COMMENT_TEXT , EVENT_DATE , EVENT_TIME, EVENT_USER, CREATION_USER, CREATION_DATE, create_user_id, create_datetime, modify_datetime ) values ( :offenderBookId , :eventSeq , 'TRANSFER' , 'TRANSFER_PO' , :agyLocIdFrom, :agyLocIdTo , null , current_timestamp , current_timestamp, :createUserId, :createUserId , current_date, :createUserId, current_timestamp, null) 
}
   
OCDORASS_COUNT_OFFENDER_BOOKING_AGY_LOCS {
    SELECT coalesce (COUNT (offender_book_id), 0) FROM offender_booking_agy_locs obal WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID  AND AGY_LOC_ID = :AGYLOCID AND removed_date IS NULL
}
         
UPDATE_OFFENDER_BOOKING_AGY_LOCS {
   update offender_booking_agy_locs set removed_date = current_date, removed_reason_code = 'TRANS_OUT', addition_comment = 'OM Assigned', removal_comment = 'OM Assigned', modify_user_id = :modifyUserId, modify_datetime = current_timestamp where offender_book_id = :offenderBookId and removed_date is null 
}
   
OCDORASS_SELECT_OFFENDER_WORK_ASSIGNMENTS_OFF_BKG_COMMIT {
 SELECT OWA.FROM_DATE ,OWA.POSITION ,OWA.ROLE ,OWA.SAC_STAFF_ID ,OWA.CAL_AGY_LOC_ID ,OWA.STATUS ,OWA.AGY_LOC_ID ,OWA.OFFASS_ID FROM OFFENDER_WORK_ASSIGNMENTS OWA WHERE OWA.OFFENDER_BOOK_ID = :offenderBookId AND OWA.CREATION_DATE = ( SELECT MAX(OWA1.CREATION_DATE) FROM OFFENDER_WORK_ASSIGNMENTS OWA1 WHERE OWA1.OFFENDER_BOOK_ID = OWA.OFFENDER_BOOK_ID) ORDER BY OWA.CREATION_DATE DESC 
}
   
GET_PROFILE_VALUE_SYSTEM_PROFILES {
         SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'FILE_TRANS' AND PROFILE_CODE = 'FILE'
}
   
GET_OFFENDER_FILE_SEQ_OFFENDER_COMMUNITY_FILES {
 SELECT offender_file_seq , non_officer_status FROM offender_community_files WHERE offender_id = :OFFENDERID AND caseload_type = 'COMM' AND file_sub_type IN ( 'AUTO_CREATED' ) 
}
        
GET_UPDATE_OFFENDER_COMMUNITY_FILES {     
 update offender_community_files set non_officer_status = null, holding_staff_id = :staffId, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where offender_id = :offenderId and offender_file_seq = :offenderFileSeq
   }
   
SELECT_GET_OFFENDER_ID_OFFENDER_BOOKINGS {
    SELECT OFF_NAME.offender_id FROM offender_bookings OFF_BKG ,offenders OFF_NAME WHERE OFF_BKG.offender_book_id = :OFFENDERBOOKID  AND OFF_BKG.root_offender_id = OFF_NAME.root_offender_id  AND OFF_NAME.alias_offender_id IS NULL
   }
   
SELECT_GET_DETAILS_OFFENDER_COMMUNITY_FILES {
  SELECT OFFENDER_FILE_SEQ,HOLDING_AGY_LOC_ID ,HOLDING_STAFF_ID,STORAGE,NON_OFFICER_STATUS,OFFENDER_FILE_NUM FROM OFFENDER_COMMUNITY_FILES  WHERE OFFENDER_ID = :OFFENDERID
}
     
GET_UPDATE_OFFENDER_COMMUNITY_FILES_TWO{ 
 update offender_community_files set holding_agy_loc_id = :agyLocIdTo , holding_staff_id = :assStaffId, non_officer_status = null, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where offender_id = :offenderId and offender_file_seq = :offenderFileSeq 
}
 
 GET_CGNBT_SKILL_SUB_TYPE_V_OM_TEAM_MEMBERS{
   SELECT 'Y' FROM assignment_transfers asst, offender_work_assignments owass WHERE asst.offass_id = owass.offass_id AND ( owass.offender_book_id IN ( SELECT b.offender_book_id FROM offender_bookings a, offender_bookings b WHERE a.root_offender_id = b.root_offender_id AND a.offender_book_id = asst.offender_book_id ) OR owass.offender_book_id_request IN ( SELECT b.offender_book_id FROM offender_bookings a, offender_bookings b WHERE a.root_offender_id = b.root_offender_id AND a.offender_book_id = asst.offender_book_id ) ) AND asst.sac_staff_id = :staffid UNION SELECT 'Y' FROM case_plans WHERE offender_book_id IN ( SELECT b.offender_book_id FROM offender_bookings a, offender_bookings b WHERE a.root_offender_id = b.root_offender_id AND a.offender_book_id = a.offender_book_id ) AND sac_staff_id =:staffid 
 } 
    
 OCDORASS_GET_CASE_OFFICER_ID {
SELECT CASE_OFFICER_ID FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
 }
OCDORASS_GET_CASE_LOV_DISABLE {
 SELECT  team_id::text CODE , DESCRIPTION  FROM TEAMS 
}
   
GET_PROFILE_VALUE_SYSTEM_PROFILES_POST_QUERY {
         SELECT * FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'IN_STAFF_QRY'
}

OCDORASS_GET_CASE_LOV_DISABLE {
 SELECT staff_id,staff_name,position,role,sex_code,schedule_type,cal_agy_loc_id,from_date, ( SELECT count(*) FROM teams WHERE ACTIVE_FLAG = 'Y' AND TEAM_ID IN ( SELECT TM.TEAM_ID FROM TEAM_MEMBERS TM, TEAM_FUNCTIONS TF, STAFF_LOCATION_ROLES SLR WHERE TM.STAFF_ID = v_om_team_members.staff_id AND TM.ACTIVE_FLAG = 'Y' AND TF.FUNCTION_TYPE = 'OM' AND TF.TEAM_ID = TM.TEAM_ID AND TM.STAFF_ID = SLR.SAC_STAFF_ID AND TM.AGY_LOC_ID = SLR.CAL_AGY_LOC_ID AND TM.LOC_ROLE_FROM_DATE = SLR.FROM_DATE AND TM.POSITION = SLR.POSITION AND TM.ROLE = SLR.ROLE AND ( TM.POSITION = v_om_team_members.position OR v_om_team_members.position IS NULL ) AND ( TM.ROLE = v_om_team_members.role OR v_om_team_members.role IS NULL ))) as count FROM v_om_team_members v_om_team_members 
}    
   
OFFENDER_AWAITING_LIST_POST_QUERY{
SELECT OFF_NAME.OFFENDER_ID, OFF_NAME.OFFENDER_ID_DISPLAY, OFF_NAME.LAST_NAME, OFF_NAME.FIRST_NAME FROM OFFENDERS OFF_NAME, OFFENDER_BOOKINGS OFF_BKG WHERE OFF_NAME.ROOT_OFFENDER_ID = :rootOffenderId AND OFF_BKG.OFFENDER_ID = OFF_NAME.OFFENDER_ID ORDER BY CASE   WHEN OFF_BKG.MODIFY_DATETIME is null THEN  TO_DATE('11/11/1111', 'DD/MM/YYYY')  ELSE OFF_BKG.MODIFY_DATETIME END desc
}

GET_OFF_BKGS_OLD_REC{
 select * from OFFENDER_BOOKINGS where OFFENDER_BOOK_ID = :offenderBookId
}
 
EXECUTING_OMTOFSB_TRIGGER{
  DELETE FROM offender_files_trig
}
OCDORASS_GET_FEE_OVERRIDES{
SELECT OFAO.* FROM OFF_FEE_ACCOUNT_OVERRIDES OFAO, OFF_FEE_ACCOUNT_PROFILE OFAP WHERE OFAP.OFFENDER_BOOK_ID = :offenderBookId AND OFAP.OFFENDER_FEE_ID = OFAO.OFFENDER_FEE_ID
AND TO_DATE(OFAO.OVERRIDE_END_DATE) >= TO_DATE(SYSDATE)
}

OCDORASS_GET_OFFENDER_ID{
SELECT OFFENDER_ID FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :offenderBookId
}
OCDORASS_CHECK_FEE_ACCOUNT_STATUS{
SELECT COUNT(*)  FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_BOOK_ID = :offenderBookId AND CASELOAD_ID = :caseLoadId
}

OCDORASS_CHECK_SUPV_ACCOUNT{
select count(*) from off_fee_account_profile ofap where offender_fee_id = :offenderFeeId and fee_code in 
(select deduction_type from CASELOAD_DEDUCTION_PROFILES where non_billable_status = 'Y' )
}

OCDORASS_GET_ACTIVE_FEE_OVERRIDE_AT_RECEIVE_COUNTY{
SELECT
    OFAP.OFFENDER_FEE_ID,
    (
        CASE
            WHEN (
                SELECT
                    COUNT(*)
                FROM
                    OFF_FEE_ACCOUNT_OVERRIDES OFAO
                WHERE
                        OFAO.OFFENDER_FEE_ID = OFAP.OFFENDER_FEE_ID
                    AND ( OFAO.OVERRIDE_END_DATE IS NULL
                          OR TO_DATE(OFAO.OVERRIDE_END_DATE) >= TO_DATE(SYSDATE) )
            ) > 0 THEN
                'Y'
            ELSE
                'N'
        END
    ) FEE_ACT_OVERRIDE_STATUS
FROM
    OFF_FEE_ACCOUNT_PROFILE OFAP
WHERE
        OFAP.OFFENDER_BOOK_ID = :offenderBookId
    AND OFAP.FEE_ACT_STATUS = 'A'
    AND OFAP.CASELOAD_ID = :agyLocIdTo
    AND OFAP.FEE_CODE IN (
        SELECT
            CDP.DEDUCTION_TYPE
        FROM
            CASELOAD_DEDUCTION_PROFILES CDP
        WHERE
                CDP.NON_BILLABLE_STATUS = 'Y'
            AND CDP.CASELOAD_ID = :agyLocIdTo
    )
}

GET_OFF_TIER_LEVEL_WORKLOAD_UNITS{
SELECT ( SELECT WORKLOAD_VALUE FROM MAINTAIN_TIER_LEVELS WHERE TIER_LEVEL_CODE = SA.TIER_LEVEL_CODE )UNITS FROM OFFENDER_TIER_LEVELS SA WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID AND ACTIVE_FLAG ='Y'
}

OCDORASS_GET_WORKED_STAFF_MEMBERS {
select asst.sac_staff_id from assignment_transfers asst, offender_work_assignments owass where asst.offass_id = owass.offass_id and ( owass.offender_book_id in ( select b.offender_book_id from offender_bookings a, offender_bookings b where a.root_offender_id = b.root_offender_id and a.offender_book_id = asst.offender_book_id ) or owass.offender_book_id_request in ( select b.offender_book_id from offender_bookings a, offender_bookings b where a.root_offender_id = b.root_offender_id and a.offender_book_id = :offenderBookId ) ) union select sac_staff_id from case_plans where offender_book_id in ( select b.offender_book_id from offender_bookings a, offender_bookings b where a.root_offender_id = b.root_offender_id and a.offender_book_id = :offenderBookId)
}   

OCDORASS_GET_OFFENDER_CONDITION{
select
			A.CON_TRANSFER_ID,
			A.OFFENDER_BOOK_ID,
			A.OFFENDER_SENT_CONDITION_ID,
			A.STAFF_ID,
			A.TEAM_ID,
			A.TEAM_MEMBER_ID,
			A.AGY_LOC_ID,
			A.TO_TEAM_ID,
			A.TO_AGY_LOC_ID,
			A.CONDI_STATUS,
			A.CREATE_DATETIME,
			A.CREATE_USER_ID,
			A.MODIFY_DATETIME,
			A.MODIFY_USER_ID,
			A.SEAL_FLAG,
			A.TO_STAFF_ID,
			A.TO_TEAM_MEMBER_ID,
			A.PARENT_CON_TRANSFER_ID,
			A.SENTENCE_SEQ,
			A.RCVD_FROM_LOC as RECEIVED_FROM_LOCATION,
			A.RCVD_FROM_TEAM as RECEIVED_FROM_TEAM
		from
			(
			select
				MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID,
				OFFENDER_BOOK_ID,
				OFFENDER_SENT_CONDITION_ID
			from
				OFFENDER_COND_TRANSFER
			group by
				OFFENDER_BOOK_ID,
				OFFENDER_SENT_CONDITION_ID) B,
			OFFENDER_COND_TRANSFER A
		where
			A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID
			and A.OFFENDER_SENT_CONDITION_ID = B.OFFENDER_SENT_CONDITION_ID
			and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID
			and A.OFFENDER_BOOK_ID = :offenderBookId;
}



