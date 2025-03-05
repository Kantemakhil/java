OMS_TRIGGER_OBJECTS_CREATE_ITEM_TRANSACTION{
--INSERT INTO OFFENDER_PPTY_ITEM_TXNS(property_item_txn_id, offender_book_id,property_item_seq, event_seq,from_status_code, to_status_code, comment_text,property_container_id, agy_loc_id,disposed_to_corp_id, disposed_to_person_id,disposed_to_person, disposed_to_offender_flag,verify_flag, verification_flag,property_container_txn_id, create_date,create_datetime,modify_datetime,create_user_id)VALUES (property_item_txn_id.NEXTVAL, :offenderBookId,:propertyItemSeq, :eventSeq,:fromStatusCode, :toStatusCode, :commentText,:propertyContainerId, :agyLocId,:disposedToCorpId, :disposedToPersonId,:disposedToPerson, :disposedToOffenderFlag,:verifyFlag, 'N',:propertyContainerTxnId, :createDate,:createDatetime,:modifyDatetime,:createUserId )
insert into OFFENDER_PPTY_ITEM_TXNS(property_item_txn_id, offender_book_id, property_item_seq, event_seq, from_status_code, to_status_code, comment_text, property_container_id, agy_loc_id, disposed_to_corp_id, disposed_to_person_id, disposed_to_person, disposed_to_offender_flag, verify_flag, verification_flag, property_container_txn_id, create_date, create_datetime, modify_datetime, create_user_id) values (NEXTVAL('property_item_txn_id'), :offenderBookId, :propertyItemSeq, :eventSeq, :fromStatusCode, :toStatusCode, :commentText, :propertyContainerId, :agyLocId, :disposedToCorpId, :disposedToPersonId, :disposedToPerson, :disposedToOffenderFlag, :verifyFlag, 'N', :propertyContainerTxnId, :createDate, current_timestamp, current_timestamp, :createUserId )
}
OMS_TRIGGER_OBJECTS_CREATE_CONTAINER_TRANSACTION{
insert into OFFENDER_PPTY_CON_TXNS(property_container_txn_id, property_container_id, action_code, agy_loc_id, seal_mark, comment_text, internal_location_id, trn_from_agy_loc_id, trn_to_agy_loc_id, create_date, create_datetime, modify_datetime, create_user_id,action_reason) values (NEXTVAL('property_container_txn_id'), :propertyContainerId, :actionCode, :agyLocId, :sealMark, :commentText, :internalLocationId, :trnFromAgyLocId, :trnToAgyLocId, current_timestamp, current_timestamp, current_timestamp, :createUserId,:actionReason)
}
OMS_TRIGGER_OBJECTS_DELETE_ITEM_TRANSACTION{
 DELETE FROM OFFENDER_PPTY_ITEM_TXNS WHERE offender_book_id = :offenderBookId AND property_item_seq = :propertyItemSeq  AND to_status_code = :toStatusCode
}
OMS_TRIGGER_OBJECTS_CHANGE_ITEMS_AGENCY_LOCATION{
-- UPDATE OFFENDER_PPTY_ITEMS SET agy_loc_id = :agyLocId,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId WHERE offender_book_id = :offenderBookId AND status_code = 'OUT'
update OFFENDER_PPTY_ITEMS set agy_loc_id = :agyLocId, modify_datetime =current_timestamp, modify_user_id =:modifyUserId where offender_book_id = :offenderBookId and status_code = 'OUT'
}
OMS_TRIGGER_OBJECTS_UPDATE_ITEM_TRANSACTION{
--UPDATE OFFENDER_PPTY_ITEM_TXNS SET comment_text = :commentText, create_date = :modifyDatetime, create_user_id = :createUserId,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId WHERE offender_book_id = p_offender_book_id AND property_item_seq = p_property_item_seq AND to_status_code = p_to_status_code
update OFFENDER_PPTY_ITEM_TXNS set comment_text = :commentText, modify_datetime = current_timestamp, modify_user_id =:modifyUserId where offender_book_id = :p_offender_book_id and property_item_seq = :p_property_item_seq and to_status_code = :p_to_status_code
}