OCDEXPOW_FIND_CGFKEXTOTAGYLOCIDFROM {
 	SELECT AGY_LOC.AGY_LOC_ID CODE /* CG$FK */  ,AGY_LOC.DESCRIPTION FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGY_LOC_ID IN (SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.CASELOAD_ID = :CASELOADID ) ORDER BY AGY_LOC.DESCRIPTION
}

OCDEXPOW_FIND_CGFKEXTOTAGYLOCIDTO {
 	SELECT AGY_LOC1.AGY_LOC_ID CODE /* CG$FK */  ,AGY_LOC1.DESCRIPTION FROM AGENCY_LOCATIONS AGY_LOC1 WHERE AGENCY_LOCATION_TYPE = 'COMM' AND DEACTIVATION_DATE IS NULL AND AGY_LOC1.AGY_LOC_ID <> :AGYLOCIDFROM ORDER BY AGY_LOC1.DESCRIPTION
}

OCDEXPOW_FIND_RGSTAFFMEMBERS {
 	SELECT STAFF.LAST_NAME DESCRIPTION ,  STAFF.FIRST_NAME ,        STAFF.STAFF_ID   CODE   FROM STAFF_MEMBERS STAFF  WHERE STAFF.STAFF_ID IN        (SELECT SL.SAC_STAFF_ID           FROM STAFF_LOCATION_ROLES SL          WHERE SL.CAL_AGY_LOC_ID = :AGYLOCIDFROM            AND SL.TO_DATE IS NULL )  ORDER BY STAFF.LAST_NAME ASC
}


OCDEXPOW_EXTOT_FIND_EXT_OWNERSHIP_TRANSFER {
 	SELECT   FROM EXT_OWNERSHIP_TRANSFER  /* where  */
}
OCDEXPOW_EXTOT_INSERT_EXT_OWNERSHIP_TRANSFER {
	INSERT INTO EXT_OWNERSHIP_TRANSFER(
OFFENDER_BOOK_ID, EXT_TRANSFER_ID, AGY_LOC_ID_FROM, AGY_LOC_ID_TO, TRANSFER_DATE, ASS_STAFF_ID, create_user_id, create_datetime, modify_datetime)
VALUES(:offenderBookId,:extTransferId,:agyLocIdFrom,:agyIocIdTo,:transferDate,:assStaffId,:createUserId, current_timestamp, null)
}

OCDEXPOW_VOFFENDERASSIGNED_FIND_V_OFFENDER_ASSIGNED {
 	SELECT
    offender_book_id,
    agy_loc_id,
    sac_staff_id,
    offender_last_name,
    offender_first_name,
    offender_id_display,
    staff_last_name,
    staff_first_name
FROM
    v_offender_assigned
     WHERE v_offender_assigned.agy_loc_id = :agy_loc_id_from  
     AND v_offender_assigned.sac_staff_id = :ass_staff_id   
     AND offender_book_id NOT IN  (SELECT eot.offender_book_id  
     FROM ext_ownership_transfer eot 
WHERE
    eot.transfer_flag = 'N')
}
OCDEXPOW_VOFFENDERASSIGNED_UPDATE_V_OFFENDER_ASSIGNED {
insert into EXT_OWNERSHIP_TRANSFER( OFFENDER_BOOK_ID, EXT_TRANSFER_ID, AGY_LOC_ID_FROM, AGY_LOC_ID_TO, TRANSFER_DATE, ASS_STAFF_ID, create_user_id, create_datetime, modify_datetime) 
 values(:offenderBookId, :extTransferId, :agyLocIdFrom, :agyIocIdTo, :transferDate, :assStaffId, :createUserId, current_timestamp, null)
}


OCDEXPOW_CGFKCHK_EXT_OT_EXT_OT_AGY_LOC_ {
	SELECT AGY_LOC1.DESCRIPTION FROM   AGENCY_LOCATIONS AGY_LOC1 WHERE  AGY_LOC1.AGY_LOC_ID = :AGYLOCIDTO
}

OCDEXPOW_CGFKCHK_EXT_OT_EXT_OT_AGY_2_ {

}

OCDEXPOW_GET_PROFILE_VALUE_2_ {
	select
	PROFILE_VALUE_2
from
	SYSTEM_PROFILES
where
	PROFILE_TYPE = :P_PROFILE_TYPE
	and PROFILE_CODE = :P_PROFILE_CODE
}

OCDEXPOW_GET_EXT_TRANSFER_ID {
 select
	coalesce( MAX(EXT_TRANSFER_ID) + 1,
	1 )
from
	EXT_OWNERSHIP_TRANSFER
where
	OFFENDER_BOOK_ID = :OFFENDERBOOKID
  }
       
       
    

    OCDEXPOW_FIND_OFFDED_EXECUTEQUERY{
      	SELECT * FROM OFF_FEE_ACCOUNT_PROFILE where OFFENDER_BOOK_ID = :offenderBookId
      }

      OCDEXPOW_OFFDED_UPDATE{
      	UPDATE OFF_FEE_ACCOUNT_PROFILE SET CASELOAD_ID =:caseloadId , FEE_ACT_STATUS = :feeActStatus , STATUS_EFFECTIVE_DATE = systimestamp, EFFECTIVE_DATE = :effectiveDate,modify_user_id = :modifyUserId, modify_datetime = current_timestamp WHERE OFFENDER_FEE_ID =:offenderFeeId
      }

OCDEXPOW_GET_FEE_ACCOUNT_SUP_DATA {
SELECT * FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID

}

OCDEXPOW_GET_SUPERVISION_START_DATE {

SELECT START_DATE FROM OFF_SUPERVISION_STS_HTY  WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID AND ERROR_FLAG = 'N' AND END_DATE IS NULL
AND BILLABLE_FLAG= 'Y'

}

OCDEXPOW_UPDATE_FEE_ACCOUNTS {
UPDATE OFF_FEE_ACCOUNT_PROFILE SET FEE_ACT_STATUS = :feeActStatus , STATUS_EFFECTIVE_DATE = systimestamp(),modify_user_id = :modifyUserId, modify_datetime = current_timestamp WHERE OFFENDER_FEE_ID =:offenderFeeId
}

ODEXPOW_FIND_CASELOAD_DEDUCTION_PROFILES{
SELECT   caseload_id, deduction_type,FO_AL_ALL_OFFENDER_FLAG, BACK_BILL, non_billable_status,max_total_amount,FREQUENCY_TYPE,FREQUENCY_CODE,DAY_OF_MONTH 
   FROM caseload_deduction_profiles where  deduction_type IN (select deduction_type from deduction_types where DEDUCTION_CATEGORY = 'FEE') and caseload_id = :caseloadId
      and fo_al_all_offender_flag ='Y'
}

OCDEXPOW_UPDATE_FEE_ACNT_CASELOAD{
UPDATE off_fee_account_profile
SET
    caseload_id = :caseloadId,FEE_ACT_STATUS = :feeActStatus , STATUS_EFFECTIVE_DATE = systimestamp(),modify_user_id = :modifyUserId, modify_datetime = current_timestamp
WHERE
    offender_fee_id = :offenderFeeId
}
OCDEXPOW_GET_CASLOAD_TYPE {
select caseload_id from CASELOAD_AGENCY_LOCATIONS where agy_loc_id= :agyLocId
}