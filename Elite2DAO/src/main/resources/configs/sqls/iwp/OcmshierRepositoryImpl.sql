
OCMSHIER_FIND_CGFKCALAGYLOCID {
 SELECT
 agy_loc.description  ,
    agy_loc.agy_loc_id    code
FROM
    agency_locations agy_loc
WHERE
    agy_loc.agy_loc_id IN (
        SELECT
            cal.agy_loc_id
        FROM
            caseload_agency_locations cal
        WHERE
            cal.caseload_id = :CASELOADID
    )
    AND agy_loc.agency_location_type = 'COMM'
ORDER BY
    agy_loc.list_seq,
    agy_loc.description

}


OCMSHIER_FIND_CGFKSTAFFLR1POSITION {
select
staff_pos1.code as position ,
staff_pos1.domain dsp_domain3,
staff_pos1.description dsp_description3
from
reference_codes staff_pos1
where
staff_pos1.domain = 'STAFF_POS'
and ( ( expired_date is null
and active_flag = 'Y' ) )
}

OCMSHIER_FIND_CGFKSTAFFLR1SCHEDULETYPE {
SELECT SCHEDULE_TYPE1.CODE  SCHEDULE_TYPE  /* CG$FK */        ,SCHEDULE_TYPE1.DOMAIN  DSP_DOMAIN2        ,SCHEDULE_TYPE1.DESCRIPTION  DSP_DESCRIPTION2 from
 	REFERENCE_CODES SCHEDULE_TYPE1 WHERE   SCHEDULE_TYPE1.DOMAIN = 'SCHEDULE_TYP' AND ((EXPIRED_DATE IS NULL       AND ACTIVE_FLAG = 'Y'  )  )

 	

}

OCMSHIER_FIND_CGFKSTAFFLR1ROLE {
SELECT STAFF_ROLE1.CODE as  ROLE  /* CG$FK */        ,STAFF_ROLE1.DOMAIN  DSP_DOMAIN        ,STAFF_ROLE1.DESCRIPTION  DSP_DESCRIPTION FROM 
 	REFERENCE_CODES STAFF_ROLE1 WHERE   STAFF_ROLE1.DOMAIN = 'STAFF_ROLE' AND ((EXPIRED_DATE IS NULL       AND ACTIVE_FLAG = 'Y'  )    )

}

OCMSHIER_FIND_STAFFLRDSPLASTNAME {
SELECT
    STAFF.LAST_NAME   ,
    STAFF.FIRST_NAME ,
    STAFF.BIRTHDATE ,
     STAFF.STAFF_ID   
   
FROM
    STAFF_MEMBERS STAFF
WHERE
    STAFF.STAFF_ID IN (
        SELECT
            SL.SAC_STAFF_ID
        FROM
            STAFF_LOCATION_ROLES SL
        WHERE
            SL.CAL_AGY_LOC_ID = :calAgyLocId
		)	
			
}

OCMSHIER_FIND_STAFFLR1DSPLASTNAME {
SELECT
    STAFF.LAST_NAME   ,
    STAFF.FIRST_NAME ,
    STAFF.BIRTHDATE ,
    to_char( STAFF.STAFF_ID  ) codes
   
FROM
    STAFF_MEMBERS STAFF
WHERE
    STAFF.STAFF_ID IN (
        SELECT
            SL.SAC_STAFF_ID
        FROM
            STAFF_LOCATION_ROLES SL
        WHERE
            SL.CAL_AGY_LOC_ID = :calAgyLocId
		)
			
}

OCMSHIER_FIND_STAFFLRPOSITION {
 SELECT
    staff_pos.description  description,
    staff_pos.code         code
FROM
    reference_codes staff_pos
WHERE
    staff_pos.domain = 'STAFF_POS'
    AND expired_date IS NULL
    AND active_flag = 'Y'
}

OCMSHIER_FIND_STAFFLRSCHEDULETYPE {
 SELECT
       schedule_type.description   description,
       schedule_type.code         code
FROM
    reference_codes schedule_type
WHERE
    schedule_type.domain = 'SCHEDULE_TYP'
    AND  expired_date IS NULL
            AND active_flag = 'Y' 

}

OCMSHIER_FIND_STAFFLRROLE {
 SELECT
    staff_role.description   description,
    staff_role.code          code 
FROM
    reference_codes staff_role
WHERE
    staff_role.domain = 'STAFF_ROLE'
    AND  expired_date IS NULL
            AND active_flag = 'Y' 
         
}

