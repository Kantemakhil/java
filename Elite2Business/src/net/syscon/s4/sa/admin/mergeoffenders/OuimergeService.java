package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.MergeTransactions;

/**
 * Interface OuimergeService
 */
public interface OuimergeService {
	List<MergeTransactions> transactionsExecuteQuery(MergeTransactions object);

	List<ReferenceCodes> rgStatusRecordGroup();

	List<ReferenceCodes> rgSourceRecordGroup();

}
