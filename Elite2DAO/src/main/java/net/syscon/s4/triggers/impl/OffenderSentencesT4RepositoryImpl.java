package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderSentencesT4Repository;

@Repository
public class OffenderSentencesT4RepositoryImpl extends RepositoryBase implements OffenderSentencesT4Repository {
	Logger logger = LogManager.getLogger(OffenderSentencesT4RepositoryImpl.class.getName());

	@Override
	public OffenderSentences getOffenderSentences(final OffenderSentences offenderSentences) {
		final String sql = getQuery("OFFENDER_SENTENCES_T4_OFFENDER_SENTENCES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderSentences.getOffenderBookId(), "sentenceSeq",
							offenderSentences.getSentenceSeq()),
					new BeanPropertyRowMapper<OffenderSentences>(OffenderSentences.class));
		} catch (final Exception e) {
			logger.error("getOffenderSentences", e);
			return null;
		}
	}

	@Override
	public Long lTrgEventId() {
		final String sql = getQuery("OFFENDER_SENTENCES_T4_L_TRG_EVENT_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("lTrgEventId", e);
			return null;
		}
	}

	@Override
	public Integer insert(final OffenderAssocPEventNotifs offenderAssocPEventNotifs) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_SENTENCES_T4_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderAssocPEventNotifs));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insert ", e);
		}
		return returnValue;

	}
}
