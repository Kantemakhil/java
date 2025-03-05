package net.syscon.s4.pkgs.oms_movements;

import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface OmsMovementsService {

	String getLastToAgyLocId(final Integer pBookingId);

	String getLastToCity(final Integer pBookingId);

	String getNextDirectionCode(final Integer pBookingId);

	String getLastMovementReasonCode(final Integer pBookingId);

	String getLastFromAgyLocId(final Integer pBookingId);

	String getLastMovementCode(final Integer offBookId);

	String getLastFromCity(final Integer offBookId);

	Integer checkActiveSentence(final OffenderExternalMovements searchdao);

	OffenderExternalMovements releaseDateCheck(final OffenderExternalMovements paramBean);
	
	String getToAddress(final Integer pBookingId);
}