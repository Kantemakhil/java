package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movementexternal.beans.OffenderNotCompletions;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.schedules.OidstojuRepository;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Class OidstojuRepositoryImpl
 */
@Repository
public class OidstojuRepositoryImpl extends RepositoryBase implements OidstojuRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstojuRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offIndSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();
	private final Map<String, FieldMapper> vOffAllSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
			.put("CODE", new FieldMapper("code")).build();

	/**
	 * Creates new OidstojuRepositoryImpl class Object
	 */
	public OidstojuRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OIDSTOJU_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES");
		final RowMapper<VOffenderAllSchedules> vOffAllSchRM = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffAllSchMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append(
						"OFFENDER_BOOK_ID =  :offenderBookId and EVENT_TYPE  = 'TRN' and EVENT_SUB_TYPE = 'OJ'  "
								+ " ORDER BY EVENT_DATE DESC , START_TIME DESC");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		return namedParameterJdbcTemplate.query(preparedSql, valuesList, vOffAllSchRM);
	}

	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffSch List<OffenderIndSchedules>
	 * @return Integer
	 *
	 * 
	 */
	public Integer offSchInsertVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffSch) {
		int returnValue = 0;
		final String sql = getQuery("OIDSTOJU_SCH_INSERT_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules vOffAllSchedules : lstOffSch) {
			parameters.add(new BeanPropertySqlParameterSource(vOffAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffSch List<OffenderIndSchedules>>
	 *
	 * 
	 */
	public Integer offSchUpdateVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffSch) {
		final String sql = getQuery("OIDSTOJU_SCH_UPDATE_V_OFFENDER_ALL_SCHEDULES_2");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules vOffAllSchedules : lstOffSch) {
			parameters.add(new BeanPropertySqlParameterSource(vOffAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffSch List<OffenderIndSchedules>
	 *
	 * 
	 */
	public Integer offSchDeleteVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffSch) {
		final String sql = getQuery("OIDSTOJU_SCH_DELETE_V_OFFENDER_ALL_SCHEDULES_2");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules vOffAllSchedules : lstOffSch) {
			parameters.add(new BeanPropertySqlParameterSource(vOffAllSchedules));
		}
		try {
			String tableName = "V_OFFENDER_ALL_SCHEDULES_2";
			String whereCondition = "EVENT_ID =:eventId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgLocationRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDSTOJU_FIND_RGLOCATION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", mode),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In rgLocationRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDSTOJU_FIND_RGESCORT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", mode),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In rgEscortRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderIndSchedules> offBkgOnCheckDeleteMaster(final OffenderIndSchedules paramBean) {
		final String sql = getQuery("OIDSTOJU_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderIndSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderIndSchedules.class, offIndSchMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * gets the current date
	 */
	public Date getCurrentDate() {
		final String sql = getQuery("OIDSTOJU_GET_CURRENT_DATE");
		final Date value = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
		return value;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * crtEvePreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offSchPreInsert() {
		final String sql = getQuery("OIDSTOJU_OFF_SCH_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @Param offIndSch
	 */
	public Integer offSchCheckScheduleConflict(final VOffenderAllSchedules offSch) {
		final String sql = getQuery("OIDSTOJU_CHECK_SCHEDULE_CONFLICT");
		Integer returnList;
		BigDecimal eventId = null;
		if (offSch.getEventId() == null) {
			eventId = new BigDecimal(0);
		} else {
			eventId = offSch.getEventId();
		}
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
				offSch.getOffenderBookId(), "EVENT_ID", eventId, "EVENT_DATE", offSch.getEventDate()), Integer.class);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgEventTypeSubTypeGroup() {
		final String sql = getQuery("OIDSTOJU_EVENTTYPE_EVENTSUBTYPE_CODES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In rgEventTypeSubTypeGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchInsNotification
	 *
	 */
	public Integer offSchInsNotification(final OffenderIndSchedules offSch) {
		Boolean vProceedFlag = true;
		Integer vCount = 0;
		Integer getOffPCntCur = 0;
		Integer getNotRecCur = 0;
		Integer getNextMoveSeqCur = 0;
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		String sql = getQuery("OIDSTOJU_INS_NOTIFICATION_1");
		final MovementReasons notReqCur = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("eventSubType", offSch.getEventSubType()), MovementReasons.class);

		if (notReqCur != null && notReqCur.getNotificationFlag() != null
				&& notReqCur.getNotificationFlag().equals("Y")) {

			if (notReqCur.getNotificationType() != null && notReqCur.getNotificationType().equals("Y")) {
				sql = getQuery("OIDSTOJU_INS_NOTIFICATION_2");
				getOffPCntCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
						offSch.getOffenderBookId(), "eventSubType", offSch.getEventSubType()), Integer.class);

				if (getOffPCntCur != null && getOffPCntCur > 0) {
					vProceedFlag = false;
				}
			} else {
				vCount = 0;
			}

			if (vProceedFlag) {
				sql = getQuery("OIDSTOJU_INS_NOTIFICATION_3");
				getNotRecCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
						offSch.getOffenderBookId(), "eventSubType", offSch.getEventSubType()), Integer.class);
				vCount = getNotRecCur;

				if (vCount != null && vCount > 0) {
					sql = getQuery("OIDSTOJU_INS_NOTIFICATION_4");

					final OffenderNotCompletions getRelNotRecCur = namedParameterJdbcTemplate.queryForObject(sql,
							createParams("offenderBookId", offSch.getOffenderBookId(), "eventSubType",
									offSch.getEventSubType()),
							OffenderNotCompletions.class);

					if (getRelNotRecCur != null) {
						sql = getQuery("OIDSTOJU_INS_NOTIFICATION_5");
						getNextMoveSeqCur = namedParameterJdbcTemplate.queryForObject(sql,
								createParams("offenderBookId", offSch.getOffenderBookId(), "vNotiSeq",
										getRelNotRecCur.getNotiSeq()),
								Integer.class);

						parameters = new ArrayList<SqlParameterSource>();
						long notiMSeq = 0;
						if (getNextMoveSeqCur != null && getNextMoveSeqCur > 0) {
							sql = getQuery("OIDSTOJU_INS_NOTIFICATION_6");
							notiMSeq = getNextMoveSeqCur.longValue() + 1;
							final OffenderPendNotifications offPenNot = new OffenderPendNotifications();
							offPenNot.setNotiSeq(getRelNotRecCur.getNotiSeq());
							offPenNot.setNotiMoveSeq(notiMSeq);
							offPenNot.setOffenderBookId(offSch.getOffenderBookId());
							offPenNot.setMovementType("TRN");
							offPenNot.setMovementReasonCode(offSch.getEventSubType());
							offPenNot.setMovementDate(offSch.getEventDate());
							offPenNot.setMoveScheduleFlag("S");
							if (offSch.getEventId() != null) {
								offPenNot.setScheduleId(new BigDecimal(offSch.getEventId().toString()));
							}
							parameters.add(new BeanPropertySqlParameterSource(offPenNot));
							namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

							parameters = new ArrayList<SqlParameterSource>();
							sql = getQuery("OIDSTOJU_INS_NOTIFICATION_7");
							final OffenderNotCompletions offNotComUpdate = new OffenderNotCompletions();
							offNotComUpdate.setNotiSeq(getRelNotRecCur.getNotiSeq());
							offNotComUpdate.setNotiMoveSeq(notiMSeq);
							offNotComUpdate.setOffenderBookId(offSch.getOffenderBookId());
							offNotComUpdate.setStatus("OPEN");
							offNotComUpdate.setNotiAgencyPartyCode(getRelNotRecCur.getNotiAgencyPartyCode());
							offNotComUpdate.setNotiCorpId(getRelNotRecCur.getNotiCorpId());
							offNotComUpdate.setNotiPersonId(getRelNotRecCur.getNotiPersonId());
							parameters.add(new BeanPropertySqlParameterSource(offNotComUpdate));
							namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
						}
					}
				}
			}

		} else {
			vCount = 0;

		}

		return vCount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchUpdNotification
	 *
	 *
	 */
	public Integer offSchUpdNotification(final OffenderIndSchedules offSch) {

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		Integer chNotCur = 0;
		String sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_1");
		 namedParameterJdbcTemplate.queryForObject(sql,
				createParams("eventId", offSch.getEventId(), "vMvtDate",
						"to_date(' " + offSch.getEventDate() + " ','DD-MON-YYYY HH24:MI:SS') ", "eventSubType",
						offSch.getEventSubType()),
				String.class);

		sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_2");
		final OffenderPendNotifications notiExisCur = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("eventId", offSch.getEventId()), OffenderPendNotifications.class);

		if (notiExisCur != null && notiExisCur.getNotiSeq() > 0) {
			parameters = new ArrayList<SqlParameterSource>();
			sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_7");
			final OffenderNotCompletions offNotCom = new OffenderNotCompletions();
			offNotCom.setNotiSeq(notiExisCur.getNotiSeq());
			offNotCom.setNotiMoveSeq(notiExisCur.getNotiMoveSeq());
			offNotCom.setOffenderBookId(offSch.getOffenderBookId());
			parameters.add(new BeanPropertySqlParameterSource(offNotCom));
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

			sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_3");
			chNotCur = namedParameterJdbcTemplate
					.queryForObject(
							sql, createParams("vNotSeq", notiExisCur.getNotiSeq(), "vMNotSeq",
									notiExisCur.getNotiMoveSeq(), "offenderBookId", offSch.getOffenderBookId()),
							Integer.class);

			if (chNotCur != null && chNotCur > 0) {
				parameters = new ArrayList<SqlParameterSource>();
				sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_8");
				final OffenderNotCompletions offNotComUpdate = new OffenderNotCompletions();
				offNotComUpdate.setNotiSeq(notiExisCur.getNotiSeq());
				offNotComUpdate.setNotiMoveSeq(notiExisCur.getNotiMoveSeq());
				parameters.add(new BeanPropertySqlParameterSource(offNotComUpdate));
				namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			}
		}

		sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_4");
		namedParameterJdbcTemplate.queryForObject(sql,
				createParams("eventSubType", offSch.getEventSubType()), MovementReasons.class);

		sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_5");
		 namedParameterJdbcTemplate.queryForObject(sql, createParams("vNotSeq", 0,
				"vMNotSeq", 0, "offenderBookId", offSch.getOffenderBookId(), "eventSubType", offSch.getEventSubType()),
				Integer.class);

		sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_6");
		 namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offSch.getOffenderBookId(), "eventSubType", offSch.getEventSubType()),
				Integer.class);
		return chNotCur;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchUpdNotification
	 *
	 *
	 */
	public Boolean offSchDelNotification(final OffenderIndSchedules offSch) {
		int[] returnArray = new int[] {};
		Boolean blDel = false;
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		String sql = getQuery("OIDSTOJU_DEL_NOTIFICATION_1");

		final OffenderPendNotifications notiExisCur = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("eventId", offSch.getEventId(), "vMvtDate",
						"to_date(' " + offSch.getEventDate() + " ','DD-MON-YYYY HH24:MI:SS') ", "eventSubType",
						offSch.getEventSubType()),
				OffenderPendNotifications.class);
		if (notiExisCur != null) {
			parameters = new ArrayList<SqlParameterSource>();
			sql = getQuery("OIDSTOJU_UPD_NOTIFICATION_7");
			final OffenderNotCompletions offNotCom = new OffenderNotCompletions();
			offNotCom.setNotiSeq(notiExisCur.getNotiSeq());
			offNotCom.setNotiMoveSeq(notiExisCur.getNotiMoveSeq());
			offNotCom.setOffenderBookId(offSch.getOffenderBookId());
			parameters.add(new BeanPropertySqlParameterSource(offNotCom));
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}
		if (returnArray.length > 0) {
			parameters = new ArrayList<SqlParameterSource>();
			sql = getQuery("OIDSTOJU_DEL_NOTIFICATION_2");
			final OffenderNotCompletions offNotComUpdate = new OffenderNotCompletions();
			offNotComUpdate.setNotiSeq(notiExisCur.getNotiSeq());
			offNotComUpdate.setNotiMoveSeq(notiExisCur.getNotiMoveSeq());
			offNotComUpdate.setOffenderBookId(offSch.getOffenderBookId());
			parameters.add(new BeanPropertySqlParameterSource(offNotComUpdate));
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (returnArray.length > 0) {
				blDel = true;
			}
		}
		return blDel;
	}

}
