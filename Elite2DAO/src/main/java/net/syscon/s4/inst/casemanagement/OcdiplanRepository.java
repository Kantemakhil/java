package net.syscon.s4.inst.casemanagement;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.casemanagement.beans.AssessmentSummaries;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.inst.casemanagement.beans.CaseworkSteps;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV2;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.PlanDetails;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.inst.casemanagement.beans.VSummaryCasePlans;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

/**
 * Interface OcdiplanRepository
 */
public interface OcdiplanRepository {
	StaffMembersV2 cgfkchkCasPlnCasPlnVsm2(CasePlans paramBean);

	List<ReferenceCodes> rgCrimNeedsStsRecordGroup();

	Integer offActionPlansV1InsertOffApV1(List<OffApV1> lstOffApV1);

	List<OffApV2> offCaseCondsOnCheckDeleteMaster(OffApV2 paramBean);

	ReferenceCodes cgfkchkCasPlnCasPlnSuper(ReferenceCodes paramBean);

	List<VSummaryCasePlans> offBkgOnCheckDeleteMaster(VSummaryCasePlans paramBean);

	SystemProfiles defaultStaffValidation(SystemProfiles paramBean);

	Integer offActionPlansV2DeleteOffApV2(List<OffApV2> lstOffApV2);

	List<OffenderCaseConditions> casPlnOnCheckDeleteMaster(OffenderCaseConditions paramBean);

	SystemProfiles getCnoteAllupdProfile(SystemProfiles paramBean);

	Integer offActionPlansV2InsertOffApV2(List<OffApV2> lstOffApV2);

	List<OffApV1> offActionPlansV1ExecuteQuery(OffApV1 objOffApV1);

	AssessmentSummaries getSequenceNo(AssessmentSummaries paramBean);

	SystemProfiles authorizedUser(SystemProfiles paramBean);

	List<OffenderCriminogenicNeeds> offCriNeedsExecuteQuery(OffenderCriminogenicNeeds objOffenderCriminogenicNeeds);

	PlanDetails getSequenceNo(PlanDetails paramBean);

	List<StaffMembers> authorizedUser(StaffMembers paramBean);

	CaseReviewPeriods setCasPlnDefaults(CaseReviewPeriods paramBean);

	Object offActionPlansV1PreInsert(Dual paramBean);

	AgencyLocations setCasPlnDefaults(AgencyLocations paramBean);

	WorkFlowLogs verificationVERIFICATION(WorkFlowLogs paramBean);

	CaseReviewPeriods nextReviewDate(CaseReviewPeriods paramBean);

	ReferenceCodes setCasPlnDefaults(ReferenceCodes paramBean);

	Integer checkReviewDate(String supVisLevel);

	CommunityConditions offCaseCondsPostQueryCode(OffenderCaseConditions paramBean);

	List<OffApV1> offCriNeedsOnCheckDeleteMaster(OffApV1 paramBean);

	List<AgencyLocations> cgfklkpCasPlnCasPlnAlFk(AgencyLocations paramBean);

	List<StaffMembers> defaultStaffValidation(StaffMembers paramBean);

	Integer casePlanUpdate(List<CasePlans> lstCasePlans);

	List<OffApV2> offActionPlansV2ExecuteQuery(OffApV2 objOffApV2);

	List<VSummaryCasePlans> vSummaryCasePlanExecuteQuery(VSummaryCasePlans objVSummaryCasePlan);

	CasePlans offBkgOnCheckDeleteMaster(CasePlans paramBean);

	List<ReferenceCodes> cgfklkpCasPlnCasPlnStatu(ReferenceCodes paramBean);

	Integer offActionPlansV2UpdateOffApV2(List<OffApV2> lstOffApV2);

	List<CasePlans> casPlnExecuteQuery(CasePlans objCasePlans);

	List<OffenderCaseConditions> offCaseCondsExecuteQuery(OffenderCaseConditions objOffenderCaseConditions);

	// List<MMDateVsm2.role> cgfkCasPlnDspStaffNameRecordGroup();

	OmsModules createFormGlobals(OmsModules paramBean);

	ReferenceCodes cgfkchkCasPlnCasPlnStatu(ReferenceCodes paramBean);

	CasePlans insertCaseplanRecordINSERT_CASEPLAN_RECORD(CasePlans paramBean);

	Object offCriNeedsPreInsert(Dual paramBean);

	Integer offCaseCondsUpdateOffenderCaseConditions(List<OffenderCaseConditions> lstOffenderCaseConditions);

