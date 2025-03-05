package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
@Service
public class OffenderBookingsBkadmTrgServiceImpl implements OffenderBookingsBkadmTrgService {

	private static final String INSERTING = "INSERTING";
	private static final String UPDATING = "UPDATING";
	private static final String N = "N";
	private static final String Y = "Y";

	@Override
	public void offenderBookingsBkadmTrg(final OffenderBookings old,final OffenderBookings newbean,final String operation) {
		
		if(operation != null && (operation.equalsIgnoreCase(INSERTING) || (operation.equalsIgnoreCase(UPDATING) && (null != old.getActiveFlag() && old.getActiveFlag().equals(N)) && (null != newbean.getActiveFlag() && newbean.getActiveFlag().equals(Y))))) {
		}
		
	}
}
