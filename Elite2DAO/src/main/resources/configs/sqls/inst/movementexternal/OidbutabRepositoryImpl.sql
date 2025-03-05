
OIDBUTAB_FIND_RGINSTITUTION {
   SELECT AGY_LOC_ID code, DESCRIPTION, ACTIVE_FLAG FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGENCY_LOCATION_TYPE = 'INST' AND AGY_LOC.AGY_LOC_ID IN  (SELECT AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID= :CASELOADID AND UPDATE_ALLOWED_FLAG = 'Y' )  AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) AND AGY_LOC.DEACTIVATION_DATE IS NULL ORDER BY AGY_LOC.LIST_SEQ , AGY_LOC.AGY_LOC_ID
}

OIDBUTAB_FIND_RGACTIVEAGENCY {
    	SELECT AGY_LOC_ID code, DESCRIPTION, ACTIVE_FLAG FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.DEACTIVATION_DATE IS NULL AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' , :AGYLOCID )  ORDER BY LIST_SEQ , AGY_LOC_ID
}

OIDBUTAB_FIND_RGACTIVEAGENCY_FORGRID {
 	SELECT AGY_LOC_ID DESCRIPTION, DESCRIPTION CODE FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.DEACTIVATION_DATE IS NULL AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' , :AGYLOCID )  ORDER BY LIST_SEQ , AGY_LOC_ID
}

OIDBUTAB_FIND_RGLULEVEL1 {
   SELECT  LIV_UNIT.LIVING_UNIT_CODE    DESCRIPTION         ,LIV_UNIT.LIST_SEQ         ,to_char(LIV_UNIT.LIVING_UNIT_ID) CODE, LIV_UNIT.ACTIVE_FLAG FROM   LIVING_UNITS LIV_UNIT WHERE   LIV_UNIT.AGY_LOC_ID= :AGYLOCID AND LIV_UNIT.ACTIVE_FLAG='Y' AND LIV_UNIT.PARENT_LIVING_UNIT_ID IS NULL ORDER BY LIV_UNIT.LIST_SEQ , LIV_UNIT.DESCRIPTION
}

OIDBUTAB_FIND_RGLULEVEL2 {
   SELECT  LIV_UNIT.LIVING_UNIT_CODE   DESCRIPTION           ,LIV_UNIT.LIST_SEQ        ,to_char(LIV_UNIT.LIVING_UNIT_ID) CODE, LIV_UNIT.ACTIVE_FLAG FROM   LIVING_UNITS LIV_UNIT WHERE   LIV_UNIT.AGY_LOC_ID= :AGYLOCID AND LIV_UNIT.ACTIVE_FLAG='Y' AND LIV_UNIT.PARENT_LIVING_UNIT_ID = :LIVINGUNITID ORDER BY LIV_UNIT.LIST_SEQ , LIV_UNIT.DESCRIPTION
}

OIDBUTAB_FIND_RGLULEVEL3 {
 	SELECT  LIV_UNIT.LIVING_UNIT_CODE   DESCRIPTION         ,LIV_UNIT.LIST_SEQ     ,to_char(LIV_UNIT.LIVING_UNIT_ID) CODE, LIV_UNIT.ACTIVE_FLAG FROM   LIVING_UNITS LIV_UNIT WHERE   LIV_UNIT.AGY_LOC_ID=:AGYLOCID AND LIV_UNIT.ACTIVE_FLAG='Y' AND LIV_UNIT.PARENT_LIVING_UNIT_ID = :LIVINGUNITID ORDER BY LIV_UNIT.LIST_SEQ , LIV_UNIT.DESCRIPTION
}

OIDBUTAB_FIND_RGCITY {
 	SELECT CODE DESCRIPTION, DESCRIPTION CODE FROM REFERENCE_CODES WHERE DOMAIN = 'CITY' AND ACTIVE_FLAG = 'Y' ORDER BY LIST_SEQ , DESCRIPTION
}

OIDBUTAB_FIND_RGREASON {
  	SELECT MOVEMENT_REASON_CODE code, DESCRIPTION, ACTIVE_FLAG  FROM MOVEMENT_REASONS WHERE MOVEMENT_TYPE = 'TAP' AND ACTIVE_FLAG = 'Y' ORDER BY LIST_SEQ , MOVEMENT_REASON_CODE
}

