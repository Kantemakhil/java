package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.VOffExtMovements;
import net.syscon.s4.inst.schedules.bean.VOffExtMovementsCommitBean;

public interface OiidmoveService  {
	MovementReasons CgfkchkOffEmOffEmMoveRs(MovementReasons paramBean);

	ReferenceCodes CgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean);

	List<Object> CgfkchkOffEmOffEmVHeade(Offenders paramBean);

	List<VOffExtMovements> offEmExecuteQuery(VOffExtMovements objVOffExtMovements);

	ReferenceCodes CgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean);

	ReferenceCodes CgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup();

	List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup();

	Integer offEmCommit(VOffExtMovementsCommitBean CommitBean);

	VHeaderBlock CgfklkpOffEmOffEmVHeade(VHeaderBlock paramBean);

}
