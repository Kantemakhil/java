package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT2Repository;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT2Service;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

@Service
public class OffenderBookingAgyLocsT2ServiceImpl implements OffenderBookingAgyLocsT2Service {
	private static Logger logger = LogManager.getLogger(OffenderBookingAgyLocsT2ServiceImpl.class);
	@Autowired
	OffenderBookingAgyLocsT2Repository offenderBookingAgyLocsT2Repository;

	@Autowired
	OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;

	@Autowired
	OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;

	@Autowired
	OffenderBookingsT7Service offenderBookingsT7Service;

	@Autowired
	OffenderBookingsT2Service offenderBookingsT2Service;

	// Below method call after insert on offender_booking_agy_locs table
	@Override
	public Integer offenderBookingAgyLocsT2Triger(final OffenderBookingAgyLocs offenderBookingAgyLocs) {
		final OffenderBookings targetObject = new OffenderBookings();
		OffenderBookings newbean = new OffenderBookings();
		String operation = null;
		BigDecimal vNoCommAgy = BigDecimal.ZERO;
		Integer result;
		// fetching the details from Offender_bookings table
		final OffenderBookings offenderBookings = offenderBookingAgyLocsT2Repository
				.getOffenderBookings(offenderBookingAgyLocs.getOffenderBookId());
		if(offenderBookings != null && offenderBookings.getNoCommAgyLocId() != null) {
			vNoCommAgy = offenderBookings.getNoCommAgyLocId();
		}
		targetObject.setOffenderBookId(offenderBookingAgyLocs.getOffenderBookId());
		targetObject.setModifyUserId(offenderBookingAgyLocs.getCreateUserId());
		targetObject.setModifyDatetime(offenderBookingAgyLocs.getCreateDatetime());
		if (offenderBookingAgyLocs != null && offenderBookingAgyLocs.getRemovedDate() == null) {
			vNoCommAgy = vNoCommAgy.add(BigDecimal.ONE);
		}
		if (vNoCommAgy.compareTo(BigDecimal.ONE) == 0) {
			targetObject.setCommunityAgyLocId(offenderBookingAgyLocs.getAgyLocId());
		} else {
			targetObject.setCommunityAgyLocId("MULTI");
		}
		try {
			targetObject.setNoCommAgyLocId(vNoCommAgy);
			// adding trigger
			// below trigger method call executes before updating data in to Offender_bookings table
			offenderBookingsT2Service.offenderBookingsT2(newbean, targetObject);
			// below method is used to update data into Offender_bookings table
			result = offenderBookingAgyLocsT2Repository.update(targetObject);
			// adding trigger
			// below trigger method call executes after updation data in to Offender_bookings table
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(targetObject, newbean, operation);
			// below trigger method call executes after updation data in to Offender_bookings table
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(targetObject,ApplicationConstants.UPDATING);
			// below trigger method call executes after updation data in to Offender_bookings table
			offenderBookingsT7Service.offenderBookingsT7Trigger(targetObject);
		} catch (final Exception e) {
			logger.error("offenderBookingAgyLocsT1Triger", e);
			result = 0;
		}
		return result;
	}
}
