package net.syscon.s4.inst.schedules.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.schedules.OidstwjuRepository;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import oracle.jdbc.OracleTypes;
/**
 * Class OidstwjuRepositoryImpl
 */
@Repository
public class OidstwjuRepositoryImpl extends RepositoryBase implements OidstwjuRepository{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstwjuRepositoryImpl.class.getName());

            private final Map<String, FieldMapper> stfMemMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("LAST_NAME", 						new FieldMapper("lastName"))
            .put("FIRST_NAME", 						new FieldMapper("firstName"))
            .put("MIDDLE_NAME",                   new FieldMapper("middleName"))
            .put("STAFF_ID",                              new FieldMapper("staffId"))
            .put("DESCRIPTION", 						new FieldMapper("description"))
            .put("CODE", 						            new FieldMapper("code"))
            .build();
            private final Map<String, FieldMapper> offIndSchWMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("REQUEST_DATE", 					new FieldMapper("requestDate"))
            .put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
            .put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
            .put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
            .put("STATUS_DATE", 					new FieldMapper("statusDate"))
            .put("EVENT_ID", 						new FieldMapper("eventId"))
            .put("WAIT_LIST_STATUS", 				new FieldMapper("waitListStatus"))
            .put("TRANSFER_PRIORITY",               new FieldMapper("transferPriority"))
            .put("APPROVED_FLAG",                   new FieldMapper("approvedFlag"))
            .put("APPROVED_STAFF_ID",               new FieldMapper("approvedStaffId"))
            .put("OUTCOME_REASON_CODE",             new FieldMapper("outcomeReasonCode"))
            .put("COMMENT_TEXT_1",                  new FieldMapper("commentText1"))	
            .put("COMMENT_TEXT_2",                  new FieldMapper("commentText2"))	
            .put("CREATE_USER_ID",                  new FieldMapper("createUserId"))
            .put("MODIFY_USER_ID",                  new FieldMapper("modifyUserId"))
            .build();
            private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("WAIT_LIST_STATUS", 				new FieldMapper("waitListStatus"))
            .put("PARENT_CODE", 					new FieldMapper("parentCode"))
            .build();
            private final Map<String, FieldMapper> offIndSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("OFFENDER_BOOK_ID",				new FieldMapper("offenderBookId"))
            .build();
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
            private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("LAST_NAME", 						new FieldMapper("lastName"))
            .put("MIDDLE_NAME", 					new FieldMapper("middleName "))
            .put("DESCRIPTION", 					new FieldMapper("description"))
            .put("FIRST_NAME", 						new FieldMapper("firstName"))
            .put("CODE", 						    new FieldMapper("code"))
            .put("REASON_CODE", 						    new FieldMapper("movementReasonCode"))
            .build();
            private final Map<String, FieldMapper> sysProMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("PROFILE_VALUE_2", 			    new FieldMapper("profileValue2"))
            .build();
            private final Map<String, FieldMapper> offPenNotiMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("NOTI_MOVE_SEQ", 					new FieldMapper("notiMoveSeq"))
            .put("COUNT", 						    new FieldMapper("count"))
            .put("NOTI_SEQ", 						new FieldMapper("notiSeq"))
            .build();


	/**
	 * Creates new OidstwjuRepositoryImpl class Object
	 */
	public OidstwjuRepositoryImpl() {
	}

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
		final String sql = getQuery("OIDSTWJU_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES");
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
						"OFFENDER_BOOK_ID =  :offenderBookId and EVENT_TYPE  = 'TRN' and EVENT_SUB_TYPE <> 'OJ'  "
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
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVOffAllSch
	 *            List<VOffenderAllSchedules>
	 *
	 * @return Integer
	 *
	 */
	public Integer offSchInsertVOffenderAllSchedules(final List<OffenderIndSchedules> lstVOffAllSch) {
		int returnValue = 0;
		final String sql = getQuery("OIDSTWJU_SCH_INSERT_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offAllSch : lstVOffAllSch) {
			parameters.add(new BeanPropertySqlParameterSource(offAllSch));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchInsertVOffenderAllSchedules error", e);	
		}
		if (lstVOffAllSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	public Integer createSchedule(final VOffenderAllSchedules vOffAllSch) {
		Integer inValue = 0;
		final OffenderIndSchedules objOffIndSch = new OffenderIndSchedules();
		objOffIndSch.setAgyLocId(vOffAllSch.getAgyLocId());
		objOffIndSch.setDirectionCode(vOffAllSch.getDirectionCode());
		objOffIndSch.setEscortCode(vOffAllSch.getEscortCode());
		objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
		objOffIndSch.setEventStatus(vOffAllSch.getEventStatus());
		objOffIndSch.setEventDate(vOffAllSch.getEventDate());
		final String strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
		objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
		objOffIndSch.setToAgyLocId(vOffAllSch.getToAgyLocId());
		objOffIndSch.setStartTime(vOffAllSch.getStartTime());
		objOffIndSch.setCommentText(vOffAllSch.getCommentText());
		objOffIndSch.setEventClass("EXT_MOV");
		objOffIndSch.setEventType("TRN");
		objOffIndSch.setHiddenCommentText(vOffAllSch.getHiddenCommentText());
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		String value = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SCHEDULE_REC", OracleTypes.JAVA_OBJECT),
				new SqlOutParameter("P_EVENT_ID", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SCHEDULE").withProcedureName("CREATE_SCHEDULE").declareParameters(sqlParameters);
		inParamMap.put("P_SCHEDULE_REC", objOffIndSch);
		inParamMap.put("P_EVENT_ID", OracleTypes.VARCHAR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeObject(String.class, inParameter);

			if (value != null) {
				inValue = Integer.parseInt(String.valueOf(value));
			}
		} catch (Exception e) {
			logger.error("In createSchedule method : ", e);
		}
		return inValue;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffAllSch
	 *            List<VOffenderAllSchedules>
	 *
	 */
	public Integer offSchUpdateVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffAllSch) {
		final String sql = getQuery("OIDSTWJU_SCH_UPDATE_V_OFFENDER_ALL_SCHEDULES_2");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offAllSchedules : lstOffAllSch) {
			parameters.add(new BeanPropertySqlParameterSource(offAllSchedules));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchUpdateVOffenderAllSchedules error", e);	
		}
		if (lstOffAllSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffAllSch
	 *            List<VOffenderAllSchedules>
	 *
	 */
	public Integer offSchDeleteVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffAllSch) {
		final String sql = getQuery("OIDSTWJU_SCH_DELETE_V_OFFENDER_ALL_SCHEDULES _2");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offAllSch : lstOffAllSch) {
			parameters.add(new BeanPropertySqlParameterSource(offAllSch));
		}
		try {
			String tableName = "V_OFFENDER_ALL_SCHEDULES_2";
			String whereCondition = "EVENT_ID = :eventId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchDeleteVOffenderAllSchedules error", e);	
		}
		if (lstOffAllSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderIndSchWaitLists
	 *
	 * @return List<OffenderIndSchWaitLists>
	 *
	 */
	public List<OffenderIndSchWaitLists> offSwlExecuteQuery(final OffenderIndSchWaitLists objSearchDao) {
		final String sql = getQuery("OIDSTWJU_OFFSWL_FIND_OFFENDER_IND_SCH_WAIT_LISTS");
		final RowMapper<OffenderIndSchWaitLists> offIndSchWaitRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderIndSchWaitLists.class, offIndSchWMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("eventId", objSearchDao.getEventId()),
				offIndSchWaitRM);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffIndSchWait
	 *            List<OffenderIndSchWaitLists>
	 *
	 * @return Integer
	 *
	 */
	public Integer offSwlInsertOffenderIndSchWaitLists(final List<OffenderIndSchWaitLists> lstOffIndSchWait) {
		int returnValue = 0;
		final String sql = getQuery("OIDSTWJU_OFFSWL_INSERT_OFFENDER_IND_SCH_WAIT_LISTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchWaitLists offIndSchWait : lstOffIndSchWait) {
			parameters.add(new BeanPropertySqlParameterSource(offIndSchWait));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSwlInsertOffenderIndSchWaitLists error", e);	
		}
		if (lstOffIndSchWait.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffIndSchWait
	 *            List<OffenderIndSchWaitLists>
	 *
	 */
	public Integer offSwlUpdateOffenderIndSchWaitLists(final List<OffenderIndSchWaitLists> lstOffIndSchWait) {
		final String sql = getQuery("OIDSTWJU_OFFSWL_UPDATE_OFFENDER_IND_SCH_WAIT_LISTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchWaitLists offIndSchWait : lstOffIndSchWait) {
			parameters.add(new BeanPropertySqlParameterSource(offIndSchWait));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSwlUpdateOffenderIndSchWaitLists error", e);	
		}
		if (lstOffIndSchWait.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param offIndSchWait
	 *            List<OffenderIndSchWaitLists>
	 *
	 */
	public Integer offSwlDeleteOffenderIndSchWaitLists(final List<OffenderIndSchWaitLists> lstOffIndSchWait) {
		final String sql = getQuery("OIDSTWJU_OFFSWL_DELETE_OFFENDER_IND_SCH_WAIT_LISTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchWaitLists offIndSchWait : lstOffIndSchWait) {
			parameters.add(new BeanPropertySqlParameterSource(offIndSchWait));
		}
		try {
			String tableName = "OFFENDER_IND_SCH_WAIT_LISTS";
			String whereCondition = "EVENT_ID  = :eventId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSwlDeleteOffenderIndSchWaitLists error", e);	
		}
		if (lstOffIndSchWait.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffAllSch
	 *            List<VOffenderAllSchedules>
	 *
	 */
	public Integer offSchUpdateVOffenderAllSchedulesStatus(final List<VOffenderAllSchedules> lstOffAllSch) {
		final String sql = getQuery("OIDSTWJU_OFFSCH_UPDATE_V_OFFENDER_ALL_SCHEDULES_STATUS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderAllSchedules offAllSchedules : lstOffAllSch) {
			parameters.add(new BeanPropertySqlParameterSource(offAllSchedules));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchUpdateVOffenderAllSchedulesStatus error", e);	
		}
		if (lstOffAllSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListReferenceCodesM>
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDSTWJU_FIND_RGESCORT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", mode),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In rgEscortRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgencyLocationRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDSTWJU_FIND_RGAGENCYLOCATION");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		final List<AgencyLocations> lstAgyLoc = new ArrayList<AgencyLocations>();
		try {
			if (agyLocId != null) {
				returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
						createParams("AGYLOCID", agyLocId), mRowMapper);
			}
			if (returnList != null && returnList.size() > 0) {
				for (final AgencyLocations objAgencyLoc : returnList) {
					objAgencyLoc.setCode(objAgencyLoc.getAgyLocId());
					lstAgyLoc.add(objAgencyLoc);
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In rgAgencyLocationRecordGroup method : ", e);
		}
		return lstAgyLoc;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MovementReasons>
	 */
	public List<MovementReasons> rgMoveReasonRecordGroup() {
		final String sql = getQuery("OIDSTWJU_FIND_RGMOVEREASON");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				mMapping);
		List<MovementReasons> lstRefCodes = new ArrayList<MovementReasons>();
		final List<MovementReasons> lstMovReasons = new ArrayList<MovementReasons>();
		try {
			lstRefCodes = (List<MovementReasons>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
			for (final MovementReasons objMovReason : lstRefCodes) {
				objMovReason.setCode(objMovReason.getMovementReasonCode());
				lstMovReasons.add(objMovReason);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In rgMoveReasonRecordGroup method : ", e);
		}
		return lstMovReasons;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDSTWJU_FIND_RGSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes =  namedParameterJdbcTemplate.query(sql, createParams("MODE", mode),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In rgStatusRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDSTWJU_FIND_RGPRIORITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", mode),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In rgPriorityRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDSTWJU_FIND_RGCANCELREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", mode),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In rgCancelReasonRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> rgApprovedByRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OIDSTWJU_FIND_RGAPPROVEDBY");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				stfMemMapping);
		List<StaffMembers> lstStaffMem = new ArrayList<StaffMembers>();
		try {
			if (caseLoadId != null) {
				lstStaffMem = (List<StaffMembers>) namedParameterJdbcTemplate.query(sql,
						createParams("CASELOADID", caseLoadId), mRowMapper);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In rgApprovedByRecordGroup method : ", e);
		}
		return lstStaffMem;
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
		final String sql = getQuery("OIDSTWJU_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderIndSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderIndSchedules.class, offIndSchMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Object> offSchOnCheckDeleteMaster(final String eventId) {
		final String sql = getQuery("OIDSTWJU_OFF_SCH_ONCHECKDELETEMASTER_");
		List<Object> lstOffSchWait = new ArrayList<Object>();
		lstOffSchWait = (List<Object>) namedParameterJdbcTemplate.query(sql, createParams("EVENTID", eventId),
				new BeanPropertyRowMapper<Object>(Object.class));
		return lstOffSchWait;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProfileValue
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getProfileValue(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDSTWJU_GET_PROFILE_VALUE_2");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * showApprovedDetails
	 *
	 * @param params
	 *
	 */
	public StaffMembers showApprovedDetails(final StaffMembers paramBean) {
		final String sql = getQuery("OIDSTWJU_SHOW_APPROVED_DETAILS_");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				stfMemMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_STAFF_ID", paramBean.getStaffId()),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getParentCode
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getParentCode(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDSTWJU_GET_PARENT_CODE_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("WAITLISTSTATUS", paramBean.getCode()),
				columnRowMapper);
	}
    /**
     * Gets Current Date
     */
	public Date getCurrentDate() {
		final String sql = getQuery("OIDSTWJU_GET_CURRENT_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchPreInsert
	 *
	 *
	 */
	public Integer offSchPreInsert(final VOffenderAllSchedules offSch) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_DATE", OracleTypes.DATE), new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_RETURN", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSTWJU").withFunctionName("INS_NOTIFICATION").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offSch.getOffenderBookId());
		inParamMap.put("P_EVENT_SUB_TYPE", offSch.getEventSubType());
		inParamMap.put("P_EVENT_DATE", offSch.getEventDate());
		inParamMap.put("P_EVENT_ID", offSch.getEventId());
		inParamMap.put("P_RETURN", "true");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return Integer.parseInt(value);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkScheduleConflict
	 *
	 *
	 */
	public Integer checkScheduleConflict(final VOffenderAllSchedulesCommitBean commitBean) {
		final String sql = getQuery("OIDSTWJU_CHECK_SCHEDULE_CONFLICT");
		Integer returnList = 0;
		OffenderIndSchedules objOffIndSch = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getInsertList()) {
				objOffIndSch = new OffenderIndSchedules();
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				final String strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
				objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
				returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
						objOffIndSch.getOffenderBookId(), "EVENT_DATE", objOffIndSch.getEventDate()), Integer.class);
				if (returnList != null && returnList > 0) {
					return returnList;
				}
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getUpdateList()) {
				objOffIndSch = new OffenderIndSchedules();
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				final String strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
				objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
				returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
						objOffIndSch.getOffenderBookId(), "EVENT_DATE", objOffIndSch.getEventDate()), Integer.class);
				if (returnList != null && returnList > 0) {
					return returnList;
				}
			}
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNonAssociation
	 *
	 *
	 */
	public Integer chkNonAssociation(final OffenderIndSchedules offSch) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter(" P_AGY_LOC_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSTWJU").withFunctionName("CHK_NON_ASSOCIATION").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offSch.getOffenderBookId());
		inParamMap.put("P_AGY_LOC_ID", offSch.getToAgyLocId());
		inParamMap.put("RETURN_VALUE", OracleTypes.VARCHAR);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return Integer.parseInt(value);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkClassification
	 *
	 *
	 */
	public Integer chkClassification(final OffenderIndSchedules offSch) {
		String sql = getQuery("OIDSTWJU_CHK_CLASSIFICATION");
		int returnList = 0;
		List<Object> lstSecurities = new ArrayList<Object>();
		List<Object> lstAgy = new ArrayList<Object>();
		String strRevSubLevType = null;
		try {
			strRevSubLevType = (String) namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", offSch.getOffenderBookId()), Object.class);
		} catch (EmptyResultDataAccessException e) {
			strRevSubLevType = null;
			logger.error(this.getClass().getName()+" In chkClassification method : " , e);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" In chkClassification method : " , e);
		}

		if (strRevSubLevType != null) {
			sql = getQuery("OIDSTWJU_CHK_CLASSIFICATION_AGENCY_INTERNAL_LOCATIONS");
			lstSecurities = namedParameterJdbcTemplate.queryForList(sql,
					createParams("securityLevelCode", strRevSubLevType, "agyLocId", offSch.getToAgyLocId()),
					Object.class);
		}
		sql = getQuery("OIDSTWJU_CHK_CLASSIFICATION_AGENCY_INTERNAL_LOCATIONS_SECURITY_CODE");
		lstAgy = namedParameterJdbcTemplate.queryForList(sql, createParams("agyLocId", offSch.getToAgyLocId()),
				Object.class);

		if (strRevSubLevType != null) {
			returnList = 1;
		} else if (lstSecurities != null && lstSecurities.size() > 0) {
			returnList = 1;
		} else if (lstAgy != null && lstAgy.size() > 0) {
			returnList = 1;
		}

		return returnList;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNonAssociation
	 *
	 *
	 */
	public Integer getStaffId() {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("P_RETURN", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_UTILS").withFunctionName("GET_STAFF_ID").declareParameters(sqlParameters);
		inParamMap.put("P_RETURN", OracleTypes.VARCHAR);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return Integer.parseInt(value);
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
		final String sql = getQuery("OIDSTWJU_OFF_SCH_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNonAssociation
	 *
	 *
	 */
	public Integer insNotification(final OffenderIndSchedules offSch) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter(" P_EVENT_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter(" P_EVENT_DATE", OracleTypes.DATE),
				new SqlParameter(" P_EVENT_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_RETURN", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSTWJU").withFunctionName("INS_NOTIFICATION").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offSch.getOffenderBookId());
		inParamMap.put("P_EVENT_SUB_TYPE", offSch.getEventSubType());
		inParamMap.put("P_EVENT_DATE", offSch.getEventDate());
		inParamMap.put("P_EVENT_ID", offSch.getEventId());
		inParamMap.put("P_RETURN", OracleTypes.VARCHAR);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return Integer.parseInt(value);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNonAssociation
	 *
	 *
	 */
	public Integer delNotification(final OffenderIndSchedules offSch) {
		Integer intValue = 0;
		String sql = getQuery("OIDSTWJU_SCH_FIND_DEL_NOTIFICATION");
		final RowMapper<OffenderPendNotifications> offPenNotRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderPendNotifications.class, offPenNotiMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (offSch != null) {
			sqlQuery.append(" where ");
			sqlQuery.append(
			" OPN.SCHEDULE_ID = :eventId AND OPN.MOVEMENT_REASON_CODE = :eventSubType "
			+ " AND OPN.MOVEMENT_DATE =:eventDate AND OPN.MOVEMENT_TYPE = 'TRN'  ");
			valuesList.addValue("eventId", offSch.getEventId());
			valuesList.addValue("eventSubType", offSch.getEventSubType());
			valuesList.addValue("eventDate", offSch.getEventDate());
		}
		preparedSql = sqlQuery.toString().trim();
		List<OffenderPendNotifications> returnObj = (List<OffenderPendNotifications>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, offPenNotRM);

		if (returnObj != null && returnObj.size() > 0) {
			for (final OffenderPendNotifications offPend : returnObj) {
				sql = getQuery("OIDSTWJU_SCH_FIND_DEL_NOTIFICATION");
				intValue = namedParameterJdbcTemplate
						.queryForObject(
								sql, createParams("notiSeq", offPend.getNotiSeq(), "notiMovSeq",
										offPend.getNotiMoveSeq(), "offenderBookId", offSch.getOffenderBookId()),
								Integer.class);
				if (intValue != null && intValue > 0) {
					sql = getQuery("OIDSTWJU_UPDATE_SCH_DEL_NOTIFICATION");
					intValue = namedParameterJdbcTemplate
							.queryForObject(sql,
									createParams("notiSeq", offPend.getNotiSeq(), "notiMovSeq",
											offPend.getNotiMoveSeq(), "offenderBookId", offSch.getOffenderBookId()),
									Integer.class);
				}
			}
		} else {
			intValue = 1;
		}
		return intValue;
	}

	@Override
	public List<OffenderNonAssociations> checkNonAssociations(VOffenderAllSchedules paramBean) {
		final String sql = getQuery("OIDSTWJU_CHK_NONASSOCIATIONS");
		List<OffenderNonAssociations> returnList = new ArrayList<OffenderNonAssociations>();
		final RowMapper<OffenderNonAssociations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderNonAssociations.class, offIndSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", paramBean.getOffenderBookId()), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"in checkNonAssociations method : ", e);
		}
		return returnList;
	}

	@Override
	public List<VOffenderAllSchedules> checkOffenderScheduleConflicts(BigDecimal offenderBookId,
			VOffenderAllSchedules vOffSch) {
		final String sql = getQuery("OIDSTWJU_CHECK_SCHEDULE_CONFLICTS");
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		final RowMapper<VOffenderAllSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, offIndSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookID",offenderBookId,"eventDate",vOffSch.getEventDate(),"toAgyLocId",vOffSch.getToAgyLocId()),columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"in checkOffenderScheduleConflicts method : ", e);   
		}
		return returnList;
	}

	@Override
	public List<OffenderNaDetails> getNonAssociationOffenderListSameLocation(Integer offenderBookId) {
		final String sql = getQuery("NONASSOCIATIONS_GET_NONASSOCIATION_OFFENDERS_SAME_LOCATION");
		List<OffenderNaDetails> returnList = new ArrayList<>();
		final RowMapper<OffenderNaDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderNaDetails.class, offIndSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId),columnRowMapper);
		} catch (Exception e) {                                              
			logger.error(this.getClass().getName()+"in getNonAssociationOffenderListSameLocation method : ", e);   
		}
		return returnList;
	
}

	@Override
	public List<VOffenderAllSchedules> checkOffenderScheduleConflictsSameFacility(BigDecimal offenderBookId,
			VOffenderAllSchedules vOffSch) {
		final String sql = getQuery("OIDSTWJU_CHECK_SCHEDULE_CONFLICTS_SAME_FACILITY");
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		final RowMapper<VOffenderAllSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, offIndSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookID",offenderBookId,"eventDate",vOffSch.getEventDate(),"toAgyLocId",vOffSch.getToAgyLocId(),"agyLocId",vOffSch.getAgyLocId()),columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"in checkOffenderScheduleConflicts method : ", e);   
		}
		return returnList;
	}
	
}
