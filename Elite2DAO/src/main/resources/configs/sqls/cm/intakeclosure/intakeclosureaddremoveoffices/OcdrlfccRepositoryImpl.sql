 
OCDRLFCC_FIND_CGFKOFFAGY1DSPDESCRIPTION {
 	SELECT REF_CODE.DESCRIPTION, REF_CODE.LIST_SEQ, REF_CODE.CODE FROM   REFERENCE_CODES REF_CODE WHERE   (DOMAIN = 'CLO_CON_RSN' AND (ACTIVE_FLAG = 'Y' OR '' <>'NORMAL' ) ) ORDER BY LIST_SEQ ,UPPER(REF_CODE.DESCRIPTION )
}

OCDRLFCC_OFFAGY_FIND_OFFENDER_BOOKING_AGY_LOCS {
 	SELECT CASELOAD_ID, AGY_LOC_ID, OFFENDER_BOOK_ID, ADDITION_DATE, REASON_CODE, REMOVED_REASON_CODE, REMOVED_DATE, ADDITION_COMMENT, REMOVAL_COMMENT, OFFENDER_STATUS, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, ADDITION_TIME, SEAL_FLAG, PTR_ID FROM OFFENDER_BOOKING_AGY_LOCS WHERE OFFENDER_BOOK_ID=:OFFENDERBOOKID ORDER BY ADDITION_DATE DESC
}

OCDRLFCC_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_BOOKING_AGY_LOCS O WHERE O.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDRLFCC_OFFAGY_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_BOOKING_AGY_LOCS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID AND AGY_LOC_ID = :AGYLOCID AND CASELOAD_ID = :CASELOADID AND ADDITION_DATE = :ADDITIONDATE 
}

OCDRLFCC_CGFKCHK_OFFAGY_AGENCY_LOCATION {
	SELECT AL.DESCRIPTION FROM   AGENCY_LOCATIONS AL WHERE  AL.AGY_LOC_ID = :AGYLOCID
}

OCDRLFCC_CGFKCHK_OFFAGY1_OFFAGY_REF_CO_ {
	SELECT 
  REF_CODE.LIST_SEQ, REF_CODE.DESCRIPTION
FROM REFERENCE_CODES REF_CODE
WHERE
  REF_CODE.CODE = :REMOVEDREASONCODE AND
  DOMAIN = 'CLO_CON_RSN' AND
  ACTIVE_FLAG = 'Y' 

}

OCDRLFCC_CGFKLKP_OFFAGY1_OFFAGY_REF_CO_ {
	SELECT
  REF_CODE.CODE, REF_CODE.LIST_SEQ
FROM REFERENCE_CODES REF_CODE
WHERE (REF_CODE.DESCRIPTION = :DSPDESCRIPTION OR (coalesce(REF_CODE.DESCRIPTION::text, '') = '' AND coalesce(:DSPDESCRIPTION::text, '') = '' )) AND
  DOMAIN = 'CLO_CON_RSN' AND
  ACTIVE_FLAG = 'Y'
}

OCDRLFCC_CGFKCHK_OFFAGY_AGENCY_LOCATION_COMPLETE {
	SELECT AL1.DESCRIPTION FROM   AGENCY_LOCATIONS AL1 WHERE  AL1.AGY_LOC_ID = :AGYLOCID AND     AL1.AGY_LOC_ID IN (SELECT AL1.AGY_LOC_ID FROM OFFENDER_BOOKING_AGY_LOCS AL WHERE AL.CASELOAD_ID = :CASELOADID AND AL.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND coalesce(AL.REMOVED_DATE::text, '') = '')
}

OCDRLFCC_OFFENDER_BOOKING_AGY_LOCS_INTAKE {
SELECT 'Y'
     FROM offender_booking_agy_locs locs
    WHERE locs.agy_loc_id = :AGYLOCID
      AND locs.reason_code ='INTAKE'
      AND locs.removed_date IS NULL
      AND locs.removed_reason_code IS NULL
      AND locs.offender_book_id=:OFFENDERBOOKID
      AND locs.addition_date = :ADDITIONDATE
}

OCDRLFCC_COUNT_OFFENDER_BOOK_ID {
SELECT COUNT( offender_book_id )
     FROM offender_booking_agy_locs
    WHERE offender_book_id = :OFFENDERBOOKID
      AND coalesce(removed_date::text, '') = ''
}

OCDRLFCC_Y_OFFENDER_BOOKING_AGY_LOCS {
SELECT 'Y'
       FROM offender_booking_agy_locs obal
      WHERE obal.offender_book_id = :OFFENDERBOOKID
        AND obal.addition_date    = :ADDITIONDATE
        AND obal.reason_code      = 'INTAKE'
        AND coalesce(obal.removed_date::text, '') = ''
        AND coalesce(obal.removed_reason_code::text, '') = ''
        AND EXISTS ( SELECT 1
              FROM offender_bookings ob
             WHERE ob.offender_book_id  = obal.offender_book_id
               AND ob.intake_agy_loc_id = obal.agy_loc_id)
}

OCDRLFCC_AGY_LOC_ID_END_DATE {
SELECT AGY_LOC_ID
        FROM CASE_PLANS
       WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
         AND coalesce(END_DATE::text, '') = ''
}

OCDRLFCC_UPDATE_OFFENDER_BOOKING_AGY_LOCS {
UPDATE offender_booking_agy_locs
         SET removed_date = :removedDate,
             removed_reason_code = :reasonCode,
             removal_comment = :additionComment,
             modify_datetime=current_timestamp,
             modify_user_id=:modifyUserId
       WHERE offender_book_id = :offenderBookId
         AND agy_loc_id = :agyLocId
         AND caseload_id = :caseloadId
         AND addition_date = :additionDate
}

OCDRLFCC_MAX_EVENT_SEQ {
SELECT coalesce( MAX( event_seq ) +1, 1 )
        FROM offender_booking_events
       WHERE offender_book_id = :offenderBookId
}

OCDRLFCC_INSERT_OFFENDER_BOOKING_AGY_LOCS {
INSERT INTO offender_booking_events(
                                       offender_book_id,
                                           event_seq,
                                           booking_event_code,
                                           reason_code,
                                           to_agy_loc_id,
                                           event_date,
                                           event_time,
                                           comment_text,create_user_id,create_datetime,modify_datetime)
                                   VALUES(
                                           :offenderBookId,
                                           :ptrId,
                                           'RCO',
                                           :reasonCode,
                                           :agyLocId,
                                           sysdate(),
                                           sysdate(),
                                           :additionComment,:createUserId,current_timestamp,null) 
}