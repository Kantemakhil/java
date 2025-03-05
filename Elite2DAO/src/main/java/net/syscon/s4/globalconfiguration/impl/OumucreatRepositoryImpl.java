package net.syscon.s4.globalconfiguration.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ADUser;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OumucreatRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.OmsUsersList;

@Repository
public class OumucreatRepositoryImpl extends RepositoryBase implements OumucreatRepository {
	private static Logger logger = LogManager.getLogger(OumucreatRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> staffMemMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSONNEL_TYPE", 				new FieldMapper("personnelType"))
			.put("DEFAULT_PRINTER_ID", 			new FieldMapper("defaultPrinterId"))
			.put("ROLE", 						new FieldMapper("role"))
			.put("SUFFIX", 						new FieldMapper("suffix"))
			.put("TERMINATION_DATE", 			new FieldMapper("terminationDate"))
			.put("AS_OF_DATE", 					new FieldMapper("asOfDate"))
			.put("MIDDLE_NAME", 				new FieldMapper("middleName"))
			.put("COMM_RECEIPT_PRINTER_ID",		new FieldMapper("commReceiptPrinterId"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("POSITION", 					new FieldMapper("position"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",  			new FieldMapper("createDateTime"))
			.put("FORCE_PASSWORD_CHANGE_FLAG", 	new FieldMapper("forcePasswordChangeFlag"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("WORKING_CASELOAD_ID", 		new FieldMapper("workingCaseloadId"))
			.put("STATUS", 						new FieldMapper("status"))
			.put("SUSPENDED_FLAG", 				new FieldMapper("suspendedFlag"))
			.put("ASSIGNED_CASELOAD_ID", 		new FieldMapper("assignedCaseloadId"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("FIRST_LOGON_FLAG", 			new FieldMapper("firstLogonFlag"))
			.put("USER_ID", 					new FieldMapper("userId"))
			.put("LAST_PASSWORD_CHANGE_DATE", 	new FieldMapper("lastPasswordChangeDate"))
			.put("STAFF_ID", 					new FieldMapper("staffId"))
			.put("SUPERVISOR_STAFF_ID", 		new FieldMapper("supervisorStaffId"))
			.put("SUSPENSION_REASON", 			new FieldMapper("suspensionReason"))
			.put("NAME_SEQUENCE", 				new FieldMapper("nameSequence"))
			.put("ABBREVIATION", 				new FieldMapper("abbreviation"))
			.put("LICENSE_EXPIRY_DATE", 		new FieldMapper("licenseExpiryDate"))
			.put("BIRTHDATE", 					new FieldMapper("birthdate"))
			.put("TITLE", 						new FieldMapper("title"))
			.put("SEX_CODE", 					new FieldMapper("sexCode"))
			.put("SUSPENSION_DATE", 			new FieldMapper("suspensionDate"))
			.put("BADGE_ID", 					new FieldMapper("badgeId"))
			.put("WORKING_STOCK_LOC_ID", 		new FieldMapper("workingStockLocId"))
			.put("UPDATE_ALLOWED_FLAG", 		new FieldMapper("updateAllowedFlag"))
			.put("LICENSE_CODE", 				new FieldMapper("licenseCode"))
			.put("EMERGENCY_CONTACT", 			new FieldMapper("emergencyContact"))
			.put("QUEUE_CLUSTER_ID", 			new FieldMapper("queueClusterId"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("USERIDVAL", 					new FieldMapper("userIdVal"))
			.build();


	private final Map<String, FieldMapper> adUserMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER_ID", 				new FieldMapper("userId"))
			.put("PWD", 			new FieldMapper("password"))
			.build();

	public OumucreatRepositoryImpl() {

	}

	@Override
	public boolean createUser(String userName, String passWord, String defaultTableSpace, String tempTableSpace) {
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
		String pwd='"'+passWord+'"';
		preparedSql = preparedSql.replace(":userName", userName).replace(":passWord", pwd)
				.replace(":defaultTableSpace", defaultTableSpace).replace(":tempTableSpace", tempTableSpace);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
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
	public boolean grantPermToUser(String userName) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("GRANT CREATE USER,CREATE SESSION,ALTER USER,TAG_USER TO :userName" + "");

			sqlQuery.append(" WITH ADMIN OPTION");


		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
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
	public Integer getUserDeatils(String userName) {
		Integer userId = null;
		final String sql = getQuery("CHECK_USER_EXIST");
		try {
			userId = namedParameterJdbcTemplate.queryForObject(sql, createParams("userName", userName.toUpperCase()), Integer.class);
		}catch(Exception e) {
			logger.error(e.getMessage());

		}
		return userId;
	}

	@Override
	public boolean connectPermToUser(String userName, String grantUser) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("Alter user :userName" + "").append(" grant connect through :grantUser "+"");


		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName).replace(":grantUser", grantUser);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
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
	public boolean tagUserToUser(String userName) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		if (userName != null) {
			sqlQuery.append("GRANT TAG_USER TO :userName" + "");

		}
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql.replace(":userName", userName);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
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
	public Integer insertOmsUsersList(OmsUsersList inputBean)  {
		String sql = getQuery("OUMUCREAT_INSERT_OMS_USERS_LIST");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("userId", inputBean.getUserId(), "password", inputBean.getPassword(),
					"passwordExpiryDate", inputBean.getPasswordExpiryDate(), "accountStatus", inputBean.getAccountStatus(),
					"failedLoginAttempts", inputBean.getFailedLoginAttempts(), "createUserId", inputBean.getCreateUserId()));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertOmsUsersList and Exception is : {}", e.getMessage());
			return 0;
		}
		return 1;
	}


	@Override
	public Integer UpdateOmsUser(OmsUsersList inputBean)  {
		String sql = getQuery("OUMUCREAT_UPDATE_OMS_USERS_LIST");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("userId", inputBean.getUserId(), "password", inputBean.getPassword(),"modifyUserId",inputBean.getModifyUserId()));
		} catch (Exception e) {
			logger.error("UpdateOmsUser failled for : {} error is : {}",inputBean.getUserId(), e.getMessage());
			return 0;
		}
		return 1;
	}




	@Override
	public Integer saveEncryptPwd(String userName, String passWord, String createUserId) {
		final String sql = getQuery("SAVE_ENCRYPT_PWD");
		Integer lireturn = 0;
		Map<String, String> map = new HashedMap();
		map.put("userId", userName);
		map.put("pwd", passWord);
		map.put("createUserId", createUserId);
		try {
			lireturn = namedParameterJdbcTemplate.update(sql, map);
		}catch(Exception e) {
			logger.error(this.getClass().getName() + " saveEncryptPwd and Exception is : {}", e.getMessage());
		}
		return lireturn;
	}
	@Override
	public Integer verifyEmailId(String emailId) {
		Integer userId = null;
		final String sql = getQuery("CHECK_EMAIL_EXIST");
		try {
			userId = namedParameterJdbcTemplate.queryForObject(sql, createParams("emailId", emailId.toUpperCase()), Integer.class);
		}catch(Exception e) {
			logger.error(this.getClass().getName() + " saveEncryptPwd and Exception is : {}", e.getMessage());
		}
		return userId;
	}

	@Override
	public List<StaffMembers> verifyEmailIdbyStaffId(String emailId, Integer staffId) {
		final String sql = getQuery("STAFF_FIND_STAFF_MEMBERS");
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		valuesList.addValue("emailId", emailId);
		final RowMapper<StaffMembers> staMemRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMemMapping);
		return namedParameterJdbcTemplate.query(sql,valuesList, staMemRowMapper);

	}

	@Override
	public String getpwd(String mailId) {
		String pwd = "";
		final String sql = getQuery("OUMUCREAT_GET_PWD");
		try {
			pwd = namedParameterJdbcTemplate.queryForObject(sql, createParams("mailId", mailId), String.class);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return pwd;
	}
	@Override
	public List<ADUser> getADUserDeatils() {
		List<ADUser> adUsers = null;
		final String sql = getQuery("AD_USER_DETAILS");
		final RowMapper<ADUser> adRowMapper = Row2BeanRowMapper.makeMapping(sql, ADUser.class,
				adUserMapping);
		try {
			adUsers = namedParameterJdbcTemplate.query(sql, adRowMapper);
		}catch(Exception e) {
			logger.error("getADUserDeatils :{}",e.getMessage());
		}
		return adUsers;
	}

	@Override
	public Integer ResetADUser(List<OmsUsersList> userList) {

		final String sql = getQuery("OUMUCREAT_UPDATE_OMS_USERS_LIST");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OmsUsersList user : userList) {
			parameters.add(new BeanPropertySqlParameterSource(user));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (userList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}
