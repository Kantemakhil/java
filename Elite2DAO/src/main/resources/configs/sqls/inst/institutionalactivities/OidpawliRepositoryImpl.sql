
OIDPAWLI_FIND_RGESTABLISHMENT {
	select
	  al.description,
	  al.agy_loc_id code
	from agency_locations al
	where al.agency_location_type = 'INST'
	and al.agy_loc_id not in ('IN', 'OUT', 'TRN')
	and
	(
	  al.active_flag = 'Y'
	)
	and exists 
	(
	  select
	    ca.agy_loc_id
	  
	  from caseload_agency_locations ca
	  
	  where ca.caseload_id = :agyLocId
	  
	  and ca.agy_loc_id = al.agy_loc_id
	)


	and exists 

	(
	  select
	    agy_loc_id
	  
	  from course_activities ca
	  
	  where ca.course_activity_type = 'IA' 
	  
	  and ca.agy_loc_id = al.agy_loc_id
	)


	order by al.list_seq,
	al.description


}

OIDPAWLI_FIND_RGSERVICES {
 	SELECT DISTINCT VCP.LIST_SEQ , VCP.SERVICE DESCRIPTION , VCP.PROGRAM_ID ,VCP.PROGRAM_CODE code   FROM V_PRISON_ACTIVITIES VCP  WHERE VCP.AGY_LOC_ID = :AGYLOCID    AND (ACTIVE_FLAG = 'Y')  ORDER BY LIST_SEQ , DESCRIPTION  /*SELECT PS.DESCRIPTION , PS.PROGRAM_ID , PS.PROGRAM_CODE   FROM PROGRAM_SERVICES PS  WHERE PROGRAM_CLASS = 'PRG'    AND (( ACTIVE_FLAG = 'Y'     AND EXPIRY_DATE IS NULL  ) OR ( ::MODE = 'ENTER-QUERY'  ) )    AND PROGRAM_CATEGORY = 'INST_ACT' ORDER BY LIST_SEQ , DESCRIPTION*/
}

OIDPAWLI_FIND_RGREASON {
 	SELECT
    description,
    code
FROM
    reference_codes
WHERE
        domain = 'PS_REJ_RSN'
    AND (
        active_flag = 'Y'
    )
ORDER BY
    list_seq,
    description,
    code
}

OIDPAWLI_FIND_RGPRIORITY { 	
	SELECT description, code,parent_code FROM reference_codes WHERE domain = 'PS_PRIORITY' AND (active_flag = 'Y')ORDER BY list_seq, description, code
}


OIDPAWLI_FIND_RGDECISION {
 	 SELECT DESCRIPTION , CODE ,
case when
(SELECT count(*) FROM REFERENCE_CODES WHERE ref.DOMAIN = 'PS_ACT_DEC' AND (ref.ACTIVE_FLAG ='Y' OR :systemMode= 'ENTER-QUERY' ) AND (:systemMode = 'ENTER-QUERY' OR (:systemMode= 'NORMAL' AND '' IN ('NEW' ,'INSERT' ) AND ref.CODE <> 'ALLOC' ) OR ref.CODE NOT IN ('ALL' ,'PEN' , 'ALLOC' ) ) )>0 then 'Y' else 'N' end SEAL_FLAG
FROM REFERENCE_CODES ref WHERE domain = 'PS_ACT_DEC' ORDER BY LIST_SEQ , DESCRIPTION , CODE

}

OIDPAWLI_FIND_RGACTDESC {
SELECT
    DESCRIPTION,
    (crs_acty_id)::TEXT code,
    schedule_start_date,
    schedule_end_date
FROM
    course_activities
WHERE
        program_id = :programId
    AND
        agy_loc_id = :agyLocId
ORDER BY
    schedule_start_date,
    schedule_end_date ASC

}

OIDPAWLI_GET_FUTURE_DAYS{
select
	round((To_Date(:offenderStartDate::text, 'DD/MM/YYYY'::text) - SYSDATE()::date)::int , 0::int)
from
	dual
}

