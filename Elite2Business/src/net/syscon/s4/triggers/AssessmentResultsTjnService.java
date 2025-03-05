package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inst.classification.beans.AssessmentResults;

public interface AssessmentResultsTjnService {

	

	public Integer insertAssessmentresult(List<AssessmentResults> insertList);

	public Integer updateAssessmentResult(List<AssessmentResults> updateList);

	public Integer deleteAssessmentResult(List<AssessmentResults> deleteList);
}
