package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface OffenderExternalMovementsT5Repository {

	OffenderExternalMovements getOffenderExternalMovements(Long pOffenderBookId,final Long movementSeq);

	String getY(Long pOffenderBookId);

	Integer getTrgEventId();

	Integer insertOffenderAssocPEventNotifs(Integer lTrgEventId, Date lMoveDate, Long lOffenderBookId, String value,String creteUserId);


}
