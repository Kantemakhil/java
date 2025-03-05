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
import net.syscon.s4.triggers.OffenderSentencesT2Repository;

@Repository
public class OffenderSentencesT2RepositoryImpl extends RepositoryBase implements OffenderSentencesT2Repository {
	Logger logger = LogManager.getLogger(OffenderSentencesT2RepositoryImpl.class.getName());

	@Override
	public OffenderSentences getOffenderSentences(final OffenderSentences offenderSentences) {
		final String sql = getQuery("OFFENDER_SENTENCES_T2_OFFENDER_SENTENCES");
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
	public Integer insert(final OffenderSentences offenderSentences) {
		final String sql = getQuery("OFFENDER_SENTENCES_T2_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderSentences));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
