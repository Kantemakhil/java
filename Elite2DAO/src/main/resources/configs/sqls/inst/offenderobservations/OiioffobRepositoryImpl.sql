OIIOFFOB_GET_OBSERVATION_PERIOD_INQUIRY_DATA {
select * from ( select
	a.*,
	case
		when a.last_check_date is null
		and a.schedule_datetime <= (current_timestamp - (1 || ' MINUTES')::interval ) then 'Y'
		else 'N'
	end over_due_flag
from
	(
	select
		schedule_datetime,
		offender_book_id,
		offender_id_display,
		last_name,
		first_name,
		offender_id,
		living_unit_description,
		observation_type,
		frequency,
		obs_period_id,
		check_date,
		observation_type_code,
		check_date as check_time,
		last_check_date
	from
		(with cte as(
		select
			*
		from
			(
			select
				a.*
			from
				(
				select
					vhb.offender_book_id,
					vhb.offender_id_display,
					vhb.last_name,
					vhb.first_name,
					vhb.OFFENDER_ID,
					(
					select
						description
					from
						living_units lu
					where
						living_unit_id = vhb.living_unit_id) as living_unit_description,
					(
					select
						description
					from
						reference_codes
					where
						code = oot.observation_type
						and domain = 'OBSRVATN_TYP')as observation_type,
					(
					select
						code
					from
						reference_codes
					where
						code = oot.observation_type
						and domain = 'OBSRVATN_TYP')as observation_type_code,
					oot.frequency,
					oop.obs_period_id
				from
					(
					select
						ob.living_unit_id,
						O.OFFENDER_ID ,
						O.LAST_NAME ,
						O.FIRST_NAME ,
						OB.OFFENDER_BOOK_ID,
						O.OFFENDER_ID_DISPLAY,
						OB.INTAKE_AGY_LOC_ID,
						OB.COMMUNITY_ACTIVE_FLAG
					from
						OFFENDERS O,
						OFFENDER_BOOKINGS OB
					where
						OB.OFFENDER_ID = O.OFFENDER_ID ) as VHB,
					off_observation_periods oop,
					offender_observation_types oot
				where
					oot.obs_type_version_id = oop.obs_type_version_id
					and 
				vhb.living_unit_id in (
					select
						internal_location_id
					from
						OFF_OBS_ZONE_DETAILS
					where
						agy_loc_id = :agyLocId
						and zone_code = :zoneCode)
					and oop.offender_book_id = vhb.offender_book_id
					and oop.status_code = 'ACTIVE') as a ) as b)
		select
			*,
			(
			select
				case
					when cnt>1 then (
					select
						check_datetime
					from
						(
						select
							check_datetime,
							row_number() over (
						order by
							create_datetime) as rownum
						from
							off_obs_period_checks
						where
							obs_period_id = cte.obs_period_id )A
					where
						rownum = maxi-1)
					else (
					select
						check_datetime
					from
						off_obs_period_checks
					where
						obs_period_id = cte.obs_period_id )
				end check_date
			from
				(
				select
					max(rownum) maxi,
					count(1) cnt
				from
					(
					select
						check_id,
						check_datetime,
						check_datetime as check_time,
						row_number() over (
					order by
						create_datetime) as rownum
					from
						off_obs_period_checks
					where
						obs_period_id = cte.obs_period_id)A)A)as check_date,
						(
			select
				case
					when cnt>1 then (
					select
						check_datetime
					from
						(
						select
							check_datetime,
							row_number() over (
						order by
							create_datetime) as rownum
						from
							off_obs_period_checks
						where
							obs_period_id = cte.obs_period_id )A
					where
						rownum = maxi)
					else (
					select
						check_datetime
					from
						off_obs_period_checks
					where
						obs_period_id = cte.obs_period_id )
				end check_date
			from
				(
				select
					max(rownum) maxi,
					count(1) cnt
				from
					(
					select
						check_id,
						check_datetime,
						check_datetime as check_time,
						row_number() over (
					order by
						create_datetime) as rownum
					from
						off_obs_period_checks
					where
						obs_period_id = cte.obs_period_id)A)A)as last_check_date,
					(
			select
				case
					when cnt>1 then (
					select
						schedule_datetime
					from
						(
						select
							schedule_datetime,
							row_number() over (
						order by
							create_datetime) as rownum
						from
							off_obs_period_checks
						where
							obs_period_id = cte.obs_period_id )A
					where
						rownum = maxi)
					else (
					select
						schedule_datetime
					from
						off_obs_period_checks
					where
						obs_period_id = cte.obs_period_id )
				end schedule_datetime
			from
				(
				select
					max(rownum) maxi,
					count(1) cnt
				from
					(
					select
						check_id,
						check_datetime,
						check_datetime as check_time,
						row_number() over (
					order by
						create_datetime) as rownum
					from
						off_obs_period_checks
					where
						obs_period_id = cte.obs_period_id)A)A)as schedule_datetime
		from
			cte )as A
	where
		(coalesce(:observationType, '') = ''
			or observation_type_code = :observationType)  ) a ) b where ((coalesce(:overDueFlag,'N')='N') or b.over_due_flag = :overDueFlag)
		}