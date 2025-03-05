package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.OumwmenuRepository;

@Repository
public class OumwmenuRepositoryImpl extends RepositoryBase implements OumwmenuRepository{
	private static Logger logger = LogManager.getLogger(OumwmenuRepositoryImpl.class);



private final Map<String, FieldMapper> referenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("WORKFLOW_CODE", 						new FieldMapper("workflowCode"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseLoadType"))
.put("CODE", 						new FieldMapper("code"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> foldersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("WORKFLOW_CODE", 						new FieldMapper("workFlowCode"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("WORKFLOW_SEQ", 						new FieldMapper("workFlowSeq"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseLoadType"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.build();
private final Map<String, FieldMapper> screensMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("WORKFLOW_CODE", 						new FieldMapper("workFlowCode"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseLoadType"))
.put("WORKFLOW_SEQ", 						new FieldMapper("workFlowSeq"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.build();

	/**
	 * Creates new OumwmenuRepositoryImpl class Object
	 */
	public OumwmenuRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            WorkflowFolders
	 *
	 * @return List<WorkflowFolders>
	 *
	 * @throws SQLException
	 */
	public List<WorkFlowFolders> wfFoldersExecuteQuery(final WorkFlowFolders objSearchDao) {
		final String sql = getQuery("OUMWMENU_WFFOLDERS_FIND_WORKFLOW_FOLDERS");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");

			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()
					&& !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" WORKFLOW_CODE  = :description " + "AND ");
				params.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao.getWorkFlowSeq() != null) {
				sqlQuery.append(" WORKFLOW_SEQ  = :workFlowSeq " + " AND ");
				params.addValue("workFlowSeq", objSearchDao.getWorkFlowSeq());
			}
			if (objSearchDao.getSealFlag() != null) {
				sqlQuery.append(" CASELOAD_TYPE  = :caseloadType " + " AND ");
				params.addValue("caseloadType", objSearchDao.getSealFlag());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql.concat("order by WORKFLOW_SEQ");
		final RowMapper<WorkFlowFolders> workflowRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				WorkFlowFolders.class, foldersMapping);
		List<WorkFlowFolders> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, workflowRowMapper);
		return returnList;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorkflowFolders
	 *            List<WorkflowFolders>
	 *
	 * @throws SQLException
	 */
	public Integer wfFoldersDeleteWorkflowFolders(final List<WorkFlowFolders> list) {
		final String sql = getQuery("OUMWMENU_WFFOLDERS_DELETE_WORKFLOW_FOLDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		list.forEach(a -> parameters.add(new BeanPropertySqlParameterSource(a)));
		try {
			String tableName = "WORKFLOW_FOLDERS";
			String whereClause = "WORKFLOW_CODE = :workFlowCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method wfFoldersDeleteWorkflowFolders", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in wfFoldersDeleteWorkflowFolders", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return null;
		}

	}
	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorkflowFolders
	 *            List<WorkflowFolders>
	 *
	 * @throws SQLException
	 */
	public Integer wfFoldersDeleteWorkflowFoldersPredelete(final List<WorkFlowFolders> list) {
		final String sql = getQuery("OUMWMENU_WFFOLDERS_DELETE_WORKFLOW_FOLDERS_PRE_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		list.forEach(a -> parameters.add(new BeanPropertySqlParameterSource(a)));
		try {
			String tableName = "WORKFLOW_SCREENS";
			String whereClause = "WORKFLOW_CODE = :workFlowCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method wfFoldersDeleteWorkflowFoldersPredelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in wfFoldersDeleteWorkflowFoldersPredelete", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            WorkflowScreens
	 *
	 * @return List<WorkflowScreens>
	 *
	 * @throws SQLException
	 */
	public List<WorkflowScreens> wfScreensExecuteQuery(final WorkflowScreens objSearchDao) {
		final String sql = getQuery("OUMWMENU_WFSCREENS_FIND_WORKFLOW_SCREENS");
		final RowMapper<WorkflowScreens> screensRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkflowScreens.class,
				screensMapping);
		return (ArrayList<WorkflowScreens>) namedParameterJdbcTemplate.query(sql,
				createParams("WORKFLOW_CODE", objSearchDao.getWorkFlowCode()), screensRowMapper);

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstWorkflowScreens
	 *            List<WorkflowScreens>
	 *
	 * @throws SQLException
	 */
	public Integer wfScreensUpdateWorkflowScreens(final List<WorkflowScreens> list) {
		final String sql = getQuery("OUMWMENU_WFSCREENS_UPDATE_WORKFLOW_SCREENS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		list.forEach(a -> parameters.add(new BeanPropertySqlParameterSource(a)));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorkflowScreens
	 *            List<WorkflowScreens>
	 *
	 * @throws SQLException
	 */
	public Integer wfScreensDeleteWorkflowScreens(final List<WorkflowScreens> list) {
		final String sql = getQuery("OUMWMENU_WFSCREENS_DELETE_WORKFLOW_SCREENS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		list.forEach(a -> parameters.add(new BeanPropertySqlParameterSource(a)));
		try {
			String tableName = "WORKFLOW_SCREENS";
			String whereClause = "WORKFLOW_CODE = :workFlowCode  and MODULE_NAME  = :moduleName";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method wfScreensDeleteWorkflowScreens", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgMenusNameRecordGroup() {
		final String sql = getQuery("OUMWMENU_FIND_RGMENUSNAME");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgmenusNameRecordGroup:",e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> rgScreensModuleNameRecordGroup() {
		final String sql = getQuery("OUMWMENU_FIND_RGSCREENSMODULENAME");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgScreensModuleNameRecordGroup:",e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfFoldersPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes wfFoldersPostQuery(final WorkFlowFolders paramBean) {
		final String sql = getQuery("OUMWMENU_WF_FOLDERS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("WORKFLOW_CODE", paramBean.getWorkFlowCode()), columnRowMapper);

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstWorkflowScreens
	 *            List<WorkflowScreens>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String wfScreensInsertWorkflowScreens(final List<WorkflowScreens> list) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("OUMWMENU_WFSCREENS_INSERT_WORKFLOW_SCREENS");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		list.forEach(a -> parameters.add(new BeanPropertySqlParameterSource(a)));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in wfFoldersInsertWorkflowFolders", e);
			String excep = e.getMessage();
			return excep.toUpperCase();
		}
		if (list.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstWorkflowFolders
	 *            List<WorkflowFolders>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	@Override
	public String wfFoldersInsertWorkflowFolders(final List<WorkFlowFolders> list) {
		final String sql = getQuery("OUMWMENU_WFFOLDERS_INSERT_WORKFLOW_FOLDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();

		list.forEach(a -> parameters.add(new BeanPropertySqlParameterSource(a)));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in wfFoldersInsertWorkflowFolders", e);
			return e.getMessage().toUpperCase();
		}
		if (list.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstWorkflowFolders
	 *            List<WorkflowFolders>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer wfFoldersUpdateWorkflowFolders(final List<WorkFlowFolders> list) {
		final String sql = getQuery("OUMWMENU_WFFOLDERS_UPDATE_WORKFLOW_FOLDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		list.forEach(a -> parameters.add(new BeanPropertySqlParameterSource(a)));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
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
	@Override
	public List<ReferenceCodes> rgCaseloadTypeRecordGroup() {
		final String sql = getQuery("OUMWMENU_FIND_RGCASELOADTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgCaseloadTypeRecordGroup: ouwmenu", e);
			return Collections.emptyList();
		}
	}

}
