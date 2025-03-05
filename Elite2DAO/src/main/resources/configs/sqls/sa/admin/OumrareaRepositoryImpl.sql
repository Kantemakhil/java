
OUMRAREA_FIND_RGAREATYPE {
 	SELECT CODE,DESCRIPTION       FROM REFERENCE_CODES     WHERE DOMAIN = 'AREA_TYPE'      AND (ACTIVE_FLAG = 'Y'      AND EXPIRED_DATE IS NULL )  ORDER BY LIST_SEQ , CODE , DESCRIPTION ASC
}

OUMRAREA_MAINTREG_FIND_AREAS {
SELECT
area_class,
area_code,
description,
parent_area_code,
list_seq,
active_flag,
expiry_date,
area_type,
create_datetime,
create_user_id,
modify_datetime,
modify_user_id,
seal_flag,
row_id
FROM
areas
}
OUMRAREA_MAINTREG_INSERT_AREAS {
 insert into AREAS(AREA_CLASS , AREA_CODE , DESCRIPTION , PARENT_AREA_CODE , LIST_SEQ , ACTIVE_FLAG , EXPIRY_DATE , AREA_TYPE ,
 CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:areaClass , :areaCode , :description , :parentAreaCode , :listSeq ,
 :activeFlag , :expiryDate , :areaType , current_timestamp , :createUserId , NULL , :sealFlag )
}

OUMRAREA_MAINTREG_UPDATE_AREAS {
UPDATE areas SET area_class = :areaClass, area_code = :areaCode, description = :description, parent_area_code = :parentAreaCode, list_seq = :listSeq, active_flag = :activeFlag, expiry_date = :expiryDate, area_type = :areaType, modify_datetime = current_timestamp, modify_user_id = :modifyUserId, seal_flag = :sealFlag WHERE ROW_ID =:rowId 
}

OUMRAREA_MAINTAREA_FIND_AREAS {
SELECT
    area_class,
    area_code,
    description,
    parent_area_code,
    list_seq,
    active_flag,
    expiry_date,
    area_type,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id,
    seal_flag,
     row_id
FROM
    areas
WHERE
    area_class = 'AREA'
    AND parent_area_code = :parentAreaCode
ORDER BY
    area_code ASC
}
OUMRAREA_MAINTAREA_INSERT_AREAS {
    insert into AREAS(AREA_CLASS , AREA_CODE , DESCRIPTION , PARENT_AREA_CODE , LIST_SEQ , ACTIVE_FLAG , EXPIRY_DATE , AREA_TYPE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:areaClass , :areaCode , :description , :parentAreaCode , :listSeq , :activeFlag , :expiryDate , :areaType , current_timestamp , :createUserId , NULL , :sealFlag )
} 

OUMRAREA_MAINTAREA_UPDATE_AREAS {
   update areas set area_class = :areaClass, area_code = :areaCode, description = :description, parent_area_code = :parentAreaCode, list_seq = :listSeq, active_flag = :activeFlag, expiry_date = :expiryDate, area_type = :areaType, modify_datetime = current_timestamp, modify_user_id = :modifyUserId, seal_flag = :sealFlag where ROW_ID = :rowId 
}

OUMRAREA_MAINTSUBAREA_FIND_AREAS {
SELECT
    area_class,
    area_code,
    description,
    parent_area_code,
    list_seq,
    active_flag,
    expiry_date,
    area_type,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id,
    seal_flag,
 row_id
FROM
    areas
WHERE
    area_class = 'SUB_AREA'
    AND parent_area_code = :parentAreaCode
ORDER BY
    area_code ASC
}
OUMRAREA_MAINTSUBAREA_INSERT_AREAS {
 insert into AREAS(AREA_CLASS , AREA_CODE , DESCRIPTION , PARENT_AREA_CODE , LIST_SEQ , ACTIVE_FLAG , EXPIRY_DATE , AREA_TYPE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:areaClass , :areaCode , :description , :parentAreaCode , :listSeq , :activeFlag , :expiryDate , :areaType , current_timestamp , :createUserId , NULL , :sealFlag )
}

OUMRAREA_MAINTSUBAREA_UPDATE_AREAS {
 update AREAS set AREA_CLASS = :areaClass , AREA_CODE = :areaCode , DESCRIPTION = :description , PARENT_AREA_CODE = :parentAreaCode , LIST_SEQ = :listSeq , ACTIVE_FLAG = :activeFlag , EXPIRY_DATE = :expiryDate , AREA_TYPE = :areaType , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where ROW_ID = :rowId
}


OUMRAREA_MAINT_REG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM AREAS A WHERE A.PARENT_AREA_CODE = :AREACODE
}

OUMRAREA_MAINT_AREA_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM AREAS A WHERE A.PARENT_AREA_CODE = :AREACODE
}
OUMRAREA_CONSTRAINT_VIOLATIONS {
SELECT AC1.TABLE_NAME FROM ALL_CONSTRAINTS AC1, ALL_CONSTRAINTS AC2 WHERE AC1.CONSTRAINT_NAME = :CONSTRAINTNAME AND AC2.TABLE_NAME = 'AREAS' AND AC1.CONSTRAINT_TYPE = 'R' AND AC2.CONSTRAINT_NAME = AC1.R_CONSTRAINT_NAME AND AC2.OWNER = AC1.R_OWNER AND AC2.CONSTRAINT_TYPE IN ('P', 'U')
}