OCMSHIER_CAL_FIND_CASELOAD_AGENCY_LOCATIONS {
 SELECT
    caseload_id,
    agy_loc_id,
    update_allowed_flag,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id,
    seal_flag
FROM
    caseload_agency_locations
    WHERE  CASELOAD_AGENCY_LOCATIONS.CASELOAD_ID   = :caseload_id
}
OCMSHIER_CAL_INSERT_CASELOAD_AGENCY_LOCATIONS {
	INSERT INTO CASELOAD_AGENCY_LOCATIONS() VALUES(:)
}

OCMSHIER_STAFFLR_FIND_STAFF_LOCATION_ROLES {
SELECT
    a.cal_agy_loc_id,
    A.sac_staff_id,
    A.from_date,
    A.to_date,
    A.position,
    A.role,
    A.schedule_type,
    A.hours_per_week,
    A.supervisor_agy_loc_id,
    A.supervisor_staff_id,
    A.supervisor_from_date,
    A.supervisor_position,
    A.supervisor_role,
    A.staff_unit,
    A.create_datetime,
    A.create_user_id,
    A.modify_datetime,
    A.modify_user_id,
    A.seal_flag,
    b.last_name,
    b.first_name,
    b.birthdate
FROM
    (
        SELECT
            *
        FROM
            staff_location_roles slr
        WHERE
            slr.cal_agy_loc_id =:CASELOADID
    ) a,
    staff_members b
WHERE
    a.sac_staff_id = b.staff_id
    
 }
 
 OCMSHIER_STAFFLR1_FIND_STAFF_LOCATION_ROLES{
select
a.cal_agy_loc_id,
a.sac_staff_id,
a.from_date,
a.to_date,
a.position,
a.role,
a.schedule_type,
a.hours_per_week,
a.supervisor_agy_loc_id,
a.supervisor_staff_id,
a.supervisor_from_date,
a.supervisor_position,
a.supervisor_role,
a.staff_unit,
a.create_datetime,
a.create_user_id,
a.modify_datetime,
a.modify_user_id,
a.seal_flag,
b.last_name,
b.first_name,
b.birthdate
from
(
select
*
from
staff_location_roles slr
where
supervisor_staff_id = :sac_staff_id
and supervisor_position = :position
and supervisor_role = :role
and date_trunc('day', supervisor_from_date)::date = date_trunc('day',:from_date::date)
and coalesce(slr.to_date::text, '') = ''
and supervisor_agy_loc_id = :agy_loc_id ) a,
staff_members b
where
a.sac_staff_id = b.staff_id
  }
 
 OCMSHIER_REMOVE_OFFICER{
  update staff_location_roles set supervisor_staff_id = null , supervisor_position = null , supervisor_role = null , supervisor_from_date = null , 
 supervisor_agy_loc_id = null, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where
 sac_staff_id = :sacStaffId and position = :position and role = :role   AND date_TRUNC('D',from_date)::date = date_TRUNC('D',:fromDate::date) AND to_date is null 

  }
    

OCMSHIER_CGFKCHK_CAL_CSLD_AL_AGY_LOC_F_ {
	SELECT AGY_LOC.DESCRIPTION FROM   AGENCY_LOCATIONS AGY_LOC WHERE  AGY_LOC.AGY_LOC_ID = :AGYLOCID AND     AGY_LOC.AGY_LOC_ID  IN ( SELECT AGY_LOC.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID    = :CASELOADID) AND AGENCY_LOCATION_TYPE = 'COMM'
}

OCMSHIER_CGFKCHK_STAFF_LR_STAFF_LR_REF_ {
	SELECT STAFF_ROLE.DOMAIN ,STAFF_ROLE.DESCRIPTION FROM   REFERENCE_CODES STAFF_ROLE WHERE  STAFF_ROLE.CODE = :ROLE AND     STAFF_ROLE.DOMAIN ='STAFF_ROLE' AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR (:MODE = 'QUERY'))
}

OCMSHIER_CGFKCHK_STAFF_LR_STAFF_LR_2_ {
	SELECT SCHEDULE_TYPE.DOMAIN ,SCHEDULE_TYPE.DESCRIPTION FROM   REFERENCE_CODES SCHEDULE_TYPE WHERE  SCHEDULE_TYPE.CODE = :SCHEDULETYPE AND     SCHEDULE_TYPE.DOMAIN = 'SCHEDULE_TYP' AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR (:MODE = 'QUERY'))
}

