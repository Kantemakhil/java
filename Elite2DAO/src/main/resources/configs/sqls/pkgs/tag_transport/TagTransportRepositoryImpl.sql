TAG_TRANSPORT_INSERT_SCHEDULED_TRIPS{
insert
	into
	scheduled_trips (scheduled_trip_id ,
	trip_code ,
	departure_date ,
	completion_date ,
	route_name ,
	status ,
	est_departure_time ,
	est_completion_time,
	create_user_id,
	modify_datetime,
	create_datetime)
values (NEXTVAL('scheduled_trip_id') ,
:p_trip_code ,
:l_sch_date ,
(
SELECT 
    (:l_sch_date::date + (:p_departure_time::time - TIME '00:00:00') 
    + (:route_length / 1400.0) * INTERVAL '1 hour') AS result_datetime)  ,
:p_route_name ,
'PEND' ,
(
select
	(:l_sch_date::date + (:p_departure_time - date_trunc('day',:p_departure_time::date)) ::interval ) 
	from dual),
:l_sch_date::date + (:p_departure_time::date - (
SELECT 
(:l_sch_date::date + (:p_departure_time::time - TIME '00:00:00') 
    + (:route_length / 1400.0) * INTERVAL '1 hour') AS result_datetime)),
:create_user_id,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP)
}

TAG_TRANSPORT_GENERATE_SCHEDULES{
SELECT coalesce(SUM(segment_length),0) FROM v_route_locations WHERE route_name = upper(:route_name)
}
TAG_TRANSPORT_GET_V_SEGMENT_LENGTH{
SELECT COALESCE(SUM(vrl.segment_length),0)  FROM v_route_locations vrl WHERE vrl.route_name = :routeName
}
TAG_TRANSPORT_UPDATE_SCHEDULED_TRIPS{
update
	scheduled_trips
set
	est_completion_time = ( date_trunc('day', departure_date) + ((est_departure_time -date_trunc('day', est_departure_time)) ) + (:v_segment_length / 1400 || ' HOURS')::interval ),
	modify_datetime = current_timestamp,
	MODIFY_USER_ID = :modifyUserId
where
	route_name = :p_route_name;                           
}
TAG_TRANSPORT_GET_ROUTE_STOP_DETAILS{
SELECT leg_id, leg_seq, agy_loc_id
           FROM route_stop_details
          WHERE route_name = :v_route_name
            AND active_flag = 'Y'
          ORDER BY leg_id, leg_seq ASC;
}
TAG_TRANSPORT_GET_COUNT_OF_AGENCY_SEGMENT_LENGTH{
SELECT COUNT(*)
           FROM agency_segment_lengths
          WHERE (from_agy_loc_id = :v_from_agy_loc AND
                to_agy_loc_id = :v_to_agy_loc)
             OR (from_agy_loc_id = :v_to_agy_loc AND
                to_agy_loc_id = :v_from_agy_loc)
}
TAG_TRANSPORT_INSERT_P_AGENCY_SEGMENT_LENGTH{
INSERT INTO agency_segment_lengths
                  (from_agy_loc_id, to_agy_loc_id, segment_length,CREATE_USER_ID,CREATE_DATETIME)
               VALUES
                  (:v_from_agy_loc, :v_to_agy_loc, 1,:userName,CURRENT_TIMESTAMP)
}


TAG_TRANSPORT_OFFENDER_PROPOSED_MVMNTS{
 UPDATE offender_proposed_mvmnts
            SET scheduled_trip_id = NULL, alternate_agy_loc_id = NULL
          WHERE offender_book_id =:pOffBkg
            AND movement_seq =:pMoveSeq
}

TAG_TRANSPORT_GET_SEQ_CUR_OFFENDER_MOVEMENT_DETAILS{
SELECT coalesce(MAX(detail_seq), 0) + 1
           FROM offender_movement_details
          WHERE offender_book_id =:pOffBkg
            AND movement_seq =:pMoveSeq
}

