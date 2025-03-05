package net.syscon.s4.inst.booking.maintainence;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SystemMessages;

/**
 * Interface OimadmisRepository
 */
public interface OimadmisRepository {
	List<SystemMessages> rgSystemMsgRecordGroup();

	Integer caseloadAdmOtherProfilesUpdateCaseloadAdmOtherProfiles(
			List<CaseloadAdmOtherProfiles> lstCaseloadAdmOtherProfiles);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer caseloadAdmOtherProfilesDeleteCaseloadAdmOtherProfiles(
			List<CaseloadAdmOtherProfiles> lstCaseloadAdmOtherProfiles);

	Integer caseloadAdmOtherProfilesInsertCaseloadAdmOtherProfiles(
			List<CaseloadAdmOtherProfiles> lstCaseloadAdmOtherProfiles);

	List<LivingUnits> rgLivingUnitsRecordGroup();

	List<CaseloadAdmAlertProfiles> caseloadAdmAlertProfilesExecuteQuery(
			CaseloadAdmAlertProfiles objCaseloadAdmAlertProfiles);

	List<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesExecuteQuery(
			CaseloadAdmOtherProfiles objCaseloadAdmOtherProfiles);

	List<SystemMessages> caseloadAdmAlertProfilesPostQueryPostQuery(SystemMessages paramBean);

	List<ReferenceCodes> rgAlertCodeRecordGroup(String alerType);

	Integer caseloadAdmAlertProfilesDeleteCaseloadAdmAlertProfiles(
			List<CaseloadAdmAlertProfiles> lstCaseloadAdmAlertProfiles);

	List<AgencyLocations> rgAgencyLocationsRecordGroup(String caseloadId);

	Integer caseloadAdmAlertProfilesUpdateCaseloadAdmAlertProfiles(
			List<CaseloadAdmAlertProfiles> lstCaseloadAdmAlertProfiles);

	List<SystemMessages> caseloadAdmOtherProfilesPostQuery(SystemMessages paramBean);

	Integer caseloadAdmAlertProfilesInsertCaseloadAdmAlertProfiles(
			List<CaseloadAdmAlertProfiles> lstCaseloadAdmAlertProfiles);

	List<SystemMessages> rgOtherSystemMsgRecordGroup();

	List<ReferenceCodes> rgAlertRecordGroup();

	String getLivingUnitDescp(CaseloadAdmOtherProfiles vCbSentTerms);
	
	Integer getAvalibleBedInTheLocation(Integer livingUnitId);

}
