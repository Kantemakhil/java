package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.OffenderAssessmentItems;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
import net.syscon.s4.inst.classification.beans.VOffassAss;

/**
 * Interface OcunoqueRepositoryl
 */
public interface OcunoqueRepository {
	Long addRemoveAnswerGetItemSeqCur(OffenderAssessments paramBean);

	AssessmentSupervisions overrideFlagRule(AssessmentSupervisions paramBean);

	OffenderAssessmentItems checkForAllAnswers(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems chkRankAssesFromQuestions(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems initiateSaveProcess(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems calculateRankScore(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems assess1WhenNewRecordInstance(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems saveProcessFromSections(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems checkRankAssesFromSections(OffenderAssessmentItems paramBean);

	List<Assessments> assess1OnCheckDeleteMaster(Assessments paramBean);

	List<Assessments> checkForAllAnswers(Assessments paramBean);

	VOffassAss inactivatePrviousAssessment(OffenderAssessments paramBean,final String userId);

	AssessmentSupervisions saveAssessmentItemsGetSuperLevelType(OffenderAssessments paramBean);

	OffenderAssessmentItems skipSectionMsg(OffenderAssessmentItems paramBean);

	Assessments overrideFlagRule(Assessments paramBean);

	OffenderAssessmentItems skipSectionTotal(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems calculateRankScoreFromWvr(OffenderAssessmentItems paramBean);

	List<Assessments> assessOnCheckDeleteMaster(Assessments paramBean);

	OffenderAssessmentItems assess1PostQuery(OffenderAssessmentItems paramBean);

	AssessmentSupervisions saveAssessmentItemsGetMinScore(OffenderAssessments paramBean);

	AssessmentSupervisions rankSupLevel(AssessmentSupervisions paramBean);

	OffenderAssessmentItems checkRankAssessments(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems getRankScoreFromListval(OffenderAssessmentItems paramBean);

	Assessments saveAssessmentItemsNew(Assessments paramBean);

	AssessmentSupervisions saveAssessmentItemsGetMaxScore(OffenderAssessments paramBean);

	OffenderAssessmentItems setRowLock(OffenderAssessmentItems paramBean);

	OffenderAssessmentItems saveAssessmentItemsGetCurrentTotalScore(OffenderAssessments paramBean);

	AssessmentSupervisions saveAssessmentItemsNew(AssessmentSupervisions paramBean);

	OffenderAssessmentItems chkRankQuesFromDifBlk(OffenderAssessmentItems paramBean);

	OffenderAssessments setRowLock(OffenderAssessments paramBean);

	OffenderAssessmentItems overrideFlagRule(OffenderAssessmentItems paramBean);

	Assessments saveAssessmentItems(Assessments paramBean);

	OffenderAssessmentItems saveAssessmentItemsNew(OffenderAssessmentItems paramBean);

	List<ReferenceCodes> rgRankRecordGroup();

	OffenderAssessmentItems checkForUserAnswer(OffenderAssessmentItems paramBean);

	OffenderAssessments getCommentText(OffenderAssessments paramBean);

	List<Assessments> assessExecuteQuery(Assessments objAssessments);

	List<Assessments> assessQuestionsExecuteQuery(Assessments searchRecord);

	List<Assessments> answersExecuteQuery(Assessments searchRecord);

	Integer initiateSaveProcessgetanscountcur(OffenderAssessments paramBean);

	OffenderAssessmentItems checkForUserAnswergetanscur(OffenderAssessmentItems offenderAssessmentItems);

	OffenderAssessmentItems getAnsIfExistsCur(OffenderAssessmentItems offenderAssessmentItems);

	Integer deleteOffenderAssessmentItems(Assessments answersBean);

	Integer insertOffenderAssessmentItems(Assessments listAnswers);
	
	AssessmentSupervisions getAssessmentScore(OffenderAssessments paramBean);

	AssessmentSupervisions getAssessmentSupervisionLevel(AssessmentSupervisions paramBean);

	Integer updateOffenderAssessments(List<OffenderAssessments> listAnswers);

	Integer existsOffenderAssessments(OffenderAssessments offAssesModel);

	Integer inActivateOffenderAssessments(List<OffenderAssessments> insertUpdateList);

	Long getAssesmentSeq(Long offenderBookId);

	Integer checkAssExist(Assessments answersBean);

	Integer removeExistingAssItems(Assessments answersObj);
	
	Integer updateOffenderAssessmentItems(Assessments listAnswers);
	
	String getQuery(Assessments assessments);
	
	Long getOffenderAge(Assessments assessments);
	
	String getBookMarkAnswers(Assessments assessments);
	
	String getSupervisionLevelTypeForScore(Long assesmentId ,Long score);
	
	List<AssessSectionNotifications> assessSectionNotificationsExecuteQuery(AssessSectionNotifications obj) ;
	
    String getAssmentEnforceFlag(Long assessmentId);
    
    Long casePlanCount(Long offenderBookId);
	
	List<VAssOffNeeds> getCriminogenicNeeds(Long assesmentId);
	
	List<VAssTreatProts> getTreatmentProtocols(Long offenderBookId,Long preSectionScore,Long assesmentId);
	
	Long getoffCrimNeedIdSeq();
	
	Integer offCriNeedsInsertOffenderCriminogenicNeeds(List<OffenderCriminogenicNeeds> lstOffenderCriminogenicNeeds);
   

}
