
OUMACASE_FIND_PAYROLLTRUSTRG {
 	SELECT ALL CASELOADS.CASELOAD_ID CODE,CASELOADS.DESCRIPTION FROM CASELOADS  WHERE CASELOAD_TYPE = 'INST' AND TRUST_ACCOUNTS_FLAG = 'Y'  AND ACTIVE_FLAG='Y'  ORDER BY LIST_SEQ , CASELOAD_ID , DESCRIPTION
} 

OUMACASE_FIND_COMMISSARYTRUSTRG {
 	SELECT ALL CASELOADS.CASELOAD_ID CODE, CASELOADS.DESCRIPTION FROM CASELOADS  WHERE CASELOAD_TYPE = 'INST'  AND TRUST_ACCOUNTS_FLAG = 'Y'   AND ACTIVE_FLAG='Y'  ORDER BY LIST_SEQ , CASELOAD_ID , DESCRIPTION
}

OUMACASE_FIND_TRUSTCOMMISSARYRG {
 	SELECT ALL CASELOADS.CASELOAD_ID CODE, CASELOADS.DESCRIPTION FROM CASELOADS  WHERE CASELOAD_TYPE = 'INST'  AND COMMISSARY_FLAG = 'Y'   AND ACTIVE_FLAG='Y'  ORDER BY LIST_SEQ , CASELOAD_ID , DESCRIPTION
}

OUMACASE_FIND_COMMUNITYTRUSTRG {
 	SELECT CASELOADS.CASELOAD_ID CODE,        CASELOADS.DESCRIPTION   FROM CASELOADS  WHERE CASELOAD_TYPE = 'COMM'    AND TRUST_ACCOUNTS_FLAG = 'Y'       AND ACTIVE_FLAG='Y'  ORDER BY LIST_SEQ , CASELOAD_ID , DESCRIPTION
}

OUMACASE_FIND_CASELOADTYPERG {
 	SELECT
  REF_CODE.CODE, REF_CODE.DESCRIPTION
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN='CLOAD_TYPE' AND
  (ACTIVE_FLAG='Y')
ORDER BY
  REF_CODE.LIST_SEQ, REF_CODE.CODE, REF_CODE.DESCRIPTION

}

OUMACASE_FIND_ALAGYLOCIDRG {
 	select
	AGY_LOC.AGY_LOC_ID,
	AGY_LOC.DESCRIPTION,
	AGY_LOC.LIST_SEQ
from
	AGENCY_LOCATIONS AGY_LOC
where
	(ACTIVE_FLAG = 'Y'
	)
order by
	AGY_LOC.LIST_SEQ ,
	AGY_LOC_ID ,
	AGY_LOC.DESCRIPTION
}

OUMACASE_CSLD_FIND_CASELOADS {
 	SELECT CASELOAD_ID ,DESCRIPTION ,CASELOAD_TYPE ,LIST_SEQ ,TRUST_ACCOUNTS_FLAG ,ACCESS_PROPERTY_FLAG ,TRUST_CASELOAD_ID ,PAYROLL_FLAG ,ACTIVE_FLAG ,DEACTIVATION_DATE ,COMMISSARY_FLAG ,PAYROLL_TRUST_CASELOAD ,COMMISSARY_TRUST_CASELOAD ,TRUST_COMMISSARY_CASELOAD ,COMMUNITY_TRUST_CASELOAD ,MDT_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,BILLING_FLAG ,SEAL_FLAG   FROM CASELOADS
}
OUMACASE_CSLD_INSERT_CASELOADS {
	INSERT INTO CASELOADS(CASELOAD_ID ,DESCRIPTION ,CASELOAD_TYPE ,LIST_SEQ ,TRUST_ACCOUNTS_FLAG ,ACCESS_PROPERTY_FLAG ,TRUST_CASELOAD_ID ,PAYROLL_FLAG ,ACTIVE_FLAG ,DEACTIVATION_DATE ,COMMISSARY_FLAG ,PAYROLL_TRUST_CASELOAD ,COMMISSARY_TRUST_CASELOAD ,TRUST_COMMISSARY_CASELOAD ,COMMUNITY_TRUST_CASELOAD ,MDT_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,BILLING_FLAG ,SEAL_FLAG ) VALUES(:caseloadId ,:description ,:caseloadType ,:listSeq ,:trustAccountsFlag ,:accessPropertyFlag ,:trustCaseloadId ,:payrollFlag ,:activeFlag ,:deactivationDate ,:commissaryFlag ,:payrollTrustCaseload ,:commissaryTrustCaseload ,:trustCommissaryCaseload ,:communityTrustCaseload ,:mdtFlag ,CURRENT_TIMESTAMP ,:createUserId ,NULL, NULL ,:billingFlag ,:sealFlag )
}

