	
	OCUOICHN_FIND_RGAGYINCPSTAFFID {
select
	STAFF1.LAST_NAME || ', ' || STAFF1.FIRST_NAME DESCRIPTION ,
	STAFF1.STAFF_ID STAFF_ID 
from
	STAFF_MEMBERS STAFF1
where
	STAFF1.STAFF_ID in (
	select
		STAFF_ID
	from
		STAFF_ACCESSIBLE_CASELOADS
	where
		CASELOAD_ID = :CASELOADID )
	and ( (SUSPENDED_FLAG = 'N'
		and TERMINATION_DATE is null )
	or ''= 'ENTER-QUERY' )
order by
	STAFF1.LAST_NAME ,
	STAFF1.FIRST_NAME ,
	STAFF1.STAFF_ID
	
}
	OCUOICHN_FIND_RGHEARINGTYPE {
select
	REF_CODE.DESCRIPTION DESCRIPTION ,
	REF_CODE.CODE CODE
from
	REFERENCE_CODES REF_CODE
where
	domain = 'OIC_HEAR'
	and (''= 'ENTER-QUERY'
		or (ACTIVE_FLAG = 'Y'
			and EXPIRED_DATE is null ) )
order by
	LIST_SEQ
}
	OCUOICHN_FIND_RGINTERNALLOCATIONS {
SELECT
	C.DESCRIPTION ,
	C.INTERNAL_LOCATION_ID
FROM
	INT_LOC_USAGE_LOCATIONS A ,
	INTERNAL_LOCATION_USAGES B ,
	AGENCY_INTERNAL_LOCATIONS C
WHERE
	A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID
	AND B.INTERNAL_LOCATION_USAGE = 'OIC'
	AND B.AGY_LOC_ID IN (
	SELECT
		AGY_LOC_ID
	FROM
		CASELOAD_AGENCY_LOCATIONS
	WHERE
		CASELOAD_ID = :CASELOADID )
	AND A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID
	AND ( (C.ACTIVE_FLAG = 'Y'
		AND C.DEACTIVATE_DATE IS NULL )
	OR ( :MODE = 'ENTER-QUERY' ) )
	AND NOT EXISTS (
	SELECT
		1
	FROM
		INT_LOC_USAGE_LOCATIONS
	WHERE
		PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID )
ORDER BY
	C.LIST_SEQ
	}
OCUOICHN_OICHEAR_FIND_OIC_HEARINGS {
 SELECT OIC_HEARING_ID ,OIC_HEARING_TYPE ,OIC_INCIDENT_ID ,SCHEDULE_DATE ,SCHEDULE_TIME ,HEARING_DATE ,HEARING_TIME ,HEARING_STAFF_ID ,VISIT_JUSTICE_TEXT ,COMMENT_TEXT ,TAPE_NUMBER ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,INTERNAL_LOCATION_ID ,REPRESENTATIVE_TEXT ,EVENT_ID ,EVENT_STATUS ,SEAL_FLAG   FROM OIC_HEARINGS  /* where OIC_HEARING_ID = ?  */
}

OCUOICHN_OICHEAR_INSERT_OIC_HEARINGS {
insert into OIC_HEARINGS(OIC_HEARING_ID , OIC_HEARING_TYPE , OIC_INCIDENT_ID , SCHEDULE_DATE , SCHEDULE_TIME , HEARING_DATE , HEARING_TIME , HEARING_STAFF_ID , VISIT_JUSTICE_TEXT , COMMENT_TEXT , TAPE_NUMBER , CREATE_DATETIME , CREATE_USER_ID , INTERNAL_LOCATION_ID , REPRESENTATIVE_TEXT , EVENT_ID , EVENT_STATUS , SEAL_FLAG , modify_datetime) values(NEXTVAL('OIC_HEARING_ID') , :oicHearingType , :oicIncidentId , :scheduleDate , :scheduleTime , :hearingDate , :hearingTime , :hearingStaffId , :visitJusticeText , :commentText , :tapeNumber , CURRENT_TIMESTAMP, :createUserId , :internalLocationId , :representativeText , NEXTVAL('EVENT_ID'), :eventStatus , :sealFlag , null )
}

OCUOICHN_OICHEAR_UPDATE_OIC_HEARINGS {
update
	OIC_HEARINGS
set
	OIC_HEARING_TYPE =:oicHearingType ,
	OIC_INCIDENT_ID =:oicIncidentId ,
	SCHEDULE_DATE =:scheduleDate ,
	SCHEDULE_TIME =:scheduleTime ,
	HEARING_DATE =:hearingDate ,
	HEARING_TIME =:hearingTime ,
	HEARING_STAFF_ID =:hearingStaffId ,
	VISIT_JUSTICE_TEXT =:visitJusticeText ,
	COMMENT_TEXT =:commentText ,
	TAPE_NUMBER =:tapeNumber ,
	MODIFY_DATETIME = current_timestamp ,
	MODIFY_USER_ID =:modifyUserId ,
	INTERNAL_LOCATION_ID =:internalLocationId ,
	REPRESENTATIVE_TEXT =:representativeText ,
	EVENT_ID =:eventId ,
	EVENT_STATUS =:eventStatus ,
	SEAL_FLAG =:sealFlag
where
	OIC_HEARING_ID =:oicHearingId 
}

	OCUOICHN_OICHEAR_DELETE_OIC_HEARINGS { 
DELETE FROM OIC_HEARINGS  WHERE OIC_HEARING_ID  = :oicHearingId 
}

