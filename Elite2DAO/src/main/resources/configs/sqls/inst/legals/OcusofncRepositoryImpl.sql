DISPLAY_OFFENSES_DAILOG {
select offence_code,
offence_date,
offence_range_date,
result_code_1,
result_code_1_indicator
from offender_charges off_chg where offender_book_id=:offenderBookId
}

FETCH_SENTENCE_CATEGORY {
SELECT DISTINCT sentence_type
FROM sentence_calc_types
WHERE sentence_category = :category
AND(active_flag = 'Y')
AND sentence_calc_type = :sentenceCalcType
AND sentence_calc_type NOT LIKE 'AGG%'
}