SUBSTANCE_TEST_RESULTS_TCN_V_SUB_CUR{
SELECT ST.date_tested FROM substance_tests ST WHERE ST.offender_book_id = :offenderBookId AND ST.sample_seq = :sampleSeq
}