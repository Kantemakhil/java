package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;

public interface OcdreverService {

	Integer adjustRevCommit(OffFeeBillTransactionsCommitBean commitBean) throws Exception;

	List<OffFeeBillTransactions> offFeeTxnExecuteQuery(BigDecimal offenderBookId);

	OffFeeBillTransactions unReversedPaymentNotExistsFlow(OffFeeBillTransactions bean) throws Exception;
	
	OffFeeBillTransactions unReversedPaymentExistsFlow(OffFeeBillTransactions bean,OffFeeBillTransactions offTxnUnRev)throws Exception;
	
	
}
