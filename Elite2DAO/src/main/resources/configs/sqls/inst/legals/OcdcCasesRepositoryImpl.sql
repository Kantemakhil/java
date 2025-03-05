DISPLAY_COURT_CASES {
select A.CASE_SEQ,
       A.CASE_ID,
       A.OFFENDER_BOOK_ID,
       A.AGY_LOC_ID,
       A.BEGIN_DATE,
       A.CASE_TYPE,
       A.CASE_INFO_PREFIX,
       A.CASE_INFO_NUMBER,
       A.CASE_STATUS,
       A.COMBINED_CASE_ID,
       A.STATUS_UPDATE_REASON,
       A.STATUS_UPDATE_DATE,
       (SELECT B.CASE_SEQ  FROM OFFENDER_CASES B WHERE B.OFFENDER_BOOK_ID=A.OFFENDER_BOOK_ID  AND B.CASE_ID =A.COMBINED_CASE_ID
       ) LINK_CASE_SEQ
        FROM OFFENDER_CASES A
        WHERE A.OFFENDER_BOOK_ID= :offenderBookId
        ORDER BY oms_miscellaneous_getdesccode('ACTIVE_TYPE', :caseStatus) ASC,begin_date DESC
}

GET_PREINSERT_CASE_TYPE {
select tag_reference_codes_default_domain('LEG_CASE_TYP') from dual
}

GET_PREINSERT_AGY_LOCATION{
select tag_legal_cases_get_latest_hearing_agency(:OFFENDERBOOKID) from dual
}

FETCH_PREINSERT_CASEPREFIX{
 select CODE, DESCRIPTION from reference_codes where domain = 'CASE_ID_TYPE' and code in ( select tag_legal_cases_get_case_prefix(:CASETYPE) casePrefix from dual)
}

GET_COURT_DATA {
SELECT   al.description,
         al.agy_loc_id,
         al.active_flag,
         al.list_seq
    FROM agency_locations al
   WHERE agency_location_type = 'CRT'
     --AND (active_flag = 'Y' AND deactivation_date IS NULL)
     AND al.agy_loc_id NOT IN ('TRN', 'OUT')
ORDER BY list_seq,description
}

CASE_STATUS {
SELECT   description nbt_case_status, 
         code case_status	 
    FROM reference_codes 
   WHERE domain = 'ACTIVE_TYPE'
     AND (active_flag = 'Y')      
ORDER BY description, list_seq
}

COURT_EVENTS {
select 
OFFENDER_BOOK_ID,
CASE_ID,
EVENT_ID,
EVENT_DATE,
START_TIME,
AGY_LOC_ID,
COURT_EVENT_TYPE,
OUTCOME_REASON_CODE,
NEXT_EVENT_DATE,
NEXT_EVENT_START_TIME,
HOLD_FLAG,
ORDER_REQUESTED_FLAG,
COMMENT_TEXT
from court_events where case_id=:caseId
order by event_date desc,start_time desc
}


LOV_HEARING_TYPE {
SELECT description nbt_court_event_type,
       code court_event_type
  FROM reference_codes
 WHERE domain = 'MOVE_RSN'
   AND parent_code = 'CRT'
   AND (active_flag = 'Y')
ORDER BY list_seq, description
}

LOV_OUTCOME{
select Description , Result_code from OFFENCE_RESULT_CODES
Where ACTIVE_FLAG = 'Y'
}

LOV_OFFENCE_TYPE{
select
	ref_code1.description  description ,
	aot.offence_type code
from
	allowable_offence_types aot,
	reference_codes ref_code1
where
	aot.offence_code = :offenceCode
	and ref_code1.domain = 'OFFENCE_TYPE'
	and ref_code1.code = aot.offence_type
	and ref_code1.active_flag = 'Y'
	and ref_code1.expired_date is null
order by
	aot.offence_type
}

OFFENCE_TYPE{
select distinct ref_code1.description DSP_DESCRIPTION3,aot.offence_type 
from allowable_offence_types aot, reference_codes ref_code1 
where ref_code1.domain = 'OFFENCE_TYPE' and ref_code1.code = aot.offence_type 
and ref_code1.active_flag = 'Y' and ref_code1.expired_date is null 
order by aot.offence_type
}

LOV_PLEA {
SELECT code plea_code, description nbt_plea_code
FROM reference_codes
WHERE domain = 'PLEA_STATUS'
AND (active_flag = 'Y')
ORDER BY list_seq, description
}

UPDATE_COURT_EVENT {
UPDATE COURT_EVENTS
SET EVENT_DATE = :eventDate,
    START_TIME=:startTime,
    AGY_LOC_ID=:agyLocId,
    COURT_EVENT_TYPE=:hearingType,
    OUTCOME_REASON_CODE=:outcomeReasonCode,
    NEXT_EVENT_DATE=:nextEventDate,
    NEXT_EVENT_START_TIME=:nextEventStartTime,
    HOLD_FLAG=:holdFlag,
    ORDER_REQUESTED_FLAG=:orderRequestedFlag,
    COMMENT_TEXT=:commentText,
     MODIFY_DATETIME =current_timestamp,
	MODIFY_USER_ID = :modifyUserId
WHERE OFFENDER_BOOK_ID=:offenderBookId AND EVENT_ID=:eventId 
}

SENTENCE_DATA {
select SENTENCE_SEQ,
	   OFFENDER_BOOK_ID,
	   SENTENCE_CATEGORY,
	   SENTENCE_CALC_TYPE,
	   FINE_AMOUNT,
	   CONSEC_TO_SENTENCE_SEQ,
	   TO_CHAR(ORDER_ID) ORDER_ID, 
	   START_DATE,
	   END_DATE,
	   SENTENCE_STATUS,
	   CASE_ID,
	   STATUS_UPDATE_REASON,
       STATUS_UPDATE_DATE,
       STATUS_UPDATE_STAFF_ID,
       STATUS_UPDATE_COMMENT
from offender_sentences 
where offender_book_id=:offenderBookId and case_id=:caseId
}

LOV_SENTENCE_CATEGORY {
SELECT DISTINCT sent_calc.sentence_category sentence_category,
ref_code.description
FROM reference_codes ref_code, sentence_calc_types sent_calc
WHERE ref_code.domain = 'CATEGORY'
AND ref_code.code = sent_calc.sentence_category
AND sent_calc.active_flag = 'Y'
}

LOV_SENTENCE_TYPE {
SELECT description nbt_sentence_calc_type,
       sentence_calc_type
  FROM sentence_calc_types
 WHERE sentence_category = :SENTENCECATEGORY
   AND (active_flag = 'Y')
   AND sentence_calc_type NOT LIKE 'AGG%'
ORDER BY sentence_calc_type
}

