package net.syscon.s4.inst.booking.maintainence.impl;

import java.util.ArrayList;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.booking.maintainence.OimprfcoRepository;

@Repository
public class OimprfcoRepositoryImpl extends RepositoryBase implements OimprfcoRepository {

	/**
	 * Creates new OimprfcoRepositoryImpl class Object
	 */
	public OimprfcoRepositoryImpl() {
		/*
		 * OimprfcoRepositoryImpl
		 */
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimprfcoRepositoryImpl.class);
	private final Map<String, FieldMapper> profileCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("USER_ID", 				new FieldMapper("userId"))
			.put("MODIFIED_DATE", 			new FieldMapper("modifiedDate"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("UPDATE_ALLOWED_FLAG", 	new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("EXPIRY_DATE", 			new FieldMapper("expiryDate"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();
	
	private final Map<String, FieldMapper> profileTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("MODIFIED_DATE", 			new FieldMapper("modifiedDate"))
			.put("UPDATED_ALLOWED_FLAG", 	new FieldMapper("updatedAllowedFlag"))
			.put("CODE_VALUE_TYPE", 		new FieldMapper("codeValueType"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MANDATORY_FLAG", 			new FieldMapper("mandatoryFlag"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("PROFILE_CATEGORY", 		new FieldMapper("profileCategory"))
			.put("EXPIRY_DATE", 			new FieldMapper("expiryDate"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ProfileTypes
	 *
	 * @return List<ProfileTypes>
	 */
	public List<ProfileTypes> pflTypeExecuteQuery(ProfileTypes objSearchDao) {
		final String sql = getQuery("OIMPRFCO_PFLTYPE_FIND_PROFILE_TYPES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		List<ProfileTypes> returnList;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getProfileType() != null && !objSearchDao.getProfileType().trim().equals("")) {
				sqlQuery.append(" and UPPER(PROFILE_TYPE) = :profileType");
				params.addValue("profileType", objSearchDao.getProfileType().toUpperCase());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" and  DESCRIPTION = :description");
				params.addValue("description", objSearchDao.getDescription());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<ProfileTypes> profileTypesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				ProfileTypes.class, profileTypesMapping);
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, profileTypesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ProfileCodes
	 *
	 * @return List<ProfileCodes>
	 */
	public List<ProfileCodes> pflCodeExecuteQuery(ProfileCodes objSearchDao) {
		List<ProfileCodes> returnList;
		final String sql = getQuery("OIMPRFCO_PFLCODE_FIND_PROFILE_CODES");
		final RowMapper<ProfileCodes> profileCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("profileType", objSearchDao.getProfileType()),
				profileCodesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstProfileCodes
	 *            List<ProfileCodes>
	 *
	 * @return List<Integer>
	 */
	public Integer pflCodeInsertProfileCodes(final List<ProfileCodes> lstProfileCodes) {
		String sql = getQuery("OIMPRFCO_PFLCODE_INSERT_PROFILE_CODES");
		int[] returnArray;
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (ProfileCodes profileCodes : lstProfileCodes) {
			parameters.add(new BeanPropertySqlParameterSource(profileCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfileCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstProfileCodes
	 *            List<ProfileCodes>
	 */
	public Integer pflCodeUpdateProfileCodes(final List<ProfileCodes> lstProfileCodes) {
		String sql = getQuery("OIMPRFCO_PFLCODE_UPDATE_PROFILE_CODES");
		int[] returnArray;
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (ProfileCodes profileCodes : lstProfileCodes) {
			parameters.add(new BeanPropertySqlParameterSource(profileCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfileCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstProfileCodes
	 *            List<ProfileCodes>
	 */
	public Integer pflCodeDeleteProfileCodes(final List<ProfileCodes> lstProfileCodes) {
		String sql = getQuery("OIMPRFCO_PFLCODE_DELETE_PROFILE_CODES");
		int[] returnArray;
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (ProfileCodes profileCodes : lstProfileCodes) {
			parameters.add(new BeanPropertySqlParameterSource(profileCodes));
		}
		try {
			String tableName = "PROFILE_CODES";
			String whereClause = "PROFILE_TYPE = :profileType AND PROFILE_CODE  = :profileCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method pflCodeDeleteProfileCodes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfileCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Check profile codes.
	 *
	 * @return the String
	 */
	@Override
	public String getUserId(final String createUserId) {
		String value = null;
		final String sql = getQuery("OIMPRFCO_GET_USER_ID");
		value = namedParameterJdbcTemplate.queryForObject(sql, createParams("createUserId",createUserId), String.class);
		return value;
	}

	/**
	 * Check profile codes.
	 *
	 * @param profileCodes
	 *            the profile codes
	 * @return the integer
	 */
	@Override
	public int checkProfileCodes(String profileCode) {
		int count;
		final String sql = getQuery("OIMPRFCO_CGRICHK_PROFILE_CODES");
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("profileCode", profileCode), Integer.class);
		return count;
	}

}
