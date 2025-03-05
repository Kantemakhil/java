
OIMSTRIP_FIND_RGTRIPTYPE {
 	SELECT code ,description   FROM reference_codes WHERE domain = 'TRIP_TYPE'   AND active_flag = 'Y' ORDER BY List_seq
}

OIMSTRIP_FIND_RGSTAFFID {
 	 select
	STAFF_ID code ,
	LAST_NAME || ' , ' || FIRST_NAME description,
	personnel_type assignment_type,
	USER_ID
from
	STAFF_MEMBERS
where
	STATUS = 'ACTIVE' 
order by
	1 asc

}

OIMSTRIP_TRIPS_FIND_TRIPS {
 	SELECT TRIP_CODE, DESCRIPTION, TRIP_TYPE, LIST_SEQ, ACTIVE_FLAG, EXPIRY_DATE, create_datetime, create_user_id ,modify_datetime FROM TRIPS order by active_flag desc, LIST_SEQ asc,modify_datetime desc
}
OIMSTRIP_TRIPS_INSERT_TRIPS {
	INSERT INTO TRIPS(TRIP_CODE, DESCRIPTION, TRIP_TYPE, LIST_SEQ, ACTIVE_FLAG, EXPIRY_DATE,create_datetime, create_user_id,modify_datetime) VALUES(:tripCode, :description, :tripType, :listSeq, :activeFlag, :expiryDate, current_timestamp, :createUserId,NULL)
}

OIMSTRIP_TRIPS_UPDATE_TRIPS {
	update trips set TRIP_TYPE=:tripType, DESCRIPTION=:description, LIST_SEQ=:listSeq, ACTIVE_FLAG=:activeFlag, EXPIRY_DATE=:expiryDate, modify_user_id=:modifyUserId, modify_datetime = current_timestamp where TRIP_CODE=:tripCode
}

OIMSTRIP_SCHEDULEDTRIPS_FIND_SCHEDULED_TRIPS_ONE {
 	--SELECT SCHEDULED_TRIP_ID, DEPARTURE_DATE, COMPLETION_DATE, EST_COMPLETION_TIME, ROUTE_NAME, CANCEL_FLAG, CANCEL_BY, CANCEL_DATE FROM SCHEDULED_TRIPS where TRIP_CODE=:tripCode
 	select st.*, (SELECT action_type
           FROM scheduled_trip_details
          WHERE scheduled_trip_id = st.scheduled_trip_id 
          ORDER BY create_datetime DESC) as v_action,
         (select count(*) 
		from scheduled_trip_details
		where scheduled_trip_id in (select scheduled_trip_id
	  					   from scheduled_trips
							   where trip_code = st.trip_code 
						   		 and departure_date > st.departure_date)) as v_num from SCHEDULED_TRIPS st where TRIP_CODE=:tripCode order by departure_date asc

}
OIMSTRIP_SCHEDULEDTRIPS_UPDATE_SCHEDULED_TRIPS {
	UPDATE SCHEDULED_TRIPS set DEPARTURE_DATE=:departureDate, COMPLETION_DATE=:completionDate, EST_COMPLETION_TIME=:estCompletionTime, ROUTE_NAME=:routeName, CANCEL_FLAG=:cancelFlag, CANCEL_BY=:cancelBy, CANCEL_DATE=:cancelDate, modify_user_id=:modifyUserId, modify_datetime=current_timestamp where SCHEDULED_TRIP_ID=:scheduledTripId   
}

OIMSTRIP_SCHEDULEDTRIPASSIGNMENTS_FIND_SCHEDULED_TRIP_ASSIGNMENTS {
 select
	sta.*,sv.*
from
	scheduled_trip_assignments sta,
	select_vehicles_v1 sv
where
	sta.scheduled_trip_id = :scheduledTripId
	and sta.assignment_type = 'VEHICLE' and sta.assigned_id = sv.vehicle_id  order by CREATE_DATETIME asc
}

OIMSTRIP_SCHEDULEDTRIPASSIGNMENTS_INSERT_SCHEDULED_TRIP_ASSIGNMENTS {
	INSERT INTO SCHEDULED_TRIP_ASSIGNMENTS(scheduled_trip_id, assignment_type, assigned_id, create_user_id, create_datetime, seal_flag) VALUES(:scheduledTripId, :assignmentType, :assignedId, :createUserId, current_timestamp, :sealFlag)
}

OIMSTRIP_SCHEDULEDTRIPASSIGNMENTS_DELETE_SCHEDULED_TRIP_ASSIGNMENTS { 
	DELETE FROM SCHEDULED_TRIP_ASSIGNMENTS where assigned_id=:assignedId and scheduled_trip_id=:scheduledTripId and assignment_type='VEHICLE'
}

OIMSTRIP_STAFFASSIGNMENT_FIND_SCHEDULED_TRIP_ASSIGNMENTS {
 SELECT scheduled_trip_id, assignment_type, assigned_id, create_user_id, create_datetime, seal_flag, modify_datetime, modify_user_id  FROM SCHEDULED_TRIP_ASSIGNMENTS  where scheduled_trip_id=:scheduledTripId and assignment_type = 'STAFF' order by CREATE_DATETIME asc
}
OIMSTRIP_STAFFASSIGNMENT_INSERT_SCHEDULED_TRIP_ASSIGNMENTS {
	INSERT INTO SCHEDULED_TRIP_ASSIGNMENTS(scheduled_trip_id, assignment_type, assigned_id, create_user_id, create_datetime, seal_flag) VALUES(:scheduledTripId, :assignmentType, :assignedId, :createUserId, current_timestamp, :sealFlag)
}

