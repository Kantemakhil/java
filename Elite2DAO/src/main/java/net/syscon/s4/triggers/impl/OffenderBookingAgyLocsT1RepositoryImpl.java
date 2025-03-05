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
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Repository;

@Repository
public class OffenderBookingAgyLocsT1RepositoryImpl extends RepositoryBase
		implements OffenderBookingAgyLocsT1Repository {
	private final Logger logger = LogManager.getLogger(OffenderBookingAgyLocsT1RepositoryImpl.class.getName());

	@Override
	public OffenderBookingAgyLocs getOffenderBookingAgyLocs(final OffenderBookingAgyLocs offeBookAgyLocs) {
		final String sql = getQuery("OFFENDER_BOOKING_AGY_LOCS_T1_OFFENDER_BOOKING_AGY_LOCS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offeBookAgyLocs.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderBookingAgyLocs>(OffenderBookingAgyLocs.class));
		} catch (final Exception e) {
			logger.error("getOffenderBookingAgyLocs", e);
			return null;
		}
	}

	@Override
	public OffenderBookings getOffenderBookings(final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_BOOKING_AGY_LOCS_T1_OFFENDER_BOOKINGS");
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
		final String sql = getQuery("OFFENDER_BOOKINGS_UPDATE");
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
