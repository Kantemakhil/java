GET_STAFF_NAME{
SELECT last_name || ', ' || first_name FROM staff_members WHERE staff_id = :p_staff_id
}

STAFF_CUR{
SELECT STAFF_ID FROM STAFF_MEMBERS WHERE user_id = :userId 
 }
GET_POSITION{
SELECT INSTR ('ABCDEFGHIJKLMNOPQRSTUVWXYZ',SUBSTR (UPPER (:P_USERID), 1, 1)) as POSITION FROM DUAL
}

GET_ILLEGAL_CHARS{
 SELECT RTRIM (LTRIM (TRANSLATE ( UPPER (:P_USERID),
                                    'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_', ' '),' '),' ') ILLEGALCHARS FROM DUAL      
}
GET_PROFILE_VALUE{
SELECT TO_NUMBER (profile_value) FROM system_profiles  WHERE profile_type = 'SYS'  AND profile_code = 'MIN_PASS_LEN'
}
GET_SUB_STRING{
select INSTR ('ABCDEFGHIJKLMNOPQRSTUVWXYZ', SUBSTR (UPPER (:p_password), 1, 1)) from dual
}
GETLTRIM{
select RTRIM (LTRIM ( TRANSLATE (UPPER (:p_password),'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_#$',' ' ),  ' '),' ' ) from dual
}
GET_SYSTEM_MESSAGES_CUR{
SELECT sys_msg.message_type, sys_msg.message_text, sys_msg.action_text, sys_msg.system_remarks_text FROM system_messages 
sys_msg WHERE sys_msg.message_number =:p_message_number AND sys_msg.appln_code = :p_application_system
}
CHECK_SEC_PROF_CUR{
SELECT 'Y' FROM dba_profiles WHERE profile = 'ELITE_PROFILE'
}

PROXY_USER_CUR{
SELECT profile_value FROM system_profiles WHERE profile_code = 'S4RU'
}

GET_OFF_BOOK_CUR{
SELECT o.offender_id_display, o.last_name, o.first_name FROM offenders o, offender_bookings ob WHERE ob.offender_book_id = :p_offender_book_id AND o.offender_id = ob.offender_id
}
STAFF_CUR_USER{
 SELECT staff_id  FROM staff_members  WHERE user_id = :userId
}