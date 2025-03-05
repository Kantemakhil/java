package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.MovementReasonsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;

/**
 * Interface OumemoveService
 */
public interface OumemoveService {
	List<MovementReasons> moveRsnCommit(MovementReasonsCommitBean commitBean);

	MovementReasons cgrichkMovementReasonsDeleteCheck(MovementReasons paramBean);

	List<MovementReasons> moveRsnExecuteQuery(MovementReasons paramBean);

	List<ReferenceCodes> cgfkMoveRsnMovementReasonRecordGroup();

	List<ReferenceCodes> cgfkMoveRsnMovementTypeRecordGroup();

}
