
OTUSUBAD_VTHA_FIND_V_TRUST_HEADER {
 	SELECT TRUST_CASELOAD_ID ,CASELOAD_ID ,COMMISSARY_TRUST_CASELOAD ,COMMUNITY_TRUST_CASELOAD ,OFFENDER_ID ,ALIAS_OFFENDER_ID ,OFFENDER_ID_DISPLAY ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,SUFFIX ,BIRTH_DATE ,OFFENDER_BOOK_ID ,BOOKING_NO ,BOOKING_TYPE ,BOOKING_BEGIN_DATE ,BOOKING_END_DATE ,AGY_LOC_ID ,LIVING_UNIT_ID ,DISCLOSURE_FLAG ,ACTIVE_FLAG ,BOOKING_STATUS ,LIVING_UNIT_DESCRIPTION ,IN_OUT_STATUS ,STATUS_DISPLAY ,ROOT_OFFENDER_ID ,INTAKE_AGY_LOC_ID ,COMMUNITY_ACTIVE_FLAG ,CREATE_INTAKE_AGY_LOC_ID ,COMMUNITY_STATUS ,STATUS_REASON ,HEADER_STATUS ,AGY_LOC_TYPE ,AGE ,GENDER ,OFFICER ,PRISON_LOCATION ,OFF_ALERTS ,STATUS_1 ,STATUS_2 ,STATUS_3 ,ETHNICITY ,ACCOUNT_CLOSED_FLAG ,MOVEMENT_REASON ,OFF_SUP_LEVEL ,INDIGENT_FLAG   FROM V_TRUST_HEADER  /* where  */
}
OTUSUBAD_OFFSAS_FIND_OFFENDER_SUB_AC_SHADOWS {
 	SELECT TRANSFERED_AMOUNT ,TRUST_ACCOUNT_CODE ,BALANCE ,OFFENDER_ID ,CASELOAD_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM OFFENDER_SUB_AC_SHADOWS  
WHERE  CASELOAD_ID = :CASELOADID AND OFFENDER_ID = :OFFID
}

OTUSUBAD_CGFKCHK_OFF_SAS_OFF_SAS_REF_C {
	SELECT REF_CODE.DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :TRUSTACCOUNTCODE AND     DOMAIN = 'SUB_AC_TYPE'
}

OTUSUBAD_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
 OTUSUBAD_GET_ROOT_OFFENDER_ID {
--  SELECT DISTINCT root_offender_id, last_name, first_name
--                          FROM v_trust_header_a va
--                         WHERE va.offender_book_id =(SELECT MAX (offender_book_id) FROM v_trust_header_a vtha
--                                       WHERE vtha.root_offender_id =  va.root_offender_id  AND NVL (vtha.booking_type, 'INST') = :caseloadType)
--                           AND NVL (va.booking_type, 'INST') = :caseloadType
--                           AND va.offender_id_display = :offenderIdDisplay

 select
	distinct root_offender_id,
	last_name,
	first_name
from
	v_trust_header_a va
where
	va.offender_book_id =(
	select
		MAX (offender_book_id)
	from
		v_trust_header_a vtha
	where
		vtha.root_offender_id = va.root_offender_id
		and coalesce  (vtha.booking_type,
		'INST') = :caseloadType)
	and coalesce  (va.booking_type,
	'INST') = :caseloadType
	and va.offender_id_display = :offenderIdDisplay ::text
}
