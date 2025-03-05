package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgRepository;

@Repository
public class OffExtMvVineIntfTrgRepositoryImpl extends RepositoryBase implements OffExtMvVineIntfTrgRepository {
	private final Logger logger = LogManager.getLogger(OffExtMvVineIntfTrgRepositoryImpl.class);

	@Override
	public OffenderExternalMovements getOffenderExternalMovements(final OffenderExternalMovements offenderExtMov) {
		final String sql = getQuery("OFF_EXT_MV_VINE_INTF_TRG_OFFENDER_EXTERNAL_MOVEMENTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderExtMov.getOffenderBookId(), "movementSeq",
							offenderExtMov.getMovementSeq()),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (final Exception e) {
			logger.error("getOffenderExternalMovements", e);
			return null;
		}
	}

	@Override
	public OffenderBookings curBookOr(final Long offenderBookId) {
		final String sql = getQuery("OFF_EXT_MV_VINE_INTF_TRG_CUR_BOOK_OR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (final Exception e) {
			logger.error("curBookOr", e);
			return null;
		}
	}

	@Override
	public Offenders curOff(final BigDecimal pOffId) {
		final String sql = getQuery("OFF_EXT_MV_VINE_INTF_TRG_CUR_OFF");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffId", pOffId),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (final Exception e) {
			logger.error("curOff", e);
			return null;
		}
	}

	@Override
	public Integer updateOrAudit(final List<OrAudit> orAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFF_EXT_MV_VINE_INTF_TRG_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OrAudit orAudit : orAuditList) {
			parameters.add(new BeanPropertySqlParameterSource(orAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (orAuditList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("updateOrAudit ", e);
		}
		return returnValue;

	}

	@Override
	public Integer insertOrAudit(final List<OrAudit> orAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFF_EXT_MV_VINE_INTF_TRG_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OrAudit orAudit : orAuditList) {
			parameters.add(new BeanPropertySqlParameterSource(orAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (orAuditList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insertOrAudit ", e);
		}
		return returnValue;

	}

}
