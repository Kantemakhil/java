GET_CATEGORY {
	SELECT description, code
	FROM REFERENCE_CODES
	WHERE domain = 'COM_CON_CAT' 
	AND ((active_flag = 'Y' AND expired_date IS NULL))
	ORDER BY LIST_SEQ, CODE
}

FETCH_ORDER_TYPE_DESC {
select oms_miscellaneous_getdesccode('CATEGORY',:conditionType) from dual 
}

GET_UNIT {
  SELECT description, code
  FROM reference_codes
  WHERE domain = 'COND_UNIT'
  AND ((active_flag = 'Y' AND expired_date IS NULL))
  ORDER BY list_seq,description,code
}

GET_PROGRAM {
	SELECT description, program_code code, PROGRAM_ID id
	FROM   program_services
	WHERE  program_category = 'ACP'
	AND    program_class = 'PRG'
	AND active_flag = 'Y'
	ORDER BY list_seq,description, code
}

GET_CONDITION_TYPE_GRID {
select SENTENCE_SEQ, COMM_CONDITION_TYPE, COMM_CONDITION_CODE, START_DATE, STATUS_DATE, OFFENDER_BOOK_ID, EXPIRY_DATE, LIST_SEQ, COMMENT_TEXT, CURFEW_START_TIME, CURFEW_END_TIME, CONDITION_RECOMMENDED_FLAG, GOVERNOR_CONDITION_FLAG, LENGTH, LENGTH_UNIT, DETAILS_TEXT, OFFENDER_SENT_CONDITION_ID, RESIDENCY_ADDRESS_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, CONDITION_REQUIRED_FLAG, CONDITION_APPLIED_FLAG, LONG_COMMENT_TEXT, APPOINTMENT_PERSON_NAME, REVIEW_CODE, SUPERVISOR_NAME, REPORT_TIME, REPORT_DATE, PERSONAL_RELATIONSHIP_FLAG, VEHICLE_DETAILS_FLAG, NON_ASSOCIATED_OFFENDERS, DRUG_TESTING, TERMINATION_DATE, STATUS_REASON_CODE, NO_RESIDENT_UNDER_AGE_OF, PROHIBITED_CONTACT, RESTRICTED_CHILD_AGE_OF, RESTRICTED_APPROVAL_PERSON, CURFEW_TAGGING_FLAG, OTHER_PROGRAM, NO_WORK_WITH_UNDER_AGE, NO_WORK_WITH_UNDER_AGE_OF, NO_ACCESS_TO_INTERNET, NO_USER_OF_COMPUTER, STATUS_UPDATE_REASON, STATUS_UPDATE_COMMENT, STATUS_UPDATE_DATE, STATUS_UPDATE_STAFF_ID, WORKFLOW_ID, PROGRAM_ID, ACTIVITY_CODE, COND_ACT_TYPE, ACTIVITY_STATUS, CATEGORY_TYPE, NON_ASSOCIATION_TEXT, FINANCIAL_TOTAL_AMOUNT, SEAL_FLAG, OBJECT_ID, OBJECT_TYPE, BOARD_ORDER_FLAG, CONDITION_STATUS, Tag_Licence_get_requirement(osc.COMM_CONDITION_CODE, osc.COMM_CONDITION_TYPE, osc.CATEGORY_TYPE) requirement, (select (case when A.staff_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = A.staff_id ) when A.team_member_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = (select tsm.staff_id from team_staff_members tsm where tsm.team_member_id = A.team_member_id) ) when A.to_staff_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = A.to_staff_id ) when A.to_team_member_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = (select tsm.staff_id from team_staff_members tsm where tsm.team_member_id = A.to_team_member_id) ) end ) as assigned_officer from ( select MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID from OFFENDER_COND_TRANSFER group by OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID) B, OFFENDER_COND_TRANSFER A where A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.OFFENDER_SENT_CONDITION_ID = osc.OFFENDER_SENT_CONDITION_ID and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID and A.OFFENDER_BOOK_ID = osc.offender_book_id ) as assigned_officer, ( select program_code from program_services where program_id = osc.PROGRAM_ID) as program, ( select program_method from community_conditions where comm_condition_type = osc.COMM_CONDITION_TYPE and category_type = osc.CATEGORY_TYPE and COMM_CONDITION_CODE = osc.COMM_CONDITION_CODE) as PROGRAM_METHOD, ( select count(*) from offender_program_profiles where offender_prg_obligation_id in ( select offender_prg_obligation_id from offender_prg_obligations where offender_sent_condition_id = osc.offender_sent_condition_id) and profile_class = 'CRS') as COURSEPROFILESACTS, ( select count(*) from offender_course_attendances where offender_prg_obligation_id in ( select offender_prg_obligation_id from offender_prg_obligations where offender_sent_condition_id = osc.offender_sent_condition_id)) as APPOINTMENTSACTS, ( select count(*) from offender_ind_schedules where offender_prg_obligation_id in ( select offender_prg_obligation_id from offender_prg_obligations where offender_sent_condition_id = osc.offender_sent_condition_id)) APPOINTMENTSSA, ( select allocation_flag from community_conditions where comm_condition_type = osc.COMM_CONDITION_TYPE and category_type = osc.CATEGORY_TYPE and COMM_CONDITION_CODE = osc.COMM_CONDITION_CODE) as allocation_flag, ( select distinct 'Y' from offender_action_plans oap where oap.off_case_cond_id in ( select occ.off_case_cond_id from offender_case_conditions occ where occ.offender_sent_condition_id = osc.offender_sent_condition_id and occ.case_plan_id = ( select max(case_plan_id) from offender_case_conditions occ1 where occ1.offender_sent_condition_id = osc.offender_sent_condition_id )) ) as plan_of_action_flag, case when ( select count(*) from OFFENDER_PROGRAM_PROFILES opp where opp.offender_sent_condition_id = osc.offender_sent_condition_id)>0 then 'Y' when ( select count(*) from OFFENDER_UNPAID_WORK_ADJ ouwa where ouwa.offender_sent_condition_id = osc.offender_sent_condition_id)>0 then 'Y' else 'N' end COMM_PROJ_ALLOC_FLAG from OFFENDER_SENT_CONDITIONS osc
}
GET_CONDITION_TYPE_GRID_QUERY {
--  SELECT DISTINCT COMM_CONDITION_TYPE,CATEGORY_TYPE
--  FROM offender_sent_conditions WHERE sentence_seq =:sentenceSeq AND comm_condition_type =:categoryTypeCode
--  AND offender_book_id =:offenderBookId ORDER BY category_type
select  distinct  OSC.COMM_CONDITION_TYPE, OSC.CATEGORY_TYPE, ( case when A.staff_id is not null then ( select sm.last_name from staff_members sm where sm.staff_id = A.staff_id ) when A.team_member_id is not null then ( select sm.last_name from staff_members sm where sm.staff_id = ( select tsm.staff_id from team_staff_members tsm where tsm.team_member_id = A.team_member_id ) ) end ) as assigned_officer from OFFENDER_SENT_CONDITIONS OSC inner join OFFENDER_COND_TRANSFER A on OSC.OFFENDER_SENT_CONDITION_ID = A.OFFENDER_SENT_CONDITION_ID left join ( select MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID , OFFENDER_SENT_CONDITION_ID from OFFENDER_COND_TRANSFER group by OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID ) B on A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.OFFENDER_SENT_CONDITION_ID = B.OFFENDER_SENT_CONDITION_ID and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID
}
GET_CONDITION_GRID {
	SELECT COMM_CONDITION_CODE,LENGTH ,LENGTH_UNIT,START_DATE,EXPIRY_DATE,OTHER_PROGRAM,CONDITION_STATUS,OFFENDER_SENT_CONDITION_ID,
	NON_ASSOCIATION_TEXT,CURFEW_START_TIME,CURFEW_END_TIME,FINANCIAL_TOTAL_AMOUNT,NON_ASSOCIATION_TEXT,PROHIBITED_CONTACT,PROGRAM_ID FROM offender_sent_conditions
    WHERE sentence_seq=:sentenceSeq AND comm_condition_type=:conditionTypeCode AND CATEGORY_TYPE=:categoryTypeCode AND offender_book_id=:offenderBookId
}