OCUOICHN_OICHEARNOTI_FIND_OIC_HEARING_NOTICES {
 SELECT OIC_HEARING_ID ,OIC_NOTICE_SEQ ,DELIVERY_DATE ,DELIVERY_TIME ,DELIVERY_STAFF_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,COMMENT_TEXT ,SEAL_FLAG   FROM OIC_HEARING_NOTICES  /* where OIC_HEARING_ID = ? and OIC_NOTICE_SEQ = ?  */
}

OCUOICHN_OICHEARNOTI_INSERT_OIC_HEARING_NOTICES {
insert into OIC_HEARING_NOTICES(OIC_HEARING_ID , OIC_NOTICE_SEQ , DELIVERY_DATE , DELIVERY_TIME , DELIVERY_STAFF_ID , CREATE_DATETIME , CREATE_USER_ID , COMMENT_TEXT , SEAL_FLAG , modify_datetime ) values(:oicHearingId , NEXTVAL('NOTIFICATION_ID') , :deliveryDate , :deliveryTime , :deliveryStaffId , CURRENT_TIMESTAMP , :createUserId , :commentText , :sealFlag , null )
}

OCUOICHN_OICHEARNOTI_UPDATE_OIC_HEARING_NOTICES {
update
	OIC_HEARING_NOTICES
set
	DELIVERY_DATE =:deliveryDate ,
	DELIVERY_TIME =:deliveryTime ,
	DELIVERY_STAFF_ID =:deliveryStaffId ,
	COMMENT_TEXT =:commentText,
	MODIFY_DATETIME =CURRENT_TIMESTAMP,
	MODIFY_USER_ID =:modifyUserId
where
	OIC_HEARING_ID =:oicHearingId
	and OIC_NOTICE_SEQ =:oicNoticeSeq
}

	OCUOICHN_OICHEARNOTI_DELETE_OIC_HEARING_NOTICES { 
DELETE FROM OIC_HEARING_NOTICES WHERE OIC_HEARING_ID  = :oicHearingId and OIC_NOTICE_SEQ = :oicNoticeSeq 
}


	OCUOICHN_OIC_HEAR_ONCHECKDELETEMASTER_OIC_HEAR_NOTI_CUR {
 SELECT 1 FROM OIC_HEARING_NOTICES O WHERE O.OIC_HEARING_ID = :OICHEARINGID
}

OCUOICHN_OIC_HEAR_PREINSERT_GET_EVENT_ID_CUR {
 SELECT NEXTVAL('EVENT_ID') FROM DUAL
}
OCUOICHN_FIND_MAXOICHEARID {
SELECT coalesce (MAX(OIC_HEARING_ID),0)+1 FROM OIC_HEARINGS
}
OCUOICHN_FIND_MAXOICNOTICESEQ {
SELECT coalesce (MAX(OIC_NOTICE_SEQ),0)+1 FROM OIC_HEARING_NOTICES
}
 OCUOICHN_CHECK_SCHEDULE_CONFLICT {
 select
	count(*)
from
	V_OFFENDER_ALL_SCHEDULES_2
where
	OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
	and EVENT_STATUS = 'SCH'
	and :EVENT_DATE::timestamp >= EVENT_DATE 
	and :EVENT_DATE::timestamp <= COALESCE(RETURN_DATE, EVENT_DATE)
    }
    
    
OCUOICHN_GET_OFFENDER_BOOK_ID{
select  offender_book_id  from agency_incident_parties where oic_incident_id=:oicIncidentId
}
OCUOICHN_GET_INCIDENTID {
select  oic_incident_id  from agency_incident_parties where offender_book_id=:offenderBookId and oic_incident_id is not null
} 
 OCUOICHN_NON_ASSOCATION_VISIT_SCHEDULE{
 select * from OIC_HEARINGS where oic_incident_id=:oicIncidentId and
hearing_date=:hearing_date::timestamp  and internal_location_id=:internal_location_id
 } 
 OCUOICHN_NON_ASSOCATION_VISIT_SCHEDULE_OFFENDER_DETAILS
 {
 select * from offenders where offender_id =
(select offender_id from offender_bookings where offender_book_id =
(select  offender_book_id  from agency_incident_parties where oic_incident_id=:oicIncidentId ))
 }
 OCUOICHN_INDIVIDUAL_NON_ASSOCATION_OFFENDERS_LIST{
SELECT *  FROM OFFENDER_NON_ASSOCIATIONS ona
	where ona.offender_book_id=:offenderBookId and ona.NS_OFFENDER_ID in 
	(SELECT  nad.NS_OFFENDER_ID FROM OFFENDER_NA_DETAILS nad 
	where  nad.offender_book_id=:offenderBookId and 
	current_date < coalesce(nad.ns_expiry_date, current_date +1)  and current_date >= nad.ns_effective_date) 
and ona.recip_ns_reason_code in (SELECT
int_loc_profile_code
FROM
agy_int_loc_profiles
WHERE
internal_location_id = :internalLocationId
AND int_loc_profile_type = 'NON_ASSO_TYP')
 }
 