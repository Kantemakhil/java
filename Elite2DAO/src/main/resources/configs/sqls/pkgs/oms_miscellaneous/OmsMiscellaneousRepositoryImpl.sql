OMS_MISC_GET_DESC_CODE{
SELECT description FROM reference_codes WHERE domain = 'AREA_CLASS' AND code = :areaClass
}
GET_PROFILE_VALUES{
	SELECT  profile_value,
	         profile_value_2
  	  FROM    SYSTEM_PROFILES
  	  WHERE	  profile_type = :p_profile_type
  	  AND	  profile_code = :p_profile_code
}


GET_STAFF_NAME{
SELECT last_name || ', ' || first_name FROM staff_members WHERE staff_id = :p_staff_id
}

GET_DESC_CODE_TWO_PARAMS{
SELECT description FROM reference_codes WHERE domain = :p_domain AND code = :p_refcode 
}
SELECT_PROFILE_VALUE{
	SELECT 	PROFILE_VALUE, PROFILE_VALUE_2
  	FROM	SYSTEM_PROFILES
  	WHERE	PROFILE_TYPE = :p_profile_type
  	AND	PROFILE_CODE = :p_profile_code;
 }