package net.syscon.s4.inst.classification;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.inst.classification.beans.VOffassAssCommitBean;

/**
 * Interface OcdnoqueService
 */
public interface OcdnoqueService {
	CaseloadAgencyLocations securityCheckNew(CaseloadAgencyLocations paramBean);

	Integer offAss1Commit(OffenderAssessmentsCommitBean commitBean);

	Assessments postQueryForOffAss(Assessments paramBean);

	List<ReferenceCodes> rgAssessCommitteCodeRecordGroup(String userId);

	Assessments cgfkchkAssessmentTypeId1(Assessments paramBean);

	Integer offAssPostQuery(OffenderAssessments paramBean);

	List<Assessments> rgAssessmentTypeIdRecordGroup(BigDecimal programid, String userId);

	SystemProfiles getCnoteAllupdProfile(SystemProfiles paramBean);

	List<VOffassAss> offAssExecuteQuery(VOffassAss objVOffassAss);

	List<CaseloadAgencyLocations> setCasenoteAccess(CaseloadAgencyLocations paramBean);

	AgencyLocations postQueryForOffAss1(AgencyLocations paramBean);

	List<AgencyLocations> rgPlaceAgyLocIdRecordGroup(final String userId);

	StaffMembers cgfkchkNbtStaffId(StaffMembers paramBean);

	List<OffenderAssessments> offAss1ExecuteQuery(OffenderAssessments object);

	List<StaffMembersV1> getStaffId(StaffMembersV1 paramBean);

	AgencyLocations cgfkchkAgyLocId(AgencyLocations paramBean);

	List<AgencyLocations> rgAgencyLocationsRecordGroup(final String userId);

	Integer offAss1PreInsert(OffenderAssessments paramBean);

	Integer offAssCommit(VOffassAssCommitBean commitBean);

	Assessments offAss1PostQuery(OffenderAssessments paramBean);

	List<ReferenceCodes> rgOverrideReasonRecordGroup();

	VOffassAss updatePrevass(VOffassAss paramBean);

	List<StaffMembers> rgStaffMembersRecordGroup();

	List<VOffassAss> offBkgOnCheckDeleteMaster(VOffassAss paramBean);

	List<AssessmentResults> rgOverridedSupLevelTypeRecordGroup(Integer assessmentId);

	String getMaxAssessmentDateCur(OffenderAssessments searchBean);

	List<Assessments> rgAssessmentTypeIdRecordGroupWithoutProgramid(String userId);

	List<AssessmentSupervisions> scoreRange();
	
	List<ReferenceCodes> assessmentDetailsAuthority(String parentCode);
	
	List<Assessments> rgAssessmentTypeEVALRecordGroup();

}
