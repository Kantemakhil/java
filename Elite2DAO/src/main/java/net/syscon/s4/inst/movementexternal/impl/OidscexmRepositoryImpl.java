package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.OidscexmRepository;
import net.syscon.s4.inst.movementexternal.beans.OffenderNotCompletions;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import oracle.jdbc.OracleTypes;

/**
 * Class OidscexmRepositoryImpl
 */
@Repository
public class OidscexmRepositoryImpl extends RepositoryBase implements OidscexmRepository {

	private final Map<String, FieldMapper> offenderPendNotificationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NOTI_MOVE_SEQ", new FieldMapper("notiMoveSeq")).put("COUNT", new FieldMapper("count"))
			.put("NOTI_SE", new FieldMapper("notiSe")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode ")).build();
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
	private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> offenderNotCompletionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NOTI_MOVE_SEQ", new FieldMapper("notiMoveSeq")).put("COUNT", new FieldMapper("count"))
			.put("NOTI_SE", new FieldMapper("notiSe")).build();
	private final Map<String, FieldMapper> bedAssignmentHistoriesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVEMENT_TIME", new FieldMapper(" movementTime "))
			.put("NVL(MAX(BED_ASSIGN_SEQ)", new FieldMapper(" nvl(max(bedAssignSeq)")).put("0)", new FieldMapper("0) "))
			.put("ROOT_OFFENDER_ID", new FieldMapper(" rootOffenderId "))
			.put("IN_OUT_STATUS", new FieldMapper(" inOutStatus "))
			.put("MOVEMENT_DATE", new FieldMapper(" movementDate")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVEMENT_TIME", new FieldMapper(" movementTime "))
			.put("NVL(MAX(BED_ASSIGN_SEQ)", new FieldMapper(" nvl(max(bedAssignSeq)")).put("0)", new FieldMapper("0) "))
			.put("IN_OUT_STATUS", new FieldMapper(" inOutStatus "))
			.put("MOVEMENT_DATE", new FieldMapper(" movementDate")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> vOiusstriMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEPARTURE_DATE", new FieldMapper("departureDate"))
			.put("ACT_DEPARTURE_TIME", new FieldMapper("actDepartureTime"))
			.put("EST_COMP_TIME", new FieldMapper("estCompTime")).put("ROUTE_NAME", new FieldMapper("routeName"))
			.put("SCHEDULED_TRIP_ID", new FieldMapper("scheduledTripId")).put("STATUS", new FieldMapper("status"))
			.put("EST_DEPARTURE_TIME", new FieldMapper("estDepartureTime"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OidscexmRepositoryImpl class Object
	 */
	public OidscexmRepositoryImpl() {
	}

	private static Logger logger = LogManager.getLogger(OidscexmRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		final String sql1 = getQuery("OIDSCEXM_EXECUTE_QUERY1");
		final String sql2 = getQuery("OIDSCEXM_EXECUTE_QUERY2");

		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		try {
			List<Integer> offenderList = namedParameterJdbcTemplate.queryForList(sql1,
					createParams("fromDate", objSearchDao.getFromDate(), "toDate", objSearchDao.getToDate(), "agyLocId",
							objSearchDao.getCaseLoadId(), "eventType", objSearchDao.getEventType(), "toAgyLocId",
							objSearchDao.getToAgyLocId()),
					Integer.class);
			if (offenderList != null && offenderList.size() > 0) {
				final StringBuffer sqlQuery2 = new StringBuffer();
				sqlQuery2.append(sql2);
				if (objSearchDao.getEventType() != null && !(objSearchDao.getEventType()).equals("")) {
					sqlQuery2.append(" and " + " offsch.event_type = :eventType ");
				}
				if (objSearchDao.getToAgyLocId() != null && !(objSearchDao.getToAgyLocId()).equals("")) {
					sqlQuery2.append(" and "
							+ "( (offsch.agy_loc_id = :toAgyLocId ) OR (offsch.to_agy_loc_id = :toAgyLocId ) ) ");
				}
				String qry2 = sqlQuery2.toString().trim();

				final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper
						.makeMapping(qry2, VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);

				offenderList = offenderList.stream().distinct().collect(Collectors.toList());
				returnList = namedParameterJdbcTemplate.query(qry2,
						createParams("offenderList", offenderList, "fromDate", objSearchDao.getFromDate(), "toDate",
								objSearchDao.getToDate(), "eventType", objSearchDao.getEventType(), "toAgyLocId",
								objSearchDao.getToAgyLocId()),
						VOffenderAllSchedulesRowMapper);
			}

		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * 
	 */
	public Integer offSchUpdateVOffenderAllSchedules(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		final String sql = getQuery("OIDSCEXM_OFFSCH_UPDATE_V_OFFENDER_ALL_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		final String sql = getQuery("OIDSCEXM_FIND_RGMOVETYPE");
		final String mode = "ENTER-QUERY";
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", mode), referenceCodesRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgBuildingRecordGroup() {
		final String sql = getQuery("OIDSCEXM_FIND_RGBUILDING");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), livingUnitsRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgyIdRecordGroup() {
		final String sql = getQuery("OIDSCEXM_FIND_RGAGYID");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), agencyLocationsRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgTierRecordGroup() {
		final String sql = getQuery("OIDSCEXM_FIND_RGTIER");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), livingUnitsRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processExternalMovement
	 *
	 * @param params
	 *
	 */
	public OffenderBookings processExternalMovement(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDSCEXM_PROCESS_EXTERNAL_MOVEMENT_ROOT_OFFENDER_ID");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final OffenderBookings returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("lvOffBookId", paramBean.getOffenderBookId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processExternalMovement
	 *
	 * @param params
	 *
	 */
	public BedAssignmentHistories processExternalMovement(final BedAssignmentHistories paramBean) {
		final String sql = getQuery("OIDSCEXM_PROCESS_EXTERNAL_MOVEMENT");
		final RowMapper<BedAssignmentHistories> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BedAssignmentHistories.class, bedAssignmentHistoriesMapping);
		final BedAssignmentHistories returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNotification
	 *
	 * @param params
	 *
	 */
	public OffenderPendNotifications chkNotification(final OffenderPendNotifications paramBean) {
		final String sql = getQuery("OIDSCEXM_CHK_NOTIFICATION");
		final RowMapper<OffenderPendNotifications> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPendNotifications.class, offenderPendNotificationsMapping);
		final OffenderPendNotifications returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	public OffenderPendNotifications chkNotificationgetpendnotiinfocur(final OffenderPendNotifications paramBean) {
		final String sql = getQuery("OIDSCEXM_CHK_NOTIFICATION");
		OffenderPendNotifications returnObj;
		final RowMapper<OffenderPendNotifications> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPendNotifications.class, offenderPendNotificationsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("V_SCH_ID", paramBean.getScheduleId(), "V_OFF_BOOK_ID", paramBean.getOffenderBookId()),
					columnRowMapper);
		} catch (final Exception e) {
			returnObj = new OffenderPendNotifications();
		}

		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNotification
	 *
	 * @param params
	 *
	 */
	public OffenderNotCompletions chkNotification(final OffenderNotCompletions paramBean) {
		final String sql = getQuery("OIDSCEXM_CHK_NOTIFICATION");
		final RowMapper<OffenderNotCompletions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderNotCompletions.class, offenderNotCompletionsMapping);
		final OffenderNotCompletions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDSCEXM_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOiusstri
	 *
	 * @return List<VOiusstri>
	 *
	 * 
	 */
	public List<VOiusstri> schTripsExecuteQuery(final VOiusstri objSearchDao) {
		final String sql = getQuery("OIUSSTRI_SCHTRIPS_FIND_V_OIUSSTRI");
		final RowMapper<VOiusstri> VOiusstriRowMapper = Row2BeanRowMapper.makeMapping(sql, VOiusstri.class,
				vOiusstriMapping);
		final ArrayList<VOiusstri> returnList = (ArrayList<VOiusstri>) namedParameterJdbcTemplate.query(sql,
				createParams(), VOiusstriRowMapper);
		return returnList;
	}

	public OffenderExternalMovements getLastMovementDateTime(final String offenderBookId) {
		final String sql = getQuery("OIDSCEXM_MOVEMENT_CUR");
		final RowMapper<OffenderExternalMovements> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnList = new OffenderExternalMovements();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					offenderRowMapper);
		} catch (final Exception e) {
			returnList = new OffenderExternalMovements();
		}
		return returnList;
	}

	public Integer prisonActivities(final VOffenderAllSchedules paramBean) {
		final String sql = getQuery("OIDSCEXM_CHECK_WAITLIST_AND_ALLOCATIONS");
		Integer returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_DATE", paramBean.getEventDate(), "P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId()),
				Integer.class);
		return returnList;
	}

	public VOffenderAllSchedules deactivateOffender(final VOffenderAllSchedules paramBean) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_SUB_TYPE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSCEXM").withProcedureName("DEACTIVATE_OFFENDER").declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_EVENT_SUB_TYPE", paramBean.getEventSubType());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setOffenderIdDisplay(returnObject.get("P_OFFENDER_ID_DISPLAY").toString());
		} catch (final Exception e) {
			logger.error("", e);
		}
		return bean;
	}

	public VOffenderAllSchedules updateOffenderInOutStatus(final VOffenderAllSchedules paramBean) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOK_ID", OracleTypes.INTEGER),
				new SqlParameter("P_DIRECTION", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSCEXM").withProcedureName("UPDATE_OFFENDER_IN_OUT_STATUS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_DIRECTION", paramBean.getDirectionCode());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error(" updateOffenderInOutStatus: ", e);
		}
		return bean;
	}

