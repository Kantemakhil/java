package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.triggers.OffenderSentencesTwfRepository;

@Repository
public class OffenderSentencesTwfRepositoryImpl extends RepositoryBase implements OffenderSentencesTwfRepository {
	Logger logger = LogManager.getLogger(OffenderSentencesTwfRepositoryImpl.class);

	@Override
	public OffenderSentences getOffenderSentences(final Long offenderBookId, final Long sentenceSeq) {
		final String sql = getQuery("OFFENDER_SENTENCES_TWF_OFFENDER_SENTENCES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, "sentenceSeq", sentenceSeq),
					new BeanPropertyRowMapper<OffenderSentences>(OffenderSentences.class));
		} catch (final Exception e) {
			logger.error("getOffenderSentences", e);
			return null;
		}
	}

	@Override
	public String lSentenceCalcType(final String sentenceCategory, final String sentenceCalcType) {
		final String sql = getQuery("OFFENDER_SENTENCES_TWF_L_SENTENCE_CALC_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":sentenceCategory", sentenceCategory, "sentenceCalcType", sentenceCalcType),
					String.class);
		} catch (final Exception e) {
			logger.error("lSentenceCalcType", e);
			return null;
		}
	}

}
