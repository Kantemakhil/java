package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.SuspendDeductionTypes;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.SuspendDeductionTypesCommitBean;
import net.syscon.s4.inmate.beans.SuspendDeductionsCommitBean;

/**
 * Interface OtdsdeduService
 */
public interface OtdsdeduService {
	String susDtCommit(SuspendDeductionTypesCommitBean CommitBean);

	DeductionTypes cgfkchkSusDtSusDtDedTyp(DeductionTypes paramBean);

	List<SuspendDeductionTypes> susDtExecuteQuery(SuspendDeductionTypes objSuspendDeductionTypes);

	List<Caseloads> cgfkSusDedCaseloadIdRecordGroup();

	List<SuspendDeductions> susDedExecuteQuery(SuspendDeductions objSuspendDeductions);

	String susDedCommit(SuspendDeductionsCommitBean CommitBean);

	Caseloads cgfkchkSusDedSusDedCasel(Caseloads paramBean);

	List<DeductionTypes> cgfkSusDtDeductionTypeRecordGroup(String caseloadType);

	Integer deductionsUpdateDeductionStatus(Long pSusDedId, String pCaseloadId, String pSuspendFlag,
			String pDeductionType,String userId);

	Integer deductionsSuspendOffDeductions();

	BigDecimal chkOverlapDate(String caseloadId, String startDate, String endDate, String flag);

}
