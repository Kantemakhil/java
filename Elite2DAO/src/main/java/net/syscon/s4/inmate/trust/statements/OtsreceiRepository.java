package net.syscon.s4.inmate.trust.statements;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.Printers;

/**
 * Interface OtsreceiRepository
 */
public interface OtsreceiRepository {
	List<CaseloadAgencyLocations> cgfkCsldDpAgyLocRecordGroup(String caseloadId);

	List<Printers> cgfkOmsReqPrinterIdRecordGroup();

	List<StaffMembers> cgfkRecptsCreatedUsersRecordGroup();

	Integer omsReqDeleteOmsRequests(List<OmsRequests> lstOmsRequests);

	List<OmsRequests> omsReqExecuteQuery(OmsRequests objOmsRequests);

	OmsModules cgfkchkOmsReqOmsReqModul(OmsModules paramBean);

	Object omsReqPreInsert(SysDual paramBean);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<OmsModules> cgfkOmsReqModuleNameRecordGroup();

	SystemProfiles runReport(SystemProfiles paramBean);

	Integer omsReqUpdateOmsRequests(List<OmsRequests> lstOmsRequests);

	OmsModules otsreceiKeyCommit(OmsModules paramBean);

	Printers cgfkchkOmsReqOmsReqPrint(Printers paramBean);

	Integer omsReqInsertOmsRequests(List<OmsRequests> lstOmsRequests);

	List<String> checkValidReceipts(CaseloadDeductionProfiles paramBean);

	BigDecimal getDefaultCopies();

	BigDecimal receiptNumExist(CaseloadDeductionProfiles param);

	List<OtrreceiReportBean> generateOtrreceireport(CaseloadDeductionProfiles paramBean);

	List<OtrdreceReportBean> generateOtrdrecereport(CaseloadDeductionProfiles paramBean);

	String numberToWord(BigDecimal amount);

	String getCurrencySymbol();

}
