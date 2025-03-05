
OCMSCHPR_FIND_RGREMAINING {
 	SELECT
    ps.description   DESCRIPTION,
    ca.list_seq::text        CODE,
    ca.list_seq LIST_SEQ
FROM
    program_services    ps,
    course_activities   ca
WHERE
    ca.program_id = ps.program_id
    AND ca.parent_crs_acty_id = :crs_acty_id
    AND ca.list_seq > :last_list_seq
ORDER BY
    ca.LIST_SEQ
}

OCMSCHPR_CRSACT_FIND_COURSE_ACTIVITIES {
 	select crs_acty_id,
CASELOAD_ID, 
AGY_LOC_ID,
DESCRIPTION, CAPACITY,
active_flag,
EXPIRY_DATE, 
SCHEDULE_START_DATE, 
SCHEDULE_END_DATE, 
CASELOAD_TYPE,
SERVICES_ADDRESS_ID,
PROGRAM_ID,
PARENT_CRS_ACTY_ID, 
INTERNAL_LOCATION_ID, 
PROVIDER_PARTY_CLASS,
PROVIDER_PARTY_ID, 
PROVIDER_PARTY_CODE,
BENEFICIARY_NAME,
BENEFICIARY_CONTACT,
LIST_SEQ, 
PLACEMENT_CORPORATE_ID,
COMMENT_TEXT,
AGENCY_LOCATION_TYPE,
PROVIDER_TYPE,
BENEFICIARY_TYPE, 
PLACEMENT_TEXT, CODE,
HOLIDAY_FLAG,
COURSE_CLASS,
COURSE_ACTIVITY_TYPE, 
CREATE_DATETIME, 
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID,
IEP_LEVEL,
NO_OF_SESSIONS,
SESSION_LENGTH, 
MULTI_PHASE_SCHEDULING_FLAG, 
SCHEDULE_NOTES,
SEAL_FLAG,
ALLOW_DOUBLE_BOOK_FLAG
 from COURSE_ACTIVITIES where crs_acty_id=:crs_acty_id
}

OCMSCHPR_CRSACT_POST_QUERY{
	select
	ps.program_code service_code,
	ps.description service_description,
	ps.program_category service_category,
	ca.code offering_code,
	ca.description offering_description,
	ca.active_flag active_flag,
	ca.schedule_start_date offering_start_date,
	ca.schedule_end_date offering_end_date
from
	PROGRAM_SERVICES ps,
	COURSE_ACTIVITIES ca
where
	ca.program_id = ps.program_id
	and ca.crs_acty_id = (with recursive cte as (
	select
		ca1.crs_acty_id,
		parent_crs_acty_id
	from
		COURSE_ACTIVITIES ca1
	where
		ca1.crs_acty_id = :p_crs_acty_id
union all
	select
		ca1.crs_acty_id,
		ca1.parent_crs_acty_id
	from
		COURSE_ACTIVITIES ca1
	join cte c on
		(c.parent_crs_acty_id = ca1.crs_acty_id ) )
	select
		crs_acty_id
	from
		cte
	where
		coalesce(parent_crs_acty_id::text, '') = '' )

}

OCMSCHPR_CRSACT_ACTUAL_SESSIONS {
	select
	COUNT(*)
from
	course_schedules
where
	schedule_status = 'SCH'
	and coalesce(catch_up_crs_sch_id::text, '') = ''
	and crs_acty_id in (with recursive cte as (
	select
		crs_acty_id
	from
		course_activities
	where
		crs_acty_id = :p_crs_acty_id
union all
	select
		ca.crs_acty_id
	from
		course_activities ca
	join cte c on
		(c.crs_acty_id = ca.parent_crs_acty_id ) )
	select
		*
	from
		cte )
 
}

OCMSCHPR_CRSACT_PROGRAM_SERVICES{
	 SELECT * FROM PROGRAM_SERVICES WHERE program_id = :p_program_id
}

OCMSCHPR_CRSACT_GET_TPTAL_SESSIONS{
	SELECT SUM ( no_of_sessions )
           FROM COURSE_ACTIVITIES
          WHERE parent_crs_acty_id = :p_program_instance_id
}


