WORK_FLOW_INSERT_L_WORKFLOW_CUR {
 SELECT WORK_FLOW_ID FROM WORK_FLOWS WHERE OBJECT_CODE = :P_FROM AND OBJECT_ID = :P_EVENT_ID AND OBJECT_SEQ = 1
}

WORK_FLOW_INSERT_INSERT_WORK_FLOWS {
--	INSERT INTO work_flows ( work_flow_id, object_code, object_id, object_seq ) VALUES ( :workFlowId, :objectCode, :objectId, 1)
insert into work_flows ( work_flow_id, object_code, object_id, object_seq, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :workFlowId, :objectCode, :objectId, 1, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}

WORK_FLOW_INSERT_GET_WORK_FLOW_ID {
	SELECT WORK_FLOW_ID.NEXTVAL FROM DUAL
}


WORK_FLOW_INSERT_L_WORK_SEQ {
	SELECT coalesce (MAX (WORK_FLOW_SEQ), 0) + 1 FROM WORK_FLOW_LOGS WHERE WORK_FLOW_ID = :P_WORK_FLOW_ID
}

WORK_FLOW_INSERT_GET_STAFF_ID {
	SELECT staff_id FROM staff_members WHERE user_id = USER

}

WORK_FLOW_INSERT_WORK_FLOW_LOGS {
/*	INSERT INTO WORK_FLOW_LOGS ( WORK_FLOW_ID, WORK_FLOW_SEQ, WORK_ACTION_CODE, WORK_ACTION_DATE, ACTION_USER_ID, WORK_FLOW_STATUS, CREATE_DATE, CREATE_USER_ID )
         VALUES (:workFlowId, :workFlowSeq, :workActionCode,  SYSDATE , :actionUserId, 'DONE', SYSDATE, USER) */
insert into WORK_FLOW_LOGS ( WORK_FLOW_ID, WORK_FLOW_SEQ, WORK_ACTION_CODE, WORK_ACTION_DATE, ACTION_USER_ID, WORK_FLOW_STATUS, CREATE_DATE, CREATE_USER_ID , CREATE_DATETIME, MODIFY_DATETIME ) values (:workFlowId, :workFlowSeq, :workActionCode, CURRENT_TIMESTAMP , :actionUserId, 'DONE', CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)

}

WORK_FLOW_INSERT_L_MOD_ACTION_CUR {
 SELECT WORK_ACTION_CODE, CREATE_USER_ID FROM WORK_FLOW_LOGS WHERE WORK_FLOW_ID = :P_WORK_FLOW_ID ORDER BY CREATE_DATE DESC 
}

WORK_FLOW_INSERT_UPDATE_COURT_EVENTS {
-- UPDATE COURT_EVENTS SET VERIFICATION_INDICATOR_FLAG = 'N' WHERE EVENT_ID = :P_EVENT_ID 
update COURT_EVENTS set VERIFICATION_INDICATOR_FLAG = 'N', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where EVENT_ID = :P_EVENT_ID
}

Event_Charges_Insert{
-- INSERT INTO court_event_charges (event_id, offender_charge_id, plea_code, most_serious_flag, property_value, total_property_value, no_of_offences, offence_date, offence_range_date, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, result_code_1, result_code_1_indicator) SELECT :p_event_id, offender_charge_id, plea_code, most_serious_flag, property_value, total_property_value, no_of_offences, offence_date, offence_range_date, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, :p_outcome_reason_code, :p_result_code_1_ind FROM offender_charges WHERE offender_book_id = :p_offender_book_id AND case_id = :p_case_id AND charge_status = 'A'
insert into court_event_charges (event_id, offender_charge_id, plea_code, most_serious_flag, property_value, total_property_value, no_of_offences, offence_date, offence_range_date, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, result_code_1, result_code_1_indicator, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select :p_event_id, offender_charge_id, plea_code, most_serious_flag, property_value, total_property_value, no_of_offences, offence_date, offence_range_date, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, :p_outcome_reason_code, :p_result_code_1_ind, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP from offender_charges where offender_book_id = :p_offender_book_id and case_id = :p_case_id and charge_status = 'A'
}

GET_ORDERS_DETAILS {
SELECT 'X'FROM ORDERS  WHERE OFFENDER_BOOK_ID = :offenderBookId  AND EVENT_ID =:eventId AND ORDER_TYPE = 'AUTO'
}
GET_AGY_LOC_ID_EVENT_DATE {
SELECT AGY_LOC_ID, EVENT_DATE FROM COURT_EVENTS WHERE EVENT_ID = :eventId 
}
INSERT_INTO_ORDERS {
-- INSERT INTO ORDERS (ORDER_ID, OFFENDER_BOOK_ID, CASE_ID, COURT_DATE, ORDER_TYPE, ISSUING_AGY_LOC_ID, ORDER_STATUS, EVENT_ID) VALUES (:order_id.NEXTVAL, :offenderBookId, :caseId, :eventDate, 'AUTO', :agyLocId, 'A', :eventId) 
insert into ORDERS (ORDER_ID, OFFENDER_BOOK_ID, CASE_ID, COURT_DATE, ORDER_TYPE, ISSUING_AGY_LOC_ID, ORDER_STATUS, EVENT_ID, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:order_id.NEXTVAL, :offenderBookId, :caseId, :eventDate, 'AUTO', :agyLocId, 'A', :eventId, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}


Get_Condition_Desc{
 SELECT description FROM offender_sent_conditions osc, community_conditions cc WHERE osc.comm_condition_type = cc.comm_condition_type AND osc.comm_condition_code = cc.comm_condition_code AND osc.comm_condition_type = :P_COM_TYPE AND osc.comm_condition_code = :P_COM_CODE AND osc.offender_book_id = :P_OFF_BKG_ID 
}

NEXT_EVENTS_INSERT_GET_BOOKING_START_DATE {
 SELECT BOOKING_BEGIN_DATE FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID 
}

NEXT_EVENTS_INSERT_GET_GET_LATEST_MOVEMENT{
 SELECT (MOVEMENT_DATE + (MOVEMENT_TIME - TRUNC(MOVEMENT_TIME))) FROM OFFENDER_EXTERNAL_MOVEMENTS WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND MOVEMENT_SEQ = (SELECT MAX(MOVEMENT_SEQ) FROM OFFENDER_EXTERNAL_MOVEMENTS WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID) 
}

NEXT_EVENTS_INSERT_EVENT_ID{
	SELECT EVENT_ID.NEXTVAL FROM DUAL
}
NEXT_EVENTS_INSERT_INSER_INTO_COURT_EVENTS {
-- INSERT INTO COURT_EVENTS (OFFENDER_BOOK_ID, CASE_ID, EVENT_ID, EVENT_DATE, START_TIME, COURT_EVENT_TYPE, AGY_LOC_ID, EVENT_STATUS, DIRECTION_CODE) VALUES (:offenderBookId, :caseId, :eventId, :eventDate, TO_DATE(TO_CHAR(:eventDate, 'DD/MM/YYYY') || TO_CHAR(:startTime, 'HH24:MI'), 'DD/MM/YYYY HH24:MI'), :courtEventType, :agyLocId, :eventStatus, 'OUT' )
insert into COURT_EVENTS (OFFENDER_BOOK_ID, CASE_ID, EVENT_ID, EVENT_DATE, START_TIME, COURT_EVENT_TYPE, AGY_LOC_ID, EVENT_STATUS, DIRECTION_CODE, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:offenderBookId, :caseId, :eventId, :eventDate, TO_DATE(TO_CHAR(:eventDate, 'DD/MM/YYYY') || TO_CHAR(:startTime, 'HH24:MI'), 'DD/MM/YYYY HH24:MI'), :courtEventType, :agyLocId, :eventStatus, 'OUT', :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

NEXT_EVENTS_INSERT_INSER_INTO_COURT_EVENT_CHARGES {
-- INSERT INTO COURT_EVENT_CHARGES (EVENT_ID, OFFENDER_CHARGE_ID, PLEA_CODE, MOST_SERIOUS_FLAG, PROPERTY_VALUE, TOTAL_PROPERTY_VALUE, NO_OF_OFFENCES, OFFENCE_DATE, OFFENCE_RANGE_DATE, CJIT_OFFENCE_CODE_1, CJIT_OFFENCE_CODE_2, CJIT_OFFENCE_CODE_3, RESULT_CODE_1, RESULT_CODE_1_INDICATOR) SELECT :P_EVENT_ID, OFFENDER_CHARGE_ID, PLEA_CODE, MOST_SERIOUS_FLAG, PROPERTY_VALUE, TOTAL_PROPERTY_VALUE, NO_OF_OFFENCES, OFFENCE_DATE, OFFENCE_RANGE_DATE, CJIT_OFFENCE_CODE_1, CJIT_OFFENCE_CODE_2, CJIT_OFFENCE_CODE_3, :P_OUTCOME_REASON_CODE, :P_RESULT_CODE_1_IND FROM OFFENDER_CHARGES WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND CASE_ID = :P_CASE_ID AND CHARGE_STATUS = 'A' 
insert into COURT_EVENT_CHARGES (EVENT_ID, OFFENDER_CHARGE_ID, PLEA_CODE, MOST_SERIOUS_FLAG, PROPERTY_VALUE, TOTAL_PROPERTY_VALUE, NO_OF_OFFENCES, OFFENCE_DATE, OFFENCE_RANGE_DATE, CJIT_OFFENCE_CODE_1, CJIT_OFFENCE_CODE_2, CJIT_OFFENCE_CODE_3, RESULT_CODE_1, RESULT_CODE_1_INDICATOR, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select :P_EVENT_ID, OFFENDER_CHARGE_ID, PLEA_CODE, MOST_SERIOUS_FLAG, PROPERTY_VALUE, TOTAL_PROPERTY_VALUE, NO_OF_OFFENCES, OFFENCE_DATE, OFFENCE_RANGE_DATE, CJIT_OFFENCE_CODE_1, CJIT_OFFENCE_CODE_2, CJIT_OFFENCE_CODE_3, :P_OUTCOME_REASON_CODE, :P_RESULT_CODE_1_IND, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP from OFFENDER_CHARGES where OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID and CASE_ID = :P_CASE_ID and CHARGE_STATUS = 'A'
}
INSERT_OFFENDER_CHARGES{
-- INSERT INTO offender_charges (offender_book_id, offender_charge_id, statute_code, offence_code, offence_date, offence_range_date, plea_code, property_value, total_property_value, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, charge_status, result_code_1, result_code_2, result_code_1_indicator, result_code_2_indicator, no_of_offences, case_id, most_serious_flag, offence_type) VALUES (:offenderBookId, :offenderChargeId, :statuteCode, :offenceCode, :offenceDate, :offenceRangeDate, :pleaCode, :propertyValue, :totalPropertyValue, :cjitOffenceCode1, :cjitOffenceCode2, :cjitOffenceCode3, :chargeStatus, :resultCode1, :resultCode2, :resultCode1Indicator, :resultCode2Indicator, :noOfOffences, :caseId, :mostSeriousFlag, :offenceType) 
insert into offender_charges (offender_book_id, offender_charge_id, statute_code, offence_code, offence_date, offence_range_date, plea_code, property_value, total_property_value, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, charge_status, result_code_1, result_code_2, result_code_1_indicator, result_code_2_indicator, no_of_offences, case_id, most_serious_flag, offence_type, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:offenderBookId, :offenderChargeId, :statuteCode, :offenceCode, :offenceDate, :offenceRangeDate, :pleaCode, :propertyValue, :totalPropertyValue, :cjitOffenceCode1, :cjitOffenceCode2, :cjitOffenceCode3, :chargeStatus, :resultCode1, :resultCode2, :resultCode1Indicator, :resultCode2Indicator, :noOfOffences, :caseId, :mostSeriousFlag, :offenceType, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}  

GET_OFFENDER_CHARGE_ID{
select OFFENDER_CHARGE_ID.nextval from dual
}

INSERT_COURT_EVENT_CHARGES{
insert into court_event_charges (event_id, offender_charge_id, plea_code, result_code_1, result_code_2, result_code_1_indicator, result_code_2_indicator, property_value, most_serious_flag, no_of_offences, total_property_value, offence_date, offence_range_date, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:eventId, coalesce(:vOffenderChargeId, :offenderChargeId), :pleaCode, :resultCode1, :resultCode2, :resultCode1Indicator, :resultCode2Indicator, :propertyValue, :mostSeriousFlag, :noOfOffences, :totalPropertyValue, :offenceDate, :offenceRangeDate, :cjitOffenceCode1, :cjitOffenceCode2, :cjitOffenceCode3, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

SENTENCE_SEQ_QUERY{
SELECT p_offender_book_id offender_book_id, p_sentence_seq sentence_seq, osc.offender_charge_id offender_charge_id, offense_code_description(off_chg.offence_code, off_chg.statute_code) offence_description, to_number(to_char(coalesce(osc.modify_datetime, osc.create_datetime), 'DDHH24MISSFF4')) check_sum FROM offender_charges off_chg, offender_sentence_charges osc WHERE osc.offender_book_id = :p_offender_book_id AND osc.sentence_seq = :p_sentence_seq AND osc.offender_charge_id = off_chg.offender_charge_id          
}

UPDATE_OFFENDER_CASES{
update offender_cases set agy_loc_id =:P_AGY_LOC_ID, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where case_id =:P_CASE_ID    
}

UPDATE_ORDER_COURT_DATE{
update orders set court_date = :P_EVENT_DATE, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :P_OFFENDER_BOOK_ID and event_id = :P_EVENT_ID and order_type = 'AUTO'
}
OFFENCES_QUERY{
select
	OFFENDER_BOOK_ID,
	EVENT_ID,
	CEC.OFFENDER_CHARGE_ID,
	CASE_ID,
	OFF_CHG.OFFENCE_CODE,
	OFF_CHG.STATUTE_CODE,
	CEC.MOST_SERIOUS_FLAG,
	CEC.PLEA_CODE,
	CEC.PROPERTY_VALUE,
	CEC.OFFENCE_DATE,
	CEC.OFFENCE_RANGE_DATE,
	CEC.RESULT_CODE_1,
	CEC.RESULT_CODE_1_INDICATOR,
	CEC.RESULT_CODE_2,
	CEC.RESULT_CODE_2_INDICATOR,
	CEC.CJIT_OFFENCE_CODE_1,
	CEC.CJIT_OFFENCE_CODE_2,
	CEC.CJIT_OFFENCE_CODE_3,
	CEC.NO_OF_OFFENCES,
	CEC.TOTAL_PROPERTY_VALUE,
	OFF_CHG.CHARGE_STATUS,
	TO_NUMBER(TO_CHAR(coalesce(CEC.MODIFY_DATETIME ::date, CEC.CREATE_DATETIME::date), 'DDHH24MISSFF4'::text), '9G999g999'::text) CHECK_SUM,
	OFF_CHG.OFFENCE_TYPE OFFENCE_TYPE
from
	OFFENDER_CHARGES OFF_CHG,
	COURT_EVENT_CHARGES CEC
where
	CEC.EVENT_ID = :P_EVENT_ID
	and CEC.OFFENDER_CHARGE_ID = OFF_CHG.OFFENDER_CHARGE_ID
	and ((coalesce(:P_OFFENCE_CODE,'') = '' 
		and coalesce(:P_STATUTE_CODE,'') = '' )
	or (OFF_CHG.OFFENCE_CODE = :P_OFFENCE_CODE
		and OFF_CHG.STATUTE_CODE = :P_STATUTE_CODE))
	and (CEC.PLEA_CODE = :P_PLEA_CODE
		or coalesce(:P_PLEA_CODE,'') = '')
	and (CEC.OFFENCE_DATE = :P_OFFENCE_DATE
		or coalesce(:P_OFFENCE_DATE,'1000-01-01') = '1000-01-01' )
	and (CEC.OFFENCE_RANGE_DATE = :P_OFFENCE_RANGE_DATE
		or coalesce(:P_OFFENCE_RANGE_DATE,'1000-01-01') = '1000-01-01')
	and (CEC.RESULT_CODE_1 = :P_RESULT_CODE_1
		or coalesce(:P_RESULT_CODE_1,'') = '')
	and (CEC.RESULT_CODE_2 = :P_RESULT_CODE_2
		or coalesce(:P_RESULT_CODE_2,'') = '')
	and (OFF_CHG.OFFENCE_TYPE = :P_OFFENCE_TYPE
		or coalesce(:P_OFFENCE_TYPE,'') = '')
  }    
  
  DESCRIPTION_CUR_ONE{
   SELECT DESCRIPTION
           FROM OFFENCES
          WHERE OFFENCE_CODE = :P_OFFENCE_CODE
            AND STATUTE_CODE = :P_STATUTE_CODE
   }
   
  DESCRIPTION_CUR_SECOND{ 
    SELECT DESCRIPTION
           FROM OFFENCE_RESULT_CODES
          WHERE RESULT_CODE = :P_RESULT_CODE
}
 
GET_LINKCASE_DETAILS{
 SELECT CASE_SEQ, OFF_CASES.BEGIN_DATE START_DATE, AGY_LOC.DESCRIPTION AGY_LOC, REF_CODE.DESCRIPTION, OFF_CASES.CASE_INFO_PREFIX INFO_PREFIX, OFF_CASES.CASE_INFO_NUMBER INFO_NUMBER, CASE_ID FROM OFFENDER_CASES OFF_CASES, AGENCY_LOCATIONS AGY_LOC, REFERENCE_CODES REF_CODE WHERE OFF_CASES.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID AND OFF_CASES.CASE_TYPE = REF_CODE.CODE(+) AND DOMAIN(+) = 'LEG_CASE_TYP' AND OFF_CASES.CASE_SEQ NOT IN (:P_CASE_SEQ) AND OFF_CASES.OFFENDER_BOOK_ID = (:P_OFFENDER_BOOK_ID) AND OFF_CASES.CASE_ID = (:P_COMBINED_CASE_ID) ORDER BY OFF_CASES.BEGIN_DATE
}

GET_OFF_CHARGES{
SELECT OFFENDER_BOOK_ID, OFFENDER_CHARGE_ID, STATUTE_CODE, OFFENCE_CODE, NO_OF_OFFENCES, OFFENCE_DATE, OFFENCE_RANGE_DATE,
PLEA_CODE, PROPERTY_VALUE, TOTAL_PROPERTY_VALUE, CJIT_OFFENCE_CODE_1, CJIT_OFFENCE_CODE_2, CJIT_OFFENCE_CODE_3, CHARGE_STATUS,
CREATE_USER_ID, MODIFY_USER_ID, MODIFY_DATETIME, CREATE_DATETIME, RESULT_CODE_1, RESULT_CODE_2, RESULT_CODE_1_INDICATOR,
RESULT_CODE_2_INDICATOR, CASE_ID, MOST_SERIOUS_FLAG, CHARGE_SEQ, ORDER_ID, LIDS_OFFENCE_NUMBER, OFFENCE_TYPE, SEAL_FLAG 
FROM OFFENDER_CHARGES A WHERE OFFENDER_BOOK_ID = :P_OFF_BOOK_ID AND CASE_ID = :L_CASE_ID AND CHARGE_STATUS = 'A' AND 
coalesce(RESULT_CODE_1_INDICATOR, 'I') NOT IN ( 'P', 'F' )
}

GET_LINK_DETAILS{
 SELECT CASE_ID  FROM LINK_CASE_TXNS WHERE COMBINED_CASE_ID = :P_CASE_ID
}


UPDATE_OFFENDER_CASES{
update OFFENDER_CASES set COMBINED_CASE_ID = :P_CASE_ID1, CASE_STATUS = :PCASESTATUS, STATUS_UPDATE_REASON = 'LINKED', STATUS_UPDATE_DATE = CURRENT_TIMESTAMP, STATUS_UPDATE_STAFF_ID = :PSTAFFID, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where CASE_ID = :P_CASE_ID
 }      
 
 GET_ACTIVE_TYPE{
 SELECT ACTIVE_TYPE  FROM LEGAL_UPDATE_REASONS   WHERE UPDATE_REASON_CODE = :P_REASON_CODE
 }      

INSERT_INTO_LINK_CASE_TXNS_ONE {
insert into LINK_CASE_TXNS (CASE_ID, COMBINED_CASE_ID, OFFENDER_CHARGE_ID, EVENT_ID, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:P_CASE_ID, :P_CASE_ID1, :POFFENDER_CHARGE_ID, :P_EVENT_ID, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP) 
}
 
 
 INSERT_INTO_COURT_EVENT_CHARGES{
 insert into COURT_EVENT_CHARGES (EVENT_ID, OFFENDER_CHARGE_ID, PLEA_CODE, RESULT_CODE_1, RESULT_CODE_2, RESULT_CODE_1_INDICATOR, RESULT_CODE_2_INDICATOR, MOST_SERIOUS_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, PROPERTY_VALUE, TOTAL_PROPERTY_VALUE, NO_OF_OFFENCES, OFFENCE_DATE, OFFENCE_RANGE_DATE) values (:P_EVENT_ID, :OFFENDER_CHARGE_ID, :PLEA_CODE, :RESULT_CODE_1, :RESULT_CODE_2, :RESULT_CODE_1_INDICATOR, :RESULT_CODE_2_INDICATOR, :MOST_SERIOUS_FLAG, :CREATE_DATETIME, :CREATE_USER_ID, :MODIFY_DATETIME, :PROPERTY_VALUE, :TOTAL_PROPERTY_VALUE, :NO_OF_OFFENCES, :OFFENCE_DATE, :OFFENCE_RANGE_DATE)
 }
 
 INSERT_INTO_LINK_CASE_TXNS_SECOND{
 insert into LINK_CASE_TXNS (CASE_ID, COMBINED_CASE_ID, OFFENDER_CHARGE_ID, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:P_CASE_ID, :P_CASE_ID1, :POFFENDER_CHARGE_ID, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
 }
 
 UPDATE_OFFENDER_CHARGES{
 update OFFENDER_CHARGES set CASE_ID = :P_CASE_ID1, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where CASE_ID = :P_CASE_ID and OFFENDER_BOOK_ID = :P_OFF_BOOK_ID
 }
 
 GET_CHARGE_DETAILS{
 SELECT OFFENDER_BOOK_ID, OFFENDER_CHARGE_ID, STATUTE_CODE, OFFENCE_CODE, NO_OF_OFFENCES, OFFENCE_DATE, OFFENCE_RANGE_DATE, PLEA_CODE, PROPERTY_VALUE,
 TOTAL_PROPERTY_VALUE, CJIT_OFFENCE_CODE_1, CJIT_OFFENCE_CODE_2, CJIT_OFFENCE_CODE_3, CHARGE_STATUS, CREATE_USER_ID, MODIFY_USER_ID, MODIFY_DATETIME,
 CREATE_DATETIME, RESULT_CODE_1, RESULT_CODE_2, RESULT_CODE_1_INDICATOR, RESULT_CODE_2_INDICATOR, CASE_ID, MOST_SERIOUS_FLAG, CHARGE_SEQ, ORDER_ID,
 LIDS_OFFENCE_NUMBER, OFFENCE_TYPE, SEAL_FLAG FROM OFFENDER_CHARGES WHERE OFFENDER_BOOK_ID = :P_OFF_BOOK_ID AND OFFENDER_CHARGE_ID = :L_CHARGE_ID AND
 CASE_ID = :P_CASE_ID
 }
 SELECT_EVENT_CHARGES{
         SELECT cec.offender_charge_id
           FROM court_event_charges cec,
                offender_charges    oc,
                court_events        ce
          WHERE cec.offender_charge_id = oc.offender_charge_id
            AND oc.case_id = ce.case_id
            AND cec.event_id = ce.event_id
            AND cec.event_id =:p_event_id
            AND cec.result_code_1 IS NULL
 }
UPDATE_COURT_EVENT_CHARGES_DUP{
update court_event_charges set result_code_1 =:p_outcome_reason_code, result_code_1_indicator =:p_result_code_1_indicator, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where event_id = :p_event_id and offender_charge_id =:offender_charge_id and result_code_1 is null
}

SELECT_STATUS_CURSOR{
         SELECT a.result_code_1,
                a.result_code_1_indicator,
                c.charge_status
           FROM court_event_charges  a,
                court_events         b,
                offence_result_codes c
          WHERE a.offender_charge_id =:p_offender_charge_id
            AND a.event_id = b.event_id
            AND a.result_code_1 = c.result_code
          ORDER BY b.event_date DESC,
                   b.start_time DESC
}

OFFENDER_CHARGES_UPDATE{
update offender_charges set result_code_1 =:v_result_code, result_code_1_indicator =:v_disposition_code, charge_status =:v_charge_status, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_charge_id =:offender_charge_id
   }
   
UPDATE_OFFENDER_CHARGESONE{
update offender_charges set result_code_1 =:p_outcome_reason_code, result_code_1_indicator =:p_result_code_1_indicator, charge_status =:p_charge_status, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_charge_id =:p_offender_charge_id
 }
 
 IS_ORDER_EXIST{
  SELECT 'X'  FROM orders WHERE offender_book_id = :p_offender_book_id AND event_id =:p_event_id  AND order_type = 'AUTO'
  }
  
  EVENT_CUR{
  SELECT agy_loc_id,event_date FROM court_events WHERE event_id = p_event_id
  }
  
  ORDER_INSERT_QUERY{
  insert into orders (order_id, offender_book_id, case_id, court_date, order_type, issuing_agy_loc_id, order_status, event_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (NEXTVAL('order_id'), :p_offender_book_id, :p_case_id, :v_event_date, 'AUTO', :v_agy_loc_id, 'A', :p_event_id, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
  }
 
 L_AGY_LOC {
 	SELECT agy_loc_id
        FROM court_events
       WHERE event_id = :l_event_id
 }
 
 L_COURT_TYPE {
 	SELECT jurisdiction_code
        FROM agency_locations COURT_EVENT_cHARGES
       WHERE agy_loc_id = :p_agy_loc_id
 
 }
 
   GET_SENTENCES {
         SELECT COUNT(*)
           FROM offender_sentences
          WHERE offender_book_id = :p_off_book_id
            AND case_id = :p_case_id
            }
            
CHARGE_SENTENCES_QUERY_IF_INST{
  SELECT
:p_offender_book_id offender_book_id, NULL sentence_seq,
off_chg.offender_charge_id offender_charge_id,
(SELECT description
FROM offences
WHERE
offence_code = off_chg.offence_code AND
statute_code = off_chg.statute_code) as description,
--offense_code_description(off_chg.offence_code, off_chg.statute_code) offence_description,
off_chg.offence_date offence_date,
off_chg.offence_range_date offence_range_date,
--get_result_code_description(off_chg.result_code_1) result_code1_desc,
(SELECT description
FROM offence_result_codes
WHERE
result_code = off_chg.result_code_1) result_code1_desc,
off_chg.result_code_1_indicator result_code_1_indicator,
-- get_result_code_description(off_chg.result_code_2) result_code2_desc,
(SELECT description
FROM offence_result_codes
WHERE
result_code = off_chg.result_code_2) result_code2_desc,
off_chg.result_code_2_indicator result_code_2_indicator, 'N' apply_flag,
to_number(to_char(coalesce (off_chg.modify_datetime, off_chg.create_datetime)::timestamp, 'DDHH24MISSFF4'::text)::text,'9G999g999'::text) check_sum
FROM offender_charges off_chg
WHERE
off_chg.offender_book_id =:p_offender_book_id AND
case_id =:p_case_id AND
off_chg.charge_status = 'A'
AND
off_chg.result_code_1_indicator IN ('F', 'P') AND
NOT EXISTS
(SELECT 'X'
FROM
offender_sentence_charges a, offender_sentences b, sentence_calc_types c
WHERE
a.offender_charge_id = off_chg.offender_charge_id AND
a.offender_book_id = :p_offender_book_id AND
a.offender_book_id = b.offender_book_id AND
a.sentence_seq = b.sentence_seq AND
b.sentence_calc_type = c.sentence_calc_type AND
b.sentence_category = c.sentence_category AND
c.sentence_type = 'INST')
}

CHARGE_SENTENCES_QUERY_IF_COMM{
 SELECT :p_offender_book_id offender_book_id,
                      NULL sentence_seq,
                      off_chg.offender_charge_id offender_charge_id,
					  (SELECT description
						FROM offences
						WHERE
					offence_code = off_chg.offence_code AND
						statute_code = off_chg.statute_code) as description,
                     -- offense_code_description(off_chg.offence_code, off_chg.statute_code) offence_description,
                      off_chg.offence_date offence_date,
                      off_chg.offence_range_date offence_date_range,
					  (SELECT description
						FROM offence_result_codes
						WHERE
						result_code = off_chg.result_code_1) result_code1_desc,
                     -- get_result_code_description(off_chg.result_code_1) result_code1_desc,
                      off_chg.result_code_1_indicator result_code_1_indicator,
					  (SELECT description
						FROM offence_result_codes
						WHERE
						result_code = off_chg.result_code_2) result_code2_desc,
                     -- get_result_code_description(off_chg.result_code_2) result_code2_desc,
                      off_chg.result_code_2_indicator result_code_2_indicator,
                      'Y' apply_flag,
                      to_number(to_char(coalesce (off_chg.modify_datetime, off_chg.create_datetime), 'DDHH24MISSFF4')) check_sum
                 FROM offender_charges off_chg
                WHERE off_chg.offender_book_id =:p_offender_book_id
                  AND case_id =:p_case_id
                  AND off_chg.charge_status = 'A'
                  AND off_chg.result_code_1_indicator IN ('F', 'P')
                  AND NOT EXISTS
                (SELECT 'X'
                         FROM offender_sentence_charges a
                        WHERE a.offender_book_id =:p_offender_book_id
                          AND a.sentence_seq = coalesce(:p_sentence_seq, 0)
                          AND a.offender_charge_id =
                              off_chg.offender_charge_id);
}

CHARGE_SENTENCES_QUERY_ONE{
select
	:p_offender_book_id offender_book_id,
	null sentence_seq,
	off_chg.offender_charge_id offender_charge_id,
	
(
	select
		description
	from
		offences
	where
		offence_code = off_chg.offence_code
		and statute_code = off_chg.statute_code) as description,
	off_chg.offence_date offence_date,
	off_chg.offence_range_date offence_date_range,
	
(
	select
		description
	from
		offence_result_codes
	where
		result_code = off_chg.result_code_1) result_code1_desc,
	off_chg.result_code_1_indicator result_code_1_indicator,
	
(
	select
		description
	from
		offence_result_codes
	where
		result_code = off_chg.result_code_2) result_code2_desc,
	off_chg.result_code_2_indicator result_code_2_indicator,
	'Y' apply_flag,
	coalesce(to_char(coalesce (off_chg.modify_datetime, off_chg.create_datetime), 'DDHH24MISSFF4')) check_sum
from
	offender_charges off_chg
where
	off_chg.offender_book_id =:p_offender_book_id
	and case_id =:p_case_id
	and off_chg.charge_status = 'A'
	and off_chg.result_code_1_indicator in ('F', 'P')
	and not exists (
	select
		'X'
	from
		offender_sentence_charges a
	where
		a.offender_book_id =:p_offender_book_id
		and a.sentence_seq = coalesce(:p_sentence_seq,
		0)
			and a.offender_charge_id = off_chg.offender_charge_id)
/*select
	:p_offender_book_id offender_book_id,
	null sentence_seq,
	off_chg.offender_charge_id offender_charge_id,
	-- offense_code_description(off_chg.offence_code, off_chg.statute_code) offence_description,
(
	select
		description
	from
		offences
	where
		offence_code = off_chg.offence_code
		and statute_code = off_chg.statute_code) as description,
	off_chg.offence_date offence_date,
	off_chg.offence_range_date offence_date_range,
	-- get_result_code_description(off_chg.result_code_1) result_code1_desc,
(
	select
		description
	from
		offence_result_codes
	where
		result_code = off_chg.result_code_1) result_code1_desc,
	off_chg.result_code_1_indicator result_code_1_indicator,
	-- get_result_code_description(off_chg.result_code_2) result_code2_desc,
(
	select
		description
	from
		offence_result_codes
	where
		result_code = off_chg.result_code_2) result_code2_desc,
	off_chg.result_code_2_indicator result_code_2_indicator,
	'Y' apply_flag,
	to_number(to_char(coalesce (off_chg.modify_datetime, off_chg.create_datetime), 'DDHH24MISSFF4')) check_sum
from
	offender_charges off_chg
where
	off_chg.offender_book_id =:p_offender_book_id
	and case_id =:p_case_id
	and off_chg.charge_status = 'A'
	and off_chg.result_code_1_indicator in ('F', 'P')
	and not exists (
	select
		'X'
	from
		offender_sentence_charges a
	where
		a.offender_book_id =:p_offender_book_id
		and a.sentence_seq = coalesce(:p_sentence_seq,
		0)
			and a.offender_charge_id = off_chg.offender_charge_id)*/

}
OFFENDER_SENTENCE_CHARGES{
-- INSERT INTO offender_sentence_charges (offender_book_id, sentence_seq, offender_charge_id) VALUES (:p_offender_book_id, :p_sentence_seq, :p_id)
insert into offender_sentence_charges (offender_book_id, sentence_seq, offender_charge_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:p_offender_book_id, :p_sentence_seq, :p_id, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

   UNLINK_CUR{
 SELECT offender_charge_id, event_id FROM link_case_txns a WHERE case_id = :p_case_id AND combined_case_id = :p_case_id1 AND NOT EXISTS (SELECT 1 FROM offender_charges b WHERE a.offender_charge_id = b.offender_charge_id AND coalesce(result_code_1_indicator, 'I') IN ('P', 'F'))
 }
                    
                    
OFFENDER_CHARGES{
update offender_charges set case_id = :p_case_id, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_charge_id = :offender_charge_id
 }
          
COURT_EVENT_CHARGES{
 DELETE court_event_charges WHERE event_id =:event_id AND offender_charge_id =:offender_charge_id
}
            
LINK_CASE_TXNS {
DELETE link_case_txns WHERE case_id = :p_case_id AND combined_case_id = :p_case_id1
}
         
 OFFENDER_CASES_UPDATE_FIRST{    
update offender_cases set combined_case_id = null, case_status = 'A', status_update_reason = decode( (select active_type from legal_update_reasons where update_reason_code = 'UNLINKED'), 'A', 'ACTIVE', 'I', 'INACTIVE', (select active_type from legal_update_reasons where update_reason_code = 'UNLINKED')), status_update_date = trunc(SYSDATE), status_update_staff_id = oms_utils.get_staff_id, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where case_id = :p_case_id and offender_book_id =:p_off_book_id
 }



L_SENT_EVENT_CUR {
SELECT sentence_event_id.nextval  FROM dual
}

OFFENDER_SENTENCES_HTY{
/* INSERT INTO offender_sentences_hty ( sentence_event_id, offender_book_id, sentence_seq, charge_seq, counts, sentence_category
                , sentence_calc_type, sentence_status, description, term_changed_flag, sent_calc_needed_flag, fine_amount, consecutive_count_flag
                , fine_comment_text, consec_to_sentence_seq, aggregate_sentence_seq, sentence_date, effective_date, good_conduct_days
                , payment_due_date, default_days, paid_date, revocation_date, as_goodtime_type, start_date, start_time, non_probation_start_date
                , probable_release_date, probable_release_time, sentence_expiry_date, sentence_expiry_time, sent_closed_date, sent_clos_reason_code
                , sent_clos_comment_text, gap_flag, comment_text, creation_date, creation_user, discharge_authority, discharge_comment
                , discharge_date, discharge_reason, dtbf_flag, extended_date, extended_flag, extending_authority, installment_details, order_code
                , order_type, registration_date, supervising_agy_loc_id, supervision_expiry_date, total_compensation, total_fine, system_discharge_date
                , order_id,end_date,case_id, create_datetime,create_user_id,modify_datetime,modify_user_id,sentence_level,status_update_reason
                ,status_update_comment,status_update_date,status_update_staff_id,line_seq,workflow_id,proceeding_outcome,offender_proceeding_id
                ,system_creation_date,pb_schedule_id,parole_decision_seq,caseload_type )
      (SELECT v_sent_event_id,  offender_book_id, sentence_seq, charge_seq, counts, sentence_category
                , sentence_calc_type, sentence_status, description, term_changed_flag, sent_calc_needed_flag, fine_amount, consecutive_count_flag
                , fine_comment_text, consec_to_sentence_seq, aggregate_sentence_seq, sentence_date, effective_date, good_conduct_days
                , payment_due_date, default_days, paid_date, revocation_date, as_goodtime_type, start_date, start_time, non_probation_start_date
                , probable_release_date, probable_release_time, sentence_expiry_date, sentence_expiry_time, sent_closed_date, sent_clos_reason_code
                , sent_clos_comment_text, gap_flag, comment_text, creation_date, creation_user, discharge_authority, discharge_comment
                , discharge_date, discharge_reason, dtbf_flag, extended_date, extended_flag, extending_authority, installment_details, order_code
                , order_type, registration_date, supervising_agy_loc_id, supervision_expiry_date, total_compensation, total_fine, system_discharge_date
                , order_id,end_date,case_id, create_datetime,create_user_id,modify_datetime,modify_user_id,sentence_level,status_update_reason
                ,status_update_comment,status_update_date,status_update_staff_id,line_seq,workflow_id,proceeding_outcome,offender_proceeding_id
                ,system_creation_date,pb_schedule_id,parole_decision_seq,caseload_type
           FROM offender_sentences
          WHERE offender_book_id = :p_offender_book_id
              AND sentence_seq = :p_sentence_seq) */
insert into offender_sentences_hty ( sentence_event_id, offender_book_id, sentence_seq, charge_seq, counts, sentence_category , sentence_calc_type, sentence_status, description, term_changed_flag, sent_calc_needed_flag, fine_amount, consecutive_count_flag , fine_comment_text, consec_to_sentence_seq, aggregate_sentence_seq, sentence_date, effective_date, good_conduct_days , payment_due_date, default_days, paid_date, revocation_date, as_goodtime_type, start_date, start_time, non_probation_start_date , probable_release_date, probable_release_time, sentence_expiry_date, sentence_expiry_time, sent_closed_date, sent_clos_reason_code , sent_clos_comment_text, gap_flag, comment_text, creation_date, creation_user, discharge_authority, discharge_comment , discharge_date, discharge_reason, dtbf_flag, extended_date, extended_flag, extending_authority, installment_details, order_code , order_type, registration_date, supervising_agy_loc_id, supervision_expiry_date, total_compensation, total_fine, system_discharge_date , order_id, end_date, case_id, create_datetime, create_user_id, modify_datetime, sentence_level, status_update_reason , status_update_comment, status_update_date, status_update_staff_id, line_seq, workflow_id, proceeding_outcome, offender_proceeding_id , system_creation_date, pb_schedule_id, parole_decision_seq, caseload_type ) ( select v_sent_event_id, offender_book_id, sentence_seq, charge_seq, counts, sentence_category , sentence_calc_type, sentence_status, description, term_changed_flag, sent_calc_needed_flag, fine_amount, consecutive_count_flag , fine_comment_text, consec_to_sentence_seq, aggregate_sentence_seq, sentence_date, effective_date, good_conduct_days , payment_due_date, default_days, paid_date, revocation_date, as_goodtime_type, start_date, start_time, non_probation_start_date , probable_release_date, probable_release_time, sentence_expiry_date, sentence_expiry_time, sent_closed_date, sent_clos_reason_code , sent_clos_comment_text, gap_flag, comment_text, creation_date, creation_user, discharge_authority, discharge_comment , discharge_date, discharge_reason, dtbf_flag, extended_date, extended_flag, extending_authority, installment_details, order_code , order_type, registration_date, supervising_agy_loc_id, supervision_expiry_date, total_compensation, total_fine, system_discharge_date , order_id, end_date, case_id, create_datetime, create_user_id, modify_datetime, sentence_level, status_update_reason , status_update_comment, status_update_date, status_update_staff_id, line_seq, workflow_id, proceeding_outcome, offender_proceeding_id , system_creation_date, pb_schedule_id, parole_decision_seq, caseload_type from offender_sentences where offender_book_id = :p_offender_book_id and sentence_seq = :p_sentence_seq)
              }
              
              
                CHG_CUR{
        SELECT osc.offender_charge_id
          FROM   offender_sentence_charges osc
         WHERE  offender_book_id = :p_offender_book_id
           AND  sentence_seq = :p_sentence_seq
           }
           
           
 OFFENDER_CHARGES_HTY{          
         /*   INSERT INTO offender_charges_hty (sentence_event_id, offender_book_id, charge_seq, investigation_id, order_id, offence_code,
                         statute_code, initial_counts, cleared_counts, attemped_flag, offense_date, offence_type, charge_status,
                          vcl_amount, vcl_comment_text, description, update_allowed_flag, charge_bail_amount, bail_type,
                          original_bail_amount, bail_allowed_flag, commit_date, conviction_date, sex_involved_flag, drugs_involved_flag,
                               firearms_involved_flag, intoximeter, good_time_allowed_flag, creation_date, creation_user, offence_date_range,
                          other_charges, deleted_flag, offender_charge_id, no_of_offences, offence_date, offence_range_date, plea_code,
                          property_value, total_property_value, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3,
                          create_user_id, modify_user_id, modify_datetime, create_datetime, result_code_1, result_code_2,
                          result_code_1_indicator, result_code_2_indicator, case_id, most_serious_flag, lids_offence_number,
                          vcl_flag, charge_info_number, LPC_COMPLICITY_TYPE_CODE)
                   SELECT v_sent_event_id, offender_book_id, offender_charge_id, investigation_id, order_id, offence_code,
                          statute_code, initial_counts, cleared_counts, attemped_flag, offense_date, offence_type, charge_status,
                          vcl_amount, vcl_comment_text, description, update_allowed_flag, charge_bail_amount, bail_type,
                          original_bail_amount, bail_allowed_flag, commit_date, conviction_date, sex_involved_flag, drugs_involved_flag,
                               firearms_involved_flag, intoximeter, good_time_allowed_flag, creation_date, creation_user, offence_date_range,
                          other_charges, 'Y', offender_charge_id, no_of_offences, offence_date, offence_range_date, plea_code,
                          property_value, total_property_value, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3,
                          create_user_id, modify_user_id, modify_datetime, create_datetime, result_code_1, result_code_2,
                          result_code_1_indicator, result_code_2_indicator, case_id, most_serious_flag, lids_offence_number,
                          vcl_flag, charge_info_number, LPC_COMPLICITY_TYPE_CODE
                    FROM offender_charges
                   WHERE offender_charge_id = :v_chg_id  */
 insert into offender_charges_hty (sentence_event_id, offender_book_id, charge_seq, investigation_id, order_id, offence_code, statute_code, initial_counts, cleared_counts, attemped_flag, offense_date, offence_type, charge_status, vcl_amount, vcl_comment_text, description, update_allowed_flag, charge_bail_amount, bail_type, original_bail_amount, bail_allowed_flag, commit_date, conviction_date, sex_involved_flag, drugs_involved_flag, firearms_involved_flag, intoximeter, good_time_allowed_flag, creation_date, creation_user, offence_date_range, other_charges, deleted_flag, offender_charge_id, no_of_offences, offence_date, offence_range_date, plea_code, property_value, total_property_value, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, create_user_id, modify_datetime, create_datetime, result_code_1, result_code_2, result_code_1_indicator, result_code_2_indicator, case_id, most_serious_flag, lids_offence_number, vcl_flag, charge_info_number, LPC_COMPLICITY_TYPE_CODE) select v_sent_event_id, offender_book_id, offender_charge_id, investigation_id, order_id, offence_code, statute_code, initial_counts, cleared_counts, attemped_flag, offense_date, offence_type, charge_status, vcl_amount, vcl_comment_text, description, update_allowed_flag, charge_bail_amount, bail_type, original_bail_amount, bail_allowed_flag, commit_date, conviction_date, sex_involved_flag, drugs_involved_flag, firearms_involved_flag, intoximeter, good_time_allowed_flag, creation_date, creation_user, offence_date_range, other_charges, 'Y', offender_charge_id, no_of_offences, offence_date, offence_range_date, plea_code, property_value, total_property_value, cjit_offence_code_1, cjit_offence_code_2, cjit_offence_code_3, create_user_id, modify_datetime, create_datetime, result_code_1, result_code_2, result_code_1_indicator, result_code_2_indicator, case_id, most_serious_flag, lids_offence_number, vcl_flag, charge_info_number, LPC_COMPLICITY_TYPE_CODE from offender_charges where offender_charge_id = :v_chg_id
 
                   }
                   
OFFENDER_SENTENCE_TERMS_HTY{
          /*         INSERT INTO offender_sentence_terms_hty (sentence_event_id, sentence_seq, offender_book_id, term_seq, sentence_term_code, years, months, weeks,
                                   hours, days, start_date, start_time, ovr_start_date, ovr_start_time, end_date, parole_supervision,
                                   end_time, ovr_end_time, ovr_end_date, attendence_hours, creation_date, creation_user, work_hours, gap_seq, life_sentence_flag,
                                   modify_datetime, modify_user_id, create_datetime, create_user_id, attendance_hours)
                        (SELECT v_sent_event_id, sentence_seq, offender_book_id, term_seq,
                                sentence_term_code, years, months, weeks, hours, days, start_date, start_time,
                                ovr_start_date, ovr_start_time, end_date, parole_supervision, end_time, ovr_end_time, ovr_end_date,
                                attendence_hours, creation_date, creation_user, work_hours, gap_seq, life_sentence_flag,
                                modify_datetime, modify_user_id, create_datetime, create_user_id, attendance_hours
                           FROM offender_sentence_terms
                          WHERE offender_book_id = :p_offender_book_id
                            AND sentence_seq = :p_sentence_seq)   */
insert into offender_sentence_terms_hty (sentence_event_id, sentence_seq, offender_book_id, term_seq, sentence_term_code, years, months, weeks, hours, days, start_date, start_time, ovr_start_date, ovr_start_time, end_date, parole_supervision, end_time, ovr_end_time, ovr_end_date, attendence_hours, creation_date, creation_user, work_hours, gap_seq, life_sentence_flag, modify_datetime, create_datetime, create_user_id, attendance_hours) ( select v_sent_event_id, sentence_seq, offender_book_id, term_seq, sentence_term_code, years, months, weeks, hours, days, start_date, start_time, ovr_start_date, ovr_start_time, end_date, parole_supervision, end_time, ovr_end_time, ovr_end_date, attendence_hours, creation_date, creation_user, work_hours, gap_seq, life_sentence_flag, modify_datetime, create_datetime, create_user_id, attendance_hours from offender_sentence_terms where offender_book_id = :p_offender_book_id and sentence_seq = :p_sentence_seq)
                            }
                            
 OFFENDER_SENT_CONDITIONS_HTY{                           
  /* INSERT INTO offender_sent_conditions_hty (sentence_event_id, sentence_seq, comm_condition_type, comm_condition_code, start_date,
                   condition_status, proceeding_outcome, status_date, offender_book_id, expiry_date, list_seq,
                   comment_text, curfew_start_time, curfew_end_time, condition_recommended_flag, governor_condition_flag,
                    length, length_unit, details_text, offender_sent_condition_id, curfew_provider, exclusion_code,
                   residency_address_id, mental_health_provider, alcohol_treatment_provider, attendance_centre,
                   create_datetime, create_user_id, modify_datetime, modify_user_id, condition_required_flag,
                   condition_applied_flag, long_comment_text, appointment_person_name, review_code, supervisor_name,
                   report_time, report_date, personal_relationship_flag, vehicle_details_flag, non_associated_offenders,
                   drug_testing, termination_date, status_reason_code, no_resident_under_age_of, prohibited_contact,
                   restricted_child_age_of, restricted_approval_person, curfew_tagging_flag, other_program,
                   no_work_with_under_age, no_work_with_under_age_of, no_access_to_internet, no_user_of_computer,
                   status_update_reason, status_update_comment, status_update_date, status_update_staff_id,
                   workflow_id, activity_code, cond_act_type, activity_status, program_id, category_type,
                   non_association_text, financial_total_amount)
           ( SELECT v_sent_event_id, sentence_seq, comm_condition_type, comm_condition_code, start_date,
                   condition_status, proceeding_outcome, status_date, offender_book_id, expiry_date, list_seq,
                   comment_text, curfew_start_time, curfew_end_time, condition_recommended_flag, governor_condition_flag,
                    length, length_unit, details_text, offender_sent_condition_id, curfew_provider, exclusion_code,
                   residency_address_id, mental_health_provider, alcohol_treatment_provider, attendance_centre,
                   create_datetime, create_user_id, modify_datetime, modify_user_id, condition_required_flag,
                   condition_applied_flag, long_comment_text, appointment_person_name, review_code, supervisor_name,
                   report_time, report_date, personal_relationship_flag, vehicle_details_flag, non_associated_offenders,
                   drug_testing, termination_date, status_reason_code, no_resident_under_age_of, prohibited_contact,
                   restricted_child_age_of, restricted_approval_person, curfew_tagging_flag, other_program,
                   no_work_with_under_age, no_work_with_under_age_of, no_access_to_internet, no_user_of_computer,
                   status_update_reason, status_update_comment, status_update_date, status_update_staff_id,
                   workflow_id, activity_code, cond_act_type, activity_status, program_id, category_type,
                   non_association_text, financial_total_amount
              FROM offender_sent_conditions
             WHERE offender_book_id = :p_offender_book_id
               AND sentence_seq = :p_sentence_seq) */
  insert into offender_sent_conditions_hty (sentence_event_id, sentence_seq, comm_condition_type, comm_condition_code, start_date, condition_status, proceeding_outcome, status_date, offender_book_id, expiry_date, list_seq, comment_text, curfew_start_time, curfew_end_time, condition_recommended_flag, governor_condition_flag, length, length_unit, details_text, offender_sent_condition_id, curfew_provider, exclusion_code, residency_address_id, mental_health_provider, alcohol_treatment_provider, attendance_centre, create_datetime, create_user_id, modify_datetime, condition_required_flag, condition_applied_flag, long_comment_text, appointment_person_name, review_code, supervisor_name, report_time, report_date, personal_relationship_flag, vehicle_details_flag, non_associated_offenders, drug_testing, termination_date, status_reason_code, no_resident_under_age_of, prohibited_contact, restricted_child_age_of, restricted_approval_person, curfew_tagging_flag, other_program, no_work_with_under_age, no_work_with_under_age_of, no_access_to_internet, no_user_of_computer, status_update_reason, status_update_comment, status_update_date, status_update_staff_id, workflow_id, activity_code, cond_act_type, activity_status, program_id, category_type, non_association_text, financial_total_amount) ( select v_sent_event_id, sentence_seq, comm_condition_type, comm_condition_code, start_date, condition_status, proceeding_outcome, status_date, offender_book_id, expiry_date, list_seq, comment_text, curfew_start_time, curfew_end_time, condition_recommended_flag, governor_condition_flag, length, length_unit, details_text, offender_sent_condition_id, curfew_provider, exclusion_code, residency_address_id, mental_health_provider, alcohol_treatment_provider, attendance_centre, create_datetime, create_user_id, modify_datetime, condition_required_flag, condition_applied_flag, long_comment_text, appointment_person_name, review_code, supervisor_name, report_time, report_date, personal_relationship_flag, vehicle_details_flag, non_associated_offenders, drug_testing, termination_date, status_reason_code, no_resident_under_age_of, prohibited_contact, restricted_child_age_of, restricted_approval_person, curfew_tagging_flag, other_program, no_work_with_under_age, no_work_with_under_age_of, no_access_to_internet, no_user_of_computer, status_update_reason, status_update_comment, status_update_date, status_update_staff_id, workflow_id, activity_code, cond_act_type, activity_status, program_id, category_type, non_association_text, financial_total_amount from offender_sent_conditions where offender_book_id = :p_offender_book_id and sentence_seq = :p_sentence_seq)             
               }

L_APP_EXISTS{
SELECT 1 FROM APPEAL_OFFENDER_CHARGES AOC WHERE AOC.APPEAL_ID = :P_APPEAL_ID AND AOC.OFFENDER_CHARGE_ID = :P_OFF_CHG AND AOC.OFFENDER_BOOK_ID = :P_OFF_BKG_ID
}

L_SENT_CUR{
SELECT SENTENCE_SEQ FROM OFFENDER_SENTENCE_CHARGES WHERE OFFENDER_CHARGE_ID = :P_OFFENDER_CHARGE AND OFFENDER_BOOK_ID = :P_OFF_BKG_ID
}

INSERT_INTO_APPEAL_OFFENDER_CHARGES{
INSERT INTO APPEAL_OFFENDER_CHARGES ( APPEAL_ID, OFFENDER_BOOK_ID, OFFENDER_CHARGE_ID, CHARGE_SEQ, ALL_SENTENCE_FLAG, ORDER_ID, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME) VALUES ( :P_APPEAL_ID, :P_OFF_BKG_ID, :P_OFF_CHG , :P_OFF_CHG, 'Y', :P_ORDER_ID, CURRENT_TIMESTAMP , :CREATEUSERID , CURRENT_TIMESTAMP)
}

UPDATE_OF_COURT_EVENT_CHARGES{
UPDATE COURT_EVENT_CHARGES SET RESULT_CODE_1 = 'APP', RESULT_CODE_1_INDICATOR = 'I', MODIFY_DATETIME =CURRENT_TIMESTAMP , MODIFY_USER_ID =:MODIFYUSERID WHERE EVENT_ID = :P_EVENT_ID AND OFFENDER_CHARGE_ID = :P_OFF_CHG
}

UPDATE_OF_OFFENDER_CHARGES{
UPDATE OFFENDER_CHARGES SET RESULT_CODE_1 = 'APP', RESULT_CODE_1_INDICATOR = 'I', MODIFY_DATETIME = CURRENT_TIMESTAMP , MODIFY_USER_ID =:MODIFYUSERID WHERE OFFENDER_CHARGE_ID = :P_OFF_CHG
}

INSERT_OF_APPEAL_OFFENDER_SENTENCES{
INSERT INTO APPEAL_OFFENDER_SENTENCES (APPEAL_ID, OFFENDER_BOOK_ID, SENTENCE_SEQ, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME) VALUES (:P_APPEAL_ID, :P_OFF_BKG_ID, :L_SENT_SEQ, CURRENT_TIMESTAMP , :CREATEUSERID , CURRENT_TIMESTAMP)
}

DELETE_OF_APPEAL_OFFENDER_CHARGES{
DELETE FROM APPEAL_OFFENDER_CHARGES AOC WHERE AOC.APPEAL_ID = :P_APPEAL_ID AND AOC.OFFENDER_CHARGE_ID = :P_OFF_CHG AND AOC.OFFENDER_BOOK_ID = :P_OFF_BKG_ID
}

UPDATE_OF_COURT_EVENT_CHARGES_ONE{
UPDATE COURT_EVENT_CHARGES SET RESULT_CODE_1 = 'SENT', RESULT_CODE_1_INDICATOR = 'F', MODIFY_DATETIME = CURRENT_TIMESTAMP , MODIFY_USER_ID =:MODIFYUSERID WHERE EVENT_ID = :P_EVENT_ID AND OFFENDER_CHARGE_ID = :P_OFF_CHG
}

UPDATE_OF_OFFENDER_CHARGES_ONE{
UPDATE OFFENDER_CHARGES SET RESULT_CODE_1 = 'SENT', RESULT_CODE_1_INDICATOR = 'F', MODIFY_DATETIME = CURRENT_TIMESTAMP , MODIFY_USER_ID =:MODIFYUSERID WHERE OFFENDER_CHARGE_ID = :P_OFF_CHG
}

DELETE_OF_APPEAL_OFFENDER_SENTENCES_ONE{
DELETE FROM APPEAL_OFFENDER_SENTENCES AOS WHERE AOS.APPEAL_ID = :P_APPEAL_ID AND AOS.OFFENDER_BOOK_ID = :P_OFF_BKG_ID AND AOS.SENTENCE_SEQ = :L_SENT_SEQ AND NOT EXISTS ( SELECT NULL FROM APPEAL_OFFENDER_CHARGES AOC, OFFENDER_SENTENCE_CHARGES OSC WHERE AOC.OFFENDER_CHARGE_ID = OSC.OFFENDER_CHARGE_ID AND AOC.OFFENDER_BOOK_ID = OSC.OFFENDER_BOOK_ID AND OSC.SENTENCE_SEQ = :L_SENT_SEQ)
}

GET_RC_DESCRIPTION{
 select description from reference_codes where domain = :P_DOMAIN and code = :P_CODE
}

L_EVENT_CUR{
 select event_id from court_events ce where event_id = ( select MAX(ce1.event_id) from court_events ce1 where ce1.court_event_type = :P_EVENT_TYPE and ce1.event_id <> :P_EVENT_ID and ce1.case_id = :P_CASE_ID)
}

L_BAIL_CUR{
 SELECT * FROM offender_bail_details WHERE event_id = :P_PR_EVENT_ID
}

LV_REC_BAIL_INSERT{
 insert into offender_bail_details (event_id, bail_date, bail_status, bail_type, cash, cash_flag, comment_text, judge, method, offender_book_id, perfected_by, perfected_datetime, property, receipt_reference_text, self_amount, surety, surety_name1, surety_name1_address, surety_name2, surety_name2_address) values (:event_id, :bail_date, :bail_status, :bail_type, :cash, :cash_flag, :comment_text, :judge, :method, :offender_book_id, :perfected_by, :perfected_datetime, :property, :receipt_reference_text, :self_amount, :surety, :surety_name1, :surety_name1_address, :surety_name2, :surety_name2_address)
}

L_BAIL_COND_CUR{
 SELECT * FROM offender_bail_conditions WHERE event_id = :P_PR_EVENT_ID
}

OFFENDER_BAIL_CONDITIONS_INSERT{
 insert into offender_bail_conditions (event_id, bail_condition_id, offender_book_id, forfeited_funds, security_deposit, cash_deposit, bail_condition_code, forfeited_funds_dist, security_deposit_dist_code, cash_deposit_dist, cash_deposit_receipt, comment_text, surety_name1, surety_name2) values (:event_id, ( select coalesce(MAX(bail_condition_id), 0) + 1 from offender_bail_conditions), :offender_book_id, :forfeited_funds, :security_deposit, :cash_deposit, :bail_condition_code, :forfeited_funds_dist, :security_deposit_dist_code, :cash_deposit_dist, :cash_deposit_receipt, :comment_text, :surety_name1, :surety_name2)
}

OFFENDER_BAIL_DETAILS_UPDATE{
 update offender_bail_details set perfected_datetime = null, receipt_reference_text = null, surety_name1 = null, surety_name1_address = null, surety_name2 = null, surety_name2_address = null, comment_text = null, self_amount = 0.00, surety = 0.00, bail_status = 'R' where event_id = :P_EVENT_ID   
}

OFFENDER_BAIL_CONDITIONS_DELETE{
 DELETE FROM offender_bail_conditions WHERE event_id = :P_EVENT_ID
}
CASE_IDENTIFIERS_INSERT{
INSERT INTO offender_case_identifiers(case_id,identifier_type,identifier_no,create_datetime,modify_datetime,create_user_id)VALUES(:caseId,:identifierType,:identifierNo,:createDatetime,:modifyDatetime,:createUserId)
}

ORDERS_DELETE{
 DELETE FROM orders WHERE offender_book_id = :offBkgId AND event_id = :eventId AND order_type = 'AUTO'
}