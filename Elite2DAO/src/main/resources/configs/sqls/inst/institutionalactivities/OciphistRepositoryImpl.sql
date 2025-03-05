
OCIPHIST_GET_PRG_PAY_BATCH_DATA {
 select * from prg_pay_batches where batch_id in (select pay_batch_id from offender_course_attendances where offender_book_id  = :offender_book_id and pay_batch_id is not null) or batch_id in (select batch_id from off_alowances_pay_details where offender_book_id  = :offender_book_id and batch_id is not null)
   order by batch_id desc
 }

OCIPHIST_GET_PAID_DETAILS_DATA {
select
	*
from
	((
	select
		voca.event_date::date,
		oca.pay_batch_id ,
		coalesce(oca.pay_actual_rate,
		0) pay_actual_rate ,
		oca.pay_actual_amount ,
		oca.pay_hours
,
		case
			when voca.event_type != 'ACP' then (
			select
				description
			from
				program_services ps
			where
				program_id = voca.program_id)
			else (
			select
				description
			from
				program_services ps
			where
				program_id in (
				select
					program_id
				from
					v_offender_prg_obligations vopo
				where
					offender_prg_obligation_id in (
					select
						offender_prg_obligation_id
					from
						offender_program_profiles
					where
						off_prgref_id = voca.off_prgref_id)
))
		end
programDescription
,
		case
			when voca.event_type != 'ACP' then (
			select
				description
			from
				course_activities ca
			where
				crs_acty_id = voca.crs_acty_id)
			else (
			select
				description
			from
				COURSE_ACTIVITIES
			where
				crs_acty_id in (
				select
					parent_crs_acty_id
				from
					COURSE_ACTIVITIES
				where
					crs_acty_id = voca.crs_acty_id))
		end activity_description
,
		voca.hidden_comment_text
	from
		V_offender_course_attendances voca,
		offender_course_attendances oca
	where
		voca.event_id = oca.event_id
		and oca.pay_batch_id = :batch_id
		and voca.offender_book_id = :offender_book_id)
union
(
select
	off_allowance_day::date as event_date,
	batch_id as pay_batch_id,
	coalesce(pay_actual_rate,
	allowance_version_rate) as pay_actual_rate,
	(case when pay_actual_amount is null then allowance_version_rate * allowance_version_max_unit::numeric else
	pay_actual_amount end) as pay_actual_amount ,
	allowance_version_max_unit::numeric as pay_hours,
	null as event_type,
	(
	select
		description
	from
		reference_codes rc
	where
		"domain" = 'ALLOWANCE'
		and code = oa.allowance_type) as programDescription
	,
	oapd.comment_text as hidden_comment_text
from
	off_alowances_pay_details oapd,
	offender_allowances oa
where
	oapd.offender_book_id = :offender_book_id
	and oapd.off_allowance_id = oa.off_allowance_id and oapd.batch_id =:batch_id) order by
		event_date desc)
as offender_pay_history 
}