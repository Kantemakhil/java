

UPDATE_ORDER_STATUS_GET_EVENT_ID{
SELECT  event_id
                FROM orders
                WHERE offender_book_id = :P_OFFENDER_BOOK_ID
                AND workflow_id = :P_MSG_ID
            FOR UPDATE OF order_status
}

UPDATE_ORDER_STATUS_UPDATE_ODER_STATUS{
/* UPDATE orders
            SET order_status = 'IN'
            WHERE offender_book_id = :P_OFFENDER_BOOK_ID AND workflow_id = :P_MSG_ID  */
update orders set order_status = 'IN', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :P_OFFENDER_BOOK_ID and workflow_id = :P_MSG_ID
 }      