
OUISDIRE_FIND_NOMREGIONRG {
 	SELECT description, area_code code, list_seq FROM areas WHERE area_class = 'REGION' AND ( ( active_flag = 'Y' AND expiry_date IS NULL )) ORDER BY list_seq, area_code, description
}

OUISDIRE_FIND_CGFKSTSKSKILLTYPE {
 	select
	REF_CODE.CODE SKILL_TYPE 
	,
	REF_CODE.DESCRIPTION DSP_DESCRIPTION2 ,
	REF_CODE.LIST_SEQ DSP_LIST_SEQ2
from
	REFERENCE_CODES REF_CODE
where
	domain = 'STAFF_SKILLS'
		and ACTIVE_FLAG = 'Y'
order by
	REF_CODE.LIST_SEQ asc ,
	REF_CODE.DESCRIPTION asc ,
	REF_CODE.CODE asc
}

OUISDIRE_FIND_CGFKSTSKSUBTYPE {
 	SELECT ref_code1.code, ref_code1.description FROM reference_codes ref_code1 WHERE ( domain = 'SKL_SUB_TYPE' AND parent_code =:subType AND (ACTIVE_FLAG = 'Y') ) ORDER BY ref_code1.list_seq ASC, ref_code1.description ASC, ref_code1.code ASC
}

OUISDIRE_FIND_CGFKVMSSEXCODE {
 	SELECT ref_code.code, ref_code.description, ref_code.list_seq FROM reference_codes ref_code WHERE ( domain = 'SEX' AND ( active_flag = 'Y') ) ORDER BY ref_code.list_seq ASC, ref_code.description ASC, ref_code.code ASC
}

OUISDIRE_FIND_CGFKVMSAGENCYLOCATIONTYPE {
 	select
	REF_CODE1.CODE AGENCY_LOCATION_TYPE 
	,
	REF_CODE1.DESCRIPTION DSP_DESCRIPTION7 ,
	REF_CODE1.LIST_SEQ DSP_LIST_SEQ6
from
	REFERENCE_CODES REF_CODE1
where
	(domain = 'AGY_LOC_TYPE'
		and ACTIVE_FLAG = 'Y'
			)
order by
	REF_CODE1.LIST_SEQ asc ,
	REF_CODE1.DESCRIPTION asc ,
	REF_CODE1.CODE asc
}

OUISDIRE_FIND_CGFKVMSAGYLOCID {
 	SELECT al.agy_loc_id code, al.description DESCRIPTION FROM agency_locations al ORDER BY al.agency_location_type ASC, al.description ASC, al.abbreviation ASC
}

OUISDIRE_FIND_CGFKVMSCITY {
 	select
	REF_CODE2.CODE CITY 
	,
	REF_CODE2.DESCRIPTION DSP_DESCRIPTION5 ,
	REF_CODE2.LIST_SEQ DSP_LIST_SEQ5
from
	REFERENCE_CODES REF_CODE2
where
	(domain = 'CITY'
		and ACTIVE_FLAG = 'Y'
			)
order by
	REF_CODE2.LIST_SEQ asc ,
	REF_CODE2.DESCRIPTION asc ,
	REF_CODE2.CODE asc
}

OUISDIRE_FIND_CGFKVMSSCHEDULETYPE {
 	select
	REF_CODE5.CODE SCHEDULE_TYPE 
	,
	REF_CODE5.DESCRIPTION DSP_DESCRIPTION3 ,
	REF_CODE5.LIST_SEQ DSP_LIST_SEQ3
from
	REFERENCE_CODES REF_CODE5
where
	(domain = 'SCHEDULE_TYP'
		and ACTIVE_FLAG = 'Y'
		)
order by
	REF_CODE5.LIST_SEQ asc ,
	REF_CODE5.DESCRIPTION asc ,
	REF_CODE5.CODE asc
}