LOV_CONSEC_TO_SENTENCE_SEQ {
--SELECT   os.line_seq,
--         os.sentence_seq,
--         os.end_date,
--         os.sentence_calc_type,
--         ocs.case_info_number,
--         stc.description
--    FROM offender_sentences os, 
--         sentence_calc_types stc, 
--         offender_cases ocs
--   WHERE os.offender_book_id = :offenderBookId
--     AND os.offender_book_id = ocs.offender_book_id
--     AND os.case_id = ocs.case_id
--     AND os.sentence_category = stc.sentence_category
--     AND os.sentence_calc_type = stc.sentence_calc_type
--     AND os.sentence_seq != NVL (:sentenceSeq, 0)
--     AND os.sentence_level = 'IND'
--     AND stc.sentence_type = 'INST'
--     AND os.sentence_category != '1967'
--     AND os.sentence_seq NOT IN (
--            SELECT     sentence_seq
--                  FROM offender_sentences
--            START WITH offender_book_id = :offenderBookId
--                   AND sentence_seq = NVL (:sentenceSeq, 0)
--            CONNECT BY PRIOR sentence_seq = consec_to_sentence_seq
--                   AND offender_book_id = :offenderBookId)
--ORDER BY os.end_date DESC
select os.line_seq, os.sentence_seq, os.end_date, os.sentence_calc_type, ocs.case_info_number, stc.description from offender_sentences os, sentence_calc_types stc, offender_cases ocs where os.offender_book_id = :offenderBookId and os.offender_book_id = ocs.offender_book_id and os.case_id = ocs.case_id and os.sentence_category = stc.sentence_category and os.sentence_calc_type = stc.sentence_calc_type and os.sentence_seq != coalesce(:sentenceSeq, 0) and os.sentence_level = 'IND' and stc.sentence_type = 'INST' and os.sentence_category != '1967' and os.sentence_seq not in (with recursive cte as ( select sentence_seq from offender_sentences where offender_book_id = :offenderBookId and sentence_seq = coalesce(:sentenceSeq, 0) union all select os.sentence_seq from offender_sentences os join cte c on (c.sentence_seq = consec_to_sentence_seq and offender_book_id = :offenderBookId)

) select * from cte

) order by os.end_date desc

--select
--	os.line_seq,
--	os.sentence_seq,
--	os.end_date,
--	os.sentence_calc_type,
--	ocs.case_info_number,
--	stc.description
--from
--	offender_sentences os,
--	sentence_calc_types stc,
--	offender_cases ocs
--where
--	os.offender_book_id = ocs.offender_book_id
--	and os.case_id = ocs.case_id
--	and os.sentence_category = stc.sentence_category
--	and os.sentence_calc_type = stc.sentence_calc_type
----	and os.sentence_level = 'IND'
----	and stc.sentence_type = 'INST'
----	and os.sentence_category != '1967'
--	and os.sentence_seq not in (with recursive cte as (
--	select
--		sentence_seq
--	from
--		offender_sentences
--	where
--		sentence_seq = coalesce(2, 0)
--union all
--	select
--		os.sentence_seq
--	from
--		offender_sentences os
--	join cte c on
--		(c.sentence_seq = consec_to_sentence_seq
--			) )
--	select
--		*
--	from
--		cte )
--order by
--	os.end_date desc

}

LOV_SENTENCE_DATE {
SELECT TO_CHAR(court_date,'DD/MM/RRRR') event_date,
       TO_CHAR(order_id) order_id
  FROM orders
 WHERE case_id = :caseId
   AND order_type = 'AUTO'
ORDER BY court_date desc
--SELECT TO_CHAR(court_date,'DD/MM/RRRR') event_date,
--       TO_CHAR(order_id) order_id
--  FROM orders
-- WHERE case_id = 14832
--   AND order_type = 'AUTO'
--ORDER BY court_date desc
}

LOV_SENTENCE_STATUS {
SELECT   description nbt_sentence_status, 
         code sentence_status	 
    FROM reference_codes 
   WHERE domain = 'ACTIVE_TYPE'
     AND active_flag = 'Y'      
ORDER BY description, list_seq
}

TERMS_DATA {
select 
oms_miscellaneous_getdesccode('SENT_TERM', sentence_term_code) NBT_SENTENCE_TERM_CODE,
SENTENCE_TERM_CODE,
LIFE_SENTENCE_FLAG,
YEARS,
MONTHS,
WEEKS,
DAYS,
HOURS,
START_DATE,
END_DATE,
OFFENDER_BOOK_ID,
SENTENCE_SEQ,
TERM_SEQ
from offender_sentence_terms terms
where terms.offender_book_id = :offenderBookId 
and terms.sentence_seq = :line 
order by term_seq
}

OCDCASE_NEWCASE_PREINSERT_CASE_SEQ{
select tag_legal_cases_generate_case_seq(:offenderBookId) from dual
}

OCDCASE_NEWCASE_PREINSERT_CASE_ID{
select tag_legal_cases_generate_case_id() from dual
}

INSERT_COURT_CASE {
insert into OFFENDER_CASES (CASE_SEQ, BEGIN_DATE, OFFENDER_BOOK_ID, AGY_LOC_ID, CASE_TYPE, CASE_INFO_PREFIX, CASE_INFO_NUMBER, CASE_STATUS, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME, CASE_ID) values (:case_Seq, :beginDate, :offenderBookId, :agy_loc_id, :caseType, :caseInfoPrefix, :caseInfoNumber, :caseStatus, :createUserId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :caseId)
}

LOV_TERM_CODE {
SELECT ref_code.description nbt_sentence_term_code,
            term_code sentence_term_code
            FROM sentence_terms st, reference_codes ref_code
            WHERE ref_code.domain = 'SENT_TERM'
            AND ref_code.code = st.term_code
            AND st.sentence_calc_type = :sentenceCalcType
            AND st.sentence_category = :sentenceCategory
            AND (st.active_flag = 'Y')
            ORDER BY sentence_term_code
}

UPDATE_COURT_CASE{
UPDATE OFFENDER_CASES
SET CASE_TYPE		  =:caseType,
	CASE_INFO_NUMBER  =:caseInfoNumber,
	CASE_INFO_PREFIX  = :caseInfoPrefix,
	CASE_STATUS		  =:caseStatus,
	AGY_LOC_ID		  =:agy_loc_id,
	BEGIN_DATE		  =:beginDate,
	STATUS_UPDATE_REASON      =:statusUpdateReason, 
	STATUS_UPDATE_COMMENT     =:statusUpdateComment, 
	STATUS_UPDATE_DATE        =:status_update_date,
	STATUS_UPDATE_STAFF_ID    =:status_update_staff_id,
	 MODIFY_DATETIME = CURRENT_TIMESTAMP,
		 MODIFY_USER_ID = :modifyUserId
WHERE OFFENDER_BOOK_ID=:offenderBookId AND CASE_ID=:caseId
}

OCDCASE_NEWEVENT_PREINSERT_EVENT_ID{
select tag_legal_cases_generate_event_id() from dual
}

OCDCASE_NEWCOURT_EVENT_GET_BOOKING_DATE {
select  tag_legal_cases_get_booking_start_date(:offenderBookId) from dual
}
INSERT_COURT_EVENT{
insert into COURT_EVENTS (EVENT_ID, CASE_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, COURT_EVENT_TYPE, AGY_LOC_ID, OUTCOME_REASON_CODE, NEXT_EVENT_DATE, NEXT_EVENT_START_TIME, HOLD_FLAG, ORDER_REQUESTED_FLAG, COMMENT_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, EVENT_STATUS, DIRECTION_CODE) values (:eventId, :caseId, :offenderBookId, :eventDate, :startTime, :hearingType, :agyLocId, :outcomeReasonCode, :nextEventDate, :nextEventStartTime, :holdFlag, :orderRequestedFlag, :commentText, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :eventStatus, 'OUT')
}
OFFENDER_OFFENCES {
	SELECT off_chg.offender_book_id,
      	cec.offender_charge_id,
      	(SELECT description FROM offences WHERE offence_code = off_chg.offence_code AND statute_code = off_chg.statute_code) offence_description,
      	cec.offence_date,
      	(SELECT description FROM offence_result_codes WHERE result_code = cec.result_code_1) result,
      	off_chg.offence_type offence_type
      	FROM offender_charges    off_chg,
          	court_event_charges cec
      	WHERE cec.offender_charge_id = off_chg.offender_charge_id
      	AND off_chg.offender_book_id = :offenderBookId
}

