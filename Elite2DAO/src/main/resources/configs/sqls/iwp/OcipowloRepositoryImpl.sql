
OCIPOWLO_FIND_CGFKSTAFFLR1DSPDESCRIPTION {
   SELECT
    agy_loc.description, 
    agy_loc.agy_loc_id    CODE
FROM
    agency_locations agy_loc
WHERE
    agy_loc.agency_location_type = 'COMM'
    AND EXISTS (
        SELECT
            '1'
        FROM
            caseload_agency_locations cal
        WHERE
            cal.agy_loc_id = agy_loc.agy_loc_id
            AND cal.caseload_id = :CASELOAD_ID
    ) 
ORDER BY
    agy_loc.list_seq,
    agy_loc.description

}

OCIPOWLO_FIND_CGFKVOFFDETDSPDESCRIPT3 {
select
	ref_code.description description,
	ref_code.code as position
from
	reference_codes ref_code
where
	domain = 'STAFF_POS'
	and active_flag = 'Y'
	and expired_date is null

}

OCIPOWLO_FIND_CGFKVOFFDETDSPDESCRIPT2 {
SELECT
    ref_code2.description   ,
    ref_code2.code          
FROM
    reference_codes ref_code2
WHERE
    domain = 'STAFF_ROLE'
    AND active_flag = 'Y'
    AND expired_date IS NULL

}

OCIPOWLO_FIND_CGFKVOFFDETDSPDESCRIPTION {
 SELECT
    ref_code1.description   dsp_description,
    ref_code1.code          schedule_type
FROM
    reference_codes ref_code1
WHERE
    domain = 'SCHEDULE_TYP'
    AND active_flag = 'Y'
    AND expired_date IS NULL

}

