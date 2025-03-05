package net.syscon.s4.inmate.trust.financialreports;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OcrorrecReportsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OtrbnrcnBean;

/**
 * Interface OsureporService
 */
public interface OsureporService {
	List<Dual> rg1cRecordGroup();

	List<Dual> rg2cRecordGroup();

	List<Dual> rg3cRecordGroup();

	List<Dual> rg4cRecordGroup();

	List<Dual> rg5cRecordGroup();

	List<Dual> rg5c1RecordGroup();

	List<OmsModuleParameters> populateRecords(OmsModuleParameters paramBean);

	List<ReferenceCodes> getDynamicLov(String qry, String caseload, BigDecimal accCode);

	List<AccountCodes> getReport(String caseloadId,String datetoLong, String flag, String userName);

	List<OtrbnrcnBean> getBankReconciliationReport(Long accountCode, String userDate, String caseloadId ,String userName );
	
	List<OcrorrecReportsBean> printReportSupv(OffenderTransactions returnReport);

	ReportBean getReportOtrbstat(List<OffenderTransactions> paramBean);

	List<ReferenceCodes> getLovOtrbstat(String parameterName,String caseloadId);

}
