package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VClearAccountPayables;

/**
 * Interface OcdcbeneRepository
 */
public interface OcdcbeneRepository {
	List<BeneficiaryTransactions> benTxnExecuteQuery(BeneficiaryTransactions obj);

	List<VClearAccountPayables> payeeAbExecuteQuery(VClearAccountPayables obj);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer payeeAbUpdateVClearAccountPayables(List<VClearAccountPayables> obj);

	String createFormGlobals(OmsModules paramBean);

	BeneficiaryTransactions offenderNameCursor(BeneficiaryTransactions obj);

	String txnPostingTypeCursor(Integer valueOf);

	String getPersonsInfoCursor(BigDecimal personId);

	String getCorporateInfoCursor(BigDecimal corporateId);

	BigDecimal getMinimuimBalCursor(BigDecimal accountCode);

	String checkLock(String caseLoadId);

	Integer txnIdNextValData();

	Integer processBankCheque(BigDecimal personId, BigDecimal corporateId, BigDecimal accountCode, String corporateName,
			String caseloadId, BigDecimal totalAmount, Integer txnIdNextVal);

	List<BeneficiaryTransactions> personIdExecuteQuery(VClearAccountPayables obj);

	List<BeneficiaryTransactions> corporateIdExecuteQuery(VClearAccountPayables obj);

	TransactionOperation checkDetailsExecuteQuery(VClearAccountPayables obj);

	Integer personIdCheck(BigDecimal personId, BigDecimal offenderDeductionId);

	Integer corporateIdCheck(BigDecimal personId, BigDecimal offenderDeductionId);

	Integer updatePersonBeneficiaryTransactions(BeneficiaryTransactions action);

	Integer updateCorporateBeneficiaryTransactions(BeneficiaryTransactions action);

	Integer insertPersonBeneficiaryTransactions(Integer txnIdNextVal, BigDecimal totalAmount, BigDecimal personId,
			Long txnId, Long rootOffenderId, BigDecimal lAbsTxnEntryAmount, BigDecimal offenderDeductionId);

	Integer insertCorporateBeneficiaryTransactions(Integer txnIdNextVal, BigDecimal totalAmount, BigDecimal corporateId,
			Long txnId, Long rootOffenderId, BigDecimal lAbsTxnEntryAmount, BigDecimal offenderDeductionId);

	void checkCaseloadUnlockType(String caseloadId);

	void checkCaseloadlockType(String caseloadId);

}
