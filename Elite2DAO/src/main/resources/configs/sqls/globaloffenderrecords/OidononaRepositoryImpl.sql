OIDONONA_FIND_CGFKOFFNADDSPDESCRIPTION3 {
 	SELECT REF_CODE1.DESCRIPTION  DSP_DESCRIPTION3,REF_CODE1.DOMAIN  DSP_DOMAIN2        ,REF_CODE1.LIST_SEQ  DSP_LIST_SEQ2        ,REF_CODE1.ACTIVE_FLAG  DSP_ACTIVE_FLAG2        ,REF_CODE1.MODIFY_USER_ID  DSP_MODIFY_USER_ID2        ,REF_CODE1.EXPIRED_DATE  DSP_EXPIRED_DATE2        ,REF_CODE1.NEW_CODE  DSP_NEW_CODE2        ,REF_CODE1.PARENT_CODE  DSP_PARENT_CODE2        ,REF_CODE1.CODE  NS_REASON_CODE FROM   REFERENCE_CODES REF_CODE1 WHERE   REF_CODE1.DOMAIN='NON_ASSO_RSN' AND REF_CODE1.ACTIVE_FLAG='Y' AND REF_CODE1.EXPIRED_DATE IS NULL ORDER BY  REF_CODE1.CODE ASC
}

OIDONONA_FIND_OFFNADSPRECIPRSN {
 	SELECT REF_CODE1.DESCRIPTION DSP_RECIP_RSN ,REF_CODE1.CODE RECIP_NS_REASON_CODE FROM REFERENCE_CODES REF_CODE1 WHERE REF_CODE1.DOMAIN='NON_ASSO_RSN' AND REF_CODE1.ACTIVE_FLAG='Y' AND REF_CODE1.EXPIRED_DATE IS NULL ORDER BY REF_CODE1.CODE ASC
}

OIDONONA_FIND_CGFKOFFNADDSPDESCRIPTION {
 	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION , REF_CODE.DOMAIN as DOMAIN,
  REF_CODE.LIST_SEQ LIST_SEQ, REF_CODE.ACTIVE_FLAG ACTIVE_FLAG,
  REF_CODE.MODIFY_USER_ID MODIFY_USER_ID, REF_CODE.EXPIRED_DATE EXPIRED_DATE,
  REF_CODE.NEW_CODE DSP_NEW_CODE, REF_CODE.PARENT_CODE PARENT_CODE,
  REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  REF_CODE.DOMAIN = 'NON_ASSO_TYP' AND
  REF_CODE.ACTIVE_FLAG = 'Y' AND
  REF_CODE.EXPIRED_DATE IS NULL
AND REF_CODE.CODE != 'STG' ORDER BY REF_CODE.CODE ASC
}

OIDONONA_OFFNA_FIND_OFFENDER_NON_ASSOCIATIONS {
 	SELECT *  FROM OFFENDER_NON_ASSOCIATIONS 
}

OIDONONA_OFFNAD_FIND_OFFENDER_NA_DETAILS {
 	SELECT  * FROM OFFENDER_NA_DETAILS 
}

OIDONONA_OFFNAD_UPDATE_OFFENDER_NA_DETAILS {
	UPDATE OFFENDER_NA_DETAILS set NS_REASON_CODE=:nsReasonCode, NS_TYPE=:nsType,modify_datetime = CURRENT_TIMESTAMP,MODIFY_USER_ID=:modifyUserId,
NS_EFFECTIVE_DATE=:nsEffectiveDate, NS_EXPIRY_DATE=:nsExpiryDate,AUTHORIZED_STAFF=:authorizedStaff,COMMENT_TEXT=:commentText  WHERE OFFENDER_ID= :offenderId
      AND NS_OFFENDER_ID = :nsOffenderId AND TYPE_SEQ=:typeSeq
}

