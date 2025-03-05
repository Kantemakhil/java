TRANSFER_BOOKING_CORE_GET_ADM_DATE{
SELECT MIN(date_trunc('day', movement_date) + (movement_time - date_trunc('day', movement_time))) FROM offender_external_movements WHERE offender_book_id = :pOffBookId AND movement_seq = (SELECT MIN (movement_seq) FROM offender_external_movements WHERE offender_book_id = :pOffBookId AND movement_type = 'ADM')
}

TRANSFER_BOOKING_CORE_GET_INTAKE_DATE{
SELECT MIN (date_trunc('day', event_date) + (event_time - date_trunc('day', event_time))) FROM offender_booking_events WHERE offender_book_id = :pOffBookId AND event_seq = (SELECT MIN (event_seq) FROM offender_booking_events WHERE offender_book_id = :pOffBookId AND booking_event_code = 'INTAKE')
}


TRANSFER_BOOKING_CORE_GET_INST_END_DATE{
SELECT MIN (date_trunc('day',movement_date) + (movement_time - date_trunc('day',movement_time))) FROM offender_external_movements WHERE offender_book_id = :pOffBookId AND movement_seq = (SELECT MAX (movement_seq) FROM offender_external_movements WHERE offender_book_id = :pOffBookId AND movement_type = 'REL') AND movement_seq > (SELECT MAX (movement_seq) FROM offender_external_movements WHERE offender_book_id = :pOffBookId AND movement_type = 'ADM')
}

TRANSFER_BOOKING_CORE_GET_COMM_END_DATE{
SELECT MIN (date_trunc('day',event_date) + (event_time -date_trunc('day',event_time))) FROM offender_booking_events WHERE offender_book_id = :pOffBookId AND event_seq = (SELECT MAX (event_seq) FROM offender_booking_events WHERE offender_book_id = :pOffBookId AND booking_event_code IN ('CAC', 'RCO')) AND event_seq > (SELECT COALESCE (MAX (event_seq), 0) FROM offender_booking_events WHERE offender_book_id = :pOffBookId AND booking_event_code = 'INTAKE')
}

TRANSFER_BOOKING_CORE_IS_COMM_BOOKING{
SELECT COUNT (*) FROM offender_bookings WHERE community_active_flag IS NOT NULL AND offender_book_id = :pOffBookId
}
TRANSFER_BOOKING_CORE_INSERT_MERG_OFF_SQLS{
INSERT INTO merge_offender_sqls (sql_id, sql_text ) VALUES (:sqlId.NEXTVAL, :pSqlDml )
}


TRANSFER_BOOKING_CORE_MERGE_TABLE_LIST_CUR{
 select
	vmtr.table_name,
	vmtr.begin_date,
	vmtr.end_date,
	mt.root_offender_id_1,
	mt.root_offender_id_2,
	mt.offender_book_id_1,
	mt.offender_book_id_2
from
	v_merge_transaction_rules vmtr,
	merge_transactions mt
where
	vmtr.application_code = UPPER (:pApplnCode)
	and vmtr.merge_transaction_id = mt.merge_transaction_id
	and vmtr.merge_transaction_id = :pMergeTransactionId
	and vmtr.root_flag = 'Y'
	and vmtr.completed_flag = 'Y'
order by
	vmtr.transfer_seq
}

TRANSFER_BOOKING_CORE_CASCADE_TRANSFER_MERGE_TABLE_LIST_CUR{
SELECT table_name, pk_type, object_class, seq_column, transfer_date_column,
                parent_off_id_column, parent_seq_column, pre_processing_rule
           FROM transfer_booking_tables tbt, transfer_table_relationships mtr
          WHERE mtr.parent_table = :pTableName
            AND tbt.table_name = mtr.child_table
            AND mtr.active_flag = 'Y'
            AND completed_flag = 'Y'
}        

TRANSFER_BOOKING_CORE_GET_TRANSFER_BOOKING_TABLES_VALS{
SELECT pk_type, seq_column, filter_column, parent_off_id_column,
             parent_seq_column, surrogate_key_column, update_clause,
             booking_where_clause, pk_first_column, pk_second_column, root_flag,
             offender_id_column, object_class, transfer_date_column
        FROM transfer_booking_tables
       WHERE table_name = :pTableName
}

TRANSFER_BOOKING_CORE_GET_TR_TAB_REL_DATA{
SELECT fk_offender_id_column, fk_seq_column, fk_pk_type, child_update_clause
           FROM transfer_table_relationships
          WHERE parent_table = :pParentTableName AND child_table = :pTableName
}

TRANSFER_BOOKING_CORE_TRNSF_TBL_RLTN_DATA{
 SELECT fk_pk_type, fk_offender_id_column, fk_seq_column
        FROM transfer_table_relationships
       WHERE parent_table = :pParentTable AND child_table = :pChildTable
}

TRANSFER_BOOKING_CORE_TRANSFER_BKNG_TABLES_DATA{
 SELECT pk_type, offender_id_column, seq_column, surrogate_key_column,
             filter_column, booking_id_column
        FROM transfer_booking_tables
       WHERE table_name = :pTableName;
}

TRANSFER_BOOKING_CORE_GET_PREV_BOOK_ID{
SELECT offender_book_id
           FROM offender_bookings
          WHERE root_offender_id = :pRootOffId
            AND booking_begin_date =
                   (SELECT MAX (booking_begin_date)
                      FROM offender_bookings
                     WHERE root_offender_id = :pRootOffId
                       AND offender_book_id <> :pOffBookId
                       AND booking_begin_date <= (SELECT booking_begin_date
                                                    FROM offender_bookings
                                                   WHERE offender_book_id = :pOffBookId))
}

TRANSFER_BOOKING_CORE_GET_NEXT_BOOK_ID{
SELECT offender_book_id
           FROM offender_bookings
          WHERE root_offender_id = :pRootOffId
            AND booking_begin_date =
                   (SELECT MIN (booking_begin_date)
                      FROM offender_bookings
                     WHERE root_offender_id = :pRootOffId
                       AND offender_book_id <> :pOffBookId
                       AND booking_begin_date >= (SELECT booking_begin_date
                                                    FROM offender_bookings
                                                   WHERE offender_book_id = :pOffBookId))
}



