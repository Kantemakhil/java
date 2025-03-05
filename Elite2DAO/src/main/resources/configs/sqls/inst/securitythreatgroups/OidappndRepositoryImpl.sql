
OIDAPPND_GET_NEW_TEXT {
	select
	concat(' [', ' ', to_char(sysdate(), 'mm/dd/yyyy hh24:mi:ss'), ' ', upper(user), ' ', ']', ' ',:newText)
from
	dual
}
OIDAPPND_OIDMBRDT_UPDATE_OFFENDER_STG_AFFILIATIONS {
update offender_stg_affiliations set comment_text =:newValue , modify_datetime = current_timestamp , modify_user_id =:modifyUserId where offender_book_id =:offenderBookId and stg_seq =:stgSeq
}
OIDAPPND_OIDSTGCN_UPDATE_STG_CASE_NOTES {
   update stg_case_notes set text = :newValue , modify_datetime = current_timestamp , modify_user_id =:modifyUserId where stg_id = :stgId and note_seq = :noteSeq

}
OIDAPPND_OIDMBRQU_UPDATE_OFFENDER_ASSESSMENT_ITEMS {
    UPDATE offender_assessment_items SET modify_datetime = current_timestamp , modify_user_id =:modifyUserId , comment_text = comment_text || :newValue WHERE offender_book_id = :offenderBookId 	AND assessment_seq = :assessmentSeq 	AND item_seq = :      this.stgNotemodel.assessmentSeq = this.data.assessmentSeq;

}
OIDAPPND_OCDCPTIT_UPDATE_OFFENDER_PTR {
    UPDATE offender_ptr SET ptr_comment = ptr_comment ||:newValue , modify_datetime = current_timestamp , modify_user_id =:modifyUserId WHERE ptr_id = :ptrId
}
OIDAPPND_OCDCPTIT_STG_CASE_NOTES{
select text  from stg_case_notes scn where stg_id =:stgId and note_seq =:noteSeq
}
OIDAPPND_OCDCPTIT_OFFENDER_STG_AFFILIATIONS{
select comment_text from offender_stg_affiliations where offender_book_id =:offenderBookId and stg_seq =:stgSeq
}
