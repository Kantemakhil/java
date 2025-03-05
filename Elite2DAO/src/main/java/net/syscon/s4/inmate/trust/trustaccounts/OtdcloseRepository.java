package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtdcloseRepository
 */
public interface OtdcloseRepository {
	Integer offSubaUpdateOffenderSubAccounts(List<OffenderSubAccounts> object);

	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> object);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts object);

	List<ReferenceCodes> cgfkOffTxnPayeeCodeRecordGroup();

	List<ReferenceCodes> cgfkchkOffTxnOffTxnRef(ReferenceCodes paramBean);

	Integer offSubaInsertOffenderSubAccounts(List<OffenderSubAccounts> object);

	ReferenceCodes offTxnWhenNewBlockInstance(ReferenceCodes paramBean);

	Integer offSubaDeleteOffenderSubAccounts(List<OffenderSubAccounts> object);

	BigDecimal getRegBal(Long rootOffenderId, String caseloadId);

	List<AccountCodes> accountNameForValidation();

	String pCashtxnOperation(String userName);

	String pChecktxnOperation(String userName);

	String accountClosedFlagValidation(Long offenderId, String caseloadId);

	Integer offenderForeignCurrenciesCount(Long offenderId, String caseloadId);

	Double holdDataCount(Long offenderId, String caseloadId);

	List<OffenderSubAccounts> balanceSubAccountCode(Long offenderId, String caseloadId);

	Integer txnIdNextValData();

	Integer offTxnInsertOffenderTransactions(Integer txnIdNextVal, BigDecimal pTxnEntrySeq, String caseloadId,
			Long offenderId, Long offenderBookId, String string, String string2, String string3, Double pBalance,
			String pSuAaccountType, String string4, String string5, String string6, String string7, String payeeCode,
			Integer payeeCorporateId, Integer payeePersonId, String userName);

	void updateOffenderBalance(String caseloadId, Long offenderId, String string, Date transDate, Integer txnIdNextVal,
			String string2, Double pBalance, String pSuAaccountType);

	Integer processGlTransNew(String caseloadId, String string, String object, Double pBalance, Integer txnIdNextVal,
			Date transDate, String string2, BigDecimal pGlTxnEntrySeq, Long offenderId, Long offenderBookId,
			String pSuAaccountType, String string3, Integer object2, Integer object3, String object4,
			BigDecimal pGlSeq);

	Integer accountCodeNumber(String pSuAaccountType, String userName);

	Integer updateOffenderSubAccounts(Integer txnIdNextVal, BigDecimal pTxnEntrySeq, String caseloadId, Long offenderId,
			Integer pTrustAccountCode, String userName);

	String lastNameFirstName(Long offenderId, String userName);

	List<OffenderSubAccounts> balanceSubAccountCodeProp(Long offenderId, String caseloadId, String userName);

	Integer insertIntoOffenderTransaction(Integer txnIdNextVal, BigDecimal pTxnEntrySeq, String caseloadId, Long offenderId,
			Long offenderBookId, String lvTxnType, String lvTxnEntryDesc, Double pBalance, Date transDate,
			String pSuAaccountType);

	Integer updateOffenderTransactionsTransfer(String lastNameFirstName, Integer txnIdNextVal, BigDecimal pTxnEntrySeq);

	String personIdLastfirstName(Integer payeePersonId);

	String corporateIdLastfirstName(Integer payeeCorporateId);

	Integer insertIntoChequeData(String caseloadId, Integer txnIdTwo, Double nbtTxnEntryAmount2, Integer pTxnNumber,
			Integer txnIdTwo2, Integer payeePersonId, String payeeNameText, Long offenderId, String vTxnType);

	Integer updateOffenderTrustAccountsY(Long offenderId, String caseloadId);

	BigDecimal chkAccountClosedFlag(Long offenderId, String caseloadId, String userName);

	String freezDisbursement(String caseloadId, Long offenderId, String pTxnType, String caseloadType);

	BigDecimal chkSubAccountFlag(Long offenderId, String caseloadId);
}
