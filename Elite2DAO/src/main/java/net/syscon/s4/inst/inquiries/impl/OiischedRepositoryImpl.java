package net.syscon.s4.inst.inquiries.impl;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.inquiries.OiischedRepository;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Class OiischedRepositoryImpl
 */
@Repository
public class OiischedRepositoryImpl extends RepositoryBase implements OiischedRepository {
	private static final String DESCRIPTION = "DESCRIPTION";
	private static Logger logger = LogManager.getLogger(OiischedRepositoryImpl.class.getName());

	/**
	 * Creates new OiischedRepositoryImpl class Object
	 */
	public OiischedRepositoryImpl() {
		/**
		 * Class OiischedRepositoryImpl
		 */
	}

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(DESCRIPTION, new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> internalScheduleReasonsapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(DESCRIPTION, new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_LAST_NAME", new FieldMapper("offenderLastName"))
			.put("OFFENDER_FIRST_NAME", new FieldMapper("offenderFirstName"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("START_TIME", new FieldMapper("startTime")).put("EVENT_TYPE_DESC", new FieldMapper("eventTypeDesc"))
			.put("EVENT_SUB_TYPE_DESC", new FieldMapper("eventSubTypeDesc"))
			.put("TO_INTERNAL_LOCATION_DESC", new FieldMapper("toInternalLocationDesc"))
			.put("AGENCY_IML_DESC", new FieldMapper("agencyImlDesc"))
			.put("TO_AGY_LOC_DESC", new FieldMapper("toAgyLocDesc"))
			.put("EVENT_CLASS", new FieldMapper("eventClass"))
			.put("BOOKING_ACTIVE_FLAG", new FieldMapper("bookingActiveFlag"))
			.put("cancelReasonDesc", new FieldMapper("cancelReason"))
			.put("TO_LOC_DESC", new FieldMapper("toLocDesc")).build();
	

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OIISCHED_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES");
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		 Format f = new SimpleDateFormat("HH.mm");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			
			if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() != null) {
				final DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				sqlQuery.append(
						" EVENT_DATE BETWEEN TO_DATE(:fromDate::text, 'DD/MM/YYYY') AND TO_DATE(:toDate::text, 'DD/MM/YYYY') and");
				params.addValue("fromDate", sdf.format(objSearchDao.getFromDate()));
				params.addValue("toDate", sdf.format(objSearchDao.getToDate()));
			}
			if (objSearchDao.getStartTime() != null && objSearchDao.getEndTime() != null) {
				sqlQuery = new StringBuffer();
				final String betweenTimes = getQuery("OIISCHED_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES_BETWEEN_TIMES");
				sqlQuery.append(betweenTimes);
				final DateFormat dateFormate = new SimpleDateFormat("dd-MM-yyyy");
				sqlQuery.append(" and");
				params.addValue("fromDate", dateFormate.format(objSearchDao.getFromDate()));
				params.addValue("toDate", dateFormate.format(objSearchDao.getToDate()));
				params.addValue("startTime",f.format(objSearchDao.getStartTime()));
				params.addValue("endTime", f.format(objSearchDao.getEndTime()));
			}
			if (objSearchDao.getStartTime() != null && objSearchDao.getEndTime() == null) {
				sqlQuery = new StringBuffer();
				final String betweenTimes = getQuery("OIISCHED_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES_ONLY_START_TIMES");
				sqlQuery.append(betweenTimes);
				final DateFormat dateFormate = new SimpleDateFormat("dd-MM-yyyy");
				sqlQuery.append(" and");
				params.addValue("fromDate", dateFormate.format(objSearchDao.getFromDate()));
				params.addValue("toDate", dateFormate.format(objSearchDao.getToDate()));
				params.addValue("startTime", f.format(objSearchDao.getStartTime()));
			}

			if (objSearchDao.getEventType() != null && !objSearchDao.getEventType().trim().equals("")) {
				sqlQuery.append(" EVENT_TYPE = :eventType and");
				params.addValue("eventType", objSearchDao.getEventType());
			}
			if (objSearchDao.getEventSubType() != null && !objSearchDao.getEventSubType().trim().equals("")) {
				sqlQuery.append(" EVENT_SUB_TYPE = :eventSubType and");
				params.addValue("eventSubType", objSearchDao.getEventSubType());
			}
			if (objSearchDao.getOffenderIdDisplay() != null && !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
				sqlQuery.append(" OFFENDER_ID_DISPLAY = :offenderIdDisplay and");
				params.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
			}
			if (objSearchDao.getEventClass() != null && objSearchDao.getEventClass().equals("EXT_MOV")) {
				sqlQuery.append(
						"( case when (event_type ='CRT') then appearance_type in ( select code from reference_codes where domain ='CRT_APP_TYPE' and parent_code ='EXT') end or  case when (event_type !='CRT') then event_class in ('EXT_MOV') end ) "
								+ " and ");
			} else if (objSearchDao.getEventClass() != null && objSearchDao.getEventClass().equals("INT_MOV")) {
				sqlQuery.append(
						"( case when (event_type ='CRT') then appearance_type in ( select code from reference_codes where domain ='CRT_APP_TYPE' and parent_code ='INT') end  or  case when (event_type !='CRT') then event_class in ('INT_MOV') end )"
								+ " and ");
			}
		}
		sqlQuery.append(" event_id is not null and ");
		sqlQuery.append(" BOOKING_ACTIVE_FLAG = :bookingActiveFlag and");
		params.addValue("bookingActiveFlag", "Y");
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY EVENT_DATE,START_TIME DESC,OFFENDER_LAST_NAME ,OFFENDER_FIRST_NAME";

		final RowMapper<VOffenderAllSchedules> vOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		try {
			return namedParameterJdbcTemplate.query(preparedSql, params, vOffenderAllSchedulesRowMapper);
		} catch (final Exception e) {
			logger.error("offSchExecuteQuery", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTypeRecordGroup(final String scheduleFilter) {
		final String sql = getQuery("OIISCHED_FIND_RGTYPE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("NBTSCHEDULE", scheduleFilter),
					referenceCodesRowMapper);
		} catch (final Exception e) {
			logger.error("rgTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<InternalScheduleReasons>
	 */
	public List<InternalScheduleReasons> rgSubtypeRecordGroup(final String scheduleFilter, final String scheduleType) {
		final String sql = getQuery("OIISCHED_FIND_RGSUBTYPE");
		final RowMapper<InternalScheduleReasons> internalScheduleReasonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternalScheduleReasons.class, internalScheduleReasonsapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("EVENTTYPE", scheduleType, "NBTSCHEDULE", scheduleFilter),
					internalScheduleReasonsRowMapper);
		} catch (final Exception e) {
			logger.error("rgSubtypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

}
