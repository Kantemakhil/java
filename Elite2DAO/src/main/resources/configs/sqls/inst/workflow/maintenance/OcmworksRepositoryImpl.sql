
OCMWORKS_FIND_RGWORKFLOWTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION          ,REF_CODE.CODE  WORKFLOW_TYPE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'ALERT_TASK'  AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR ::MODE = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,REF_CODE.DESCRIPTION
}

OCMWORKS_FIND_RGAGYLOCTYPE {
 	SELECT DESCRIPTION         ,CODE FROM REFERENCE_CODES WHERE DOMAIN = 'CLOAD_TYPE' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR ::MODE = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION
}

OCMWORKS_FIND_RGWORKTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION, REF_CODE.CODE  CODE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'TASK_TYPE'  AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR 'ENTER-QUERY' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,REF_CODE.DESCRIPTION
}

OCMWORKS_FIND_RGWORKSUBTYPE {
select A.DOMAIN, A.CODE, A.DESCRIPTION, coalesce (B.FLAG, 'N') ACTIVE_FLAG from REFERENCE_CODES A left join ( select REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE, 'Y' FLAG, domain from REFERENCE_CODES REF_CODE where PARENT_CODE =:PARENT_CODE and domain = 'TASK_SUBTYPE' and (ACTIVE_FLAG = 'Y' and EXPIRED_DATE is null ) order by LIST_SEQ, REF_CODE.DESCRIPTION)B on A.DOMAIN = B.DOMAIN and A.CODE = B.CODE where A.DOMAIN = 'TASK_SUBTYPE'
}

OCMWORKS_FIND_RGMODULES {
 	SELECT DESCRIPTION , MODULE_NAME CODE FROM OMS_MODULES WHERE MODULE_TYPE = 'SCREEN' AND  DESCRIPTION IS NOT NULL ORDER BY 1
}

OCMWORKS_FIND_RGTEMPLATES {
 	SELECT TEMPLATE_NAME         ,DESCRIPTION         ,to_char(TEMPLATE_ID) CODE ,
case when (select count(*)  FROM IWP_TEMPLATES WHERE ACTIVE_FLAG = 'Y'  AND OBJECT_TYPE = 'IWP' and TEMPLATE_ID = iwp.TEMPLATE_ID)>0 then 'Y' else 'N' end ACTIVE_FLAG
FROM IWP_TEMPLATES  iwp ORDER BY TEMPLATE_NAME ASC
}

OCMWORKS_FIND_RGTRIGGERNAME {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION          ,REF_CODE.CODE  TRIGGER_NAME FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'WORK_TRIGGER'  AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR ::MODE = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,REF_CODE.DESCRIPTION
}

OCMWORKS_FIND_RGFUNCTION {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION        ,REF_CODE.CODE  FUNCTION FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'FUNCTION' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ORDER BY LIST_SEQ
}

OCMWORKS_WFWORKTYPES_FIND_WORKS {
 	SELECT WORK_ID, WORKFLOW_TYPE, WORK_TYPE, WORK_SUB_TYPE, MANUAL_CLOSE_FLAG, MODULE_NAME, ACTIVE_FLAG, EXPIRY_DATE, CASELOAD_TYPE, MANUAL_SELECT_FLAG, CREATE_DATETIME, CREATE_USER_ID, 
MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG, EMAIL_SUBJECT, EMAIL_BODY ,case_note_text,
(SELECT count(CASE_NOTE_ID) FROM offender_case_notes WHERE case_note_type = w.WORK_TYPE AND case_note_sub_type = w.WORK_SUB_TYPE) as CASENOTE_FLAG
    FROM OMS_OWNER.WORKS w WHERE
}
OCMWORKS_WFWORKTYPES_INSERT_WORKS {
insert into OMS_OWNER.WORKS(WORK_ID, WORKFLOW_TYPE, WORK_TYPE, WORK_SUB_TYPE, MANUAL_CLOSE_FLAG, MODULE_NAME, ACTIVE_FLAG, EXPIRY_DATE, CASELOAD_TYPE, MANUAL_SELECT_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG, EMAIL_SUBJECT, EMAIL_BODY,case_note_text) values(nextval('work_id'), :workflowType, :workType, :workSubType, :manualCloseFlag, :moduleName, :activeFlag, :expiryDate, :caseloadType, :manualSelectFlag, CURRENT_TIMESTAMP, :createUserId, null, :sealFlag, :emailSubject, :emailBody,:caseNoteText)
}

