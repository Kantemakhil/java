package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.MovementReasonsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;

/**
 * Interface OuminoutService
 */
public interface OuminoutService {

	List<MovementReasons> moveRsnExecuteQuery(MovementReasons object);

	List<ReferenceCodes> cgfkMoveRsnInMovementReasRecordGroup();

	List<MovementReasons> moveRsnCommit(MovementReasonsCommitBean commitBean);

	MovementReasons cgrichkMovementReasonsDeleteCheck(MovementReasons searchBean);

}