OIDPAWLI_WAITLIST_FIND_OFFENDER_PROGRAM_PROFILES {
select
	*
from
	offender_program_profiles
where
	PROGRAM_ID = :programId
	and AGY_LOC_ID = :agyLocId  
	and(offender_program_status = 'WAIT' 
		or (waitlist_decision_code != 'APP'
			and (extract(days
		from
			(localtimestamp - reject_date)) < 30) ) or  offender_start_date is null  )
order by
	case
		when waitlist_decision_code = 'APP' then 1
		when waitlist_decision_code = 'PEN' then 2
		when waitlist_decision_code = 'REJ' then 3
	end ,
	referral_date desc,
	oms_miscellaneous_getdesccode('PS_PRIORITY',
	referral_priority) desc,
	osinames_getoffname(osinames_getoffid(offender_book_id) ) asc
}

OIDPAWLI_GET_REJECT_REASON{
SELECT oms_miscellaneous_getdesccode('PS_REJ_RSN',:rejectCode) FROM DUAL

}

OIDPAWLI_WAITLIST_INSERT_OFFENDER_PROGRAM_PROFILES {
insert into OFFENDER_PROGRAM_PROFILES(OFF_PRGREF_ID , OFFENDER_BOOK_ID, PROGRAM_ID, OFFENDER_START_DATE, OFFENDER_PROGRAM_STATUS, CRS_ACTY_ID, REFERRAL_PRIORITY, REFERRAL_DATE, REFERRAL_COMMENT_TEXT, OFFENDER_END_REASON, AGREED_TRAVEL_FARE, AGREED_TRAVEL_HOUR, OFFENDER_END_COMMENT_TEXT, REJECT_DATE, WAITLIST_DECISION_CODE, REFERRAL_STAFF_ID, OFFENDER_END_DATE, CREDIT_WORK_HOURS, CREDIT_OTHER_HOURS, SUSPENDED_FLAG, REJECT_REASON_CODE, AGY_LOC_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, REVIEWED_BY, OFFENDER_SENT_CONDITION_ID, SENTENCE_SEQ, HOLIDAY_FLAG, START_SESSION_NO, PARENT_OFF_PRGREF_ID, OFFENDER_PRG_OBLIGATION_ID, PROGRAM_OFF_PRGREF_ID, PROFILE_CLASS, COMPLETION_DATE, NEEDED_FLAG, COMMENT_TEXT, EARLY_END_REASON, OFFENDER_ID, MEDICAL_RECORD_SEQ, PARAMETER_1, SEAL_FLAG) values(:offPrgrefId, :offenderBookId, :programId, :offenderStartDate, :offenderProgramStatus, :crsActyId, :referralPriority, :referralDate, :referralCommentText, :offenderEndReason, :agreedTravelFare, :agreedTravelHour, :offenderEndCommentText, :rejectDate, :waitlistDecisionCode, :referralStaffId, :offenderEndDate, :creditWorkHours, :creditOtherHours, :suspendedFlag, :rejectReasonCode, :agyLocId, CURRENT_TIMESTAMP, :createUserId, null , :reviewedBy, :offenderSentConditionId, :sentenceSeq, :holidayFlag, :startSessionNo, :parentOffPrgrefId, :offenderPrgObligationId, :programOffPrgrefId, :profileClass, :completionDate, :neededFlag, :commentText, :earlyEndReason, :offenderId, :medicalRecordSeq, :parameter1, :sealFlag)
}

OIDPAWLI_WAITLIST_UPDATE_OFFENDER_PROGRAM_PROFILES {
	UPDATE OFFENDER_PROGRAM_PROFILES 
    SET  OFFENDER_BOOK_ID=:offenderBookId, PROGRAM_ID=:programId, OFFENDER_START_DATE=:offenderStartDate, OFFENDER_PROGRAM_STATUS=:offenderProgramStatus, CRS_ACTY_ID=:crsActyId, REFERRAL_PRIORITY=:referralPriority, REFERRAL_DATE=:referralDate, REFERRAL_COMMENT_TEXT=:referralCommentText, OFFENDER_END_REASON=:offenderEndReason
    , AGREED_TRAVEL_FARE=:agreedTravelFare, AGREED_TRAVEL_HOUR=:agreedTravelHour, OFFENDER_END_COMMENT_TEXT=:offenderEndCommentText, REJECT_DATE=:rejectDate, WAITLIST_DECISION_CODE=:waitlistDecisionCode, REFERRAL_STAFF_ID=:referralStaffId, OFFENDER_END_DATE=:offenderEndDate, CREDIT_WORK_HOURS=:creditWorkHours, CREDIT_OTHER_HOURS=:creditOtherHours, SUSPENDED_FLAG=:suspendedFlag, REJECT_REASON_CODE=:rejectReasonCode, AGY_LOC_ID=:agyLocId, MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId
    , REVIEWED_BY=:reviewedBy, OFFENDER_SENT_CONDITION_ID=:offenderSentConditionId, SENTENCE_SEQ=:sentenceSeq, HOLIDAY_FLAG=:holidayFlag, START_SESSION_NO=:startSessionNo, PARENT_OFF_PRGREF_ID=:parentOffPrgrefId, OFFENDER_PRG_OBLIGATION_ID=:offenderPrgObligationId, PROGRAM_OFF_PRGREF_ID=:programOffPrgrefId, PROFILE_CLASS=:profileClass, COMPLETION_DATE=:completionDate, NEEDED_FLAG=:neededFlag, COMMENT_TEXT=:commentText, EARLY_END_REASON=:earlyEndReason, OFFENDER_ID=:offenderId, MEDICAL_RECORD_SEQ=:medicalRecordSeq, PARAMETER_1=:parameter1, SEAL_FLAG=:sealFlag
    WHERE OFF_PRGREF_ID=:offPrgrefId  
}

