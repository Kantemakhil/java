package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.im.beans.MovementReasons;

public interface OffenderExternalMovementT13Repository {
	MovementReasons closeBookingCur(String movementType, String movReasonCode);

	Integer update(OffenderBillingProfiles offeBillProfiles);
}
