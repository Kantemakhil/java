 
OCDCLOSE_FIND_CGFKOBEDSPDESCRIPTION {
 	SELECT REF_CODE.DESCRIPTION DSP_DESCRIPTION /* CG$FK */  ,REF_CODE.LIST_SEQ DSP_LIST_SEQ2  ,REF_CODE.CODE REASON_CODE FROM REFERENCE_CODES REF_CODE WHERE (DOMAIN = 'CLO_CON_RSN' AND (ACTIVE_FLAG = 'Y' OR '' <> 'NORMAL' ) )
}

OCDCLOSE_FIND_AGENCYLOCATIONRECORDGROUP {
 	SELECT
  AL.DESCRIPTION DESCRIPTION, AL.LIST_SEQ DSP_LIST_SEQ, AL.AGY_LOC_ID CODE
FROM
  AGENCY_LOCATIONS AL, OFFENDER_BOOKING_AGY_LOCS OBAL
WHERE
  AL.AGY_LOC_ID = OBAL.AGY_LOC_ID AND
  OBAL.REMOVED_REASON_CODE IS NULL AND
  OBAL.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND
  OBAL.AGY_LOC_ID IN
    (SELECT CA.AGY_LOC_ID
     FROM CASELOAD_AGENCY_LOCATIONS CA
     WHERE
       CA.CASELOAD_ID = :CASELOADID ) AND
  AL.AGY_LOC_ID NOT IN ('TRN' , 'OUT' )
ORDER BY
  AL.LIST_SEQ ASC, AL.DESCRIPTION

}

OCDCLOSE_OBE_FIND_OFFENDER_BOOKING_EVENTS {
 	SELECT *  FROM OFFENDER_BOOKING_EVENTS  /* where  */
}
OCDCLOSE_OBE_INSERT_OFFENDER_BOOKING_EVENTS {
	INSERT INTO OFFENDER_BOOKING_EVENTS(OFFENDER_BOOK_ID, EVENT_SEQ, BOOKING_EVENT_CODE, REASON_CODE, FROM_AGY_LOC_ID, TO_AGY_LOC_ID, COMMENT_TEXT, EVENT_DATE, EVENT_TIME, EVENT_USER, CREATION_USER, CREATION_DATE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) 
    VALUES(:offenderBookId, :eventSeq, :bookingEventCode, :reasonCode, :fromAgyLocId, :toAgyLocId, :commentText, :eventDate, :eventTime, :eventUser, :createUserId, :creationDate, CURRENT_TIMESTAMP, :createUserId, null, :sealFlag)
}


OCDCLOSE_OBE_PREINSERT {
	SELECT coalesce(MAX(EVENT_SEQ),0) + 1
FROM OFFENDER_BOOKING_EVENTS
WHERE
  OFFENDER_BOOK_ID = :OFFENDERBOOKID;
}

OCDCLOSE_CGFKLKP_OBE_OBE_TO_AGY_LOC_F1_ {
	SELECT 
  AGY_LOC.AGY_LOC_ID, AGY_LOC.LIST_SEQ
FROM AGENCY_LOCATIONS AGY_LOC
WHERE
  (AGY_LOC.DESCRIPTION = :DSPDESCRIPTION2 OR (AGY_LOC.DESCRIPTION IS NULL AND :DSPDESCRIPTION2 IS NULL )) AND
  AGY_LOC.AGY_LOC_ID IN 
    (SELECT CA.AGY_LOC_ID
     FROM CASELOAD_AGENCY_LOCATIONS CA
     WHERE
       CA.CASELOAD_ID = :CASELOADID) AND
  AGY_LOC.AGY_LOC_ID NOT IN ('TRN', 'OUT') 

}

