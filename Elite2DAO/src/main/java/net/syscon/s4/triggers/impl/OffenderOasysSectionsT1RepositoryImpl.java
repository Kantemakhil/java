package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderOasysSections;
import net.syscon.s4.triggers.OffenderOasysSectionsT1Repository;

@Repository
public class OffenderOasysSectionsT1RepositoryImpl extends RepositoryBase implements OffenderOasysSectionsT1Repository {
	private final Logger logger = LogManager.getLogger(OffenderOasysSectionsT1RepositoryImpl.class);

	@Override
	public OffenderOasysSections getOffenderOasysSections(final Long offenderBookId, final Integer planSeq,
			final String sectionCode) {
		final String sql = getQuery("OFFENDER_OASYS_SECTIONS_T1_OFFENDER_OASYS_SECTIONS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":planSeq", planSeq, ":sectionCode", sectionCode),
					new BeanPropertyRowMapper<OffenderOasysSections>(OffenderOasysSections.class));
		} catch (final Exception e) {
			logger.error("getOffenderOasysSections", e);
			return null;
		}
	}

	@Override
	public Integer weightedScore(final String sectionCode, final Integer rawRcore) {
		final String sql = getQuery("OFFENDER_OASYS_SECTIONS_T1_OASYS_TWO_SCORES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":sectionCode", sectionCode, ":rawRcore", rawRcore), Integer.class);
		} catch (final Exception e) {
			logger.error("weightedScore", e);
			return null;
		}
	}

	@Override
	public Double summaryRatio(final Integer weightedScore, final String sectionCode) {
		final String sql = getQuery("OFFENDER_OASYS_SECTIONS_T1_SUMMARY_RATIO");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":weightedScore", weightedScore, ":sectionCode", sectionCode), Double.class);
		} catch (final Exception e) {
			logger.error("summaryRatio", e);
			return 0.0;
		}
	}
}
