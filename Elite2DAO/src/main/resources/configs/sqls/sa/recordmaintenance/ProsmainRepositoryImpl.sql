PROSMAIN_EXECUTE_QUERY {
select
	A.PROCESS_ID,
	A.PROCESS_KEY,
	A.DEPLOYE_ID,
	A.PROC_DEF_ID,
	A.PROCESS_DESC,
	A.BPMN_FILE,
	A.DEF_VERSION,
	A.CREATE_DATETIME,
	A.CREATE_USER_ID,
	A.MODIFY_DATETIME,
	A.MODIFY_USER_ID,
	A.DEPLOY_FLAG,
	A.HISTORY_FLAG,
	A.DEPLOY_DATETIME,
	A.DEPLOY_USER_ID,
	A.TRIGGER_ID,
	A.COMMON_PROCESS,
	A.CATEGORY,
	A.TIMER_PROCESS
from
	(
	select
		Process_key,
		max(def_version) max_def_version
	from
		BPMN_PROCESS
	where
		def_version is not null
	group by
		Process_key)B,
	BPMN_PROCESS A
where
	A.def_version = B.max_def_version
	and
  A.Process_key = B.Process_key
union all 
select
	A.PROCESS_ID,
	A.PROCESS_KEY,
	A.DEPLOYE_ID,
	A.PROC_DEF_ID,
	A.PROCESS_DESC,
	A.BPMN_FILE,
	A.DEF_VERSION,
	A.CREATE_DATETIME,
	A.CREATE_USER_ID,
	A.MODIFY_DATETIME,
	A.MODIFY_USER_ID,
	A.DEPLOY_FLAG,
	A.HISTORY_FLAG,
	A.DEPLOY_DATETIME,
	A.DEPLOY_USER_ID,
	A.TRIGGER_ID,
	A.COMMON_PROCESS,
	A.CATEGORY,
	A.TIMER_PROCESS
from
	BPMN_PROCESS A
where
	process_key is null
order by
	DEPLOY_DATETIME desc nulls last,
	MODIFY_DATETIME desc nulls last

}

PROSMAIN_INSERT_PROCESS {
	insert
	into
	BPMN_PROCESS(PROCESS_ID,
	PROCESS_DESC,
	DEPLOY_FLAG,
	HISTORY_FLAG,
	COMMON_PROCESS,
	CREATE_DATETIME,
	CREATE_USER_ID,
	CATEGORY)
values(NEXTVAL('BPMN_PROCESS_PROCESS_ID_SEQ'),
:processDesc,
'N',
'N',
:commonProcess,
CURRENT_TIMESTAMP,
:createUserId,
:category)
}

PROSMAIN_UPDATE_PROCESS {
	UPDATE BPMN_PROCESS SET BPMN_FILE=:bpmnByte, TRIGGER_ID=:triggerId, DEPLOY_FLAG = 'Y', HISTORY_FLAG = 'N', COMMON_PROCESS = :commonProcess, PROCESS_KEY = :processKey, DEF_VERSION = :defVersion, TIMER_PROCESS = :timerProcess,MODIFY_DATETIME = :modifyDatetime, MODIFY_USER_ID = :modifyUserId where PROCESS_ID=:processId
}

PROSMAIN_UPDATE_PROCESS2 {
	UPDATE BPMN_PROCESS SET BPMN_FILE=:bpmnByte, TRIGGER_ID=:triggerId, DEPLOY_FLAG = 'Y', HISTORY_FLAG = 'N', COMMON_PROCESS = :commonProcess, TIMER_PROCESS = :timerProcess, MODIFY_DATETIME = :modifyDatetime, MODIFY_USER_ID = :modifyUserId where DEF_VERSION = :defVersion AND PROCESS_KEY = :processKey
}

PROSMAIN_INSERT_PROCESS2 {
	insert
	into
	BPMN_PROCESS(PROCESS_ID,
	PROCESS_KEY,
	PROCESS_DESC,
	BPMN_FILE,
	TRIGGER_ID,
	COMMON_PROCESS,
	DEF_VERSION,
	DEPLOY_FLAG,
	HISTORY_FLAG,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	CATEGORY,
	TIMER_PROCESS)
values (NEXTVAL('BPMN_PROCESS_PROCESS_ID_SEQ'),
:processKey,
:processDesc,
:bpmnByte,
:triggerId,
:commonProcess,
:defVersion,
'Y',
'Y',
CURRENT_TIMESTAMP,
:createUserId,
null,
null,
:category,
:timerProcess)
}

PROSMAIN_DELETE_PROCESS {
 	DELETE FROM TABLE   /* where  */
}

PROSMAIN_UPDATE_DEPLOYE_ID {
	UPDATE BPMN_PROCESS SET PROCESS_KEY = :processKey, DEPLOYE_ID = :deployeId, PROC_DEF_ID = :procDefId, DEPLOY_FLAG = 'N', HISTORY_FLAG = 'Y', DEPLOY_DATETIME =CURRENT_TIMESTAMP, DEPLOY_USER_ID = :deployUserId where PROCESS_ID=:processId 
}

PROSMAIN_PRE_INSERT {
 	SELECT DEPLOYE_ID FROM BPMN_PROCESS WHERE PROCESS_ID = :processId
}

PROSMAIN_PRE_UPDATE {
	SELECT MAX(DEF_VERSION) FROM BPMN_PROCESS WHERE PROCESS_KEY = :processKey
}

PROSMAIN_GET_BPMN_PROCESS {
	SELECT * FROM BPMN_PROCESS WHERE PROCESS_ID = :processId
}

