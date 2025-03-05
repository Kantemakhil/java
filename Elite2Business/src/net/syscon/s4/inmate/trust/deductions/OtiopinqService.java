package net.syscon.s4.inmate.trust.deductions;

import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

public interface OtiopinqService {
	List<Object> CgwhenNewFormInstance();

	Integer offTxnCommit(OffenderTransactionsCommitBean commitBean);

	Integer offDedCommit(OffenderDeductionsCommitBean commitBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	Object offBkgPreDelete();

	List<DeductionTypes> cgfkchkOffDedOffDedDed();

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	String chkDedCat(String deductionType);

}
