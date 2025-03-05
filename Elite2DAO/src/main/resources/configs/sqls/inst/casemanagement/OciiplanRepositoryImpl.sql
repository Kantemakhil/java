
OCIIPLAN_FIND_RGINSTAGYLOC {
 	SELECT AGY_LOC.DESCRIPTION ,        AGY_LOC.AGY_LOC_ID CODE   FROM AGENCY_LOCATIONS AGY_LOC  WHERE AGENCY_LOCATION_TYPE = 'INST'    AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' )    AND EXISTS (SELECT 'X'           FROM CASELOAD_AGENCY_LOCATIONS CAL          WHERE CAL.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID            AND CAL.CASELOAD_ID = :caseLoadId )    AND ACTIVE_FLAG = 'Y'
}

OCIIPLAN_FIND_COMINSTAGYLOC {
 	SELECT AGY_LOC.DESCRIPTION ,        AGY_LOC.AGY_LOC_ID  code   FROM AGENCY_LOCATIONS AGY_LOC  WHERE AGY_LOC.AGY_LOC_ID IN        (SELECT CA.AGY_LOC_ID           FROM CASELOAD_AGENCY_LOCATIONS CA          WHERE CA.CASELOAD_ID = :caseLoadId            AND CA.AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) )    AND ACTIVE_FLAG = 'Y'    AND AGENCY_LOCATION_TYPE = 'COMM'
}

OCIIPLAN_CASEPLANS_FIND_CASE_PLANS {
select
	CP.OFFENDER_BOOK_ID,
	CP.CASE_PLAN_ID,
	CP.FROM_DATE,
	CP.POSITION,
	CP.ROLE,
	CP.SAC_STAFF_ID,
	CP.AGY_LOC_ID,
	CP.CASE_PLAN_STATUS,
	CP.CREATION_DATE,
	CP.CREATION_USER,
	CP.END_DATE,
	CP.SUPERVISION_LEVEL,
	CP.CHANGES,
	CP.NEXT_REVIEW_DATE,
	CP.START_DATE,
	CP.CASELOAD_TYPE,
	CP.VERIFIED_FLAG,
	CP.CREATE_DATETIME,
	CP.CREATE_USER_ID,
	CP.MODIFY_DATETIME,
	CP.MODIFY_USER_ID,
	CP.INST_SAC_STAFF_ID,
	CP.INST_FROM_DATE,
	CP.INST_POSITION,
	CP.INST_ROLE,
	CP.AUTO_ASSESS_MODIFY_DATETIME,
	CP.AUTO_CONDITION_MODIFY_DATETIME,
	CP.SEAL_FLAG,
	(select
		sm.LAST_NAME || ', ' || sm.FIRST_NAME
	from
		staff_members sm
	where
		staff_id in
	(
		select
			staff_id
		from
			case_plan_staff_roles cpsr
		where
			cpsr.cn_officer = 'Y'
			and cpsr.offender_book_id = CP.OFFENDER_BOOK_ID and cpsr.case_plan_id=CP.CASE_PLAN_ID)) as INST_STAFF_NAME,
	(select sm.last_name ||', ' || sm.first_name from staff_members sm where staff_id =SAC_STAFF_ID) as COMM_STAFF_NAME,
	
	case
		when (
		select
			count(*)
		from
			case_plan_staff_roles ooad
		where
			offender_book_id = cp.offender_book_id
			and case_plan_id = cp.CASE_PLAN_ID
			and cp_owner = 'Y') > 1 then 'Multiple Officers'
		else (
		select
			concat (last_name || ', ' || first_name)
		from
			staff_members sm,
			case_plan_staff_roles ooad
		where
			sm.staff_id = ooad.staff_id
			and ooad.cp_owner = 'Y'
			and offender_book_id = cp.offender_book_id
			and case_plan_id = cp.CASE_PLAN_ID )
	end  as CP_OWNER_NAME,
	
		(select ocn2.contact_date  from OFFENDER_CASE_NOTES ocn2 where ocn2.offender_book_id =CP.OFFENDER_BOOK_ID
	and ocn2.case_note_type ='CASEOFF' and ocn2.case_note_sub_type ='PCO'  and create_datetime =
	(select max(create_datetime) from OFFENDER_CASE_NOTES where offender_book_id =CP.OFFENDER_BOOK_ID
	and case_note_type ='CASEOFF' and case_note_sub_type ='PCO' )) as pcoCaseNoteDate,
	case
		when (CP.OFFENDER_BOOK_ID is not null) then (
		select
			O.LAST_NAME || ', ' || O.FIRST_NAME
		from
			OFFENDERS O,
			OFFENDER_BOOKINGS OB
		where
			OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID
			and O.OFFENDER_ID = OB.OFFENDER_ID)
		else null
	end OFFENDER_NAME,
	case
		when (CP.OFFENDER_BOOK_ID is not null) then (
		select
			OFFENDER_ID_DISPLAY
		from
			OFFENDERS O,
			OFFENDER_BOOKINGS OB
		where
			OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID
			and O.OFFENDER_ID = OB.OFFENDER_ID)
		else null
	end OFFENDER_ID_DISPLAY,
	TAG_ESTABLISHMENT_GET_AGY_LOC_DESC(CP.INST_CAL_AGY_LOC_ID) INST_CAL_AGY_LOC_ID,
	TAG_ESTABLISHMENT_GET_AGY_LOC_DESC(CP.CAL_AGY_LOC_ID) CAL_AGY_LOC_ID
from
	CASE_PLANS CP
where
	
}
OCIIPLAN_CASEPLANS_TAG_MAIN_GET_OFFENDER {
	SELECT distinct root_offender_id, last_name, first_name, agy_loc_id, offender_book_id, living_unit_id
                   FROM  V_HEADER_BLOCK_FN(:USERID)  V_HEADER_BLOCK
                  WHERE offender_id_display = :offenderIdDisplay AND #whereclause
}
