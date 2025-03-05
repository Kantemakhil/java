INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRTYPE', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL, 'OMS_OWNER', NULL, 'Court Report Report Type'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRTYPE');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCRT', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL, 'OMS_OWNER', NULL, 'Court Report Court'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRCRT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRDREQ', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Date Requested'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRDREQ');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRDTDU', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Date Due'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRDTDU');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRTRES', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL,NULL, NULL, 'Court Report Team Responsible'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRTRES');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRASOF', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL,NULL, NULL, 'Court Report Assigned Officer'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRASOF');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRSTAT', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL,NULL, NULL, 'Court Report Status'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRSTAT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRSTDT', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Status Date'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRSTDT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRAUCOM', 'LEG_CRT_REPT', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Author’s Comment'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='LEG_CRAUCOM');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRIPTY', 'CRT_INT_PART', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Interested Party Type'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_INT_PART' and parameter_name='LEG_CRIPTY');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRIPDES', 'CRT_INT_PART', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Interested Party Description'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_INT_PART' and parameter_name='LEG_CRIPDES');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRIPCOM', 'CRT_INT_PART', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Interested Party Comment'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_INT_PART' and parameter_name='LEG_CRIPCOM');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCHMA', 'CRT_RPT_CHRG', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Charges Matter'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_RPT_CHRG' and parameter_name='LEG_CRCHMA');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCHDES', 'CRT_RPT_CHRG', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Charges  Description'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_RPT_CHRG' and parameter_name='LEG_CRCHDES');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCHCO', 'CRT_RPT_CHRG', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Charges Code'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_RPT_CHRG' and parameter_name='LEG_CRCHCO');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCHOC', 'CRT_RPT_CHRG', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Charges Outcome'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_RPT_CHRG' and parameter_name='LEG_CRCHOC');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCHAC', 'CRT_RPT_CHRG', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Charges Act'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_RPT_CHRG' and parameter_name='LEG_CRCHAC');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRRPDSP', 'CRT_REP_PROP', current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'Court Report Proposal Disposal'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_REP_PROP' and parameter_name='LEG_CRRPDSP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRRPNS', 'CRT_REP_PROP', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Proposal Not Suitable'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_REP_PROP' and parameter_name='LEG_CRRPNS');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRRPNSR', 'CRT_REP_PROP', current_timestamp, 'OMS_OWNER',NULL,NULL, NULL, 'Court Report Proposal Not Suitable Reason'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_REP_PROP' and parameter_name='LEG_CRRPNSR');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRRPCOM', 'CRT_REP_PROP', current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'Court Report Proposal Comment'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_REP_PROP' and parameter_name='LEG_CRRPCOM');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCDACT', 'CRT_RPT_COND', current_timestamp, 'OMS_OWNER',NULL, NULL, NULL, 'Court Report Condition Details Activity'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_RPT_COND' and parameter_name='LEG_CRCDACT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CRCDCOM', 'CRT_RPT_COND', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Report Condition Details Comment'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_RPT_COND' and parameter_name='LEG_CRCDCOM');



INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAONO', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Number '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAONO');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOOD', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Ordered Date'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOOD');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOMAT', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Matter Number'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOMAT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOCRT', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOCRT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOTYP', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Type '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOTYP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOCTYP', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Commence Type '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOCTYP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOREL', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Related To '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOREL');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOCOMD', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Commence Date '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOCOMD');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAODUR', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Duration '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAODUR');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOEXP', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Duration '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOEXP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAOSTAT', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Status '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='LEG_CAOSTAT');


INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACPROC', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Proceeding'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACPROC');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACSDAT', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Start Date'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACSDAT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACHBOD', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Hearing Body'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACHBOD');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CAPURTO', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Pursuant To'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_CAPURTO');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACRESP', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Team Responsibility'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACRESP');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACSRES', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Staff Responsible '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACSRES');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACCOMM', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Comment'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACCOMM');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACSTAT', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Status'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACSTAT');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_CARECDOC', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Recommendation Documents'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_CARECDOC');

INSERT INTO iwp_bookmark_out_parameters
(parameter_name, bookmark_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, parameter_desc)
SELECT 'LEG_ACODATE', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'Court Actions Outcome Date '
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_out_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='LEG_ACODATE');
