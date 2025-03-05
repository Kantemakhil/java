package net.syscon.s4.globalrbac.impl;
import java.sql.SQLException;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalrbac.OumassmuRepository;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
/**
 * Class OumassmuRepositoryImpl
 */
@Repository
public class OumassmuRepositoryImpl extends RepositoryBase implements OumassmuRepository{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumassmuRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> modulePrivilegesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("VERIFICATION_FLAG", 						new FieldMapper("verificationFlag"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("MODULE_NAME", 						new FieldMapper("moduleName"))
			.put("ACCESS_PRIVILEGE", 						new FieldMapper("accessPrivilege"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDateTime"))
			.put("DESCRIPTION", 						new FieldMapper("moduleDescription"))
			.put("MODULE_TYPE", 						new FieldMapper("moduleType"))
			.put("ROLE_ID", 						new FieldMapper("roleId"))
			.put("'1'", 						new FieldMapper("  '1' "))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("CODE", 						new FieldMapper("code"))
			.put("ACCESS_PRIVILEGE", 						new FieldMapper("accessPrivilege"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", 						new FieldMapper("moduleName"))
			.put("MODULE_TYPE", 						new FieldMapper("moduleType"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> omsRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARENT_ROLE_CODE", 						new FieldMapper("parentRoleCode"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("ROLE_CODE", 						new FieldMapper("roleCode"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDateTime"))
			.put("ROLE_ID", 						new FieldMapper("roleId"))
			.put("ROLE_NAME", 						new FieldMapper("roleName"))
			.put("ROLE_SEQ", 						new FieldMapper("roleSeq"))
			.build();

/**
 * Creates new OumassmuRepositoryImpl class Object 
 */
	public OumassmuRepositoryImpl() {
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OmsRoles
	 *
	 * @return List<OmsRoles>
	 *
	 * @throws SQLException
	 */
	public List<OmsRoles> omsRoleExecuteQuery(final OmsRoles objSearchDao) {
		final String sql = getQuery("OUMASSMU_OMSROLE_FIND_OMS_ROLES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao != null && objSearchDao.getRoleCode() != null) {
				sqlQuery.append(" ROLE_CODE =:roleCode  " + " and");
				params.addValue("roleCode", objSearchDao.getRoleCode());
			}
			if (objSearchDao != null && objSearchDao.getRoleName() != null) {
				sqlQuery.append(" ROLE_NAME =:roleName " + " and");
				params.addValue("roleName", objSearchDao.getRoleName());
			}
			if (objSearchDao != null && objSearchDao.getRoleId() != null) {
				sqlQuery.append(" ROLE_ID =:roleId " + " and");
				params.addValue("roleId", objSearchDao.getRoleId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + "ORDER BY ROLE_NAME ASC";
		final RowMapper<OmsRoles> OmsRolesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, OmsRoles.class,
				omsRolesMapping);
		final ArrayList<OmsRoles> returnList = (ArrayList<OmsRoles>) namedParameterJdbcTemplate.query(preparedSql,
				params, OmsRolesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ModulePrivileges
	 *
	 * @return List<ModulePrivileges>
	 *
	 * @throws SQLException
	 */
	public List<ModulePrivileges> modPrivExecuteQuery(final ModulePrivileges objSearchDao) {
		final String sql = getQuery("OUMASSMU_MODPRIV_FIND_MODULE_PRIVILEGES");
		final RowMapper<ModulePrivileges> ModulePrivilegesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ModulePrivileges.class, modulePrivilegesMapping);
		final ArrayList<ModulePrivileges> returnList = (ArrayList<ModulePrivileges>) namedParameterJdbcTemplate
				.query(sql, createParams("ROLE_ID", objSearchDao.getRoleId()), ModulePrivilegesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstModulePrivileges
	 *            List<ModulePrivileges>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer modPrivInsertModulePrivileges(final List<ModulePrivileges> lstModulePrivileges) {
		final String sql = getQuery("OUMASSMU_MODPRIV_INSERT_MODULE_PRIVILEGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ModulePrivileges modulePrivileges : lstModulePrivileges) {
			parameters.add(new BeanPropertySqlParameterSource(modulePrivileges));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstModulePrivileges.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstModulePrivileges
	 *            List<ModulePrivileges>
	 *
	 * @throws SQLException
	 */
	public Integer modPrivUpdateModulePrivileges(final List<ModulePrivileges> lstModulePrivileges) {
		final String sql = getQuery("OUMASSMU_MODPRIV_UPDATE_MODULE_PRIVILEGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ModulePrivileges modulePrivileges : lstModulePrivileges) {
			parameters.add(new BeanPropertySqlParameterSource(modulePrivileges));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstModulePrivileges.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstModulePrivileges
	 *            List<ModulePrivileges>
	 *
	 * @throws SQLException
	 */
	public Integer modPrivDeleteModulePrivileges(final List<ModulePrivileges> lstModulePrivileges) {
		final String sql = getQuery("OUMASSMU_MODPRIV_DELETE_MODULE_PRIVILEGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ModulePrivileges modulePrivileges : lstModulePrivileges) {
			parameters.add(new BeanPropertySqlParameterSource(modulePrivileges));
		}
		try {
			String tableName = "MODULE_PRIVILEGES";
			String whereClause = "MODULE_NAME  = :moduleName AND ROLE_ID  = :roleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method modPrivDeleteModulePrivileges", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstModulePrivileges.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OmsRoles>
	 */
	public List<OmsRoles> rgStaffMemberRolesRoleRecordGroup() {
		final String sql = getQuery("OUMASSMU_FIND_RGSTAFFMEMBERROLESROLE");
		final RowMapper<OmsRoles> omsRolesRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRoles.class,
				omsRolesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), omsRolesRowMapper);
		} catch (Exception e) {
			logger.error("In  method rgStaffMemberRolesRoleRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkModPrivAccessPrivilegeRecordGroup() {
		final String sql = getQuery("OUMASSMU_FIND_CGFKMODPRIVACCESSPRIVILEGE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("In  method cgfkModPrivAccessPrivilegeRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OmsModules>
	 */
	public List<OmsModules> cgfkModPrivModuleNameRecordGroup() {
		final String sql = getQuery("OUMASSMU_FIND_CGFKMODPRIVMODULENAME");
		final RowMapper<OmsModules> omsModulesRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), omsModulesRowMapper);
		} catch (Exception e) {
			logger.error("In  method cgfkModPrivModuleNameRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkModPrivModPrivAcc
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkModPrivModPrivAcc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OUMASSMU_CGFKCHK_MOD_PRIV_MOD_PRIV_ACC");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkModPrivModPrivOms
	 *
	 * @param params
	 *
	 */
	public OmsModules cgfkchkModPrivModPrivOms(final OmsModules paramBean) {
		final String sql = getQuery("OUMASSMU_CGFKCHK_MOD_PRIV_MOD_PRIV_OMS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("MODULENAME", paramBean.getModuleName()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkModulePrivsPk
	 *
	 * @param params
	 *
	 */
	public ModulePrivileges cguvchkModulePrivsPk(final ModulePrivileges paramBean) {
		final String sql = getQuery("OUMASSMU_CGUVCHK_MODULE_PRIVS_PK");
		final RowMapper<ModulePrivileges> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ModulePrivileges.class,
				modulePrivilegesMapping);
		final ModulePrivileges returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}


}