GET_COMPLETE_CONDITION_GRID {
	SELECT SENTENCE_SEQ, COMM_CONDITION_TYPE, COMM_CONDITION_CODE, START_DATE,  STATUS_DATE, OFFENDER_BOOK_ID, EXPIRY_DATE, LIST_SEQ, COMMENT_TEXT, CURFEW_START_TIME, CURFEW_END_TIME, CONDITION_RECOMMENDED_FLAG, GOVERNOR_CONDITION_FLAG, LENGTH, LENGTH_UNIT, DETAILS_TEXT, OFFENDER_SENT_CONDITION_ID,
RESIDENCY_ADDRESS_ID,CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, CONDITION_REQUIRED_FLAG,
CONDITION_APPLIED_FLAG, LONG_COMMENT_TEXT, APPOINTMENT_PERSON_NAME, REVIEW_CODE, SUPERVISOR_NAME, REPORT_TIME,
REPORT_DATE, PERSONAL_RELATIONSHIP_FLAG, VEHICLE_DETAILS_FLAG, NON_ASSOCIATED_OFFENDERS, DRUG_TESTING, TERMINATION_DATE, 
STATUS_REASON_CODE, NO_RESIDENT_UNDER_AGE_OF, PROHIBITED_CONTACT, RESTRICTED_CHILD_AGE_OF, RESTRICTED_APPROVAL_PERSON,
CURFEW_TAGGING_FLAG, OTHER_PROGRAM, NO_WORK_WITH_UNDER_AGE, NO_WORK_WITH_UNDER_AGE_OF, NO_ACCESS_TO_INTERNET, NO_USER_OF_COMPUTER, 
STATUS_UPDATE_REASON, STATUS_UPDATE_COMMENT, STATUS_UPDATE_DATE, STATUS_UPDATE_STAFF_ID, WORKFLOW_ID, PROGRAM_ID,ACTIVITY_CODE,
 COND_ACT_TYPE, ACTIVITY_STATUS, CATEGORY_TYPE, NON_ASSOCIATION_TEXT, FINANCIAL_TOTAL_AMOUNT, SEAL_FLAG, OBJECT_ID, OBJECT_TYPE, BOARD_ORDER_FLAG,CONDITION_STATUS,
Tag_Licence.get_requirement(osc.COMM_CONDITION_CODE,osc.COMM_CONDITION_TYPE,osc.CATEGORY_TYPE) requirement,
( SELECT program_code FROM program_services WHERE program_id = osc.PROGRAM_ID) as program,
 ( SELECT program_method  FROM community_conditions WHERE comm_condition_type =osc.COMM_CONDITION_TYPE AND  category_type =osc.CATEGORY_TYPE
  AND COMM_CONDITION_CODE = osc.COMM_CONDITION_CODE) as PROGRAM_METHOD,
 (SELECT count(*) FROM offender_program_profiles WHERE offender_prg_obligation_id in 
 (SELECT offender_prg_obligation_id  FROM offender_prg_obligations WHERE offender_sent_condition_id = osc.offender_sent_condition_id) AND profile_class = 'CRS') as COURSEPROFILESACTS,
 (SELECT count(*) FROM offender_course_attendances WHERE offender_prg_obligation_id  in (SELECT offender_prg_obligation_id  FROM offender_prg_obligations 
 WHERE offender_sent_condition_id = osc.offender_sent_condition_id)) as APPOINTMENTSACTS,
 ( SELECT count(*)
           FROM offender_ind_schedules
          WHERE offender_prg_obligation_id in (SELECT offender_prg_obligation_id  FROM offender_prg_obligations 
 WHERE offender_sent_condition_id = osc.offender_sent_condition_id)) APPOINTMENTSSA
 
            
FROM OFFENDER_SENT_CONDITIONS osc WHERE sentence_seq=:sentenceSeq AND comm_condition_type=:commConditionType  AND offender_book_id=:offenderBookId
}

