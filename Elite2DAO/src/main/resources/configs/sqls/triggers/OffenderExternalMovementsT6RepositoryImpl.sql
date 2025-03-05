GET_OFFENDER_EXTERNAL_MOVEMENTS {
select OFFENDER_BOOK_ID,
MOVEMENT_SEQ,
MOVEMENT_DATE,
MOVEMENT_TIME,
INTERNAL_SCHEDULE_TYPE,
INTERNAL_SCHEDULE_REASON_CODE,
MOVEMENT_TYPE,
MOVEMENT_REASON_CODE,
DIRECTION_CODE,
ARREST_AGENCY_LOC_ID,
TO_PROV_STAT_CODE,
ESCORT_CODE,
FROM_AGY_LOC_ID,
TO_AGY_LOC_ID,
ACTIVE_FLAG,
ESCORT_TEXT,
COMMENT_TEXT,
REPORTING_DATE,
TO_CITY,
FROM_CITY,
REPORTING_TIME,
EVENT_ID,
PARENT_EVENT_ID,
TO_COUNTRY_CODE,
OJ_LOCATION_CODE,
APPLICATION_DATE,
APPLICATION_TIME,
TO_ADDRESS_ID,
FROM_ADDRESS_ID,
SEAL_FLAG,
CREATE_DATETIME,
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID from offender_external_movements where OFFENDER_BOOK_ID=:OFFENDER_BOOK_ID and MOVEMENT_SEQ=:MOVEMENT_SEQ
}

GET_REFERENCE_CODES_DESC {
SELECT description
             FROM reference_codes
            WHERE domain = :cp_ref_domain
              AND code = :cp_ref_code
              AND active_flag = 'Y'
}

UPDATE_OFFENDER_CIP_DETAILS {
update
	offender_cip_details
set
	release_date = :movement_time::date,
	release_time = :movement_time,
	comment_text = :lv_comment_text || '      ' || comment_text,
	expiry_date = coalesce(expiry_date,:movement_time::date),
	expiry_time = coalesce(expiry_time,:movement_time),
	modify_datetime = CURRENT_TIMESTAMP,
	modify_user_id =:modifyUserId
where
	offender_book_id = :OFFENDER_BOOK_ID
	and ((duration_type != 'INDEFINITE'
		and ((expiry_time > CURRENT_TIMESTAMP
			and release_time is null)
		or (release_time is not null
			and release_time > CURRENT_TIMESTAMP)))
	or (duration_type = 'INDEFINITE'
		and (release_time is null
			or release_time > CURRENT_TIMESTAMP)))
}