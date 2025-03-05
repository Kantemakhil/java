package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;
import net.syscon.s4.triggers.OffenderCaseIdentifiersT1Repository;

@Repository
public class OffenderCaseIdentifiersT1RepositoryImpl extends RepositoryBase
		implements OffenderCaseIdentifiersT1Repository {
	Logger logger = LogManager.getLogger(OffenderCaseIdentifiersT1RepositoryImpl.class.getName());

	@Override
	public OffenderCaseIdentifiers getOffenderCaseIdentifiers(final OffenderCaseIdentifiers offenderCaseIdentifiers) {
		final String sql = getQuery("OFFENDER_CASE_IDENTIFIERS_T1_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":caseId", offenderCaseIdentifiers.getCaseId()),
					new BeanPropertyRowMapper<OffenderCaseIdentifiers>(OffenderCaseIdentifiers.class));
		} catch (final Exception e) {
			logger.error("getOffenderCaseIdentifiers", e);
			return null;
		}
	}

	@Override
	public Integer vNumrows(final OffenderCaseIdentifiers offenderCaseIdentifiers) {
		final String sql = getQuery("OFFENDER_CASE_IDENTIFIERS_T1_V_NUMROWS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final Exception e) {
			logger.error("vNumrows", e);
			return 0;
		}
	}

}
