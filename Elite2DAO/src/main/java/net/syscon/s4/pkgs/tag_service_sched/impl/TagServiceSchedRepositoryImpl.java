package net.syscon.s4.pkgs.tag_service_sched.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedRepository;

@Repository
public class TagServiceSchedRepositoryImpl extends RepositoryBase implements TagServiceSchedRepository {
	private static Logger logger = LogManager.getLogger(TagServiceSchedRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> staffMemMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SCHEDULE_DATE", new FieldMapper("scheduleDate")).build();

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> couSchmapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SESSION_NO_ONE", new FieldMapper("sessionNo"))
			.put("SCHEDULE_DATE_ONE", new FieldMapper("scheduleDate"))
			.put("SESSION_NO_TWO", new FieldMapper("crsSchId")).put("SCHEDULE_DATE_TWO", new FieldMapper("startTime"))
			.build();

	//getting schedule_date, start_time records in course schedules table
	@Override
	public CourseSchedules crsCur(final Long crsSchId) {
		final String sql = getQuery("COURSE_SCHEDULES_SELECT");
		final RowMapper<CourseSchedules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseSchedules.class,
				staffMemMapp);
		CourseSchedules courseScedule = new CourseSchedules();
		try {
			courseScedule = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_crs_sch_id", crsSchId),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getCommDefault", e);
		}
		return courseScedule;
	}
	//This method is used for delete the course schedules
	@Override
	public Long deleteCourseSchedule(final Date startDate, final Date startTime,final Long crsActyId,String modifyUserId) {
		long count = 0;
		final String sql = getQuery("COURSE_SCHEDULE_DELETE");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_schedules";
			String whereCondition = "crs_acty_id = :crsActyId  AND ( schedule_date > :lv_start_date OR ( schedule_date = :lv_start_date AND start_time >= :lv_start_time ) )";
			inputMap.put("lv_start_date", startDate);
			inputMap.put("lv_start_time", startTime);
			inputMap.put("crsActyId", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteCourseSchedule " + e.getMessage());
		}
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("lv_start_date", startDate, "lv_start_time",startTime,"crsActyId",crsActyId));
		} catch (EmptyResultDataAccessException e) {
			logger.error("deleteCourseSchedule", e);
		}
		return count;
	}

	// delete any future cancelation records in attendances table
	@Override
	public Long clearCourseAttendances(final Long programInstanceId, final Long phaseInstanceId, final Long crsChId) {
		final String sql = getQuery("OFFENDER_COURSE_ATTENDANCES_DELETE");
		int count = 0;
		final Long count1 = (long) count;
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("p_program_instance_id", programInstanceId,
					"p_phase_instance_id", phaseInstanceId, "p_crs_sch_id", crsChId));
		} catch (Exception e) {
			logger.error("clearCourseAttendances", e);
		} 
		return count1;
	}

	// delete the course schedules
	@Override
	public Long deleteCourseSchedules(final Long programInstanceId, final Long phaseInstanceId, final Long crsChId,
			final Date startDate, final Date startTime) {
		final String sql = getQuery("COURSE_SCHEDULES_DELETE");
		int count = 0;
		final Long count1 = (long) count;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("p_program_instance_id", programInstanceId, "p_phase_instance_id", phaseInstanceId,
							"p_crs_sch_id", crsChId, "lv_schedule_date", startDate, "lv_start_time", startTime));
		} catch (EmptyResultDataAccessException e) {
			logger.error("clearCourseAttendances", e);
		}
		return count1;
	}

	public Long getLvSessionSeq(final VAcpSchedules paramBean) {
		final String sql = getQuery("GET_LV_SESSION_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_PHASE_LIST_SEQ", paramBean.getPhaseListSeq(), "P_SESSION_NO", paramBean.getSessionNo()),
				Long.class);
	}

	public Date getMaxStartTimeCursorPriorCur(final VAcpSchedules paramBean) {
		final String sql = getQuery("GET_MAX_START_TIME_CURSOR_PRIOR_CUR_IS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_PROGRAM_INSTANCE_ID", paramBean.getProgramInstanceId()), Date.class);

	}

	public Date getMinStartTimeCursorPriorCur(final VAcpSchedules paramBean) {
		final String sql = getQuery("GET_MIN_START_TIME_CURSOR_PRIOR_CUR_IS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_PROGRAM_INSTANCE_ID", paramBean.getProgramInstanceId()), Date.class);

	}

	@Override
	public CourseActivities gapCur(final Long crsActyId) {
		final String sql = getQuery("GET_CRS_ACTY_WITH_GAPS_GAP_CUR");
		CourseActivities retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", crsActyId),
					new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retObj;
	}

	@Override
	public CourseActivities phsCur(final Long phaseInstanceId) {
		final String sql = getQuery("GET_CRS_ACTY_WITH_GAPS_PHS_CUR");
		CourseActivities retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PHASE_INSTANCE_ID", phaseInstanceId), new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retObj;
	}

	@Override
	public Date getLastSchedDate(final Long pCrsActyId) {
		final String sql = getQuery("CS_BUILD_RECURRING_SCHEDULE_GET_LAST_SCHED_DATE");
		Date lastSchedDate = null;
		try {
			lastSchedDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return lastSchedDate;
	}

	@Override
	public List<CourseActivities> getCaDates(final Long pCrsActyId) {
		List<CourseActivities> returnList = null;
		final String sql = getQuery("CS_BUILD_RECURRING_SCHEDULE_GET_CA_DATES");
		final RowMapper<CourseActivities> crsActRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				staffMemMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					crsActRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}
	// check if we should respect holidays.
	@Override
	public List<CourseActivities> checkRespectHols(final Long pCrsActyId) {
		List<CourseActivities> returnList = null;
		final String sql = getQuery("CS_BUILD_RECURRING_SCHEDULE_HOL_CUR_ONE");
		final RowMapper<CourseActivities> crsActRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				staffMemMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					crsActRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Long getCrsSchId() {
		final String sql = getQuery("CS_BUILD_RECURRING_SCHEDULE_CRS_SCH_ID");
		Long crsSchId = null;
		try {
			crsSchId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return crsSchId;
	}

	@Override
	public Integer insertCourseSchedules(final CourseSchedules crsSchedules) {
		final String sql = getQuery("CS_BUILD_RECURRING_SCHEDULE_INSERT_CRS_SCHEDULES");
		Integer returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(crsSchedules));
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}

	@Override
	public List<SystemEvents> holCur(final Date pStartDate) {
		List<SystemEvents> returnList = null;
		final String sql = getQuery("CS_BUILD_RECURRING_SCHEDULE_HOL_CUR");
		final RowMapper<SystemEvents> sysEventsRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemEvents.class,
				staffMemMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_START_DATE", pStartDate),
					sysEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public List<CourseScheduleRules> lvRuleCur(final Long pCrsActyId) {
		List<CourseScheduleRules> returnList = null;
		final String sql = getQuery("CS_BUILD_RECURRING_SCHEDULE_LV_RULE_CUR");
		final RowMapper<CourseScheduleRules> crsSchRulRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseScheduleRules.class, staffMemMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					crsSchRulRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Long ssnCur(final Long listSeq, final Long parentCrsActyId) {
		final String sql = getQuery("GET_SSN_CUR");
		Long no = 0l;
		try {
			no = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("listSeq", listSeq, "parentCrsActyId", parentCrsActyId), Long.class);
		} catch (Exception e) {
			logger.error("ssnCur", e);
			no = 0l;
		}
		return no;

	}

	private final Map<String, FieldMapper> refMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("NO_DAYS", new FieldMapper("eventSeq")).build();

	private final Map<String, FieldMapper> couSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> offPrgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_FLAG", new FieldMapper("neededFlag")).build();

	@Override
	public Boolean checkRespectHols1(final Long pCrsActyId) {
		final String sql = getQuery("GET_CHECK_RESPECT_HOLS");
		Boolean flag = false;
		try {
			flag = namedParameterJdbcTemplate.queryForObject(sql, createParams("pCrsActyId", pCrsActyId),
					Boolean.class);
		} catch (Exception e) {
			logger.error("checkRespectHols", e);
			flag = false;
		}
		return flag;
	}

	@Override
	public List<SystemEvents> holCur1(final Date pStartDate) {
		final String sql = getQuery("GET_HOL_CUR");
		List<SystemEvents> returnList = new ArrayList<SystemEvents>();
		final RowMapper<SystemEvents> rowMapper = Row2BeanRowMapper.makeMapping(sql, SystemEvents.class, refMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pStartDate", pStartDate), rowMapper);
		} catch (Exception e) {
			logger.error("holCur", e);
		}
		return returnList;
	}

	@Override
	public List<CourseScheduleRules> lvRuleCur1(final Long pCrsActyId) {
		final String sql = getQuery("GET_LV_RULE_CUR");
		List<CourseScheduleRules> returnList = new ArrayList<CourseScheduleRules>();
		final RowMapper<CourseScheduleRules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,
				couSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", pCrsActyId), rowMapper);
		} catch (Exception e) {
			logger.error("lvRuleCur", e);
		}
		return returnList;
	}

	@Override
	public List<CourseActivities> crsCur1(final Long pCrsActyId) {
		final String sql = getQuery("GET_CRS_CUR");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		List<CourseActivities> retList = new ArrayList<CourseActivities>();

		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", pCrsActyId), rowMapper);
		} catch (Exception e) {
			logger.error("crsCur", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<VAcpSchedules> schCur1(final Long lvProgramInstanceId, final Long lvPhaseInstanceId,
			final Long pCrsActyId) {
		final String sql = getQuery("GET_SCH_CUR");
		List<VAcpSchedules> returnList = new ArrayList<VAcpSchedules>();
		final RowMapper<VAcpSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, VAcpSchedules.class,
				couSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", pCrsActyId,
					"lvProgramInstanceId", lvProgramInstanceId, "lvPhaseInstanceId", lvPhaseInstanceId), rowMapper);
		} catch (Exception e) {
			logger.error("schCur", e);
		}
		return returnList;
	}

	@Override
	public List<CourseActivities> adjAllPerCrsCur1(final Long pCrsActyId) {
		final String sql = getQuery("GET_ADJ_ALL_PER_CRS_CUR");
		List<CourseActivities> returnList = new ArrayList<CourseActivities>();
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				couSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", pCrsActyId), rowMapper);

		} catch (Exception e) {
			logger.error("adjAllPerCrsCur", e);
		}
		return returnList;
	}

	@Override
	public List<CourseActivities> adjustPeriodCrsCur1(final Long crsActyId) {
		final String sql = getQuery("GET_ADJUST_PERIOD_CRS_CUR");
		List<CourseActivities> returnList = new ArrayList<CourseActivities>();
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				couSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", crsActyId), rowMapper);

		} catch (Exception e) {
			logger.error("adjustPeriodCrsCur", e);
		}
		return returnList;
	}

	@Override
	public List<CourseSchedules> getSessionLimits1(final Long crsActyId) {
		final String sql = getQuery("GET_SESSION_IMITS");
		final RowMapper<CourseSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseSchedules.class,
				couSchmapping);
		List<CourseSchedules> retList = new ArrayList<CourseSchedules>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", crsActyId), rowMapper);
		} catch (Exception e) {
			logger.error("getSessionLimits", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Integer ssnCur1(final Long listSeq, final Long parentCrsActyId) {
		final String sql = getQuery("GET_SSN_CUR");
		Integer no = 0;
		try {
			no = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("listSeq", listSeq, "parentCrsActyId", parentCrsActyId), Integer.class);
		} catch (Exception e) {
			logger.error("ssnCur", e);
			no = 0;
		}
		return no;

	}

	@Override
	public Integer updateCourseActivitiesOperation1(final Long crsActyId, final Integer lvMinSessionNo,
			final Date lvMinScheduleDate, final Integer lvMaxSessionNo, final Date lvMaxScheduleDate,
			final Integer lvStartSessionNo, final Integer lvEndSessionNo, final String userName) {
		int count = 0;
		final String sql = getQuery("UPDATE_COURSE_ACTIVITIES_OPERATION");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("CRS_ACTY_ID", crsActyId);
		inParamMap.put("LV_MIN_SESSION_NO", lvMinSessionNo);
		inParamMap.put("LV_MIN_SCHEDULE_DATE", lvMinScheduleDate);
		inParamMap.put("LV_MAX_SESSION_NO", lvMaxSessionNo);
		inParamMap.put("LV_MAX_SCHEDULE_DATE", lvMaxScheduleDate);
		inParamMap.put("LV_START_SESSION_NO", lvStartSessionNo);
		inParamMap.put("LV_END_SESSION_NO", lvEndSessionNo);
		inParamMap.put("modifyUserId", userName);
		try {
			count = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateCourseActivitiesOperation", e);
			count = 0;
		}
		return count;
	}

	@Override
	public List<CourseActivities> courseActivitiesSEDates1(final Long crsActyId) {
		final String sql = getQuery("COURSE_ACTIVITIES_S_E_DATES");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		List<CourseActivities> retList = new ArrayList<CourseActivities>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", crsActyId), rowMapper);
		} catch (Exception e) {
			logger.error("courseActivitiesSEDates", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<OffenderProgramProfiles> allocCurSelect1(final Long crsActyId) {
		final String sql = getQuery("GET_ALLOC_CUR_SELECT");
		List<OffenderProgramProfiles> returnList = new ArrayList<OffenderProgramProfiles>();
		final RowMapper<OffenderProgramProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offPrgMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", crsActyId), rowMapper);

		} catch (Exception e) {
			logger.error("allocCurSelect", e);
		}
		return returnList;
	}

	@Override
	public Date getScheduleDateForSession1(final Long crsActyId, final Long startSessionNo) {
		final String sql = getQuery("GET_SCHEDULE_DATE_FOR_SESSION");
		Date schDate = null;
		try {
			schDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("crsActyId", crsActyId, "startSessionNo", startSessionNo), Date.class);
		} catch (Exception e) {
			logger.error("getScheduleDateForSession", e);
			schDate = null;
		}
		return schDate;
	}

	@Override
	public Date endCur1(final Long offPrgrefId, final Date lvBigDate) {
		final String sql = getQuery("GET_END_CUR");
		Date schDate = null;
		try {
			schDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offPrgrefId", offPrgrefId, "lvBigDate", lvBigDate), Date.class);
		} catch (Exception e) {
			logger.error("endCur", e);
			schDate = null;
		}
		return schDate;
	}

	@Override
	public void updateOffProProf1(final Long offPrgrefId, final Date lvOffenderStartDate, final Date lvOffenderEndDate,
			final String userName) {
		final String sql = getQuery("UPDATE_OFF_PRO_PROF");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("offPrgrefId", offPrgrefId);
		inParamMap.put("lvOffenderStartDate", lvOffenderStartDate);
		inParamMap.put("lvOffenderEndDate", lvOffenderEndDate);
		inParamMap.put("modifyUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateOffProProf", e);
		}
	}

	@Override
	public void clearCourseAttendances1(final Long pCrsActyId, final Long lvProgramInstanceId,
			final Long lvPhaseInstanceId) {
		final String sql = getQuery("CLEAR_COURSE_ATTENDANCES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("pCrsActyId", pCrsActyId);
		inParamMap.put("lvProgramInstanceId", lvProgramInstanceId);
		inParamMap.put("lvPhaseInstanceId", lvPhaseInstanceId);
		try {
			String tableName="offender_course_attendances";
			String whereCondition="";
			updatePreDeletedRow(tableName, whereCondition, inParamMap);			
		}catch (Exception e) {
			logger.error(e);
		}
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("clearCourseAttendances", e);
		}

	}

	@Override
	public Integer updateCourseSchedule(final VAcpSchedules vacpSchedules, final String userName) {

		final String sql = getQuery("CHANGE_COURSE_SCHEDULE_UPDATE_COURSE_SCHEDULE");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_schedule_date", vacpSchedules.getScheduleDate(), "p_start_time",
							vacpSchedules.getStartTime(), "p_end_time", vacpSchedules.getEndTime(), "p_schedule_status",
							vacpSchedules.getScheduleStatus(), "p_crs_sch_id", vacpSchedules.getCrsSchId(),
							"modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	@Override
	public List<CourseActivities> crsCurAp(final Long pCrsActyId) {
		List<CourseActivities> returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_CRS_CUR");
		final RowMapper<CourseActivities> crsActRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					crsActRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public CourseSchedules getSessionLimits(final Long pCrsActyId) {
		CourseSchedules returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_GSL_CUR");
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					new BeanPropertyRowMapper<CourseSchedules>(CourseSchedules.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Integer updateCourseActivities(final Long lvMinSessionNo, final Long lvStartSessionNo,
			final Date lvMinScheduleDate, final Long lvMaxSessionNo, final Long lvEndSessionNo,
			final Date lvMaxScheduleDate, final Long crsActyId, final String userName) {
		
		Date scheduleStartDate;
		Date scheduleEndDate;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_UPDATE_COURSE_ACTIVITIES");
		
		scheduleStartDate =( lvMinSessionNo == lvStartSessionNo) ? lvMinScheduleDate : null;
		scheduleEndDate = (lvMaxSessionNo == lvEndSessionNo)?lvMaxScheduleDate : null;
		

		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("LV_MIN_SCHEDULE_DATE", scheduleStartDate, "LV_MAX_SCHEDULE_DATE", scheduleEndDate,
							"CRS_ACTY_ID", crsActyId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;

	}

	@Override
	public CourseActivities getCourseActivities(final Long crsActyId) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_GET_SCHEDULE_START_DATE_AND_SCHEDULE_END_DATE");
		CourseActivities retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("CRS_ACTY_ID", crsActyId),
					new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public Date schCur(final Long pCrsActyId, final Long pSessionNumber) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_SCH_CUR");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CRS_ACTY_ID", pCrsActyId, "P_SESSION_NUMBER", pSessionNumber), Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	/*
	 * This function is migrated from oracle TAG_SERVICE_SCHED.
	 * 
	 * get total number of schedules at crs_acty level and lower
	 */
	@Override
	public Long countSessions(final Long pCrsActyId) {
		final String sql = getQuery("COUNT_SESSIONS_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_crs_acty_id", pCrsActyId), Long.class);
	}

	@Override
	public Date getLastProgramScheduleDate(final Long pProgramInstanceId, final Long pPhaseListSeq) {
		final String sql = getQuery("GET_LAST_PROGRAM_SCHEDULE_DATE_MAX_CUR");
		Date maxDate = null;
		try {
			maxDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_program_instance_id", pProgramInstanceId, "p_phase_list_seq", pPhaseListSeq),
					Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return maxDate;
	}

	@Override
	public Date getNextPhaseScheduleDate(final Long pProgramInstanceId, final Long pPhaseListSeq) {
		final String sql = getQuery("GET_NEXT_PHASE_SCHEDULE_DATE_MIN_CUR");
		Date minDate = null;
		try {
			minDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_program_instance_id", pProgramInstanceId, "p_phase_list_seq", pPhaseListSeq),
					Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return minDate;
	}

	@Override
	public Integer getMaxSessions(final Long pCrsActyId) {
		final String sql = getQuery("GET_MAX_SESSION_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pCrsActyId), Integer.class);
	}

	@Override
	public CourseActivities getCourseActstartCur(final Long pCrsActyId) {
		final String sql = getQuery("START_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
				new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
	}

	@Override
	public Date getPriorSchedueDate(final CourseActivities bean) {
		final String sql = getQuery("PRIOR_CUR");
		Date date = null;
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_program_instance_id",
					bean.getProgramIstanceId(), "lv_session_seq", bean.getSessionLength()), Date.class);
		} catch (Exception e) {
			logger.error("getPriorSchedueDate :" + e);
		}
		return date;
	}

	@Override
	public Integer insertCourseSchedule(final VAcpSchedules vacpSchedules, final String userName) {
		final String sql = getQuery("CREATE_COURSE_SCHEDULE_INSERT_COURSE_SCHEDULE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("crs_sch_id",vacpSchedules.getCrsSchId(),"p_crs_acty_id", vacpSchedules.getpCrsActyId(), "p_schedule_date",
							vacpSchedules.getScheduleDate(), "p_start_time", vacpSchedules.getStartTime(), "p_end_time",
							vacpSchedules.getEndTime(), "p_session_no", vacpSchedules.getSessionNo(), "createUserId",
							userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return null;
	}

	@Override
	public Integer createSchAttendances(final Long pCrsSchId, final String userName) {
		final String sql = getQuery("CREATE_COURSE_SCHEDULE_CREATE_SCH_ATTENDANCES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("p_crs_sch_id", pCrsSchId, "createUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public List<CourseActivities> crsCurTwo(final Long pCrsActyId) {
		final String sql = getQuery("GET_CRS_CUR_TWO");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		List<CourseActivities> retList = new ArrayList<CourseActivities>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", pCrsActyId), rowMapper);
		} catch (Exception e) {
			logger.error("crsCur", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<VAcpSchedules> schCur(final Long lvProgramInstanceId, final Long lvPhaseInstanceId,
			final Long pCrsSchId) {
		final String sql = getQuery("GET_SCH_CUR_TWO");
		List<VAcpSchedules> returnList = new ArrayList<VAcpSchedules>();
		final RowMapper<VAcpSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, VAcpSchedules.class,
				couSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pCrsSchId", pCrsSchId,
					"lvProgramInstanceId", lvProgramInstanceId, "lvPhaseInstanceId", lvPhaseInstanceId), rowMapper);
		} catch (Exception e) {
			logger.error("schCur", e);
		}
		return returnList;
	}

	@Override
	public List<CourseActivities> adjAllPerCrsCur(final Long pCrsActyId) {
		final String sql = getQuery("GET_ADJ_ALL_PER_CRS_CUR");
		List<CourseActivities> returnList = new ArrayList<CourseActivities>();
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				couSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pCrsActyId", pCrsActyId), rowMapper);

		} catch (Exception e) {
			logger.error("adjAllPerCrsCur", e);
		}
		return returnList;
	}

	@Override
	public void clearCourseAttendancesTwo(final Long pCrsActyId, final Long lvProgramInstanceId,
			final Long lvPhaseInstanceId,String modifyUserId) {
		final String sql = getQuery("CLEAR_COURSE_ATTENDANCES_TWO");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("pcrsactyid", pCrsActyId);
		inParamMap.put("lvprograminstanceid", lvProgramInstanceId);
		inParamMap.put("lvphaseinstanceid", lvPhaseInstanceId);
		try {
			String tableName = "offender_course_attendances";
			String whereCondition = " crs_sch_id IN ( SELECT crs_sch_id FROM course_schedules WHERE start_time >= ( SELECT start_time FROM v_acp_schedules WHERE crs_sch_id = :pcrsactyid AND program_instance_id = :lvprograminstanceid ) AND catch_up_crs_sch_id IS NULL AND schedule_status = 'SCH' AND crs_acty_id IN ( SELECT crs_acty_id FROM course_activities START WITH crs_acty_id = nvl(:lvphaseinstanceid, :lvprograminstanceid) CONNECT BY PRIOR crs_acty_id = parent_crs_acty_id ) )";
			inParamMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inParamMap);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("clearCourseAttendances", e);
		}

	}

	// This methods is used to get_last_phase_details
	@Override
	public CourseActivities getCourseAcctDetails(final Long pParentCrsActyId) {
		CourseActivities retObj = null;
		final String sql = getQuery("LAST_CUR");
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PARENT_CRS_ACTY_ID", pParentCrsActyId),
					new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;

	}

	@Override
	public Date getPriorScheduleDate(Long pProgramInstanceId, BigDecimal lvSessionSeq) {
		final String sql = getQuery("PRIOR_CUR");
		Date date = null;
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_program_instance_id", pProgramInstanceId, "lv_session_seq", lvSessionSeq),
					Date.class);
		} catch (Exception e) {
			logger.error("getPriorSchedueDate :" + e);
		}
		return date;
	}

	@Override
	public Date getNextScheduleDate(Long pProgramInstanceId, BigDecimal lvSessionSeq) {
		final String sql = getQuery("PRIOR_CUR_TWO");
		Date date = null;
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PROGRAM_INSTANCE_ID", pProgramInstanceId, "LV_SESSION_SEQ", lvSessionSeq),
					Date.class);
		} catch (Exception e) {
			logger.error("getPriorSchedueDate :" + e);
		}
		return date;
	}
	// this method CS_BUILD_ACP_SCHEDULE to be used for  build the schedule
	@Override
	public List<CourseActivities> caCurCsBldAcpSch(Long pCrsActyId) {
		List<CourseActivities> returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_CA_CUR");
		final RowMapper<CourseActivities> crsActRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					crsActRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;

	}

	@Override
	public Long maxCurCsBldAcpSch(Long pcrsActyId) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_MAX_CUR");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pcrsActyId),
					Long.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public Long startCurCsBldAcpSch(Long pcrsActyId) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_START_CUR");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pcrsActyId),
					Long.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public VAcpSchedules getLastSessionDetailsCsBldAcpSch(Long pProgramInstanceId, Long pPhaseInstanceId) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_LAST_CUR_NEW");
		VAcpSchedules retVal = null;
		try {
			//Commented because schedule are not creating properly after clearing session because of listphase seq
//			if(pPhaseInstanceId != null) {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PROGRAM_INSTANCE_ID", pProgramInstanceId, "P_PHASE_INSTANCE_ID", pPhaseInstanceId),
					new BeanPropertyRowMapper<VAcpSchedules>(VAcpSchedules.class));
