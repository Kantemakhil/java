GET_V_NUMROWS{ 
  SELECT count(*) FROM  Reference_codes WHERE  Code = :VISIT_STATUS AND    DOMAIN = 'VIS_STS'
}