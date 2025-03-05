 
OUMCDTAB_FIND_CGFKMODIFYTABMOVEMENTTYPE {
 	SELECT REF_CODE1.CODE  CODE ,REF_CODE1.DESCRIPTION  DESCRIPTION, REF_CODE1.LIST_SEQ LIST_SEQ FROM   REFERENCE_CODES REF_CODE1 WHERE   DOMAIN = 'MOVE_TYPE' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL AND REF_CODE1.CODE IN (SELECT DISTINCT MOVEMENT_TYPE  FROM    MOVEMENT_REASONS ) ))   ORDER BY  REF_CODE1.LIST_SEQ ASC
}

OUMCDTAB_FIND_CGFKMODIFYTABMOVEMENTREASON {
 	SELECT move_rsn.movement_reason_code code, move_rsn.description description, move_rsn.list_seq list_seq, move_rsn.active_flag active_flag FROM movement_reasons move_rsn WHERE movement_type = :movementType AND ( movement_type != 'CRI' ) UNION ALL SELECT ref_code1.code, ref_code1.description dsp_description, ref_code1.list_seq dsp_list_seq, ref_code1.active_flag FROM reference_codes ref_code1 WHERE domain = 'INTAKE_RSN' AND parent_code = 'INTAKE' AND ( active_flag = 'Y' AND expired_date IS NULL ) AND :movementType = 'CRI' ORDER BY 3 ASC
}

  OUMCDTAB_FIND_LOVPARENTTABLE {
SELECT DISTINCT
	upper(B.TABLE_NAME) CODE,
	upper(B.TABLE_NAME )DESCRIPTION
FROM
	PG_TABLES A,
	INFORMATION_SCHEMA.COLUMNS B
WHERE
	A.TABLENAME = B.TABLE_NAME
	AND UPPER(TABLESPACE)=(
	SELECT
		PROFILE_VALUE
	FROM
		SYSTEM_PROFILES
	WHERE
		PROFILE_TYPE = 'SYS'
		AND PROFILE_CODE = 'DEFTABLESP')
order by
	1

}

OUMCDTAB_FIND_LOVTABLENAME {
select
	distinct
	UPPER(B.table_name) code,
	UPPER(B.table_name) description
from
	pg_tables A,
	information_schema.columns B
where
	A.tablename = B.table_name
	and upper(tablespace)=(
	select
		profile_value
	from
		SYSTEM_PROFILES
	where
		profile_type = 'SYS'
		and profile_code = 'DEFTABLESP')
	and exists (
	select
		1
	from
		information_schema.columns B
	where
		b.table_name = a.tablename
		and upper(B.column_name) in ( 'OFFENDER_BOOK_ID', 'OFFENDER_BOOKING_ID' ) )
order by
	1
}

OUMCDTAB_FIND_LOVCOLUMNNAME {
select
	upper(COLUMN_NAME) as CODE,
	upper(COLUMN_NAME) as DESCRIPTION
from
	information_schema.columns
where
	upper(TABLE_NAME) = upper(:tableName)
order by
	1;
}

OUMCDTAB_FIND_LOVSEQNAME {
select
	distinct 
	UPPER(SEQUENCE_NAME) CODE,
	UPPER(SEQUENCE_NAME) DESCRIPTION
from
	ALL_SEQUENCES
where
	SEQUENCE_OWNER not in ('SYSTEM' , 'SYS' )
order by
	1

}

OUMCDTAB_MODIFYTAB_FIND_COPY_TABLES {
select
TABLE_OPERATION_CODE ,
MOVEMENT_TYPE ,
MOVEMENT_REASON_CODE ,
upper(TABLE_NAME) as TABLE_NAME ,
ACTIVE_FLAG ,
EXPIRY_DATE ,
LIST_SEQ ,
upper(PARENT_TABLE) as PARENT_TABLE ,
CREATE_USER_ID ,
CREATE_DATETIME ,
MODIFY_DATETIME ,
MODIFY_USER_ID ,
COL_NAME ,
SEQ_NAME ,
UPDATE_ALLOWED_FLAG ,
SEAL_FLAG
from
COPY_TABLES
order by
TABLE_OPERATION_CODE,
MOVEMENT_TYPE,
MOVEMENT_REASON_CODE,
LIST_SEQ
}
OUMCDTAB_MODIFYTAB_INSERT_COPY_TABLES {
insert into COPY_TABLES(TABLE_OPERATION_CODE , MOVEMENT_TYPE , MOVEMENT_REASON_CODE , TABLE_NAME , ACTIVE_FLAG , EXPIRY_DATE , LIST_SEQ , PARENT_TABLE , CREATE_USER_ID , CREATE_DATETIME , MODIFY_DATETIME , COL_NAME , SEQ_NAME , UPDATE_ALLOWED_FLAG , SEAL_FLAG ) values(:tableOperationCode , :movementType , :movementReasonCode , :tableName , :activeFlag , :expiryDate , :listSeq , :parentTable , :createUserId , current_timestamp , NULL , :colName , :seqName , :updateAllowedFlag , :sealFlag )
}

OUMCDTAB_MODIFYTAB_UPDATE_COPY_TABLES {
 update COPY_TABLES set TABLE_OPERATION_CODE = :tableOperationCode , MOVEMENT_TYPE = :movementType , MOVEMENT_REASON_CODE = :movementReasonCode , TABLE_NAME = :tableName , ACTIVE_FLAG = :activeFlag , EXPIRY_DATE = :expiryDate , LIST_SEQ = :listSeq , PARENT_TABLE = :parentTable , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , COL_NAME = :colName , SEQ_NAME = :seqName , UPDATE_ALLOWED_FLAG = :updateAllowedFlag , SEAL_FLAG = :sealFlag where TABLE_OPERATION_CODE = :tableOperationCode and MOVEMENT_TYPE = :movementType and MOVEMENT_REASON_CODE = :movementReasonCode and TABLE_NAME = :tableName
}

 OUMCDTAB_MODIFYTAB_DELETE_COPY_TABLES { 
	DELETE FROM COPY_TABLES  where TABLE_OPERATION_CODE  = :tableOperationCode AND MOVEMENT_TYPE  = :movementType AND MOVEMENT_REASON_CODE  = :movementReasonCode AND TABLE_NAME  = :tableName
}

