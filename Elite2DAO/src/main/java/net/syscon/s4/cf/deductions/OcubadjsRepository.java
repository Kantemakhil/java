package net.syscon.s4.cf.deductions;

import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.common.beans.ReferenceCodes;

public interface OcubadjsRepository {

	List<ReferenceCodes> adjustmentTypeRecordGroup();

	Integer getstaffId(String userId);

	Integer getBillTranId(String billId);

	Integer offFeeBillTransInsertQuery(OffFeeBillTransactions ofFeeBillBean);

	List<OffFeeBillTransactions> billAdjustDetailsExecuteQuery(OffFeeBillTransactions searchBean);

	String getSelectedOverrideType(OffFeeBillTransactions searchBean);
	
	OffFeeBillTransactions getOriginalBalanceOwing(OffFeeBillTransactions searchBean);
	
	OffFeeBillTransactions getLatestRec(OffFeeBillTransactions searchBean);

	
}
