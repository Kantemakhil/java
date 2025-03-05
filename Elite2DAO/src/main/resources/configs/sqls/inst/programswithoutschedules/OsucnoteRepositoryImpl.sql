
OSUCNOTE_FIND_RGWORKS {
-- 	SELECT ref_cd1.description description, 
-- 	ref_cd2.description work_sub_type,
-- 	ref_cd2.code codes,
--	works.work_type work_type,
-- 	to_char(works.work_id ) code 
-- 	FROM works, reference_codes ref_cd1, reference_codes ref_cd2 WHERE works.workflow_type = 'CNOTE' AND works.active_flag = 'Y' AND works.manual_select_flag = 'Y' AND DECODE( works.caseload_type, 'BOTH', :caseLoadType, works.caseload_type ) = :caseLoadType AND works.work_type = ref_cd1.code AND ref_cd1.domain = 'TASK_TYPE' AND works.work_sub_type = ref_cd2.code AND ref_cd2.domain = 'TASK_SUBTYPE' ORDER BY ref_cd1.list_seq ASC, ref_cd1.description ASC, ref_cd2.list_seq ASC, ref_cd2.description ASC
SELECT ref_cd1.description description, 
 	ref_cd2.description work_sub_type,
 	ref_cd2.code codes,
	works.work_type work_type,
 	to_char(works.work_id ) code 
 	FROM works, reference_codes ref_cd1, reference_codes ref_cd2 WHERE works.workflow_type = 'CNOTE' AND works.active_flag = 'Y' AND works.manual_select_flag = 'Y' AND CASE  works.caseload_type  WHEN 'BOTH' THEN  :caseLoadType  ELSE works.caseload_type  END = :caseLoadType AND works.work_type = ref_cd1.code AND ref_cd1.domain = 'TASK_TYPE' AND works.work_sub_type = ref_cd2.code AND ref_cd2.domain = 'TASK_SUBTYPE' ORDER BY ref_cd1.list_seq ASC, ref_cd1.description ASC, ref_cd2.list_seq ASC, ref_cd2.description ASC

}


OSUCNOTE_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OSUCNOTE_GET_DISPLAY_AUTO {
--	SELECT offd.last_name || ', ' || offd.first_name ||
--DECODE(offd.middle_name, NULL, NULL, ' ' || SUBSTR(offd.middle_name, 1,1) || '.' ) ||
--' (' || DECODE(oms_miscellaneous.get_profile_value ('DISPLAY', 'ID_DISPLAY'), 'Y', UPPER( LPAD( LTRIM ( offd.offender_id_display, '0' ), 10, '0')),
--offd.offender_id_display) || ')' names
--FROM offender_bookings , offenders offd
--WHERE offender_bookings.offender_book_id = :offenderBookId and offender_bookings.OFFENDER_ID =offd.OFFENDER_ID
select
	offd.last_name || ', ' || offd.first_name ||
	case
		when coalesce(offd.middle_name::text, '') = '' then null
		else ' ' || SUBSTR(offd.middle_name,
		1,
		1) || '.'
	end || ' (' ||
	case
		when oms_miscellaneous_get_profile_value('DISPLAY',
		'ID_DISPLAY')= 'Y' then UPPER( LPAD( LTRIM( offd.offender_id_display, '0' ), 10, '0'))
		else offd.offender_id_display
	end || ')' as names
from
	offender_bookings ,
	offenders offd
where
	offender_bookings.offender_book_id = :offenderBookId
	and offender_bookings.OFFENDER_ID = offd.OFFENDER_ID
}

OSUCNOTE_GET_DISPLAY_AUTO{
--SELECT offd.last_name || ', ' || offd.first_name ||
--DECODE(offd.middle_name, NULL, NULL, ' ' || SUBSTR(offd.middle_name, 1,1) || '.' ) ||
--' (' || DECODE(oms_miscellaneous.get_profile_value ('DISPLAY', 'ID_DISPLAY'), 'Y', UPPER( LPAD( LTRIM ( offd.offender_id_display, '0' ), 10, '0')),
--offd.offender_id_display) || ')' names
--FROM offender_bookings , offenders offd
--WHERE offender_bookings.offender_book_id = :offenderBookId and offender_bookings.OFFENDER_ID =offd.OFFENDER_ID
select
	offd.last_name || ', ' || offd.first_name ||
	case
		when coalesce(offd.middle_name::text, '') = '' then null
		else ' ' || SUBSTR(offd.middle_name,
		1,
		1) || '.'
	end || ' (' ||
	case
		when oms_miscellaneous_get_profile_value('DISPLAY',
		'ID_DISPLAY')= 'Y' then UPPER( LPAD( LTRIM( offd.offender_id_display, '0' ), 10, '0'))
		else offd.offender_id_display
	end || ')' as names
from
	offender_bookings ,
	offenders offd
where
	offender_bookings.offender_book_id = :offenderBookId::bigint
	and offender_bookings.OFFENDER_ID = offd.OFFENDER_ID
}

OSUCNOTE_GET_SUB_STRING{
select SUBSTR(:offName || ':' ||  :details, 1, 4000) from dual
}

OSUCNOTE_GET_STAFF_ID{
	select staff_id from staff_members where user_id = :userId
}
