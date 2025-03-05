OFFENDER_CASE_DETAILS{
SELECT CASE_TYPE,CASE_STATUS,CASE_SEQ FROM OFFENDER_CASES WHERE  OFFENDER_BOOK_ID=:offenderBookId 
}
OFFENDER_SENTENCES_DETAILS{
SELECT SENTENCE_CATEGORY,SENTENCE_CALC_TYPE FROM offender_sentences WHERE OFFENDER_BOOK_ID=:offenderBookId 
}
FIND_FACILITY_FOR_OFFENDER{
 select agy_loc_id,description,agency_location_type from agency_locations Where AGENCY_LOCATION_TYPE=:agyLocationId
}

GET_OFFENDER_PESONAL_ATTRIBUTES{
	select profile_type,profile_code from OFFENDER_PROFILE_DETAILS where offender_book_id=:offenderBookId and profile_code is not null
}

GET_MOVEMENT_REASON_CODE{
	SELECT profile_code,description,profile_value FROM SYSTEM_PROFILES WHERE PROFILE_TYPE='TIJ_DEF_VAL'
}

FIND_OFFENDER_ATTRIBUTES{
SELECT OFFENDER_SYSTEM_TABLE,OFFENDER_SYSTEM_TABLE_COLUMN,OFFENDER_LABEL,DOMAIN_CODE,DOMAIN_VALUE,UNIQUE_ID FROM OFFENDER_CONFIGURATION
}
FIND_REF_TABLE{
SELECT OFFENDER_TABLE,OFFENDER_TBL_COL1,OFFENDER_TBL_COL2 FROM FACILITY_PLACEMENT WHERE OFFENDER_DOMAIN=:domainCode 
}
GET_ATTRIBUTE_MAPPING_CODE{
select SOURCE_SYSTEM,SOURCE_SYSTEM_TBL_COL,SOURCE_DOMAIN,OFFENDER_TABLE,OFFENDER_TBL_COL1,OFFENDER_TBL_COL2 from facility_placement where offender_code=:offenderCode and offender_domain=:offenderDomain
 and mapped_for='F'
}
GET_HOUSING_CRITERIA{
select source_system,source_system_table,source_system_tbl_col,source_domain from facility_placement where offender_code=:offenderCode and offender_domain=:offenderDomain and mapped_for='H'
}

GET_LAST_QUERY{
 SELECT AGY_LOC.AGY_LOC_ID AS AGY_LOC_ID, AGY_LOC.DESCRIPTION AS DESCRIPTION, SUM(AGY_INT_LOC.CAPACITY - AGY_INT_LOC.NO_OF_OCCUPANT) AS NO_OF_AVAILABLE FROM AGENCY_INTERNAL_LOCATIONS AGY_INT_LOC INNER JOIN AGENCY_LOCATIONS AGY_LOC ON AGY_INT_LOC.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID WHERE AGY_INT_LOC.AGY_LOC_ID IN(:agencyLocIds) AND AGY_INT_LOC.unit_type IS NOT NULL AND AGY_INT_LOC.INTERNAL_LOCATION_Id NOT IN (Select PARENT_INTERNAL_LOCATION_ID from AGENCY_INTERNAL_LOCATIONS WHERE AGY_LOC_ID IN(:agencyLocIds) and PARENT_INTERNAL_LOCATION_ID is not null) GROUP BY AGY_LOC.AGY_LOC_ID, AGY_LOC.DESCRIPTION 
 }
 
OIDARFPL_OFFENDER_PERSONAL_DETAILS_CODE{
select
	vhb.description as name ,vhb.profileDesc as code 
from
	offender_profile_details opd,
	(select
	O.profile_type  ,
	O.description as profileDesc ,
	O.code_value_type  ,
	OB.description ,
	OB.profile_code ,
	O.profile_category 
from
	profile_types O,
	profile_codes OB
where
	OB.profile_type = O.profile_type ) as vhb
where  
	opd.offender_book_id  = :offenderBookId and vhb.profile_code =opd.profile_code and 
	vhb.profile_type =opd.profile_type and opd.caseload_type =:caseloadType and vhb.profile_category =:profileCategory
}
OIDARFPL_OFFENDER_PERSONAL_DETAILS_TEXT{
select
		pt.description as code ,
		opd.profile_code as name
	from
		profile_types pt,
		offender_profile_details opd
	where
		opd.profile_type  = pt.profile_type  and opd.offender_book_id =:offenderBookId 
		and opd.profile_code !='' and opd.profile_code is not null
		 and opd.caseload_type =:caseloadType and 	 pt.active_flag ='Y'and
		pt.code_value_type ='TEXT' and  pt.profile_category =:profileCategory
}