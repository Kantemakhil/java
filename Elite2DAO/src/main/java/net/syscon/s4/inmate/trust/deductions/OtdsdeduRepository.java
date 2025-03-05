package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.SuspendDeductionTypes;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Interface OtdsdeduRepository
 */
public interface OtdsdeduRepository {
	List<SuspendDeductionTypes> susDtExecuteQuery(SuspendDeductionTypes objSuspendDeductionTypes);

	List<SuspendDeductions> susDedExecuteQuery(SuspendDeductions objSuspendDeductions);

	Integer susDtDeleteSuspendDeductionTypes(List<SuspendDeductionTypes> lstSuspendDeductionTypes);

	Caseloads cgfkchkSusDedSusDedCasel(Caseloads paramBean);

	List<Caseloads> cgfkSusDedCaseloadIdRecordGroup();

	DeductionTypes cgfkchkSusDtSusDtDedTyp(DeductionTypes paramBean);

	Integer susDtInsertSuspendDeductionTypes(List<SuspendDeductionTypes> lstSuspendDeductionTypes);

	Integer susDtUpdateSuspendDeductionTypes(List<SuspendDeductionTypes> lstSuspendDeductionTypes);

	List<DeductionTypes> cgfkSusDtDeductionTypeRecordGroup(String caseloadType);

	Integer susDedDeleteSuspendDeductions(List<SuspendDeductions> lstSuspendDeductions);

	Integer susDedUpdateSuspendDeductions(List<SuspendDeductions> lstSuspendDeductions);

	Integer susDedInsertSuspendDeductions(List<SuspendDeductions> lstSuspendDeductions);

	Long suspendDeductionsPreInsertc();

	Integer deductionsUpdateDeductionStatus(Long pSusDedId, String pCaseloadId, String pSuspendFlag,
			String pDeductionType);

	Integer deductionsSuspendOffDeductions();

	BigDecimal chkOverlapDate(String caseloadId, String startDate, String endDate, String flag);

}
