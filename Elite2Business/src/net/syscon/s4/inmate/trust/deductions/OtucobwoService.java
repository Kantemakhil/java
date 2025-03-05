package net.syscon.s4.inmate.trust.deductions;

import java.util.List;

import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;
import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OtucobwoService  {
	
	List<TransactionOperation> cgfkCobwoAdjustmentReasoRecordGroup(String caseloadId) ;

	List<OffenderAdjustmentTxns> save(OffenderAdjustmentTxns bean);

	List<OffenderDeductions> offenderDeductionCur(Long deductionId);

}
