package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtdttaccRepository
 */
public interface OtdttaccRepository {
	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	Integer acPrdInsertAccountPeriods(List<AccountPeriods> lstAccountPeriods);

	TransactionTypes cgfkchkCsldTtCsldTyTxn(TransactionTypes paramBean);

	Integer acPrdUpdateAccountPeriods(List<AccountPeriods> lstAccountPeriods);

	List<CaseloadTransactionTypes> csldTtExecuteQuery(CaseloadTransactionTypes objCaseloadTransactionTypes);

	List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup(String caseLoadId);

	Caseloads cgfkchkCsldTtCsldTtCsld(Caseloads paramBean);

	List<AccountPeriods> acPrdExecuteQuery(AccountPeriods objAccountPeriods);

	Integer csldTtInsertCaseloadTransactionTypes(List<CaseloadTransactionTypes> lstCaseloadTransactionTypes);

	// List<M> cgfkOffTxnOffenderIdRecordGroup();

	Integer offTxnUpdateOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	// List<Object> cgwhenNewFormInstance(SysDual paramBean);

	Integer csldTtDeleteCaseloadTransactionTypes(List<CaseloadTransactionTypes> lstCaseloadTransactionTypes);

	List<TransactionTypes> cgfkCsldTtTxnTypeRecordGroup(String caseLoadId);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	String getHoldClearFlag(String caseloadId, Long  offenderId, String casaeloadType);

	List<OffenderSubAccounts>  getHoldBal(String caseloadId, Long offenderId, String txnType);

	String getDuplicateOffenders(Long offenderId);

	List<Corporates> getCorporateidNames(String toCaseload);

	Object checkTxnType(String toCaseload, String txnType, String caseloadId);

	Integer getNextVal();

	List<OffenderSubAcShadows> gettxnAmtandTxnsubAc(Long offenderId, String caseloadId);

	void updateOffenderBalance(List<OffenderTransactions> insertList);

	Integer processGlTransNew(List<OffenderTransactions> insertList);

	Integer offTxnTorInsertOffenderTransactions(List<OffenderTransactions> insertList);

	List<OffenderSubAcShadows> getTrustacTransAmt(Long offenderId,String caseloadId, String subAccountType);

	Integer offTxnCheckAmtInsertOffenderTransactions(List<OffenderTransactions> insertList);

	Integer processGlTransNewforcheckAmt(List<OffenderTransactions> insertList);

	Integer deleteoffendersubAcShadows(List<OffenderTransactions> insertList);

	String getcheckProductionflag(OffenderTransactions tempBean);

	String checkChkRrecCur(OffenderTransactions tempBean);

	void insertIntoChequeData(OffenderTransactions tempBean);

	void updatecheckAmt(OffenderTransactions tempBean);

	String trustTranExistCur(OffenderTransactions tempBean);

	void trustTranupdateOffendertrustTrans(OffenderTransactions tempBean);

	void trustTranInsertOffendertrustTrans(OffenderTransactions tempBean);

	OffenderTransactions getRootOffenderId(String casaeloadType, String offenderIdDisplay);

	List<OffenderTransactions> whenNewBlockInstanceRetrive(Date startDate, Date endDate, String currentCaseload,
			String toCaseload,final String txnType);

	Integer inserOffenderAcntShadows(OffenderSubAccounts objSub);

	Integer deleteOffacShads(String caseloadId, Long offenderId, String modifyUserId);

	List<OffenderSubAcShadows> selectOffenderAcntShadows(String caseloadId, Long offenderId);

	List<VTrustHeader> getRootOffenderIds(String caseloadId, String offenderIdDisplay);

	String deductionGetAcAndSetIndDate(Long offenderId, String caseloadId);

	OffenderTransactions getBookIdandStatusDisplay(Long rootOffenderId, String casaeloadType, String userName);


	// List<VTrustHeaderA> cgfkchkOffTxnOffTxnVTha(VTrustHeaderA paramBean);

}
