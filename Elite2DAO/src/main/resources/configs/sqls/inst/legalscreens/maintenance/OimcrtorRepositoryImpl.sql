
OIMCRTOR_FIND_RGCATEGORY {
SELECT
    description,
    code
FROM
    reference_codes
WHERE
    domain = 'ORD_CATEGORY'
    AND ( active_flag = 'Y'  )   AND CODE <> 'AUTO' ORDER BY LIST_SEQ , DESCRIPTION

}

OIMCRTOR_FIND_RGTEMPLATE {
 	SELECT TEMPLATE_NAME CODE, DESCRIPTION,TEMPLATE_ID  FROM IWP_TEMPLATES  WHERE ACTIVE_FLAG = 'Y'    AND OBJECT_TYPE = 'IWP' ORDER BY TEMPLATE_NAME ASC
}

OIMCRTOR_GET_TMEPLATE_CODE {
 	SELECT TEMPLATE_NAME FROM IWP_TEMPLATES WHERE  TEMPLATE_ID = :templateId AND  ACTIVE_FLAG = 'Y' AND OBJECT_TYPE   = 'IWP'
}

OIMCRTOR_GET_TMEPLATE_ID {
 	SELECT TEMPLATE_ID FROM IWP_TEMPLATES WHERE  TEMPLATE_NAME = :templateName AND  ACTIVE_FLAG = 'Y' AND OBJECT_TYPE   = 'IWP'
}

OIMCRTOR_ORDERTYPES_FIND_ORDER_TYPES {
 	SELECT ORDER_TYPE ,DESCRIPTION ,YOUTH_ORDER_FLAG ,CUSTODY_FLAG ,TIME_SENSITIVE_FLAG ,ACTIVE_FLAG ,LIST_SEQ ,UPDATE_ALLOWED_FLAG ,EXPIRY_DATE ,CREATE_USER_ID ,SCHEDULE_FLAG ,CHARGES_FLAG ,SEVERITY_RANK ,CASELOAD_TYPE ,NO_OF_HOLD_DAYS ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,ORDER_CATEGORY ,CUSTODY_DAYS ,SEAL_FLAG   FROM ORDER_TYPES 
}
OIMCRTOR_ORDERTYPES_INSERT_ORDER_TYPES {
 insert into ORDER_TYPES(ORDER_TYPE , DESCRIPTION , YOUTH_ORDER_FLAG , CUSTODY_FLAG , TIME_SENSITIVE_FLAG , ACTIVE_FLAG , LIST_SEQ , UPDATE_ALLOWED_FLAG , EXPIRY_DATE , CREATE_USER_ID , SCHEDULE_FLAG , CHARGES_FLAG , SEVERITY_RANK , CASELOAD_TYPE , NO_OF_HOLD_DAYS , CREATE_DATETIME , MODIFY_DATETIME , ORDER_CATEGORY , CUSTODY_DAYS , SEAL_FLAG ) values(:orderType , :description , :youthOrderFlag , :custodyFlag , :timeSensitiveFlag , :activeFlag , :listSeq , :updateAllowedFlag , :expiryDate , :createUserId , :scheduleFlag , :chargesFlag , :severityRank , :caseloadType , :noOfHoldDays , current_timestamp , NULL , :orderCategory , :custodyDays , :sealFlag ) 
}

OIMCRTOR_ORDERTYPES_UPDATE_ORDER_TYPES {
 update ORDER_TYPES set ORDER_TYPE = :orderType , DESCRIPTION = :description , YOUTH_ORDER_FLAG = :youthOrderFlag , CUSTODY_FLAG = :custodyFlag , TIME_SENSITIVE_FLAG = :timeSensitiveFlag , ACTIVE_FLAG = :activeFlag , LIST_SEQ = :listSeq , UPDATE_ALLOWED_FLAG = :updateAllowedFlag , EXPIRY_DATE = :expiryDate , SCHEDULE_FLAG = :scheduleFlag , CHARGES_FLAG = :chargesFlag , SEVERITY_RANK = :severityRank , CASELOAD_TYPE = :caseloadType , NO_OF_HOLD_DAYS = :noOfHoldDays , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , ORDER_CATEGORY = :orderCategory , CUSTODY_DAYS = :custodyDays , SEAL_FLAG = :sealFlag where ORDER_TYPE = :orderType
}

OIMCRTOR_IWPTEMPLATEOBJECTS_FIND_IWP_TEMPLATE_OBJECTS {
SELECT
    iwp_template_object_id,
    template_id,
    object_type,
    object_code,
    active_flag,
    expiry_date,
    list_seq,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id,
    seal_flag
FROM
    iwp_template_objects
WHERE
    object_type = 'ORDER'
    AND object_code = :objectcode
ORDER BY
    case active_flag  when 'Y' then  1  when 'N' then  2 end,
  ( SELECT description
            FROM   iwp_templates
            WHERE  template_id =:p_template_id)
    
    
}
OIMCRTOR_IWPTEMPLATEOBJECTS_INSERT_IWP_TEMPLATE_OBJECTS {
 insert into IWP_TEMPLATE_OBJECTS(IWP_TEMPLATE_OBJECT_ID , TEMPLATE_ID , OBJECT_TYPE , OBJECT_CODE , ACTIVE_FLAG , EXPIRY_DATE , LIST_SEQ , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:iwpTemplateObjectId , :templateId , :objectType , :objectCode , :activeFlag , :expiryDate , :listSeq , current_timestamp , :createUserId , NULL , :sealFlag ) 
}

OIMCRTOR_IWPTEMPLATEOBJECTS_UPDATE_IWP_TEMPLATE_OBJECTS {
 update IWP_TEMPLATE_OBJECTS set IWP_TEMPLATE_OBJECT_ID = :iwpTemplateObjectId , TEMPLATE_ID = :templateId , OBJECT_TYPE = :objectType , OBJECT_CODE = :objectCode , ACTIVE_FLAG = :activeFlag , EXPIRY_DATE = :expiryDate , LIST_SEQ = :listSeq , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where IWP_TEMPLATE_OBJECT_ID = :iwpTemplateObjectId
}

OIMCRTOR_IWPTEMPLATEOBJECTS_DELETE_IWP_TEMPLATE_OBJECTS { 
	DELETE FROM IWP_TEMPLATE_OBJECTS where IWP_TEMPLATE_OBJECT_ID  = :iwpTemplateObjectId
}


OIMCRTOR_ORDER_TYPES_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM IWP_TEMPLATE_OBJECTS I WHERE I.OBJECT_CODE = :ORDERTYPE
}

OIMCRTOR_IWP_TEMP_OBJ_ONCHECKDELETEMASTER {
	SELECT COUNT(*) FROM IWP_DOCUMENTS I WHERE I.template_id = :templateId
}

OIMCRTOR_GET_NEXT_IWPID {
  select NEXTVAL('iwp_template_object_id')
  }