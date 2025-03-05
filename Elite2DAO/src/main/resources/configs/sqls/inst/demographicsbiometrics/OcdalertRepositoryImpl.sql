
OCDALERT_ALERT_FIND_OFFENDER_ALERTS{
	SELECT OID.ALERT_DATE, OID.OFFENDER_BOOK_ID, OID.ROOT_OFFENDER_ID, OID.ALERT_SEQ, OID.ALERT_TYPE, OID.ALERT_CODE, OID.AUTHORIZE_PERSON_TEXT , OID.CREATE_DATE, OID.ALERT_STATUS, OID.VERIFIED_FLAG, OID.EXPIRY_DATE, OID.COMMENT_TEXT, OID.CASELOAD_ID, OID.MODIFY_USER_ID, OID.MODIFY_DATETIME, OID.CASELOAD_TYPE, OID.CREATE_DATETIME, OID.CREATE_USER_ID, OID.SEAL_FLAG,(select description from reference_codes rc where code = OID.ALERT_TYPE and "domain" ='ALERT') as alert_type_description,
	(select description from reference_codes rc where code = OID.ALERT_CODE and "domain" ='ALERT_CODE') as alert_code_description FROM OFFENDER_ALERTS OID /* WHERE OFFENDER_BOOK_ID = :offenderBookId ORDER BY ALERT_STATUS DESC, EXPIRY_DATE DESC , MODIFY_DATETIME DESC*/
}
	 	 
OCDALERT_ALERT_INSERT_OFFENDER_ALERTS{
 insert into OFFENDER_ALERTS(ALERT_DATE , OFFENDER_BOOK_ID , ROOT_OFFENDER_ID , ALERT_SEQ , ALERT_TYPE , ALERT_CODE , AUTHORIZE_PERSON_TEXT , CREATE_DATE , ALERT_STATUS , VERIFIED_FLAG , EXPIRY_DATE , COMMENT_TEXT , CASELOAD_ID , MODIFY_DATETIME , CASELOAD_TYPE , CREATE_DATETIME , CREATE_USER_ID , SEAL_FLAG) 
 values(:alertDate , :offenderBookId , :rootOffenderId , :alertSeq , :alertType , :alertCode , :authorizePersonText , :createDate , :alertStatus , :verifiedFlag , :expiryDate , :commentText , :caseloadId , NULL , :caseloadType , current_timestamp , :createUserId , :sealFlag)
}   

 OCDALERT_ALERT_UPDATE_OFFENDER_ALERTS {
   UPDATE OFFENDER_ALERTS set VERIFIED_FLAG = :verifiedFlag, ALERT_DATE = :alertDate, EXPIRY_DATE = :expiryDate, ALERT_STATUS = :alertStatus, AUTHORIZE_PERSON_TEXT = :authorizePersonText, COMMENT_TEXT = :commentText, MODIFY_USER_ID =:modifyUserId, MODIFY_DATETIME=current_timestamp where OFFENDER_BOOK_ID = :offenderBookId and ALERT_SEQ = :alertSeq
 }    
  
  OCDALERT_ALERT_DELETE_OFFENDER_ALERTS{
  DELETE FROM OFFENDER_ALERTS 
  where OFFENDER_BOOK_ID = :offenderBookId and ALERT_SEQ = :alertSeq
  }    

OCDALERT_SYSPFL_FIND_SYSTEM_PROFILES{   
    SELECT SP.PROFILE_TYPE, SP.PROFILE_CODE, SP.DESCRIPTION, SP.PROFILE_VALUE, SP.PROFILE_VALUE_2, SP.MODIFY_USER_ID, SP.OLD_TABLE_NAME, SP.CREATE_DATETIME, SP.CREATE_USER_ID, SP.MODIFY_DATETIME, SP.SEAL_FLAG FROM SYSTEM_PROFILES SP      
}

