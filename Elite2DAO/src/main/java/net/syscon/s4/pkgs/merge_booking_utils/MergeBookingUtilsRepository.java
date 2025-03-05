package net.syscon.s4.pkgs.merge_booking_utils;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;

public interface MergeBookingUtilsRepository {

	public List<OffenderBookings> getBookingVals(Long offenderBookId);
	
	
}
