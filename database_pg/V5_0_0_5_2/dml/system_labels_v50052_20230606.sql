INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCIPHIST', 'ociphist.comment', 'Comment', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCIPHIST' AND msg_key =  'ociphist.comment'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.title', 'Pay Details', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.title'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.fromdate', 'From Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.fromdate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.todate', 'To Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.todate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.pid', 'PID #', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.pid'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.unconfirmedpaysummary', 'Unconfirmed Pay Summary', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.unconfirmedpaysummary'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.back', 'Back', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.back'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.date', 'Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.date'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.lastname', 'Last Name', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.lastname'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.firstname', 'First Name', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.firstname'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.code', 'Code', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.code'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.noofunits', 'No.of Units', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.noofunits'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.rate', 'Rate', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.rate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.amount', 'Amount', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.amount'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCUPDETA', 'ocupdeta.comment', 'Comment', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCUPDETA' AND msg_key =  'ocupdeta.comment'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OUMSYSET', 'oumsyset.numberinuse', 'This phone number format is already in use', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OUMSYSET' AND msg_key =  'oumsyset.numberinuse'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.routename', 'Route Name', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.routename'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.title', 'Maintain Routes', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.title'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.stops', '#Stops', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.stops'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.startlocation', 'Start', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.startlocation'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.stoplocation', 'Stop', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.stoplocation'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.active', 'Act?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.active'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.ExpDate', 'Expiry Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.ExpDate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.leg', 'Leg', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.leg'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.seq', 'Seq', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.seq'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.aglocid', 'Agency Location', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.aglocid'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.countflag', 'Don''t exceed count', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.countflag'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.intakelocflag', 'Intake Facility', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.intakelocflag'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.overnightcountflag', 'Overnight Stop', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.overnightcountflag'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.descrip', 'Description', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.descrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.deletevalidation', 'Cannot delete master record when matching detail records exist', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.deletevalidation'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.duplegandseq', 'Duplicate Entry of Leg and Seq', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.duplegandseq'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMROUTE', 'oimroute.minvaluevaidation', 'Leg Value Should Range from 1 to 99999', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMROUTE' AND msg_key =  'oimroute.minvaluevaidation'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSGLEN', 'oimsglen.maintainsegmentlengths', 'Maintain Segment Lengths', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSGLEN' AND msg_key =  'oimsglen.maintainsegmentlengths'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSGLEN', 'oimsglen.fromAgyLocId', 'From Location', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSGLEN' AND msg_key =  'oimsglen.fromAgyLocId'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSGLEN', 'oimsglen.fromDescription', 'From Description', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSGLEN' AND msg_key =  'oimsglen.fromDescription'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSGLEN', 'oimsglen.toAgyLocId', 'To Location', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSGLEN' AND msg_key =  'oimsglen.toAgyLocId'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSGLEN', 'oimsglen.toDescription', 'To Description', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSGLEN' AND msg_key =  'oimsglen.toDescription'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSGLEN', 'oimsglen.segmentLength', 'Segment Length', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSGLEN' AND msg_key =  'oimsglen.segmentLength'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.fixedassetsdetails', 'Fixed Assets Details', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.fixedassetsdetails'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.assets', 'Assets', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.assets'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.assetdetails', 'Asset Details', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.assetdetails'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.vehicledetails', 'Vehicle Details', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.vehicledetails'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.vehicleid#', 'Vehicle ID #', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.vehicleid#'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.plate#', 'Plate #', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.plate#'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.licreq', 'LIC Req', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.licreq'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.fueltype', 'Fuel Type', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.fueltype'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.optimalcapacity', 'Optimal Capacity', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.optimalcapacity'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.physicalcapacity', 'Physical Capacity', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.physicalcapacity'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.year', 'Year', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.year'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.color', 'Color', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.color'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.class', 'Class', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.class'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.type', 'Type', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.type'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.make', 'Make', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.make'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.id#', 'ID#', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.id#'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.model#', 'Model #', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.model#'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.status', 'Status', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.status'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.description', 'Description', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.description'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.vehiclealreadyassignedtoascheduledtrip', 'Vehicle already assigned to a scheduled trip. You can not delete this record.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.vehiclealreadyassignedtoascheduledtrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.assetclassmustbeentered', 'Asset class must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.assetclassmustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.assettypemustbeentered', 'Asset type must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.assettypemustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.assetmakemustbeentered', 'Asset make must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.assetmakemustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.assetstatusmustbeentered', 'Asset status must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.assetstatusmustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.vehicleidmustbeentered', 'Vehicle id must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.vehicleidmustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.optimalcapacitymustbeentered', 'Optimal capacity must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.optimalcapacitymustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.physicalcapacitymustbeentered', 'Physical capacity must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.physicalcapacitymustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.optcapacitycannotbegreaterthenphycapacity', 'Optimal capacity of the vehicle cannot be greater then physical capacity.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.optcapacitycannotbegreaterthenphycapacity'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.thisvehicleidalreadyexistspleaseenteruniqueid', 'This Vehicle ID already exists. Please enter unique ID.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.thisvehicleidalreadyexistspleaseenteruniqueid'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.thisdispositionpersoniddoesnotexist', 'This disposition person id does not exist.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.thisdispositionpersoniddoesnotexist'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.thisacquisitionpersoniddoesnotexist', 'This acquisition person id does not exist.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.thisacquisitionpersoniddoesnotexist'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.thisdispositioncorporateiddoesnotexist', 'This disposition corporate id does not exist.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.thisdispositioncorporateiddoesnotexist'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.thisacquisitioncorporationiddoesnotexist', 'This acquisition corporation id does not exist.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.thisacquisitioncorporationiddoesnotexist'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.cannotdeletefixedassetswhiledependentvehiclemileagesexists', 'Cannot delete fixed assets while dependent vehicle mileages exists.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.cannotdeletefixedassetswhiledependentvehiclemileagesexists'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.cannotdeletefixedassetswhiledependentvehiclesexists', 'Cannot delete fixed assets while dependent vehicle mileages exists.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.cannotdeletefixedassetswhiledependentvehiclesexists'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.cannotdeletefixedassetswhiledependentfixedassetstaffsexists', 'Cannot delete fixed assets while dependent fixed asset staffs exists.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.cannotdeletefixedassetswhiledependentfixedassetstaffsexists'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.cannotdeletefixedassetswhiledependentfixedasseteventsexists', 'Cannot delete fixed assets while dependent fixed asset events exists.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.cannotdeletefixedassetswhiledependentfixedasseteventsexists'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.cannotdeletefixedasswhiledependentassetlocationassignmentsexists', 'Cannot delete fixed ass while dependent asset location assignments exists.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.cannotdeletefixedasswhiledependentassetlocationassignmentsexists'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.cannotdeletevehicleswhiledependentvehiclemileagesexists', 'Cannot delete vehicles while dependent vehicle mileages exists.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.cannotdeletevehicleswhiledependentvehiclemileagesexists'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.cannotdeletevehicleswhiledependenttripsexists', 'Cannot delete vehicles while dependent trips exists.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.cannotdeletevehicleswhiledependenttripsexists'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.scheduletrips', 'Schedule Trips', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.scheduletrips'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.trips', 'Trips', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.trips'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.tripcode', 'Trip Code', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.tripcode'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.description', 'Description', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.description'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.triptype', 'Trip Type', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.triptype'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.seq', 'Seq', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.seq'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.act', 'Act?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.act'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.expirydate', 'Expiry Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.expirydate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.id', 'Id', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.id'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.departuredate', 'Departure Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.departuredate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.generateschedule', 'Generate Schedule', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.generateschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.scheduledtrips', 'Scheduled Trips', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.scheduledtrips'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.vehicleassignment', 'Vehicle Assignment', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.vehicleassignment'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.staffassignment', 'Staff Assignment', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.staffassignment'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.departuretime', 'Departure Time', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.departuretime'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.completiondate', 'Completion Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.completiondate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.estcomptime', 'Est Comp Time', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.estcomptime'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.route', 'Route', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.route'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.optcap', 'Opt. Cap.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.optcap'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.physcap', 'Phys. Cap.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.physcap'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.cancel', 'Cancel', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.cancel'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.cancelledby', 'Cancelled By', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.cancelledby'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.canceldate', 'Cancel Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.canceldate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.vehicleid', 'Vehicle ID #', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.vehicleid'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.type', 'Type', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.type'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.make', 'Make', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.make'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.model', 'Model #', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.model'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.staffname', 'Staff Name', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.staffname'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.thistripcodealreadyexist', 'This trip code already exist. Please provide a unique name.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.thistripcodealreadyexist'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.pleasesaveyourchanges', 'Please save your changes.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.pleasesaveyourchanges'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.pleaseactivatethetriptogenerateaschedule', 'Please activate the trip to generate a schedule.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.pleaseactivatethetriptogenerateaschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.pleaseprovidethevalidtripcodetogenerateschedule', 'Please provide the valid trip code to generate schedule.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.pleaseprovidethevalidtripcodetogenerateschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.pleaseprovidethevaliddescriptiontogenerateschedule', 'Please provide the valid description to generate schedule.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.pleaseprovidethevaliddescriptiontogenerateschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.tripdoesnotexistdoyouwanttocreatethistrip', 'Trip does not exist. Do you want to create this trip?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.tripdoesnotexistdoyouwanttocreatethistrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.pleaseenteronlynumericvalues', 'Please enter only numeric values.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.pleaseenteronlynumericvalues'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.doyouwanttocancelthistrip', 'Do you want to cancel this trip?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.doyouwanttocancelthistrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.selectedtriphasastatusof', 'Selected trip has a status of', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.selectedtriphasastatusof'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.doyouwanttocontinuetheroutepatternforthetrip', 'Do you want to continue the route pattern for the Trip?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.doyouwanttocontinuetheroutepatternforthetrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.atripthatoccursafterthetripyouaretryingtocancelha', 'A trip that occurs after the trip you are trying to cancel has already been locked. The option to update the routes of these trips is therefore not available. Do you still want to cancel this trip?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.atripthatoccursafterthetripyouaretryingtocancelha'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.cannotcancelpasttrips', 'Cannot cancel past trips.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.cannotcancelpasttrips'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.oneormorescheduletripsthatyouaretryingtocancelhasoffenderassignedtothem', 'One or more schedule trips that you are trying to cancel has offender assigned to them. Do you still want to cancel this trip?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.oneormorescheduletripsthatyouaretryingtocancelhasoffenderassignedtothem'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.samestaffmemberhasalreadybeenselectedforthescheduledtrip', 'Same STAFF MEMBER has already been selected for the scheduled trip.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.samestaffmemberhasalreadybeenselectedforthescheduledtrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.samevehiclehasalreadybeenselectedforthescheduledtrip', 'Same VEHICLE has already been selected for the scheduled trip.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.samevehiclehasalreadybeenselectedforthescheduledtrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.cannotaddvehiclebecausenoscheduleexistfortheselectedtrip', 'Can not add Vehicle because No schedule exist for the selected Trip.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.cannotaddvehiclebecausenoscheduleexistfortheselectedtrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.theselectedvehiclehasalreadybeenassignedtoacheduledtrip', 'The selected Vehicle has already been assigned to a Scheduled Trip in the same date range.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.theselectedvehiclehasalreadybeenassignedtoacheduledtrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIMSTRIP', 'oimstrip.cannotassignstaffbecausenoscheduleexistfortheselectedtrip', 'Can not assign staff because no schedule exist for the selected trip.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIMSTRIP' AND msg_key =  'oimstrip.cannotassignstaffbecausenoscheduleexistfortheselectedtrip'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.generatescheduledtrips', 'Generate Scheduled Trips', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.generatescheduledtrips'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripschedule', 'Trip Schedule', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripcode', 'Trip Code', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripcode'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.description', 'Description', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.description'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.startdate', 'Start Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.startdate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.enddate', 'End Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.enddate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.scheduleplanner', 'Schedule Planner', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.scheduleplanner'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.scheduleoverview', 'Schedule Overview', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.scheduleoverview'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.startdatemustbeentered', 'Start Date must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.startdatemustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.enddatemustbeentered', 'End Date must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.enddatemustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.pleaseprovideweekandestimatedstarttimeandaroutetogenerateschedule', 'Please provide week and estimated start time and a route to generate schedule.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.pleaseprovideweekandestimatedstarttimeandaroutetogenerateschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.doyouwanttosavetheschedule', 'Do you want to save the Schedule?', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.doyouwanttosavetheschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.pleasesavechangesonscheduleplannerbeforegeneratingtheschedules', 'Please save changes on Schedule Planner before generating the schedules.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.pleasesavechangesonscheduleplannerbeforegeneratingtheschedules'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.schedulealreadyexistforthedatesspecified', 'Schedule already exist for the dates specified.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.schedulealreadyexistforthedatesspecified'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.schedulealreadygeneratedforthespecifieddatestosaveclickonthesavebutton', 'Schedule already generated for the specified Dates. To save click on the save button', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.schedulealreadygeneratedforthespecifieddatestosaveclickonthesavebutton'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripstartdatecannotbegreaterthentripenddate', 'Trip Start Date cannot be greater then Trip End Date.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripstartdatecannotbegreaterthentripenddate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripenddatecannotbesmallerthencurrentdate', 'Trip End Date cannot be smaller then Current Date.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripenddatecannotbesmallerthencurrentdate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripstartdateshouldbegreaterthen', 'Trip Start Date should be greater then', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripstartdateshouldbegreaterthen'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.schedulestartdateshouldbegreaterthen', 'Schedule start date should be greater then', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.schedulestartdateshouldbegreaterthen'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.schedulestartandenddateshouldbemorethan1monthapart', 'Schedule start and end date should be more than 1 month apart.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.schedulestartandenddateshouldbemorethan1monthapart'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.selectatleastoneroutetogenerateschedule', 'Select at least one route to generate schedule.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.selectatleastoneroutetogenerateschedule'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.weekanddayshouldbeunique', 'Week and Day should be unique.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.weekanddayshouldbeunique'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripstartdatecannotbesmallerthencurrentdate', 'Trip Start Date cannot be smaller then Current Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripstartdatecannotbesmallerthencurrentdate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripdeparturedatecannotbelessthentodaysdate', 'Trip Departure Date cannot be less then todays date.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripdeparturedatecannotbelessthentodaysdate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tripdeparturedatecannotbegreaterthatscheduleenddate', 'Trip Departure Date cannot be greater that schedule end date.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tripdeparturedatecannotbegreaterthatscheduleenddate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.departuredatemustbegreaterthantoday', 'Departure Date must be greater than today.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.departuredatemustbegreaterthantoday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.departuredatemustbeentered', 'Departure Date must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.departuredatemustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.estimatedendtimemustbeentered', 'Estimated End Time must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.estimatedendtimemustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.routenamemustbeentered', 'Route name must be entered.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.routenamemustbeentered'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.youcannotdeletethisscheduledtripaseitheroffendersvehicles', 'You cannot delete this Scheduled Trip as either Offenders, Vehicles,', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.youcannotdeletethisscheduledtripaseitheroffendersvehicles'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.thistripcannotbeupdatedasitalreadyhasoffendersassignedtoit', 'This trip cannot be updated as it already has offenders assigned to it.', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.thistripcannotbeupdatedasitalreadyhasoffendersassignedtoit'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.selectvechile', 'Select Vehicle', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.selectvechile'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.select', 'Select', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.select'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.cancel', 'Cancel', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.cancel'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.type', 'Type', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.type'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.make', 'Make', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.make'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.modelNo', 'ModelNo', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.modelNo'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.vehicleId', 'Vehicle ID#', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.vehicleId'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.description', 'Description', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.description'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.PhysCap', 'Phys.Cap', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.PhysCap'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIUSELVE', 'oiuselve.PhysOpt', 'Phys.Opt', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIUSELVE' AND msg_key =  'oiuselve.PhysOpt'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.week', 'Week', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.week'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.sunday', 'Sunday', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.sunday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.monday', 'Monday', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.monday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.tuesday', 'Tuesday', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.tuesday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.wednesday', 'Wednesday', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.wednesday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.thursday', 'Thursday', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.thursday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.friday', 'Friday', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.friday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.saturday', 'Saturday', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.saturday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.estimatedstarttime', 'Estimated Start Time', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.estimatedstarttime'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.departuredate', 'Departure Date', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.departuredate'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.estimatedendtime', 'Estimated End Time', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.estimatedendtime'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.endday', 'End Day', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.endday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.startday', 'Start Day', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.startday'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.rout', 'Rout', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.rout'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDGENST', 'oidgenst.route', 'Route', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDGENST' AND msg_key =  'oidgenst.route'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OIDFIXAD', 'oidfixad.serial', 'Serial #', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIDFIXAD' AND msg_key =  'oidfixad.serial'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCMLESET', 'ocmleset.ordersandconditionsstatus', 'Orders and condition status', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCMLESET' AND msg_key =  'ocmleset.ordersandconditionsstatus'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCMLESET', 'ocmleset.adjustmentsconfigurations', 'Adjustments configurations', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCMLESET' AND msg_key =  'ocmleset.adjustmentsconfigurations'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCMLESET', 'ocmleset.deletepermissions', 'Delete permissions', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCMLESET' AND msg_key =  'ocmleset.deletepermissions'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCMLESET', 'ocmleset.ValueMustBeEnter', 'Value must be enter', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCMLESET' AND msg_key =  'ocmleset.ValueMustBeEnter'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCMLESET', 'ocmleset.RecordUpdatedSuccessfully', 'Record Updated Successfully', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCMLESET' AND msg_key =  'ocmleset.RecordUpdatedSuccessfully'); 
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type
       , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('LABLE_ID_SEQUENCE'), 'OCMLESET', 'ocmleset.RecordNotUpdated', 'Record Not Updated', 'LABEL' 
    , current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OCMLESET' AND msg_key =  'ocmleset.RecordNotUpdated'); 
 
update
	system_labels
set
	msg_value = 'Duration must be entered.',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'oidcustad.durationmust';

update
	system_labels
set
	msg_value = 'Please enter a whole number. ',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'oidcustad.providewholenumbers';

update
	system_labels
set
	msg_value = 'This adjustment cannot be entered as the offender is ineligible for remission.',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'oidcustad.actionnotallowed';

update
	system_labels
set
	msg_value = 'From Date must be entered',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'oidcustad.fromDatemustbe';

update
	system_labels
set
	msg_value = 'This adjustment cannot be entered as the order is ineligible for remission.',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'oidcustad.actionNotallowed';
	
update
	system_labels
set
	msg_value = 'Please enter whole or half %days%.',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'oidcustad.providedecimals';	
	