TAG_TRANSPORT_GET_SEQ_CUR_INST{
SELECT coalesce (MAX(location_seq), 0) + 1
           FROM offender_proposed_intlocs
          WHERE offender_book_id = :offenderBookId
}

TAG_TRANSPORT_INSERT_MVMNT_DETAILS{
INSERT INTO offender_movement_details(offender_book_id,movement_seq ,detail_seq,status_code,recorded_by,recorded_date,app_rsn,txn_status,txn_rsn,create_user_id,create_datetime)
							VALUES(:offenderBookId,:movementSeq,:detailSeq,:statusCode,:recordedBy,:recordedDate,:appRsn,:txnStatus,:txnRsn,:createUserId,CURRENT_TIMESTAMP)
}

TAG_TRANSPORT_NON_ADMITTED_INMATE_MVMTS{
 UPDATE non_admitted_inmate_mvmts
            SET scheduled_trip_id = NULL, event_date = NULL
          WHERE non_adm_inmate_id = :p_inm_id
}

TAG_TRANSPORT_GET_SEQ_CUR_ONE{
  SELECT coalesce(MAX(detail_seq), 0) + 1
           FROM non_adm_inm_mvmt_details
          WHERE non_adm_inmate_id =:pInmId
}

TAG_TRANSPORT_INSERT_NADM_MVMNT_DETAILS{
INSERT INTO non_adm_inm_mvmt_details(non_adm_inmate_id,detail_seq,status_code,recorded_by,recorded_date,app_rsn,txn_status,txn_rsn,create_user_id,create_datetime)
 								VALUES(:nonAdmInmateId,:detailSeq,:statusCode,:recordedBy,current_timestamp,:pAppRsn,:pTxnStat,:pTxnRsn,:createUserId,CURRENT_TIMESTAMP)}

TAG_TRANSPORT_SCH_TRIP_CUR{
SELECT scheduled_trip_id, departure_date, route_name
           FROM scheduled_trips
          WHERE trip_code =:pTripCode
            AND departure_date > :pDepDt
            AND cancel_flag = 'N'
          ORDER BY departure_date
}

TAG_TRANSPORT_OVERNIGHT_CUR{
 SELECT COUNT(*)
           FROM route_stop_details
          WHERE route_name =:pRoute
            AND active_flag = 'Y'
            AND overnight_flag = 'Y';
}

TAG_TRANSPORT_MX_STOP_CUR{
SELECT leg_id, MAX(leg_seq)
           FROM route_stop_details
          WHERE leg_id = (SELECT MAX(rx.leg_id)
                            FROM route_stop_details rx
                           WHERE rx.route_name = :pRoute
                             AND rx.active_flag = 'Y'
                         )
            AND route_name = :pRoute
            AND active_flag = 'Y'
          GROUP BY leg_id
}

TAG_TRANSPORT_MX_NIGHT_CUR{
 SELECT leg_id, MAX(leg_seq)
           FROM route_stop_details
          WHERE leg_id = (SELECT MAX(rx.leg_id)
                            FROM route_stop_details rx
                           WHERE rx.route_name = :pRoute
                             AND rx.active_flag = 'Y')
            AND route_name = :pRoute
            AND overnight_flag = 'Y'
            AND active_flag = 'Y'
          GROUP BY leg_id;
}


TAG_TRANSPORT_SCHEDULED_TRIPS{
   UPDATE scheduled_trips
            SET route_name =:vRoute1, completion_date =:vCompDt
          WHERE scheduled_trip_id =:vSchId
}

TAG_TRANSPORT_TRIP_DET_CUR{
SELECT action_type
           FROM scheduled_trip_details
          WHERE scheduled_trip_id = :pTripId
          ORDER BY create_datetime DESC
}

