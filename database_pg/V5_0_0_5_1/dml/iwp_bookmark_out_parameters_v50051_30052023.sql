INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSOFNM', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSOFNM'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSOFNM');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSODTE', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSODTE'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSODTE');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSCMTN', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSCMTN'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSCMTN');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSCOURT', 'LEG_CUST',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSCOURT'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSCOURT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSOTYP', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp,  'OMS_OWNER', NULL, 'LEG_CUSOTYP'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSOTYP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSCTYP', 'LEG_CUST',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSCTYP'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSCTYP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSLNKT', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSLNKT'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSLNKT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSCDTE', 'LEG_CUST',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSCDTE'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSCDTE');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSTTYL', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSTTYL'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSTTYL');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSOSTS', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSOSTS'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSOSTS');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSHEDT', 'LEG_CUST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSHEDT'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='LEG_CUSHEDT');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSMTNM', 'LEG_CUS_CHRG',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSMTNM'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUS_CHRG' and parameter_name='LEG_CUSMTNM');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSCHRG', 'LEG_CUS_CHRG',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSCHRG'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUS_CHRG' and parameter_name='LEG_CUSCHRG');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSOTCM', 'LEG_CUS_CHRG',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSOTCM'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CUS_CHRG' and parameter_name='LEG_CUSOTCM');





INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSPLEA', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSPLEA'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='LEG_CUSPLEA');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSINDT', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSINDT'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='LEG_CUSINDT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSRAN', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSRAN'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='LEG_CUSRAN');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSPAR', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSPAR'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='LEG_CUSPAR');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CHARGEID', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'CHARGEID'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='CHARGEID');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'OFFENDERBOOKID', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'OFFENDERBOOKID'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='OFFENDERBOOKID');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSINCD', 'LEG_CHRG_IND',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSINCD'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_IND' and parameter_name='LEG_CUSINCD');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'CODE', 'LEG_CHRG_CAT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'CODE'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_CAT' and parameter_name='CODE');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSCAT', 'LEG_CHRG_CAT',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSCAT'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_CAT' and parameter_name='LEG_CUSCAT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CUSSEV', 'LEG_CHRG_CAT',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'LEG_CUSSEV'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_CAT' and parameter_name='LEG_CUSSEV');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'OFFENCE_ID', 'LEG_CHRG_CAT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, 'OFFENCE_ID'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CHRG_CAT' and parameter_name='OFFENCE_ID');



