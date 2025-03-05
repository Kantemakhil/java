
GET_REFERENCECODE{
SELECT description FROM REFERENCE_CODES WHERE domain =:p_Domain AND code =:p_RefCode
}
P_REFCURSOR{
select
	vla.description description,
	vla.column_name column_name,
	vla.old_value old_value,
	vla.new_value new_value,
	case
		when vla.deactivate_reason_code = null then null
		else Oms_Miscellaneous_GetDescCode('LIV_UN_RSN',
		vla.deactivate_reason_code)
	end deactivate_reason_code,
	vla.action_code action_code,
	vla.amend_date amend_date,
	vla.amend_user_id amend_user_id
from
	v_agy_int_loc_amendments vla
where
	vla.agy_loc_id = :p_agy_loc_id
	and (coalesce(:p_level_1_code ::text, '') = ''
		or vla.level_1_code = :p_level_1_code)
	and (coalesce(:p_level_2_code ::text, '') = ''
		or vla.level_2_code = :p_level_2_code)
	and (coalesce(:p_level_3_code ::text, '') = ''
		or vla.level_3_code = :p_level_3_code)
	and(coalesce(:p_level_4_code ::text, '') = ''
		or vla.level_4_code = :p_level_4_code)
	and vla.amend_date >= TO_DATE(:p_amend_date_from ::text, 'DD/MM/YYYY')
	and vla.amend_date <TO_DATE(:p_amend_date_to ::text, 'DD/MM/YYYY') + interval '1 DAY'
}

GETTING_CASELOAD{
SELECT CASELOAD_ID FROM CASELOADS WHERE DESCRIPTION=:DESCRIPTION
}