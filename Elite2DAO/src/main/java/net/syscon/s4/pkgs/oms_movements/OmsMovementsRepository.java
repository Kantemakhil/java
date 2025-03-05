package net.syscon.s4.pkgs.oms_movements;

import java.util.List;

public interface OmsMovementsRepository {

	String getLastToAgyLocId(final Integer pBookingId);

	String getLastToCity(final Integer pBookingId);

	String getvDirection(final Integer pBookingId);

	String getNextDirectionCode(final String vDirection);

	String getLastMovementReasonCode(final Integer pBookingId);

	String getLastFromAgyLocId(final Integer pBookingId);

	String getLastMovementCode(final Integer offBookId);

	String getLastFromCity(final Integer offBookId);

	Integer checkActiveSentence(final Long offBookId);

	List<Object[]> checkReleaseDateCur(final Long offenderBookId);
	
	String getToAddress(final Integer pBookingId);
}