//			}
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public List<CourseActivities> lvCaRefCsBldAcpSch(Long pCrsActyId, Long lvPhaseListSeq, Long lvModuleListSeq,
			Long pListSeqEnd) {
		List<CourseActivities> returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_LV_CA_REF");
		final RowMapper<CourseActivities> crsActRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		try {
			returnList = namedParameterJdbcTemplate
					.query(sql,
							createParams("P_CRS_ACTY_ID", pCrsActyId, "LV_PHASE_LIST_SEQ", lvPhaseListSeq,
									"LV_MODULE_LIST_SEQ", lvModuleListSeq, "P_LIST_SEQ_END", pListSeqEnd),
							crsActRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public List<CourseActivities> crsCurCsBldAcpSch(Long pCrsActyId) {
		List<CourseActivities> returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_CRS_CUR_ONE");
		final RowMapper<CourseActivities> crsActRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					crsActRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public CourseSchedules getSessionLimitsCsBldAcpSch(Long pCrsActyId) {
		CourseSchedules returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_GSL_CUR_ONE");
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					new BeanPropertyRowMapper<CourseSchedules>(CourseSchedules.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Long ssnCurCsBldAcpSch(Long pListSeq, Long pParentCrsActyId) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_SSN_CUR");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PARENT_CRS_ACTY_ID", pParentCrsActyId, "P_LIST_SEQ", pListSeq), Long.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public Integer updateCourseActivitiesCsBldAcpSch(Date schStartDate, Date schEndDate, Long crsActyId) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_UPDATE_COURSE_ACTIVITIES_ONE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("schStartDate", schStartDate, "schEndDate", schEndDate,
							"crsActyId", crsActyId));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;

	}

	@Override
	public CourseActivities getCourseActivitiesCsBldAcpSch(Long crsActyId) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_GET_SCHEDULE_START_DATE_AND_SCHEDULE_END_DATE_ONE");
		CourseActivities retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("CRS_ACTY_ID", crsActyId),
					new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public List<OffenderProgramProfiles> allocCurCsBldAcpSch(Long crsActyId) {
		List<OffenderProgramProfiles> returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_ALLOC_CUR");
		final RowMapper<OffenderProgramProfiles> offPPRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", crsActyId),
					offPPRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Date schCurCsBldAcpSch(Long pCrsActyId, Long pSessionNumber) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_SSN_CUR");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CRS_ACTY_ID", pCrsActyId, "P_SESSION_NUMBER", pSessionNumber), Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public Date endCurCsBldAcpSch(Long pOffPrgrefId, Date lvBigDate) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_END_CUR");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LV_BIG_DATE", lvBigDate, "P_OFF_PRGREF_ID", pOffPrgrefId), Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public Integer updateOffenderProgramProfilesCsBldAcpSch(Long pOffPrgrefId, Date lvOffenderStartDate,
			Date lvOffenderEndDate) {

		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_UPDATE_OFFENDER_PROGRAM_PROFILES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("OFF_PRGREF_ID", pOffPrgrefId,
					"LV_OFFENDER_START_DATE", lvOffenderStartDate, "LV_OFFENDER_END_DATE", lvOffenderEndDate));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateCourseSchedule(CourseSchedules obj) {

		final String sql = getQuery("UPDATE_COURSE_SCHEDULES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

}