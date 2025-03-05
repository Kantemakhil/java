package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.inst.casemanagement.beans.ProgramAssessments;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.Assessments;

/**
 * Interface OcmsvassService
 */

public interface OcmsvassService {
	Integer prgQstCommit(ProgramAssessmentsCommitBean commitBean);

	List<Assessments> rgAssessmentsRecordGroup();

	List<ProgramAssessments> prgQstExecuteQuery(ProgramAssessments objProgramAssessments);

}