TAG_TRANSPORT_IF_EXIST_MOV{
 SELECT COUNT(*)
           FROM offender_external_movements t
          WHERE event_id = :pEventId
}
TAG_TRANSPORT_GET_ROUTE_LEN{
select SUM(seg_len) from ( select v.route_name , ( select a.segment_length from agency_segment_lengths a where ((v.agy_loc_id = a.from_agy_loc_id and v.to_agy = a.to_agy_loc_id) or (v.agy_loc_id = a.to_agy_loc_id and v.to_agy = a.from_agy_loc_id))) seg_len from v_route_flow v where v.route_name = :p_route)A
}
TAG_TRANSPORT_COMP_TIME_CUR{
SELECT :p_dep_date::date + ((:p_est_dep_time - :p_est_dep_time::date) + (:p_route_len / 1440)) FROM dual;
}
TAG_TRANSPORT_OVERNIGHT_CUR{
SELECT COUNT(*) FROM route_stop_details WHERE route_name =:p_route AND active_flag = 'Y' AND overnight_flag = 'Y'
}
TAG_TRANSPORT_MX_STOP_CUR{
SELECT leg_id, MAX(leg_seq) FROM route_stop_details WHERE leg_id = (SELECT MAX(rx.leg_id) FROM route_stop_details rx WHERE rx.route_name =:p_route AND rx.active_flag = 'Y' ) AND route_name = :p_route AND active_flag = 'Y' GROUP BY leg_id
}
TAG_TRANSPORT_MX_NIGHT_CUR{
SELECT leg_id, MAX(leg_seq) FROM route_stop_details WHERE leg_id = (SELECT MAX(rx.leg_id) FROM route_stop_details rx WHERE rx.route_name = :p_route AND rx.active_flag = 'Y') AND route_name = :p_route AND overnight_flag = 'Y' AND active_flag = 'Y' GROUP BY leg_id
}



TAG_TRANSPORT_NON_ASSOCIATION_EXISTS{
select
	st.departure_date,
	t.description,
	st.route_name,
	st.scheduled_trip_id,
	(
	select
		sum(v.physical_capacity)
	from
		scheduled_trip_assignments s,
		select_vehicles_v1 v
	where
		s.assigned_id = v.vehicle_id
		and s.assignment_type = 'VEHICLE'
		and s.scheduled_trip_id = :p_scheduled_trip_id) as physical_capacity
from
	scheduled_trips st,
	trips t
where
	st.scheduled_trip_id = :p_scheduled_trip_id
	and st.trip_code = t.trip_code   
}




TAG_TRANSPORT_UPDATE_OFFENDER_IND_SCHEDULES{
 UPDATE offender_ind_schedules
            SET scheduled_trip_id = NULL
          WHERE event_id = :p_event_id
            AND offender_book_id = :p_offender_book_id
            AND scheduled_trip_id = :p_scheduled_trip_id
}

TAG_TRANSPORT_UPDATE_OFFENDER_COURT_EVENTS{
  UPDATE court_events
            SET scheduled_trip_id = NULL
          WHERE event_id = :p_event_id
            AND offender_book_id = :p_offender_book_id
            AND scheduled_trip_id = :p_scheduled_trip_id

}

TAG_TRANSPORT_REMOVE_OFFENDER_FROM_TRIPS{
   DELETE FROM non_admitted_inmate_mvmts
          WHERE scheduled_trip_id = :p_scheduled_trip_id
            AND non_adm_inmate_id = :p_inmate_id 

}

TAG_TRANSPORT_ASSIGN_OFFENDER_TO_TRIPS{

}

TAG_TRANSPORT_SCHEDULED_TRIPS_NOWAIT{
        SELECT * FROM scheduled_trips WHERE scheduled_trip_id = :scheduled_trip_id FOR UPDATE NOWAIT;
}

TAG_TRANSPORT_TOTAL_SCHEDULED_TRIPS{
select count(*)
        from v_offender_all_schedules
       where scheduled_trip_id = :p_scheduled_trip_id
         and offender_book_id = :p_offender_book_id
}

TAG_TRANSPORT_UPDATE_OFFENDER_IND_SCHEDULES{
  UPDATE offender_ind_schedules
            SET scheduled_trip_id = :p_scheduled_trip_id
          WHERE event_id = :p_event_id
            AND offender_book_id = :p_offender_book_id
}

