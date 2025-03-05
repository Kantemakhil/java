package net.syscon.s4.sa.admin.integratedwordprocessing.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.IwpTemplateModules;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpBookmarksGroups;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumdtempRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OumdtempRepositoryImpl
 */
@Repository
public class OumdtempRepositoryImpl extends RepositoryBase implements OumdtempRepository{


/**
* Logger object used to print the log in the file
*/
private static Logger logger = LogManager.getLogger(OumdtempRepositoryImpl.class.getName());
private final Map<String, FieldMapper> iwpBookmarksGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TEMPLATE_ID", 						new FieldMapper("templateId"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("BOOKMARK_NAME", 						new FieldMapper("bookmarkName"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CODE", 						new FieldMapper("code"))
.put("ROLE_CODE", 						new FieldMapper("roleCode"))
.put("BOOKMARK_TYPE", 						new FieldMapper("bookmarkType"))
.build();
private final Map<String, FieldMapper> iwpBookmarkParametersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PARAMETER_NAME", 						new FieldMapper("parameterName"))
.put("BOOKMARK_NAME", 						new FieldMapper("bookmarkName"))
.put("TEMPLATE_ID", 						new FieldMapper("templateId"))
.put("PARAMETER_DATA_TYPE", 						new FieldMapper(" parameterDataType "))
.build();
private final Map<String, FieldMapper> iwpTemplatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("RELCOUNT", 						new FieldMapper("relCount"))
.put("CONTEXT_RULE_COUNT", 						new FieldMapper("contextRuleCount"))
.put("IWP_DOC_COUNT", 						new FieldMapper("iwpDocCount"))
.put("IS_TEMPLATE",                          new FieldMapper("isTemplate"))
.build();
private final Map<String, FieldMapper> iwpTemplateModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.build();
private final Map<String, FieldMapper> iwpParameterMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DATATYPE", 						new FieldMapper("dataType"))
.build();
private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.build();

	/**
	 * Creates new OumdtempRepositoryImpl class Object
	 */
	public OumdtempRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            IwpTemplates
	 *
	 * @return List<IwpTemplates>
	 *
	 * @throws SQLException
	 */
	public List<IwpTemplates> aIwpTemplatesExecuteQuery(IwpTemplates objSearchDao) {
		final String sql = getQuery("OUMDTEMP_AIWPTEMPLATES_FIND_IWP_TEMPLATES");
		final RowMapper<IwpTemplates> IwpTemplatesRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class,
				iwpTemplatesMapping);

		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getObjectType() != null ) {
				sqlQuery.append(" OBJECT_TYPE = :OBJECT_TYPE" + " AND ");
				inParameterSource.addValue("OBJECT_TYPE", objSearchDao.getObjectType());
			}
			if (objSearchDao.getTemplateName() != null && !objSearchDao.getTemplateName().isEmpty()
					&& !("".equals(objSearchDao.getTemplateName().trim()))) {
				sqlQuery.append(" TEMPLATE_NAME = :TEMPLATE_NAME" + " AND ");
				inParameterSource.addValue("TEMPLATE_NAME", objSearchDao.getTemplateName());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()
					&& !("".equals(objSearchDao.getDescription().trim()))) {
				sqlQuery.append(" DESCRIPTION = :DESCRIPTION" + " AND ");
				inParameterSource.addValue("DESCRIPTION", objSearchDao.getDescription());
			}
			if (objSearchDao.getUserCreated() != null && !objSearchDao.getUserCreated().isEmpty()
					&& !("".equals(objSearchDao.getUserCreated().trim()))) {
				sqlQuery.append(" USER_CREATED = :USER_CREATED" + " AND ");
				inParameterSource.addValue("USER_CREATED", objSearchDao.getUserCreated());
			}
			
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY ACTIVE_FLAG DESC, DESCRIPTION ASC ";
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, IwpTemplatesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstIwpTemplates
	 *            List<IwpTemplates>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer aIwpTemplatesInsertIwpTemplates(final List<IwpTemplates> lstIwpTemplates) {
		String sql = getQuery("OUMDTEMP_AIWPTEMPLATES_INSERT_IWP_TEMPLATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (IwpTemplates iwpTemplates : lstIwpTemplates) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplates));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpTemplates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIwpTemplates
	 *            List<IwpTemplates>
	 *
	 * @throws SQLException
	 */
	public Integer aIwpTemplatesUpdateIwpTemplates(final List<IwpTemplates> lstIwpTemplates) {
		String sql = getQuery("OUMDTEMP_AIWPTEMPLATES_UPDATE_IWP_TEMPLATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpTemplates iwpTemplates : lstIwpTemplates) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplates));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpTemplates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstIwpTemplates
	 *            List<IwpTemplates>
	 *
	 * @throws SQLException
	 */
	public Integer aIwpTemplatesDeleteIwpTemplates(final List<IwpTemplates> lstIwpTemplates) {
		String sql = getQuery("OUMDTEMP_AIWPTEMPLATES_DELETE_IWP_TEMPLATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpTemplates iwpTemplates : lstIwpTemplates) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplates));
		}
		try {
			String tableName = "IWP_TEMPLATES";
			String whereClause = "TEMPLATE_ID=:templateId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method aIwpTemplatesDeleteIwpTemplates", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpTemplates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            IwpTemplateModules
	 *
	 * @return List<IwpTemplateModules>
	 *
	 * @throws SQLException
	 */
	public List<IwpTemplateModules> aIwpTagRelationsExecuteQuery(IwpTemplateModules objSearchDao) {
		final String sql = getQuery("OUMDTEMP_AIWPTAGRELATIONS_FIND_IWP_TEMPLATE_MODULES");
		final RowMapper<IwpTemplateModules> IwpTemplateModulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpTemplateModules.class, iwpTemplateModulesMapping);
		return namedParameterJdbcTemplate
				.query(sql, createParams("TEMPLATE_ID",objSearchDao.getTemplateId()), IwpTemplateModulesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstIwpTemplateModules
	 *            List<IwpTemplateModules>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String aIwpTagRelationsInsertIwpTemplateModules(final List<IwpTemplateModules> listObj) {
		String sql = getQuery("OUMDTEMP_AIWPTAGRELATIONS_INSERT_IWP_TEMPLATE_MODULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpTemplateModules iwpTemplateModules : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplateModules));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIwpTemplateModules
	 *            List<IwpTemplateModules>
	 *
	 * @throws SQLException
	 */
	public String aIwpTagRelationsUpdateIwpTemplateModules(final List<IwpTemplateModules> listObj) {
		String sql = getQuery("OUMDTEMP_AIWPTAGRELATIONS_UPDATE_IWP_TEMPLATE_MODULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (IwpTemplateModules iwpTemplateModules : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplateModules));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstIwpTemplateModules
	 *            List<IwpTemplateModules>
	 *
	 * @throws SQLException
	 */
	public Integer aIwpTagRelationsDeleteIwpTemplateModules(final List<IwpTemplateModules> lstIwpTemplateModules) {
		String sql = getQuery("OUMDTEMP_AIWPTAGRELATIONS_DELETE_IWP_TEMPLATE_MODULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpTemplateModules iwpTemplateModules : lstIwpTemplateModules) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplateModules));
		}
		try {
			String tableName = "IWP_TEMPLATE_MODULES";
			String whereClause = "TEMPLATE_MODULE_ID =:templateModuleId and TEMPLATE_ID =:templateId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method aIwpTagRelationsDeleteIwpTemplateModules", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpTemplateModules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            IwpParameterMappings
	 *
	 * @return List<IwpParameterMappings>
	 *
	 * @throws SQLException
	 */
	public List<IwpParameterMappings> iwpParameterMappingsExecuteQuery(IwpParameterMappings objSearchDao) {
		final String sql = getQuery("OUMDTEMP_IWPPARAMETERMAPPINGS_FIND_IWP_PARAMETER_MAPPINGS");
		final RowMapper<IwpParameterMappings> IwpParameterMappingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpParameterMappings.class, iwpParameterMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("TEMPLATE_MODULE_ID", objSearchDao.getTemplateModuleId()), IwpParameterMappingsRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIwpParameterMappings
	 *            List<IwpParameterMappings>
	 *
	 * @throws SQLException
	 */
	public Integer iwpParameterMappingsUpdateIwpParameterMappings(
			final List<IwpParameterMappings> lstIwpParameterMappings) {
		String sql = getQuery("OUMDTEMP_IWPPARAMETERMAPPINGS_UPDATE_IWP_PARAMETER_MAPPINGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpParameterMappings iwpParameterMappings : lstIwpParameterMappings) {
			parameters.add(new BeanPropertySqlParameterSource(iwpParameterMappings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpParameterMappings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstIwp10gGetTemplRoles
	 *            List<Iwp10gGetTemplRoles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsRoles> rgRolesRecordGroup() {
		final String sql = getQuery("OUMDTEMP_FIND_RGROLES");
		final RowMapper<OmsRoles> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRoles.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<IwpBookmarks> rgBmListRecordGroup() {
		final String sql = getQuery("OUMDTEMP_FIND_RGBMLIST");
		final RowMapper<IwpBookmarks> mRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpBookmarks.class, mMapping);
		List<IwpBookmarks> returnList = new ArrayList<>(); 
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
			return returnList; 
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> rgOmsModuleRecordGroup() {
		final String sql = getQuery("OUMDTEMP_FIND_RGOMSMODULE");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> rgReportNameRecordGroup() {
		final String sql = getQuery("OUMDTEMP_FIND_RGREPORTNAME");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> rgDocNameRecordGroup() {
		final String sql = getQuery("OUMDTEMP_FIND_RGDOCNAME");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<StaffMembers> rgStaffRecordGroup() {
		final String sql = getQuery("OUMDTEMP_FIND_RGSTAFF");
		final RowMapper<StaffMembers> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgObjectTypeRecordGroup() {
		final String sql = getQuery("OUMDTEMP_FIND_RGOBJECTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * aIwpTagRelationsPreInsert
	 *
	 * @param params
	 *
	 */
	public BigDecimal aIwpTagRelationsPreInsert() {
		final String sql = getQuery("OUMDTEMP_A_IWP_TAG_RELATIONS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * aIwpTagRelationsPostUpdate
	 *
	 * @param params
	 *
	 */
	public Integer aIwpTagRelationsPostUpdate(IwpTemplateModules paramBean) {
		final String sql = getQuery("OUMDTEMP_A_IWP_TAG_RELATIONS_POSTUPDATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("TEMPLATEMODULEID", paramBean.getTemplateModuleId()), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * aIwpTagRelationsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<IwpParameterMappings> aIwpTagRelationsOnCheckDeleteMaster(IwpParameterMappings paramBean) {
		final String sql = getQuery("OUMDTEMP_A_IWP_TAG_RELATIONS_ONCHECKDELETEMASTER");
		final RowMapper<IwpParameterMappings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpParameterMappings.class, iwpParameterMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * iwpParameterMappingsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<IwpBookmarkParameters> iwpParameterMappingsPostQuery(IwpBookmarkParameters paramBean) {
		final String sql = getQuery("OUMDTEMP_IWP_PARAMETER_MAPPINGS_POSTQUERY");
		final RowMapper<IwpBookmarkParameters> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpBookmarkParameters.class, iwpBookmarkParametersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * contextRule
	 *
	 * @param params
	 *
	 */
	public List<IwpBookmarksGroups> contextRule(IwpBookmarksGroups paramBean) {
		final String sql = getQuery("OUMDTEMP_CONTEXT_RULE");
		final RowMapper<IwpBookmarksGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpBookmarksGroups.class, iwpBookmarksGroupsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}
	public List<IwpTemplateRoles> iwpRolesExecuteQuery(final IwpTemplates objSearchDao) {
		List<IwpTemplateRoles> partialRecordslist = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RESULTCURSOR", OracleTypes.CURSOR),
				new SqlParameter("TEMPID", OracleTypes.NUMBER),
				new SqlParameter("TEMP_ROLE_CODE", Types.VARCHAR),  };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("IWP_10G").withProcedureName("GET_TEMPL_ROLES")
				.declareParameters(sqlParameters);
		inParamMap.put("RESULTCURSOR", OracleTypes.CURSOR);
		inParamMap.put("TEMPID", objSearchDao.getTemplateId());
		inParamMap.put("TEMP_ROLE_CODE", null);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			partialRecordslist = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("RESULTCURSOR");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				IwpTemplateRoles bean = new IwpTemplateRoles();
				bean.setRoleCode(childMap.get("ROLE_CODE"));
				bean.setRoleName(childMap.get("ROLE_NAME"));
				partialRecordslist.add(bean);
			}

		} catch (Exception e) {
			logger.error("psOffNameExecuteQuery", e);
		}

		return partialRecordslist;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * contextRule
	 *
	 * @param params
	 *
	 */
	public Integer templateNameexists(List<String> returnObj) {
		final String sql = getQuery("OUMDTEMP_TEMPLATE_NAMME_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEMP_NAME",returnObj), Integer.class);
	}

	@Override
	public Integer bookmarkGrpCur(IwpTemplates searchBean) {
		final String sql = getQuery("OUMDTEMP_TEMPLATE_NAMME_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEMP_NAME",searchBean.getTemplateName()), Integer.class);
	}
	@Override
	public Integer isContextRecCur(IwpTemplates searchBean) {
		final String sql = getQuery("OUMDTEMP_IS_CONTEXT_REC_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("TEMPLATE_ID",searchBean.getTemplateId()), Integer.class);
	}
	@Override
	public Integer recExistsCur(IwpTemplates searchBean) {
		final String sql = getQuery("OUMDTEMP_REC_EXISTS_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("TEMPLATE_ID",searchBean.getTemplateId()), Integer.class);
	}

	@Override
	public Integer aIwpTagRelationsInsertQuery(List<IwpTemplateRoles> listObj) {
		String sql = getQuery("OUMDTEMP_TEMPLROLES_INSERT_IWP_10G");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpTemplateRoles iwpTemplates : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplates));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer aIwpTagRelationsUpdateQuery(List<IwpTemplateRoles> listObj) {
		String sql = getQuery("OUMDTEMP_AIWPTEMPLATES_UPDATE_IWP_TEMPLATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpTemplateRoles iwpTemplates : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplates));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int aIwpTagRelationsDeleteQuery(List<IwpTemplateRoles> listObj) {
		String sql = getQuery("OUMDTEMP_TEMPLROLES_DELETE_IWP_10G");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (IwpTemplateRoles iwpTemplates : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplates));
		}
		try {
			String tableName = "IWP_TEMPLATE_ROLES";
			String whereClause = "TEMPLATE_ID = :templateId AND ROLE_CODE = :roleCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method aIwpTagRelationsDeleteQuery", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer isTemplRoleExists(List<Long> templateId ,List<String> roleCode) {
		final String sql = getQuery("OUMDTEMP_IS_TEMPL_ROLE_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEMPLATE_ID",templateId,"p_role_code",roleCode),Integer.class);
	}
	
	public Integer tagRelationsPostInsert(final List<IwpTemplateModules> listObj) {
		final String sql = getQuery("A_IWP_TAG_RELATIONS_POST_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (IwpTemplateModules iwpTemplateModules : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplateModules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}



	}

	@Override
	public List<IwpTemplateRoles> iwpRolesForStaffExecuteQuery(String userId) {
		List<IwpTemplateRoles> returnList=null;
		final String sql = getQuery("GET_IWP_ROLES_FOR_STAFF");
		final RowMapper<IwpTemplateRoles> IwpTemplateRolesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpTemplateRoles.class, iwpTemplateModulesMapping);
		try {
			returnList= namedParameterJdbcTemplate
					.query(sql, createParams("userId",userId), IwpTemplateRolesRowMapper);
		
		}catch(Exception e) {
			logger.error("error in iwpRolesForStaffExecuteQuery"+ e.getMessage());
		}
		return returnList;
	}
	
}
