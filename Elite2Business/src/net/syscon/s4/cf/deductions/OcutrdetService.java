package net.syscon.s4.cf.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.beans.ReferenceCodes;

public interface OcutrdetService {

	List<ReferenceCodes> overrideTypeRecordGroup();

	List<OffFeeBills> billDetailsExecuteQuery(OffFeeBills searchBean);

	FeeAccountProfiles feeActExecuteQuery(FeeAccountProfiles searchBean);

	List<OffFeeBillTransactions> billTransDetailsExecuteQuery(OffFeeBillTransactions searchBean);

	List<OffFeeBillTransactions> billTransTotalExecuteQuery(OffFeeBillTransactions searchBean);

	Integer updateBillTransactionDeails(List<OffFeeBills> bean);
	
	Integer sysPflBillAdjusExecuteQuery(String userName);
	
	Integer sysPflBillStatusExecuteQuery(String userName);
	
	List<FeeAccountBalanceBean> billTransReportExecuteQuery(final OffFeeBillTransactions searchBean);
	
	BigDecimal calculateCurrentBalance(FeeAccountProfiles searchBean);
}
