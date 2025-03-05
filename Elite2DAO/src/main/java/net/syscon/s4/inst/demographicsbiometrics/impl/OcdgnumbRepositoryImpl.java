package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.demographicsbiometrics.OcdgnumbRepository;

/**
 * Class OcdgnumbRepositoryImpl
 * 
 * 
 */
@Repository
public class OcdgnumbRepositoryImpl extends RepositoryBase implements OcdgnumbRepository {

	private static Logger logger = LogManager.getLogger(OcdgnumbRepositoryImpl.class.getName());

	/**
	 * Creates new OcdgnumbRepositoryImpl class Object
	 */
	public OcdgnumbRepositoryImpl() {
		super();
	}

	private final Map<String, FieldMapper> internetAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_ID", new FieldMapper("ownerId")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_CODE", new FieldMapper("ownerCode")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("INTERNET_ADDRESS_ID", new FieldMapper("internetAddressId"))
			.put("INTERNET_ADDRESS_CLASS", new FieldMapper("internetAddressClass"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("INTERNET_ADDRESS", new FieldMapper("internetAddress")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_ID", new FieldMapper("ownerId")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PHONE_ID", new FieldMapper("phoneId")).put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_CODE", new FieldMapper("ownerCode")).put("EXT_NO", new FieldMapper("extNo"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("PHONE_TYPE", new FieldMapper("phoneType")).put("PHONE_NO", new FieldMapper("phoneNo"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Phones
	 *
	 * @return List<Phones>
	 *
	 * @throws SQLException
	 */
	public List<Phones> phonesExecuteQuery(final Phones objSearchDao) {
		final String sql = getQuery("OCDGNUMB_PHONES_FIND_PHONES");
		ArrayList<Phones> returnList = null;
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getOwnerCode() != null) {
				sqlQuery.append("OWNER_CODE = :ownerCode and");
				params.addValue("ownerCode", objSearchDao.getOwnerCode());
			}
			if (objSearchDao.getOwnerId() != null) {
				sqlQuery.append("OWNER_ID = :ownerId and ");
				params.addValue("ownerId", objSearchDao.getOwnerId());
			}
			if (objSearchDao.getOwnerClass() != null) {
				sqlQuery.append("OWNER_CLASS = :ownerClass and");
				params.addValue("ownerClass", objSearchDao.getOwnerClass());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<Phones> phonesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Phones.class,
				phonesMapping);
		returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(preparedSql, params, phonesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int preinsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstPhones List<Phones>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer phonesInsertPhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OCDGNUMB_PHONES_INSERT_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPhones List<Phones>
	 *
	 * 
	 */
	public Integer phonesUpdatePhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OCDGNUMB_PHONES_UPDATE_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstPhones List<Phones>
	 *
	 * 
	 */
	public Integer phonesDeletePhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OCDGNUMB_PHONES_DELETE_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		try {
			String tableName = "PHONES";
			String whereClause = "PHONE_ID  = :phoneId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method phonesDeletePhones", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao InternetAddresses
	 *
	 * @return List<InternetAddresses>
	 *
	 * 
	 */
	public List<InternetAddresses> internetAddrExecuteQuery(final InternetAddresses objSearchDao) {
		final String sql = getQuery("OCDGNUMB_INTERNETADDR_FIND_INTERNET_ADDRESSES");
		List<InternetAddresses> returnList = new ArrayList<InternetAddresses>();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getOwnerCode() != null) {
				sqlQuery.append("OWNER_CODE = :ownerCode and ");
				params.addValue("ownerCode", objSearchDao.getOwnerCode());
			}
			if (objSearchDao.getOwnerId() != null) {
				sqlQuery.append("OWNER_ID = :ownerId and ");
				params.addValue("ownerId", objSearchDao.getOwnerId());
			}
			if (objSearchDao.getOwnerClass() != null) {
				sqlQuery.append("OWNER_CLASS = :ownerClass and");
				params.addValue("ownerClass", objSearchDao.getOwnerClass());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<InternetAddresses> intAddRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				InternetAddresses.class, internetAddressesMapping);

		returnList = namedParameterJdbcTemplate.query(preparedSql, params, intAddRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstInternetAddresses List<InternetAddresses>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer internetAddrInsertInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		final String sql = getQuery("OCDGNUMB_INTERNETADDR_INSERT_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstInternetAddresses List<InternetAddresses>
	 *
	 * @throws SQLException
	 */
	public Integer internetAddrUpdateInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		final String sql = getQuery("OCDGNUMB_INTERNETADDR_UPDATE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstInternetAddresses List<InternetAddresses>
	 *
	 * 
	 */
	public Integer internetAddrDeleteInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		final String sql = getQuery("OCDGNUMB_INTERNETADDR_DELETE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		try {
			String tableName = "INTERNET_ADDRESSES";
			String whereClause = "INTERNET_ADDRESS_ID  = :internetAddressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method internetAddrDeleteInternetAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		final String sql = getQuery("OCDGNUMB_FIND_RGPHONETYPE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * phonesPreInsertPRE-INSERT
	 *
	 * @param params
	 *
	 */
	public Object phonesPreInsertPreInsert() {
		final String sql = getQuery("OCDGNUMB_PHONES_PREINSERT_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * internetAddrPreInsert
	 *
	 * @param params
	 *
	 */
	public Object internetAddrPreInsert() {
		final String sql = getQuery("OCDGNUMB_INTERNET_ADDR_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	@Override
	public Phones getOldDataOfPhones(Long phoneId) {
		final String sql = getQuery("OCDGNUMB_GETTING_PHONES_OLD_DATA");
		Phones bean = new Phones();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("PHONEID", phoneId),
					new BeanPropertyRowMapper<Phones>(Phones.class));
		} catch (Exception e) {
			logger.error("getOldDataOfPhones");
		}
		return bean;
	}
	
	@Override
	public List<String> gettingEmailDomains() {
		final String sql = getQuery("OCDGNUMB_DESCRIPTION_EMAIL");
		List<String> returnList = null;
		try {
			returnList= namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("gettingEmailDomains");
		}
		return returnList;
	}

}
