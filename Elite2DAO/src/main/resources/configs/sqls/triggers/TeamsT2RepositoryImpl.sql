TEaMS_T2_SELECT{
select profile_value from system_profiles where profile_type = 'SYS'and profile_code = 'Q_CLUSTER_ID'
}
TEAMS_T2_MOD{
SELECT MOD(:TEAM_ID::NUMERIC ,:LV_PROFILE_VALUE::NUMERIC) + 1  FROM DUAL
}