GET_BAIL_STATUS {
	SELECT code, description FROM reference_codes 
    WHERE domain = 'BAIL_STS'
    AND ((active_flag = 'Y' AND expired_date IS NULL))       
    ORDER BY list_seq,code 
}

GET_BAIL_BOND_TYPE {
	SELECT code, description
    FROM reference_codes 
    WHERE domain = 'BAIL_TYPE'
    AND ((active_flag = 'Y' AND expired_date IS NULL))
    ORDER BY list_seq,code
}

INSERT_BAIL_DETAILS {
	insert into OFFENDER_BAIL_DETAILS(OFFENDER_BOOK_ID, OFFENDER_CHARGE_ID, BAIL_STATUS, CASH_FLAG, CASH, SURETY, PROPERTY, PERFECTED_DATETIME, PERFECTED_BY , method, COMMENT_TEXT, BAIL_DATE, JUDGE, RECEIPT_REFERENCE_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG ) values(:bookId, :chargeId, :bailStatus, :cashFlag, :cash, :surety, :property, :preferedDateTime, :preferedBy, :method, :commentText, :BailDate, :judge, :receiptText, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :sealFlag)
}

POPULATE_OFFENDER_OFFENSES {
	select OFFENDER_BOOK_ID, CASE_ID, OFFENDER_CHARGE_ID,OFFENCE_CODE, STATUTE_CODE, OFFENCE_DESC  
    from V_BAIL_INFO where CASE_ID = :caseId
}

UPDATE_BAIL_DETAILS {
   UPDATE OFFENDER_BAIL_DETAILS
   SET BAIL_STATUS = :bailStatus,
       CASH_FLAG = :cashFlag,
       CASH		= :cash,
       SURETY	= :surety,
       PROPERTY	= :property,
       PERFECTED_DATETIME = :preferedDateTime,
       PERFECTED_BY	 = :preferedBy,
       METHOD		= :method,
       COMMENT_TEXT	= :commentText,
       BAIL_DATE  = :BailDate,
       JUDGE  = :judge,
       RECEIPT_REFERENCE_TEXT = :receiptText,
      MODIFY_DATETIME = CURRENT_TIMESTAMP,
		 MODIFY_USER_ID = :modifyUserId
       SEAL_FLAG = :sealFlag
       WHERE OFFENDER_BOOK_ID = :bookId AND OFFENDER_CHARGE_ID = :chargeId
       
}

UPDATE_BOND_DETAILS {
   UPDATE OFFENDER_BAIL_DETAILS
   SET BAIL_STATUS = :bailStatus,
       CASH_FLAG = :cashFlag,      
       PERFECTED_DATETIME = :preferedDateTime,
       PERFECTED_BY	 = :preferedBy,
       METHOD		= :method,
       COMMENT_TEXT	= :commentText,
       RECEIPT_REFERENCE_TEXT = :receiptText,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId       
       WHERE OFFENDER_BOOK_ID = :bookId AND OFFENDER_CHARGE_ID = :chargeId
}

