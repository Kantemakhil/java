package net.syscon.s4.inst.classification.assessmentmaintenance;
import java.util.List;

import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.Assessments;
/**
 * Interface OimsenotRepository
 */
public interface OimsenotRepository {
	Assessments assessSectionNotificationsPostQuery(AssessSectionNotifications notfifctns);

	List<Assessments> cgfkSectionCodeRecordGroup( Long assessmentId) ;

	AssessSectionNotifications assessSectionNotificationsPreInsert(AssessSectionNotifications paramBean);

	Integer assessSectionNotificationsUpdateAssessSectionNotifications(List<AssessSectionNotifications> list) ;

	List<ReferenceCodes> cgfkNextSectionFlagRecordGroup() ;

	Integer assessSectionNotificationsDeleteAssessSectionNotifications(List<AssessSectionNotifications> list) ;

	List<Assessments> cgfkNextSectionRecordGroup(String parentId, String assemtId) ;

	List<ReferenceCodes> cgfkScoreTypeRecordGroup() ;

	List<AssessSectionNotifications> assessSectionNotificationsExecuteQuery(AssessSectionNotifications obj) ;

	Integer assessSectionNotificationsInsertAssessSectionNotifications(List<AssessSectionNotifications> list) ;

	List<Assessments> assessmentsExecuteQuery(Assessments objAssessments) ;

}
