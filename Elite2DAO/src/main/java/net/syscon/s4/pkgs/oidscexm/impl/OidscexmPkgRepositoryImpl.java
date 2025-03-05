package net.syscon.s4.pkgs.oidscexm.impl;

import java.util.ArrayList;
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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.oidscexm.OidscexmPkgRepository;

@Repository
public class OidscexmPkgRepositoryImpl extends RepositoryBase implements OidscexmPkgRepository {

	private static Logger logger = LogManager.getLogger(OidscexmPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vOffAllSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("OFFENDER_ID_DISPLAY",      		new FieldMapper("offenderIdDisplay"))
            .put("TO_INTERNAL_LOCATION_ID", 		new FieldMapper("toInternalLocationId"))
            .put("CREDITED_HOURS", 					new FieldMapper("creditedHours"))
            .put("UNEXCUSED_ABSENCE_FLAG", 		    new FieldMapper("unexcusedAbsenceFlag"))
            .put("UNDERSTANDING_CODE", 				new FieldMapper("understandingCode"))
            .put("OFFENDER_LAST_NAME", 				new FieldMapper("offenderLastName"))
            .put("LU_LEVEL_4_CODE", 				new FieldMapper("luLevel4Code"))
            .put("AGENCY_IML_LEVEL_3_CODE", 		new FieldMapper("agencyImlLevel3Code"))
            .put("PERFORMANCE_CODE", 				new FieldMapper("performanceCode"))
            .put("TA_ID", 						    new FieldMapper("taId"))
            .put("LIVING_UNIT_DESC", 				new FieldMapper("livingUnitDesc"))
            .put("ESCORT_DESC", 					new FieldMapper("escortDesc"))
            .put("BOOKING_ACTIVE_FLAG", 		    new FieldMapper("bookingActiveFlag"))
            .put("TO_CITY_CODE", 					new FieldMapper("toCityCode"))
            .put("IN_OUT_STATUS", 					new FieldMapper("inOutStatus"))
            .put("CHECK_SUM", 						new FieldMapper("checkSum"))
            .put("APPLICATION_TIME", 				new FieldMapper("applicationTime"))
            .put("RECORD_SOURCE", 					new FieldMapper("recordSource"))
            .put("SCHEDULE_MOVEMENT_TIME", 			new FieldMapper("scheduleMovementTime"))
            .put("OFFENDER_FIRST_NAME", 			new FieldMapper("offenderFirstName"))
            .put("EVENT_STATUS", 					new FieldMapper("eventStatus"))
            .put("IN_CHARGE_STAFF_ID", 				new FieldMapper("inChargeStaffId"))
            .put("PIECE_WORK", 						new FieldMapper("pieceWork"))
            .put("UNPAID_WORK_BEHAVIOUR", 			new FieldMapper("unpaidWorkBehaviour"))
            .put("START_TIME", 						new FieldMapper("startTime"))
            .put("DIRECTION_CODE", 					new FieldMapper("directionCode"))
            .put("TO_ADDRESS_OWNER_CLASS", 			new FieldMapper("toAddressOwnerClass"))
            .put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
            .put("FROM_CITY_NAME", 					new FieldMapper("fromCityName"))
            .put("TO_LOC_DESC", 					new FieldMapper("toLocDesc"))
            .put("RETURN_TIME", 					new FieldMapper("returnTime"))
            .put("AGENCY_IML_ID", 					new FieldMapper("agencyImlId"))
            .put("APPLICATION_DATE", 				new FieldMapper("applicationDate"))
            .put("EVENT_OUTCOME", 					new FieldMapper("eventOutcome"))
            .put("EVENT_ID", 						new FieldMapper("eventId"))
            .put("ENGAGEMENT_CODE", 				new FieldMapper("engagementCode"))
            .put("OUTCOME_REASON_CODE", 			new FieldMapper("outcomeReasonCode"))
            .put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
            .put("SICK_NOTE_EXPIRY_DATE", 			new FieldMapper("sickNoteExpiryDate"))
            .put("TO_INT_LOC_LEVEL_1_CODE",			new FieldMapper("toIntLocLevel1Code"))
            .put("LU_LEVEL_3_CODE", 				new FieldMapper("luLevel3Code"))
            .put("TO_CITY_NAME", 					new FieldMapper("toCityName"))
            .put("PROV_STATE_DESC", 				new FieldMapper("provStateDesc"))
            .put("TO_AGY_LOC_ID", 					new FieldMapper("toAgyLocId"))
            .put("TO_ADDRESS_ID", 					new FieldMapper("toAddressId"))
            .put("UNPAID_WORK_SUPERVISOR", 			new FieldMapper("unpaidWorkSupervisor"))
            .put("LIVING_UNIT_ID", 					new FieldMapper("livingUnitId"))
            .put("UNPAID_WORK_ACTION", 				new FieldMapper("unpaidWorkAction"))
            .put("AGENCY_IML_LEVEL_1_CODE", 	    new FieldMapper("agencyImlLevel1Code"))
            .put("ESCORT_CODE", 					new FieldMapper("escortCode"))
            .put("OFFENDER_ID", 					new FieldMapper("offenderId"))
            .put("EVENT_DATE", 						new FieldMapper("eventDate"))
            .put("TO_INT_LOC_USER_DESC", 			new FieldMapper("toIntLocUserDesc"))
            .put("EVENT_STATUS_DESC", 				new FieldMapper("eventStatusDesc"))
            .put("TO_INTERNAL_LOCATION_DESC", 		new FieldMapper("toInternalLocationDesc"))
            .put("AGY_LOC_DESC", 					new FieldMapper("agyLocDesc"))
            .put("LU_LEVEL_2_CODE", 				new FieldMapper("luLevel2Code"))
            .put("FROM_CITY_CODE", 					new FieldMapper("fromCityCode"))
            .put("EVENT_SUB_TYPE", 					new FieldMapper("eventSubType"))
            .put("DETAILS", 						new FieldMapper("details"))
            .put("CHECK_BOX_1", 					new FieldMapper("checkBox1"))
            .put("BUSY_DATE_FLAG", 					new FieldMapper("busyDateFlag"))
            .put("EVENT_OUTCOME_DESC", 			    new FieldMapper("eventOutcomeDesc"))
            .put("SCHEDULED_TRIP_ID", 		     	new FieldMapper("scheduledTripId"))
            .put("EVENT_SUB_TYPE_DESC", 		    new FieldMapper("eventSubTypeDesc"))
            .put("HIDDEN_COMMENT_TEXT", 			new FieldMapper("hiddenCommentText"))
            .put("IN_CHARGE_STAFF_NAME", 			new FieldMapper("inChargeStaffName"))
            .put("TO_INT_LOC_LEVEL_2_CODE", 		new FieldMapper("toIntLocLevel2Code"))
            .put("TRANSPORT_CODE", 					new FieldMapper("transportCode"))
            .put("COMMENT_TEXT", 					new FieldMapper("commentText"))
            .put("EVENT_TYPE_DESC", 				new FieldMapper("eventTypeDesc"))
            .put("AGENCY_IML_LEVEL_2_CODE", 		new FieldMapper("agencyImlLevel2Code"))
            .put("IN_TIME", 						new FieldMapper("inTime"))
            .put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
            .put("OFF_PRGREF_ID", 					new FieldMapper("offPrgrefId"))
            .put("SICK_NOTE_RECEIVED_DATE", 		new FieldMapper("sickNoteReceivedDate"))
            .put("CONTACT_PERSON_NAME", 			new FieldMapper("contactPersonName"))
            .put("OUT_TIME", 						new FieldMapper("outTime"))
            .put("AGREED_TRAVEL_HOUR", 				new FieldMapper("agreedTravelHour"))
            .put("EVENT_TYPE", 						new FieldMapper("eventType"))
            .put("REFERENCE_ID", 					new FieldMapper("referenceId"))
            .put("PROV_STATE_CODE", 			    new FieldMapper("provStateCode"))
            .put("LU_LEVEL_1_CODE", 		     	new FieldMapper("luLevel1Code"))
            .put("BOOKING_NO", 						new FieldMapper("bookingNo"))
            .put("AGENCY_IML_DESC", 				new FieldMapper("agencyImlDesc"))
            .put("TO_INT_LOC_LEVEL_3_CODE", 		new FieldMapper("toIntLocLevel3Code"))
            .put("CHECK_BOX_2", 					new FieldMapper("checkBox2"))
            .put("TO_AGY_LOC_DESC", 				new FieldMapper("toAgyLocDesc"))
            .put("END_TIME", 						new FieldMapper("endTime"))
            .put("TO_LOC", 						    new FieldMapper("toLoc"))
            .put("EVENT_CLASS", 					new FieldMapper("eventClass"))
            .put("RETURN_DATE", 					new FieldMapper("returnDate"))
            .build();
	
	private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	@Override
	public Long getSeq(final Long offenderBookId) {
		final String sql = getQuery("SELECT_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_book_id", offenderBookId), Long.class);
	}

