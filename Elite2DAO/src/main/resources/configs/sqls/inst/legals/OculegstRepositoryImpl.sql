Get_UPDATE_CASE_REASON {
	SELECT  
        l.description description,
        l.update_reason_code code        
FROM 
	legal_update_usages u,
	legal_update_reasons l
WHERE
	u.legal_class = 'CASE'
AND     u.update_reason_code = l.update_reason_code
AND     l.reason_category NOT IN ('CASE_UNMERGE', 'CASE_MERGE')   
AND     u.active_flag = 'Y' 
AND     l.active_flag = 'Y'
ORDER BY 
        l.list_seq, 
        l.description	 
}

LOV_UPDATE_REASON_RG_SENT{
SELECT  
        s.description description,
        l.update_reason_code code,
        l.active_type status,
        l.reason_category category
FROM 
	sentence_update_reasons s,
	legal_update_reasons l
WHERE
	s.sentence_category = :category
AND     s.sentence_calc_type = :sentenceCalcType
AND     s.update_reason_code = l.update_reason_code
AND     l.reason_category NOT IN ('CASE_UNMERGE', 'CASE_MERGE')   
AND     s.active_flag = 'Y' 
AND     l.active_flag = 'Y'
ORDER BY 
        l.list_seq,
        s.description
}

Get_UPDATE_USER {
	 SELECT STAFF_ID,LAST_NAME,FIRST_NAME FROM STAFF_MEMBERS WHERE USER_ID =:name
}


GET_UPDATE_CONDITION_REASON {
SELECT  
        l.description description,
        l.update_reason_code code,
        l.active_type status,
        l.reason_category category
FROM 
	legal_update_usages u,
	legal_update_reasons l
WHERE
	u.legal_class = 'CONDITION'
AND     u.update_reason_code = l.update_reason_code
AND     l.reason_category NOT IN ('CASE_UNMERGE', 'CASE_MERGE')   
AND     u.active_flag = 'Y' 
AND     l.active_flag = 'Y'
ORDER BY 
        l.list_seq, 
        l.description
    }   