OCMSHIER_CGFKCHK_STAFF_LR_STAFF_LR_3_ {
	SELECT STAFF_POS.DOMAIN ,STAFF_POS.DESCRIPTION FROM   REFERENCE_CODES STAFF_POS WHERE  STAFF_POS.CODE = :POSITION AND     STAFF_POS.DOMAIN = 'STAFF_POS' AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR (:MODE = 'QUERY'))
}

OCMSHIER_CGFKCHK_STAFF_LR_STAFF_LR_STA_ {
	SELECT STAFF.LAST_NAME ,STAFF.FIRST_NAME ,STAFF.BIRTHDATE FROM   STAFF_MEMBERS STAFF WHERE  STAFF.STAFF_ID = :SACSTAFFID AND     STAFF.STAFF_ID  IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE  SL.CAL_AGY_LOC_ID = :AGYLOCID)
}

OCMSHIER_CGFKLKP_STAFF_LR_STAFF_LR_STA_ {
	SELECT STAFF.STAFF_ID FROM   STAFF_MEMBERS STAFF WHERE  (STAFF.LAST_NAME = :DSPLASTNAME OR (STAFF.LAST_NAME IS NULL AND :DSPLASTNAME IS NULL )) AND    (STAFF.FIRST_NAME = :DSPFIRSTNAME OR (STAFF.FIRST_NAME IS NULL AND :DSPFIRSTNAME IS NULL )) AND    (STAFF.BIRTHDATE = :DSPBIRTHDATE OR (STAFF.BIRTHDATE IS NULL AND :DSPBIRTHDATE IS NULL )) AND     STAFF.STAFF_ID  IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE  SL.CAL_AGY_LOC_ID = :AGYLOCID)
}

OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR_RE_ {
	SELECT STAFF_ROLE1.DOMAIN ,STAFF_ROLE1.DESCRIPTION FROM   REFERENCE_CODES STAFF_ROLE1 WHERE  STAFF_ROLE1.CODE = :ROLE AND     STAFF_ROLE1.DOMAIN = 'STAFF_ROLE' AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR (:MODE = 'QUERY'))
}

OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR2_ {
	SELECT SCHEDULE_TYPE1.DOMAIN ,SCHEDULE_TYPE1.DESCRIPTION FROM   REFERENCE_CODES SCHEDULE_TYPE1 WHERE  SCHEDULE_TYPE1.CODE = :SCHEDULETYPE AND     SCHEDULE_TYPE1.DOMAIN = 'SCHEDULE_TYP' AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR (:MODE = 'QUERY'))
}

OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR3_ {
	SELECT STAFF_POS1.DOMAIN ,STAFF_POS1.DESCRIPTION FROM   REFERENCE_CODES STAFF_POS1 WHERE  STAFF_POS1.CODE = :POSITION AND     STAFF_POS1.DOMAIN = 'STAFF_POS' AND ((EXPIRED_DATE IS NULL AND ACTIVE_FLAG = 'Y' ) OR (:MODE = 'QUERY'))
}

OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR_ST_ {
	SELECT STAFF1.LAST_NAME ,STAFF1.FIRST_NAME ,STAFF1.BIRTHDATE FROM   STAFF_MEMBERS STAFF1 WHERE  STAFF1.STAFF_ID = :SACSTAFFID AND     STAFF1.STAFF_ID  IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE  SL.CAL_AGY_LOC_ID = :AGYLOCID)
}

OCMSHIER_CGFKLKP_STAFF_LR1_STAFF_LR_ST_ {
	SELECT STAFF1.STAFF_ID FROM   STAFF_MEMBERS STAFF1 WHERE  (STAFF1.LAST_NAME = :DSPLASTNAME OR (STAFF1.LAST_NAME IS NULL AND :DSPLASTNAME IS NULL )) AND    (STAFF1.FIRST_NAME = :DSPFIRSTNAME OR (STAFF1.FIRST_NAME IS NULL AND :DSPFIRSTNAME IS NULL )) AND    (STAFF1.BIRTHDATE = :DSPBIRTHDATE OR (STAFF1.BIRTHDATE IS NULL AND :DSPBIRTHDATE IS NULL )) AND     STAFF1.STAFF_ID  IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE  SL.CAL_AGY_LOC_ID = :AGYLOCID)
}
