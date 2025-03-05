package net.syscon.s4.inst.classification.assessmentmaintenance;
import java.util.List;

import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.AssessSectionNotificationsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.Assessments;
/**
 * Interface OimsenotService 
 */
public interface OimsenotService  {
	Assessments assessSectionNotificationsPostQuery(AssessSectionNotifications paramBean)  ;
	List<Assessments> cgfkSectionCodeRecordGroup(Long assessmentId) ;

	List<ReferenceCodes> cgfkNextSectionFlagRecordGroup() ;

	AssessSectionNotifications assessSectionNotificationsPreInsert(AssessSectionNotifications paramBean)  ;

	Integer assessSectionNotificationsCommit(AssessSectionNotificationsCommitBean commitBean) ;

	List<Assessments> cgfkNextSectionRecordGroup(String parentField1) ;

	List<ReferenceCodes> cgfkScoreTypeRecordGroup() ;

	List<AssessSectionNotifications> assessSectionNotificationsExecuteQuery(AssessSectionNotifications obj) ;

	List<Assessments> assessmentsExecuteQuery(Assessments objAssessments) ;


}