	public VOffenderAllSchedules insertReturnSchedule(final VOffenderAllSchedules paramBean) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOK_ID", OracleTypes.INTEGER),
				new SqlParameter("P_TO_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVEMENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVEMENT_REASON_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_ESCORT_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_RETURN_DATE", OracleTypes.DATE),
				new SqlParameter("P_RETURN_TIME", OracleTypes.VARCHAR),
				new SqlParameter("P_TA_STATUS", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_ID", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSCEXM").withProcedureName("INSERT_RETURN_SCHEDULE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_MOVEMENT_TYPE", paramBean.getEventType());
		inParamMap.put("P_TO_AGY_LOC_ID", paramBean.getAgyLocId());
		inParamMap.put("P_MOVEMENT_REASON_CODE", paramBean.getEventSubType());
		inParamMap.put("P_ESCORT_CODE", paramBean.getEscortCode());
		inParamMap.put("P_RETURN_DATE", paramBean.getReturnDate());
		inParamMap.put("P_RETURN_TIME", paramBean.getReturnTime());
		inParamMap.put("P_TA_STATUS", paramBean.getEventStatus());
		inParamMap.put("P_EVENT_ID", paramBean.getEventId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("insertReturnSchedule: ", e);
		}
		return bean;
	}

	public String getLastToAgyLocId(final Integer offBookId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_LAST_TO_AGY_LOC_ID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public String getLastToCity(final Integer offBookId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_LAST_TO_CITY").declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public String getToAddress(final Integer offBookId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_TO_ADDRESS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public VOffenderAllSchedules updateOffExtMvnts(final VOffenderAllSchedules paramBean) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOK_ID", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSCEXM").withProcedureName("UPDATE_OFF_EXT_MVNTS").declareParameters(sqlParameters);
		inParamMap.put("P_BOOK_ID", paramBean.getOffenderBookId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("", e);
		}
		return bean;
	}

	public VOffenderAllSchedules insertExternalMovement(final Integer offenderBookId, final Integer eventId,
			final String fromAgyLocId, final String lvFromCity, final Integer fromAddress, final String toAgyLocId,
			final Integer lvToAddressId, final String toCityCode, final String eventType, final String eventSubType,
			final String escortCode, final String directionCode, final String commentText, final Date fMtime) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_FROM_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_FROM_CITY", OracleTypes.VARCHAR),
				new SqlParameter("P_FROM_ADDRESS_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TO_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_TO_ADDRESS_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TO_CITY", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVEMENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVEMENT_REASON_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_ESCORT_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_DIRECTION_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_COMMENT_TEXT", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_TIME", OracleTypes.DATE),
				new SqlParameter("P_PROPOSED_MVMNT_SEQ", OracleTypes.NUMBER), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSCEXM").withProcedureName("INSERT_EXTERNAL_MOVEMENT")
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOK_ID", offenderBookId);
		inParamMap.put("P_EVENT_ID", eventId);
		inParamMap.put("P_FROM_AGY_LOC_ID", fromAgyLocId);
		inParamMap.put("P_FROM_CITY", lvFromCity);
		inParamMap.put("P_FROM_ADDRESS_ID", fromAddress);
		inParamMap.put("P_TO_AGY_LOC_ID", toAgyLocId);
		inParamMap.put("P_TO_ADDRESS_ID", lvToAddressId);
		inParamMap.put("P_TO_CITY", toCityCode);
		inParamMap.put("P_MOVEMENT_TYPE", eventType);
		inParamMap.put("P_MOVEMENT_REASON_CODE", eventSubType);
		inParamMap.put("P_ESCORT_CODE", escortCode);
		inParamMap.put("P_DIRECTION_CODE", directionCode);
		inParamMap.put("P_COMMENT_TEXT", commentText);
		inParamMap.put("P_EVENT_TIME", fMtime);
		inParamMap.put("P_PROPOSED_MVMNT_SEQ", 0);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("", e);
		}
		return bean;
	}

	public VOffenderAllSchedules updateCrtEventStatus(final Integer eventId) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSCEXM").withProcedureName("UPDATE_CRT_EVENT_STATUS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_ID", eventId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("", e);
		}
		return bean;
	}

	public VOffenderAllSchedules updateOffSchStatus(final Integer eventId) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSCEXM").withProcedureName("UPDATE_OFF_SCH_STATUS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_ID", eventId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("", e);
		}
		return bean;
	}

	public VOffenderAllSchedules updateWorkAsgnStatuses(final Integer offId, final String caseLoadId, final Date movementDate) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVEMENT_DATE", OracleTypes.DATE) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PAYROLL").withProcedureName("UPDATE_WORK_ASGN_STATUSES")
				.declareParameters(sqlParameters);
		inParamMap.put("OFF_ID", offId);
		inParamMap.put("CSLD_ID", caseLoadId);
		inParamMap.put("P_MOVEMENT_DATE", movementDate);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("updateWorkAsgnStatuses: ", e);
		}
		return bean;
	}

	public Integer statusRecords(final VOffenderAllSchedules paramBean) {
		final String sql = getQuery("OIDSCEXM_STATUSRECORDS");
		Integer returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", paramBean.getEventId()),
				Integer.class);
		return returnList;
	}

	public Integer chkNotificationgetcountcur(final OffenderPendNotifications paramBean) {
		final String sql = getQuery("OIDSCEXM_GET_COUNT_CUR");
		Integer returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("vOffBookId", paramBean.getOffenderBookId(), "vNotiS", paramBean.getNotiSeq(),
						"vNotiMoveMs", paramBean.getNotiMoveSeq()),
				Integer.class);
		return returnList;
	}

	public String getToAgyLocId(final String toAgyLocDesc) {
		final String sql = getQuery("OIDSCEXM_GETTOAGYLOCID");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("toAgyLocDesc", toAgyLocDesc),
				String.class);
		return returnList;
	}

	public OffenderExternalMovements suspendAllocations(final VOffenderAllSchedules paramBean) {
		OffenderExternalMovements bean = new OffenderExternalMovements();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_DATE", OracleTypes.DATE) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withProcedureName("SUSPEND_ALLOCATIONS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_DATE", paramBean.getEventDate());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("suspendAllocations: ", e);
			bean = new OffenderExternalMovements();
		}
		return bean;
	}

	public OffenderExternalMovements endWaitingListAllocations(final VOffenderAllSchedules paramBean) {
		OffenderExternalMovements bean = new OffenderExternalMovements();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_DATE", OracleTypes.DATE), new SqlParameter("P_END_REASON", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withProcedureName("END_WAITLIST_AND_ALLOCATIONS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_DATE", paramBean.getEventDate());
		inParamMap.put("P_END_REASON", "TRF");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("endWaitingListAllocations: ", e);
			bean = new OffenderExternalMovements();
		}
		return bean;
	}

	@Override
	public List<VOffenderAllSchedules> getoffenderExtMovements(final VOffenderAllSchedules objSearchDao) {

		final String sql = getQuery("OIDSCEXM_EXECUTE_QUERY");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			sqlQuery.append(
					" (EXISTS (SELECT 'X' FROM caseload_agency_locations cal, offender_bookings ob WHERE cal.agy_loc_id = ob.agy_loc_id "
							+ " AND offsch.direction_code != ob.in_out_status AND ob.offender_book_id = offsch.offender_book_id "
							+ " AND ob.active_flag = 'Y' AND cal.caseload_id = :caseLoadId) AND offsch.offender_book_id=:offenderBookId"
							+ " AND offsch.event_class = 'EXT_MOV'AND  offsch.event_status = 'SCH' AND offsch.active_flag = 'Y') "
							+ " and ");
			valuesList.addValue("caseLoadId", objSearchDao.getCaseLoadId());
			valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by  offsch.OFFENDER_LAST_NAME ";
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VOffenderAllSchedulesRowMapper);
		return returnList;

	}

	@Override
	public List<VOffenderAllSchedules> getOldRecOffenderIndSchedules(final BigDecimal eventId) {
		final String sql = getQuery("GET_OLD_REC_OF_V_OFFENDER_ALL_SCHEDULES_2");
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		List<VOffenderAllSchedules> obj = new ArrayList<VOffenderAllSchedules>();
		obj = namedParameterJdbcTemplate.query(sql, createParams("EVENT_ID", eventId), VOffenderAllSchedulesRowMapper);
		return obj;
	}
	
	@Override
	public String getExternalAddress(BigDecimal toAddressId) {
		final String sql = getQuery("OIDSCEXM_GET_CORP_ADDRESS");
		String addressDetail = null;
		try {
			addressDetail= namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", toAddressId), String.class);
			
		} catch (Exception e) {
			logger.error("getExternalAddress: ", e);
		}
		return addressDetail;
		
	}
	
}
