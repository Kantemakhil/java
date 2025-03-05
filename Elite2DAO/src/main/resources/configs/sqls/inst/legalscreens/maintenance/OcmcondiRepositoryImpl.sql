
OCMCONDI_FIND_RGCAT {
 	SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'COM_CON_CAT'  AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , CODE
}

OCMCONDI_FIND_RGTYPE {
 	SELECT DISTINCT SENT_CALC.SENTENCE_CATEGORY CODE ,  REF_CODE.DESCRIPTION DESCRIPTION   FROM REFERENCE_CODES     REF_CODE ,        SENTENCE_CALC_TYPES SENT_CALC  WHERE REF_CODE.DOMAIN = 'CATEGORY'    AND REF_CODE.CODE = SENT_CALC.SENTENCE_CATEGORY    AND ((SENT_CALC.ACTIVE_FLAG = 'Y' )  )  ORDER BY SENTENCE_CATEGORY--OR ::MODE = 'ENTER-QUERY'
}

OCMCONDI_FIND_RGUNIT {
 	SELECT DESCRIPTION DSP_DESCRIPTION          ,CODE CONDITION_UNIT_TYPE FROM   REFERENCE_CODES WHERE DOMAIN = 'COND_UNIT' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OCMCONDI_FIND_RGSVCOBL {
 	SELECT DESCRIPTION  DSP_DESCRIPTION , CODE  FROM REFERENCE_CODES WHERE DOMAIN = 'PS_CATEGORY'  AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OCMCONDI_FIND_RGFUNCTIONTYPE {
 	SELECT  DESCRIPTION          ,CODE FROM REFERENCE_CODES WHERE DOMAIN = 'FUNCTION' AND (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCMCONDI_COMCOND_FIND_COMMUNITY_CONDITIONS {
 	SELECT COMM_CONDITION_TYPE ,COMM_CONDITION_CODE ,DESCRIPTION ,PROVISO_FLAG ,AMOUNT_REQUIRED_FLAG ,DUE_DATE_REQUIRED_FLAG ,ACTIVE_FLAG ,UPDATE_ALLOWED_FLAG ,CREATION_DATE ,CREATION_USER ,EXPIRY_DATE ,LIST_SEQ ,MAXIMUM_AMOUNT ,CONDITION_UNIT_TYPE ,CONDITION_TEXT ,CSO_FLAG ,FINANCIAL_FLAG ,SUBSTANCE_FLAG ,ALLOCATION_FLAG ,PROGRAM_FLAG ,PROGRAM_UNITS ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,PROGRAM_METHOD ,FUNCTION_TYPE ,CASE_PLAN_FLAG ,CATEGORY_TYPE ,SEAL_FLAG   FROM COMMUNITY_CONDITIONS
}

OCMCONDI_COMCOND_FILTERED_CONDS {
	SELECT comm_condition_type, comm_condition_code, description, proviso_flag, amount_required_flag, due_date_required_flag, active_flag, update_allowed_flag, creation_date, creation_user, expiry_date, list_seq, maximum_amount, condition_unit_type, condition_text, cso_flag, financial_flag, substance_flag, allocation_flag, program_flag, program_units, create_datetime, create_user_id, modify_datetime, modify_user_id, program_method, function_type, case_plan_flag, category_type, seal_flag FROM community_conditions where ACTIVE_FLAG = 'Y' and CATEGORY_TYPE=:categoryType and COMM_CONDITION_TYPE = :commConditionType and comm_condition_code not in (select COMM_CONDITION_CODE from offender_sent_conditions WHERE  sentence_seq=:sentenceSeq AND comm_condition_type=:commConditionType AND offender_book_id=:offenderBookId AND category_type = :categoryType)
}

OCMCONDI_COMCOND_INSERT_COMMUNITY_CONDITIONS {
	 insert into COMMUNITY_CONDITIONS(COMM_CONDITION_TYPE , COMM_CONDITION_CODE , DESCRIPTION , PROVISO_FLAG , AMOUNT_REQUIRED_FLAG , DUE_DATE_REQUIRED_FLAG , ACTIVE_FLAG , UPDATE_ALLOWED_FLAG , CREATION_DATE , CREATION_USER , EXPIRY_DATE , LIST_SEQ , MAXIMUM_AMOUNT , CONDITION_UNIT_TYPE , CONDITION_TEXT , CSO_FLAG , FINANCIAL_FLAG , SUBSTANCE_FLAG , ALLOCATION_FLAG , PROGRAM_FLAG , PROGRAM_UNITS , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , PROGRAM_METHOD , FUNCTION_TYPE , CASE_PLAN_FLAG , CATEGORY_TYPE , SEAL_FLAG ) values(:commConditionType , :commConditionCode , :description , :provisoFlag , :amountRequiredFlag , :dueDateRequiredFlag , :activeFlag , :updateAllowedFlag , :creationDate , :creationUser , :expiryDate , :listSeq , :maximumAmount , :conditionUnitType , :conditionText , :csoFlag , :financialFlag , :substanceFlag , :allocationFlag , :programFlag , :programUnits , CURRENT_TIMESTAMP , :createUserId , NULL , :programMethod , :functionType , :casePlanFlag , :categoryType , :sealFlag ) 
}

OCMCONDI_COMCOND_UPDATE_COMMUNITY_CONDITIONS {
	update COMMUNITY_CONDITIONS set COMM_CONDITION_TYPE = :commConditionType , COMM_CONDITION_CODE = :commConditionCode , DESCRIPTION = :description , PROVISO_FLAG = :provisoFlag , AMOUNT_REQUIRED_FLAG = :amountRequiredFlag , DUE_DATE_REQUIRED_FLAG = :dueDateRequiredFlag , ACTIVE_FLAG = :activeFlag , UPDATE_ALLOWED_FLAG = :updateAllowedFlag , CREATION_DATE = :creationDate , CREATION_USER = :creationUser , EXPIRY_DATE = :expiryDate , LIST_SEQ = :listSeq , MAXIMUM_AMOUNT = :maximumAmount , CONDITION_UNIT_TYPE = :conditionUnitType , CONDITION_TEXT = :conditionText , CSO_FLAG = :csoFlag , FINANCIAL_FLAG = :financialFlag , SUBSTANCE_FLAG = :substanceFlag , ALLOCATION_FLAG = :allocationFlag , PROGRAM_FLAG = :programFlag , PROGRAM_UNITS = :programUnits , MODIFY_DATETIME = CURRENT_TIMESTAMP , MODIFY_USER_ID = :modifyUserId , PROGRAM_METHOD = :programMethod , FUNCTION_TYPE = :functionType , CASE_PLAN_FLAG = :casePlanFlag , CATEGORY_TYPE = :categoryType , SEAL_FLAG = :sealFlag where COMM_CONDITION_TYPE = :commConditionType and COMM_CONDITION_CODE = :commConditionCode and CATEGORY_TYPE = :categoryType
}

OCMCONDI_COMCOND_DELETE_COMMUNITY_CONDITIONS{ 
	DELETE FROM COMMUNITY_CONDITIONS  where COMM_CONDITION_TYPE  = :commConditionType AND COMM_CONDITION_CODE  = :commConditionCode AND CATEGORY_TYPE  = :categoryType
}

OCMCONDI_DELETEORNOT_TABLE_CONDITIONS{
SELECT ac1.table_name table_name FROM all_constraints ac1, all_constraints ac2 WHERE ac1.constraint_type = 'R' AND ac2.owner = UPPER (COALESCE ('OMS_OWNER', 'OMS_OWNER')) AND ac2.constraint_name = ac1.r_constraint_name AND ac2.owner = ac1.r_owner AND ac2.constraint_type IN ('P', 'U') AND ac2.table_name = UPPER ('COMMUNITY_CONDITIONS') AND UPPER('COMM_CONDITION_TYPE') IN ( SELECT u1.column_name FROM all_cons_columns u1 WHERE ac2.constraint_name = u1.constraint_name AND ac2.table_name = u1.table_name AND ac2.owner = u1.owner ) AND UPPER('COMM_CONDITION_CODE') IN ( SELECT u2.column_name FROM all_cons_columns u2 WHERE ac2.constraint_name = u2.constraint_name AND ac2.table_name = u2.table_name AND ac2.owner = u2.owner ) 
}

OCMCONDI_DELETE_COUNT_CONDITIONS{
SELECT COUNT(*) FROM :tableName WHERE COMM_CONDITION_TYPE = :commConditionType AND COMM_CONDITION_CODE = :commConditionCode
}
OCMCONDI_CONSTRAINT_VALIDATIONS{
SELECT AC1.TABLE_NAME FROM ALL_CONSTRAINTS AC1, ALL_CONSTRAINTS AC2 WHERE AC1.CONSTRAINT_NAME = :CONSTRAINTNAME AND AC2.TABLE_NAME = 'COMMUNITY_CONDITIONS' AND AC1.CONSTRAINT_TYPE = 'R' AND AC2.CONSTRAINT_NAME = AC1.R_CONSTRAINT_NAME AND AC2.OWNER = AC1.R_OWNER AND AC2.CONSTRAINT_TYPE IN ('P', 'U')
}

