OCDENFOR_FIND_RGAGYLOCS {
select
	agy_loc_id code,
	description
from
	agency_locations
where
	agency_location_type = 'CRT'	
	and active_flag = 'Y'
	and deactivation_date is null
order by
	list_seq,
	description
}

OCDENFOR_RG_TEAM_RESPONSIBLE {
select at.team_code team_code, at.description description , to_char(at.team_id) code from automation_teams at, team_agency_loc tal, team_staff_members tsm where (at.active_flag = 'Y' and tal.active_flag = 'Y' and tsm.active_flag = 'Y' )and tal.team_id = at.team_id and tsm.team_id = at.team_id and tsm.staff_id = (select staff_id from staff_members where staff_members.user_id = :userId and staff_members.status = 'ACTIVE') and tal.agy_loc_id=:agylocId
}

OCDENFOR_RG_STAFF_RESPONSIBLE {
select
	TO_CHAR(TSM.TEAM_MEMBER_ID) CODE,
	(SM.LAST_NAME || ' , ' || SM.FIRST_NAME) DESCRIPTION
from
	TEAM_STAFF_MEMBERS TSM,
	STAFF_MEMBERS SM
where
	TEAM_ID = (:teamResponsible)::bigint
	and SM.STAFF_ID = TSM.STAFF_ID
	and TSM.ACTIVE_FLAG = 'Y'
	and SM.STATUS = 'ACTIVE'
}

OCDENFOR_RG_ALL_STAFF_RESPONSIBLE {
	SELECT to_char(SM.STAFF_ID) CODE, (SM.LAST_NAME || ' , ' || SM.FIRST_NAME) DESCRIPTION FROM  STAFF_ACCESSIBLE_CASELOADS SAC, STAFF_MEMBERS SM WHERE  SAC.CASELOAD_ID = :caseloadId AND SAC.STAFF_ID =SM.STAFF_ID AND SM.STATUS = 'ACTIVE' ORDER BY SM.STAFF_ID ASC
}

OCDENFOR_ACTIONS_EXECUTE_QUERY {
	select offender_proceeding_id, offender_book_id, proceeding_type, start_date, proceeding_agy_loc_id, comment_text, proceeding_status, outcome_date, team_id, staff_responsible,order_type, sentence_seq, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, proceeding_pursuant_act,crt_action_recommendation, to_char(staff_responsible) staff_id,to_char(team_id) team_responsible from offender_proceedings where offender_book_id = :offenderBookId and order_type = :orderType
}

OCDENFOR_INSERT_COURT_ACTIONS {
	insert into offender_proceedings (offender_proceeding_id, offender_book_id, proceeding_type, start_date, proceeding_agy_loc_id, comment_text, proceeding_status, outcome_date, team_id, staff_responsible, order_type, sentence_seq, create_datetime, create_user_id, modify_datetime, modify_user_id,proceeding_pursuant_act,crt_action_recommendation) values (:offenderProceedingId, :offenderBookId, :proceedingType, :startDate, :proceedingAgyLocId, :commentText, :proceedingStatus, :outcomeDate, :teamId, :staffResponsible, :orderType, :sentenceSeq, current_timestamp, :createUserId, current_timestamp, :modifyUserId,:proceedingPursuantAct,:crtActionRecommendation) 
}

OCDENFOR_UPDATE_COURT_ACTIONS {
	update offender_proceedings set offender_proceeding_id = :offenderProceedingId, offender_book_id = :offenderBookId, proceeding_type = :proceedingType, start_date = :startDate, proceeding_agy_loc_id = :proceedingAgyLocId, comment_text = :commentText, proceeding_status = :proceedingStatus, outcome_date = :outcomeDate, team_id = :teamId, staff_responsible = :staffResponsible, order_type = :orderType, sentence_seq = :sentenceSeq, modify_datetime = current_timestamp, modify_user_id = :modifyUserId,proceeding_pursuant_act=:proceedingPursuantAct,crt_action_recommendation=:crtActionRecommendation where offender_proceeding_id = :offenderProceedingId
}

