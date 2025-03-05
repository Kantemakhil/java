package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderAssocPartyNotesT1Repository;

@Repository
public class OffenderAssocPartyNotesT1RepositoryImpl extends RepositoryBase
		implements OffenderAssocPartyNotesT1Repository {
	private static Logger logger = LogManager.getLogger(OffenderAssocPartyNotesT1RepositoryImpl.class);

	@Override
	public Integer saveOffenderAssocPEventNotifs(final OffenderAssocPEventNotifs offenAssPEventNot) {
		final String sql = getQuery("OFFENDER_ASSOC_PARTY_NOTES_T1_OFFENDER_ASSOC_P_EVENT_NOTIFS");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offenAssPEventNot));
		} catch (final Exception e) {
			logger.error("saveOffenderAssocPEventNotifs", e);
			return null;
		}
	}

	@Override
	public Long trgEventIdSeq() {
		final String sqlname = getQuery("OFFENDER_ASSOC_PARTY_NOTES_T1_TRG_EVENT_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("trgEventIdSeq", e);
			return null;
		}

	}

	@Override
	public Long offenderBookId(final Long assPartyId) {
		final String sqlname = getQuery("OFFENDER_ASSOC_PARTY_NOTES_T1_TRG_EVENT_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(":associatedPartyId", assPartyId),
					Long.class);
		} catch (final Exception e) {
			logger.error("offenderBookId", e);
			return null;
		}

	}

}
