package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.VOffExtMovements;

public interface OiidmoveRepository {
	MovementReasons cgfkchkOffEmOffEmMoveRs(MovementReasons paramBean);

	List<VOffExtMovements> offEmExecuteQuery(VOffExtMovements objVOffExtMovements);

	ReferenceCodes cgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean);

	List<Object> cgfkchkOffEmOffEmVHeade(Offenders paramBean);

	Integer offEmInsertVOffExtMovements(List<VOffExtMovements> lstVOffExtMovements);

	List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup();

	List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup();

	List<VHeaderBlock> cgfklkpOffEmOffEmVHeade(VHeaderBlock paramBean);

	ReferenceCodes cgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean);

}
