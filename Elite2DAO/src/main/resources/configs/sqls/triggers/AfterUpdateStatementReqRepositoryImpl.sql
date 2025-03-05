INSERT_OFFENDER_INTERNAL_STATUSES{
INSERT INTO offender_internal_statuses( offender_book_id,
                                               internal_status_seq,
                                               effective_date,
                                               int_sts_reason_code,
                                               internal_status,
                                               duration_code,
                                               is_requested_party_type,
                                               agy_loc_id,
                                               create_date,
                                               review_date )
                                   VALUES   (  :lv_book_id,
                                               :lv_internal_status_seq + 1,
                                               sysdate,
                                               :lv_request_reason,
                                               :lv_request_type,
                                               :lv_duration_code,
                                               :lv_party_type,
                                               :lv_agy_loc_id,
                                               sysdate,
                                               :lv_request_date +
                                                :lv_next_review )
}