TAG_ASS_OFF_NEEDS_ASSESSED_OFFENDER_NEEDS_PRINS{
insert into assessed_offender_needs(assessment_id, off_ass_need_id, assessed_need_code, objective, target_date, active_flag, expiry_date, create_datetime, modify_datetime, create_user_id ) values (:assessmentId, nextval('off_ass_need_id'), :assessedNeedCode, :objective, :targetDate, :activeFlag, :expiryDate, current_timestamp, null, :createUserId)
}
TAG_ASS_OFF_NEEDS_ASSESSED_OFFENDER_NEEDS_PRUPD{
update assessed_offender_needs set assessment_id = :assessmentId, assessed_need_code = :assessedNeedCode, objective = :objective, target_date = :targetDate, active_flag = :activeFlag, expiry_date = :expiryDate, modify_datetime =current_timestamp , modify_user_id =:modifyUserId where off_ass_need_id = :offAssNeedId
}
TAG_ASS_OFF_NEEDS_ASSESSED_OFFENDER_NEEDS_PRDEL{
DELETE FROM assessed_offender_needs  WHERE off_ass_need_id  = :offAssNeedId
}
