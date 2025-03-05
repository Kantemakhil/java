OCDCLOGS_FIND_RGCASENOTETYPE {
select
	distinct RC.DESCRIPTION,
	W.WORK_TYPE CODE,
	W.ACTIVE_FLAG,
	case
		when (W.CASELOAD_TYPE in (:CASELOADTYPE, 'BOTH' )
		and W.MANUAL_SELECT_FLAG = 'Y'
		and W.ACTIVE_FLAG = 'Y') then 1
		else 0
	end SEQVALUE,cnp.work_id,
	false DISPLAY,
	W.CASELOAD_TYPE as state
from
	WORKS W,
	REFERENCE_CODES RC,
	case_note_permissions cnp 
where
      cnp.work_id =W.work_id and
	WORKFLOW_TYPE = 'CNOTE'
	and RC.DOMAIN = 'TASK_TYPE'
	and RC.CODE = W.WORK_TYPE
order by
	DESCRIPTION ,
	CODE
}
OCDCLOGS_FIND_RGCASENOTESUBTYPE {
select
	distinct RC.DESCRIPTION,
	W.WORK_SUB_TYPE CODE,
	W.ACTIVE_FLAG,
	case
		when ( (W.WORK_TYPE = :CASENOTETYPE
		and W.MANUAL_SELECT_FLAG = 'Y'
		and W.ACTIVE_FLAG = 'Y')) then 1
		else 0
	end SEQVALUE,
	cnp.work_id, false DISPLAY,
	W.caseload_type as state
from
	WORKS W,
	REFERENCE_CODES RC,
	case_note_permissions cnp 
where
    cnp.work_id =W.work_id and
	WORKFLOW_TYPE = 'CNOTE'
	and RC.DOMAIN = 'TASK_SUBTYPE'
	and RC.CODE = W.WORK_SUB_TYPE
and W.WORK_TYPE = :CASENOTETYPE
order by
	DESCRIPTION ,
	CODE
}

OCDCLOGS_OFFNOTES_FIND_OFFENDER_CASE_NOTES {
select ocn.OFFENDER_BOOK_ID , ocn.CONTACT_DATE , ocn.CONTACT_TIME , ocn.CASE_NOTE_TYPE , ocn.CASE_NOTE_SUB_TYPE , ocn.STAFF_ID , ocn.CASE_NOTE_TEXT , ocn.AMENDMENT_FLAG , ocn.IWP_FLAG , ocn.CHECK_BOX1 , ocn.CHECK_BOX2 , ocn.CHECK_BOX3 , ocn.CHECK_BOX4 , ocn.CHECK_BOX5 , ocn.EVENT_ID , ocn.CASE_NOTE_ID , ocn.NOTE_SOURCE_CODE , ocn.DATE_CREATION , ocn.TIME_CREATION , ocn.SEAL_FLAG , ocn.OBJECT_TYPE , ocn.OBJECT_ID , ocn.CREATE_DATETIME , ocn.CREATE_USER_ID , ocn.MODIFY_DATETIME , ocn.MODIFY_USER_ID, ( select coalesce(STAFF.LAST_NAME, '') || ', ' || coalesce(STAFF.FIRST_NAME, '') from STAFF_MEMBERS STAFF where staff_id = ocn.staff_id ) as staff_name, case when ( select COUNT(*) from CASE_NOTE_PERMISSIONS where CREATE_FLAG = 'Y' and WORK_ID =( select WORK_ID from WORKS W where WORKFLOW_TYPE = 'CNOTE' and WORK_TYPE = OCN.CASE_NOTE_TYPE and WORK_SUB_TYPE = OCN.CASE_NOTE_SUB_TYPE) and ROLE_ID in ( select ROLE_ID from STAFF_MEMBER_ROLES where STAFF_ID = ( select STAFF_ID from STAFF_MEMBERS where LOWER(USER_ID) = LOWER(:user)) )) > 0 then 'Y' else 'N' end as CREATE_FLAG, case when ( select COUNT(*) from CASE_NOTE_PERMISSIONS where VIEW_FLAG = 'Y' and WORK_ID =( select WORK_ID from WORKS W where WORKFLOW_TYPE = 'CNOTE' and WORK_TYPE = OCN.CASE_NOTE_TYPE and WORK_SUB_TYPE = OCN.CASE_NOTE_SUB_TYPE) and ROLE_ID in ( select ROLE_ID from STAFF_MEMBER_ROLES where STAFF_ID = ( select STAFF_ID from STAFF_MEMBERS where LOWER(USER_ID) = LOWER(:user)) )) > 0 then 'Y' else 'N' end as VIEW_FLAG from offender_case_notes ocn
} 
OCDCLOGS_OFF_SCH_PE_INSERT
{
        SELECT COUNT (*)
        FROM v_offender_all_schedules_2
       WHERE offender_book_id = :OFFENDER_BOOK_ID
         AND event_status = 'SCH'
         AND event_date =to_char(:EVENT_DATE)::date 
}


