FETCH_SENTENCE_AGGREGATES_DATA {
select 
SENTENCE_CATEGORY,
SENTENCE_TERM,
ELIGIBLE_REMISSION,
(SELECT DESCRIPTION FROM sentence_calc_types sec where sec.Sentence_Calc_type = offsent.SENTENCE_CALC_TYPE ) SENTENCE_CALC_TYPE,
START_DATE,
END_DATE,
AGGREGATE_TERM,
AGGREGATE_ADJUST_DAYS,
SED_CALCULATED_DATE AS SED_CALCULATED_DATE,
RED_CALCULATED_DATE AS ARD_CALCULATED_DATE,
LRD_CALCULATED_DATE AS CRD_CALCULATED_DATE,
PED_CALCULATED_DATE AS PED_CALCULATED_DATE
from offender_sentences offsent
WHERE ( SENTENCE_LEVEL = 'AGG' OR AGG_SENTENCE_SEQ IS NULL ) AND offender_book_id=:offenderBookId
}

CALC_DAYS_BETWEEN {
--select tag_sentence_calc.days_between(:startDate,:endDate) from dual
select tag_sentence_calc_days_between(:startDate::timestamp,:endDate::timestamp) from dual
}