OCDCLOSE_CGFKCHK_OBE_OBE_REF_CODE_F2_ {
	SELECT
  REF_CODE.LIST_SEQ, REF_CODE.DESCRIPTION
FROM REFERENCE_CODES REF_CODE
WHERE
  REF_CODE.CODE = :REASONCODE AND
  (DOMAIN = 'CLO_CON_RSN' AND (ACTIVE_FLAG = 'Y'))

}

OCDCLOSE_CGFKLKP_OBE_OBE_REF_CODE_F2_ {
	SELECT 
  REF_CODE.CODE, REF_CODE.LIST_SEQ
FROM REFERENCE_CODES REF_CODE
WHERE
  (REF_CODE.DESCRIPTION = :DSPDESCRIPTION OR (REF_CODE.DESCRIPTION IS NULL AND :DSPDESCRIPTION IS NULL )) AND
  (DOMAIN = 'CLO_CON_RSN' AND (ACTIVE_FLAG = 'Y')) 

}
OCDCLOSE_CHECK_INSTITUTION_OFFENDER_BOOKINGS {
 	UPDATE offender_bookings SET community_active_flag = 'N' ,intake_agy_loc_id ='CLOSE' ,activity_date = :eventDate, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP
     WHERE offender_book_id = :offenderBookId
  }
OCDCLOSE_CHECK_INSTITUTION_OFFENDER_BOOKING_AGY_LOCS {
	UPDATE offender_booking_agy_locs SET removed_date = :eventDate, removed_reason_code = :reasonCode,MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP
    WHERE offender_book_id = :offenderBookId AND removed_reason_code IS NULL
     }
     OCDCLOSE_CHECK_MULTY_CASELOAD_OFFENDER_BOOKINGS {
 	 UPDATE offender_bookings
      SET community_active_flag = 'N'
         ,intake_agy_loc_id ='CLOSE'
         ,booking_status = 'C'
         ,in_out_status = 'OUT'
         ,activity_date = :eventDate
         ,MODIFY_USER_ID = :modifyUserId 
         ,MODIFY_DATETIME = CURRENT_TIMESTAMP
    WHERE offender_book_id = :offenderBookId
  }
OCDCLOSE_CHECK_MULTY_CASELOAD_OFFENDER_BOOKING_AGY_LOCS {
	 UPDATE offender_booking_agy_locs
      SET removed_date = :eventDate,
          removed_reason_code = :reasonCode,
          MODIFY_USER_ID = :modifyUserId ,
          MODIFY_DATETIME = CURRENT_TIMESTAMP
    WHERE offender_book_id = :offenderBookId
      AND removed_reason_code IS NULL
     } 
 OCDCLOSE_GET_BOOKING_BEGIN_DATE {
   SELECT date_trunc('day', EVENT_DATE) EVENT_DATE, EVENT_TIME FROM OFFENDER_BOOKING_EVENTS
       WHERE OFFENDER_BOOK_ID = :offenderBookId
         AND BOOKING_EVENT_CODE = 'INTAKE'
         AND EVENT_SEQ = (SELECT MAX(EVENT_SEQ) FROM OFFENDER_BOOKING_EVENTS WHERE OFFENDER_BOOK_ID = :offenderBookId AND BOOKING_EVENT_CODE = 'INTAKE');
}

