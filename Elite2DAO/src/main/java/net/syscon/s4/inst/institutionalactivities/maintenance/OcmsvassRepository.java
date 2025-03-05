package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.inst.casemanagement.beans.ProgramAssessments;
import net.syscon.s4.inst.classification.beans.Assessments;

/**
 * Interface OcmsvassRepository
 */

public interface OcmsvassRepository {
	List<Assessments> rgAssessmentsRecordGroup();

	Integer prgQstInsertProgramAssessments(List<ProgramAssessments> lstProgramAssessments);

	Integer prgQstDeleteProgramAssessments(List<ProgramAssessments> lstProgramAssessments);

	Integer prgQstUpdateProgramAssessments(List<ProgramAssessments> lstProgramAssessments);

	List<ProgramAssessments> prgQstExecuteQuery(ProgramAssessments obj);

	Integer postQuery(ProgramAssessments obj);

	String getAssessmentCode(BigDecimal assessmentId);

	BigDecimal getAssessmentId(String assessmentCode);

}
