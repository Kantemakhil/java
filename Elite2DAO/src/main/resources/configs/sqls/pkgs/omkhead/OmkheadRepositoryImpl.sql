IN_OUT_STATUS{ 
SELECT in_out_status FROM offender_bookings WHERE offender_book_id = :p_book_id
}
          
          
OFFENDER_BOOKINGS_UPDATE_OUT{         
 update offender_bookings set in_out_status = 'OUT', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :p_book_id
}
           
OFFENDER_BOOKINGS_UPDATE_IN{
update offender_bookings set in_out_status = 'IN', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :p_book_id
}
     
      