	List<ReferenceCodes> rgCaseworkRecordGroup();

	List<ReferenceCodes> cgfkCasPlnDspDescriptionRecordGroup();

	Integer offActionPlansV1UpdateOffApV1(List<OffApV1> lstOffApV1);

	List<OffenderCriminogenicNeeds> fetchLastDatetime(OffenderCriminogenicNeeds paramBean);

	Integer offCriNeedsInsertOffenderCriminogenicNeeds(List<OffenderCriminogenicNeeds> lstOffenderCriminogenicNeeds);

	Integer offActionPlansV1DeleteOffApV1(List<OffApV1> lstOffApV1);

	StaffMembersV2 casPlnPostQuerySacStaffId(CasePlans paramBean);

	Object offActionPlansV2PreInsert(Dual paramBean);

	CaseworkSteps getSequenceNo(CaseworkSteps paramBean);

	Integer offCriNeedsUpdateOffenderCriminogenicNeeds(List<OffenderCriminogenicNeeds> lstOffenderCriminogenicNeeds);

	List<ReferenceCodes> rgPrgCategoryRecordGroup();

	List<WorkFlows> verificationVERIFICATION(WorkFlows paramBean);

	List<ReferenceCodes> rgCaseplanAssRecordGroup();

	List<Dual> rgCaseInfoRecordGroup();

	AgencyLocations cgfkchkCasPlnCasPlnAlFk(AgencyLocations paramBean);

	List<ProgramServices> rgProgramId2RecordGroup();

	List<OffenderCriminogenicNeeds> casPlnOnCheckDeleteMaster(OffenderCriminogenicNeeds paramBean);

	List<StaffMembersV1> getStaffId(StaffMembersV1 paramBean);

	List<ProgramServices> rgProgramIdRecordGroup(String programCategory);

	List<VHeaderBlock> setCasPlnDefaults(VHeaderBlock paramBean);

	AgencyLocations casPlnPostQuery(AgencyLocations paramBean);

	CasePlans getAutherizedUser(CasePlans paramBean);

	List<AgencyLocations> cgfkCasPlnDspDescription4RecordGroup();

	CasePlans authorizedUser(CasePlans paramBean);

	ReferenceCodes offCriNeedsPostQuery(ReferenceCodes paramBean);

	ReferenceCodes offCriNeedsPostQueryStatus(ReferenceCodes paramBean);

	String getnbtConSys(String conditionStatus);

	StaffMembers cgfkchkOffBkgsOffBkgStafc(StaffMembers paramBean);

	OffenderCaseConditions offCaseCondsGetLatestDatePostQuery(BigDecimal casePlanId, BigDecimal offenderBookId);

	OffenderCriminogenicNeeds offCriNeedsGetLatestDatePostQuery(BigDecimal casePlanId, BigDecimal offenderBookId);

	Integer getStaffId(String user);

	Integer getRoleValue();

	Integer getverifyUserRole(Integer profileVal, String user);

	Long getworkFlowId(CasePlans searchBean);

	List<WorkFlowLogs> workFlExecuteQuery(CasePlans workFlowLst);

	Integer casePlanOldRecUpdate(List<CasePlans> updateOldList);

	Integer casePlanInsert(List<CasePlans> updateOldList);

	Long getNxtCasePlanId(CasePlans obj);

	String getSupLevel(Long offenderBookId);

	Integer whenNextbuttonUpdates(OffenderCriminogenicNeeds searchRecord);

	String getDescriptionFromAgencyLocations(AgencyLocations searchRecord);

	String getIntakeAgyLocIdFromvHeaderBlock(VHeaderBlock searchRecord);

	Integer getReviewPeriodFromCaseReviewPeriods(CaseReviewPeriods searchRecord);

	String getDescriptionFromReferenceCodes(ReferenceCodes searchRecord);
	
	public List<OffenderSentConditions> getOffSentConditions(BigDecimal offBookId);
	
	Long getoffCrimNeedIdSeq();
	
	List<CasePlanStaff> getCasePlanStaffDetails(Long offenderBookId ,Long casePlanId );
	
	String getStaffName(String userId);
	
	List<StaffMembers> getUserIdOfAssignedStaff(Long offenderBookId, Long casePlanId);
	
	List<StaffMembers> getUserIdOfAssignedStaffForCpOwn(Long offenderBookId, Long casePlanId);
	
	List<CasePlans> maxCaseplanIdRecord(final CasePlans objSearchDao);
}
