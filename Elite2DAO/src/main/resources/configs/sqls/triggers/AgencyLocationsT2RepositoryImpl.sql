AGENCY_LOCATIONS_T2_ADDRESS_RECORDS{
SELECT count(*) FROM   addresses WHERE  owner_Class = 'AGY' AND    Owner_code = :agyLocId
}
AGENCY_LOCATIONS_T2_PHONE_RECORDS{
SELECT count(*) FROM   phones WHERE  owner_Class = 'AGY' AND    Owner_code = :agyLocId
}
AGENCY_LOCATIONS_T2_INTERNET_ADDRESSES{
SELECT count(*) into v_numrows FROM   internet_addresses WHERE  owner_Class = 'AGY'  AND    Owner_code = :agyLocId

}