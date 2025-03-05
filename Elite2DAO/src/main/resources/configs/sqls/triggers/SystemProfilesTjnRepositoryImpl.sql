 INSERT_QUERY{
  insert into SYSTEM_PROFILES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, PROFILE_TYPE, PROFILE_CODE, DESCRIPTION , PROFILE_VALUE, PROFILE_VALUE_2, MODIFY_USER_ID, CREATE_USER_ID , CREATE_DATETIME, MODIFY_DATETIME , OLD_TABLE_NAME ) values ('INS', :createUserId, current_timestamp, null, null, 0, :profileType, :profileCode, :description , :profileValue, :profileValue2, :modifyUserId, :createUserId , :createDateTime, :modifyDateTime , :oldTableName )
 }
 UPDATE_QUERY{
 insert into SYSTEM_PROFILES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, PROFILE_TYPE, PROFILE_CODE , DESCRIPTION, PROFILE_VALUE, PROFILE_VALUE_2 , MODIFY_USER_ID, CREATE_USER_ID , CREATE_DATETIME, MODIFY_DATETIME, OLD_TABLE_NAME ) values ('UPD', :createUserId, current_timestamp, null, null, 0, :profileType, :profileCode, :description , :profileValue, :profileValue2, :modifyUserId, :createUserId , :createDateTime, :modifyDateTime , :oldTableName )
  }
 DELETE_QUERY{
 insert into SYSTEM_PROFILES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, PROFILE_TYPE, PROFILE_CODE , DESCRIPTION, PROFILE_VALUE, PROFILE_VALUE_2 , MODIFY_USER_ID, CREATE_USER_ID , CREATE_DATETIME, MODIFY_DATETIME, OLD_TABLE_NAME ) values ('DEL', :createUserId, current_timestamp, null, null, 0, :profileType, :profileCode, :description , :profileValue, :profileValue2, :modifyUserId, :createUserId , :createDateTime, :modifyDateTime , :oldTableName )
 }