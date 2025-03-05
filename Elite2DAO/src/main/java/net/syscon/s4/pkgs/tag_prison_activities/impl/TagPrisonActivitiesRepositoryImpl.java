package net.syscon.s4.pkgs.tag_prison_activities.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesRepository;

@Repository
public class TagPrisonActivitiesRepositoryImpl extends RepositoryBase implements TagPrisonActivitiesRepository {
	private static Logger logger = LogManager.getLogger(TagPrisonActivitiesRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> courseSchecueMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	private final Map<String, FieldMapper> offproMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	@Override
	public Integer intIdCur(final Long crsActyId) {
		final String sql = getQuery("BACKDATE_ATTENDANCES_INT_ID_CUR");
		Integer internationalLocationId = null;
		try {
			internationalLocationId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CRS_ACTY_ID", crsActyId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return internationalLocationId;
	}

	@Override
	public List<CourseSchedules> getCourseScheculesDetails(final Long crsActyId, final Date offenderStartDate) {
		List<CourseSchedules> returnList = null;
		final String sql = getQuery("BACKDATE_ATTENDANCES_GET_COURSE_SCHEDULES");
		final RowMapper<CourseSchedules> courseScheduleRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseSchedules.class, courseSchecueMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_CRS_ACTY_ID", crsActyId, "P_OFFENDER_START_DATE", offenderStartDate),
					courseScheduleRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Integer getEventId() {
		final String sql = getQuery("BACKDATE_ATTENDANCES_GET_EVENT_ID");
		Integer eventId = null;
		try {
			eventId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return eventId;
	}

	@Override
	public Integer insertOffenderCourseAttendance(final OffenderCourseAttendance offCAttendance) {
		final String sql = getQuery("BACKDATE_ATTENDANCES_INSERT_OFFENDER_COURSE_ATTENDANCES");
		int[] returnArray = new int[] {};

		final List<OffenderCourseAttendance> list = new ArrayList<OffenderCourseAttendance>();
		list.add(offCAttendance);

		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final OffenderCourseAttendance ocn : list) {
			parameters.add(new BeanPropertySqlParameterSource(ocn));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderCourseAttendance:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int cursorC1(final OffenderProgramProfiles offPrgProfiles) {
		final String sql = getQuery("CURSOR_C1_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("agyLocId", offPrgProfiles.getAgyLocId(), "offenderBookId",
						offPrgProfiles.getOffenderBookId(), "crsActyId", offPrgProfiles.getCrsActyId(), "offPrgrefId",
						offPrgProfiles.getOffPrgrefId()),
				Integer.class);
	}

	@Override
	public int cursorC2(final OffenderProgramProfiles offPrgProfiles) {
		final String sql = getQuery("CURSOR_C2_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("agyLocId", offPrgProfiles.getAgyLocId(), "offenderBookId",
						offPrgProfiles.getOffenderBookId(), "crsActyId", offPrgProfiles.getCrsActyId(), "offPrgrefId",
						offPrgProfiles.getOffPrgrefId()),
				Integer.class);
	}

	@Override
	public int cursorC3(final OffenderProgramProfiles offPrgProfiles) {
		final String sql = getQuery("CURSOR_C3_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("agyLocId", offPrgProfiles.getAgyLocId(), "offenderBookId",
						offPrgProfiles.getOffenderBookId(), "crsActyId", offPrgProfiles.getCrsActyId(), "offPrgrefId",
						offPrgProfiles.getOffPrgrefId()),
				Integer.class);
	}

	@Override
	public Integer UpdateCourseSchedules(final Long crsActId, final Date scheduleEndDate, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_PROGRAM_PROFILES");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("P_CRS_ACTY_ID", crsActId, "P_SCHEDULE_END_DATE",
					scheduleEndDate, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("UpdateCourseSchedules:", e);
			return 1;
		}
		return 0;
	}

	@Override
	public List<OffenderProgramProfiles> cLock(final BigDecimal offenderBookId, final Date date) {
		final String sql = getQuery("C_LOCK_SELECT");

		final RowMapper<OffenderProgramProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offproMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId, "endDate", date),
				rowMapper);
	}

	@Override
	public void updateOffPrgPro(final Long offPrgrefId, final String userName) {
		final String sql = getQuery("UPDATE_OFF_PRG_PRO");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("OFF_PRGREF_ID", offPrgrefId);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer removeCourseSchedulesDelete(final List<CourseSchedules> list) {
		final String sql = getQuery("REMOVE_COURSE_SCHEDULES_DELETE");
		int count = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final CourseSchedules couSch : list) {
			parameters.add(new BeanPropertySqlParameterSource(couSch));
		}
		try {
			String tableName = "course_schedules";
			String whereCondition = "crs_acty_id = :crsActyId AND schedule_date > :scheduleDate";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			count = 0;
			logger.error("removeCourseSchedulesDelete", e);
		}

		if (list.size() == returnArray.length) {
			count = 1;
		} else {
			count = 0;
		}
		return count;
	}

	@Override
	public List<OffenderProgramProfiles> executeQueryoffenderProgramStatus(final BigDecimal offenderBookId) {
		List<OffenderProgramProfiles> returnList = null;
		final String sql = getQuery("END_WAITLIST_AND_ALLOCATIONS_SELECT_OFFENDER_PROGRAM_STATUS");
		final RowMapper<OffenderProgramProfiles> OffProgProfRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offproMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", offenderBookId),
					OffProgProfRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Integer offProgProfUpdateOne(final Date pDate, final String pEndReason, final String userName) {
		final String sql = getQuery("END_WAITLIST_AND_ALLOCATIONS_UPDATE_ONE");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_date", pDate, "p_end_reason", pEndReason, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("offProgProfUpdateOne :", e);
		}
		return retVal;

	}

	@Override
	public Integer offProgProfUpdateSecond(final Date pDate, final String pEndReason, final String userName) {
		final String sql = getQuery("END_WAITLIST_AND_ALLOCATIONS_UPDATE_SECOND");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_date", pDate, "p_end_reason", pEndReason, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("offProgProfUpdateSecond :", e);
		}
		return retVal;
	}

	public String getAgyLocIdDescription(final String agyLocId) {
		final String sql = getQuery("GET_AGY_LOC_ID_DESCRIPTION");

		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("AGY_LOC_ID", agyLocId),
					String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String getProgramIdDescription(final Long programId) {
		final String sql = getQuery("GET_PROGRAM_ID_DESCRIPTION");

		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROGRAM_ID", programId),
					String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String getCrsActIdIdDescription(final Long crsActyId) {
		final String sql = getQuery("GET_CRS_ACTY_ID_DESCRIPTION");

		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CRS_ACTY_ID", crsActyId),
					String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String getOffenderEndReasonDescription(final String offenderEndReason) {
		final String sql = getQuery("GET_OFFENDER_END_REASON_DESCRIPTION");

		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_END_REASON", offenderEndReason), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public String dispActyDesc(final Long pCrsActyId) {
		final String sql = getQuery("DISPLAY_WAITLIST_DETAILS_DISP_ACTY_DESC");
		String description = null;
		try {
			description = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return description;
	}

	@Override
	public Integer getCapacity(final Long pCrsActyId) {
		final String sql = getQuery("DISPLAY_WAITLIST_DETAILS_GET_CAPACITY");
		Integer capacity = null;
		try {
			capacity = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_COURSE_ACTIVITY_ID", pCrsActyId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		if (capacity == null) {
			capacity = 0;
		}
		return capacity;
	}

	@Override
	public Integer getCount(final Long pCrsActyId) {
		final String sql = getQuery("DISPLAY_WAITLIST_DETAILS_GET_COUNT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_COURSE_ACTIVITY_ID", pCrsActyId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public String getDescCode(final String pDomain, final String pRefCode) {
		final String sql = getQuery("DISPLAY_WAITLIST_DETAILS_GETDESCCODE");
		String description = null;
		description = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_DOMAIN", pDomain, "P_REFCODE", (pRefCode != null) ? pRefCode : "0"), String.class);

		if (description == null) {
			description = "";
		}
		return description;
	}

	@Override
	public String chkActyEndDate(final Long crsActyId, final Date scheduleEnddate) {
		final String sql = getQuery("CHK_ACTY_END_DATE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(crsActyId, "p_schedule_end_date", scheduleEnddate), String.class);
		} catch (Exception e) {
			logger.error("chkActyEndDate :" + e);
			result = "null";
		}
		return result;
	}

	@Override
	public Long getCrsActyId() {
		final String sql = getQuery("CRS_ACTY_ID");
		Long seq = null;
		try {
			seq = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getCrsActyId");
		}
		return seq;
	}

	@Override
	public Long chkwaitList(final OffenderProgramProfiles bean) {
		final String sql = getQuery("CHK_WAITLIST");
		Long count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID",
					bean.getOffenderBookId(), "P_PROGRAM_ID", bean.getProgramId(), "P_AGY_LOC_ID", bean.getAgyLocId()),
					Long.class);
		} catch (Exception e) {
			logger.error("chkwaitList :", e);
		}
		return count;
	}

	@Override
	public Date getAdmissionDate(final Long offenderBookId) {
		final String sql = getQuery("GET_ADMISSION_DATE");
		Date date = null;
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offenderBookId),
					Date.class);
		} catch (Exception e) {
			logger.error("getAdmissionDate :", e);
		}
		return date;
	}

	@Override
	public Date getBookingDate(final Long offenderBookId) {
		final String sql = getQuery("GET_BOOKING_DATE");
		Date date = null;
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offenderBookId),
					Date.class);
		} catch (Exception e) {
			logger.error("getAdmissionDate :", e);
		}
		return date;
	}

	@Override
	public Long chkAllocated(final Long pcrsActId, final Long pOffenderBookId, final Date offStartDate) {
		final String sql = getQuery("CHK_ALLOCATED");
		Long count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", pOffenderBookId,
					"P_CRS_ACTY_ID", pcrsActId, "P_OFFENDER_START_DATE", offStartDate), Long.class);
		} catch (Exception e) {
			logger.error("chkAllocated :", e);
		}
		return count;
	}

	@Override
	public String chkendDate(final Long crsActyId, final Date scheduleEnddate) {
		final String sql = getQuery("CHK_ACTY_END_DATE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(crsActyId, "p_schedule_end_date", scheduleEnddate, "p_crs_acty_id", crsActyId),
					String.class);
		} catch (Exception e) {
			logger.error("chkendDate :" + e);
		}
		return result;
	}

	@Override
	public List<CourseActivities> getCourseActivity(final String activity, final Long programId,
			final String agyLocId) {
		final String sql = getQuery("GET_COURSE_ACTIVITY");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				offproMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("activity", activity, "programId", programId, "agyLocId", agyLocId), rowMapper);
	}

	@Override
	public String getServices(final Long pProgramId) {
		final String sql = getQuery("GET_SERVICES");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROGRAM_ID", pProgramId),
					String.class);
		} catch (Exception e) {
			logger.error("getServices :" + e);
		}
		return desc;
	}
	
	@Override
	public Integer bulkUpdate(OffenderProgramProfiles inputObj) {
		final String sql = getQuery("TAG_PRISION_BULK_UPDATE");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("P_CRS_ACTY_ID", inputObj.getCrsActyId(),
					"P_OFFENDER_BOOK_ID", inputObj.getOffenderBookId()));
		} catch (Exception e) {
			logger.error("UpdateCourseSchedules:", e);
			return 0;
		}
		return 1;
	}
}