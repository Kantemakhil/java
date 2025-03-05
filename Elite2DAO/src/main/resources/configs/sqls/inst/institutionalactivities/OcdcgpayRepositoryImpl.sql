
OCDCGPAY_GET_UNPAID_ATTENDANCE_RECORDS_QUERY {
select distinct(program_id),offender_id_display,last_name,first_name,offender_book_id,et event_type,programDescription,activityDescription,SUM(pay_actual_amount) pay_actual_amount
,crs_acty_id,off_prgref_id,parent_crs_acty_id
from(
select  voca.event_id ei,voca.event_type et,offender_id_display,last_name,first_name,voca.offender_book_id,voca.program_id,voca.event_date,voca.crs_acty_id,voca.off_prgref_id,
null parent_crs_acty_id,
(select program_code from program_services ps where program_id = voca.program_id) programDescription,
(select description from course_activities ca where crs_acty_id = voca.crs_acty_id) activityDescription,coalesce (oca.pay_actual_amount ,0) pay_actual_amount
 FROM V_offender_course_attendances voca ,offender_course_attendances oca WHERE voca.event_id is not null and voca.event_id  = oca.event_id and voca.event_type = 'INST_ACT'
 and oca.pay_hours is not null and oca.pay_batch_id is null
union 
select  voca.event_id ei,voca.event_type et,offender_id_display,last_name,first_name,voca.offender_book_id,voca.program_id,voca.event_date,voca.crs_acty_id,voca.off_prgref_id,
(SELECT crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id in (SELECT parent_crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id = voca.crs_acty_id)) parent_crs_acty_id,
(select program_code from program_services ps where program_id in (select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id in (select offender_prg_obligation_id from offender_program_profiles where off_prgref_id =voca.off_prgref_id)
)) programDescription,
(SELECT description FROM COURSE_ACTIVITIES WHERE crs_acty_id in (SELECT parent_crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id = voca.crs_acty_id)) activityDescription,coalesce (oca.pay_actual_amount ,0) pay_actual_amount
FROM V_offender_course_attendances voca ,offender_course_attendances oca WHERE voca.event_id is not null and voca.event_id  = oca.event_id and voca.event_type = 'ACP'
 and voca.program_id is not null and voca.crs_acty_id is not null
and oca.pay_hours is not null and oca.pay_batch_id is null
)a    where
}

OCDCGPAY_GENERATE_PAY {
  update OFFENDER_COURSE_ATTENDANCES set PAY_FLAG = 'Y', PAY_BATCH_ID =:payBatchId, modify_datetime = current_timestamp , modify_user_id =:modifyUserId where EVENT_ID =:eventId
}

OCDCGPAY_GET_BATH_ID {

select nextval('pay_batch_id_seq') 

}

OCDCGPAY_GET_DATES {
select
	(starttime-interval '1 week')::date as STARTTIME,
	(endtime-interval '1 week')::date as ENDTIME
from
	(
	select
		case
			when extract(ISODOW
		from
			current_timestamp::date) < DAYINDEX then cast(date_trunc('week', current_timestamp::date) as date) - 8 + DAYINDEX
			else cast(date_trunc('week', current_timestamp::date) as date) - 1 + DAYINDEX
		end starttime
    ,
		case
			when extract(ISODOW
		from
			current_timestamp::date) < DAYINDEX then cast(date_trunc('week', current_timestamp::date) as date) - 8 + DAYINDEX + 6
			else cast(date_trunc('week', current_timestamp::date) as date) - 1 + DAYINDEX + 6
		end endtime
	from
		(
		select
			case
				when pay_cycle_start_day = 'SUN' then 0
				when pay_cycle_start_day = 'MON' then 1
				when pay_cycle_start_day = 'TUE' then 2
				when pay_cycle_start_day = 'WED' then 3
				when pay_cycle_start_day = 'THU' then 4
				when pay_cycle_start_day = 'FRI' then 5
				when pay_cycle_start_day = 'SAT' then 6
				else null
			end DAYINDEX
		from
			programs_pay_settings)A)A
}

