BACKDATE_ATTENDANCES_INT_ID_CUR{
 
SELECT internal_location_id
   FROM course_activities
        WHERE crs_acty_id = :P_CRS_ACTY_ID
        
   }
   
BACKDATE_ATTENDANCES_GET_COURSE_SCHEDULES{
 select
	start_time,
	end_time,
	schedule_date,
	crs_sch_id
from
	course_schedules
where
	crs_acty_id = :P_CRS_ACTY_ID
	and schedule_date >= :P_OFFENDER_START_DATE
	and schedule_date <= current_timestamp
  }
   
   
BACKDATE_ATTENDANCES_GET_EVENT_ID {
SELECT NEXTVAL('event_id');
}        
          
BACKDATE_ATTENDANCES_INSERT_OFFENDER_COURSE_ATTENDANCES{
insert into offender_course_attendances (event_id, offender_book_id, event_date, start_time, end_time, event_status, off_prgref_id, event_class, crs_acty_id, event_type, event_sub_type, in_time, out_time, reference_id, crs_sch_id, agy_loc_id, to_internal_location_id , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:eventId, :offenderBookId, :eventDate, :startTime, :endTime, 'SCH', :offPrgrefId, 'INT_MOV', :crsActyId, 'INST_ACT', 'IA', :startTime, :endTime, :crsSchId, :crsSchId, :agyLocId, :toInternalLocationId ,'OMS_OWNER',CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
  }   
  
CURSOR_C1_SELECT{
select
	COUNT(*)
from
	offender_program_profiles
where
	agy_loc_id = :agyLocId
	and offender_book_id = :offenderBookId
	and crs_acty_id = :crsActyId
	and waitlist_decision_code = 'PEN'
	and off_prgref_id <> coalesce (:offPrgrefId,
	0)

}

CURSOR_C2_SELECT{
select
	COUNT(*)
from
	offender_program_profiles
where
	agy_loc_id = :agyLocId
	and offender_book_id = :offenderBookId
	and crs_acty_id = :crsActyId
	and waitlist_decision_code = 'APP'
	and offender_program_status = 'WAIT'
	and off_prgref_id <> coalesce (:offPrgrefId,
	0)

}

CURSOR_C3_SELECT{
select
	COUNT (*)
from
	offender_program_profiles
where
	agy_loc_id = :agyLocId
	and offender_book_id = :offenderBookId
	and crs_acty_id = :crsActyId
	and offender_program_status = 'ALLOC'
	and off_prgref_id <> coalesce (:offPrgrefId,
	0)

}
UPDATE_OFFENDER_PROGRAM_PROFILES{
update offender_program_profiles set offender_end_date =:P_SCHEDULE_END_DATE, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where crs_acty_id =:P_CRS_ACTY_ID and offender_program_status = 'ALLOC' and offender_end_date >=:P_SCHEDULE_END_DATE
 }
 C_LOCK_SELECT{
SELECT * FROM offender_program_profiles WHERE ( ( coalesce (offender_end_date::timestamp::text,'')='' OR offender_end_date > :endDate) AND offender_program_status NOT IN ( 'WAIT', 'PLAN' ) ) AND offender_book_id = :offenderBookId 
 }

UPDATE_OFF_PRG_PRO{
update offender_program_profiles set suspended_flag = 'Y', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where OFF_PRGREF_ID = :OFF_PRGREF_ID
}
  REMOVE_COURSE_SCHEDULES_DELETE{
DELETE FROM course_schedules WHERE crs_acty_id = :crsActyId AND schedule_date > :scheduleDate
}
   END_WAITLIST_AND_ALLOCATIONS_SELECT_OFFENDER_PROGRAM_STATUS{
 


   SELECT opp.offender_program_status
           FROM offender_program_profiles opp
          WHERE (   (    (   coalesce (opp.offender_end_date::timestamp::text,'')=''  OR opp.offender_end_date > current_timestamp 
                             )      
                            
                             AND opp.offender_program_status NOT IN ('WAIT', 'PLAN')
                        )
                     OR (    (coalesce (opp.reject_date::timestamp::text,'')=''  OR opp.reject_date > current_timestamp)
                         AND opp.offender_program_status = 'WAIT'
                        )
                    )
                AND OPP.OFFENDER_BOOK_ID = :p_offender_book_id
                
                AND   EXISTS ( SELECT PS.PROGRAM_ID
                                                FROM PROGRAM_SERVICES PS
                                               
						WHERE (coalesce (PS.Program_Category::text,'')='' OR PS.PROGRAM_CATEGORY <> 'PWS') 
                                                AND OPP.PROGRAM_ID = PS.PROGRAM_ID)

 }   
     
  END_WAITLIST_AND_ALLOCATIONS_UPDATE_ONE{   
  update offender_program_profiles set offender_end_date = :p_date, offender_end_reason = :p_end_reason, offender_program_status = 'END', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where current of c_lock
  }           
           
 END_WAITLIST_AND_ALLOCATIONS_UPDATE_SECOND{
 update offender_program_profiles set offender_end_date = :p_date, reject_date = CURRENT_TIMESTAMP, waitlist_decision_code = 'REJ', reject_reason_code = :p_end_reason, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where current of c_lock
             
   }  
   GET_AGY_LOC_ID_DESCRIPTION {
SELECT DESCRIPTION FROM AGENCY_LOCATIONS  WHERE AGY_LOC_ID = :AGY_LOC_ID
}
GET_PROGRAM_ID_DESCRIPTION {
 SELECT DESCRIPTION FROM PROGRAM_SERVICES WHERE PROGRAM_ID = :PROGRAM_ID
 
 GET_CRS_ACTY_ID_DESCRIPTION {         
          SELECT DESCRIPTION
           FROM COURSE_ACTIVITIES
}
GET_OFFENDER_END_REASON_DESCRIPTION {   
SELECT DESCRIPTION FROM OFFENDER_PROGRAM_PROFILES WHERE OFFENDER_END_REASON = :OFFENDER_END_REASON
}

DISPLAY_WAITLIST_DETAILS_DISP_ACTY_DESC {
	SELECT DESCRIPTION
           FROM COURSE_ACTIVITIES
          WHERE CRS_ACTY_ID = :P_CRS_ACTY_ID

}

DISPLAY_WAITLIST_DETAILS_GET_CAPACITY {
 select
	CAPACITY
from
	COURSE_ACTIVITIES
where
	CRS_ACTY_ID = :P_COURSE_ACTIVITY_ID
}

DISPLAY_WAITLIST_DETAILS_GET_COUNT {
 select
	COUNT(*)
from
	OFFENDER_PROGRAM_PROFILES
where
	CRS_ACTY_ID = :P_COURSE_ACTIVITY_ID
	and OFFENDER_PROGRAM_STATUS = 'ALLOC'

}

DISPLAY_WAITLIST_DETAILS_GETDESCCODE {
	SELECT DESCRIPTION
        FROM REFERENCE_CODES
       WHERE DOMAIN = :P_DOMAIN
         AND CODE = :P_REFCODE

}

CHK_ACTY_END_DATE{
SELECT
    'x'
FROM
    offender_program_profiles
WHERE
    crs_acty_id = :p_crs_acty_id
    AND offender_program_status = 'ALLOC'
    AND coalesce(offender_end_date, '31-DEC-2382') > :p_schedule_end_date
    limit 1
}

CRS_ACTY_ID{
 SELECT NEXTVAL('crs_acty_id')
}
CHK_WAITLIST{
SELECT COUNT (*) FROM OFFENDER_PROGRAM_PROFILES WHERE AGY_LOC_ID =:P_AGY_LOC_ID AND PROGRAM_ID =:P_PROGRAM_ID AND 
OFFENDER_BOOK_ID =:P_OFFENDER_BOOK_ID AND CRS_ACTY_ID IS NULL AND WAITLIST_DECISION_CODE <> 'REJ'
}
GET_ADMISSION_DATE{
 SELECT booking_begin_date FROM offender_bookings WHERE offender_book_id =:p_offender_book_id
}
GET_BOOKING_DATE{
 SELECT booking_begin_date FROM offender_bookings  WHERE offender_book_id =:p_offender_book_id
}
CHK_ALLOCATED{
select
	COUNT (*)
from
	offender_program_profiles
where
	crs_acty_id = :P_CRS_ACTY_ID
	and offender_book_id = :P_OFFENDER_BOOK_ID
	and offender_program_status = 'ALLOC' and offender_start_date is not null
	and coalesce (offender_end_date,
	'31-DEC-2382') > :P_OFFENDER_START_DATE

}
CHK_ACTY_END_DATE{
SELECT 'x' FROM offender_program_profiles WHERE crs_acty_id =:p_crs_acty_id AND offender_program_status = 'ALLOC' AND NVL (offender_end_date, '31-DEC-2382') >:p_schedule_end_date AND ROWNUM = 1
}

GET_COURSE_ACTIVITY{
SELECT description,crs_acty_id,internal_location_id  FROM course_activities
WHERE program_id = :programId AND agy_loc_id = :agyLocId AND active_flag = 'Y'
AND upper(description) LIKE upper(:activity)
}

GET_SERVICES{
SELECT DESCRIPTION FROM PROGRAM_SERVICES WHERE PROGRAM_ID = :P_PROGRAM_ID
}

TAG_PRISION_BULK_UPDATE{
update OFFENDER_PROGRAM_PROFILES set OFFENDER_PROGRAM_STATUS = 'ALLOC', modify_datetime = current_timestamp , modify_user_id =:modifyUserId where CRS_ACTY_ID =:P_CRS_ACTY_ID and OFFENDER_BOOK_ID =:P_OFFENDER_BOOK_ID
}


