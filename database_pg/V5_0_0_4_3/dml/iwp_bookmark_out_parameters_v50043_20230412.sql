INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCDNUM',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCDNUM');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCDTE1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCDTE1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCDTE2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCDTE2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCDTE3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCDTE3');

INSERT INTO iwp_bookmark_out_parameters
(bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCRPDT1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCRPDT1');

INSERT INTO iwp_bookmark_out_parameters
(bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCRPDT2',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCRPDT2');

INSERT INTO iwp_bookmark_out_parameters
(bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCRPDT3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCRPDT3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCTME', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCTME');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCTYP',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCTYP');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_INCLOCA', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCLOCA');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_OICNUM', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_OICNUM');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  'OFF_IN_CSTDY', 'OIC_INCDETL',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCDETL');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CSTDY', 'OIC_RPTUSNMN', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_RPTUSNMN');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CHARG', 'OIC_INCCHRG', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='OIC_INCCHRG');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CHARG', 'OIC_INCCHDS',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='OIC_INCCHDS');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CHARG', 'OIC_INCCHTYP', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='OIC_INCCHTYP');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CHARG', 'OIC_INCCHCT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='OIC_INCCHCT');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CHARG', 'OIC_INCCHEV',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='OIC_INCCHEV');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_CHARG', 'OIC_INCDISPO', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='OIC_INCDISPO');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  'OFF_SCH_HEAR', 'OIC_INCHRDT1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHRDT1');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHRDT2',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHRDT2');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHRDT3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHRDT3');



INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHRDT4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHRDT4');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTME1',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTME1');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTME2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTME2');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTME3',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTME3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTME4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTME4');



INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHLOC1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHLOC1');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHLOC2',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHLOC2');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHLOC3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHLOC3');



INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  'OFF_SCH_HEAR', 'OIC_INCHLOC4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHLOC4');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTYP1',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTYP1');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTYP2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTYP2');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTYP3',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTYP3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHTYP4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHTYP4');



INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHCMT1',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHCMT1');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHCMT2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHCMT2');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  'OFF_SCH_HEAR', 'OIC_INCHCMT3',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHCMT3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SCH_HEAR', 'OIC_INCHCMT4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_INCHCMT4');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTDT1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDT1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  'OFF_SH_NOTIF', 'OIC_INCNTDT2',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDT2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTDT3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDT3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTDT4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDT4');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTTM1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTTM1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTTM2',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTTM2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTTM3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTTM3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTTM4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTTM4');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTDB1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDB1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTDB2',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDB2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTDB3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDB3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNTDB4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNTDB4');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNCMT1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNCMT1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNCMT2',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNCMT2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNCMT3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNCMT3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_SH_NOTIF', 'OIC_INCNCMT4', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_INCNCMT4');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_INVE', 'OIC_INCDTASN',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_INVE' and parameter_name='OIC_INCDTASN');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_INVE', 'OIC_INCINVST', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_INVE' and parameter_name='OIC_INCINVST');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_INVE', 'OIC_INCINCMT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_INVE' and parameter_name='OIC_INCINCMT');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVTY1',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVTY1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVTY2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVTY2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVTY3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVTY3');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVTY4',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVTY4');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVTY5', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVTY5');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVTY6', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVTY6');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVTY7', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVTY7');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  'OFF_CUS_EVID', 'OIC_INCEVDT1',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDT1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDT2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDT2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDT3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDT3');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDT4',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDT4');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDT5', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDT5');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDT6', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDT6');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDT7', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDT7');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDL1',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDL1');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDL2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDL2');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDL3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDL3');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDL4',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDL4');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDL5', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDL5');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDL6', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDL6');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_CUS_EVID', 'OIC_INCEVDL7', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='OIC_INCEVDL7');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H&P', 'OIC_INCHPTYP', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_INCHPTYP');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  'OFF_IN_H&P', 'OIC_INCHPDTE', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_INCHPDTE');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H&P', 'OIC_INCHTME',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_INCHTME');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H&P', 'OIC_INCHLOC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_INCHLOC');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H&P', 'OIC_INCHHEAB', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_INCHHEAB');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H&P', 'OIC_INCHOTHR', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_INCHOTHR');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H&P', 'OIC_INCHCMNT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_INCHCMNT');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCROCHG', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCROCHG');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCROCTY', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCROCTY');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCRODSC', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCRODSC');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCRRCHG', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCRRCHG');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCRRTYP', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCRRTYP');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCRRODS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCRRODS');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCRPLEA', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCRPLEA');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_H_RES', 'OIC_INCRFNDG', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_INCRFNDG');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCPDMLN', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCPDMLN');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDMTP', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDMTP');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDMMO', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDMMO');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDMDY', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDMDY');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDRES', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDRES');


INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDEDT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDEDT');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDCLN', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDCLN');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDCMT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDCMT');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDOIN', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDOIN');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDSTS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDSTS');

INSERT INTO iwp_bookmark_out_parameters
( bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFF_IN_C_PEN', 'OIC_INCCDSDT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OIC_INCCDSDT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STDTTKN1', 'CMG_ST_SAMPL',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STDTTKN1');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STDTTKN2', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STDTTKN2');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STDTTKN3', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STDTTKN3');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STSTYPE', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STSTYPE');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STSRSN', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STSRSN');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STWIT', 'CMG_ST_SAMPL',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STWIT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STTKNBY', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STTKNBY');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STDTTST1', 'CMG_ST_SAMPL',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STDTTST1');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STDTTST2', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STDTTST2');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STDTTST3', 'CMG_ST_SAMPL',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STDTTST3');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STEXAGY', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STEXAGY');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STTSTBY', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STTSTBY');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STTSPOS', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STTSPOS');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STCOMNT', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='CMG_STCOMNT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STSUBS', 'CMG_ST_TESTE', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_TESTE' and parameter_name='CMG_STSUBS');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STRSLT', 'CMG_ST_TESTE', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_TESTE' and parameter_name='CMG_STRSLT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STDISPO', 'CMG_ST_TESTE', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_TESTE' and parameter_name='CMG_STDISPO');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'CMG_STTSCOM', 'CMG_ST_TESTE', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CMG_ST_TESTE' and parameter_name='CMG_STTSCOM');