OUISDIRE_FIND_CGFKVMSPOSITION {
 	select
	REF_CODE3.CODE as position 
	,
	REF_CODE3.DESCRIPTION DSP_DESCRIPTION2 ,
	REF_CODE3.LIST_SEQ DSP_LIST_SEQ2
from
	REFERENCE_CODES REF_CODE3
where
	(domain = 'STAFF_POS'
		and ACTIVE_FLAG = 'Y'
		)
order by
	REF_CODE3.LIST_SEQ asc ,
	REF_CODE3.DESCRIPTION asc ,
	REF_CODE3.CODE asc
}

OUISDIRE_FIND_CGFKVMSROLE {
 	select
	REF_CODE6.CODE as role 
	,
	REF_CODE6.DESCRIPTION DSP_DESCRIPTION ,
	REF_CODE6.LIST_SEQ DSP_LIST_SEQ
from
	REFERENCE_CODES REF_CODE6
where
	(domain = 'STAFF_ROLE'
		and ACTIVE_FLAG = 'Y'
	)
order by
	REF_CODE6.LIST_SEQ asc ,
	REF_CODE6.DESCRIPTION asc ,
	REF_CODE6.CODE asc
}

OUISDIRE_VMS1_FIND_V_MEMBER_SKILLS {
 	SELECT STAFF_ID ,LAST_NAME ,FIRST_NAME ,SEX_CODE ,AGY_LOC_ID ,POSITION ,ROLE ,SCHEDULE_TYPE ,STATUS   FROM V_MEMBER_SKILLS
}
OUISDIRE_VMS1_INSERT_V_MEMBER_SKILLS {
	INSERT INTO V_MEMBER_SKILLS(STAFF_ID ,LAST_NAME ,FIRST_NAME ,SEX_CODE ,AGY_LOC_ID ,POSITION ,ROLE ,SCHEDULE_TYPE ,STATUS ) VALUES(:staffId ,:lastName ,:firstName ,:sexCode ,:agyLocId ,:position ,:role ,:scheduleType ,:status )
}

OUISDIRE_STSK_FIND_STAFF_SKILLS {
 	SELECT STAFF_ID ,SKILL_TYPE ,SUB_TYPE ,AS_OF_DATE ,STSK_COMMENT ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,PROGRAM_ID ,STAFF_SKILL_ID ,ACTIVE_FLAG ,EXPIRY_DATE ,SEAL_FLAG   FROM STAFF_SKILLS   where  STAFF_ID=:staffId
}
OUISDIRE_STSK_INSERT_STAFF_SKILLS {
insert into STAFF_SKILLS(STAFF_ID , SKILL_TYPE , SUB_TYPE , AS_OF_DATE , STSK_COMMENT , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , PROGRAM_ID , STAFF_SKILL_ID , ACTIVE_FLAG , EXPIRY_DATE , SEAL_FLAG ) values(:staffId , :skillType , :subType , :asOfDate , :stskComment , current_timestamp , :createUserId , null , :programId , :staffSkillId , :activeFlag , :expiryDate , :sealFlag )
}

OUISDIRE_HMPHONE_FIND_PHONES {
 	SELECT PHONE_ID ,OWNER_CLASS ,OWNER_ID ,OWNER_SEQ ,OWNER_CODE ,PHONE_TYPE ,PHONE_NO ,EXT_NO ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG FROM phones WHERE phone_id = (SELECT MAX(phone_id) FROM phones p2 WHERE p2.owner_class = 'ADDR' AND p2.phone_type = 'HOME' AND p2.owner_id IN (SELECT address_id FROM addresses WHERE owner_class = 'STF' AND owner_id = :staffId))
}
OUISDIRE_BUSPHONE_FIND_PHONES{
SELECT PHONE_ID ,OWNER_CLASS ,OWNER_ID ,OWNER_SEQ ,OWNER_CODE ,PHONE_TYPE ,PHONE_NO ,EXT_NO ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG FROM phones WHERE phone_id = (select max(q.phone_id) from phones q where q.owner_id = :staffId and owner_class= 'STF' and phone_type = 'BUS')
}
OUISDIRE_EMAIL_FIND_INTERNET_ADDRESSES {
 	SELECT INTERNET_ADDRESS_ID ,OWNER_CLASS ,OWNER_ID ,OWNER_SEQ ,OWNER_CODE ,INTERNET_ADDRESS_CLASS ,INTERNET_ADDRESS ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM INTERNET_ADDRESSES   where internet_address_id  = (select max(I.internet_address_id) from internet_addresses I  where I.owner_id = :staffId and owner_class= 'STF')  
}

