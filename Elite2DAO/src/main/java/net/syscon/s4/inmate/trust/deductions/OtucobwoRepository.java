package net.syscon.s4.inmate.trust.deductions;

import java.util.List;

import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OtucobwoRepository
 * */
public interface OtucobwoRepository {
	List<TransactionOperation> cgfkCobwoAdjustmentReasoRecordGroup(String caseloadId) ;

	Long getTxnId();

	List<OffenderAdjustmentTxns> writeOffGl(OffenderAdjustmentTxns bean);

	Integer insertintoGltransactionsDr(OffenderAdjustmentTxns bean);

	Integer insertintoGltransactionsCr(OffenderAdjustmentTxns bean);

	Integer insertintoOffnederAdjustmentTxns(OffenderAdjustmentTxns bean);

	Integer updateOffenderdeductions(OffenderAdjustmentTxns bean);

	List<OffenderDeductions> offenderDeductionCur(Long deductionId);

}
