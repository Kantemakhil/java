package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtdcntacRepository
 */
public interface OtdcntacRepository {
	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> object);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	OffenderTransactions initPostTranTypesTxnType( String user);

	OffenderTransactions initPostTranTypesAccountType(String txnType,  String user);

	Integer txnIdNextValData();

	Integer txnEntrySeqNextValData(Integer txnId);

	Integer offTxnInsertOffenderTrustAccounts(OffenderTransactions lstOffenderTransactions);

	Integer offTxnInsertOffenderSubAccounts(OffenderSubAccounts lstOffenderTransactions);

	String checkAccountSatus(OffenderTransactions offTrans);

	OffenderTransactions drAccountCodeCrAccountCode( String user);

	String getGroupPrivilege();

	String transactionOperationsFlag(OffenderTransactions offenderTransactions);

	Integer genTrustRcptNmbr(String string);

	Integer processGlTransNew(OffenderTransactions offTrans);

	BigDecimal accountCodeTemp(String subActType, String user);

	String deductionTypesProcedure(OffenderTransactions offTrans);

	List<OffenderDeductions> deductionTypeTemp(Long offenderId);

	String caseloadCodeTemp(String deductionType);

	Integer groupIdTemp(String deductionType);

	Integer offDedInsertOffenderDeductions(OffenderTransactions offenderTransactions);

	void updateOffenderBalance(OffenderTransactions offTxnsPostQuery);

	String getAcAndSetIndDate(OffenderTransactions offTxnsPostQuery);

	String obligationGroups();

	OffenderDeductions gettingOldRecordOffendderDeduction(Long offenderId, String deductionType);

}
