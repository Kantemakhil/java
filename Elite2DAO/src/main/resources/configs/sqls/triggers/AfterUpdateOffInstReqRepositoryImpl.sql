GET_OFFENDER_INST_REQUESTS{
SELECT 
OFF_INST_REQ_ID,
OFFENDER_BOOKING_ID,
REQUEST_DATE,
REQUEST_TYPE,
REQUEST_STATUS,
REQUEST_REASON,
REQUESTED_BY_TYPE,
REQUESTED_BY_ID,
REQUESTED_AGENCY_ID,
REQUEST_COMMENT,
CREATE_DATETIME,
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID,
SEAL_FLAG
from OFFENDER_INST_REQUESTS
WHERE OFF_INST_REQ_ID = :OFF_INST_REQ_ID
}

GET_DATA{
SELECT duration_code
           , internal_status_seq
           , is_requested_party_type
           , agy_loc_id
      FROM   offender_internal_statuses
      WHERE  offender_book_id = :offender_booking_id AND
             internal_status_seq IN (SELECT MAX(internal_status_seq)
                            FROM offender_internal_statuses
                            WHERE  offender_book_id = :offender_booking_id)
}

GET_DATA1{
 SELECT next_review, rev_box
        FROM   durations
        WHERE duration_code = :lv_duration_code
        AND   internal_status = :lv_request_type
        AND   int_sts_reason_code = :lv_request_reason
}