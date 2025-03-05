
OIDRELSC_FIND_RGAGYLOCATIONS {
 	SELECT AGY_LOC.DESCRIPTION DESCRIPTION, AGY_LOC.AGY_LOC_ID CODE, AGY_LOC.list_seq FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGY_LOC_ID IN ( SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.CASELOAD_ID = :caseloadId AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) ) AND AGY_LOC.AGENCY_LOCATION_TYPE = 'INST'
}

OIDRELSC_FIND_RGDATETYPE {
 	SELECT description, code, list_seq FROM reference_codes WHERE domain = 'REL_DATE_TYP' AND active_flag = 'Y'
}

OIDRELSC_FIND_RGMOVEMENTREASONS {
 	SELECT MOVEMENT_REASON_CODE CODE, DESCRIPTION FROM MOVEMENT_REASONS WHERE MOVEMENT_TYPE = 'REL' AND ACTIVE_FLAG = 'Y'
}

OIDRELSC_OFFRELDET_FIND_OFFENDER_RELEASE_DETAILS {
 	SELECT ord.OFFENDER_BOOK_ID ,ord.RELEASE_DATE ,ord.COMMENT_TEXT ,ord.MOVEMENT_TYPE ,ord.MOVEMENT_REASON_CODE ,ord.CREATE_DATETIME ,ord.CREATE_USER_ID ,ord.MODIFY_DATETIME ,ord.MODIFY_USER_ID ,ord.EVENT_ID ,ord.EVENT_STATUS ,ord.VERIFIED_FLAG ,ord.SEAL_FLAG  FROM OFFENDER_RELEASE_DETAILS ord
}
OIDRELSC_OFFRELDET_INSERT_OFFENDER_RELEASE_DETAILS {
	INSERT INTO OFFENDER_RELEASE_DETAILS(OFFENDER_BOOK_ID ,RELEASE_DATE ,COMMENT_TEXT ,MOVEMENT_TYPE ,MOVEMENT_REASON_CODE ,CREATE_DATETIME ,CREATE_USER_ID ,EVENT_ID ,EVENT_STATUS,VERIFIED_FLAG ,SEAL_FLAG, MODIFY_USER_ID, MODIFY_DATETIME) VALUES(:offenderBookId ,:releaseDate ,:commentText ,:movementType ,:movementReasonCode ,CURRENT_TIMESTAMP,:createUserId ,:eventId ,:eventStatus,:verifiedFlag ,:sealFlag, null, null)
}

OIDRELSC_OFFRELDET_UPDATE_OFFENDER_RELEASE_DETAILS {
	UPDATE OFFENDER_RELEASE_DETAILS set OFFENDER_BOOK_ID  = :offenderBookId ,RELEASE_DATE  = :releaseDate ,COMMENT_TEXT  = :commentText ,MOVEMENT_TYPE  = :movementType ,MOVEMENT_REASON_CODE  = :movementReasonCode,MODIFY_DATETIME  = CURRENT_TIMESTAMP ,MODIFY_USER_ID  = :modifyUserId ,EVENT_ID  = :eventId ,EVENT_STATUS  = :eventStatus,VERIFIED_FLAG  = :verifiedFlag ,SEAL_FLAG  = :sealFlag  where OFFENDER_BOOK_ID  = :offenderBookId
}


OIDRELSC_CTL_WHENVALIDATERECORD {
	 SELECT max (OFFENDER_BOOK_ID) FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE ltrim(OFFENDER_ID_DISPLAY::text,'0') = ltrim(:offenderIdDisplay::text,'0') AND AGY_LOC_ID IN ( SELECT AGY_LOC_ID FROM AGENCY_LOCATIONS WHERE AGENCY_LOCATION_TYPE = 'INST' AND AGY_LOC_ID NOT IN ('OUT', 'TRN') AND AGY_LOC_ID IN (SELECT AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = :caseLoadId))
}

