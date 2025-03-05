package net.syscon.s4.triggers.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.SubstanceTestResultsTcnRepository;
import net.syscon.s4.triggers.SubstanceTests;

@Repository
public class SubstanceTestResultsTcnRepositoryImpl extends RepositoryBase implements SubstanceTestResultsTcnRepository {
	private static Logger logger = LogManager.getLogger(SubstanceTestResultsTcnRepositoryImpl.class);
	
	@Override
	public SubstanceTests vSubCur(final Long offenderBookId, final Integer sampleSeq) {
		final String sqlname = getQuery("OFFENDER_RELEASE_DETAILS_T2_L_CHECK_EXIST_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname,
					createParams(":offenderBookId", offenderBookId, ":sampleSeq", sampleSeq), SubstanceTests.class);
		} catch (final Exception e) {
			logger.error("vSubCur", e);
			return null;
		}
	}

}
