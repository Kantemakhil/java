package net.syscon.s4.cf.deductions.maintenance;

import java.util.List;

import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Interface OtmcfeesRepository
 */
public interface OtmcfeesRepository {
	List<DeductionTypes> cgfkTranFdReceiptDeductionRecordGroup(String caseLoadType);

	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseLoadType);

	DeductionTypes cgfkchkCsldDpDedprofDedty(DeductionTypes paramBean);

	Integer csldDpInsertCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> list);

	Integer tierTfaInsertTieredTransactionFeeAmounts(List<TieredTransactionFeeAmounts> list);

	Integer tierTfaUpdateTieredTransactionFeeAmounts(List<TieredTransactionFeeAmounts> list);

	Integer tierTfaDeleteTieredTransactionFeeAmounts(List<TieredTransactionFeeAmounts> list);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles object);

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseLoadType);

	Integer csldDpDeleteCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> list);

	List<TransactionFeeDetails> tranFdExecuteQuery(TransactionFeeDetails object);

	Integer tranFdInsertTransactionFeeDetails(List<TransactionFeeDetails> list);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	Corporates cgfkchkCsldDpDedprofCorp(Corporates paramBean);

	Integer csldDpUpdateCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> list);

	Integer tranFdUpdateTransactionFeeDetails(List<TransactionFeeDetails> list);

	AccountCodes cgfkchkCsldDpDedprofAcCo(AccountCodes paramBean);

	Integer tranFdDeleteTransactionFeeDetails(List<TransactionFeeDetails> list);

	List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup();

	List<TieredTransactionFeeAmounts> tierTfaExecuteQuery(TieredTransactionFeeAmounts object);

	DeductionTypes cgfkchkTranFdTranFdDed(DeductionTypes paramBean);

	String getExternalPriorityNumber(String caseloadId);

	Integer getOverLapCount(TieredTransactionFeeAmounts paramBean);

	String getCorporateName(String payeeCorporateId);

}
