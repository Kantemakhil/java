package net.syscon.s4.sa.admin.integratedwordprocessing.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpCompositeBookMarks;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumbmarkRepository;

/**
 * Class OumbmarkRepositoryImpl
 * 
 */
@Repository
public  class OumbmarkRepositoryImpl extends RepositoryBase implements OumbmarkRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumbmarkRepositoryImpl.class.getName());
	
	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	
	

	private final Map<String, FieldMapper> iwpParameterMappingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BOOKMARK_NAME", new FieldMapper("bookmarkName"))
			.put("MAPPING_ID", new FieldMapper("mappingId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USERID", new FieldMapper("createUserId"))
			.put("DOCUMENT_CONTEXT_FLAG", new FieldMapper("documentContextFlag"))
			.put("FIELD_NAME", new FieldMapper("fieldName"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("TEMPLATE_MODULE_ID", new FieldMapper("templateModuleId"))
			.put("PARAMETER_NAME", new FieldMapper("parameterName"))
			.put("DATA_TYPE", new FieldMapper("dataType"))
			.build();
	private final Map<String, FieldMapper> iwpBookmarksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BOOKMARK_NAME", new FieldMapper("bookmarkName"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKMARK_TYPE", new FieldMapper("bookmarkType"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USERID", new FieldMapper("createUserId"))
			.put("DATE_CREATED", new FieldMapper("dateCreated"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USERID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("SQL_TEXT", new FieldMapper("sqlText"))
			.put("SQL_VERIFIED_FLAG", new FieldMapper("sqlVerifiedFlag"))
			.put("USER_CREATED", new FieldMapper("userCreated"))
			.build();
	private final Map<String, FieldMapper> iwpBookmarkParametersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' ")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' ")).build();
	/**
	 * Creates new OumbmarkRepositoryImpl class Object
	 */
	public OumbmarkRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            IwpBookmarks
	 *
	 * @return List<IwpBookmarks>
	 *
	 */
	public List<IwpBookmarks> aIwpBookmarksExecuteQuery(final IwpBookmarks objSearchDao) {
		final String sql = getQuery("OUMBMARK_AIWPBOOKMARKS_FIND_IWP_BOOKMARKS");
		final RowMapper<IwpBookmarks> IwpBookmarkRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpBookmarks.class, iwpBookmarksMapping);
		List<IwpBookmarks> returnList = new ArrayList<IwpBookmarks>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams(), IwpBookmarkRowMapper);
		} catch (Exception e) {
			logger.error("aIwpBookmarksExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstIwpBookmarks
	 *            List<IwpBookmarks>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer aIwpBookmarksInsertIwpBookmarks(final List<IwpBookmarks> lstIwpBookmarks) {
		String sql = getQuery("OUMBMARK_AIWPBOOKMARKS_INSERT_IWP_BOOKMARKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IwpBookmarks iwpBookmarks : lstIwpBookmarks) {
			parameters.add(new BeanPropertySqlParameterSource(iwpBookmarks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpBookmarks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIwpBookmarks
	 *            List<IwpBookmarks>
	 *
	 */
	public Integer aIwpBookmarksUpdateIwpBookmarks(final List<IwpBookmarks> lstIwpBookmarks) {
		String sql = getQuery("OUMBMARK_AIWPBOOKMARKS_UPDATE_IWP_BOOKMARKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IwpBookmarks iwpBookmarks : lstIwpBookmarks) {
			parameters.add(new BeanPropertySqlParameterSource(iwpBookmarks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpBookmarks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            IwpBookmarkParameters
	 *
	 * @return List<IwpBookmarkParameters>
	 *
	 */
	public List<IwpBookmarkParameters> aIwpParametersExecuteQuery(final IwpBookmarkParameters objSearchDao) {
		final String sql = getQuery("OUMBMARK_AIWPPARAMETERS_FIND_IWP_BOOKMARK_PARAMETERS");
		final RowMapper<IwpBookmarkParameters> IwpBookmarkParametersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpBookmarkParameters.class, iwpBookmarkParametersMapping);
		List<IwpBookmarkParameters> returnList = new ArrayList<IwpBookmarkParameters>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("BOOKMARKNAME", objSearchDao.getBookmarkName()), IwpBookmarkParametersRowMapper);
		} catch (Exception e) {
			logger.error("aIwpParametersExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstIwpBookmarkParameters
	 *            List<IwpBookmarkParameters>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer aIwpParametersInsertIwpBookmarkParameters(
			final List<IwpBookmarkParameters> lstIwpBookmarkParameters) {
		String sql = getQuery("OUMBMARK_AIWPPARAMETERS_INSERT_IWP_BOOKMARK_PARAMETERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IwpBookmarkParameters iwpBookmarkParameters : lstIwpBookmarkParameters) {
			parameters.add(new BeanPropertySqlParameterSource(iwpBookmarkParameters));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpBookmarkParameters.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIwpBookmarkParameters
	 *            List<IwpBookmarkParameters>
	 *
	 */
	public Integer aIwpParametersUpdateIwpBookmarkParameters(
			 final List<IwpBookmarkParameters> lstIwpBookmarkParameters) {
		String sql = getQuery("OUMBMARK_AIWPPARAMETERS_UPDATE_IWP_BOOKMARK_PARAMETERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpBookmarkParameters iwpBookmarkParameters : lstIwpBookmarkParameters) {
			parameters.add(new BeanPropertySqlParameterSource(iwpBookmarkParameters));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpBookmarkParameters.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * aIwpBookmarksOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<IwpBookmarkParameters> aIwpBookmarksOnCheckDeleteMaster(final IwpBookmarks paramBean) {
		final String sql = getQuery("OUMBMARK_A_IWP_BOOKMARKS_ONCHECKDELETEMASTER");
		final RowMapper<IwpBookmarkParameters> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpBookmarkParameters.class, iwpBookmarkParametersMapping);
		List<IwpBookmarkParameters> returnList = new ArrayList<IwpBookmarkParameters>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("BOOKMARKNAME", paramBean.getBookmarkName()), columnRowMapper);
		} catch (Exception e) {
			logger.error("aIwpBookmarksOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OUMBMARK_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<OmsModules>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("createFormGlobals", e);
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkForMapping
	 *
	 * @param params
	 *
	 */
	public List<IwpParameterMappings> checkForMapping(final IwpParameterMappings paramBean) {
		final String sql = getQuery("OUMBMARK_CHECK_FOR_MAPPING");
		final RowMapper<IwpParameterMappings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpParameterMappings.class, iwpParameterMappingsMapping);
		List<IwpParameterMappings> returnList = new ArrayList<IwpParameterMappings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("checkForMapping", e);
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * duplicateBookmarkName
	 *
	 * @param params
	 *
	 */
	public List<IwpBookmarks> duplicateBookmarkName(final IwpBookmarks paramBean) {
		final String sql = getQuery("OUMBMARK_DUPLICATE_BOOKMARK_NAME");
		final RowMapper<IwpBookmarks> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpBookmarks.class,
				iwpBookmarksMapping);
		List<IwpBookmarks> returnList = new ArrayList<IwpBookmarks>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("duplicateBookmarkName", e);
		}
		return returnList;
	}
	
	
	public String oumbmarkGetStaffName(final String staffName) {
		final String sql = getQuery("OUMBMARK_OMS_MISCELLANEOUS_STAFF_NAME");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_USER_ID" ,staffName), String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}
	
	public String oumbmarkGetUserId(String createUserId) {
		final String sql = getQuery("OUMBMARK_GET_USER_NAME");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_Id",createUserId), String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public List<Map<String, Object>> getOutParamLov(IwpBookmarks searchBean) {
		final String sql = "select dynamicQuery.* from (select 1) as ignoredIt left join ( ##SQL_TEXT## ) as dynamicQuery on true";
		String finalSql = sql.replace("##SQL_TEXT##", searchBean.getSqlText().replace("::", "##").replaceAll("(:\\w+)", "null").replace("?", "null").replace("##", "::"));
		List<Map<String, Object>> returnData = new ArrayList<>();
		try {
			List<Map<String, Object>> sqlData = jdbcTemplate.queryForList(finalSql);
			sqlData.get(0).keySet().forEach(key -> {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("outParam", key.toUpperCase());
				dataMap.put("outParamDesc", key.toUpperCase());
				returnData.add(dataMap);
			});					
		}  catch(Exception e) {
			e.printStackTrace();
			Map<String, Object> error = new HashMap<>();
			error.put("error", e.getCause().getMessage());
			returnData.add(error);
			return returnData;
		}		
		return returnData;
	}
	
	
	@Override
	public Integer outParametersUpdate(List<IwpCompositeBookMarks> updateList) {
		String sql = getQuery("OUMBMARK_UPDATE_IWP_COMPOSITE_BOOKMARKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IwpCompositeBookMarks iwpBookmarks : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(iwpBookmarks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertOutParams(List<IwpBookmarkParameters> outParams) {
		String sql = getQuery("OUMBMARK_INSERT_IWP_OUTPARAMS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IwpBookmarkParameters iwpBookmarks : outParams) {
			parameters.add(new BeanPropertySqlParameterSource(iwpBookmarks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (outParams.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<IwpBookmarkParameters> getOutParams(IwpBookmarks searchBean) {
		final String sql = getQuery("OUMBMARK_GET_IWP_BOOKMARK_OUT_PARAMETERS");
		final RowMapper<IwpBookmarkParameters> IwpBookmarkParametersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpBookmarkParameters.class, iwpBookmarkParametersMapping);
		List<IwpBookmarkParameters> returnList = new ArrayList<IwpBookmarkParameters>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("bookmarkName", searchBean.getBookmarkName()), IwpBookmarkParametersRowMapper);
		} catch (Exception e) {
			logger.error("getOutParams", e);
		}
		return returnList;
	}

	@Override
	public Integer deleteBookmarkOutParams(IwpBookmarks iwpBookmarks) {
		final String sql = getQuery("OUMBMARK_DELETE_IWP_BOOKMARK_OUT_PARAMETERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(iwpBookmarks));
		try {
			String tableName = "IWP_BOOKMARK_OUT_PARAMETERS";
			String whereClause = "BOOKMARK_NAME = :bookmarkName";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteBookmarkOutParams", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		return returnArray.length;
	}

	@Override
	public List<IwpBookmarks> getIwpBookmarksSQLText(final String bookmarkName) {
		final String sql = getQuery("OUMBMARK_GET_IWP_BOOKMARK");	
		final RowMapper<IwpBookmarks> IwpBookmarkParametersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpBookmarks.class, iwpBookmarksMapping);
		List<IwpBookmarks> returnList = new ArrayList<IwpBookmarks>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("bookmarkName", bookmarkName), IwpBookmarkParametersRowMapper);
		} catch (Exception e) {
			logger.error("getOutParams", e);
		}
		return returnList;
	}

	@Override
	public Integer deleteCompositeOutParamBookmarks(IwpBookmarks iwpBookmarks) {
		final String sql = getQuery("OUMBMARK_DELETE_IWP_COMPOSITE_BOOKMARK");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(iwpBookmarks));
		try {
			String tableName = "IWP_COMPOSITE_BOOKMARKS";
			String whereClause = "BOOKMARK_NAME =:bookmarkName";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteCompositeOutParamBookmarks", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		return returnArray.length;
	}


}
