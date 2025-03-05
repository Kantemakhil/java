package net.syscon.s4.inst.programswithoutschedules;

import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Interface OcmxprogRepository
 */
public interface OcmxprogRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<ProgramServices> rgProgramTypeRecordGroup();

	List<IntLocUsageLocations> rgIntLocRecordGroup(String agyLocId);

	List<VAddresses> getAddressId(VAddresses paramBean);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities);

	List<VProgramProviders> rgProviderRecordGroup(String caseLoadId, String caseLoadType, String providerType, String user);

	List<VProgramProviders> rgProviderRecordGroupTeam(String caseLoadId, String caseLoadType, String providerType, String user);

	Integer crsActUpdateCourseActivities(List<CourseActivities> lstCourseActivities);

	Integer crsActInsertCourseActivities(List<CourseActivities> lstCourseActivities);

	List<ReferenceCodes> rgPsProvTypeRecordGroup();

	Integer checkCodeExistOcmsvacp(CourseActivities courseActivity);

	Long getCrsActyId();

	Long getAgyAddressID(String providerPartyCode);

	Long getTeamAddressId(Long providerId, String user);

	Long getCorpAddressId(String providerPartyCode);

}
