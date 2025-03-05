package net.syscon.s4.pkgs.tag_multiple_failure.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.tag_multiple_failure.TagMultipleFailureRepository;

@Repository
public class TagMultipleFailureRepositoryImpl extends RepositoryBase implements TagMultipleFailureRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(TagMultipleFailureRepositoryImpl.class.getName());

	/**
	 * Creates new AdjustUaRepositoryImpl class Object
	 */
	public TagMultipleFailureRepositoryImpl() {
	}

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("STAFFID", new FieldMapper("staffId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	@Override
	public Integer checkUaEventOutcomeOld(final VOffenderAllSchedules obj) {
		int count = 0;
		final String sql = getQuery("CHECK_UA_EVENT_OUTCOME_OLD");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventType", obj.getEventType(),
					"eventSubType", obj.getEventSubType(), "eventOutcome", obj.getEventOutcomeDb()), Integer.class);
		} catch (Exception e) {
			logger.error("checkUaEventOutcomeOld", e);
		}
		return count;
	}

	@Override
	public Integer checkUaEventOutcomeNew(final VOffenderAllSchedules obj) {
		int count = 0;
		final String sql = getQuery("CHECK_UA_EVENT_OUTCOME_NEW");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventType", obj.getEventType(),
					"eventSubType", obj.getEventSubType(), "eventOutcome", obj.getEventOutcome()), Integer.class);
		} catch (Exception e) {
			logger.error("checkUaEventOutcomeNew", e);
		}
		return count;
	}

	@Override
	public List<VOffenderAllSchedules> osuaCurSelectOperation(final BigDecimal eventId) {
		final String sql = getQuery("OCDCLOGS_OSUA_CUR_SELECT_OPERATION");
		List<VOffenderAllSchedules> returnList = new ArrayList<VOffenderAllSchedules>();

		final RowMapper<VOffenderAllSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("eventId", eventId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("osuaCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public Integer osuaRecDeleteOperation(final VOffenderAllSchedules obj) {
		final String sql = getQuery("OCDCLOGS_OSUA_REC_DELETE_OPERATION");
		final List<VOffenderAllSchedules> list = new ArrayList<VOffenderAllSchedules>();
		list.add(obj);

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final VOffenderAllSchedules offSch : list) {
			parameters.add(new BeanPropertySqlParameterSource(offSch));
		}
		try {
			String tableName = "OFFENDER_SENTENCE_UA_EVENTS";
			String whereCondition = "OFFENDER_BOOK_ID = :offenderBookId AND SENTENCE_SEQ = :sentenceSeq AND EVENT_ID =:eventId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer mfADdjustSelectOperation(final VOffenderAllSchedules obj) {
		final String sql = getQuery("OCDCLOGS_MF_ADJUST_SELECT_OPERATION");
		List<VOffenderAllSchedules> returnList = new ArrayList<VOffenderAllSchedules>();

		final RowMapper<VOffenderAllSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", obj.getOffenderBookId(), "sentenceSeq", obj.getSentenceSeq()),
					rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("mfADdjustSelectOperation", e);
		}
		if (returnList.size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer mfDAdjustUpdate(final VOffenderAllSchedules obj) {
		final String sql = getQuery("OCDCLOGS_MFD_ADJUST_UPDATE");
		int[] returnArray = new int[] {};
		final List<VOffenderAllSchedules> list = new ArrayList<VOffenderAllSchedules>();
		list.add(obj);

		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final VOffenderAllSchedules vOffSch : list) {
			parameters.add(new BeanPropertySqlParameterSource(vOffSch));
		}

		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("mfDAdjustUpdate:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer mfDAdjustInsert(final VOffenderAllSchedules obj) {
		final List<VOffenderAllSchedules> list = new ArrayList<VOffenderAllSchedules>();
		list.add(obj);
		final String sql = getQuery("OCDCLOGS_MFD_ADJUST_INSERT");
		int[] returnArray = new int[] {};

		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final VOffenderAllSchedules ocn : list) {
			parameters.add(new BeanPropertySqlParameterSource(ocn));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("mfDAdjustInsert:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<VOffenderAllSchedules> oseCurSelectOperation(final BigDecimal eventId) {
		final String sql = getQuery("OCDCLOGS_OSECUR_SELECT_OPERATION");
		List<VOffenderAllSchedules> returnList = new ArrayList<VOffenderAllSchedules>();

		final RowMapper<VOffenderAllSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("eventId", eventId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public Integer countSentenceUaSelectOperation(final VOffenderAllSchedules obj) {
		int count = 0;
		final String sql = getQuery("OCDCLOGS_COUNT_SENTENCE_UA_SELECT_OPERATION");
		try {
			count = namedParameterJdbcTemplate
					.queryForObject(
							sql, createParams("offenderBookId", obj.getOffenderBookId(), "sentenceSeq",
									obj.getSentenceSeq(), "eventDate", obj.getEventDate(), "eventId", obj.getEventId()),
							Integer.class);
		} catch (Exception e) {
			logger.error("countSentenceUaSelectOperation", e);
		}
		return count;
	}

	@Override
	public Integer uaEventExists(final VOffenderAllSchedules obj) {
		int count = 0;
		final String sql = getQuery("OCDCLOGS_UA_EVENT_EXISTS");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					obj.getOffenderBookId(), "sentenceSeq", obj.getSentenceSeq(), "eventId", obj.getEventId()),
					Integer.class);
		} catch (Exception e) {
			logger.error("uaEventExists", e);
		}
		return count;
	}

	@Override
	public Integer uaEventExistsDelOperation(final VOffenderAllSchedules obj) {
		final String sql = getQuery("OCDCLOGS_UA_EVENT_EXISTS_DEL_OPERATION");
		final List<VOffenderAllSchedules> list = new ArrayList<VOffenderAllSchedules>();
		list.add(obj);

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final VOffenderAllSchedules offSch : list) {
			parameters.add(new BeanPropertySqlParameterSource(offSch));
		}
		try {
			String tableName = "OFFENDER_SENTENCE_UA_EVENTS";
			String whereCondition = "offender_book_id = :offenderBookId AND sentence_seq = :sentenceSeq AND event_id = :eventId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("uaEventExistsDelOperation", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer createUasInsert(final VOffenderAllSchedules obj) {
		final List<VOffenderAllSchedules> list = new ArrayList<VOffenderAllSchedules>();
		list.add(obj);
		final String sql = getQuery("OCDCLOGS_CREATE_UAS_INSERT");
		int[] returnArray = new int[] {};

		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final VOffenderAllSchedules ocn : list) {
			parameters.add(new BeanPropertySqlParameterSource(ocn));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("createUasInsert:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<VOffenderSentenceEvents> failcur(final Long eventId) {
		final String sql = getQuery("SELECT_OFFENDER_SENTANCEEVENTS");
		final RowMapper<VOffenderSentenceEvents> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderSentenceEvents.class, referenceCodeMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_event_id", eventId), mRowMapper);
	}

	@Override
	public String curUaOldOutcome(final String eventType, final String eventSubType, final String oldOutCome) {
		final String sql = getQuery("UA_CUR_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_event_type", eventType,
				"p_event_sub_type", eventSubType, "p_event_outcome", oldOutCome), String.class);
	}

	@Override
	public Integer selectCount(final VOffenderSentenceEvents bean) {
		final String sql = getQuery("SELECT_COUNT_OFFENDER_SENTENCES");
		return namedParameterJdbcTemplate.queryForObject(
				sql, createParams("p_offender_book_id", bean.getOffenderBookId(), "p_sentence_seq",
						bean.getSentenceSeq(), "p_event_date", bean.getEventDate(), "p_event_id", bean.getEventId()),
				Integer.class);
	}

	@Override
	public Boolean checkUaEventOutcome(final OffenderCourseAttendance obj) {
		Integer count = 0;
		final String sql = getQuery("CHECK_UA_EVENT_OUTCOME_ONE");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_event_type", obj.getEventType(),
					"p_event_sub_type", obj.getEventSubType(), "p_event_outcome", obj.getEventOutcome()),
					Integer.class);
		} catch (Exception e) {
			logger.error("checkUaEventOutcome", e);
		}

		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

}
