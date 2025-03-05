package net.syscon.s4.pkgs.deductions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

public interface DeductionsRepository {

	List<OffenderDeductions> chkDedCur(final Long pOffId, final Long pShadowId, final String csldType);

	String chkReceiptType(final Long offenderDeductionId, final String pTransType);

	List<OffenderSubAccounts> getMinSubIndValues(final Integer accountCode, final Long offenderId,
			final String caseloadId);

	Double getMinBalance(final Integer accountCode, final Long offenderId, final String caseloadId);

	Double getMinBalanceOne(final Integer accountCode, final String caseloadId);

	void updateOffenderSubAccountsInd(final Integer accountCode, final Long offenderId, final String caseloadId,
			final String userName);

	void updateOffenderSubAccountsIndOne(final Integer accountCode, final Long offenderId, final String caseloadId,
			final String userName);

	List<CaseloadDeductionProfiles> cCondDedCursor(final String pCsldId, final String pConditionType,
			final String pConditionCode, final String pConCategoryType, final BigDecimal corporateId,
			final String csldType);

	Long uniUi2Select(final String caseloadId, final String deductionType, final BigDecimal pOffId,
			final String pInfoNumber);

	Long vDedIdSelect();

	BigDecimal vOdpSelect(final String deductionType, final BigDecimal pOffId);

	BigDecimal lvGroupIdSelect(final String deductionType);

	void insertOffenderDeductions(final OffenderDeductions offDed);

	Integer chkCaseloadC(final String caseloadId);

	List<OffenderSubAccounts> chkIndAcC(final Long offenderId, final String caseloadId, final String csldType);

	List<SystemProfiles> systemProfileC();

	Integer getTrustAccountCodeC();

	Double sumOffSubActBalC(final Integer lvTrustAccountCode, final Long offenderId);

	Date maxIndDateC(final Integer lvTrustAccountCode, final Long offenderId);

	void updateOffenderSubAccounts(final Date lvIndigentDate, final String lvIndigentDaysLimit,
			final Integer lvTrustAccountCode, final Long offenderId, final String userName);

	void updateOffenderSubAccountsOne(final Integer lvTrustAccountCode, final Long offenderId, final String userName);

	void insertOffenderBeneficiaries(final BigDecimal pOffId, final BigDecimal pTotalAmount, final String pCsldId,
			final String pConditionType, final String pConditionCode, final String pConCategoryType,
			final Date vSysdate, final String userName);

	List<CaseloadDeductionDetails> cReceiptCursor(final BigDecimal pOffId, final String pCsldId, final String csldType);

	Integer existFlagSelect(final Long offenderDeductionId, final String receiptTxnType);

	void insertOffenderDeductionReceipts(final Long offenderDeductionId, final String receiptTxnType,
			final BigDecimal percentage, final BigDecimal flatRate, final String userName);

	List<CaseloadDeductionProfiles> defaultDeductionCursor(final String pCsldId, final Integer pOffId,
			final String csldType, final BigDecimal corporateId);

	BigDecimal maxOdpCursor(final String deductionType, final Integer pOffId);

	Integer offenderDeductionsInser(final CaseloadDeductionProfiles eachDed, final String userName);

	Integer offenderBeneficiariesInsert(final String pCsldId, final Integer pOffId, final Date vSysdate,
			final String userName);

	Integer offenderBeneficiariesInsertOne(final String pCsldId, final Integer pOffId, final Date vSysdate,
			final String userName);

	List<CaseloadDeductionDetails> cReceiptCursor(final String pCsldId, final Integer pOffId, final String csldType);

	Integer offenderDeductionReceiptsSelectOperation(final Long offenderDeductionId, final String receiptTxnType);

	Integer offenderDeductionReceiptsInsert(final CaseloadDeductionDetails csldDedDtais, final String userName);

	List<OffenderDeductions> cColFeeCursor(final Integer pOffId, final String pCsldId, final String csldType);

	Integer offenderTxnFeeDetailsSelect(final Long offenderDeductionId, final String receiptDeductionType);

	Integer offenderTxnFeeDetailsInsert(final Long offenderDeductionId, final String receiptDeductionType,
			final String userName);

	List<TieredTransactionFeeAmounts> cFeeAmtCursor(final Integer pOffId, final String pCsldId);

	Integer offenderTierTxnFeeAmountsInsert(final TieredTransactionFeeAmounts tieredTranFeeAms);

	Integer updateDeductionStatus(final SuspendDeductions obj, final String userName);

	List<SuspendDeductions> getSuspendDeductionsDetails();
}
