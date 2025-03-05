GET_OFFENDER_CASES_CASE_ID {
select CASE_ID,
OFFENDER_BOOK_ID,
CASE_INFO_NUMBER,
CASE_TYPE,
CASE_STATUS,
COMBINED_CASE_ID,
MODIFY_DATETIME,
MODIFY_USER_ID,
BEGIN_DATE,
AGY_LOC_ID,
CREATE_USER_ID,
CREATE_DATETIME,
CASE_INFO_PREFIX,
VICTIM_LIAISON_UNIT,
STATUS_UPDATE_REASON,
STATUS_UPDATE_COMMENT,
STATUS_UPDATE_DATE,
STATUS_UPDATE_STAFF_ID,
LIDS_CASE_NUMBER,
CASE_SEQ,
SEAL_FLAG from OFFENDER_CASES where CASE_ID=:CASE_ID
}

INSERT_OFFENDER_CASE_STATUSES {
INSERT INTO offender_case_statuses
                  (case_id,
                   status_update_reason,
                   status_update_comment,
                   status_update_date,
                   status_update_staff_id,
                   offender_case_status_id
                  )
           VALUES (:case_id,
                   coalesce(:status_update_reason1,:status_update_reason2),
                   coalesce(:status_update_comment1,:status_update_comment2),
                   coalesce(:status_update_date1,:status_update_date2),
                   coalesce(:status_update_staff_id1,:status_update_staff_id2),
                   NEXTVAL('offender_case_status_id')
                  )
}