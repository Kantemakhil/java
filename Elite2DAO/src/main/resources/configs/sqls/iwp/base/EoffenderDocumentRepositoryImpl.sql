UPDATE_DOCUMENT_NAME { 
UPDATE iwp_documents SET document_name =:documentName,COMMENT_TEXT=:commentText,modify_datetime = current_timestamp,modify_user_id = :modifyUserId where DOCUMENT_ID=:documentId
}

GET_USER_MODULE_ACCESS { 
	SELECT 
		MP.MODULE_NAME, 
		MP.ACCESS_PRIVILEGE, 
		MP.ROLE_ID, 
		MP.VERIFICATION_FLAG        
 	FROM MODULE_PRIVILEGES MP,	
          STAFF_MEMBER_ROLES SMR,	
          STAFF_MEMBERS SM	
	WHERE SM.USER_ID = :USER_ID	
      AND SM.STAFF_ID = SMR.STAFF_ID	
      AND SMR.ROLE_ID = MP.ROLE_ID 
      AND MP.MODULE_NAME = :MODULE_NAME
	ORDER BY MP.ACCESS_PRIVILEGE DESC
} 

GET_EOFFENDER_FILELIMITS { 
select profile_code ,profile_value from system_profiles sp where profile_code In('EOF_SIZE_LIM','EOF_NO_LIM')
}