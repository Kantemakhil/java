OCSPROIN_FIND_RECORD_GROUP_TEAM {
 	SELECT
    t.description   description,
    t.team_code     code,
    t.team_id       
FROM
    automation_teams t
WHERE
    t.active_flag = 'Y'
    AND exists     ( SELECT
    tm.team_id
FROM
    team_staff_members    tm,
    staff_members   sm
WHERE
    tm.active_flag = 'Y'
    AND tm.staff_id = sm.staff_id
    AND sm.user_id = :createUserId
        AND tm.team_id = t.team_id )
AND t.team_id IN (
                     SELECT
                         team_id
                     FROM
                         team_functions
                     WHERE
                         function_type = 'UNPAID_WORK'
                 )
                 ORDER BY
                    
                     t.description,
                     t.team_code 
}

OCSPROIN_FIND_RGPROJECTS_TEAM {
	select
	CA.DESCRIPTION ,
	to_char(CA.CRS_ACTY_ID) CODE,
	CA.LIST_SEQ
from
	COURSE_ACTIVITIES CA,
	program_services ps
where
	CA.PROVIDER_PARTY_CLASS = 'TEAM'
	and CA.PROVIDER_PARTY_ID = :NBTTEAMID::numeric
	and ps.active_flag = 'Y' 
	and ps.program_category = 'UW'
	and ps.program_id = CA.program_id 
order by
	LIST_SEQ
}

OCSPROIN_FIND_RGPROJECTS_WITHOUT_TEAM {
select
	CA.DESCRIPTION ,
	to_char(CA.CRS_ACTY_ID) CODE,
	CA.LIST_SEQ
from
	COURSE_ACTIVITIES CA,
	program_services ps
where
	ps.active_flag = 'Y' 
	and ps.program_category = 'UW'
	and ps.program_id = CA.program_id 
order by
	LIST_SEQ
	}

OCSPROIN_COURSEACT_FIND_COURSE_ACTIVITIES {
select
(select
		count(distinct offender_book_id)
	from
		offender_program_profiles
	where
		offender_program_status = 'ALLOC'
		and CRS_ACTY_ID = ca.crs_acty_id
		and offender_book_id in (
		select
			ob2.offender_book_id
		from
			offender_bookings ob2
		where
			ob2.booking_status = 'O'
			and ((coalesce (ob2.community_active_flag,
			'')= '')
				or ob2.community_active_flag in('Y'))) )
as allocatedOffender,
(select
		count(distinct offender_book_id)
	from
		offender_program_profiles
	where
		offender_program_status = 'REF'
		and CRS_ACTY_ID = ca.crs_acty_id
		and offender_book_id in (
		select
			ob2.offender_book_id
		from
			offender_bookings ob2
		where
			ob2.booking_status = 'O'
			and ((coalesce (ob2.community_active_flag,
			'')= '')
				or ob2.community_active_flag in('Y'))) ) as referredOffenders,
(select distinct 'Y' from COURSE_ACTIVITY_PROFILES where crs_acty_id = ca.crs_acty_id ) as target_off_flag,
    ca.crs_acty_id,
     ca.caseload_id,
     ca.agy_loc_id,
     ca.description,
     ca.capacity,
     ca.active_flag,
     ca.expiry_date,
     ca.schedule_start_date,
     ca.schedule_end_date,
     ca.caseload_type,
     ca.services_address_id,
     ca.program_id,
     ca.parent_crs_acty_id,
     ca.provider_party_class,
     ca.provider_party_id,
     ca.provider_party_code,
     ca.beneficiary_name,
     ca.list_seq,
     ca.comment_text,
     ca.provider_type,
     ca.beneficiary_type,
     ca.code,
     ca.holiday_flag,
     ca.course_class,
     ca.course_activity_type,
     ca.create_datetime,
     ca.create_user_id,
     ca.modify_datetime,
     ca.modify_user_id,
     ca.seal_flag
FROM
    course_activities ca,
    COURSE_SCHEDULE_RULES csr
WHERE 
ca.program_id = (select program_id from program_services ps where ps.active_flag = 'Y' and ps.program_category = 'UW')
	and ca.crs_acty_id  = csr.crs_acty_id
	and ca.provider_party_class = 'TEAM' and ( COALESCE(:team_id,0) = 0 or ca.provider_party_id = :team_id ) and 
( COALESCE(:crs_acty_id,0) = 0 or ca.crs_acty_id = :crs_acty_id ) and 
( COALESCE(:monday_flag,'NULL') = 'NULL' or csr.monday_flag = :monday_flag ) and 
( COALESCE(:tuesday_flag,'NULL') = 'NULL' or csr.tuesday_flag = :tuesday_flag ) and
( COALESCE(:wednesday_flag,'NULL') = 'NULL' or csr.wednesday_flag = :wednesday_flag ) and
( COALESCE(:thursday_flag,'NULL') = 'NULL' or csr.thursday_flag = :thursday_flag ) and
( COALESCE(:friday_flag,'NULL') = 'NULL' or csr.friday_flag = :friday_flag ) and
( COALESCE(:saturday_flag,'NULL') = 'NULL' or csr.saturday_flag = :saturday_flag ) and
( COALESCE(:sunday_flag,'NULL') = 'NULL' or csr.sunday_flag = :sunday_flag ) and
(COALESCE(ca.schedule_end_date,'12-12-2000') = '12-12-2000' or 
ca.schedule_end_date >= current_timestamp )
ORDER BY ca.active_flag desc, ca.schedule_start_date desc

}


