
OCIPOWOF_FIND_CGFKVSWADSPDESCRIPTION4 {
 	select
	AL1.DESCRIPTION DSP_DESCRIPTION4 /* CG$FK */
	,
	AL1.LIST_SEQ DSP_LIST_SEQ4 ,
	AL1.AGY_LOC_ID AGY_LOC_ID
from
	AGENCY_LOCATIONS AL1
where
	exists (
	select
		1
	from
		STAFF_WORK_ASSIGNMENTS_V1 VSWA
	where
		:STAFFID = :STAFFID
		and VSWA.CAL_AGY_LOC_ID = AL1.AGY_LOC_ID )
order by
	AL1.LIST_SEQ asc ,
	AL1.AGY_LOC_ID asc ,
	AL1.DESCRIPTION asc
}

OCIPOWOF_FIND_CGFKVSWAORDERCODE {
 	select
	POT.ORDER_CODE ORDER_CODE /* CG$FK */
	,
	POT.ORDER_TYPE DSP_ORDER_TYPE ,
	POT.DESCRIPTION DSP_DESCRIPTION3 ,
	POT.LIST_SEQ DSP_LIST_SEQ3
from
	PROBATION_ORDER_TYPES POT
where
	POT.ORDER_TYPE = coalesce(:ORDERTYPE ,
	POT.ORDER_TYPE )
	and ACTIVE_FLAG = 'Y'
order by
	POT.LIST_SEQ asc ,
	POT.ORDER_CODE asc ,
	POT.DESCRIPTION asc
}

OCIPOWOF_FIND_CGFKVSWADSPDESCRIPTION2 {
 	select
	REF_CODE1.DESCRIPTION DSP_DESCRIPTION2 /* CG$FK */
	,
	REF_CODE1.LIST_SEQ DSP_LIST_SEQ2 ,
	REF_CODE1.CODE COMPONENT
from
	REFERENCE_CODES REF_CODE1
where
	(domain = 'ORD_WGHT_COM'
		and ACTIVE_FLAG = 'Y'
		)
order by
	REF_CODE1.LIST_SEQ asc
}

OCIPOWOF_FIND_CGFKVSWAORDERTYPE {
 	select
	REF_CODE.CODE ORDER_TYPE /* CG$FK */
	,
	REF_CODE.DESCRIPTION DSP_DESCRIPTION ,
	REF_CODE.LIST_SEQ DSP_LIST_SEQ
from
	REFERENCE_CODES REF_CODE
where
	(domain = 'PROB_ORD_TYP'
		and ACTIVE_FLAG = 'Y'
		)
order by
	REF_CODE.LIST_SEQ asc
}

