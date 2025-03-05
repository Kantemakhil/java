--
--reference_codes_v50050_20230509.sql 
--
DELETE	from oms_owner.reference_codes
	where
		domain = 'KEY_DATES'
		and code = 'CUST_STATUS';

--
--system_labels_v50050_20230509.sql 
--
UPDATE 	system_labels 
       SET    msg_value = 'This flag can only be checked for one record.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
WHERE	module_name = 'OIMCUSTS' 
      AND  msg_key = 'oimcusts.onlyoneintakeshouldbechecked';

UPDATE 	system_labels 
       SET    msg_value = 'This flag can only be checked for one record.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
  WHERE module_name = 'OIMCUSTS'
       AND msg_key = 'oimcusts.onlyonereleaseshouldbechecked';

UPDATE 	system_labels 
       SET    msg_value = 'This description has already been used.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
 WHERE  module_name = 'OIMCUSTS' 
     AND  msg_key = 'oimcusts.descriptionshouldnotbeduplicate';

UPDATE 	system_labels 
       SET    msg_value = 'This code has already been used.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
  WHERE  module_name = 'OIMCUSTS'
      AND msg_key = 'oimcusts.codeshouldnotbeduplicate';


UPDATE 	system_labels 
       SET    msg_value = 'This adjustment can only be recorded after Initial Booking Level Remission has been entered.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
   WHERE   module_name = 'OIDCUSTAD'
        AND msg_key = 'oidcustad.firtsrecordmustbe'; 

--
--system_labels_v50051_30052023.sql 
--
UPDATE 	system_labels 
       SET    msg_value = 'Date cannot be before the start date of the offender''s booking. ', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
 WHERE 	module_name = 'OIDCNOTE'
      AND   msg_key = 'oidcnote.datecannotbebeforetheoffenderbookingbegindate';

UPDATE 	system_labels 
       SET    msg_value = 'This adjustment cannot be entered without an initial <booking/sentence> remission adjustment.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
 WHERE 	module_name = 'OIDCUSTAD'
      AND   msg_key = 'oidcustad.withoutinitial'; 

UPDATE 	system_labels 
       SET    msg_value = 'From Date must be entered.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
 WHERE 	module_name = 'OIDCUSTAD'
      AND   msg_key = 'oidcustad.fromDatemustbe';


UPDATE 	system_labels 
       SET    msg_value = 'Appearance location must be entered.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
 WHERE  module_name = 'CALSCH'
      AND   msg_key = 'calsch.appearanceLocationMand';

--
--system_labels_v50052_20230606.sql
--
UPDATE 	system_labels 
       SET    msg_value = 'Value must be entered.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
  WHERE    module_name = 'OCMLESET' 
      AND msg_key =  'ocmleset.ValueMustBeEnter';

--
--reference_codes_v50053_20230613.sql
--
--remove statement: DELETE FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE';


--
--system_labels_v50053_20230613.sql
--
UPDATE 	system_labels 
       SET    msg_value = 'This record cannot be deleted as a child record exists.', 
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
 WHERE 	module_name = 'OIMSATYP'
      AND   msg_key = 'oimsatyp.childsexists';



