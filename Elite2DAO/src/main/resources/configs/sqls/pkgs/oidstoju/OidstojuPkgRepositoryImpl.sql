REC_UPD_CUR{
select 'Y' from OFFENDER_IND_SCHEDULES where event_id = :v_sch_id and (event_date <> :v_mvt_date or event_sub_type <> :v_mvt_reason)
}

NOTI_EXIS_CUR{
select noti_seq, noti_move_seq from OFFENDER_PEND_NOTIFICATIONS opn where opn.schedule_id = :v_sch_id 
}

DELETE_OFFENDER_NOT_COMPLETIONS{
 delete OFFENDER_NOT_COMPLETIONS where status <> 'COMPLETED' and noti_seq = :v_not_seq and noti_move_seq = :v_not_m_seq and offender_book_id = :v_off_book_id
}

CH_NOT_CUR{
 select COUNT(*) from OFFENDER_NOT_COMPLETIONS where noti_seq = :v_not_seq and noti_move_seq = :v_not_m_seq and offender_book_id = :v_off_book_id and STATUS = 'COMPLETED'  
}

UPDATE_OFFENDER_NOT_COMPLETIONS{
-- update OFFENDER_NOT_COMPLETIONS set status = 'CANCEL' where noti_seq = :v_not_seq and noti_move_seq = :v_not_m_seq and status = 'COMPLETED'
update OFFENDER_NOT_COMPLETIONS set status = 'CANCEL', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where noti_seq = :v_not_seq and noti_move_seq = :v_not_m_seq and status = 'COMPLETED'
}