GET_CONDITION_LOV {
  SELECT comm_condition_code code, description, program_method pgmethod,active_flag
  FROM community_conditions
  WHERE comm_condition_type =:condition
  AND  category_type =:catogry 
  ORDER BY list_seq,description, code
}

INSERT_CONDITION_DATA {
    INSERT INTO offender_sent_conditions (sentence_seq,OFFENDER_BOOK_ID,COMM_CONDITION_CODE,COMM_CONDITION_TYPE,LENGTH,LENGTH_UNIT,START_DATE,EXPIRY_DATE,OTHER_PROGRAM,
    CONDITION_STATUS,LONG_COMMENT_TEXT,CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, CONDITION_APPLIED_FLAG,OFFENDER_SENT_CONDITION_ID, CATEGORY_TYPE)
                                  VALUES(:sentenceSeq, :offenderBookId, :conditionTypeCode, :conditionCode, :length, :lengthUnit, :startDate, :endDate, :program, :status, :commentText,
                                  CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :modifyUserId, :appliedFlag, :sentConditionId, :categoryTypeCode )
}

UPDATE_CONDITION_DATA {
    UPDATE offender_sent_conditions 
    SET 
    LENGTH =:length,
    LENGTH_UNIT =:lengthUnit,
    START_DATE =:startDate,
    EXPIRY_DATE =:endDate,
    --OTHER_PROGRAM =:program,
    CONDITION_STATUS =:status
    WHERE OFFENDER_SENT_CONDITION_ID =:sentConditionId
}