OIDBUTAB_VHB_FIND_V_HEADER_BLOCK {
 	SELECT OFFENDER_ID ,ALIAS_OFFENDER_ID ,OFFENDER_ID_DISPLAY ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,SUFFIX ,BIRTH_DATE ,OFFENDER_BOOK_ID ,BOOKING_NO ,BOOKING_BEGIN_DATE ,BOOKING_END_DATE ,AGY_LOC_ID ,AGENCY_IML_ID ,LIVING_UNIT_ID ,DISCLOSURE_FLAG ,BOOKING_STATUS ,LIVING_UNIT_DESCRIPTION ,IN_OUT_STATUS ,STATUS_DISPLAY ,ROOT_OFFENDER_ID ,ASSIGNED_STAFF_ID ,AGY_LOC_TYPE ,CREATE_AGY_LOC_ID ,BOOKING_TYPE ,BOOKING_CREATED_DATE ,LOCATION_CODE ,LIV_UNIT_DESC ,INTAKE_AGY_LOC_ID ,COMMUNITY_ACTIVE_FLAG ,CREATE_INTAKE_AGY_LOC_ID ,COMMUNITY_STATUS ,STATUS_REASON ,HEADER_STATUS ,AGE ,GENDER ,OFFICER ,PRISON_LOCATION ,OFF_ALERTS ,STATUS_1 ,STATUS_2 ,STATUS_3 ,ETHNICITY ,MOVEMENT_REASON ,OFF_SUP_LEVEL   FROM V_HEADER_BLOCK_FN(:USERID)  v_header_block 
}
OIDBUTAB_VHB_UPDATE_V_HEADER_BLOCK {
	UPDATE V_HEADER_BLOCK set OFFENDER_ID  = :offenderId ,ALIAS_OFFENDER_ID  = :aliasOffenderId ,OFFENDER_ID_DISPLAY  = :offenderIdDisplay ,LAST_NAME  = :lastName ,FIRST_NAME  = :firstName ,MIDDLE_NAME  = :middleName ,SUFFIX  = :suffix ,BIRTH_DATE  = :birthDate ,OFFENDER_BOOK_ID  = :offenderBookId ,BOOKING_NO  = :bookingNo ,BOOKING_BEGIN_DATE  = :bookingBeginDate ,BOOKING_END_DATE  = :bookingEndDate ,AGY_LOC_ID  = :agyLocId ,AGENCY_IML_ID  = :agencyImlId ,LIVING_UNIT_ID  = :livingUnitId ,DISCLOSURE_FLAG  = :disclosureFlag ,ACTIVE_FLAG  = :activeFlag ,BOOKING_STATUS  = :bookingStatus ,LIVING_UNIT_DESCRIPTION  = :livingUnitDescription ,IN_OUT_STATUS  = :inOutStatus ,STATUS_DISPLAY  = :statusDisplay ,ROOT_OFFENDER_ID  = :rootOffenderId ,ASSIGNED_STAFF_ID  = :assignedStaffId ,AGY_LOC_TYPE  = :agyLocType ,CREATE_AGY_LOC_ID  = :createAgyLocId ,BOOKING_TYPE  = :bookingType ,BOOKING_CREATED_DATE  = :bookingCreatedDate ,LOCATION_CODE  = :locationCode ,LIV_UNIT_DESC  = :livUnitDesc ,INTAKE_AGY_LOC_ID  = :intakeAgyLocId ,COMMUNITY_ACTIVE_FLAG  = :communityActiveFlag ,CREATE_INTAKE_AGY_LOC_ID  = :createIntakeAgyLocId ,COMMUNITY_STATUS  = :communityStatus ,STATUS_REASON  = :statusReason ,HEADER_STATUS  = :headerStatus ,AGE  = :age ,GENDER  = :gender ,OFFICER  = :officer ,PRISON_LOCATION  = :prisonLocation ,OFF_ALERTS  = :offAlerts ,STATUS_1  = :status1 ,STATUS_2  = :status2 ,STATUS_3  = :status3 ,ETHNICITY  = :ethnicity ,MOVEMENT_REASON  = :movementReason ,OFF_SUP_LEVEL  = :offSupLevel 
}


OIDBUTAB_QRY_BLK_WHENNEWBLOCKINSTANCEWHEN-NEW-BLOCK-INSTANCE {
	SELECT AGY_LOC_ID, DESCRIPTION INTO :QRY_BLK.AGY_LOC_ID, :QRY_BLK.DSP_INSTITUTION FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGY_LOC_ID IN (SELECT AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = :GLOBAL.CASELOAD_ID AND UPDATE_ALLOWED_FLAG = 'Y') AND AGENCY_LOCATION_TYPE = 'INST' AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT', 'TRN') AND AGY_LOC.DEACTIVATION_DATE IS NULL
}

OIDBUTAB_VHB_POSTQUERY_ {
select coalesce (TO_AGY_LOC_ID, TO_CITY), COMMENT_TEXT from OFFENDER_EXTERNAL_MOVEMENTS where OFFENDER_BOOK_ID =:P_OFFENDER_BOOK_ID and MOVEMENT_SEQ = ( select MAX(MOVEMENT_SEQ) from OFFENDER_EXTERNAL_MOVEMENTS where OFFENDER_BOOK_ID =:P_OFFENDER_BOOK_ID) and DIRECTION_CODE = 'OUT' and MOVEMENT_TYPE = 'TAP'
}

OIDBUTAB_OIDBUTAB_KEYCOMMIT_ {
select
	MOVEMENT_SEQ,
	MOVEMENT_TYPE,
	MOVEMENT_REASON_CODE,
	DIRECTION_CODE,
	TO_AGY_LOC_ID,
	TO_CITY
from
	OFFENDER_EXTERNAL_MOVEMENTS
where
	OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID
order by
	MOVEMENT_SEQ desc

}

