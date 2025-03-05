package net.syscon.s4.cf.statements;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.cf.statements.beans.ocsreceiReportsBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.Printers;
/**
 * Interface OcsreceiRepository
 */
public interface OcsreceiRepository {
	Integer omsReqInsertOmsRequests(List<OmsRequests> lstOmsRequests) ;

	String cgfkchkOmsReqOmsReqOms(OmsModules paramBean);

	String cgfkchkOmsReqOmsReqPrint(Printers paramBean);

	List<OmsRequests> omsReqExecuteQuery(OmsRequests objOmsRequests) ;

	List<OffenderTransactions> offTxnExecuteQuery(OmsRequests objOffenderTransactions) ;

	Long omsReqPreInsert();

	String runReportForClient();

	Long runReportroleCur();

	List<OmsModules> cgfkOmsReqModuleNameRecordGroup() ;

	Integer omsReqUpdateOmsRequests(List<OmsRequests> lstOmsRequests) ;

	Integer omsReqDeleteOmsRequests(List<OmsRequests> lstOmsRequests) ;

	Integer offTxnUpdateOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions) ;

	List<Printers> cgfkOmsReqPrinterIdRecordGroup() ;

	String runReportGetPrintFormatCode(OmsModules paramBean);
	
	String runReportrolepwd();

	OffenderBookings offTxnPostQueryTwo(Long offenderId);

	Integer printReceiptsTmpDeletequery(BigDecimal sessionId, String createUserId);
	
	Integer printReceitsInsertQuery(List<PrintReceiptsTmp> lstOmsRequests);

	SystemProfiles getFclientValue();

	String getfcaseloadDesc(String caseloadId);

	Integer updateOffenderTransactionsrpt(String modulename, Long sessionId ,String modifyUserId);

	Integer printReceiptsTmpDeletequeryRpt(String modulename, Long sessionId, String modifyUserId);

	List<ocsreceiReportsBean> getReportData(OmsRequests bean);

	String numberToWord(Double amount);

	String getOffenderIdData(String caseLoadId, Long offenderId,String userId);

	PrintReceiptsTmp getPrintReceiptsBySessionid(BigDecimal sessionId);

}
