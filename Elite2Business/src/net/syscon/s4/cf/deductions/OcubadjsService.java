package net.syscon.s4.cf.deductions;

import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;

public interface OcubadjsService {

	List<ReferenceCodes> adjustmentTypeRecordGroup();

	List<OffFeeBillTransactions> saveBillAdjustdDetCommit(OffFeeBillTransactionsCommitBean commitBean);

	List<OffFeeBillTransactions> billAdjustDetailsExecuteQuery(OffFeeBillTransactions searchBean);
	
	String getSelectedOverrideType(OffFeeBillTransactions searchBean);
}