OIDPAWLI_WAITLIST_DELETE_OFFENDER_PROGRAM_PROFILES { 
	DELETE FROM OFFENDER_PROGRAM_PROFILES 
	WHERE OFF_PRGREF_ID = :offPrgrefId
}

OIDPAWLI_OFF_PRGREF_ID {
	SELECT NEXTVAL('off_prgref_id') FROM DUAL;
}

OIDPAWLI_CHECK_CONFLICT {
	SELECT ROOT_OFFENDER_ID, OFFENDER_ID FROM V_HEADER_BLOCK_FN(:USERID) WHERE OFFENDER_BOOK_ID = :offenderBookId
}

OIDPAWLI_CHECK_ASSIGN_CONFLICT_LV_DATE {
	select
	MIN(OFFENDER_START_DATE)
from
	OFFENDER_PROGRAM_PROFILES
where
	OFFENDER_BOOK_ID in (
	select
		distinct NS_OFFENDER_BOOK_ID
	from
		OFFENDER_NA_DETAILS ONA
	where
		OFFENDER_BOOK_ID =:offenderBookId
		and (current_timestamp) between ONA.NS_EFFECTIVE_DATE and coalesce (ONA.NS_EXPIRY_DATE,
		TO_DATE('01/01/3000', 'DD/MM/YYYY')))
	and OFFENDER_END_DATE is null
	and PROGRAM_ID =:programId
}

OIDPAWLI_CHECK_ASSIGN_CONFLICT_LV_DATE_ONE {
	select
		MIN(OFFENDER_START_DATE)
	from
		OFFENDER_PROGRAM_PROFILES
	where
		OFFENDER_BOOK_ID in (
		select
			distinct NS_OFFENDER_BOOK_ID
		from
			OFFENDER_NA_DETAILS ONA
		where
			OFFENDER_BOOK_ID =:offenderBookId
			and (current_timestamp) between ONA.NS_EFFECTIVE_DATE and coalesce (ONA.NS_EXPIRY_DATE,
			TO_DATE('01/01/3000', 'DD/MM/YYYY')))
		and PROGRAM_ID =:programId
		and OFFENDER_START_DATE between :offenderStartDate and :offenderEndDate
}


CKECK_CONFLICT_GET_OFF_DETAILS{
SELECT o.offender_id_display offender_id_display,
			    o.last_name           last_name,
			    o.first_name          first_name
           FROM offenders o
          WHERE o.offender_id = :offenderId
}


OIDPAWLI_GET_OFFENDER_ID{
SELECT  offender_id
                 FROM V_HEADER_BLOCK_FN(:USERID)
                      WHERE offender_book_id = :offenderBookId

}

