package net.syscon.s4.inmate.trust.statements;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OmsRequestsCommitBean;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.Printers;

/**
 * Interface OtsreceiService
 */
public interface OtsreceiService {
	Printers cgfkchkOmsReqOmsReqPrint(Printers paramBean);

	List<Object> cgwhenNewFormInstance();

	SystemProfiles runReport(SystemProfiles paramBean);

	List<CaseloadAgencyLocations> cgfkCsldDpAgyLocRecordGroup(String caseloadId);

	List<OmsModules> cgfkchkOmsReqOmsReqModul(OmsModules paramBean);

	List<Printers> cgfkOmsReqPrinterIdRecordGroup();

	List<StaffMembers> cgfkRecptsCreatedUsersRecordGroup();

	List<OmsModules> cgfkOmsReqModuleNameRecordGroup();

	Object omsReqPreInsert();

	List<OmsModules> otsreceiKeyCommit(OmsModules paramBean);

	Integer omsReqCommit(OmsRequestsCommitBean commitBean);

	List<OmsRequests> omsReqExecuteQuery(OmsRequests objOmsRequests);

	List<String> checkValidReceipts(CaseloadDeductionProfiles paramBean);

	BigDecimal getDefaultCopies();

	BigDecimal receiptNumExist(CaseloadDeductionProfiles param);

	ReportBean generateOtrdrecereport(CaseloadDeductionProfiles paramBean);

	ReportBean generateOtrreceireport(CaseloadDeductionProfiles paramBean);

	ReportBean getReport(CaseloadDeductionProfiles paramBean);

}
