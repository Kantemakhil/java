INSERT INTO TEAM_AGENCY_LOC(TEAM_ID ,AGY_LOC_ID ,CREATE_DATETIME ,CREATE_USER_ID , modify_datetime, modify_user_id, ACTIVE_FLAG )
select (SELECT team_id FROM OMS_OWNER.automation_teams WHERE team_code = 'PRISONER PAY TEAM'),'HRP',current_timestamp,'OMS_OWNER', current_timestamp,'OMS_OWNER','Y'
where NOT EXISTS (SELECT 1 FROM oms_owner.TEAM_AGENCY_LOC WHERE team_id = (SELECT team_id FROM OMS_OWNER.automation_teams WHERE team_code = 'PRISONER PAY TEAM')
and AGY_LOC_ID = 'HRP');