OUMACASE_CSLD_UPDATE_CASELOADS {
	UPDATE CASELOADS set DESCRIPTION  = :description ,CASELOAD_TYPE  = :caseloadType ,LIST_SEQ  = :listSeq ,TRUST_ACCOUNTS_FLAG  = :trustAccountsFlag ,ACCESS_PROPERTY_FLAG  = :accessPropertyFlag ,TRUST_CASELOAD_ID  = :trustCaseloadId ,PAYROLL_FLAG  = :payrollFlag ,ACTIVE_FLAG  = :activeFlag ,DEACTIVATION_DATE  = :deactivationDate ,COMMISSARY_FLAG  = :commissaryFlag ,PAYROLL_TRUST_CASELOAD  = :payrollTrustCaseload ,COMMISSARY_TRUST_CASELOAD  = :commissaryTrustCaseload ,TRUST_COMMISSARY_CASELOAD  = :trustCommissaryCaseload ,COMMUNITY_TRUST_CASELOAD  = :communityTrustCaseload ,MDT_FLAG  = :mdtFlag ,MODIFY_DATETIME  = CURRENT_TIMESTAMP ,MODIFY_USER_ID  = :modifyUserId ,BILLING_FLAG  = :billingFlag ,SEAL_FLAG  = :sealFlag where CASELOAD_ID  = :caseloadId
}

OUMACASE_CSLDAL_FIND_CASELOAD_AGENCY_LOCATIONS {
 	SELECT CASELOAD_ID ,AGY_LOC_ID ,UPDATE_ALLOWED_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM CASELOAD_AGENCY_LOCATIONS  
}
OUMACASE_CSLDAL_INSERT_CASELOAD_AGENCY_LOCATIONS {
	INSERT INTO CASELOAD_AGENCY_LOCATIONS(CASELOAD_ID ,AGY_LOC_ID ,UPDATE_ALLOWED_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG ) VALUES(:caseloadId ,:agyLocId ,:updateAllowedFlag ,CURRENT_TIMESTAMP ,:createUserId ,NULL ,NULL,:sealFlag )
} 

OUMACASE_CSLDAL_UPDATE_CASELOAD_AGENCY_LOCATIONS {
	UPDATE CASELOAD_AGENCY_LOCATIONS set AGY_LOC_ID  = :agyLocId ,UPDATE_ALLOWED_FLAG  = :updateAllowedFlag  ,MODIFY_DATETIME  = CURRENT_TIMESTAMP ,MODIFY_USER_ID  = :modifyUserId ,SEAL_FLAG  = :sealFlag  where CASELOAD_ID  = :caseloadId and AGY_LOC_ID  = :agyLocId  
} 

OUMACASE_CSLDAL_DELETE_CASELOAD_AGENCY_LOCATIONS { 
	DELETE FROM CASELOAD_AGENCY_LOCATIONS where CASELOAD_ID  = :caseloadId and AGY_LOC_ID = :agyLocId
} 


OUMACASE_CSLD_ONCHECKDELETEMASTER {
	SELECT 1 FROM CASELOAD_AGENCY_LOCATIONS C WHERE C.CASELOAD_ID = :CASELOADID
}

OUMACASE_CHECK_AGENCY {
	SELECT CAL.CASELOAD_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS C WHERE CAL.CASELOAD_ID = C.CASELOAD_ID AND CAL.AGY_LOC_ID = :agyLocId AND CAL.CASELOAD_ID= :caseloadId  AND C.BILLING_FLAG = 'Y' AND CAL.AGY_LOC_ID NOT IN ('TRN','OUT')
}

OUMACASE_CASELOAD_SEQ_EXISTSORNOT {
  SELECT count(*) FROM ALL_SEQUENCES WHERE UPPER(SEQUENCE_NAME) = UPPER(:SEQUENCE_NAME)
}

OUMACASE_DYNAMIC_CREATE_SEQ {
  create sequence %seqname% start with 1 increment by 1

}





