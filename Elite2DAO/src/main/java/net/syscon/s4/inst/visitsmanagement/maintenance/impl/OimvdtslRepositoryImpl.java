package net.syscon.s4.inst.visitsmanagement.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitDays;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.im.beans.AgencyVisitTimes;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvdtslRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OimvdtslRepositoryImpl
 */
@Repository
public class OimvdtslRepositoryImpl extends RepositoryBase implements OimvdtslRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimvdtslRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> agencyVisitTimesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("WEEK_DAY", new FieldMapper("weekDay"))
			.put("TIME_SLOT_SEQ", new FieldMapper("timeSlotSeq")).put("START_TIME", new FieldMapper("startTime"))
			.put("END_TIME", new FieldMapper("endTime")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.build();
	private final Map<String, FieldMapper> agencyVisitSlotsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_VISIT_SLOT_ID", new FieldMapper("agencyVisitSlotId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("MAX_ADULTS", new FieldMapper("maxAdults"))
			.put("MAX_GROUPS", new FieldMapper("maxGroups")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("WEEK_DAY", new FieldMapper("weekDay"))
			.put("TIME_SLOT_SEQ", new FieldMapper("timeSlotSeq"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))

			.build();
	private final Map<String, FieldMapper> agencyVisitDaysMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("WEEK_DAY", new FieldMapper("weekDay"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("code", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> linkMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Creates new OimvdtslRepositoryImpl class Object
	 */
	public OimvdtslRepositoryImpl() {
		// OimvdtslRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyVisitDays
	 *
	 * @return List<AgencyVisitDays>
	 *
	 * @throws SQLException
	 */

	public List<AgencyVisitDays> agyVisitDaysExecuteQuery(final AgencyVisitDays objSearchDao) {
		final String sql = getQuery("OIMVDTSL_AGYVISITDAYS_FIND_AGENCY_VISIT_DAYS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID  = :agyLocId " + " AND ");
				params.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql.concat(
				" order by case when WEEK_DAY = 'MON' then '1' when WEEK_DAY = 'TUE' then '2' when WEEK_DAY = 'WED' then '3' when WEEK_DAY = 'THU' then '4' when WEEK_DAY = 'FRI' then '5' when WEEK_DAY = 'SAT' then '6' when WEEK_DAY = 'SUN' then '7' end");

		final RowMapper<AgencyVisitDays> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyVisitDays.class, agencyVisitDaysMapping);
		List<AgencyVisitDays> returnList = new ArrayList<AgencyVisitDays>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, alertOffenderRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyVisitDays List<AgencyVisitDays>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agyvisitdaysInsertAgencyVisitDays(final List<AgencyVisitDays> lstAgencyVisitDays) {
		final String sql = getQuery("OIMVDTSL_AGYVISITDAYS_INSERT_AGENCY_VISIT_DAYS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitDays list : lstAgencyVisitDays) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyVisitDays.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyVisitDays List<AgencyVisitDays>
	 *
	 * @throws SQLException
	 */
	public Integer agyVisitDaysUpdateAgencyVisitDays(final List<AgencyVisitDays> lstAgencyVisitDays) {
		final String sql = getQuery("OIMVDTSL_AGYVISITDAYS_UPDATE_AGENCY_VISIT_DAYS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitDays agencyVisitDays : lstAgencyVisitDays) {
			parameters.add(new BeanPropertySqlParameterSource(agencyVisitDays));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyVisitDays.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyVisitDays List<AgencyVisitDays>
	 *
	 * @throws SQLException
	 */
	public Integer agyVisitDaysDeleteAgencyVisitDays(final List<AgencyVisitDays> lstAgencyVisitDays) {

		final String sql = getQuery("OIMVDTSL_AGYVISITDAYS_DELETE_AGENCY_VISIT_DAYS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitDays agencyVisitDays : lstAgencyVisitDays) {
			parameters.add(new BeanPropertySqlParameterSource(agencyVisitDays));
		}
		try {
			String tableName = "AGENCY_VISIT_DAYS";
			String whereCondition = "agy_loc_id =:agyLocId and week_day =:weekDay";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyVisitDays.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyVisitTimes
	 *
	 * @return List<AgencyVisitTimes>
	 *
	 * @throws SQLException
	 */
	public List<AgencyVisitTimes> agyVisitTimesExecuteQuery(final AgencyVisitTimes objSearchDao) {
		final String sql = getQuery("OIMVDTSL_AGYVISITTIMES_FIND_AGENCY_VISIT_TIMES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID  = :agyLocId " + " AND ");
				params.addValue("agyLocId", objSearchDao.getAgyLocId());
			}

			if (objSearchDao.getWeekDay() != null) {
				sqlQuery.append(" WEEK_DAY  = :weekDay " + " AND ");
				params.addValue("weekDay", objSearchDao.getWeekDay());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql.concat(" order by ACTIVE_FLAG desc, to_char(start_time,'HH24:MI') asc ");

		final RowMapper<AgencyVisitTimes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyVisitTimes.class, agencyVisitTimesMapping);
		List<AgencyVisitTimes> returnList = new ArrayList<AgencyVisitTimes>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, alertOffenderRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyVisitTimes List<AgencyVisitTimes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agyvisittimesInsertAgencyVisitTimes(final List<AgencyVisitTimes> lstAgencyVisitTimes) {
		final String sql = getQuery("OIMVDTSL_AGYVISITTIMES_INSERT_AGENCY_VISIT_TIMES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitTimes list : lstAgencyVisitTimes) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyVisitTimes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyVisitTimes List<AgencyVisitTimes>
	 *
	 * @throws SQLException
	 */
	public AgencyVisitTimes agyVisitTimesUpdateAgencyVisitTimes(final List<AgencyVisitTimes> lstAgencyVisitTimes) {
		final String sql = getQuery("OIMVDTSL_AGYVISITTIMES_UPDATE_AGENCY_VISIT_TIMES");
		final AgencyVisitTimes returnData = new AgencyVisitTimes();
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitTimes agencyVisitTimes : lstAgencyVisitTimes) {
			parameters.add(new BeanPropertySqlParameterSource(agencyVisitTimes));
		}
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				if(error.indexOf("\" on")!=-1) {
					error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
							.replaceFirst("constraint", "").trim();
				}
				else {
					error = error.substring(error.indexOf("constraint"), error.indexOf("Detail"))
							.replaceFirst("constraint", "").trim();
				}	
				final String tableName = errorNameValidationTimes(error.substring(1, error.length()));
				returnData.setSealFlag(tableName);
				returnData.setServerCode(2292);
				returnData.setErrorMessage(error.toUpperCase());
				logger.error("agyVisitTimesUpdateAgencyVisitTimes", e);
				return returnData;
			}
		}
		if (lstAgencyVisitTimes.size() == returnArray.length) {
			returnData.setReturnValue(BigDecimal.valueOf(1));
			return returnData;
		} else {
			returnData.setReturnValue(BigDecimal.valueOf(0));
			return returnData;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyVisitTimes List<AgencyVisitTimes>
	 *
	 * @throws SQLException
	 */
	public AgencyVisitTimes agyVisitTimesDeleteAgencyVisitTimes(final List<AgencyVisitTimes> lstAgencyVisitTimes) {
		final String sql = getQuery("OIMVDTSL_AGYVISITTIMES_DELETE_AGENCY_VISIT_TIMES");
		int[] returnArray = new int[] {};
		final AgencyVisitTimes returnData = new AgencyVisitTimes();
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitTimes agencyVisitTimes : lstAgencyVisitTimes) {
			parameters.add(new BeanPropertySqlParameterSource(agencyVisitTimes));
		}
		try {
			String tableName = "AGENCY_VISIT_TIMES";
			String whereCondition = "agy_loc_id =:agyLocId and week_day =:weekDay and time_slot_seq =:timeSlotSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				//				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				//				final String tableName = errorNameValidationTimes(error);
				error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				final String tableName = errorNameValidationTimes(error.substring(1, error.length()));

				returnData.setSealFlag(tableName);
				returnData.setServerCode(2292);
				logger.error("agyVisitTimesDeleteAgencyVisitTimes", e);
				return returnData;
			}
		}
		if (lstAgencyVisitTimes.size() == returnArray.length) {
			returnData.setReturnValue(BigDecimal.valueOf(1));
			return returnData;
		} else {
			returnData.setReturnValue(BigDecimal.valueOf(0));
			return returnData;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyVisitSlots
	 *
	 * @return List<AgencyVisitSlots>
	 *
	 * @throws SQLException
	 */

	public List<AgencyVisitSlots> agyVisitSlotsExecuteQuery(final AgencyVisitSlots objSearchDao) {
		final String sql = getQuery("OIMVDTSL_AGYVISITSLOTS_FIND_AGENCY_VISIT_SLOTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID  = :agyLocId " + " AND ");
				params.addValue("agyLocId", objSearchDao.getAgyLocId());
			}

			if (objSearchDao.getWeekDay() != null) {
				sqlQuery.append(" WEEK_DAY  = :weekDay " + " AND ");
				params.addValue("weekDay", objSearchDao.getWeekDay());
			}
			if (objSearchDao.getTimeSlotSeq() != null) {
				sqlQuery.append(" TIME_SLOT_SEQ  = :timeSlotSeq " + " AND ");
				params.addValue("timeSlotSeq", objSearchDao.getTimeSlotSeq());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<AgencyVisitSlots> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyVisitSlots.class, agencyVisitSlotsMapping);
		List<AgencyVisitSlots> returnList = new ArrayList<AgencyVisitSlots>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, alertOffenderRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyVisitSlots List<AgencyVisitSlots>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agyvisitslotsInsertAgencyVisitSlots(final List<AgencyVisitSlots> lstAgencyVisitSlots) {
		final String sql = getQuery("OIMVDTSL_AGYVISITSLOTS_INSERT_AGENCY_VISIT_SLOTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitSlots agencyVisitSlots : lstAgencyVisitSlots) {
			parameters.add(new BeanPropertySqlParameterSource(agencyVisitSlots));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyVisitSlots.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyVisitSlots List<AgencyVisitSlots>
	 *
	 * @throws SQLException
	 */
	public Integer agyVisitSlotsUpdateAgencyVisitSlots(final List<AgencyVisitSlots> lstAgencyVisitSlots) {
		final String sql = getQuery("OIMVDTSL_AGYVISITSLOTS_UPDATE_AGENCY_VISIT_SLOTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitSlots agencyVisitSlots : lstAgencyVisitSlots) {
			parameters.add(new BeanPropertySqlParameterSource(agencyVisitSlots));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyVisitSlots.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyVisitSlots List<AgencyVisitSlots>
	 *
	 * @throws SQLException
	 */
	public AgencyVisitSlots agyVisitSlotsDeleteAgencyVisitSlots(final List<AgencyVisitSlots> lstAgencyVisitSlots) {
		final String sql = getQuery("OIMVDTSL_AGYVISITSLOTS_DELETE_AGENCY_VISIT_SLOTS");
		int[] returnArray = new int[] {};
		final AgencyVisitSlots returnData = new AgencyVisitSlots();
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyVisitSlots agencyVisitSlots : lstAgencyVisitSlots) {
			parameters.add(new BeanPropertySqlParameterSource(agencyVisitSlots));
		}
		try {
			String tableName = "AGENCY_VISIT_SLOTS";
			String whereCondition = "AGENCY_VISIT_SLOT_ID  = :agencyVisitSlotId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("violates foreign key constraint")) {
				final String tableName = error.substring(error.lastIndexOf("is still referenced from table \""), error.lastIndexOf("\".")).replaceFirst("is still referenced from table \"", "");
				//final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName.toUpperCase());
				returnData.setServerCode(2292);
				logger.error("agyVisitSlotsDeleteAgencyVisitSlots", e);
				return returnData;
			}
		}
		if (lstAgencyVisitSlots.size() == returnArray.length) {
			returnData.setReturnValue(BigDecimal.valueOf(1));
			return returnData;
		} else {
			returnData.setReturnValue(BigDecimal.valueOf(0));
			return returnData;
		}

	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OIMVDTSL_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			logger.error("errorNameValidation", e);
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	public String errorNameValidationTimes(final String errorName) {
		final String sql = getQuery("OIMVDTSL_CONSTRAINT_VALIDATIONS_TIMES");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName.toUpperCase()),
					String.class);
		} catch (Exception e) {
			logger.error("errorNameValidationTimes", e);
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAgyVisitDaysRecordGroup() {
		final String sql = getQuery("OIMVDTSL_FIND_RGAGYVISITDAYS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAgyVisitDaysRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyIntLocRecordGroup(final String userId) {
		final String sql = getQuery("OIMVDTSL_FIND_RGAGYINTLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				linkMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("userId", userId), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAgyIntLocRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsages>
	 */
	public List<AgencyInternalLocations> rgAgyVisitSlotsRecordGroup(final String agyLocId, final String caseLoadId) {
		final String sql = getQuery("OIMVDTSL_FIND_RGAGYVISITSLOTS");
		final RowMapper<AgencyInternalLocations> mMInternalLocationUsagesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, linkMapping);// agencyVisitSlotsMapping
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId, "CASELOAD_ID",caseLoadId),
					mMInternalLocationUsagesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception:rgAgyVisitSlotsRecordGroup", e);
			return Collections.emptyList();
		}
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyVisitDays> rgWeekdayRecordGroup() {
		final String sql = getQuery("OIMVDTSL_FIND_RGWEEKDAY");
		final RowMapper<AgencyVisitDays> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyVisitDays.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception:rgWeekdayRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agyVisitDaysOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */

	public Integer agyVisitDaysOnCheckDeleteMaster(final AgencyVisitTimes paramBean) {
		final String sql = getQuery("OIMVDTSL_AGY_VISIT_DAYS_ONCHECKDELETEMASTER");
		final Integer returnList = (Integer) namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AGYLOCID", paramBean.getAgyLocId(), "WEEKDAY", paramBean.getWeekDay()), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agyVisitTimesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public Integer agyVisitTimesOnCheckDeleteMaster(final AgencyVisitSlots paramBean) {
		final String sql = getQuery("OIMVDTSL_AGY_VISIT_TIMES_ONCHECKDELETEMASTER");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("AGYLOCID", paramBean.getAgyLocId(),
				"WEEKDAY", paramBean.getWeekDay(), "TIMESLOTSEQ", paramBean.getTimeSlotSeq()), Integer.class);
		return returnValue;
	}

	/**
	 * getting capacity value from this method
	 */
	public AgencyVisitSlots getCapacityValue(final AgencyVisitSlots objSearchDao) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR),
				new SqlParameter("P_INT_LOC_ID", Types.VARCHAR), new SqlInOutParameter("P_CAPACITY", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("tag_visits").withProcedureName("get_capacity").declareParameters(sqlParameters);
		inParamMap.put("P_INT_LOC_ID", objSearchDao.getInternalLocationId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			objSearchDao.setpCapacity((String) returnObject.get("P_CAPACITY"));

		} catch (Exception e) {
			logger.error("Exception:getCapacityValue", e);
		}
		return objSearchDao;
	}

	/**
	 * to get the location from this method
	 */
	public AgencyVisitSlots getLocationDescription(final AgencyVisitSlots objSearchDao) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR),
				new SqlParameter("P_INT_LOC_ID", Types.VARCHAR), new SqlInOutParameter("P_DESC", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("tag_visits").withProcedureName("get_location_desc").declareParameters(sqlParameters);
		inParamMap.put("P_INT_LOC_ID", objSearchDao.getInternalLocationId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			objSearchDao.setNbtLocationDesc((String) returnObject.get("P_DESC"));
		} catch (Exception e) {
			logger.error("Exceptiong: getLocationDescription", e);
		}
		return objSearchDao;
	}

	/**
	 * to check the timeslot seqs to insert in visit slots for seq id
	 */
	public String getCheckTimeSlots(final AgencyVisitSlots objSearchDao) {
		Map<String, Object> returnObject = null;
		String timeSlot = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_INT_LOC_ID", Types.NUMERIC),
				new SqlParameter("P_WEEK_DAY", Types.VARCHAR), new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR),
				new SqlParameter("P_START_TIME", Types.VARCHAR), new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("CHECK_TIMESLOT").declareParameters(sqlParameters);
		inParamMap.put("P_INT_LOC_ID", objSearchDao.getInternalLocationId());
		inParamMap.put("P_WEEK_DAY", objSearchDao.getWeekDay());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		final String startDate = new SimpleDateFormat("HH:mm").format(objSearchDao.getStartTime());
		inParamMap.put("P_START_TIME", startDate);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			timeSlot = (String) returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			logger.error("getCheckTimeSlots" + e);
			timeSlot = null;
		}
		return timeSlot;
	}

	/**
	 * to get the getNextAgencyVisitSlotId to insert in visit slot table
	 */
	@Override
	public Integer getNextAgencyVisitSlotId() {
		Integer visitSlotId = 0;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_OPEN_AN_ACCOUNT", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("tag_visits").withFunctionName("get_next_agy_visit_slot_id")
				.declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			visitSlotId = Integer.valueOf(String.valueOf(returnObject.get("P_OPEN_AN_ACCOUNT")));
		} catch (Exception e) {
			logger.error("getNextAgencyVisitSlotId" + e);
		}
		return visitSlotId;
	}

	/**
	 * to validate the check box in times grid
	 */
	@Override
	public String agyVisitTimescheckboxChange(final AgencyVisitTimes searchBean) {
		final String sql = getQuery("OIMVDTSL_AGY_VISIT_TIMES_CHECKBOXCHANGE");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AGYLOCID", searchBean.getAgyLocId(), "WEEKDAY", searchBean.getWeekDay(),
							"TIMESLOTSEQ", searchBean.getTimeSlotSeq()),
					String.class);
		} catch (Exception e) {
			returnValue = "N";
			return returnValue;
		}
		return returnValue;
	}
}
