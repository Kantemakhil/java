package net.syscon.s4.cm.weeklyactivityplans.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.weeklyactivityplans.OffenderEmTagDetails;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnRepository;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlansHty;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

@Repository
public class OweacplnRepositoryImpl extends RepositoryBase implements OweacplnRepository {

	private static Logger logger = LogManager.getLogger(OweacplnRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> weeklyPlanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("EVENT_END_DATE", new FieldMapper("eventEndDate"))
			.put("EVENT_SEQ", new FieldMapper("eventSeq")).put("EVENT_TYPE", new FieldMapper("eventType"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("SYSTEM_EVENT_ID", new FieldMapper("systemEventId"))
			.put("HTY_VERSION_NO", new FieldMapper("htyVersionNo"))
			.put("EVENT_ID_TEMP", new FieldMapper("eventIdTemp"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId"))
			.build();

	private final Map<String, FieldMapper> emDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("em_tag_id", new FieldMapper("emTagId"))
			.put("em_tag_strap_size", new FieldMapper("emTagStrapSize"))
			.put("offender_book_id", new FieldMapper("offenderBookId"))
			.put("em_daily_charging_period", new FieldMapper("emTagDailyChargingPeriod"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))			
			.put("EM_TAG_START_TIME", new FieldMapper("emTagStartTime"))
			.put("EM_TAG_END_TIME", new FieldMapper("emTagEndTime"))
			.put("SYSTEM_EVENT_ID", new FieldMapper("systemEventId"))
			.put("EM_TAG_DATA", new FieldMapper("emTagData")).build();

	private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("SICK_NOTE_EXPIRY_DATE", 						new FieldMapper("sickNoteExpiryDate"))
			.put("TO_INT_LOC_LEVEL_1_CODE", 						new FieldMapper("toIntLocLevel1Code"))
			.put("LU_LEVEL_3_CODE", 						new FieldMapper("luLevel3Code"))
			.put("TO_CITY_NAME", 						new FieldMapper("toCityName"))
			.put("PROV_STATE_DESC", 						new FieldMapper("provStateDesc"))
			.put("TO_AGY_LOC_ID", 						new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID", 						new FieldMapper("toAddressId"))
			.put("UNPAID_WORK_SUPERVISOR", 						new FieldMapper("unpaidWorkSupervisor"))
			.put("LIVING_UNIT_ID", 						new FieldMapper("livingUnitId"))
			.put("UNPAID_WORK_ACTION", 						new FieldMapper("unpaidWorkAction"))
			.put("AGENCY_IML_LEVEL_1_CODE", 						new FieldMapper("agencyImlLevel1Code"))
			.put("ESCORT_CODE", 						new FieldMapper("escortCode"))
			.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
			.put("EVENT_DATE", 						new FieldMapper("eventDate"))
			.put("TO_INT_LOC_USER_DESC", 						new FieldMapper("toIntLocUserDesc"))
			.put("EVENT_STATUS_DESC", 						new FieldMapper("eventStatusDesc"))
			.put("TO_INTERNAL_LOCATION_DESC", 						new FieldMapper("toInternalLocationDesc"))
			.put("AGY_LOC_DESC", 						new FieldMapper("agyLocDesc"))
			.put("LU_LEVEL_2_CODE", 						new FieldMapper("luLevel2Code"))
			.put("FROM_CITY_CODE", 						new FieldMapper("fromCityCode"))
			.put("EVENT_SUB_TYPE", 						new FieldMapper("eventSubType"))
			.put("DETAILS", 						new FieldMapper("details"))
			.put("CHECK_BOX_1", 						new FieldMapper("checkBox1"))
			.put("BUSY_DATE_FLAG", 						new FieldMapper("busyDateFlag"))
			.put("EVENT_OUTCOME_DESC", 						new FieldMapper("eventOutcomeDesc"))
			.put("SCHEDULED_TRIP_ID", 						new FieldMapper("scheduledTripId"))
			.put("EVENT_SUB_TYPE_DESC", 						new FieldMapper("eventSubTypeDesc"))
			.put("HIDDEN_COMMENT_TEXT", 						new FieldMapper("hiddenCommentText"))
			.put("IN_CHARGE_STAFF_NAME", 						new FieldMapper("inChargeStaffName"))
			.put("TO_INT_LOC_LEVEL_2_CODE", 						new FieldMapper("toIntLocLevel2Code"))
			.put("TRANSPORT_CODE", 						new FieldMapper("transportCode"))
			.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
			.put("EVENT_TYPE_DESC", 						new FieldMapper("eventTypeDesc"))
			.put("AGENCY_IML_LEVEL_2_CODE", 						new FieldMapper("agencyImlLevel2Code"))
			.put("IN_TIME", 						new FieldMapper("inTime"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("OFF_PRGREF_ID", 						new FieldMapper("offPrgrefId"))
			.put("SICK_NOTE_RECEIVED_DATE", 						new FieldMapper("sickNoteReceivedDate"))
			.put("CONTACT_PERSON_NAME", 						new FieldMapper("contactPersonName"))
			.put("OUT_TIME", 						new FieldMapper("outTime"))
			.put("AGREED_TRAVEL_HOUR", 						new FieldMapper("agreedTravelHour"))
			.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
			.put("EVENT_TYPE", 						new FieldMapper("eventType"))
			.put("REFERENCE_ID", 						new FieldMapper("referenceId"))
			.put("PROV_STATE_CODE", 						new FieldMapper("provStateCode"))
			.put("LU_LEVEL_1_CODE", 						new FieldMapper("luLevel1Code"))
			.put("BOOKING_NO", 						new FieldMapper("bookingNo"))
			.put("AGENCY_IML_DESC", 						new FieldMapper("agencyImlDesc"))
			.put("TO_INT_LOC_LEVEL_3_CODE", 						new FieldMapper("toIntLocLevel3Code"))
			.put("CHECK_BOX_2", 						new FieldMapper("checkBox2"))
			.put("TO_AGY_LOC_DESC", 						new FieldMapper("toAgyLocDesc"))
			.put("END_TIME", 						new FieldMapper("endTime"))
			.put("TO_LOC", 						new FieldMapper("toLoc"))
			.put("EVENT_CLASS", 						new FieldMapper("eventClass"))
			.put("RETURN_DATE", 						new FieldMapper("returnDate"))
			.put("depart_start_time", 						new FieldMapper("departStartTime"))
			.build();
	
	@Override
	public List<WeeklyActivityPlans> getWeeklyActivity(WeeklyActivityPlans searchBean) {
		String sql = getQuery("OWEACPLN_GET_WEEKLY_PLANS");
		try {
			final RowMapper<WeeklyActivityPlans> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					WeeklyActivityPlans.class, weeklyPlanMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("fromDate", searchBean.getFromDate(), "toDate",
					searchBean.getToDate(), "offenderBookId", searchBean.getOffenderBookId()), systemEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getWeeklyActivity ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer emDetailsInsertData(List<OffenderEmTagDetails> insertList) {
		final String sql = getQuery("OWEACPLN_EM_DETAILS_INSERT_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderEmTagDetails list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public Integer emDetailsUpdateData(List<OffenderEmTagDetails> updateList) {
		final String sql = getQuery("OWEACPLN_EM_DETAILS_UPDATE_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderEmTagDetails sentenceCalcTypes : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("emDetailsUpdateData: ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<OffenderEmTagDetails> retrieveEmDetails(OffenderEmTagDetails searchBean) {
		String sql = getQuery("OWEACPLN_GET_OFFENDER_EM_DETAILS");
		try {
			final RowMapper<OffenderEmTagDetails> emDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderEmTagDetails.class, emDetailsMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", searchBean.getOffenderBookId()),
					emDetailsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " retrieveEmDetails ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer weeklyActivityCommitInsertData(List<WeeklyActivityPlans> insertList) {
		final String sql = getQuery("OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_INSERT_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final WeeklyActivityPlans list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " weeklyActivityCommitInsertData ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public Integer weeklyActivityCommitUpdateData(List<WeeklyActivityPlans> updateList) {
		final String sql = getQuery("OWEACPLN_EM_WEEKLY_PLAN_ACTIVITY_DETAILS_UPDATE_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WeeklyActivityPlans sentenceCalcTypes : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("emDetailsUpdateData: ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer weeklyActivityDeleteData(List<WeeklyActivityPlans> updateList) {
		final String sql = getQuery("OWEACPLN_EM_WEEKLY_PLAN_ACTIVITY_DETAILS_DELETE_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WeeklyActivityPlans sentenceCalcTypes : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			String tableName = "WEEKLY_ACTIVITY_PLAN";
			String whereClause = "WEEKLY_ACTIVITY_PLAN_ID =:weeklyActivityPlanId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method weeklyActivityDeleteData", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senCalcDeleteSentenceCalcTypes : ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer weeklyActivityHtyCommitInsertData(List<WeeklyActivityPlansHty> insertList) {
		final String sql = getQuery("OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_HTY_INSERT_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WeeklyActivityPlansHty sentenceCalcTypes : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("emDetailsUpdateData: ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public List<WeeklyActivityPlansHty> getWeeklyActivityHty(WeeklyActivityPlansHty searchBean) {
		String sql = getQuery("OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_HTY_RETRIEVE");
		try {
			final RowMapper<WeeklyActivityPlansHty> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					WeeklyActivityPlansHty.class, weeklyPlanMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",searchBean.getOffenderBookId(),"wapStartDate",searchBean.getWapStartDate(),"wapEndDate",searchBean.getWapEndDate(),"versionNo",searchBean.getVersionNo()), systemEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getWeeklyActivity ", e);
			return Collections.emptyList();
		}
	}
	
	
	@Override
	public Integer weeklyActivityCommentInsertList(List<WeeklyActivityPlansHty> insertList) {
		final String sql = getQuery("OWEACPLN_WAP_COMMENTS_INSERT_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WeeklyActivityPlansHty sentenceCalcTypes : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("weeklyActivityCommentInsertList: ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer weeklyActivityCommentUpdateList(List<WeeklyActivityPlansHty> updateList) {
		final String sql = getQuery("OWEACPLN_WAP_COMMENTS_UPDATE_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WeeklyActivityPlansHty sentenceCalcTypes : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("weeklyActivityCommentUpdateList: ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
@Override
	public VHeaderBlock getClientDetails(Long offenderBookId, String userId) {
		final String sql = getQuery("OWEACPLN_OFFENDER_DETAILS_OBJECT");
		VHeaderBlock returnObject = new VHeaderBlock();
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "userId", userId),
					new BeanPropertyRowMapper<VHeaderBlock>(VHeaderBlock.class));
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class getDatabaseCourtEventAppointmentsData : ", e);
			return returnObject;
		}

		return returnObject;
	}

	@Override
	public String getWapComment(WeeklyActivityPlansHty bean) {
		final String sql = getQuery("OWEACPLN_GET_WAP_COMMENTS");
		String comment = null;
		try {
			comment = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", bean.getOffenderBookId(), "wapStartDate", bean.getWapStartDate(),
							"wapEndDate", bean.getWapEndDate()),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getWapComment : ", e);
			comment="notfound";
		}
		return comment;
	}
	
	@Override
	public BigDecimal getMaxVersionNo(WeeklyActivityPlansHty weeklyActivityPlansHty) {
		final String sql = getQuery("OWECPLN_GET_MAX_VERSION_NO");
		BigDecimal versionNo = null;
		try {
			versionNo = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", weeklyActivityPlansHty.getOffenderBookId(), "wapStartDate", weeklyActivityPlansHty.getWapStartDate(),
							"wapEndDate", weeklyActivityPlansHty.getWapEndDate()),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getMaxVersionNo : ", e);
		}
		return versionNo;
	}
	
	@Override
	public List<WeeklyActivityPlans> getWeeklyActivityHtyMaxData(WeeklyActivityPlansHty searchBean) {
		String sql = getQuery("OWEACPLN_WEEKLY_PLAN_ACTIVITY_DETAILS_HTY_RETRIEVE_MAX_DATA");
		try {
			final RowMapper<WeeklyActivityPlans> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					WeeklyActivityPlans.class, weeklyPlanMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",searchBean.getOffenderBookId(),"wapStartDate",searchBean.getWapStartDate(),"wapEndDate",searchBean.getWapEndDate(),"versionNo",searchBean.getVersionNo()), systemEventsRowMapper); 
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getWeeklyActivityHtyMaxData ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public String getModeOfTransportDesc(String modeOfTransport) {		
		final String sql = getQuery("OWEACPLN_GET_MODE_OF_TRANSPORT_DESCRIPTION");
		String returnList = null;
		try {		
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("modeOfTransport",
					modeOfTransport), String.class);
		} catch(Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getModeOfTransportDesc ", e);
			return returnList;
		}
		return returnList;
	}
	
	@Override
	public BigDecimal getMaxHtyVersion(WeeklyActivityPlans searchBean) {
		String sql = getQuery("OWEACPLN_GET_MAX_HTY_VERSION");
		BigDecimal returnVal = BigDecimal.ZERO;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("fromDate", searchBean.getFromDate(), "toDate",
					searchBean.getToDate(), "offenderBookId", searchBean.getOffenderBookId()), BigDecimal.class);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getMaxHtyVersion ", e);
		}
		return returnVal;
	}

	@Override
	public Integer weeklyActivityCommitUpdateParentData(List<WeeklyActivityPlansHty> updateList) {
		final String sql = getQuery("OWEACPLN_WEEKLY_PLAN_PARENT_DATA_UPDATE_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WeeklyActivityPlansHty sentenceCalcTypes : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("emDetailsUpdateData: ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public List<WeeklyActivityPlans> getPreviousWeekData(Date previousWeekStartDate, Date currentWeekStartDate,BigDecimal offenderBookId) {
		String sql = getQuery("OWEACPLN_COPY_OVER_PREVIOUS_DATA");
		try {
			final RowMapper<WeeklyActivityPlans> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					WeeklyActivityPlans.class, weeklyPlanMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("previousWeekStartDate", previousWeekStartDate, "currentWeekStartDate",
					currentWeekStartDate, "offenderBookId", offenderBookId), systemEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getPreviousWeekData ", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public Integer copyOverCommit(List<WeeklyActivityPlans> insertList) {
		final String sql = getQuery("OWEACPLN_COPY_OVER_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final WeeklyActivityPlans list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " copyOverCommit ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public List<StaffMembers> userDetails(String userId) {
		String sql = getQuery("OWEACPLN_GET_USER_DETAILS");
		try {
			final RowMapper<StaffMembers> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					StaffMembers.class, weeklyPlanMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("userId", userId), systemEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " userDetails ", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public String getActivityDescriptionValue(String  activityNew) {		
		final String sql = getQuery("OWEACPLN_GET_MODE_OF_ACTIVITY_DESCRIPTION");
		String returnList = null;
		try {		
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("activityNew",
					activityNew), String.class);
		} catch(Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getModeOfTransportDesc ", e);
			return returnList;
		}
		return returnList;
	}

	@Override
	public List<VOffenderAllSchedules> getWapManualActivities(VOffenderAllSchedules obj) {
		String sql = getQuery("OWEACPLN_GET_MANUALLY_ENTERED_ACTIVITIES_DATA");
		try {
			final RowMapper<VOffenderAllSchedules> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", obj.getOffenderBookId()), systemEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getWapManualActivities ", e);
			return Collections.emptyList();
	}
	}
	
	@Override
	public List<VOffenderAllSchedules> getWapManualActivitiesBasedOnEventDate(VOffenderAllSchedules obj,List<Date> eventDate) {
		String sql = getQuery("OWEACPLN_GET_MANUALLY_ENTERED_ACTIVITIES_BASED_EVENT_DATA");
		try {
			final RowMapper<VOffenderAllSchedules> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", obj.getOffenderBookId(), "EVENTDATE",eventDate), systemEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getWapManualActivities ", e);
			return Collections.emptyList();
	}
	}

	@Override
	public List<WeeklyActivityPlans> getActiveWapList(final Long programInstanceId, final Long phaseInstanceId, final Long crsChId) {
		String sql = getQuery("OWEACPLN_GET_CLEAR_SESSION_DATA");
		try {
			final RowMapper<WeeklyActivityPlans> systemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					WeeklyActivityPlans.class, weeklyPlanMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("p_program_instance_id", programInstanceId,
					"p_phase_instance_id", phaseInstanceId, "p_crs_sch_id", crsChId), systemEventsRowMapper);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getWeeklyActivity ", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public BigDecimal getMaxHtyVersionData(WeeklyActivityPlans searchBean) {
		String sql = getQuery("OWEACPLN_GET_MAX_VERSION_DATA");
		BigDecimal returnVal = BigDecimal.ZERO;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("fromDate", searchBean.getFromDate(), "toDate",
					searchBean.getToDate(), "offenderBookId", searchBean.getOffenderBookId()), BigDecimal.class);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getMaxHtyVersion ", e);
		}
		return returnVal;
	}

	@Override
	public Integer getWeeklyActivityHtyDelete(List<WeeklyActivityPlans> deleteList) {
		final String sql = getQuery("OWEACPLN_EM_WEEKLY_PLAN_HISTORY_ACTIVITY_DETAILS_DELETE_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WeeklyActivityPlans sentenceCalcTypes : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			String tableName = "WEEKLY_ACTIVITY_PLAN_HTY";
			String whereClause = "WEEKLY_ACTIVITY_PLAN_ID =:weeklyActivityPlanId AND VERSION_NO=:versionNo";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method weeklyActivityDeleteData", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senCalcDeleteSentenceCalcTypes : ", e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public BigDecimal getHistoryTableMaxVersion(WeeklyActivityPlans searchBean) {
		String sql = getQuery("OWEACPLN_GET_MAX_HISTORY_VERSION_DATA");
		BigDecimal returnVal = BigDecimal.ZERO;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("fromDate", searchBean.getFromDate(), "toDate",
					searchBean.getToDate(), "offenderBookId", searchBean.getOffenderBookId()), BigDecimal.class);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getHistoryTableMaxVersion ", e);
		}
		return returnVal;
	}
	
	
}
