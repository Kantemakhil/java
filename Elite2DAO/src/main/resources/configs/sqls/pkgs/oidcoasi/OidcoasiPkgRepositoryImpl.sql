GET_LIVING_UNIT_ID{
 SELECT living_unit_id FROM living_units WHERE DESCRIPTION = :p_desc

}  

OFFENDER_ASSIGNMENTS_QUERY_SELECT_ONE{
select
	ofn.offender_id_display,
	ofn.last_name,
	ofn.first_name,
	ofn.offender_book_id,
	ofn.description,
	b.case_officer_id,
	b.staff_last_name,
	b.staff_first_name,
	b.case_assigned_date,
	b.case_assigned_time,
	case
		when :p_confirm_flag = 'Y' then 'Y'
		else 'N'
	end confirm_flag,
	b.case_agy_loc_id
from
	((
	select
		OFF.offender_id_display,
		OFF.last_name,
		OFF.first_name,
		off_bkg.offender_book_id,
		off_bkg.agy_loc_id,
		liv.description
	from
		offenders off,
		offender_bookings off_bkg
	left outer join living_units liv on
		(off_bkg.living_unit_id = liv.living_unit_id)
	where
		OFF.offender_id = off_bkg.offender_id
		and off_bkg.agy_loc_id = :p_agy_loc_id
		and ( coalesce(:p_offender_id_display::text, '') = ''
			or OFF.offender_id_display = :p_offender_id_display )
		and ( coalesce(:p_off_last_name::text, '') = ''
			or OFF.last_name = :p_off_last_name )
		and ( coalesce(:p_off_first_name::text, '') = ''
			or OFF.first_name = :p_off_first_name )
		and off_bkg.active_flag = 'Y'
		and (( coalesce(:v_living_unit_desc::text, '') = ''
			or liv.living_unit_id in (with recursive cte as (
			select
				lu.living_unit_id
			from
				living_units lu
			where
				lu.parent_living_unit_id = :v_living_unit_id
		union all
			select
				lu.living_unit_id
			from
				living_units lu
			join cte c on
				(c.living_unit_id = lu.parent_living_unit_id ) )
			select
				*
			from
				cte
		union
			select
				lu1.living_unit_id
			from
				living_units lu1
			where
				lu1.living_unit_id = :v_living_unit_id ) )) ) ) ofn,
	((
	select
		a.case_officer_id,
		a.offender_book_id,
		stf.last_name staff_last_name,
		stf.first_name staff_first_name,
		a.case_assigned_date,
		a.case_assigned_time,
		a.case_agy_loc_id
	from
		offender_case_officers a,
		staff_members stf,
		offender_bookings obkg
	where
		obkg.agy_loc_id = :p_agy_loc_id
		and a.offender_book_id = obkg.offender_book_id
		and (a.case_officer_id = :v_case_officer_id)
			and a.case_assigned_time in ((
			select
				MAX(case_assigned_time)
			from
				offender_case_officers
			where
				offender_book_id = a.offender_book_id))
			and a.case_officer_id = stf.staff_id)) b
where
	ofn.offender_book_id = b.offender_book_id
order by
	ofn.last_name,
	ofn.first_name
}

OFFENDER_ASSIGNMENTS_QUERY_SELECT_SECOND{
 select
	ofn.offender_id_display,
	ofn.last_name,
	ofn.first_name,
	ofn.offender_book_id,
	ofn.description,
	b.case_officer_id,
	b.staff_last_name,
	b.staff_first_name,
	b.case_assigned_date,
	b.case_assigned_time,
	case
		when :p_confirm_flag = 'Y' then 'Y'
		else 'N'
	end confirm_flag,
	b.case_agy_loc_id
from
	((
	select
		OFF.offender_id_display,
		OFF.last_name,
		OFF.first_name,
		off_bkg.offender_book_id,
		liv.description
	from
		offenders off,
		offender_bookings off_bkg
	left outer join living_units liv on
		(off_bkg.living_unit_id = liv.living_unit_id)
	where
		OFF.offender_id = off_bkg.offender_id
		and off_bkg.agy_loc_id = :p_agy_loc_id
		and ( coalesce(:p_offender_id_display::text, '') = ''
			or OFF.offender_id_display = :p_offender_id_display )
		and ( coalesce(:p_off_last_name::text, '') = ''
			or OFF.last_name = :p_off_last_name )
		and ( coalesce(:p_off_first_name::text, '') = ''
			or OFF.first_name = :p_off_first_name )
		and off_bkg.active_flag = 'Y'
		and (( coalesce(:v_living_unit_desc::text, '') = ''
			or liv.living_unit_id in (with recursive cte as (
			select
				lu.living_unit_id
			from
				living_units lu
			where
				lu.parent_living_unit_id = :v_living_unit_id
		union all
			select
				lu.living_unit_id
			from
				living_units lu
			join cte c on
				(c.living_unit_id = lu.parent_living_unit_id ) )
			select
				*
			from
				cte
		union
			select
				lu1.living_unit_id
			from
				living_units lu1
			where
				lu1.living_unit_id = :v_living_unit_id ) )) ) ) ofn
left outer join ((
	select
		a.case_officer_id,
		a.offender_book_id,
		stf.last_name staff_last_name,
		stf.first_name staff_first_name,
		a.case_assigned_date,
		a.case_assigned_time,
		a.case_agy_loc_id
	from
		offender_case_officers a,
		staff_members stf,
		offender_bookings obkg
	where
		obkg.agy_loc_id = :p_agy_loc_id
		and a.offender_book_id = obkg.offender_book_id
		and (( coalesce(:p_current_officer_id::text, '') = ''
			or a.case_officer_id = :p_current_officer_id ))
		and (( coalesce(:p_case_officer_id::text, '') = ''
			or a.case_officer_id = :p_case_officer_id ))
		and a.case_assigned_time in ((
		select
			MAX(case_assigned_time)
		from
			offender_case_officers
		where
			offender_book_id = a.offender_book_id))
		and a.case_officer_id = stf.staff_id)) b on
	(ofn.offender_book_id = b.offender_book_id)
order by
	ofn.last_name,
	ofn.first_name
}

