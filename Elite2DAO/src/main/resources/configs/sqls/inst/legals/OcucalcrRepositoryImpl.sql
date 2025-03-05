LOV_CALCULATION_REASON {
SELECT   code, description
FROM reference_codes
WHERE domain = 'CALC_REASON' AND code != 'DELETE'
AND(( Active_flag = 'Y'))
ORDER BY list_seq, description
}
   
GENERATE_STAFF_ID {
select oms_utils_get_staff_id_u(:username) from dual
}

FETCH_STAFF_NAME {
select oms_utils_get_staff_name (:staffId) from dual
}

GET_ALL_STAFF_DATA {
	SELECT to_char(SM.USER_ID) CODE, (SM.LAST_NAME || ' , ' || SM.FIRST_NAME) DESCRIPTION FROM  STAFF_MEMBERS SM where SM.USER_ID is not null ORDER BY SM.STAFF_ID asc
}