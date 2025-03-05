package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.TransactionTypesCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.TransactionPayees;
import net.syscon.s4.im.beans.TransactionPayeesCommitBean;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcmtransService
 */
public interface OcmtransService {
	Caseloads cgfkchkCsldTtCsldTtCsld(Caseloads paramBean);

	List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup();

	List<CaseloadTransactionTypes> csldTtExecuteQuery(CaseloadTransactionTypes objCaseloadTransactionTypes);

	Integer txnPayeeCommit(TransactionPayeesCommitBean commitBean);

	List<DeductionTypes> cgfkTxnTypeCreditObligatioRecordGroup();

	List<ReferenceCodes> cgfkTxnTypeTxnUsageRecordGroup();

	Integer txnTypeCommit(TransactionTypesCommitBean commitBean);

	TransactionPayees txnTypeOnCheckDeleteMaster(TransactionPayees paramBean);

	List<TransactionTypes> txnTypeExecuteQuery(TransactionTypes objTransactionTypes);

	String cgfkchkTxnPayeeTxnPayee(BigDecimal corporateId);

	List<TransactionPayees> txnPayeeExecuteQuery(TransactionPayees objTransactionPayees);

	ReferenceCodes cgfkchkTxnTypeTxnTypeRef(ReferenceCodes paramBean);

	String cgfkchkTxnPayeeTxnPayee(Long personId);

	List<Persons> cgfkTxnPayeePayeePersonIdRecordGroup();

	List<Corporates> cgfkTxnPayeePayeeCorporateRecordGroup();

	// Integer csldTtCommit(CaseloadTransactionTypescommitBean CommitBean);

	Integer txnTypeOnCheckDeleteMaster(String txnType);

	String txnTypeValidation(String txnType, String userName);

}
