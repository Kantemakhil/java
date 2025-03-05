
OIUFSOFF_FIND_CGFKAGYLOCID {
 	SELECT AL.AGY_LOC_ID CODE , AL.DESCRIPTION DESCRIPTION   FROM AGENCY_LOCATIONS AL  WHERE ACTIVE_FLAG = 'Y'    AND AL.AGY_LOC_ID IN  (SELECT CAL.AGY_LOC_ID  FROM CASELOAD_AGENCY_LOCATIONS CAL , CASELOADS CAS WHERE CAS.CASELOAD_ID = CAL.CASELOAD_ID  AND CAL.CASELOAD_ID = :caseloadId AND CAL.AGY_LOC_ID NOT IN ('OUT' , 'TRN' )  AND (CAL.UPDATE_ALLOWED_FLAG = 'Y' OR '' <> 'NORMAL' ) )  ORDER BY AL.LIST_SEQ ASC
}

OIUFSOFF_FIND_CGFKHOUSINGLEVEL1 {
 /*	select
		TO_CHAR(internal_location_id) as code,
		DESCRIPTION
from
		agency_internal_locations ail
where
		ail.unit_type is not null
	and ail.unit_type::text <> ''::text
	and AGY_LOC_ID = :agyLocId
	and ACTIVE_FLAG = 'Y'
	and parent_internal_location_id is null*/
 /*	select
		TO_CHAR(internal_location_id) as code,
		DESCRIPTION
from
		agency_internal_locations ail
where
		ail.unit_type is not null
	and ail.unit_type::text <> ''::text
	and AGY_LOC_ID = :agyLocId
	and ACTIVE_FLAG = 'Y'
	and coalesce(:parent_internal_location_id ::text, '') = ''*/
--	
select
	TO_CHAR(LIV_UNIT.LIVING_UNIT_ID) CODE ,
	LIV_UNIT.DESCRIPTION DESCRIPTION
from
	LIVING_UNITS LIV_UNIT
where
	AGY_LOC_ID = :agyLocId
	and ACTIVE_FLAG = 'Y'
   and PARENT_LIVING_UNIT_ID is null


}

OIUFSOFF_FIND_CGFKHOUSINGLEVEL2 {
 	SELECT TO_CHAR(LIV_UNIT.LIVING_UNIT_ID) CODE ,  LIV_UNIT.DESCRIPTION  FROM
LIVING_UNITS LIV_UNIT  WHERE AGY_LOC_ID = :agyLocId    AND ACTIVE_FLAG = 'Y'    AND PARENT_LIVING_UNIT_ID = :parentLivingUnitId
}

OIUFSOFF_FIND_CGFKHOUSINGLEVEL3 {
 	SELECT TO_CHAR(LIV_UNIT.LIVING_UNIT_ID) CODE , LIV_UNIT.DESCRIPTION  
 FROM LIVING_UNITS LIV_UNIT  WHERE AGY_LOC_ID = :agyLocId   AND ACTIVE_FLAG = 'Y'    AND PARENT_LIVING_UNIT_ID = :parentLivingUnitId
}

OIUFSOFF_VOFFBKG_INSERT_OIUFSOFF.GET_GENERAL_OFFENDERS {
	INSERT INTO OIUFSOFF.GET_GENERAL_OFFENDERS() VALUES(:)
}

OIUFSOFF_VOFFBKG_UPDATE_OIUFSOFF_GET_GENERAL_OFFENDERS {
	UPDATE OIUFSOFF.GET_GENERAL_OFFENDERS set /* where */
}


OIUFSOFF_V_OFF_BKG_POSTQUERY {
--	SELECT decode(off_ta.account_closed_flag, 'N', 'Y', null, 'Y', 'N') FROM offender_trust_accounts off_ta WHERE off_ta.offender_id = :rootOffenderId
--               AND EXISTS (SELECT '1'  FROM caseloads csld WHERE csld.caseload_id = :caseloadId AND (   (    csld.commissary_flag = 'Y' AND csld.commissary_trust_caseload IS NOT NULL  AND off_ta.caseload_id = csld.commissary_trust_caseload
--               AND csld.caseload_id = :caseloadId) OR (    csld.commissary_flag = 'Y' AND csld.commissary_trust_caseload IS NULL AND off_ta.caseload_id IN (SELECT csld1.caseload_id  FROM caseloads csld1, caseload_agency_locations ca
--               WHERE csld1.trust_commissary_caseload = csld.caseload_id and ca.caseload_id = csld1.caseload_id and ca.agy_loc_id = :agyLocId) AND csld.caseload_id = :caseloadId) OR (    csld.trust_accounts_flag = 'Y' 
--               AND off_ta.caseload_id = csld.caseload_id AND csld.caseload_id = :caseloadId)))
select case when :account_closed_flag = 'N' then 'Y' when coalesce(:account_closed_flag ::text, '') = '' then 'Y' else 'N' end from offender_trust_accounts off_ta where off_ta.offender_id = :rootOffenderId and exists ( select '1' from caseloads csld where csld.caseload_id = :caseloadId and ( ( csld.commissary_flag = 'Y' and csld.commissary_trust_caseload is not null and off_ta.caseload_id = csld.commissary_trust_caseload and csld.caseload_id = :caseloadId) or ( csld.commissary_flag = 'Y' and csld.commissary_trust_caseload is null and off_ta.caseload_id in ( select csld1.caseload_id from caseloads csld1, caseload_agency_locations ca where csld1.trust_commissary_caseload = csld.caseload_id and ca.caseload_id = csld1.caseload_id and ca.agy_loc_id = :agyLocId) and csld.caseload_id = :caseloadId) or ( csld.trust_accounts_flag = 'Y' and off_ta.caseload_id = csld.caseload_id and csld.caseload_id = :caseloadId)))
}

OIUFSOFF_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}