OCDCLOGS_SCH_EXECUTE_QUERY{
    select
	vs.event_date EVENT_DATE,
	vs.start_time START_TIME,
	tag_establishment_get_agy_loc_desc (to_agy_loc_id) AGY_LOC_DESC
from
	v_offender_all_schedules_2 vs
where
	vs.offender_book_id = :offenderBookId
	and vs.start_time =
                   (
	select
		MIN (vs1.start_time)
	from
		v_offender_all_schedules_2 vs1
	where
		vs1.offender_book_id = :offenderBookId
		and vs1.event_outcome is null
		and event_status = 'SCH'
		and vs1.start_time >= current_timestamp)
}

OCDCLOGS_FIND_RGCASE_NOTE_STAFF_NAME{
SELECT
    b.last_name
    || ', '
    || b.first_name AS description,
    b.staff_id AS code,
    coalesce(a.active_flag, 'N') active_flag
FROM
    (
        SELECT DISTINCT
            a.staff_name     description,
            a.sac_staff_id   code,
            'Y' active_flag
        FROM
            v_staff_location_roles   a,
            offender_bookings        b
        WHERE
            ( b.offender_book_id = :offenderBookId
              AND EXISTS (
                SELECT
                    'X'
                FROM
                    caseload_agency_locations
                WHERE
                    agy_loc_id = a.cal_agy_loc_id
                    AND caseload_id = :caseloadId
            ) )
            OR ( b.active_flag = 'Y'
                 AND a.cal_agy_loc_id = b.agy_loc_id
                 AND b.agy_loc_id = :agyLocId )
            OR ( b.community_active_flag = 'Y'
                 AND b.community_agy_loc_id = a.cal_agy_loc_id )
            OR ( coalesce(b.active_flag, 'N') = 'N'
                 AND coalesce(b.community_active_flag, 'N') = 'N'
                 AND EXISTS (
                SELECT
                    'X'
                FROM
                    caseload_agency_locations
                WHERE
                    agy_loc_id = a.cal_agy_loc_id
                    AND caseload_id = :caseloadId
            ) )
        ORDER BY
            a.staff_name ASC
    ) a
    RIGHT OUTER JOIN staff_members b ON a.code = b.staff_id

}

OCDCLOGS_FIND_LOGING_STAFF_ID{
select STAFF.LAST_NAME || ', ' || STAFF.FIRST_NAME STAFF_NAME , STAFF.STAFF_ID , USER_ID from STAFF_MEMBERS STAFF where USER_ID =:USER_NAME order by STAFF_NAME asc
}

OCDCLOGS_FIND_RGEVENTOUTCOME {
select A.DESCRIPTION, A.CODE, coalesce(B.ACTIVE_FLAG, 'N') as ACTIVE_FLAG from ( select description, code from reference_codes where domain = 'OUTCOMES' and code in ( select outcome_code from event_measure_outcomes where event_measure_id in ( select event_measure_id from event_measures)) order by list_seq, code)A left join ( select description, code, 'Y' as Active_Flag from reference_codes where domain = 'OUTCOMES' 
and code in ( select outcome_code from event_measure_outcomes where event_measure_id in ( select event_measure_id from event_measures where event_type = :et and event_sub_type = :est ) and ( active_flag = 'Y' and ( ( :nbtUpdOutcomeFlag = 'Y' and update_on_contact_log = 'Y' ) or :nbtUpdOutcomeFlag = 'N' ) ) ) and active_flag = 'Y' and code != 'PENDING' order by list_seq, code)B on A.description = B.description and A.code = B.code
}