UPDATE_CONDITION_PROGRAM {
  UPDATE offender_sent_conditions SET
  --OTHER_PROGRAM =:program ,
  PROGRAM_ID =:programId,
  LONG_COMMENT_TEXT =:commentText,
  CURFEW_START_TIME =:curfewStartTime,
  CURFEW_END_TIME =:curfewEndTime, 
  FINANCIAL_TOTAL_AMOUNT =:finTotalAmount,
  NON_ASSOCIATION_TEXT =:nonAssociationText,
  ACTIVITY_CODE =:prohibitedContact,
  COMMENT_TEXT =:sortComment
  WHERE OFFENDER_SENT_CONDITION_ID =:sentConditionId
}

DELETE_CONDITION_DATA {
   DELETE FROM offender_sent_conditions where OFFENDER_SENT_CONDITION_ID =:offenderSentConditionId
}

OCUCONDI_DELETE_CONDITION_TYPE_DATA {
   DELETE FROM offender_sent_conditions where CATEGORY_TYPE =:categoryType and COMM_CONDITION_TYPE=:commConditionType and OFFENDER_BOOK_ID=:offenderBookId and OBJECT_ID = :objectId
}

PREINSERT_SENT_CONDITION_ID{
	SELECT NEXTVAL('order_id') FROM dual
}

CONDITION_STATUS {
SELECT   description , 
         code 	 
    FROM reference_codes 
   WHERE domain = 'ACTIVE_TYPE'
     AND (active_flag = 'Y')      
ORDER BY description, list_seq
} 

PROGRAM_COMMENT {
	SELECT OTHER_PROGRAM,
	PROGRAM_ID,
	LONG_COMMENT_TEXT,
	CURFEW_START_TIME,
	CURFEW_END_TIME,
	FINANCIAL_TOTAL_AMOUNT,
	NON_ASSOCIATION_TEXT,
	ACTIVITY_CODE,
	COMMENT_TEXT
	from offender_sent_conditions where OFFENDER_SENT_CONDITION_ID =:sentConditionId
}

SENTENCE_CATEGORY_LOV {
	SELECT DISTINCT sent_calc.sentence_category code,
	ref_code.description
	FROM reference_codes ref_code, sentence_calc_types sent_calc
	WHERE ref_code.domain = 'CATEGORY'
	AND ref_code.code = sent_calc.sentence_category
	AND sent_calc.active_flag = 'Y'
}

PROHIBITED_LOV {
     SELECT description, code
	FROM   reference_codes
	WHERE active_flag = 'Y'
	AND domain = 'COND_PRO_ACT'
	ORDER BY list_seq,description,code	
} 

GET_PROGRAM_ID {
	 SELECT PROGRAM_ID from program_services where program_code =:code
}

