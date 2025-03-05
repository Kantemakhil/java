GET_NOTF_INFO_CUR{
SELECT notification_flag,notification_type FROM movement_reasons  WHERE movement_type =:p_mvt_type AND movement_reason_code =:p_mvt_reason
 }       
 
 GET_CNT_PEN_CUR{
 SELECT COUNT(*) FROM offender_pend_notifications WHERE offender_book_id =:p_off_book_id AND movement_type =:p_mvt_type AND movement_reason_code =:p_mvt_reason
 }
 CHECK_FOR_NOTIF_REC_CUR{
 SELECT COUNT(*) FROM offender_release_notis orn WHERE orn.offender_book_id =:p_off_book_id AND (orn.expiration_date IS NULL OR orn.expiration_date >= current_timestamp) AND NOT EXISTS(SELECT NULL FROM notify_movement_types nmt WHERE nmt.movement_type =:p_mvt_type AND nmt.movement_reason_code=:p_mvt_reason AND nmt.offender_book_id =:p_off_book_id AND nmt.noti_seq = orn.noti_seq)
 }
 GET_ALL_NOTIF_REC_CUR{
 SELECT orn.offender_book_id, orn.noti_seq, orn.noti_agency_party_code, orn.noti_corp_id, orn.noti_person_id FROM offender_release_notis orn WHERE orn.offender_book_id =:p_off_book_id AND (orn.expiration_date IS NULL OR orn.expiration_date >= current_timestamp) AND NOT EXISTS(SELECT NULL FROM notify_movement_types nmt WHERE nmt.movement_type =:p_mvt_type AND nmt.movement_reason_code=:p_mvt_reason AND nmt.offender_book_id =:p_off_book_id AND nmt.noti_seq = orn.noti_seq)
 }
 GET_NEXT_NOTI_MOVE_SEQ_CUR{
 SELECT COUNT(*) + 1 FROM offender_pend_notifications WHERE offender_book_id =:cp_off_book_id AND noti_seq =:cp_noti_seq
 }
 INSERT_OFFENDER_PEND_NOTIFICATIONS{
 insert into offender_pend_notifications(offender_book_id, noti_seq, noti_move_seq, movement_type, movement_reason_code, movement_date, move_schedule_flag, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values(:offenderBookId, :notiSeq, :notiMoveSeq, :movementType, :movementReasonCode, :movementDate, 'M', :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
 }
 INSERT_OFFENDER_NOT_COMPLETIONS{
 insert into offender_not_completions(offender_book_id, noti_seq, status, noti_move_seq, noti_agency_party_code, noti_corp_id, noti_person_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values(:offenderBookId, :notiSeq, 'OVERRIDE', :notiMoveSeq, :notiAgencyPartyCode, :notiCorpId, :notiPersonId, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
 }
                                   
                 