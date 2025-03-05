package net.syscon.s4.inst.classification.assessmentmaintenance;

import java.util.List;

import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;

/**
 *
 * Interface OimslevlRepository
 */
public interface OimslevlRepository {
	Integer typeAssSupInsertAssessmentSupervisions(List<AssessmentSupervisions> object);

	List<AssessmentSupervisions> typeAssSupExecuteQuery(AssessmentSupervisions object);

	List<Assessments> assTypeExecuteQuery(Assessments object);

	List<Assessments> rgAssessmentTypesRecordGroup(String userId);

	List<Assessments> rgAssessmentSectionsRecordGroup();

	Integer typeAssSupDeleteAssessmentSupervisions(List<AssessmentSupervisions> object);

	List<AssessmentSupervisions> assSectOnCheckDeleteMaster(AssessmentSupervisions paramBean);

	Integer typeAssSupUpdateAssessmentSupervisions(List<AssessmentSupervisions> object);

	List<Assessments> assSectExecuteQuery(Assessments searchRecord);

	List<Assessments> assTypeAssSectExecuteQuery(Assessments searchRecord);

	List<AssessmentSupervisions> secAssSupExecuteQuery(AssessmentSupervisions searchRecord);

	Integer validateDelRow(List<Long> assessmentId);

	List<AssessmentResults> rgAssessmentResultsRecordGroup(Long assessmentId);

	String checkLovData(Long assessmentId);
	
	Integer updateEnforceFlag(Long assesmentId,String enforcementFlag,String userName);
	
	String getEnforcementFalg(Long assessmentId);
	
	Integer updateSectionsDetails( List<Assessments> assList);
}
