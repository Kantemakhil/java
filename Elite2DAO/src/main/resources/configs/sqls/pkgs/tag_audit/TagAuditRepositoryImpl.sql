ALL_AUDIT_POLICIES_SELECT_OPERATION{
 SELECT object_schema, object_name, policy_name FROM all_audit_policies WHERE policy_name = :p_policy_name AND enabled = 'YES'
}

ALL_AUDIT_POLICIES_SELECT_OPERATION_ONE{
 SELECT object_schema, object_name, policy_name FROM all_audit_policies WHERE policy_name = :p_policy_name AND enabled != 'YES'
}

ALL_AUDIT_POLICIES_SELECT_OPERATION_TWO{
 SELECT object_schema, object_name, policy_name FROM all_audit_policies WHERE policy_name = :p_policy_name
}

LIST_TAB{
 SELECT DBAO.OBJECT_NAME FROM DBA_OBJECTS DBAO WHERE DBAO.OBJECT_TYPE = 'TABLE' AND NOT DBAO.OBJECT_NAME LIKE 'BIN%' AND DBAO.TEMPORARY = 'N' AND DBAO.OWNER = :P_OWNER AND NOT EXISTS (SELECT 'X' FROM ALL_AUDIT_POLICIES AAP WHERE AAP.OBJECT_NAME = DBAO.OBJECT_NAME )
}                