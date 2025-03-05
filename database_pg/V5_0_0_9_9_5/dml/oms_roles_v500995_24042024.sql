INSERT INTO oms_owner.oms_roles (role_id, role_name, role_seq, role_code, create_datetime, create_user_id) 
SELECT nextval('role_id'), 'Significant Incident User', 1, 'SIGNFCT_INCIDENT_ROLE', CURRENT_TIMESTAMP, 'OMS_OWNER'
      WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.oms_roles WHERE role_code = 'SIGNFCT_INCIDENT_ROLE')
        AND NOT EXISTS ( SELECT 1 FROM oms_owner.oms_roles WHERE role_name = 'Significant Incident User');