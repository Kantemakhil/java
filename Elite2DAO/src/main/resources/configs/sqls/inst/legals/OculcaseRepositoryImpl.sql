
LOV_LINK_CASES {
SELECT case_SEQ,to_date(off_cases.BEGIN_DATE,'DD/MM/YYYY') Start_date,
off_cases.offender_book_id,

       agy_loc.DESCRIPTION Agy_Loc,ref_code.DESCRIPTION, 

       off_cases.CASE_INFO_PREFIX INFO_PREFIX, 

       off_cases.CASE_INFO_NUMBER INFO_NUMBER,DECODE(case_status,'A','Active','I','Inactive',case_status) case_status,case_id,COMBINED_CASE_ID 

From   offender_cases off_cases, agency_locations agy_loc, reference_codes REF_CODE  

Where  off_cases.AGY_LOC_ID=agy_loc.AGY_LOC_ID 

AND    off_cases.CASE_TYPE=REF_CODE.code(+) 

and    domain(+)='LEG_CASE_TYP' 

and    off_cases.case_seq not in (:caseSeq)

and    off_cases.OFFENDER_BOOK_ID= :offenderBookId

and    combined_case_id is null 

order by off_cases.BEGIN_DATE
}

COURT_EVENTS_HEARING{
select Event_Date,START_TIME,tag_establishment.get_agy_loc_desc (agy_loc_id) Court,
TAG_LEGAL_CASES.get_reference_code_description ( 'MOVE_RSN',court_event_type,'CRT' ) HearingType,
COMMENT_TEXT, COURT_EVENTS.EVENT_ID
from COURT_EVENTS
where offender_book_id= :offenderBookId
and case_id= :caseId
order by event_date desc, start_time desc
}

CHECK_SENTENCES{
SELECT COUNT(*)
           FROM offender_sentences
          WHERE offender_book_id= :offenderBookId
            AND case_id = :caseId
     }
     
GET_LINKCASE_DETAILS{
SELECT case_seq,off_cases.offender_book_id,
                off_cases.begin_date start_date,
                agy_loc.description agy_loc,
                ref_code.description,
                off_cases.case_info_prefix info_prefix,
                off_cases.case_info_number info_number,
                case_id
           FROM offender_cases   off_cases,
                agency_locations agy_loc,
                reference_codes  ref_code
          WHERE off_cases.agy_loc_id = agy_loc.agy_loc_id
            AND off_cases.case_type = ref_code.code(+)
            AND domain(+) = 'LEG_CASE_TYP'
            AND off_cases.case_seq NOT IN (:caseSeq)
            AND off_cases.offender_book_id = :offenderBookId
            AND off_cases.case_id = :combinedCaseId 
            ORDER BY off_cases.begin_date

}

get_cases_post_query_rec{
select tag_legal_cases.get_case_seq(:combinedCaseId) from dual 
		
}