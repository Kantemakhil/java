package net.syscon.s4.inst.schedules.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.schedules.OiuscinqRepository;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
/**
 * Class OiuscinqRepositoryImpl
 */
@Repository
public class OiuscinqRepositoryImpl extends RepositoryBase implements OiuscinqRepository{


private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("FIRST_NAME", 						new FieldMapper(" firstName"))
.put("LAST_NAME", 						new FieldMapper(" lastName "))
.build();
private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("offenderIdDisplay"))
.put("TO_INTERNAL_LOCATION_ID", 					new FieldMapper("toInternalLocationId"))
.put("CREDITED_HOURS", 								new FieldMapper("creditedHours"))
.put("UNEXCUSED_ABSENCE_FLAG", 						new FieldMapper("unexcusedAbsenceFlag"))
.put("UNDERSTANDING_CODE", 							new FieldMapper("understandingCode"))
.put("OFFENDER_LAST_NAME", 							new FieldMapper("offenderLastName"))
.put("LU_LEVEL_4_CODE", 							new FieldMapper("luLevel4Code"))
.put("AGENCY_IML_LEVEL_3_CODE", 					new FieldMapper("agencyImlLevel3Code"))
.put("PERFORMANCE_CODE", 							new FieldMapper("performanceCode"))
.put("TA_ID", 										new FieldMapper("taId"))
.put("LIVING_UNIT_DESC", 							new FieldMapper("livingUnitDesc"))
.put("ESCORT_DESC", 								new FieldMapper("escortDesc"))
.put("BOOKING_ACTIVE_FLAG", 						new FieldMapper("bookingActiveFlag"))
.put("TO_CITY_CODE", 								new FieldMapper("toCityCode"))
.put("IN_OUT_STATUS", 								new FieldMapper("inOutStatus"))
.put("CHECK_SUM", 									new FieldMapper("checkSum"))
.put("APPLICATION_TIME", 							new FieldMapper("applicationTime"))
.put("RECORD_SOURCE", 								new FieldMapper("recordSource"))
.put("SCHEDULE_MOVEMENT_TIME", 						new FieldMapper("scheduleMovementTime"))
.put("OFFENDER_FIRST_NAME", 						new FieldMapper("offenderFirstName"))
.put("EVENT_STATUS", 								new FieldMapper("eventStatus"))
.put("IN_CHARGE_STAFF_ID", 							new FieldMapper("inChargeStaffId"))
.put("PIECE_WORK", 									new FieldMapper("pieceWork"))
.put("UNPAID_WORK_BEHAVIOUR", 						new FieldMapper("unpaidWorkBehaviour"))
.put("START_TIME", 									new FieldMapper("startTime"))
.put("DIRECTION_CODE", 								new FieldMapper("directionCode"))
.put("TO_ADDRESS_OWNER_CLASS", 						new FieldMapper("toAddressOwnerClass"))
.put("OFFENDER_BOOK_ID", 							new FieldMapper("offenderBookId"))
.put("FROM_CITY_NAME", 								new FieldMapper("fromCityName"))
.put("TO_LOC_DESC", 								new FieldMapper("toLocDesc"))
.put("RETURN_TIME", 								new FieldMapper("returnTime"))
.put("AGENCY_IML_ID", 								new FieldMapper("agencyImlId"))
.put("APPLICATION_DATE", 							new FieldMapper("applicationDate"))
.put("EVENT_OUTCOME", 								new FieldMapper("eventOutcome"))
.put("EVENT_ID", 									new FieldMapper("eventId"))
.put("ENGAGEMENT_CODE", 							new FieldMapper("engagementCode"))
.put("OUTCOME_REASON_CODE", 						new FieldMapper("outcomeReasonCode"))
.put("AGY_LOC_ID", 									new FieldMapper("agyLocId"))
.put("SICK_NOTE_EXPIRY_DATE", 						new FieldMapper("sickNoteExpiryDate"))
.put("TO_INT_LOC_LEVEL_1_CODE", 					new FieldMapper("toIntLocLevel1Code"))
.put("LU_LEVEL_3_CODE", 							new FieldMapper("luLevel3Code"))
.put("TO_CITY_NAME", 								new FieldMapper("toCityName"))
.put("PROV_STATE_DESC", 							new FieldMapper("provStateDesc"))
.put("TO_AGY_LOC_ID", 								new FieldMapper("toAgyLocId"))
.put("TO_ADDRESS_ID", 								new FieldMapper("toAddressId"))
.put("UNPAID_WORK_SUPERVISOR", 						new FieldMapper("unpaidWorkSupervisor"))
.put("LIVING_UNIT_ID", 								new FieldMapper("livingUnitId"))
.put("UNPAID_WORK_ACTION", 							new FieldMapper("unpaidWorkAction"))
.put("AGENCY_IML_LEVEL_1_CODE", 					new FieldMapper("agencyImlLevel1Code"))
.put("ESCORT_CODE", 								new FieldMapper("escortCode"))
.put("OFFENDER_ID", 								new FieldMapper("offenderId"))
.put("EVENT_DATE", 									new FieldMapper("eventDate"))
.put("TO_INT_LOC_USER_DESC", 						new FieldMapper("toIntLocUserDesc"))
.put("EVENT_STATUS_DESC", 							new FieldMapper("eventStatusDesc"))
.put("TO_INTERNAL_LOCATION_DESC", 					new FieldMapper("toInternalLocationDesc"))
.put("AGY_LOC_DESC", 								new FieldMapper("agyLocDesc"))
.put("LU_LEVEL_2_CODE", 							new FieldMapper("luLevel2Code"))
.put("FROM_CITY_CODE", 								new FieldMapper("fromCityCode"))
.put("EVENT_SUB_TYPE", 								new FieldMapper("eventSubType"))
.put("DETAILS", 									new FieldMapper("details"))
.put("CHECK_BOX_1", 								new FieldMapper("checkBox1"))
.put("BUSY_DATE_FLAG", 								new FieldMapper("busyDateFlag"))
.put("EVENT_OUTCOME_DESC", 							new FieldMapper("eventOutcomeDesc"))
.put("SCHEDULED_TRIP_ID", 							new FieldMapper("scheduledTripId"))
.put("EVENT_SUB_TYPE_DESC", 						new FieldMapper("eventSubTypeDesc"))
.put("HIDDEN_COMMENT_TEXT", 						new FieldMapper("hiddenCommentText"))
.put("IN_CHARGE_STAFF_NAME", 						new FieldMapper("inChargeStaffName"))
.put("TO_INT_LOC_LEVEL_2_CODE", 					new FieldMapper("toIntLocLevel2Code"))
.put("TRANSPORT_CODE", 								new FieldMapper("transportCode"))
.put("COMMENT_TEXT", 								new FieldMapper("commentText"))
.put("EVENT_TYPE_DESC", 							new FieldMapper("eventTypeDesc"))
.put("AGENCY_IML_LEVEL_2_CODE", 					new FieldMapper("agencyImlLevel2Code"))
.put("IN_TIME", 									new FieldMapper("inTime"))
.put("ACTIVE_FLAG", 								new FieldMapper("activeFlag"))
.put("OFF_PRGREF_ID", 								new FieldMapper("offPrgrefId"))
.put("SICK_NOTE_RECEIVED_DATE", 					new FieldMapper("sickNoteReceivedDate"))
.put("CONTACT_PERSON_NAME", 						new FieldMapper("contactPersonName"))
.put("OUT_TIME", 									new FieldMapper("outTime"))
.put("AGREED_TRAVEL_HOUR", 							new FieldMapper("agreedTravelHour"))
.put("EVENT_TYPE", 									new FieldMapper("eventType"))
.put("REFERENCE_ID", 								new FieldMapper("referenceId"))
.put("PROV_STATE_CODE", 							new FieldMapper("provStateCode"))
.put("LU_LEVEL_1_CODE", 							new FieldMapper("luLevel1Code"))
.put("BOOKING_NO", 									new FieldMapper("bookingNo"))
.put("AGENCY_IML_DESC", 							new FieldMapper("agencyImlDesc"))
.put("TO_INT_LOC_LEVEL_3_CODE", 					new FieldMapper("toIntLocLevel3Code"))
.put("CHECK_BOX_2", 								new FieldMapper("checkBox2"))
.put("TO_AGY_LOC_DESC", 							new FieldMapper("toAgyLocDesc"))
.put("END_TIME", 									new FieldMapper("endTime"))
.put("TO_LOC", 										new FieldMapper("toLoc"))
.put("EVENT_CLASS", 								new FieldMapper("eventClass"))
.put("RETURN_DATE", 								new FieldMapper("returnDate"))
.build();
private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("MODULE_TYPE", 						new FieldMapper("moduleType"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

	/**
	 * Creates new OiuscinqRepositoryImpl class Object
	 */
	public OiuscinqRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * @
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		 StringBuilder sqlBuilder = new StringBuilder();
		    String sql;
		    if (objSearchDao.getModuleName() != null && objSearchDao.getModuleName().equals("OIDSTABS")) {
		        sqlBuilder.append(getQuery("OIUSCINQ_OFFSCH_FIND_V_OFFENDERS_ALL_SCHEDULES_OIDSTABS"));
		        if (objSearchDao.getEventId() != null) {
		            sqlBuilder.append("  AND (EVENT_ID <> :EVENT_ID or EVENT_ID is null) order by EVENT_DATE desc , START_TIME desc ");
		        } else {
		            sqlBuilder.append("  AND (EVENT_ID is not null or EVENT_ID is null) order by EVENT_DATE desc , START_TIME desc ");
		        }
		    } else {
		        sqlBuilder.append(getQuery("OIUSCINQ_OFFSCH_FIND_V_OFFENDERS_ALL_SCHEDULES"));
		    }
		    sql = sqlBuilder.toString();
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		SimpleDateFormat eventDate=new SimpleDateFormat("yyyy-MM-dd");
		String date=eventDate.format(objSearchDao.getEventDate());
		String returnDate = objSearchDao.getReturnDate() != null ? eventDate.format(objSearchDao.getReturnDate()) : null;

		final ArrayList<VOffenderAllSchedules> returnList = (ArrayList<VOffenderAllSchedules>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "EVENT_DATE",
						date ,"RETURN_DATE",returnDate , "EVENT_ID",objSearchDao.getEventId() ), VOffenderAllSchedulesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiuscinqPostInsert
	 *
	 * @param params
	 *
	 */
	public StaffMembers oiuscinqPostInsert(final StaffMembers paramBean) {
		final String sql = getQuery("OIUSCINQ_OIUSCINQ_POSTINSERT");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIUSCINQ_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

}
