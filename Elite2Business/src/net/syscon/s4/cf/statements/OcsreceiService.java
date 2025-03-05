package net.syscon.s4.cf.statements;
import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OmsRequestsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.Printers;
/**
 * Interface OcsreceiService 
 */
public interface OcsreceiService  {
	List<OffenderTransactions> offTxnExecuteQuery(OmsRequests objOffenderTransactions) ;

	List<OffenderTransactions> offTxnCommit(OffenderTransactionsCommitBean commitBean) ;

	String cgfkchkOmsReqOmsReqPrint(Printers paramBean)  ;

	String cgfkchkOmsReqOmsReqOms(OmsModules paramBean)  ;

	List<OmsModules> cgfkOmsReqModuleNameRecordGroup() ;

	Integer omsReqCommit(OmsRequestsCommitBean commitBean) ;

	List<Printers> cgfkOmsReqPrinterIdRecordGroup() ;

	List<OmsRequests> omsReqExecuteQuery(OmsRequests objOmsRequests) ;

}