	@Override
	public Long getParentCur(final Long offenderBookId) {
		final String sql = getQuery("SELECET_EVENT_ID");
		Long retVal = null;
		try {
		retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_book_id", offenderBookId), Long.class);
		}
		catch(Exception e) {
			logger.error("getParentCur :"+e);
		}
		return retVal;
	}

	@Override
	public Long getNewEventId() {
		final String sql = getQuery("SELECT_NEW_EVENT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public Integer insertOffenderExtMov(final OffenderExternalMovements bean) {
		final String sql = getQuery("INSERT_OFFENDER_EXT_MOV");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean));
	}

	@Override
	public String getCloseContactFlag() {
		final String sql = getQuery("GET_CLOSE_CONTACT_FLAG");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public String getCommActiveFlag(final Long offenderBookId) {
		final String sql = getQuery("GET_COMM_ACTIVE_FLAG");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_bk_id", offenderBookId),
				String.class);
	}

	@Override
	public Integer updateOffenderBookings(final String lvInOutStatus, final String lvAgyLocId,
			final Long offenderBookId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_BOOKINGS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("LV_IN_OUT_STATUS", lvInOutStatus);
		inParamMap.put("LV_AGY_LOC_ID", lvAgyLocId);
		inParamMap.put("p_off_bk_id", offenderBookId);
		inParamMap.put("modifyUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer updateOffenderBookingsOne(final String lvInOutStatus, final String lvAgyLocId,
			final Long offenderBookId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_BOOKINGS_ONE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("LV_IN_OUT_STATUS", lvInOutStatus);
		inParamMap.put("LV_AGY_LOC_ID", lvAgyLocId);
		inParamMap.put("p_off_bk_id", offenderBookId);
		inParamMap.put("modifyUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer updateOffenderBookingsTwo(final String lvInOutStatus, final String lvAgyLocId,
			final Long offenderBookId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_BOOKINGS_TWO");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("LV_IN_OUT_STATUS", lvInOutStatus);
		inParamMap.put("LV_AGY_LOC_ID", lvAgyLocId);
		inParamMap.put("p_off_bk_id", offenderBookId);
		inParamMap.put("modifyUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer updatevOffenderAllSchedules2(final Date upDate, final Integer eventId) {
		final String sql = getQuery("UPDATE_V_OFFENDER");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("RETURN_TIME", upDate);
		inParamMap.put("P_EVENT_ID", eventId);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer insertCourtEvents(final VOffenderAllSchedules offIndSchedls, final String name) {
		final String sql = getQuery("INSERT_COURT_EVENTS_NEW");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_book_id", offIndSchedls.getOffenderBookId());
		inParamMap.put("lv_return_date", offIndSchedls.getEventDate());
		inParamMap.put("start_time", offIndSchedls.getStartTime());

		inParamMap.put("p_to_agy_loc_id", offIndSchedls.getToAgyLocId());
		inParamMap.put("p_movement_reason_code", offIndSchedls.getEventSubType());

		inParamMap.put("p_ta_status", offIndSchedls.getEventStatus());
		inParamMap.put("p_event_id", offIndSchedls.getEventId());
		inParamMap.put("createUserId", name);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public void insertOffenderIndSchedules(final VOffenderAllSchedules offIndSchedls, final String name) {
		final String sql = getQuery("INSERT_OFFENDER_IND_SCHEDULES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_BOOK_ID", offIndSchedls.getOffenderBookId());
		inParamMap.put("LV_RETURN_DATE", offIndSchedls.getReturnDate());
		inParamMap.put("START_TIME", offIndSchedls.getReturnTime());

		inParamMap.put("P_TO_AGY_LOC_ID", offIndSchedls.getToAgyLocId());
		inParamMap.put("P_MOVEMENT_TYPE", offIndSchedls.getEventType());
		inParamMap.put("P_MOVEMENT_REASON_CODE", offIndSchedls.getEventSubType());

		inParamMap.put("P_ESCORT_CODE", offIndSchedls.getEscortCode());
		inParamMap.put("P_TA_STATUS", offIndSchedls.getEventStatus());
		inParamMap.put("P_EVENT_ID", offIndSchedls.getEventId());
		inParamMap.put("createUserId", name);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer updatecrsEventStatus(final Integer eventId, final String userName) {
		final String sql = getQuery("UPDATE_CRS_EVENT_STATUS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("EVENT_ID", eventId, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updatecrsEventStatus :" + e);
		}
		return count;
	}

	@Override
	public Integer updateOffExtmvnts(final VOffenderAllSchedules offExtMov, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDEREXTERNAL_MOVEMENTS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("P_BOOK_ID", offExtMov.getOffenderBookId(), "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffExtmvnts :" + e);
		}
		return count;
	}

	@Override
	public Integer updateOffSchStatus(final Integer eventId) {
		final String sql = getQuery("UPDATE_V_OFFNDER_ALL_SCH");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("event_id", eventId));
		} catch (DataAccessException e) {
			logger.error("updateOffSchStatus :" + e);
		}
		return count;
	}

	@Override
	public Integer UpdateInOutStatus(final String lvInOut, final Long pBookId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_IN_OUT_STATUS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("LV_IN_OUT", lvInOut, "P_BOOK_ID", pBookId, "modifyUserId", userName));
	}
	@Override
	public List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules searchRecord) {
		final String sql = getQuery("GET_OLD_REC_OF_V_OFFENDER_ALL_SCHEDULES_2");
		final RowMapper<VOffenderAllSchedules> vOffAllSchRM = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffAllSchMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("EVENT_ID",searchRecord.getEventId()),vOffAllSchRM);
	}
	
	@Override
	public List<OffenderBookings> getOldRecOffBooking(Long offenderBookId) {
		final String sql = getQuery("GET_OLD_REC_OFF_BOOKING");
		List<OffenderBookings> retObj = new ArrayList<OffenderBookings>();
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				vHeaderBlockMapping);
		try {
			retObj =  namedParameterJdbcTemplate.query(sql, createParams("offender_book_id", offenderBookId),
					columnRowMapper);
		}
		catch(Exception e) {
			logger.error("getOldRecOffBooking "+e);
		}
		return retObj;
	}

	@Override
	public List<OffenderExternalMovements> getOldRecOffExtMov(Long getOffenderBookId,Integer movementSeq) {
		final String sql = getQuery("GET_OLD_REC_OFFENDER_EXTERNAL_MOVEMENTS");
		List<OffenderExternalMovements> obj = new ArrayList<OffenderExternalMovements>();
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderExternalMovements.class,
				vHeaderBlockMapping);
		try {
			obj = namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", getOffenderBookId,"MOVEMENT_SEQ",movementSeq),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getOldRecOffExtMov: " + e);
		}
		return obj;
	}
}
