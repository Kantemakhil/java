package net.syscon.s4.inst.booking.maintainence.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.inst.booking.maintainence.OimadmisRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OimadmisRepositoryImpl
 */
@Repository
public class OimadmisRepositoryImpl extends RepositoryBase implements OimadmisRepository {
	public OimadmisRepositoryImpl() {
		// OimctactRepositoryImpl
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimadmisRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("APPLN_CODE", new FieldMapper("applnCode")).put("MESSAGE_TYPE", new FieldMapper("messageType"))
			.put("MESSAGE_TEXT", new FieldMapper("messageText")).put("MESSAGE_NUMBER", new FieldMapper("messageNumber"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> beanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MESSAGE_NUMBER", new FieldMapper("messageNumber")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadAdmAlertProfiles
	 *
	 * @return List<CaseloadAdmAlertProfiles>
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAdmAlertProfiles> caseloadAdmAlertProfilesExecuteQuery(
			final CaseloadAdmAlertProfiles objSearchDao) {
		final String sql = getQuery("OIMADMIS_CASELOADADMALERTPROFILES_FIND_CASELOAD_ADM_ALERT_PROFILES");
		final RowMapper<CaseloadAdmAlertProfiles> caseloadAdmAlertProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmAlertProfiles.class, beanMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadid", objSearchDao.getCaseloadId()),
					caseloadAdmAlertProfilesRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCaseloadAdmAlertProfiles
	 *            List<CaseloadAdmAlertProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer caseloadAdmAlertProfilesInsertCaseloadAdmAlertProfiles(
			final List<CaseloadAdmAlertProfiles> lstCaseloadAdmAlertProfiles) {
		final String sql = getQuery("OIMADMIS_CASELOADADMALERTPROFILES_INSERT_CASELOAD_ADM_ALERT_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAdmAlertProfiles caseloadAdmAlertProfiles : lstCaseloadAdmAlertProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadAdmAlertProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadAdmAlertProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadAdmAlertProfiles
	 *            List<CaseloadAdmAlertProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer caseloadAdmAlertProfilesUpdateCaseloadAdmAlertProfiles(
			final List<CaseloadAdmAlertProfiles> lstCaseloadAdmAlertProfiles) {
		final String sql = getQuery("OIMADMIS_CASELOADADMALERTPROFILES_UPDATE_CASELOAD_ADM_ALERT_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAdmAlertProfiles caseloadAdmAlertProfiles : lstCaseloadAdmAlertProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadAdmAlertProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadAdmAlertProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadAdmAlertProfiles
	 *            List<CaseloadAdmAlertProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer caseloadAdmAlertProfilesDeleteCaseloadAdmAlertProfiles(
			final List<CaseloadAdmAlertProfiles> lstCaseloadAdmAlertProfiles) {
		final String sql = getQuery("OIMADMIS_CASELOADADMALERTPROFILES_DELETE_CASELOAD_ADM_ALERT_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAdmAlertProfiles caseloadAdmAlertProfiles : lstCaseloadAdmAlertProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadAdmAlertProfiles));
		}
		try {
			String tableName = "CASELOAD_ADM_ALERT_PROFILES";
			String whereClause = "CASELOAD_ID = :caseloadId AND ROW_ID = :rowId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseloadAdmAlertProfilesDeleteCaseloadAdmAlertProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadAdmAlertProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadAdmOtherProfiles
	 *
	 * @return List<CaseloadAdmOtherProfiles>
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesExecuteQuery(
			final CaseloadAdmOtherProfiles objSearchDao) {
		final String sql = getQuery("OIMADMIS_CASELOADADMOTHERPROFILES_FIND_CASELOAD_ADM_OTHER_PROFILES");
		final RowMapper<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmOtherProfiles.class, beanMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadid", objSearchDao.getCaseloadId()),
					caseloadAdmOtherProfilesRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCaseloadAdmOtherProfiles
	 *            List<CaseloadAdmOtherProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer caseloadAdmOtherProfilesInsertCaseloadAdmOtherProfiles(
			final List<CaseloadAdmOtherProfiles> lstCaseloadAdmOtherProfiles) {
		final String sql = getQuery("OIMADMIS_CASELOADADMOTHERPROFILES_INSERT_CASELOAD_ADM_OTHER_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAdmOtherProfiles caseloadAdmOtherProfiles : lstCaseloadAdmOtherProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadAdmOtherProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadAdmOtherProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadAdmOtherProfiles
	 *            List<CaseloadAdmOtherProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer caseloadAdmOtherProfilesUpdateCaseloadAdmOtherProfiles(
			final List<CaseloadAdmOtherProfiles> lstCaseloadAdmOtherProfiles) {
		final String sql = getQuery("OIMADMIS_CASELOADADMOTHERPROFILES_UPDATE_CASELOAD_ADM_OTHER_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAdmOtherProfiles caseloadAdmOtherProfiles : lstCaseloadAdmOtherProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadAdmOtherProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadAdmOtherProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadAdmOtherProfiles
	 *            List<CaseloadAdmOtherProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer caseloadAdmOtherProfilesDeleteCaseloadAdmOtherProfiles(
			final List<CaseloadAdmOtherProfiles> lstCaseloadAdmOtherProfiles) {
		final String sql = getQuery("OIMADMIS_CASELOADADMOTHERPROFILES_DELETE_CASELOAD_ADM_OTHER_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAdmOtherProfiles caseloadAdmOtherProfiles : lstCaseloadAdmOtherProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadAdmOtherProfiles));
		}
		try {
			String tableName = "CASELOAD_ADM_OTHER_PROFILES";
			String whereClause = "CASELOAD_ID = :caseloadId AND ROW_ID=:rowId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseloadAdmOtherProfilesDeleteCaseloadAdmOtherProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstCaseloadAdmOtherProfiles.size() == returnArray.length) {
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
	public List<SystemMessages> rgSystemMsgRecordGroup() {
		final String sql = getQuery("OIMADMIS_FIND_RGSYSTEMMSG");
		final RowMapper<SystemMessages> mRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemMessages.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SystemMessages> rgOtherSystemMsgRecordGroup() {
		final String sql = getQuery("OIMADMIS_FIND_RGOTHERSYSTEMMSG");
		final RowMapper<SystemMessages> mRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemMessages.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIMADMIS_FIND_RGAGENCYLOCATIONS");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> rgLivingUnitsRecordGroup() {
		final String sql = getQuery("OIMADMIS_FIND_RGLIVINGUNITS");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAlertRecordGroup() {
		final String sql = getQuery("OIMADMIS_FIND_RGALERT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> rgAlertCodeRecordGroup(final String alerType) {
		final String sql = getQuery("OIMADMIS_FIND_RGALERTCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("ALERTTYPE", alerType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * caseloadAdmAlertProfilesPostQueryPOST-QUERY
	 *
	 * @param params
	 *
	 */
	public List<SystemMessages> caseloadAdmAlertProfilesPostQueryPostQuery(final SystemMessages paramBean) {
		final String sql = getQuery("OIMADMIS_CASELOAD_ADM_ALERT_PROFILES_POSTQUERY_POSTQUERY");
		final RowMapper<SystemMessages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemMessages.class,
				beanMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * caseloadAdmOtherProfilesPostQuery
	 *
	 * @param params
	 *
	 */
	public List<SystemMessages> caseloadAdmOtherProfilesPostQuery(final SystemMessages paramBean) {
		final String sql = getQuery("OIMADMIS_CASELOAD_ADM_OTHER_PROFILES_POSTQUERY");
		final RowMapper<SystemMessages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemMessages.class,
				beanMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIMADMIS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, beanMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture Email Sender from Procedure
	 * 
	 * @return String
	 */
	@Override
	public String getLivingUnitDescp(final CaseloadAdmOtherProfiles vCbSentTerms) {
		String code = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_LIV_UNIT_ID", OracleTypes.NUMBER),
				new SqlOutParameter("v_Return", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UTILS").withFunctionName("GET_LIVING_UNIT_DESCP")
				.declareParameters(sqlParameters);
		inParamMap.put("P_LIV_UNIT_ID", vCbSentTerms.getLivingUnitId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.get("v_Return") != null) {
				code = (String) returnObject.get("v_Return");
			}
		} catch (final Exception e) {
			logger.error("getLivingUnitDescp :", e);
			return null;
		}
		return code;
	}

	@Override
	public Integer getAvalibleBedInTheLocation(Integer livingUnitId) {
		String sql = getQuery("OIMADMIS_AVALIBLE_BEDS_IN_THE_LOCATION");
		Integer avalibleBeds = null;
		try {
			avalibleBeds = namedParameterJdbcTemplate.queryForObject(sql, createParams("livingUnitId", livingUnitId),
					Integer.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return avalibleBeds;
	}

}
