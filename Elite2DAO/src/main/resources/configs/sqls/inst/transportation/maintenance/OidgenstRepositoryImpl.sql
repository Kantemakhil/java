OIDGENST_FIND_RGROUTE {
 	--SELECT ROUTE_NAME ,DESCRIPTION  FROM ROUTES R WHERE (((::TRIPTYPE = 'LOCAL' AND ROUTE_NAME NOT IN (SELECT ROUTE_NAME    FROM ROUTE_STOP_DETAILS T    WHERE T.OVERNIGHT_FLAG = 'Y'      AND T.ACTIVE_FLAG = 'Y' )     AND EXISTS        (SELECT 1            FROM ROUTE_STOP_DETAILS RX           WHERE RX.ROUTE_NAME = R.ROUTE_NAME             AND RX.ACTIVE_FLAG = 'Y'            AND RX.OVERNIGHT_FLAG = 'N' )  ) ) OR ::TRIPTYPE = 'INTER' ) AND (ACTIVE_FLAG = 'Y' OR ::MODE ='ENTER-QUERY' )
SELECT route_name code , description  FROM routes r WHERE ( ( ( route_name NOT IN ( SELECT route_name FROM route_stop_details t WHERE t.overnight_flag = 'Y' AND t.active_flag = 'Y' ) AND EXISTS ( SELECT 1 FROM route_stop_details rx WHERE rx.route_name = r.route_name AND rx.active_flag = 'Y' AND rx.overnight_flag = 'N' ) ) ) ) AND ( active_flag = 'Y' )
}

OIDGENST_SCHPLANNER_FIND_SCHEDULED_TRIP_PARAMETERS {
SELECT week_no,sunday,monday,tuesday,wednesday,thursday,friday,saturday,est_departure_time,  ( SELECT MAX(DEPARTURE_DATE)FROM SCHEDULED_TRIPS WHERE CANCEL_FLAG = 'N'AND TRIP_CODE = :tripCode )  as vMdate FROM scheduled_trip_parameters stp where stp.trip_code  = :tripCode 
}
OIDGENST_SCHPLANNER_INSERT_SCHEDULED_TRIP_PARAMETERS {
	INSERT INTO SCHEDULED_TRIP_PARAMETERS(trip_code ,week_no,sunday,monday,tuesday,wednesday,thursday,friday,saturday,est_departure_time,create_datetime,create_user_id)
	VALUES(:tripCode, :weekNo, :sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday, :estDepartureTime,current_timestamp, :createUserId)
}

OIDGENST_SCHPLANNER_UPDATE_SCHEDULED_TRIP_PARAMETERS {
	UPDATE SCHEDULED_TRIP_PARAMETERS set week_no =:weekNo, sunday =:sunday, monday =:monday, tuesday =:tuesday, wednesday =:wednesday, thursday =:thursday, friday=:friday, saturday=:saturday, est_departure_time=:estDepartureTime,modify_user_id=:modifyUserId ,modify_datetime=current_timestamp where week_no = :weekNoTemp
}

