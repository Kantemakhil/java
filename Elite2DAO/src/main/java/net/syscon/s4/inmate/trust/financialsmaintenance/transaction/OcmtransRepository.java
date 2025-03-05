package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.TransactionPayees;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcmtransRepository
 */
public interface OcmtransRepository {
	String cgfkchkTxnPayeeTxnPayee(BigDecimal corporateId);

	Integer txnPayeeDeleteTransactionPayees(List<TransactionPayees> lstTransactionPayees);

	String cgfkchkTxnPayeeTxnPayee(Long personId);

	List<CaseloadTransactionTypes> csldTtExecuteQuery(CaseloadTransactionTypes objCaseloadTransactionTypes);

	Integer txnTypeUpdateTransactionTypes(List<TransactionTypes> lstTransactionTypes);

	List<DeductionTypes> cgfkTxnTypeCreditObligatioRecordGroup();

	Integer csldTtInsertCaseloadTransactionTypes(List<CaseloadTransactionTypes> lstCaseloadTransactionTypes);

	List<TransactionTypes> txnTypeExecuteQuery(TransactionTypes objTransactionTypes);

	List<TransactionPayees> txnPayeeExecuteQuery(TransactionPayees objTransactionPayees);

	TransactionPayees txnTypeOnCheckDeleteMaster(TransactionPayees paramBean);

	CaseloadTransactionTypes txnTypeOnCheckDeleteMaster(CaseloadTransactionTypes paramBean);

	Integer txnPayeeUpdateTransactionPayees(List<TransactionPayees> lstTransactionPayees);

	List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup();

	Caseloads cgfkchkCsldTtCsldTtCsld(Caseloads paramBean);

	ReferenceCodes cgfkchkTxnTypeTxnTypeRef(ReferenceCodes paramBean);

	Integer txnTypeDeleteTransactionTypes(List<TransactionTypes> lstTransactionTypes);

	List<ReferenceCodes> cgfkTxnTypeTxnUsageRecordGroup();

	Integer txnTypeInsertTransactionTypes(List<TransactionTypes> lstTransactionTypes);

	Integer csldTtDeleteCaseloadTransactionTypes(List<CaseloadTransactionTypes> lstCaseloadTransactionTypes);

	List<Persons> cgfkTxnPayeePayeePersonIdRecordGroup();

	List<Corporates> cgfkTxnPayeePayeeCorporateRecordGroup();

	Integer txnPayeeInsertTransactionPayees(List<TransactionPayees> lstTransactionPayees);

	DeductionTypes cgfkchkTxnTypeTxnTypeDed(DeductionTypes paramBean);

	Long payeeSeqInsert(String txnType);

	Integer cgrichkTransactionOperations(String txnType);

	Integer cgrichkOffenderPostDatedTxns(String txnType);

	Integer cgrichkInterestTransactionTypes(String txnType);

	Integer cgrichkGlTransactions(String txnType);

	Integer cgrichkOffenderInterests(String txnType);

	Integer cgrichkDeductionLimits(String txnType);

	String txnTypeValidation(String txnType, String userName);

}