OCMSCHPR_CRSACT_UPDATE_COURSE_ACTIVITIES {
	UPDATE COURSE_ACTIVITIES set CASELOAD_ID=:caseloadId, AGY_LOC_ID=:agyLocId, DESCRIPTION=:description, CAPACITY=:capacity, active_flag=:activeFlag, EXPIRY_DATE=:expiryDate, SCHEDULE_START_DATE=:scheduleStartDate, SCHEDULE_END_DATE=:scheduleEndDate, CASELOAD_TYPE=:caseloadType, SERVICES_ADDRESS_ID=:servicesAddressId, PROGRAM_ID=:programId, PARENT_CRS_ACTY_ID=:parentCrsActyId, INTERNAL_LOCATION_ID=:internaLocationId, PROVIDER_PARTY_CLASS=:providerPartyClass, PROVIDER_PARTY_ID =:providerPartyId, PROVIDER_PARTY_CODE =:providerPartyCode, BENEFICIARY_NAME=:beneficiaryName, BENEFICIARY_CONTACT=:beneficiaryContact, LIST_SEQ=:listSeq, PLACEMENT_CORPORATE_ID=:placementCorporateId, COMMENT_TEXT=:commentText, AGENCY_LOCATION_TYPE=:agencyLocationType, PROVIDER_TYPE=:providerType, BENEFICIARY_TYPE=:beneficiaryType, PLACEMENT_TEXT=:placementText, CODE=:code, HOLIDAY_FLAG=:holidayFlag, COURSE_CLASS=:courseClass, COURSE_ACTIVITY_TYPE=:courseActivityType, MODIFY_DATETIME=current_timestamp, MODIFY_USER_ID=:modifyUserId, IEP_LEVEL=:iepLevel, NO_OF_SESSIONS=:noOfSessions, SESSION_LENGTH=:sessionLength, MULTI_PHASE_SCHEDULING_FLAG=:multiPhaseSchedulingFlag, SCHEDULE_NOTES=:scheduleNotes, SEAL_FLAG=:sealFlag, ALLOW_DOUBLE_BOOK_FLAG=:allowDoubleBookFlag where CRS_ACTY_ID=:crsActyId
}

OCMSCHPR_CRSACT_DELETE_COURSE_ACTIVITIES { 
	DELETE FROM COURSE_ACTIVITIES
}

OCMSCHPR_VACPSCHEDULES_GET_SESSION_NO_ONE{
	SELECT MIN ( vas.session_no )
           FROM v_acp_schedules vas
          WHERE vas.phase_instance_id = :p_phase_instance_id
            AND vas.schedule_status = 'CANC'
            AND vas.catch_up_session_flag = 'N'
            AND NOT EXISTS (
                   SELECT 'X'
                     FROM v_acp_schedules vas2
                    WHERE vas2.phase_instance_id = :p_phase_instance_id
                      AND vas2.session_no = vas.session_no
                      AND vas2.schedule_status = 'SCH'
                      AND vas2.catch_up_session_flag = 'N' )
}


OCMSCHPR_VACPSCHEDULES_GET_SESSION_NO_TWO{
	  select
	COUNT(*)
from
	course_schedules
where
	schedule_status = 'SCH'
	and coalesce(catch_up_crs_sch_id::text, '') = ''
	and crs_acty_id in (with recursive cte as (
	select
		crs_acty_id
	from
		course_activities
	where
		crs_acty_id = :p_crs_acty_id
union all
	select
		ca.crs_acty_id
	from
		course_activities ca
	join cte c on
		(c.crs_acty_id = parent_crs_acty_id ) )
	select
		*
	from
		cte )

}

OCMSCHPR_VACPSCHEDULES_FIND_V_ACP_SCHEDULES {
 	SELECT
    program_id,
    program_code,
    program_desc,
    program_instance_id,
    program_instance_code,
    program_instance_desc,
    phase_code,
    phase_description,
    phase_provider_party_class,
    phase_provider_party_id,
    phase_provider_party_code,
    phase_provider_name,
    phase_instance_code,
    phase_instance_id,
    phase_list_seq,
    phase_session_length,
    phase_instance_desc,
    module_instance_id,
    module_list_seq,
    module_instance_desc,
    crs_sch_id,
    schedule_date,
    start_time,
    end_time,
    session_no,
    catch_up_session_flag,
    internal_location_desc,
    schedule_status
FROM
    v_acp_schedules 
}