OCMWORKS_WFWORKTYPES_UPDATE_WORKS {
update OMS_OWNER.WORKS set WORKFLOW_TYPE =:workflowType, WORK_TYPE =:workType, WORK_SUB_TYPE =:workSubType, MANUAL_CLOSE_FLAG =:manualCloseFlag, MODULE_NAME =:moduleName, ACTIVE_FLAG =:activeFlag, EXPIRY_DATE =:expiryDate, CASELOAD_TYPE =:caseloadType, MANUAL_SELECT_FLAG =:manualSelectFlag, MODIFY_DATETIME = CURRENT_TIMESTAMP, MODIFY_USER_ID =:modifyUserId, EMAIL_SUBJECT =:emailSubject, EMAIL_BODY =:emailBody,case_note_text =:caseNoteText where WORK_ID =:workId 
}

OCMWORKS_WFWORKTYPES_DELETE_WORKS { 
	DELETE FROM WORKS WHERE WORK_ID=:workId
}

OCMWORKS_WFIWPTEMPLATES_FIND_WORK_IWP_TEMPLATES {
select * from WORK_IWP_TEMPLATES where WORK_ID = :workId order by ACTIVE_FLAG desc, OCMWORKS_GET_TEMPLATE_DESC(TEMPLATE_ID)
}
OCMWORKS_WFIWPTEMPLATES_INSERT_WORK_IWP_TEMPLATES {
insert into WORK_IWP_TEMPLATES(WORK_ID, TEMPLATE_ID, ACTIVE_FLAG, EXPIRY_DATE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, LIST_SEQ, SEAL_FLAG) values(:workId, :templateId, :activeFlag, :expiryDate, CURRENT_TIMESTAMP, :createUserId, null, :listSeq, :sealFlag)
}

OCMWORKS_WFIWPTEMPLATES_UPDATE_WORK_IWP_TEMPLATES {
update OMS_OWNER.WORK_IWP_TEMPLATES set ACTIVE_FLAG =:activeFlag, EXPIRY_DATE =:expiryDate, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where WORK_ID =:workId and TEMPLATE_ID =:templateId 
}

OCMWORKS_WFIWPTEMPLATES_DELETE_WORK_IWP_TEMPLATES { 
	DELETE FROM WORK_IWP_TEMPLATES WHERE WORK_ID=:workId  and TEMPLATE_ID=:templateId
}

OCMWORKS_WFTRIGGERS_FIND_WORK_TRIGGERS {
select * from WORK_TRIGGERS where WORK_ID = :workId order by ACTIVE_FLAG desc, OMS_MISCELLANEOUS_GETDESCCODE ('WORK_TRIGGER', TRIGGER_NAME) desc
}
OCMWORKS_WFTRIGGERS_INSERT_WORK_TRIGGERS {
insert into OMS_OWNER.WORK_TRIGGERS(TRIGGER_NAME, WORK_ID, DAYS, DUE, ACTIVE_FLAG, EXPIRY_DATE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) values(:triggerName, :workId, :days, :due, :activeFlag, :expiryDate, current_timestamp, :createUserId , null, :sealFlag) 
}

