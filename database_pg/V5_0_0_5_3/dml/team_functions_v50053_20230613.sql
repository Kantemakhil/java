INSERT INTO TEAM_FUNCTIONS(TEAM_ID ,FUNCTION_TYPE ,CREATE_DATETIME ,CREATE_USER_ID , modify_datetime, modify_user_id, SEAL_FLAG ) 
select (SELECT team_id FROM OMS_OWNER.automation_teams WHERE team_code = 'PRISONER PAY TEAM'),'PRISONER PAY',current_timestamp,'OMS_OWNER', current_timestamp,'OMS_OWNER', null
where NOT EXISTS (SELECT 1 FROM oms_owner.TEAM_FUNCTIONS WHERE team_id = (SELECT team_id FROM OMS_OWNER.automation_teams WHERE team_code = 'PRISONER PAY TEAM')
and FUNCTION_TYPE = 'PRISONER PAY');
