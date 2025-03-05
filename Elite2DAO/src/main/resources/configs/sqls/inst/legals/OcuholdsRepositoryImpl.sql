DISPLAY_HOLDS_REMANDS {
select 
ORDER_TYPE,
ISSUING_AGY_LOC_ID,
COURT_DATE,
COMPLETE_DATE,
COMMENT_TEXT,
ORDER_STATUS,
CASE_ID,
OFFENDER_BOOK_ID,
EVENT_ID,
ORDER_ID
from orders
where event_id = :eventId
AND order_type IN (SELECT order_type
                        FROM order_types
                       WHERE order_category = 'HOLD_ORDER')

}

LOV_ORDER_TYPE{
SELECT description description,
order_type report_type
FROM order_types
WHERE order_category = 'HOLD_ORDER'
AND (active_flag = 'Y')
Order by description,order_type ASC   
}


LOV_HOLD_STATUS {
SELECT description,code 
  FROM reference_codes 
 WHERE domain = 'HOLD_STATUS' 
   AND (active_flag = 'Y')
 ORDER BY description,list_seq 
 }
 
COURT_DATA {
SELECT   description,
         al.agy_loc_id
    FROM agency_locations al
   WHERE agency_location_type = 'CRT'
     AND (active_flag = 'Y' AND deactivation_date IS NULL)
     AND al.agy_loc_id NOT IN ('TRN', 'OUT')
ORDER BY list_seq,description
}

INSERT_HOLDS_DATA{
--INSERT INTO ORDERS (ORDER_TYPE,ISSUING_AGY_LOC_ID,COURT_DATE,COMPLETE_DATE,COMMENT_TEXT,ORDER_STATUS,CASE_ID,OFFENDER_BOOK_ID,EVENT_ID,ORDER_ID,CREATE_DATETIME,CREATE_USER_ID)
--VALUES(:orderType,:issuingAgyLocId,:orderDate,:expiryDate,:commentText,:orderStatus,TO_NUMBER(:caseId,'9G999g999'),:offenderBookId,:eventId,:orderId,CURRENT_TIMESTAMP,:createUserId)
INSERT INTO ORDERS (ORDER_TYPE,ISSUING_AGY_LOC_ID,COURT_DATE,COMPLETE_DATE,COMMENT_TEXT,ORDER_STATUS,CASE_ID,OFFENDER_BOOK_ID,EVENT_ID,ORDER_ID,CREATE_DATETIME,CREATE_USER_ID)
VALUES(:orderType,:issuingAgyLocId,:orderDate,:expiryDate,:commentText,:orderStatus,case
	when coalesce(:caseId, null) = null then null
	else :caseId::bigint end,:offenderBookId,:eventId,:orderId,CURRENT_TIMESTAMP,:createUserId)

}

UPDATE_HOLDS_DATA{
UPDATE ORDERS
SET COMMENT_TEXT =:commentText,
    ORDER_STATUS =:orderStatus,
    MODIFY_DATETIME =current_timestamp,
	MODIFY_USER_ID = :modifyUserId
  WHERE OFFENDER_BOOK_ID=:offenderBookId AND EVENT_ID=:eventId AND ORDER_ID=:orderId
}

PREINSERT_ORDER_ID{
SELECT NEXTVAL('order_id') FROM dual
}

CALC_EXP_DATE{
SELECT custody_days
        FROM order_types
       WHERE order_category = 'HOLD_ORDER'
         AND order_type = :orderType
}

DELETE_HOLDS_RECORD{
	DELETE FROM ORDERS WHERE order_id = :orderId 
}

UPDATE_EVENT_FOR_HOLDS_DATA{
UPDATE COURT_EVENTS
SET HOLD_FLAG='Y',
MODIFY_DATETIME =current_timestamp,
MODIFY_USER_ID = :modifyUserId
WHERE EVENT_ID=:eventId 
}

OLD_VALUE_ORDERS{
select * from ORDERS where ORDER_ID =:ORDERID
}