package net.syscon.s4.pkgs.oms_utils.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsRepository;

@Repository
public class OmsUtilsRepositoryImpl extends RepositoryBase implements OmsUtilsRepository {

	final private static Logger logger = LogManager.getLogger(OmsUtilsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	@Override
	public Boolean changeUserPassword(final String userName, final String passWord) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("ALTER USER :userName" + "").append(" IDENTIFIED BY :PassWord " + "");

		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName).replace(":PassWord", passWord);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Integer expireUser(final String userName) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();

		if (userName != null) {
			sqlQuery.append(" ALTER user  :userName password expire ");
		}
		preparedSql = sqlQuery.toString().trim();

		preparedSql = preparedSql.replace(":userName", userName);

		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}

			});
		} catch (Exception e) {
			logger.error("expireUser", e);
			return 0;
		}
		return 1;
	}

	@Override
	public String getStaffName(final BigDecimal supervisorStaffId) {
		final String sql = getQuery("GET_STAFF_NAME");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_staff_id", supervisorStaffId),
					String.class);
		} catch (Exception e) {
			logger.error("getStaffName", e);
			retVal = null;
		}
		return retVal;
	}
    
	@Override
	public Integer getStaffId(String userName) {
		final String sql = getQuery("STAFF_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId",userName), Integer.class);
	}

	@Override
	public Integer getPosition(final String pUserid) {
		final String sql = getQuery("GET_POSITION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_USERID", pUserid), Integer.class);
	}

	@Override
	public String getIllegalChars(final String pUserid) {
		final String sql = getQuery("GET_ILLEGAL_CHARS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_USERID", pUserid), String.class);
	}

	@Override
	public Long getProfilevalue() {
		final String sql = getQuery("GET_PROFILE_VALUE");
		Long profvalue = null;
		try {
			profvalue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getProfilevalue :" + e);
			return 0l;
		}
		return profvalue;
	}

	@Override
	public Integer getSubString(final String passWord) {
		final String sql = getQuery("GET_SUB_STRING");
		Integer profvalue = null;
		try {
			profvalue = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_password", passWord),
					Integer.class);
		} catch (Exception e) {
			logger.error("getSubString :" + e);
			return 0;
		}
		return profvalue;
	}

	@Override
	public String getLtrim(final String passWord) {
		final String sql = getQuery("GETLTRIM");
		String profvalue = null;
		try {
			profvalue = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_password", passWord),
					String.class);
		} catch (Exception e) {
			logger.error("getLtrim :" + e);
		}
		return profvalue;
	}

	@Override
	public SystemMessages getSystemMsg(final Integer msgNumber, final String applicationSystem) {
		final String sql = getQuery("GET_SYSTEM_MESSAGES_CUR");
		SystemMessages bean = new SystemMessages();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_message_number", msgNumber, "p_application_system", applicationSystem),
					SystemMessages.class);
		} catch (Exception e) {
			logger.error("getSystemMsg :" + e);
		}
		return bean;
	}

	@Override
	public String checkProfCur() {
		final String sql = getQuery("CHECK_SEC_PROF_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public Boolean createUser(final String userName, final String passWord, final String defaultTableSpace,
			final String tempTableSpace) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append(" CREATE USER :userName" + "");

		}
		if (passWord != null) {
			sqlQuery.append(" IDENTIFIED BY  :passWord" + "");

		}
		sqlQuery.append(" DEFAULT TABLESPACE :defaultTableSpace" + "")
				.append(" TEMPORARY TABLESPACE :tempTableSpace" + " ");

		preparedSql = sqlQuery.toString().trim();
		String pwd = '"' + passWord + '"';
		preparedSql = preparedSql.replace(":userName", userName).replace(":passWord", pwd)
				.replace(":defaultTableSpace", defaultTableSpace).replace(":tempTableSpace", tempTableSpace);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}

			});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;

	}

	@Override
	public Boolean grantPermToUser(final String userName) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("GRANT CREATE SESSION,TAG_USER TO,CONNECT TO,ALTER USER:userName" + "");
			sqlQuery.append(" WITH ADMIN OPTION");
		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {
				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean toAssignProfile(final String userName) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("GRANT ALTER USER :userName" + "PROFILE ELITE_PROFILE");

		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {
				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean expirePassword(final String userName) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("GRANT ALTER USER :userName" + "PASSWORD EXPIRE");
		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {
				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public String grantS4UserProxy(final String userName) {
		final String sql = getQuery("PROXY_USER_CUR");
		String proVal = null;
		try {
			proVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("grantS4UserProxy :" + e);
		}
		return proVal;
	}

	@Override
	public Boolean connectPermToUser(final String userName, final String grantUser) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("Alter user :userName" + "").append(" grant connect through :grantUser " + "");

		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName).replace(":grantUser", grantUser);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}

			});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Offenders getOffBookCur(final Long offenderBookId) {
		final String sql = getQuery("GET_OFF_BOOK_CUR");
		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offenderBookId),
				rowMapper);
	}

	@Override
	public BigDecimal staffCur(final String userId) {
		final String sql = getQuery("STAFF_CUR_USER");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), BigDecimal.class);
	}

}