OUMAGYHT_INSERT_INTO_AGY_LOC_AMENDMENTS{
insert into agency_location_amendments (agy_loc_amend_id, agy_loc_id, FIELD, original_value, new_value, amend_datetime, amend_user , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME) values (nextval('agy_loc_amend_id'), :agyLocId, :pColName, :originalValue, :newValue, current_timestamp, :amendUser, current_timestamp , :createUserId , NULL ) 
}
OUMAGYHT_GET_ADDRESS_OWNER_CODE{
SELECT OWNER_CODE  FROM ADDRESSES  WHERE ADDRESS_ID = :pAddrId  AND OWNER_CLASS = 'AGY'
}