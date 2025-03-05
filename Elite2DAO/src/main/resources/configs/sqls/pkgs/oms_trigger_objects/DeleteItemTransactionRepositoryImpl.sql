
OFFENDER_PPTY_ITEM_TXNS_DELETE{
DELETE FROM OFFENDER_PPTY_ITEM_TXNS WHERE offender_book_id = :p_offender_book_id AND property_item_seq = :p_property_item_seq AND to_status_code = :p_to_status_code
}
OFFENDER_PPTY_ITEM_TXNS_UPDATE{
 UPDATE OFFENDER_PPTY_ITEM_TXNS SET comment_text = :p_comment_text, create_date = current_timestamp , create_user_id = :createUserId WHERE offender_book_id = :p_offender_book_id AND property_item_seq = :p_property_item_seq AND to_status_code = :p_to_status_code 
 }