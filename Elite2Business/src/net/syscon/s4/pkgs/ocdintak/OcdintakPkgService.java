package net.syscon.s4.pkgs.ocdintak;

import java.util.Map;

import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderResidence;

public interface OcdintakPkgService {
	Integer updateOffBookings(final String toAgyLocId, final OffenderBookings offbkg, final String userName, final Integer staffId, final String pCommStatus, final String lvBookingType);

	OffenderBookingEvent processOcdintakTrust(final OffenderBookingEvent offEve);

	Map<String, Object> storGlobals(final OffenderBookingEvent offEve);

	String genReceiptNo(final String pCsldId, final String pTxnusage);

	void removeClosAcct(final OffenderBookingEvent offEve);

	void createNewAccount(final Integer pOffId, final String pCsldId, final Integer pTxnId, final String userName);

	void postTrustGl(final OffenderBookingEvent offEve);

	void populateGroupId(final Integer pOffId, final String userName);

	Integer insertOffTxn(final OffenderBookingEvent offEve);

	Integer instOffBooking(final OffenderBookingEvent object, final String userName);

	Integer instOffBookAgyLoc(final OffenderBookingEvent object, final String userName);

	Integer insOffSchedule(final OffenderResidence object);
}
