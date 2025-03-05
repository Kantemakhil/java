
OCDPSREP_FIND_RGORDERTYPE {
 	SELECT
    description,
    order_type code,active_flag FROM order_types WHERE order_type <> 'AUTO'    
}

OCDPSREP_FIND_RGISSUINGAGYLOCID {
select
			agy_loc.description DESCRIPTION,
			agy_loc.agy_loc_id code,
			agy_loc.active_flag 
		FROM agency_locations agy_loc
   WHERE agency_location_type = 'CRT'
     AND (active_flag = 'Y' AND deactivation_date IS NULL)
     AND agy_loc.agy_loc_id NOT IN ('TRN', 'OUT')
ORDER BY list_seq,description;
}
 	   

OCDPSREP_FIND_ORDER_PROPOSAL_DESC {
select sct.sentence_calc_type code, sct.description, sct.active_flag from SENTENCE_CALC_TYPES sct where  sentence_category  in ('NCUS','PAR');
}

OCDPSREP_FIND_NBT_SENT_CALC_TYPE {
SELECT
    sct.description,
    sct.sentence_calc_type code
FROM
    sentence_calc_types sct
WHERE
     active_flag = 'Y'
     
    AND sentence_category = :sentence_category order BY sct.list_seq,
   sct.sentence_calc_type
 }
 
 
OCDPSREP_FIND_NBT_COMM_CONDITION_DESC {
select
	a.description,
	codea as code,
	case
		when coalesce(b.codeb::text, '') = '' then 'N'
		else 'Y'
	end as active_flag
from
	(
	select
		description,
		comm_condition_code codea,
		category_type
	from
		community_conditions  where category_type = 'CRT_REP'
    ) a
left join(
	select
		cc.description,
		cc.comm_condition_code codeb,
		category_type
	from
		community_conditions cc
	where
		comm_condition_type = 'COMM' and category_type = 'CRT_REP'
		and active_flag = 'Y'
    ) b on
	a.description = b.description
	and a.codea = b.codeb and
	a.category_type=b.category_type

 }


OCDPSREP_FIND_RGPROPOSEDCATEGORY {
SELECT
    ref_code.description,
            ref_code.code
FROM
    reference_codes ref_code
WHERE
    ref_code.domain = 'CATEGORY'
    AND  ref_code.active_flag = 'Y'
         ORDER by list_seq,
      description
}


OCDPSREP_ORD_EXECUTEQUERY {
 	SELECT
    order_id,
    offender_book_id,
    case_id,
    court_date,
    order_type,
    issuing_agy_loc_id,
    court_info_id,
    order_status,
    create_user_id,
    modify_user_id,
    modify_datetime,
    create_datetime,
    due_date,
    court_seriousness_level,
    order_seriousness_level,
    request_date,
    staff_work_id,
    event_id,
    complete_date,
    complete_staff_id,
    intervention_tier_code,
    non_report_flag,
    cps_received_date,
    comment_text,
    issue_date,
    message_id,
    workflow_id,
    offender_proceeding_id,
    requesting_officer,
    team_id,
    status_date,
    staff_member_id,
    defence_solicitor,
    seal_flag FROM orders where OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID::bigint AND order_type <> 'AUTO' order by request_date desc
}
OCDPSREP_ORD_UPDATE_ORDERS {
	UPDATE ORDERS set 
    case_id=:caseId,
    court_date=:courtDate,
    order_type=:orderType,
    issuing_agy_loc_id=:issuingAgyLocId,
    court_info_id=:courtInfoId,
    order_status=:orderStatus,
    modify_user_id=:modifyUserId,
  	modify_datetime=current_timestamp,
    due_date=:dueDate,
   request_date=:requestDate,
   event_id=:eventId,
   complete_date=:completeDate,
   issue_date=:issueDate,
   message_id=:messageId,
   seal_flag=:sealFlag,
   comment_text=:commentText,
   requesting_officer=:requestingOfficer,
   team_id=:teamId,
   status_date=:statusDate,
   staff_member_id=:staffMemberId,
   defence_solicitor = :defenceSolicitor
   where ORDER_ID=:orderId::bigint
}

OCDPSREP_ORD_POSTQUERY{
	SELECT DESCRIPTION FROM REFERENCE_CODES
    WHERE DOMAIN = 'PSR_STATUS' 
      AND CODE = :CODE
}

