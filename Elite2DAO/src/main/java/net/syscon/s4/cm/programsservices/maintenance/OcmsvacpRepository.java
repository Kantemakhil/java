package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Interface OcmsvacpRepository
 */
public interface OcmsvacpRepository {
	List<ProgramServices> rgAccProgramRecordGroup();

	List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId);

	List<StaffMembers> rgIntLocationRecordGroup(String providerPartyCode);

	List<VAddresses> rgAllAgyAddressRecordGroup(Long providerPartyId);

	VCoursePhaseOfferings vCrsPhsUpdateVCoursePhaseOfferings(List<VCoursePhaseOfferings> lstVCoursePhaseOfferings);

	List<VAddresses> rgAddressRecordGroup(Long providerPartyId);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities);

	Integer crsActDeleteCourseActivities(List<CourseActivities> lstCourseActivities);

	List<VProgramProviders> rgProviderRecordGroup(String providerType, String caseLoadType, String caseLoadId,String userId);

	Integer crsActUpdateCourseActivities(List<CourseActivities> lstCourseActivities);

	List<VCoursePhaseOfferings> vCrsPhsExecuteQuery(CourseActivities objVCoursePhaseOfferings);

	List<TeamMembers> rgTeamAgyLocsRecordGroup(String user, String caseloadId);

	List<ReferenceCodes> rgRefCodeRecordGroup();

	List<TeamMembers> rgCorpLocsRecordGroup();

	List<VAddresses> rgAgyAddressRecordGroup(String providerPartyCode);

	Integer crsActInsertCourseActivities(List<CourseActivities> lstCourseActivities);

	List<VCoursePhaseOfferings> addressExecuteQuery(VCoursePhaseOfferings searchBean);

	List<VAddresses> addressExecuteQueryDialog(VAddresses searchBean);

	Long getCrsActyId();

//	boolean preInsertCourseActivities(CourseActivities courseActivities);

//	VAddresses getDefaultAddress(CourseActivities courseActivities);

	VAddresses getPlacementDetails(VCoursePhaseOfferings courseActivities);

	Boolean isCoursePhaseExists(CourseActivities bean);
	
	void deleteCourseActivityAreas(Long crsActyId);
	
	void deleteCourseActivityParties(Long crsActyId);
	
	void deleteCourseActivityProf(Long crsActyId);

	String errorNameValidation(String error);
	List<VAddresses> rgAllAddressDeatails(String caseLoadId);

}
