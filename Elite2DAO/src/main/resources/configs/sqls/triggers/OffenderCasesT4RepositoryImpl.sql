GET_LIDS_CASE_NUMBER {
SELECT coalesce (MAX (lids_case_number), 0) + 1
        FROM offender_cases
       WHERE offender_book_id = :offender_book_id
}