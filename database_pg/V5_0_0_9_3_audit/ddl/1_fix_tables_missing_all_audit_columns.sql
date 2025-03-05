ALTER TABLE oms_owner.agency_internal_loc_hotspot ADD COLUMN	create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
	                                                   ,ADD COLUMN create_user_id varchar(32) NULL  
	                                                   ,ADD COLUMN modify_datetime timestamp(6) NULL 
	                                                   ,ADD COLUMN modify_user_id varchar(32) NULL 
	                                                   ,ADD COLUMN seal_flag varchar(1) NULL;
													   
													   
-- ALTER TABLE oms_owner.dfmt ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                           -- ,ADD COLUMN create_user_id varchar(32) NULL  
	                           -- ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                           -- ,ADD COLUMN modify_user_id varchar(32) NULL 
	                           -- ,ADD COLUMN seal_flag varchar(1) NULL;
							   
-- ALTER TABLE oms_owner.ext_session_local_tz ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                                           -- ,ADD COLUMN create_user_id varchar(32) NULL  
	                                           -- ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                                           -- ,ADD COLUMN modify_user_id varchar(32) NULL 
	                                           -- ,ADD COLUMN seal_flag varchar(1) NULL;			

ALTER TABLE oms_owner.facility_placement ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                                         ,ADD COLUMN create_user_id varchar(32) NULL  
	                                         ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                                         ,ADD COLUMN modify_user_id varchar(32) NULL 
	                                         ,ADD COLUMN seal_flag varchar(1) NULL;									   
							   
ALTER TABLE oms_owner.floor_plan ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                           ,ADD COLUMN create_user_id varchar(32) NULL  
	                           ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                           ,ADD COLUMN modify_user_id varchar(32) NULL 
	                           ,ADD COLUMN seal_flag varchar(1) NULL;		

-- ALTER TABLE oms_owner.format_models ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                                    -- ,ADD COLUMN create_user_id varchar(32) NULL  
	                                    -- ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                                    -- ,ADD COLUMN modify_user_id varchar(32) NULL 
	                                    -- ,ADD COLUMN seal_flag varchar(1) NULL;		

-- ALTER TABLE oms_owner.nfmt ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                           -- ,ADD COLUMN create_user_id varchar(32) NULL  
	                           -- ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                           -- ,ADD COLUMN modify_user_id varchar(32) NULL 
	                           -- ,ADD COLUMN seal_flag varchar(1) NULL;		

ALTER TABLE oms_owner.offender_configuration ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                                             ,ADD COLUMN create_user_id varchar(32) NULL  
	                                             ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                                             ,ADD COLUMN modify_user_id varchar(32) NULL 
	                                             ,ADD COLUMN seal_flag varchar(1) NULL;		

ALTER TABLE oms_owner.offender_deductions_temp ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                                               ,ADD COLUMN create_user_id varchar(32) NULL  
	                                               ,ADD COLUMN modify_datetime timestamp(6) NULL ; 	                                             
	                                             													   

ALTER TABLE oms_owner.oms_modules_help ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                                               ,ADD COLUMN create_user_id varchar(32) NULL  
	                                               ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                                               ,ADD COLUMN modify_user_id varchar(32) NULL 
	                                               ,ADD COLUMN seal_flag varchar(1) NULL;	


-- ALTER TABLE oms_owner.tdfmt ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                            -- ,ADD COLUMN create_user_id varchar(32) NULL  
	                            -- ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                            -- ,ADD COLUMN modify_user_id varchar(32) NULL 
	                            -- ,ADD COLUMN seal_flag varchar(1) NULL;		

-- ALTER TABLE oms_owner.tnfmt ADD COLUMN create_datetime timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP 
	                            -- ,ADD COLUMN create_user_id varchar(32) NULL  
	                            -- ,ADD COLUMN modify_datetime timestamp(6) NULL  
	                            -- ,ADD COLUMN modify_user_id varchar(32) NULL 
	                            -- ,ADD COLUMN seal_flag varchar(1) NULL;

								
UPDATE oms_owner.agency_internal_loc_hotspot SET create_user_id = 'OMS_OWNER';
-- UPDATE oms_owner.dfmt SET create_user_id = 'OMS_OWNER';
-- UPDATE oms_owner.ext_session_local_tz SET create_user_id = 'OMS_OWNER';
UPDATE oms_owner.facility_placement SET create_user_id = 'OMS_OWNER';
UPDATE oms_owner.floor_plan SET create_user_id = 'OMS_OWNER';
-- UPDATE oms_owner.format_models SET create_user_id = 'OMS_OWNER';
-- UPDATE oms_owner.nfmt SET create_user_id = 'OMS_OWNER';
UPDATE oms_owner.offender_configuration SET create_user_id = 'OMS_OWNER';
UPDATE oms_owner.offender_deductions_temp SET create_user_id = 'OMS_OWNER';
UPDATE oms_owner.oms_modules_help SET create_user_id = 'OMS_OWNER';
-- UPDATE oms_owner.tdfmt SET create_user_id = 'OMS_OWNER';
-- UPDATE oms_owner.tnfmt SET create_user_id = 'OMS_OWNER';	


ALTER TABLE oms_owner.agency_internal_loc_hotspot ALTER COLUMN create_user_id SET NOT NULL;
-- ALTER TABLE oms_owner.dfmt ALTER COLUMN create_user_id SET NOT NULL;
-- ALTER TABLE oms_owner.ext_session_local_tz ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.facility_placement ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.floor_plan ALTER COLUMN create_user_id SET NOT NULL;
-- ALTER TABLE oms_owner.format_models ALTER COLUMN create_user_id SET NOT NULL;
-- ALTER TABLE oms_owner.nfmt ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.offender_configuration ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.offender_deductions_temp ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.oms_modules_help ALTER COLUMN create_user_id SET NOT NULL;
-- ALTER TABLE oms_owner.tdfmt ALTER COLUMN create_user_id SET NOT NULL;
-- ALTER TABLE oms_owner.tnfmt ALTER COLUMN create_user_id SET NOT NULL;

							
								