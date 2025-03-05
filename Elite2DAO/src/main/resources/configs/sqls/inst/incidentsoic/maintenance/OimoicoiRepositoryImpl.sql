
OIMOICOI_FIND_RGOICOFFENCECATEG {
 	SELECT REF_CODE1.CODE OIC_OFFENCE_CATEGORY /* CG$FK */  ,REF_CODE1.DESCRIPTION DSP_DESCRIPTION2 FROM REFERENCE_CODES REF_CODE1 WHERE DOMAIN = 'OIC_OFN_CAT' AND (::MODE = 'ENTER-QUERY' OR ACTIVE_FLAG = 'Y' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIMOICOI_FIND_RGOICOFFENCETYPE {
 	SELECT REF_CODE3.CODE OIC_OFFENCE_TYPE /* CG$FK */  ,REF_CODE3.DESCRIPTION DSP_DESCRIPTION FROM REFERENCE_CODES REF_CODE3 WHERE DOMAIN = 'OIC_OFN_TYPE' AND (::MODE='ENTER-QUERY' OR ACTIVE_FLAG = 'Y' )  ORDER BY LIST_SEQ , DESCRIPTION
}

OIMOICOI_FIND_RGOICOFFENCEINDICATORS {
 	SELECT REF_CODE.CODE INDICATOR_CODE /* CG$FK */  ,REF_CODE.DESCRIPTION DSP_DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE DOMAIN = 'OFFENCE_IND' AND (::MODE = 'ENTER-QUERY' OR ACTIVE_FLAG = 'Y' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIMOICOI_OICOFN_FIND_OIC_OFFENCES {
 	SELECT OIC_OFFENCE_CODE ,DESCRIPTION ,ACTIVE_FLAG ,LIST_SEQ ,UPDATE_ALLOWED_FLAG ,EXPIRY_DATE ,MODIFY_USER_ID ,OIC_OFFENCE_CATEGORY ,OIC_OFFENCE_TYPE ,MAX_PENALTY_MONTHS ,MAX_PENALTY_DAYS ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,OIC_OFFENCE_ID ,START_DATE ,END_DATE ,SEAL_FLAG   FROM OIC_OFFENCES order by LIST_SEQ
}
OIMOICOI_OICOFN_INSERT_OIC_OFFENCES {
insert into OIC_OFFENCES(OIC_OFFENCE_CODE , DESCRIPTION , ACTIVE_FLAG , LIST_SEQ , EXPIRY_DATE , OIC_OFFENCE_CATEGORY , OIC_OFFENCE_TYPE , MAX_PENALTY_MONTHS , MAX_PENALTY_DAYS , OIC_OFFENCE_ID , START_DATE , END_DATE , SEAL_FLAG , create_user_id, create_datetime, modify_user_id, modify_datetime ) values(:oicOffenceCode , :description , :activeFlag , :listSeq , :expiryDate , :oicOffenceCategory , :oicOffenceType , :maxPenaltyMonths , :maxPenaltyDays , :oicOffenceId , :startDate , :endDate , :sealFlag , :createUserId, current_timestamp, null , null )
}

OIMOICOI_OICOFN_UPDATE_OIC_OFFENCES {
update OIC_OFFENCES set OIC_OFFENCE_CODE = :oicOffenceCode , DESCRIPTION = :description , ACTIVE_FLAG = :activeFlag , LIST_SEQ = :listSeq , UPDATE_ALLOWED_FLAG = :updateAllowedFlag , EXPIRY_DATE = :expiryDate , OIC_OFFENCE_CATEGORY = :oicOffenceCategory , OIC_OFFENCE_TYPE = :oicOffenceType , MAX_PENALTY_MONTHS = :maxPenaltyMonths , MAX_PENALTY_DAYS = :maxPenaltyDays , OIC_OFFENCE_ID = :oicOffenceId , START_DATE = :startDate , END_DATE = :endDate , SEAL_FLAG = :sealFlag, modify_user_id =:modifyUserId , modify_datetime = current_timestamp where OIC_OFFENCE_ID = :oicOffenceId
}

OIMOICOI_OICOFFENCEINDICATORS_FIND_OIC_OFFENCE_INDICATORS {
 	SELECT OIC_OFFENCE_INDICATOR_ID ,INDICATOR_CODE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,OIC_OFFENCE_ID ,SEAL_FLAG   FROM OIC_OFFENCE_INDICATORS   where  OIC_OFFENCE_ID=:oicOffenceId
}
OIMOICOI_OICOFFENCEINDICATORS_INSERT_OIC_OFFENCE_INDICATORS {
	insert into OIC_OFFENCE_INDICATORS(OIC_OFFENCE_INDICATOR_ID , INDICATOR_CODE , CREATE_DATETIME , CREATE_USER_ID , OIC_OFFENCE_ID , SEAL_FLAG , modify_datetime ) values(:oicOffenceIndicatorId , :indicatorCode , current_timestamp , :createUserId , :oicOffenceId , :sealFlag , null )
	}

OIMOICOI_OICOFFENCEINDICATORS_UPDATE_OIC_OFFENCE_INDICATORS {
update OIC_OFFENCE_INDICATORS set INDICATOR_CODE = :indicatorCode, modify_user_id =:modifyUserId , modify_datetime = current_timestamp where OIC_OFFENCE_INDICATOR_ID = :oicOffenceIndicatorId
}

OIMOICOI_OICOFFENCEINDICATORS_DELETE_OIC_OFFENCE_INDICATORS { 
	DELETE FROM OIC_OFFENCE_INDICATORS where OIC_OFFENCE_INDICATOR_ID  = :oicOffenceIndicatorId
}


OIMOICOI_OIC_OFN_PREDELETEPRE-DELETE {
	DELETE FROM OIC_OFFENCE_INDICATORS O WHERE O.OIC_OFFENCE_ID = :OICOFFENCEID
}

OIMOICOI_OIC_OFFENCE_INDICATORS_PREINSERT {
	SELECT  NEXTVAL('OIC_OFFENCE_INDICATOR_ID') FROM DUAL
}

OIMOICOI_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OIMOICOI_OIC_OFFENCE_CHECK_OVERLAP_PERIOD {
select
	off.start_date P_START_DATE,
	off.end_date P_END_DATE
from
	oic_offences off
where
	off.oic_offence_id <> coalesce(:oicOffenceId, - 999)
	and
  off.oic_offence_code = :oicOffenceCode::text
	and
  off.active_flag = 'Y'
	and
  ( :startDate::date between off.start_date::date and coalesce(off.end_date::date, '9999/12/31'::date)
		or coalesce(:endDate::date, '9999/12/31'::date) between off.start_date and coalesce(off.end_date, '9999/12/31'::date)
			or off.start_date between :startDate::date and coalesce(:endDate::date, '9999/12/31'::date)
				or coalesce(off.end_date, '9999/12/31'::date) between :startDate::date and coalesce(to_date(:endDate, 'dd/mm/yyyy'), '9999/12/31'::date) )

          }
OIMOICOI_OIC_OFFENCE_CHECK_OFFENCE_CODE {          
   SELECT count(*) FROM oic_offences off where off.oic_offence_code = :oicOffenceCode AND off.active_flag = 'Y' AND off.end_date IS NULL
   }
 
 OIMOICOI_GET_NEXT_OFFENCE_ID{
    SELECT nextval('oic_offence_id') from dual 
 }