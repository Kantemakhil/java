package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkillsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;

/**
 * Interface OcduatteService
 */
public interface OcduatteService {
	List<ReferenceCodes> rgSupervisorRecordGroup(Long crsActyId);

	List<ReferenceCodes> rgWorkQualityRecordGroup();

	List<ReferenceCodes> rgSkillsRecordGroup();

	List<CourseActivities> rgProjectsRecordGroup(String teamId);

	List<ReferenceCodes> rgBehaviourRecordGroup();

	List<OffenderCourseSkills> offCourseSkillsExecuteQuery(OffenderCourseSkills objOffenderCourseSkills);

	List<Teams> rgTeamsRecordGroup(final String userId);

	List<StaffMembers> rgStaffCheckRecordGroup();

	List<OffenderCourseSkills> offCourseAttendOnCheckDeleteMaster(OffenderCourseSkills paramBean);

	List<CourseActivities> rgProjects2RecordGroup(Long offenderBookId);

	List<OffenderCourseSkills> offCourseSkillsCommit(OffenderCourseSkillsCommitBean commitBean);

	List<VOffenderCourseEvents> offCourseAttendExecuteQuery(VOffenderCourseEvents objVOffenderCourseEvents);

	Integer offCourseAttendCommit(VOffenderCourseEventsCommitBean commitBean);

	List<ReferenceCodes> rgAttendanceRecordGroup(String pShowOutcome);

	Integer compareDateTime(VOffenderCourseEvents actionObj);

	VOffenderCourseEvents checkUa(VOffenderCourseEvents actionObj);

	String getStaffName(long staffId);

}
