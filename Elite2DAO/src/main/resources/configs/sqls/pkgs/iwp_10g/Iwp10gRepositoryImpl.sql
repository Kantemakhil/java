
GET_TEMP_ROLES_ONE{
SELECT qq.template_id, qq.role_code, q.role_name FROM oms_roles q, iwp_template_roles qq WHERE q.role_code = qq.role_code AND qq.template_id = :TEMPID AND qq.role_code = :TEMP_ROLE_CODE
}   
GET_TEMP_ROLES_SECOND{
 SELECT qq.template_id, qq.role_code, q.role_name FROM oms_roles q, iwp_template_roles qq WHERE q.role_code = qq.role_code AND qq.template_id = :TEMPID
}               
               