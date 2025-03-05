UPDATE oms_owner.offender_bookings
   SET living_unit_id = NULL
      ,modify_user_id = 'OMS_OWNER'
	  ,modify_datetime = CURRENT_TIMESTAMP 
 WHERE agy_loc_id = 'TRN' 
   AND living_unit_id IS NOT NULL;