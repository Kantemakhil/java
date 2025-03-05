package net.syscon.s4.pkgs.tag_contact_log.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderCaseNoteSent;
import net.syscon.s4.common.beans.OffenderIndSchSent;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.tag_contact_log.TagContactLogRepository;

@Repository
public class TagContactLogRepositoryImpl extends RepositoryBase implements TagContactLogRepository {

	private static Logger logger = LogManager.getLogger(TagContactLogRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	@Override
	public Integer deleteVirtualSchedule(final VOffenderAllSchedules lstVOffenderAllSchedules2) {
		final String sql = getQuery("UPDATE_V_OFFENDER_ALL_SCHEDULES_2");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstVOffenderAllSchedules2));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("activeSentaCur :" + e);
			return 0;
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffenderSentences> activeSentaCur(final Long offenderBookId) {
		final String sql = getQuery("ACTIVE_SENTA_CUR");
		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", offenderBookId), rowMapper);
	}

	@Override
	public Integer insertOffenderCaseNoteSen(final OffenderCaseNoteSent bean) {
		final String sql = getQuery("INSERT_OFFENDER_CASE_NOTE_SENTE");
		final OffenderCaseNoteSent offCaNoteSen = new OffenderCaseNoteSent();
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offCaNoteSen));
	}

	@Override
	public Integer offenderIndSchSentens(final OffenderIndSchSent oofIndSch) {
		final String sql = getQuery("INSERT_OFFENNDER_IND_SCH");
		final OffenderIndSchSent offInd = new OffenderIndSchSent();
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offInd));
	}

	@Override
	public Integer updateScheduleForPhysicalRecord(final VOffenderAllSchedules offsch) {
		final String sql = getQuery("UPDATE_SCHEDULE_UPDATING_PHYSICAL_RECORDS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("P_EVENT_DATE", offsch.getEventDate(), "P_START_TIME", offsch.getStartTime(),
							"P_END_TIME", offsch.getEndTime(), "P_AGY_LOC_ID", offsch.getToAgyLocId(),
							"P_IN_CHARGE_STAFF_ID", offsch.getInChargeStaffId(), "P_COMMENT_TEXT",
							offsch.getCommentText(), "P_EVENT_OUTCOME", offsch.getEventOutcome(), "P_FLAG",
							offsch.getUnexcusedAbsenceFlag(), "P_EVENT_STATUS", offsch.getEventStatus(), "P_EVENT_ID",
							offsch.getEventId(), "smsScheduleHoursBefore", offsch.getSmsScheduleHoursBefore(),
							"emailScheduleHoursBefore", offsch.getEmailScheduleHoursBefore(), "emailFlag",
							offsch.getEmailFlag(), "smsFlag", offsch.getSmsFlag(), "emailSentFlag",
							offsch.getEmailSentFlag(), "smsSentFlag", offsch.getSmsSentFlag()));
		} catch (Exception e) {
			logger.error("updateScheduleForPhysicalRecord :", e);
		}
		return count;
	}

	@Override
	public Integer updateScheduleForVirtualRecords(final VOffenderAllSchedules offsch) {
		final String sql = getQuery("UPDATE_SCHEDULE_UPDATING_VIRTUAL_RECORDS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("P_EVENT_DATE", offsch.getEventDate(), "P_START_TIME", offsch.getStartTime(),
							"P_END_TIME", offsch.getEndTime(), "P_AGY_LOC_ID", offsch.getToAgyLocId(),
							"P_IN_CHARGE_STAFF_ID", offsch.getInChargeStaffId(), "P_COMMENT_TEXT",
							offsch.getCommentText(), "P_EVENT_OUTCOME", offsch.getEventOutcome(), "P_FLAG",
							offsch.getUnexcusedAbsenceFlag(), "P_EVENT_STATUS", offsch.getEventStatus(),
							"P_OFF_BOOK_ID", offsch.getOffenderBookId(), "P_EVENT_TYPE", offsch.getEventType(),
							"P_REF_ID", offsch.getReferenceId(), "smsScheduleHoursBefore",
							offsch.getSmsScheduleHoursBefore(), "emailScheduleHoursBefore",
							offsch.getEmailScheduleHoursBefore(), "emailFlag", offsch.getEmailFlag(), "smsFlag",
							offsch.getSmsFlag(), "emailSentFlag", offsch.getEmailSentFlag(), "smsSentFlag",
							offsch.getSmsSentFlag()));
		} catch (Exception e) {
			logger.error("updateScheduleForVirtualRecords :", e);
		}
		return count;
	}

	@Override
	public Integer deleteNoteschAss(final Long pOffenderBookId, final BigDecimal pSchId,String modifyUserId) {
		final String sql = getQuery("DEL_NOTE_SCH_ASSOCIATION");
		Integer retValue =null;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_IND_SCH_SENTS";
			String whereCondition = "EVENT_ID = :P_SCH_ID AND OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID";
			inputMap.put("P_SCH_ID", pSchId);
			inputMap.put("P_OFFENDER_BOOK_ID", pOffenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteNoteschAss " + e.getMessage());
		}
		try {
			retValue = namedParameterJdbcTemplate.update(sql,
					createParams("P_SCH_ID", pSchId, "P_OFFENDER_BOOK_ID", pOffenderBookId));
		} catch (Exception e) {
			logger.error("deleteNoteschAss :" + e);
			retValue = 0;
		}
		return 1;
	}

	@Override
	public String getOutcomeFlagCur(final String pEventType, final String pEventSubType, final String pEventOutcome) {
		final String sql = getQuery("GET_OUTCOME_FLAG_CUR");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_event_type", pEventType,
					"p_event_sub_type", pEventSubType, "p_event_outcome", pEventOutcome), String.class);
		} catch (Exception e) {
			logger.error("getOutcomeFlagCur :" + e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public String getModuleNameFmWork(final String pContactType, final String pContactSubType) {
		final String sql = getQuery("GET_MODULE_NAME");
		String moduleName = null;
		try {
			moduleName = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CONTACT_TYPE", pContactType, "P_CONTACT_SUB_TYPE", pContactSubType), String.class);
		} catch (Exception e) {
			logger.error("getModuleNameFmWork :", e);
		}
		return moduleName;
	}

}