OFFENDERS_T1_COUNT_ADDRESS_RECORDS{

 SELECT count(*) 
    FROM   addresses
    WHERE  owner_Class = 'OFF'
    AND    Owner_id = :offENDer_id 
}

 OFFENDERS_T1_COUNT_PHONE_RECORDS{
   SELECT count(*)
    FROM   phones
    WHERE  owner_Class = 'OFF'
    AND    Owner_id = :offENDer_id 
}

OFFENDERS_T1_COUNT_INTERNET_ADDRESSES{
   SELECT count(*) 
    FROM   internet_addresses
    WHERE  owner_Class = 'OFF'
    AND    Owner_id = :offENDer_id 
}