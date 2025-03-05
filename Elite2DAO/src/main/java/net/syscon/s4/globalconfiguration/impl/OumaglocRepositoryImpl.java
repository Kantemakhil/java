package net.syscon.s4.globalconfiguration.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OumaglocRepository;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgyLocEstablishments;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
/**
 * Class OumaglocRepositoryImpl
 */
@Repository
public class OumaglocRepositoryImpl extends RepositoryBase implements OumaglocRepository {
	
	

	private final Map<String, FieldMapper> agyLocEstMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ESTABLISHMENT_TYPE", new FieldMapper("establishmentType")).build();
	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_ID", 						new FieldMapper("ownerId"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("PHONE_ID", 						new FieldMapper("phoneId"))
			.put("OWNER_CLASS", 					new FieldMapper("ownerClass"))
			.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
			.put("EXT_NO", 							new FieldMapper("extNo"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("OWNER_SEQ", 						new FieldMapper("ownerSeq"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("PHONE_TYPE", 						new FieldMapper("phoneType"))
			.put("PHONE_NO", 						new FieldMapper("phoneNo"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.build();

	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_LEV_1_CODE", new FieldMapper("propertyLev1Code"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("DISABILITY_ACCESS_CODE", new FieldMapper("disabilityAccessCode"))
			.put("BAIL_OFFICE_FLAG", new FieldMapper("bailOfficeFlag"))
			.put("AREA_CODE", new FieldMapper("areaCode"))
			.put("GEOGRAPHIC_REGION_CODE", new FieldMapper("geographicRegionCode"))
			.put("HOUSING_LEV_1_CODE", new FieldMapper("housingLev1Code"))
			.put("COMMISSARY_PRIVILEGE", new FieldMapper("commissaryPrivilege"))
			.put("DEACTIVATION_DATE", new FieldMapper("deactivationDate"))
			.put("INTAKE_FLAG", new FieldMapper("intakeFlag"))
			.put("JURISDICTION_CODE", new FieldMapper("jurisdictionCode"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("HOUSING_LEV_3_CODE", new FieldMapper("housingLev3Code"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("SUB_AREA_CODE", new FieldMapper("subAreaCode"))
			.put("LONG_DESCRIPTION", new FieldMapper("longDescription"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("BUSINESS_HOURS", new FieldMapper("businessHours"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("PROPERTY_LEV_2_CODE", new FieldMapper("propertyLev2Code"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("PRINT_QUEUE", new FieldMapper("printQueue"))
			.put("ABBREVIATION", new FieldMapper("abbreviation"))
			.put("JUSTICE_AREA_CODE", new FieldMapper("justiceAreaCode"))
			.put("NOMS_REGION_CODE", new FieldMapper("nomsRegionCode"))
			.put("LABCORP_CLIENT_ID", new FieldMapper("labcorpClientId"))
			.put("CONTACT_NAME", new FieldMapper("contactName"))
			.put("HOUSING_LEV_2_CODE", new FieldMapper("housingLev2Code"))
			.put("LAST_BOOKING_NO", new FieldMapper("lastBookingNo"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("HOUSING_LEV_4_CODE", new FieldMapper("housingLev4Code"))
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("DISTRICT_CODE", new FieldMapper("districtCode"))
			.put("PROPERTY_LEV_3_CODE", new FieldMapper("propertyLev3Code")).build();
	private final Map<String, FieldMapper> vAgyAddrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("STREET", new FieldMapper("street"))
			.put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("HOUSE", new FieldMapper("house"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("STREET_DIRECTION_DESC", new FieldMapper("streetDirectionDesc"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			.put("CITY_CODE", new FieldMapper("cityCode"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag"))
			.put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("VALIDATED_FLAG", new FieldMapper("validatedFlag"))
			.build();
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumaglocRepositoryImpl .class.getName());

	/**
	 * Creates new OumaglocRepositoryImpl class Object
	 */
	public OumaglocRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param paramBean
	 *            AgencyLocations
	 *
	 * @return List<AgencyLocations>
	 *
	 * @
	 */
	public List<AgencyLocations> agyLocExecuteQuery(final AgencyLocations paramBean) {
		final String sql = getQuery("OUMAGLOC_AGYLOC_FIND_AGENCY_LOCATIONS");
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		String preparedSql = null;
		sqlQuery.append(sql);
		if (paramBean != null) {
			sqlQuery.append(" where ");
		
		if (paramBean.getAgyLocId() != null) {
			sqlQuery.append("AGY_LOC_ID =  :agyLocId " + " and ");
			valuesList.addValue("agyLocId", paramBean.getAgyLocId());
		}
		if (paramBean.getDescription() != null) {
			sqlQuery.append("DESCRIPTION =  :description " + " and ");
			valuesList.addValue("description", paramBean.getDescription());
		}
		if (paramBean.getAgencyLocationType() != null) {
			sqlQuery.append("AGENCY_LOCATION_TYPE = :agencyLocationType" + "  and ");
			valuesList.addValue("agencyLocationType", paramBean.getAgencyLocationType());
		}
		if (paramBean.getActiveFlag() != null) {
			sqlQuery.append("ACTIVE_FLAG = :activeFlag" + "  and ");
			valuesList.addValue("activeFlag", paramBean.getActiveFlag());
		}
		if (paramBean.getDeactivationDate() != null) {
			sqlQuery.append("DEACTIVATION_DATE = :deactivationDate" + "  and ");
			valuesList.addValue("deactivationDate", paramBean.getDeactivationDate());
		}
		if (paramBean.getContactName() != null) {
			sqlQuery.append("CONTACT_NAME =  :contactName" + "  and ");
			valuesList.addValue("contactName", paramBean.getContactName());
		}
		if (paramBean.getDisabilityAccessCode() != null) {
			sqlQuery.append("DISABILITY_ACCESS_CODE =  :disabilityAccessCode" + " and ");
			valuesList.addValue("disabilityAccessCode", paramBean.getDisabilityAccessCode());
		}
		if (paramBean.getJurisdictionCode() != null) {
			sqlQuery.append("JURISDICTION_CODE =  :jurisdictionCode " + "  and ");
			valuesList.addValue("jurisdictionCode", paramBean.getJurisdictionCode());
		}
		if (paramBean.getIntakeFlag() != null) {
			sqlQuery.append("INTAKE_FLAG =  :intakeFlag " + "  and ");
			valuesList.addValue("intakeFlag", paramBean.getIntakeFlag());
		}
		if (paramBean.getListSeq() != null) {
			sqlQuery.append("LIST_SEQ =  :listSeq " + " and ");
			valuesList.addValue("listSeq", paramBean.getListSeq());
		}
		if (paramBean.getPrintQueue() != null) {
			sqlQuery.append("PRINT_QUEUE =  :printQueue " + " and ");
			valuesList.addValue("printQueue", paramBean.getPrintQueue());
		}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<AgencyLocations> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, agyLocRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyLocations
	 *            List<AgencyLocations>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer agyLocInsertAgencyLocations(final List<AgencyLocations> lstAgyLocs) {
		int insertCount = 0;
		final String sql = getQuery("OUMAGLOC_AGYLOC_INSERT_AGENCY_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		
		for(final AgencyLocations agyLoc : lstAgyLocs) {
			parameters.add(new BeanPropertySqlParameterSource(agyLoc));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyLocs.size() == returnArray.length) {
			insertCount = 1;
		} 
		return insertCount;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyLocations
	 *            List<AgencyLocations>
	 *
	 * @
	 */
	public Integer agyLocUpdateAgencyLocations(final List<AgencyLocations> lstAgencyLocations) {
		final String sql = getQuery("OUMAGLOC_AGYLOC_UPDATE_AGENCY_LOCATIONS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocations agencyLocations : lstAgencyLocations) {
			parameters.add(new BeanPropertySqlParameterSource(agencyLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocations.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param paramBean
	 *            VAgencyAddresses
	 *
	 * @return List<VAgencyAddresses>
	 *
	 * @
	 */
	public List<VAgencyAddresses> vAgyAddrExecuteQuery(final VAgencyAddresses paramBean) {
		final String sql = getQuery("OUMAGLOC_VAGYADDR_FIND_V_AGENCY_ADDRESSES");
		final RowMapper<VAgencyAddresses> VAgyAddRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAgencyAddresses.class, vAgyAddrMapping);
		final ArrayList<VAgencyAddresses> returnList = (ArrayList<VAgencyAddresses>) namedParameterJdbcTemplate
				.query(sql, createParams("agyLocId",paramBean.getAgyLocId()), VAgyAddRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param paramBean
	 *            Phones
	 *
	 * @return List<Phones>
	 *
	 * @
	 */
	public List<Phones> phonesExecuteQuery(final Phones paramBean) {
		final String sql = getQuery("OUMAGLOC_PHONES_FIND_PHONES");
		final RowMapper<Phones> PhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		final ArrayList<Phones> returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql, createParams("ownerId",paramBean.getOwnerId()),
				PhonesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer phonesInsertPhones(final List<Phones> lstPhones) {
		int insertCount = 0;
		final String sql = getQuery("OUMAGLOC_PHONES_INSERT_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			insertCount = 1;
		} 
   return insertCount;
	}
	

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 *
	 * @
	 */
	public Integer phonesUpdatePhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OUMAGLOC_PHONES_UPDATE_PHONES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			returnValue =1;
		}
		return returnValue;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 *
	 * @
	 */
	public Integer phonesDeletePhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OUMAGLOC_PHONES_DELETE_PHONES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		try {
			String tableName = "PHONES";
			String whereClause = "PHONE_ID = :phoneId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method phonesDeletePhones", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			returnValue =1;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param paramBean
	 *            AgyLocEstablishments
	 *
	 * @return List<AgyLocEstablishments>
	 *
	 * @
	 */
	public List<AgyLocEstablishments> agyLocEstExecuteQuery(final AgyLocEstablishments paramBean) {
		final String sql = getQuery("OUMAGLOC_AGYLOCEST_FIND_AGY_LOC_ESTABLISHMENTS");
		final RowMapper<AgyLocEstablishments> agyLocEstRMap = Row2BeanRowMapper.makeMapping(sql,
				AgyLocEstablishments.class, agyLocEstMapping);
		final ArrayList<AgyLocEstablishments> returnList = (ArrayList<AgyLocEstablishments>) namedParameterJdbcTemplate
				.query(sql,  createParams("agyLocId",paramBean.getAgyLocId()), agyLocEstRMap);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgyLocEstablishments
	 *            List<AgyLocEstablishments>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer agyLocEstInsertAgyLocEstablishments(final List<AgyLocEstablishments> lstAgyLocEsts)
			 {
		int returnValue = 0;
		final String sql = getQuery("OUMAGLOC_AGYLOCEST_INSERT_AGY_LOC_ESTABLISHMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyLocEstablishments clAgyLocations : lstAgyLocEsts) {
			parameters.add(new BeanPropertySqlParameterSource(clAgyLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyLocEsts.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgyLocEstablishments
	 *            List<AgyLocEstablishments>
	 *
	 * @
	 */
	public Integer agyLocEstUpdateAgyLocEstablishments(final List<AgyLocEstablishments> lstAgyLocEsts)
			 {
		final String sql = getQuery("OUMAGLOC_AGYLOCEST_UPDATE_AGY_LOC_ESTABLISHMENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyLocEstablishments agyLocEsts : lstAgyLocEsts) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocEsts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyLocEsts.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgyLocEstablishments
	 *            List<AgyLocEstablishments>
	 *
	 * @
	 */
	public Integer agyLocEstDeleteAgyLocEstablishments(final List<AgyLocEstablishments> lstAgyLocEsts)
			 {
		final String sql = getQuery("OUMAGLOC_AGYLOCEST_DELETE_AGY_LOC_ESTABLISHMENTS");
	    int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyLocEstablishments agyLocEsts : lstAgyLocEsts) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocEsts));
		}
		try {
			String tableName = "AGY_LOC_ESTABLISHMENTS";
			String whereClause = "AGY_LOC_ID = :agyLocId and ESTABLISHMENT_TYPE  = :establishmentType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agyLocEstDeleteAgyLocEstablishments", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	
		if (lstAgyLocEsts.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		final String sql = getQuery("OUMAGLOC_FIND_RGPHONETYPE");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgPhoneTypeRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAgencyLocationTypeRecordGroup() {
		final String sql = getQuery("OUMAGLOC_FIND_RGAGENCYLOCATIONTYPE");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgAgencyLocationTypeRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgJurisdictionRecordGroup() {
		final String sql = getQuery("OUMAGLOC_FIND_RGJURISDICTION");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgJurisdictionRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgDisabilityAccessCodeRecordGroup() {
		final String sql = getQuery("OUMAGLOC_FIND_RGDISABILITYACCESSCODE");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);

		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgDisabilityAccessCodeRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgHousingLevelCodesRecordGroup() {
		final String sql = getQuery("OUMAGLOC_FIND_RGHOUSINGLEVELCODES");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgHousingLevelCodesRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgEstablishmentTypeRecordGroup() {
		final String sql = getQuery("OUMAGLOC_FIND_RGESTABLISHMENTTYPE");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgEstablishmentTypeRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agyLocOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VAgencyAddresses> agyLocOnCheckDeleteMaster(final VAgencyAddresses paramBean) {
		final String sql = getQuery("OUMAGLOC_AGY_LOC_ONCHECKDELETEMASTER");
		final RowMapper<VAgencyAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAgencyAddresses.class,
				vAgyAddrMapping);
		final ArrayList<VAgencyAddresses> returnList = (ArrayList<VAgencyAddresses>) namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agyLocOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public AgyLocEstablishments agyLocOnCheckDeleteMaster(final AgyLocEstablishments paramBean) {
		final String sql = getQuery("OUMAGLOC_AGY_LOC_ONCHECKDELETEMASTER");
		final RowMapper<AgyLocEstablishments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyLocEstablishments.class, agyLocEstMapping);
		AgyLocEstablishments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vAgyAddrOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Phones> vAgyAddrOnCheckDeleteMaster(Phones paramBean) {
		final String sql = getQuery("OUMAGLOC_V_AGY_ADDR_ONCHECKDELETEMASTER");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		final ArrayList<Phones> returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * phonesPreInsert
	 *
	 * @param params
	 *
	 */
	public Object phonesPreInsert() {
		final String sql = getQuery("OUMAGLOC_PHONES_PREINSERT_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}


	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgYnFlagRecordGroup()  {
		final String sql = getQuery("OUMAGLOC_FIND_RGYNFLAG");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgYnFlagRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public AgencyLocations getOldAgencyLocations(String agyLocId) {
		AgencyLocations retObj=null;
		final String sql = getQuery("OUMAGLOC_FIND_OLD");
		final RowMapper<AgencyLocations> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, refCodesMapping);
		try {
			retObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("AGY_LOC_ID",agyLocId),refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In rgYnFlagRecordGroup method : ", e);
			
		}
		return retObj;
		
		
	}
	
	
	@Override
	public Integer iepLevelCommit(final IepLevelBean bean) {
		final String sql = getQuery("OUMAGLOC_INSERT_IEP_DATA");
		Integer returnValue=null;
		try {
			returnValue = namedParameterJdbcTemplate.update(sql,createParams("agyLocId",bean.getAgyLocId(),"iepLevelCode",bean.getIepLevelCode(),"createUserId",bean.getCreateUserId(),"modifyUserId",bean.getModifyUserId(),"sealFlag",bean.getSealFlag()));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+ "iepLevelCommit", e);
		}
		return returnValue;
	}
	
	 @Override
		public Integer iepLevelCommitUpdate(final String agyLocid,String iepLevelCode, String modifyUserId) {
			final String sql = getQuery("OUMAGLOC_UPDATE_IEP_DATA");
			Integer returnValue=null;
			try {
				returnValue = namedParameterJdbcTemplate.update(sql,createParams("agyLocId",agyLocid,"iepLevelCode",iepLevelCode,"modifyUserId", modifyUserId));
			}catch (Exception e) {
				logger.error(this.getClass().getName()+ "iepLevelCommit", e);
			}
			return returnValue;
		}
	 
	 
	 @Override
		public Integer iepLevelCommitDelete(final String agyLocid, String modifyUserId) {
			final String sql = getQuery("OUMAGLOC_DELETE_IEP_DATA");
			Integer returnValue=null;
			try {
				String tableName = "agy_loc_iep_levels";
				String whereClause = "agy_loc_id =:agyLocId";
				Map<String , Object> inputMap = new HashMap<String, Object>();
				inputMap.put("agyLocId", agyLocid);
				inputMap.put("modifyUserId", modifyUserId);
				updatePreDeletedRow(tableName, whereClause , inputMap);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method iepLevelCommitDelete", e);
			}
			try {
				returnValue = namedParameterJdbcTemplate.update(sql,createParams("agyLocId",agyLocid));
			}catch (Exception e) {
				logger.error(this.getClass().getName()+ "iepLevelCommit", e);
			}
			return returnValue;
		}
	 
	 
	 @Override
	 	public IepLevelBean getIepdetails(String agyLocId) {
	 		final String sql = getQuery("OUMAGLOC_FETCH_IEP_DATA");
	 		IepLevelBean profiles=new IepLevelBean();
	 		try {
	 			profiles=namedParameterJdbcTemplate.queryForObject(sql,new MapSqlParameterSource("agyLocId", agyLocId), new BeanPropertyRowMapper<IepLevelBean>(IepLevelBean.class));
	 		}catch (EmptyResultDataAccessException e) {
	 			profiles.setIepLevelCode(ApplicationConstants.EMPTYDATA);
	 			logger.error(this.getClass().getName()+ "getIepdetails", e);
	 		}
	 		catch (Exception e) {
	 			logger.error(this.getClass().getName()+ "getIepdetails", e);
	 		}
	 		return profiles;
	 	}
}
