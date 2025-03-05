INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_TYPE', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Type'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_TYPE');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_REAS', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Reason'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_REAS');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_FACIL', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Facility'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_FACIL');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_REQBY', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Requested By'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_REQBY');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_AUTH', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Authorized By'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_AUTH');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_AUTHNAM', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Name'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_AUTHNAM');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_EFFDAT', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Effective Date'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_EFFDAT');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_EFFTIM', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Effective Time'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_EFFTIM');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_DURTYP', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Duration Type'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_DURTYP');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_DUR', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Duration'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_DUR');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_REVDAT', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Review Date'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_REVDAT');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_EXPDAT', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Expiry Date'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_EXPDAT');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_EXPTIM', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Expiry Time'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_EXPTIM');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_COM', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Comments'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_COM');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_RELBY', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Released By'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_RELBY');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_RELBYNAM', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Released By Name'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_RELBYNAM');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_RELDAT', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Released Date'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_RELDAT');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_RELTIM', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Released Time'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_RELTIM');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_DAYS', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Days'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_DAYS');

INSERT INTO oms_owner.iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CIP_HOURS', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'CIP Hours'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='CIP_HOURS');
