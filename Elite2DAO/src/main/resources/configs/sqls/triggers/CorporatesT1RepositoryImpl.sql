CORPORATES_T1_COUNT_ADDRESS_RECORDS{

 SELECT count(*) 
    FROM   addresses
    where  owner_Class = 'CORP'
    AND    Owner_id = :corporate_id 
}

CORPORATES_T1_COUNT_PHONE_RECORDS{
   SELECT count(*) 
    FROM   phones
    where  owner_Class = 'CORP'
    AND    Owner_id = :corporate_id 
}

CORPORATES_T1_COUNT_INTERNET_ADDRESSES{
  SELECT count(*) 
    FROM   internet_addresses
    where owner_Class = 'CORP'
    AND    Owner_id = :corporate_id 
}