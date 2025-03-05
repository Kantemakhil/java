package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.triggers.ChAudit;
import net.syscon.s4.triggers.OffenderChargesVineIntfTrgRepository;

@Repository
public class OffenderChargesVineIntfTrgRepositoryImpl extends RepositoryBase
		implements OffenderChargesVineIntfTrgRepository {
	private static Logger logger = LogManager.getLogger(OffenderChargesVineIntfTrgRepositoryImpl.class);
	@Override
	public OffenderBookings curActBook(final Long pOffBookId) {
		final String sqlname = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_CUR_ACT_BOOK");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("pOffBookId", pOffBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (final Exception e) {
			logger.error("curActBook", e);
			return null;
		}

	}

	@Override
	public Offenders curOff(final Long pOffId) {
		final String sqlname = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_CUR_OFF");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("pOffId", pOffId), new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (final Exception e) {
			logger.error("curOff", e);
			return null;
		}

	}

	@Override
	public RecordGroup curOffDesc(final String pOffCode, final String pStatCode) {
		final String sqlname = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_CUR_OFF_DESC");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname,
					createParams("offCode", pOffCode, "statCode", pStatCode),new BeanPropertyRowMapper<RecordGroup>(RecordGroup.class));
		} catch (final Exception e) {
			logger.error("curOffDesc", e);
			return null;
		}

	}

	@Override
	public OffenderCharges getOldOffenderCharge(final Long offenderChargeId) {
		final String sqlname = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_OFFENDER_CHARGES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname,
					createParams("offenderChargeId", offenderChargeId), new BeanPropertyRowMapper<OffenderCharges>(OffenderCharges.class));
		} catch (final Exception e) {
			logger.error("getOldOffenderCases", e);
			return null;
		}

	}

	@Override
	public Integer inserting(final ChAudit chAudit) {
		final String sql = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_INSERTING");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(chAudit));
		} catch (final Exception e) {
			logger.error("inserting", e);
			return null;
		}
	}

	@Override
	public Integer updating(final ChAudit chAudit) {
		final String sql = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_UPDATING");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(chAudit));
		} catch (final Exception e) {
			logger.error("updating", e);
			return null;
		}
	}

	@Override
	public Integer deleting(final ChAudit chAudit) {
		final String sql = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_DELETING");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(chAudit));
		} catch (final Exception e) {
			logger.error("deleting", e);
			return null;
		}
	}

}
