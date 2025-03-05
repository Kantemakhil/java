package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.OsanviosRepository;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcome;
import net.syscon.s4.inst.legals.beans.OffenderCommunitySentense;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

@Repository
public class OsanviosRepositoryImpl extends RepositoryBase implements OsanviosRepository {
	private static Logger logger = LogManager.getLogger(OsanviosRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("AGY_LOC_ID", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> courtEvMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("RESULT_CODE", new FieldMapper("resultCode"))
			.put("COURT_EVENT_TYPE", new FieldMapper("hearingReason")).put("JUDGE_NAME", new FieldMapper("judgeName"))
			.put("START_TIME", new FieldMapper("startTime")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome")).put("CASE_ID", new FieldMapper("caseId"))
			.put("EVENT_ID", new FieldMapper("eventId")).put("OUTCOME_DATE", new FieldMapper("outcomeDate"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
			.put("AGY_LOC_ID", new FieldMapper("court")).put("END_TIME", new FieldMapper("endTime"))
			.put("sentence_seq", new FieldMapper("sentenseSeq"))
			.put("ORDER_TYPE", new FieldMapper("orderType"))
			.put("recommended_sanction_count", new FieldMapper("recommendedSanctionCount"))
			.put("recommended_reward_count", new FieldMapper("recommendedRewardCount"))
			.build();
	private final Map<String, FieldMapper> sentenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ID", new FieldMapper("Id")).put("FORM_INFO_JSON", new FieldMapper("formInfoJson"))
			.put("FORM_IDENTIFIER", new FieldMapper("formIdentifier"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> vOffenderAllSchedulesExeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_TYPE", new FieldMapper("eventType")).put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("EMAIL_FLAG",                   new FieldMapper("emailFlag"))
			.put("SMS_FLAG",                     new FieldMapper("smsFlag"))
			.put("SMS_SCHEDULE_HOURS_BEFORE",    new FieldMapper("smsScheduleHoursBefore"))
			.put("EMAIL_SCHEDULE_HOURS_BEFORE",  new FieldMapper("emailScheduleHoursBefore"))
			.put("EMAIL_FLAG_TEMP",              new FieldMapper("emailFlagTemp"))
			.put("SMS_FLAG_TEMP",                new FieldMapper("smsFlagTemp"))
			.put("NON_ASSOCIATION_FLAG",                new FieldMapper("nonAssociationFlag"))
			.build();

	@Override
	public List<AgencyLocations> hearingreasonRecordGroup() {
		final String sql = getQuery("OSANVIOS_HEARING_REASON_RECORDGROUP");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in OsanviosRepositoryImpl class hearingreasonRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<>();
		final String sql = getQuery("OSANVIOS_COURT_EVENTS_COURT_DATA");
		final RowMapper<Court> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Court.class, agyLocMapping);
		try {
			courtList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class populateCourtData : ", e);
		}
		return courtList;
	}

	public List<CourtEvents> crtEveExecuteQuery(final CourtEvents objSearchDao) {
		final String sql = getQuery("OSANVIOS_CRTEVE_FIND_COURT_EVENTS");
		final RowMapper<CourtEvents> courtEvRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtEvents.class,
				courtEvMapping);
		List<CourtEvents> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate
					.query(sql,
							createParams("offender_book_id", objSearchDao.getOffenderBookId(), "sentenseSeq",
									objSearchDao.getSentenseSeq(), "orderType", objSearchDao.getOrderType()),
							courtEvRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class crtEveExecuteQuery : ", e);
		}
		return returnList;
	}

	public Integer crtEveInsertCourtEvents(final List<CourtEvents> lstCourtEvents) {
		int returnValue = 0;
		final String sql = getQuery("OSANVIOS_CRTEVE_INSERT_COURT_EVENTS_PRE_EVENT_ID");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents courtEvents : lstCourtEvents) {
			courtEvents.setEventId(crtEvePreInsert());
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class crtEveInsertCourtEvents : ", e);
		}
		if (lstCourtEvents.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	public Integer crtEveUpdateCourtEvents(final List<CourtEvents> lstCourtEvents) {
		final String sql = getQuery("OSANVIOS_CRTEVE_UPDATE_COURT_EVENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents courtEvents : lstCourtEvents) {
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class crtEveUpdateCourtEvents : ", e);
		}
		if (lstCourtEvents.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	public Integer crtEvePreInsert() {
		final String sql = getQuery("OSANVIOS_CRT_EVE_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public List<OffenderCommunitySentense> retriveSentenceData(OffenderCommunitySentense searchBean) {
		String sql = getQuery("OSANVIOS_GET_SENTENCE_DATA");
		try {
			final RowMapper<OffenderCommunitySentense> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderCommunitySentense.class, sentenceMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", searchBean.getOffenderBookId()),
					sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class retriveSentenceData : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<CourtEvnetAppointmentOutcome> appointMentsData(CourtEvnetAppointmentOutcome searchBean) {
		String sql = getQuery("OSANVIOS_GET_APPOINTMENTS_DATA");
		try {
			final RowMapper<CourtEvnetAppointmentOutcome> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					CourtEvnetAppointmentOutcome.class, vOffenderAllSchedulesExeMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", searchBean.getOffenderBookId(), "courtEventDate",
							searchBean.getCourtEventDate(), "court_event_id", searchBean.getCourtEventId()),
					sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class appointMentsData : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer crtEventAppointmentInsertCourtEvents(List<CourtEvnetAppointmentOutcome> insertList) {
		int returnValue = 0;
		final String sql = getQuery("OSANVIOS_CRTEVE_INSERT_COURT_EVENTS_APPOINTMENTS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvnetAppointmentOutcome courtEvents : insertList) {
			courtEvents.setCourtEvntSanctDtlId(crtEveAppointPreInsert());
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class crtEventAppointmentInsertCourtEvents : ", e);
		}
		if (insertList.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	@Override
	public Integer crtEventAppointmentUpdateCourtEvents(List<CourtEvnetAppointmentOutcome> updateList) {
		final String sql = getQuery("OSANVIOS_CRTEVE_UPDATE_COURT_EVENTS_APPOINTMENTS_DATA");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvnetAppointmentOutcome courtEvents : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class crtEventAppointmentUpdateCourtEvents : ", e);
		}
		if (updateList.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	public BigDecimal crtEveAppointPreInsert() {
		final String sql = getQuery("OSANVIOS_CRT_EVE_APPOINT_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public CourtEvnetAppointmentOutcome getDatabaseCourtEventAppointmentsData(
			CourtEvnetAppointmentOutcome inputParamBean) {

		final String sql = getQuery("OSANVIOS_COURT_EVENT_OUTCOME_APPOINTMENT_EVENT_DATA");
		CourtEvnetAppointmentOutcome returnObject = new CourtEvnetAppointmentOutcome();
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("courtEventId", inputParamBean.getCourtEventId(), "sessionEventId",
							inputParamBean.getSessionEventId()),
					new BeanPropertyRowMapper<CourtEvnetAppointmentOutcome>(CourtEvnetAppointmentOutcome.class));
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class getDatabaseCourtEventAppointmentsData : ", e);
			return returnObject;
		}

		return returnObject;
	}

	@Override
	public String getDefaultCancellationReason() {
		final String sql = getQuery("OSANVIOS_GET_DEFAULT_CANCELATION_REASON");
		String cancelReason = null;
		try {
			cancelReason = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			cancelReason = null;
			logger.error("In getDefaultCancellationReason method : ", e);
		}
		return cancelReason;
	}

	@Override
	public Integer countOutcomereasonCodes(Date eventDate, Integer offenderBookId) {
		final String sql = getQuery("OSANVIOS_COUNT_OUTCOME_REASON");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "courtEventDate", eventDate), Integer.class);
		} catch (Exception e) {
			logger.error("In countOutcomereasonCodes method : ", e);
			count = null;
		}
		return (count != null && count > 0) ? 1 : 0;
	}

	@Override
	public Integer getLinkedCourtEventData(BigDecimal eventId) {
		final String sql = getQuery("OSANVIOS_COUNT_COURT_EVENT_LINK_DATA");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", eventId), Integer.class);
		} catch (Exception e) {
			logger.error("In getLinkedCourtEventData method : ", e);
			count = null;
		}
		return (count != null && count > 0) ? 1 : 0;
	}

	public List<CourtEvnetAppointmentOutcome> progOutComeEecuteQuery(CourtEvnetAppointmentOutcome objSearchDao) {
		final String sql = getQuery("OSANVIOS_PROGRAM_OUTCOME_DATA");
		ArrayList<CourtEvnetAppointmentOutcome> returnList = null;
		try {
			returnList = (ArrayList<CourtEvnetAppointmentOutcome>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId(), "courtEventDate",
							objSearchDao.getEventDate(),"court_event_id",objSearchDao.getCourtEventId()),
					new BeanPropertyRowMapper<CourtEvnetAppointmentOutcome>(CourtEvnetAppointmentOutcome.class));
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName() + " progOutComeEecuteQuery in error ");
		}
		return returnList;
	}

	public List<CourtEvnetAppointmentOutcome> progAppointmentEecuteQuery(CourtEvnetAppointmentOutcome objSearchDao) {
		final String sql = getQuery("OSANVIOS_PROGRAM_APPOINTMENTS_DATA");
		ArrayList<CourtEvnetAppointmentOutcome> returnList = null;
		try {
			returnList = (ArrayList<CourtEvnetAppointmentOutcome>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId(), "courtEventDate",
							objSearchDao.getEventDate(),"court_event_id",objSearchDao.getCourtEventId()),
					new BeanPropertyRowMapper<CourtEvnetAppointmentOutcome>(CourtEvnetAppointmentOutcome.class));
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName() + " progAppointmentEecuteQuery in error ");
		}
		return returnList;
	}

	public List<CourtEvnetAppointmentOutcome> comServiceEecuteQuery(CourtEvnetAppointmentOutcome objSearchDao) {
		final String sql = getQuery("OSANVIOS_COMMUNITY_SERVICE_DATA");
		ArrayList<CourtEvnetAppointmentOutcome> returnList = null;
		try {
			returnList = (ArrayList<CourtEvnetAppointmentOutcome>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId(), "courtEventDate",
							objSearchDao.getEventDate(),"court_event_id",objSearchDao.getCourtEventId()),
					new BeanPropertyRowMapper<CourtEvnetAppointmentOutcome>(CourtEvnetAppointmentOutcome.class));
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName() + " comServiceEecuteQuery in error ");
		}
		return returnList;
	}

	@Override
	public CourtEvnetAppointmentOutcome getCrtEvntSancDetailsData(CourtEvnetAppointmentOutcome obj) {

		final String sql = getQuery("OSANVIOS_GET_COURT_EVNT_SANCTIONS_DETAILS_DATA");
		CourtEvnetAppointmentOutcome returnObject = new CourtEvnetAppointmentOutcome();
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("courtEvntSanctDtlId", obj.getCourtEvntSanctDtlId()),
					new BeanPropertyRowMapper<CourtEvnetAppointmentOutcome>(CourtEvnetAppointmentOutcome.class));
		} catch (Exception e) {
			logger.error("Exception in OsanviosRepositoryImpl class getCrtEvntSancDetailsData : ", e);
			return returnObject;
		}

		return returnObject;
	}
	
}
