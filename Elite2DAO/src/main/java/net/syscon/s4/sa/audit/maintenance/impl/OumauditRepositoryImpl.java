package net.syscon.s4.sa.audit.maintenance.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
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
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.audit.maintenance.AllAuditPolicies;
import net.syscon.s4.sa.audit.maintenance.OumauditRepository;

/**
 * Class OumauditRepositoryImpl
 */
@Repository
public class OumauditRepositoryImpl extends RepositoryBase implements OumauditRepository {

	/**
	 * Creates new OumauditRepositoryImpl class Object
	 */
	public OumauditRepositoryImpl() {
		/*
		 * OumauditRepositoryImpl
		 */
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumauditRepositoryImpl.class);
	private final Map<String, FieldMapper> allAuditPoliciesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OBJECT_NAME", new FieldMapper("objectName")).put("SEL", new FieldMapper("sel"))
			.put("INHERITED", new FieldMapper("inherited")).put("AUDIT_TRAIL", new FieldMapper("auditTrail"))
			.put("POLICY_COLUMN_OPTIONS", new FieldMapper("policyColumnOptions"))
			.put("POLICY_OWNER", new FieldMapper("policyOwner")).put("COMMON", new FieldMapper("common"))
			.put("DEL", new FieldMapper("del")).put("POLICY_NAME", new FieldMapper("policyName"))
			.put("INS", new FieldMapper("ins")).put("UPD", new FieldMapper("upd"))
			.put("POLICY_COLUMN", new FieldMapper("policyColumn")).put("POLICY_TEXT", new FieldMapper("policyText"))
			.put("ENABLED", new FieldMapper("enabled")).put("PF_PACKAGE", new FieldMapper("pfPackage"))
			.put("OBJECT_SCHEMA", new FieldMapper("objectSchema")).put("PF_FUNCTION", new FieldMapper("pfFunction"))
			.put("PF_SCHEMA", new FieldMapper("pfSchema")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OBJECT_NAME", new FieldMapper("objectName")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AllAuditPolicies
	 *
	 * @return List<AllAuditPolicies>
	 */
	public List<AllAuditPolicies> allAuditPoliciesExecuteQuery(final AllAuditPolicies objSearchDao) {
		final String sql = getQuery("OUMAUDIT_ALLAUDITPOLICIES_FIND_ALL_AUDIT_POLICIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getObjectName() != null && !objSearchDao.getObjectName().trim().equals("")) {
				sqlQuery.append(" OBJECT_NAME = :objectName and");
				params.addValue("objectName", objSearchDao.getObjectName());
			}
			if (objSearchDao.getEnabled() != null && !objSearchDao.getEnabled().trim().equals("")) {
				sqlQuery.append(" ENABLED = :enabled and");
				params.addValue("enabled", objSearchDao.getEnabled());
			}
			if (objSearchDao.getSel() != null && !objSearchDao.getSel().trim().equals("")) {
				sqlQuery.append(" SEL = :sel and");
				params.addValue("sel", objSearchDao.getSel());
			}
			if (objSearchDao.getIns() != null && !objSearchDao.getIns().trim().equals("")) {
				sqlQuery.append(" INS = :ins and");
				params.addValue("ins", objSearchDao.getIns());
			}
			if (objSearchDao.getUpd() != null && !objSearchDao.getUpd().trim().equals("")) {
				sqlQuery.append(" UPD  = :upd and");
				params.addValue("upd", objSearchDao.getUpd());
			}
			if (objSearchDao.getDel() != null && !objSearchDao.getDel().trim().equals("")) {
				sqlQuery.append(" DEL  = :del and");
				params.addValue("del", objSearchDao.getDel());
			}
			if (objSearchDao.getPolicyText() != null && !objSearchDao.getPolicyText().trim().equals("")) {
				sqlQuery.append(" POLICY_TEXT  = :policyText and");
				params.addValue("policyText", objSearchDao.getPolicyText());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<AllAuditPolicies> allAuditPoliciesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				AllAuditPolicies.class, allAuditPoliciesMapping);
		return namedParameterJdbcTemplate.query(preparedSql, params, allAuditPoliciesRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgDbObjectsRecordGroup() {
		final String sql = getQuery("OUMAUDIT_FIND_RGDBOBJECTS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public int enableOrDisablePolicy(final AllAuditPolicies searchBean) {
		int returnValue = 0;
		final List<SqlParameter> paramList = new ArrayList<>();
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			jdbcTemplate.call(new CallableStatementCreator() {
				public CallableStatement createCallableStatement(final Connection con) throws SQLException {
					CallableStatement callableStatement = null;
					try {
						callableStatement = con.prepareCall("{call TAG_AUDIT.SETPOLICY_OUMAUDIT(?, ?, ?)}");
						callableStatement.setString(1, searchBean.getObjectName());
						callableStatement.setString(2, searchBean.getPolicyName());
						callableStatement.setInt(3, searchBean.getEnableOrDisable());
						callableStatement.execute();

					} catch (final SQLException e) {
						logger.error(e.getMessage());
					}
					return callableStatement;
				}
			}, paramList);
			returnValue = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}

		return returnValue;
	}

	@Override
	public int createPolicy(final AllAuditPolicies searchBean, final String statType) {
		int result = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("p_object_name", Types.VARCHAR),
				new SqlParameter("p_stat_type", Types.VARCHAR), new SqlParameter("p_conditions", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withCatalogName("tag_audit")
				.withProcedureName("create_policy").declareParameters(sqlParameters);
		inParamMap.put("p_object_name", searchBean.getObjectName());
		inParamMap.put("p_stat_type", statType);
		inParamMap.put("p_conditions", searchBean.getPolicyText());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			result = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean checkPolicyExists(final String objectName) {
		int result = 0;
		final String sql = getQuery("OUMAUDIT_CHECK_POLICY_EXISTS");
		result = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_object_name", objectName),
				Integer.class);
		return result > 0;
	}

	@Override
	public int dropPolicy(final AllAuditPolicies searchBean) {
		int result = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("p_object_name", Types.VARCHAR),
				new SqlParameter("p_policy_name", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withCatalogName("tag_audit")
				.withProcedureName("droppolicy").declareParameters(sqlParameters);
		inParamMap.put("p_object_name", searchBean.getObjectName());
		inParamMap.put("p_policy_name", searchBean.getPolicyName());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			result = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int disableAll() {
		int result = 0;
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withCatalogName("tag_audit")
				.withProcedureName("disable_all").declareParameters();
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute();
			result = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int enableAll() {
		int result = 0;
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withCatalogName("tag_audit")
				.withProcedureName("enable_all").declareParameters();
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute();
			result = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int dropAll() {
		int result = 0;
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withCatalogName("tag_audit")
				.withProcedureName("drop_all").declareParameters();
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute();
			result = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int createAll() {
		int result = 0;
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withCatalogName("tag_audit")
				.withProcedureName("create_all").declareParameters();
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute();
			result = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<ReferenceCodes> getAllTableNames() {
		final String sql = getQuery("OUMAUDIT_GET_ALL_TABLENAMES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

}
