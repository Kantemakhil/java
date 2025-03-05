 L_CHECK_EXIST_CUR{
   select DISTINCT OFFENDER_BOOK_ID from offender_assoc_p_event_notifs where notification_code = 'NEWVIC' AND OFFENDER_BOOK_ID in (select OFFENDER_BOOK_ID from offender_bookings ob where root_offender_id = :l_root_offender_id )
 }
 
 TRG_EVENT_ID_SEQ{
   select nextval('trg_event_id')  from dual
   }
OFFENDER_ASSOC_P_EVENT_NOTIFS_INSERT{   
 insert into offender_assoc_p_event_notifs(trg_event_id, event_date, offender_book_id, notification_code,create_datetime, create_user_id ) values ( :l_trg_event_id, current_timestamp, :l_offender_book_id_old, 'NEWCHG', current_timestamp, :createUserId)
 }
           