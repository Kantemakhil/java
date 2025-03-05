package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcuoscpvRepository;
import net.syscon.s4.cm.programsservices.OffenderCourseApptGrp;
import net.syscon.s4.cm.programsservices.OffenderCourseApptRule;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import oracle.jdbc.OracleTypes;

@Repository
public class OcuoscpvRepositoryImpl extends RepositoryBase implements OcuoscpvRepository {



	private static Logger logger = LogManager.getLogger(OcuoscpvRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> offenderProgramProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.build();
	private final Map<String, FieldMapper> courseScheduleRulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TO_CHAR(END_TIME", new FieldMapper("toChar(endTime"))
			.put("MONDAY_FLAG", new FieldMapper("mondayFlag"))
			.put("SATURDAY_FLAG", new FieldMapper("saturdayFlag"))
			.put("SCHEDULE_START_DATE", new FieldMapper("scheduleStartDate"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", new FieldMapper("corporateName "))
			.put("P_CRS_ACTY_ID", new FieldMapper("pCrsActyId"))
			.build();

	public List<CourseActivities> crsActExecuteQuery(final CourseActivities objSearchDao) {
		final String sql = getQuery("OCUOSCPV_CRSACT_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("pcrsactyid", objSearchDao.getCrsActyId()),
				CourseRowMapper);

	}

	@Override
	public List<CourseActivities> gettingProgramServicePostQuerry(final CourseActivities courseActivities) {
		final String sql = getQuery("OCUOSCPV_CRSACT_POST_QUERRY");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("programid", courseActivities.getProgramId()),
				rowMapper);
	}

	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules objSearchDao) {
		final String sql = getQuery("OCUOSCPV_CRSSCHEDULERUL_FIND_COURSE_SCHEDULE_RULES");
		final RowMapper<CourseScheduleRules> courseRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseScheduleRules.class, courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("pcrsactyid", objSearchDao.getCrsActyId()),
				courseRowMapper);

	}

	public List<VOffenderCourseEvents> offSchExecuteQuery(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_OFFSCH_FIND_V_OFFENDER_COURSE_EVENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		sqlQuery.append(" WHERE EVENT_STATUS <> 'CANC'  AND");
		if (objSearchDao != null && objSearchDao.getOffenderBookId() != null && objSearchDao.getCrsActyId() != null
				&& objSearchDao.getOffPrgrefId() != null) {
			sqlQuery.append(
					" offender_book_id = :p_offender_book_id and crs_acty_id =:p_crs_acty_id and off_prgref_id = :p_off_prgref_id  "
							+ " AND ");
			params.addValue("p_offender_book_id", objSearchDao.getOffenderBookId());
			params.addValue("p_crs_acty_id", objSearchDao.getCrsActyId());
			params.addValue("p_off_prgref_id", objSearchDao.getOffPrgrefId());
		}
		if (objSearchDao != null && "Pending".equals(objSearchDao.getActionCode())) {
			sqlQuery.append(" event_date >=current_date ");
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY event_date asc");
		final RowMapper<VOffenderCourseEvents> vOffenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VOffenderCourseEvents.class, courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.query(preparedSql, params, vOffenderRowMapper);
	}

	public Integer offSchInsertVOffenderCourseEvents(final List<VOffenderCourseEvents> courseEvents) {
		final String sql = getQuery("OCUOSCPV_OFFSCH_INSERT_V_OFFENDER_COURSE_EVENTS");
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (final VOffenderCourseEvents list : courseEvents) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in offSchInsertVOffenderCourseEvents Ocuoscpv:", e);
			return 0;
		}
	}

	public Integer offSchDeleteVOffenderCourseEvents(final List<VOffenderCourseEvents> courseEvents) {
		final String sql = getQuery("OCUOSCPV_OFFSCH_DELETE_V_OFFENDER_COURSE_EVENTS");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final VOffenderCourseEvents vOffenderEvents : courseEvents) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderEvents));
		}
		try {
			String tableName = "V_OFFENDER_COURSE_EVENTS";
			String whereClause = "off_prgref_id = :offPrgrefId and offender_book_id = :offenderBookId and crs_acty_id = :p_crs_acty_id and crs_sch_id = :p_crs_sch_id";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offSchDeleteVOffenderCourseEvents", e);
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in offSchDeleteVOffenderCourseEvents Ocuoscpv:", e);
			return 0;
		}

	}

	public List<OffenderCourseApptGrp> weeklyDefExecuteQuery(final OffenderCourseApptGrp objSearchDao) {
		final String sql = getQuery("OCUOSCPV_WEEKLYDEF_FIND_OFFENDER_COURSE_APPT_GRPS");
		final RowMapper<OffenderCourseApptGrp> apptGrpsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseApptGrp.class, courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_prgref_id", objSearchDao.getOffPrgrefId()),
				apptGrpsRowMapper);

	}

	public Integer weeklyDefInsertOffenderCourseApptGrps(final List<OffenderCourseApptGrp> courseApptGrps) {
		final String sql = getQuery("OCUOSCPV_WEEKLYDEF_INSERT_OFFENDER_COURSE_APPT_GRPS");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCourseApptGrp courseEvents : courseApptGrps) {
			parameters.add(new BeanPropertySqlParameterSource(courseEvents));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in weeklyDefInsertOffenderCourseApptGrps Ocuoscpv:", e);
			return 0;
		}

	}

	public Integer weeklyDefUpdateOffenderCourseApptGrps(final List<OffenderCourseApptGrp> lstOffenderCourseApptGrps) {
		String sql = getQuery("OCUOSCPV_WEEKLYDEF_UPDATE_OFFENDER_COURSE_APPT_GRPS");
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OffenderCourseApptGrp offenderCourseApptGrps : lstOffenderCourseApptGrps) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseApptGrps));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in weeklyDefUpdateOffenderCourseApptGrps Ocuoscpv:", e);
			return 0;
		}

	}

	public List<OffenderCourseApptRule> offSchDefExecuteQuery(final OffenderCourseApptRule objSearchDao) {
		final String sql = getQuery("OCUOSCPV_OFFSCHDEF_FIND_OFFENDER_COURSE_APPT_RULES");
		final RowMapper<OffenderCourseApptRule> courseApptRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseApptRule.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("offender_course_appt_grp_id", objSearchDao.getOffenderCourseApptGrpId()),
				courseApptRowMapper);

	}

	public Integer offschdefInsertOffenderCourseApptRules(final List<OffenderCourseApptRule> courseApptRules) {
		String sql = getQuery("OCUOSCPV_OFFSCHDEF_INSERT_OFFENDER_COURSE_APPT_RULES");

		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OffenderCourseApptRule offenderCourseApptRule : courseApptRules) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseApptRule));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in offschdefInsertOffenderCourseApptRules Ocuoscpv:", e);
			return 0;
		}

	}

	public Integer weeklyDefDeleteOffenderCourseApptGrps(final List<OffenderCourseApptGrp> lstOffenderCourseApptGrps) {

		String sql = getQuery("OCUOSCPV_WEEKLYDEF_DELETE_OFFENDER_COURSE_APPT_GRPS");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCourseApptGrp offenderCourseApptGrps : lstOffenderCourseApptGrps) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseApptGrps));
		}
		try {
			String tableName = "OFFENDER_COURSE_APPT_GRPS";
			String whereClause = "OFFENDER_COURSE_APPT_GRP_ID=:offenderCourseApptGrpId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseloadDedProfDelete", e);
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in weeklyDefDeleteOffenderCourseApptGrps Ocuoscpv:", e);
			return 0;
		}

	}

	@Override
	public String gettingWeekdayOffSch(final Date eventDate) {
		final String sql = getQuery("OCUOSCPV_GET_WEEKDAY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("event_date", eventDate),
				String.class);
	}

	@Override
	public Long gettingCrsApptid(final VOffenderCourseEvents courtEvents) {
		Long returnList = null;
		final String sql = getQuery("OCUOSCPV_GETTING_CRSAPPTID");
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_COURSE_APPT_RULE_ID",
				courtEvents.getOffenderCourseApptGrpId(), "start_date", courtEvents.getStartDate()), Long.class);
		return returnList;

	}

	@Override
	public Long gettingrefferenceId(final VOffenderCourseEvents courtEvents) {
		Long returnList = null;
		final String sql = getQuery("OCUOSCPV_GETTING_REFFERENCE_ID");
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_COURSE_APPT_RULE_ID",
				courtEvents.getOffenderCourseApptGrpId(), "start_date", courtEvents.getStartDate()), Long.class);
		return returnList;
	}

	@Override
	public Integer gettingVReturnNumberForConfict(final VOffenderCourseEvents searchBean) {

		Integer returnList = null;
		final String sql = getQuery("OCUOSCPV_GETTING_VRETURN_NUMBER_FORCONFLICT");
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id",
				searchBean.getOffenderBookId(), "p_event_date", searchBean.getEventDate()), Integer.class);
		return returnList;
	}

	@Override
	public CourseScheduleRules gettingLvDayMonday(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_GETTING_MONDAY_LVDAY");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("crs_acty_id", objSearchDao.getCrsActyId(),
				"course_schedule_rule_id", objSearchDao.getCourseScheduleRuleId()), rowMapper);
	}

	@Override
	public CourseScheduleRules gettingLvDayTuesday(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_GETTING_TUESDAY_LVDAY");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("crs_acty_id", objSearchDao.getCrsActyId(),
				"course_schedule_rule_id", objSearchDao.getCourseScheduleRuleId()), rowMapper);
	}

	@Override
	public CourseScheduleRules gettingLvDayWednesday(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_GETTING_WEDNESDAY_LVDAY");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("crs_acty_id", objSearchDao.getCrsActyId(),
				"course_schedule_rule_id", objSearchDao.getCourseScheduleRuleId()), rowMapper);
	}

	@Override
	public CourseScheduleRules gettingLvDayThursday(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_GETTING_THURSDAY_LVDAY");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("crs_acty_id", objSearchDao.getCrsActyId(),
				"course_schedule_rule_id", objSearchDao.getCourseScheduleRuleId()), rowMapper);
	}

	@Override
	public CourseScheduleRules gettingLvDayFriday(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_GETTING_FRIDAY_LVDAY");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("crs_acty_id", objSearchDao.getCrsActyId(),
				"course_schedule_rule_id", objSearchDao.getCourseScheduleRuleId()), rowMapper);
	}

	@Override
	public CourseScheduleRules gettingLvDaySunday(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_GETTING_SUNDAY_LVDAY");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("crs_acty_id", objSearchDao.getCrsActyId(),
				"course_schedule_rule_id", objSearchDao.getCourseScheduleRuleId()), rowMapper);
	}

	@Override
	public CourseScheduleRules gettingLvDaySaturday(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCUOSCPV_GETTING_SATURDAY_LVDAY");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("crs_acty_id", objSearchDao.getCrsActyId(),
				"course_schedule_rule_id", objSearchDao.getCourseScheduleRuleId()), rowMapper);
	}

	@Override
	public Integer gettingHolidayCountNumber(final VOffenderCourseEvents searchBean) {
		Integer returnVal = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_event_date", Types.DATE),
				new SqlOutParameter("RETURN_VAL", OracleTypes.NUMBER) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UNPAID_WORK").withFunctionName("GET_HOLIDAY_COUNT")
				.declareParameters(sqlParameters);

		inParamMap.put("p_event_date", searchBean.getEventDate());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			returnVal = Integer.valueOf(returnValObj.get("RETURN_VAL").toString());
		} catch (final Exception e) {
			logger.error("Exception in gettingHolidayCountNumber Ocuoscpv:", e);
		}
		return returnVal;
	}

	@Override
	public Date gettingStartDate(final OffenderCourseApptGrp courseAppGrp) {
		Date returnVal = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] {

				new SqlParameter("p_off_prgref_id", Types.INTEGER),
				new SqlOutParameter("RETURN_VAL", OracleTypes.DATE) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UNPAID_WORK").withFunctionName("GET_WEEKLY_DEF_START_DATE")
				.declareParameters(sqlParameters);

		inParamMap.put("p_off_prgref_id", courseAppGrp.getOffPrgrefId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			returnVal = (Date) returnValObj.get("RETURN_VAL");
		} catch (final Exception e) {
			logger.error("Exception in gettingStartDate Ocuoscpv:", e);
		}
		return returnVal;

	}

	@Override
	public Integer OffSchDefDeleteData(final List<OffenderCourseApptRule> courseAppRule) {
		final String sql = getQuery("OCUOSCPV_OFFSCHDEF_DELETE_OFFENDER_COURSE_APPT_RULES");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCourseApptRule courseRule : courseAppRule) {
			parameters.add(new BeanPropertySqlParameterSource(courseRule));
		}
		try {
			String tableName = "offender_course_appt_rules";
			String whereClause = "OFFENDER_COURSE_APPT_RULE_ID=:offenderCourseApptRuleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method OffSchDefDeleteData", e);
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in OffSchDefDeleteData Ocuoscpv:", e);
			return 0;
		}
	}

	@Override
	public Integer OffSchDefUpdateData(final List<OffenderCourseApptRule> courseAppRule) {
		final String sql = getQuery("OCUOSCPV_OFFSCHDEF_UPDATE_OFFENDER_COURSE_APPT_RULES");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCourseApptRule courseRule : courseAppRule) {
			parameters.add(new BeanPropertySqlParameterSource(courseRule));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in OffSchDefUpdate Ocuoscpv:", e);
			return 0;
		}
	}

	@Override
	public Integer weeklyDefPreDeleteQuerry(final OffenderCourseApptGrp couseAppGrp) {
		final String sql = getQuery("OCUOSCPV_WEEKLYDEF_PREDELETE_OFFENDER_COURSE_APPT_GRPS");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(couseAppGrp));
		try {
			String tableName = "offender_course_appt_rules";
			String whereClause = "OFFENDER_COURSE_APPT_GRP_ID = :offenderCourseApptGrpId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method weeklyDefPreDeleteQuerry", e);
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in OffSchDefUpdate Ocuoscpv:", e);
			return 0;
		}
	}

	@Override
	public List<CourseScheduleRules> gettingSheduleRulesData(final CourseScheduleRules searchBean) {
		final String sql = getQuery("OCUOSCPV_GETTING_GET_SCHEDULES");
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("crs_acty_id", searchBean.getCrsActyId()), rowMapper);
	}

	@Override
	public void updatingDirectionCode(VOffenderCourseEvents courtEvents) {
		final String sql = getQuery("OCUOSCPV_OFFSCHDEF_UPDATE_OFFENDER_COURSE_APPT_RULES");
		final List<SqlParameterSource> parameters = new ArrayList<>();

		parameters.add(new BeanPropertySqlParameterSource(courtEvents));

		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		} catch (Exception e) {
			logger.error("Exception in OffSchDefUpdate Ocuoscpv:", e);

		}

	}
	@Override
	public Integer getCrsApptGrpId() {
		Integer returnVal = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { 
				new SqlOutParameter("RETURN_VAL", OracleTypes.NUMBER) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UNPAID_WORK").withFunctionName("GET_CRS_APPT_GRP_ID")
				.declareParameters(sqlParameters);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			returnVal = Integer.valueOf(returnValObj.get("RETURN_VAL").toString());
		} catch (final Exception e) {
			logger.error("Exception in getCrsApptGrpId Ocuoscpv:", e);
		}
		return returnVal;
	}

	@Override
	public List<OffenderProgramProfiles> offPrgProfilesExecuteQuery(OffenderProgramProfiles searchBean) {
		final String sql = getQuery("OCUOSCPV_OFF_PRG_PROFILES_HOLIDAY_FLAG");
		final RowMapper<OffenderProgramProfiles> apptGrpsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, courseScheduleRulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_prgref_id",searchBean.getOffPrgrefId(),"p_offender_book_id",searchBean.getOffenderBookId()),
				apptGrpsRowMapper);

	}

	@Override
	public Integer offPrgProfilesCommit(List<OffenderProgramProfiles> updObj) {
		final String sql = getQuery("OCUOSCPV_UPDATE_OFF_PRG_PROFILES_HOLIDAY_FLAG");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderProgramProfiles upd : updObj) {
			parameters.add(new BeanPropertySqlParameterSource(upd));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in offPrgProfilesCommit Ocuoscpv:", e);
			return 0;
		}
	}

	

	@Override
	public Integer offPrgProfilesStatusCommit(BigDecimal offPrgrefId,String modifyUserId) {
		final String sql = getQuery("OCUOSCPV_UPDATE_OFF_PRG_PROFILES_STATUS");
		try {
			namedParameterJdbcTemplate.update(sql,createParams("offPrgrefId",offPrgrefId,"modifyUserId",modifyUserId));
			return 1;
		} catch (Exception e) {
			logger.error("Exception in offPrgProfilesStatusCommit Ocuoscpv:", e);
			return 0;
		}
	}

}
