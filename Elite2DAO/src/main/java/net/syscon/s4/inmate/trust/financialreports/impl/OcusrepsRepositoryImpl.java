package net.syscon.s4.inmate.trust.financialreports.impl;

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
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.trust.financialreports.OcusrepsRepository;

/**
 * Class OcusrepsRepositoryImpl
 */
@Repository
public class OcusrepsRepositoryImpl extends RepositoryBase implements OcusrepsRepository {
	
	private static Logger logger = LogManager.getLogger(OcusrepsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEFAULT_COPY", 		new FieldMapper("defaultCopy"))
			.put("CREATE_USER_ID", 		new FieldMapper("createUserId"))
			.put("HELP_DIRECTORY", 		new FieldMapper("helpDirectory"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("MODULE_NAME", 		new FieldMapper("moduleName"))
			.put("PREVIEW_FLAG", 		new FieldMapper("previewFlag"))
			.put("APPLN_CODE", 			new FieldMapper("applnCode"))
			.put("SEAL_FLAG", 			new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 	new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 	new FieldMapper("modifyDatetime"))
			.put("MODULE_TYPE", 		new FieldMapper("moduleType"))
			.put("OUTPUT_TYPE", 		new FieldMapper("outputType"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("PRINT_FORMAT_CODE", 	new FieldMapper("printFormatCode"))
			.build();

	/**
	 * Creates new OcusrepsRepositoryImpl class Object
	 */
	public OcusrepsRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OmsModules
	 *
	 * @return List<OmsModules>
	 */
	public List<OmsModules> omsModulesExecuteQuery(final OmsModules objSearchDao) {
		final String sql = getQuery("OCUSREPS_OMSMODULES_FIND_OMS_MODULES");
		final StringBuffer sqlQuery = new StringBuffer();
		String preparedSql = null;
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getDescription() != null) {
				sqlQuery.append(
						" (UPPER(description) like upper('%' || :DESCRIPTION || '%') or UPPER(module_name) like upper('%' || :DESCRIPTION || '%') "
								+ " or upper('%' || :DESCRIPTION || '%') is null ) and module_type = 'REPORT' AND  EXISTS (SELECT '1' "
								+ " FROM module_privileges p WHERE p.module_name = oms_modules.module_name AND  p.access_privilege = 'A' AND EXISTS (SELECT '2' "
								+ " FROM staff_member_roles r WHERE r.role_id = p.role_id AND EXISTS (SELECT '3' "
								+ " FROM staff_members s WHERE s.staff_id = r.staff_id AND s.user_id = :USER_ID))) "
								+ " ORDER BY oms_modules.description ");

				valuesList.addValue("DESCRIPTION", objSearchDao.getDescription());
				valuesList.addValue("USER_ID", objSearchDao.getCreateUserId());
			} else {
				sqlQuery.append(
						" (UPPER(description) like upper('%' || null || '%') or UPPER(module_name) like upper('%' || null || '%') "
								+ " or upper('%' || null || '%') is null ) and module_type = 'REPORT' AND  EXISTS (SELECT '1' "
								+ " FROM module_privileges p WHERE p.module_name = oms_modules.module_name AND p.access_privilege = 'A' AND EXISTS (SELECT '2' "
								+ " FROM staff_member_roles r WHERE r.role_id = p.role_id AND EXISTS (SELECT '3' "
								+ " FROM staff_members s WHERE s.staff_id = r.staff_id AND s.user_id = :USER_ID))) "
								+ " ORDER BY oms_modules.description ");
				valuesList.addValue("USER_ID", objSearchDao.getCreateUserId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<OmsModules> OmsModulesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, OmsModulesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOmsModules
	 *            List<OmsModules>
	 *
	 * @return List<Integer>
	 */
	public Integer omsModulesInsertOmsModules(final List<OmsModules> lstOmsModules) {
		String sql = getQuery("OCUSREPS_OMSMODULES_INSERT_OMS_MODULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OmsModules omsModules : lstOmsModules) {
			parameters.add(new BeanPropertySqlParameterSource(omsModules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsModules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOmsModules
	 *            List<OmsModules>
	 */
	public Integer omsModulesUpdateOmsModules(final List<OmsModules> lstOmsModules) {
		String sql = getQuery("OCUSREPS_OMSMODULES_UPDATE_OMS_MODULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OmsModules omsModules : lstOmsModules) {
			parameters.add(new BeanPropertySqlParameterSource(omsModules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsModules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOmsModules
	 *            List<OmsModules>
	 */
	public Integer omsModulesDeleteOmsModules(final List<OmsModules> lstOmsModules) {
		String sql = getQuery("OCUSREPS_OMSMODULES_DELETE_OMS_MODULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OmsModules omsModules : lstOmsModules) {
			parameters.add(new BeanPropertySqlParameterSource(omsModules));
		}
		try {
			String tableName = "OMS_MODULES";
			String whereClause = "";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method omsModulesDeleteOmsModules", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsModules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCUSREPS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

}