GET_BAIL_DETAILS {
    SELECT  V_BAIL.OFFENDER_BOOK_ID OFFENDER_BOOK_ID,
	V_BAIL.OFFENDER_CHARGE_ID OFFENDER_CHARGE_ID,
   (select OFF_BAIL.OFFENDER_CHARGE_ID  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) BAIL_OFFENDER_CHARGE_ID,
    (select OFF_BAIL.OFFENDER_BOOK_ID  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) BAIL_OFFENDER_BOOK_ID,
    V_BAIL.OFFENDER_BOOK_ID,
   (select OFF_BAIL.BAIL_STATUS  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) BAIL_STATUS,
	(select OFF_BAIL.CASH_FLAG  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  CASH_FLAG,
    (select OFF_BAIL.CASH  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) CASH,
    (select OFF_BAIL.RECEIPT_REFERENCE_TEXT  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  RECEIPT_REFERENCE_TEXT,
    (select OFF_BAIL.COMMENT_TEXT  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  COMMENT_TEXT,
    (select OFF_BAIL.BAIL_DATE  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  BAIL_DATE,
    (select OFF_BAIL.PERFECTED_DATETIME  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  PERFECTED_DATETIME,
    (select OFF_BAIL.PERFECTED_BY  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  PERFECTED_BY,
    (select OFF_BAIL.METHOD  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  as METHOD,
    (select OFF_BAIL.SURETY  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  SURETY,
    (select OFF_BAIL.PROPERTY  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  PROPERTY,
    (select OFF_BAIL.JUDGE  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  JUDGE,
	V_BAIL.OFFENCE_DESC
	FROM V_BAIL_INFO V_BAIL where 
	V_BAIL.OFFENDER_BOOK_ID = :bookId and V_BAIL.CASE_ID = :caseId          
}

OCDCASE_PREINSERT_SENTENCE_SEQ {
select tag_legal_cases_generate_sentence_seq(:offenderBookId) from dual
}

OCDCASE_PREINSERT_LINE_SEQ {
select tag_legal_cases_generate_sentence_line_seq(:offenderBookId) from dual
}

INSERT_OFFENDER_SENTENCE_RECORD {
insert into OFFENDER_SENTENCES(OFFENDER_BOOK_ID, SENTENCE_SEQ, ORDER_ID, SENTENCE_CALC_TYPE, SENTENCE_STATUS, CONSEC_TO_SENTENCE_SEQ, START_DATE, CASE_ID, SENTENCE_CATEGORY, FINE_AMOUNT, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SENTENCE_LEVEL , LINE_SEQ) values(:offenderBookId, :sentenceSeq, case when coalesce(:orderId,'') != '' then TO_NUMBER(:orderId::text,'99G999D9S'::text) else null end, :sentenceCalcType, :status, :consecutiveToLine, :startDate, :caseId, :category, :fineAmount, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :sentenceLevel, :line)
}


GENERATE_TERM_SEQ {
select tag_legal_cases_generate_term_seq(:offenderBookId,:sentenceSeq) from dual
}

INSERT_OFFENDER_SENTENCE_TERM {
insert into OFFENDER_SENTENCE_TERMS(OFFENDER_BOOK_ID, SENTENCE_SEQ, TERM_SEQ, SENTENCE_TERM_CODE, YEARS, MONTHS, WEEKS, DAYS, HOURS, START_DATE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME ) values(:offenderBookId, :sentenceSeq, :termSeq, :sentenceTermCode, :years, :months, :weeks, :days, :hours, :startDate, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP )
}

UPDATE_SENTENCE_TERMS {
update offender_sentence_terms
            set SENTENCE_TERM_CODE=:sentenceTermCode,
                years=:years,
            	months=:months,
            	weeks=:weeks,
            	days=:days,
            	hours=:hours,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId 
            where offender_book_id=:offenderBookId and term_seq=:termSeq and SENTENCE_SEQ = :sentenceSeq
}

INSERT_OFFENDER_OFFENSES_CHARGES {
 insert into offender_sentence_charges (OFFENDER_BOOK_ID, SENTENCE_SEQ, OFFENDER_CHARGE_ID, CREATE_DATETIME , CREATE_USER_ID, MODIFY_DATETIME) values (:offenderBookId, :sentenceSeq, :offenderChargeId, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP) )
}

OCDCASE_FETCH_STATUTE_CODE {
SELECT distinct statute_code 
FROM offences 
WHERE OFFENCE_CODE = :offenceCode
}

INSERT_OFFENDER_OFFENSES{
 insert
	into
	offender_charges (offender_book_id,
	offender_charge_id,
	statute_code,
	offence_code,
	offence_date,
	offence_range_date,
	plea_code,
	property_value,
	total_property_value,
	cjit_offence_code_1,
	cjit_offence_code_2,
	cjit_offence_code_3,
	charge_status,
	result_code_1,
	result_code_2,
	result_code_1_indicator,
	result_code_2_indicator,
	no_of_offences,
	case_id,
	most_serious_flag,
	offence_type,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME)
values (:offenderBookId,
:offenderChargeId,
:statuteCode,
:offenceCode,
:offenseDate,
:range,
:plea,
:propertyValue,
:totalpropertyvalue,
:cjitoffencecode1,
:cjitoffencecode2,
:cjitoffencecode3,
:chargeStatus,
:resultcode1,
:resultcode2,
:resultcode1indicator,
:resultcode2indicator,
:noofoffences,
:caseId,
:mostseriousflag,
:offenseType,
CURRENT_TIMESTAMP,
:createUserId,
CURRENT_TIMESTAMP)
           
}

INSERT_COURT_EVENT_CHARGES {
insert
	into
	court_event_charges (event_id,
	offender_charge_id,
	plea_code,
	result_code_1,
	result_code_2,
	result_code_1_indicator,
	result_code_2_indicator,
	property_value,
	most_serious_flag,
	no_of_offences,
	total_property_value,
	offence_date,
	offence_range_date,
	cjit_offence_code_1,
	cjit_offence_code_2,
	cjit_offence_code_3,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME)
values (:eventId,
:offenderChargeId,
:plea,
:resultcode1,
:resultcode2,
:resultcode1indicator,
:resultcode2indicator,
:propertyValue,
:mostseriousflag,
:noofoffences,
:totalpropertyvalue,
:offenseDate,
:range,
:cjitoffencecode1,
:cjitoffencecode2,
:cjitoffencecode3,
CURRENT_TIMESTAMP,
:createUserId,
CURRENT_TIMESTAMP)
}

IS_ORDER_EXIST {
 SELECT 'X' FROM orders
 WHERE offender_book_id = :offenderBookId
 AND event_id = :eventId
}


OCDCCASE_PREINSERT_OFFENDER_CHARGE_ID {
	SELECT NEXTVAL('offender_charge_id') FROM DUAL
}

UPDATE_COURT_EVENT_CHARGES{
 UPDATE court_event_charges
         SET most_serious_flag       = 'N',
             no_of_offences          = :noofoffences,
             plea_code               = :plea,
             property_value          = :propertyValue,
             total_property_value    = :totalpropertyvalue,
             result_code_1           = :resultcode1,
             result_code_2           = :resultcode2,
             result_code_1_indicator = :resultcode1indicator,
             result_code_2_indicator = :resultcode2indicator,
             offence_date            = :offenseDate,
             offence_range_date      = :range,
             cjit_offence_code_1     = :cjitoffencecode1,
             cjit_offence_code_2     = :cjitoffencecode2,
             cjit_offence_code_3     = :cjitoffencecode3,
       MODIFY_DATETIME = CURRENT_TIMESTAMP
       WHERE event_id = :eventId
			 AND offender_charge_id = :offenderChargeId
}

UPDATE_OFFENDER_CHARGES {
 UPDATE offender_charges
            SET no_of_offences          = :noofoffences,
                offence_date            = :offenseDate,
                offence_range_date      = :range,
                plea_code               = :plea,
                property_value          = :propertyValue,
                total_property_value    = :totalpropertyvalue,
                cjit_offence_code_1     = :cjitoffencecode1,
                cjit_offence_code_2     = :cjitoffencecode2,
                cjit_offence_code_3     = :cjitoffencecode3,
                charge_status           = :chargeStatus,
                result_code_1           = :resultcode1,
                result_code_2           = :resultcode2,
                result_code_1_indicator = :resultcode1indicator,
                result_code_2_indicator = :resultcode2indicator,
                most_serious_flag       = 'N',
                offence_type            = :offenseType,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId 
          WHERE offender_charge_id =:offenderChargeId
}

IS_EVENT_CHARGES_FINAL_ACTIVE {
	 SELECT 'X'
           FROM dual
          WHERE EXISTS (SELECT 'X'
                   FROM court_event_charges  c,
                        offence_result_codes o
                  WHERE c.event_id = :eventId
                    AND c.result_code_1 = o.result_code
                    AND c.result_code_1_indicator IN ('P', 'F')
                    AND o.charge_status = 'A')
}

GET_EVENT_CHARGES_COUNT{
select tag_legal_cases_get_event_charges_count(:offenderChargeId) from dual
}

FETCH_SENTENCE_START_DATE {
SELECT TO_CHAR(court_date,'DD/MM/RRRR') event_date
FROM orders
WHERE order_id = :orderId
AND order_type = 'AUTO'
}

OCDCASE_GET_ACTIVE_AGY_LOC {
select tag_establishment_get_active_agy_loc_desc(:agyLocation) from dual
}

UPDATE_SENTENCE_RECORD{
	UPDATE OFFENDER_SENTENCES SET
       FINE_AMOUNT=:fineAmount,
       CONSEC_TO_SENTENCE_SEQ=:consecutiveToLine,
       ORDER_ID=:orderId,
       START_DATE=:startDate,
       SENTENCE_STATUS=:status,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId 
 WHERE OFFENDER_BOOK_ID=:offenderBookId AND SENTENCE_SEQ=:line
}
OCDCCASE_FETCH_EVENTS {
	select * from COURT_EVENTS where CASE_ID = :courtCaseId Order by event_date desc
}

GET_USER_NAME{
SELECT user 
}
DISPLAY_COURT_CASES {
select A.CASE_SEQ,
       A.CASE_ID,
       A.OFFENDER_BOOK_ID,
       A.AGY_LOC_ID,
       A.BEGIN_DATE,
       A.CASE_TYPE,
       A.CASE_INFO_PREFIX,
       A.CASE_INFO_NUMBER,
       A.CASE_STATUS,
       A.COMBINED_CASE_ID,
       A.STATUS_UPDATE_REASON,
       A.STATUS_UPDATE_DATE,
       (SELECT B.CASE_SEQ  FROM OFFENDER_CASES B WHERE B.OFFENDER_BOOK_ID=A.OFFENDER_BOOK_ID  AND B.CASE_ID =A.COMBINED_CASE_ID
       ) LINK_CASE_SEQ
        FROM OFFENDER_CASES A
        WHERE A.OFFENDER_BOOK_ID= :offenderBookId
        ORDER BY oms_miscellaneous_getdesccode('ACTIVE_TYPE', :caseStatus) ASC,begin_date DESC
}

GET_PREINSERT_CASE_TYPE {
select tag_reference_codes_default_domain('LEG_CASE_TYP') from dual
}

GET_PREINSERT_AGY_LOCATION{
select tag_legal_cases_get_latest_hearing_agency(:OFFENDERBOOKID) from dual
}

FETCH_PREINSERT_CASEPREFIX{
 select CODE, DESCRIPTION from reference_codes where domain = 'CASE_ID_TYPE' and code in ( select tag_legal_cases_get_case_prefix(:CASETYPE) casePrefix from dual)
}


CASE_STATUS {
SELECT   description nbt_case_status, 
         code case_status	 
    FROM reference_codes 
   WHERE domain = 'ACTIVE_TYPE'
     AND (active_flag = 'Y')      
ORDER BY description, list_seq
}

COURT_EVENTS {
select 
OFFENDER_BOOK_ID,
CASE_ID,
EVENT_ID,
EVENT_DATE,
START_TIME,
AGY_LOC_ID,
COURT_EVENT_TYPE,
OUTCOME_REASON_CODE,
NEXT_EVENT_DATE,
NEXT_EVENT_START_TIME,
HOLD_FLAG,
ORDER_REQUESTED_FLAG,
COMMENT_TEXT
from court_events where case_id=:caseId
order by event_date desc,start_time desc
}


LOV_HEARING_TYPE {
SELECT description nbt_court_event_type,
       code court_event_type
  FROM reference_codes
 WHERE domain = 'MOVE_RSN'
   AND parent_code = 'CRT'
   AND (active_flag = 'Y')
ORDER BY list_seq, description
}

LOV_OUTCOME{
select Description , Result_code from OFFENCE_RESULT_CODES
Where ACTIVE_FLAG = 'Y'
}

LOV_OFFENCE_TYPE{
select
	ref_code1.description  description ,
	aot.offence_type code
from
	allowable_offence_types aot,
	reference_codes ref_code1
where
	aot.offence_code = :offenceCode
	and ref_code1.domain = 'OFFENCE_TYPE'
	and ref_code1.code = aot.offence_type
	and ref_code1.active_flag = 'Y'
	and ref_code1.expired_date is null
order by
	aot.offence_type
}

OFFENCE_TYPE{
select distinct ref_code1.description DSP_DESCRIPTION3,aot.offence_type 
from allowable_offence_types aot, reference_codes ref_code1 
where ref_code1.domain = 'OFFENCE_TYPE' and ref_code1.code = aot.offence_type 
and ref_code1.active_flag = 'Y' and ref_code1.expired_date is null 
order by aot.offence_type
}

LOV_PLEA {
SELECT code plea_code, description nbt_plea_code
FROM reference_codes
WHERE domain = 'PLEA_STATUS'
AND (active_flag = 'Y')
ORDER BY list_seq, description
}

UPDATE_COURT_EVENT {
UPDATE COURT_EVENTS
SET EVENT_DATE = :eventDate,
    START_TIME=:startTime,
    AGY_LOC_ID=:agyLocId,
    COURT_EVENT_TYPE=:hearingType,
    OUTCOME_REASON_CODE=:outcomeReasonCode,
    NEXT_EVENT_DATE=:nextEventDate,
    NEXT_EVENT_START_TIME=:nextEventStartTime,
    HOLD_FLAG=:holdFlag,
    ORDER_REQUESTED_FLAG=:orderRequestedFlag,
    COMMENT_TEXT=:commentText,
     MODIFY_DATETIME =current_timestamp,
	MODIFY_USER_ID = :modifyUserId
WHERE OFFENDER_BOOK_ID=:offenderBookId AND EVENT_ID=:eventId 
}

SENTENCE_DATA {
select SENTENCE_SEQ,
	   OFFENDER_BOOK_ID,
	   SENTENCE_CATEGORY,
	   SENTENCE_CALC_TYPE,
	   FINE_AMOUNT,
	   CONSEC_TO_SENTENCE_SEQ,
	   TO_CHAR(ORDER_ID) ORDER_ID, 
	   START_DATE,
	   END_DATE,
	   SENTENCE_STATUS,
	   CASE_ID,
	   STATUS_UPDATE_REASON,
       STATUS_UPDATE_DATE,
       STATUS_UPDATE_STAFF_ID,
       STATUS_UPDATE_COMMENT
from offender_sentences 
where offender_book_id=:offenderBookId and case_id=:caseId
}

LOV_SENTENCE_CATEGORY {
SELECT DISTINCT sent_calc.sentence_category sentence_category,
ref_code.description
FROM reference_codes ref_code, sentence_calc_types sent_calc
WHERE ref_code.domain = 'CATEGORY'
AND ref_code.code = sent_calc.sentence_category
AND sent_calc.active_flag = 'Y'
}

LOV_SENTENCE_TYPE {
SELECT description nbt_sentence_calc_type,
       sentence_calc_type
  FROM sentence_calc_types
 WHERE sentence_category = :SENTENCECATEGORY
   AND (active_flag = 'Y')
   AND sentence_calc_type NOT LIKE 'AGG%'
ORDER BY sentence_calc_type
}

LOV_CONSEC_TO_SENTENCE_SEQ {
--SELECT   os.line_seq,
--         os.sentence_seq,
--         os.end_date,
--         os.sentence_calc_type,
--         ocs.case_info_number,
--         stc.description
--    FROM offender_sentences os, 
--         sentence_calc_types stc, 
--         offender_cases ocs
--   WHERE os.offender_book_id = :offenderBookId
--     AND os.offender_book_id = ocs.offender_book_id
--     AND os.case_id = ocs.case_id
--     AND os.sentence_category = stc.sentence_category
--     AND os.sentence_calc_type = stc.sentence_calc_type
--     AND os.sentence_seq != NVL (:sentenceSeq, 0)
--     AND os.sentence_level = 'IND'
--     AND stc.sentence_type = 'INST'
--     AND os.sentence_category != '1967'
--     AND os.sentence_seq NOT IN (
--            SELECT     sentence_seq
--                  FROM offender_sentences
--            START WITH offender_book_id = :offenderBookId
--                   AND sentence_seq = NVL (:sentenceSeq, 0)
--            CONNECT BY PRIOR sentence_seq = consec_to_sentence_seq
--                   AND offender_book_id = :offenderBookId)
--ORDER BY os.end_date DESC
select os.line_seq, os.sentence_seq, os.end_date, os.sentence_calc_type, ocs.case_info_number, stc.description from offender_sentences os, sentence_calc_types stc, offender_cases ocs where os.offender_book_id = :offenderBookId and os.offender_book_id = ocs.offender_book_id and os.case_id = ocs.case_id and os.sentence_category = stc.sentence_category and os.sentence_calc_type = stc.sentence_calc_type and os.sentence_seq != coalesce(:sentenceSeq, 0) and os.sentence_level = 'IND' and stc.sentence_type = 'INST' and os.sentence_category != '1967' and os.sentence_seq not in (with recursive cte as ( select sentence_seq from offender_sentences where offender_book_id = :offenderBookId and sentence_seq = coalesce(:sentenceSeq, 0) union all select os.sentence_seq from offender_sentences os join cte c on (c.sentence_seq = consec_to_sentence_seq and offender_book_id = :offenderBookId)

) select * from cte

) order by os.end_date desc

--select
--	os.line_seq,
--	os.sentence_seq,
--	os.end_date,
--	os.sentence_calc_type,
--	ocs.case_info_number,
--	stc.description
--from
--	offender_sentences os,
--	sentence_calc_types stc,
--	offender_cases ocs
--where
--	os.offender_book_id = ocs.offender_book_id
--	and os.case_id = ocs.case_id
--	and os.sentence_category = stc.sentence_category
--	and os.sentence_calc_type = stc.sentence_calc_type
----	and os.sentence_level = 'IND'
----	and stc.sentence_type = 'INST'
----	and os.sentence_category != '1967'
--	and os.sentence_seq not in (with recursive cte as (
--	select
--		sentence_seq
--	from
--		offender_sentences
--	where
--		sentence_seq = coalesce(2, 0)
--union all
--	select
--		os.sentence_seq
--	from
--		offender_sentences os
--	join cte c on
--		(c.sentence_seq = consec_to_sentence_seq
--			) )
--	select
--		*
--	from
--		cte )
--order by
--	os.end_date desc

}

LOV_SENTENCE_DATE {
SELECT TO_CHAR(court_date,'DD/MM/RRRR') event_date,
       TO_CHAR(order_id) order_id
  FROM orders
 WHERE case_id = :caseId
   AND order_type = 'AUTO'
ORDER BY court_date desc
--SELECT TO_CHAR(court_date,'DD/MM/RRRR') event_date,
--       TO_CHAR(order_id) order_id
--  FROM orders
-- WHERE case_id = 14832
--   AND order_type = 'AUTO'
--ORDER BY court_date desc
}

LOV_SENTENCE_STATUS {
SELECT   description nbt_sentence_status, 
         code sentence_status	 
    FROM reference_codes 
   WHERE domain = 'ACTIVE_TYPE'
     AND active_flag = 'Y'      
ORDER BY description, list_seq
}

TERMS_DATA {
select 
oms_miscellaneous_getdesccode('SENT_TERM', sentence_term_code) NBT_SENTENCE_TERM_CODE,
SENTENCE_TERM_CODE,
LIFE_SENTENCE_FLAG,
YEARS,
MONTHS,
WEEKS,
DAYS,
HOURS,
START_DATE,
END_DATE,
OFFENDER_BOOK_ID,
SENTENCE_SEQ,
TERM_SEQ
from offender_sentence_terms terms
where terms.offender_book_id = :offenderBookId 
and terms.sentence_seq = :line 
order by term_seq
}

OCDCASE_NEWCASE_PREINSERT_CASE_SEQ{
select tag_legal_cases_generate_case_seq(:offenderBookId) from dual
}

OCDCASE_NEWCASE_PREINSERT_CASE_ID{
select tag_legal_cases_generate_case_id() from dual
}

INSERT_COURT_CASE {
insert into OFFENDER_CASES (CASE_SEQ, BEGIN_DATE, OFFENDER_BOOK_ID, AGY_LOC_ID, CASE_TYPE, CASE_INFO_PREFIX, CASE_INFO_NUMBER, CASE_STATUS, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME, CASE_ID) values (:case_Seq, :beginDate, :offenderBookId, :agy_loc_id, :caseType, :caseInfoPrefix, :caseInfoNumber, :caseStatus, :createUserId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :caseId)
}

LOV_TERM_CODE {
SELECT ref_code.description nbt_sentence_term_code,
            term_code sentence_term_code
            FROM sentence_terms st, reference_codes ref_code
            WHERE ref_code.domain = 'SENT_TERM'
            AND ref_code.code = st.term_code
            AND st.sentence_calc_type = :sentenceCalcType
            AND st.sentence_category = :sentenceCategory
            AND (st.active_flag = 'Y')
            ORDER BY sentence_term_code
}

UPDATE_COURT_CASE{
UPDATE OFFENDER_CASES
SET CASE_TYPE		  =:caseType,
	CASE_INFO_NUMBER  =:caseInfoNumber,
	CASE_INFO_PREFIX  = :caseInfoPrefix,
	CASE_STATUS		  =:caseStatus,
	AGY_LOC_ID		  =:agy_loc_id,
	BEGIN_DATE		  =:beginDate,
	STATUS_UPDATE_REASON      =:statusUpdateReason, 
	STATUS_UPDATE_COMMENT     =:statusUpdateComment, 
	STATUS_UPDATE_DATE        =:status_update_date,
	STATUS_UPDATE_STAFF_ID    =:status_update_staff_id,
	 MODIFY_DATETIME = CURRENT_TIMESTAMP,
		 MODIFY_USER_ID = :modifyUserId
WHERE OFFENDER_BOOK_ID=:offenderBookId AND CASE_ID=:caseId
}

OCDCASE_NEWEVENT_PREINSERT_EVENT_ID{
select tag_legal_cases_generate_event_id() from dual
}

OCDCASE_NEWCOURT_EVENT_GET_BOOKING_DATE {
select  tag_legal_cases_get_booking_start_date(:offenderBookId) from dual
}
INSERT_COURT_EVENT{
insert into COURT_EVENTS (EVENT_ID, CASE_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, COURT_EVENT_TYPE, AGY_LOC_ID, OUTCOME_REASON_CODE, NEXT_EVENT_DATE, NEXT_EVENT_START_TIME, HOLD_FLAG, ORDER_REQUESTED_FLAG, COMMENT_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, EVENT_STATUS, DIRECTION_CODE) values (:eventId, :caseId, :offenderBookId, :eventDate, :startTime, :hearingType, :agyLocId, :outcomeReasonCode, :nextEventDate, :nextEventStartTime, :holdFlag, :orderRequestedFlag, :commentText, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :eventStatus, 'OUT')
}
OFFENDER_OFFENCES {
	SELECT off_chg.offender_book_id,
      	cec.offender_charge_id,
      	(SELECT description FROM offences WHERE offence_code = off_chg.offence_code AND statute_code = off_chg.statute_code) offence_description,
      	cec.offence_date,
      	(SELECT description FROM offence_result_codes WHERE result_code = cec.result_code_1) result,
      	off_chg.offence_type offence_type
      	FROM offender_charges    off_chg,
          	court_event_charges cec
      	WHERE cec.offender_charge_id = off_chg.offender_charge_id
      	AND off_chg.offender_book_id = :offenderBookId
}

GET_BAIL_STATUS {
	SELECT code, description FROM reference_codes 
    WHERE domain = 'BAIL_STS'
    AND ((active_flag = 'Y' AND expired_date IS NULL))       
    ORDER BY list_seq,code 
}

GET_BAIL_BOND_TYPE {
	SELECT code, description
    FROM reference_codes 
    WHERE domain = 'BAIL_TYPE'
    AND ((active_flag = 'Y' AND expired_date IS NULL))
    ORDER BY list_seq,code
}

INSERT_BAIL_DETAILS {
	insert into OFFENDER_BAIL_DETAILS(OFFENDER_BOOK_ID, OFFENDER_CHARGE_ID, BAIL_STATUS, CASH_FLAG, CASH, SURETY, PROPERTY, PERFECTED_DATETIME, PERFECTED_BY , method, COMMENT_TEXT, BAIL_DATE, JUDGE, RECEIPT_REFERENCE_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG ) values(:bookId, :chargeId, :bailStatus, :cashFlag, :cash, :surety, :property, :preferedDateTime, :preferedBy, :method, :commentText, :BailDate, :judge, :receiptText, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :sealFlag)
}

POPULATE_OFFENDER_OFFENSES {
	select OFFENDER_BOOK_ID, CASE_ID, OFFENDER_CHARGE_ID,OFFENCE_CODE, STATUTE_CODE, OFFENCE_DESC  
    from V_BAIL_INFO where CASE_ID = :caseId
}

UPDATE_BAIL_DETAILS {
   UPDATE OFFENDER_BAIL_DETAILS
   SET BAIL_STATUS = :bailStatus,
       CASH_FLAG = :cashFlag,
       CASH		= :cash,
       SURETY	= :surety,
       PROPERTY	= :property,
       PERFECTED_DATETIME = :preferedDateTime,
       PERFECTED_BY	 = :preferedBy,
       METHOD		= :method,
       COMMENT_TEXT	= :commentText,
       BAIL_DATE  = :BailDate,
       JUDGE  = :judge,
       RECEIPT_REFERENCE_TEXT = :receiptText,
      MODIFY_DATETIME = CURRENT_TIMESTAMP,
		 MODIFY_USER_ID = :modifyUserId
       SEAL_FLAG = :sealFlag
       WHERE OFFENDER_BOOK_ID = :bookId AND OFFENDER_CHARGE_ID = :chargeId
       
}

UPDATE_BOND_DETAILS {
   UPDATE OFFENDER_BAIL_DETAILS
   SET BAIL_STATUS = :bailStatus,
       CASH_FLAG = :cashFlag,      
       PERFECTED_DATETIME = :preferedDateTime,
       PERFECTED_BY	 = :preferedBy,
       METHOD		= :method,
       COMMENT_TEXT	= :commentText,
       RECEIPT_REFERENCE_TEXT = :receiptText,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId       
       WHERE OFFENDER_BOOK_ID = :bookId AND OFFENDER_CHARGE_ID = :chargeId
}

GET_BAIL_DETAILS {
    SELECT  V_BAIL.OFFENDER_BOOK_ID OFFENDER_BOOK_ID,
	V_BAIL.OFFENDER_CHARGE_ID OFFENDER_CHARGE_ID,
   (select OFF_BAIL.OFFENDER_CHARGE_ID  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) BAIL_OFFENDER_CHARGE_ID,
    (select OFF_BAIL.OFFENDER_BOOK_ID  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) BAIL_OFFENDER_BOOK_ID,
    V_BAIL.OFFENDER_BOOK_ID,
   (select OFF_BAIL.BAIL_STATUS  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) BAIL_STATUS,
	(select OFF_BAIL.CASH_FLAG  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  CASH_FLAG,
    (select OFF_BAIL.CASH  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID) CASH,
    (select OFF_BAIL.RECEIPT_REFERENCE_TEXT  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  RECEIPT_REFERENCE_TEXT,
    (select OFF_BAIL.COMMENT_TEXT  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  COMMENT_TEXT,
    (select OFF_BAIL.BAIL_DATE  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  BAIL_DATE,
    (select OFF_BAIL.PERFECTED_DATETIME  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  PERFECTED_DATETIME,
    (select OFF_BAIL.PERFECTED_BY  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  PERFECTED_BY,
    (select OFF_BAIL.METHOD  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  as METHOD,
    (select OFF_BAIL.SURETY  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  SURETY,
    (select OFF_BAIL.PROPERTY  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  PROPERTY,
    (select OFF_BAIL.JUDGE  from OFFENDER_BAIL_DETAILS OFF_BAIL where OFF_BAIL.OFFENDER_CHARGE_ID = V_BAIL.OFFENDER_CHARGE_ID)  JUDGE,
	V_BAIL.OFFENCE_DESC
	FROM V_BAIL_INFO V_BAIL where 
	V_BAIL.OFFENDER_BOOK_ID = :bookId and V_BAIL.CASE_ID = :caseId          
}

OCDCASE_PREINSERT_SENTENCE_SEQ {
select tag_legal_cases_generate_sentence_seq(:offenderBookId) from dual
}

OCDCASE_PREINSERT_LINE_SEQ {
select tag_legal_cases_generate_sentence_line_seq(:offenderBookId) from dual
}

INSERT_OFFENDER_SENTENCE_RECORD {
insert into OFFENDER_SENTENCES(OFFENDER_BOOK_ID, SENTENCE_SEQ, ORDER_ID, SENTENCE_CALC_TYPE, SENTENCE_STATUS, CONSEC_TO_SENTENCE_SEQ, START_DATE, CASE_ID, SENTENCE_CATEGORY, FINE_AMOUNT, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SENTENCE_LEVEL , LINE_SEQ) values(:offenderBookId, :sentenceSeq, case when coalesce(:orderId,'') != '' then TO_NUMBER(:orderId::text,'99G999D9S'::text) else null end, :sentenceCalcType, :status, :consecutiveToLine, :startDate, :caseId, :category, :fineAmount, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :sentenceLevel, :line)
}


GENERATE_TERM_SEQ {
select tag_legal_cases_generate_term_seq(:offenderBookId,:sentenceSeq) from dual
}

INSERT_OFFENDER_SENTENCE_TERM {
insert into OFFENDER_SENTENCE_TERMS(OFFENDER_BOOK_ID, SENTENCE_SEQ, TERM_SEQ, SENTENCE_TERM_CODE, YEARS, MONTHS, WEEKS, DAYS, HOURS, START_DATE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME ) values(:offenderBookId, :sentenceSeq, :termSeq, :sentenceTermCode, :years, :months, :weeks, :days, :hours, :startDate, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP )
}

UPDATE_SENTENCE_TERMS {
update offender_sentence_terms
            set SENTENCE_TERM_CODE=:sentenceTermCode,
                years=:years,
            	months=:months,
            	weeks=:weeks,
            	days=:days,
            	hours=:hours,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId 
            where offender_book_id=:offenderBookId and term_seq=:termSeq and SENTENCE_SEQ = :sentenceSeq
}

INSERT_OFFENDER_OFFENSES_CHARGES {
 insert into offender_sentence_charges (OFFENDER_BOOK_ID, SENTENCE_SEQ, OFFENDER_CHARGE_ID, CREATE_DATETIME , CREATE_USER_ID, MODIFY_DATETIME) values (:offenderBookId, :sentenceSeq, :offenderChargeId, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP) )
}

OCDCASE_FETCH_STATUTE_CODE {
SELECT distinct statute_code 
FROM offences 
WHERE OFFENCE_CODE = :offenceCode
}

INSERT_OFFENDER_OFFENSES{
 insert
	into
	offender_charges (offender_book_id,
	offender_charge_id,
	statute_code,
	offence_code,
	offence_date,
	offence_range_date,
	plea_code,
	property_value,
	total_property_value,
	cjit_offence_code_1,
	cjit_offence_code_2,
	cjit_offence_code_3,
	charge_status,
	result_code_1,
	result_code_2,
	result_code_1_indicator,
	result_code_2_indicator,
	no_of_offences,
	case_id,
	most_serious_flag,
	offence_type,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME)
values (:offenderBookId,
:offenderChargeId,
:statuteCode,
:offenceCode,
:offenseDate,
:range,
:plea,
:propertyValue,
:totalpropertyvalue,
:cjitoffencecode1,
:cjitoffencecode2,
:cjitoffencecode3,
:chargeStatus,
:resultcode1,
:resultcode2,
:resultcode1indicator,
:resultcode2indicator,
:noofoffences,
:caseId,
:mostseriousflag,
:offenseType,
CURRENT_TIMESTAMP,
:createUserId,
CURRENT_TIMESTAMP)
           
}

INSERT_COURT_EVENT_CHARGES {
insert
	into
	court_event_charges (event_id,
	offender_charge_id,
	plea_code,
	result_code_1,
	result_code_2,
	result_code_1_indicator,
	result_code_2_indicator,
	property_value,
	most_serious_flag,
	no_of_offences,
	total_property_value,
	offence_date,
	offence_range_date,
	cjit_offence_code_1,
	cjit_offence_code_2,
	cjit_offence_code_3,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME)
values (:eventId,
:offenderChargeId,
:plea,
:resultcode1,
:resultcode2,
:resultcode1indicator,
:resultcode2indicator,
:propertyValue,
:mostseriousflag,
:noofoffences,
:totalpropertyvalue,
:offenseDate,
:range,
:cjitoffencecode1,
:cjitoffencecode2,
:cjitoffencecode3,
CURRENT_TIMESTAMP,
:createUserId,
CURRENT_TIMESTAMP)
}

IS_ORDER_EXIST {
 SELECT 'X' FROM orders
 WHERE offender_book_id = :offenderBookId
 AND event_id = :eventId
}


OCDCCASE_PREINSERT_OFFENDER_CHARGE_ID {
	SELECT NEXTVAL('offender_charge_id') FROM DUAL
}

UPDATE_COURT_EVENT_CHARGES{
 UPDATE court_event_charges
         SET most_serious_flag       = 'N',
             no_of_offences          = :noofoffences,
             plea_code               = :plea,
             property_value          = :propertyValue,
             total_property_value    = :totalpropertyvalue,
             result_code_1           = :resultcode1,
             result_code_2           = :resultcode2,
             result_code_1_indicator = :resultcode1indicator,
             result_code_2_indicator = :resultcode2indicator,
             offence_date            = :offenseDate,
             offence_range_date      = :range,
             cjit_offence_code_1     = :cjitoffencecode1,
             cjit_offence_code_2     = :cjitoffencecode2,
             cjit_offence_code_3     = :cjitoffencecode3,
       MODIFY_DATETIME = CURRENT_TIMESTAMP
       WHERE event_id = :eventId
			 AND offender_charge_id = :offenderChargeId
}

UPDATE_OFFENDER_CHARGES {
 UPDATE offender_charges
            SET no_of_offences          = :noofoffences,
                offence_date            = :offenseDate,
                offence_range_date      = :range,
                plea_code               = :plea,
                property_value          = :propertyValue,
                total_property_value    = :totalpropertyvalue,
                cjit_offence_code_1     = :cjitoffencecode1,
                cjit_offence_code_2     = :cjitoffencecode2,
                cjit_offence_code_3     = :cjitoffencecode3,
                charge_status           = :chargeStatus,
                result_code_1           = :resultcode1,
                result_code_2           = :resultcode2,
                result_code_1_indicator = :resultcode1indicator,
                result_code_2_indicator = :resultcode2indicator,
                most_serious_flag       = 'N',
                offence_type            = :offenseType,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId 
          WHERE offender_charge_id =:offenderChargeId
}

IS_EVENT_CHARGES_FINAL_ACTIVE {
	 SELECT 'X'
           FROM dual
          WHERE EXISTS (SELECT 'X'
                   FROM court_event_charges  c,
                        offence_result_codes o
                  WHERE c.event_id = :eventId
                    AND c.result_code_1 = o.result_code
                    AND c.result_code_1_indicator IN ('P', 'F')
                    AND o.charge_status = 'A')
}

GET_EVENT_CHARGES_COUNT{
select tag_legal_cases_get_event_charges_count(:offenderChargeId) from dual
}

FETCH_SENTENCE_START_DATE {
SELECT TO_CHAR(court_date,'DD/MM/RRRR') event_date
FROM orders
WHERE order_id = :orderId
AND order_type = 'AUTO'
}

OCDCASE_GET_ACTIVE_AGY_LOC {
select tag_establishment_get_active_agy_loc_desc(:agyLocation) from dual
}

UPDATE_SENTENCE_RECORD{
	UPDATE OFFENDER_SENTENCES SET
       FINE_AMOUNT=:fineAmount,
       CONSEC_TO_SENTENCE_SEQ=:consecutiveToLine,
       ORDER_ID=:orderId,
       START_DATE=:startDate,
       SENTENCE_STATUS=:status,
       MODIFY_DATETIME = CURRENT_TIMESTAMP,
       MODIFY_USER_ID = :modifyUserId 
 WHERE OFFENDER_BOOK_ID=:offenderBookId AND SENTENCE_SEQ=:line
}
OCDCCASE_FETCH_EVENTS {
	select * from COURT_EVENTS where CASE_ID = :courtCaseId Order by event_date desc
}

GET_USER_NAME{
SELECT user 
}

OCDCCASE_GET_LEGALS_DETAILS{
select
	 code,
	"Date Type" as date_type,
	"Value",
	"Indefinite",
	override,
	"indefinite(override)",
	custody_status
from
	(
	select
		code as code,
		offender_book_id,
		description as "Date Type",
		coalesce(to_char(to_timestamp(nullif("Value", ''), 'YYYY-MM-DD'),(select profile_value::text from system_profiles where profile_code = 'DATE' and profile_type = 'DISPLAY')), '-') as "Value",
		coalesce(A."Indefinite", '-') as "Indefinite",
		coalesce(to_char(to_timestamp(nullif("override", ''), 'YYYY-MM-DD'),(select profile_value::text from system_profiles where profile_code = 'DATE' and profile_type = 'DISPLAY')), '-') as "override",
		coalesce(A."indefinite(override)", '-') as "indefinite(override)"
	from
		(
		select
			form_identifier::jsonb->>'offenderBookId' as offender_book_id ,
			jsonb_array_elements(Legal_Summary::jsonb)->>'dateType' as "date Type",
			jsonb_array_elements(Legal_Summary::jsonb)->>'dateValue' as "Value",
			jsonb_array_elements(Legal_Summary::jsonb)->>'indefinite' as "Indefinite",
			jsonb_array_elements(Legal_Summary::jsonb)->>'overrideDateValue' as "override",
			jsonb_array_elements(Legal_Summary::jsonb)->>'overrideIndefinite' as "indefinite(override)"
		from
			(
			select
				form_identifier,
				form_info_json,
				items ->> 1 as Legal_Summary
			from
				(
				select
					form_identifier,
					form_info_json,
					jsonb_path_query_array( encode(form_info_json, 'escape')::jsonb,
					'$.*') as items
				from
					ocdlegls_data
) as t
			where
				form_identifier::jsonb->>'offenderBookId' = :offender_book_id::text 
)A)A,
		(
		select
			rc.code,
			rc.description
		from
			(
			select
				*
			from
				reference_domains rd
			where
				"domain" = 'KEY_DATES')rd,
			reference_codes rc
		where
			rd.domain = rc.domain)B
	where
		upper(A."date Type")= B.code)A,
	offender_custody_status B
where
	A.offender_book_id = B.offender_book_id::text
}
