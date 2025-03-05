OWEACPLN_GET_WEEKLY_PLANS{
select
	activity_date,
	activity,
	activity_start,
	off_prgref_id,
	activity_finish,
	system_generated,
	(case when (system_generated = 'Y' and weekly_activity_plan_id is  not null) then (select activity_address from weekly_activity_plan wap1 where wap1.weekly_activity_plan_id = wap.weekly_activity_plan_id) 
	else activity_address end) as activity_address,
	offender_book_id,
	event_id,
	record_source,
	depart_start_time,
	return_time,
	mode_of_transport,
	weekly_activity_plan_id,
	update_indicator,
	not_for_offender_flag,
	finalized,
	version_no,
	(
	select
		max(version_no)
	from
		WEEKLY_ACTIVITY_PLAN_HTY
	where
		weekly_activity_plan_id = wap.weekly_activity_plan_id) as hty_version_no
from
	(
	select
		ois.event_date as activity_date,
		(
		select
			description
		from
			reference_codes rc
		where
			domain = 'EVENTS'
			and code = ois.event_sub_type) as activity,
		ois.start_time as activity_Start,
		ois.off_prgref_id,
		case when ois.end_time is not null then ois.end_time  
		else		
		(
		select
			activity_finish 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id) end as activity_Finish,
		'Y' as System_Generated,
		(
		select
			description
		from
			agency_locations al
		where
			agy_loc_id = ois.to_agy_loc_id) as activity_Address,
		ois.offender_book_id,
		ois.event_id,
		'SCH' as record_source,
		(
		select
			depart_start_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id) as depart_start_time,
		(
		select
			return_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id) as return_time,
		(
		select
			mode_of_transport
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id) as mode_of_transport,
		(
		select
			weekly_activity_plan_id
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id) as weekly_activity_plan_id,
		(
		select
			update_indicator
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id) as update_indicator,
		(
		select
			not_for_offender_flag
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id) as not_for_offender_flag,
			coalesce((
		select
			finalized
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id), 'N') as finalized,
			coalesce((
		select
			version_no 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'SCH_' || ois.event_id), 0) as version_no
	from
		offender_ind_schedules ois
	where
		ois.event_date >= :fromDate
		and ois.event_date <= :toDate
		and ois.event_status = 'SCH'
		and ois.event_class = 'COMM'
		and ois.offender_book_id = :offenderBookId
union all
	select
		a.event_date as activity_date,
		'Community Service' as activity,
		a.start_time as activity_Start,	
		a.off_prgref_id,
		case when a.end_time is not null then a.end_time else	
		(
		select
			activity_finish 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CS_' || a.event_id) end as activity_Finish,
		'Y' as System_Generated,
		( coalesce(a.corporate_name, '') || ' ' || coalesce(a.corporate_addresses, '') ) as activity_Address,
		a.offender_book_id,
		a.event_id,
		'CS' as record_source,
		(
		select
			depart_start_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate) as depart_start_time,
		(
		select
			return_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate) as return_time,
		(
		select
			mode_of_transport
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate) as mode_of_transport,
		(
		select
			weekly_activity_plan_id
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate) as weekly_activity_plan_id,
		(
		select
			update_indicator
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate) as update_indicator,
		(
		select
			not_for_offender_flag
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate) as not_for_offender_flag,
			coalesce((
		select
			finalized
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate), 'N') as finalized,
			coalesce((
		select
			version_no 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity = 'Community Service' and ((date(a.event_date) = date(activity_date))) and offender_book_id =:offenderBookId and wap_start_date = :fromDate and wap_end_date = :toDate), 0) as version_no
	from
		(
		select
			oca.event_date,
			oca.start_time,
			oca.end_time,
			oca.offender_book_id,
			oca.event_id,
			oca.off_prgref_id,
			(
			select
				corporate_name
			from
				CORPORATES
			where
				corporate_id = (
				select
					ca.placement_corporate_id
				from
					course_activities ca
				where
					ca.crs_acty_id = oca.crs_acty_id)) as corporate_name ,
			(
			select COALESCE(street_information,'') || ',' || COALESCE(city_code,'') || ',' || COALESCE(prov_state_code ,'') || ',' || COALESCE(prov_state_code ,'') || ',' || COALESCE(zip_postal_code ,'')  from V_ADDRESSES where address_id =(
				select
					ca.services_address_id
				from
					course_activities ca
				where
					ca.crs_acty_id = oca.crs_acty_id) ) as corporate_addresses
		from
			(
			select
				a.*,
				row_number () over (
			order by
				row_id) as event_id
			from
				(
				select
					ca.row_id,
					opp.offender_book_id,
					opp.off_prgref_id,
					voca.crs_appt_id as reference_id,
					voca.crs_appt_id,
					opp.program_id,
					opp.crs_acty_id,
					voca.schedule_date as event_date,
					voca.start_time,
					voca.end_time,
					substr(coalesce(ps.program_category, tag_prg_prg_event_type(ps.program_id))::text,
					1,
					12) as event_type,
					case
						when opp.suspended_flag::text = 'N'::text then 'SCH'::text
						else 'CANC'::text
					end as event_status,
					opp.agy_loc_id,
					'V_CRS_APPT'::text as record_source
				from
					v_offender_course_appts_2 voca,
					reference_codes rc,
					program_services ps,
					offender_program_profiles opp,
					offender_bookings bkg,
					course_activities ca
				left join addresses addr on
					ca.services_address_id = addr.address_id
				where
					voca.off_prgref_id = opp.off_prgref_id
					and voca.schedule_date > tag_schedule_schedule_date()
					and opp.offender_program_status::text in (select refcode.code from reference_codes refcode where "domain" ='OFF_PRG_STS' and refcode.parent_code='A')
					and voca.schedule_date >= opp.offender_start_date
					and voca.schedule_date <= coalesce(coalesce(opp.offender_end_date, ca.schedule_end_date), to_date('30001231'::text, 'YYYYMMDD'::text)::timestamp without time zone)
						and ca.crs_acty_id = opp.crs_acty_id
						and ca.course_activity_type is not null
						and ca.course_activity_type::text <> ''::text
						and ca.program_id = ps.program_id
						and rc.domain::text = 'PS_CATEGORY'::text
						and (rc.parent_code::text = any (array['COMM'::character varying::text,
						'INT_MOV'::character varying::text,
						'BOTH'::character varying::text,
						'EXT_MOV'::character varying::text]))
							and rc.code::text = coalesce(ps.program_category, tag_prg_prg_event_type(ps.program_id))::text
								and bkg.offender_book_id = opp.offender_book_id
								and not (exists (
								select
									'x'::text as text
								from
									offender_course_attendances oca
								where
									oca.offender_book_id = opp.offender_book_id
									and oca.event_date = voca.schedule_date
									and oca.crs_appt_id = voca.crs_appt_id))) as a) oca
		where
			oca.offender_book_id =:offenderBookId
			and oca.event_status NOT IN ('PROG_CANC','CANC')
			and oca.event_type = 'UW'
			and oca.event_date >= :fromDate
			and oca.event_date <= :toDate
	union
		select
			oca1.event_date,
			oca1.start_time,
			oca1.end_time,
			oca1.offender_book_id,
			oca1.event_id,
			oca1.off_prgref_id,
			(
			select
				corporate_name
			from
				CORPORATES
			where
				corporate_id = (
				select
					ca.placement_corporate_id
				from
					course_activities ca
				where
					ca.crs_acty_id = oca1.crs_acty_id)) as corporate_name ,
			case when (select
				services_address_id
			from
				course_activities ca
			where
				crs_acty_id = oca1.crs_acty_id) is null then(
	(
	select
		(
		select
			coalesce(street_information, '') || ',' || coalesce(city_code, '') || ',' || coalesce(prov_state_code , '') || ',' || coalesce(country_desc, '') || ',' || coalesce(zip_postal_code, '')
		from
			V_ADDRESSES
		where
			address_id = vcp.cp_services_address_id )
	from
		V_COURSE_PHASE_OFFERINGS vcp,
		(
		select
			program_id ,
			crs_acty_id
		from
			course_activities ca
		where
			crs_acty_id = (
			select
				parent_crs_acty_id
			from
				course_activities ca
			where
				crs_acty_id = oca1.crs_acty_id)) cs
	where
		vcp.program_id = cs.program_id
		and vcp.course_id = cs.crs_acty_id
		and vcp.program_phase_id = oca1.program_id)) else (select coalesce(street_information, '') || ',' || coalesce(city_code, '') || ',' || coalesce(prov_state_code , '') || ',' || coalesce(country_desc, '') || ',' || coalesce(zip_postal_code, '') from v_addresses va where address_id = (select
				services_address_id
			from
				course_activities ca
			where
				crs_acty_id = oca1.crs_acty_id)) end as corporate_addresses
		from
			OFFENDER_COURSE_ATTENDANCES oca1
		where
			oca1.offender_book_id =:offenderBookId
			and oca1.event_status NOT IN ('PROG_CANC','CANC')
			and oca1.event_type = 'UW'
			and oca1.event_date >= :fromDate
			
			and oca1.event_date <= :toDate ) a
union all
	select
		oca.event_date as activity_date,
		(
		select
			description
		from
			COURSE_ACTIVITIES
		where
			crs_acty_id = (
			select
				parent_crs_acty_id
			from
				COURSE_ACTIVITIES
			where
				crs_acty_id = oca.crs_acty_id) ) as activity,
		oca.in_time as activity_Start,
		null as off_prgref_id,
	case when oca.out_time is not null then oca.out_time  
		else		
		(
		select
			activity_finish 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id) end as activity_Finish,			
		'Y' as System_Generated,
		(select
	(
	select
		street_information || ',' || coalesce(city_code, '') || ',' || coalesce(prov_state_code , '') || ',' || country_desc || ',' || zip_postal_code
	from
		V_ADDRESSES
	where
		address_id = vcp.cp_services_address_id
)
from
	V_COURSE_PHASE_OFFERINGS vcp,
	(
	select
		program_id ,
		crs_acty_id
	from
		course_activities ca
	where
		crs_acty_id = (
		select
			parent_crs_acty_id
		from
			course_activities ca
		where
			crs_acty_id = oca.crs_acty_id)) cs
where
	vcp.program_id = cs.program_id
	and vcp.course_id = cs.crs_acty_id and vcp.program_phase_id = oca.program_id) as activity_Address,
		oca.offender_book_id,
		oca.event_id,
		'ACP' as record_source,
		(
		select
			depart_start_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id) as depart_start_time,
		(
		select
			return_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id) as return_time,
		(
		select
			mode_of_transport
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id) as mode_of_transport,
		(
		select
			weekly_activity_plan_id
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id) as weekly_activity_plan_id,
		(
		select
			update_indicator
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id) as update_indicator,
		(
		select
			not_for_offender_flag
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id) as not_for_offender_flag,
			coalesce((
		select
			finalized
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id), 'N') as finalized,
			coalesce((
		select
			version_no 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACP_' || oca.event_id), 0) as version_no
	from
		OFFENDER_COURSE_ATTENDANCES oca
	where
		oca.offender_book_id =:offenderBookId
		and oca.event_status = 'SCH' and (event_outcome is null or event_outcome != 'CANC')
		and oca.event_type = 'ACP' and oca.crs_acty_id  is not null
		and oca.event_date >= :fromDate
		and oca.event_date <= :toDate
union all
	select
		oca.event_date as activity_date,
		(
		select
			description
		from
			reference_codes rc
		where
			domain = 'EVENTS'
			and code = oca.event_sub_type) as activity,
		oca.start_time as activity_Start,
		null as off_prgref_id,
	case when oca.end_time is not null then oca.end_time  
		else		
		(
		select
			activity_finish 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id) end as activity_Finish,			
		'Y' as System_Generated,
		(
		select
			description
		from
			agency_locations al
		where
			agy_loc_id = oca.agy_loc_id) as activity_Address,
		oca.offender_book_id,
		oca.event_id,
		'ACPA' as record_source,
		(
		select
			depart_start_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id) as depart_start_time,
		(
		select
			return_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id) as return_time,
		(
		select
			mode_of_transport
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id) as mode_of_transport,
		(
		select
			weekly_activity_plan_id
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id) as weekly_activity_plan_id,
		(
		select
			update_indicator
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id) as update_indicator,
		(
		select
			not_for_offender_flag
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id) as not_for_offender_flag,
			coalesce((
		select
			finalized
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id), 'N') as finalized,
			coalesce((
		select
			version_no 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'ACPA_' || oca.event_id), 0) as version_no
	from
		OFFENDER_COURSE_ATTENDANCES oca
	where
		oca.offender_book_id =:offenderBookId
		and oca.event_status = 'SCH'
		and oca.event_type = 'ACP' and oca.crs_acty_id  is null
		and oca.event_date >= :fromDate
		and oca.event_date <= :toDate
		union all
		select
		ce.event_date  as activity_date,
		('Court Event') as activity,
		ce.start_time as activity_Start,
		null as off_prgref_id,
		case when ce.end_time is not null then ce.end_time  
		else		
		(
		select
			activity_finish 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id) end as activity_Finish,		
		'Y' as System_Generated,
		(SELECT   al.description
    FROM agency_locations al
   WHERE al.agy_loc_id= ce.agy_loc_id) as activity_Address,
		ce.offender_book_id,
		ce.event_id as event_id,
		'CE' as record_source,
		(
		select
			depart_start_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id) as depart_start_time,
		(
		select
			return_time
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id) as return_time,
		(
		select
			mode_of_transport
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id) as mode_of_transport,
		(
		select
			weekly_activity_plan_id
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id) as weekly_activity_plan_id,
		(
		select
			update_indicator
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id) as update_indicator,
		(
		select
			not_for_offender_flag
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id) as not_for_offender_flag,
			coalesce((
		select
			finalized
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id), 'N') as finalized,
			coalesce((
		select
			version_no 
		from
			WEEKLY_ACTIVITY_PLAN
		where
			activity_id = 'CE_' || ce.event_id), 0) as version_no
	from
		COURT_EVENTS ce
	where
		ce.offender_book_id =:offenderBookId
		and ce.event_date >= :fromDate
		and ce.event_date <= :toDate
		and ce.event_status = 'SCH'
		
union all
	select
		wap.activity_date,
		wap.activity,
		wap.activity_start,
		null as off_prgref_id,
		wap.activity_finish,
		'N' as System_Generated,
		wap.activity_address,
		wap.offender_book_id ,
		wap.activity_id::bigint as event_id ,
		wap.activity_id as record_source,
		wap.depart_start_time ,
		wap.return_time,
		wap.mode_of_transport,
		wap.weekly_activity_plan_id,
		wap.update_indicator,
		wap.not_for_offender_flag,
		coalesce(wap.finalized, 'N') as finalized,
		coalesce(wap.version_no, 0) as version_no
	from
		WEEKLY_ACTIVITY_PLAN wap
	where
		wap.offender_book_id = :offenderBookId
		and wap.wap_start_date >= :fromDate
		and wap.wap_end_date <= :toDate
		and wap.activity_id is null ) as wap
order by
	activity_date,
	activity_start
}
OWEACPLN_EM_DETAILS_INSERT_LIST {
insert into OFFENDER_EM_TAG_DETAILS(offender_book_id, em_tag_strap_size, em_tag_data, create_datetime, create_user_id, modify_datetime, modify_user_id, EM_TAG_START_TIME, EM_TAG_END_TIME) values(:offenderBookId, :emTagStrapSize, :emTagData, CURRENT_TIMESTAMP, :createUserId, null , null , :emTagStartTime, :emTagEndTime)
}
OWEACPLN_EM_DETAILS_UPDATE_LIST {
UPDATE OFFENDER_EM_TAG_DETAILS set em_tag_strap_size=:emTagStrapSize,em_tag_data =:emTagData,MODIFY_DATETIME=CURRENT_TIMESTAMP,MODIFY_USER_ID= :modifyUserId ,EM_TAG_START_TIME=:emTagStartTime,EM_TAG_END_TIME=:emTagEndTime  where em_tag_id=:emTagId
}
OWEACPLN_GET_OFFENDER_EM_DETAILS {
select * from OFFENDER_EM_TAG_DETAILS where offender_book_id=:offenderBookId
}
OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_INSERT_LIST {
insert into WEEKLY_ACTIVITY_PLAN(offender_book_id, activity, activity_address, depart_start_time, return_time, mode_of_transport, update_indicator, comment_text, create_datetime, create_user_id, modify_datetime, modify_user_id, activity_id, activity_start, activity_finish, wap_start_date, wap_end_date, activity_date, not_for_offender_flag, finalized, version_no) values (:offenderBookId, :activity, :activityAddress, :departStartTime, :returnTime, :modeOfTransport, :updateIndicator, :commentText, CURRENT_TIMESTAMP, :createUserId, null , null , :activityId, :activityStart, :activityFinish, :wapStartDate, :wapEndDate, :activityDate, :notForOffenderFlag, :finalized, :versionNo)
}
OWEACPLN_EM_WEEKLY_PLAN_ACTIVITY_DETAILS_UPDATE_LIST {
update WEEKLY_ACTIVITY_PLAN set activity =:activity, activity_address =:activityAddress, depart_start_time =:departStartTime, return_time =:returnTime, mode_of_transport =:modeOfTransport, finalized =:finalized, version_no =:versionNo, update_indicator =:updateIndicator, comment_text =:commentText, modify_user_id =:modifyUserId, modify_datetime =current_timestamp, ACTIVITY_START = :activityStart, ACTIVITY_FINISH =:activityFinish, not_for_offender_flag = :notForOffenderFlag, activity_date =:activityDate where WEEKLY_ACTIVITY_PLAN_ID =:weeklyActivityPlanId
}
OWEACPLN_EM_WEEKLY_PLAN_ACTIVITY_DETAILS_DELETE_LIST {
DELETE FROM WEEKLY_ACTIVITY_PLAN where WEEKLY_ACTIVITY_PLAN_ID =:weeklyActivityPlanId AND VERSION_NO=:versionNo;
}
OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_HTY_INSERT_LIST {
insert into WEEKLY_ACTIVITY_PLAN_HTY( weekly_activity_plan_id, offender_book_id, activity, activity_address, depart_start_time, return_time, mode_of_transport, update_indicator, comment_text, version_no, create_datetime, create_user_id, modify_datetime, modify_user_id, activity_start, activity_finish, wap_start_date, wap_end_date, activity_date, not_for_offender_flag) values ( :weeklyActivityPlanId, :offenderBookId, :activity, :activityAddress, :departStartTime, :returnTime, :modeOfTransport, :updateIndicator, :commentText, :versionNo, CURRENT_TIMESTAMP, :createUserId, null , null , :activityStart, :activityFinish, :wapStartDate, :wapEndDate, :activityDate, :notForOffenderFlag)
}
OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_HTY_RETRIEVE{
select
waph.version_no as hty_version_no,
waph.weekly_activity_plan_hty_id,
waph.weekly_activity_plan_id,
waph.offender_book_id,
waph.activity_date,
waph.wap_start_date,
waph.wap_end_date,
waph.activity_id,
waph.activity,
waph.activity_address,
waph.depart_start_time,
waph.activity_start,
waph.activity_finish,
waph.return_time,
waph.mode_of_transport,
waph.update_indicator,
waph.comment_text,
waph.version_no,
waph.create_datetime,
waph.create_user_id,
waph.modify_datetime,
waph.modify_user_id,
waph.seal_flag,
waph.not_for_offender_flag,
	case when COALESCE((
	select
		activity_id
	from
		weekly_activity_plan wap
	where
		wap.weekly_activity_plan_id = waph.weekly_activity_plan_id),'') = '' then 'N' else 'Y' end as System_Generated
from
	weekly_activity_plan_hty waph  where waph.OFFENDER_BOOK_ID=:offenderBookId AND waph.wap_start_date >= :wapStartDate and waph.wap_end_date <= :wapEndDate AND waph.VERSION_NO=:versionNo
}


OWEACPLN_WAP_COMMENTS_INSERT_LIST{
	INSERT INTO WAP_COMMENTS (OFFENDER_BOOK_ID ,COMMENT_TEXT , WAP_START_DATE, WAP_END_DATE, CREATE_DATETIME, CREATE_USER_ID,MODIFY_DATETIME ,MODIFY_USER_ID)
	VALUES (:offenderBookId ,:comment,:wapStartDate,:wapEndDate ,CURRENT_TIMESTAMP ,:createUserId ,null ,null )
}

OWEACPLN_WAP_COMMENTS_UPDATE_LIST{
UPDATE WAP_COMMENTS set COMMENT_TEXT = :comment, modify_user_id =:modifyUserId, modify_datetime =current_timestamp WHERE OFFENDER_BOOK_ID = :offenderBookId and WAP_START_DATE >= :wapStartDate and WAP_END_DATE <= :wapEndDate  
}
OWEACPLN_OFFENDER_DETAILS_OBJECT {
select FIRST_NAME||', '||LAST_NAME as  lastName , offender_id_display, ROOT_OFFENDER_ID from V_HEADER_BLOCK_FN(:userId) v_header_block where offender_book_id=:offenderBookId 
}
OWEACPLN_GET_WAP_COMMENTS{
 select comment_text from WAP_COMMENTS where OFFENDER_BOOK_ID = :offenderBookId and wap_start_date >= :wapStartDate and wap_end_date <= :wapEndDate
}
OWECPLN_GET_MAX_VERSION_NO{
select
	(coalesce(max(version_no), 0) + 1 ) as version_no
from
	WEEKLY_ACTIVITY_PLAN_HTY
where
	offender_book_id = :offenderBookId
	and wap_start_date =:wapStartDate and wap_end_date = :wapEndDate
}

OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_HTY_RETRIEVE_MAX_DATA{
select
waph.version_no as hty_version_no,
waph.weekly_activity_plan_hty_id,
waph.weekly_activity_plan_id,
waph.offender_book_id,
waph.activity_date,
waph.wap_start_date,
waph.wap_end_date,
waph.activity_id,
waph.activity,
waph.activity_address,
waph.depart_start_time,
waph.activity_start,
waph.activity_finish,
waph.return_time,
waph.mode_of_transport,
waph.update_indicator,
waph.comment_text,
waph.version_no,
waph.create_datetime,
waph.create_user_id,
waph.modify_datetime,
waph.modify_user_id,
waph.seal_flag,
waph.not_for_offender_flag,
	case
	when coalesce((
	select
		activity_id
	from
		weekly_activity_plan wap
	where
		wap.weekly_activity_plan_id = waph.weekly_activity_plan_id), '') = '' then 'N'
	else 'Y'
end as System_Generated
from
	weekly_activity_plan_hty waph
where
waph.OFFENDER_BOOK_ID =:offenderBookId
and waph.wap_start_date >= :wapStartDate
and waph.wap_end_date <= :wapEndDate
and waph.VERSION_NO =:versionNo
}

OWEACPLN_GET_MODE_OF_TRANSPORT_DESCRIPTION {
SELECT DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'TA_TRANSPORT'AND CODE = :modeOfTransport
}
OWEACPLN_GET_MAX_HTY_VERSION{
select
	 coalesce(max(version_no),0)
	from
		WEEKLY_ACTIVITY_PLAN_HTY wap
	where
		wap.offender_book_id = :offenderBookId
		and wap.wap_start_date >= :fromDate
		and wap.wap_end_date <= :toDate
}

OWEACPLN_WEEKLY_PLAN_PARENT_DATA_UPDATE_LIST {
update weekly_activity_plan set finalized = 'Y', modify_datetime =current_timestamp , modify_user_id =:modifyUserId where weekly_activity_plan_id =:weeklyActivityPlanId
}
OWEACPLN_COPY_OVER_PREVIOUS_DATA{
select * from weekly_activity_plan wap where activity_id is null and wap_start_date >= :previousWeekStartDate
and wap_end_date < :currentWeekStartDate and offender_book_id = :offenderBookId
}
OWEACPLN_COPY_OVER_INSERT{
insert
	into
	WEEKLY_ACTIVITY_PLAN(offender_book_id,
	activity,
	activity_address,
	depart_start_time,
	return_time,
	mode_of_transport,
	update_indicator,
	comment_text,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	activity_id,
	activity_start,
	activity_finish,
	wap_start_date,
	wap_end_date,
	activity_date,
	not_for_offender_flag,
	finalized,
	version_no)
values (:offenderBookId,
:activity,
:activityAddress,
:departStartTime,
:returnTime,
:modeOfTransport,
:updateIndicator,
:commentText,
CURRENT_TIMESTAMP,
:createUserId,
null ,
null ,
:activityId,
:activityStart,
:activityFinish,
:wapStartDate,
:wapEndDate,
:activityDate,
:notForOffenderFlag,
:finalized,
(select coalesce (max(version_no),1) from weekly_activity_plan wap where wap_start_date >= :wapStartDate
and wap_end_date <= :wapEndDate and offender_book_id = :offenderBookId))
}


OWEACPLN_GET_USER_DETAILS
{  
SELECT
    staff_id,
    user_id,
    last_name,
    first_name,
    middle_name
FROM
    staff_members
WHERE 
	user_id = :userId
}
OWEACPLN_GET_MODE_OF_ACTIVITY_DESCRIPTION {
SELECT DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'WAP_ACTIVITY'AND CODE = :activityNew
}
OWEACPLN_GET_MANUALLY_ENTERED_ACTIVITIES_DATA {
select offender_book_id , activity_date as event_date, 'WAP_ACTIVITY' as event_type, 'WAP_ACTIVITY' as event_class, activity_start as start_time, activity_finish as end_time, activity_address as to_Agy_Loc_Desc , activity as event_type_desc,(select description from reference_codes rc  where rc.code= wap.activity and rc.domain='WAP_ACTIVITY' ) as event_sub_type_desc,( select comment_text from wap_comments wc where wc.wap_start_date = wap.wap_start_date and wc.wap_end_date = wap.wap_end_date and wc.offender_book_id = wap.offender_book_id) as comment_text,depart_start_time, return_time from WEEKLY_ACTIVITY_PLAN wap where offender_book_id = :offenderBookId AND activity_id is null
}

OWEACPLN_GET_MANUALLY_ENTERED_ACTIVITIES_BASED_EVENT_DATA {
select offender_book_id , activity_date as event_date, 'WAP_ACTIVITY' as event_type, 'WAP_ACTIVITY' as event_class, activity_start as start_time, activity_finish as end_time, activity_address as to_Agy_Loc_Desc , activity as event_type_desc,(select description from reference_codes rc  where rc.code= wap.activity and rc.domain='WAP_ACTIVITY' ) as event_sub_type_desc,( select comment_text from wap_comments wc where wc.wap_start_date = wap.wap_start_date and wc.wap_end_date = wap.wap_end_date and wc.offender_book_id = wap.offender_book_id) as comment_text,depart_start_time, return_time from WEEKLY_ACTIVITY_PLAN wap where offender_book_id = :offenderBookId and activity_date in(:EVENTDATE) AND activity_id is null
}

OWEACPLN_GET_CLEAR_SESSION_DATA {
select 'ACP_' || event_id as event_id_temp, offender_book_id ,date_trunc('week', event_date)::date as from_date, date_trunc('week', event_date)::date + 6 as to_date
from
	offender_course_attendances
where
	crs_sch_id in (
	select
		crs_sch_id
	from
		course_schedules
	where
		start_time >= (
		select
			start_time
		from
			v_acp_schedules
		where
			crs_sch_id =:p_crs_sch_id
			and program_instance_id =:p_program_instance_id)
		and catch_up_crs_sch_id is null
		and schedule_status = 'SCH'
		and crs_acty_id in (with recursive cte as (
		select
			crs_acty_id
		from
			course_activities
		where
			crs_acty_id = coalesce(:p_phase_instance_id::text, :p_program_instance_id::text)::bigint
	union all
		select
			ca.crs_acty_id
		from
			course_activities ca
		join cte c on
			(c.crs_acty_id = parent_crs_acty_id) )
		select
			*
		from
			cte))
}

OWEACPLN_GET_MAX_VERSION_DATA{
select
	 coalesce(max(version_no),0)
	from
		WEEKLY_ACTIVITY_PLAN wap
	where
		wap.offender_book_id = :offenderBookId
		and wap.wap_start_date >= :fromDate
		and wap.wap_end_date <= :toDate
}
OWEACPLN_EM_WEEKLY_PLAN_HISTORY_ACTIVITY_DETAILS_DELETE_LIST {
delete  from weekly_activity_plan_hty waph where weekly_activity_plan_id =:weeklyActivityPlanId and version_no =:versionNo
}

OWEACPLN_GET_MAX_HISTORY_VERSION_DATA {
select
	 coalesce(max(version_no),0)
	from
		weekly_activity_plan_hty wap
	where
		wap.offender_book_id = :offenderBookId
		and wap.wap_start_date >= :fromDate
		and wap.wap_end_date <= :toDate
}