OCDCGPAY_GET_SYSTEM_PAY_RATE {

select case when 'INST_ACT' =:event_type and actrate is not null then actrate
 when 'INST_ACT' =:event_type and orgrate is NOT null then orgrate
 when 'ACP' =:event_type and acpActrate is not null then acpActrate
 when 'ACP' =:event_type and acpOrgRate is not null then acpOrgRate
 else 0 end pay_system_rate from (
select 
(select rate from programs_pay_compensation where PROGRAM_ID = :program_id and crs_acty_id = :crs_acty_id) as actrate,
(select rate from programs_pay_compensation where PROGRAM_ID = :program_id and coalesce(crs_acty_id,0) = 0) as orgrate,
(select rate from programs_pay_compensation where PROGRAM_ID = (select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id in (select offender_prg_obligation_id from offender_program_profiles where off_prgref_id =:off_prgref_id)
) and crs_acty_id =:crs_acty_id) as acpActrate,
(select rate from programs_pay_compensation where PROGRAM_ID = (select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id in (select offender_prg_obligation_id from offender_program_profiles where off_prgref_id =:off_prgref_id)
) and coalesce(crs_acty_id,0) = 0) as acpOrgRate)A
}

OCDCGPAY_INSERT_PRG_PAY_BATCHES {
insert into prg_pay_batches(batch_id, from_date, to_date, batch_pay_amount, batch_generated_datetime, create_datetime, create_user_id) 
values(:batchId, :fromDate, :toDate, :batchPayAmount, current_timestamp, current_timestamp , :createUserId)
}

OCDCGPAY_INSET_PRG_PAY_BATCH_OFF_CRS {
insert into prg_pay_batch_off_crs(batch_id, offender_book_id, crs_acty_id, crs_pay_amount, create_datetime, create_user_id)
values(:payBatchId, :offenderBookId, :crsActyId, :payActualAmount, current_timestamp , :createUserId)
}
OCDCGPAY_GET_OFFENDER_ALLOWANCE_RECORDS_QUERY {
select
	offender_book_id,
	offender_id_display,
	allowId as off_allowance_id,
	last_name,
	first_name,
	unit as allowance_version_max_unit, 
	"type",
	code as allowance_type,
	rate as prev_allow_version_rate,
	amount as allowance_version_rate
from
	(
	select
		B.offender_book_id,
		B.max_off_allowance_day,
		unit,
		allowId,
		offender_id_display,
		last_name,
		first_name,
		"type",
		code,
		rate,
		amount
	from
		(
		select
			offender_book_id,
			sum(case when pay_actual_amount is null then allowance_version_rate * allowance_version_max_unit::numeric else
pay_actual_amount end) as Amount
		from
			off_alowances_pay_details
		where
			batch_id is null
			and off_allowance_day between TO_TIMESTAMP(:START_TIME,
	'DD/MM/YYYY') and TO_TIMESTAMP(:END_TIME,
	'DD/MM/YYYY')
		group by
			offender_book_id) A,
		(
		select
			A.offender_book_id,
			A.max_off_allowance_day,
			B.off_allowance_id as allowId,
			B.allowance_version_max_unit::int as unit,
			B.allowance_version_rate as rate,
			null::varchar as type,
			B.allowance_type as code,
			o.first_name,
			o.last_name,
			o.offender_id_display
		from
			(
			select
				max(off_allowance_day) as max_off_allowance_day,
				offender_book_id
			from
				off_alowances_pay_details
			group by
				offender_book_id) A,
			off_alowances_pay_details b,
			offenders o,
			offender_bookings ob
		where
			A.offender_book_id = b.offender_book_id
			and A.max_off_allowance_day = b.off_allowance_day
			and A.offender_book_id = ob.offender_book_id
			and o.offender_id = ob.offender_id)B
	where
		A.offender_book_id = B.offender_book_id)C
where
}
OCDCGPAY_GET_OFFENDER_ALLOWANCE_RECORDS{
select sum(apd.allowance_version_rate) as allowance_version_rate, 
	apd.allowance_version_max_unit,
	apd.off_allowance_id ,
	apd.allowance_type,
	apd.offender_book_id ,
	o.last_name,
	o.offender_id_display,
	o.first_name
from
	off_alowances_pay_details apd,
	offenders o,
	offender_bookings ob
where
	apd.offender_book_id = ob.offender_book_id
	and ob.offender_id = o.offender_id and
}

OCDCGPAY_INSERT_PAY_BATCH_OFF_ALLOWANCES {
insert into pay_batch_off_allowances(batch_id, offender_book_id, off_allowance_id, allowance_pay_amount, create_datetime, create_user_id) 
values(:payBatchId, :offenderBookId, :offAllowanceId, :allowancePayAmount, current_timestamp , :createUserId)
}

OCDCGPAY_GENERATE_ALLOW_PAY {
  update off_alowances_pay_details  set PAY_FLAG = 'Y', batch_id = :payBatchId,  modify_datetime = current_timestamp , modify_user_id =:modifyUserId where detail_id =:detailId
  }