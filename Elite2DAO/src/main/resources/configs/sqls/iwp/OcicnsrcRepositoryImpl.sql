OCICNSRC_GET_ALL_STAFFNAMES_BY_CASELOAD
{
SELECT to_char(SM.STAFF_ID) CODE, concat( SM.LAST_NAME ,' ', SM.FIRST_NAME) as DESCRIPTION   FROM  STAFF_MEMBERS SM
WHERE   SM.STATUS = 'ACTIVE' and :workingcaseloadid in (SM.working_caseload_id,SM.assigned_caseload_id)
ORDER BY SM.STAFF_ID asc
}
OCICNSRC_GET_ALL_FACILITIES{
 	SELECT AGY_LOC.AGY_LOC_ID   CODE, AGY_LOC.DESCRIPTION DESCRIPTION  FROM   AGENCY_LOCATIONS AGY_LOC WHERE AGENCY_LOCATION_TYPE IS NOT NULL ORDER BY AGY_LOC_ID ,DESCRIPTION
}
OCICNSRC_GET_ALL_RECORDS{

select
vhb.*,
ocn.offender_book_id,
ocn.contact_date,
ocn.contact_time,
ocn.staff_id,
ocn.case_note_text,
ocn.amendment_flag,
ocn.iwp_flag,
ocn.check_box1,
ocn.check_box2,
ocn.check_box3,
ocn.check_box4,
ocn.check_box5,
ocn.event_id,
ocn.case_note_id,
ocn.note_source_code,
ocn.date_creation,
ocn.time_creation,
ocn.seal_flag,
ocn.object_type,
ocn.object_id,
ocn.create_datetime,
ocn.create_user_id,
ocn.modify_datetime,
ocn.modify_user_id,
	(select last_name || ' ' || first_name from staff_members sm where sm.staff_id = ocn.staff_id) as staff_name,
	 (SELECT description FROM reference_codes rc WHERE rc.code = ocn.case_note_type  and rc."domain" ='TASK_TYPE') AS case_note_type ,
    (SELECT description FROM reference_codes rc WHERE rc.code = ocn.case_note_sub_type and rc."domain" ='TASK_SUBTYPE') AS case_note_sub_type
from
	offender_case_notes ocn,
	(select
	O.OFFENDER_ID_DISPLAY  ,
	O.LAST_NAME ,
	O.FIRST_NAME ,
	OB.OFFENDER_BOOK_ID,
	O.OFFENDER_ID_DISPLAY,
	OB.agy_loc_id,
	OB.intake_agy_loc_id
from
	OFFENDERS O,
	OFFENDER_BOOKINGS OB
where
	OB.OFFENDER_ID = O.OFFENDER_ID ) as vhb,
	(select distinct  w.work_type,w.work_sub_type from WORKS w  join case_note_permissions cnp on w.work_id =cnp.work_id 
where 	WORKFLOW_TYPE = 'CNOTE' and cnp.role_id in (
		select
			ROLE_ID
		from
			STAFF_MEMBER_ROLES
		where
			staff_id = :staffId ) and (cnp.create_flag ='Y' or cnp.view_flag ='Y') and caseload_type in (:sourceCode,'BOTH')) as cnote
where
	ocn.staff_id = :staffId
	and ocn.contact_date::date between :fromdate and :todate
	and  vhb.OFFENDER_BOOK_ID = ocn.OFFENDER_BOOK_ID and 
 ( case when ('INST'=:sourceCode ) then (COALESCE(:facility,'') = '' or vhb.agy_loc_id =:facility) 
else (COALESCE(:facility,'') = '' or vhb.intake_agy_loc_id =:facility) end )	
and ocn.case_note_type =cnote.work_type and ocn.case_note_sub_type =cnote.work_sub_type
order by ocn.contact_date desc


}
OCICNSRC_GET_STAFFNAME{
SELECT  concat(last_name,' ',first_name)  FROM STAFF_MEMBERS WHERE staff_id =:staffId
}
OCICNSRC_CHECK_USER_STAFF_CLOG
{
select role_code  from staff_member_roles smr where staff_id =(select staff_id  from staff_members sm where user_id =:userID)
}