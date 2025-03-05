V_OFFENDER_PROCEEDING_SENTS_TU_DELETE{
DELETE FROM Offender_Proceeding_Sents WHERE  Offender_proceeding_id = :offenderProceedingId AND    Offender_Book_Id  = :offenderBookId AND    Sentence_seq  = :New.sentenceSeq
}
V_OFFENDER_PROCEEDING_SENTS_TU_INSERT{
INSERT INTO Offender_Proceeding_Sents(Offender_proceeding_id, Offender_Book_Id, Sentence_seq,create_datetime,modify_datetime,create_user_id)VALUES (:offenderProceedingId, :offenderBookId, :sentenceSeq,:createDatetime,:modifyDatetime,:createUserId)
}
