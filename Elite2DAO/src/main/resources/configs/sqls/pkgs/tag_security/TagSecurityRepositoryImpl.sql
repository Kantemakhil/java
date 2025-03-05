GET_GROUP_PRIVILEGE{
--SELECT MIN(module_privileges.access_privilege)  FROM module_privileges, staff_member_roles, staff_members WHERE staff_members.user_id = USER AND staff_member_roles.staff_id = staff_members.staff_id AND module_privileges.role_id = staff_member_roles.role_id AND module_privileges.module_name =:p_module_name
select
	MIN(module_privileges.access_privilege)
from
	module_privileges,
	staff_member_roles,
	staff_members
where
	staff_members.user_id = :user_name
	and staff_member_roles.staff_id = staff_members.staff_id
	and module_privileges.role_id = staff_member_roles.role_id
	and module_privileges.module_name =:p_module_name
}

GET_CASE_LOAD_ID{
select s.working_caseload_id from staff_members s where s.user_id = :userName
}

CHECK_PRIVILEGE_EXISTS{
 select count(*) from staff_members sm, staff_member_roles smr, oms_roles om where sm.staff_id = smr.staff_id and sm.user_id = :user_name and smr.role_id = om.role_id and om.role_code = :p_role_code
}

TAG_SECURITY_CHECK_PRIVILEGE {
 select case when (SELECT count(*)
        FROM staff_members sm,
             staff_member_roles smr,
             oms_roles om
       WHERE sm.staff_id = smr.staff_id
         AND sm.user_id = :user_name
         AND smr.role_id = om.role_id
         AND om.role_name = :p_role_name) >0 then 'Y' else 'N' end flag
         
         }