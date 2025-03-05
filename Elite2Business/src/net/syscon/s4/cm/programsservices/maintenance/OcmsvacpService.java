package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferingsCommitBean;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

/**
 * Interface OcmsvacpService
 */
public interface OcmsvacpService {
	List<ProgramServices> rgAccProgramRecordGroup();

	VCoursePhaseOfferings vCrsPhsCommit(VCoursePhaseOfferingsCommitBean commitBean);

	List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId);

	List<StaffMembers> rgIntLocationRecordGroup(String providerPartyCode);

	List<VAddresses> rgAllAgyAddressRecordGroup(Long providerPartyId);

	List<VAddresses> rgAddressRecordGroup(Long providerPartyId);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities);

	List<VProgramProviders> rgProviderRecordGroup(String providerType, String caseLoadType, String caseLoadId,String userId);

	List<VCoursePhaseOfferings> vCrsPhsExecuteQuery(CourseActivities objVCoursePhaseOfferings);

	Integer crsActCommit(CourseActivitiesCommitBean commitBean);

	List<TeamMembers> rgTeamAgyLocsRecordGroup(String user, String caseLoadId);

	List<ReferenceCodes> rgRefCodeRecordGroup();

	List<TeamMembers> rgCorpLocsRecordGroup();

	List<VAddresses> rgAgyAddressRecordGroup(String providerPartyCode);

	List<VCoursePhaseOfferings> addressExecuteQuery(VCoursePhaseOfferings searchBean);

	List<VAddresses> addressExecuteQueryDialog(CourseActivities searchBean);

	String errorNameValidation(String error);


}
