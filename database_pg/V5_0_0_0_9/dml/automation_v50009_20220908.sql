--
--1. GET_ASTRIA_PERSON_ID_v5

Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_ASTRIA_PERSON_ID','select oi.identifier astriaPersonId   from   offender_identifiers oi,offender_bookings ob 
WHERE
    ob.offender_book_id = :offenderBookId
    AND ob.root_offender_id = oi.root_offender_id 
    AND oi.identifier_type="APID"','GetAstriaPersonId',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);
		
Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'GET_ASTRIA_PERSON_ID','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);


Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values ('GET_ASTRIA_PERSON_ID','GetAstriaPersonId','GET_ASTRIA_PERSON_ID',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null);

--
--2. GET_MESSAGE_ID Below Script are related to GET_MESSAGE_ID  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story

Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_MESSAGE_ID','select nextval(''EXTERNAL_MESSAGE_ID'')::text messageId from dual','GetMessageId',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',null);
  
Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values ('GET_MESSAGE_ID','GetMessageId','GET_MESSAGE_ID',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null);

--
--3. GET_PRISONER_ADMISSION Below Script are related to GET_PRISONER_ADMISSION  qucik action which is used in PrisionerAdmission Process which is for  s4-17096 story
	
delete from automation_api_query where QUERY_KEY='GET_PRISIONER_ADMISSION';

delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='GET_PRISIONER_ADMISSION';

delete from ACTION_API where API_ID='GET_PRISIONER_ADMISSION';
	
Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_PRISONER_ADMISSION','SELECT
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
     to_char(oe.MOVEMENT_TIME::TIMESTAMP AT TIME ZONE ''GMT'', ''YYYY-MM-DD"T"HH24:MI:SS"Z"'') as admissionDateAndTime, 
     oe.MOVEMENT_REASON_CODE admissionReason
FROM
    bed_assignment_histories bd,
    offender_external_movements oe,
    living_units lv,
    agency_locations al
WHERE
    bd.offender_book_id = :offenderBookId
    AND bd.bed_assign_seq = (
        SELECT
            MAX(bed_assign_seq)
        FROM
            bed_assignment_histories
        WHERE
            offender_book_id = :offenderBookId
    )
	and oe.movement_seq = (
        SELECT
            MAX(movement_seq)
        FROM
            offender_external_movements
        WHERE
            offender_book_id = :offenderBookId
    )
    and bd.offender_book_id = oe.offender_book_id 
    and oe.movement_type=''ADM''
    AND lv.living_unit_id=bd.living_unit_id
    and al.agy_loc_id=lv.agy_loc_id','Prisoner Admission Details',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',null);
	
Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'GET_PRISONER_ADMISSION','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);

Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values ('GET_PRISONER_ADMISSION','PrisonerAdmissionDetails','GET_PRISONER_ADMISSION',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null);
  
--
--4. GET_PRISONER_ALIAS  -- Below Script are related to GET_PRISONER_ALIAS  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story

delete from automation_api_query where QUERY_KEY='GET_PRISIONER_ALIAS';

delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='GET_PRISIONER_ALIAS';

delete from ACTION_API where API_ID='GET_PRISIONER_ALIAS';


Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_PRISONER_ALIAS','SELECT
    offenders.last_name familyName,
     offenders.offender_id_display eliteOffenderId,
     offenders.alias_name_type aliasTypeCode,
      offenders.first_name givenName,
       offenders.middle_name otherGivenNames,
        TO_CHAR(offenders.birth_date ,''YYYY-MM-DD'') dateOfBirth
     FROM
    offender_bookings,
    offenders
WHERE
    offender_bookings.offender_book_id = :offenderBookId
    AND offenders.root_offender_id = offender_bookings.root_offender_id
    AND offenders.offender_id != offender_bookings.offender_id','Prisoner Alias Details',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);


Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'GET_PRISONER_ALIAS','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);

Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values ('GET_PRISONER_ALIAS','PrisonerAliasDetails','GET_PRISONER_ALIAS',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null);
  
