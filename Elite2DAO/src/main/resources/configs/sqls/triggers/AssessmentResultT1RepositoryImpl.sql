ASSESSMENT_RESULT_T1_GET{
SELECT ASSESSMENT_ID,SUPERVISION_LEVEL_TYPE,LIST_SEQ,EXPIRY_DATE,UPDATE_ALLOWED_FLAG,ACTIVE_FLAG,CELL_SHARING_ALERT_FLAG,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_IDSEAL_FLAG FROM ASSESSMENT_RESULTS WHERE ASSESSMENT_ID=:assessmentId AND SUPERVISION_LEVEL_TYPE=:supervisionLevelType 
}