OCDENFOR_GET_NEXT_PROCEEDING_ID{
	select NEXTVAL('offender_proceeding_id') from DUAL
}

OCDENFOR_SCHEDULE_COUNT {
SELECT COUNT (*)
        FROM v_offender_all_schedules_2
       WHERE offender_book_id = :offender_book_id
         AND event_status = 'SCH'
         AND event_date = :event_date         
}
OCDENFOR_GET_NEXT_OFF_PROCEEDING_COND_ID {
select NEXTVAL('off_proceeding_cond_id') from DUAL
}
OCDENFOR_GET_OFFENDER_PROCEEDING_DATA_BASED_ON_ID {
select * from offender_proceeding_conditions where offender_proceeding_id=:offenderProceedingId
}
OCDENFOR_DELETE_OFFENDER_PROCEEDING_DATA_BASED_ON_ID {
delete from offender_proceeding_conditions where offender_proceeding_id=:offenderProceedingId
}
OCDENFOR_INSERT_OFFENDER_PROCEEDING_CONDITIONS {
insert into offender_proceeding_conditions(off_proceeding_cond_id,offender_proceeding_id,offender_sent_condition_id,create_datetime,create_user_id) values 
(:offProceedingCondId,:offenderProceedingId,:offenderSentConditionId,current_timestamp,:createUserId)
}
OCDENFOR_GET_CONDITION_TYPE_GRID {
select (select count(1) from offender_proceeding_conditions where offender_sent_condition_id = osc.offender_sent_condition_id and offender_proceeding_id = :offenderProceedingId) as linked_count,
(select count(1) from offender_proceeding_conditions where offender_sent_condition_id = osc.offender_sent_condition_id and offender_proceeding_id in(select offender_proceeding_id  from offender_proceedings where offender_book_id  = :OFFENDER_BOOK_ID)) as linked_to_other_proceeding,
SENTENCE_SEQ, COMM_CONDITION_TYPE, COMM_CONDITION_CODE, START_DATE, STATUS_DATE, OFFENDER_BOOK_ID, EXPIRY_DATE, LIST_SEQ, COMMENT_TEXT, CURFEW_START_TIME, CURFEW_END_TIME, CONDITION_RECOMMENDED_FLAG, GOVERNOR_CONDITION_FLAG, LENGTH, LENGTH_UNIT, DETAILS_TEXT, OFFENDER_SENT_CONDITION_ID, RESIDENCY_ADDRESS_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, CONDITION_REQUIRED_FLAG, CONDITION_APPLIED_FLAG, LONG_COMMENT_TEXT, APPOINTMENT_PERSON_NAME, REVIEW_CODE, SUPERVISOR_NAME, REPORT_TIME, REPORT_DATE, PERSONAL_RELATIONSHIP_FLAG, VEHICLE_DETAILS_FLAG, NON_ASSOCIATED_OFFENDERS, DRUG_TESTING, TERMINATION_DATE, STATUS_REASON_CODE, NO_RESIDENT_UNDER_AGE_OF, PROHIBITED_CONTACT, RESTRICTED_CHILD_AGE_OF, RESTRICTED_APPROVAL_PERSON, CURFEW_TAGGING_FLAG, OTHER_PROGRAM, NO_WORK_WITH_UNDER_AGE, NO_WORK_WITH_UNDER_AGE_OF, NO_ACCESS_TO_INTERNET, NO_USER_OF_COMPUTER, STATUS_UPDATE_REASON, STATUS_UPDATE_COMMENT, STATUS_UPDATE_DATE, STATUS_UPDATE_STAFF_ID, WORKFLOW_ID, PROGRAM_ID, ACTIVITY_CODE, COND_ACT_TYPE, ACTIVITY_STATUS, CATEGORY_TYPE, NON_ASSOCIATION_TEXT, FINANCIAL_TOTAL_AMOUNT, SEAL_FLAG, OBJECT_ID, OBJECT_TYPE, BOARD_ORDER_FLAG, CONDITION_STATUS, Tag_Licence_get_requirement(osc.COMM_CONDITION_CODE, osc.COMM_CONDITION_TYPE, osc.CATEGORY_TYPE) requirement, (select (case when A.staff_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = A.staff_id ) when A.team_member_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = (select tsm.staff_id from team_staff_members tsm where tsm.team_member_id = A.team_member_id) ) when A.to_staff_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = A.to_staff_id ) when A.to_team_member_id is not null then (select coalesce(sm.last_name,'') || ', ' || coalesce(sm.first_name,'') from staff_members sm where sm.staff_id = (select tsm.staff_id from team_staff_members tsm where tsm.team_member_id = A.to_team_member_id) ) end ) as assigned_officer from ( select MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID from OFFENDER_COND_TRANSFER group by OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID) B, OFFENDER_COND_TRANSFER A where A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.OFFENDER_SENT_CONDITION_ID = osc.OFFENDER_SENT_CONDITION_ID and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID and A.OFFENDER_BOOK_ID = osc.offender_book_id ) as assigned_officer, ( select program_code from program_services where program_id = osc.PROGRAM_ID) as program, ( select program_method from community_conditions where comm_condition_type = osc.COMM_CONDITION_TYPE and category_type = osc.CATEGORY_TYPE and COMM_CONDITION_CODE = osc.COMM_CONDITION_CODE) as PROGRAM_METHOD, ( select count(*) from offender_program_profiles where offender_prg_obligation_id in ( select offender_prg_obligation_id from offender_prg_obligations where offender_sent_condition_id = osc.offender_sent_condition_id) and profile_class = 'CRS') as COURSEPROFILESACTS, ( select count(*) from offender_course_attendances where offender_prg_obligation_id in ( select offender_prg_obligation_id from offender_prg_obligations where offender_sent_condition_id = osc.offender_sent_condition_id)) as APPOINTMENTSACTS, ( select count(*) from offender_ind_schedules where offender_prg_obligation_id in ( select offender_prg_obligation_id from offender_prg_obligations where offender_sent_condition_id = osc.offender_sent_condition_id)) APPOINTMENTSSA, ( select allocation_flag from community_conditions where comm_condition_type = osc.COMM_CONDITION_TYPE and category_type = osc.CATEGORY_TYPE and COMM_CONDITION_CODE = osc.COMM_CONDITION_CODE) as allocation_flag, ( select distinct 'Y' from offender_action_plans oap where oap.off_case_cond_id in ( select occ.off_case_cond_id from offender_case_conditions occ where occ.offender_sent_condition_id = osc.offender_sent_condition_id and occ.case_plan_id = ( select max(case_plan_id) from offender_case_conditions occ1 where occ1.offender_sent_condition_id = osc.offender_sent_condition_id )) ) as plan_of_action_flag, case when ( select count(*) from OFFENDER_PROGRAM_PROFILES opp where opp.offender_sent_condition_id = osc.offender_sent_condition_id)>0 then 'Y' when ( select count(*) from OFFENDER_UNPAID_WORK_ADJ ouwa where ouwa.offender_sent_condition_id = osc.offender_sent_condition_id)>0 then 'Y' else 'N' end COMM_PROJ_ALLOC_FLAG from OFFENDER_SENT_CONDITIONS osc
}
OCDENFOR_GET_LEGAL_SETTING_PERSUET_HIDE_SHOW_VALUE {
select ls.value from legal_settings ls where code=:code
}
--
--OCDENFOR_FIND_RGPROCEEDINGS {
-- 	select
--	REF_CODE.DESCRIPTION ,
--	REF_CODE.CODE
--from
--	REFERENCE_CODES REF_CODE
--where
--	domain = 'PROCEEDING'
--	and ( ( ACTIVE_FLAG = 'Y'
--		and EXPIRED_DATE is null )
--	)
--order by
--	LIST_SEQ ,
--	DESCRIPTION ,
--	CODE
--}
--
--OCDENFOR_FIND_RGPROCEEDSTS {
-- 	select
--	REF_CODE.DESCRIPTION ,
--	REF_CODE.CODE
--from
--	REFERENCE_CODES REF_CODE
--where
--	domain = 'PROCEED_STS'
--	and ( ( ACTIVE_FLAG = 'Y'
--		and EXPIRED_DATE is null )
--	)
--order by
--	LIST_SEQ ,
--	DESCRIPTION ,
--	CODE
--}
--
--OCDENFOR_OFFPRCS_FIND_OFFENDER_PROCEEDINGS {
-- select * from offender_proceedings where OFFENDER_BOOK_ID= :offBookId Order By START_DATE DESC
--}
--OCDENFOR_OFFPRCS_INSERT_OFFENDER_PROCEEDINGS {
--INSERT INTO OFFENDER_PROCEEDINGS(OFFENDER_BOOK_ID,PROCEEDING_TYPE,START_DATE,PROCEEDING_AGY_LOC_ID,COMMENT_TEXT,PROCEEDING_STATUS,OUTCOME_DATE,OFFENDER_PROCEEDING_ID,CREATE_DATETIME, MODIFY_DATETIME, CREATE_USER_ID)
--         values(:offenderBookId,:proceedingType,:startDate,:proceedingAgyLocId,:commentText,:proceedingStatus,:outcomeDate,:offenderProceedingId,current_timestamp, current_timestamp, :createUserId)
--         }
--
--OCDENFOR_OFFPRCS_UPDATE_OFFENDER_PROCEEDINGS {
--	     UPDATE offender_proceedings SET proceeding_type = :proceedingType, start_date = :startDate, PROCEEDING_AGY_LOC_ID = :proceedingAgyLocId, comment_text = :commentText, proceeding_status = :proceedingStatus, outcome_date = :outcomeDate, modify_user_id =:modifyUserId, modify_datetime = current_timestamp WHERE offender_proceeding_id = :offenderProceedingId
--}
--
--OCDENFOR_VOFFSNT_FIND_V_OFFENDER_PROCEEDING_SENTS {
--SELECT OFFENDER_PROCEEDING_ID, OFFENDER_BOOK_ID, PROCEEDING_TYPE, PROCEEDING_AGY_LOC_ID, COMMENT_TEXT, PROCEEDING_STATUS, OUTCOME_DATE, PROCEEDING_SENTENCE_FLAG, SENTENCE_SEQ, SENTENCE_CALC_TYPE, SENTENCE_CATEGORY, SENTENCE_CATEGORY_DESC, SENTENCE_DESC, NO_OF_UNEXCUSED_ABSENCE, START_DATE, END_DATE, CASE_ID, CASE_INFO_PREFIX, CASE_INFO_NUMBER, CASE_COURT_DESC FROM v_offender_proceeding_sents where OFFENDER_PROCEEDING_ID=:offProcId AND OFFENDER_BOOK_ID=:offBookId order by start_date desc, sentence_seq asc
--}
--OCDENFOR_VOFFSNT_FIND_V_OFFENDER_PROCEEDING_ONINSERT_SENTS {
--SELECT OFFENDER_PROCEEDING_ID, OFFENDER_BOOK_ID, PROCEEDING_TYPE, PROCEEDING_AGY_LOC_ID, COMMENT_TEXT, PROCEEDING_STATUS, OUTCOME_DATE, PROCEEDING_SENTENCE_FLAG, SENTENCE_SEQ, SENTENCE_CALC_TYPE, SENTENCE_CATEGORY, SENTENCE_CATEGORY_DESC, SENTENCE_DESC, NO_OF_UNEXCUSED_ABSENCE, START_DATE, END_DATE, CASE_ID, CASE_INFO_PREFIX, CASE_INFO_NUMBER, CASE_COURT_DESC FROM v_offender_proceeding_sents
--}
--OCDENFOR_VOFFSNT_UPDATE_V_OFFENDER_PROCEEDING_SENTS {
--	UPDATE V_OFFENDER_PROCEEDING_SENTS set SENTENCE_SEQ=:sentenceSeq where OFFENDER_PROCEEDING_ID=:offenderProceedingId
--}
--
--OCDENFOR_OFFPRCS_UPDATE_VOFFENDER_PROCEEDING_SENTS{
--update Offender_Proceeding_Sents set SENTENCE_SEQ=:sentenceSeq, modify_user_id =:modifyUserId, modify_datetime = current_timestamp  where OFFENDER_PROCEEDING_ID=:offenderProceedingId and offender_book_id=:offenderBookId
--}
--
--OCDENFOR_OFFPRCS_UPDATE_VOFFENDER_PROCEEDING_SENTS_TEMP{
--	insert into Offender_Proceeding_Sents(OFFENDER_PROCEEDING_ID,OFFENDER_BOOK_ID,SENTENCE_SEQ,CREATE_DATETIME,CREATE_USER_ID) values(:offenderProceedingId,:offenderBookId,:sentenceSeq,current_timestamp,:createUserId)
--
--}
--
--OCDENFOR_VOFFSCH_FIND_V_OFFENDER_SENTENCE_EVENTS {
--select
--	*
--from
--	V_Offender_Sentence_events
--where
--	offender_book_id = :offBookId
--	and sentence_seq = :sentSeq
--	and event_outcome in (
--	select
--		outcome_code
--	from
--		event_measure_outcomes
--	where
--		coalesce(set_counter_flag,
--		'N') = 'Y' )
--order by
--	event_date desc
--}
--OCDENFOR_CRTEVTS_FIND_COURT_EVENTS {
--select
--	*
--from
--	court_events
--where
--	offender_book_id =:offBookId
--	and offender_proceeding_id =:offProcId
--order by
--	event_date desc
--}
--
--OCDENFOR_EVENT_OUTCOME {
-- SELECT description
--        FROM offence_result_codes
--       WHERE result_code =:event_outcome
--       
--}
--OCDENFOR_CRTEVTS_INSERT_COURT_EVENTS {
--	INSERT INTO COURT_EVENTS(EVENT_DATE,START_TIME,AGY_LOC_ID,COURT_EVENT_TYPE,COMMENT_TEXT,ORDER_REQUESTED_FLAG,EVENT_OUTCOME,EVENT_ID,OFFENDER_BOOK_ID,CREATE_USER_ID,OFFENDER_PROCEEDING_ID,CREATE_DATETIME,MODIFY_DATETIME) VALUES(
--:eventDate,:startTime,:agyLocId,:courtEventType,:commentText,:orderRequestedFlag,:eventOutcome,NEXTVAL('event_id'),:offenderBookId,:createUserId,:offenderProceedingId,current_timestamp,current_timestamp)
--}
--
--OCDENFOR_CRTEVTS_UPDATE_COURT_EVENTS {
--	UPDATE COURT_EVENTS set EVENT_DATE=:eventDate,START_TIME=:startTime,AGY_LOC_ID=:agyLocId,COURT_EVENT_TYPE=:courtEventType,COMMENT_TEXT=:commentText,
--	ORDER_REQUESTED_FLAG=:orderRequestedFlag,EVENT_OUTCOME=:eventOutcome, modify_user_id =:modifyUserId, modify_datetime = current_timestamp where OFFENDER_BOOK_ID=:offenderBookId and event_id=:eventId
--}
--
--OCDENFOR_CRTEVTS_DELETE_COURT_EVENTS { 
--	DELETE FROM COURT_EVENTS   where OFFENDER_BOOK_ID=:offenderBookId and event_id=:eventId
--}
--
--
--OCDENFOR_OFF_BKG_ONCHECKDELETEMASTER_ {
--	SELECT 1 FROM OFFENDER_PROCEEDINGS O WHERE O.OFFENDER_BOOK_ID = :offBookId 	
--}
--
--OCDENFOR_CASE_START_DATE {
--select
--	MIN (begin_date) beginDate
--from
--	offender_cases
--where
--	offender_book_id =:offBookId
--	and case_status = 'A'
--}
--
--OCDENFOR_OFF_PRCS_ONCHECKDELETEMASTER_ {
--	SELECT 1 FROM COURT_EVENTS C WHERE C.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND C.OFFENDER_PROCEEDING_ID = :OFFENDERPROCEEDINGID
--}
--
--OCDENFOR_OFF_PRCS_ONCHECKDELETEMASTER_ {
--	SELECT 1 FROM V_OFFENDER_PROCEEDING_SENTS V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND V.OFFENDER_PROCEEDING_ID = :OFFENDERPROCEEDINGID
--}
--
--OCDENFOR_VOFF_SNT_ONCHECKDELETEMASTER_ {
--	SELECT 1 FROM V_OFFENDER_SENTENCE_EVENTS V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND V.SENTENCE_SEQ = :SENTENCESEQ
--}
--
--OCDENFOR_CRT_EVTS_ONCHECKDELETEMASTER_ {
--	SELECT 1 FROM COURT_EVENT_SENTENCES V WHERE  V.EVENT_ID = :EVENTID
--}
--OCDENFOR_CHECK_ACTIVE_OR_NOT{
--  SELECT count(*)
--        FROM v_offender_proceeding_sents
--       WHERE offender_book_id = :offBookId
--         AND sentence_seq = :senSeq
--         AND offender_proceeding_id != :offProcId
--         AND proceeding_status = 'ACTIVE'
--         AND proceeding_sentence_flag = 'Y'
--}
--
--OCDENFOR_VOFFSNT_FIND_DISPOSITION_V_OFFENDER_PROCEEDING_SENTS {
--select
--	OFFENDER_PROCEEDING_ID,
--	OFFENDER_BOOK_ID,
--	PROCEEDING_TYPE,
--	PROCEEDING_AGY_LOC_ID,
--	COMMENT_TEXT,
--	PROCEEDING_STATUS,
--	OUTCOME_DATE,
--	PROCEEDING_SENTENCE_FLAG,
--	SENTENCE_SEQ,
--	SENTENCE_CALC_TYPE,
--	SENTENCE_CATEGORY,
--	SENTENCE_CATEGORY_DESC,
--	SENTENCE_DESC,
--	NO_OF_UNEXCUSED_ABSENCE,
--	START_DATE,
--	END_DATE,
--	CASE_ID,
--	CASE_INFO_PREFIX,
--	CASE_INFO_NUMBER,
--	CASE_COURT_DESC
--from
--	v_offender_proceeding_sents
--where
--	offender_book_id =:offBookId
--	and offender_proceeding_id =:offProcId
--	and coalesce(PROCEEDING_SENTENCE_FLAG,
--	'N') = 'Y'
--order by
--	start_date desc,
--	sentence_seq asc
--}
--
--
--OCDENFOR_OFFPRCS_INSERT_OFFENDER_PROCEEDINGS_SENT {
--insert into offender_proceeding_sents(OFFENDER_PROCEEDING_ID, OFFENDER_BOOK_ID, SENTENCE_SEQ, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG)
--values (:offenderProceedingId,:offenderBookId,:sentenceSeq,current_timestamp,:createUserId,current_timestamp,:sealFlag)
--}
--
--OCDENFOR_OFFENDER_PROCEEDING_INFORMATION {
-- SELECT *  FROM offender_proceeding_sents where OFFENDER_PROCEEDING_ID=:offenderProceedingId
--}