OIDONONA_STGRELATIONSHIPS_FIND_STG_RELATIONSHIPS {
 	SELECT   FROM STG_RELATIONSHIPS  
}
OIDONONA_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT   FROM SYSTEM_PROFILES 
}
OIDONONA_SYSPFL_INSERT_SYSTEM_PROFILES {
	INSERT INTO SYSTEM_PROFILES() VALUES(:)
} developer need to verify

OIDONONA_SYSPFL_DELETE_SYSTEM_PROFILES { 
	DELETE FROM SYSTEM_PROFILES
} developer need to verify


OIDONONA_OFF_BKG_PREDELETEPRE-DELETE {
	DELETE FROM OFFENDER_NON_ASSOCIATIONS O WHERE O.OFFENDER_ID = :ROOTOFFENDERID
} developer need to verify


OIDONONA_CGRICHK_OFFENDER_NON_ASSOCIAT_ {
	select
	'1'
from
	OFFENDER_NA_DETAILS OFF_NAD
where
	OFF_NAD.OFFENDER_ID = :P_OFFENDER_ID
	and OFF_NAD.NS_OFFENDER_ID = :P_NS_OFFENDER_ID
}

OIDONONA_OFF_NA_VALIDATE_RSN_ {
	SELECT REF_CODE1.CODE FROM REFERENCE_CODES REF_CODE1 WHERE  (REF_CODE1.DESCRIPTION = :DSPRECIPRSN OR (REF_CODE1.DESCRIPTION IS NULL AND :DSPRECIPRSN IS NULL )) AND  REF_CODE1.DOMAIN='NON_ASSO_RSN'
}

OIDONONA_OFF_NA_CHK_RSN_ {
	SELECT REF_CODE1.DESCRIPTION FROM   REFERENCE_CODES REF_CODE1 WHERE  REF_CODE1.CODE = :RECIPNSREASONCODE AND     REF_CODE1.DOMAIN='NON_ASSO_RSN'
}

OIDONONA_GET_STG_GROUP_DESCRIPTION_ {
	select
	case
		when coalesce(STG3.DESCRIPTION::text, '') = '' then null
		else STG3.DESCRIPTION || '-'
	end ||
	case
		when coalesce(STG2.DESCRIPTION::text, '') = '' then null
		else STG2.DESCRIPTION || '-'
	end || STG1.DESCRIPTION
from
	SECURITY_THREAT_GROUPS STG1,
	SECURITY_THREAT_GROUPS STG2,
	SECURITY_THREAT_GROUPS STG3
where
	STG1.STG_ID = :RELATEDSTGID
	and STG1.STG_LEVEL = 'SET'
	and STG1.ACTIVE_FLAG = 'Y'
	and STG1.PARENT_STG_ID = STG2.STG_ID
	and STG2.PARENT_STG_ID = STG3.STG_ID
union
 select
	case
		when coalesce(STG2.DESCRIPTION::text, '') = '' then null
		else STG2.DESCRIPTION || '-'
	end || STG1.DESCRIPTION
from
	SECURITY_THREAT_GROUPS STG1,
	SECURITY_THREAT_GROUPS STG2
where
	STG1.STG_ID = :RELATEDSTGID
	and STG1.STG_LEVEL = 'GANG'
	and STG1.ACTIVE_FLAG = 'Y'
	and STG1.PARENT_STG_ID = STG2.STG_ID
union
 select
	DESCRIPTION
from
	SECURITY_THREAT_GROUPS
where
	STG_ID = :RELATEDSTGID
	and STG_LEVEL = 'NATION'
	and ACTIVE_FLAG = 'Y'
}
OIDONONA_GET_STG_GROUP_ENEMY {
SELECT * FROM STG_RELATIONSHIPS WHERE relationship_type = 'ENEMY' and stg_id in (SELECT STG_ID 
        FROM OFFENDER_STG_AFFILIATIONS
             WHERE OFFENDER_BOOK_ID =  :offenderBookId  AND ACTIVE_FLAG = 'Y')
             }

       
             