OCMSCHPR_VACPSCHEDULES_POST_QUERY{
	select TO_CHAR(to_timestamp(:schedulDate,'RR-MM-DD HH24:MI:SS.FF'), 'Day') from dual
}

OCMSCHPR_VACPSCHEDULES_UPDATE_CRS_ACTY_CHECKSUM {
	UPDATE COURSE_ACTIVITIES
	         SET modify_datetime = current_timestamp ,
	         MODIFY_USER_ID=:modifyUserId	
	       WHERE crs_acty_id = :p_crs_acty_id
}

OCMSCHPR_VACPSCHEDULES_CHKALLOCATIONEXISTS{
	select
	count(*)
where
	exists ((
	select
		'Y'
	from
		offender_program_profiles
	where
		crs_acty_id in (with recursive cte as (
		select
			crs_acty_id,
			ca.course_class
		from
			course_activities ca
		where
			crs_acty_id = :p_crs_acty_id
	union all
		select
			ca.crs_acty_id,
			ca.course_class
		from
			course_activities ca
		join cte c on
			(c.crs_acty_id = parent_crs_acty_id) )
		select
			count(*)
		from
			cte
		where
			course_class in ('CRS_PH', :p_crs_class) )
			and profile_class = 'CRS'
			and offender_program_status in ('ALLOC', 'END')))

}

OCMSCHPR_CRSSCHEDULERUL_FIND_COURSE_SCHEDULE_RULES {
 	SELECT
    course_schedule_rule_id,
    crs_acty_id,
    week_no,
    monday_flag,
    tuesday_flag,
    wednesday_flag,
    thursday_flag,
    friday_flag,
    saturday_flag,
    sunday_flag,
    start_time,
    end_time,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id,
    seal_flag,
    capacity
FROM
    course_schedule_rules where  crs_acty_id=:crs_acty_id order by week_no,start_time
}

OCMOFACC_COURSESCHEDULERULE_PREINSERT{
	SELECT nextval('course_schedule_rule_id') FROM DUAL
}
OCMSCHPR_CRSSCHEDULERUL_INSERT_COURSE_SCHEDULE_RULES {
insert into COURSE_SCHEDULE_RULES(COURSE_SCHEDULE_RULE_ID, CRS_ACTY_ID, WEEK_NO, MONDAY_FLAG, TUESDAY_FLAG, WEDNESDAY_FLAG, THURSDAY_FLAG, FRIDAY_FLAG, SATURDAY_FLAG, SUNDAY_FLAG, START_TIME, END_TIME, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG, CAPACITY) values(:courseScheduleRuleId, :crsActyId, :weekNo, :mondayFlag, :tuesdayFlag, :wednesdayFlag, :thursdayFlag, :fridayFlag, :saturdayFlag, :sundayFlag, :startTime, :endTime, current_timestamp, :createUserId, null, :sealFlag, :capacity)
}

OCMSCHPR_CRSSCHEDULERUL_UPDATE_COURSE_SCHEDULE_RULES {
	update COURSE_SCHEDULE_RULES set CRS_ACTY_ID =:crsActyId, WEEK_NO =:weekNo, MONDAY_FLAG =:mondayFlag, TUESDAY_FLAG =:tuesdayFlag, WEDNESDAY_FLAG =:wednesdayFlag, THURSDAY_FLAG =:thursdayFlag, FRIDAY_FLAG =:fridayFlag, SATURDAY_FLAG =:saturdayFlag, SUNDAY_FLAG =:sundayFlag, START_TIME =:startTime, END_TIME =:endTime, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID =:modifyUserId, SEAL_FLAG =:sealFlag, CAPACITY =:capacity where COURSE_SCHEDULE_RULE_ID =:courseScheduleRuleId
}

OCMSCHPR_CRSSCHEDULERUL_DELETE_COURSE_SCHEDULE_RULES { 
	DELETE FROM COURSE_SCHEDULE_RULES where COURSE_SCHEDULE_RULE_ID=:courseScheduleRuleId
}


OCMSCHPR_CRS_ACT_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM COURSE_SCHEDULE_RULES C WHERE C.CRS_ACTY_ID = :CRSACTYID
}


OCMSCHPR_BUILD_BLOCK_CHK_ANY_TO_BUILD{
	SELECT count(*)
           FROM course_activities
          WHERE parent_crs_acty_id = :p_parent_crs_acty_id
            AND list_seq > :p_last_list_seq

}
