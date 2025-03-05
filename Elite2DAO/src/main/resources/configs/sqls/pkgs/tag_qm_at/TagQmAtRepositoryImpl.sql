TAG_QM_AT_QM_ACTIVITIES_INSERT{
INSERT INTO QM_ACTIVITIES (ACTIVITY_ID,NAME,DESCRIPTION, ALLOCATED_TIME,ACTIVE_FLAG,EXPIRY_DATE,PROCESS_ID,SEQUENCE,create_datetime,modify_datetime,create_user_id)VALUES (:activityId,:name,:description,:qmcaAllocatedTime,:activeFlag,:expiryDate,:processId,  :sequence,:createDatetime,:modifyDatetime,:createUserId)
}
TAG_QM_AT_QM_CON_ACTIVITY_TEAMS_INSERT{
INSERT INTO QM_CON_ACTIVITY_TEAMS(MAN_ACT_TEAM_ID,COMPOSITION_ID, ACTIVITY_ID,TEAM_ID, ALLOCATED_TIME,ACTIVE_FLAG, EXPIRY_DATE,create_datetime,modify_datetime,create_user_id)VALUES (MAN_ACT_TEAM_ID_SEQ.NEXTVAL,:compositionId,:activityId,:teamId, :qmcaAllocatedTime, :activeFlag,:expiryDate,:createDatetime,:modifyDatetime,:createUserId)
}
TAG_QM_AT_QM_ACTIVITIES_UPDATE{
UPDATE QM_ACTIVITIES SET SEQUENCE = :sequence,ALLOCATED_TIME = :qmcaAllocatedTime,ACTIVE_FLAG    = :activeFlag, EXPIRY_DATE    = :expiryDate,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId WHERE ACTIVITY_ID = :activityId
}
TAG_QM_AT_QM_CON_ACTIVITY_TEAMS_UPDATE{
UPDATE QM_CON_ACTIVITY_TEAMS SET ALLOCATED_TIME = :qmcaAllocatedTime,TEAM_ID   = :teamId,ACTIVE_FLAG = :activeFlag,  EXPIRY_DATE = :expiryDate,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId  WHERE MAN_ACT_TEAM_ID = :manActTeamId
}
TAG_QM_ATACTIVITY_ID_CUR{
SELECT ACTIVITY_ID_SEQ.NEXTVAL FROM DUAL
}