OCDCLOGS_FIND_RGLOCATION {
select A.DESCRIPTION, A.CODE, coalesce(B.ACTIVE_FLAG, 'N')as ACTIVE_FLAG from ( select description description, agy_loc_id CODE from agency_locations order by description, agy_loc_id)A left join ( select description description, agy_loc_id CODE, 'Y' as Active_Flag from agency_locations where agy_loc_id not in ( 'OUT', 'TRN' ) and deactivation_date is null order by description, agy_loc_id)B on A.description = B.description and A.CODE = B.CODE
}

OCDCLOGS_FIND_RGSCHEDULETYPE {
select a.description, a.code, coalesce(b.active_flag, 'N') as active_flag from ( select ref_code.description, ref_code.code from reference_codes ref_code where ( domain = 'EVENTS' and parent_code is null ) order by ref_code.list_seq asc, ref_code.description asc, ref_code.code asc ) a left outer join ( select ref_code.description, ref_code.code, 'Y' as active_flag from reference_codes ref_code where ( domain = 'EVENTS' and ( active_flag = 'Y' ) and parent_code is null ) and exists ( select 'X' from event_measures em where em.event_type = ref_code.code and ( em.active_flag = 'Y' and em.event_type not in ( 'CRT', 'INTAKE', 'CCASE', 'ACP', 'UW', 'DRR', 'SA', 'PACT' ) ) ) order by ref_code.list_seq asc, ref_code.description asc, ref_code.code asc ) b on a.description = b.description and a.code = b.code
}

OCDCLOGS_FIND_RGSCHEDULESUBTYPE {
select A.description, A.code, coalesce(B.ACTIVE_FLAG, 'N')ACTIVE_FLAG from ( select ref_code.description, ref_code.code from reference_codes ref_code where domain in('EVENTS', 'MOVE_RSN', 'INT_SCH_RSN') and parent_code = :eventType order by ref_code.list_seq asc, ref_code.description asc, ref_code.code asc)A left join ( select ref_code.description, ref_code.code, 'Y' ACTIVE_FLAG from reference_codes ref_code where domain = 'EVENTS' 
and parent_code = :eventType and ( active_flag = 'Y' ) and exists ( select 'X' from event_measures em where em.event_sub_type = ref_code.code and ( em.active_flag = 'Y') ) order by ref_code.list_seq asc, ref_code.description asc, ref_code.code asc)B on A.description = B.description and A.code = B.code
}


OCDCLOGS_GETCASE_NOTE_ID{
SELECT NEXTVAL('CASE_NOTE_ID') FROM DUAL
}

OCDCLOGS_FIND_RGNOTESOURCE {
 select DESCRIPTION , CODE from REFERENCE_CODES where domain = 'NOTE_SOURCE' and ACTIVE_FLAG = 'Y' order by LIST_SEQ , DESCRIPTION
}

OCDCLOGS_TYPE_SUB_TYPE{
select
	COUNT(*)
from
	works
where
	work_type = :type
	and work_sub_type = :subType
	and workflow_type = 'CNOTE'
}

OCDCLOGS_CHE_PRI_EXISTS{
select COUNT(*) from staff_members sm, staff_member_roles smr, oms_roles om where sm.staff_id = smr.staff_id and sm.user_id = upper(:userName) and smr.role_id = om.role_id and om.role_code = :lvRoleCode
}

