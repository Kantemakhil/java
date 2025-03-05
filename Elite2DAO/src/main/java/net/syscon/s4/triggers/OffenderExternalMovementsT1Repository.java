package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.MovementReasons;

public interface OffenderExternalMovementsT1Repository {
	Integer save(MovementReasons oldRef, MovementReasons newRef, Long offenderBookId);

	Integer update(MovementReasons oldRef, MovementReasons newRef, Long offenderBookId);
}
