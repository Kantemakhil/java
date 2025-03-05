
OIDPWAIT_FIND_RGPROGRAMSERVICES {
    SELECT DESCRIPTION DESCRIPTION ,PROGRAM_CODE CODE, PROGRAM_ID  FROM PROGRAM_SERVICES WHERE PROGRAM_CATEGORY = 'ACP' AND PROGRAM_CLASS = 'PRG' AND ( ACTIVE_FLAG = 'Y'  ) ORDER BY  DESCRIPTION ,PROGRAM_CODE}

OIDPWAIT_FIND_RGREGION {
 	SELECT REF_CODE.DESCRIPTION DESCRIPTION ,REF_CODE.AREA_CODE CODE   FROM AREAS REF_CODE  WHERE AREA_CLASS = 'REGION'    AND ACTIVE_FLAG = 'Y'  ORDER BY LIST_SEQ , DESCRIPTION ,CODE
}

OIDPWAIT_FIND_RGAREAS {
select description DESCRIPTION, area_code code from ( select c.area_class, c.area_code, c.description, c.list_seq, c.area_type, p.parent_area_class, p.parent_area_code, p.parent_description, p.parent_list_seq from ( select area_class parent_area_class, area_code parent_area_code, description parent_description, list_seq parent_list_seq from areas where coalesce(parent_area_code::text, '') = '' and area_class = 'REGION' ) p left outer join ( select area_type, area_class, area_code, description, parent_area_code, list_seq from areas where (parent_area_code is not null and parent_area_code::text <> '') ) c on (p.parent_area_code = c.parent_area_code) union all select c.area_class, c.area_code, c.description, c.list_seq, c.area_type, null, null, null, null from areas c where area_class = 'REGION' and coalesce(parent_area_code::text, '') = '' ) alias5 where area_type = :CLT and ( coalesce(:RC::text, '') = '' or ( :RC = parent_area_code and parent_area_class = 'REGION' ) ) order by description
}

OIDPWAIT_FIND_RGAGYLOCS {
select al.description DESCRIPTION , al.agy_loc_id CODE from agency_locations al where al.agency_location_type =:CLT and al.active_flag = 'Y' and coalesce (al.deactivation_date::timestamp::text,'') = '' and ( coalesce (:AC,'') ='' or :AC = al.area_code ) and ( coalesce (:RC,'') = '' or :RC = al.noms_region_code ) order by al.list_seq, al.description
}

OIDPWAIT_FIND_RGPSPRGAVAIL {
SELECT REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE FROM REFERENCE_CODES REF_CODE WHERE DOMAIN = 'PS_PRG_AVAIL' AND ( ACTIVE_FLAG = 'Y' ) ORDER BY LIST_SEQ, DESCRIPTION, CODE }


OIDPWAIT_FIND_RGRESTRICTTEAMS {
SELECT T.DESCRIPTION, T.TEAM_CODE  CODE,T.TEAM_ID TEAM_ID FROM TEAMS T, TEAM_FUNCTIONS TF WHERE T.ACTIVE_FLAG = 'Y' AND T.TEAM_ID = TF.TEAM_ID AND TF.FUNCTION_TYPE = 'ACP' AND T.AGY_LOC_ID IN ( SELECT AGY_LOC_ID FROM AGENCY_LOCATIONS WHERE NOMS_REGION_CODE = :RC OR AREA_CODE = :AC ) ORDER BY T.LIST_SEQ, T.DESCRIPTION
}

OIDPWAIT_FIND_RGALLTEAMS {
 	SELECT t.description description, t.team_code code, t.team_id team_id FROM automation_teams t, team_functions tf WHERE t.active_flag = 'Y' AND t.team_id = tf.team_id AND tf.function_type = 'ACP' ORDER BY t.description
}

OIDPWAIT_VOFFPRGOBL_FIND_V_OFFENDER_PRG_OBLIGATIONS {
 SELECT * FROM V_OFFENDER_PRG_OBLIGATIONS WHERE STATUS IN ( 'SUSP', 'REF' ) AND EVENT_TYPE = 'ACP' AND ( ACTIVE_FLAG = 'Y' OR COMMUNITY_ACTIVE_FLAG = 'Y' ) AND
}
OIDPWAIT_FIND_OFFENDER_ID{
SELECT O.ROOT_OFFENDER_ID FROM OFFENDERS O, OFFENDER_BOOKINGS OB WHERE O.OFFENDER_ID = OB.OFFENDER_ID AND OB.OFFENDER_BOOK_ID = :OFFBOOKID
}

