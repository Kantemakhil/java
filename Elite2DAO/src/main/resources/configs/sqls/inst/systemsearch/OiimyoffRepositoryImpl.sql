GET_OIIMYOFF_MY_OFFENDERS_LIST_DATA {
select * from ( select O.OFFENDER_ID , O.LAST_NAME , O.FIRST_NAME , O.MIDDLE_NAME, OB.OFFENDER_BOOK_ID, O.OFFENDER_ID_DISPLAY, OB.INTAKE_AGY_LOC_ID, 
OB.COMMUNITY_ACTIVE_FLAG, OB.agy_loc_id, OB.booking_status, OB.booking_no, (select rc.description from reference_codes rc where rc.domain = 'CPLAN_ROLE'
and rc.code=cpsr.case_plan_role) as case_plan_flag, ( select description from agency_internal_locations ail where ail.unit_type is not null 
and ail.unit_type::text <> ''::text and internal_location_id = OB.living_unit_id) living_unit_description from OFFENDERS O, OFFENDER_BOOKINGS OB, case_plans cp, 
case_plan_staff_roles cpsr where OB.OFFENDER_ID = O.OFFENDER_ID and cp.offender_book_id=OB.offender_book_id and cpsr.offender_book_id=OB.offender_book_id 
and cp.offender_book_id =cpsr.offender_book_id and cp.case_plan_id=cpsr.case_plan_id and cp.case_plan_status='ACTIVE' and 
cpsr.staff_id in( select staff_id from staff_members sm where user_id =:userId)) vhb where agy_loc_id in ( select agy_loc_id from caseload_agency_locations cal
where caseload_id = :caseLoadId and agy_loc_id not in ('IN', 'OUT')) and booking_status = 'O'
}
GET_OIIMYOFF_MY_OFFENDERS_CASE_PLAN_ROLE{
select rc.description  from case_plan_staff_roles  cpsr  join  case_plans cp 
on (cp.offender_book_id = cpsr.offender_book_id  and cp.case_plan_id =cpsr.case_plan_id )
join reference_codes rc on cpsr.case_plan_role =rc.code and rc.domain = 'CPLAN_ROLE'
where cp.case_plan_status ='ACTIVE' and cpsr.cp_owner ='Y' 
and cp.offender_book_id =:offenderBookId  
}


