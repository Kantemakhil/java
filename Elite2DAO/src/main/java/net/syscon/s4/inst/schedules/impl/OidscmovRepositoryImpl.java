package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.OidscmovRepository;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import oracle.jdbc.OracleTypes;

/**
 * Class OidscmovRepositoryImpl
 */
@Repository
public class OidscmovRepositoryImpl extends RepositoryBase implements OidscmovRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidscmovRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> courtEvMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("HOLD_FLAG", new FieldMapper("holdFlag"))
			.put("PARENT_EVENT_ID", new FieldMapper("parentEventId"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("RESULT_CODE", new FieldMapper("resultCode"))
			.put("SCHEDULED_TRIP_ID", new FieldMapper("scheduledTripId"))
			.put("COURT_EVENT_TYPE", new FieldMapper("courtEventType")).put("JUDGE_NAME", new FieldMapper("judgeName"))
			.put("START_TIME", new FieldMapper("startTime"))
			.put("NEXT_EVENT_START_TIME", new FieldMapper("nextEventStartTime"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("OFFENDER_PROCEEDING_ID", new FieldMapper("offenderProceedingId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("NEXT_EVENT_REQUEST_FLAG", new FieldMapper("nextEventRequestFlag"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome")).put("CASE_ID", new FieldMapper("caseId"))
			.put("EVENT_ID", new FieldMapper("eventId")).put("OUTCOME_DATE", new FieldMapper("outcomeDate"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("ORDER_REQUESTED_FLAG", new FieldMapper("orderRequestedFlag"))
			.put("END_TIME", new FieldMapper("endTime")).put("NEXT_EVENT_DATE", new FieldMapper("nextEventDate"))
			.put("APPEARANCE_TYPE", new FieldMapper("appearanceType"))
			.put("APPEARANCE_LOCATION", new FieldMapper("appearanceLocation")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> mMappingOffender = new ImmutableMap.Builder<String, FieldMapper>()
			.put("startTime", new FieldMapper("createDateTime")).put("agyLocId", new FieldMapper("identifier"))

			.build();

	/**
	 * Creates new OidscmovRepositoryImpl class Object
	 */
	public OidscmovRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourtEvents
	 *
	 * @return List<CourtEvents>
	 *
	 *
	 */

	public List<CourtEvents> crtEveExecuteQuery(final CourtEvents objSearchDao) {
		final String sql = getQuery("OIDSCMOV_CRTEVE_FIND_COURT_EVENTS");
		final RowMapper<CourtEvents> courtEvRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtEvents.class,
				courtEvMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getCreateUserId() != null) {
				valuesList.addValue("userId", objSearchDao.getCreateUserId());
			}
			if (objSearchDao.getEventDate() != null) {
				sqlQuery.append("EVENT_DATE = to_date(:eventDate::text,'DD/MM/YYYY')  " + " and ");
				valuesList.addValue("eventDate", sdf.format(objSearchDao.getEventDate()));
			}
			if (objSearchDao.getStartTime() != null) {
				sqlQuery.append("  TO_CHAR(START_TIME,'HH24:MI') =:startTime " + " and ");
				final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				final String strTime = formatter.format(objSearchDao.getStartTime());
				valuesList.addValue("startTime", strTime);
			}
			if (objSearchDao.getCourtAgyLocId() != null) {
				sqlQuery.append("AGY_LOC_ID =  :agyLocId " + " and ");
				valuesList.addValue("agyLocId", objSearchDao.getCourtAgyLocId());
			}
			if (objSearchDao.getMovementReasonCode() != null) {
				sqlQuery.append("COURT_EVENT_TYPE =  :courtEventType " + " and ");
				valuesList.addValue("courtEventType", objSearchDao.getMovementReasonCode());
			}
			if (objSearchDao.getJudgeName() != null) {
				sqlQuery.append("JUDGE_NAME =  :judgeName " + " and ");
				valuesList.addValue("judgeName", objSearchDao.getJudgeName());
			}
			
			sqlQuery.append("DIRECTION_CODE =  :directionCode " + " and ");
			valuesList.addValue("directionCode", "OUT");

			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(
						" OFFENDER_BOOK_ID IN (select offender_book_id  from offender_bookings ob where agy_loc_id =:vAgyLocId) "
								+ " and ");
				valuesList.addValue("vAgyLocId", objSearchDao.getAgyLocId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat("  ORDER BY AGY_LOC_ID , START_TIME ");
		List<CourtEvents> returnList = new ArrayList<CourtEvents>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, courtEvRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCourtEvents List<CourtEvents>
	 *
	 * @return List<Integer>
	 *
	 *
	 */
	public Integer crtEveInsertCourtEvents(final List<CourtEvents> lstCourtEvents) {
		int returnValue = 0;
		final String sql = getQuery("OIDSCMOV_CRTEVE_INSERT_COURT_EVENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents courtEvents : lstCourtEvents) {
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstCourtEvents.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (DataAccessException e) {
			logger.error("crtEveInsertCourtEvents" + e);
		}
		return returnValue;
	}

	@Override
	public BigDecimal crtEveInsertCourtEventsPreEventId(final CourtEvents courtEvents) {
		BigDecimal eventId = crtEvePreInsert();
		courtEvents.setEventId(eventId.intValue());
		final String sql = getQuery("OIDSCMOV_CRTEVE_INSERT_COURT_EVENTS_PRE_EVENT_ID");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		 namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		return eventId;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourtEvents List<CourtEvents>
	 *
	 *
	 */
	public Integer crtEveUpdateCourtEvents(final List<CourtEvents> lstCourtEvents) {
		final String sql = getQuery("OIDSCMOV_CRTEVE_UPDATE_COURT_EVENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents courtEvents : lstCourtEvents) {
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourtEvents.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourtEvents List<CourtEvents>
	 *
	 *
	 */
	public Integer crtEveDeleteCourtEvents(final List<CourtEvents> lstCourtEvents) {
		final String sql = getQuery("OIDSCMOV_CRTEVE_DELETE_COURT_EVENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents courtEvents : lstCourtEvents) {
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			String tableName = "COURT_EVENTS";
			String whereCondition = "EVENT_ID = :eventId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourtEvents.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListAgencyLocationsM>
	 */
	public List<AgencyLocations> rgCtrlInstRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDSCMOV_FIND_RGCTRLINST");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		final List<AgencyLocations> lstAgyLoc = new ArrayList<AgencyLocations>();
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), mRowMapper);
			for (final AgencyLocations objAgencyLoc : returnList) {
				objAgencyLoc.setCode(objAgencyLoc.getAgyLocId());
				lstAgyLoc.add(objAgencyLoc);
			}
		} catch (Exception e) {
			logger.error("In rgCtrlInstRecordGroup method : ", e);
		}
		return lstAgyLoc;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<MovementReasons> rgCtrlReasonRecordGroup() {
		final String sql = getQuery("OIDSCMOV_FIND_RGCTRLREASON");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				mMapping);
		final List<MovementReasons> lstMovReasons = new ArrayList<MovementReasons>();
		List<MovementReasons> returnList = new ArrayList<MovementReasons>();
		try {
			returnList = (List<MovementReasons>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
			for (final MovementReasons objMovReason : returnList) {
				objMovReason.setCode(objMovReason.getMovementReasonCode());
				lstMovReasons.add(objMovReason);
			}
		} catch (Exception e) {
			logger.error("In rgCtrlReasonRecordGroup method : ", e);
		}
		return lstMovReasons;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocationsM>
	 */
	public List<AgencyLocations> rgCtrlCourtRecordGroup() {
		final String sql = getQuery("OIDSCMOV_FIND_RGCTRLCOURT");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		final List<AgencyLocations> lstAgyLoc = new ArrayList<AgencyLocations>();
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
			for (final AgencyLocations objAgencyLoc : returnList) {
				objAgencyLoc.setCode(objAgencyLoc.getAgyLocId());
				lstAgyLoc.add(objAgencyLoc);
			}
		} catch (Exception e) {
			logger.error("In rgCtrlCourtRecordGroup method : ", e);
		}
		return lstAgyLoc;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<MovementReasons> rgCourtReaRecordGroup() {
		final String sql = getQuery("OIDSCMOV_FIND_RGCOURTREA");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				mMapping);
		List<MovementReasons> lstMovReasons = new ArrayList<MovementReasons>();
		try {
			lstMovReasons = (List<MovementReasons>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In rgCourtReaRecordGroup method : ", e);
		}
		return lstMovReasons;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * crtEvePreInsert
	 *
	 * @param params
	 *
	 */
	public BigDecimal crtEvePreInsert() {
		final String sql = getQuery("OIDSCMOV_CRT_EVE_PREINSERT_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	public Date getCurrentDate() {
		final String sql = getQuery("OIDSCMOV_GET_CURRENT_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);

	}

	/**
	 * Retrieves offender details for the given offender display Id
	 * 
	 * @param objSearchDao
	 * @return List<VNameSearch>
	 * 
	 */
	public List<VNameSearch> nameSrchExecuteoffenderQuery(final VNameSearch objSearchDao) {
		final List<VNameSearch> lListObj = new ArrayList<>();
		final VNameSearch bean = new VNameSearch();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OSINAMES").withProcedureName("GET_OFF_DETAILS_BY_BOOK_ID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
		inParamMap.put("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR);
		inParamMap.put("P_LAST_NAME", OracleTypes.VARCHAR);
		inParamMap.put("P_FIRST_NAME", OracleTypes.VARCHAR);
		inParamMap.put("P_AGY_LOC_ID", OracleTypes.VARCHAR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject != null) {
				bean.setOffenderIdDisplay(String.valueOf(returnObject.get("P_OFFENDER_ID_DISPLAY")));
				bean.setFirstName(String.valueOf(returnObject.get("P_FIRST_NAME")));
				bean.setLastName(String.valueOf(returnObject.get("P_LAST_NAME")));
				bean.setAgyLocId(String.valueOf(returnObject.get("P_AGY_LOC_ID")));
				lListObj.add(bean);
			}

		} catch (Exception e) {
			logger.error(" In nameSrchExecuteoffenderQuery method :" + e);
		}
		return lListObj;
	}

	/**
	 * Retrieves offender details for the given offender display Id , global
	 * caseload id & agyloc id
	 * 
	 * @param nbtOffDisplayId
	 * @param agyLocId
	 * @param caseloadId
	 * @return List<CourtEvents>
	 * 
	 */
	public List<CourtEvents> getOffenderDetails(final String nbtOffDisplayId, final String agyLocId,
			final String caseloadId) {

		final List<CourtEvents> lstCourt = new ArrayList<>();
		Map<String, Object> returnObject = null;
		String agLocId = null;
		if (agyLocId != null && !agyLocId.equals("undefined")) {
			agLocId = agyLocId;
		}

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OSINAMES").withProcedureName("GET_OFFENDER_DETAILS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER);
		inParamMap.put("P_OFFENDER_ID_DISPLAY", nbtOffDisplayId);
		inParamMap.put("P_LAST_NAME", OracleTypes.VARCHAR);
		inParamMap.put("P_FIRST_NAME", OracleTypes.VARCHAR);
		inParamMap.put("P_AGY_LOC_ID", agLocId);
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject != null && String.valueOf(returnObject.get("P_AGY_LOC_ID")) != null) {
				final CourtEvents bean = new CourtEvents();
				bean.setNbtOffenderIdDisplay(nbtOffDisplayId);
				bean.setNbtFirstName(String.valueOf(returnObject.get("P_FIRST_NAME")));
				bean.setNbtLastName(String.valueOf(returnObject.get("P_LAST_NAME")));
				bean.setNbtInst(String.valueOf(returnObject.get("P_AGY_LOC_ID")));
				bean.setOffenderBookId(Integer.valueOf(String.valueOf(returnObject.get("P_OFFENDER_BOOK_ID"))));
				lstCourt.add(bean);
			}
		} catch (Exception e) {
			logger.error(" In getOffenderDetails method :" + e);
		}
		return lstCourt;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @param searchRecord
	 * @return Integer
	 */
	public Integer checkScheduleConflict(final CourtEvents objCourts) {

		final String sql = getQuery("OIDSCMOV_CHECK_SCHEDULE_CONFLICT");
		Integer returnList = 0;
		if (objCourts.getEventDate() != null && objCourts.getOffenderBookId() > 0) {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
					objCourts.getOffenderBookId(), "EVENT_DATE", objCourts.getEventDate()), Integer.class);
			if (returnList != null && returnList > 0) {
				returnList = objCourts.getOffenderBookId();
			}
		}
		return returnList;
	}

	@Override
	public List<OffenderBookings> getNaCur(final Integer offenderBookId) {
		final String sql = getQuery("OIDSCMOV_CHK_NA_CONFLICT_NA_CUR");
		final RowMapper<OffenderBookings> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				mMapping);
		List<OffenderBookings> lstOffBookId = new ArrayList<OffenderBookings>();
		try {
			lstOffBookId = namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", offenderBookId),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In rgCourtReaRecordGroup method : ", e);
		}
		return lstOffBookId;
	}

	@Override
	public Integer getLvNaCount(final Integer offenderBookId) {
		final String sql = getQuery("OIDSCMOV_CHK_NA_CONFLICT_LV_NA_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offenderBookId),
				Integer.class);
	}

	@Override
	public Integer getLvNaCountCur(final Long offenderBookId, final String agyLocId, final Date eventDate) {
		final String sql = getQuery("OIDSCMOV_CHK_NA_CONFLICT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_na_off_book_id", offenderBookId,
				"p_court_agy_loc_id", agyLocId, "p_event_date", eventDate), Integer.class);
	}

	@Override
	public List<Offenders> getExternalIndNonAssocationForINP(Long offenderBookId, String agyLocId, Date eventDate) {
		final String sql = getQuery("OIDSCMOV_EXTERNAL_IND_NON_ASSOCATION_FOR_INP");
		List<Offenders> returnList = new ArrayList<Offenders>();
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mMappingOffender);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId, "agyLocId", agyLocId, "eventDate", eventDate),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In getExternalIndNonAssocationForINP method : ", e);
			returnList = Collections.emptyList();
		}

		return returnList;
	}

	@Override
	public List<Offenders> getExternalIndNonAssocationForVIDOrOME(Long offenderBookId, String appearanceLocation,
			Date eventDate, String caseLoad) {
		final String sql = getQuery("OIDSCMOV_EXTERNAL_IND_NON_ASSOCATION_FOR_VID_OR_OME");
		List<Offenders> returnList = new ArrayList<Offenders>();
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mMappingOffender);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId,
					"appearanceLocation", appearanceLocation, "eventDate", eventDate, "caseLoad", caseLoad),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In getExternalIndNonAssocationForVIDOrOME method : ", e);
			returnList = Collections.emptyList();
		}

		return returnList;

	}

	@Override
	public List<Integer> getInternalIndNonAssocationForINPOrVIDOrOME(Long offenderBookId, List<Integer> internalList) {
		final String sql = getQuery("OIDSCMOV_INTERNAL_IND_NON_ASSOCATION_FOR_INP_OR_VID_OME");
		List<Integer> returnList = new ArrayList<Integer>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("offenderBookId", offenderBookId, "internalList", internalList), Integer.class);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error("In getInternalIndNonAssocationForINPOrVIDOrOME method : ", e);

		}
		return returnList;

	}

	@Override
	public List<Offenders> getExternalGangNonAssocationForINP(Long offenderBookId, String agyLocId, Date eventDate) {
		final String sql = getQuery("OIDSCMOV_EXTERNAL_GANG_NON_ASSOCATION_FOR_INP");
		List<Offenders> returnList = new ArrayList<Offenders>();
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mMappingOffender);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId, "agyLocId", agyLocId, "eventDate", eventDate),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In getExternalGangNonAssocationForINP method : ", e);
			returnList = Collections.emptyList();
		}

		return returnList;
	}

	@Override
	public List<Offenders> getExternalGangNonAssocationForVIDOrOME(Long offenderBookId, String appearanceLocation,
			Date eventDate,String caseLoad) {
		final String sql = getQuery("OIDSCMOV_EXTERNAL_GANG_NON_ASSOCATION_FOR_VID_OME");
		List<Offenders> returnList = new ArrayList<Offenders>();
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mMappingOffender);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId,
					"appearanceLocation", appearanceLocation, "eventDate", eventDate,"caseLoad",caseLoad), mRowMapper);
		} catch (Exception e) {
			logger.error("In getExternalIndNonAssocationForVIDOrOME method : ", e);
			returnList = Collections.emptyList();
		}

		return returnList;
	}


	@Override
	public List<Integer> getInternalGangNonAssocationForINPOrVIDOrOME(Long offenderBookId, List<Integer> internalList) {
		final String sql = getQuery("OIDSCMOV_INTERNAL_GANG_NON_ASSOCATION_FOR_INP_OR_VID_OME");
		List<Integer> returnList = new ArrayList<Integer>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("offenderBookId", offenderBookId, "inernalList", internalList), Integer.class);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error("In getInternalIndNonAssocationForINPOrVIDOrOME method : ", e);

		}
		return returnList;
	}

	@Override
	public List<Integer> getInternalIndNonAssocationForINPOrVIDOrOMELocation(CourtEvents courtEvents,
			List<Integer> internalList) {
		final String sql = getQuery("OIDSCMOV_INTERNAL_IND_NON_ASSOCATION_FOR_INP_OR_VID_OME_LOCATION");
		List<Integer> returnList = new ArrayList<Integer>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("offenderBookId", courtEvents.getOffenderBookId(), "internalList", internalList,
							"appearanceLocation", courtEvents.getAppearanceLocation(), "caseLoad",
							courtEvents.getCaseLoad()),
					Integer.class);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error("In getInternalIndNonAssocationForINPOrVIDOrOME method : ", e);

		}

		return returnList;
	}

}