OCDALERT_ALERT_PREINSERT_C{	   
	 SELECT coalesce(MAX(ALERT_SEQ),0) + 1
         FROM   OFFENDER_ALERTS
         WHERE  OFFENDER_BOOK_ID = :offenderBookId
	 }

OCDALERT_ALERT_POSTQUERY_VARIFICATION_CURR{	
	 select COUNT(0) from MODULE_PRIVILEGES M where M.ROLE_ID in ( select S.ROLE_ID from STAFF_MEMBER_ROLES S where S.STAFF_ID = ( select D.STAFF_ID from STAFF_MEMBERS D where USER_ID = upper(user)) ) and M.MODULE_NAME = :moduleName and M.VERIFICATION_FLAG = 'Y'
}
	 
OCDALERT_CGWHEN_NEW_FORM_INSTANCE_C{	 
	 SELECT  sysdate(), upper(user) from dual
}
	
OCDALERT_REFERENCE_CODES_ALERT {
SELECT REF_CODE1.CODE ,REF_CODE1.DESCRIPTION FROM REFERENCE_CODES REF_CODE1 WHERE ACTIVE_FLAG = 'Y' AND domain = 'ALERT' order by LIST_SEQ
}

OCDALERT_REFERENCE_CODES_ALERT_CODE {
select ref_code.description, ref_code.list_seq, ref_code.code code from reference_codes ref_code where ref_code.domain = 'ALERT_CODE' and ref_code.active_flag = 'Y' and ref_code.expired_date is null and ref_code.parent_code= :alertType order by ref_code.list_seq, ref_code.description
}
	 
OCDALERT_CGFKCHK_ALERT_ALERT_REF_ALERT_C{  
   SELECT REF_CODE.DESCRIPTION , REF_CODE.LIST_SEQ FROM REFERENCE_CODES REF_CODE WHERE REF_CODE.CODE = :code AND (DOMAIN = :domain AND (ACTIVE_FLAG = 'Y' OR 'N' <> 'NORMAL'))
}

OCDALERT_CGFKLKP_ALERT_ALERT_REF_ALERT_C{  
    SELECT REF_CODE.CODE, REF_CODE.LIST_SEQ FROM REFERENCE_CODES REF_CODE WHERE ((REF_CODE.CODE LIKE :code||'%' OR REF_CODE.DESCRIPTION LIKE :description||'%') OR (REF_CODE.DESCRIPTION IS NULL AND :description IS NULL )) AND REF_CODE.PARENT_CODE = :parentCode AND (DOMAIN = 'ALERT_CODE' AND (ACTIVE_FLAG = 'Y' OR :activeFlag <> 'NORMAL'))

}

OCDALERT_CGFKCHK_ALERT_ALERT_REF_AL_C {
    SELECT REF_CODE1.CODE, REF_CODE1.DESCRIPTION , REF_CODE1.LIST_SEQ FROM REFERENCE_CODES REF_CODE1 WHERE REF_CODE1.CODE = :code AND DOMAIN = :domain
}

FIND_DESCRIPTION_BY_DOMAIN {
 SELECT REF_CODE1.CODE , REF_CODE1.LIST_SEQ, REF_CODE1.DESCRIPTION FROM REFERENCE_CODES REF_CODE1 WHERE DOMAIN = :domain
}

FIND_DESCRIPTION_BY_DES_CODE_PERENTCODE{
SELECT REF_CODE1.CODE , REF_CODE1.DESCRIPTION, REF_CODE1.LIST_SEQ FROM REFERENCE_CODES REF_CODE1 WHERE DOMAIN = :domain AND ( REF_CODE1.DESCRIPTION LIKE :description || '%' OR REF_CODE1.CODE LIKE :code || '%' ) AND REF_CODE1.PARENT_CODE = :parentCode
}