OCSPROIN_FIND_RGPROJECTS
{
select
	DESCRIPTION ,
	to_char(CRS_ACTY_ID) CODE,
	LIST_SEQ
from
	COURSE_ACTIVITIES CA
where
	COURSE_ACTIVITY_TYPE = 'WS'
	and (CA.ACTIVE_FLAG = 'Y'
		or '' = 'ENTER-QUERY' )
order by
	LIST_SEQ
	
}

OCSPROIN_PROGRAM_PROFILES_REFERRED_LIST{
SELECT
	O.OFFENDER_ID_DISPLAY,
	O.LAST_NAME,
	O.FIRST_NAME,
	OPP.OFFENDER_START_DATE REFERRAL_DATE,	
	(SELECT OS.START_DATE FROM OFFENDER_SENT_CONDITIONS OS WHERE OS.OFFENDER_SENT_CONDITION_ID = OPP.OFFENDER_SENT_CONDITION_ID) AS CONDITION_START_DATE, 
	(SELECT OS.EXPIRY_DATE FROM OFFENDER_SENT_CONDITIONS OS WHERE OS.OFFENDER_SENT_CONDITION_ID = OPP.OFFENDER_SENT_CONDITION_ID) AS OFFENDER_END_DATE ,
	OPP.CRS_ACTY_ID,
	(SELECT OS.LENGTH ||OS.LENGTH_UNIT FROM OFFENDER_SENT_CONDITIONS OS WHERE OS.OFFENDER_SENT_CONDITION_ID = OPP.OFFENDER_SENT_CONDITION_ID) AS CONDITION_LENGTH 
FROM
	OFFENDER_PROGRAM_PROFILES OPP,
	OFFENDERS O,
	OFFENDER_BOOKINGS OFF_BOOK
WHERE
	OPP.OFFENDER_BOOK_ID IN (
	SELECT
		OB.OFFENDER_BOOK_ID
	FROM
		OFFENDER_BOOKINGS OB
		      WHERE
		       OB.BOOKING_STATUS = 'O'
		     AND ((COALESCE (OB.COMMUNITY_ACTIVE_FLAG, '')= '') OR OB.COMMUNITY_ACTIVE_FLAG IN('Y'))
    )
	AND
	OPP.CRS_ACTY_ID = :CRS_ACTY_ID
	AND OPP.OFFENDER_PROGRAM_STATUS = 'REF'
	AND OPP.OFFENDER_BOOK_ID = OFF_BOOK.OFFENDER_BOOK_ID
	AND O.OFFENDER_ID IN (
	SELECT
		OB1.OFFENDER_ID
	FROM
		OFFENDER_BOOKINGS OB1
	WHERE
		OB1.OFFENDER_BOOK_ID = OPP.OFFENDER_BOOK_ID
    )
}

OCSPROIN_PROGRAM_PROFILES_EXECUTE_QUERY {
	select
	o.LAST_NAME,
	o.first_name ,
	O.OFFENDER_ID_DISPLAY,
	(SELECT OS.START_DATE FROM OFFENDER_SENT_CONDITIONS OS WHERE OS.OFFENDER_SENT_CONDITION_ID = OPP.OFFENDER_SENT_CONDITION_ID) AS CONDITION_START_DATE, 
	(SELECT OS.EXPIRY_DATE FROM OFFENDER_SENT_CONDITIONS OS WHERE OS.OFFENDER_SENT_CONDITION_ID = OPP.OFFENDER_SENT_CONDITION_ID) AS CONDITION_END_DATE ,
	(SELECT OS.sentence_seq FROM OFFENDER_SENT_CONDITIONS OS WHERE OS.OFFENDER_SENT_CONDITION_ID = OPP.OFFENDER_SENT_CONDITION_ID) AS sentence_seq ,
    OPP.OFFENDER_BOOK_ID as OFFENDER_BOOK_ID,
	  opp.crs_acty_id,
	(SELECT OS.LENGTH ||OS.LENGTH_UNIT FROM OFFENDER_SENT_CONDITIONS OS WHERE OS.OFFENDER_SENT_CONDITION_ID = OPP.OFFENDER_SENT_CONDITION_ID) AS CONDITION_LENGTH 
FROM
	OFFENDER_PROGRAM_PROFILES OPP,
	OFFENDERS O,
	OFFENDER_BOOKINGS OFF_BOOK
WHERE
	OPP.OFFENDER_BOOK_ID IN (
	SELECT
		OB.OFFENDER_BOOK_ID
	FROM
		OFFENDER_BOOKINGS OB
		      WHERE
		       OB.BOOKING_STATUS = 'O'
		     AND ((COALESCE (OB.community_active_flag, '')= '') OR OB.community_active_flag IN('Y'))
    )
	AND
	OPP.CRS_ACTY_ID = :CRS_ACTY_ID
	AND OPP.OFFENDER_PROGRAM_STATUS = 'ALLOC'
	AND OPP.OFFENDER_BOOK_ID = OFF_BOOK.OFFENDER_BOOK_ID
	AND O.OFFENDER_ID IN (
	SELECT
		OB1.OFFENDER_ID
	FROM
		OFFENDER_BOOKINGS OB1
	WHERE
		OB1.OFFENDER_BOOK_ID = OPP.OFFENDER_BOOK_ID
    )
}


OCSPROIN_SCH_WEEKDAYS {
select distinct to_char(event_date , 'DY'::text)  from OFFENDER_COURSE_ATTENDANCES where offender_book_id  = :OFFENDER_BOOK_ID and crs_acty_id =:CRS_ACTY_ID 
}
