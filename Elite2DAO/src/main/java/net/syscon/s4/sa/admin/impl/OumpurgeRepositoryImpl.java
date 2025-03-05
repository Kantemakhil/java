package net.syscon.s4.sa.admin.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.sa.admin.OumpurgeRepository;

@Repository
public class OumpurgeRepositoryImpl extends RepositoryBase implements OumpurgeRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumpurgeRepositoryImpl.class);
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("BOOKING_STATUS", 						new FieldMapper("bookingStatus"))
			.put("TRUST_COUNT", 						new FieldMapper("nbtOffenderBookId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put(" SEAL_FLAG  ", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> offTrustAccMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Creates new OumpurgeRepositoryImpl class Object
	 */
	public OumpurgeRepositoryImpl() {

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VHeaderBlock
	 *
	 * @return List<VHeaderBlock>
	 */
	public List<VHeaderBlock> offExecuteQuery(final VHeaderBlock objSearchDao,String count) {
		final String sql = getQuery("OUMPURGE_OFF_FIND_V_HEADER_BLOCK");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getOffenderIdDisplay() != null && !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
				sqlQuery.append(" OFFENDER_ID_DISPLAY = :offenderIdDisplay and");
				params.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
			}
			if (objSearchDao.getLastName() != null && !objSearchDao.getLastName().trim().equals("")) {
				sqlQuery.append(" LAST_NAME = :lastName and");
				params.addValue("lastName", objSearchDao.getLastName());
			}
			if (objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().trim().equals("")) {
				sqlQuery.append(" FIRST_NAME = :firstName and");
				params.addValue("firstName", objSearchDao.getFirstName());
			}
		}
		String defWhere = count;
		if (defWhere != null) {
			sqlQuery.append(defWhere);
			params.addValue("caseload_id", objSearchDao.getCaseLoadId());
			params.addValue("caseload_type", objSearchDao.getAgyLocId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		params.addValue("USERID", objSearchDao.getCreateUserId());
		preparedSql = preparedSql.concat(" ORDER BY LAST_NAME, FIRST_NAME ASC");
		final RowMapper<VHeaderBlock> vHeaderBlockRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VHeaderBlock.class, referenceCodesMapping);
		return namedParameterJdbcTemplate.query(preparedSql, params, vHeaderBlockRowMapper);
	}

	@Override
	public String defWhereCondition() {
		Map<String, Object> returnObject = null;
		String count = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		String whereClause = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_FORM_NAME", Types.VARCHAR),
				new SqlOutParameter("P_DEF_WHERE", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_MAIN").withProcedureName("DEF_BKG_WHERE").declareParameters(sqlParameters);
		inParamMap.put("P_FORM_NAME", "OUMPURGE");
		inParamMap.put("P_DEF_WHERE", whereClause);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			count = (String) returnObject.get("P_DEF_WHERE");
		} catch (Exception e) {
			count = null;
		}
		return count;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVHeaderBlock List<VHeaderBlock>
	 */
	public Integer offUpdateVHeaderBlock(final List<VHeaderBlock> lstVHeaderBlock) {
		final String sql = getQuery("OUMPURGE_OFF_UPDATE_V_HEADER_BLOCK");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final VHeaderBlock vHeaderBlock : lstVHeaderBlock) {
			parameters.add(new BeanPropertySqlParameterSource(vHeaderBlock));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVHeaderBlock.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 * 
	 */
	public List<OffenderBookings> offBkgExecuteQuery(final OffenderBookings objSearchDao) {
		final String sql = getQuery("OUMPURGE_OFFBKG_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> offenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", objSearchDao.getRootOffenderId()),
				offenderBookingsRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBookings List<OffenderBookings>
	 */
	public Integer offBkgUpdateOffenderBookings(final List<OffenderBookings> lstOffenderBookings) {
		final String sql = getQuery("OUMPURGE_OFFBKG_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderBookings offenderBookings : lstOffenderBookings) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBookings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderBookings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<OffenderBookings> offOnCheckDeleteMaster(final OffenderBookings paramBean) {
		final String sql = getQuery("OUMPURGE_OFF_ONCHECKDELETEMASTER");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffContactsOffBkg
	 *
	 * @param params
	 */
	public String BookingsOne(final String bookingStatus) {
		final String sql = getQuery("OUMPURGE_CGFKCHK_OFF_CONTACTS_OFF_BKG");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", bookingStatus),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public Integer getLvCountSealBookings(final BigDecimal rootOffenderId) {
		final String sql = getQuery("OUMPURGE_LV_COUNT_SEAL_BOOKINGS");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_root_id", rootOffenderId),
					Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public List<OffenderTrustAccounts> showCaseloadAcct(final VHeaderBlock paramBean) {
		final String sql = getQuery("OUMPURGE_SHOW_CASELOAD_ACCT");
		final RowMapper<OffenderTrustAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustAccounts.class, offTrustAccMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("lv_root_off_id", paramBean.getRootOffenderId()),
				columnRowMapper);
	}

	public Integer checkActiveBooking(final VHeaderBlock paramBean) {
		final String sql = getQuery("OUMPURGE_CHECK_ACTIVE_BOOKING");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lv_root_off_id", paramBean.getRootOffenderId()), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;

	}

	public Integer processRecord(final VHeaderBlock paramBean) {
		final String sql = getQuery("OUMPURGE_GET_BOOKING_CNT_CUR");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lv_root_off_id", paramBean.getRootOffenderId()), Integer.class);

		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String getProfileValue() {
		final String sql = getQuery("OUMPURGE_GET_PROFILE_VALUE");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;

	}

	public String getCaseloadSubStr(final String caseloadValue) {
		final String sql = getQuery("OUMPURGE_GET_SUBSTR_VAL");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOADID", caseloadValue),
					String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public String chkValueExists(final VHeaderBlock bean) {
		Map<String, Object> returnObject = null;
		String count = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		String whereClause = bean.getStatus1();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_table_name", Types.VARCHAR),
				new SqlOutParameter("p_where", Types.VARCHAR), new SqlParameter("RETURN_VALUE", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OUMPURGE").withFunctionName("CHK_VALUE_EXISTS").declareParameters(sqlParameters);

		inParamMap.put("p_table_name", "SOR_CONTROL_TABLES");
		inParamMap.put("p_where", whereClause);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			count = (String) returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			count = null;
		}
		return count;
	}

	public Integer getBeneficiaryAcc(final BigDecimal paramBean) {
		final String sql = getQuery("OUMPURGE_GET_BENEFICIARY_ACC_CUR");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_ID", paramBean),
					Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer getTrustAccountCode(final VHeaderBlock paramBean) {
		final String sql = getQuery("OUMPURGE_GET_TRUST_ACCOUNT_CODE");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERID", paramBean.getRootOffenderId()), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String getSealedFlag(final VHeaderBlock paramBean) {
		final String sql = getQuery("OUMPURGE_GET_SEALED_FLAG");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERID", paramBean.getRootOffenderId()), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getPurgeOffenders(final BigDecimal offenderId, final String statusReason,
			final BigDecimal offenderBookId) {
		String procedureQuery = "{CALL OMS_OWNER.OUMPURGE.PURGE_OFFENDERS(:P_ROOT_OFFENDER_ID, :P_OFF_BOOK_ID, :P_DEL_TYPE, :P_STATUS)}";
		try (Connection connection = dataSource.getConnection()) {
			CallableStatement callableStatement = connection.prepareCall(procedureQuery);
			callableStatement.setBigDecimal("P_ROOT_OFFENDER_ID", offenderId);
			callableStatement.setBigDecimal("P_OFF_BOOK_ID", offenderBookId);
			callableStatement.setString("P_DEL_TYPE", statusReason);
			callableStatement.registerOutParameter("P_STATUS", Types.VARCHAR);
			callableStatement.execute();
			Map<String, Object> returnMapData = new HashMap<>();
			returnMapData.put("P_STATUS", callableStatement.getString("P_STATUS"));
			return returnMapData;
		} catch (Exception e) {
			logger.error("getSubActType", e);
		}
		return null;
	}

	public String getSubString(final String data, final String subString) {
		final String sql = getQuery("OUMPURGE_GET_SUBSTR");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("DATA", data, "STATUSREASON", subString), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getSealOffenders(final BigDecimal offenderId, final String statusReason,
			final BigDecimal offenderBookId, final String sealFlag) {
		String procedureQuery = "{CALL OMS_OWNER.OUMPURGE.SEALING_OFFENDERS(:P_OFFENDER_ID, :P_OFF_BOOK_ID, :P_SEAL_TYPE, :P_SEAL_FLAG, :P_STATUS)}";
		try (Connection connection = dataSource.getConnection()) {
			CallableStatement callableStatement = connection.prepareCall(procedureQuery);
			callableStatement.setBigDecimal("P_OFFENDER_ID", offenderId);
			callableStatement.setBigDecimal("P_OFF_BOOK_ID", offenderBookId);
			callableStatement.setString("P_SEAL_TYPE", statusReason);
			callableStatement.setString("P_SEAL_FLAG", sealFlag);
			callableStatement.registerOutParameter("P_STATUS", Types.VARCHAR);
			callableStatement.execute();
			Map<String, Object> returnMapData = new HashMap<>();
			returnMapData.put("P_STATUS", callableStatement.getString("P_STATUS"));
			return returnMapData;
		} catch (Exception e) {
			logger.error("getSubActType", e);
		}
		return null;
	}
	
	@Override
	public OffenderBookings getOldData(Long offenderBookId) {
		String sql=getQuery("OUMPURGE_GET_OLDDATA");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId), new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
	}


	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OUMPURGE_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("profileType", objSearchDao.getProfileType(),
				"profileCode", objSearchDao.getProfileCode()), SystemProfilesRowMapper);
		return returnList;
	}
	
	public Integer getAccessStaffCount(final Integer roleIdVal,String userName) {
		final String sql = getQuery("OUMPURGE_SEALBUTTON_ACCESS_PERMISSION");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("roleIdVal", roleIdVal,"userID",userName), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public Integer getLvCountSealBookings(String rootOffenderId) {
		final String sql = getQuery("OUMPURGE_GET_LV_SEAL_BOOKING_COUNT");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("rootOffenderId", rootOffenderId), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
}
