GET_MAX_ASS_SEQ_CUR{
SELECT MAX(OFF_ASS.ASSESSMENT_SEQ) FROM OFFENDER_ASSESSMENTS OFF_ASS, ASSESSMENTS ASS WHERE OFF_ASS.OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND OFF_ASS.ASSESSMENT_TYPE_ID = ASS.ASSESSMENT_ID AND ASS.DETERMINE_SUP_LEVEL_FLAG ='Y' AND OFF_ASS.REVIEW_SUP_LEVEL_TYPE IS NOT NULL
}

GET_CASELOAD_TYPE_CUR{
--SELECT CASELOAD_TYPE FROM STAFF_MEMBERS SM, CASELOADS CL WHERE SM.USER_ID = USER AND SM.WORKING_CASELOAD_ID = CL.CASELOAD_ID
SELECT CASELOAD_TYPE FROM STAFF_MEMBERS SM, CASELOADS CL WHERE SM.USER_ID = :createUserId AND SM.WORKING_CASELOAD_ID = CL.CASELOAD_ID
}

GET_SUP_LEVEL_CUR{
SELECT OFF_ASS.REVIEW_SUP_LEVEL_TYPE FROM OFFENDER_ASSESSMENTS OFF_ASS WHERE OFF_ASS.OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND OFF_ASS.ASSESSMENT_SEQ = :CP_ASS_SEQ
}

WORK_OFFICER_USERENV{
--SELECT USERENV('SESSIONID') FROM   SYS.DUAL
 SELECT USERENV_number('SESSIONID')
       FROM   DUAL
}

WORK_OFFICER_OFFASS_CUR{
--SELECT book_id , charge_seq , line , component , order_type  , order_code FROM staff_work_assignments_v1 WHERE agy_loc_id =:p_agy_loc_id AND cal_agy_loc_id =:p_agy_loc_id AND sac_staff_id =:p_staff_id AND position =:p_position AND ROLE =:p_role AND from_date =:p_from_date AND status IS NULL AND
--TRUNC(NVL(to_date(order_req_expiry),SYSDATE)) >= TRUNC(SYSDATE) AND NOT EXISTS ( SELECT 1 FROM assignment_transfers ass WHERE ass.offass_id = staff_work_assignments_v1.offass_id AND ass.status_to IS NULL AND ass.sac_staff_id IS NULL ) ORDER BY offender_book_id , order_type , order_code , component
select
	book_id ,
	charge_seq ,
	line ,
	component ,
	order_type ,
	order_code
from
	staff_work_assignments_v1
where
	agy_loc_id =:p_agy_loc_id
	and cal_agy_loc_id =:p_agy_loc_id
	and sac_staff_id =:p_staff_id
	and position =:p_position
	and role =:p_role
	and from_date =:p_from_date
	and status is null
	and (coalesce ((order_req_expiry) , current_timestamp)) >= (current_timestamp)
	and not exists (
	select
		1
	from
		assignment_transfers ass
	where
		ass.offass_id = staff_work_assignments_v1.offass_id
		and ass.status_to is null
		and ass.sac_staff_id is null )
order by
	offender_book_id ,
	order_type ,
	order_code ,
	component
}

SELECT_WEIGHTING{
SELECT weighting FROM pot_weightings WHERE order_type = :p_order_type AND order_code = :p_order_code AND component = :p_component AND ( offender_danger_rating = :lv_sup_level OR offender_danger_rating IS NULL )
AND ( range_code = :lv_time_served OR range_code IS NULL ) AND active = 'Y'
}

INSERT_TEMP_WEIGHTINGS{
/* INSERT INTO temp_weightings ( tmp_wei_id , book_id , order_type , order_code , component , weighting , session_id )
VALUES ( :tmpWeiId ,:bookId ,:orderType : orderCode ,:component ,:weighting , :sessionId ) */
insert into temp_weightings ( tmp_wei_id , book_id , order_type , order_code , component , weighting , session_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :tmpWeiId , :bookId , :orderType , :orderCode , :component , :weighting , :sessionId , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

ALL_RECS_CUR{
SELECT tmp_wei_id , book_id , order_type , order_code , component , weighting , calculated_weighting , overridden_by , ROWID FROM temp_weightings
WHERE session_id =:lv_session_id AND overridden_by IS NULL ORDER BY book_id , weighting DESC
}

CON_CUR_WEIGHTING{
	SELECT order_type , order_code , order_type_concurrent_to , order_code_concurrent_to FROM pot_concurrencies WHERE ( order_type =:p_order_type AND order_code =:p_order_code )
	OR ( order_type_concurrent_to =:p_order_type AND order_code_concurrent_to =:p_order_code )
}

CON_TMP_WEI_CUR{
SELECT weighting , tmp_wei_id , overridden_by FROM temp_weightings WHERE book_id =:p_book_id AND order_type =:p_order_code AND order_code =:p_order_type 
AND component =:p_component AND session_id =:lv_session_id
}

LV_OVER_WEIGHTING{
SELECT weighting FROM temp_weightings  WHERE  tmp_wei_id =:overridden_by  AND session_id =:lv_session_id                      
}

UPDATE_TEMP_WEIGHTINGS_ONE{
-- UPDATE temp_weightings SET calculated_weighting = :weighting WHERE  ROWID  = :ROWID
update temp_weightings set calculated_weighting = :weighting, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where ROWID = :ROWID
}

UPDATE_TEMP_WEIGHTINGS_TWO{
--  UPDATE temp_weightings SET calculated_weighting = 0 , overridden_by =:tmp_wei_id WHERE  tmp_wei_id =:overridden_by  AND session_id =:lv_session_id
update temp_weightings set calculated_weighting = 0 , overridden_by =:tmp_wei_id, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where tmp_wei_id =:overridden_by and session_id =:lv_session_id
}

UPDATE_TEMP_WEIGHTINGS_THREE{
-- UPDATE temp_weightings SET calculated_weighting = 0 , overridden_by = :overridden_by WHERE ROWID = :ROWID
update temp_weightings set calculated_weighting = 0 , overridden_by = :overridden_by, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where ROWID = :ROWID
}

UPDATE_TEMP_WEIGHTINGS_FOUR{
-- UPDATE temp_weightings SET calculated_weighting = :weighting WHERE ROWID = :ROWID
update temp_weightings set calculated_weighting = :weighting, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where ROWID = :ROWID
}

UPDATE_TEMP_WEIGHTINGS_FIVE{
-- UPDATE temp_weightings SET calculated_weighting = 0 , overridden_by =:ALL_TMP_WEI_ID WHERE tmp_wei_id = :CON_TMP_WEI_ID AND session_id = :LV_SESSION_ID
update temp_weightings set calculated_weighting = 0 , overridden_by =:ALL_TMP_WEI_ID, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where tmp_wei_id = :CON_TMP_WEI_ID and session_id = :LV_SESSION_ID
}

UPDATE_TEMP_WEIGHTINGS_SIX{
-- UPDATE temp_weightings SET calculated_weighting = 0 , overridden_by =:tmp_wei_id WHERE ROWID =:ROWID
update temp_weightings set calculated_weighting = 0 , overridden_by =:tmp_wei_id, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where ROWID =:ROWID
}

UPDATE_TEMP_WEIGHTINGS_SEVEN{
-- UPDATE temp_weightings SET calculated_weighting =:weighting WHERE ROWID =:ROWID
update temp_weightings set calculated_weighting =:weighting, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where ROWID =:ROWID
}

SELECT_CALCULATED_WEIGHTING{
SELECT SUM(calculated_weighting) FROM temp_weightings WHERE session_id =:LV_SESSION_ID
}











