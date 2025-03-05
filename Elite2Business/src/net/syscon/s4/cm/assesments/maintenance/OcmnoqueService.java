package net.syscon.s4.cm.assesments.maintenance;

import java.util.List;

import net.syscon.s4.cm.assesments.beans.AssessmentResultsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;

/**
 * Interface OcmnoqueService
 */
public interface OcmnoqueService {

	List<Assessments> assSectExecuteQuery(Assessments objAssessments);

	Integer assessCommit(AssessmentsCommitBean commitBean);

	Integer assResCommit(AssessmentResultsCommitBean commitBean);

	List<Assessments> assessExecuteQuery(Assessments objAssessments);

	List<ReferenceCodes> rgCaseloadTypeRecordGroup();

	List<AssessmentResults> assResExecuteQuery(AssessmentResults objAssessmentResults);

	List<Assessments> assQueExecuteQuery(Assessments searchBean);

	List<Assessments> assSectLov(Long assessmentId);

	List<Assessments> assQueLov(Long assessmentId);

	List<Assessments> assAnsExecuteQuery(Assessments searchBean);

	Integer validateCaseLoad(Long assessmentId);

	Boolean checkAssesRelations(Long assessmentId);

	boolean assessKeyDeleteRec(Long assessmentId);

	Boolean assessResKeyDeleteRec(AssessmentResults searchBean);

	String getDefaultAssessmentType();

	List<ReferenceCodes> rgBookMarkRecordGroup();
}
