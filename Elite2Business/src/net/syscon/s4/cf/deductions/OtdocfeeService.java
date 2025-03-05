package net.syscon.s4.cf.deductions;

import java.util.List;

import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmountsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetailsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

/**
 * Interface OtdocfeeService
 */
public interface OtdocfeeService {
	List<ReferenceCodes> cgfkchkOffDedOffDedRef(final ReferenceCodes paramBean);

	Long cgrichkOffenderDeductions(final OffenderTransactions paramBean);

	List<SysDual> cgwhenNewFormInstance(final SysDual paramBean);

	List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup();

	Long offBkgOnCheckDeleteMaster(final OffenderDeductions paramBean);

	List<DeductionTypes> cgfkchkOffTfdOffTfdDed(final DeductionTypes paramBean);

	List<DeductionTypes> cgfkchkOffDedOffDedDed(final DeductionTypes paramBean);

	List<OffenderTxnFeeDetails> offTfdExecuteQuery(final OffenderTxnFeeDetails objOffenderTxnFeeDetails);

	Integer offTtfCommit(final OffenderTierTxnFeeAmountsCommitBean commitBean);

	List<OffenderTierTxnFeeAmounts> offTtfExecuteQuery(final OffenderTierTxnFeeAmounts objOffenderTierTxnFeeAmounts);

	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseloadId, final String caseloadType);

	List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions objOffenderDeductions);

	Object offDedPreInsert(final SysDual paramBean);

	List<DeductionTypes> cgfkOffTfdReceiptDeductionRecordGroup(final String caseloadType);

	List<ReferenceCodes> cgfklkpOffDedOffDedRef(final ReferenceCodes paramBean);

	Integer offDedCommit(final OffenderDeductionsCommitBean commitBean);

	Integer offTfdCommit(final OffenderTxnFeeDetailsCommitBean commitBean);

	List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSystemProfiles);

	Integer otdocfeePopulateDetailsData(OffenderDeductions searchBean);
	Integer getOverLapCount(OffenderTierTxnFeeAmounts paramBean);

}
