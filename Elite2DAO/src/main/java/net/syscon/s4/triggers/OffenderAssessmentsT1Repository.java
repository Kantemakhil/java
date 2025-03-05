package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderAssessments;

public interface OffenderAssessmentsT1Repository {
	OffenderAssessments getOffenderAssessments(Long offenderBookId,Long assessmentSeq);
}