GET_PROGRAM_CODE {
	 SELECT program_code FROM program_services WHERE PROGRAM_ID =:id
} 
OCUCONDI_INSERT_OFFENDER_SENT_CONDITIONS {
	  INSERT INTO offender_sent_conditions (sentence_seq,OFFENDER_BOOK_ID,COMM_CONDITION_CODE,COMM_CONDITION_TYPE,LENGTH,LENGTH_UNIT,START_DATE,EXPIRY_DATE,OTHER_PROGRAM,
    CONDITION_STATUS,LONG_COMMENT_TEXT,CREATE_DATETIME, CREATE_USER_ID, CONDITION_APPLIED_FLAG,OFFENDER_SENT_CONDITION_ID, CATEGORY_TYPE,CURFEW_START_TIME,CURFEW_END_TIME,PROGRAM_ID,CURFEW_PROVIDER,OBJECT_ID,OBJECT_TYPE,ACTIVITY_CODE,FINANCIAL_TOTAL_AMOUNT,NON_ASSOCIATION_TEXT,MODIFY_DATETIME)
                                  VALUES(:sentenceSeq, :offenderBookId, :commConditionCode, :commConditionType, :length, :lengthUnit, :startDate, :expiryDate, :otherProgram, :conditionStatus, :longCommentText,
                                  CURRENT_TIMESTAMP, :createUserId, :conditionAppliedFlag,  :offenderSentConditionId, :categoryType,:curfewStartTime,:curfewEndTime,:programId,:curfewProvider,:objectId,:objectType,:activityCode,:financialTotalAmount,:nonAssociationText,NULL )
}
OCUCONDI_UPDATE_OFFENDER_SENT_CONDITIONS {
UPDATE OFFENDER_SENT_CONDITIONS
SET  COMM_CONDITION_TYPE=:commConditionType, COMM_CONDITION_CODE=:commConditionCode, START_DATE=:startDate, CONDITION_STATUS=:conditionStatus, STATUS_DATE=:statusDate,
 EXPIRY_DATE=:expiryDate, LIST_SEQ=:listSeq, COMMENT_TEXT=:commentText, CURFEW_START_TIME=:curfewStartTime, CURFEW_END_TIME=:curfewEndTime,  LENGTH=:length, LENGTH_UNIT=:lengthUnit, DETAILS_TEXT=:detailsText,
CURFEW_PROVIDER=:curfewProvider, EXCLUSION_CODE=:exclusionCode, RESIDENCY_ADDRESS_ID=:residencyAddressId, MENTAL_HEALTH_PROVIDER=:mentalHealthProvider, ATTENDANCE_CENTRE=:attendanceCentre,
CONDITION_REQUIRED_FLAG=:conditionRequiredFlag, CONDITION_APPLIED_FLAG=:conditionAppliedFlag, LONG_COMMENT_TEXT=:longCommentText, APPOINTMENT_PERSON_NAME=:appointmentPersonName, REVIEW_CODE=:reviewCode, SUPERVISOR_NAME=:supervisorName, REPORT_TIME=:reportTime,
REPORT_DATE=:reportDate, STATUS_REASON_CODE=:statusReasonCode,  PROHIBITED_CONTACT=:prohibitedContact, RESTRICTED_APPROVAL_PERSON=:restrictedApprovalPerson, PROGRAM_ID=:programId, ACTIVITY_CODE=:activityCode, COND_ACT_TYPE=:condActType, ACTIVITY_STATUS=:activityStatus,
CATEGORY_TYPE=:categoryType,NON_ASSOCIATION_TEXT=:nonAssociationText, FINANCIAL_TOTAL_AMOUNT=:financialTotalAmount, MODIFY_DATETIME= CURRENT_TIMESTAMP, MODIFY_USER_ID= :modifyUserId
WHERE OFFENDER_SENT_CONDITION_ID= :offenderSentConditionId
}
IS_DUPLICATE_DETAIL_ACP {
SELECT COUNT(*) FROM OFFENDER_SENT_CONDITIONS
  WHERE program_id = :p_program_id AND sentence_seq = :p_sentence_seq AND comm_condition_type = :p_comm_cond_type
   AND offender_book_id = :p_offender_book_id
}

