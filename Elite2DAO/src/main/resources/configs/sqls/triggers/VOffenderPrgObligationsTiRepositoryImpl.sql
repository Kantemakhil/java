V_OFFENDER_PRG_OBLIGATIONS_TI_INSERT{
insert into offender_prg_obligations(offender_prg_obligation_id, offender_book_id, program_id, status, status_change_date, status_change_reason, offender_sent_cond_act_id, start_date, end_date, event_type, event_sub_type, comment_text, sentence_seq, length, length_unit, offender_sent_condition_id, special_need_flag, availability_code, obligation_source, create_datetime, modify_datetime, create_user_id) values(:offenderPrgObligationId, :offenderBookId, :programId, :status, :statusChangeDate, :statusChangeReason, :offenderSentCondActId, :startDate, :endDate, :eventType, :eventSubType, :commentText, :sentenceSeq, :length, :lengthUnit, :offenderSentConditionId, :specialNeedFlag, :availabilityCode, :obligationSource, current_timestamp, null, :createUserId) 
}
V_OFFENDER_PRG_OBLIGATIONS_TI_UPDARE{
update offender_prg_obligations set referral_date = :referralDate, special_need_flag = :specialNeedFlag, comment_text = :commentText, availability_code = :availabilityCode, modify_datetime = current_timestamp, modify_user_id=:modifyUserId where offender_prg_obligation_id = :offenderPrgObligationId 
}
V_OFFENDER_PRG_OBLIGATIONS_TI_DELETE1{
DELETE FROM offender_prg_obligation_hty WHERE offender_prg_obligation_id = :offenderPrgObligationId
}
V_OFFENDER_PRG_OBLIGATIONS_TI_DELETE2{
DELETE FROM offender_prg_obligations WHERE offender_prg_obligation_id = :offenderPrgObligationId
}