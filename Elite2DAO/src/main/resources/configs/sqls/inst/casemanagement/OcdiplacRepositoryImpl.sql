OCDIPLANPOP_STAFF_MEMBER_LIST_BY_AGYLOCID
{
select distinct * from (select
	 VSM2.staff_name as description,
	VSM2.sac_staff_id::text  as code,
	VSM2.user_id as userId,
	VSM2.SUSPENDED_FLAG  as SUSPENDED_FLAG,
	VSM2.CAL_AGY_LOC_ID as first_name,
	VSM2.DATE_TO as EXPIRED_DATE
from
	STAFF_MEMBERS_V2 VSM2
where
(coalesce(VSM2.DATE_TO ::text, '') = ''
		or '' <> 'NORMAL' ) 
order by
	VSM2.LAST_NAME ,
	VSM2.FIRST_NAME ) a
	}
OCDIPLANPOP_INSERT_CASE_PLAN_STAFF_ROLES
{
insert into oms_owner.case_plan_staff_roles (case_plan_staff_role_id, offender_book_id, case_plan_id, staff_id, case_plan_role, active_flag, start_date, end_date, cn_officer, cp_owner, create_user_id, create_datetime , modify_datetime, modify_user_id, seal_flag) values(nextval(' case_plan_staff_role_id_seq '), :offenderBookId, :casePlanId, :staffId::numeric, :casePlanRole, :activeFlag, :startDate, :endDate, :cnOfficer, :cpOwner, :createUserId, current_timestamp, null, null, null)
}

OCDIPLANPOP_UPDATE_CASE_PLAN_STAFF_ROLES
{
update oms_owner.case_plan_staff_roles set offender_book_id=:offenderBookId, case_plan_id=:casePlanId, staff_id=:staffId::numeric, case_plan_role = :casePlanRole, active_flag = :activeFlag,cp_owner = :cpOwner, start_date = :startDate, end_date = :endDate, cn_officer = :cnOfficer, modify_datetime = CURRENT_TIMESTAMP, modify_user_id = :modifyUserId, seal_flag = null where case_plan_staff_role_id =:casePlanStaffRoleId
}


OCDIPLANPOP_GET_ALL_CASE_PLAN_STAFF_ROLES
{
select
	case_plan_staff_role_id,
	offender_book_id,
	case_plan_id,
	staff_id::text as staffName ,
	case_plan_role,
	active_flag,
	start_date,
	end_date,
	cp_owner,
	cn_officer,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag
from
	oms_owner.case_plan_staff_roles
	where
	offender_book_id =:offender_book_id and case_plan_id =:case_plan_id 
}
OCDIPLANPOP_CHILD_DATA_CARRYING
{
select case_plan_staff_role_id, offender_book_id, case_plan_id, staff_id::text as staffName , case_plan_role, active_flag, start_date, end_date,cp_owner, cn_officer, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag from oms_owner.case_plan_staff_roles where offender_book_id =:offenderBookId and active_flag='Y' and case_plan_id =( select max(case_plan_id) from case_plan_staff_roles where offender_book_id =:offenderBookId)
}



