package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;

public interface OuminoutRepository {

	Integer moveRsnUpdateMovementReasons(List<MovementReasons> object);

	List<MovementReasons> moveRsnExecuteQuery(MovementReasons object);

	List<ReferenceCodes> cgfkMoveRsnInMovementReasRecordGroup();

	Integer moveRsnDeleteMovementReasons(List<MovementReasons> object);

	Integer cgrichkMovementReasonsMovements(MovementReasons movementReasons);

	Integer cgrichkMovementReasonsBooking(MovementReasons movementReasons);

}
