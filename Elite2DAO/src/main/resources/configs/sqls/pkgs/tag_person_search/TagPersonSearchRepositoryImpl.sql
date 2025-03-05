INSERT_PERSON_PROFILE_TYPES{
insert into person_profiles ( person_profile_id, person_id, profile_type, display_seq , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select NEXTVAL('person_profile_id'), :p_person_id, pt.profile_type, pt.list_seq, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP from profile_types pt where profile_category = 'VP' and active_flag = 'Y' and expiry_date is null and not exists ( select 1 from person_profiles a where a.person_id = :p_person_id and a.profile_type = pt.profile_type )
}

P_RESULT_SET{
select
	last_name,
	second_middle_name,
	COUNT(*) hits
from
	persons
where
	soundex(last_name) = soundex(:p_last_name)
	and ( (regexp_match(first_name, '^' || :p_first_name || '' , 'n') is not null
		and (regexp_match(first_name, '^' || :p_first_name || '' , 'n'))::text <> '')
		or coalesce(:p_first_name::text, '') = '' )
	and ( (regexp_match(middle_name, '^' || :p_middle_name || '' , 'n') is not null
		and (regexp_match(middle_name, '^' || :p_middle_name || '' , 'n'))::text <> '')
		or coalesce(:p_middle_name::text, '') = '' )
	and ( (regexp_match(second_middle_name, '^' || :p_second_middle_name || '' , 'n') is not null
		and (regexp_match(second_middle_name, '^' || :p_second_middle_name || '' , 'n'))::text <> '')
		or coalesce(:p_second_middle_name::text, '') = '' )
	and ( sex = :p_sex
		or coalesce(:p_sex::text, '') = '' )
	and ( birthdate = :p_birth_date
		or coalesce(:p_birth_date::text, '') = '' )
	and ( birthdate between :v_from_date and :v_to_date
		or ( coalesce(:v_from_date::text, '') = ''
			and coalesce(:v_to_date::text, '') = '' ) )
group by
	last_name,  second_middle_name
}

P_RESULT_SET_ONE{
select
	last_name,
	second_middle_name,
	COUNT(*) hits
from
	persons
where
	(last_name ilike '%' || :p_last_name || '%')
	and ((first_name ilike '%' || :p_first_name || '%')
		or (coalesce(:p_first_name::text, '') = ''))
	and ((middle_name ilike '%' || :p_middle_name || '%')
		or (coalesce(:p_middle_name::text, '') = ''))
	and ((second_middle_name ilike '%' || :p_second_middle_name || '%')
		or (coalesce(:p_second_middle_name::text, '') = ''))
	and ( sex = :p_sex
		or coalesce(:p_sex::text, '') = '' )
	and ( birthdate = :p_birth_date
		or coalesce(:p_birth_date::text, '') = '' )
	and ( birthdate between :v_from_date and :v_to_date
		or ( coalesce(:v_from_date::text, '') = ''
			and coalesce(:v_to_date::text, '') = '' ) )
group by
	last_name,
	second_middle_name
}

P_RESULT_SET_ONE_FP{
select
	last_name,
	first_name,
	middle_name,
	sex,
	birthdate,
	person_id,
	second_middle_name,
	gender,
	(select description  from  reference_codes rc where domain='GENDER' and code=gender) as SEX_DESCRIPTION
from
	persons
where
	last_name = :p_last_name
	and ( (regexp_match(first_name, '^' || :p_first_name || '' , 'n') is not null
		and (regexp_match(first_name, '^' || :p_first_name || '' , 'n'))::text <> '')
		or coalesce(:p_first_name::text, '') = '' )
	and ( (regexp_match(middle_name, '^' || :p_middle_name || '' , 'n') is not null
		and (regexp_match(middle_name, '^' || :p_middle_name || '' , 'n'))::text <> '')
		or coalesce(:p_middle_name::text, '') = '' )
	and ( (regexp_match(second_middle_name, '^' || :p_second_middle_name || '' , 'n') is not null
		and (regexp_match(second_middle_name, '^' || :p_second_middle_name || '' , 'n'))::text <> '')
		or coalesce(:p_second_middle_name::text, '') = '' )
	and ( sex = :p_sex
		or coalesce(:p_sex::text, '') = '' )
	and ( birthdate = :p_birth_date
		or coalesce(:p_birth_date::text, '') = '' )
	and ( birthdate between :v_from_date and :v_to_date
		or ( coalesce(:v_from_date::text, '') = ''
			and coalesce(:v_to_date::text, '') = '' ) )
order by
	1,
	2,
	3	
}

P_RESULT_SET_TWO{
SELECT last_name, first_name, middle_name, sex, birthdate, person_id,second_middle_name, gender, (select description  from  reference_codes rc where domain='GENDER' and code=gender) as SEX_DESCRIPTION FROM persons WHERE person_id = :p_person_id ORDER BY 1, 2, 3
}

P_RESULT_SET_THREE{
SELECT a.last_name, a.first_name, a.middle_name, a.sex, a.birthdate, a.person_id,a.second_middle_name, a.gender, (select description  from  reference_codes rc where domain='GENDER' and code=gender) as SEX_DESCRIPTION FROM persons a, person_identifiers b WHERE a.person_id = b.person_id AND b.identifier_type = :p_identifier_type AND b.identifier LIKE '%' || :p_identifier_value || '%' ORDER BY 1, 2, 3
}

P_RESULT_SET_FOUR{
SELECT a.last_name, a.first_name, a.middle_name, a.sex, a.birthdate, a.person_id,a.second_middle_name, a.gender,(select description  from  reference_codes rc where domain='GENDER' and code=gender) as SEX_DESCRIPTION FROM persons a, person_identifiers b WHERE a.person_id = b.person_id AND b.identifier_type = :p_identifier_type AND b.identifier LIKE '%' || :p_identifier_value || '%' ORDER BY 1, 2, 3
}

GET_NEXT_ID_SEQ{
select
	coalesce (MAX (ID_SEQ) + 1,
	0)
from
	PERSON_IDENTIFIERS
where
	PERSON_ID = :P_PERSON_ID
}
GET_NEXT_EMP_SEQ{
select
	coalesce (MAX (EMPLOYMENT_SEQ) + 1,
	0) EMPLOYMENT_SEQ
from
	PERSON_EMPLOYMENTS
where
	PERSON_ID = :P_PERSON_ID
}