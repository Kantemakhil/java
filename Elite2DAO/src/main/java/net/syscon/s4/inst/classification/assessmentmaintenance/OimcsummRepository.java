package net.syscon.s4.inst.classification.assessmentmaintenance;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
/**
 * Interface OimcsummRepository
 */
public interface OimcsummRepository {
	List<VAssOffNeeds> assessmentsOnCheckDeleteMaster(VAssOffNeeds paramBean);

	List<VAssTreatProts> vAssOffNeedsOnCheckDeleteMaster(VAssTreatProts paramBean);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer vAssOffNeedsUpdateVAssOffNeeds(List<VAssOffNeeds> lstVAssOffNeeds) ;

	Integer vAssOffNeedsInsertVAssOffNeeds(List<VAssOffNeeds> lstVAssOffNeeds) ;

	List<ReferenceCodes> rgPrgCategoryRecordGroup() ;

	List<VAssOffNeeds> vAssOffNeedsExecuteQuery(VAssOffNeeds objVAssOffNeeds) ;

	List<ReferenceCodes> rgCaseplanAssRecordGroup() ;

	Integer vAssTreatProtsInsertVAssTreatProts(List<VAssTreatProts> lstVAssTreatProts) ;

	List<ReferenceCodes> rgCaseworkRecordGroup() ;

	Integer vAssTreatProtsUpdateVAssTreatProts(List<VAssTreatProts> lstVAssTreatProts) ;

	Integer vAssTreatProtsDeleteVAssTreatProts(List<VAssTreatProts> lstVAssTreatProts) ;

	Integer vAssOffNeedsDeleteVAssOffNeeds(List<VAssOffNeeds> lstVAssOffNeeds) ;

	List<ProgramServices> rgProgramIdRecordGroup(String programCategory) ;

	List<VAssTreatProts> vAssTreatProtsExecuteQuery(VAssTreatProts objVAssTreatProts) ;

	Long vAssTreatProtsPreInsert();

	List<Assessments> cgfkNextSectionRecordGroup() ;

	Long vAssOffNeedsPreInsert();

	List<ReferenceCodes> cgfkScoreTypeRecordGroup() ;

	List<Assessments> assessmentsExecuteQuery(Assessments objAssessments) ;

	List<Assessments> cgfkSectionCodeRecordGroup( Long assessmentId) ;
}
