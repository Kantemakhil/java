package net.syscon.s4.pkgs.trust;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

public interface TrustRepository {

	List<OffenderBeneficiaries> getCorpBeneficiariesRecord(final BigDecimal corporateId);

	Integer getMonAmt(final Long offenderDeductionId, final BigDecimal corporateId);

	Integer getRecMonth(final Long offenderDeductionId);

	String chkAccountStatusSelect(final String caseLoadId, final BigDecimal offenderId);

	Integer tempFlagSelectOperation(final ChkFreezeDisbursements chkFreeze);

	Integer frzFlagSelectOperation(final String caseLoadId, final Long offenderId, final Date date);

	String csldTypeC(final String caseLoadId);

	String getLowHighFlag();

	List<OffenderDeductionReceipts> getRateCur(final String disbuType, final Long dedId);

	String getVTxnRefNum(final Long transNumber, final Long transSeq);

	Integer glTransInsert(final GlTransactions glTra);

	Long insertIntoChequeDataSelectOperation(final String caseloadId, final String moduleName, final String transType, String userName);

	Integer insertIntoChequeDataInsertOperation(final BankChequeData cheData);

	void updateOffenderSubAccounts(final Date txnEntryDate, final Integer txnId, final Double postingAmount,
			final String caseloadId, final Long offenderId, final String subAccountType, final String caseLoadType,
			final String userName);

	void updateOffenderTrustAccount(final Date txnEntryDate, final Double postingAmount, final String caseloadId,
			final Long offenderId, final String userName);

	Double getBalanceFromOffenderAccounts(final String caseloadId, final Long offenderId, final String subAccountType,
			final String caseLoadType);

	void updateOffenderSubAccountsWhenOverridenFlagN(final Date txnEntryDate, final Integer txnId,
			final Double postingAmount, final String caseloadId, final Long offenderId, final String subAccountType,
			final String caseLoadType, final String userName);

	List<AccountCodes> getSubActNdTxnPostTypesDr(final OtddisbuProcessTransactionsBean proTxn1);

	String getCaseLoadType(final String caseloadId);

	TransactionTypes getTransTypes(final String txnType, final String caseLoadType);

	List<String> checkCaselodexist(final String caseloadId);

	SystemProfiles getsysProfiles(final SystemProfiles paramBean);

	String getagyLocId(final BigDecimal offenderId, final String caseLoadType,String userId );

	InstitutionMiniBalances getInstMiniBalance(final String caseloadId, final String agyLocId,
			final BigDecimal accountCodeOne);

	BigDecimal getDrAccountCode(final String caseloadId, final String moduleName, final String txnType);

	OffenderSubAccounts getOffSubAccounts(final String caseloadId, final BigDecimal offenderId,
			final BigDecimal drAccountCode);

	OffenderSubAccounts getOffSubAccountstwo(final String caseloadId, final BigDecimal offenderId,
			final BigDecimal drAccountCode);

	BigDecimal getIndDays(final String caseloadId, final BigDecimal offenderId, final BigDecimal drAccountCode);

	String lCheckCaseloadCur(final String pCsldId);

	String lAgyLocIdCur(final BigDecimal pOffId, final String lvCsldType,String userName);

	InstitutionMiniBalances lMinBalanceCur(final String pCsldId, final String lAgyLocId, final BigDecimal trustacccode);

	SystemProfiles lSystemProfilesCur(final String pProfileType, final String pProfileCode);

	BigDecimal lIndDays(final String pCsldId, final BigDecimal pOffId, final BigDecimal trustacccode);

	TransactionTypes transactionTypesC(final String csldType, final String lvSetupCsldType, String txntype);

	String getCrob(final String obligationtype, final String lvSetupCsldType, final String csldType);

	CaseloadDeductionProfiles indigentflag(final String pCsldId, final String obligationtype);

	Integer getCount();

	List<OffenderSubAccounts> chkIndAcOthersC(final BigDecimal pOffId, final String pCsldId, final BigDecimal trstcode);

	OffenderLimits getOffenderLimits(final String caseloadid, final BigDecimal offenderid, final String limittype);

