package net.syscon.s4.cm.assessments.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;

/**
 * Interface OcmnoqueRepository
 */
public interface OcmnoqueRepository {
	Integer assResUpdateAssessmentResults(List<AssessmentResults> lstAssessmentResults);

	List<Assessments> assSectExecuteQuery(Assessments objAssessments);

	Integer assResDeleteAssessmentResults(List<AssessmentResults> lstAssessmentResults);

	Integer assResInsertAssessmentResults(List<AssessmentResults> lstAssessmentResults);

	List<Assessments> assessExecuteQuery(Assessments objAssessments);

	Integer assessInsertAssessments(List<Assessments> lstAssessments);

	List<ReferenceCodes> rgCaseloadTypeRecordGroup();

	Integer assessUpdateAssessments(List<Assessments> lstAssessments);

	Integer assessDeleteAssessments(List<Assessments> lstAssessments);

	List<AssessmentResults> assResExecuteQuery(AssessmentResults objAssessmentResults);

	List<Assessments> assQueExecuteQuery(Assessments searchRecord);

	List<Assessments> assSectLov(Long assessmentId);

	List<Assessments> assQueLov(Long assessmentId);

	Long getAssessmentId();

	List<Assessments> assAnsExecuteQuery(Assessments searchRecord);

	Integer validateCaseLoad(Long assessmentId);

	Integer getAssRelCount(Long assessmentId);

	Integer getAssResRelCount(Long assessmentId);

	List<TableColumnNameBean> assessKeyDelList();

	Integer getAssessKeyDeleteRecRowCount(String tableName, String columnName, Long assessmentId);

	Integer getAssessmentSupCount(AssessmentResults searchBean);

	Integer getOffenderAssCount(AssessmentResults searchBean);

	String getDefaultAssessmentType();
	
	List<ReferenceCodes> rgBookMarkRecordGroup();

}
