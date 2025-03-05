GET_ACT_OUTCOME_FLAG{
select
	pay_flag,
	authorised_absence_flag
from
	payable_attendance_outcomes
where
	outcome_code = :p_outcome_code
	and event_type = :p_event_type
	and :p_event_date between start_date and coalesce(end_date,
	date_trunc('D', sysdate()));
}

GET_PROVIDER_TYPE{
select
	'Y'
from
	offender_course_attendances
where
	agy_loc_id = :p_agy_loc_id
	and event_class in ( 'INT_MOV', 'EXT_MOV' )
	limit 1
}

GET_PAY_LOCK{
select
	case
		when coalesce(txn_id::text, '') = '' then 'N'
		else 'Y'
	end
from
	offender_course_attendances
where
	event_id = :p_event_id;
}