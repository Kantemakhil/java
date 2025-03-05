update iwp_bookmark_out_parameters set parameter_desc='Address line 1 (Street Number, Street Name)',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='ADDRESS_LINE1' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Address line 2 (City, State)',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='ADDRESS_LINE2'and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Address line 3 (Country, Postal Code)',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='ADDRESS_LINE3' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Comment', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='PROP_ITMRECR' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Primary Address flag',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='PRIMARY_FLAG' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Active flag',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='ACTIVE' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Validated flag' ,modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='VALIDATED' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Phone Type', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='PHONE_TYPE' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Extension',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='EXTENSION' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='Phone Number',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='PHONE_NUMBER' and bookmark_name='OFFEN_ADDRES';

update iwp_bookmark_out_parameters set parameter_desc='Address Type', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='ADDRESS_TYPE' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='From date',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='FROM_DATE' and bookmark_name='OFFEN_ADDRES';
update iwp_bookmark_out_parameters set parameter_desc='To Date',modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where parameter_name='TO_DATE' and bookmark_name='OFFEN_ADDRES';
