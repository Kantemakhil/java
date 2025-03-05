package net.syscon.s4.inst.booking.maintainence;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.inst.booking.beans.CaseloadAdmAlertProfilesCommitBean;
import net.syscon.s4.inst.booking.beans.CaseloadAdmOtherProfilesCommitBean;

/**
 * Interface OimadmisService
 */
public interface OimadmisService {
	List<SystemMessages> rgSystemMsgRecordGroup();

	List<AgencyLocations> rgAgencyLocationsRecordGroup(String caseloadId);

	List<SystemMessages> caseloadAdmOtherProfilesPostQuery(SystemMessages paramBean);

	List<SystemMessages> caseloadAdmAlertProfilesPostQuery(SystemMessages paramBean);

	String caseloadAdmAlertProfilesCommit(CaseloadAdmAlertProfilesCommitBean commitBean);

	List<LivingUnits> rgLivingUnitsRecordGroup();

	List<CaseloadAdmAlertProfiles> caseloadAdmAlertProfilesExecuteQuery(
			CaseloadAdmAlertProfiles objCaseloadAdmAlertProfiles);

	List<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesExecuteQuery(
			CaseloadAdmOtherProfiles objCaseloadAdmOtherProfiles);

	List<ReferenceCodes> rgAlertCodeRecordGroup(String alerType);

	List<SystemMessages> rgOtherSystemMsgRecordGroup();

	String caseloadAdmOtherProfilesCommit(CaseloadAdmOtherProfilesCommitBean commitBean);

	List<ReferenceCodes> rgAlertRecordGroup();

}
