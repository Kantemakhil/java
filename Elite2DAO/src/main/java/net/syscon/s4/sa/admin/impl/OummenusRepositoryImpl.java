package net.syscon.s4.sa.admin.impl;

import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VMenuSecs;
import net.syscon.s4.sa.admin.OummenusRepository;

/**
 * Class OummenusRepositoryImpl
 */
@Repository
public class OummenusRepositoryImpl extends RepositoryBase implements OummenusRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OummenusRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 		new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("VFORMDESC", new FieldMapper("vFormDesc"))
			.build();
	private final Map<String, FieldMapper> vMenuSecsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("APPLN_CODE",  	new FieldMapper("applnCode"))
			.put("DESCRIPTION", 	new FieldMapper("description"))
			.put("MENU_ID", 		new FieldMapper("menuId"))
			.put("MENU_ITEM", 		new FieldMapper("menuItem"))
			.put("MODULE_NAME", 	new FieldMapper("moduleName"))
			.put("MODULE_TYPE", 	new FieldMapper("moduleType"))
			.put("PARENT_MENU_ID",  new FieldMapper("parentMenuId"))
			.put("SORT_ORDER", 		new FieldMapper("sortOrder"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 		new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 		new FieldMapper("sealFlag"))
			.put("MODULE_DESCRIPTION", 	new FieldMapper("moduleDescription"))
			.build();
	private final Map<String, FieldMapper> moduleMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DYANMIC_FORM", new FieldMapper("dynamicForm"))
			.build();

	/**
	 * Creates new OummenusRepositoryImpl class Object
	 */
	public OummenusRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VMenuSecs
	 *
	 * @return List<VMenuSecs>
	 *
	 * @throws SQLException
	 */
	public List<VMenuSecs> vMenuSecsExecuteQuery(final VMenuSecs objSearchDao) {
		final String sql = getQuery("OUMMENUS_VMENUSECS_FIND_V_MENU_SECS");
		final RowMapper<VMenuSecs> rowMapper = Row2BeanRowMapper.makeMapping(sql, VMenuSecs.class, vMenuSecsMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getMenuId() != null) {
				sqlQuery.append(" vm.MENU_ID = :MENU_ID" + " AND ");
				inParameterSource.addValue("MENU_ID", objSearchDao.getMenuId());
			}
			if (objSearchDao.getParentMenuId() != null) {
				sqlQuery.append(" vm.PARENT_MENU_ID = :PARENT_MENU_ID" + " AND ");
				inParameterSource.addValue("PARENT_MENU_ID", objSearchDao.getParentMenuId());
			}
			if (objSearchDao.getMenuItem() != null && !objSearchDao.getMenuItem().isEmpty()  && !("".equals(objSearchDao.getMenuItem().trim()))) {
				sqlQuery.append(" vm.MENU_ITEM = :MENU_ITEM" + " AND ");
				inParameterSource.addValue("MENU_ITEM", objSearchDao.getMenuItem());
			}
			if (objSearchDao.getModuleName() != null) {
				sqlQuery.append(" vm.MODULE_NAME = :MODULE_NAME" + " AND ");
				inParameterSource.addValue("MODULE_NAME", objSearchDao.getModuleName());
			}
			if (objSearchDao.getModuleDescription() != null&& !objSearchDao.getModuleDescription().isEmpty() && !("".equals(objSearchDao.getModuleDescription().trim()))) {
				sqlQuery.append(" vm.DESCRIPTION = :DESCRIPTION" + " AND ");
				inParameterSource.addValue("DESCRIPTION", objSearchDao.getModuleDescription());
			}
			if (objSearchDao.getSortOrder() != null) {
				sqlQuery.append(" vm.SORT_ORDER = :SORT_ORDER" + " AND ");
				inParameterSource.addValue("SORT_ORDER", objSearchDao.getSortOrder());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + "  ORDER BY PARENT_MENU_ID, SORT_ORDER,MENU_ITEM";
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, rowMapper);
	}
	
	
	/***
	 * 
	 * @param moduleName
	 * @return
	 */
	@Override
	public OmsModules getOmsModule(final String moduleName) {
		final String sql = getQuery("OUMMENUS_OMSMODULE_DYANMICFORM");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, moduleMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), columnRowMapper);
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVMenuSecs
	 *            List<VMenuSecs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer vMenuSecsInsertQuery(final List<MenuSecurities> lstVMenuSecs) {
		final String sql = getQuery("OUMMENUS_VMENUSECS_INSERT_V_MENU_SECS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final MenuSecurities vMenuSecs : lstVMenuSecs) {
			parameters.add(new BeanPropertySqlParameterSource(vMenuSecs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVMenuSecs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVMenuSecs
	 *            List<VMenuSecs>
	 *
	 * @throws SQLException
	 */
	public Integer vMenuSecsUpdateQuery(final List<MenuSecurities> lstVMenuSecs) {
		final String sql = getQuery("OUMMENUS_VMENUSECS_UPDATE_V_MENU_SECS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final MenuSecurities vMenuSecs : lstVMenuSecs) {
			parameters.add(new BeanPropertySqlParameterSource(vMenuSecs));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch(Exception e) {
			return 3;
		}
		if (lstVMenuSecs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVMenuSecs
	 *            List<VMenuSecs>
	 *
	 * @throws SQLException
	 */
	public Integer vMenuSecsDeleteQuery(final List<MenuSecurities> lstVMenuSecs) {
		final String sql = getQuery("OUMMENUS_VMENUSECS_DELETE_V_MENU_SECS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final MenuSecurities vMenuSecs : lstVMenuSecs) {
			parameters.add(new BeanPropertySqlParameterSource(vMenuSecs));
		}
		try {
			String tableName = "MENU_SECURITIES";
			String whereClause = "MENU_ID=:menuId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method vMenuSecsDeleteQuery", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVMenuSecs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OmsModules>
	 */
	public List<OmsModules> rgMenuSecDescRecordGroup() {
		final String sql = getQuery("OUMMENUS_FIND_RGMENUSECDESC");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vMenuSecsPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer vMenuSecsPreInsert(final MenuSecurities paramBean) {
		final String sql = getQuery("OUMMENUS_V_MENU_SECS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	public Integer descUpdateQuery(final String description,final String modifyUserId, final String  moduleName) {
		final String sql = getQuery("OUMMENUS_OMS_MODULES_UPDATE_DESC");
		Integer updateCount = 0;
		try {
			 updateCount = namedParameterJdbcTemplate.update(sql,
					createParams("description", description,"modifyUserId", modifyUserId,"moduleName",moduleName));
		} catch (final Exception e) {
			logger.error("descUpdateQuery", e);
		}
		return updateCount;

	}
	
	@Override
	public Integer vMenuSecsPreInsertPreUpdate(final MenuSecurities paramBean) {
		Integer returnObj = null;
		final String sql = getQuery("OUMMENUS_PREINSERT_PREUPDATE_PREDELETE_VALID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PARENTMENUID", paramBean.getParentMenuId(), "SORTORDER", paramBean.getSortOrder()),
				Integer.class);
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer getParentMenuExistCount(BigDecimal menuId) {
		Integer returnObj = null;
		final String sql = getQuery("OUMMENUS_PREDELETE_CHECKING");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("menuId", menuId),
				Integer.class);
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

}
