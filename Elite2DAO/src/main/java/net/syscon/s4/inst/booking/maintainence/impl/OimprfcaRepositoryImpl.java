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
import net.syscon.s4.common.beans.ProfileCategory;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.booking.maintainence.OimprfcaRepository;

/**
 * Class OimprfcaRepositoryImpl
 */
@Repository
public class OimprfcaRepositoryImpl extends RepositoryBase implements OimprfcaRepository {
	
	private static Logger logger = LogManager.getLogger(OimprfcaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> profileCatogoryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CATEGORY", 		new FieldMapper("profileCategory"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("RECHECK_FLAG", 			new FieldMapper("recheckFlag"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> profileTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("PROFILE_CATEGORY", 		new FieldMapper("profileCategory"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("MANDATORY_FLAG", 			new FieldMapper("mandatoryFlag"))
			.put("UPDATED_ALLOWED_FLAG", 	new FieldMapper("updatedAllowedFlag"))
			.put("CODE_VALUE_TYPE", 		new FieldMapper("codeValueType"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", 			new FieldMapper("expiryDate"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag")).build();

	/**
	 * Creates new OimprfcaRepositoryImpl class Object
	 */
	public OimprfcaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ProfileCategory
	 *
	 * @return List<ProfileCategory>
	 */
	public List<ProfileCategory> pflCatExecuteQuery(final ProfileCategory objSearchDao) {
		final String sql = getQuery("OIMPRFCA_PFLCAT_FIND_PROFILE_CATEGORIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		List<ProfileCategory> returnList;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getProfileCategory() != null && !objSearchDao.getProfileCategory().trim().equals("")) {
				sqlQuery.append(" PROFILE_CATEGORY = :profileCategory and");
				params.addValue("profileCategory", objSearchDao.getProfileCategory());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" DESCRIPTION = :description and");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ = :listSeq");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<ProfileCategory> profCatRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				ProfileCategory.class, profileCatogoryMapping);
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, profCatRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstProfileCategories
	 *            List<ProfileCategory>
	 *
	 * @return List<Integer>
	 */
	@Override
	public Integer pflCatInsertProfileCategories(final List<ProfileCategory> lstProfCat) {
		final String sql = getQuery("OIMPRFCA_PFLCAT_INSERT_PROFILE_CATEGORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProfileCategory profileCategory : lstProfCat) {
			parameters.add(new BeanPropertySqlParameterSource(profileCategory));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfCat.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstProfileCategories
	 *            List<ProfileCategory>
	 */
	public Integer pflCatUpdateProfileCategories(final List<ProfileCategory> lstProfCat) {
		final String sql = getQuery("OIMPRFCA_PFLCAT_UPDATE_PROFILE_CATEGORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProfileCategory profileCategories : lstProfCat) {
			parameters.add(new BeanPropertySqlParameterSource(profileCategories));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfCat.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstProfileCategories
	 *            List<ProfileCategory>
	 */
	public Integer pflCatDeleteProfileCategories(final List<ProfileCategory> lstProfCat) {
		final String sql = getQuery("OIMPRFCA_PFLCAT_DELETE_PROFILE_CATEGORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProfileCategory profileCategories : lstProfCat) {
			parameters.add(new BeanPropertySqlParameterSource(profileCategories));
		}
		try {
			String tableName = "PROFILE_CATEGORIES";
			String whereClause = "PROFILE_CATEGORY = :profileCategory";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method pflCatDeleteProfileCategories", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfCat.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ProfileTypes
	 *
	 * @return List<ProfileTypes>
	 */
	public List<ProfileTypes> pflTypeExecuteQuery(final ProfileTypes objSearchDao) {
		List<ProfileTypes> returnList = new ArrayList<>();
		final String sql = getQuery("OIMPRFCA_PFLTYPE_FIND_PROFILE_TYPES");
		final RowMapper<ProfileTypes> profTypRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("profileCategory", objSearchDao.getProfileCategory()), profTypRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstProfileTypes
	 *            List<ProfileTypes>
	 *
	 * @return List<Integer>
	 */
	@Override
	public Integer pflTypeInsertProfileTypes(final List<ProfileTypes> lstProfileTypes) {
		final String sql = getQuery("OIMPRFCA_PFLTYPE_INSERT_PROFILE_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProfileTypes profileTypes : lstProfileTypes) {
			parameters.add(new BeanPropertySqlParameterSource(profileTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfileTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstProfileTypes
	 *            List<ProfileTypes>
	 */
	public Integer pflTypeUpdateProfileTypes(final List<ProfileTypes> lstProfileTypes) {
		final String sql = getQuery("OIMPRFCA_PFLTYPE_UPDATE_PROFILE_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProfileTypes profileTypes : lstProfileTypes) {
			parameters.add(new BeanPropertySqlParameterSource(profileTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProfileTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
}
