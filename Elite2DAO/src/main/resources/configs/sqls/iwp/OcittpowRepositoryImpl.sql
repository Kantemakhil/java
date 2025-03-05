
OCITTPOW_FIND_DSP_DESCRIPTION_RECORD_GROUP {
SELECT
    agy_loc1.description   DESCRIPTION,
    agy_loc1.agy_loc_id    CODE
FROM
    agency_locations agy_loc1
WHERE
    agy_loc1.agy_loc_id IN (
        SELECT
            cal.agy_loc_id
        FROM
            caseload_agency_locations cal
        WHERE
            cal.caseload_id = :currentCaseLoad
            AND cal.update_allowed_flag = 'Y'
    )
ORDER BY
    agy_loc1.description
}

OCITTPOW_FIND_AGYLOCIDFROM {
SELECT
    agy_loc2.description   DESCRIPTION,
    agy_loc2.agy_loc_id    CODE
FROM
    agency_locations agy_loc2
WHERE
    agy_loc2.agy_loc_id != :agyLocIdFrom
ORDER BY
    agy_loc2.description
}

OCITTPOW_EXTOTNA_FIND_V_EXT_OWNERSHIP_TRANSFER {
SELECT
    *
FROM
    v_ext_ownership_transfer
WHERE
    agy_loc_id_from = :code
        ORDER BY OFFENDER_LAST_NAME ASC
}

POST_QUERY_STAFF_NAME{
SELECT DISTINCT
       :lastName
    || ', '
    || :fastName AS staffName
FROM
    v_ext_ownership_transfer
}

OCITTPOW_GET_PROFILE_VALUE {
SELECT
    profile_value
FROM
    system_profiles
WHERE
    profile_type = 'FILE_TRANS'
    AND profile_code = 'FILE'
}

OCITTPOW_FIND_OFFENDER_ID {
SELECT
    offender_id
FROM
    V_HEADER_BLOCK_FN(:USERID) v_header_block
WHERE
    offender_book_id = :offenderBookId

}

OCITTPOW_FIND_CUR_TRAN{
SELECT
    offender_file_seq
FROM
    offender_file_transactions
WHERE
    offender_id = :v_offenderId
    AND agy_loc_id_to = :agyLocIdTo
    AND non_officer_to = 'INT'
}

CANCEL_FILE_TRANSFER_UPDATE{

UPDATE offender_community_files
SET
    holding_agy_loc_id = :agyLocIdFrom,
    holding_staff_id = :assStaffId,
    non_officer_status = '',
    modify_user_id =:modifyUserId,
    modify_datetime = current_timestamp
WHERE
    offender_id = :v_offenderId
    AND offender_file_seq = :vOffenderFileSeq
}

OCITTPOW_DELETE_EXT_OWN_TRAF{

DELETE FROM ext_ownership_transfer
WHERE
    offender_book_id = :offenderBookId
    AND ext_transfer_id = :extTransferId
}

OCITTPOW_CUR_EXISTS{

SELECT
   count(*)
FROM
    ext_ownership_transfer
WHERE
    offender_book_id = :offenderBookId
    AND ext_transfer_id = :extTransferId
}

OCITTPOW_CUR_TRAN{
SELECT
   count(*)
FROM
    offender_file_transactions
WHERE
    offender_id = :v_offenderId
    AND agy_loc_id_to = :agyLocIdTo
    AND non_officer_to = 'INT'
    AND transaction_id = (
        SELECT
            MAX(transaction_id)
        FROM
            offender_file_transactions
        WHERE
            offender_id = :v_offenderId
    )
}

OCITTPOW_CUR_LOC{
SELECT
    count(*)
FROM
    offender_community_files
WHERE
    offender_id = :v_offenderId
    AND holding_agy_loc_id = :agyLocIdFrom
}

OCITTPOW_V_AGY_LOC_ID{
select agy_loc_id_to from ext_ownership_transfer where offender_book_id = :offenderBookId  AND ext_transfer_id = :extTransferId
}

OCITTPOW_AGY_LOC_ID_UPDATE{
      UPDATE EXT_OWNERSHIP_TRANSFER
             SET agy_loc_id_to = :VagylocIdTo,
             modify_user_id =:modifyUserId,
             modify_datetime = current_timestamp
           WHERE offender_book_id = :offenderBookId
             AND ext_transfer_id = :extTransferId
}

OCITTPOW_AGY_LOC_ID_TO_EXECUTE_QUERY{
SELECT
  count(*)
FROM
    agency_locations agy_loc2
WHERE
    agy_loc2.agy_loc_id = :agyLocIdTo
}