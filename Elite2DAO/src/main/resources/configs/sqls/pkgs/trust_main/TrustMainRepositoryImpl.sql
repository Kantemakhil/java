
TRUST_HEADER_QUERY_GET_APPLN_CODE{
 SELECT appln_code FROM oms_modules WHERE module_name = :P_FORM_NAME
 }      
 
TRUST_HEADER_QUERY_GET_CASELOADS_DETAILS{
 select c.trust_accounts_flag, c.commissary_flag, c.payroll_flag, c.trust_caseload_id, c.commissary_trust_caseload, c.trust_commissary_caseload, c.payroll_trust_caseload, c.caseload_type from caseloads c where c.caseload_id = :P_CASELOAD_ID      
}       
       