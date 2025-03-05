package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.OffenderAssessments;

public interface OffenderAssessmentsTwf1Repository {
	OffenderAssessments getOffenderAssessments(Long offenderBookId, Long assessmentSeq);

	Long curAssessmentType();

	String curInmateIsAtRisk(Long offenderBookId, Long assessmentSeq, BigDecimal assessmentTypeId);

}
