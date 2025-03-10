MERGE_OFFENDER_CORE_MERGE_TABLE_LIST_CURSER_DATA{
SELECT TABLE_NAME
           FROM MERGE_OFFENDER_TABLES
          WHERE APPLICATION_CODE = UPPER (:applnCode)
            AND ROOT_FLAG = 'Y'
            AND COMPLETED_FLAG = 'Y'
}

MERGE_OFFENDER_CORE_MERGE_TABLE_RELATED_DATA{
 SELECT PK_TYPE, ADDITIONAL_UPDATE_RULE, SEQ_COLUMN,
             OFFENDER_ID_COLUMN, OBJECT_CLASS
        FROM MERGE_OFFENDER_TABLES
       WHERE TABLE_NAME = :tableName;
}

MERGE_OFFENDER_CORE_EXTRA_PK_COLUMN{
SELECT SEQ_COLUMN FROM MERGE_OFFENDER_TABLES WHERE TABLE_NAME = :tableName
}

MERGE_OFFENDER_CORE_INSERT_MERGE_OFFENDER_SQLS{
insert into MERGE_OFFENDER_SQLS (SQL_ID, SQL_TEXT ) values (SQL_ID.NEXTVAL, :pSqlDml )
}
