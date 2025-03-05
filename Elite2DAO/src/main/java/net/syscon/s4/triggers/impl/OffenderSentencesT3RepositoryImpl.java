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
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.triggers.OffenderSentencesT3Repository;

@Repository
public class OffenderSentencesT3RepositoryImpl extends RepositoryBase implements OffenderSentencesT3Repository {
	Logger logger = LogManager.getLogger(OffenderSentencesT3RepositoryImpl.class);

	@Override
	public OffenderSentences getOffenderSentences(final OffenderSentences offenderSentences) {
		final String sql = getQuery("OFFENDER_SENTENCES_T3_OFFENDER_SENTENCES");
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
	public String isDrrCur(final String sentenceCategory, final String sentenceCalcType) {
		final String sql = getQuery("OFFENDER_SENTENCES_T3_IS_DRR_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("sentenceCategory", sentenceCategory, "sentenceCalcType", sentenceCalcType),
					String.class);
		} catch (final Exception e) {
			logger.error("isDrrCur", e);
			return null;
		}
	}

	@Override
	public Integer insert(final OffenderPrgObligations offenPrgOliga) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_SENTENCES_T3_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenPrgOliga));
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

	@Override
	public Integer update(final OffenderSentences offenderSentences) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_SENTENCES_T3_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderSentences));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("update ", e);
		}
		return returnValue;

	}

}