OIMSTRIP_STAFFASSIGNMENT_UPDATE_SCHEDULED_TRIP_ASSIGNMENTS {
	update SCHEDULED_TRIP_ASSIGNMENTS set assigned_id =:assignedId , modify_datetime = current_timestamp, modify_user_id =:modifyUserId where assigned_id =:assignedIdTemp and scheduled_trip_id =:scheduledTripId and assignment_type='STAFF'
}

OIMSTRIP_STAFFASSIGNMENT_DELETE_SCHEDULED_TRIP_ASSIGNMENTS { 
	DELETE FROM SCHEDULED_TRIP_ASSIGNMENTS where assigned_id=:assignedId and scheduled_trip_id=:scheduledTripId and assignment_type='STAFF'
}

TAGTRANSPORT_C_TRIP{
select 1 from trips 
			where trip_code = :tripCode
}

SCHEDULE_GENERATE_OIDGENST{
	select
	T.*,
	(
	select
		active_flag
	from
		trips
	where
		trip_code = t.trip_code) as v_flag,
	(
	select
		count(*)
	from
		trips
	where
		trip_code = t.trip_code) as v_count,
	  (
	select
		MAX(departure_date)
	from
		scheduled_trips
	where
		trip_code = t.trip_code AND cancel_flag = 'N') as v_mdate,
		(SELECT MAX(departure_date) FROM scheduled_trips 
	  WHERE trip_code = t.trip_code
	    AND cancel_flag = 'N') as v_mdate1
from
	TRIPS t
order by
	active_flag desc,
	LIST_SEQ asc
}

OFF_BKG_CUR{
select offender_book_id,movement_seq
		from offender_proposed_mvmnts
		where scheduled_trip_id = :sacheduledTripId
}

OFF_NADMT_CUR{
select non_adm_inmate_id
		from non_admitted_inmate_mvmts
		where scheduled_trip_id = :sacheduledTripId;
}

SCHID_CUR{
select scheduled_trip_id 
		from scheduled_trip_details
		where scheduled_trip_id in (select scheduled_trip_id
	  					   from scheduled_trips
							   where trip_code = :tripCode
						   		 and departure_date > :departureDate)
		group by scheduled_trip_id
}

--new

IF_OFF_ON_TRIP{
 SELECT   offender_book_id
        FROM   v_offender_all_schedules_2
       WHERE   scheduled_trip_id IN (SELECT   scheduled_trip_id
                                       FROM   scheduled_trips
                                      WHERE   trip_code = :tripCode
                                          AND departure_date >= :departureDate)
}

IF_NADT_ON_TRIP{
SELECT   non_adm_inmate_id
        FROM   non_admitted_inmate_mvmts
       WHERE   scheduled_trip_id IN (SELECT   scheduled_trip_id
                                       FROM   scheduled_trips
                                      WHERE   trip_code = :tripCode
                                          AND departure_date >= :departureDate)
}

GET_SCH_ID_CUR{
SELECT   scheduled_trip_id
        FROM   scheduled_trips
       WHERE   trip_code = :tripCode
           AND departure_date >= :departureDate
           AND cancel_flag = 'N'
}

GET_EVENT_ID_CUR{
SELECT event_id
		  FROM v_offender_all_schedules_2
		 WHERE scheduled_trip_id = :pSchId
}

V_OFFENDER_ALL_SCHEDULES_2{
UPDATE v_offender_all_schedules_2
	SET scheduled_trip_id = NULL
 WHERE event_id = :pEventId
}

NON_ADMITTED_INMATE_MVMTS{
 DELETE  FROM non_admitted_inmate_mvmts WHERE scheduled_trip_id = :pSchId
}

TRIPS_OIDGENST_INSERT{
INSERT INTO TRIPS(TRIP_CODE,DESCRIPTION, LIST_SEQ, active_flag, expiry_date, start_date, end_date, trip_type, create_user_id, create_datetime) 
VALUES (:tripCode, :description, :listSeq, :activeFlag, :expiryDate, :startDate, :endDate, :tripType, :createUserId, current_timestamp)
}

SCHEDULED_TRIPS_EXECUTEQUERY_EXTRA{
select 	sum(v.optimum_capacity) as optCap
						,sum(v.physical_capacity) as physCap
		from scheduled_trip_assignments s
	 			, select_vehicles_v1 v
	where s.assigned_id = v.VEHICLE_ID
	  	and s.assignment_type = 'VEHICLE'
	  	and scheduled_trip_id = :scheduledTripId;
}

IF_ASSIGNED_CUR{
 select
	count(*)
from
	scheduled_trip_assignments sta,
	scheduled_trips st
where
	sta.scheduled_trip_id = st.scheduled_trip_id
	and assigned_id = :assignedId
	and assignment_type = 'VEHICLE'
	and (st.departure_date >=:departureDate
		and st.completion_date <=:completionDate)
}

TRIP_TABLE_UPDATE{
update trips set ACTIVE_FLAG='Y', EXPIRY_DATE=null, modify_user_id=:modifyUserId, modify_datetime = current_timestamp where TRIP_CODE=:tripCode
}
















