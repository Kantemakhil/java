package net.syscon.s4.pkgs.merge_booking_utils.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.merge_booking_utils.MergeBookingUtilsRepository;

@Repository
public class MergeBookingUtilsRepositoryImpl extends RepositoryBase implements MergeBookingUtilsRepository {

	private static Logger logger = LogManager.getLogger(MergeBookingUtilsRepositoryImpl.class.getName());

	@Override
	public List<OffenderBookings> getBookingVals(Long offenderBookId) {
		final String sql = getQuery("MERGE_BOOKING_UTILS_GET_BOOKING_VALS");
		List<OffenderBookings> bookingVals = new ArrayList<OffenderBookings>();
		try {
			bookingVals = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getBookingVals", e);
		}
		return bookingVals;
	}

}