OCDPSREP_ORD_AGY_LOC_ID_POSTQUERY{
	SELECT description
           FROM agency_locations
          WHERE agy_loc_id = :p_agy_loc_id
       }
 

OCDPSREP_ORD_POSTUPDATESTATUS {
 update orders set order_status =:order_status, modify_user_id =:modifyUserId, modify_datetime =current_timestamp where order_id = :order_id
}

OCDPSREP_ORDPROPOSALS_EXECUTEQUERY {
 	select
ROW_ID,
	order_id,
	order_proposal_code,
	sentence_calc_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	sentence_category,
	NOT_SUITABLE_FLAG,
	NOT_SUITABLE_REASON,
	comment_text,
	seal_flag
from
	order_proposals
where
	order_id =:order_id
order by
	oumagloc_sort_on_description('RPT_PPSL',
	order_proposal_code)
}




OCDPSREP_ORDPROPOSALS_INSERT_ORDER_PROPOSALS {
INSERT INTO ORDER_PROPOSALS(ORDER_ID, ORDER_PROPOSAL_CODE, SENTENCE_CALC_TYPE,NOT_SUITABLE_FLAG,NOT_SUITABLE_REASON, COMMENT_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SENTENCE_CATEGORY, SEAL_FLAG) 
VALUES(:orderId,:orderProposalCode,:sentenceCalcType,:notSuitableFlag,:notSuitableReason,:commentText,current_timestamp,:createUserId,NULL,NULL,:sentenceCategory,:sealFlag) 	
}

OCDPSREP_ORDPROPOSALS_UPDATE_ORDER_PROPOSALS {
update ORDER_PROPOSALS set ORDER_PROPOSAL_CODE =:orderProposalCode,NOT_SUITABLE_FLAG = :notSuitableFlag, NOT_SUITABLE_REASON = :notSuitableReason, COMMENT_TEXT = :commentText, MODIFY_DATETIME = current_timestamp,MODIFY_USER_ID =:modifyUserId where	ORDER_ID =:orderId and ORDER_PROPOSAL_CODE =:oldOrderProposalCode
}

OCDPSREP_ORDPROPOSALS_DELETE_ORDER_PROPOSALS { 
	DELETE FROM ORDER_PROPOSALS where ORDER_ID=:orderId and ORDER_PROPOSAL_CODE=:orderProposalCode
}

OCDPSREP_ORDER_PROPOSAL_CONDITIONS_EXECUTEQUERY {
 	select
	order_proposal_condition_id,
	order_id,
	order_proposal_code,
	comm_condition_type,
	comm_condition_code,
	length,
	length_unit_code,
	comment_text,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	category_type,
	seal_flag
from
	order_proposal_conditions
where
	order_id =:order_id
	and ORDER_PROPOSAL_CODE =:ORDER_PROPOSAL_CODE
order by
	tag_court_reports_get_requirement_desc(comm_condition_type,
	comm_condition_code)
}

OCDPSREP_ORDPROPCONDS_PREINSERT {
 SELECT nextval('order_proposal_condition_id')
	      FROM DUAL
}

OCDPSREP_ORDPROPCONDS_INSERT_ORDER_PROPOSAL_CONDITIONS {
insert into ORDER_PROPOSAL_CONDITIONS(ORDER_PROPOSAL_CONDITION_ID, ORDER_ID, ORDER_PROPOSAL_CODE, COMM_CONDITION_TYPE, COMM_CONDITION_CODE, LENGTH, LENGTH_UNIT_CODE, COMMENT_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, CATEGORY_TYPE, SEAL_FLAG) values(:orderProposalConditionId, :orderId, :orderProposalCode, :commConditionType, :commConditionCode, :length, :lengthUnitCode, :commentText, current_timestamp, :createUserId, NULL, NUll, :categoryType, :sealFlag) 
}

OCDPSREP_ORDPROPCONDS_UPDATE_ORDER_PROPOSAL_CONDITIONS {
	update ORDER_PROPOSAL_CONDITIONS set ORDER_PROPOSAL_CONDITION_ID =:orderProposalConditionId, ORDER_ID =:orderId, ORDER_PROPOSAL_CODE =:orderProposalCode, COMM_CONDITION_TYPE =:commConditionType, COMM_CONDITION_CODE =:commConditionCode, LENGTH =:length, LENGTH_UNIT_CODE =:lengthUnitCode, COMMENT_TEXT =:commentText, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID =:modifyUserId, CATEGORY_TYPE =:categoryType, SEAL_FLAG =:sealFlag where ORDER_PROPOSAL_CONDITION_ID =:orderProposalConditionId
}

