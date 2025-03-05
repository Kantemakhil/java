API_OFF_OBSERVASTION_LV_SEQ{
SELECT api_off_obs_event.NEXTVAL FROM dual
}
API_OFF_OBSERVASTION_LOGGING{
INSERT INTO api_off_obs_staging(log_id, offender_book_id, observation_type, frequency, status_code, modify_datetime, obs_period_id, log_message,create_datetime,modify_datetime,create_user_id) VALUES(logId, :offenderBookId, :observationType, :frequency, :statusCode, :modifyDatetime, :obsPeriodId, :trigEvent || ' ON TABLE ' || :tableName,:createDatetime,:modifyDatetime,:createUserId)
}