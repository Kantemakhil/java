package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.SubstanceTests;
import net.syscon.s4.triggers.SubstanceTestsTieqRepository;

@Repository
public class SubstanceTestsTieqRepositoryImpl extends RepositoryBase implements SubstanceTestsTieqRepository {
	final Logger logger = LogManager.getLogger(SubstanceTestsTieqRepositoryImpl.class);

	@Override
	public SubstanceTests getSubstanceTests(final Long offenderBookId, final Integer sampleSeq, final Integer testSeq) {
		final String sql = getQuery("SUBSTANCE_TESTS_TIEQ_SUBSTANCE_TESTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":sampleSeq", sampleSeq, ":testSeq", testSeq),
					new BeanPropertyRowMapper<SubstanceTests>(SubstanceTests.class));
		} catch (final Exception e) {
			logger.error("getSubstanceTests", e);
			return null;
		}
	}

	@Override
	public String vSubCur(final Long offenderBookId, final Integer sampleSeq) {
		final String sql = getQuery("SUBSTANCE_TESTS_TIEQ_V_SUB_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":sampleSeq", sampleSeq), String.class);
		} catch (final Exception e) {
			logger.error("vSubCur", e);
			return null;
		}
	}

}
