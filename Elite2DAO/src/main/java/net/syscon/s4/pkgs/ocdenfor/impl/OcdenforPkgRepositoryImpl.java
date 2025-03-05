package net.syscon.s4.pkgs.ocdenfor.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.inst.legals.beans.VOffenderProceedingSents;
import net.syscon.s4.pkgs.ocdenfor.OcdenforPkgRepository;

@Repository
public class OcdenforPkgRepositoryImpl extends RepositoryBase implements OcdenforPkgRepository {

	final static Logger logger = LogManager.getLogger(OcdenforPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> couActyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	@Override
	public CourseActivities getCourseActivitiesData(final Long crsActyId) {
		final String sql = getQuery("GET_COURSE_ACTIVITIES_DATA");

		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				couActyMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_crs_acty_id", crsActyId), rowMapper);
	}

	@Override
	public String getRequirement(final Long programId) {
		final String sql = getQuery("GET_REQUIREMENT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("l_program_id", programId), String.class);
	}

	@Override
	public String getLocation(final String providerPartyCode) {
		final String sql = getQuery("GET_LOCATION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("l_provider_party_code", providerPartyCode),
				String.class);
	}

	@Override
	public String getLocationFrmTeams(final Long providerPartyId) {
		final String sql = getQuery("GET_LOCATION_FRM_TEAMS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("l_provider_party_id", providerPartyId),
				String.class);
	}

	@Override
	public String getLocationFrmCorporates(final Long providerPartyId) {
		final String sql = getQuery("GET_LOCATION_FRM_CORPORATES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("l_provider_party_id", providerPartyId),
				String.class);
	}

	@Override
	public OffenceResultCodes getCourtEventsentence(final VOffenderProceedingSents bean) {
		final String sql = getQuery("GET_COURTEVENT_SENTENCES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id",
				bean.getOffenderBookId(), "p_event_id", bean.getEventId(), "p_sentence_seq", bean.getSentenceSeq()),
				new BeanPropertyRowMapper<OffenceResultCodes>(OffenceResultCodes.class));
	}

	@Override
	public Integer deleteCourtEventSentence(final VOffenderProceedingSents objSearchDao) {
		final String sql = getQuery("DELETE_FROM_COURT_EVENT_SENTENCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "COURT_EVENT_SENTENCES";
			String whereCondition = "EVENT_ID = :P_EVENT_ID AND OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND SENTENCE_SEQ = :P_SENTENCE_SEQ";
			inputMap.put("P_EVENT_ID", objSearchDao.getEventId());
			inputMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			inputMap.put("P_SENTENCE_SEQ", objSearchDao.getSentenceSeq());
			inputMap.put("modifyUserId", objSearchDao.getModifyUserId());
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteCourtEventSentence " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_EVENT_ID", objSearchDao.getEventId(), "P_OFFENDER_BOOK_ID",
						objSearchDao.getOffenderBookId(), "P_SENTENCE_SEQ", objSearchDao.getSentenceSeq()));
	}

	@Override
	public Integer updateCourtEventSentence(final VOffenderProceedingSents objSearchDao) {
		final String sql = getQuery("UPDATE_COURT_EVENT_SENTENCES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_RESULT_CODE", objSearchDao.getResultCode(), "P_EVENT_ID", objSearchDao.getEventId(),
						"P_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "P_SENTENCE_SEQ",
						objSearchDao.getSentenceSeq(), "modifyUserId", objSearchDao.getModifyUserId()));
	}

	@Override
	public Integer insertCourtEventSentences(final VOffenderProceedingSents objSearchDao) {
		final String sql = getQuery("INSERT_INTO_COURT_EVENT_SENTENCES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_RESULT_CODE", objSearchDao.getResultCode(), "P_EVENT_ID", objSearchDao.getEventId(),
						"P_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "P_SENTENCE_SEQ",
						objSearchDao.getSentenceSeq(), "createUserId", objSearchDao.getCreateUserId()));

	}
}