OUISDIRE_VMS1_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM INTERNET_ADDRESSES I WHERE I.OWNER_ID = :STAFFID
}

OUISDIRE_VMS1_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM STAFF_SKILLS S WHERE S.STAFF_ID = :STAFFID
}

OUISDIRE_VMS1_WHENNEWRECORDINSTANCE_ {
	SELECT COUNT(*) FROM PHONES WHERE PHONE_ID = (SELECT MAX(PHONE_ID) FROM PHONES P2 WHERE P2.OWNER_CLASS = 'ADDR' AND P2.PHONE_TYPE = 'HOME' AND P2.OWNER_ID IN (SELECT ADDRESS_ID FROM ADDRESSES WHERE OWNER_CLASS = 'STF' AND OWNER_ID = :STAFFID))
}

OUISDIRE_CGFKCHK_VMS1_AGY_LOC_ID_F3c {
	select
	AL.DESCRIPTION,
	VA.CITY_CODE,
	VA.CITY_NAME
from
	AGENCY_LOCATIONS AL,
	V_AGENCY_ADDRESSES VA
where
	AL.AGY_LOC_ID = VA.AGY_LOC_ID
	and AL.AGY_LOC_ID = VA.AGY_LOC_ID
	and (VA.PRIMARY_FLAG = 'Y'
		or (VA.ADDRESS_ID = (
		select
			MAX(V2.ADDRESS_ID)
		from
			V_AGENCY_ADDRESSES V2
		where
			V2.AGY_LOC_ID = VA.AGY_LOC_ID)
		and not exists (
		select
			'X'
		from
			V_AGENCY_ADDRESSES V3
		where
			V3.PRIMARY_FLAG = 'Y'
			and V3.AGY_LOC_ID = VA.AGY_LOC_ID)))
}

OUISDIRE_CGUVCHK_STSK_PK_ { 
	select
	'1'
from
	STAFF_SKILLS
where
	STAFF_ID = :P_STAFF_ID
	and (SKILL_TYPE = :P_SKILL_TYPE
		or (SKILL_TYPE is null
			and :P_SKILL_TYPE is null ))
	and (SUB_TYPE = :P_SUB_TYPE
		or (SUB_TYPE is null
			and :P_SUB_TYPE is null ))
}

OUISDIRE_POST_QUERY_ {
	select
	REF.DESCRIPTION
from
	REFERENCE_CODES ref
where
	domain = 'CITY'
	and ACTIVE_FLAG = 'Y'
	and EXPIRED_DATE is null
	and CODE = :V_CITY
}

OUISDIRE_GET_CITYCODE{
select
	va.city_code
from
	agency_locations al,
	v_agency_addresses va
where
	al.agy_loc_id = :agyLocId
	and al.agy_loc_id = va.agy_loc_id
	and (va.primary_flag = 'Y'
		or (va.address_id = (
		select
			MAX(v2.address_id)
		from
			v_agency_addresses v2
		where
			v2.agy_loc_id = va.agy_loc_id)
		and not exists (
		select
			'X'
		from
			v_agency_addresses v3
		where
			v3.primary_flag = 'Y'
			and v3.agy_loc_id = va.agy_loc_id)))
			
}
