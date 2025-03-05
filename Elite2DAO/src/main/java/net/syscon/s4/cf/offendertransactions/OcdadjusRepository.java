package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.VTrustHeader;

public interface OcdadjusRepository {

	List<OffFeeBillTransactions> offFeeExecuteQuery(BigDecimal offenderBookId);

	List<TransactionTypes> rgAdjustType();

	Integer offTxnCommit(List<OffFeeBillTransactions> insertList );

	Integer getBillTranId(String billId);

	String getbillEndDayPfVal();

	Integer getCasePlanId(VTrustHeader bean);

	Integer offStmtCommit(List<offBillingStatements> insertList);

	Integer offFeeBillsUpdate(List<offBillingStatements> updateBean);

	Integer offFeeBillsTxnUpdate(List<offBillingStatements> updateBean);

	Integer stmtPreInsert();
	
	Integer getOffAdjustTxnId();

	BigDecimal getPostQuery(String billId);

}
