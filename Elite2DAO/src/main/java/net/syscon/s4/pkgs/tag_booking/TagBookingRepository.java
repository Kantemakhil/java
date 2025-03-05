package net.syscon.s4.pkgs.tag_booking;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface TagBookingRepository {

	OffenderExternalMovements getOffExtMovementsdetails(final BigDecimal pOffenderBookId);
}