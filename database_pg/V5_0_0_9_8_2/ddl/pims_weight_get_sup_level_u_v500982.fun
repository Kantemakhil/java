CREATE OR REPLACE FUNCTION oms_owner.pims_weight_get_sup_level_u(p_offender_book_id bigint, p_username text)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE
    GET_MAX_ASS_SEQ_CUR CURSOR(P_CASELOAD_TYPE VARCHAR) FOR
                                  SELECT MAX(OFF_ASS.ASSESSMENT_SEQ)
                                    FROM OFFENDER_ASSESSMENTS OFF_ASS,
                                         ASSESSMENTS ASS
                                   WHERE OFF_ASS.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
                                   
                                     AND OFF_ASS.ASSESSMENT_TYPE_ID = ASS.ASSESSMENT_ID
                                     --AND ASS.CASELOAD_TYPE = P_CASELOAD_TYPE
                                     AND ASS.DETERMINE_SUP_LEVEL_FLAG ='Y'
                                     AND OFF_ASS.REVIEW_SUP_LEVEL_TYPE IS NOT NULL;
    GET_SUP_LEVEL_CUR CURSOR(CP_ASS_SEQ   DOUBLE PRECISION) FOR
                                  SELECT OFF_ASS.REVIEW_SUP_LEVEL_TYPE
                                    FROM OFFENDER_ASSESSMENTS OFF_ASS
                                   WHERE OFF_ASS.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
                                     AND OFF_ASS.ASSESSMENT_SEQ = CP_ASS_SEQ;
    LV_SUPLEVEL        REFERENCE_CODES.CODE%TYPE;
    LV_ASSESSMENT_SEQ  OFFENDER_ASSESSMENTS.ASSESSMENT_SEQ%TYPE;
    LV_CASELOAD_TYPE   CASELOADS.CASELOAD_TYPE%TYPE;
    LV_DET_FLAG        ASSESSMENTS.DETERMINE_SUP_LEVEL_FLAG%TYPE;
  BEGIN
    LV_CASELOAD_TYPE := PIMS_WEIGHT_GET_CASELOAD_TYPE_U1(p_username);
    
    OPEN  GET_MAX_ASS_SEQ_CUR(LV_CASELOAD_TYPE);
    FETCH GET_MAX_ASS_SEQ_CUR  INTO LV_ASSESSMENT_SEQ;
    CLOSE GET_MAX_ASS_SEQ_CUR;
    
    OPEN  GET_SUP_LEVEL_CUR(LV_ASSESSMENT_SEQ);
    FETCH GET_SUP_LEVEL_CUR INTO LV_SUPLEVEL;
    CLOSE GET_SUP_LEVEL_CUR;
    RETURN LV_SUPLEVEL;
    
  END;
$function$;