LOV_IDENTIFIRE_TYPE{
	SELECT ref_code.description,
	ref_code.code
	FROM reference_codes ref_code
	WHERE domain = 'CASE_ID_TYPE'
	AND ((active_flag = 'Y'
	AND expired_date IS NULL))
	ORDER BY ref_code.list_seq, ref_code.code
}

GRID_CASE_IDENTIFIRE{
	select IDENTIFIER_TYPE,IDENTIFIER_NO 
    from offender_case_identifiers
    where CASE_ID = :caseId
}

INSERT_IDENTEFIER_DATA {
  INSERT INTO OFFENDER_CASE_IDENTIFIERS (IDENTIFIER_TYPE,CASE_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, IDENTIFIER_NO)
                                  VALUES(:type, :caseId, :createDateTime, :createUserId, :modifyDateTime, :modifyUserId, :number  )    
}

UPDATE_IDENTEFIER_DATA {
   UPDATE OFFENDER_CASE_IDENTIFIERS SET IDENTIFIER_TYPE= :type, IDENTIFIER_NO= :number WHERE CASE_ID= :caseId and IDENTIFIER_TYPE= :oldType and IDENTIFIER_NO= :oldNumber
}

DELETE_IDENTEFIER_DATA {
	DELETE FROM OFFENDER_CASE_IDENTIFIERS WHERE IDENTIFIER_TYPE= :type AND IDENTIFIER_NO= :number
}