TAG_TRANSPORT_UPDATE_OFFENDER_COURT_EVENTS{
  UPDATE court_events
            SET scheduled_trip_id = :p_scheduled_trip_id
          WHERE event_id = :p_event_id
            AND offender_book_id = :p_offender_book_id
}
TAG_TRANSPORT_COUNT_SCHEDULED_TRIPS{

 SELECT COUNT(*)
        FROM v_local_trip_occupancy
       WHERE scheduled_trip_id = :p_scheduled_trip_id
         AND occupancy > COALESCE(:p_capacity, 0)
}
TAG_TRANSPORT_OFFSCHEDS_FIND_V_ASSIGN_OFFENDER_TRIPS {
select
	scheduled_trip_id,
	event_id,
	event_status,
	offender_book_id,
	root_offender_id,
	offender_id,
	sex_code,
	offender_id_display,
	offender_last_name,
	offender_first_name,
	event_type,
	event_sub_type,
	lu_level_1_code,
	from_location,
	agy_loc_id,
	to_agy_loc_id,
	event_date,
	start_time,
	event_class,
	record_source,
	from_seq
from
	v_assign_offender_trips where (scheduled_trip_id = :scheduled_trip_id)
	order by from_seq, offender_last_name 
	}

TAG_TRANSPORT_OFFTRIPS_NEW_RECORD_INSTANCE{
 select
	1
where
	exists (
	select
		1
	from
		offender_na_details
	where
		offender_id = p_root_offender_id
		and (coalesce(ns_expiry_date::text, '') = ''
			or date_trunc('day', ns_expiry_date) > date_trunc('day', clock_timestamp())))
union all

         select
	1
where
	exists (
	select
		1
	from
		stg_relationships
	where
		relationship_type = 'ENEMY'
		and stg_id in (
		select
			stg_id
		from
			offender_stg_affiliations
		where
			offender_book_id = :p_offender_book_id
			and active_flag = 'Y')
		and active_flag = 'Y')

}

TAG_TRANSPORT_OFFSCHEDS_NEW_RECORD_INSTANCE{
select
	1
where
	exists (
	select
		1
	from
		offender_na_details
	where
		offender_id = :p_root_offender_id
		and (coalesce(ns_expiry_date::text, '') = ''
			or date_trunc('day', ns_expiry_date) > date_trunc('day', clock_timestamp())))
union all

         select
	1
where
	exists (
	select
		1
	from
		stg_relationships
	where
		relationship_type = 'ENEMY'
		and stg_id in (
		select
			stg_id
		from
			offender_stg_affiliations
		where
			offender_book_id = :p_offender_book_id
			and active_flag = 'Y')
		and active_flag = 'Y')
}

TAG_TRANSPORT_OFFENDER_BOOKINGS_RECORDS{
 SELECT root_offender_id
           FROM offender_bookings
          WHERE offender_book_id = :p_offender_book_id
}


TAG_TRANSPORT_STATUS_INM_CUR{
  SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
              ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_movement_details omd
          WHERE offender_book_id = :p_off_bkg
            AND movement_seq = :p_move_seq
            AND detail_seq =
                (SELECT MAX(detail_seq)
                   FROM offender_movement_details
                  WHERE offender_book_id = :p_off_bkg
                    AND movement_seq = :p_move_seq
                    AND ((app_rsn IS NOT NULL AND :p_choice = 'APP') OR
                        (txn_rsn IS NOT NULL AND :p_choice = 'TXN') OR
                        (status_code = :p_choice AND
                        :p_choice NOT IN ('APP', 'TXN')))
                    AND txn_status != 'CREQ')	

}


TAG_TRANSPORT_MAX_STATUS_INM_CUR{
SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
              ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_loc_chng_dtls omd
          WHERE offender_book_id = :p_off_bkg
            AND location_seq = :p_loc_seq
            AND detail_seq = (SELECT MAX(detail_seq)
                                FROM offender_loc_chng_dtls
                               WHERE offender_book_id = :p_off_bkg
                                 AND location_seq = :p_loc_seq
                                 AND txn_status = :p_choice)
}


