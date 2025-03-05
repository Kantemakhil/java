
OSISTGKW_STGSEARCHV1_FIND_STG_SEARCH_V1 {
 	SELECT STG_ID, SEQ, CODE, DESCRIPTION, SOURCE FROM STG_SEARCH_V1
}
OSISTGKW_STGSEARCHV1_INSERT_STG_SEARCH_V1 {
	INSERT INTO STG_SEARCH_V1(STG_ID,SEQ,CODE,DESCRIPTION,SOURCE) VALUES(:stgId,:seq,:code,:description,:source)
}

OSISTGKW_STGSEARCHV1_UPDATE_STG_SEARCH_V1 {
	UPDATE STG_SEARCH_V1 set 
}

OSISTGKW_STGSEARCHV1_DELETE_STG_SEARCH_V1 { 
	DELETE FROM STG_SEARCH_V1
}

OSISTGKW_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OSISTGKW_GET_STG_GROUP_DESCRIPTION_ {
	SELECT DESCRIPTION FROM SECURITY_THREAT_GROUPS WHERE STG_ID = :STGID AND STG_LEVEL = 'SET' AND ACTIVE_FLAG = 'Y'
}

OSISTGKW_GET_STG_GROUP_DESCRIPTION_ {
	select
	case
		when coalesce(STG2.DESCRIPTION::text, '') = '' then null
		else STG2.DESCRIPTION || '-'
	end || STG1.DESCRIPTION
from
	SECURITY_THREAT_GROUPS STG1,
	SECURITY_THREAT_GROUPS STG2
where
	STG1.STG_ID = :STGID
	and STG1.STG_LEVEL = 'SET'
	and STG1.ACTIVE_FLAG = 'Y'
	and STG1.PARENT_STG_ID = STG2.STG_ID
union
 select
	DESCRIPTION
from
	SECURITY_THREAT_GROUPS
where
	STG_ID = :STGID
	and STG_LEVEL = 'GANG'
	and ACTIVE_FLAG = 'Y'

}

OSISTGKW_GET_STG_GROUP_DESCRIPTION_ {
	select
	case
		when coalesce(STG3.DESCRIPTION::text, '') = '' then null
		else STG3.DESCRIPTION || '-'
	end ||
	case
		when coalesce(STG2.DESCRIPTION::text, '') = '' then null
		else STG2.DESCRIPTION || '-'
	end || STG1.DESCRIPTION
from
	SECURITY_THREAT_GROUPS STG1,
	SECURITY_THREAT_GROUPS STG2,
	SECURITY_THREAT_GROUPS STG3
where
	STG1.STG_ID = :STGID
	and STG1.STG_LEVEL = 'SET'
	and STG1.ACTIVE_FLAG = 'Y'
	and STG1.PARENT_STG_ID = STG2.STG_ID
	and STG2.PARENT_STG_ID = STG3.STG_ID
union
 select
	case
		when coalesce(STG2.DESCRIPTION::text, '') = '' then null
		else STG2.DESCRIPTION || '-'
	end || STG1.DESCRIPTION
from
	SECURITY_THREAT_GROUPS STG1,
	SECURITY_THREAT_GROUPS STG2
where
	STG1.STG_ID = :STGID
	and STG1.STG_LEVEL = 'GANG'
	and STG1.ACTIVE_FLAG = 'Y'
	and STG1.PARENT_STG_ID = STG2.STG_ID
union
 select
	DESCRIPTION
from
	SECURITY_THREAT_GROUPS
where
	STG_ID = :STGID
	and STG_LEVEL = 'NATION'
	and ACTIVE_FLAG = 'Y';
}

OSISTGKW_V_PROF_VALUE {
   select
	oms_miscellaneous_get_profile_value('CLIENT',
	'STG_LOV_LVL')
from
	dual
}

OSISTGKW_V_PROF_VALUE_DESCRIPTION_ONE {
select
	description
from
	security_threat_groups
where
	STG_ID = :STGID
	and stg_level = 'SET'
	and active_flag = 'Y'
}
OSISTGKW_V_PROF_VALUE_DESCRIPTION_TWO {
select
	case
		when coalesce(stg2.description::text, '') = '' then null
		else stg2.description || '-'
	end || stg1.description
from
	security_threat_groups stg1,
	security_threat_groups stg2
where
	stg1.stg_id = :STGID
	and stg1.stg_level = 'SET'
	and stg1.active_flag = 'Y'
	and stg1.parent_stg_id = stg2.stg_id
union
    select
	description
from
	security_threat_groups
where
	stg_id = :STGID
	and stg_level = 'GANG'
	and active_flag = 'Y';
}

OSISTGKW_V_PROF_VALUE_DESCRIPTION_THREE {
select
	case
		when coalesce(stg3.description::text, '') = '' then null
		else stg3.description || '-'
	end ||
	case
		when coalesce(stg2.description::text, '') = '' then null
		else stg2.description || '-'
	end || stg1.description
from
	security_threat_groups stg1,
	security_threat_groups stg2,
	security_threat_groups stg3
where
	stg1.stg_id = :STGID
	and stg1.stg_level = 'SET'
	and stg1.active_flag = 'Y'
	and stg1.parent_stg_id = stg2.stg_id
	and stg2.parent_stg_id = stg3.stg_id
union

select
	case
		when coalesce(stg2.description::text, '') = '' then null
		else stg2.description || '-'
	end || stg1.description
from
	security_threat_groups stg1,
	security_threat_groups stg2
where
	stg1.stg_id = :STGID
	and stg1.stg_level = 'GANG'
	and stg1.active_flag = 'Y'
	and stg1.parent_stg_id = stg2.stg_id
union
  select
	description
from
	security_threat_groups
where
	stg_id = :STGID
	and stg_level = 'NATION'
	and active_flag = 'Y'
}