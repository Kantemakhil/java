package net.syscon.s4.inst.classification;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;
import net.syscon.s4.inst.classification.beans.VOffassAss;

/**
 * Interface OcdnoqueRepository
 */
public interface OcdnoqueRepository {
	List<ReferenceCodes> rgAssessCommitteCodeRecordGroup(final String userId);

	StaffMembers offAss1PreInsertGetStaffId(final String userId);

	List<Assessments> rgAssessmentTypeIdRecordGroup(BigDecimal programid ,String userId);

	List<VOffassAss> offAssExecuteQuery(VOffassAss objVOffassAss);

	Assessments postQueryForOffAss(VOffassAss searchRecord);

	Integer offAssPostQuery(OffenderAssessments paramBean);

	Assessments cgfkchkAssessmentTypeId1(Assessments paramBean);

	List<AgencyLocations> rgPlaceAgyLocIdRecordGroup(final String userId);

	SystemProfiles getCnoteAllupdProfile(SystemProfiles paramBean);

	Assessments cgfkchkAssessmentTypeId(Assessments paramBean);

	List<OffenderAssessments> offAss1ExecuteQuery(OffenderAssessments object);

	Integer offAss1InsertOffenderAssessments(List<OffenderAssessments> object);

	StaffAccessibleCaseloads setCasenoteAccess(StaffAccessibleCaseloads paramBean);

	List<AgencyLocations> rgAgencyLocationsRecordGroup(final String userId);

	CaseloadAgencyLocations setCasenoteAccess(CaseloadAgencyLocations paramBean);

	Integer offAss1PreInsert(OffenderAssessments paramBean);

	List<StaffMembersV1> getStaffId(StaffMembersV1 paramBean);

	AgencyLocations postQueryForOffAss1(AgencyLocations paramBean);

	StaffMembers cgfkchkNbtStaffId(StaffMembers paramBean);

	CaseloadAgencyLocations securityCheckNew(CaseloadAgencyLocations paramBean);

	List<VOffassAss> updatePrevass(VOffassAss paramBean);

	StaffAccessibleCaseloads securityCheckNew(StaffAccessibleCaseloads paramBean);

	List<ReferenceCodes> rgOverrideReasonRecordGroup();

	Integer offAss1UpdateOffenderAssessments(List<OffenderAssessments> object);

	AgencyLocations cgfkchkAgyLocId(AgencyLocations paramBean);

	List<StaffMembers> rgStaffMembersRecordGroup();

	List<AssessmentResults> rgOverridedSupLevelTypeRecordGroup(Integer assessmentId);

	List<VOffassAss> offBkgOnCheckDeleteMaster(VOffassAss paramBean);

	Assessments offAss1PostQuery(OffenderAssessments paramBean);

	StaffMembers postQueryForOffAss1(StaffMembers paramBean);

	String postQueryForOffAss1(final OffenderAssessments paramBean);

	String getMaxAssessmentDateCur(OffenderAssessments searchBean);

	List<Assessments> rgAssessmentTypeIdRecordGroupWithoutProgramid(String userId);
	
	List<AssessmentSupervisions> scoreRange();
	
	String getWorkingCaseLoadType(String userId);
	
	List<ReferenceCodes> assessmentDetailsAuthority(final String parentCode);
	
	List<Assessments> rgAssessmentTypeEVALRecordGroup();
	
		
}
