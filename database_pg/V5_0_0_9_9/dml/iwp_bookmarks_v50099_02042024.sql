update
	oms_owner.iwp_bookmarks
set
	sql_text = 'select
		obp.check_id as obs_oopcid,
		TO_CHAR(obp.schedule_datetime, ''DD-MM-YYYY'') as obs_oopcsdte,
		greatest((extract(epoch from check_datetime) * 1000 - extract(epoch from obp.schedule_datetime) * 1000 - ( select frequency from offender_observation_types where obs_type_version_id = ? )) / 60000, 0) as obs_oopcvar,
		TO_CHAR(obp.schedule_datetime, ''HH24:MI'') as obs_oopcstme,
		TO_CHAR(obp.check_datetime , ''DD-MM-YYYY'') as obs_oopcdte,
		TO_CHAR(obp.check_datetime, ''HH24:MI'') as obs_oopctme,
		(
		select
			frequency
		from
			offender_observation_types
		where
			obs_type_version_id = ? ) as obs_oopcfreq,
		(
		select
			last_name || '','' || '' '' || first_name as user_id
		from
			staff_members sm
		where
			staff_id = obp.performing_staff_id ) as obs_oopcstaf,
		obp.comment_text,
		(
		select
			STRING_AGG(rc.description, '','')
		from
			reference_codes rc
		join off_obs_add_check_details ooacd_sub on
			rc.code = ooacd_sub.detail_code
		where
			ooacd_sub.obs_type_version_id = ?
			and ooacd_sub.detail_type = ''ACTIVITY''
			and ooacd_sub.check_id = obp.check_id ) as activity,
		(
		select
			STRING_AGG(rc.description, '','')
		from
			reference_codes rc
		join off_obs_add_check_details ooacd_sub on
			rc.code = ooacd_sub.detail_code
		where
			ooacd_sub.obs_type_version_id = ?
			and ooacd_sub.detail_type = ''CELL_CNDITNS''
			and ooacd_sub.check_id = obp.check_id ) as cell_cnditns,
		(
		select
			STRING_AGG(rc.description, '','')
		from
			reference_codes rc
		join off_obs_add_check_details ooacd_sub on
			rc.code = ooacd_sub.detail_code
		where
			ooacd_sub.obs_type_version_id = ?
			and ooacd_sub.detail_type = ''COM_DET_CAT''
			and ooacd_sub.check_id = obp.check_id ) as com_det_cat,
		(
		select
			STRING_AGG(rc.description, '','')
		from
			reference_codes rc
		join off_obs_add_check_details ooacd_sub on
			rc.code = ooacd_sub.detail_code
		where
			ooacd_sub.obs_type_version_id = ?
			and ooacd_sub.detail_type = ''NOT_IN_CELL''
			and ooacd_sub.check_id = obp.check_id ) as not_in_cell
	from
		OFF_OBS_PERIOD_CHECKS obp
	left join off_obs_add_check_details ooacd on
		obp.check_id = ooacd.check_id
	where
		obp.OBS_PERIOD_ID = ?
	group by
		obp.check_id ', 
		modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_OBS_CHEC';
