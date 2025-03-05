package net.syscon.s4.inmate.trust.deductions;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VClearAccountPayables;
import net.syscon.s4.inmate.beans.VClearAccountPayablesCommitBean;

/**
 * Interface OcdcbeneService
 */
public interface OcdcbeneService {
	List<BeneficiaryTransactions> benTxnExecuteQuery(BeneficiaryTransactions obj);

	Integer payeeAbCommit(VClearAccountPayablesCommitBean commitBean);

	List<VClearAccountPayables> payeeAbExecuteQuery(VClearAccountPayables obj);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	String createFormGlobals(OmsModules paramBean);

	String checkLock(String caseLoadId);

}
