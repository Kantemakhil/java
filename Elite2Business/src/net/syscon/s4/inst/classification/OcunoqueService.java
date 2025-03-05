package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.OffenderAssessmentItems;

/**
 * Interface OcunoqueService
 */
public interface OcunoqueService {
	OffenderAssessments getCommentText(OffenderAssessments paramBean);

	OffenderAssessmentItems InitiateSaveProcess(OffenderAssessmentItems paramBean);

	List<ReferenceCodes> rgRankRecordGroup();

	Integer assessCommit(AssessmentsCommitBean CommitBean);

	Integer assess1Commit(AssessmentsCommitBean CommitBean);

	Integer answersCommit(AssessmentsCommitBean CommitBean);

	List<Assessments> assessExecuteQuery(Assessments objAssessments);

	List<Assessments> assessQuestionsExecuteQuery(Assessments searchBean);

	List<Assessments> answersExecuteQuery(Assessments searchBean);

	AssessmentsCommitBean assessCommitExecuteQuery(Assessments searchBean);

	AssessmentSupervisions getAssessmentScore(OffenderAssessments searchBean);

}