OIDRELSC_CHK_WORK_FLOWS {
	SELECT COUNT (WF.WORK_FLOW_ID) FROM WORK_FLOWS WF, WORK_FLOW_LOGS WFL WHERE WF.WORK_FLOW_ID = WFL.WORK_FLOW_ID AND WF.OBJECT_CODE = :P_OBJECT_CODE AND coalesce (WF.OBJECT_SEQ, 0) = coalesce (:P_OBJECT_SEQ, 0) AND WF.OBJECT_ID = :P_OBJECT_ID AND WFL.WORK_FLOW_SEQ = (SELECT MAX (WORK_FLOW_SEQ) FROM WORK_FLOW_LOGS WHERE WORK_FLOW_ID = WF.WORK_FLOW_ID) AND WFL.WORK_ACTION_CODE = 'VER'
}

OIDRELSC_VERIFICATION_BUTTON_ {
	SELECT MAX (OBJECT_SEQ) FROM WORK_FLOWS WHERE OBJECT_CODE = :P_OBJ_CODE AND OBJECT_ID = :P_OBJ_ID
}

OIDRELSC_VERIFICATION_BUTTON_ {
	select
	WORK_FLOW_ID
from
	WORK_FLOWS
where
	OBJECT_CODE =:P_OBJ_CODE
	and OBJECT_ID =:P_OBJ_ID
	and COALESCE (OBJECT_SEQ,
	0) =:P_OBJ_SEQ
}

OIDRELSC_INS_WORK_FLOWS_COUNTCURSOR {
	SELECT COUNT (WORK_FLOW_ID) FROM WORK_FLOWS WHERE OBJECT_CODE = :P_OBJ_CODE AND OBJECT_ID = :P_OBJ_ID AND COALESCE (OBJECT_SEQ, 0) = :P_OBJ_SEQ
}

OIDRELSC_INS_WORK_FLOW{
	SELECT coalesce (MAX (OBJECT_SEQ), 0) + 1 FROM WORK_FLOWS WHERE OBJECT_CODE = :P_OBJ_CODE AND OBJECT_ID = :P_OBJ_ID
}

OIDRELSC_INS_WORK_FLOWS_SEQ{
	SELECT NEXTVAL('WORK_FLOW_ID')  FROM DUAL
}

OIDRELSC_UPD_WORK_FLOWS_ {
	SELECT MAX (OBJECT_SEQ) FROM WORK_FLOWS WHERE OBJECT_CODE = :P_OBJ_CODE AND OBJECT_ID = :P_OBJ_ID
}

OIDRELSC_UPD_WORK_FLOWS_ {
     SELECT MAX (WORK_FLOW_ID) FROM WORK_FLOWS WHERE OBJECT_CODE =:P_OBJ_CODE AND OBJECT_ID =:P_OBJ_ID AND COALESCE (OBJECT_SEQ, 0) =:P_OBJ_SEQ
}

OIDRELSC_UPD_WORK_FLOWS_ {
	SELECT COALESCE (MAX (WORK_FLOW_SEQ), 0) + 1 FROM WORK_FLOW_LOGS WHERE WORK_FLOW_ID =:V_WORK_FLOW_ID
}

OIDRELSC_CHECK_OFF_AGENCY_LOCATION_ {
select
	'X'
from
	OFFENDERS off,
	OFFENDER_BOOKINGS OFF_BKG
where
	OFF.OFFENDER_ID_DISPLAY =:P_OTIS
	and OFF.OFFENDER_ID = OFF_BKG.OFFENDER_ID
	and OFF_BKG.AGY_LOC_ID =:P_AGY_LOC_ID
	and OFF_BKG.ACTIVE_FLAG = 'Y'
}

OIDRELSC_RELEASE_BUTTONRELEASE_BUTTON {
	SELECT OFFENDER_ID FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID
}

OIDRELSC_INSERT_WORK_FLOW_ENT {
	SELECT MAX (OBJECT_SEQ) FROM WORK_FLOWS WHERE OBJECT_CODE = :P_OBJ_CODE AND OBJECT_ID = :P_OBJ_ID
}