OIDPWAIT_VOFFPRGOBL_UPDATE_V_OFFENDER_PRG_OBLIGATIONS {
update OFFENDER_PRG_OBLIGATIONS set REFERRAL_DATE = :referralDate, SPECIAL_NEED_FLAG = :specialNeedFlag, COMMENT_TEXT = :commentText, AVAILABILITY_CODE = :availabilityCode, modify_datetime = current_timestamp , modify_user_id = :modifyUserId where OFFENDER_PRG_OBLIGATION_ID = :offenderPrgObligationId
}

OIDPWAIT_ASN_SER_TO_OFF_UPDATE{

update OFFENDER_PRG_OBLIGATIONS set STATUS = :status, STATUS_CHANGE_DATE = current_timestamp , modify_datetime = current_timestamp , modify_user_id = :modifyUserId where OFFENDER_PRG_OBLIGATION_ID = :offenderPrgObligationId and OFFENDER_BOOK_ID = :offenderBookId and PROGRAM_ID = :programId
}

OIDPWAIT_GET_CHECK_SUM{
select TAG_SCHEDULE_CHECK_SUM(coalesce (OPO.MODIFY_DATETIME, OPO.CREATE_DATETIME)) from OFFENDER_PRG_OBLIGATIONS OPO where OPO.OFFENDER_PRG_OBLIGATION_ID = :OFFPRGOBLD
}

OIDPWAIT_V_OFF_PRG_OBL_POSTQUERY_ {
	SELECT O.ROOT_OFFENDER_ID FROM OFFENDERS O, OFFENDER_BOOKINGS OB WHERE O.OFFENDER_ID = OB.OFFENDER_ID AND OB.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDPWAIT_FIND_COMM_DEFALULT{
SELECT DISTINCT a.description description, a.agy_loc_id code FROM team_staff_members t, agency_locations a, staff_user_accounts s, team_agency_loc ta WHERE t.staff_id = s.staff_id AND s.username = :caseloadid AND a.agy_loc_id = ta.agy_loc_id ANd ta.team_id = t.team_id AND a.active_flag = 'Y' AND a.deactivation_date IS NULL
}

OIDPWAIT_FIND_COMM_AREA_DEFALULT{
SELECT al.area_code CODE, al.noms_region_code DESCRIPTION FROM caseload_agency_locations ca, agency_locations al, areas a, areas r WHERE ca.caseload_id = :CASELOADID AND al.agy_loc_id = ca.agy_loc_id AND a.area_code = al.area_code AND a.area_class = 'AREA' AND a.area_code = al.noms_region_code AND a.area_class = 'REGION'
}

OIDPWAIT_CONSTRAINT_VALIDATIONS_TIMES{
SELECT AC1.TABLE_NAME FROM ALL_CONSTRAINTS AC1, ALL_CONSTRAINTS AC2 WHERE AC1.CONSTRAINT_NAME = :CONSTRAINTNAME AND AC2.TABLE_NAME = 'V_OFFENDER_PRG_OBLIGATIONS' AND AC1.CONSTRAINT_TYPE = 'R' AND AC2.CONSTRAINT_NAME = AC1.R_CONSTRAINT_NAME AND AC2.OWNER = AC1.R_OWNER AND AC2.CONSTRAINT_TYPE IN ('P', 'U')
}

GET_OLD_REC_OFFENDER_PRG_OBLIGATIONS{
select offender_prg_obligation_id, offender_book_id, program_id , status , offender_sent_cond_act_id, start_date, end_date, event_type, event_sub_type, comment_text, sentence_seq, length, length_unit, offender_sent_condition_id, referral_date, status_change_date, status_change_reason, special_need_flag, availability_code, obligation_source, create_datetime, create_user_id, modify_datetime , modify_user_id, seal_flag, referral_priority, decision_date from offender_prg_obligations where offender_prg_obligation_id = :offenderPrgObligationId
}
