GET_OFFENDER_IDENTIFIERS{
SELECT offender_id, coalesce (oms_miscellaneous_getdesccode('ID_TYPE', identifier_type), 'Undefined') identifier_type, identifier FROM offender_identifiers WHERE offender_id = :p_offender_id
}

GET_PROFILE_VALUE{
SELECT profile_value FROM system_profiles WHERE profile_type = :p_profile_type AND profile_code = :p_profile_code
}

RESULT_SET_OPERATION_ONE{
select
	a.last_name ,
	a.first_name ,
	a.middle_name ,
	a.sex_code ,
	a.birth_date ,
	a.offender_id ,
	a.root_offender_id ,
	a.offender_id_display ,
	(case
		(
		select
			COUNT(offender_id)
		from
			offender_bookings b
		where
			b.offender_id = a.offender_id
			and b.offender_book_id = (
			select
				MAX(vhb2.offender_book_id)
			from
				offender_bookings vhb2
			where
				vhb2.root_offender_id = a.root_offender_id))
		when 0 then 'N'
		else 'Y'
	end) working_name_flag,
	a.gender_code,
	a.ALIAS_NAME_TYPE,
	a.second_middle_name from (
	select
		last_name ,
		first_name ,
		middle_name ,
		sex_code ,
		birth_date ,
		offender_id ,
		root_offender_id ,
		offender_id_display ,
		race_code ,
		gender_code ,
		OMS_MISCELLANEOUS_GETDESCCODE('NAME_TYPE',
		ALIAS_NAME_TYPE) as ALIAS_NAME_TYPE,
		second_middle_name
	from
		offenders
	where
		upper(last_name_alpha_key) = upper(:p_lname_key)) a
where
	regexp_replace(upper(a.last_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_last_name),
	'[- \,''"]',
	'',
	'g')
	and ((coalesce(cast(regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g') as varchar),
	'') = '')
		or
   regexp_replace(upper(a.first_name),
		'[- \,''"]',
		'',
		'g') like regexp_replace(upper(:p_first_name),
		'[- \,''"]',
		'',
		'g'))
	and ((coalesce(cast(regexp_replace(upper(:p_middle_name),
	'[ -,''"]',
	'',
	'g') as varchar),
	'') = '')
		or
   regexp_replace(upper(a.middle_name),
		'[- \,''"]',
		'',
		'g') like regexp_replace(upper(:p_middle_name),
		'[- \,''"]',
		'',
		'g'))
	and ((coalesce(cast(regexp_replace(upper(:p_second_middle_name),
	'[- \,''"]',
	'',
	'g') as varchar),
	'') = '')
		or
   regexp_replace(upper(a.second_middle_name),
		'[- \,''"]',
		'',
		'g') like regexp_replace(upper(:p_second_middle_name),
		'[- \,''"]',
		'',
		'g'))
	and ((coalesce(cast(:lv_from_date as date)::text,
	'')= ''
		and coalesce(cast(:lv_to_date as date)::text,
			'')= '')
		or
                         (a.birth_date between :lv_from_date and :lv_to_date))
	and (coalesce(cast(:p_birth_date as date)::text,
			'')= ''
		or
                         a.birth_date = :p_birth_date)
	and (coalesce(cast(:p_sex_code as varchar),
			'')= ''
		or a.sex_code = :p_sex_code)
	and (coalesce(cast(:p_gender_code as varchar),
			'')= ''
		or a.gender_code = :p_gender_code)
	and (coalesce(cast(:p_agedate as date)::text,
			'')= ''
		or
                         a.birth_date between :lv_from_agedate and
                         :lv_to_agedate)
	and (coalesce(cast(:p_ethnicity as varchar),
			'')= ''
		or a.race_code = :p_ethnicity)
order by 1,
	 2,
	3
}

RESULT_SET_OPERATION_TWO{

select
	a.last_name,
	a.first_name,
	a.middle_name,
	a.sex_code,
	a.birth_date,
	a.offender_id,
	a.root_offender_id,
	a.offender_id_display,
	a.second_middle_name,
	(
	case (
	select
		COUNT(offender_id)
	from
		offender_bookings b
	where
		b.offender_id = a.offender_id
		and b.offender_book_id = (
		select
			MAX(vhb2.offender_book_id)
		from
			offender_bookings vhb2
		where
			vhb2.root_offender_id = a.root_offender_id ) )
	when 0 then 'N'
	else 'Y'
end ) working_name_flag,
a.gender_code 
from
	offenders a
where
	( ( regexp_replace(upper(a.last_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_last_name),
	'[- \,''"]',
	'',
	'g')
	and regexp_replace(upper(a.first_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g') )
	or ( regexp_replace(upper(a.last_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g')
		and regexp_replace(upper(a.first_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_last_name),
	'[- \,''"]',
	'',
	'g') ) )
	and ( (cast(:p_middle_name as varchar) is null )
		or a.middle_name = :p_middle_name )
	and ( (cast(:p_second_middle_name as varchar) is null )
		or a.second_middle_name = :p_second_middle_name )
	and ( ( (cast(:lv_from_date as date) is null )
		and (cast(:lv_to_date as date) is null ) )
	or ( a.birth_date between :lv_from_date and :lv_to_date ) )
	and ( (cast(:p_birth_date as date) is null )
		or a.birth_date = :p_birth_date )
	and ( (cast(:p_sex_code as varchar) is null )
		or a.sex_code = :p_sex_code )
	and ( (cast(:p_gender_code as varchar) is null )
		or a.gender_code = :p_gender_code )
	and ( (cast(:p_agedate as date) is null )
		or a.birth_date between :lv_from_agedate and :lv_to_agedate )
	and ( (cast(:p_ethnicity as varchar) is null )
		or a.race_code = :p_ethnicity )
order by
	1,
	2,
	3
}

RESULT_SET_OPERATION_THREE{
select
	a.last_name,
	a.first_name,
	a.middle_name,
	a.sex_code,
	a.birth_date,
	a.offender_id,
	a.root_offender_id,
	a.offender_id_display,
	a.second_middle_name,
	(
	case (
	select
		COUNT(offender_id)
	from
		offender_bookings b
	where
		b.offender_id = a.offender_id
		and b.offender_book_id = (
		select
			MAX(vhb2.offender_book_id)
		from
			offender_bookings vhb2
		where
			vhb2.root_offender_id = a.root_offender_id ) )
	when 0 then 'N'
	else 'Y'
end ) working_name_flag,
a.gender_code 
from
	offenders a
where
	( regexp_replace(upper(a.last_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_last_name),
	'[- \,''"]',
	'',
	'g')
		and (
		
		
			((coalesce(cast(regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g') as varchar),
	'') = '')
		or
   regexp_replace(upper(a.first_name),
		'[- \,''"]',
		'',
		'g') like regexp_replace(upper(:p_first_name),
		'[- \,''"]',
		'',
		'g'))
		
		
        OR 
        
         regexp_replace(upper(a.first_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g')
        
        OR regexp_replace(upper(a.first_name),
	'[- \,''"]',
	'',
	'g')  IN (
            SELECT DISTINCT upper(ns.base_name)
            FROM name_synonyms ns
            where
            
             regexp_replace(upper(ns.name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g')
            
        )
        
		or ( regexp_replace(upper(a.first_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g') )
			or ( regexp_replace(upper(a.first_name),
	'[- \,''"]',
	'',
	'g') in (
			select
				ns1.name
			from
				name_synonyms ns1
			where
			
			 regexp_replace(upper(ns1.base_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g')
				
		union
			select
				ns2.name
			from
				name_synonyms ns2
			where
				base_name = (
				select
					distinct (ns3.base_name)
				from
					name_synonyms ns3
				where
				
				 regexp_replace(upper(ns3.name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_first_name),
	'[- \,''"]',
	'',
	'g')
				
			 ) ) ) ) )
	and ( (cast(upper(regexp_replace(upper(:p_middle_name),
	'[- \,''"]',
	'',
	'g')) as varchar) is null )
		or 
		
		 regexp_replace(upper(a.middle_name),
	'[- \,''"]',
	'',
	'g') = regexp_replace(upper(:p_middle_name),
	'[- \,''"]',
	'',
	'g')  )
	and ( (cast(:p_second_middle_name as varchar) is null )
		or a.second_middle_name = :p_second_middle_name )
	and ( ( (cast(:lv_from_date as date) is null )
		and (cast(:lv_to_date as date) is null ) )
	or ( a.birth_date between :lv_from_date and :lv_to_date ) )
	and ( (cast(:p_birth_date as date) is null )
		or a.birth_date = :p_birth_date )
	and ( (cast(:p_sex_code as varchar) is null )
		or a.sex_code = :p_sex_code )
	and ( (cast(:p_gender_code as varchar) is null )
		or a.gender_code  = :p_gender_code )
	and ( (cast(:p_agedate as date) is null )
		or a.birth_date between :lv_from_agedate and :lv_to_agedate )
	and ( (cast(:p_ethnicity as varchar) is null )
		or a.race_code = :p_ethnicity )
order by
	1,
	2,
	3
	



}

RESULT_SET_OPERATION_FOUR{
SELECT a.last_name, a.first_name, a.middle_name, a.sex_code, a.birth_date, a.offender_id, a.root_offender_id, a.offender_id_display,a.second_middle_name, ( CASE ( SELECT COUNT(offender_book_id) FROM offender_bookings b WHERE b.offender_id = a.offender_id AND b.offender_book_id = ( SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id ) ) WHEN 0 THEN 'N' ELSE 'Y' END ) working_name_flag,a.gender_code  FROM offenders a WHERE a.offender_id_display like '%' || :v_offender_id_display ORDER BY 1, 2, 3
}

RESULT_SET_OPERATION_FIVE{
SELECT a.last_name, a.first_name, a.middle_name, a.sex_code, a.birth_date, a.offender_id, a.root_offender_id, a.offender_id_display,a.second_middle_name, ( CASE ( SELECT COUNT(offender_book_id) FROM offender_bookings b WHERE b.offender_id = a.offender_id AND b.offender_book_id = ( SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id ) ) WHEN 0 THEN 'N' ELSE 'Y' END ) working_name_flag,a.gender_code FROM offenders a WHERE a.offender_id_display = :v_offender_id_display AND EXISTS ( SELECT c.offender_id FROM offender_bookings c WHERE c.booking_no = :p_book_no AND c.offender_id = a.offender_id ) ORDER BY 1, 2, 3
}

RESULT_SET_OPERATION_SIX{
SELECT a.last_name, a.first_name, a.middle_name, a.sex_code, a.birth_date, a.offender_id, a.root_offender_id, a.offender_id_display,a.second_middle_name, ( CASE ( SELECT COUNT(offender_book_id) FROM offender_bookings b WHERE b.offender_id = a.offender_id AND b.offender_book_id = ( SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id ) ) WHEN 0 THEN 'N' ELSE 'Y' END ) working_name_flag,a.gender_code FROM offenders a, offender_bookings c WHERE a.offender_id = c.offender_id AND c.booking_no = :p_book_no ORDER BY 1, 2, 3
}

RESULT_SET_OPERATION_SEVEN{
	select
	a.last_name,
	a.first_name,
	a.middle_name,
	a.sex_code,
	a.birth_date,
	a.offender_id,
	a.root_offender_id,
	a.offender_id_display,
	a.second_middle_name,
	(
	case (
	select
		COUNT(offender_book_id)
	from
		offender_bookings b
	where
		b.offender_id = a.offender_id
		and b.offender_book_id = (
		select
			MAX(vhb2.offender_book_id)
		from
			offender_bookings vhb2
		where
			vhb2.root_offender_id = a.root_offender_id ) )
	when 0 then 'N'
	else 'Y'
end ) working_name_flag,
a.gender_code
from
	offenders a
where
	a.offender_id in (
	select
		distinct offender_id
	from
		offender_identifiers
	where
		identifier_type = :p_identifier_type
		and identifier like '%' || :p_identifier_value || '%' )
	and ( (cast(:p_book_no as varchar) is null )
		or ( (cast(:p_book_no as varchar) is not null )
			and exists (
			select
				c.offender_id
			from
				offender_bookings c
			where
				c.booking_no = :p_book_no
				and c.offender_id = a.offender_id ) ) )
order by
	1,
	2,
	3
}

RESULT_SET_OPERATION_EIRHT{
	select
	a.last_name,
	a.first_name,
	a.middle_name,
	a.sex_code,
	a.birth_date,
	a.offender_id,
	a.root_offender_id,
	a.offender_id_display,
	a.second_middle_name,
	(
	case (
	select
		COUNT(offender_book_id)
	from
		offender_bookings b
	where
		b.offender_id = a.offender_id
		and b.offender_book_id = (
		select
			MAX(vhb2.offender_book_id)
		from
			offender_bookings vhb2
		where
			vhb2.root_offender_id = a.root_offender_id ) )
	when 0 then 'N'
	else 'Y'
end ) working_name_flag,
a.gender_code
from
	offenders a
where
	a.offender_id_display = :v_offender_id_display
	and a.offender_id in (
	select
		distinct offender_id
	from
		offender_identifiers
	where
		identifier_type = :p_identifier_type
		and identifier like '%' || :p_identifier_value || '%' )
	and ( (cast(:p_book_no as varchar) is null )
		or ( (cast(:p_book_no as varchar) is not null )
			and exists (
			select
				c.offender_id
			from
				offender_bookings c
			where
				c.booking_no = :p_book_no
				and c.offender_id = a.offender_id ) ) )
order by
	1,
	2,
	3
}

GET_PARTIAL_RECORDS_SELECT_OPERATION{
select
	a.last_name,
	a.second_middle_name,
	COUNT(a.last_name) hits from offenders a where regexp_replace(upper(a.last_name), '[- \,''"]', '', 'g') like '%' || regexp_replace(upper(:p_last_name), '[- \,''"]', '', 'g') || '%'
	and ((coalesce(cast(regexp_replace(upper(:p_first_name), '[- \,''"]', '', 'g') as varchar), '') = '')
	or regexp_replace(upper(a.first_name), '[- \,''"]', '', 'g') like '%' || regexp_replace(upper(:p_first_name), '[- \,''"]', '', 'g') || '%')
	and ((coalesce(cast(regexp_replace(upper(:p_middle_name), '[ -,''"]', '', 'g') as varchar), '') = '')
	or regexp_replace(upper(a.middle_name), '[- \,''"]', '', 'g') like '%' || regexp_replace(upper(:p_middle_name), '[- \,''"]', '', 'g')|| '%')
	and ((coalesce(cast(regexp_replace(upper(:p_second_middle_name), '[- \,''"]', '', 'g') as varchar), '') = '')
	or regexp_replace(upper(a.second_middle_name), '[- \,''"]', '', 'g') like '%' || regexp_replace(upper(:p_second_middle_name), '[- \,''"]', '', 'g') || '%')
	and ( ( coalesce(:lv_from_date::text, '') = ''
	and coalesce(:lv_to_date::text, '') = '' )
	or ( a.birth_date between :lv_from_date and :lv_to_date ) )
	and ( coalesce(:p_birth_date ::text, '') = ''
	or a.birth_date = :p_birth_date )
	and ( coalesce(:p_sex_code ::text, '') = ''
	or a.sex_code = :p_sex_code )
	and ( coalesce(:p_agedate ::text, '') = ''
	or a.birth_date between :lv_from_agedate and :lv_to_agedate )
	and ( coalesce(:p_ethnicity ::text, '') = ''
	or a.race_code = :p_ethnicity )
	group by a.last_name,
	a.second_middle_name
}

GET_PARTIAL_RECORDS_SELECT_OPERATION_ONE{
select
	a.last_name,
	a.second_middle_name,
	COUNT(a.last_name) hits
from
	offenders a
where
	a.last_name_soundex = :p_soundex_lname
	and ( coalesce(:p_first_name::text, '') = ''
		or (regexp_match(a.first_name, '^' || :p_first_name || '', 'cn') is not null
			and (regexp_match(a.first_name, '^' || :p_first_name || '', 'cn'))::text <> '') )
	and ( coalesce(:p_middle_name::text, '') = ''
		or (regexp_match(a.middle_name, '^' || :p_middle_name || '', 'cn') is not null
			and (regexp_match(a.middle_name, '^' || :p_middle_name || '', 'cn'))::text <> '') )
	and ( coalesce(:p_second_middle_name::text, '') = ''
		or (regexp_match(a.second_middle_name, '^' || :p_second_middle_name || '', 'cn') is not null
			and (regexp_match(a.second_middle_name, '^' || :p_second_middle_name || '', 'cn'))::text <> '') )
	and ( ( coalesce(:lv_from_date::text, '') = ''
		and coalesce(:lv_to_date::text, '') = '' )
		or ( a.birth_date between :lv_from_date and :lv_to_date ) )
	and ( coalesce(:p_birth_date::text, '') = ''
		or a.birth_date = :p_birth_date )
	and ( coalesce(:p_sex_code::text, '') = ''
		or a.sex_code = :p_sex_code )
	and ( coalesce(:p_agedate::text, '') = ''
		or a.birth_date between :lv_from_agedate and :lv_to_agedate )
	and ( coalesce(:p_ethnicity::text, '') = ''
		or a.race_code = :p_ethnicity )
group by
	a.last_name, a.second_middle_name
}

GET_COMMUNITY_DETAILS_CUR{
SELECT OB.OFFENDER_BOOK_ID , OB.COMMUNITY_AGY_LOC_ID ,
 (CASE COMMUNITY_ACTIVE_FLAG WHEN 'Y' THEN OMS_SYSTEM_PROFILE('CLIENT', 'B/C_STATUS', 1) WHEN 'N' 
 THEN OMS_SYSTEM_PROFILE('CLIENT', 'B/C_STATUS', 2) ELSE NULL END) COMMUNITY_STATUS ,
 (SELECT FULL_ADDRESS FROM V_ADDRESSES WHERE OWNER_ID = :P_ROOT_OFFENDER_ID AND OWNER_CLASS = 'OFF' AND PRIMARY_FLAG = 'Y') ADDRESS
 FROM OFFENDER_BOOKINGS OB WHERE OB.ROOT_OFFENDER_ID = :P_ROOT_OFFENDER_ID AND OB.BOOKING_END_DATE IS NULL

}

GET_AGY_LOC_DESCRIPTION{
 SELECT DESCRIPTION
        FROM AGENCY_LOCATIONS
       WHERE AGY_LOC_ID = :P_AGY_LOC_ID
}     

GET_INT_LOC_PATH{
  SELECT INTERNAL_LOCATION_CODE, PARENT_INTERNAL_LOCATION_ID
        FROM AGENCY_INTERNAL_LOCATIONS
       WHERE INTERNAL_LOCATION_ID = :P_INTERNAL_LOCATION_ID
}  


GET_PRISON_DETAILS_CUR{
SELECT OB.OFFENDER_BOOK_ID ,OB.AGY_LOC_ID, OB.LIVING_UNIT_ID, OB.AGENCY_IML_ID, 
(CASE ACTIVE_FLAG WHEN 'Y' THEN 'ACTIVE' WHEN 'N' THEN 'INACTIVE' ELSE NULL END) PRISON_STATUS ,
(SELECT FULL_ADDRESS FROM V_ADDRESSES WHERE OWNER_ID = :P_ROOT_OFFENDER_ID AND OWNER_CLASS = 'OFF' AND PRIMARY_FLAG = 'Y')
ADDRESS FROM OFFENDER_BOOKINGS OB WHERE OB.ROOT_OFFENDER_ID = :P_ROOT_OFFENDER_ID AND OB.BOOKING_END_DATE IS NULL
}

GET_PRISON_DETAILS_INACTIVE_CUR{
SELECT OB.OFFENDER_BOOK_ID, OB.AGY_LOC_ID, OB.LIVING_UNIT_ID, OB.AGENCY_IML_ID, 
( CASE ACTIVE_FLAG WHEN 'Y' THEN 'ACTIVE' WHEN 'N' THEN 'INACTIVE' ELSE NULL END ) PRISON_STATUS, 
( SELECT FULL_ADDRESS FROM V_ADDRESSES WHERE OWNER_ID = :P_ROOT_OFFENDER_ID AND OWNER_CLASS = 'OFF' AND PRIMARY_FLAG = 'Y' ) 
ADDRESS FROM OFFENDER_BOOKINGS OB WHERE OB.ROOT_OFFENDER_ID = :P_ROOT_OFFENDER_ID AND OB.BOOKING_END_DATE = 
( SELECT MAX(OB1.BOOKING_END_DATE) FROM OFFENDER_BOOKINGS OB1 WHERE OB1.ROOT_OFFENDER_ID = :P_ROOT_OFFENDER_ID )
}

DELETE_COURSE_ACTIVITY_AREAS_DELETE_OPERATION{
DELETE FROM COURSE_ACTIVITY_AREAS  WHERE crs_acty_id = :crsActyId
}

SELECT_C_AREA{
SELECT description, area_class FROM areas WHERE area_code = :areaCode
}

RESULT_SET_OPERATION_DOB{
SELECT
    last_name,
    first_name,
    middle_name,
    sex_code,
    birth_date,
    offender_id,
    root_offender_id,
    offender_id_display,
    (CASE
        WHEN EXISTS (
            SELECT 1
            FROM offender_bookings b
            WHERE b.offender_id = offenders.offender_id
            AND b.offender_book_id = (
                SELECT MAX(vhb2.offender_book_id)
                FROM offender_bookings vhb2
                WHERE vhb2.root_offender_id = offenders.root_offender_id
            )
        ) THEN 'Y'
        ELSE 'N'
    END) AS working_name_flag,
    gender_code,
    OMS_MISCELLANEOUS_GETDESCCODE('NAME_TYPE', ALIAS_NAME_TYPE) AS ALIAS_NAME_TYPE,
    second_middle_name,
    (SELECT identifier
     FROM offender_identifiers
     WHERE offender_id = offenders.offender_id
     AND identifier_type = 'PIN'
     LIMIT 1
    ) AS pin_identifier
FROM
    offenders
WHERE
    (
        (
            COALESCE(CAST(:lv_from_date AS DATE)::TEXT, '') = ''
            AND COALESCE(CAST(:lv_to_date AS DATE)::TEXT, '') = ''
        )
        OR (offenders.birth_date BETWEEN :lv_from_date AND :lv_to_date)
    )
    AND (
        COALESCE(CAST(:p_birth_date AS DATE)::TEXT, '') = ''
        OR offenders.birth_date = :p_birth_date
    )
    AND (
    COALESCE(CAST(:p_birth_year AS INTEGER), -1) = -1
    OR EXTRACT(YEAR FROM offenders.birth_date) = CAST(:p_birth_year AS INTEGER)
)  
ORDER BY
    last_name,
    first_name,
    middle_name

}

