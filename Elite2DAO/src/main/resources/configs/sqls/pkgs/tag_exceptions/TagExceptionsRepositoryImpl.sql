TAG_EXCEPTIONS_INSERT{
--INSERT INTO TAG_ERROR_LOGS(Tag_Error_Id,Sid,Module_Name,Procedure_Name,Error_Message,Error_Location,Modify_User_Id,Modify_Datetime,user_message,user_module,user_location ,user_error_code ) 
--VALUES(:tagErrorId,:sid,:moduleName,:procedureName,:errorMsg,:errorPlace,:createUserId,:modifyDatetime,:userMessage,:userModule,:userLocation ,:userErrorCode )
insert into TAG_ERROR_LOGS(Tag_Error_Id, Sid, Module_Name, Procedure_Name, Error_Message, Error_Location, CREATE_USER_ID, Modify_Datetime, user_message, user_module, user_location , user_error_code, CREATE_DATETIME) values(:tagErrorId, :sid, :moduleName, :procedureName, :errorMessage, :errorLocation, :createUserId, CURRENT_TIMESTAMP, :userMessage, :userModule, :userLocation , :userErrorCode , CURRENT_TIMESTAMP)
}
TAG_EXCEPTIONS_TAG_ERROR_ID{
--select TAG_ERROR_ID.NEXTVAL from Dual;
select NEXTVAL('TAG_ERROR_ID') from Dual
}
TAG_EXCEPTIONS_INSERT_EXCEPTION{
--INSERT INTO TAG_ERROR_LOGS(TAG_ERROR_ID,Sid,Module_Name,Procedure_Name,Error_Message,Error_Location,Modify_User_Id,Modify_Datetime) 
--VALUES(:tagErrorId,:sid,:moduleName,:procedureName,:errorMsg,:errorPlace,:createUserId,:modifyDatetime)
insert into TAG_ERROR_LOGS(TAG_ERROR_ID, Sid, Module_Name, Procedure_Name, Error_Message, Error_Location, CREATE_USER_ID, Modify_Datetime, CREATE_DATETIME) values(:tagErrorId, :sid, :moduleName, :procedureName, :errorMsg, :errorPlace, :createUserId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
}
TAG_EXCEPTIONS_SID_CUR{
SELECT pg_backend_pid() from dual;

}
TAG_EXCEPTIONS_MODULE_NAME_CUR{
SELECT Module_Name FROM TRUST_AUDITS_TMP WHERE Sid = :sid
}
TAG_EXCEPTIONS_SYSTEM_PROFILES_CUR{
SELECT Profile_Value FROM SYSTEM_PROFILES  WHERE Profile_Type = 'CLIENT' AND Profile_Code = 'FINAN_AUDIT'
}