GET_DEFAULT_CONDITIONS {
   SELECT description,
                comm_condition_code,
                category_type,
                condition_unit_type length_unit, PROGRAM_METHOD
           FROM COMMUNITY_CONDITIONS
          WHERE comm_condition_type = :p_comm_condition_type
            AND category_type = :p_category_type
            AND active_flag = 'Y'
            AND default_flag = 'Y'
            ORDER BY list_seq, comm_condition_code
   
}
GET_LV_CNT {
SELECT COUNT(*) FROM community_conditions
    WHERE comm_condition_type = :comm_condition_type AND comm_condition_code = :comm_condition_code
      AND category_type = :category_type AND program_method = 'FIN'
      }
GET_LV_CASE_INFO_NUMBER {
      SELECT oc.case_info_number
        INTO lv_case_info_number
        FROM offender_cases     oc,
             offender_sentences os
       WHERE os.offender_book_id = :offender_book_id
         AND os.sentence_seq = :sentence_seq
         AND os.offender_book_id = oc.offender_book_id
         AND os.case_id = oc.case_id
         
         }
 GET_LV_ROOT_OFFENDER_ID {
 SELECT root_offender_id
        FROM V_HEADER_BLOCK_FN(:USERID)
       WHERE offender_book_id = :offender_book_id
       }
OCUCONDI_GET_FK_TABLE_NAMES {       
  SELECT  AC1.TABLE_NAME
        FROM ALL_CONSTRAINTS AC1, ALL_CONSTRAINTS AC2
       WHERE AC1.CONSTRAINT_NAME = :P_CONSTRAINT_NAME
         AND AC1.CONSTRAINT_TYPE = 'R'
         AND AC2.CONSTRAINT_NAME = AC1.R_CONSTRAINT_NAME
         AND AC2.OWNER = AC1.R_OWNER
         AND AC2.CONSTRAINT_TYPE IN ('P', 'U')
         }
         
OCUCONDI_POST_INSERT_OFFENDER_COND_TRANSFERS{
insert into OFFENDER_COND_TRANSFER(CON_TRANSFER_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID, STAFF_ID, TEAM_ID, TEAM_MEMBER_ID, AGY_LOC_ID, TO_TEAM_ID, TO_AGY_LOC_ID, CONDI_STATUS, CREATE_DATETIME, CREATE_USER_ID, SEAL_FLAG, TO_STAFF_ID, TO_TEAM_MEMBER_ID, PARENT_CON_TRANSFER_ID,SENTENCE_SEQ) values (nextval('OFFENDER_COND_TRANSFER_ID'), :offenderBookId, :offenderSentConditionId, :staffId, :teamId, :teamMemberId, :agyLocId, :toTeamId, :toAgyLocId, :condiStatus, current_timestamp, :createUserId, :sealFlag, :toStaffId, :toTeamMemberId, :parentCondTransferId,:sentenceSeq)
}
OCUCONDI_PRE_INSERT_GET_SEQ{
SELECT NEXTVAL('OFFENDER_SENT_CONDITION_ID') FROM DUAL
}

OCUCONDI_GET_COND_TRANSFER_DATA {
	select * from offender_cond_transfer oct where offender_sent_condition_id = :offenderSentConditionId order by create_datetime desc
}
OCUCONDI_DELETE_OFFENDER_COND_TRANSFER {
delete from OFFENDER_COND_TRANSFER where con_transfer_id  = :conTransferId
}
OCUCONDI_GET_CASEPLAN_CONDITIONS {
select * from offender_case_conditions occ where occ.offender_sent_condition_id = :offenderSentConditionId and case_plan_id = (select max(oc.case_plan_id) from offender_case_conditions oc where oc.offender_sent_condition_id = occ.offender_sent_condition_id )
}
OCUCONDI_DELETE_CASEPLAN_CONDITIONS {
delete from offender_case_conditions where off_case_cond_id  = :offCaseCondId
}

GET_AGY_LOC_ID{
select community_agy_loc_id from offender_bookings ob  where offender_book_id =:offenderBookId ;
}

