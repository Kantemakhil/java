package net.syscon.s4.cf.deductions.maintenance;

import java.util.List;

import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmountsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetailsCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Interface OtmcfeesService
 */
public interface OtmcfeesService {
	List<DeductionTypes> cgfkTranFdReceiptDeductionRecordGroup(String caseLoadType);

	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseLoadType);

	List<TieredTransactionFeeAmounts> tierTfaCommit(TieredTransactionFeeAmountsCommitBean commitBean);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles object);

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseLoadType);

	CaseloadDeductionProfiles getCorporateName(CaseloadDeductionProfiles paramBean);

	List<TransactionFeeDetails> tranFdCommit(TransactionFeeDetailsCommitBean commitBean);

	List<TransactionFeeDetails> tranFdExecuteQuery(TransactionFeeDetails object);

	List<CaseloadDeductionProfiles> csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean);

	List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup();

	List<TieredTransactionFeeAmounts> tierTfaExecuteQuery(TieredTransactionFeeAmounts object);

	Integer getOverLapCount(TieredTransactionFeeAmounts paramBean);

}
