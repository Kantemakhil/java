package net.syscon.s4.sa.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.sa.OumcpassRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OumcpassRepositoryImpl
 */
@Repository
public class OumcpassRepositoryImpl extends RepositoryBase implements OumcpassRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumcpassRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	private final Map<String, FieldMapper> staffAccMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DOMAIN", new FieldMapper("domain")).build();

	/**
	 * Creates new OumcpassRepositoryImpl class Object
	 */
	public OumcpassRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffMembers
	 *
	 * @return List<StaffMembers>
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> staffExecuteQuery(StaffMembers objSearchDao) {
		final String sql = getQuery("OUMCPASS_STAFF_FIND_STAFF_MEMBERS");
		final RowMapper<StaffMembers> StaffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), StaffMembersRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *
	 * @throws SQLException
	 */
	public Integer staffUpdateStaffMembers(final String userId) {
		String sql = getQuery("OUMCPASS_STAFF_UPDATE_STAFF_MEMBERS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("L_USER_NAME", userId);
		paramMap.put("modifyUserId", userId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffAccessibleCaseloads
	 *
	 * @return List<StaffAccessibleCaseloads>
	 *
	 * @throws SQLException
	 */
	public List<StaffAccessibleCaseloads> staffAcExecuteQuery(StaffAccessibleCaseloads objSearchDao) {
		final String sql = getQuery("OUMCPASS_STAFFAC_FIND_STAFF_ACCESSIBLE_CASELOADS");
		final RowMapper<StaffAccessibleCaseloads> StaffAccessibleCaseloadsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffAccessibleCaseloads.class, staffAccMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), StaffAccessibleCaseloadsRowMapper);
	}

	@Override
	public Integer changePassword(String userId, String oldPassword, String newPassword) {
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("username", OracleTypes.VARCHAR),
				new SqlParameter("password", OracleTypes.VARCHAR),
				new SqlParameter("old_password", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_IR").withProcedureName("CHANGE_USER_PASSWORD").declareParameters(sqlParameters);
		inParamMap.put("username", userId);
		inParamMap.put("password", newPassword);
		inParamMap.put("old_password", oldPassword);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	@Override
	public Map<String, Object> validatePassword(final String password) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PASSWORD", Types.VARCHAR),
				new SqlOutParameter("P_MIN_PASSWD_LEN", Types.INTEGER) ,
				new SqlOutParameter("P_ERROR_MESSAGE", Types.VARCHAR)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_UTILS").withFunctionName("VALIDATE_PASSWORD").declareParameters(sqlParameters);
		inParamMap.put("P_PASSWORD", password);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error("validatePassword", e);
		}
		return returnObject;
	}

	@Override
	public boolean authenticate(String username, String password) {
		return false;
	}
}