OCIPOWOF_STAFF_FIND_STAFF_MEMBERS {
 	select
	STAFF_ID,
	ASSIGNED_CASELOAD_ID,
	WORKING_STOCK_LOC_ID,
	WORKING_CASELOAD_ID,
	USER_ID,
	BADGE_ID,
	LAST_NAME,
	FIRST_NAME,
	MIDDLE_NAME,
	ABBREVIATION,
	position,
	BIRTHDATE,
	TERMINATION_DATE,
	UPDATE_ALLOWED_FLAG,
	DEFAULT_PRINTER_ID,
	SUSPENDED_FLAG,
	SUPERVISOR_STAFF_ID,
	COMM_RECEIPT_PRINTER_ID,
	PERSONNEL_TYPE,
	AS_OF_DATE,
	EMERGENCY_CONTACT,
	role,
	SEX_CODE,
	STATUS,
	SUSPENSION_DATE,
	SUSPENSION_REASON,
	FORCE_PASSWORD_CHANGE_FLAG,
	LAST_PASSWORD_CHANGE_DATE,
	LICENSE_CODE,
	LICENSE_EXPIRY_DATE,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	TITLE,
	NAME_SEQUENCE,
	QUEUE_CLUSTER_ID,
	FIRST_LOGON_FLAG,
	SUFFIX,
	SEAL_FLAG
from
	STAFF_MEMBERS
where
	user_id = :userId
}
OCIPOWOF_CPLAN_FIND_CASE_PLANS {
 	select *
from
	CASE_PLANS /* where  */
}
OCIPOWOF_VASSOFF_FIND_V_ASSIGNED_OFFENDERS {
select O.OFFENDER_ID_DISPLAY , OB.OFFENDER_BOOK_ID , O.LAST_NAME , O.FIRST_NAME , O.MIDDLE_NAME , O.SEX_CODE , SUBSTR(OMS_INTAKE_GET_OFFENDER_SUPERVISION_U(CP.OFFENDER_BOOK_ID,:username), 1, 40) supervision_level , CP.START_DATE , CP.POSITION , CP.ROLE , CP.SAC_STAFF_ID , CP.CASE_PLAN_STATUS , CP.END_DATE , CP.CAL_AGY_LOC_ID, substr(tag_header_get_header_status_u(OB.active_flag, OB.community_active_flag, OB.status_reason, ( select mov_rsn.movement_reason_code from movement_reasons mov_rsn where substr(OB.status_reason::text, 1::numeric, instr(OB.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text and substr(OB.status_reason::text, instr(OB.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text), OB.comm_status, OB.in_out_status, OB.root_offender_id, OB.offender_book_id, :username)::text, 0, 100) as offender_status from OFFENDERS O, OFFENDER_BOOKINGS OB, CASE_PLANS CP where OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID and CP.CASE_PLAN_STATUS = 'ACTIVE' and O.OFFENDER_ID = OB.OFFENDER_ID and CP.SAC_STAFF_ID =:STAFF_ID and CP.END_DATE is null and cp.offender_book_id in ( select A.offender_book_id from ( select * from CASE_PLANS CP where CASE_PLAN_STATUS = 'ACTIVE' and END_DATE is null and SAC_STAFF_ID =:STAFF_ID and exists ( select 'Y' from V_STAFF_LOCATION where STAFF_ID = :STAFF_ID and position = CP.POSITION and role = CP.ROLE)) A, V_STAFF_LOCATION B where A.POSITION = B.POSITION and A.ROLE = B.ROLE and A.AGY_LOC_ID = B.CAL_AGY_LOC_ID and A.SAC_STAFF_ID = B.STAFF_ID ) order by O.LAST_NAME asc, O.FIRST_NAME asc, O.MIDDLE_NAME asc
}
OCIPOWOF_VSWA_EXECUTE_QUERRY_STAFF_WORK_ASSIGNMENTS_V1 {
 SELECT
    staff_id,
    staff_name,
    offender_id_display,
    offender_id,
    offender_name,
    last_name,
    first_name,
    book_id,
    line,
    order_type,
    order_code,
    description,
    component,
    agy_loc_id,
    order_req_expiry,
    sup_exp_rpt_due,
    view_order,
    cal_agy_loc_id,
    sac_staff_id,
    position,
    role,
    from_date,
    offender_book_id,
    sentence_seq,
    offender_book_id_request,
    charge_seq,
    request_seq,
    status,
    offass_id
FROM
    staff_work_assignments_v1
WHERE
    ( offender_book_id = :offenderbookid
      OR offender_book_id_request = :offenderbookid )
    AND staff_id = :sacstaffid
    AND NOT EXISTS (
        SELECT
            1
        FROM
            assignment_transfers ass
        WHERE
            ass.offass_id = staff_work_assignments_v1.offass_id
            AND ass.status_to IS NULL
            AND ass.sac_staff_id IS NULL
    )
ORDER BY
    view_order,
    last_name,
    first_name,
    order_type,
    order_code,
    component
}
OCIPOWOF_POTWTG_FIND_POT_WEIGHTINGS {
select *
from
	POT_WEIGHTINGS /* where  */
}

OCIPOWOF_CGFKCHK_C_PLAN_CAS_PLN_OB_FK_ {
	select
	OFF_BKG.ROOT_OFFENDER_ID ,
	OFF_NAME.OFFENDER_ID ,
	OFF_NAME.LAST_NAME ,
	OFF_NAME.FIRST_NAME ,
	OFF_NAME.MIDDLE_NAME ,
	OFF_NAME.OFFENDER_ID_DISPLAY
from
	OFFENDER_BOOKINGS OFF_BKG ,
	OFFENDERS OFF_NAME
where
	OFF_BKG.OFFENDER_BOOK_ID = :OFFENDERBOOKID
	and OFF_NAME.OFFENDER_ID = OFF_BKG.OFFENDER_ID
}

OCIPOWOF_CGFKCHK_VSWA_VSWA_REF_FK2_ {
	select
	REF_CODE.DESCRIPTION ,
	REF_CODE.LIST_SEQ
from
	REFERENCE_CODES REF_CODE
where
	REF_CODE.CODE = :ORDERTYPE
	and (domain = 'PROB_ORD_TYP'
		and ACTIVE_FLAG = 'Y'
			)
}

OCIPOWOF_CGFKCHK_VSWA_VSWA_REF_FK_ {
	select
	REF_CODE1.DESCRIPTION ,
	REF_CODE1.LIST_SEQ
from
	REFERENCE_CODES REF_CODE1
where
	REF_CODE1.CODE = :COMPONENT
	and (domain = 'ORD_WGHT_COM'
		and ACTIVE_FLAG = 'Y'
			)
}

OCIPOWOF_CGFKCHK_VSWA_VSWA_POT_FK_ {
	select
	POT.ORDER_TYPE ,
	POT.DESCRIPTION ,
	POT.LIST_SEQ
from
	PROBATION_ORDER_TYPES POT
where
	POT.ORDER_CODE = :ORDERCODE
	and POT.ORDER_TYPE = coalesce(:ORDERTYPE,
	POT.ORDER_TYPE)
	and ACTIVE_FLAG = 'Y'
}

OCIPOWOF_CGFKCHK_VSWA_VSWA_AGY_LOC_FK_ {
	select
	AL1.DESCRIPTION ,
	AL1.LIST_SEQ
from
	AGENCY_LOCATIONS AL1
where
	AL1.AGY_LOC_ID = :AGYLOCID
	and exists (
	select
		1
	from
		STAFF_WORK_ASSIGNMENTS_V1 VSWA
	where
		STAFF_ID = :STAFFID
		and VSWA.CAL_AGY_LOC_ID = AL1.AGY_LOC_ID)
}
OCIPOWOF_GETTING_POST_STAFF_DATA {
SELECT SLR.CAL_AGY_LOC_ID
                     ,SLR.SAC_STAFF_ID
                     ,SLR.POSITION
                     ,SLR.ROLE
                     ,SLR.FROM_DATE
                FROM  STAFF_LOCATION_ROLES SLR
                     ,STAFF_MEMBERS SM
               WHERE  SM.STAFF_ID = SLR.SAC_STAFF_ID
                 AND  SM.STAFF_ID  = :VSTAFFID
}
OCIPOWOF_GETTING_IMAGE_DATA{
SELECT
    *
FROM
    images
WHERE
    image_object_type = 'OFF_BKG'
    AND image_view_type = 'FACE'
    AND active_flag = 'Y'
    AND image_object_id = :offenderbookid
}

OCIPOWOF_GETTING_HP_COUNT{
 SELECT COUNT(*)
        FROM offender_cases
       WHERE offender_book_id = :offenderbookid
         AND case_status = 'A'
         AND case_type = 'HP'
}
OCIPOWOF_GETTING_Y_COUNT{
 SELECT COUNT(*)
        FROM offender_cases
       WHERE offender_book_id = :offenderbookid
         AND case_status = 'A'
         AND case_type = 'Y'
}

OCIPOWOF_GETTING_A_COUNT{

  SELECT COUNT(*)
        FROM offender_cases
       WHERE offender_book_id = :offenderbookid
         AND case_status = 'A'
         AND case_type = 'A'
}
OCIPOWOF_GETTING_OFFENDER_COUNT {
SELECT 
COUNT(*)
FROM 
  (SELECT *
   FROM CASE_PLANS CP
   WHERE
     CASE_PLAN_STATUS = 'ACTIVE' AND
     END_DATE IS NULL AND
     SAC_STAFF_ID =:STAFF_ID AND
     EXISTS 
       (SELECT 'Y'
        FROM V_STAFF_LOCATION
        WHERE
          STAFF_ID = :STAFF_ID AND
          POSITION = CP.POSITION AND
          ROLE = CP.ROLE)) A, V_STAFF_LOCATION B
WHERE
  A.POSITION=B.POSITION AND
  A.ROLE=B.ROLE AND
  A.AGY_LOC_ID=B.CAL_AGY_LOC_ID AND
  A.SAC_STAFF_ID=B.STAFF_ID 

}