OCDCLOGS_FIND_RGSTAFFNAME {
 	select distinct A.STAFF_NAME STAFF_NAME , A.SAC_STAFF_ID STAFF_ID from V_STAFF_LOCATION_ROLES A , OFFENDER_BOOKINGS B where ( B.OFFENDER_BOOK_ID = :OFFENDERBOOKID and exists ( select 'X' from CASELOAD_AGENCY_LOCATIONS where AGY_LOC_ID 
 	= A.CAL_AGY_LOC_ID and CASELOAD_ID = :CASELOADID ) or ( B.ACTIVE_FLAG = 'Y' and A.CAL_AGY_LOC_ID = B.AGY_LOC_ID and B.AGY_LOC_ID = :AGYLOCID ) or ( B.COMMUNITY_ACTIVE_FLAG = 'Y' and B.COMMUNITY_AGY_LOC_ID = A.CAL_AGY_LOC_ID ) or ( coalesce (B.ACTIVE_FLAG , 'N' ) = 'N' and coalesce (B.COMMUNITY_ACTIVE_FLAG , 'N' ) = 'N' and exists ( select 'X' from CASELOAD_AGENCY_LOCATIONS where AGY_LOC_ID = A.CAL_AGY_LOC_ID and CASELOAD_ID = :CASELOADID ) ) ) order by A.STAFF_NAME asc
}


OCDCLOGS_OFFNOTES_INSERT_OFFENDER_CASE_NOTES {
INSERT INTO OFFENDER_CASE_NOTES(OFFENDER_BOOK_ID ,CONTACT_DATE ,CONTACT_TIME ,CASE_NOTE_TYPE ,CASE_NOTE_SUB_TYPE ,STAFF_ID ,CASE_NOTE_TEXT ,AMENDMENT_FLAG ,IWP_FLAG ,CHECK_BOX1 ,CHECK_BOX2 ,CHECK_BOX3 ,CHECK_BOX4 ,CHECK_BOX5 ,EVENT_ID ,CASE_NOTE_ID ,NOTE_SOURCE_CODE ,DATE_CREATION ,TIME_CREATION ,SEAL_FLAG, OBJECT_TYPE ,OBJECT_ID ,CREATE_DATETIME ,CREATE_USER_ID,MODIFY_DATETIME)
VALUES(:offenderBookId ,:contactDate ,:contactTime ,:caseNoteType ,:caseNoteSubType ,:staffId ,:caseNoteText ,:amendmentFlag ,:iwpFlag ,:checkBox1 ,:checkBox2 ,:checkBox3 ,:checkBox4 ,:checkBox5 ,:eventId ,:caseNoteId ,:noteSourceCode ,:dateCreation ,:timeCreation ,:sealFlag ,:objectType ,:objectId ,current_timestamp,:createUserId,current_timestamp)
}

OCDCLOGS_GET_STAFF_ID{
SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID =:USER_NAME
}

OCDCLOGS_OFFNOTES_UPDATE_OFFENDER_CASE_NOTES {
	UPDATE OFFENDER_CASE_NOTES set CONTACT_DATE = :contactDate , CONTACT_TIME = :contactTime , AMENDMENT_FLAG = :amendmentFlag , IWP_FLAG = :iwpFlag , CHECK_BOX1 = :checkBox1 , CHECK_BOX2 = :checkBox2 , CHECK_BOX3 = :checkBox3 , CHECK_BOX4 = :checkBox4 , CHECK_BOX5 = :checkBox5 , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId where CASE_NOTE_ID = :caseNoteId AND OFFENDER_BOOK_ID = :offenderBookId
}

OCDCLOGS_OFFNOTES_DELETE_OFFENDER_CASE_NOTES { 
	DELETE FROM OFFENDER_CASE_NOTES where OFFENDER_BOOK_ID=:offenderBookId AND CASE_NOTE_ID  = :caseNoteId

}

OCDCLOGS_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES {
select
	*,(
	select
		last_name || ',' || ' ' || first_name  
	from
		staff_members sm
	where
		staff_id = ois.in_charge_staff_id) in_charge_staff_name,(
	select
		count(*)
	from
		INTERNET_ADDRESSES
	where
		owner_id = (
		select
			ROOT_OFFENDER_ID
		from
			v_header_block
		where
			offender_book_id = :offenderBookId)) email_address_count,
	(
	select
		count(*)
	from
		PHONES
	where
		owner_id = (
		select
			TRIM(offender_id_display,'0')
		from
			v_header_block
		where
			offender_book_id = :offenderBookId)::bigint  and format = 'MOB' and owner_class='OFF' ) phone_number_count,
	(
	select
		coalesce (CANCEL_FLAG,
		'N')
	from
		(
		select
			outcome_code,
			CANCEL_FLAG
		from
			event_measure_outcomes
		where
			event_measure_id in (
			select
				event_measure_id
			from
				event_measures
			where
				event_type = ois.event_Type
				and event_sub_type = ois.event_Sub_Type ) )a
	where
		a.outcome_code =ois.event_OutCome) as out_come_flag
from
	offender_ind_schedules ois where event_class in ('COMM') AND
}


