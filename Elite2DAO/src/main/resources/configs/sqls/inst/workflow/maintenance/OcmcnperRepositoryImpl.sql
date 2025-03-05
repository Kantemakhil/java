CASE_NOTE_PERMISSIONS_INSERTING{
insert into CASE_NOTE_PERMISSIONS(ROLE_ID, WORK_ID, CREATE_FLAG , VIEW_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) values (:roleId, :workId, :createFlag, :viewFlag, current_timestamp , :createUserId , null , :sealFlag ) 
}

CASE_NOTE_PERMISSIONS_UPDATING{
update CASE_NOTE_PERMISSIONS set CREATE_FLAG=:createFlag,VIEW_FLAG=:viewFlag,modify_user_id =:modifyUserId, modify_datetime = current_timestamp, seal_flag = :sealFlag where ROLE_ID=:roleId and WORK_ID=:workId
}

CASE_NOTE_PERMISSIONS_COUNT_RECORDS{
select COUNT(*) from CASE_NOTE_PERMISSIONS where ROLE_ID =:roleId and work_id =:workId

}

OCMCNPER_CASE_NOTE_PERMISSIONS_EXECUTE_QUERRY{

select w.work_id,w.workflow_type,w.work_type,w.work_sub_type,w.caseload_type, (select create_flag from CASE_NOTE_PERMISSIONS where role_id = :roleId and work_id = w.work_id) as create_flag,
(select view_flag from CASE_NOTE_PERMISSIONS where role_id = :roleId and work_id = w.work_id) as view_flag from works w where W.WORKfLOW_TYPE = 'CNOTE'
}
