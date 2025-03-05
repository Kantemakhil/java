package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.inst.classification.beans.AssessmentSectionScoresV1;
import net.syscon.s4.inst.classification.beans.AssessmentSectionScoresV1CommitBean;

/**
 * Interface OmuclassService
 */
public interface OmuclassService {
	List<Object> cgwhenNewFormInstance();

	Integer subTotalsCommit(AssessmentSectionScoresV1CommitBean commitBean);

	List<AssessmentSectionScoresV1> subTotalsExecuteQuery(AssessmentSectionScoresV1 objAssessmentSectionScoresV1);

}
