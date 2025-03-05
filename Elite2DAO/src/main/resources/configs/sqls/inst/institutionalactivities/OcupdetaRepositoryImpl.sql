
OCUPDETA_GET_UNPAID_ATTENDANCE_RECORDS_QUERY {
select * from(
select  voca.event_id ,
(select program_code from program_services ps where program_id = voca.program_id) programDescription,
(select description from course_activities ca where crs_acty_id = voca.crs_acty_id) activityDescription,
coalesce (oca.pay_actual_rate ,0) pay_actual_rate,coalesce (oca.pay_actual_amount ,0) pay_actual_amount,
oca.pay_system_unit ,coalesce(oca.pay_system_rate,0) pay_system_rate,coalesce(oca.pay_actual_rate,0)*coalesce(oca.pay_hours,0) amount,oca.pay_hours ,voca.*
 FROM V_offender_course_attendances voca ,offender_course_attendances oca WHERE voca.event_id is not null and voca.event_id  = oca.event_id and voca.event_type = 'INST_ACT'
 and oca.pay_hours is not null and oca.pay_batch_id is null
union 
select  voca.event_id ,
(select program_code from program_services ps where program_id in (select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id in (select offender_prg_obligation_id from offender_program_profiles where off_prgref_id =voca.off_prgref_id)
)) programDescription,
(SELECT description FROM COURSE_ACTIVITIES WHERE crs_acty_id in (SELECT parent_crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id = voca.crs_acty_id)) activityDescription,
coalesce (oca.pay_actual_rate ,0) pay_actual_rate,coalesce (oca.pay_actual_amount ,0) pay_actual_amount,
oca.pay_system_unit ,coalesce(oca.pay_system_rate,0) pay_system_rate,coalesce(oca.pay_actual_rate,0)*coalesce(oca.pay_hours,0) amount,oca.pay_hours ,voca.*
 FROM V_offender_course_attendances voca ,offender_course_attendances oca WHERE voca.event_id is not null and voca.event_id  = oca.event_id and voca.event_type = 'ACP'
 and voca.program_id is not null and voca.crs_acty_id is not null
 and oca.pay_hours is not null and oca.pay_batch_id is null )a   where
}

OCUPDETA_GENERATE_PAYAMOUNT {
 update OFFENDER_COURSE_ATTENDANCES set PAY_ACTUAL_RATE =:payActualRate , PAY_ACTUAL_AMOUNT =:payActualAmount , hidden_comment_text =:hiddenCommentText , modify_user_id =:modifyUserId , modify_datetime = current_timestamp where EVENT_ID =:eventId
}

OCUPDETA_GET_OFFENDER_ALLOWANCE_RECORDS_CHILD_QUERY {
select
	coalesce (apd.pay_actual_rate ,apd.allowance_version_rate)::int allowance_version_rate,
	coalesce (apd.pay_actual_amount ,apd.allowance_version_rate)::int pay_actual_amount,
	apd.detail_id ,
	apd.comment_text,
	apd.allowance_version_max_unit::int ,
	apd.off_allowance_day,
	o.last_name,
	o.offender_id_display,
	o.first_name ,
	apd.off_allowance_id ,
	apd.allowance_type,
	apd.offender_book_id 
from
	off_alowances_pay_details apd,
	offenders o,
	offender_bookings ob
where
	apd.offender_book_id = ob.offender_book_id
	and ob.offender_id = o.offender_id and apd.pay_flag = 'N' and
}

OCUPDETA_GENERATE_PAY_ALLOW_AMOUNT{
 update off_alowances_pay_details set PAY_ACTUAL_RATE =:payActualRate , PAY_ACTUAL_AMOUNT =:payActualAmount, comment_text= :hiddenCommentText  , modify_user_id =:modifyUserId , modify_datetime = current_timestamp where detail_id =:detailId
}

OCUPDETA_GET_OFFENDER_ALLOWANCE_GENERATE_RECORDS_CHILD_QUERY {
select
	coalesce (apd.pay_actual_rate ,apd.allowance_version_rate)::int allowance_version_rate,
	coalesce (apd.pay_actual_amount ,apd.allowance_version_rate)::int pay_actual_amount,
	apd.detail_id ,
	apd.comment_text,
	apd.allowance_version_max_unit::int ,
	apd.off_allowance_day,
	o.last_name,
	o.offender_id_display,
	o.first_name ,
	apd.off_allowance_id ,
	apd.allowance_type,
	apd.offender_book_id 
from
	off_alowances_pay_details apd,
	offenders o,
	offender_bookings ob
where
    apd.pay_flag in('N') and
	apd.offender_book_id = ob.offender_book_id
	and ob.offender_id = o.offender_id and 
}