OFFENDER_ASSIGNMENTS_QUERY_SELECT_THIRD{
 select
	ofn.offender_id_display,
	ofn.last_name,
	ofn.first_name,
	ofn.offender_book_id,
	ofn.description,
	b.case_officer_id,
	b.staff_last_name,
	b.staff_first_name,
	b.case_assigned_date,
	b.case_assigned_time,
	case
		when :p_confirm_flag = 'Y' then 'Y'
		else 'N'
	end confirm_flag,
	b.case_agy_loc_id
from
	((
	select
		OFF.offender_id_display,
		OFF.last_name,
		OFF.first_name,
		off_bkg.offender_book_id,
		liv.description
	from
		offenders off,
		offender_bookings off_bkg
	left outer join living_units liv on
		(off_bkg.living_unit_id = liv.living_unit_id)
	where
		OFF.offender_id = off_bkg.offender_id
		and off_bkg.agy_loc_id = :p_agy_loc_id
		and ( coalesce(:p_offender_id_display::text, '') = ''
			or OFF.offender_id_display = :p_offender_id_display )
		and ( coalesce(:p_off_last_name::text, '') = ''
			or OFF.last_name = :p_off_last_name )
		and ( coalesce(:p_off_first_name::text, '') = ''
			or OFF.first_name = :p_off_first_name )
		and off_bkg.active_flag = 'Y'
		and (( coalesce(:v_living_unit_desc::text, '') = ''
			or liv.living_unit_id in (with recursive cte as (
			select
				lu.living_unit_id
			from
				living_units lu
			where
				lu.parent_living_unit_id = :v_living_unit_id
		union all
			select
				lu.living_unit_id
			from
				living_units lu
			join cte c on
				(c.living_unit_id = lu.parent_living_unit_id ) )
			select
				*
			from
				cte
		union
			select
				lu1.living_unit_id
			from
				living_units lu1
			where
				lu1.living_unit_id = :v_living_unit_id ) ))
		and ((exists ((
		select
			'X'
		from
			offender_case_officers
		where
			offender_book_id = off_bkg.offender_book_id
			and case_assigned_date > date_trunc('day', LOCALTIMESTAMP)
				and ((
				select
					COUNT(1)
				from
					offender_case_officers
				where
					offender_book_id = off_bkg.offender_book_id
					and case_assigned_date <= date_trunc('day', LOCALTIMESTAMP))) = 0
					or not exists ((
					select
						'X'
					from
						offender_case_officers
					where
						offender_book_id = off_bkg.offender_book_id)))) ))) ) ofn
left outer join ((
	select
		off_case.case_officer_id,
		obkg.offender_book_id,
		stf.last_name staff_last_name,
		stf.first_name staff_first_name,
		off_case.case_assigned_date,
		off_case.case_assigned_time,
		off_case.case_agy_loc_id
	from
		offender_bookings obkg,
		staff_members stf,
		offender_case_officers off_case
	where
		obkg.agy_loc_id = :p_agy_loc_id
		and obkg.offender_book_id = off_case.offender_book_id
		and off_case.case_assigned_date > date_trunc('day', LOCALTIMESTAMP)
			and off_case.case_assigned_time in ((
			select
				MAX(case_assigned_time)
			from
				offender_case_officers
			where
				offender_book_id = obkg.offender_book_id))
			and off_case.case_officer_id = stf.staff_id)) b on
	(ofn.offender_book_id = b.offender_book_id)
order by
	ofn.last_name,
	ofn.first_name
}


	         
	         
	         
			
			