OCDALERT_CGFKLKP_ALERT_ALERT_REF_AL_C{
SELECT REF_CODE1.CODE , REF_CODE1.LIST_SEQ FROM REFERENCE_CODES REF_CODE1 WHERE DOMAIN = :domain AND ((REF_CODE1.CODE LIKE :code||'%' OR REF_CODE1.DESCRIPTION = :description) OR (REF_CODE1.DESCRIPTION IS NULL)) AND (REF_CODE1.ACTIVE_FLAG = 'Y' AND REF_CODE1.EXPIRED_DATE IS NULL)
}

OCDALERT_CGUVCHK_OFF_ALERT_UK{	 
	 SELECT '1' FROM OFFENDER_ALERTS WHERE ( :rowId IS NULL OR ROW_ID <> :rowId) AND ALERT_DATE = :alertDate AND OFFENDER_BOOK_ID = :offenderBookId AND ALERT_TYPE = :alertType AND ALERT_CODE = :alertCode
}
	 
 OCDALERT_GET_BOOKING_BEGIN_DATE{	 
	SELECT BOOKING_BEGIN_DATE  
	FROM OFFENDER_BOOKINGS 
	WHERE OFFENDER_BOOK_ID = :offenderBookId
	 }
	 
FIND_CHECK_ALERT_DATATABLE_COUNT{	 
	select OFFENDER_BOOK_ID 
	from OFFENDER_BOOKINGS
	where OFFENDER_ID = :offenderId
	 }
	 
FIND_CODE_ALERT_DESCRIPTION_FROM_REFERENCE_CODES{	 
	SELECT REF_CODE1.CODE ,REF_CODE1.DESCRIPTION ,REF_CODE1.LIST_SEQ 
	FROM   REFERENCE_CODES REF_CODE1 
	WHERE DOMAIN = :domain
	 }
FIND_ALERT_STATUS_LIST{
	SELECT DISTINCT ALERT_STATUS FROM OFFENDER_ALERTS
}
FIND_WORK_FLOW_ID_COUNT {
SELECT COUNT(WORK_FLOW_ID)
          FROM WORK_FLOWS
          WHERE OBJECT_ID  = :OFFENDERBOOKID
            AND OBJECT_SEQ = :ALERTSEQ
            AND OBJECT_CODE = 'ALERT'
}
FIND_MAX_WORK_FLOW_ID {
SELECT NEXTVAL('WORK_FLOW_ID') FROM DUAL
}
 INSERT_WORK_FLOWS {
   insert into WORK_FLOWS (WORK_FLOW_ID, OBJECT_CODE, OBJECT_ID, OBJECT_SEQ, CREATE_USER_ID, create_datetime,modify_datetime) values (:workFlowId, 'ALERT', :offenderBookId, :alertSeq, :createUserId, current_timestamp,NULL) 
  }  
  INSERT_WORK_FLOW_LOGS {
   insert into work_flow_logs (Work_flow_id, Work_flow_seq, Work_action_code, Work_Action_date , Action_user_id, work_flow_status, create_date, locate_agy_loc_id , create_user_id, create_datetime,modify_datetime) values (:workFlowId, 1, 'ENT', current_timestamp, null, 'DONE', current_timestamp, null , :createUserId, current_timestamp,NULL) 
  }  
  INSERT_WORK_FLOW_LOGS_UPDATE {
   insert into WORK_FLOW_LOGS (WORK_FLOW_ID, WORK_FLOW_SEQ, WORK_ACTION_CODE, WORK_ACTION_DATE , ACTION_USER_ID, WORK_FLOW_STATUS, CREATE_DATE, LOCATE_AGY_LOC_ID , CREATE_USER_ID, create_datetime, modify_datetime) values (:workFlowId, :workFlowSeq, 'MOD', current_timestamp , null, 'DONE', current_timestamp, null , :createUserId, current_timestamp, null) 
  }  
