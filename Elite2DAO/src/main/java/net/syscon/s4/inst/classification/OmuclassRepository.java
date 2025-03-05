package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.classification.beans.AssessmentSectionScoresV1;

/**
 * Interface OmuclassRepository
 * 
 */
public interface OmuclassRepository {
	List<AssessmentSectionScoresV1> subTotalsExecuteQuery(AssessmentSectionScoresV1 object);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	String preQueryGetCaseloadType(String userName);

	String preQueryGetSecurityLevel(AssessmentSectionScoresV1 obj);

}
