OFFENDER_BOOKING_DETAILS_INSERT{
 insert into Offender_booking_details(OFFENDER_BOOK_ID, CELL_SHARING_ALERT_FLAG, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME) values (:Offender_Book_ID, (case when ( select profile_value from system_profiles where profile_type = 'CLIENT' and profile_code = 'ADM_CSA') = 'N' then 'N' else 'Y' end), current_timestamp , :createUserId , NULL)
}