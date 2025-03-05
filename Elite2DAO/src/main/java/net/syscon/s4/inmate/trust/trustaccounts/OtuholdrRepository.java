package net.syscon.s4.inmate.trust.trustaccounts;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
/**
 * Interface OtuholdrRepository
 * */
public interface OtuholdrRepository {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions) ;

	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions) ;

	Integer offTxnUpdateOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions) ;

	Integer offTxnDeleteOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions) ;

	String getVHoldClearFlag(OffenderTransactions paramBean);

	Integer genTrustTrans(String seqId);

	Long getMaxBookId(Long offenderId);

	void processGlTransNew(String pCsldId, String pTransType, String pOperationType, Double pTransAmount, Integer pTransNumber,
			Date pTransDate, String pTransDesc, Integer pTransSeq, String pModuleName, Long pOffId, Long pOffBookId,
			String pSubActTypeDr, String pSubActTypeCr, Long pPayeePersId, BigDecimal pPayeeCorpId, String pPayeeNameText, Integer pGlSqnc,
			BigDecimal pOffDedId);

	void updateOffenderTrustAccounts(Double txnEntryAmount, Long offenderId, String caseloadId, String modifyUserId);

	void updateOffenderSubAccount(Double txnEntryAmount, Long offenderId, String caseloadId, String subAccountType, String modifyUserId);

	void subInsertOffTxnInsert(Integer txnId, Integer txnEntrySeq, String caseloadId, Long offenderId, Long offenderBookId,
			String txnPostingType, String txnType, String txnEntryDesc, Double txnEntryAmount, Date txnEntryDate, String subAccountType,
			Date modifyDate, String modifyUserId, String slipPrintedFlag, String txnAdjustedFlag, String holdClearFlag,
			Integer holdNumber, String createuserId);

	void updateOffenderBalance(String pCsldId, Long pOffId, String pTransPostType, Date pTransDate, Integer pTransNumber,
			String pTransType, Double pTransAmount, String pSubActType, String pAllowOverdrawn);

	void updateHoldRecord(Integer txnId, Integer holdNumber, Long offenderId, String modifyUserId );

	String getOrgTxnType(Integer txnId, Integer txnEntrySeq, Long offenderId);

	void financialDoDuctionsFinancial(String pCsldId, Long pOffId, Long pOffBookId, String pTransType,
			Integer pTransNumber, Date pTransDate, String pSubActType, String pDedFlag, Double pReceiptAmount, Object pShadowId,
			Double pDedAmount, Integer txnSequence, String pInfoNumber);

	void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId);

	

}
