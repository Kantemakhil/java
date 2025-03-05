package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.im.beans.OffenderGrievanceTxnsCommitBean;

/**
 * Interface OiuprresService
 */
public interface OiuprresService {
	List<OffenderGrievanceTxns> prresExecuteQuery(OffenderGrievanceTxns objOffenderGrievanceTxns);

	Integer prresCommit(OffenderGrievanceTxnsCommitBean commitBean);

}
