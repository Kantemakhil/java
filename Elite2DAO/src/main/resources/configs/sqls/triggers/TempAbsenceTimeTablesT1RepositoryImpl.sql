TEMP_ABSENCE_TIME_TABLES_T1_LV_TEMP_ABS_SCH_ID{
SELECT temp_abs_sch_id.NEXTVAL FROM DUAL
}
TEMP_ABSENCE_TIME_TABLES_T1_GET{
SELECT TA_ID,TA_TT_SEQ,OUT_DATE,OUT_TIME,IN_DATE,IN_TIME,AGY_LOC_ID,CITY_CODE,CANCEL_REASON_CODE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,TEMP_ABS_SCH_ID,SEAL_FLAG FROM TEMP_ABSENCE_TIME_TABLES WHERE TA_ID=:taId AND TA_TT_SEQ=:taTtSeq
}