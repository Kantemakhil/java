OMTOFFIR_OFFENDER_COMMUNITY_FILES{
SELECT OFFENDER_FILE_SEQ,OFFENDER_ID,FILE_TYPE,FILE_SUB_TYPE,OFFENDER_FILE_NUM,VOLUME_SEQ,FILE_REFERENCE,FILE_CREATE_DATE,HOLDING_AGY_LOC_ID,HOLDING_STAFF_ID,NON_OFFICER_STATUS,STORAGE,RESUBMISSION_DATE,CREATION_DATE,CREATION_USER,CASELOAD_TYPE,CLOSE_FILE_NO,TRANS_COMMENT_TEXT,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG FROM OFFENDER_COMMUNITY_FILES WHERE OFFENDER_ID=:offenderId AND OFFENDER_FILE_SEQ=:offenderFileSeq
}
OMTOFFIR_OFFENDER_FILES_TRIG{
INSERT INTO offender_files_trig (offender_file_seq, offender_id,offender_file_num, volume_seq,holding_agy_loc_id, non_officer_status,resubmission_date,create_datetime,modify_datetime,create_user_id)VALUES (:offenderFileSeq, :offenderId,:offenderFileNum, :volumeSeq, :holdingAgyLocId, :nonOfficerStatus, :resubmissionDate,current_timestamp,null,:createUserId)
}
OMTOFFIR_OFFENDER_FILES_TRIG1{
INSERT INTO offender_files_trig1( OFFENDER_FILE_SEQ, OFFENDER_ID,  OLD_HOLD_AGY_LOC_ID, NEW_HOLD_AGY_LOC_ID, OLD_HOLD_STAFF_ID, NEW_HOLD_STAFF_ID, OLD_NON_OFF_STATUS, NEW_NON_OFF_STATUS,CREATE_DATETIME,MODIFY_DATETIME,CREATE_USER_ID )VALUES(:offenderFileSeq, :offenderId,:holdingAgyLocId, :holdingAgyLocId,:holdingStaffId, :holdingStaffId,:nonOfficerStatus, :nonOfficerStatus,current_timestamp,null,:createUserId )
}