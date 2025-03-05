package net.syscon.s4.cf.offendertransactions;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

/**
 * Interface OcudpdisService
 */
public interface OcudpdisService {
	OffenderTransactions tbdCommit(OffenderDeductionsCommitBean commitBean);

	OffenderDeductions tbdExecuteQuery(OffenderDeductions object);
}
