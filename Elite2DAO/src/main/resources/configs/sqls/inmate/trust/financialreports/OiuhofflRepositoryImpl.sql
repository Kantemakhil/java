OIUHOFFL_VOFFBKG_FIND_V_HEADER_BLOCK {
 	select
	V_HEADER_BLOCK.OFFENDER_ID,
	V_HEADER_BLOCK.ALIAS_OFFENDER_ID,
	V_HEADER_BLOCK.OFFENDER_ID_DISPLAY,
	V_HEADER_BLOCK.LAST_NAME,
	V_HEADER_BLOCK.FIRST_NAME,
	V_HEADER_BLOCK.MIDDLE_NAME,
	V_HEADER_BLOCK.SUFFIX,
	V_HEADER_BLOCK.BIRTH_DATE,
	V_HEADER_BLOCK.OFFENDER_BOOK_ID,
	V_HEADER_BLOCK.BOOKING_NO,
	V_HEADER_BLOCK.BOOKING_BEGIN_DATE,
	V_HEADER_BLOCK.BOOKING_END_DATE,
	V_HEADER_BLOCK.AGY_LOC_ID,
	V_HEADER_BLOCK.AGENCY_IML_ID,
	V_HEADER_BLOCK.LIVING_UNIT_ID,
	V_HEADER_BLOCK.DISCLOSURE_FLAG,
	V_HEADER_BLOCK.ACTIVE_FLAG,
	V_HEADER_BLOCK.BOOKING_STATUS,
	V_HEADER_BLOCK.LIVING_UNIT_DESCRIPTION,
	V_HEADER_BLOCK.IN_OUT_STATUS,
	V_HEADER_BLOCK.STATUS_DISPLAY,
	V_HEADER_BLOCK.ROOT_OFFENDER_ID,
	V_HEADER_BLOCK.ASSIGNED_STAFF_ID,
	V_HEADER_BLOCK.AGY_LOC_TYPE,
	V_HEADER_BLOCK.CREATE_AGY_LOC_ID,
	V_HEADER_BLOCK.BOOKING_TYPE,
	V_HEADER_BLOCK.BOOKING_CREATED_DATE,
	V_HEADER_BLOCK.LOCATION_CODE,
	V_HEADER_BLOCK.LIV_UNIT_DESC,
	V_HEADER_BLOCK.INTAKE_AGY_LOC_ID,
	V_HEADER_BLOCK.COMMUNITY_ACTIVE_FLAG,
	CREATE_INTAKE_AGY_LOC_ID,
	V_HEADER_BLOCK.COMMUNITY_STATUS,
	V_HEADER_BLOCK.STATUS_REASON,
	V_HEADER_BLOCK.HEADER_STATUS,
	V_HEADER_BLOCK.AGE,
	V_HEADER_BLOCK.GENDER,
	V_HEADER_BLOCK.OFFICER,
	V_HEADER_BLOCK.PRISON_LOCATION,
	V_HEADER_BLOCK.OFF_ALERTS,
	V_HEADER_BLOCK.STATUS_1,
	V_HEADER_BLOCK.STATUS_2,
	V_HEADER_BLOCK.STATUS_3,
	V_HEADER_BLOCK.ETHNICITY,
	V_HEADER_BLOCK.MOVEMENT_REASON,
	V_HEADER_BLOCK.OFF_SUP_LEVEL
from
	V_HEADER_BLOCK V_HEADER_BLOCK
where
	( ( v_header_block.OFFENDER_BOOK_ID in(
	select
		vhb2.OFFENDER_BOOK_ID
	from
		v_header_block vhb2
	where
		vhb2.root_offender_id = v_header_block.root_offender_id
		and vhb2.active_flag = 'Y'
		and exists (
		select
			cal.agy_loc_id
		from
			caseload_agency_locations cal,
			caseloads csld
		where
			cal.caseload_id = :caseload_id
			and csld.caseload_type = 'INST'
			and csld.caseload_id = cal.caseload_id
			and cal.agy_loc_id = vhb2.agy_loc_id
			and :caseload_type = 'INST'))
	or ( v_header_block.OFFENDER_BOOK_ID =(
	select
		MAX(vhb2.OFFENDER_BOOK_ID)
	from
		v_header_block vhb2
	where
		vhb2.root_offender_id = v_header_block.root_offender_id
		and exists (
		select
			cal.agy_loc_id
		from
			caseload_agency_locations cal,
			caseloads csld
		where
			cal.caseload_id = :caseload_id
			and csld.caseload_type = 'INST'
			and csld.caseload_id = cal.caseload_id
			and cal.agy_loc_id = vhb2.agy_loc_id
			and :caseload_type = 'INST')
		and not exists (
		select
			'1'
		from
			OFFENDER_BOOKINGS BK,
			caseload_agency_locations cal,
			caseloads csld
		where
			BK.ROOT_OFFENDER_ID = vhb2.ROOT_OFFENDER_ID
			and BK.ACTIVE_FLAG = 'Y'
			and csld.caseload_type = 'INST'
			and csld.caseload_id = cal.caseload_id
			and cal.agy_loc_id = BK.agy_loc_id
			and :caseload_type = 'INST') )))
	or ( v_header_block.offender_book_id = (
	select
		MAX(vhb2.offender_book_id)
	from
		v_header_block vhb2
	where
		vhb2.root_offender_id = v_header_block.root_offender_id
		and ( vhb2.community_active_flag = 'Y'
			or (not exists(
			select
				'X'
			from
				v_header_block vhb3
			where
				vhb3.community_active_flag = 'Y'
				and vhb3.root_offender_id = vhb2.root_offender_id)))
		and ((vhb2.intake_agy_loc_id <> 'CLOSE'
			and exists(
			select
				'X'
			from
				offender_booking_agy_locs locs,
				caseload_agency_locations cal1
			where
				cal1.caseload_id =:caseload_id
				and cal1.agy_loc_id = locs.agy_loc_id
				and locs.offender_book_id = vhb2.offender_book_id
				--	AND locs.removed_date IS  NULL
				and :caseload_type = 'COMM'))
		or (vhb2.intake_agy_loc_id = 'CLOSE'
			and exists(
			select
				'X'
			from
				offender_booking_agy_locs locs,
				caseload_agency_locations cal1
			where
				cal1.caseload_id = :caseload_id
				and cal1.agy_loc_id = locs.agy_loc_id
				and locs.offender_book_id = vhb2.offender_book_id
				and :caseload_type = 'COMM'))))))
				
																															
}
