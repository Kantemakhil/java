package net.syscon.s4.pkgs.tag_unpaid_work.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.pkgs.tag_unpaid_work.TagUnpaidWorkRepository;

@Repository
public class TagUnpaidWorkRepositoryImpl extends RepositoryBase implements TagUnpaidWorkRepository {

	private static Logger logger = LogManager.getLogger(TagUnpaidWorkRepositoryImpl.class.getName());
	private Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	private final Map<String, FieldMapper> couActyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEAM_ID", new FieldMapper("offPrgrefId")).put("NAME", new FieldMapper("name"))
			.put("AREA_CODE", new FieldMapper("areaInformation")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.build();

	@Override
	public Integer insertOffCourseAttendance(final VOffenderCourseEvents offAttCourse) {
		final String sql = getQuery("INSERT_OFFENDER_COURSE_ATTENDANCES");
		final VOffenderCourseEvents offCourseAtte = new VOffenderCourseEvents();
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offCourseAtte));
		} catch (Exception e) {
			logger.error("insertOffCourseAttendance:", e);
			count = 0;
		}
		return count;
	}

	@Override
	public CourseActivities getProjectAllocDetails(final Long crsActyId) {
		final String sql = getQuery("GET_PROJECT_ALLOC_DETAILS");
		CourseActivities couAct = new CourseActivities();

		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				couActyMapping);
		try {
			couAct = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_crs_acty_id", crsActyId),
					rowMapper);
		} catch (Exception e) {
			logger.error("GetProjectAllocDetails", e);
		}
		return couAct;
	}

	@Override
	public Float getTotCreditHrsCur(final VOffenderCourseEvents offcourseAtt) {
		final String sql = getQuery("SELECYT_SUM_OFFENDER_COURSE_ATTENDANCE");
		Float hrs = null;
		try {
			hrs = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_off_book_id", offcourseAtt.getOffenderBookId(), "p_crs_acty_id",
							offcourseAtt.getCrsActyId(), "p_off_prgref_id", offcourseAtt.getOffPrgrefId()),
					Float.class);
		} catch (Exception e) {
			logger.error("getTotCeditHrsCur :" + e);
			return 0f;
		}
		return hrs;
	}

	@Override
	public Integer updateOffProgramprofiles(final VOffenderCourseEvents offcourseAtt, final Float hours,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_PROGRAM_PROFILES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("p_off_book_id", offcourseAtt.getOffenderBookId(), "lv_tot_credit_hrs", hours,
							"p_crs_acty_id", offcourseAtt.getCrsActyId(), "p_off_prgref_id",
							offcourseAtt.getOffPrgrefId(), "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffProgramprofiles :" + e);
			return count = 0;
		} catch (Exception e) {
			logger.error("updateOffProgramprofiles :" + e);
			return count = 0;
		}
		return count;
	}

	@Override
	public List<CourseActivities> getDispActyDesc(final BigDecimal crsActyId, final Long offenderBookId) {
		final String sql = getQuery("GET_DISP_ACTY_DESC");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				courtCasesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_offender_book_id", offenderBookId, "p_crs_acty_id", crsActyId), rowMapper);

	}

	// This methos is used to get count
	@Override
	public Integer getCount(final Long pCrsActyId, final Date pScheduleEndDate) {
		final String sql = getQuery("ALLOC_EXISTS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CRS_ACTY_ID", pCrsActyId, "P_SCHEDULE_END_DATE", pScheduleEndDate), Integer.class);
		} catch (Exception e) {
			logger.error("getCount :" + e);
			count = 0;
		}
		return count;
	}

	@Override
	public BigDecimal getOffSentTermHrs(final BigDecimal offenderBookId, final BigDecimal sentenceSeq) {
		final String sql = getQuery("GET_OFF_SENT_TERM_HRS");
		BigDecimal length = null;
		try {
			length = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", offenderBookId, "p_sentence_seq", sentenceSeq),
					BigDecimal.class);
		} catch (Exception e) {
			length = null;
			logger.error("getOffSentTermHrs :" + e);
		}
		return length;
	}

	@Override
	public Integer getHolidayCount(final Date eventDate) {
		final String sql = getQuery("GET_HOLIDAY_COUNT");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_event_date", eventDate),
					Integer.class);
		} catch (Exception e) {
			logger.error("getHolidayCount", e);
		}
		return retVal;

	}

	@Override
	public Date getWeeklyDefStartDate(final BigDecimal offPrgrefId) {
		final String sql = getQuery("GET_WEEKLY_DEF_START_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_prgref_id", offPrgrefId), Date.class);
	}

	@Override
	public Integer getCrsApptGrpId() {
		final String sql = getQuery("GET_CRS_APPT_GRP_ID");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getCrsApptGrpId", e);
		}
		return retVal;
	}

	// This methos CURSOR c1 is migrated from oracle
	@Override
	public List<Object[]> c1Select(final Long crsActyId, final Date scheduleEndDate) {
		final String sql = getQuery("C1_SELECT");
		final RowMapper<Object[]> courtCasesRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class,
				courtCasesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_crs_acty_id", crsActyId, "p_schedule_end_date", scheduleEndDate), courtCasesRowMapper);
	}

	// This method is used to update OFFENDER_PROGRAM_PROFILES table
	@Override
	public Integer offenderProgramProfilesUpdate(final Date pScheduleEndDate, final Long vOffPrgrefId,
			final String userName) {
		final String sql = getQuery("OFFENDER_PROGRAM_PROFILES_UPDATE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_schedule_end_date", pScheduleEndDate,
				"v_off_prgref_id", vOffPrgrefId, "modifyUserId", userName));
	}

	// This method is used to DELETE FROM v_offender_course_events table
	@Override
	public Integer vOffenderCourseEventsDelete(final Date pScheduleEndDate, final Long vOffPrgrefId,String modifyUserId) {
		final String sql = getQuery("V_OFFENDER_COURSE_EVENTS_DELETE");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "v_offender_course_events";
			String whereCondition = "event_date > :p_schedule_end_date AND off_prgref_id = :v_off_prgref_id";
			inputMap.put("p_schedule_end_date", pScheduleEndDate);
			inputMap.put("v_off_prgref_id", vOffPrgrefId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in vOffenderCourseEventsDelete " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_schedule_end_date", pScheduleEndDate, "v_off_prgref_id", vOffPrgrefId));
	}

}