--
--5. GET_PRISONER_DETAILS  -- Below Script are related to GET_PRISONER_DETAILS  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story


delete from automation_api_query where QUERY_KEY='GET_PRISIONER_DETAILS';

delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='GET_PRISIONER_DETAILS';

delete from ACTION_API where API_ID='GET_PRISIONER_DETAILS';


Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_PRISONER_DETAILS','SELECT
    offenders.last_name familyName,
     offenders.offender_id_display eliteOffenderId,
      offenders.first_name givenName,
       offenders.middle_name otherGivenNames,
        TO_CHAR(offenders.birth_date ,''YYYY-MM-DD'') dateOfBirth,
		to_char(CURRENT_TIMESTAMP::TIMESTAMP AT TIME ZONE ''GMT'', ''YYYY-MM-DD"T"HH24:MI:SS"Z"''),
		offender_bookings.offender_book_id offenderBookId 	
     FROM
    offender_bookings,
    offenders
WHERE
    offender_bookings.offender_book_id = :offenderBookId
    AND offender_bookings.offender_id = offenders.offender_id','Prisoner Details',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);



Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'GET_PRISONER_DETAILS','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);

Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values ('GET_PRISONER_DETAILS','PrisonerDetails','GET_PRISONER_DETAILS',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null);

--
--6. GET_PRISONER_HOUSING -- Below Script are related to GET_PRISONER_HOUSING  qucik action which is used in PrisionerHousing Process which is for  s4-17096 story

delete from automation_api_query where QUERY_KEY='GET_PRISIONER_HOUSING';

delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='GET_PRISIONER_HOUSING';

delete from ACTION_API where API_ID='GET_PRISIONER_HOUSING';

Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_PRISONER_HOUSING','SELECT 
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
    to_char(bd.assignment_time::TIMESTAMP AT TIME ZONE ''GMT'', ''YYYY-MM-DD"T"HH24:MI:SS"Z"'') as newHousingDateAndTime 
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
    and al.agy_loc_id=lv.agy_loc_id','Prisoner Housing Details',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);
	
Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'GET_PRISONER_HOUSING','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);
   
Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values ('GET_PRISONER_HOUSING','PrisonerHousingDetails','GET_PRISONER_HOUSING',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null);
   
--
--7. GET_PRISONER_IDENTFIERS  -- Below Script are related to GET_PRISONER_IDENTFIERS  qucik action which is used in  PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story

delete from automation_api_query where QUERY_KEY='GET_PRISIONER_IDENTFIERS';

delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='GET_PRISIONER_IDENTFIERS';

delete from ACTION_API where API_ID='GET_PRISIONER_IDENTFIERS';

Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_PRISONER_IDENTFIERS','SELECT 
   oi. identifier_type identifierTypeCode,
    oi.identifier identifier,
    oi.issued_authority_text issuer   
FROM
    offender_identifiers oi,
    offender_bookings ob
WHERE
    ob.offender_book_id = :offenderBookId
    AND ob.root_offender_id = oi.root_offender_id','Prisoner Identifiers',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'GET_PRISONER_IDENTFIERS','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);
   
Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values ('GET_PRISONER_IDENTFIERS','PrisonerIdentifierDetails','GET_PRISONER_IDENTFIERS',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null); 
   
--
--8. GET_PRISONER_RELEASE   -- Below Script are related to GET_PRISONER_RELEASE  qucik action which is used in PrisionerRelease Process which is for  s4-17098 story
 
delete from automation_api_query where QUERY_KEY='GET_PRISIONER_RELEASE';

delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='GET_PRISIONER_RELEASE';

delete from ACTION_API where API_ID='GET_PRISIONER_RELEASE';

Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_ID_SEQ'),'GET_PRISONER_RELEASE','SELECT
    to_char(movement_time::TIMESTAMP AT TIME ZONE ''GMT'', ''YYYY-MM-DD"T"HH24:MI:SS"Z"'') as releasedateandtime,  
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
    )','Prisoner Release Details',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);
	
Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'GET_PRISONER_RELEASE','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);

Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values ('GET_PRISONER_RELEASE','PrisonerReleaseDetails','GET_PRISONER_RELEASE',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null);
   
