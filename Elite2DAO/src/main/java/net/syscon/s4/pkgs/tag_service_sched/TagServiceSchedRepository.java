package net.syscon.s4.pkgs.tag_service_sched;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;

public interface TagServiceSchedRepository {
	// public CourseSchedules crsCur(final Long crsSchId);

//	public Long deleteCourseSchedule(final Date startDate, final Date startTime);

	public CourseSchedules crsCur(final Long crsSchId);

	public Long clearCourseAttendances(final Long programInstanceId, final Long phaseInstanceId, final Long crsChId);

	public Long deleteCourseSchedules(final Long programInstanceId, final Long phaseInstanceId, final Long crsChId,
			final Date startDate, final Date startTime);

	Long getLvSessionSeq(final VAcpSchedules paramBean);

	Date getMaxStartTimeCursorPriorCur(VAcpSchedules paramBean);

	Date getMinStartTimeCursorPriorCur(VAcpSchedules paramBean);

	CourseActivities gapCur(final Long crsActyId);

	CourseActivities phsCur(Long phaseInstanceId);

	public Date getLastSchedDate(Long pCrsActyId);

	List<CourseActivities> getCaDates(Long pCrsActyId);

	List<CourseActivities> checkRespectHols(Long pCrsActyId);

	Long getCrsSchId();

	Integer insertCourseSchedules(CourseSchedules crsSchedules);

	List<SystemEvents> holCur(Date pStartDate);

	List<CourseScheduleRules> lvRuleCur(Long pCrsActyId);

	Long ssnCur(Long listSeq, Long parentCrsActyId);

	Boolean checkRespectHols1(Long pCrsActyId);

	List<SystemEvents> holCur1(Date pStartDate);

	List<CourseScheduleRules> lvRuleCur1(Long pCrsActyId);

	List<CourseActivities> crsCur1(Long pCrsActyId);

	List<VAcpSchedules> schCur1(Long lvProgramInstanceId, Long lvPhaseInstanceId, Long pCrsActyId);

	List<CourseActivities> adjAllPerCrsCur1(Long pCrsActyId);

	List<CourseActivities> adjustPeriodCrsCur1(Long crsActyId);

	List<CourseSchedules> getSessionLimits1(Long crsActyId);

	Integer ssnCur1(Long listSeq, Long parentCrsActyId);

	Integer updateCourseActivitiesOperation1(Long crsActyId, Integer lvMinSessionNo, Date lvMinScheduleDate,
			Integer lvMaxSessionNo, Date lvMaxScheduleDate, Integer lvStartSessionNo, Integer lvEndSessionNo,
			final String userName);

	List<CourseActivities> courseActivitiesSEDates1(Long crsActyId);

	List<CourseActivities> crsCurAp(Long pCrsActyId);

	List<OffenderProgramProfiles> allocCurSelect1(Long crsActyId);

	Date getScheduleDateForSession1(Long crsActyId, Long startSessionNo);

	Date endCur1(Long offPrgrefId, Date lvBigDate);

	void updateOffProProf1(Long offPrgrefId, Date lvOffenderStartDate, Date lvOffenderEndDate, final String userName);

	void clearCourseAttendances1(Long pCrsActyId, Long lvProgramInstanceId, Long lvPhaseInstanceId);

	Integer updateCourseSchedule(final VAcpSchedules vacpSchedules, final String userName);

	CourseSchedules getSessionLimits(Long crsActyId);

	Integer updateCourseActivities(Long lvMinSessionNo, Long lvStartSessionNo, Date lvMinScheduleDate,
			Long lvMaxSessionNo, Long lvEndSessionNo, Date lvMaxScheduleDate, Long crsActyId, String userName);

	CourseActivities getCourseActivities(Long crsActyId);

	Date schCur(Long pCrsActyId, Long pSessionNumber);

	Long countSessions(Long pCrsActyId);

	Date getLastProgramScheduleDate(Long pProgramInstanceId, Long pPhaseListSeq);

	Date getNextPhaseScheduleDate(Long pProgramInstanceId, Long pPhaseListSeq);

	public Integer getMaxSessions(final Long pCrsActyId);

	public CourseActivities getCourseActstartCur(final Long pCrsActyId);

	Date getPriorSchedueDate(CourseActivities bean);

	Integer insertCourseSchedule(final VAcpSchedules vacpSchedules, final String userName);

	Integer createSchAttendances(final Long pCrsSchId, final String userName);

	List<CourseActivities> crsCurTwo(Long pCrsActyId);

	List<VAcpSchedules> schCur(Long lvProgramInstanceId, Long lvPhaseInstanceId, Long pCrsSchId);

	List<CourseActivities> adjAllPerCrsCur(Long pCrsActyId);

	void clearCourseAttendancesTwo(Long pCrsActyId, Long lvProgramInstanceId, Long lvPhaseInstanceId,String modifyUserId);

	public CourseActivities getCourseAcctDetails(Long pParentCrsActyId);

	Date getPriorScheduleDate(Long pProgramInstanceId, BigDecimal lvSessionSeq);

	Date getNextScheduleDate(Long pProgramInstanceId, BigDecimal lvSessionSeq);

	List<CourseActivities> caCurCsBldAcpSch(Long pCrsActyId);

	Long maxCurCsBldAcpSch(Long pcrsActyId);

	Long startCurCsBldAcpSch(Long pcrsActyId);

	VAcpSchedules getLastSessionDetailsCsBldAcpSch(Long pProgramInstanceId, Long pPhaseInstanceId);

	List<CourseActivities> lvCaRefCsBldAcpSch(Long pCrsActyId, Long lvPhaseListSeq, Long lvModuleListSeq,
			Long pListSeqEnd);

	List<CourseActivities> crsCurCsBldAcpSch(Long pCrsActyId);

	CourseSchedules getSessionLimitsCsBldAcpSch(Long pCrsActyId);

	Long ssnCurCsBldAcpSch(Long pListSeq, Long pParentCrsActyId);

	Integer updateCourseActivitiesCsBldAcpSch(Date schStartDate, Date schEndDate, Long crsActyId);

	CourseActivities getCourseActivitiesCsBldAcpSch(Long crsActyId);

	List<OffenderProgramProfiles> allocCurCsBldAcpSch(Long crsActyId);

	Date schCurCsBldAcpSch(Long pCrsActyId, Long pSessionNumber);

	Date endCurCsBldAcpSch(Long pOffPrgrefId, Date lvBigDate);

	Integer updateOffenderProgramProfilesCsBldAcpSch(Long pOffPrgrefId, Date lvOffenderStartDate,
			Date lvOffenderEndDate);
	
	Integer updateCourseSchedule(CourseSchedules obj);

	Long deleteCourseSchedule(Date startDate, Date startTime, Long crsActyId,String modifyUserId);

}