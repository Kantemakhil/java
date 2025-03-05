package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.OffenderAlertsCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;

/**
 * Interface OcdalertService
 */

public interface OcdalertService {

	String alertDeleteOffenderAlerts(List<OffenderAlerts> lstOffenderAlerts);

	OffenderBookings getBookingBeginDate(String const0);

	Integer alertPreInsertc(String offenderBookId);

	String alertUpdateOffenderAlerts(List<OffenderAlerts> lstOffenderAlerts);

	Integer alertPostQueryvarificationCurr(ModulePrivileges params);

	List<ReferenceCodes> cgfklkpAlertAlertRefAlertc(String code, String activeFlag, String description,
			String parentCode);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);

	List<OffenderAlerts> cguvchkOffAlertUkc(OffenderAlerts paramBean);

	String alertInsertOffenderAlerts(List<OffenderAlerts> lstOffenderAlerts);

	List<OffenderAlerts> searchOffenderAlerts(OffenderAlerts objOffenderAlerts);

	List<OffenderAlerts> alertSearchOffenderAlerts(String offenderBookId);

	List<ReferenceCodes> findDescriptionbyCode();

	String insertUpdateDeleteOffenderAlerts(OffenderAlertsCommitBean commitBean);

	Integer countOffenderBookings(String offenderId);

	List<Dual> cgwhenNewFormInstancec(SysDual sysDual);

	List<ReferenceCodes> cgfkchkAlertAlertRefAlc(String code, String domain);

	List<ReferenceCodes> findDescriptionbyDomain(ReferenceCodes referenceCodes);

	List<ReferenceCodes> findDescriptionbyDescriptionCodeAndParentCode(ReferenceCodes referenceCodes);

	List<ReferenceCodes> cgfkchkAlertAlertRefAlertc(String alertCode, String mode);

	List<ReferenceCodes> cgfklkpAlertAlertRefAlc(ReferenceCodes referenceCodes);

	List<String> findAlertStatusList();

	Integer workFlCommit(WorkFlowLogsCommitBean commitBean);

	List<ReferenceCodes> cgfklkpAlertAlertRefCodes();

    List<ReferenceCodes> cgfklkpAlertAlertCode(String alertType);

	String alertDeleteChecking();

	String alertCodeChecking();
}
