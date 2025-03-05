GTABS{
select t.table_name from information_schema."tables" t where upper(t.table_name) not like 'BIN%' and upper(t.table_schema) =:vpd_ss_owner and upper(t.table_name) not in ('COMM_GL_TRANSACTIONS', 'GL_TRANSACTIONS', 'COMM_INTERFACE_TMP', 'GL_TRANSACTIONS_JN','LV_OFFENDER_ID','CT_OFFENDERS','OFFENDER_DEDUCTIONS_TEMP','PURGED_OFFENDER_HISTORIES','OFFENDERS_JN','CURRENT_OFFENDERS','OFFENDER_DEDUCTIONS_JN','ORDERS_JN') and exists ( select 1 from information_schema."columns" c where c.table_name = t.table_name and c.table_schema = t.table_schema and upper(c.column_name) =:key_col) and table_type='BASE TABLE' 
}
GRID{
SELECT root_offender_id  FROM oms_owner.offenders WHERE offender_id = :p_off_id
}
GOID{
SELECT o1.offender_id  FROM oms_owner.offenders o1 WHERE o1.root_offender_id IN (SELECT o2.root_offender_id FROM oms_owner.offenders o2 WHERE o2.offender_id = :p_off_id)
}
GBID{
SELECT offender_book_id  FROM oms_owner.offender_bookings WHERE offender_id = :p_off_id
}
GETINDRID{
SELECT root_offender_id  FROM oms_owner.offender_bookings WHERE offender_book_id = :p_ob
}
TAG_SEALER_GET_TABLE_NAME{
SELECT
    concat(column_name,',',data_type)
FROM
    information_schema.columns
WHERE
    upper(table_schema) = 'OMS_OWNER' AND 
    table_name = :table AND
    (column_name)  = :column
    }
