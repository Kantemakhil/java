insert into OMS_OWNER.automation_teams
(team_id, team_code, description, active_flag, expiry_date,create_datetime, create_user_id, modify_datetime, modify_user_id)
select nextval('AUTOMATION_TEAMS_team_id'),'PRISONER PAY TEAM','PRISONER PAY TEAM','Y',null,current_timestamp,'OMS_OWNER', current_timestamp,'OMS_OWNER' 
WHERE NOT EXISTS (SELECT 1 FROM OMS_OWNER.automation_teams WHERE team_code = 'PRISONER PAY TEAM');