OIDRELSC_INSERT_WORK_FLOW_ENT_MAXFLOWID {
SELECT MAX (WORK_FLOW_ID) FROM WORK_FLOWS WHERE OBJECT_CODE = :P_OBJ_CODE AND OBJECT_ID = :P_OBJ_ID AND COALESCE (OBJECT_SEQ, 0) = :P_OBJ_SEQ
}
OIDRESC_INSERT_INTO_WORK_FLOWS {
      INSERT INTO WORK_FLOWS (
                      WORK_FLOW_ID,
					  OBJECT_CODE,
					  OBJECT_ID,
					  OBJECT_SEQ,
					  CREATE_DATETIME,
					  CREATE_USER_ID,
					  MODIFY_USER_ID, MODIFY_DATETIME
					  )
			VALUES(:workFlowId,
					  :objectCode,
					  :objectId,
					  :objectSeq,
					  CURRENT_TIMESTAMP,
					  :createUserId,
					  null, null
					 )
					 
 }
 
 OIDRESC_INSERT_INTO_WORK_FLOW_LOGS {
     INSERT INTO WORK_FLOW_LOGS (
                      WORK_FLOW_ID,
					  WORK_FLOW_SEQ,
					  WORK_ACTION_CODE,
					  WORK_ACTION_DATE,
					    ACTION_USER_ID,
					  WORK_FLOW_STATUS,
					  CREATE_DATE,
					  LOCATE_AGY_LOC_ID,
					  CREATE_USER_ID,
					  CREATE_DATETIME,
					  MODIFY_USER_ID, MODIFY_DATETIME
					  
					  )
			VALUES(:workFlowId,
					  :workFlowSeq,
					  :workActionCode,
					 CURRENT_TIMESTAMP,
					 0,
					 :workFlowStatus,
					 CURRENT_TIMESTAMP,
					 NULL,
                      :createUserId,
                      CURRENT_TIMESTAMP, null, null
					 )
					 
 }
OIDRELSC_SELECTED_RECORD {
select * from V_HEADER_BLOCK_FN(:USERID) v_header_block where OFFENDER_BOOK_ID =:offenderBookId
}
OIDRELSC_GET_OFF_BOOK_ID {
SELECT ROOT_OFFENDER_ID FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
}
OIDRELSC_GET_OFF_ID_DISPLAY {
	SELECT OFFENDER_ID_DISPLAY FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE ROOT_OFFENDER_ID = :ROOTOFFENDERID  LIMIT 1
}
OIDRELSC_GET_EVENT_ID{
SELECT NEXTVAL('EVENT_ID')  FROM DUAL
}


