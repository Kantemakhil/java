package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.cm.programsservices.OffenderUnpaidWorkAdj;
import net.syscon.s4.genericservices.CustomException;

public interface OffenderUnpaidWorkAdjTr1Service {
	Integer offenderUnpaidWorkAdjTr1Tgr(List<OffenderUnpaidWorkAdj> OffenderUnpaidWorkAdjList) throws CustomException;
}
