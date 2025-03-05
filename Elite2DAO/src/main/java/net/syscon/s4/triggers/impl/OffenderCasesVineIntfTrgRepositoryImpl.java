package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.triggers.CaAudit;
import net.syscon.s4.triggers.OffenderCasesVineIntfTrgRepository;

@Repository
public class OffenderCasesVineIntfTrgRepositoryImpl extends RepositoryBase
		implements OffenderCasesVineIntfTrgRepository {
	private static Logger logger = LogManager.getLogger(OffenderCasesVineIntfTrgRepositoryImpl.class);

	@Override
	public OffenderBookings curActBook(final Long pOffBookId) {
		final String sqlname = getQuery("OFFENDER_CASES_VINE_INTF_TRG_CUR_ACT_BOOK");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("pOffBookId", pOffBookId),
					OffenderBookings.class);
		} catch (final Exception e) {
			logger.error("curActBook", e);
			return null;
		}

	}

	@Override
	public Offenders curOff(final Long pOffId) {
		final String sqlname = getQuery("OFFENDER_CASES_VINE_INTF_TRG_CUR_OFF");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("pOffId", pOffId), Offenders.class);
		} catch (final Exception e) {
			logger.error("curOff", e);
			return null;
		}

	}

	@Override
	public OffenderCases getOldOffenderCases(final Long caseId) {
		final String sqlname = getQuery("OFFENDER_CASES_VINE_INTF_TRG_OFFENDER_CASES_OLD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("caseId", caseId),
					OffenderCases.class);
		} catch (final Exception e) {
			logger.error("getOldOffenderCases", e);
			return null;
		}

	}

	@Override
	public Integer inserting(final CaAudit caAudit) {
		final String sql = getQuery("OFFENDER_CASES_VINE_INTF_TRG_INSERTING");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(caAudit));
		} catch (final Exception e) {
			logger.error("inserting", e);
			return null;
		}
	}

	@Override
	public Integer updating(final CaAudit caAudit) {
		final String sql = getQuery("OFFENDER_CASES_VINE_INTF_TRG_UPDATING");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(caAudit));
		} catch (final Exception e) {
			logger.error("updating", e);
			return null;
		}
	}

	@Override
	public Integer deleting(final CaAudit caAudit) {
		final String sql = getQuery("OFFENDER_CASES_VINE_INTF_TRG_DELETING");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(caAudit));
		} catch (final Exception e) {
			logger.error("deleting", e);
			return null;
		}
	}

}