OCDCLOSE_CHECK_EXTERNAL_TRANSFER {
 SELECT 1
        FROM ext_ownership_transfer
       WHERE offender_book_id = :offenderBookId
         AND agy_loc_id_from = :toAgyLocId
         AND transfer_flag = 'N'
 }
 OCDCLOSE_UPDATE_OFFENDER_BOOKINGS {
   UPDATE offender_bookings
       SET booking_end_date = :eventDate
          ,comm_status      = NULL,
          MODIFY_USER_ID = :modifyUserId ,
          MODIFY_DATETIME = CURRENT_TIMESTAMP
          
     WHERE offender_book_id = :offenderBookId
 }

 OCDCLOSE_UPDATE_OFFENDER_COMM {
 UPDATE offender_community_files
      SET close_file_no = :vno,
                MODIFY_USER_ID = :modifyUserId ,
          MODIFY_DATETIME = CURRENT_TIMESTAMP
    WHERE offender_id   = :offenderId
 } 
 check_caseload_access_agy_loc_cur {
 SELECT AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = :caseloadId
 }
  check_caseload_access_agy_loc_two_cur {
  SELECT AGY_LOC_ID FROM OFFENDER_BOOKING_AGY_LOCS
    WHERE OFFENDER_BOOK_ID = :offenderBookId AND coalesce(REMOVED_DATE::text, '') = '';
 }
 CHECK_CASELOAD_ACCESS_COUNT_ONE {
 SELECT COUNT(AGY_LOC_ID) FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = :caseloadId
 }
 CHECK_CASELOAD_ACCESS_COUNT_TWO {
  SELECT COUNT(AGY_LOC_ID) FROM OFFENDER_BOOKING_AGY_LOCS
    WHERE OFFENDER_BOOK_ID = :offenderBookId AND coalesce(REMOVED_DATE::text, '') = '';

 }

CASEPLAN_AND_PAPERFILE_VS_PAPER_CUR {
   SELECT OFFENDER_FILE_SEQ, HOLDING_STAFF_ID, NON_OFFICER_STATUS
       FROM OFFENDER_COMMUNITY_FILES WHERE OFFENDER_ID = :offenderId AND HOLDING_AGY_LOC_ID = :toAgyLocId
      ORDER BY OFFENDER_ID, OFFENDER_FILE_SEQ
 }
 CASEPLAN_AND_PAPERFILE_CASE_PLAN_CUR {
    SELECT OB.ACTIVE_FLAG INST_ACTIVE_FLAG,
                CP.OFFENDER_BOOK_ID, CP.CASE_PLAN_ID, CP.AGY_LOC_ID, CP.CASE_PLAN_STATUS, CP.CREATION_DATE, CP.CREATION_USER, CP.SUPERVISION_LEVEL,
            CP.START_DATE, CP.CASELOAD_TYPE, CP.VERIFIED_FLAG, CP.INST_SAC_STAFF_ID, CP.INST_CAL_AGY_LOC_ID, CP.INST_FROM_DATE, CP.INST_POSITION, CP.INST_ROLE
          FROM OFFENDER_BOOKINGS OB, CASE_PLANS CP
         WHERE OB.OFFENDER_BOOK_ID = :offenderBookId AND OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID
       AND coalesce(CP.END_DATE::text, '') = ''  --ACTIVE RECORD
       AND (CP.SAC_STAFF_ID IS NOT NULL AND CP.SAC_STAFF_ID::text <> '')
 }
OCDCLOSE_CASEPLAN_INSERT {
 INSERT INTO case_plans (offender_book_id, case_plan_id, agy_loc_id, case_plan_status, creation_date, creation_user, supervision_level, 
                         start_date, caseload_type,verified_flag,inst_sac_staff_id, inst_cal_agy_loc_id, inst_from_date, inst_position,inst_role,create_user_id,create_datetime,modify_datetime)
VALUES (:offenderBookId,:casePlanId,:agyLocId,:casePlanStatus,sysdate, 
         :creationUser,:supervisionLevel, sysdate,null,'Y',:instSacStaffId, 
         :instCalAgyLocId,:instFromDate,:instPosition,:instRole,:createUserId,current_timestamp,null)
    } 
