package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VTrustHeader;

public interface OcdadjusService {

	List<OffFeeBillTransactions> offFeeExecuteQuery( BigDecimal offenderBookId);

	List<TransactionTypes> rgAdjustType();

	Integer offTxnCommit(OffFeeBillTransactionsCommitBean commitBean);

	String getbillEndDayPfVal();

	Integer getCasePlanId(VTrustHeader bean);

	List<OffFeeBillTransactions> postExecuteQuery(List<OffFeeBillTransactions> returnList);

}