OIDONONA_GET_ACTIVEFLG {
select
	COUNT(*) NUMBER_ACTIVE
from
	OFFENDER_NA_DETAILS
where
	OFFENDER_ID = :offenderId
	and NS_OFFENDER_ID = :nsOffenderId
	and (NS_EXPIRY_DATE>localtimestamp
	or coalesce(NS_EXPIRY_DATE::text, '') = '')
}
OIDONONA_GET_NBT_RELATED_STGID_DESCRIPTION {
        SELECT CASE WHEN coalesce(stg3.description::text, '') = '' THEN  NULL  ELSE stg3.description || '-' END  ||
             CASE WHEN coalesce(stg2.description::text, '') = '' THEN  NULL  ELSE stg2.description || '-' END  ||
             stg1.description Description
        FROM security_threat_groups stg1,
             security_threat_groups stg2,
             security_threat_groups stg3
       WHERE stg1.stg_id = :relatedStgId
         AND stg1.stg_level = 'SET'
         AND stg1.active_flag = 'Y'
         AND stg1.parent_stg_id = stg2.stg_id
         AND stg2.parent_stg_id = stg3.stg_id
UNION

      SELECT CASE WHEN coalesce(stg2.description::text, '') = '' THEN  NULL  ELSE stg2.description || '-' END  ||
             stg1.description
        FROM security_threat_groups stg1,
             security_threat_groups stg2
       WHERE stg1.stg_id = :relatedStgId
         AND stg1.stg_level = 'GANG'
         AND stg1.active_flag = 'Y'
         AND stg1.parent_stg_id = stg2.stg_id
      UNION
      SELECT description
        FROM security_threat_groups
       WHERE stg_id = :relatedStgId
         AND stg_level = 'NATION'
         AND active_flag = 'Y'
         }
OIDONONA_GET_ACTIVEFLG {
SELECT COUNT(*)	NUMBER_ACTIVE	FROM OFFENDER_NA_DETAILS	WHERE OFFENDER_ID=:offenderId	AND NS_OFFENDER_ID=:nsOffenderId
	AND (NS_EXPIRY_DATE>current_timestamp OR NS_EXPIRY_DATE IS NULL)
}
OIDONONA_COMPAREEFFECTIVEDATEC {
SELECT oms_date_time_compare_date_time(TO_DATE(:effectiveDate, 'DD/MM/YYYY')::text,null,
          to_char(LOCALTIMESTAMP,'DD-MON-YYYY HH24:MI:SS')::text,null)  as TO_DATE from dual;
}
OIDONONA_PERINSERT {
SELECT coalesce (MAX(TYPE_SEQ)+1, 1)MAX_REC	FROM OFFENDER_NA_DETAILS
	WHERE OFFENDER_ID=:offenderId AND NS_OFFENDER_ID=:nsOffenderId
}
OIDONONA_OFFNA_INSERT_OFFENDER_NON_ASSOCIATIONS {
	INSERT INTO OFFENDER_NON_ASSOCIATIONS(
                       OFFENDER_ID
                      ,NS_OFFENDER_ID
                      ,OFFENDER_BOOK_ID
                      ,NS_OFFENDER_BOOK_ID
                      ,NS_REASON_CODE
                      ,NS_LEVEL_CODE
                      ,INTERNAL_LOCATION_FLAG
                      ,TRANSPORT_FLAG
                      ,RECIP_NS_REASON_CODE,
                      CREATE_USER_ID,
                     CREATE_DATETIME,
                     modify_datetime)
    VALUES (       
                       :offenderId
                      ,:nsOffenderId
                      ,:offenderBookId
                      ,:nsOffenderBookId
                      ,:nsReasonCode
                      ,:nsLevelCode
                      ,:internalLocationFlag
                      ,:transportFlag
                      ,:recipNsReasonCode
                      ,:createUserId
                      ,CURRENT_TIMESTAMP,
                      NULL)
}     
OIDONONA_OFFNAD_INSERT_OFFENDER_NA_DETAILS {
	INSERT INTO OFFENDER_NA_DETAILS (
                       OFFENDER_ID
                      ,NS_OFFENDER_ID
                      ,OFFENDER_BOOK_ID
                      ,NS_OFFENDER_BOOK_ID
                      ,TYPE_SEQ
                      ,NS_REASON_CODE
                      ,NS_LEVEL_CODE
                      ,NS_TYPE
                      ,NS_EFFECTIVE_DATE
                      ,NS_EXPIRY_DATE
                      ,AUTHORIZED_STAFF
                      ,COMMENT_TEXT
                      ,RECIP_NS_REASON_CODE
                      , CREATE_USER_ID,
                     CREATE_DATETIME,
                     modify_datetime)
     VALUES (        
                       :offenderId
                      ,:nsOffenderId
                      ,:offenderBookId
                      ,:nsOffenderBookId
                      ,:typeSeq
                      ,:nsReasonCode
                      ,:nsLevelCode
                      ,:nsType
                      ,:nsEffectiveDate
                      ,:nsExpiryDate
                      ,:authorizedStaff
                      ,:commentText
                      ,NULL,
                      :createUserId
                      ,CURRENT_TIMESTAMP,
                      NULL)
}       
OIDONONA_OFFNA_UPDATE_OFFENDER_NON_ASSOCIATIONS {
UPDATE OFFENDER_NON_ASSOCIATIONS
      SET RECIP_NS_REASON_CODE = :nsReasonCode,modify_datetime = CURRENT_TIMESTAMP,MODIFY_USER_ID=:modifyUserId
    WHERE OFFENDER_ID          = :offenderId
      AND NS_OFFENDER_ID       = :nsOffenderId
      }       