OIDBUTAB_CREATE_FORM_GLOBALSCREATE_FORM_GLOBALS {
	SELECT DESCRIPTION  FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OIDBUTAB_SET_OFF_ID_DISPLAY_PROMPT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'OFF_ID_CODE'
}

OIDBUTAB_HAS_LATER_MOVEMENT_WHEN_CHECK {
	SELECT TRUNC(MOVEMENT_DATE), NVL(TO_CHAR(MOVEMENT_TIME, 'HH24MI'), '0000') FROM OFFENDER_EXTERNAL_MOVEMENTS OEM WHERE OEM.OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID ORDER BY MOVEMENT_SEQ DESC
}

OIDBUTAB_OFFENDER_BOOKINGS {
UPDATE offender_bookings  SET in_out_status = :inOutStatus,
                            agency_iml_id = NULL,
                            modify_datetime = CURRENT_TIMESTAMP,
                            modify_user_id = :modifyUserId
                      WHERE offender_book_id = :offenderBookId
}

UPDATE_OFFENDER_EXTERNAL_MOVEMENTS {
UPDATE offender_external_movements
                        SET active_flag = 'N',
                         modify_datetime = CURRENT_TIMESTAMP,
                         modify_user_id = :modifyUserId
                         WHERE offender_book_id = :offenderBookId
                         AND active_flag = 'Y'
}

INSERT_OFFENDER_EXTERNAL_MOVEMENTS{
INSERT INTO OFFENDER_EXTERNAL_MOVEMENTS
                        (OFFENDER_BOOK_ID,
                         MOVEMENT_SEQ,
                         MOVEMENT_DATE,
                         MOVEMENT_TIME,
                         MOVEMENT_TYPE,
                         MOVEMENT_REASON_CODE,
                         DIRECTION_CODE,
                         FROM_AGY_LOC_ID,
                         TO_AGY_LOC_ID,
                         FROM_CITY,
                         TO_CITY,
                         ACTIVE_FLAG,
                         COMMENT_TEXT,
                         CREATE_USER_ID,
                         CREATE_DATETIME,
                         modify_datetime
                         )
                     VALUES
                        (:offenderBookId,
                         :movementSeq,
                         :movementDate,
                         :movementTime,
                         :movementType,
                         :movementReasonCode,
                         :directionCode,
                         :fromAgyLocId,
                         :toAgyLocId,
                         :fromCity,
                         :toCity,
                         :activeFlag,
                         :commentText,
                         :createUserId,
                         CURRENT_TIMESTAMP,
                         null)
}

OIDBUTAB_GET_MOVEMENT_SEQ {
select * from ( select coalesce (movement_seq, 0) + 1 movement_seq, movement_type, movement_reason_code, direction_code, to_agy_loc_id, to_city 
from offender_external_movements where offender_book_id = :offenderBookId order by movement_seq desc ) ali LIMIT 1 
}

OCDBUTAB_WHENVALIDATEITEM{
select
	count(*)

from
	V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK
where
	v_header_block.agy_loc_id = :agyLocId
	and v_header_block.active_flag = 'Y'
	and v_header_block.in_out_Status = 'IN'
	and v_header_block.offender_id_display = :offenderIdDisplay
	and v_header_block.living_unit_id in (with recursive cte as (
	select
		lu.living_unit_id
	from
		living_units lu
	where
		lu.living_unit_id = :livingUnitDesc
union all
	select
		lu.living_unit_id
	from
		living_units lu
	join cte c on
		(c.living_unit_id = lu.parent_living_unit_id) )
	select
		*
	from
		cte )
}

OCDBUTAB_WHENVALIDATEITEM_ONE{
select
	count(*)

from
	V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK
where
	v_header_block.agy_loc_id = :agyLocId
	and v_header_block.active_flag = 'Y'
	and v_header_block.in_out_Status = 'IN'
}
OCDBUTAB_WHENVALIDATEITEM_TWO{
union all
	select
		lu.living_unit_id
	from
		living_units lu
	join cte c on
		(c.living_unit_id = lu.parent_living_unit_id) )
	select
		*
	from
		cte )
}

GET_LASTMOVEANDLOC {
select * from (SELECT MOVEMENT_SEQ,COMMENT_TEXT, FROM_AGY_LOC_ID,TO_CITY, TO_AGY_LOC_ID  FROM offender_external_movements WHERE 
DIRECTION_CODE = 'OUT' and FROM_AGY_LOC_ID = :fromAgyLocId and OFFENDER_BOOK_ID =:offenderBookId order by MOVEMENT_SEQ desc) ali LIMIT 1
}

OIDBUTAB_HAS_LATER_MOVEMENT {
select * from ( select MOVEMENT_DATE, MOVEMENT_TIME from offender_external_movements where OFFENDER_BOOK_ID =:offenderBookId order by MOVEMENT_SEQ desc) ali LIMIT 1
}
GET_OLD_OFFENDER_BOOKINGS_DATA{
select * from offender_bookings where OFFENDER_BOOK_ID=:offender_book_id
}
OIDBUTAB_OLD_DATA_EXTERNAL_MOVEMENTS{
SELECT * FROM OFFENDER_EXTERNAL_MOVEMENTS WHERE OFFENDER_BOOK_ID =:offnderBookId 
}