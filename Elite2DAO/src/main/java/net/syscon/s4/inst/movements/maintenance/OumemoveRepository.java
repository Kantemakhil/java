package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;

/**
 * Interface OumemoveRepository
 */
public interface OumemoveRepository {
	Integer moveRsnUpdateMovementReasons(List<MovementReasons> lstMovReasons);

	List<MovementReasons> moveRsnExecuteQuery(MovementReasons lstMovReasons);

	Integer moveRsnDeleteMovementReasons(List<MovementReasons> lstMovReasons);

	List<ReferenceCodes> cgfkMoveRsnMovementReasonRecordGroup();

	Integer moversnInsertMovementReasons(List<MovementReasons> lstMovReasons);

	List<ReferenceCodes> cgfkMoveRsnMovementTypeRecordGroup();

	Integer cgrichkMovementReasonsScheduleCheck(MovementReasons movementReasons);

	Integer cgrichkMovementReasonsExterMovment(MovementReasons movementReasons);
}
