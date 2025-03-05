package net.syscon.s4.cf.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OtdocfeeRepository
 */
public interface OtdocfeeRepository {
	Object offDedPreInsert(SysDual paramBean);

	Integer offDedUpdateOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions);

	Long offBkgOnCheckDeleteMaster(final OffenderDeductions paramBean);

	List<ReferenceCodes> cgfklkpOffDedOffDedRef(final ReferenceCodes paramBean);

	Integer offTfdUpdateOffenderTxnFeeDetails(final List<OffenderTxnFeeDetails> lstOffenderTxnFeeDetails);

	List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup();

	Integer offTfdDeleteOffenderTxnFeeDetails(final List<OffenderTxnFeeDetails> lstOffenderTxnFeeDetails);

	Long cgrichkOffenderDeductions(final OffenderPaymentPlan paramBean);

	List<DeductionTypes> cgfkchkOffTfdOffTfdDed(final DeductionTypes paramBean);

	List<OffenderTxnFeeDetails> offTfdExecuteQuery(final OffenderTxnFeeDetails objOffenderTxnFeeDetails);

	Integer offTtfInsertOffenderTierTxnFeeAmounts(final List<OffenderTierTxnFeeAmounts> lstOffenderTierTxnFeeAmounts);

	Long cgrichkOffenderDeductions(final OffenderTransactions paramBean);

	Integer offDedInsertOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions);

	List<SysDual> cgwhenNewFormInstance(final SysDual paramBean);

	List<OffenderTierTxnFeeAmounts> offTtfExecuteQuery(final OffenderTierTxnFeeAmounts objOffenderTierTxnFeeAmounts);

	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseloadId, final String caseloadType);

	List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions objOffenderDeductions);

	Integer offTfdInsertOffenderTxnFeeDetails(final List<OffenderTxnFeeDetails> lstOffenderTxnFeeDetails);

	List<ReferenceCodes> cgfkchkOffDedOffDedRef(final ReferenceCodes paramBean);

	List<DeductionTypes> cgfkOffTfdReceiptDeductionRecordGroup(final String caseloadType);

	List<DeductionTypes> cgfkchkOffDedOffDedDed(final DeductionTypes paramBean);

	Integer offTtfDeleteOffenderTierTxnFeeAmounts(final List<OffenderTierTxnFeeAmounts> lstOffenderTierTxnFeeAmounts);

	Integer offTtfUpdateOffenderTierTxnFeeAmounts(final List<OffenderTierTxnFeeAmounts> lstOffenderTierTxnFeeAmounts);

	List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSystemProfiles);

	Integer otdocfeePopulateDetails(OffenderTxnFeeDetails paramBean);

	Long otdocfeeCgrichkOffenderDeductionsOffenderDeductionId(OffenderPaymentPlan paramBean);

	Long otdocfeeCgrichkOffenderDeductionsInformationNumber(OffenderPaymentPlan paramBean);

	String otdocfeeGetCaseloadType(final String caseloadId);

	BigDecimal otdocfeePostQueryTwoPayeeCorporateId(final CaseloadDeductionProfiles paramBean);

	Integer otdocfeePostQueryTwoUnknownBenId();

	Integer insertIntoOffenderBeneficiaries(final OffenderBeneficiaries offBeneficiaries);

	List<TransactionFeeDetails> otdocfeePopulateDetailsReceiptDeductionType(String deductionType, String caseloadId);

	Integer insertIntoOffenderTxnFeeDetails(OffenderTxnFeeDetails objectOne);

	List<TieredTransactionFeeAmounts> otdocfeePopulateDetailsTieredTransactionFeeAmounts(final OffenderDeductions paramBean);

	Integer insertIntoOffenderTierTxnFeeAmounts(OffenderTierTxnFeeAmounts returnObj);

	Long otdocfeeGetOffenderDeductionId(Long deductionId);

	Long otdocfeePostQueryTwo();

	Long getOffenderId();
	Integer getOverLapCount(OffenderTierTxnFeeAmounts paramBean);

}
