package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;

/**
 * Interface OcduatteRepository
 */
public interface OcduatteRepository {
	List<ReferenceCodes> rgSupervisorRecordGroup(Long offenderbookId);

	Integer offCourseAttendUpdateVOffenderCourseEvents(List<VOffenderCourseEvents> lstVOffenderCourseEvents);

	List<ReferenceCodes> rgWorkQualityRecordGroup();

	List<ReferenceCodes> rgSkillsRecordGroup();

	List<CourseActivities> rgProjectsRecordGroup(String teamId);

	List<ReferenceCodes> rgBehaviourRecordGroup();

	List<OffenderCourseSkills> offCourseSkillsExecuteQuery(OffenderCourseSkills objOffenderCourseSkills);

	List<Teams> rgTeamsRecordGroup(String userId);

	List<StaffMembers> rgStaffCheckRecordGroup();

	Integer offCourseSkillsInsertOffenderCourseSkills(List<OffenderCourseSkills> lstOffenderCourseSkills);

	List<CourseActivities> rgProjects2RecordGroup(Long offenderBookId);

	Integer offCourseSkillsUpdateOffenderCourseSkills(List<OffenderCourseSkills> lstOffenderCourseSkills);

	List<VOffenderCourseEvents> offCourseAttendExecuteQuery(VOffenderCourseEvents objVOffenderCourseEvents);

	List<OffenderCourseSkills> offCourseAttendOnCheckDeleteMaster(OffenderCourseSkills paramBean);

	List<ReferenceCodes> rgAttendanceRecordGroup(String pShowOutcome);

	Integer offCourseSkillsDeleteOffenderCourseSkills(List<OffenderCourseSkills> lstOffenderCourseSkills);

	VOffenderCourseEvents offCourseAttendPostQuery(VOffenderCourseEvents action);

	OffenderCourseSkills getStaffNamePostQuery(OffenderCourseSkills action);

	Integer compareDateTime(VOffenderCourseEvents actionObj);

	Integer getCompareDateTime(VOffenderCourseEvents actionObj);

	Integer saveOffCourseAttendonUpdate(VOffenderCourseEvents actionObj);

	Integer saveOffCourseAttendPostUpdate(VOffenderCourseEvents actionObj);

	Integer saveOffCourseAttendPreUpdate(VOffenderCourseEvents actionObj);

	VOffenderCourseEvents checkUa(VOffenderCourseEvents actionObj);

	String getStaffName(long staffId);

	List<VOffenderSentenceEvents> checkUaFailCur(VOffenderCourseEvents actionObj);

	String checkUaCheckUaEventOutcome(VOffenderCourseEvents actionObj, String eventOutcom);

	Integer checkUaCountSentenceUa(VOffenderSentenceEvents actionObj, BigDecimal eventId);

	VOffenderCourseEvents ocduatteVoffenderCourseEventsOldObject(VOffenderCourseEvents actionObj);

}
