package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inst.classification.beans.AssessmentResults;

public interface AssessmentResultsTjnRepository {

	Integer insertAssessmentResult(List<AssessmentResults> assessmentResult);

	Integer updateAssessmentResult(List<AssessmentResults> assessmentResult);

	Integer deleteAssessmentResult(List<AssessmentResults> assessmentResult);
	


}
