package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CasePlansCommitBean;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV1CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffApV2;
import net.syscon.s4.inst.casemanagement.beans.OffApV2CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditionsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeedsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.PlanDetails;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.VSummaryCasePlans;
import net.syscon.s4.inst.casemanagement.beans.VSummaryCasePlansCommitBean;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;

/**
 * Interface OcdiplanService
 */
public interface OcdiplanService {
	List<OffApV1> offCriNeedsOnCheckDeleteMaster(OffApV1 paramBean);

	Integer offActionPlansV2Commit(OffApV2CommitBean CommitBean);

	Integer offCaseCondsCommit(OffenderCaseConditionsCommitBean CommitBean);

	List<ReferenceCodes> rgCrimNeedsStsRecordGroup();

	WorkFlows Verification(WorkFlows paramBean);

	List<OffApV2> offCaseCondsOnCheckDeleteMaster(OffApV2 paramBean);

	OffenderCriminogenicNeeds casPlnOnCheckDeleteMaster(OffenderCriminogenicNeeds paramBean);

	List<CasePlans> offBkgOnCheckDeleteMaster(CasePlans paramBean);

	Integer offActionPlansV1Commit(OffApV1CommitBean CommitBean);

	List<OffApV1> offActionPlansV1ExecuteQuery(OffApV1 objOffApV1);

	List<CommunityConditions> offCaseCondsPostQuery(CommunityConditions paramBean);

	List<OffenderCriminogenicNeeds> offCriNeedsExecuteQuery(OffenderCriminogenicNeeds objOffenderCriminogenicNeeds);

	List<CaseReviewPeriods> SetCasPlnDefaults(CaseReviewPeriods paramBean);

	List<AgencyLocations> CgfklkpCasPlnCasPlnAlFk(AgencyLocations paramBean);

	List<ReferenceCodes> offCriNeedsPostQuery(ReferenceCodes paramBean);

	CaseReviewPeriods NextReviewDate(CaseReviewPeriods paramBean);

	Integer vSummaryCasePlanCommit(VSummaryCasePlansCommitBean CommitBean);

	List<OffApV2> offActionPlansV2ExecuteQuery(OffApV2 objOffApV2);

	List<VSummaryCasePlans> vSummaryCasePlanExecuteQuery(VSummaryCasePlans objVSummaryCasePlan);

	List<CasePlans> casPlnExecuteQuery(CasePlans objCasePlans);

	List<OffenderCaseConditions> offCaseCondsExecuteQuery(OffenderCaseConditions objOffenderCaseConditions);

	PlanDetails GetSequenceNo(PlanDetails paramBean);

	SystemProfiles GetCnoteAllupdProfile(SystemProfiles paramBean);

	CasePlans InsertCaseplanRecord(CasePlans paramBean);

	List<ReferenceCodes> rgCaseworkRecordGroup();

	List<ReferenceCodes> cgfkCasPlnDspDescriptionRecordGroup();

	Integer casPlnCommit(CasePlansCommitBean CommitBean);

	Integer offCriNeedsCommit(OffenderCriminogenicNeedsCommitBean CommitBean);

	List<ReferenceCodes> CgfklkpCasPlnCasPlnStatu(ReferenceCodes paramBean);

	List<ReferenceCodes> rgPrgCategoryRecordGroup();

	List<ReferenceCodes> rgCaseplanAssRecordGroup();

	List<Dual> rgCaseInfoRecordGroup();

	List<ProgramServices> rgProgramId2RecordGroup();

	List<ProgramServices> rgProgramIdRecordGroup(String programCategory);

	List<AgencyLocations> cgfkCasPlnDspDescription4RecordGroup();

	StaffMembersV1 GetStaffId(StaffMembersV1 paramBean);

	Integer insertUpdateFlagQuery(CasePlans searchBean);

	List<WorkFlowLogs> workFlExecuteQuery(CasePlans searchBean);
	
	String getStaffName(String userId);
	
	List<String> getUserIdOfAssignedStaff(CasePlans searchBean);
	
	List<String> getUserIdOfAssignedStaffForCpOwn(CasePlans searchBean);
	
}