TAG_TRANSPORT_STATUS_NON_INM_CUR{
  select
	status_code
               ,
	app_rsn
               ,
	txn_status
               ,
	txn_rsn
               ,
	recorded_by
               ,
	recorded_date
from
	non_adm_inm_mvmt_details omd
where
	non_adm_inmate_id = :p_inm_id
	and detail_seq =
                (
	select
		MAX(detail_seq)
	from
		non_adm_inm_mvmt_details
	where
		non_adm_inmate_id = :p_inm_id
		and ((app_rsn is not null
			and p_choice = 'APP')
		or
                        (txn_rsn is not null
			and p_choice = 'TXN')
		or
                        (status_code = :p_choice
			and
                        p_choice not in ('APP', 'TXN')))
			and txn_status != 'CREQ')
}

TAG_TRANSPORT_MAX_STATUS_NON_INM_CUR{
  SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
               ,recorded_by
               ,recorded_date
           FROM non_adm_inm_mvmt_details omd
          WHERE non_adm_inmate_id = :p_inm_id
            AND detail_seq = (SELECT MAX(detail_seq)
                    FROM non_adm_inm_mvmt_details
                    WHERE non_adm_inmate_id = :p_inm_id
                    AND txn_status = :p_choice)
            AND txn_status = :p_choice
	}

TAG_TRANSPORT_GET_SEQ_CUR{
SELECT coalesce(MAX(movement_seq), 0) + 1
           FROM offender_proposed_mvmnts
          WHERE offender_book_id = :pOffBkg	

}


TAG_TRANSPORT_STATUS_INM_CUR_INT_LOC {
 SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
               ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_loc_chng_dtls omd
          WHERE offender_book_id = :p_off_bkg
            AND location_seq = :p_loc_seq
            AND detail_seq =
                (SELECT MAX(detail_seq)
                   FROM offender_loc_chng_dtls
                  WHERE offender_book_id = :p_off_bkg
                    AND location_seq = :p_loc_seq
                    AND ((app_rsn IS NOT NULL AND :p_choice = 'APP') OR
                        (txn_rsn IS NOT NULL AND :p_choice = 'TXN') OR
                        (status_code = :p_choice AND
                        :p_choice NOT IN ('APP', 'TXN')))
                    AND txn_status != 'CREQ')
}

TAG_TRANSPORT_MAX_STATUS_INM_CUR_INT_LOC {
SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
               ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_loc_chng_dtls omd
          WHERE offender_book_id = :p_off_bkg
            AND location_seq = :p_loc_seq
            AND detail_seq = (SELECT MAX(detail_seq)
                                FROM offender_loc_chng_dtls
                               WHERE offender_book_id = :p_off_bkg
                                 AND location_seq = :p_loc_seq
                                 AND txn_status = :p_choice)
            AND txn_status = :p_choice
}



TAG_TRANSPORT_IMPSTATUS_CUR{
SELECT imprisonment_status
           FROM offender_imprison_statuses
          WHERE offender_book_id = :offender_book_id 
            AND expiry_date IS NULL
          ORDER BY imprison_status_seq DESC
}

TAG_TRANSPORT_SANCTION_CUR{
  SELECT COUNT(*)
           FROM offender_oic_sanctions
          WHERE offender_book_id = :offender_book_id
            AND status = 'ACTIVE'
}

TAG_TRANSPORT_STGAFFI_CUR{
 SELECT stg.description
           FROM offender_stg_affiliations osa, security_threat_groups stg
          WHERE offender_book_id =:offender_book_id
            AND stg_seq = (SELECT MAX(stg_seq)
                             FROM offender_stg_affiliations
                            WHERE offender_book_id = :offender_book_id
                              AND active_flag = 'Y')
            AND osa.stg_id = stg.stg_id
}


TAG_TRANSPORT_GETSEQ_CUR{

   SELECT coalesce(MAX(detail_seq), 0) + 1
           FROM offender_movement_details
          WHERE offender_book_id =  :offender_book_id 
              AND movement_seq = :movement_seq

}


