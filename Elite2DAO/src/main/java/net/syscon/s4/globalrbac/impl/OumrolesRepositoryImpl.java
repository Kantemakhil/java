package net.syscon.s4.globalrbac.impl;

import java.sql.SQLException;
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
import net.syscon.s4.globalrbac.OumrolesRepository;
import net.syscon.s4.im.beans.OmsRoles;

/**
 * Class OumrolesRepositoryImpl
 */
@Repository
public class OumrolesRepositoryImpl extends RepositoryBase implements OumrolesRepository {
	
	private static Logger logger = LogManager.getLogger(OumrolesRepositoryImpl.class.getName());

	/**
	 * Creates new OumrolesRepositoryImpl class Object
	 */
	public OumrolesRepositoryImpl() {
	}

	private final Map<String, FieldMapper> omsRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARENT_ROLE_CODE", new FieldMapper("parentRoleCode"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("ROLE_CODE", new FieldMapper("roleCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime")).put("ROLE_ID", new FieldMapper("roleId"))
			.put("ROLE_SEQ", new FieldMapper("roleSeq")).put("ROLE_NAME", new FieldMapper("roleName")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OmsRoles
	 *
	 * @return List<OmsRoles>
	 *
	 * @throws SQLException
	 */
	public List<OmsRoles> omsRoleExecuteQuery(final OmsRoles objSearchDao) {
		final String sql = getQuery("OUMROLES_OMSROLE_FIND_OMS_ROLES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getRoleId() != null) {
				sqlQuery.append(" ROLE_ID =:roleId  " + " and");
				params.addValue("roleId", objSearchDao.getRoleId());
			}
			if (objSearchDao.getRoleCode() != null && objSearchDao.getRoleCode().trim().length() != 0) {
				sqlQuery.append(" ROLE_CODE =:roleCode " + " and");
				params.addValue("roleCode", objSearchDao.getRoleCode());
			}
			if (objSearchDao.getRoleName() != null && objSearchDao.getRoleName().trim().length() != 0) {
				sqlQuery.append(" ROLE_NAME = :roleName " + " and");
				params.addValue("roleName", objSearchDao.getRoleName());
			}
			if (objSearchDao.getRoleSeq() != null) {
				sqlQuery.append(" ROLE_SEQ = :roleSeq " + " and");
				params.addValue("roleSeq", objSearchDao.getRoleSeq());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY ROLE_SEQ";
		final RowMapper<OmsRoles> omsRolesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, OmsRoles.class,
				omsRolesMapping);
		final ArrayList<OmsRoles> returnList = (ArrayList<OmsRoles>) namedParameterJdbcTemplate.query(preparedSql,
				params, omsRolesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() throws SQLException {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOmsRoles List<OmsRoles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer omsRoleInsertOmsRoles(final List<OmsRoles> lstOmsRoles) {
		final String sql = getQuery("OUMROLES_OMSROLE_INSERT_OMS_ROLES_TEST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsRoles omsroles : lstOmsRoles) {
			parameters.add(new BeanPropertySqlParameterSource(omsroles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRoles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOmsRoles List<OmsRoles>
	 *
	 * @throws SQLException
	 */
	public Integer omsRoleUpdateOmsRoles(final List<OmsRoles> lstOmsRoles) {
		final String sql = getQuery("OUMROLES_OMSROLE_UPDATE_OMS_ROLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsRoles omsRoles : lstOmsRoles) {
			parameters.add(new BeanPropertySqlParameterSource(omsRoles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRoles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOmsRoles List<OmsRoles>
	 *
	 * @throws SQLException
	 */
	public Integer omsRoleDeleteOmsRoles(final List<OmsRoles> lstOmsRoles) {
		final String sql = getQuery("OUMROLES_OMSROLE_DELETE_OMS_ROLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsRoles omsRoles : lstOmsRoles) {
			parameters.add(new BeanPropertySqlParameterSource(omsRoles));
		}
		try {
			String tableName = "OMS_ROLES";
			String whereClause = "ROLE_ID  = :roleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method omsRoleDeleteOmsRoles", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String[] foreignKeys = { "iwp_template_roles_fk1", "doc_tmp_ro_oms_role_fk1", "mod_priv_oms_role_f1",
					"staff_role_oms_role_f1", "stf_mbr_roles_oms_roles_fk2", "user_caseload_roles_fk2",
					"rle_inarc_oms_role_fk1", "oms_roles_oms_roles_fk" };
			for (int i = 0; i < foreignKeys.length; i++) {
				if (e.getMessage() != null && e.getMessage().contains(foreignKeys[i])) {
					return 2;
				}
			}
		}
		if (lstOmsRoles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * omsRolePreInsert
	 *
	 * @param params
	 *
	 */
	public Object omsRolePreInsert() {
		final String sql = getQuery("OUMROLES_OMS_ROLE_PREINSERT");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
		return returnObj;
	}

}
