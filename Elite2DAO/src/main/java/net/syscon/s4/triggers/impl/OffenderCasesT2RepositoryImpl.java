package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.triggers.OffenderCasesT2Repository;

@Repository
public class OffenderCasesT2RepositoryImpl extends RepositoryBase implements OffenderCasesT2Repository {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public String caseIdentifierExist(final String caseInfoPrefix, final String caseInfoNumber) {
		final String sql = getQuery("OFFENDER_CASES_T2_CASE_IDENTIFIER_EXIST");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseInfoPrefix", caseInfoPrefix, "caseInfoNumber", caseInfoNumber), String.class);
		} catch (final Exception e) {
			logger.error("caseIdentifierExist", e);
			return null;
		}
	}

	@Override
	public OffenderCases getOffenderCases(final Long caseId) {
		final String sql = getQuery("OFFENDER_CASES_T2_OFFENDER_CASES_OLD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseId", caseId),
					new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));
		} catch (final Exception e) {
			logger.error("getOffenderCases", e);
			return null;
		}
	}

}