CHECK_CONFLICT_GET_NA_PRG_SRV {
  select
	off.offender_id_display offender_id_display,
	off.last_name last_name,
	off.first_name first_name,
	off.offender_id offender_id,
	off.offender_id_display offender_id_display
from
	offender_bookings bkg,
	offender_na_details ona,
	offenders off
where
	bkg.offender_id = off.offender_id
	and bkg.root_offender_id = ona.ns_offender_id
	and ((bkg.active_flag = 'Y')
		or
                (bkg.active_flag = 'N'
			and
                bkg.offender_book_id =
                (
			select
				MAX(ob2.offender_book_id)
			from
				offender_bookings ob2
			where
				ob2.root_offender_id = bkg.root_offender_id
				and not exists
                    (
				select
					'X'
				from
					offender_bookings ob3
				where
					ob3.root_offender_id = bkg.root_offender_id
					and ob3.active_flag = 'Y'))))
	and ona.offender_id =
                (
	select
		distinct root_offender_id
	from
		offenders
	where
		offender_id = :offenderId)
	and (current_timestamp) between ona.ns_effective_date and
                coalesce (ona.ns_expiry_date,
	to_date('01/01/3000', 'DD/MM/YYYY'))
	and exists
          (
	select
		'1'
	from
		v_offender_program_profiles opp
	where
		opp.crs_acty_id = :crsActyId
		and opp.offender_program_status = 'ALLOC'
		and (:offenderStartDate::date) between (offender_start_date) and 
                    coalesce ((offender_end_date),
		(:offenderStartDate::date))
			and off.root_offender_id =
                        (
			select
				distinct root_offender_id
			from
				offenders
			where
				offender_id = opp.offender_id))               
}



CHECK_CONFLICT_GET_STG_NA_PRG_SRV {
select
		O.OFFENDER_ID_DISPLAY  offender_id_display,
		O.LAST_NAME last_name,
		O.FIRST_NAME first_name,
		OB.OFFENDER_BOOK_ID
from
		OFFENDERS O,
		OFFENDER_BOOKINGS OB
where
		OB.OFFENDER_ID = O.OFFENDER_ID
	and OB.offender_book_id in(
	select
		vopp.offender_book_id
	from
		v_offender_program_profiles vopp
	where
		vopp.crs_acty_id = :crsActyId
		and vopp.offender_program_status = 'ALLOC'
		and vopp.offender_book_id in (
		select
			distinct offender_book_id
		from
			OFFENDER_STG_AFFILIATIONS osa
		where
			osa.active_flag = 'Y'
			and osa.offender_book_id <> :offenderBookId
			and osa.stg_id in (
			select
				sr.related_stg_id
			from
				STG_RELATIONSHIPS sr
			where
				active_flag = 'Y'
				and sr.stg_id in (
				select
					osa1.stg_id
				from
					OFFENDER_STG_AFFILIATIONS osa1
				where
					osa1.offender_book_id = :offenderBookId
					and active_flag = 'Y')))
		)
}
CKECK_CONFLICT_GET_OFF_DETAILS_OFFENDER_BOOK_ID{
select
	o.offender_id_display offender_id_display,
			    o.last_name last_name,
			    o.first_name first_name
from
	offenders o
where
	o.offender_id = (
	select
		ob.offender_id
	from
		offender_bookings ob
	where
		ob.offender_book_id = :offenderBookId)
}
OIDPAWLI_CKECK_CONFLICT_BY_INDIVIDUAL
{
select o.offender_id_display offender_id_display,o.offender_id offender_id,o.last_name last_name,o.first_name first_name from offenders o 
where o.offender_id in (select ona.ns_offender_id  from offender_na_details ona where ona.offender_id =:offenderId )  and o.offender_id in (:offenderIdsList)
}
OIDPAWLI_CKECK_CONFLICT_BY_GANG
{
select
		O.OFFENDER_ID_DISPLAY  offender_id_display,
		O.offender_id offender_id,
		O.LAST_NAME last_name,
		O.FIRST_NAME first_name,
		OB.OFFENDER_BOOK_ID
from
		OFFENDERS O,
		OFFENDER_BOOKINGS OB
where
		OB.OFFENDER_ID = O.OFFENDER_ID
	and OB.offender_book_id in(
		select
			distinct offender_book_id
		from
			OFFENDER_STG_AFFILIATIONS osa
		where
			osa.active_flag = 'Y'
			and osa.offender_book_id <> :offenderBookId
			and osa.stg_id in (
			select
				sr.related_stg_id
			from
				STG_RELATIONSHIPS sr
			where
				active_flag = 'Y'
				and sr.stg_id in (
				select
					osa1.stg_id
				from
					OFFENDER_STG_AFFILIATIONS osa1
				where
					osa1.offender_book_id = :offenderBookId
					and active_flag = 'Y'))) and O.offender_id in (:offendersList)
}
