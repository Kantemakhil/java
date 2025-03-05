package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkillsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;

/**
 * Interface OcduprojService
 */
public interface OcduprojService {
	List<ReferenceCodes> rgSupervisorRecordGroup(Long crsActyId);

	Integer unpaidWkCommit(VOffenderSentCondActsCommitBean commitBean);

	List<CourseActivities> rgProjectRecordGroup(Long offenderBookId);

	List<ReferenceCodes> rgWorkQualityRecordGroup();

	Integer skillsCommit(OffenderCourseSkillsCommitBean commitBean);

	List<ReferenceCodes> rgSkillsRecordGroup();

	List<ReferenceCodes> rgAdjReasonRecordGroup();

	List<OffenderProgramProfiles> unpaidWkOnCheckDeleteMaster(OffenderProgramProfiles paramBean);

	Integer creditAdjCommit(OffenderUnpaidWorkAdjCommitBean commitBean);

	List<ReferenceCodes> rgBehaviourRecordGroup();

	List<VOffenderSentCondActs> unpaidWkExecuteQuery(VOffenderSentCondActs objVOffenderSentCondActs);

	List<VOffenderCourseEvents> projAllocOnCheckDeleteMaster(VOffenderCourseEvents paramBean);

	Integer attendanceCommit(VOffenderCourseEventsCommitBean commitBean);

	List<StaffMembers> rgStaffCheckRecordGroup();

	List<VOffenderCourseEvents> attendanceExecuteQuery(VOffenderCourseEvents objVOffenderCourseEvents);

	List<OffenderUnpaidWorkAdj> creditAdjExecuteQuery(OffenderUnpaidWorkAdj objOffenderUnpaidWorkAdj);

	List<OffenderCourseSkills> attendanceOnCheckDeleteMaster(OffenderCourseSkills paramBean);

	List<VOffenderSentCondActs> offBkgOnCheckDeleteMaster(VOffenderSentCondActs paramBean);

	List<OffenderProgramProfiles> projAllocExecuteQuery(OffenderProgramProfiles objOffenderProgramProfiles);

	public Integer projAllocOnDeleteQuery(OffenderProgramProfiles searchBean);

	List<OffenderCourseSkills> skillsExecuteQuery(OffenderCourseSkills objOffenderCourseSkills);

	Integer projAllocCommit(OffenderProgramProfilesCommitBean commitBean);

	List<ReferenceCodes> rgAttendanceRecordGroup();

	List<CourseActivities> rgProjectCheckRecordGroup();

	List<ReferenceCodes> reaonsReferenceCodes();

	Integer getActiveFlag(Long offenderId);

	List<VHeaderBlock> getLastAndFirstName(Long offenderId);

	String checkingNonAssociations(OffenderProgramProfilesCommitBean commitBean);

}