FIND_WORK_FLOW_ID_MAX_VAL_DEL {
SELECT MAX(WORK_FLOW_ID)
         FROM WORK_FLOWS
         WHERE OBJECT_ID  = :OFFENDERBOOKID
            AND OBJECT_SEQ = :ALERTSEQ
            AND OBJECT_CODE = 'ALERT'
}
  DELETE_WORK_FLOWS_WORK_FLOW_ID {
  DELETE FROM WORK_FLOWS WHERE WORK_FLOW_ID = :workFlowId
  }  
  DELETE_WORK_FLOW_LOGS_WORK_FLOW_ID {
  DELETE FROM WORK_FLOW_LOGS WHERE WORK_FLOW_ID = :workFlowId
  }  
OCDALERT_GET_WORK_FLOW_SEQ {
select coalesce(MAX(WORK_FLOW_SEQ), 0) + 1 from WORK_FLOW_LOGS where WORK_FLOW_ID = :WORK_FLOW_ID;
}
  OCDALERT_WORK_FLOW_COMMIT {
   insert into WORK_FLOW_LOGS(WORK_FLOW_ID, WORK_FLOW_SEQ, CREATE_USER_ID, CREATE_DATE, WORK_ACTION_CODE, WORK_FLOW_STATUS, CREATE_DATETIME, MODIFY_DATETIME) 
   values(:workFlowId, :workFlowSeq, :createUserId, :createDate, :workActionCode, :workFlowStatus, current_timestamp, NULL) 
   }  
  OCDALERT_OFF_ALERT_UPDATE {
	 update OFFENDER_ALERTS set VERIFIED_FLAG = :verifiedFlag, modify_user_id =:modifyUserId, modify_datetime = current_timestamp where OFFENDER_BOOK_ID = :offenderBookId and ALERT_SEQ = :alertSeq
  }  
CHECK_WORK_ACTION_CODE {
 SELECT Count(WORK_ACTION_CODE) FROM WORK_FLOW_LOGS WHERE WORK_FLOW_ID=:WORKFLOWID
}

ALERT_TYPE_COUNT {
SELECT count(rirc.code) staff_Id, rirc.code,count(rirc.role_id) seq_Value FROM role_inaccessible_ref_codes rirc,reference_codes rcggg WHERE rirc.domain = 'ALERT' AND rirc.module_name = 'OCDALERT' AND EXISTS (SELECT 'X' FROM staff_member_roles smr WHERE smr.role_id = rirc.role_id AND smr.staff_id = (SELECT d.staff_id FROM staff_members d WHERE d.user_id = upper(user))) AND rirc.code = rcggg.code AND rirc.domain = rcggg.domain group by rirc.code order by seq_Value desc
}

STAFF_MEMBER_ROLES_COUNT {
SELECT count(0) FROM staff_member_roles s WHERE s.staff_id = (SELECT d.staff_id FROM staff_members d WHERE user_id = upper(user)) AND s.role_id IN (SELECT m.role_id FROM module_privileges m WHERE m.module_name = 'OCDALERT' AND m.access_privilege = 'A')
}

ALERT_CODE_COUNT {
select count(rirc.code) staff_Id, rirc.code, count(rirc.role_id) seq_Value from role_inaccessible_ref_codes rirc, reference_codes rcggg where rirc.domain = 'ALERT_CODE' and rirc.module_name = 'OCDALERT' and exists ( select 'X' from staff_member_roles smr where smr.role_id = rirc.role_id and smr.staff_id = ( select d.staff_id from staff_members d where d.user_id = upper(user))) and rirc.code = rcggg.code and rirc.domain = rcggg.domain group by rirc.code order by seq_Value desc
}

CHECK_ALERT_DELETE{
select oms_miscellaneous_get_profile_value ('CLIENT', 'ALERT_DEL')
}

CHECK_ALERT_CODE{
select oms_miscellaneous_get_profile_value ('CLIENT', 'ALERT_CODE')
}

OCDALERT_GET_ALERT_DETAILS{
	select alert_date, expiry_date, verified_flag from offender_alerts oa where offender_book_id  = :offenderBookId and alert_type = :alertType and alert_code = :alertCode
}

