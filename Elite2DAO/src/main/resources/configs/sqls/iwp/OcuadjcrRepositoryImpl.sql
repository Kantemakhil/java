
OCUADJCR_FIND_RGREASON {
 	SELECT DESCRIPTION , CODE    FROM REFERENCE_CODES  WHERE DOMAIN = 'COUNTER_RSN'    AND ((ACTIVE_FLAG = 'Y' )) ORDER BY  LIST_SEQ , DESCRIPTION , CODE
}

OCUADJCR_CTLBLK_FIND_OFFENDER_SENTENCE_HTY {
 	SELECT
    offender_sentence_hty_id,
    offender_book_id,
    sentence_seq,
    adjust_reason,
    staff_id,
    no_of_unexcused_absence,
    adjust_date,
    adjust_time,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id,
    seal_flag,
    COMMENT_TEXT
FROM
    offender_sentence_hty
WHERE
    offender_book_id = :offender_book_id
ORDER BY
    adjust_date DESC,
    adjust_time DESC
}
OCUADJCR_CTLBLK_INSERT_OFFENDER_SENTENCE_HTY {
	INSERT INTO OFFENDER_SENTENCE_HTY(OFFENDER_SENTENCE_HTY_ID,OFFENDER_BOOK_ID,SENTENCE_SEQ,ADJUST_REASON,STAFF_ID,NO_OF_UNEXCUSED_ABSENCE,ADJUST_DATE,ADJUST_TIME,
	CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG,COMMENT_TEXT) VALUES(:offenderSentenceHtyId,:offenderBookId,:sentenceSeq,:adjustReason,:staffId,:noOfUnexcusedAbsence,
	:adjustDate,:adjustTime,current_timestamp,:createUserId,current_timestamp,:modifyUserId,:sealFlag,:commentText)
}

OCUADJCR_STAFF_NAME{
	SELECT LAST_NAME||', '||FIRST_NAME L_NAME FROM STAFF_MEMBERS WHERE STAFF_ID = :STAFF_ID 
}

OCUADJCR_STAFF_ID{
	SELECT staff_id
        FROM staff_members
       WHERE user_id = :user_id

}

OCUADJCR_PREINSERT{
SELECT NEXTVAL('offender_sentence_hty_id') FROM DUAL
}

OCUADJCR_POSTINSERT{
 UPDATE OFFENDER_SENTENCES
         SET no_of_unexcused_absence = :p_counter
       WHERE offender_book_id = :p_offender_book_id
         AND sentence_seq = :p_sentence_seq
         
 }
 
 OCDLEGLO_SANCTION_HTY_QUERY{
 select
	hty.off_sanction_sent_hty_id,
	hty.offender_book_id,
	hty.sentence_seq,
	hty.order_type,
	hty.adjust_reason,
	hty.staff_id,
	hty.adjust_date,
	hty.adjust_time,
	hty.new_counter,
	hty.create_datetime,
	hty.create_user_id,
	hty.modify_datetime,
	hty.modify_user_id,
	hty.seal_flag,
	hty.comment_text,
	hty.new_counter  as no_of_unexcused_absence,
	(SELECT LAST_NAME||', '||FIRST_NAME L_NAME FROM STAFF_MEMBERS WHERE STAFF_ID = hty.STAFF_ID ) as staff_Name
from
	OCDLEGLO_SANCTION_HTY hty where hty.offender_book_id  = :offender_book_id order by create_datetime desc
 }
 
OCDLEGLO_SANCTION_HISTORY{
insert
	into
	OCDLEGLO_SANCTION_HTY (
	off_sanction_sent_hty_id,
	offender_book_id,
	sentence_seq,
	order_type,
	adjust_reason,
	staff_id,
	adjust_date,
	adjust_time,
	new_counter,
	create_datetime,
	create_user_id,
	seal_flag,
	comment_text )
values(:offSanctionSentHtyId,
:offenderBookId,
:sentenceSeq,
:orderType,
:adjustReason,
:staffId,
:adjustDate,
:adjustTime,
:newCounter,
current_timestamp,
:createUserId,
:sealFlag,
:commentText)

}

OCDLEGLO_SANCTION_PRE_INSERT{
select nextval('OCDLEGLO_SANCTION_HTY_ID') from dual
}


