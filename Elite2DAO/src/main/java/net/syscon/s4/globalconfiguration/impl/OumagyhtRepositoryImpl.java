package net.syscon.s4.globalconfiguration.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OumagyhtRepository;
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Class OumagyhtRepositoryImpl
 */
@Repository
public class OumagyhtRepositoryImpl extends RepositoryBase implements OumagyhtRepository {

	/**
	 * Creates new OumagyhtRepositoryImpl class Object
	 */
	public OumagyhtRepositoryImpl() {
		/*
		 * OumagyhtRepositoryImpl
		 */
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumagyhtRepositoryImpl.class);
	private final Map<String, FieldMapper> agencyLocationAmendmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("AMEND_DATETIME", new FieldMapper("amendDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("FIELD", new FieldMapper("field")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_LEV_1_CODE", new FieldMapper("propertyLev1Code"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("DISABILITY_ACCESS_CODE", new FieldMapper("disabilityAccessCode"))
			.put("BAIL_OFFICE_FLAG", new FieldMapper("bailOfficeFlag")).put("AREA_CODE", new FieldMapper("areaCode"))
			.put("GEOGRAPHIC_REGION_CODE", new FieldMapper("geographicRegionCode"))
			.put("HOUSING_LEV_1_CODE", new FieldMapper("housingLev1Code"))
			.put("COMMISSARY_PRIVILEGE", new FieldMapper("commissaryPrivilege"))
			.put("DEACTIVATION_DATE", new FieldMapper("deactivationDate"))
			.put("INTAKE_FLAG", new FieldMapper("intakeFlag"))
			.put("JURISDICTION_CODE", new FieldMapper("jurisdictionCode")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("HOUSING_LEV_3_CODE", new FieldMapper("housingLev3Code"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", new FieldMapper("description")).put("SUB_AREA_CODE", new FieldMapper("subAreaCode"))
			.put("LONG_DESCRIPTION", new FieldMapper("longDescription"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("BUSINESS_HOURS", new FieldMapper("businessHours")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("PROPERTY_LEV_2_CODE", new FieldMapper("propertyLev2Code"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("PRINT_QUEUE", new FieldMapper("printQueue")).put("ABBREVIATION", new FieldMapper("abbreviation"))
			.put("JUSTICE_AREA_CODE", new FieldMapper("justiceAreaCode"))
			.put("NOMS_REGION_CODE", new FieldMapper("nomsRegionCode"))
			.put("LABCORP_CLIENT_ID", new FieldMapper("labcorpClientId"))
			.put("CONTACT_NAME", new FieldMapper("contactName"))
			.put("HOUSING_LEV_2_CODE", new FieldMapper("housingLev2Code"))
			.put("LAST_BOOKING_NO", new FieldMapper("lastBookingNo")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("HOUSING_LEV_4_CODE", new FieldMapper("housingLev4Code"))
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("DISTRICT_CODE", new FieldMapper("districtCode"))
			.put("PROPERTY_LEV_3_CODE", new FieldMapper("propertyLev3Code")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyLocations
	 *
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> agyLocExecuteQuery() {
		final String sql = getQuery("OUMAGYHT_AGYLOC_FIND_AGENCY_LOCATIONS");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams(), agencyLocationsRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyLocationAmendments
	 *
	 * @return List<AgencyLocationAmendments>
	 */
	public List<AgencyLocationAmendments> agyLocAmExecuteQuery(final AgencyLocationAmendments objSearchDao) {
		List<AgencyLocationAmendments> returnList;
		final String sql = getQuery("OUMAGYHT_AGYLOCAM_FIND_AGENCY_LOCATION_AMENDMENTS");
		final RowMapper<AgencyLocationAmendments> agencyLocationAmendmentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocationAmendments.class, agencyLocationAmendmentsMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", objSearchDao.getAgyLocId()),
				agencyLocationAmendmentsRowMapper);
		return returnList;
	}

}
