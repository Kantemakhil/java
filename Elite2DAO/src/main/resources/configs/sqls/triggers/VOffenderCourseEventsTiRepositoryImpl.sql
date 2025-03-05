V_OFFENDER_COURSE_EVENTS_TI_OFFENDER_COURSE_ATTENDANCES1{
insert
	into
	OFFENDER_COURSE_ATTENDANCES(EVENT_ID,
	event_class,
	offender_book_id,
	event_date,
	off_prgref_id,
	event_status,
	crs_acty_id,
	event_sub_type,
	event_type,
	Start_Time,
	End_Time,
	in_time,
	out_time,
	event_outcome,
	agy_loc_ID,
	comment_text,
	piece_work,
	performance_code,
	outcome_reason_code,
	to_internal_location_id,
	to_address_id,
	to_agy_loc_id,
	credited_hours,
	agreed_travel_hour,
	engagement_code,
	understanding_code,
	behaviour_code,
	supervisor_staff_id,
	unexcused_absence_flag,
	session_no,
	reference_id,
	direction_code,
	crs_appt_id,
	create_user_id,
	create_datetime)
select
	coalesce(:eventId, nextval('event_id')),
	case
		when RC.Parent_Code = 'BOTH' then TAG_PRG_PRG_EVENT_CLASS(opp.off_prgref_id)
		else RC.Parent_Code
	end,
	opp.offender_book_id,
	:eventDate,
	opp.off_prgref_id,
	coalesce(:eventStatus, 'SCH'),
	Opp.crs_acty_ID,
	ca.Course_activity_type,
	coalesce(ps.program_category, tag_prg_prg_event_type(ps.program_id)),
	:startTime,
	:endTime,
	coalesce(:inTime::timestamp,:startTime::timestamp),
	coalesce(:outTime::timestamp,:endTime::timestamp),
	:eventOutcome,
	ob.agy_loc_id,
	:commentText,
	:pieceWork,
	:performanceCode,
	:outcomeReasonCode,
	ca.internal_location_id,
	ca.services_address_ID,
	case
		when ca.provider_party_class = 'AGY' then provider_party_code
		else CA.agy_loc_id
	end,
	:creditedHours,
	opp.agreed_travel_hour,
	:engagementCode,
	:understandingCode,
	:behaviourCode,
	:supervisorStaffId,
	:unexcusedAbsenceFlag,
	:sessionNo,
	:referenceId,
	:directionCode,
	:crsApptId,
	:createUserId,
	current_timestamp
from
	reference_codes rc,
	program_services ps,
	offender_program_profiles opp,
	offender_bookings ob,
	course_activities ca
left outer join addresses addr on
	(ca.Services_Address_ID = Addr.Address_ID)
where
	ca.crs_acty_id = opp.crs_acty_id
	and
  opp.Offender_Program_status IN('ALLOC','REF')
	and
  ca.program_id = ps.program_id
	and
  RC.domain = 'PS_CATEGORY'
	and
  RC.code = coalesce(ps.program_category, tag_prg_prg_event_type(ps.program_id))
	and
  ca.active_flag = 'Y'
	and
  ob.offender_book_id = opp.offender_book_id
	and
  opp.off_prgref_id = :offPrgrefId
}
  
V_OFFENDER_COURSE_EVENTS_TI_OFFENDER_COURSE_ATTENDANCES2{
insert into OFFENDER_COURSE_ATTENDANCES(EVENT_ID, event_class, offender_book_id, event_date, off_prgref_id, event_status, Reference_id, crs_sch_id, crs_appt_id, crs_acty_id, event_sub_type, event_type, Start_Time, End_Time, in_time, out_time, event_outcome, agy_loc_ID, comment_text, piece_work, performance_code, outcome_reason_code, to_agy_loc_id, to_internal_location_id, engagement_code, understanding_code, credited_hours, agreed_travel_hour, behaviour_code, supervisor_staff_id, unexcused_absence_flag, to_address_id, direction_code, session_no, create_datetime, modify_datetime, create_user_id) values (NEXTVAL('Event_ID'), :eventClass, :offenderBookId, :eventDate, :offPrgrefId, :eventStatus, :referenceId, :crsSchId, :crsApptId, :crsActyId, :eventSubType, :eventType, :startTime, :endTime, :inTime, :outTime, :eventOutcome, :agyLocId, :commentText, :pieceWork, :performanceCode, :outcomeReasonCode, :toAgyLocId, :toInternalLocationId, :engagementCode, :understandingCode, :creditedHours, :agreedTravelHour, :behaviourCode, :supervisorStaffId, :unexcusedAbsenceFlag, :toAddressId, :directionCode, :sessionNo, current_timestamp, null , :createUserId)
}