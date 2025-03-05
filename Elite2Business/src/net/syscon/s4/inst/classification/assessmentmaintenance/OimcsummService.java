package net.syscon.s4.inst.classification.assessmentmaintenance;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssOffNeedsCommitBean;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
import net.syscon.s4.inst.classification.beans.VAssTreatProtsCommitBean;
/**
 * Interface OimcsummService 
 */
public interface OimcsummService  {
	List<VAssOffNeeds> assessmentsOnCheckDeleteMaster(VAssOffNeeds paramBean)  ;

	List<VAssTreatProts> vAssOffNeedsOnCheckDeleteMaster(VAssTreatProts paramBean)  ;

	List<ReferenceCodes> rgPrgCategoryRecordGroup() ;

	Integer assessmentsCommit(AssessmentsCommitBean commitBean) ;

	Integer vAssOffNeedsCommit(VAssOffNeedsCommitBean commitBean) ;

	Integer vAssTreatProtsCommit(VAssTreatProtsCommitBean commitBean) ;

	List<VAssOffNeeds> vAssOffNeedsExecuteQuery(VAssOffNeeds objVAssOffNeeds) ;

	List<ReferenceCodes> rgCaseplanAssRecordGroup() ;

	Long vAssOffNeedsPreInsert()  ;

	Long vAssTreatProtsPreInsert()  ;

	List<ReferenceCodes> rgCaseworkRecordGroup() ;

	List<ProgramServices> rgProgramIdRecordGroup(String programCategory) ;

	List<VAssTreatProts> vAssTreatProtsExecuteQuery(VAssTreatProts objVAssTreatProts) ;

	List<Assessments> cgfkNextSectionRecordGroup() ;

	List<ReferenceCodes> cgfkScoreTypeRecordGroup() ;

	List<Assessments> assessmentsExecuteQuery(Assessments objAssessments) ;

	List<Assessments> cgfkSectionCodeRecordGroup(Long assessmentId) ;
}