OIDONONA_GETMAXVAL {
SELECT max(type_seq)  type_seq  FROM offender_na_details      WHERE offender_id = :offenderId        AND ns_offender_id = :offenderId
}
OIDONONA_UPDATEOFFENDERNONASSOCIATIONFROMWEB {
UPDATE OFFENDER_NON_ASSOCIATIONS SET  RECIP_NS_REASON_CODE = :nsReasonCode,modify_datetime = CURRENT_TIMESTAMP,MODIFY_USER_ID=:modifyUserId   WHERE OFFENDER_ID = :offenderId AND NS_OFFENDER_ID= :nsOffenderId
}
OIDONONA_UPDATEOFFENDERNADETAILS {
UPDATE OFFENDER_NA_DETAILS set NS_REASON_CODE=:nsReasonCode, NS_TYPE=:nsType,modify_datetime = CURRENT_TIMESTAMP,MODIFY_USER_ID=:modifyUserId,
NS_EFFECTIVE_DATE=:nsEffectiveDate, NS_EXPIRY_DATE=:nsExpiryDate,AUTHORIZED_STAFF=:authorizedStaff,COMMENT_TEXT=:commentText  WHERE OFFENDER_ID= :offenderId
      AND NS_OFFENDER_ID = :nsOffenderId AND TYPE_SEQ=:typeSeq
}
OIDONONA_GET_ROOT_ID {
SELECT distinct ROOT_OFFENDER_ID FROM V_HEADER_BLOCK_FN(:USERID) v_header_block WHERE offender_id_display = :offenderIdDisplay
}
OIDONONA_GETLASTFIRSTNAME {
SELECT V_OFF_BKG.OFFENDER_ID
            ,V_OFF_BKG.OFFENDER_ID_DISPLAY
            ,V_OFF_BKG.LAST_NAME
            ,V_OFF_BKG.FIRST_NAME
            ,V_OFF_BKG.MIDDLE_NAME
            ,V_OFF_BKG.PRISON_LOCATION 
            ,V_OFF_BKG.BIRTH_DATE
            ,V_OFF_BKG.OFFENDER_BOOK_ID
            ,V_OFF_BKG.IN_OUT_STATUS
            ,V_OFF_BKG.BOOKING_STATUS
            ,V_OFF_BKG.AGY_LOC_ID
            ,V_OFF_BKG.ACTIVE_FLAG
            ,V_OFF_BKG.STATUS_DISPLAY
            ,V_OFF_BKG.AGENCY_IML_ID
            ,V_OFF_BKG.LOCATION_CODE
            ,V_OFF_BKG.ROOT_OFFENDER_ID
      FROM  V_HEADER_BLOCK_FN(:USERID) V_OFF_BKG
      WHERE  V_OFF_BKG.ROOT_OFFENDER_ID = :nsOffenderId
        AND (V_OFF_BKG.BOOKING_BEGIN_DATE = (SELECT MAX(VHB2.BOOKING_BEGIN_DATE) 
                                               FROM V_HEADER_BLOCK_FN(:USERID) VHB2 
                                              WHERE VHB2.ROOT_OFFENDER_ID =V_OFF_BKG.ROOT_OFFENDER_ID
                                                AND NOT EXISTS (SELECT NULL
                                                                  FROM V_HEADER_BLOCK_FN(:USERID) VHB3
                                                                 WHERE VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID
                                                                   AND VHB3.ACTIVE_FLAG='Y'))
        OR   V_OFF_BKG.ACTIVE_FLAG ='Y')
        AND   V_OFF_BKG.ROOT_OFFENDER_ID != :offenderId
        }
        
   OIDONONA_OFFNAD_UPDATE_OFFENDER_NA_DETAILS_DOUBLE {
UPDATE OFFENDER_NA_DETAILS set  NS_TYPE=:nsType,
NS_EFFECTIVE_DATE=:nsEffectiveDate, NS_EXPIRY_DATE=:nsExpiryDate,AUTHORIZED_STAFF=:authorizedStaff,COMMENT_TEXT=:commentText,modify_datetime = CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId  WHERE OFFENDER_ID= :nsOffenderId
      AND NS_OFFENDER_ID = :offenderId AND TYPE_SEQ=( SELECT MAX(TYPE_SEQ)
                               FROM OFFENDER_NA_DETAILS
                              WHERE OFFENDER_ID = :nsOffenderId
                                AND  NS_OFFENDER_ID = :offenderId)
}

