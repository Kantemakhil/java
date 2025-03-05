
OIDPEXIM_PROPMOVE_FIND_OFFENDER_PROPOSED_MVMNTS{
select * from offender_proposed_mvmnts where offender_book_id = :offenderBookId 
}

OIDPEXIM_FIND_FROM_AGY_RECORDGROUP{
select
	agy_loc_id code,
	description 
from
agency_locations
where
agy_loc_id not in ('OUT', 'TRN') 
and deactivation_date is null 
order by
1

}

OIDPEXIM_FIND_RGMOVEREASON{
select
	movement_reason_code code,
	description
from
	movement_reasons
where
	movement_type = :movementType
and trn_p_list_flag = 'Y' 
and ( (active_flag = 'Y'
	and expiry_date is null))

order by
1
}

OIDPEXIM_FIND_RGMOVETYPE{
select
	distinct mov.movement_type code,
	refc.description,
	refc.list_seq 
from
movement_reasons mov,
reference_codes refc
where
mov.movement_type = refc.code 
and domain = 'MOVE_TYPE'
and trn_p_list_flag = 'Y'
and ( (refc.active_flag = 'Y'
	and refc.expired_date is null))
and ( 
( tag_utils_get_sys_profile('DEFAULT',
'T_TYPE_ADM') = 'N' 
	and mov.movement_type != 'ADM' ) 
or tag_utils_get_sys_profile('DEFAULT',
'T_TYPE_ADM') = 'Y')
}

OIDPEXIM_FIND_RGAGY{
select
	agy_loc_id as code,
	description as description 
from
agency_locations 
where
agy_loc_id not in ('OUT', 'TRN') 
and deactivation_date is null 
order by
1
}

OIDPEXIM_GET_EXTIN_CUR{
select
	CREATE_USER_ID,
	movement_time
from
	offender_external_movements
where
	offender_book_id = :offender_book_id
	and movement_seq = :movement_seq
	and from_agy_loc_id = :from_agy_loc_id
	and to_agy_loc_id = :to_agy_loc_id
	and direction_code = 'IN'
		  
 }

OIDPEXIM_PROPOSED_MVMNTS_INSERT{
insert
	into
	offender_proposed_mvmnts 
	(  offender_book_id ,
	movement_seq ,
	from_agy_loc_id,
	to_agy_loc_id,
	movement_type,
	movement_reason,
	event_date ,
	move_by_date ,
	move_allow_date ,
	priority_code ,
	priority_assigned_by ,
	priority_assigned_date ,
	reason_text ,
	comment_text ,
	scheduled_trip_id ,
	judge ,
	alternate_agy_loc_id,
	algo_comment,
	user_define_flag1 ,
	user_define_flag2,
	tmp_group_id ,
	from_agy_seq ,
	to_agy_seq ,
	create_datetime ,
	create_user_id ,
	modify_datetime ,
	modify_user_id,
	seal_flag)
values(:offenderBookId,
	:movementSeq,
	:fromAgyLocId,
	:toAgyLocId,
	:movementType,
	:movementReason,
	:eventDate,
	:moveByDate,
	:moveAllowDate,
	:priorityCode,
	:priorityAssignedBy,
	:priorityAssignedDate,
	:reasonText,
	:commentText,
	:scheduledTripId,
	:judge,
	:alternateAgyLocId,
	:algoComment,
	:userDefineFlag1,
	:userDefineFlag2,
	:tmpGroupId,
	:fromAgySeq,
	:toAgySeq,
	:createDatetime,
	:createUserId,
	:modifyDatetime,
	:modifyUserId,
	:sealFlag)

}

OIDPEXIM_PROPOSED_MVMNTS_UPDATE{
update offender_proposed_mvmnts set comment_text = :commentText,event_date=:eventDate,move_by_date=:moveByDate,move_allow_date=:moveAllowDate where offender_book_id = :offenderBookId and movement_seq =:movementSeq

}

OIDPEXIM_OFFENDER_EXTERNAL_MVMNTS{

select * from offender_external_movements   where offender_book_id =:offenderBookId 

}
