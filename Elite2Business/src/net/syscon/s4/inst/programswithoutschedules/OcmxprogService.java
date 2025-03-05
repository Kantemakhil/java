package net.syscon.s4.inst.programswithoutschedules;

import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Interface OcmxprogService
 */
public interface OcmxprogService {
	List<ProgramServices> rgProgramTypeRecordGroup();

	List<IntLocUsageLocations> rgIntLocRecordGroup(String agyLocId);

	List<CourseActivities> crsActCommit(CourseActivitiesCommitBean commitBean);

	List<VAddresses> GetAddressId(VAddresses paramBean);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities);

	List<VProgramProviders> rgProviderRecordGroup(String caseLoadId, String caseLoadType, String providerType, String user);

	List<VProgramProviders> rgProviderRecordGroupTeam(String caseLoadId, String caseLoadType, String providerType, String user);

	List<ReferenceCodes> rgPsProvTypeRecordGroup();

}
