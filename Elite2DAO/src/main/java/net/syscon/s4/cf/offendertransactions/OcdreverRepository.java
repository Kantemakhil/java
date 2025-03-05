package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;

public interface OcdreverRepository {
	Integer offTxnCommit(List<OffFeeBillTransactions> insertList);

	Integer getBillTranId(String billId);

	Integer offStmtCommit(List<offBillingStatements> insertList);

	Integer offFeeBillsUpdate(List<offBillingStatements> updateBean);

	Integer offFeeBillsTxnUpdate(List<offBillingStatements> updateBean);

	Integer stmtPreInsert();

	List<OffFeeBillTransactions> offFeeTxnExecuteQuery(BigDecimal offenderBookId);
	
	BigDecimal getOffFeeBillOrginalBalanceOwing(String billId);
	
	List<OffFeeBillTransactions> getAdjustedBills(String billId,Integer billTxnNo);
	
	Integer offFeeBillsUpdateAging(OffFeeBills updateBean);
	
	OffFeeBills getOldDataOffFeeBills(String billId);


}
