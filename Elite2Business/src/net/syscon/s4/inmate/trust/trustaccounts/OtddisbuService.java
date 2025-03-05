package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.GetOffenderSubBalanceBean;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;

/**
 * Interface OtddisbuService
 */
public interface OtddisbuService {
	
	List<Object> CgwhenNewFormInstance() ;

	List<OffenderTransactions> offTxn1Commit(OffenderTransactionsCommitBean commitBean) ;

	Integer offTxnCommit(OffenderTransactionsCommitBean CommitBean) ;

	List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions objOffenderTransactions) ;

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions) ;

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles) ;

	Integer sysPflCommit(SystemProfilesCommitBean commitBean) ;

	TransactionTypes CgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean) ;

	List<Offenders> CgfkchkOffTxnOffTxnOff(Offenders paramBean) ;

	// List<MMCrp.corporateName> cgfkOffTxn1PayeeCorporateRecordGroup() throws
	// SQLException;

	List<ReferenceCodes> cgfkOffTxn1PayeePersonIdRecordGroup() ;

	Corporates CgfkchkOffTxn1OffTxnCorp(Corporates paramBean) ;

	List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String caseloadId, String caseloadType) ;

	String checkproductionFlag(String caseloadId, String txCode);
	
	Integer getDebitActCode(String txnType, String caseloadId);
	
	GetOffenderSubBalanceBean getOffenderSubBalance(GetOffenderSubBalanceBean param);
	
	BigDecimal getOffenderSependableBalance(Long offenderId, String caseloadId, String txnType,String UserId);

	Map<String, Object> getCreditEligibility(OffenderTransactions param);
	
	String getVProValue();

	ChkFreezeDisbursements chkDisbursementFreeze(ChkFreezeDisbursements chkFreezeDisbursements);
	
	

}