OCDCLOGS_OFF_SCH_STAFF_ID {
select
	staff_id
from
	staff_members
where
	last_name =:lName
	and first_name = :fName
	limit 1
}

OCDCLOGS_SCH_INSERT_OFFENDER_IND_SCHEDULES{
 INSERT INTO OFFENDER_IND_SCHEDULES (event_id, offender_book_id, event_date, start_time, end_time, event_class, event_type, event_sub_type, event_status, comment_text, hidden_comment_text, application_date, application_time, parent_event_id, agy_loc_id, to_agy_loc_id, to_internal_location_id, from_city, to_city, crs_sch_id, order_id, sentence_seq, outcome_reason_code, judge_name, check_box_1, check_box_2, in_charge_staff_id, credited_hours, report_in_date, piece_work, engagement_code, understanding_code, details, credited_work_hour, agreed_travel_hour, unpaid_work_supervisor, unpaid_work_behaviour, unpaid_work_action, sick_note_received_date, sick_note_expiry_date, court_event_result, unexcused_absence_flag, escort_code, confirm_flag, direction_code, to_city_code, from_city_code, off_prgref_id, in_time, out_time, performance_code, temp_abs_sch_id, reference_id, transport_code, contact_person_name, to_address_owner_class, to_address_id, return_date, return_time, prov_state_code,create_datetime,modify_datetime,create_user_id,sms_schedule_hours_before,email_schedule_hours_before,email_flag,sms_flag,series_id,event_outcome)
 VALUES (:eventId, :offenderBookId, :eventDate, :startTime, :endTime, :eventClass, :eventType, :eventSubType, :eventStatus, :commentText, :hiddenCommentText, :applicationDate, :applicationTime, :parentEventId, :agyLocId, :toAgyLocId, :toInternalLocationId, :fromCity, :toCity, :crsSchId, :orderId, :sentenceSeq, :outcomeReasonCode, :judgeName, :checkBox1, :checkBox2, :inChargeStaffId, :creditedHours, :reportInDate, :pieceWork, :engagementCode, :understandingCode, :details, :creditedWorkHour, :agreedTravelHour, :unpaidWorkSupervisor, :unpaidWorkBehaviour, :unpaidWorkAction, :sickNoteReceivedDate, :sickNoteExpiryDate, :courtEventResult, :unexcusedAbsenceFlag, :escortCode, :confirmFlag, :directionCode, :toCityCode, :fromCityCode, :offPrgrefId, :inTime, :outTime, :performanceCode, :tempAbsSchId, :referenceId, :transportCode, :contactPersonName, :toAddressOwnerClass, :toAddressId, :returnDate, :returnTime, :provStateCode,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,:createUserId,:smsScheduleHoursBefore,:emailScheduleHoursBefore,:emailFlag,:smsFlag,:seriesId,:eventOutcome)
}
OCDCLOGS_OFF_SCH_EVENT_ID{
	select NEXTVAL('EVENT_ID') from DUAL
}
OCDCLOGS_OFF_SCH_POST_INSERT
{
SELECT
    check_sum
FROM
    v_offender_all_schedules
WHERE
    event_id = :EVENT_ID
}

OCDCLOGS_OFFSCH_DELETE_V_OFFENDER_ALL_SCHEDULES { 
	DELETE FROM V_OFFENDER_ALL_SCHEDULES
}


OCDCLOGS_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_CASE_NOTES O WHERE O.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDCLOGS_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_OFFENDER_ALL_SCHEDULES V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDCLOGS_GET_M_NAME{
SELECT
    module_name
FROM
    works ws
WHERE
    ws.workflow_type = 'CNOTE'
    AND ws.work_type = :CASENOTETYPE
    AND ws.work_sub_type = :CASENOTESUBTYPE
}

