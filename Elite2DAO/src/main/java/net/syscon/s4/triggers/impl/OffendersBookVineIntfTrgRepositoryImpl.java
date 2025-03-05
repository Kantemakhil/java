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
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.AnAudit;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgRepository;

@Repository
public class OffendersBookVineIntfTrgRepositoryImpl extends RepositoryBase
		implements OffendersBookVineIntfTrgRepository {
	Logger logger = LogManager.getLogger(OffendersBookVineIntfTrgRepositoryImpl.class);

	@Override
	public OffenderBookings getOffenderBookings(final Long offenderBookId) {
		final String sql = getQuery("OFFENDERS_BOOK_VINE_INTF_TRG_OFFENDER_BOOKINGS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (final Exception e) {
			logger.error("getOffenderBookings", e);
			return null;
		}
	}

	@Override
	public Offenders curOff(final BigDecimal offenderId) {
		final String sql = getQuery("OFFENDERS_BOOK_VINE_INTF_TRG_CUR_OFF");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (final Exception e) {
			logger.error("curOff", e);
			return null;
		}
	}

	@Override
	public Integer insert(final List<OrAudit> orAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDERS_BOOK_VINE_INTF_TRG_OR_AUDIT_INSERT");
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
			logger.error("insert ", e);
		}
		return returnValue;

	}

	@Override
	public Integer update(final List<OrAudit> orAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDERS_BOOK_VINE_INTF_TRG_OR_AUDIT_UPDATE");
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
			logger.error("update ", e);
		}
		return returnValue;

	}

	@Override
	public Integer delete(final List<OrAudit> orAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDERS_BOOK_VINE_INTF_TRG_OR_AUDIT_DELETE");
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
			logger.error("delete ", e);
		}
		return returnValue;

	}

	@Override
	public Integer anAuditUpdate(final List<AnAudit> anAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDERS_BOOK_VINE_INTF_TRG_AN_AUDIT_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AnAudit anAudit : anAuditList) {
			parameters.add(new BeanPropertySqlParameterSource(anAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (anAuditList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("anAuditUpdate ", e);
		}
		return returnValue;

	}

}
