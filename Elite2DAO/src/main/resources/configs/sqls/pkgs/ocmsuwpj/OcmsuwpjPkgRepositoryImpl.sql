GET_PLACEMENT_DET_CUR{
SELECT  c.corporate_name cityName FROM   CORPORATES c WHERE c.corporate_id =:p_corporate_id
}
GET_PLACEMENT_ADDRESS_DET_CUR{
 select
	vca.house house,
	vca.address_line1 streetAddress,
	vca.prov_state_desc ,
	vca.prov_state_code ,
	vca.suite_number,
	vca.city_code ,
	vca.city_name ,
	vca.zip_postal_code zipPostalCode,
	vca.country country
from
	v_corporate_addresses vca
where
	vca.corporate_id =:p_corporate_id
	and vca.address_id =:p_service_address_id 
}
GET_PLACEMENT_ADDRESS_FOR_AGYLOCID
{
select 
vaa.house house,
vaa.address_line1 streetAddress,
	vaa.prov_state_desc ,
	vaa.prov_state_code ,
	vaa.city_code ,
	vaa.city_name ,
	vaa.suite_number,
vaa.zip_postal_code zipPostalCode,
vaa.country country 
from V_AGENCY_ADDRESSES vaa where agy_loc_id =:agylocId and  address_id =:addressId
}