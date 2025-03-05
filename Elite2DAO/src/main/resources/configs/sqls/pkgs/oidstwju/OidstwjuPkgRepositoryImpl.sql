
OIDSTWJU_FIRTS_SELECT{
 SELECT COUNT ( * ) INTO v_na_1 FROM offender_na_details ond, offender_bookings ob WHERE ob.agy_loc_id =: p_agy_loc_id AND ob.offender_book_id = ond.ns_offender_book_id AND ond.offender_book_id = :p_offender_book_id AND ( ond.ns_expiry_date > SYSDATE OR ond.ns_expiry_date IS NULL )      }
     
 OIDSTWJU_FIRTS_SELECT{  
 SELECT COUNT ( * ) INTO v_na_2 FROM offender_na_details ond, offender_bookings ob WHERE ob.agy_loc_id = :p_agy_loc_id AND ob.offender_book_id = ond.offender_book_id AND ond.ns_offender_book_id = :p_offender_book_id AND ( ond.ns_expiry_date > SYSDATE OR ond.ns_expiry_date IS NULL )
               
 }
               
               
  CANCEL_CUR{
      
  SELECT oswl.outcome_reason_code FROM offender_ind_sch_wait_lists oswl, offender_ind_schedules os WHERE os.event_id = oswl.event_id AND os.agy_loc_id = :p_agy_loc_id AND os.event_type = 'TRN' AND ( TO_CHAR ( os.event_date, 'MM/DD/YYYY' ) >= TO_CHAR ( SYSDATE, 'MM/DD/YYYY' ) OR os.event_date IS NULL ) AND EXISTS ( SELECT 'X' FROM offender_na_details ond WHERE ( ond.offender_book_id = :p_offender_book_id AND ond.ns_offender_book_id = os.offender_book_id ) OR ( ond.offender_book_id = os.offender_book_id AND ond.ns_offender_book_id = : p_offender_book_id ) AND ( ond.ns_expiry_date > SYSDATE OR ond.ns_expiry_date IS NULL ) )
          }

                                
 SCHEDULE_NA_CUR{
      
 SELECT COUNT ( * ) FROM offender_ind_schedules os WHERE os.agy_loc_id = :p_agy_loc_id AND os.event_type = 'TRN' AND ( os.event_date >= TRUNC ( SYSDATE ) OR os.event_date IS NULL ) AND EXISTS ( SELECT 'X' FROM offender_na_details ond WHERE ( ond.offender_book_id = :p_offender_book_id AND ond.ns_offender_book_id = os.offender_book_id ) OR ( ond.offender_book_id = os.offender_book_id AND ond.ns_offender_book_id = : p_offender_book_id ) AND ( ond.ns_expiry_date > SYSDATE OR ond.ns_expiry_date IS NULL ) )
  }
                                
 DATE_CONVERSION{
	select current_timestamp  from dual
}

  NOT_REQ_CUR{
  SELECT notification_flag, notification_type FROM movement_reasons WHERE movement_type =:v_mvt_type AND movement_reason_code = :v_mvt_reason
    }


GET_OFF_P_CNT_CUR{
              SELECT COUNT ( * ) FROM offender_pend_notifications WHERE offender_book_id = :v_off_book_id AND movement_type = :v_mvt_type AND movement_reason_code = :v_mvt_reason
            }
  GET_NOT_REC_CUR{
         SELECT COUNT ( offender_book_id ) FROM offender_release_notis orn WHERE orn.offender_book_id = :v_off_book_id AND ( orn.expiration_date IS NULL OR orn.expiration_date >= SYSDATE ) AND NOT EXISTS ( SELECT NULL FROM notify_movement_types nmt WHERE nmt.movement_type = :v_mvt_type AND nmt.movement_reason_code = :v_mvt_reason AND nmt.offender_book_id = :v_off_book_id AND nmt.noti_seq = orn.noti_seq )   
         }
               
     GET_REL_NOT_REC_CUR{
      SELECT orn.noti_seq, orn.noti_agency_party_code, orn.noti_corp_id, orn.noti_person_id FROM offender_release_notis orn WHERE offender_book_id = :v_off_book_id AND ( orn.expiration_date IS NULL OR orn.expiration_date >= SYSDATE ) AND NOT EXISTS ( SELECT NULL FROM notify_movement_types nmt WHERE nmt.movement_type = :v_mvt_type AND nmt.movement_reason_code = :v_mvt_reason AND nmt.offender_book_id = :v_off_book_id AND nmt.noti_seq = orn.noti_seq ) 
          }
  GET_NEXTMOVE_SEQ_CUR{
         SELECT NVL ( MAX ( noti_move_seq ), 0 ) FROM offender_pend_notifications WHERE offender_book_id =:v_off_book_id AND noti_seq =:v_noti_seq 
          }
OFFENDER_PEND_NOTIFICATIONS{          
insert into offender_pend_notifications ( offender_book_id, noti_seq, noti_move_seq, movement_type, movement_reason_code, movement_date, move_schedule_flag, schedule_id , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :v_off_book_id, :v_noti_seq, :v_noti_m_seq, :v_mvt_type, :v_mvt_reason, :v_mvt_date, 'S', :v_sch_id , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)      
}
     OFFENDER_NOT_COMPLETIONS{                             
 insert into offender_not_completions ( offender_book_id, noti_seq, noti_move_seq, status, noti_agency_party_code, noti_corp_id, noti_person_id, CREATE_USER_ID, CREATE_DATETIME ) values ( :v_off_book_id, :v_noti_seq, :v_noti_m_seq, 'OPEN', rec.noti_agency_party_code, rec.noti_corp_id, rec.noti_person_id, :createUserId, CURRENT_TIMESTAMP )    
     }
     