OFFENDER_BOOKING_AGY_LOCS_T2_OFFENDER_BOOKING_AGY_LOCS_GET{
SELECT CASELOAD_ID,AGY_LOC_ID,OFFENDER_BOOK_ID,ADDITION_DATE,REASON_CODE,REMOVED_REASON_CODE,REMOVED_DATE,ADDITION_COMMENT,REMOVAL_COMMENT,OFFENDER_STATUS,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,ADDITION_TIME,SEAL_FLAG,PTR_ID FROM OFFENDER_BOOKING_AGY_LOCS WHERE offender_book_id = :offenderBookId
}
OFFENDER_BOOKING_AGY_LOCS_T2_OFFENDER_BOOKINGS{
Select community_agy_loc_id, coalesce(no_Comm_Agy_loc_id,0) as no_Comm_Agy_loc_id From   Offender_bookings Where  Offender_Book_ID = :offenderBookId
}

OFFENDER_BOOKING_AGY_LOCS_T2_UPDATE_OFFENDER_BOOKINGS{
 Update Offender_bookings Set  community_agy_loc_id = :communityAgyLocId, no_comm_agy_loc_id = :noCommAgyLocId,MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId Where  Offender_Book_ID = :offenderBookId
}

OFFENDER_BOOKING_AGY_LOCS_T2_GET_OFFENDER_BOOKINGS
{
Select community_agy_loc_id, coalesce(no_Comm_Agy_loc_id,0)
		From   Offender_bookings
		Where  Offender_Book_ID = :offenderBookId
}