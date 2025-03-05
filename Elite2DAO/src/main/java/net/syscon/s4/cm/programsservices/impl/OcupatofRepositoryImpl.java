package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
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
import net.syscon.s4.cm.programsservices.OcupatofRepository;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import oracle.jdbc.OracleTypes;

@Repository
public class OcupatofRepositoryImpl extends RepositoryBase implements OcupatofRepository {

	private static Logger logger = (org.apache.logging.log4j.core.Logger) LogManager
			.getLogger(OcupatofRepositoryImpl.class.getName());

	/**
	 * Creates new OcupatofRepositoryImpl class Object
	 */
	public OcupatofRepositoryImpl() {
	}

	private final Map<String, FieldMapper> eventMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("START_TIME", new FieldMapper("inTime")).put("END_TIME", new FieldMapper("outTime"))
			.put("SESSION_NO", new FieldMapper("sessionNo")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	/**
	 * Fetch the records from database table
	 *
	 */
	public List<OffenderCourseAttendance> offCrsAttExecuteQuery(final OffenderCourseAttendance objSearchDao) {
		final String sql = getQuery("OCUPATOF_OFFCRSATT_FIND_OFFENDER_COURSE_ATTENDANCES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao.getModuleFlag().equals("false")) {
			if (objSearchDao.getViewCode().equals("PAST_PRESENT")) {
				sqlQuery.append(" WHERE ");
				sqlQuery.append(" OFF_PRGREF_ID =:OFF_PRGREF_ID ");
				params.addValue("OFF_PRGREF_ID", objSearchDao.getOffPrgrefId());
				sqlQuery.append(
						" AND date_trunc('hour',EVENT_DATE) <= date_trunc('hour',current_date ) ORDER BY EVENT_DATE DESC ");
			} else {
				sqlQuery.append(" WHERE ");
				sqlQuery.append(" OFF_PRGREF_ID =:OFF_PRGREF_ID ");
				params.addValue("OFF_PRGREF_ID", objSearchDao.getOffPrgrefId());
				sqlQuery.append(
						" AND date_trunc('hour',EVENT_DATE) > date_trunc('hour',current_date ) ORDER BY EVENT_DATE ASC ");
			}
		} else {
			if (objSearchDao.getViewCode().equals("PAST_PRESENT")) {
				sqlQuery.append(" WHERE ");
				sqlQuery.append(" OFF_PRGREF_ID IN (SELECT OPP.OFF_PRGREF_ID FROM OFFENDER_PROGRAM_PROFILES OPP ");
				sqlQuery.append(" WHERE ");
				sqlQuery.append(" OPP.PARENT_OFF_PRGREF_ID = :OFF_PRGREF_ID ) ");
				params.addValue("OFF_PRGREF_ID", objSearchDao.getOffPrgrefId());
				sqlQuery.append(
						" AND date_trunc('hour',EVENT_DATE) <= date_trunc('hour',current_date ) ORDER BY EVENT_DATE DESC ");
			} else {
				sqlQuery.append(" WHERE ");
				sqlQuery.append(" OFF_PRGREF_ID IN ( SELECT OPP.OFF_PRGREF_ID FROM  OFFENDER_PROGRAM_PROFILES OPP ");
				sqlQuery.append(" WHERE ");
				sqlQuery.append(
						" OPP.PARENT_OFF_PRGREF_ID = :OFF_PRGREF_ID ) AND date_trunc('hour',EVENT_DATE) > date_trunc('hour',current_date ) ORDER BY EVENT_DATE ASC ");
				params.addValue("OFF_PRGREF_ID", objSearchDao.getOffPrgrefId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<OffenderCourseAttendance> OffCouRowMapp = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderCourseAttendance.class, eventMapping);
		final List<OffenderCourseAttendance> returnList = namedParameterJdbcTemplate.query(preparedSql, params,
				OffCouRowMapp);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 */

	public Integer offCrsAttUpdateOffenderCourseAttendances(final List<OffenderCourseAttendance> OffAttendances) {
		int insertCount = 0;
		final String sql = getQuery("OCUPATOF_OFFCRSATT_UPDATE_OFFENDER_COURSE_ATTENDANCES");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCourseAttendance offenderCourseAttendances : OffAttendances) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseAttendances));
		}
		if (OffAttendances.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 */
	public List<ReferenceCodes> rgAttendancesRecordGroup() {
		final String sql = getQuery("OCUPATOF_FIND_RGATTENDANCES");
		final RowMapper<ReferenceCodes> MEventMeasuresRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), MEventMeasuresRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 */
	public List<ReferenceCodes> rgAttendancyViewRecordGroup() {
		final String sql = getQuery("OCUPATOF_FIND_RGATTENDANCYVIEW");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 */
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		final String sql = getQuery("OCUPATOF_FIND_RGENGAGEMENT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		final String sql = getQuery("OCUPATOF_FIND_RGUNDERSTANDING");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public int updateOffCrseAttend(final OffenderCourseAttendance searchBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_event_id", Types.INTEGER),
				new SqlParameter("p_event_outcome", Types.VARCHAR), new SqlParameter("p_in_time", Types.TIME),
				new SqlParameter("p_out_time", Types.TIME), new SqlParameter("p_engagement_code", Types.VARCHAR),
				new SqlParameter("p_understanding_code", Types.VARCHAR),
				new SqlParameter("p_comment_text", Types.VARCHAR), };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCUPATOF").withProcedureName("UPDATE_OFF_CRSE_ATTEND")
				.declareParameters(sqlParameters);

		inParamMap.put("p_event_id", searchBean.getEventId());
		inParamMap.put("p_event_outcome", searchBean.getEventOutcome());
		inParamMap.put("p_in_time", searchBean.getInTime());
		inParamMap.put("p_out_time", searchBean.getOutTime());
		inParamMap.put("p_engagement_code", searchBean.getEngagementCode());
		inParamMap.put("p_understanding_code", searchBean.getUnderstandingCode());
		inParamMap.put("p_comment_text", searchBean.getCommentText());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			return 1;
		} catch (final Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public OffenderCourseAttendance getOffenderCourseDetails(final OffenderCourseAttendance searchBean) {
		final OffenderCourseAttendance bean = new OffenderCourseAttendance();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_crs_acty_id", Types.INTEGER),
				new SqlParameter("p_crs_sch_id", Types.INTEGER), new SqlParameter("p_event_id", Types.INTEGER),
				new SqlParameter("p_event_outcome", Types.VARCHAR),
				new SqlParameter("p_engagement_code", Types.VARCHAR),
				new SqlParameter("p_understanding_code", Types.VARCHAR),

				new SqlOutParameter("p_module", OracleTypes.VARCHAR),
				new SqlOutParameter("p_catch_up", OracleTypes.VARCHAR),
				new SqlOutParameter("p_confirm_attend_desc", OracleTypes.VARCHAR),
				new SqlOutParameter("p_engagement_desc", OracleTypes.VARCHAR),
				new SqlOutParameter("p_understanding_desc", OracleTypes.VARCHAR),
				new SqlOutParameter("p_check_sum", OracleTypes.NUMBER) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCUPATOF").withProcedureName("GET_OFFENDER_COURSE_DETAILS")
				.declareParameters(sqlParameters);

		inParamMap.put("p_crs_acty_id", searchBean.getCrsActyId());
		inParamMap.put("p_crs_sch_id", searchBean.getCrsSchId());
		inParamMap.put("p_event_id", searchBean.getEventId());
		inParamMap.put("p_event_outcome", searchBean.getEventOutcome());
		inParamMap.put("p_engagement_code", searchBean.getEngagementCode());
		inParamMap.put("p_understanding_code", searchBean.getUnderstandingCode());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			bean.setModule(returnValObj.get("p_module") != null ? String.valueOf(returnValObj.get("p_module")) : null);
			bean.setCatchUpFlag(
					returnValObj.get("p_catch_up") != null ? String.valueOf(returnValObj.get("p_catch_up")) : null);
			bean.setConfirmAttendance(returnValObj.get("p_confirm_attend_desc") != null
					? String.valueOf(returnValObj.get("p_confirm_attend_desc"))
					: null);
		} catch (final Exception e) {
			logger.error(e);
		}
		return bean;
	}

	@Override
	public String getModule(OffenderCourseAttendance searchBean) {
		final String sql = getQuery("OCUPATOF_OFF_CRS_ATT_POSTQUERY");
		String module = null;
		try {
			module = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CRS_ACTY_ID", searchBean.getCrsActyId()), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return module;
	}

	@Override
	public String getModuleDescription(BigDecimal crsActyId) {
		final String sql = getQuery("OCUPATOF_OFF_CRS_MODULE_DESCRIPTION");
		String module = null;
		try {
			module = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_crs_acty_id", crsActyId),
					String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return module;
	}

	@Override
	public String getCatchupFlag(BigDecimal crsSchId) {
		final String sql = getQuery("OCUPATOF_OFF_CRS_CATCH_UP_FLAG_DATA");
		String module = null;
		try {
			module = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_SCH_ID", crsSchId),
					String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return module;
	}

	public Integer updateVOffCrseEvents(final OffenderCourseAttendance searchBean) {
		final String sql = getQuery("OCUPATOF_UPDATE_V_OFF_CRSE_EVENTS");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(searchBean));
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("UpdateVOffCrseEvents : ", e);
			return 0;
		}

	}
}