--   
--9. PRISONER_TRANSFER_DETAILS  -- Below Script are related to PRISONER_TRANSFER_DETAILS  qucik action which is used in PrisionerTransfer Process which is for  s4-17097 story


delete from automation_api_query where QUERY_KEY='PRISIONER_TRANSFER_DETAILS';
delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='PRISIONER_TRANSFER_DETAILS';
delete from ACTION_API where API_ID='PRISIONER_TRANSFER_DETAILS';
Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_ID_SEQ'),'PRISONER_TRANSFER_DETAILS','SELECT
    to_char(movement_time::TIMESTAMP AT TIME ZONE ''GMT'', ''YYYY-MM-DD"T"HH24:MI:SS"Z"'') as transferOutDateAndTime, 
    movement_reason_code   transferReasonCode,
    to_agy_loc_id  transferToFacilityCode
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
            offender_book_id = :offenderBookId  )','Transfer Details',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);
			
Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'PRISONER_TRANSFER_DETAILS','Number',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderBookId','offenderBookId',null);
   
Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values ('PRISONER_TRANSFER_DETAILS','Transfer Details','PRISONER_TRANSFER_DETAILS',null,'Elite2Web/api/cmdaction/executeSelectQuery',current_timestamp,'OMS_OWNER',current_timestamp,null,null); 
   
--
--10. RESPONSE_FORMATTER   -- Below Script are related to RESPONSE_FORMATTER  qucik action which is used in PrisionerAdmission,PrisionerRelease,PrisionerTransfer,PrisionerHousing Processes which is for  s4-17096, s4-17097,s4-17098,story

delete from automation_api_query where QUERY_KEY='RESPONE_FORMATTER';

delete from AUTOMATION_QUERY_PARAMETERS where QUERY_KEY='RESPONE_FORMATTER';

delete from ACTION_API where API_ID='RESPONE_FORMATTER';	
	
	
Insert into automation_api_query (QUERY_ID,QUERY_KEY,QUERY_TEXT,QUERY_DESC,ACTIVE_FLAG,VERIFIED_BY,VERIFIED_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_ID_SEQ'),'RESPONSE_FORMATTER','SELECT 1 FROM DUAL','Response Formatter',null,null,null,current_timestamp,'OMS_OWNER',current_timestamp,null,null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'Prisoner Admission Details','PrisonerAdmissionDetails',null);
   
Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'Prisoner Alias Details','PrisonerAliasDetails',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'Prisoner Details','PrisonerDetails',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'Prisoner Housing Details','PrisonerHousingDetails',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
   values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'Prisoner Identifier Details','PrisonerIdentifierDetails',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'Prisoner Release Details','PrisonerReleaseDetails',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'PrisonerTransferDetails','PrisonerTransferDetails',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER','specificationKey','specificationKey',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'offenderData','offenderData',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'MessageId','MessageId',null);

Insert into AUTOMATION_QUERY_PARAMETERS (PARAMETER_ID,QUERY_KEY,PARAMETER_TYPE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,PARAMETER_DESCRIPTION,PARAMETER_CODE,SEAL_FLAG) 
  values (nextval('AUTOMATION_QUERY_PARAM_ID_SEQ'),'RESPONSE_FORMATTER','String',current_timestamp,'OMS_OWNER',current_timestamp,null,'AstriaPersonId','AstriaPersonId',null);


Insert into ACTION_API (API_ID,API_DESCRIPTION,QUERY_KEY,REQUEST_TYPE,URL,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG) 
   values ('RESPONSE_FORMATTER','ResponSe Formatter','RESPONSE_FORMATTER',null,'Elite2Web/api/serviceBusController/convertJson',current_timestamp,'OMS_OWNER',current_timestamp,null,null); 
