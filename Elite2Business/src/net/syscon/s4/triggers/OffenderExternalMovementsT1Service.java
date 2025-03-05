package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.MovementReasons;

public interface OffenderExternalMovementsT1Service {
	Integer OffenderExternalMovementsT1Trigger(MovementReasons oldRef, MovementReasons newRef, Long offenderBookId,
			String operationType);

}
