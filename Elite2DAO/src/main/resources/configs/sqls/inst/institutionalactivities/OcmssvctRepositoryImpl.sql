OCMSSVCT_FIND_RGSTAFFNAMEINST {
 SELECT DISTINCT last_name DESCRIPTION, first_name, To_CHAR(staff_id) CODE FROM staff_members, staff_location_roles WHERE working_caseload_id IN ( SELECT caseload_id FROM caseloads WHERE caseload_type =:caseloadType ) AND SAC_STAFF_ID = STAFF_ID AND STATUS = 'ACTIVE' AND CAL_AGY_LOC_ID = :providerPartyId ORDER BY last_name, first_name
}

OCMSSVCT_FIND_RGSTAFFNAMECOMM {
 	SELECT DISTINCT to_char(tm.staff_id) code, sm.last_name description, sm.first_name FROM automation_teams t, team_staff_members tm, staff_members sm WHERE t.team_id = tm.team_id AND tm.staff_id = sm.staff_id AND t.team_id = :pproviderpartyid AND t.active_flag = 'Y' AND sm.status = 'ACTIVE' ORDER BY last_name, first_name 
}

OCMSSVCT_FIND_RGTEAMMEMBERS {
 SELECT DISTINCT sm.last_name, sm.first_name, to_char(sm.staff_id) staff_id FROM staff_members sm, team_staff_members tm, automation_teams t WHERE tm.team_id = t.team_id AND sm.staff_id = tm.staff_id AND sm.status = 'ACTIVE' AND t.team_id = :pproviderpartyid ORDER BY sm.last_name, sm.first_name 
}
	
OCMSSVCT_FIND_RGSTAFFNAMEINSTPROG {
 SELECT DISTINCT LAST_NAME DESCRIPTION, FIRST_NAME , To_CHAR(STAFF_ID) CODE FROM STAFF_MEMBERS SM , STAFF_LOCATION_ROLES WHERE WORKING_CASELOAD_ID IN ( SELECT CASELOAD_ID FROM CASELOADS WHERE CASELOAD_TYPE = 'INST' ) AND SAC_STAFF_ID = STAFF_ID AND CAL_AGY_LOC_ID = :providerPartyCode AND SM.STATUS = 'ACTIVE' AND EXISTS ( SELECT STAFF_ID FROM STAFF_SKILLS SS WHERE SS.STAFF_ID = SM.STAFF_ID AND SS.PROGRAM_ID = :programId AND COALESCE (SS.ACTIVE_FLAG , 'N' ) = 'Y' ) ORDER BY LAST_NAME , FIRST_NAME 
}

OCMSSVCT_FIND_RGSTAFFNAMECOMMPROG {
 SELECT DISTINCT to_char(tm.staff_id) code, sm.last_name description, sm.first_name FROM automation_teams t, team_staff_members tm, staff_members sm WHERE t.team_id = tm.team_id AND tm.staff_id = sm.staff_id AND t.team_id = :providerpartyid AND t.active_flag = 'Y' AND sm.status = 'ACTIVE' AND EXISTS ( SELECT staff_id FROM staff_skills ss WHERE ss.staff_id = sm.staff_id AND ss.program_id = :programid AND coalesce (ss.active_flag, 'N') = 'Y' ) ORDER BY last_name, first_name 
}

OCMSSVCT_CRSACTPTY_FIND_COURSE_ACTIVITY_PARTIES {
 SELECT CRS_ACTY_ID ,PARTY_ROLE ,PERSON_ID ,STAFF_ID ,REGISTRATION_TEXT ,CONTACT_TEXT ,CRS_ACTY_PARTY_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,FIRST_NAME ,LAST_NAME ,PARTY_ROLE_TEXT ,SEAL_FLAG FROM COURSE_ACTIVITY_PARTIES where  CRS_ACTY_ID = :crsActyId and STAFF_ID is not null
}
OCMSSVCT_CRSACTPTY_INSERT_COURSE_ACTIVITY_PARTIES {
insert into COURSE_ACTIVITY_PARTIES(CRS_ACTY_ID , PARTY_ROLE , PERSON_ID , STAFF_ID , REGISTRATION_TEXT , CONTACT_TEXT , CRS_ACTY_PARTY_ID , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , FIRST_NAME , LAST_NAME , PARTY_ROLE_TEXT , SEAL_FLAG ) values(:crsActyId , :partyRole , :personId , :staffId , :registrationText , :contactText , :crsActyPartyId , current_timestamp , :createUserId , null , :firstName , :lastName , :partyRoleText , :sealFlag )
}

OCMSSVCT_CRSACTPTY_UPDATE_COURSE_ACTIVITY_PARTIES {
update COURSE_ACTIVITY_PARTIES set CRS_ACTY_ID = :crsActyId , PARTY_ROLE = :partyRole , PERSON_ID = :personId , STAFF_ID = :staffId , REGISTRATION_TEXT = :registrationText , CONTACT_TEXT = :contactText , CRS_ACTY_PARTY_ID = :crsActyPartyId , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , FIRST_NAME = :firstName , LAST_NAME = :lastName , PARTY_ROLE_TEXT = :partyRoleText , SEAL_FLAG = :sealFlag where CRS_ACTY_PARTY_ID = :crsActyPartyId
}

OCMSSVCT_CRSACTPTY_DELETE_COURSE_ACTIVITY_PARTIES { 
DELETE FROM COURSE_ACTIVITY_PARTIES where CRS_ACTY_PARTY_ID  = :crsActyPartyId
}


OCMSSVCT_CRS_ACT_PTY_PREINSERT {
SELECT NEXTVAL('CRS_ACTY_PARTY_ID')
}

OCMSSVCT_EXT_CON_PREINSERT {
SELECT NEXTVAL('CRS_ACTY_PARTY_ID')
}

OCMSSVCT_STAFF_RECORD {
select DISTINCT LAST_NAME , FIRST_NAME , STAFF_ID from staff_members where staff_id =:staffId
}

OCMSSVCT_EXTCONPTY_FIND_COURSE_ACTIVITY_PARTIES {
SELECT CRS_ACTY_ID ,PARTY_ROLE ,PERSON_ID ,STAFF_ID ,REGISTRATION_TEXT ,CONTACT_TEXT ,CRS_ACTY_PARTY_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,FIRST_NAME ,LAST_NAME ,PARTY_ROLE_TEXT ,SEAL_FLAG   FROM COURSE_ACTIVITY_PARTIES   where  CRS_ACTY_ID = :crsActyId and STAFF_ID is  null
}

OCMSSVCT_CONSTRAINT_VALIDATIONS {
select tc.table_name from information_schema.table_constraints as tc join information_schema.key_column_usage as kcu on tc.constraint_name = kcu.constraint_name and tc.table_schema = kcu.table_schema join information_schema.constraint_column_usage as ccu on ccu.constraint_name = tc.constraint_name and ccu.table_schema = tc.table_schema where tc.constraint_type = 'FOREIGN KEY' and upper(ccu.table_name)= 'COURSE_ACTIVITY_PARTIES' and upper(tc.CONSTRAINT_NAME)= :CONSTRAINTNAME and upper(tc.constraint_schema)= 'OMS_OWNER'
}