OCDPSREP_ORDPROPCONDS_DELETE_ORDER_PROPOSAL_CONDITIONS { 
	DELETE FROM ORDER_PROPOSAL_CONDITIONS where ORDER_PROPOSAL_CONDITION_ID=:orderProposalConditionId
}

OCDPSREP_ORDPPSLCONDACT_EXECUTEQUERY {
 SELECT
    order_id,
    condition_activity_code,
    comment_text,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id,
    order_ppsl_cond_activity_id,
    program_id,
    seal_flag
FROM
    order_ppsl_cond_activities where order_id=:ORDER_PROPOSAL_CONDITION_ID 
}

OCDPSREP_ORD_PPSL_COND_ACT_PREINSERT_ {
	SELECT NEXTVAL('ORDER_PPSL_COND_ACTIVITY_ID') FROM DUAL
}

OCDPSREP_ORDPPSLCONDACT_INSERT_ORDER_PPSL_COND_ACTIVITIES {
 insert into ORDER_PPSL_COND_ACTIVITIES(ORDER_ID, CONDITION_ACTIVITY_CODE, COMMENT_TEXT, CREATE_DATETIME, CREATE_USER_ID, modify_datetime , ORDER_PPSL_COND_ACTIVITY_ID, PROGRAM_ID, SEAL_FLAG) values(:orderId, :conditionActivityCode, :commentText, current_timestamp, :createUserId, NULL, :orderPpslCondActivityId, :programId, :sealFlag) 
}

OCDPSREP_ORDPPSLCONDACT_UPDATE_ORDER_PPSL_COND_ACTIVITIES {
	update ORDER_PPSL_COND_ACTIVITIES set CONDITION_ACTIVITY_CODE =:conditionActivityCode, COMMENT_TEXT =:commentText, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID =:modifyUserId, ORDER_PPSL_COND_ACTIVITY_ID =:orderPpslCondActivityId, PROGRAM_ID =:programId, SEAL_FLAG =:sealFlag where ORDER_PPSL_COND_ACTIVITY_ID =:orderPpslCondActivityId
}

OCDPSREP_ORDPPSLCONDACT_DELETE_ORDER_PPSL_COND_ACTIVITIES { 
	DELETE FROM ORDER_PPSL_COND_ACTIVITIES where ORDER_PPSL_COND_ACTIVITY_ID=:orderPpslCondActivityId
}
GETTING_OLD_RECORDS {
select * from orders where ORDER_ID= :ORDER_ID
}
OCDPSREP_ORD_INSERT_ORDERS {
insert into ORDERS (ORDER_ID, ORDER_TYPE, ISSUING_AGY_LOC_ID, COURT_DATE, COMPLETE_DATE, REQUEST_DATE, DUE_DATE, COMMENT_TEXT, ORDER_STATUS, CASE_ID, OFFENDER_BOOK_ID, EVENT_ID, CREATE_DATETIME, CREATE_USER_ID, REQUESTING_OFFICER, TEAM_ID, STATUS_DATE, STAFF_MEMBER_ID, DEFENCE_SOLICITOR,modify_datetime) values(:orderId, :orderType, :issuingAgyLocId, CURRENT_DATE, :completeDate, :requestDate, :dueDate, :commentText, :orderStatus, case when coalesce(:caseId, null) = null then null else :caseId::bigint end, :offenderBookId, :eventId, CURRENT_TIMESTAMP, :createUserId, :requestingOfficer, :teamId, :statusDate, :staffMemberId, :defenceSolicitor,null) 
}

OCDPSREP_ORD_INSERT_ORDERS_HTY{
  insert into ORDERS_HTY (ORDER_HTY_ID, ORDER_ID, ORDER_TYPE, ISSUING_AGY_LOC_ID, REQUESTING_OFFICER, REQUEST_DATE, DUE_DATE, TEAM_ID, STAFF_MEMBER_ID, ORDER_STATUS, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, DEFENCE_SOLICITOR) values (nextval('Orders_hty_order_hty_id'), :orderId, :orderType, :issuingAgyLocId, :requestingOfficer, :requestDate, :dueDate, :teamId, :staffMemberId, :orderStatus, current_timestamp, :createUserId, NULL, :defenceSolicitor)
}

