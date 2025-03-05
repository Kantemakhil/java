
OIDPHUNC_FIND_RGFROMLIVUNIT {
SELECT LIVING_UNIT_CODE DESCRIPTION, TO_CHARLIVING_UNIT_ID) CODE , AGY_LOC_ID FROM LIVING_UNITS ORDER BY 1
}

OIDPHUNC_FIND_RGLEVEL_ONE {
 SELECT living_unit_code,description,living_unit_id code FROM living_units WHERE agy_loc_id = :agy_loc_id AND parent_living_unit_id IS null and (active_flag = 'Y' ) ORDER BY 1

}

OIDPHUNC_FIND_RGLEVEL_TWO {
select
	living_unit_code,
	description,
	living_unit_id code
from
	living_units
where
	 parent_living_unit_id = :LEVEL_ONE_ID
	and (active_flag = 'Y')
	order by 1

	}

OIDPHUNC_FIND_RGLEVEL_THREE {

   SELECT living_unit_code,description,living_unit_id code FROM living_units WHERE  parent_living_unit_id = :LEVEL_TWO_ID and (active_flag = 'Y' )ORDER BY 1
}

OIDPHUNC_FIND_RGLEVEL_FOUR {
	SELECT living_unit_code,description,living_unit_id code FROM living_units WHERE  parent_living_unit_id = :LEVEL_THREE_ID and (active_flag = 'Y' )ORDER BY 1

}

OIDPHUNC_FIND_RGLEVEL_TYPE{

select
	distinct mov.INTERNAL_SCHEDULE_TYPE code,
	refc.description,
	refc.list_seq 
from
INTERNAL_SCHEDULE_reasons mov,
reference_codes refc
where
mov.INTERNAL_SCHEDULE_TYPE = refc.code  
and ( (refc.active_flag = 'Y'
	and refc.expired_date is null))
order by
refc.list_seq


}

OIDPHUNC_FIND_RGLEVEL_REASON{

select
	INTERNAL_SCHEDULE_RSN_CODE CODE,
	DESCRIPTION
from
	INTERNAL_SCHEDULE_REASONS
where
	INTERNAL_SCHEDULE_TYPE = :MOVEMENT_TYPE
and ( (active_flag = 'Y'
	and expiry_date is null))
order by
1


}


OIDPHUNC_PROPMOVE_FIND_OFFENDER_PROPOSED_INTLOCS {
select
	opi.*,
	( SELECT count(*) FROM v_off_sch_overview	
		where offender_book_id = opi.offender_book_id
	   		 AND (TO_DATE(EVENT_DATE::text,'yyyy/mm/dd') =TO_DATE(current_timestamp ::text,'yyyy/mm/dd') + 14
	   		 			or event_date is null)
			  and int_ext = 'INTERNAL') as vCount,
	(
	select recorded_date from offender_loc_chng_dtls where offender_book_id = opi.offender_book_id
	 and location_seq = opi.location_seq and detail_seq = 1) as initiated_date,
	 (select recorded_date from offender_loc_chng_dtls where offender_book_id = opi.offender_book_id
	and location_seq = opi.location_seq and status_code = 'APP' 
	and detail_seq = (select min(detail_seq) from offender_loc_chng_dtls  where offender_book_id = opi.offender_book_id and status_code = 'APP'
	and location_seq =  opi.location_seq) order by recorded_date ) as APPROVAL_DATE
from
	OFFENDER_PROPOSED_INTLOCS opi
where
	opi.offender_book_id = :offenderBookId
}
OIDPHUNC_PROPMOVE_INSERT_OFFENDERPROPOSEDINTLOCS {
	INSERT INTO OFFENDER_PROPOSED_INTLOCS 
(OFFENDER_BOOK_ID,LOCATION_SEQ,FROM_LIVING_UNIT_ID,TO_LIVING_UNIT_ID,MOVEMENT_TYPE,MOVEMENT_REASON,COMMENT_TEXT,CREATE_USER_ID,CREATE_DATETIME,SEAL_FLAG,MODIFY_DATETIME)
VALUES(:offenderBookId, :locationSeq, :fromLivingUnitId, :toLivingUnitId, :movementType, :movementReason, :commentText, :createUserId, :createDatetime, :sealFlag, null)

}

OIDPHUNC_PROPMOVE_UPDATE_OFFENDERPROPOSEDINTLOCS {
UPDATE OFFENDER_PROPOSED_INTLOCS SET COMMENT_TEXT=:commentText, MODIFY_DATETIME = :modifiedDatetime, MODIFY_USER_ID =:modifiyUserId where 
OFFENDER_BOOK_ID = :offenderBookId and LOCATION_SEQ =:locationSeq
}

OIDPHUNC_POTE_SCH_CUR {
SELECT count(*) FROM v_off_sch_overview	
		where offender_book_id = :offender_book_id	
	   		 AND (TO_DATE(EVENT_DATE::text,'yyyy/mm/dd') =TO_DATE(current_timestamp ::text,'yyyy/mm/dd')
	   		 			or event_date is null)
			  and int_ext = 'INTERNAL'
}
OIDPHUN_LIV_AGY_CUR {

SELECT AGY_LOC_ID FROM LIVING_UNITS WHERE LIVING_UNIT_ID = :fromLivingUnitId

}

OIDPHUNC_GET_NODES_CUR{

with recursive cte as (
select
	living_unit_id,
	living_unit_code,
	parent_living_unit_id
from
	living_units
where
	living_unit_id = :to_living_unit_id
union all
select
	lu.living_unit_id,
	lu.living_unit_code,
	lu.parent_living_unit_id
from
	living_units lu
join cte c on
	(c.parent_living_unit_id = lu.living_unit_id )
)
select
	living_unit_id,
	living_unit_code
from
	cte order by living_unit_id
}