OIDGENST_SCHEDULEDTRIPS_FIND_SCHEDULED_TRIPS {
 	--SELECT st.*,
 	--(SELECT count(*) FROM v_offender_all_schedules_2 WHERE scheduled_trip_id = st.scheduled_trip_id) as if_off_on_trip_cur ,
 	--(SELECT count(*) FROM non_admitted_inmate_mvmts WHERE scheduled_trip_id = st.scheduled_trip_id ) l_non_off_count,
    --(SELECT COUNT(*)FROM SCHEDULED_TRIP_ASSIGNMENTS WHERE ASSIGNMENT_TYPE = 'VEHICLE' AND SCHEDULED_TRIP_ID = st.scheduled_trip_id ) l_vehicle_count ,
   -- (SELECT count(*)
	--	  FROM scheduled_trips
	--	 WHERE departure_date = st.departure_date
	--	   AND trip_code = :TRIP_CODE) as v_cnt,
   -- (SELECT COUNT(*)FROM SCHEDULED_TRIP_ASSIGNMENTS WHERE ASSIGNMENT_TYPE = 'STAFF' AND SCHEDULED_TRIP_ID = st.scheduled_trip_id) l_staff_count
   -- where TRIP_CODE = :TRIP_CODE and cancel_flag = 'N'
    	SELECT st.*,
 	(SELECT count(*) FROM v_offender_all_schedules_2 WHERE scheduled_trip_id = st.scheduled_trip_id) as if_off_on_trip_cur ,
 	(SELECT count(*) FROM non_admitted_inmate_mvmts WHERE scheduled_trip_id = st.scheduled_trip_id ) l_non_off_count,
    (SELECT COUNT(*)FROM SCHEDULED_TRIP_ASSIGNMENTS WHERE ASSIGNMENT_TYPE = 'VEHICLE' AND SCHEDULED_TRIP_ID = st.scheduled_trip_id ) l_vehicle_count ,
    (SELECT count(*) FROM scheduled_trips WHERE departure_date = st.departure_date  AND trip_code = :TRIP_CODE) as v_cnt,
    (SELECT COUNT(*)FROM SCHEDULED_TRIP_ASSIGNMENTS WHERE ASSIGNMENT_TYPE = 'STAFF' AND SCHEDULED_TRIP_ID = st.scheduled_trip_id) l_staff_count
    from scheduled_trips st
    where st.TRIP_CODE = :TRIP_CODE and st.cancel_flag = 'N' order by departure_date asc;
}
OIDGENST_SCHEDULEDTRIPS_INSERT_SCHEDULED_TRIPS {
	INSERT INTO SCHEDULED_TRIPS(trip_code,scheduled_trip_id,departure_date, cancel_flag, est_departure_time, route_name,completion_date,est_completion_time,create_user_id,create_datetime,modify_datetime) 
	VALUES(:tripCode,  nextval('SCHEDULED_TRIP_ID') ,:departureDate, :cancelFlag, :estDepartureTime, :routeName, :completionDate, current_timestamp, :createUserId, current_timestamp,NULL)
}

OIDGENST_SCHEDULEDTRIPS_UPDATE_SCHEDULED_TRIPS {
	UPDATE SCHEDULED_TRIPS set departure_date =:departureDate, est_departure_time =:estDepartureTime, route_name =:routeName, completion_date =:completionDate,modify_user_id=:modifyUserId ,modify_datetime=current_timestamp where scheduled_trip_id =:scheduledTripId --, est_completion_time = current_timestamp
}

OIDGENST_SCHEDULEDTRIPS_DELETE_SCHEDULED_TRIPS { 
	DELETE FROM SCHEDULED_TRIPS where scheduled_trip_id = :scheduledTripId
}

OIDGENST_SCHEDULEDTRIPS_IF_EXIST_CUR{
select count(*) from scheduled_trips where trip_code = :trip_code and departure_date >= :start_date and completion_date <= :end_date
}			
OIDGENST_SCHEDULED_TRIPS_IF_EXIST_CUR{
SELECT COUNT(*) FROM scheduled_trips WHERE trip_code = :trip_code AND departure_date BETWEEN :start_date AND :end_date AND CANCEL_FLAG = 'N'
}
OIDGENST_SCHEDULED_TRIPS_MIN_SDATE_CUR{
SELECT MAX(DEPARTURE_DATE) FROM scheduled_trips WHERE CANCEL_FLAG = 'N' AND trip_code = :trip_code
}
OIDGENST_IF_EXIST_CUR{
SELECT count(*) FROM scheduled_trips WHERE departure_date = :departure_date AND trip_code = :trip_code
}
OIDGENST_IF_SCH_CUR{
SELECT count(*) FROM scheduled_trips WHERE departure_date BETWEEN :start_date AND :end_date AND trip_code = :trip_code
}
OIDGENST_UPDATE_TRIPS{
update trips set start_date = :start_date, end_date = :end_date,modify_user_id=:modifyUserId ,modify_datetime=current_timestamp where trip_code = :trip_code
}
OIDGENST_GETSCHEDSEQ{
SELECT nextval('SCHEDULED_TRIP_ID') from dual
}
OIDGENST_GETWEEKDAY{
select to_char(timestamp :departure_date, 'Day') as "Day" from dual;
}
OIDGENST_GETENDDAY{
select to_char(timestamp :completion_date, 'Day') as "Day" from dual;
}
OIDGENST_MAX_TIME{
select max(departure_date) + interval '1 DAY' from scheduled_trips where cancel_flag = 'N' and trip_code = :trip_code
}