TAG_TRANSPORT_NONADMITTEDINMATE_GETSEQ_CUR{

  SELECT coalesce(MAX(detail_seq), 0) + 1
           FROM non_adm_inm_mvmt_details
          WHERE non_adm_inmate_id = :non_adm_inmate_id

}

TAG_TRANSPORT_INSERTINTO_OFFENDERMOVEMENTDETAILS{
    INSERT INTO offender_movement_details
         (offender_book_id
         ,movement_seq
         ,detail_seq
         ,status_code
         ,recorded_by
         ,recorded_date
         ,app_rsn
         ,txn_status
         ,txn_rsn)
      VALUES
         (:offenderBookId
         ,:movementSeq
         ,:detailSeq
         ,:statusCode
         ,:recordedBy
         ,:recordedDate
         ,:appRsn
         ,:txnStatus
         ,:txnRsn)
         
}
TAG_TRANSPORT_INSERTINTO_NON_ADM_MVMDET{

 INSERT INTO non_adm_inm_mvmt_details
         (non_adm_inmate_id
         ,detail_seq
         ,status_code
         ,recorded_by
         ,recorded_date
         ,app_rsn
         ,txn_status
         ,txn_rsn)
      VALUES
         (:pInmId
         ,:p_det_seq
         ,:p_status
         ,:p_user
         ,:pRecdDt
         ,:pAppRsn
         ,:pTxnStat
         ,:pTxnRsn)

}


TAG_TRANSPORT_IF_ROLE_ASSIGNED{
select tag_transport_if_role_assigned ( :userId, :roleNm )
--    SELECT 'Y'
--           FROM staff_member_roles smr, oms_roles omr
--          WHERE staff_id =:userId
--            AND smr.role_id = omr.role_id
--            AND role_name = :roleNm
         
}

TAG_TRANSPORT_IMPRESMENT_STATUS{

       SELECT imprisonment_status
           FROM offender_imprison_statuses
          WHERE offender_book_id = :offBkg
            AND expiry_date IS NULL
          ORDER BY imprison_status_seq DESC;

}

TAG_TRANSPORT_NON_ASS_COUNT{

   SELECT COUNT(*)
           FROM offender_non_associations ona, offender_bookings obk
          WHERE ona.offender_book_id = :offBkg
            AND transport_flag = 'N'
            AND ona.ns_offender_book_id = obk.offender_book_id
            AND obk.agy_loc_id = :agyLoc

}



TAG_TRANSPORT_NON_ASS_NS_COUNT{

  SELECT COUNT(*)
           FROM offender_non_associations ona, offender_bookings obk
          WHERE ona.ns_offender_book_id = :offBkg
            AND transport_flag = 'N'
            AND ona.offender_book_id = obk.offender_book_id
            AND obk.agy_loc_id = :agyLoc
}


TAG_TRANSPORT_GET_SCHEDULE_DATE{
SELECT 
    CASE
        WHEN extract(ISODOW FROM  TO_DATE(:fromdate::text,'yyyy/mm/dd')) <= DAYINDEX THEN cast(date_trunc('week',  TO_DATE(:fromdate::text,'yyyy/mm/dd')) AS date) - 8 + DAYINDEX + 7*orgweek
        ELSE cast(date_trunc('week', TO_DATE(:fromdate::text,'yyyy/mm/dd')) AS date) - 1 + DAYINDEX + 7*orgweek
    END ENDTIME
   from (select 
extract(ISODOW FROM  TO_DATE(ENDTIME::text,'yyyy/mm/dd')) AAA,
   case when :week >1 and extract(ISODOW FROM  TO_DATE(ENDTIME::text,'yyyy/mm/dd')) > DAYINDEX  then :week-1
else :week end orgweek,DAYINDEX
from
 (SELECT DAYINDEX,
    CASE WHEN extract(ISODOW FROM current_timestamp) < 0 THEN cast(date_trunc('week', current_timestamp) AS date) - 8 + 0
        ELSE cast(date_trunc('week', current_timestamp) AS date) - 1 + DAYINDEX END STARTTIME, 
    CASE WHEN extract(ISODOW FROM current_timestamp) < 0 THEN cast(date_trunc('week', current_timestamp) AS date) - 8 + 0 + 6
        ELSE cast(date_trunc('week', current_timestamp) AS date) - 1 + DAYINDEX + 6 END ENDTIME
   from (select 
case when :day ='SUN' then 0 
when :day ='MON' then 1 
when :day ='TUE' then 2 
when :day ='WED' then 3 
when :day ='THU' then 4 
when :day ='FRI' then 5 
when :day ='SAT' then 6 else null end DAYINDEX )A)b)c
}


