
OIMLEGST_FIND_RGCAT {
 	SELECT DESCRIPTION ,        CODE    FROM REFERENCE_CODES   WHERE DOMAIN = 'LGL_RSN_CAT'    AND (   (ACTIVE_FLAG = 'Y' )              ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIMLEGST_FIND_RGSTAT {
 	SELECT DESCRIPTION ,        CODE    FROM REFERENCE_CODES   WHERE DOMAIN = 'ACTIVE_TYPE'    AND (   (ACTIVE_FLAG = 'Y' )            ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIMLEGST_UPDATEREASONS_FIND_LEGAL_UPDATE_REASONS {
 	SELECT UPDATE_REASON_CODE ,DESCRIPTION ,EFFECTIVE_DATE ,REASON_CATEGORY ,ACTIVE_TYPE ,LIST_SEQ ,EXPIRY_DATE ,ACTIVE_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM LEGAL_UPDATE_REASONS
}
OIMLEGST_UPDATEREASONS_INSERT_LEGAL_UPDATE_REASONS {
 insert into LEGAL_UPDATE_REASONS(UPDATE_REASON_CODE , DESCRIPTION , EFFECTIVE_DATE , REASON_CATEGORY , ACTIVE_TYPE , LIST_SEQ , EXPIRY_DATE , ACTIVE_FLAG , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:updateReasonCode , :description , :effectiveDate , :reasonCategory , :activeType , :listSeq , :expiryDate , :activeFlag , current_timestamp , :createUserId , NULL , :sealFlag ) 
}

OIMLEGST_UPDATEREASONS_UPDATE_LEGAL_UPDATE_REASONS {
	update LEGAL_UPDATE_REASONS set UPDATE_REASON_CODE = :updateReasonCode , DESCRIPTION = :description , EFFECTIVE_DATE = :effectiveDate , REASON_CATEGORY = :reasonCategory , ACTIVE_TYPE = :activeType , LIST_SEQ = :listSeq , EXPIRY_DATE = :expiryDate , ACTIVE_FLAG = :activeFlag , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where UPDATE_REASON_CODE = :updateReasonCode
}

OIMLEGST_UPDATEREASONS_DELETE_LEGAL_UPDATE_REASONS { 
	DELETE FROM LEGAL_UPDATE_REASONS where  UPDATE_REASON_CODE  = :updateReasonCode
}

OIMLEGST_GET_NBT_REASON_CATEGORY{
select OMS_MISCELLANEOUS_GetDescCode( 'LGL_RSN_CAT', :REASONCATEGORY) from dual
}
OIMLEGST_GET_NBT_ACTIVE_TYPE{
select OMS_MISCELLANEOUS_GetDescCode( 'ACTIVE_TYPE', :ACTIVETYPE) from dual
}
OIMLEGST_GET_LISTPARENT_TABLES{
select
	upper(tc.table_name) as table_name,
	upper(kcu.column_name) as column_name
from
	information_schema.table_constraints as tc
join information_schema.key_column_usage as kcu

      on
	tc.constraint_name = kcu.constraint_name
	and tc.table_schema = kcu.table_schema
join information_schema.constraint_column_usage as ccu

      on
	ccu.constraint_name = tc.constraint_name
	and ccu.table_schema = tc.table_schema
where
	tc.constraint_type = 'FOREIGN KEY'
	and upper(ccu.table_name)= 'LEGAL_UPDATE_REASONS'
}