	OffenderLimits getOffenderLimitsTwo(final String caseloadid, final String limittype);

	Integer getWeekDay();

	Date getFromDate(final Integer weekday);

	Date getToDate(final Integer weekday);

	Date getFromDateTwo();

	Date getToDateTwo();

	BigDecimal getTotalTaken(final BigDecimal pOffenderId, final String dedType, final String caseloadId,
			final Date fromDate, final Date toDate);

	BigDecimal getTotalReversed(final BigDecimal pOffenderId, final String dedType, final String caseloadId,
			final Date fromDate, final Date toDate);

	BigDecimal getAmountWrittenOff(final BigDecimal offenderId, final String limittype, final String caseloadid,
			final Date fromDate, final Date toDate);

	AccountCodes getAccountCodes(final String formName, final String obligationtype, final String lvSetupcsldType,
			final String csldType, final String pSetupCaseload, final String caseload);

	OffenderDeductions getOffenderDeductions(final String caseload, final BigDecimal offenderid,
			final String obligationtype);

	CaseloadDeductionProfiles getCaseloadDeductionProfiles(final String caseload, final String obligationtype);

	Integer getLlCnt(final BigDecimal offenderid, final String obligationtype, final String infonum);

	BigDecimal getPrtyNmbr(final BigDecimal offenderid, final String obligationtype);

	Long getOffdedid();

	Integer insertOffenderDeductions(final OffenderDeductions searchBean);

	Integer insertOffenderDeductionReceipts(final String caseload, final BigDecimal offenderid, final Long offdedid);

	Integer insertOffenderBeneficiaries(final BigDecimal offenderid, final Long offdedid, final BigDecimal payeecorp,
			final String userName);

	BigDecimal getOffBookId(final BigDecimal pOffId);

	Integer insertOffenderTransactions(final BigDecimal pTransNumber, final Long pTransSeq, final String pCsldId,
			final BigDecimal pOffId, final BigDecimal offBookId, final String pTransPostType, final String pTransType,
			final String pTransDesc, final Double pTransAmount, final Date pTransDate, final String pSubActType,
			final String pSlipPrintFlag, final Double pPreDedAmount, final String pDeductionFlag,
			final String pPayeeCode, final BigDecimal pPayeeCorpId, final BigDecimal pPayeePersonId,
			final String pPayeeNameText, final String pDeductionType, final String pInfoNumber, final String userName);

	AccountCodes postingC(final String pSubActTypeDr, final String pSubActTypeCr, final String setupCsldType,
			final String pTransType, final String pModuleName, final String pOperationType, final String pSetupCsldId);

	Integer updateOffenderDeductions(final BigDecimal maxlimit, final Double overdrawn, final Date todayDate,
			final Long offdeductionid, final String userName);

	Integer updateOffenderBeneficiaries(final Double overdrawn, final Date todayDate, final Long offdeductionid,
			final String userName);

	List<AccountCodes> getSubActNdTxnPostTypesCr(final OtddisbuProcessTransactionsBean proTxn1);

	List<AccountCodes> postingCCursor(final Object pSubActTypeDr, final String pSubActTypeCr, final String csldType,
			final String pTransType, final String pModuleName, final Object pOperationType, final String pCsldId);

	String trustCommunityCsld(final String caseloadId);

	Long getOffenderBookIdCurForInsert(BigDecimal pOffId, String pCsldId);

	Integer insertOffenderTransactions(final OffenderTransactions offTrans);
	
	AccountCodes getDrTxnOpr(String disbuType,String modName,String csldId,String caseloadtype);
	
	 List<OffenderDeductions> getTxnFeeType(Long offId,String csldId,String caseloadtype);

	BigDecimal gettingLvOffBalance(String caseloadId, String csldType, Long pOffId, Integer accountCode);

	BigDecimal getSubBal(String pCsldId, Long pOffId, String deductionType, String caseloadType);

	BigDecimal gettingSubBal(String deductionType, Long pOffId, String pCsldId, String caseloadType);

	BigDecimal gettingVMinBal(String pCsldId, String deductionType, String pTransType);
}