OCIPOWLO_VOFFDET_FIND_V_STAFF_LOCATION {
 SELECT
    from_date,
    hours_per_week,
    schedule_type,
    role,
    position,
    cal_agy_loc_id,
    sex_code,
    name,
    staff_id,
    supervisor_agy_loc_id
FROM
    v_staff_location
WHERE
    cal_agy_loc_id =:AGY_LOC_ID
ORDER BY
    name
 	
}
OCIPOWLO_VASSOFF_FIND_V_ASSIGNED_OFFENDERS {
select O.OFFENDER_ID_DISPLAY , OB.OFFENDER_BOOK_ID , O.LAST_NAME , O.FIRST_NAME , O.MIDDLE_NAME , O.SEX_CODE , SUBSTR(OMS_INTAKE_GET_OFFENDER_SUPERVISION_U(CP.OFFENDER_BOOK_ID,:username), 1, 40) supervision_level , CP.START_DATE , CP.POSITION , CP.ROLE , CP.SAC_STAFF_ID , CP.CASE_PLAN_STATUS , CP.END_DATE , CP.CAL_AGY_LOC_ID, substr(tag_header_get_header_status_u(OB.active_flag, OB.community_active_flag, OB.status_reason, ( select mov_rsn.movement_reason_code from movement_reasons mov_rsn where substr(OB.status_reason::text, 1::numeric, instr(OB.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text and substr(OB.status_reason::text, instr(OB.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text), OB.comm_status, OB.in_out_status, OB.root_offender_id, OB.offender_book_id, :username)::text, 0, 100) as offender_status from OFFENDERS O, OFFENDER_BOOKINGS OB, CASE_PLANS CP where OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID and CP.CASE_PLAN_STATUS = 'ACTIVE' and O.OFFENDER_ID = OB.OFFENDER_ID and CP.SAC_STAFF_ID =:staff_id and CP.END_DATE is null and CP.POSITION =:position and CP.ROLE = :role and CP.CAL_AGY_LOC_ID =:calAgyLocId order by O.LAST_NAME, O.FIRST_NAME
}

OCIPOWLO_CGFKCHK_STAFF_LR1_STAFF_LR_AG_ {
	SELECT AGY_LOC.DESCRIPTION ,AGY_LOC.AGENCY_LOCATION_TYPE FROM   AGENCY_LOCATIONS AGY_LOC WHERE  AGY_LOC.AGY_LOC_ID = :CALAGYLOCID AND     AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM' AND EXISTS ( SELECT '1' FROM CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID AND CAL.CASELOAD_ID =  :CASELOADID)
}

OCIPOWLO_CGFKLKP_STAFF_LR1_STAFF_LR_AG_ {
	SELECT AGY_LOC.AGY_LOC_ID ,AGY_LOC.AGENCY_LOCATION_TYPE FROM   AGENCY_LOCATIONS AGY_LOC WHERE  (AGY_LOC.DESCRIPTION = :DSPDESCRIPTION OR (AGY_LOC.DESCRIPTION IS NULL AND :DSPDESCRIPTION IS NULL )) AND     AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM' AND EXISTS ( SELECT '1' FROM CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID AND CAL.CASELOAD_ID =  :CASELOADID)
}

OCIPOWLO_CGFKCHK_V_OFF_DET_V_OFF_DET_R_ {
	SELECT REF_CODE1.DESCRIPTION FROM   REFERENCE_CODES REF_CODE1 WHERE  REF_CODE1.CODE = :SCHEDULETYPE AND     DOMAIN = 'SCHEDULE_TYP'
}

OCIPOWLO_CGFKLKP_V_OFF_DET_V_OFF_DET_R_ {
	SELECT REF_CODE1.CODE FROM   REFERENCE_CODES REF_CODE1 WHERE  (REF_CODE1.DESCRIPTION = :DSPDESCRIPTION OR (REF_CODE1.DESCRIPTION IS NULL AND :DSPDESCRIPTION IS NULL )) AND     DOMAIN = 'SCHEDULE_TYP'
}

OCIPOWLO_CGFKCHK_V_OFF_DET_V_OFF_DE2_ {
	SELECT REF_CODE2.DESCRIPTION FROM   REFERENCE_CODES REF_CODE2 WHERE  REF_CODE2.CODE = :ROLE AND     DOMAIN = 'STAFF_ROLE'
}

OCIPOWLO_CGFKLKP_V_OFF_DET_V_OFF_DE2_ {
	SELECT REF_CODE2.CODE FROM   REFERENCE_CODES REF_CODE2 WHERE  REF_CODE2.DESCRIPTION = :DSPDESCRIPTION3 AND     DOMAIN = 'STAFF_ROLE'
}

OCIPOWLO_CGFKCHK_V_OFF_DET_V_OFF_DE3_ {
	SELECT REF_CODE.DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :POSITION AND     DOMAIN = 'STAFF_POS'
}

OCIPOWLO_CGFKLKP_V_OFF_DET_V_OFF_DE3_ {
	SELECT REF_CODE.CODE FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.DESCRIPTION = :DSPDESCRIPTION4 AND     DOMAIN = 'STAFF_POS'
}


OCDORASS_EXTOT_GET_COUNT_CASE_PLANS{

SELECT
    COUNT(*)
FROM
    case_plans
WHERE
    CAL_AGY_LOC_ID = :calAgyLocId
    AND FROM_DATE = :fromDate
    AND POSITION = :position
    AND ROLE = :role
    AND SAC_STAFF_ID = :staffId
    AND END_DATE IS NULL
    
    
 }
OCDORASS_CUR_GET_HP_CASE{
 SELECT COUNT(*)
        FROM offender_cases
       WHERE offender_book_id = :offenderBookId
         AND case_status = 'A'
         AND case_type = 'HP'
}

OCDORASS_CUR_GET_Y_CASE{
SELECT COUNT(*)
        FROM offender_cases
       WHERE offender_book_id = :offenderBookId
         AND case_status = 'A'
         AND case_type = 'Y'
         }
         
    OCDORASS_CUR_GET_A_CASE{     
              SELECT COUNT(*)
        FROM offender_cases
       WHERE offender_book_id = :offenderBookId
         AND case_status = 'A'
         AND case_type = 'A'
         
         }
         
 OCCIPOWLO_GET_IMAGE_DATA {     
 SELECT
    *
FROM
    images
WHERE
    image_object_type = 'OFF_BKG'
    AND image_view_type = 'FACE'
    AND active_flag = 'Y'
    AND image_object_id = :OFFENDERBOOKID
    }