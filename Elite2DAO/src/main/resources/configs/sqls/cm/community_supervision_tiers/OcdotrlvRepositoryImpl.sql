OCDOTRLV_FIND_OFFENDER_TIER_LEVEL{
select
    offender_tier_level_id,
	tier_level_code,
	record_creation_datetime,
	offender_book_id,
	assignment_date,
	assignment_reason,
	(select CONCAT(sm.last_name ,', ',sm.first_name)from staff_members sm where sm.staff_id = oftl.assigned_by_staff_id) as
	assigned_By,
	approve_flag,
	(select CONCAT(sm.last_name ,', ',sm.first_name)from staff_members sm where sm.staff_id = oftl.approved_by_staff_id) as
	approved_By,
	next_review_date,
	comment,
	create_datetime,
	assigned_by_staff_id,
	approved_by_staff_id,
	active_flag,
	deactivated_date
from
	offender_tier_levels oftl
where
	offender_book_id = :offenderBookId
	order by offender_tier_level_id desc

}

OCDOTRLV_INSERT_OFFENDER_TIER_LEVEL{
insert into offender_tier_levels(tier_level_code,offender_book_id,record_creation_datetime,assignment_date,assignment_reason,assigned_by_staff_id,approve_flag,approved_by_staff_id,comment,create_datetime,create_user_id,next_review_date,deactivated_date,active_flag)
	values(:tierLevel,:offenderBookId,CURRENT_TIMESTAMP,:dateAssigned,:assignmentReason,:assignedByStaffId,:approveFlag,:approvedByStaffId,:comment,CURRENT_TIMESTAMP,:createUserId,:nextReviewDate,:deactivatedDate,:activeFlag)
}
OCDOTRLV_UPDATE_OFFENDER_TIER_LEVEL{
	update offender_tier_levels set  deactivated_date = :deactivatedDate ,active_flag = :activeFlag ,approve_flag =:approveFlag,  assignment_date =:dateAssigned, assignment_reason = :assignmentReason, approved_by_staff_id =:approvedByStaffId,modify_datetime=CURRENT_TIMESTAMP,modify_user_id=:modifyUserId ,comment= :comment where offender_tier_level_id = :offenderTierLevelId and OFFENDER_BOOK_ID = :offenderBookId
}
OCDOTRLV_DELETE_OFFENDER_TIER_LEVEL{ 
	DELETE FROM offender_tier_levels WHERE OFFENDER_BOOK_ID = :offenderBookId and offender_tier_level_id = :offenderTierLevelId
}
OCDOTRLV_OFFENDER_TIER_LEVES_RECORD_GROUP{
select tier_level_code code, tier_level_desc description ,active_flag, list_seq from maintain_tier_levels
}

OCDOTRLV_DEFAULT_INTAKE_TIER_FLAG{
select tier_level_code tierLevel,review_days reviewDays  from maintain_tier_levels mtl where mtl.default_intake_tier_flag = 'Y'
}

OCDOTRLV_GET_STAFF_ID{
select staff_id from staff_members where user_id ='SYSTEM'
}

OCDOTRLV_GET_REVIEW_DAYS{
select review_days from maintain_tier_levels mtl where mtl.tier_level_code = :code
}

OCDOTRLV_GET_MAINTIER_DEFAULT_EVENTS{
select * from maintain_tier_default_events where tier_level_code = :tierLevelCode 
}

OCDOTRLV_GET_MAX_OFFENDER_TIER_LEVEL_ID{
select max(offender_tier_level_id) from offender_tier_levels where offender_book_id = :offenderBookId
}