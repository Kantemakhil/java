
GET_REFERENCE_CODES_BY_DOMAIN {
    select
	DESCRIPTION,
	TRIM(CODE) as CODE,
	PARENT_CODE,
	PARENT_DOMAIN,
	domain,
	LIST_SEQ ,
	ACTIVE_FLAG,
	case when (
	select
		count(*)
	from
		ROLE_INACCESSIBLE_REF_CODES
	where
		ROLE_INACCESSIBLE_REF_CODES.module_name = :moduleName
		and ROLE_INACCESSIBLE_REF_CODES.role_id in (
		select
			role_id
		from
			staff_member_roles smr
		where
			staff_id = (
			select
				staff_id
			from
				staff_members sm
			where
				user_id = :userId))
		and ROLE_INACCESSIBLE_REF_CODES.DOMAIN = :domain and ROLE_INACCESSIBLE_REF_CODES.code = REFERENCE_CODES.code) > 0 then 'N' else 'Y' end as domain_access
from
	REFERENCE_CODES
where
	domain = :domain #dynamicwhere
order by
	LIST_SEQ,
	CODE asc
}	

