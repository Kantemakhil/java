package net.syscon.s4.pkgs.tag_schedule.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleRepository;

@Repository
public class TagScheduleRepositoryImpl extends RepositoryBase implements TagScheduleRepository {

	private static Logger logger = LogManager.getLogger(TagScheduleRepositoryImpl.class);
	
	
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

	@Override
	public Integer offnderIndScchedule(final OffenderIndSchedules bean) {
		final String sql = getQuery("INSERT_OFFENDER_IND_SCHEDULES");
		final OffenderIndSchedules offIndSch = new OffenderIndSchedules();
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offIndSch));
		} catch (DataAccessException e) {
			logger.error("offnderIndScchedule", e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer deleteScheduleDeleteOperation(final BigDecimal eventId,String modifyUserId) {
		final String sql = getQuery("DELETE_SCHEDULE_DELETE_OPERATION");

		final VOffenderAllSchedules2 obj = new VOffenderAllSchedules2();
		obj.setEventId(eventId);

		final List<VOffenderAllSchedules2> list = new ArrayList<VOffenderAllSchedules2>();
		list.add(obj);

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final VOffenderAllSchedules2 offAllSch : list) {
			parameters.add(new BeanPropertySqlParameterSource(offAllSch));
		}
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "v_offender_all_schedules_2";
			String whereCondition = "event_id = :eventId";
			inputMap.put("eventId", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteScheduleDeleteOperation " + e.getMessage());
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteScheduleDeleteOperation", e);
			return 0;
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Long GetIntoCheckSum(final Long eventId) {
		final String sql = getQuery("Get_INTO_V_CHECK_SUM");
		Long returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_EVENT_ID", eventId), Long.class);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnVal;

	}

	public Long GetIntoRowId(final Long eventId) {
		final String sql = getQuery("Get_INTO_V_ROW_ID");
		Long returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_EVENT_ID", eventId), Long.class);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnVal;
	}

	@Override
	public Long getCheckSumNum(final Date modiDate) {
		final String sql = getQuery("CHECK_SUM");
		Long retNo = null;
		try {
			retNo = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TIMESTAMP", modiDate), Long.class);
		} catch (Exception e) {
			logger.error("getCheckSumNum :" + e);
			retNo = null;
		}
		return retNo;
	}

	@Override
	public Integer checkScheduleConflict(final VOffenderAllSchedules schedules) {
		final String sql = getQuery("CHECK_SCHEDULE_CONFLICT_ONE");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID",
					schedules.getOffenderBookId(), "P_EVENT_DATE", schedules.getEventDate()), Integer.class);
		} catch (Exception e) {
			logger.error("checkScheduleConflict :" + e);
		}
		return count;
	}

	@Override
	public Integer checkScheduleConflict(final Long pOffenderBookId, final Date pEventDate, final Integer pEventId) {
		final String sql = getQuery("CHECK_SCHEDULE_CONFLICT_SECOND");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", pOffenderBookId,
					"P_EVENT_DATE", pEventDate, "P_EVENT_ID", pEventId), Integer.class);
		} catch (Exception e) {
			logger.error("checkScheduleConflict :" + e);
		}
		return count;
	}

	@Override
	public Integer setScheduleStatus(VOffenderAllSchedules2 obj) {
		String sql = getQuery("");
		if (Optional.ofNullable(obj.getEventId()).isPresent()) {
			sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_UPDATE1");
		} else {
			sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_UPDATE2");
		}
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(obj));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("setScheduleStatus", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Date scheduleDate() {
		final String sql = getQuery("TAG_SCHEDULE_GET_SCHEDULE_DATE");
		Date returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					Date.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " scheduleDate error :: ", e);
		}
		return returnVal;
	}

	@Override
	public Integer updateScheduleDate(Date pScheduleDate) {
		final String sql = getQuery("TAG_SCHEDULE_UPDATE_SYSTEM_PROFILES_SCHEDULE_DATE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,createParams("P_SCHEDULE_DATE",pScheduleDate));
		} catch (DataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() + " updateScheduleDate error :: ", e);
			count = 0;
		}
		return count;
	}
	
	@Override
	public Integer updateoffProgramstatus(){
		final String sql = getQuery("TAG_SCHEDULE_UPDATE_OFFENDER_PROGRAM_PROFILES_STATUS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,createParams());
		} catch (DataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() + " updateoffProgramstatus error :: ", e);
			count = 0;
		}
		return count;
	}
	
	@Override
	public Integer materializePrgSchB(Date pScheduleDate){
		final String sql = getQuery("TAG_SCHEDULE_INSERT_OFFENDER_COURSE_MATERIALZE_B");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,createParams("P_SCHEDULE_DATE",pScheduleDate));
		} catch (DataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() + " materializePrgSchB error :: ", e);
			count = 0;
		}
		return count;
	}
	
	@Override
	public Integer materializePrgSchA(Date pScheduleDate){
		final String sql = getQuery("TAG_SCHEDULE_INSERT_OFFENDER_COURSE_MATERIALZE_A");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,createParams("P_SCHEDULE_DATE",pScheduleDate));
		} catch (DataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() + " materializePrgSchA error :: ", e);
			count = 0;
		}
		return count;
	}
	
	@Override
	public Integer flushDailySchUpdOffIndSch(Date pScheduleDate){
		final String sql = getQuery("TAG_SCHEDULE_UPDATE_OFFENDER_IND_SCHEDULES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,createParams("P_SCHEDULE_DATE",pScheduleDate));
		} catch (DataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() + " flushDailySchUpdOffIndSch error :: ", e);
			count = 0;
		}
		return count;
	}

	@Override
	public List<VOffenderAllSchedules2> getVoffAllSch(Date pScheduleDate) {
		final String sql = getQuery("GET_OLD_REC_OF_V_OFFENDER_ALL_SCHEDULES_TWO_ONE");
		final RowMapper<VOffenderAllSchedules2> vOffAllSchRM = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules2.class, vOffAllSchMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("P_SCHEDULE_DATE",pScheduleDate),vOffAllSchRM);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getVoffAllSchExp error :: ", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public Integer flushDailySchUpdOffCourse(Date pScheduleDate){
		final String sql = getQuery("TAG_SCHEDULE_UPDATE_OFFENDER_COURSE_ATTENDANCES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,createParams("P_SCHEDULE_DATE",pScheduleDate));
		} catch (DataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() + " flushDailySchUpdOffIndSch error :: ", e);
			count = 0;
		}
		return count;
	}
	
	@Override
	public Integer updateCourseSchDays(){
		final String sql = getQuery("TAG_SCHEDULE_UPDATE_COURSE_SCHEDULE_DAYS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,createParams());
		} catch (DataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() + " updateCourseSchDays error :: ", e);
			count = 0;
		}
		return count;
	}

	@Override
	public List<VOffenderAllSchedules2> getVoffAllSchExp(Date pScheduleDate) {
		final String sql = getQuery("GET_OLD_REC_OF_V_OFFENDER_ALL_SCHEDULES_TWO_TWO");
		final RowMapper<VOffenderAllSchedules2> vOffAllSchRM = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules2.class, vOffAllSchMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("P_SCHEDULE_DATE",pScheduleDate),vOffAllSchRM);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getVoffAllSchExp error :: ", e);
			return Collections.emptyList();
		}
	}
	
	

}