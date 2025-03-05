INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGISSID', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGISSID');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGISDTE1', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGISDTE1');



INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGISDTE2', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGISDTE2');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGISDTE3', 'REQ_GRIEVANC',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGISDTE3');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGISTYP', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGISTYP');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGISSRSN', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGISSRSN');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGOICNUM', 'REQ_GRIEVANC',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGOICNUM');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGCLAMNT', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGCLAMNT');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGFLLOC', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGFLLOC');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGCOMNT', 'REQ_GRIEVANC',current_timestamp,'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGCOMNT');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGCURLVL', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGCURLVL');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGCURSTS', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='ISS_RGCURSTS');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTRDTE1', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTRDTE1');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTRDTE2', 'REQ_GRI_TRNS',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTRDTE2');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTRDTE3', 'REQ_GRI_TRNS',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTRDTE3');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTRNACT', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTRNACT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTFIND', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTFIND');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTASSGN', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTASSGN');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTRNLVL', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTRNLVL');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTUID', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTUID');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTSTS', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTSTS');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTPRCOM', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTPRCOM');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTOFRSP', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTOFRSP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGTDYLFT', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='ISS_RGTDYLFT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGSINVID', 'REQ_GRI_STAF', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_STAF' and parameter_name='ISS_RGSINVID');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'ISS_RGSINSNM', 'REQ_GRI_STAF', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'REQ_GRI_STAF' and parameter_name='ISS_RGSINSNM');