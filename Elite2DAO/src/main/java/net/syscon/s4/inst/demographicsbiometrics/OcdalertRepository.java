package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;

/**
 * Interface OcdalertRepository
 */

public interface OcdalertRepository {

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);

	Integer alertInsertOffenderAlerts(List<OffenderAlerts> lstOffenderAlerts);

	Integer alertUpdateOffenderAlerts(List<OffenderAlerts> lstOffenderAlerts);

	OffenderBookings getBookingBeginDate(String const0);

	Integer alertDeleteOffenderAlerts(List<OffenderAlerts> lstOffenderAlerts);

	Integer alertPreInsertc(Long offenderBookId);

	List<OffenderAlerts> cguvchkOffAlertUkc(OffenderAlerts paramBean);

	List<OffenderAlerts> searchOffenderAlerts(OffenderAlerts objOffenderAlerts);

	Integer countOffenderBookings(String offenderId);

	Integer alertPostQueryvarificationCurr(ModulePrivileges modulePrivileges);

    List<ReferenceCodes> getAlertTypeCount();

	List<ReferenceCodes> getAlertCodeCount();

	Long getStaffMemCount();

	List<Dual> cgwhenNewFormInstancec(SysDual sysDual);

	List<OffenderAlerts> alertSearchOffenderAlerts(String offenderBookId);

	List<ReferenceCodes> getCodeAlertDes();

	List<ReferenceCodes> cgfkchkAlertAlertRefAlc(String code, String domain);

	List<ReferenceCodes> findDescriptionbyDomain(ReferenceCodes referenceCodes);

	List<ReferenceCodes> cgfkchkAlertAlertRefAlertc(String alertCode, String mode);

	List<ReferenceCodes> cgfklkpAlertAlertRefAlertc(String code, String activeFlag, String description,
			String parentCode);

	List<ReferenceCodes> findDescriptionbyDescriptionCodeAndParentCode(ReferenceCodes referenceCodes);

	List<ReferenceCodes> cgfklkpAlertAlertRefAlc(ReferenceCodes referenceCodes);

	List<String> findAlertStatusList();

	Integer workFlowIdCount(Long offenderBookId, Long alertSeq);

	Long workFlowLogsPreInsertc();

	Integer workFlowsInsertWorkFlows(List<OffenderAlerts> lstOffenderAlerts);

	Integer workFlowsInsertWorkFlowLogs(List<OffenderAlerts> lstOffenderAlerts);

	Integer workFlowIdMaxVal(Long offenderBookId, Long alertSeq);

	Integer workFlowsDeleteWorkFlows(List<OffenderAlerts> lstOffenderAlerts);

	Integer workFlowsDeleteWorkFlowLogs(List<OffenderAlerts> lstOffenderAlerts);

	Integer workFlCommit(List<WorkFlowLogs> insertList);

	Integer preInsert(Long obj);

	Integer offAlertUpdate(List<OffenderAlerts> updateList);

	List<ReferenceCodes> cgfklkpAlertAlertRefCodes();

	Integer workFlowsInsertWorkFlowLogsUpdate(List<OffenderAlerts> lstOffenderAlerts);

	List<ReferenceCodes> cgfklkpAlertAlertCode(String alertType);

	Integer checkWorkActionCode(Long workFlowId);

	String alertDeleteData();

	String alertCodeData();
	
	List<OffenderAlerts> getAlertDetails(OffenderAlerts obj);
}
