package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Interface OcdcashrRepository
 */
public interface OcdcashrRepository {
	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(String caseloadId, String caseloadType);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer glTxnInsertGlTransactions(List<GlTransactions> lstGlTransactions);

	String receiptNumber(Long txnId, Long txnEntrySeq);

	String offenderIdDisplayData(BigDecimal offenderId);

	List<TransactionTypes> txnTypeDescriptionData(String modName, String caseloadId, String caseloadType);

	Integer txnIdNextValData();

	BigDecimal txnEntryAmountData(Double amtEnt, Double txnAmt);

	BigDecimal crAccountCodeData(BigDecimal acCode, String caseloadId);

	AccountCodes accountNameTxnPostingType(BigDecimal nbtAcCode);

	AccountCodes txnPostingTypeData(BigDecimal acCode);

	Integer insertGlTransNew(String string, BigDecimal accountCodeOne, String pDrAccntPosting, String caseloadId,
			String txnType, BigDecimal txnEntryAmount, Integer pTxnNum, Date transDate, String adjustDesc,
			Integer pTxnEntrySeq, Integer pGlSeq, BigDecimal offenderId, BigDecimal drOffBookId,
			String txnReferenceNumber);

	BigDecimal bankCrAccountCodeData(BigDecimal acCode, String caseloadId);

	BigDecimal drAccountCodeData(BigDecimal acCode, String caseloadId);

	String profileValData();

	String txnAmountData(String caseloadId, String caseloadType, BigDecimal accountCode, String lvMultiCash, String userName);

	String txnAmountDataSlashes(String returnAmount);

	SystemProfiles getOldRecords(SystemProfiles beanRef);
}
