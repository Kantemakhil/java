OFFENDER_EXTERNAL_MOVEMENTS_T3_GET_LOCATION_CUR{
SELECT description FROM agency_locations WHERE agy_loc_id = :agyLocId AND deactivation_date IS NULL
}
OFFENDER_EXTERNAL_MOVEMENTS_T3_COMMUNITY_ACTIVE_CUR{
SELECT *  FROM offender_bookings  WHERE community_active_flag = 'Y' AND offender_id = (SELECT offender_id   FROM offender_bookings  WHERE offender_book_id = :offenderBookId)
}
OFFENDER_EXTERNAL_MOVEMENTS_T3_OFFENDER_EXTERNAL_MOVEMENTS_GET{
SELECT OFFENDER_BOOK_ID,MOVEMENT_SEQ,MOVEMENT_DATE,MOVEMENT_TIME,INTERNAL_SCHEDULE_TYPE,INTERNAL_SCHEDULE_REASON_CODE,MOVEMENT_TYPE,MOVEMENT_REASON_CODE,DIRECTION_CODE,ARREST_AGENCY_LOC_ID,TO_PROV_STAT_CODE,ESCORT_CODE,FROM_AGY_LOC_ID,TO_AGY_LOC_ID,ACTIVE_FLAG,ESCORT_TEXT,COMMENT_TEXT,REPORTING_DATE,TO_CITY,FROM_CITY,REPORTING_TIME,EVENT_ID,PARENT_EVENT_ID,TO_COUNTRY_CODE,OJ_LOCATION_CODE,APPLICATION_DATE,APPLICATION_TIME,TO_ADDRESS_ID,FROM_ADDRESS_ID,SEAL_FLAG,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID FROM OFFENDER_EXTERNAL_MOVEMENTS WHERE OFFENDER_BOOK_ID =:offenderBookId AND MOVEMENT_SEQ=:movementSeq
}