OCDCLOSE_CASEPLAN_UPDATE {
   UPDATE case_plans SET case_plan_status = 'CLOSED' ,end_date = current_timestamp, MODIFY_USER_ID = :modifyUserId ,
          MODIFY_DATETIME = CURRENT_TIMESTAMP
       WHERE offender_book_id = :offenderBookId AND end_date IS NULL AND case_plan_id = :casePlanId
   } 
 OCDCLOSE_V_PROFILE_VALUE { 
 SELECT OMS_MISCELLANEOUS_GET_PROFILE_VALUE( 'FILE_TRANS', 'AUTO_ARCHIVE') PROFILEVALUE FROM DUAL
 }
  OCDCLOSE_V_AGY_LOC_TO { 
 select OMS_MISCELLANEOUS_GET_PROFILE_VALUE( 'CLIENT', 'INA_FILE_LOC') profilevalue from dual
 }
 OCDCLOSE_OFFENDER_COMMUNITY_FILES_UPDATE {
  UPDATE offender_community_files
            SET holding_agy_loc_id = :holdingAgyLocId
               ,holding_staff_id = NULL
               ,non_officer_status = 'INT',
                         MODIFY_USER_ID = :modifyUserId ,
          MODIFY_DATETIME = CURRENT_TIMESTAMP
          WHERE offender_id = :offenderId
            AND offender_file_seq = :offenderFileSeq
            }
  GET_V_PROFILE_VALUE {          
SELECT OMS_MISCELLANEOUS_GET_PROFILE_VALUE('CLIENT', 'CREATE_PAPER') FROM DUAL
}
UPDATE_OFFENDER_COMM_V_NO {
select TO_CHAR(sysdate(),'YYYY')||'-'||LPAD(to_char(NEXTVAL('close_file_no')),5,'0') from dual
}
 OCDCLOSE_UPDATE_OFFENDER_BOOKINGS_Y {
   UPDATE offender_bookings
       SET comm_status      = NULL,
                              MODIFY_USER_ID = :modifyUserId ,
          MODIFY_DATETIME = CURRENT_TIMESTAMP
     WHERE offender_book_id = :offenderBookId
 }
OCDCLOSE_CASE_PLANS_PREINSERT {
	SELECT coalesce(MAX(CASE_PLAN_ID),0) + 1 FROM   CASE_PLANS WHERE  OFFENDER_BOOK_ID = :offenderBookId;
}
GET_OLD_VALUES_OFFENDER_BOOKINGS {
SELECT * FROM OFFENDER_BOOKINGS  WHERE OFFENDER_BOOK_ID =:OFFENDER_BOOK_ID
}

		--Additional Trigger OMTOFSB
OCDCLOSE_TRIGGER_DELETE{
DELETE FROM offender_files_trig
}
GET_VALUES_OFFENDER_COMMUNITY_FILES {
	SELECT * FROM OFFENDER_COMMUNITY_FILES WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
}
OCDCLOSE_FIND_LIST_REFERENCE_CODE_FOR_REASON {
SELECT REF_CODE.LIST_SEQ ,REF_CODE.DESCRIPTION, REF_CODE.CODE, REF_CODE.PARENT_CODE FROM   REFERENCE_CODES REF_CODE WHERE   (DOMAIN = 'CLO_CON_RSN')
}
OCDCLOSE_CHK_ACTIVE_ORDERS_DEPENDENCIES {
select 'Y' from legal_update_reasons lur, ( select jsonb_array_elements(Par_Orders::jsonb)->>'status' as status from ( select orderType, form_info_json as formInfoJson, items ->> 0 as Par_Orders from ( select form_identifier::jsonb->>'offenderBookId' as offenderbookid, form_identifier::jsonb->>'orderType' as orderType, form_info_json, jsonb_path_query_array(encode(form_info_json, 'escape')::jsonb, '$.*') as items from ocdleglo_data ) as t where offenderbookid = :offenderBookId::text and orderType in ('BAIL', 'NONCUST', 'PAR')) A) Orders where orders.status = lur.update_reason_code and lur.active_type = 'A' limit 1
}
OCDCLOSE_CHK_ACTIVE_COURT_REPORTS {
select 'Y' from ORDERS o where offender_book_id = :offenderBookId and order_status in ( select code from reference_codes rc where rc.domain = 'REP_REQ_STS' and rc.code = o.order_status and rc.parent_code = 'ACTIVE') limit 1
}
