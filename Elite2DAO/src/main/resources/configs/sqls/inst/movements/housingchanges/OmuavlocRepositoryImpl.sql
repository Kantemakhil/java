
OMUAVLOC_LIVUNIT_FIND_V_LIV_UNIT {
select
	LIVING_UNIT_ID ,
	AGY_LOC_ID ,
	CAPACITY ,
	LEVEL_1_TYPE ,
	LEVEL_1_DESC ,
	LEVEL_1_CODE ,
	LEVEL_1_LIST_SEQ ,
	LEVEL_2_TYPE ,
	LEVEL_2_DESC ,
	LEVEL_2_CODE ,
	LEVEL_2_LIST_SEQ ,
	LEVEL_3_TYPE ,
	LEVEL_3_DESC ,
	LEVEL_3_CODE ,
	LEVEL_3_LIST_SEQ ,
	LEVEL_4_TYPE ,
	LEVEL_4_DESC ,
	LEVEL_4_CODE ,
	LEVEL_4_LIST_SEQ ,
	DESCRIPTION ,
	ACTIVE_FLAG
from
	V_LIV_UNIT
where
	(v_liv_unit.agy_loc_id = :toAgyLocId
		or coalesce(:toAgyLocId ::text,'') = '')
	and v_liv_unit.agy_loc_id in (
	select
		ca.agy_loc_id
	from
		caseload_agency_locations ca
	where
		ca.caseload_id = :caseloadId)
	and v_liv_unit.active_flag = 'Y'
order by
	coalesce (LEVEL_1_LIST_SEQ,
	9999),
	coalesce(LEVEL_1_DESC,
	'Z'),
	coalesce(LEVEL_2_LIST_SEQ,
	9999),
	coalesce(LEVEL_2_DESC,
	'Z'),
	coalesce(LEVEL_3_LIST_SEQ,
	9999),
	coalesce(LEVEL_3_DESC,
	'Z'),
	coalesce(LEVEL_4_LIST_SEQ,
	9999),
	coalesce(LEVEL_4_DESC,
	'Z')
}
