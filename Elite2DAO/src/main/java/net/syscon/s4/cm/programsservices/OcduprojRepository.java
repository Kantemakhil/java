package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

/**
 * Interface OcduprojRepository
 */
public interface OcduprojRepository {
	List<ReferenceCodes> rgSupervisorRecordGroup(Long crsActyId);

	Integer projAllocUpdateOffenderProgramProfiles(List<OffenderProgramProfiles> lstOffenderProgramProfiles);

	List<OffenderProgramProfiles> checkAssignConflict(OffenderProgramProfiles paramBean);

	List<OffenderProgramProfiles> unpaidWkOnCheckDeleteMaster(OffenderProgramProfiles paramBean);

	List<ReferenceCodes> rgBehaviourRecordGroup();

	Integer skillsDeleteOffenderCourseSkills(List<OffenderCourseSkills> lstOffenderCourseSkills);

	List<StaffMembers> rgStaffCheckRecordGroup();

	Integer projAllocDeleteOffenderProgramProfiles(List<OffenderProgramProfiles> lstOffenderProgramProfiles);

	List<VOffenderCourseEvents> attendanceExecuteQuery(VOffenderCourseEvents objVOffenderCourseEvents);

	List<OffenderUnpaidWorkAdj> creditAdjExecuteQuery(OffenderUnpaidWorkAdj objOffenderUnpaidWorkAdj);

	List<VOffenderSentCondActs> offBkgOnCheckDeleteMaster(VOffenderSentCondActs paramBean);

	Integer attendanceUpdateVOffenderCourseEvents(List<VOffenderCourseEvents> lstVOffenderCourseEvents);

	List<OffenderProgramProfiles> projAllocExecuteQuery(OffenderProgramProfiles objOffenderProgramProfiles);

	Integer projAllocOnDeleteQuery(OffenderProgramProfiles searchBean);

	List<ReferenceCodes> rgAttendanceRecordGroup();

	List<CourseActivities> rgProjectCheckRecordGroup();

	List<CourseActivities> rgProjectRecordGroup(Long offenderBookId);

	List<ReferenceCodes> rgWorkQualityRecordGroup();

	List<ReferenceCodes> rgSkillsRecordGroup();

	List<ReferenceCodes> rgAdjReasonRecordGroup();

	Integer projAllocInsertOffenderProgramProfiles(List<OffenderProgramProfiles> lstOffenderProgramProfiles);

	List<VOffenderSentCondActs> unpaidWkExecuteQuery(VOffenderSentCondActs objVOffenderSentCondActs);

	List<OffenderUnpaidWorkAdj> unpaidWkOnCheckDeleteMaster(OffenderUnpaidWorkAdj paramBean);

	Integer creditAdjInsertOffenderUnpaidWorkAdj(List<OffenderUnpaidWorkAdj> lstOffenderUnpaidWorkAdj);

	Integer skillsUpdateOffenderCourseSkills(List<OffenderCourseSkills> lstOffenderCourseSkills);

	List<OffenderCourseSkills> skillsExecuteQuery(OffenderCourseSkills objOffenderCourseSkills);

	List<VOffenderCourseEvents> projAllocOnCheckDeleteMaster(VOffenderCourseEvents paramBean);

	Integer skillsInsertOffenderCourseSkills(List<OffenderCourseSkills> lstOffenderCourseSkills);

	List<OffenderCourseSkills> attendanceOnCheckDeleteMaster(OffenderCourseSkills paramBean);

	String userNameRecordGroup(String createUserId);

	BigDecimal getOffPrgrefIdCur();

	int createOffCourseAttendance(final VOffenderCourseEvents searchBean);

	int updateCourseAttendance(final VOffenderCourseEvents searchBean);

	OffenderCourseSkills firstNameLastName(OffenderCourseSkills paramBean);

	public void unpaidWorkCreditHours(final VOffenderCourseEvents searchBean);

	BigDecimal getCourseAttendenceId();

	CourseActivities getProjectCode(final String code, final Long crsActyId);

	List<VOffenderCourseEvents> getOldRecvOffenderCourseEvents(final Long eventId);

	Integer getActiveFlag(Long offenderId);

	List<VHeaderBlock> getLastAndFirstName(Long offenderId);

	List<OffenderNonAssociations> checkNonAssociations(final OffenderProgramProfiles paramBean);

	List<VOffenderCourseEvents> getOffederManualSchedules(OffenderProgramProfiles vOffenderProgramProfiles);

	List<OffenderProgramProfiles> checkNonAssociationProgramAssignment(final OffenderProgramProfiles search,
			final OffenderNonAssociations offNonAss);

	VHeaderBlock ocdprogrGetOffenderNames(final Long offBookId);

	List<VOffenderCourseEvents> getVOffenderCourseEventsData(Long offPrgrefId,Date offenderEndDate);

	Integer updateVOffenderCourseEventsDataStatus(final List<VOffenderCourseEvents> voffendercourseevents) ;
	
	List<VOffenderCourseEvents> getVOffenderCourseEventsDataOne(Long offPrgrefId,Date offnederEndDate);
	
	List<VOffenderCourseEvents> getVOffenderCourseEventsDataWithoutDate(Long offPrgrefId);
	
	List<VOffenderSentCondActs> getSentenceData(VOffenderSentCondActs searchBean);

	List<OffenderProgramProfiles> projectOldData(OffenderProgramProfiles objSearchDao);

	List<SentenceCalcTypes> senTypeExecuteQuery(String senCategory);

	String getCourtrecords(String agyLocId);

	Integer getEventId();

	List<ReferenceCodes> getReferenceDomainCodes(String string);
	
	
//	BigDecimal tagPrgCreditHours(OffenderSentConditions searchBean);
}
