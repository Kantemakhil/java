package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;
import net.syscon.s4.inmate.beans.OffenderWorks;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;

/**
 * Interface OtdclinaRepository
 */
public interface OtdclinaRepository {
	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<LockedModules> checkLock();

	OffenderTrustAccountsTemp offTracKeyExeqry(OffenderTrustAccountsTemp paramBean);

	Integer offTracInsertOffenderTrustAccountsTemp(List<OffenderTrustAccountsTemp> lstOffenderTrustAccountsTemp);

	List<OffenderTrustAccountsTemp> offTracExecuteQuery(OffenderTrustAccountsTemp objOffenderTrustAccountsTemp);

	List<OffenderWorks> cgrichkOffenderTrustAccoun(OffenderWorks paramBean);

	List<AccountCodes> zeroAmount(AccountCodes paramBean);

	TransactionOperations checkSetupBeforeTransfer(TransactionOperations paramBean);

	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	TransactionTypes clearZeroBalanceAccounts(TransactionTypes paramBean);

	Integer sysPflUpdateSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	OffenderSubAccounts checkBalanceTally(OffenderSubAccounts paramBean);

	Offenders clearZeroBalanceAccounts(Offenders paramBean);

	BigDecimal closeAccount(Long offenderId);

	OffenderTrustAccountsTemp offTracKeyEntqry(OffenderTrustAccountsTemp paramBean);

	Offenders transferFundsToProPer(Offenders paramBean);

	Integer closeAccountRootOffIdCur();

	Object clearInactiveAccounts(OffenderSubAccounts paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer offTracUpdateOffenderTrustAccountsTemp(List<OffenderTrustAccountsTemp> lstOffenderTrustAccountsTemp);

	List<ReferenceCodes> selectMethodRgRecordGroup();

	Object otdclinaWhenNewFormInstance(Dual paramBean);

	OmsModules createFormGlobals(OmsModules paramBean);

	BigDecimal offTracPopulateTempTable(OffenderTrustAccountsTemp searchRecord);

	Integer updateOffenderTrustAccounts(Long offenderId, Date notifyDate, String caseloadId);

	String offenderSubAccountsMax(Long offenderId, String userId);

	BigDecimal sumBalanceOffenderSubAccounts(Long offenderId, String userId);

	BigDecimal findRootOffenderId(Long offenderId);

	String lastNameFirstName(BigDecimal rootOffenderId, String userId);

	Integer txnIdNextValData();

	String txnIdDescription(String vTxnType);

	Integer insertIntoOffenderTransaction(Integer txnIdNextVal, BigDecimal pTxnEntrySeq, String caseloadId,
			BigDecimal rootOffenderId, BigDecimal offenderBookId, String vTxnType, String lvTxnEntryDesc,
			BigDecimal pBalance, Date transDate, String subActType, String string);

	Integer updateOffenderTransactions(String payeeCode, String txnTypeDescription, Integer txnIdNextVal,
			BigDecimal pTxnEntrySeq, String userId);

	Integer processGlTransNew(String caseloadId, String vTxnType, BigDecimal pBalance, Integer txnIdNextVal,
			Date transDate, String lvTxnEntryDesc, BigDecimal pTxnEntrySeq, BigDecimal rootOffenderId,
			BigDecimal offenderBookId, BigDecimal pGlSeq, String pOperationType, String pSubAccountType,
			String pSubAccountTypeCr);

	Integer accountCodeData(String pSubAcAype, String userId);

	Integer updateOffenderSubAccounts(Integer pTxnNum, BigDecimal pTxnEntrySeq, String caseloadId,
			BigDecimal lvRootOffenderId, Integer pTrustAccountCode, String userId);

	Integer updateOffenderTrustAccountsY(BigDecimal rootOffenderId, String userId);

	Integer countSessionId();

	List<OffenderSubAccounts> balanceSubAccountCode(BigDecimal rootOffenderId, String userId);

	Integer updateOffenderTransactionsTransfer(String lastNameFirstName, Integer txnIdNextVal, BigDecimal pTxnEntrySeq, String userId);

	List<OffenderSubAccounts> balanceSubAccountCodeProp(BigDecimal rootOffenderId, String userId);

	BigDecimal sumBalanceTemp(BigDecimal rootOffenderId, String userId);

	Integer updateOffenderTrustAccountsBalances(BigDecimal sumBalance, BigDecimal rootOffenderId, String userId);

	List<OffenderSubAccounts> balanceSubAccountCodeReg(BigDecimal rootOffenderId, String userId);

	Integer txnTypeCountData(String userId);

}
