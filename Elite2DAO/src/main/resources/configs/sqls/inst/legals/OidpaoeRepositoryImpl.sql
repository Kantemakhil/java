GET_PAYROL_EVENT_BY_OFFENDER_BOOKID {
	SELECT OFFENDER_BOOK_ID, PAROLE_EVENT_ID, EVENT_DATE, PAROLE_EVENT, "comment", CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID FROM OFFENDER_PAROLE_EVENTS WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID ORDER BY OFFENDER_BOOK_ID, PAROLE_EVENT_ID
}
GET_MAX_EVENT_ID_BY_OFFENDER_BOOKID {
	SELECT MAX(PAROLE_EVENT_ID) FROM OFFENDER_PAROLE_EVENTS WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
}
INSERT_PAYROL_EVENT{
	INSERT INTO OFFENDER_PAROLE_EVENTS
	(OFFENDER_BOOK_ID, PAROLE_EVENT_ID, EVENT_DATE, PAROLE_EVENT, "comment", CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID)
	VALUES(:offenderBookId, :paroleEventId, :eventDate, :paroleEvent, :comment, CURRENT_TIMESTAMP, :createUserId, null, null);
}

UPDATE_PAYROL_EVENT{
	UPDATE offender_parole_events
	SET event_date=:eventDate,  "comment"=:comment,  modify_datetime=CURRENT_TIMESTAMP, modify_user_id= :createUserId
	WHERE offender_book_id=:offenderBookId AND parole_event_id=:paroleEventId
}

INSERT_PAYROL_ADJUSTMENTS{
	INSERT INTO offender_legal_adjustments 
   (offender_order_adjust_id, adjust_code, offender_book_id, object_id, object_type,adjust_date, adjust_days, adjust_from_date, adjust_to_date,comment_text, seal_flag, create_datetime, create_user_id, modify_datetime, modify_user_id) 
    VALUES (nextval('offender_order_adjust_id_seq'), :orderAdjustCode, :offenderBookId, :objectId, :objectType, :adjustDate, :adjustDays, :adjustFromDate, :adjustToDate, :commentText, :sealFlag, CURRENT_TIMESTAMP, :createUserId, null, null)
}

UPDATE_PAYROL_ADJUSTMENTS{
	UPDATE offender_legal_adjustments SET 
	object_id = :objectId,
	object_type = :objectType,
	adjust_date = :adjustDate,
	adjust_days = :adjustDays,
	adjust_from_date = :adjustFromDate,
	adjust_to_date = :adjustToDate,
	comment_text = :commentText,
	seal_flag = :sealFlag,
	modify_datetime = CURRENT_TIMESTAMP,
	modify_user_id = :createUserId 
	WHERE offender_book_id = :offenderBookId and object_id =:objectId and object_type = :objectType and adjust_code =:orderAdjustCode and offender_order_adjust_id =:offenderOrderAdjustId
}

GET_PAYROL_EVENT_BY_OFFENDER_BOOKID_OBJECT_ID_OBJECT_TYPE {
	SELECT offender_order_adjust_id, adjust_code, offender_book_id, object_id, object_type, adjust_date, adjust_days,  adjust_from_date, adjust_to_date, comment_text, seal_flag, create_datetime, create_user_id, modify_datetime, modify_user_id
	FROM offender_legal_adjustments 
	WHERE offender_book_id = :OFFENDER_BOOK_ID AND object_id = :OBJECT_ID AND object_type = :OBJECT_TYPE ORDER BY create_datetime

}

GET_ADUJSTMENT_TYPE{
	select sentence_adjust_code as code, description,active_flag  from oms_owner.SENTENCE_ADJUSTMENTS where usage_code = 'PAR';
}

DELETE_ADUJSTMENT_BY_OFFENDERBOOKID_OBJECTID_OBJECTTYPE{
	delete from offender_legal_adjustments where offender_book_id  =:offenderBookId and object_id =:objectId and object_type = :objectType
}

DELETE_ADUJSTMENT_BY_ADJUSTMENTID{
	delete from offender_legal_adjustments where offender_order_adjust_id  =:offenderOrderAdjustId
}

DELETE_PAROLE_EVENTS_BY_OFFENDERBOOKID_PAROLEEVENTID{
	DELETE FROM OFFENDER_PAROLE_EVENTS WHERE offender_book_id = :offenderBookId AND parole_event_id =:paroleEventId
}

GET_PAYROL_ADJUSTMENT_BY_OFFENDER_BOOKID{
	SELECT offender_order_adjust_id, adjust_code, offender_book_id, object_id, object_type, adjust_date, adjust_days,  adjust_from_date, adjust_to_date, comment_text, seal_flag, create_datetime, create_user_id, modify_datetime, modify_user_id
	FROM offender_legal_adjustments 
	WHERE offender_book_id = :OFFENDER_BOOK_ID

}

