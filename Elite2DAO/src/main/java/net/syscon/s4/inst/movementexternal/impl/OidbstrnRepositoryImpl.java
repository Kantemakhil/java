package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
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
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movementexternal.OidbstrnRepository;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import oracle.jdbc.OracleTypes;

@Repository
public class OidbstrnRepositoryImpl extends RepositoryBase implements OidbstrnRepository {

	/**
	 * Creates new OidbstrnRepositoryImpl class Object
	 */
	Logger logger = LogManager.getLogger(OidbstrnRepositoryImpl.class.getName());
	private static Logger log = LogManager.getLogger(OidbstrnRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("TO_INTERNAL_LOCATION_ID", new FieldMapper("toInternalLocationId"))
			.put("CREDITED_HOURS", new FieldMapper("creditedHours"))
			.put("UNEXCUSED_ABSENCE_FLAG", new FieldMapper("unexcusedAbsenceFlag"))
			.put("UNDERSTANDING_CODE", new FieldMapper("understandingCode"))
			.put("OFFENDER_LAST_NAME", new FieldMapper("offenderLastName"))
			.put("LU_LEVEL_4_CODE", new FieldMapper("luLevel4Code"))
			.put("AGENCY_IML_LEVEL_3_CODE", new FieldMapper("agencyImlLevel3Code"))
			.put("PERFORMANCE_CODE", new FieldMapper("performanceCode")).put("TA_ID", new FieldMapper("taId"))
			.put("LIVING_UNIT_DESC", new FieldMapper("livingUnitDesc"))
			.put("ESCORT_DESC", new FieldMapper("escortDesc"))
			.put("BOOKING_ACTIVE_FLAG", new FieldMapper("bookingActiveFlag"))
			.put("TO_CITY_CODE", new FieldMapper("toCityCode")).put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("CHECK_SUM", new FieldMapper("checkSum")).put("APPLICATION_TIME", new FieldMapper("applicationTime"))
			.put("RECORD_SOURCE", new FieldMapper("recordSource"))
			.put("SCHEDULE_MOVEMENT_TIME", new FieldMapper("scheduleMovementTime"))
			.put("OFFENDER_FIRST_NAME", new FieldMapper("offenderFirstName"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus"))
			.put("IN_CHARGE_STAFF_ID", new FieldMapper("inChargeStaffId"))
			.put("PIECE_WORK", new FieldMapper("pieceWork"))
			.put("UNPAID_WORK_BEHAVIOUR", new FieldMapper("unpaidWorkBehaviour"))
			.put("START_TIME", new FieldMapper("startTime")).put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("TO_ADDRESS_OWNER_CLASS", new FieldMapper("toAddressOwnerClass"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("FROM_CITY_NAME", new FieldMapper("fromCityName")).put("TO_LOC_DESC", new FieldMapper("toLocDesc"))
			.put("RETURN_TIME", new FieldMapper("returnTime")).put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("APPLICATION_DATE", new FieldMapper("applicationDate"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome")).put("EVENT_ID", new FieldMapper("eventId"))
			.put("ENGAGEMENT_CODE", new FieldMapper("engagementCode"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
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
			.put("EVENT_TYPE", new FieldMapper("eventType")).put("REFERENCE_ID", new FieldMapper("referenceId"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("LU_LEVEL_1_CODE", new FieldMapper("luLevel1Code")).put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("AGENCY_IML_DESC", new FieldMapper("agencyImlDesc"))
			.put("TO_INT_LOC_LEVEL_3_CODE", new FieldMapper("toIntLocLevel3Code"))
			.put("CHECK_BOX_2", new FieldMapper("checkBox2")).put("TO_AGY_LOC_DESC", new FieldMapper("toAgyLocDesc"))
			.put("END_TIME", new FieldMapper("endTime")).put("TO_LOC", new FieldMapper("toLoc"))
			.put("EVENT_CLASS", new FieldMapper("eventClass")).put("RETURN_DATE", new FieldMapper("returnDate"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).build();

	public OidbstrnRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderAllSchedules> offAllSchExecuteQuery(String caseLoad, VOffenderAllSchedules objSearchDao) {

		final String preSql = getQuery("OIDBSTRN_OFFALLSCH_FIND_V_OFFENDER_ALL_SCHEDULES");
		final StringBuffer preparedSql = new StringBuffer(preSql);
		if (objSearchDao != null && objSearchDao.getFromDate() == null && objSearchDao.getToDate() == null) {
			preparedSql.append(" AND V_OFFENDER_ALL_SCHEDULES.EVENT_DATE IS NULL ");
			preparedSql.append(" AND EXISTS ( SELECT 1 FROM OFFENDER_IND_SCH_WAIT_LISTS O ");
			preparedSql.append(" WHERE O.EVENT_ID = V_OFFENDER_ALL_SCHEDULES.EVENT_ID ");
			preparedSql.append(" AND   O.WAIT_LIST_STATUS = 'PEN' ");
			preparedSql.append(" AND   O.APPROVED_FLAG  = 'Y' ");

		} else {
			preparedSql.append(" AND EVENT_DATE  BETWEEN (:FROMDATE) AND (:TODATE) ");
		}
		if (objSearchDao != null && objSearchDao.getToAgyLocId() != null) {
			preparedSql.append(" AND TO_AGY_LOC_ID = :toAgyLocId ");
		}
		if (objSearchDao != null && objSearchDao.getAgyLocId() != null) {
			preparedSql.append(" AND AGY_LOC_ID = :AgyLocId ");
		}
		if (objSearchDao != null && objSearchDao.getToAgyLocId() == null && objSearchDao.getAgyLocId() == null) {
			preparedSql.append(" AND EXISTS ( SELECT 1 FROM AGENCY_LOCATIONS AL ");
			preparedSql.append(" WHERE AL.AGENCY_LOCATION_TYPE = 'INST' ");
			preparedSql.append(" AND   AL.AGY_LOC_ID = v_offender_all_schedules.AGY_LOC_ID  ");
			preparedSql.append(" AND   AL.AGY_LOC_ID NOT IN ('OUT', 'TRN') ");
			preparedSql.append(" AND   EXISTS ( SELECT 1 FROM CASELOAD_AGENCY_LOCATIONS CL ");
			preparedSql.append(" WHERE  CL.AGY_LOC_ID = V_OFFENDER_ALL_SCHEDULES.AGY_LOC_ID ");
			preparedSql.append(" AND    CL.CASELOAD_ID = :caseLoad)) ");

		}
		preparedSql.append(" ORDER BY EVENT_DATE DESC,START_TIME ASC,OFFENDER_LAST_NAME,OFFENDER_FIRST_NAME ");

		final String sql = preparedSql.toString();
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		List<VOffenderAllSchedules> returnList = new ArrayList<VOffenderAllSchedules>();
		if (returnList != null) {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AgyLocId", objSearchDao.getAgyLocId(), "toAgyLocId", objSearchDao.getToAgyLocId(),
							"FROMDATE", new java.sql.Date(objSearchDao.getFromDate().getTime()), "TODATE",
							new java.sql.Date(objSearchDao.getToDate().getTime()), "caseLoad", caseLoad),
					VOffenderAllSchedulesRowMapper);
		}
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offAllSchInsertVOffenderAllSchedules(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		int insertCount = 0;
		String sql = getQuery("OIDBSTRN_OFFALLSCH_INSERT_V_OFFENDER_ALL_SCHEDULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount++;
		}
		if (returnArray.length == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * @throws SQLException
	 */
	public Integer offAllSchUpdateVOffenderAllSchedules(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		int insertCount = 0;
		String sql = getQuery("OIDBSTRN_OFFALLSCH_UPDATE_V_OFFENDER_ALL_SCHEDULES");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllSchedules.size() == insertCount) {
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
	public List<MovementReasons> rgReasonRecordGroup() {
		final String sql = getQuery("OIDBSTRN_FIND_RGREASON");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(String caseLoadId) {
		final String sql = getQuery("OIDBSTRN_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAllAgyLocRecordGroup() {
		final String sql = getQuery("OIDBSTRN_FIND_RGALLAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		final String sql = getQuery("OIDBSTRN_FIND_RGESCORT");
		List<ReferenceCodes> returnList = null;
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			log.error("rgEscortRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		final String sql = getQuery("OIDBSTRN_FIND_RGCANCELREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> resultList = null;
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			log.error("rgCancelReasonRecordGroup", e);
		}
		return resultList;
	}

	@Override
	public Integer tagSchedulesCreateSchedules(List<OffenderIndSchedules> offederIndSch) {

		String sql = getQuery("OIDBSTRN_TAG_SCHEDULE_CREATE_SCHEDULE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offIndschedules : offederIndSch) {
			parameters.add(new BeanPropertySqlParameterSource(offIndschedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (offederIndSch.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer tagSchedulesUpdateSchedules(List<OffenderIndSchedules> offederIndSch) {
		String sql = getQuery("OIDBSTRN_TAG_SCHEDULE_UPDATE_SCHEDULE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offIndschedules : offederIndSch) {
			parameters.add(new BeanPropertySqlParameterSource(offIndschedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (offederIndSch.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String tagScheduleLockEventWl(Long eventId, Long checkSum) {
		SqlParameter[] sqlParameters = new SqlParameter[2];
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CHECK_SUM", OracleTypes.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SCHEDULE").withProcedureName("LOCK_EVENT_WL").declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_ID", eventId);
		inParamMap.put("P_CHECK_SUM", checkSum);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		 simpleJDBCCall.execute(inParameter);

		return "1";
	}

	@Override
	public Integer oidbstrnDuplicateExists(VOffenderAllSchedules schedules) {
		final String sql = getQuery("OIDBSTRN_DUPLICATE_EXISTS");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			String strTime = formatter.format(schedules.getStartTime());
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderIdDisplay", schedules.getOffenderIdDisplay(), "eventStatus",
							schedules.getEventStatus(), "eventClass", schedules.getEventClass(), "eventType",
							schedules.getEventType(), "eventDate", schedules.getEventDate(), "startTime", strTime,
							"eventID", schedules.getEventId()),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception in repo"+e);
			return 0;
		}

	}

	@Override
	public Integer oidbstrnTagScheduleCheckScheduleConflict(VOffenderAllSchedules schedules) {
		final String sql = getQuery("OIDBSTRN_TAG_SCHEDULE_CHECK_SCHEDULE_CONFLICT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					schedules.getOffenderBookId(), "eventDate", schedules.getEventDate()), Integer.class);
		} catch (Exception e) {
			return 0;
		}
	}

}
