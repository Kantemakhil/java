
OIIWLTWJ_FIND_CGFKVTWLDSPDESCRIPTION {
 	SELECT REF_CODE1.DESCRIPTION DSP_DESCRIPTION ,REF_CODE1.CODE WAIT_LIST_STATUS  FROM REFERENCE_CODES REF_CODE1  WHERE REF_CODE1.DOMAIN = 'TRN_SCH_STS'  AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' ) ORDER BY REF_CODE1.LIST_SEQ ASC ,REF_CODE1.DESCRIPTION ASC
}

OIIWLTWJ_FIND_CGFKVTWLAGENCYLOCATIONTO {
  SELECT AGY_LOC_ID CODE ,  DESCRIPTION , ACTIVE_FLAG FROM AGENCY_LOCATIONS   WHERE AGENCY_LOCATION_TYPE = 'INST'    AND AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) ORDER BY CODE
}

OIIWLTWJ_FIND_CGFKVTWLDSPDESCRIPTION3 {
 	SELECT REF_CODE.DESCRIPTION ,REF_CODE.CODE FROM   REFERENCE_CODES REF_CODE WHERE   REF_CODE.DOMAIN = 'TRN_PRIORITY'  AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' ) ORDER BY  REF_CODE.LIST_SEQ ASC , REF_CODE.DESCRIPTION ASC
}

OIIWLTWJ_FIND_RGCANCELREASON {
 	SELECT DESCRIPTION         ,CODE FROM REFERENCE_CODES WHERE DOMAIN = 'TRN_CNCL_RSN'    AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE  IS NULL )      OR '' = 'ENTER-QUERY' ) ORDER BY  LIST_SEQ ASC ,DESCRIPTION ASC
}

OIIWLTWJ_VTWL_FIND_V_TRANSFER_WAITING_LISTS_2 {
select bkg.offender_book_id, off.offender_id_display, off.last_name, off.first_name, off.root_offender_id, bkg.agy_loc_id, wl.event_id, sch.to_agy_loc_id as agency_location_to, substr(tag_schedule_event_sub_type_desc(sch.event_class, sch.event_type, sch.event_sub_type)::text, 1, 40) as event_sub_type_desc, case when sch.event_status::text = 'SCH'::text then 'Y'::text else 'N'::text end as active_flag, wl.request_date as requested_date, wl.wait_list_status as wait_list_status, wl.transfer_priority as transfer_priority, wl.outcome_reason_code, wl.approved_flag, tag_schedule_check_sum(coalesce(wl.modify_datetime, wl.create_datetime)) as check_sum from offender_ind_schedules sch, offender_ind_sch_wait_lists wl, offenders off, offender_bookings bkg where wl.approved_flag = 'Y' and wl.wait_list_status = 'PEN' and exists ( select 'X' from ( select active_flag, offender_book_id, agy_loc_id from offenders inner join offender_bookings on offenders.offender_id = offender_bookings.offender_id) VHB where VHB.active_flag = 'Y' and VHB.OFFENDER_BOOK_ID = sch.offender_book_id and exists ( select 'X' from CASELOAD_AGENCY_LOCATIONS CA where CA.AGY_LOC_ID = VHB.AGY_LOC_ID and CA.CASELOAD_ID = :CASELOADID ) ) and sch.event_id = wl.event_id and sch.offender_book_id = bkg.offender_book_id and bkg.offender_id = off.offender_id order by case WAIT_LIST_STATUS when 'PEN' then 1 when 'CON' then 2 when 'CAN' then 3 else 4 end, wl.transfer_priority, wl.request_date
}
OIIWLTWJ_VTWL_UPDATE_V_TRANSFER_WAITING_LISTS_2 {
	UPDATE V_TRANSFER_WAITING_LISTS_2 set OFFENDER_BOOK_ID  = :offenderBookId ,OFFENDER_ID_DISPLAY  = :offenderIdDisplay ,LAST_NAME  = :lastName ,EVENT_ID  = :eventId ,AGENCY_LOCATION_TO  = :agencyLocationTo ,ACTIVE_FLAG  = :activeFlag ,REQUESTED_DATE  = :requestedDate ,WAIT_LIST_STATUS  = :waitListStatus ,TRANSFER_PRIORITY  = :transferPriority ,OUTCOME_REASON_CODE  = :outcomeReasonCode ,APPROVED_FLAG  = :approvedFlag ,CHECK_SUM  = :checkSum /* where */
}


OIIWLTWJ_CGFKCHK_V_TWL_V_TWL_AGY_LOC_F {
	SELECT AGENCY_LOCATIONS.DESCRIPTION FROM   AGENCY_LOCATIONS AGENCY_LOCATIONS WHERE  AGENCY_LOCATIONS.AGY_LOC_ID = :AGENCYLOCATIONTO AND     AGENCY_LOCATIONS.AGENCY_LOCATION_TYPE IN ('INST','COMM')
}

OIIWLTWJ_CGFKCHK_V_TWL_V_TWL_V_OFF_BKG {
	SELECT V_HEADER_BLOCK.ROOT_OFFENDER_ID ,V_HEADER_BLOCK.FIRST_NAME ,V_HEADER_BLOCK.AGY_LOC_ID FROM   V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE  V_HEADER_BLOCK.OFFENDER_BOOK_ID = :OFFENDERBOOKID
 }

OIIWLTWJ_REFRESH_CHECK_SUM_ {
	SELECT CHECK_SUM FROM   V_TRANSFER_WAITING_LISTS_2 WHERE EVENT_ID = :EVENTID
}

OIIWLTWJ_GET_PARENT_CODE_ {
	SELECT PARENT_CODE FROM REFERENCE_CODES WHERE DOMAIN      = 'TRN_SCH_STS' AND CODE        = :WAITLISTSTATUS AND ACTIVE_FLAG = 'Y'
}

OIIWLTWJ_UPDATE_SCHEDULE_INFO_OFFENDER_IND_SCHEDULES_SSH {
UPDATE OFFENDER_IND_SCHEDULES  SET TO_AGY_LOC_ID  = :toAgyLocId, EVENT_STATUS = 'SCH',MODIFY_DATETIME=current_timestamp ,MODIFY_USER_ID=:modifyUserId
 WHERE EVENT_ID = :eventId
}

OIIWLTWJ_UPDATE_SCHEDULE_INFO_OFFENDER_IND_SCHEDULES {
UPDATE OFFENDER_IND_SCHEDULES SET TO_AGY_LOC_ID  = :toAgyLocId, MODIFY_DATETIME=current_timestamp ,MODIFY_USER_ID=:modifyUserId WHERE EVENT_ID = :eventId
}

OIIWLTWJ_UPDATE_SCHEDULE_INFO_OFFENDER_IND_SCHEDULES_WAIT_LIST {
UPDATE OFFENDER_IND_SCH_WAIT_LISTS SET WAIT_LIST_STATUS = :waitListStatus, TRANSFER_PRIORITY = :transferPriority , OUTCOME_REASON_CODE =:outcomeReasonCode, MODIFY_DATETIME=current_timestamp ,MODIFY_USER_ID=:modifyUserId
  WHERE EVENT_ID = :eventId
}