TAG_TRANSPORT_INSERT_LOC_CHNG_DTLS {
INSERT INTO offender_loc_chng_dtls(offender_book_id,location_seq,detail_seq,status_code,recorded_by,recorded_date,app_rsn,txn_status,txn_rsn)
VALUES(:offenderBookId,:locationSeq,:detailSeq,:statusCode,:recordedBy,:recordedDate,:appRsn,:txnStatus,:txnRsn)
}

TAG_TRANSPORT_GET_SEQ_CUR_LOC_DTLS {
SELECT coalesce(MAX(detail_seq), 0) + 1
           FROM offender_loc_chng_dtls
          WHERE offender_book_id = :p_off_bkg
            AND location_seq = :p_loc_seq
}

TAG_TRANSPORT_IF_INTR_NON_ASSO_EXISTS {
select tag_transport_IF_INTR_NON_ASSO_EXISTS ( :offender_book_id, :curr_agy_id, :to_living_unit_id )
}

TAG_TRANSPORT_NON_ASSO_EXISTS{
   SELECT offender_book_id
                      FROM v_local_trip_offenders
                     WHERE scheduled_trip_id =:pscheduledTripId
                       AND offender_book_id IS NOT NULL
                       AND offender_book_id !=:pOffenderBookId
}

TAG_TRANSPORT_IF_NON_ASSOC_BETWEEN{

                  SELECT 'Y'
           FROM offender_non_associations ona
          WHERE offender_book_id = :p_off_bkg1
            AND transport_flag = 'N'
            AND ns_offender_book_id = :p_off_bkg2
            AND exists
                   (select 1
                      from offender_na_details nad
                     where nad.offender_book_id = :p_off_bkg1
                       and nad.ns_offender_book_id = :p_off_bkg2
                       and (date_trunc('day',ns_expiry_date) > date_trunc('day',current_timestamp)
                OR ns_expiry_date IS NULL
                        )
                   )
         UNION
         SELECT 'Y'
           FROM offender_non_associations
          WHERE ns_offender_book_id =:p_off_bkg1
            AND transport_flag = 'N'
            AND offender_book_id =:p_off_bkg2
            AND exists
                   (select 1
                      from offender_na_details nad
                     where nad.offender_book_id =:p_off_bkg2
                       and nad.ns_offender_book_id =:p_off_bkg1
                       and (date_trunc('day',ns_expiry_date) > date_trunc('day',current_timestamp)
                OR ns_expiry_date IS NULL
                        )
                   )  

}
TAG_TRANSPORT_EXIT_NON_ASSOC_COUNT{

  SELECT   count(*)
          
           FROM   stg_relationships rel
          WHERE   rel.stg_id IN (SELECT   osa.stg_id
                                   FROM   offender_stg_affiliations osa
                                  WHERE   osa.offender_book_id =:p_offender_book_id
                                    AND osa.active_flag = 'Y')
            AND rel.relationship_type = 'ENEMY'
            AND rel.active_flag = 'Y'
            AND rel.related_stg_id IN
                 (SELECT   os.stg_id
                    FROM   offender_stg_affiliations os
                   WHERE   os.offender_book_id != :p_offender_book_id
                     AND os.offender_book_id IN
                               (SELECT   vs.offender_book_id
                                  FROM   v_offender_all_schedules vs
                                 WHERE   vs.scheduled_trip_id =:p_scheduled_trip_id)
                     AND os.active_flag = 'Y')
}


