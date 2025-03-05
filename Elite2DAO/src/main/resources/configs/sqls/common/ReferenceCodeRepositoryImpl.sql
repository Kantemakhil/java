
FIND_REFERENCE_CODES_BY_DOMAIN {
    SELECT code, domain, description, parent_domain, parent_code, active_flag
	    FROM reference_codes
	   WHERE domain = :domain
}

FIND_REFERENCE_CODES_BY_DOMAIN_PARENT {
	SELECT code, domain, description, parent_domain, parent_code, active_flag
	FROM reference_codes
	WHERE domain = :domain AND parent_code = :parentCode
}


FIND_REFERENCE_CODE_BY_DOMAIN_CODE {
    SELECT code, domain, description, parent_domain, parent_code, active_flag
	    FROM reference_codes
	   WHERE domain = :domain AND code = :code
}

FIND_REFERENCE_CODE_BY_DOMAIN_PARENT_CODE {
    SELECT code, domain, description, parent_domain, parent_code, active_flag
	    FROM reference_codes
	   WHERE domain = :domain AND  parent_code = :parentCode AND code = :code
}


FIND_CNOTE_TYPES_BY_CASELOAD {
	SELECT DISTINCT rc.description, 
					work_type code, 
					rc.domain, 
					rc.PARENT_DOMAIN, 
					rc.PARENT_CODE, 
					rc.ACTIVE_FLAG
	FROM works w JOIN reference_codes rc on rc.code = w.work_type
	WHERE workflow_type = 'CNOTE'
	AND rc.domain = 'TASK_TYPE'
	AND w.caseload_type IN ( (:caseLoadType), 'BOTH')
	AND w.manual_select_flag ='Y'
	AND w.active_flag  = 'Y'
	AND rc.code  <> 'WR'
}

FIND_CNOTE_SUB_TYPES_BY_CASE_NOTE_TYPE {
	SELECT	rc.description,
			w.work_sub_type code, 
			rc.domain, rc.PARENT_DOMAIN, 
			rc.PARENT_CODE, 
			rc.ACTIVE_FLAG
	FROM works w join reference_codes rc on rc.code = w.work_sub_type
 	WHERE workflow_type = 'CNOTE'
	AND rc.domain = 'TASK_SUBTYPE'
	AND w.work_type = :caseNoteType
	AND w.manual_select_flag ='Y'
	AND w.active_flag  = 'Y'
}

FIND_REFERENCE_CODES_SEARCH_TYPE {
	select code ,description ,
	list_seq 
	from reference_codes 
	where domain = 'SEARCH_TYPE'
	and active_flag = 'Y' 
	and expired_date is null 
	order by list_seq
}