OCDPSREP_FIND_RGTEAM{
SELECT at.team_code AS team_code, at.description AS description, TO_CHAR(at.team_id) AS code FROM automation_teams at JOIN team_agency_loc tal ON at.team_id = tal.team_id JOIN OFFENDER_BOOKING_AGY_LOCS ol ON tal.agy_loc_id = ol.agy_loc_id JOIN agency_locations al ON ol.agy_loc_id = al.agy_loc_id WHERE at.active_flag = 'Y' AND tal.active_flag = 'Y' AND ol.offender_book_id = :offender_book_id AND al.agency_location_type = 'COMM' ORDER BY at.description
}

OCDPSREP_FIND_RGSTAFFMEMBERS{
SELECT to_char(SM.STAFF_ID) CODE, (SM.LAST_NAME || ' , ' || SM.FIRST_NAME) DESCRIPTION FROM  STAFF_ACCESSIBLE_CASELOADS SAC, STAFF_MEMBERS SM
WHERE  SAC.CASELOAD_ID = :caseloadId AND SAC.STAFF_ID =SM.STAFF_ID AND SM.STATUS = 'ACTIVE' 
ORDER BY SM.STAFF_ID ASC
}

OCDPSREP_FIND_TEAM_RGSTAFFMEMBERS{
select
	to_char(TSM.TEAM_MEMBER_ID) CODE,
	(SM.LAST_NAME || ' , ' || SM.FIRST_NAME) DESCRIPTION
from
	TEAM_STAFF_MEMBERS TSM,
	STAFF_MEMBERS SM
where
	TEAM_ID = (:teamResponsible)::bigint
	and SM.STAFF_ID = TSM.STAFF_ID
	and TSM.ACTIVE_FLAG = 'Y'
	and SM.STATUS = 'ACTIVE'
	}
OCDPSREP_ORD_HISTORY_EXECUTEQUERY {
select
	(select description  from order_types where order_type = o.order_type ) as order_type,
	(select description from agency_locations al where agy_loc_id = o.issuing_agy_loc_id) as issuing_agy_loc_id,
	requesting_officer,
	request_date,
	due_date,
	(select rc.description from reference_codes rc where  domain = 'REP_REQ_STS' and code = o.order_status ) as order_status,
	create_datetime,
	create_user_id,
	defence_solicitor,
	case
		when
		o.team_id is null
		then
	(
		select
			(SM.LAST_NAME || ' , ' || SM.FIRST_NAME)::varchar
		from
			STAFF_MEMBERS SM
		where
			o.staff_member_id = sm.staff_id::text)
		else 
	(
		select
			(SM.LAST_NAME || ' , ' || SM.FIRST_NAME)::varchar
		from
			STAFF_MEMBERS SM
		where
			sm.staff_id = (
			select
				staff_id
			from
				TEAM_STAFF_MEMBERS tm
			where
				tm.team_id = o.team_id::bigint
				and o.staff_member_id::bigint = tm.team_member_id ))
	end as staff_member_id,
	case when team_id is not null then 
	(select at.description from automation_teams at where team_id =  o.team_id::bigInt) else null end as team_id,
	o.order_status
from
	orders_hty o where order_id = :orderId order by create_datetime desc
}


OCDPSREP_RG_ACCREDITED_PROGRAMS_RECORD_GROUP{
 	SELECT  program_code code,description  FROM PROGRAM_SERVICES  WHERE PROGRAM_CATEGORY= 'ACP' AND EXPIRY_DATE IS NULL
}

OCDPSREP_SEQUENCE_ORDER_ID{
SELECT NEXTVAL('ORDER_ID') FROM DUAL
}

OCDPSREP_UPD_ORD_AUTHORS_COMMENT_TEXT {
update orders set comment_text = :comment_text,modify_user_id =:modify_user_id,modify_datetime = current_timestamp where order_id = :order_id
}

OCDPSREP_CHARGES_EXECUTE_QUERY {
	select offender_book_id, charge_id, order_id, create_datetime from off_court_report_charges where offender_book_id = :offenderBookId and order_id = :orderId
}

OCDPSREP_INSERT_CHARGES {
 insert into off_court_report_charges (offender_book_id, order_id, charge_id, create_datetime, create_user_id, modify_datetime) values (:offenderBookId, :orderId, :chargeId, current_timestamp, :createUserId, NULL) 
}

OCDPSREP_DELETE_CHARGES {
	delete from off_court_report_charges where offender_book_id = :offenderBookId and order_id = :orderId and charge_id = :chargeId
}
