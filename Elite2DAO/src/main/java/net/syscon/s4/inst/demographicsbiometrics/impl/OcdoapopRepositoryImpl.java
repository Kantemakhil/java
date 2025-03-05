package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.demographicsbiometrics.OcdoapopRepository;

/**
 * Class OcdoapopRepositoryImpl
 * 
 */
@Repository
public class OcdoapopRepositoryImpl extends RepositoryBase implements OcdoapopRepository {
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcdoapopRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> addressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate"))
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("NO_FIXED_ADDRESS_FLAG", new FieldMapper("noFixedAddressFlag"))
			.put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("BUSINESS_HOUR", new FieldMapper("businessHour"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag"))
			.put("STREET", new FieldMapper("street"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("CITY_CODE", new FieldMapper("cityCode"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("SERVICES_FLAG", new FieldMapper("servicesFlag"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("VALIDATED_PAF_FLAG", new FieldMapper("validatedPafFlag"))
			.put("SPECIAL_NEEDS_CODE", new FieldMapper("specialNeedsCode"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper(" description "))
			.put("CODE", new FieldMapper(" code")).build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' "))
			.put("ADDRESS_ID", new FieldMapper("addressId")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper(" profileValue ")).build();

	/**
	 * Creates new OcdoapopRepositoryImpl class Object
	 */
	public OcdoapopRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Addresses
	 *
	 * @return List<Addresses>
	 *
	 * @
	 */
	public List<Addresses> addressExecuteQuery(final Addresses objSearchDao) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_FIND_ADDRESSES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOwnerId() != null) {
				sqlQuery.append(" OWNER_ID =:ownerId  " +" and " );
				params.addValue("ownerId", objSearchDao.getOwnerId());
			}
			if (objSearchDao.getOwnerCode() != null) {
				sqlQuery.append(" OWNER_CODE =:ownerCode  " );
				params.addValue("ownerCode", objSearchDao.getOwnerCode());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		
		final RowMapper<Addresses> AddressesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Addresses.class,
				addressesMapping);
		final ArrayList<Addresses> returnList = (ArrayList<Addresses>) namedParameterJdbcTemplate.query(preparedSql,
				params, AddressesRowMapper);
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
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAddresses
	 *            List<Addresses>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer addressInsertAddresses(final List<Addresses> lstAddresses) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_INSERT_ADDRESSES");
		int[] returnArray = new int[] {};
		Integer addressId = null;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
			addressId = Integer.valueOf(addresses.getAddressId() + "");
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return addressId;
		} else {
			return returnArray[0];
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAddresses
	 *            List<Addresses>
	 *
	 * @
	 */
	public Integer addressUpdateAddresses(final List<Addresses> lstAddresses) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_UPDATE_ADDRESSES");
		int[] returnArray = new int[] {};
		Integer addressId = null;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
			addressId = Integer.valueOf(addresses.getAddressId() + "");
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch(Exception e) {
			logger.error("error in addressUpdateAddresses"+e.getMessage());
		}
		
		if (lstAddresses.size() == returnArray.length) {
			return addressId;
		} else {
			return returnArray[0];
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAddresses
	 *            List<Addresses>
	 *
	 * @
	 */
	public Integer addressDeleteAddresses(final List<Addresses> lstAddresses) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_DELETE_ADDRESSES");
		int[] returnArray = new int[] {};
		Integer addressId = null;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
			addressId = Integer.valueOf(addresses.getAddressId() + "");
		}
		try {
			String tableName = "ADDRESSES";
			String whereClause = "ADDRESS_ID  = :addressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method addressDeleteAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return addressId;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCityRecordGroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGCITY");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCountyRecordGroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGCOUNTY");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCountryRecordGroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGCOUNTRY");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGTYPE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSpecialNeedsRecordGroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGSPECIALNEEDS");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgProvStateCodeRecordGroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGPROVSTATECODE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStreetDirRecordGroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGSTREETDIR");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * addressWhenCreateRecord
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes addressWhenCreateRecord(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_WHENCREATERECORD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * addressKeyDelrec
	 *
	 * @param params
	 *
	 */
	public List<Phones> addressKeyDelrec(final Phones paramBean) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_KEYDELREC");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		final ArrayList<Phones> returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql,
				createParams("OWNER_ID", paramBean.getOwnerId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocdoapopPreInsert
	 *
	 * @param params
	 *
	 */
	public Object ocdoapopPreInsert() {
		final String sql = getQuery("OCDOAPOP_OCDOAPOP_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateCityInfo
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes validateCityInfoCode(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDOAPOP_VALIDATE_CITY_INFO_CODE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateCityInfo
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes validateCityInfoDescription(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDOAPOP_VALIDATE_CITY_INFO_DESCRIPTION");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * citySystemProfile
	 *
	 * @param params
	 *
	 */
	public SystemProfiles citySystemProfile(final SystemProfiles paramBean) {
		final String sql = getQuery("OCDOAPOP_CITY_SYSTEM_PROFILE");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

}