OIDONONA_UPDATEOFFENDERNONASSOCIATIONFROMWEBDOUBLE{
UPDATE OFFENDER_NON_ASSOCIATIONS SET  RECIP_NS_REASON_CODE = :nsReasonCode ,modify_datetime = CURRENT_TIMESTAMP,MODIFY_USER_ID=:modifyUserId  WHERE OFFENDER_ID = :nsOffenderId AND NS_OFFENDER_ID= :offenderId

}

OIDONONA_UPDATEOFFENDERNONASSOCIATIONRECIPROCALREASON{
UPDATE OFFENDER_NA_DETAILS SET  NS_REASON_CODE = :nsReasonCode,modify_datetime = CURRENT_TIMESTAMP,MODIFY_USER_ID=:modifyUserId
   WHERE OFFENDER_ID = :nsOffenderId AND NS_OFFENDER_ID= :offenderId AND TYPE_SEQ=( SELECT MAX(TYPE_SEQ)
                               FROM OFFENDER_NA_DETAILS
                              WHERE OFFENDER_ID = :nsOffenderId
                               AND  NS_OFFENDER_ID = :offenderId)

}

OIDONONA_GET_ACTIVE_STAFF_MEMBERS{
SELECT  (SM.LAST_NAME || ', ' || SM.FIRST_NAME) DESCRIPTION ,SM.STAFF_ID::text CODE FROM STAFF_MEMBERS SM WHERE  SM.STATUS = 'ACTIVE' ORDER BY SM.STAFF_ID asc
}


