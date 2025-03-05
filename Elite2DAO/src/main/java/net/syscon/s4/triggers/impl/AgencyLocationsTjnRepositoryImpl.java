package net.syscon.s4.triggers.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.triggers.AgencyLocationsTjnRepository;

@Repository
public class AgencyLocationsTjnRepositoryImpl extends RepositoryBase implements AgencyLocationsTjnRepository {
	private static final Logger logger = LogManager.getLogger(AgencyLocationsTjnRepositoryImpl.class);

	@Override
	public Integer insertAgencyLocationsJn(final AgencyLocations agencyLocations, final String input) {
		final String sql = getQuery("INSERT_AGENCY_LOCATIONS_JN");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("input", input);
		inParamMap.put("USERNAME", agencyLocations.getCreateUserId());
		inParamMap.put("agy_loc_id", agencyLocations.getAgyLocId());
		inParamMap.put("description", agencyLocations.getDescription());
		inParamMap.put("agency_location_type", agencyLocations.getAgencyLocationType());
		inParamMap.put("district_code", agencyLocations.getDistrictCode());
		inParamMap.put("abbreviation", agencyLocations.getAbbreviation());
		inParamMap.put("deactivation_date", agencyLocations.getDeactivationDate());
		inParamMap.put("contact_name", agencyLocations.getContactName());
		inParamMap.put("print_queue", agencyLocations.getPrintQueue());
		inParamMap.put("jurisdiction_code", agencyLocations.getJurisdictionCode());
		inParamMap.put("bail_office_flag", agencyLocations.getBailOfficeFlag());
		inParamMap.put("list_seq", agencyLocations.getListSeq());
		inParamMap.put("housing_lev_1_code", agencyLocations.getHousingLev1Code());
		inParamMap.put("housing_lev_2_code", agencyLocations.getHousingLev2Code());
		inParamMap.put("housing_lev_3_code", agencyLocations.getHousingLev3Code());
		inParamMap.put("housing_lev_4_code", agencyLocations.getHousingLev4Code());
		inParamMap.put("property_lev_1_code", agencyLocations.getPropertyLev1Code());
		inParamMap.put("property_lev_2_code", agencyLocations.getPropertyLev2Code());
		inParamMap.put("property_lev_3_code", agencyLocations.getPropertyLev3Code());
		inParamMap.put("last_booking_no", agencyLocations.getLastBookingNo());
		inParamMap.put("commissary_privilege", agencyLocations.getCommissaryPrivilege());
		inParamMap.put("business_hours", agencyLocations.getBusinessHours());
		inParamMap.put("address_type", agencyLocations.getAddressType());
		inParamMap.put("active_flag", agencyLocations.getActiveFlag());
		inParamMap.put("disability_access_code", agencyLocations.getDisabilityAccessCode());
		inParamMap.put("intake_flag", agencyLocations.getIntakeFlag());
		inParamMap.put("sub_area_code", agencyLocations.getSubAreaCode());
		inParamMap.put("area_code", agencyLocations.getAreaCode());
		
		inParamMap.put("noms_region_code", agencyLocations.getNomsRegionCode());
		inParamMap.put("geographic_region_code", agencyLocations.getGeographicRegionCode());
		inParamMap.put("justice_area_code", agencyLocations.getJusticeAreaCode());
		inParamMap.put("create_user_id", agencyLocations.getCreateUserId());
		inParamMap.put("modify_user_id", agencyLocations.getModifyUserId());
		inParamMap.put("long_description", agencyLocations.getLongDescription());
		inParamMap.put("labcorp_client_id", agencyLocations.getLabcorpClientId());
		inParamMap.put("seal_flag", agencyLocations.getSealFlag());
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

}
