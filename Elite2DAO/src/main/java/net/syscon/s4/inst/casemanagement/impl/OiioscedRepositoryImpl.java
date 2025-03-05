package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.casemanagement.OiioscedRepository;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Class OiioscedRepositoryImpl
 */
@Repository
public class OiioscedRepositoryImpl extends RepositoryBase implements OiioscedRepository {

	/**
	 * Creates new OiioscedRepositoryImpl class Object
	 */
	public OiioscedRepositoryImpl() {
	}

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode")).build();
	private final Map<String, FieldMapper> internalScheduleReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("SICK_NOTE_EXPIRY_DATE", new FieldMapper("sickNoteExpiryDate"))
			.put("TO_INT_LOC_LEVEL_1_CODE", new FieldMapper("toIntLocLevel1Code"))
			.put("LU_LEVEL_3_CODE", new FieldMapper("luLevel3Code")).put("TO_CITY_NAME", new FieldMapper("toCityName"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId")).put("TO_ADDRESS_ID", new FieldMapper("toAddressId"))
			.put("UNPAID_WORK_SUPERVISOR", new FieldMapper("unpaidWorkSupervisor"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("UNPAID_WORK_ACTION", new FieldMapper("unpaidWorkAction"))
			.put("AGENCY_IML_LEVEL_1_CODE", new FieldMapper("agencyImlLevel1Code"))
			.put("ESCORT_CODE", new FieldMapper("escortCode")).put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("TO_INT_LOC_USER_DESC", new FieldMapper("toIntLocUserDesc"))
			.put("EVENT_STATUS_DESC", new FieldMapper("eventStatusDesc"))
			.put("TO_INTERNAL_LOCATION_DESC", new FieldMapper("toInternalLocationDesc"))
			.put("AGY_LOC_DESC", new FieldMapper("agyLocDesc")).put("LU_LEVEL_2_CODE", new FieldMapper("luLevel2Code"))
			.put("FROM_CITY_CODE", new FieldMapper("fromCityCode"))
			.put("EVENT_SUB_TYPE", new FieldMapper("eventSubType")).put("DETAILS", new FieldMapper("details"))
			.put("CHECK_BOX_1", new FieldMapper("checkBox1")).put("BUSY_DATE_FLAG", new FieldMapper("busyDateFlag"))
			.put("EVENT_OUTCOME_DESC", new FieldMapper("eventOutcomeDesc"))
			.put("SCHEDULED_TRIP_ID", new FieldMapper("scheduledTripId"))
			.put("EVENT_SUB_TYPE_DESC", new FieldMapper("eventSubTypeDesc"))
			.put("HIDDEN_COMMENT_TEXT", new FieldMapper("hiddenCommentText"))
			.put("IN_CHARGE_STAFF_NAME", new FieldMapper("inChargeStaffName"))
			.put("TO_INT_LOC_LEVEL_2_CODE", new FieldMapper("toIntLocLevel2Code"))
			.put("TRANSPORT_CODE", new FieldMapper("transportCode")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("EVENT_TYPE_DESC", new FieldMapper("eventTypeDesc"))
			.put("AGENCY_IML_LEVEL_2_CODE", new FieldMapper("agencyImlLevel2Code"))
			.put("IN_TIME", new FieldMapper("inTime")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId"))
			.put("SICK_NOTE_RECEIVED_DATE", new FieldMapper("sickNoteReceivedDate"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("OUT_TIME", new FieldMapper("outTime")).put("AGREED_TRAVEL_HOUR", new FieldMapper("agreedTravelHour"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("EVENT_TYPE", new FieldMapper("eventType"))
			.put("REFERENCE_ID", new FieldMapper("referenceId"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("LU_LEVEL_1_CODE", new FieldMapper("luLevel1Code")).put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("AGENCY_IML_DESC", new FieldMapper("agencyImlDesc"))
			.put("TO_INT_LOC_LEVEL_3_CODE", new FieldMapper("toIntLocLevel3Code"))
			.put("CHECK_BOX_2", new FieldMapper("checkBox2")).put("TO_AGY_LOC_DESC", new FieldMapper("toAgyLocDesc"))
			.put("END_TIME", new FieldMapper("endTime")).put("TO_LOC", new FieldMapper("toLoc"))
			.put("EVENT_CLASS", new FieldMapper("eventClass")).put("RETURN_DATE", new FieldMapper("returnDate"))
			.put("appearance_type", new FieldMapper("appearanceType"))
			.put("appearance_location", new FieldMapper("appearanceLocation"))
			.put("cancelReasonDesc", new FieldMapper("cancelReason"))
			.put("cancelReasoncode", new FieldMapper("reasonCode"))
			.put("cs_address", new FieldMapper("csAddress"))
			.put("cs_description", new FieldMapper("csDescription"))
			.put("oic_hearing_description", new FieldMapper("oicHearingDescription"))	
			.put("visit_description", new FieldMapper("visitDescription"))			
			.put("work_relaese_project_code", new FieldMapper("workRelaeseProjectCode"))	
			.put("work_relaese_provide_desc", new FieldMapper("workRelaeseProvideDesc"))
			
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> vOffenderAllSchedulesExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OIIOSCED_VOFFENDERALLSCHEDULES_FIND_V_OFFENDER_ALL_SCHEDULES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() != null) {
				sqlQuery.append(" voas.OFFENDER_BOOK_ID = :offenderBookId and "
						+ "(voas.event_date  between :fromDate and :toDate)" + " and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
				valuesList.addValue("fromDate", trunc(objSearchDao.getFromDate()));
				valuesList.addValue("toDate", trunc(objSearchDao.getToDate()));
			}
			if (objSearchDao.getEventClass() == null) {
			} else if (objSearchDao.getEventClass().equals("EXT_MOV")) {
				sqlQuery.append(
						" ( case when (voas.event_type ='CRT') then voas.appearance_type in (select code from reference_codes where domain ='CRT_APP_TYPE' and parent_code ='EXT') end or  case when (voas.event_type !='CRT') then voas.event_class in ('EXT_MOV') end )"
								+ " and ");
			} else {
				sqlQuery.append(
						" ( case when (voas.event_type ='CRT') then voas.appearance_type in ( select code from reference_codes where domain ='CRT_APP_TYPE' and parent_code ='INT') end  or  case when (voas.event_type !='CRT') then voas.event_class in ('INT_MOV') end )"
								+ " and ");
			}
			if (objSearchDao.getEventType() != null) {
				sqlQuery.append("voas.EVENT_TYPE = :eventType" + " and ");
				valuesList.addValue("eventType", objSearchDao.getEventType());
			}
			if (objSearchDao.getEventSubType() != null) {
				sqlQuery.append("voas.EVENT_SUB_TYPE = :eventSubType" + " and ");
				valuesList.addValue("eventSubType", objSearchDao.getEventSubType());
			}
			if (objSearchDao.getStartTime() != null) {
				sqlQuery.append("voas.START_TIME >= (:startTime:: timestamp)" + " and ");
				valuesList.addValue("startTime", objSearchDao.getStartTime());
			}
			if (objSearchDao.getEndTime() != null) {
				sqlQuery.append("to_char(voas.START_TIME :: timestamp,'HH24:MI'  ::text) <= to_char(:endTime :: timestamp,'HH24:MI' ::text)" + " and ");
				valuesList.addValue("endTime", objSearchDao.getEndTime());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by  EVENT_DATE,START_TIME,EVENT_TYPE_DESC,EVENT_SUB_TYPE_DESC ";
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VOffenderAllSchedulesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSchTypeRecordGroup(final String domain) {
		final String sql = getQuery("OIIOSCED_FIND_RGSCHTYPE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("domain", domain), referenceCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MovementReasons>
	 */
	public List<MovementReasons> rgSchReaExtRecordGroup(final String schTypeCode) {
		final String sql = getQuery("OIIOSCED_FIND_RGSCHREAEXT");
		final RowMapper<MovementReasons> movementReasonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MovementReasons.class, movementReasonsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("SCHTYPECODE", schTypeCode),
					movementReasonsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<InternalScheduleReasons>
	 */
	public List<InternalScheduleReasons> rgSchReaIntRecordGroup(final String schTypeCode) {
		final String sql = getQuery("OIIOSCED_FIND_RGSCHREAINT");
		final RowMapper<InternalScheduleReasons> internalScheduleReasonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternalScheduleReasons.class, internalScheduleReasonsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("SCHTYPECODE", schTypeCode),
					internalScheduleReasonsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(final VOffenderAllSchedules paramBean) {
		final String sql = getQuery("OIIOSCED_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderAllSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		final ArrayList<VOffenderAllSchedules> returnList = (ArrayList<VOffenderAllSchedules>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<VOffenderAllSchedules> vOffenderAllSchedulesQuery(VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OIIOSCED_VOFFENDERALLSCHEDULES_FIND_V_OFFENDER_ALL_SCHEDULES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append(" voas.OFFENDER_BOOK_ID = :offenderBookId and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getEventClass() != null) {
				sqlQuery.append("voas.EVENT_CLASS = :eventClass" + " and ");
				valuesList.addValue("eventClass", objSearchDao.getEventClass());
			}
			if (objSearchDao.getEventType() != null) {
				sqlQuery.append("voas.EVENT_TYPE = :eventType" + " and ");
				valuesList.addValue("eventType", objSearchDao.getEventType());
			}
			if (objSearchDao.getEventSubType() != null) {
				sqlQuery.append("voas.EVENT_SUB_TYPE = :eventSubType" + " and ");
				valuesList.addValue("eventSubType", objSearchDao.getEventSubType());
			}
			if (objSearchDao.getStartTime() != null) {
				sqlQuery.append("to_char(voas.START_TIME,'HH24:MI') >= to_char(:startTime,'HH24:MI')" + " and ");
				valuesList.addValue("startTime", objSearchDao.getStartTime());
			}
			if (objSearchDao.getEndTime() != null) {
				sqlQuery.append("to_char(voas.START_TIME,'HH24:MI') <= to_char(:endTime,'HH24:MI')" + " and ");
				valuesList.addValue("endTime", objSearchDao.getEndTime());
			}
			if (("CANC").equalsIgnoreCase(objSearchDao.getEventStatus())) {
				sqlQuery.append("(case when (event_type = 'CRT') then voas.direction_code = 'OUT' else voas.EVENT_STATUS in ('CANC') or voas.EVENT_STATUS not in ('CANC')  end) " + " and ");
			} else {
				sqlQuery.append("voas.EVENT_STATUS NOT IN ('CANC') " + " and ");
				sqlQuery.append("(case\r\n"
						+ "		when (voas.event_type = 'CRT') then (\r\n"
						+ "		voas.direction_code = 'OUT') \r\n"
						+ "		else ''=''\r\n"
						+ "		end ) "+ " and ");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by  EVENT_DATE,START_TIME,EVENT_TYPE_DESC,EVENT_SUB_TYPE_DESC ";
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VOffenderAllSchedulesRowMapper);
		return returnList;
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	@Override
	public String getProgramLocation(Integer offPrgrefId) {
		String locCode = null;
		final String sql = getQuery("OIIOSCED_LOCATION_NAME");

		try {
			locCode = namedParameterJdbcTemplate.queryForObject(sql, createParams("offPrgrefId", offPrgrefId),
					String.class);
		} catch (Exception e) {
			locCode = null;
		}

		return locCode;
	}

}