PROSMAIN_GET_VERSION_HISTORY {
	SELECT * FROM BPMN_PROCESS WHERE PROCESS_KEY = :processKey order by DEF_VERSION desc
}

GET_PROCESS_INSTANCE_BPMN {
select
  PROCESS_ID, PROCESS_KEY, DEPLOYE_ID,PROCESS_DESC, BPMN_FILE, DEF_VERSION, DEPLOY_FLAG, HISTORY_FLAG,
  CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, DEPLOY_DATETIME, DEPLOY_USER_ID, TRIGGER_ID, COMMON_PROCESS
from BPMN_PROCESS where DEPLOYE_ID=:deployId
}

PROSMAIN_GET_PROC_DEF_ID{
	SELECT PROC_DEF_ID FROM BPMN_PROCESS WHERE DEF_VERSION = (SELECT MAX(DEF_VERSION) FROM BPMN_PROCESS WHERE PROCESS_KEY = :processKey) AND PROCESS_KEY = :processKey
}

PROSMAIN_GET_INSERT_PROCESS{
	SELECT PROCESS FROM WORK_ITEMS WHERE ADD_TRIGGER = 'Y' AND TRIGGER_ID = :triggerId
}

PROSMAIN_GET_UPDATE_PROCESS{
	SELECT PROCESS FROM WORK_ITEMS WHERE UPDATE_TRIGGER = 'Y' AND TRIGGER_ID = :triggerId
}

PROSMAIN_GET_DELETE_PROCESS{
	SELECT PROCESS FROM WORK_ITEMS WHERE DELETE_TRIGGER = 'Y' AND TRIGGER_ID = :triggerId
}

PROSMAIN_GET_PROCESS_KEY{
	SELECT PROCESS_KEY FROM BPMN_PROCESS WHERE PROCESS_ID = :processId
}

PROSMAIN_GET_BY_PROC_DESC{
	SELECT PROCESS_ID, PROCESS_KEY, CATEGORY, TIMER_PROCESS,DEPLOYE_ID, PROCESS_DESC, DEPLOY_FLAG, HISTORY_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, PROC_DEF_ID, DEF_VERSION, TRIGGER_ID, COMMON_PROCESS, DEPLOY_DATETIME, DEPLOY_USER_ID FROM BPMN_PROCESS, (SELECT MAX(PROCESS_ID) PID, PROCESS_DESC PDESC FROM BPMN_PROCESS WHERE PROCESS_DESC = :processDesc GROUP BY PROCESS_DESC) BPG WHERE BPG.PID = PROCESS_ID
}

GET_PROCESS_DATA {
select
  PROCESS_ID, PROCESS_KEY, CATEGORY,TIMER_PROCESS,DEPLOYE_ID,PROCESS_DESC, BPMN_FILE, DEF_VERSION, DEPLOY_FLAG, HISTORY_FLAG,
  CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, DEPLOY_DATETIME, DEPLOY_USER_ID, TRIGGER_ID, COMMON_PROCESS
from BPMN_PROCESS where PROCESS_ID=:processId
}

PROSMAIN_UPDATE_TRIGGER_ID {
	UPDATE BPMN_PROCESS SET TRIGGER_ID = null WHERE PROCESS_ID = :processId
}

GET_CAMUNDA_SERVER_URL { 
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'CAMUNDA_URL'
}
GET_APP_SERVER_URL { 
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'APP_SER_URL'
}
SAVE_CAMUNDA_ERR {
	INSERT INTO AUTOMATION_ERROR_LOG(error_data, process_key, process_id) Values(:errorData, :processKey, :processId)
}
PROSMAIN_BULK_INSERT_PROCESS {
	insert
	into
	BPMN_PROCESS(PROCESS_ID,
	PROCESS_DESC,
	PROCESS_KEY,
	BPMN_FILE,
	DEPLOY_FLAG,
	HISTORY_FLAG,
	DEF_VERSION,
	TRIGGER_ID,
	COMMON_PROCESS,
	CREATE_DATETIME,
	MODIFY_DATETIME,
	CREATE_USER_ID,
	MODIFY_USER_ID,
	CATEGORY,
	TIMER_PROCESS)
values(NEXTVAL('BPMN_PROCESS_PROCESS_ID_SEQ'),
:processDesc,
:processKey,
:bpmnFile,
'Y',
'N',
:defVersion,
:triggerId,
:commonProcess,
CURRENT_TIMESTAMP,
null,
:createUserId,
null,
:category,
:timerProcess)
}
PROSMAIN_GET_COMMON_PROCESS {
	select A.PROCESS_KEY as code, A.PROCESS_DESC as description from (select PROCESS_KEY, max(def_version) max_def_version from BPMN_PROCESS where def_version is not null and common_process = 'Y' group by PROCESS_KEY)B, BPMN_PROCESS A where A.def_version=B.max_def_version and A.PROCESS_KEY=B.PROCESS_KEY
}
PROSMAIN_GET_WORKID_BY_TRIGGERID {
	select work_id from work_items where trigger_id = :triggerId
}
PROSMAIN_BULK_PRE_UPDATE {
	DELETE FROM bpmn_process where process_id = :processId and process_key is null
}
UPDATE_PROCESS_CATEGORY {
	UPDATE BPMN_PROCESS SET CATEGORY = :category,MODIFY_DATETIME = :modifyDatetime, MODIFY_USER_ID = :modifyUserId where PROCESS_ID=:processId 
}

