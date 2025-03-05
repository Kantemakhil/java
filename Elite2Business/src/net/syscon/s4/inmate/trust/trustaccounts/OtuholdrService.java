package net.syscon.s4.inmate.trust.trustaccounts;
import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;
/**
 * Interface OtuholdrService 
 * */
public interface OtuholdrService  {
	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions) ;

	List<Object> CgwhenNewFormInstance()  ;

	Integer offTxnCommit(OffenderTransactionsCommitBean commitBean) ;
	
	String getVHoldClearFlag(OffenderTransactions paramBean);

	String onInsert(OffenderTransactions paramBean);

}
