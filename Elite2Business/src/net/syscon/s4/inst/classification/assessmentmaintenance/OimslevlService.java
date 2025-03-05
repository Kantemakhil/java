package net.syscon.s4.inst.classification.assessmentmaintenance;

import java.util.List;
import net.syscon.s4.common.beans.AssessmentSupervisionsCommitBean;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;

/**
 * Interface OimslevlService
 */
public interface OimslevlService {
	List<AssessmentSupervisions> typeAssSupExecuteQuery(AssessmentSupervisions object);

	List<AssessmentSupervisions> secAssSupCommit(AssessmentSupervisionsCommitBean object);

	List<Assessments> assTypeExecuteQuery(Assessments object);

	List<Assessments> rgAssessmentTypesRecordGroup(String userNameOne);

	List<Assessments> rgAssessmentSectionsRecordGroup();

	List<AssessmentSupervisions> typeAssSupCommit(AssessmentSupervisionsCommitBean commitBean);

	List<AssessmentResults> rgAssessmentResultsRecordGroup(Long assessmentId);

	List<AssessmentSupervisions> secAssSupExecuteQuery(AssessmentSupervisions searchBean);

	List<Assessments> assSectExecuteQuery(Assessments searchBean);

	List<Assessments> assTypeAssSectExecuteQuery(Assessments searchRecord);

	Integer validateDelRow(List<AssessmentSupervisions> object);

	String checkLovData(Long assessmentId);
	
	Integer updateEnforceFlag(Long assesmentId,String enforcementFlag,String userName);

	String getEnforcementFlag(Long assessmentId);
	
	Integer updateSectionsDetails( AssessmentsCommitBean commitBean);

}
