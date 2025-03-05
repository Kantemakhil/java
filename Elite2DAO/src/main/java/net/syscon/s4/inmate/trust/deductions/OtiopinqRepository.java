package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OtiopinqRepository {
	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<DeductionTypes> cgfkchkOffDedOffDedDed();

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	String chkDedCat(String deductionType);

	BigDecimal otiopinqSubMaxTotDedctAmt(Long offenderId, String caseloadId, String deductionType,
			BigDecimal deductionPriority);

	BigDecimal getDedAmtOnDate(Long offenderDeductionId);

	BigDecimal adjustmentAmountC(Long offenderDeductionId);

	Map<String, Object> lvTotalAmount(Long offenderDeductionId);

}
