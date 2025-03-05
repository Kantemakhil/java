package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.triggers.OffenderSentCalculations;
import net.syscon.s4.triggers.OffenderSentCalculationsT2Repository;

@Repository
public class OffenderSentCalculationsT2RepositoryImpl extends RepositoryBase
		implements OffenderSentCalculationsT2Repository {
	final Logger logger = LogManager.getLogger(OffenderSentCalculationsT2RepositoryImpl.class);

	@Override
	public OffenderSentCalculations getOffenderSentCalculations(final Long offenderSentCalculationId) {
		final String sql = getQuery("OFFENDERS_BOOK_VINE_INTF_TRG_OFFENDER_BOOKINGS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderSentCalculationId", offenderSentCalculationId),
					new BeanPropertyRowMapper<OffenderSentCalculations>(OffenderSentCalculations.class));
		} catch (final Exception e) {
			logger.error("getOffenderSentCalculations", e);
			return null;
		}
	}

	@Override
	public Integer lvCount(final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_SENT_CALCULATIONS_T2_LV_COUNT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":offenderBookId", offenderBookId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("lvCount", e);
			return null;
		}
	}

	@Override
	public Integer update(final OffenderReleaseDetails offenderReleaseDetails) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_SENT_CALCULATIONS_T2_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderReleaseDetails));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("update ", e);
		}
		return returnValue;

	}

	@Override
	public Integer insert(final OffenderReleaseDetails offenderReleaseDetails) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_SENT_CALCULATIONS_T2_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderReleaseDetails));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insert ", e);
		}
		return returnValue;

	}

}
