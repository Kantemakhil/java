OFFENDER_ASSESSMENTS_TWF1_OFFENDER_ASSESSMENTS_GET{
SELECT OFFENDER_BOOK_ID,ASSESSMENT_SEQ,ASSESSMENT_DATE,ASSESSMENT_TYPE_ID,SCORE,ASSESS_STATUS,CALC_SUP_LEVEL_TYPE,ASSESS_STAFF_ID,ASSESS_COMMENT_TEXT,OVERRIDE_REASON_TEXT,PLACE_AGY_LOC_ID,OVERRIDED_SUP_LEVEL_TYPE,OVERRIDE_COMMENT_TEXT,OVERRIDE_STAFF_ID,EVALUATION_DATE,NEXT_REVIEW_DATE,EVALUATION_RESULT_CODE,REVIEW_SUP_LEVEL_TYPE,REVIEW_PLACEMENT_TEXT,REVIEW_COMMITTE_CODE,COMMITTE_COMMENT_TEXT,REVIEW_PLACE_AGY_LOC_ID,REVIEW_SUP_LEVEL_TEXT,MODIFY_USER_ID,ASSESS_COMMITTE_CODE,CREATION_DATE,CREATION_USER,APPROVED_SUP_LEVEL_TYPE,ASSESSMENT_CREATE_LOCATION,ASSESSOR_STAFF_ID,MODIFY_DATETIME,OVERRIDE_USER_ID,OVERRIDE_REASON,CREATE_DATETIME,CREATE_USER_ID,SEAL_FLAG FROM OFFENDER_ASSESSMENTS WHERE OFFENDER_BOOK_ID=:offenderBookId AND ASSESSMENT_SEQ=:assessmentSeq
}
OFFENDER_ASSESSMENTS_TWF1_CUR_ASSESSMENT_TYPE{
SELECT assessment_id FROM assessments WHERE assessment_class = 'TYPE'AND assessment_code = 'TPS_T1'
}
CUR_INMATE_IS_AT_RISK{
SELECT  'Y' FROM offender_assessment_items WHERE offender_book_id = pOffenderBookId
    AND assessment_seq = :pAssessmentSeq
    AND assessment_id IN (SELECT assessment_id  FROM ( SELECT assessment_id, SYS_CONNECT_BY_PATH(assessment_code, '/') answer_path
    FROM assessments START WITH  assessment_id = :pAssessmentTypeId
     CONNECT BY PRIOR assessment_id = :pAssessmentTypeId ) WHERE answer_path IN ( '/TPS_T1/GHW/MEDCOND/Y', '/TPS_T1/GHW/ATTSUI/Y', '/TPS_T1/GHW/SUI2MO/Y', '/TPS_T1/GHW/SUINOW/Y',  '/TPS_T1/GHW/DISPEN/Y'  ) )
}