OIDRELSC_GET_LEGAL_SUMMERY_DATA_FOR_DATES{
select
	*
from
	(
	select
		A.offender_book_id,
		A.nbt_name,
		A.offender_id_display,
		A.form_info_json_blob,
		A.form_identifier,
		A.agy_loc_id_desc,
		ord.release_date,
		ord.comment_text,
		ord.movement_type,
		ord.movement_reason_code,
		ord.event_id,
		ord.event_status,
		ord.verified_flag,
		ord.create_datetime,
		case
			when ord.modify_datetime is null then ord.create_datetime
			else ord.modify_datetime
		end latest_date,
		ord.modify_datetime,
		ord.create_user_id,
		case
			when ord.offender_book_id::text is null then 'N'
			else 'Y'
		end data_Exist_Flag
	from
		(
		select
			(form_identifier::json ->> 'offenderBookId')::int as offender_book_id,
			(
			select
				concat(TRIM(last_name) ,
				', ',
				TRIM(first_name))
			from
				offenders
			where
				offender_id = ob.offender_id) as nbt_name,
			(
			select
				offender_id_display
			from
				offenders
			where
				offender_id = ob.offender_id) as offender_id_display,
			B.form_info_json as form_info_json_blob,
			B.form_identifier,
			B.create_user_id,
			B.modify_datetime,
			B.modify_user_id,
			(
			select
				al.description
			from
				agency_locations al
			where
				al.agy_loc_id = ob.agy_loc_id ) as agy_loc_id_desc
		from
			ocdlegls_data B,
			offender_bookings ob
		where
			(B.form_identifier::json ->> 'offenderBookId')::int = ob.offender_book_id
			and ob.active_flag = 'Y'
			and ob.agy_loc_id in (:facility))A
	left outer join offender_release_details ord on
		A.OFFENDER_BOOK_ID = ORD.OFFENDER_BOOK_ID)A
order by
	latest_date desc NULLS last
}
	
    OIDRELSC_GET_OFFENDER_BOOK_ID_KEY_DATES {	
	select 
    A.offbkgid as offender_book_id,	
	B.form_info_json as form_info_json_blob,
	B.form_identifier,
	B.create_datetime,
	B.create_user_id,
	B.modify_datetime,
	B.modify_user_id
from
	(
	select
		max(create_datetime) max_create_date,
		(form_identifier::json ->> 'offenderBookId')::int as offbkgid
	from
		ocdlegls_data
	group by
		offbkgid)A,
	ocdlegls_data B
	
where
    A.offbkgid=:offenderBookId and
	A.max_create_date = B.create_datetime
	and A.offbkgid =(B.form_identifier::json ->> 'offenderBookId')::int 
	}
	
OIDRELSC_GET_CHARGES_DATA_OFFENSES {
select
	json_array_elements((json_array_elements((encode(form_info_json, 'escape')::json ->> 'myJsonRowData')::json)->>'charges')::json)->>'chargeId' as charge_id,
	json_array_elements((json_array_elements((encode(form_info_json, 'escape')::json ->> 'myJsonRowData')::json)->>'charges')::json)->>'matter' as matter,
	json_array_elements((json_array_elements((encode(form_info_json, 'escape')::json ->> 'myJsonRowData')::json)->>'charges')::json)->>'act' as act,
	json_array_elements((json_array_elements((encode(form_info_json, 'escape')::json ->> 'myJsonRowData')::json)->>'charges')::json)->>'code' as code,
	json_array_elements((json_array_elements((encode(form_info_json, 'escape')::json ->> 'myJsonRowData')::json)->>'charges')::json)->>'description' as description,
	json_array_elements((json_array_elements((encode(form_info_json, 'escape')::json ->> 'myJsonRowData')::json)->>'charges')::json)->>'outcome' as outcome
from
	ocdleglo_data
where
(form_identifier::json->>'offenderBookId')::int=:offenderBookId
order by
	CREATE_DATETIME desc
limit 1
}

OIDRELSC_FIND_RGKEY_DATE_FUNCTION {
select code,description from reference_codes where "domain" =:domainName and active_flag='Y'
}

OIDRELSC_GET_RELEASE_DATE{
select release_date from OFFENDER_RELEASE_DETAILS where offender_book_id =:offenderBookId 
}
OIDRELSC_GET_MAX_WORK_FLOW_ID {
select
	*
from
	work_flow_logs wfl
where
	work_flow_id in (
	select
		WF.work_flow_id
	from
		work_flows wf
	where 
		WF.object_id = :objectId
		and wf.object_seq  = :objectSeq
		and wf.object_code = 'RELEASE'
		)
	and work_action_code = 'VER'
	and work_flow_seq = (
	select
		max(wfl2.work_flow_seq)
	from
		work_flow_logs wfl2
	where
		wfl2.work_flow_id = wfl.work_flow_id)
}
OIDRELSC_COMMENT_UPDATE_OFFENDER_RELEASE_DETAILS
{
update OFFENDER_RELEASE_DETAILS set comment_text =:commentText,modify_datetime =current_timestamp ,modify_user_id =:modifyUserId where offender_book_id =:offenderBookId 
}