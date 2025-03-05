CASELOAD_TYPE_CASELOAD_CUR { 
	SELECT c.trust_accounts_flag, c.payroll_flag, c.commissary_flag
        FROM caseloads c, caseload_agency_locations cal
       WHERE c.caseload_id = cal.caseload_id
         AND cal.agy_loc_id NOT IN ('OUT', 'TRN')
         AND cal.agy_loc_id = :p_agy_loc_id

}