-- Below Script are related to PRISIONER_TRANSFER_DETAILS  qucik action which is used in PrisionerTransfer Process which is for  s4-17097 story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'PRISIONER_TRANSFER_DETAILS'
           ,'SELECT 
    movement_time          transferOutDateAndTime,
    movement_reason_code   transferReasonCode,
    to_agy_loc_id  transferToFacilityCode 
FROM offender_external_movements
WHERE offender_book_id = :offenderBookId  
  AND movement_seq = (
        SELECT
            MAX(movement_seq)
        FROM
            offender_external_movements
        WHERE
            offender_book_id = :offenderBookId           
    )'
	,'Transfer Details',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);
	
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'PRISIONER_TRANSFER_DETAILS','Number',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderBookId','offenderBookId',null);


INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('PRISIONER_TRANSFER_DETAILS','Transfer Details','PRISIONER_TRANSFER_DETAILS',null,'Elite2Web/api/cmdaction/executeSelectQuery',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


-- Below Script are related to GET_PRISIONER_RELEASE  qucik action which is used in PrisionerRelease Process which is for  s4-17098 story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   values (nextval('automation_query_id_seq'),'GET_PRISIONER_RELEASE','SELECT 
    movement_time          releasedateandtime,
    movement_reason_code   releasereasoncode
FROM
    offender_external_movements
WHERE
    offender_book_id = :offenderBookId
    AND movement_seq = (
        SELECT
            MAX(movement_seq)
        FROM
            offender_external_movements
        WHERE
            offender_book_id = :offenderBookId
    )','Prisioner Release Details',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);
	

INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'GET_PRISIONER_RELEASE','Number',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderBookId','offenderBookId',null);

INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('GET_PRISIONER_RELEASE','PrisionerReleaseDetails','GET_PRISIONER_RELEASE',null,'Elite2Web/api/cmdaction/executeSelectQuery',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);
   

-- Below Script are related to GET_PRISIONER_ADMISSION  qucik action which is used in PrisionerAdmission Process which is for  s4-17096 story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'GET_PRISIONER_ADMISSION','SELECT     
    lv.level_1_code housingLevelOneCode,
    al.housing_lev_1_code housingLevelOneTypeCode, 
    lv.level_2_code housingLevelTwoCode,
    al.housing_lev_2_code housingLevelTwoTypeCode,
    lv.level_3_code housingLevelThreeCode,
    al.housing_lev_3_code housingLevelThreeTypeCode,
    lv.level_4_code housingLevelFourCode,
    al.housing_lev_4_code housingLevelFourTypeCode,
    lv.description housingLocationCode,
    lv.agy_loc_id housingFacilityCode,   
    bd.assignment_time admissionDateAndTime   
FROM
    bed_assignment_histories bd,
    living_units lv,
    agency_locations al
WHERE
    offender_book_id = :offenderBookId
    AND bed_assign_seq = (
        SELECT
            MAX(bed_assign_seq)
        FROM
            bed_assignment_histories
        WHERE
            offender_book_id = :offenderBookId
    )
    AND lv.living_unit_id=bd.living_unit_id
    and al.agy_loc_id=lv.agy_loc_id','Prisioner Admission Details',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);
	
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'GET_PRISIONER_ADMISSION','Number',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderBookId','offenderBookId',null);

INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('GET_PRISIONER_ADMISSION','PrisionerAdmissionDetails','GET_PRISIONER_ADMISSION',null,'Elite2Web/api/cmdaction/executeSelectQuery',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);

	
	
-- Below Script are related to RESPONE_FORMATTER  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story	
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'RESPONE_FORMATTER','SELECT 1 FROM DUEL','Respone Formatter',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);

INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'Prisioner Admission Details','PrisionerAdmissionDetails',null);
   
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
  VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'Prisioner Alias Details','PrisionerAliasDetails',null);
  
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
  VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'Prisioner Details','PrisionerDetails',null);
  
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
  VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'Prisioner Housing Details','PrisionerHousingDetails',null);
  
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'Prisioner Identifier Details','PrisionerIdentifierDetails',null);
   
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'Prisioner Release Details','PrisionerReleaseDetails',null);
   
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'PrisionerTransferDetails','PrisionerTransferDetails',null);
   
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',current_timestamp,'OMS_OWNER','specificationKey','specificationKey',null);
   
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'RESPONE_FORMATTER','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderData','offenderData',null);

INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('RESPONE_FORMATTER','Respone Formatter','RESPONE_FORMATTER',null,'Elite2Web/api/serviceBusController/convertJson',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


-- Below Script are related to RESPONE_FORMATTER  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'CONNECT_EXTERNAL_SERVICE','SELECT 1 FROM DUEL','Connect External Service',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);

INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
  VALUES (nextval('automation_query_param_id_seq'),'CONNECT_EXTERNAL_SERVICE','String',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'OffenderDetails','OffenderDetails',null);

INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('CONNECT_EXTERNAL_SERVICE','Connect External Service','CONNECT_EXTERNAL_SERVICE',null,'Elite2Web/api/externalsystem/connectExternalSystem',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);



-- Below Script are related to GET_PRISIONER_HOUSING  qucik action which is used in PrisionerHousing Process which is for  s4-17096 story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'GET_PRISIONER_HOUSING','SELECT   
    lv.level_1_code newHousingLevelOneCode,
    al.housing_lev_1_code newHousingLevelOneTypeCode,    
    lv.level_2_code newHousingLevelTwoCode,
    al.housing_lev_2_code newHousingLevelTwoTypeCode,
    lv.level_3_code newHousingLevelThreeCode,
    al.housing_lev_3_code newHousingLevelThreeTypeCode,
    lv.level_4_code newHousingLevelFourCode,
    al.housing_lev_4_code newHousingLevelFourTypeCode,
    lv.description newHousingLocationCode,
    lv.agy_loc_id newHousingFacilityCode, 
    bd.assignment_time newHousingDateAndTime    
FROM bed_assignment_histories bd,
    living_units lv,
    agency_locations al
WHERE offender_book_id = :offenderBookId
    AND bed_assign_seq = (
        SELECT
            MAX(bed_assign_seq)
        FROM
            bed_assignment_histories
        WHERE
            offender_book_id = :offenderBookId
    )
    AND lv.living_unit_id=bd.living_unit_id
    and al.agy_loc_id=lv.agy_loc_id','Prisioner Housing Details',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);
	
INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'GET_PRISIONER_HOUSING','Number',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderBookId','offenderBookId',null);
   
INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('GET_PRISIONER_HOUSING','PrisionerHousingDetails','GET_PRISIONER_HOUSING',null,'Elite2Web/api/cmdaction/executeSelectQuery',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


-- Below Script are related to GET_PRISIONER_ALIAS  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'GET_PRISIONER_ALIAS','SELECT
    offenders.last_name familyName,
     offenders.offender_id_display eliteOffenderId,
     offenders.alias_name_type aliasTypeCode,
      offenders.first_name givenName,
       offenders.middle_name otherGivenNames,
        offenders.birth_date dateOfBirth
     FROM offender_bookings,
    offenders
WHERE offender_bookings.offender_book_id = :offenderBookId
    AND offenders.root_offender_id = offender_bookings.root_offender_id
    AND offenders.offender_id != offender_bookings.offender_id','Prisioner Alias Details',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'GET_PRISIONER_ALIAS','Number',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderBookId','offenderBookId',null);

INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('GET_PRISIONER_ALIAS','PrisionerAliasDetails','GET_PRISIONER_ALIAS',null,'Elite2Web/api/cmdaction/executeSelectQuery',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


-- Below Script are related to GET_PRISIONER_IDENTFIERS  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'GET_PRISIONER_IDENTFIERS','SELECT  
   oi. identifier_type identifierTypeCode,
    oi.identifier identifier,
    oi.issued_authority_text issuer   
FROM offender_identifiers oi,
    offender_bookings ob
WHERE ob.offender_book_id = :offenderBookId
    AND ob.offender_id = oi.offender_id','Prisioner Identifiers',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);

INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'GET_PRISIONER_IDENTFIERS','Number',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderBookId','offenderBookId',null);
   
INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('GET_PRISIONER_IDENTFIERS','PrisionerIdentifierDetails','GET_PRISIONER_IDENTFIERS',null,'Elite2Web/api/cmdaction/executeSelectQuery',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


-- Below Script are related to GET_PRISIONER_DETAILS  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story
INSERT INTO automation_api_query (query_id,query_key,query_text,query_desc,active_flag,verified_by,verified_date,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES (nextval('automation_query_id_seq'),'GET_PRISIONER_DETAILS','SELECT 
    offenders.last_name familyName,
     offenders.offender_id_display eliteOffenderId,
      offenders.first_name givenName,
       offenders.middle_name otherGivenNames,
        offenders.birth_date dateOfBirth,
		systimestamp() as dateTime,
		offender_bookings.offender_book_id offenderBookId 	
     FROM offender_bookings,
    offenders
WHERE offender_bookings.offender_book_id = :offenderBookId
    AND offender_bookings.offender_id = offenders.offender_id','Prisioner Details',null,null,null,CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


INSERT INTO automation_query_parameters (parameter_id,query_key,parameter_type,create_datetime,create_user_id,modify_datetime,modify_user_id,parameter_description,parameter_code,seal_flag) 
   VALUES (nextval('automation_query_param_id_seq'),'GET_PRISIONER_DETAILS','Number',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,'offenderBookId','offenderBookId',null);

INSERT INTO action_api (api_id,api_description,query_key,request_type,url,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag) 
   VALUES ('GET_PRISIONER_DETAILS','PrisionerDetails','GET_PRISIONER_DETAILS',null,'Elite2Web/api/cmdaction/executeSelectQuery',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,null,null);


   
	