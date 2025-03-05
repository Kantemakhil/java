package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT2Repository;

@Repository
public class OffenderBookingAgyLocsT2RepositoryImpl extends RepositoryBase
		implements OffenderBookingAgyLocsT2Repository {
	private static Logger logger = LogManager.getLogger(OffenderBookingAgyLocsT2RepositoryImpl.class);

	@Override
	public OffenderBookingAgyLocs getOffenderBookingAgyLocs(final OffenderBookingAgyLocs offeBookAgyLocs) {
		final String sql = getQuery("OFFENDER_BOOKING_AGY_LOCS_T2_OFFENDER_BOOKING_AGY_LOCS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offeBookAgyLocs.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderBookingAgyLocs>(OffenderBookingAgyLocs.class));
		} catch (final Exception e) {
			logger.error("getOffenderBookingAgyLocs", e);
			return null;
		}
	}

	@Override
	public OffenderBookings getOffenderBookings(final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_BOOKING_AGY_LOCS_T2_OFFENDER_BOOKINGS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (final Exception e) {
			logger.error("getOffenderBookings", e);
			return null;
		}
	}

	@Override
	public Integer update(final OffenderBookings offenderBookings) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_BOOKING_AGY_LOCS_T2_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderBookings));
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

}
