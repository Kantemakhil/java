OCSPROGR_PROVIDER_REFGROUP {
SELECT  ACTIVE_FLAG,DESCRIPTION DESCRIPTION , TO_CHAR(PROGRAM_ID) CODE FROM PROGRAM_SERVICES WHERE PROGRAM_CATEGORY ='PWS'  AND ACTIVE_FLAG='Y'
}

OCSPROGR_PROVIDER_TYPE_REF_CODE {
SELECT REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE, REF_CODE.LIST_SEQ FROM REFERENCE_CODES REF_CODE WHERE DOMAIN = 'PS_PROV_TYPE' AND (ACTIVE_FLAG = 'Y' ) ORDER BY LIST_SEQ, DESCRIPTION, CODE
}

OCSPROGR_PROVIDER_EXTERNAL {
SELECT to_char( c.corporate_id ) code, c.corporate_name description FROM corporates c, corporate_types ct WHERE c.corporate_id = ct.corporate_id AND ct.corporate_type = 'PWS' AND c.active_flag = 'Y' ORDER BY corporate_name 
}

OCSPROGR_INTERNAL_PROVIDER{
select at.team_code team_code, at.description description , to_char(at.team_id) code
from automation_teams at,team_staff_members tm,
	staff_members sm  where at.TEAM_ID in ( select TEAM_ID from team_agency_loc where AGY_LOC_ID in
( select ol.agy_loc_id from CASELOAD_AGENCY_LOCATIONS ol, agency_locations al where caseload_id ='CC' ) ) and AT.active_flag ='Y' and At.expiry_date is null
and at.team_id = TM.team_id 
and at.team_id in (select
		TF.team_id
	from
		team_functions TF
	where
		function_type = 'PWS')
		and tm.staff_id = sm.staff_id
	and sm.user_id =:userId
order by DESCRIPTION
}

OCSPROGR_OFFENDERS_PROG { 
select
	offender_id_display,
	last_name,
	first_name,
	referral_date,
	status,
	offender_prg_obligation_id,
	program_id,
	offender_start_date,
	OFFENDER_END_DATE,
	OFFENDER_BOOK_ID
from
	(
	select
		A.*,
		B.offender_start_date,
		B.crs_acty_id
	from
		(
		select
			VHB.OFFENDER_BOOK_ID,
			VHB.offender_id_display,
			VHB.last_name,
			VHB.first_name,
			opo.referral_date,
			opo.status,
			opo.program_id,
			opo.offender_prg_obligation_id,
			opo.end_date as OFFENDER_END_DATE
		from
			(
			select
				O.OFFENDER_ID,
				O.LAST_NAME,
				O.FIRST_NAME,
				OB.OFFENDER_BOOK_ID,
				O.OFFENDER_ID_DISPLAY,
				OB.INTAKE_AGY_LOC_ID,
				OB.COMMUNITY_ACTIVE_FLAG
			from
				OFFENDERS O,
				OFFENDER_BOOKINGS OB
			where
				OB.OFFENDER_ID = O.OFFENDER_ID ) as VHB,
			offender_prg_obligations opo
		where
			opo.obligation_source = (select caseload_type from CASELOADS where caseload_id =:currentCaseload) and
			opo.offender_book_id in
    (
			select
				ob.offender_book_id
			from
				offender_bookings ob
			where
				ob.booking_status = 'O'
				and
       ((coalesce (ob.active_flag,
				'')= '')
					or ob.active_flag = 'Y')
				and
      ( (ob.intake_agy_loc_id in(
				select
					al.AGY_LOC_ID
				from
					AGENCY_LOCATIONS al
				where
					al.AGENCY_LOCATION_TYPE = 'COMM'
					and al.AGY_LOC_ID in (
					select
						AGY_LOC_ID
					from
						CASELOAD_AGENCY_LOCATIONS cal
					where
						cal.CASELOAD_ID = :currentCaseload )))
				or ((
		ob.agy_loc_id in (
				select
					al.AGY_LOC_ID
				from
					AGENCY_LOCATIONS al
				where
					al.AGENCY_LOCATION_TYPE = 'INST'
					and al.AGY_LOC_ID in (
					select
						AGY_LOC_ID
					from
						CASELOAD_AGENCY_LOCATIONS cal
					where
						cal.CASELOAD_ID = :currentCaseload ))))) )
			and
  opo.event_type = 'PWS'
			and
  VHB.offender_book_id = opo.offender_book_id) A
	left join offender_program_profiles B on 
		A.offender_prg_obligation_id = B.offender_prg_obligation_id) A
where
	((coalesce (OFFENDER_END_DATE::timestamp,
	'2020-12-12')= '2020-12-12' )
		or OFFENDER_END_DATE >= current_date) and 
}

OCSPROGR_LEGEL_EXECUTEQUERY {
select * from offender_prg_obligations opo where sentence_seq  is not null  and offender_book_id =:offender_book_id and offender_prg_obligation_id = :offenderPrgObligationId

}

