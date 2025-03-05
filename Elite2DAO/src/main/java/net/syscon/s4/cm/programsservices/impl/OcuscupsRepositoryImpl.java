package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcuscupsRepository;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.iwp.impl.OcdtapowRepositoryImpl;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuscupsRepositoryImpl
 * 
 */
@Repository
public class OcuscupsRepositoryImpl extends RepositoryBase implements OcuscupsRepository {

	private static final String PSCHEDULEDATE = "p_schedule_date";
	private static final String PSTARTTIME = "p_start_time";
	private static final String PENDTIME = "p_end_time";
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdtapowRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> courseAttendMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("START_TIME", new FieldMapper("startTime"))
			.put("END_TIME", new FieldMapper("endTime"))
			.put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("HIDDEN_COMMENT_TEXT", new FieldMapper("hiddenCommentText"))
			.put("TO_INTERNAL_LOCATION_ID", new FieldMapper("toInternalLocationId"))
			.put("CRS_SCH_ID", new FieldMapper("crsSchId"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
			.put("ENGAGEMENT_CODE", new FieldMapper("engagementCode"))
			.put("UNDERSTANDING_CODE", new FieldMapper("understandingCode"))
			.put("DETAILS", new FieldMapper("details"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome"))
			.put("OFF_CRS_SCH_REF_ID", new FieldMapper("offCrsSchRefId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("PROGRAM_ID", new FieldMapper("programId")).build();

	private final Map<String, FieldMapper> offenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay")).build();

	/**
	 * Creates new OcuscupsRepositoryImpl class Object
	 */
	public OcuscupsRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderCourseAttendances
	 *
	 * @return List<OffenderCourseAttendances>
	 *
	 */
	public List<OffenderCourseAttendance> offCrsAttendExecuteQuery(final OffenderCourseAttendance objSearchDao) {
		final String sql = getQuery("OCUSCUPS_OFFCRSATTEND_FIND_OFFENDER_COURSE_ATTENDANCES_NEW");
		final RowMapper<OffenderCourseAttendance> OffCoAtRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendance.class, courseAttendMap);
		return namedParameterJdbcTemplate.query(sql,
				createParams("crs_sch_id",objSearchDao.getCrsSchId() ),
				OffCoAtRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCourseAttendances
	 *            List<OffenderCourseAttendances>
	 *
	 */
	public Integer offCrsAttendUpdateOffenderCourseAttendances(final List<OffenderCourseAttendance> lst) {
		final int insertCount = 0;
		final String sql = getQuery("OCUSCUPS_OFFCRSATTEND_UPDATE_OFFENDER_COURSE_ATTENDANCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCourseAttendance offCourseAtt : lst) {
			parameters.add(new BeanPropertySqlParameterSource(offCourseAtt));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lst.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Offenders getOffenderDetails(final OffenderCourseAttendance offCourseAtte) {
		final String sql = getQuery("OCUSCUPS_GET_OFFENDER_DETAILS");
		final RowMapper<Offenders> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offenderMapping);
		Offenders offenders = new Offenders();
		try {
			offenders = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offBookId", offCourseAtte.getOffenderBookId()), OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}

		return offenders;
	}

	@Override
	public BigDecimal insertCourseScheduleCatchUp(final OffenderCourseAttendance courseSchedules) {
		BigDecimal crschId = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put(PSCHEDULEDATE, courseSchedules.getScheduleDate());
		inParamMap.put(PSTARTTIME, courseSchedules.getStartTime());
		inParamMap.put(PENDTIME, courseSchedules.getEndTime());
		inParamMap.put("p_catch_up_crs_sch_id", courseSchedules.getCatchUpCrsSchId());
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_schedule_date", OracleTypes.DATE),
				new SqlParameter("p_start_time", OracleTypes.DATE),
				new SqlParameter("p_catch_up_crs_sch_id", OracleTypes.NUMERIC),
				new SqlParameter("p_end_time", OracleTypes.DATE) };

		try {
			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("tag_programmes").withFunctionName("ins_course_schedules_catchup")
					.declareParameters(sqlParameters);
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			crschId = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);

		} catch (final Exception e) {
			logger.error("insertCourseScheduleCatchUp", e);
		}

		return crschId;
	}

	@Override
	public int insOffCrsAttCatchup(final OffenderCourseAttendance courseSchedules) {
		final Map<String, Object> inParamMap = new HashMap<>();
		Map<String, Object> returnObject = new HashMap<>();
		Integer val = 0;
		inParamMap.put("p_old_crs_sch_id",courseSchedules.getCrsSchId());
		inParamMap.put("p_new_crs_sch_id", courseSchedules.getCrsSchId());
		inParamMap.put(PSCHEDULEDATE, courseSchedules.getScheduleDate());
		inParamMap.put(PSTARTTIME, courseSchedules.getStartTime());
		inParamMap.put(PENDTIME, courseSchedules.getEndTime());
		inParamMap.put("p_offender_book_id", courseSchedules.getOffenderBookId());
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_old_crs_sch_id", OracleTypes.NUMERIC),
				new SqlParameter("p_new_crs_sch_id", OracleTypes.NUMERIC),
				new SqlParameter("p_schedule_date", OracleTypes.DATE),
				new SqlParameter("p_start_time", OracleTypes.DATE), new SqlParameter("p_end_time", OracleTypes.DATE),
				new SqlParameter("p_offender_book_id", OracleTypes.NUMERIC) };
		try {
			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("tag_programmes").withProcedureName("ins_off_crs_att_catchup")
					.declareParameters(sqlParameters);
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			returnObject = simpleJDBCCall.execute(inParameter);
			val = 1;
		} catch (final Exception e) {
			logger.error("insOffCrsAttCatchup", e);
		}

		return val;

	}

}
