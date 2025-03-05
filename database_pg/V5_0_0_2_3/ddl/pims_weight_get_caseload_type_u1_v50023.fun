CREATE OR REPLACE FUNCTION oms_owner.pims_weight_get_caseload_type_u1(p_username text)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
     LV_CASELOAD_TYPE CASELOADS.CASELOAD_TYPE%TYPE;
     GET_CASELOAD_TYPE_CUR CURSOR FOR SELECT CASELOAD_TYPE
                                       FROM STAFF_MEMBERS SM,
                                            CASELOADS CL
                                      WHERE upper(SM.USER_ID)= upper(p_username)
                                        AND SM.WORKING_CASELOAD_ID = CL.CASELOAD_ID;
  BEGIN
     OPEN   GET_CASELOAD_TYPE_CUR;
     FETCH  GET_CASELOAD_TYPE_CUR INTO LV_CASELOAD_TYPE;
     CLOSE  GET_CASELOAD_TYPE_CUR;
     RETURN(LV_CASELOAD_TYPE);
  END;
$function$
;