OCMWORKS_WFTRIGGERS_UPDATE_WORK_TRIGGERS {
update OMS_OWNER.WORK_TRIGGERS set DAYS =:days, DUE =:due, ACTIVE_FLAG =:activeFlag, EXPIRY_DATE =:expiryDate, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where TRIGGER_NAME =:triggerName and WORK_ID =:workId 
}

OCMWORKS_WFTRIGGERS_DELETE_WORK_TRIGGERS { 
	DELETE FROM WORK_TRIGGERS  WHERE TRIGGER_NAME=:triggerName and WORK_ID=:workId
}

OCMWORKS_WFFUNCTIONS_FIND_WORK_FUNCTIONS {
 	SELECT  * FROM WORK_FUNCTIONS  WHERE WORK_ID = :workId ORDER BY FUNCTION_TYPE ASC
}
OCMWORKS_WFFUNCTIONS_INSERT_WORK_FUNCTIONS {
 insert into OMS_OWNER.WORK_FUNCTIONS(FUNCTION_TYPE, WORK_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) values(:functionType, :workId, CURRENT_TIMESTAMP , :createUserId , null , :sealFlag) 
}

OCMWORKS_WFFUNCTIONS_UPDATE_WORK_FUNCTIONS {
}

OCMWORKS_WFFUNCTIONS_DELETE_WORK_FUNCTIONS { 
	DELETE FROM WORK_FUNCTIONS where  FUNCTION_TYPE=:functionType and WORK_ID=:workId
}

OCMWORKS_WFEMAILRECIPIENTS_FIND_INTERNET_ADDRESSES {
 select * from INTERNET_ADDRESSES where OWNER_ID = :ownerId and OWNER_CLASS = 'WORKFLOW' and INTERNET_ADDRESS_CLASS in ('EMAIL_TO', 'EMAIL_CC', 'EMAIL_BCC') order by case when INTERNET_ADDRESS_CLASS = 'EMAIL_TO' then 1 when INTERNET_ADDRESS_CLASS = 'EMAIL_CC' then 2 when INTERNET_ADDRESS_CLASS = 'EMAIL_BCC' then 3 end , INTERNET_ADDRESS
}
OCMWORKS_WFEMAILRECIPIENTS_INSERT_INTERNET_ADDRESSES {
insert into INTERNET_ADDRESSES(INTERNET_ADDRESS_ID , OWNER_CLASS , OWNER_ID , OWNER_SEQ , OWNER_CODE , INTERNET_ADDRESS_CLASS , INTERNET_ADDRESS , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(NEXTVAL('INTERNET_ADDRESS_ID') , 'WORKFLOW' , :ownerId , :ownerSeq , :ownerCode , :internetAddressClass , :internetAddress , CURRENT_TIMESTAMP , :createUserId , null , :sealFlag ) 
}

OCMWORKS_WFEMAILRECIPIENTS_UPDATE_INTERNET_ADDRESSES {
 update INTERNET_ADDRESSES set OWNER_CLASS =:ownerClass, OWNER_ID =:ownerId , OWNER_SEQ =:ownerSeq , OWNER_CODE =:ownerCode , INTERNET_ADDRESS_CLASS =:internetAddressClass , INTERNET_ADDRESS =:internetAddress , MODIFY_DATETIME =current_timestamp , MODIFY_USER_ID =:modifyUserId , SEAL_FLAG =:sealFlag where INTERNET_ADDRESS_ID =:internetAddressId 
}

OCMWORKS_WFEMAILRECIPIENTS_DELETE_INTERNET_ADDRESSES { 
	DELETE FROM INTERNET_ADDRESSES where INTERNET_ADDRESS_ID=:internetAddressId  
}
OCMWORKS_WFEMAILRETURNS_FIND_INTERNET_ADDRESSES {
 	SELECT INTERNET_ADDRESS_ID, OWNER_CLASS, OWNER_ID, OWNER_SEQ, OWNER_CODE, INTERNET_ADDRESS_CLASS, INTERNET_ADDRESS,INTERNET_ADDRESS RETURN_ADDRESS, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG 
    FROM INTERNET_ADDRESSES
 	WHERE    OWNER_ID= :ownerId and owner_class = 'WORKFLOW' AND internet_address_class = 'EMAIL_RTN' 
}
OCMWORKS_WFWORKEMAIL_FIND_WORKS {
 	SELECT  * FROM WORKS  
}
OCMWORKS_WFWORKEMAIL_INSERT_WORKS {
	INSERT INTO WORKS() VALUES(:)
}

OCMWORKS_WFWORKEMAIL_UPDATE_WORKS {
 update OMS_OWNER.WORKS set EMAIL_SUBJECT =:emailSubject, EMAIL_BODY =:emailBody , MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where WORK_ID =:workId 
}

OCMWORKS_WFWORKEMAIL_DELETE_WORKS { 
	DELETE FROM WORKS WHERE WORK_ID=:workId
}


OCMWORKS_WF_WORK_TYPES_POSTQUERY_ {
	SELECT DESCRIPTION FROM OMS_MODULES WHERE MODULE_NAME = :MODULENAME
}

OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM WORK_IWP_TEMPLATES W WHERE W.WORK_ID = :WORKID
}

OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM WORK_TRIGGERS W WHERE W.WORK_ID = :WORKID
}

OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM WORK_FUNCTIONS W WHERE W.WORK_ID = :WORKID
}

OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM INTERNET_ADDRESSES W WHERE W.OWNER_ID = :WORKID AND W.OWNER_CLASS = 'WORKFLOW' AND W.INTERNET_ADDRESS_CLASS IN ('EMAIL_TO', 'EMAIL_CC', 'EMAIL_BCC')
}

OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM INTERNET_ADDRESSES W WHERE W.OWNER_ID = :WORKID AND W.OWNER_CLASS = 'WORKFLOW' AND W.INTERNET_ADDRESS_CLASS IN ('EMAIL_RTN')
}

OCMWORKS_WF_EMAIL_RECIPIENTS_PREINSERT_ {
SELECT NEXTVAL('INTERNET_ADDRESS_ID') FROM DUAL
}

OCMWORKS_WF_EMAIL_RETURN_PREINSERT_ {
	SELECT 1 FROM INTERNET_ADDRESSES WHERE OWNER_CLASS = 'WORKFLOW' AND OWNER_ID = :WORKID AND INTERNET_ADDRESS_CLASS = 'EMAIL_RTN'
}

OCMWORKS_WF_EMAIL_RETURN_PREINSERT_ {
	SELECT INTERNET_ADDRESS_ID.NEXTVAL FROM DUAL
}

OCMWORKS_RECIPIENTS_ {
	SELECT INTERNET_ADDRESS_ID, INTERNET_ADDRESS_CLASS FROM INTERNET_ADDRESSES WHERE OWNER_CLASS = 'WORKFLOW' AND OWNER_ID = P_WORK_ID
}
OCMWORKS_ON_CHECK_DELETE_MASTER_QUERY {
select ( select 'templateCount' from work_iwp_templates w where w.work_id = work.work_id limit 1) as templateCount, ( select 'triggerCount' from work_triggers w where w.work_id = work.work_id limit 1) as triggerCount , ( select 'functionCount' from work_functions w where w.work_id = work.work_id limit 1) as functionCount, ( select 'emailRecipientsCount' from internet_addresses w where w.owner_id = work.work_id and w.owner_class = 'WORKFLOW' and w.internet_address_class in ('EMAIL_TO', 'EMAIL_CC', 'EMAIL_BCC') limit 1) as emailRecipientsCount, ( select 'emailReturnCount' from internet_addresses w where w.owner_id = work.work_id and w.owner_class = 'WORKFLOW' and w.internet_address_class in ('EMAIL_RTN') limit 1) as emailReturnCount from works work where work_id = :work_id
}