OCDCLOGS_FIND_LOV_OUTCOME
{
SELECT RC.DESCRIPTION DESCRIPTION, RC.CODE CODE, RC.LIST_SEQ LIST_SEQ FROM REFERENCE_CODES RC WHERE DOMAIN = 'OUTCOMES' ORDER BY LIST_SEQ, CODE
}

GET_OLD_DATA_V_OFFENDER_ALL_SCHEDULES_2{
SELECT * FROM offender_ind_schedules WHERE EVENT_ID =:EVENT_ID
}

OCDCLOGS_GET_DEFAULT_CASE_NOTE_TEXT {
select CASE_NOTE_TEXT from WORKS where WORKFLOW_TYPE='CNOTE' and  WORK_TYPE=:caseNoteType and WORK_SUB_TYPE=:caseNoteSubType
}

OCDCLOGS_CHECK_SCHEDULE_CONFLICTS{
SELECT * FROM OFFENDER_IND_SCHEDULES WHERE OFFENDER_BOOK_ID = :offenderBookID AND EVENT_DATE = :eventDate AND TO_AGY_LOC_ID = :toAgyLocId and event_status = 'SCH'
}

OCDCLOGS__SCHEDULE_FIND_EVENT_MEASURES {
select coalesce(EMAIL_FLAG, 'N') as EMAIL_FLAG, coalesce(SMS_FLAG, 'N') as SMS_FLAG, ( select count(*) from INTERNET_ADDRESSES where owner_id = ( select ROOT_OFFENDER_ID from v_header_block where offender_book_id = :offenderBookId) and owner_class='OFF') as email_address_count, ( select COUNT(*) from PHONES where owner_id = ( select offender_id from v_header_block where offender_book_id = :offenderBookId ) and format = 'MOB' and owner_class='OFF' ) as phone_number_count, coalesce(NON_ASSOCIATION_FLAG, 'N') as NON_ASSOCIATION_FLAG from EVENT_MEASURES where EVENT_TYPE =:eventType and EVENT_SUB_TYPE =:eventSubType
}

OCDCLOGS_GET_CANCEL_FLAG_FOR_OUTCOME {
select coalesce (CANCEL_FLAG, 'N') as active_flag from ( select outcome_code, CANCEL_FLAG from event_measure_outcomes where event_measure_id in ( select event_measure_id from event_measures where event_type = :eventType and event_sub_type = :eventSubType ) )a where outcome_code =:eventOutCome
}

OCDCLOGS_CHECK_CASENOTE_SUBTYPE{
select (case when count(*) > 0 then 'Y' else 'N' end) as create_flag from (select
	RC.DESCRIPTION,
	W.WORK_SUB_TYPE CODE,
	case
		when ( (W.WORK_TYPE = :CASENOTETYPE
		and W.MANUAL_SELECT_FLAG = 'Y'
		and W.ACTIVE_FLAG = 'Y')) then 1
		else 0
	end SEQVALUE,
	cnp.create_flag,
	cnp.view_flag
from
	WORKS W,
	REFERENCE_CODES RC,
	(
	select * from STAFF_MEMBER_ROLES
	where staff_id = (
		select staff_id from STAFF_MEMBERS
		where LOWER(user_ID) = LOWER(:user)) ) as roles,
	case_note_permissions cnp
where
	WORKFLOW_TYPE = 'CNOTE'
	and RC.DOMAIN = 'TASK_SUBTYPE'
	and RC.CODE = W.WORK_SUB_TYPE
	and W.WORK_TYPE = :CASENOTETYPE
	and w.work_id = cnp.work_id
	and cnp.role_id = roles.ROLE_ID
order by
	DESCRIPTION ,
	CODE ) a where a.CODE = :workSubType and a.create_flag = 'Y'
}
OCDCLOGS_ALL_CORPORATES
{
select c.corporate_id::varchar as CODE  ,c.corporate_name as DESCRIPTION from corporates c order by create_datetime desc
}
OCDCLOGS_GET_AGENCY_LOCATION_DESCRIPTION {
select description from agency_locations al where agy_loc_id = :agyLocId
}
