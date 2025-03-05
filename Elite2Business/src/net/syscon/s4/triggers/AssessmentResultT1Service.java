package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.classification.beans.AssessmentResults;

public interface AssessmentResultT1Service {
	Integer assessmentResultT1Tgr(List<AssessmentResults> assessmentResultsList) throws CustomException;
}
