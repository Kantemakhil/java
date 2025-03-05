GET_OFFENDER_EXTERNAL_MOVEMENTS_DATA {
select OFFENDER_BOOK_ID,
MOVEMENT_SEQ,
MOVEMENT_DATE,
MOVEMENT_TIME,
INTERNAL_SCHEDULE_TYPE,
INTERNAL_SCHEDULE_REASON_CODE,
MOVEMENT_TYPE,
MOVEMENT_REASON_CODE,
DIRECTION_CODE,
ARREST_AGENCY_LOC_ID,
TO_PROV_STAT_CODE,
ESCORT_CODE,
FROM_AGY_LOC_ID,
TO_AGY_LOC_ID,
ACTIVE_FLAG,
ESCORT_TEXT,
COMMENT_TEXT,
REPORTING_DATE,
TO_CITY,
FROM_CITY,
REPORTING_TIME,
EVENT_ID,
PARENT_EVENT_ID,
TO_COUNTRY_CODE,
OJ_LOCATION_CODE,
APPLICATION_DATE,
APPLICATION_TIME,
TO_ADDRESS_ID,
FROM_ADDRESS_ID,
SEAL_FLAG,
CREATE_DATETIME,
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID from offender_external_movements where OFFENDER_BOOK_ID=:OFFENDER_BOOK_ID and MOVEMENT_SEQ=:MOVEMENT_SEQ
}

GET_Y {
select 'Y'
    from offence_indicators oi
   where indicator_code = 'SEX'
     and (
	select
		offence_code
	from
		offences o
	where
		o.offence_id = oi.offence_id) in
              (select distinct offence_code
           from offender_charges
        where offender_book_id =:p_offender_book_id)
}

GET_TRG_EVENT_ID {
  select nextval('trg_event_id')
}

INSERT_OFFENDER_ASSOC_P_EVENT_NOTIFS {
insert into offender_assoc_p_event_notifs(trg_event_id, event_date, offender_book_id, notification_code , create_datetime, create_user_id) values (:l_trg_event_id, :l_move_date, :l_offender_book_id, :input_value, current_timestamp, :createUserId) 
}
