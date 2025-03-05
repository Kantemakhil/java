package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderChargesT1Repository;

@Repository
public class OffenderChargesT1RepositoryImpl extends RepositoryBase implements OffenderChargesT1Repository {
	private static Logger logger = LogManager.getLogger(OffenderChargesT1RepositoryImpl.class);

	@Override
	public Long lidsOffenceNumber(final Long offenderBookId, final Long caseId) {
		final String sqlname = getQuery("OFFENDER_CHARGES_T1_LIDS_OFFENCE_NUMBER");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname,
					createParams("offenderBookId", offenderBookId, "caseId", caseId), Long.class);
		} catch (final Exception e) {
			logger.error("lidsOffenceNumber", e);
			return null;
		}

	}

}
