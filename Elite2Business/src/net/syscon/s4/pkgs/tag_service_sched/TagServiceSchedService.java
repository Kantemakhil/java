package net.syscon.s4.pkgs.tag_service_sched;

import java.util.Date;
import java.util.Map;

import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

public interface TagServiceSchedService {

	String csClearSchedules(final Long crsSchId, final Long crActyId,String modifyUserId);

	void csAcpClearSessions(final VAcpSchedules searchBean);

	Date getMaxStartTimeCursorPriorCur(VAcpSchedules paramBean);

	Date getMinStartTimeCursorPriorCur(VAcpSchedules paramBean);

	Map<String, Object> getCrsActyWithGaps(Long crsActyId);

	Date getLastSchedDate(Long pCrsActyId);

	CourseScheduleRules csBuildRecurringSchedule(Long pCrsActyId, Long pNoDays, String userName);

	Map<String, Object> csBuildRecurringSchedule(Long pCrsActyId, Date pPeriodStartDate, Date pPeriodEndDate, String userName);

	void csInitBuild(Long pCrsActyId, Date pStartDate);

	Map<String, Object> csCreateSchedule(Long pCrsActyId, Long pNoOfSessions, Date pEndDate,String userName);

	Long getStartSessionNumber(Long crsActyId, String courseClass, Long listSeq, Long parentCrsActyId);

	Long getModStartSessionNumber(Long crsActyId, Long listSeq, Long parentCrsActyId);

	// CourseActivities reSchedule(Long pCrsActyId, Long pCrsSchId, Date
	// pStartDate);
	void getHolidays(Date pStartDate);

	Date changeCourseSchedule(final VAcpSchedules vacpSchedules, final String userName);

	void updateCourseSchedule(final VAcpSchedules vacpSchedules, final String userName);

	Map<String, Object> adjustPeriod(Long pCrsActyId, final String userName);

	CourseSchedules getSessionLimits(Long crsActyId);

	Date getScheduleDateForSession(Long pCrsActyId, Long pSessionNumber);

	Long countSessions(Long pCrsActyId);

	Date getLastProgramScheduleDate(Long pProgramInstanceId, Long pPhaseListSeq);

	Date getNextPhaseScheduleDate(Long pProgramInstanceId, Long pPhaseListSeq);

	Long noSessionsDone(final Long pCrsActyId);

	Date getPriorScheduleDate(CourseActivities bean);

	Date createCourseSchedule(final VAcpSchedules vacpSchedules, final String userName);

	Long insertCourseSchedule(final VAcpSchedules vacpSchedules, final String userName);

	void createSchAttendances(final Long pCrsSchId, final String userName);

	CourseActivities csAcpReSchedule(Long pCrsActyId, Long pCrsSchId, Date pStartDate, final String userName);

	Map<String, Object> getNextSession(Date pDate, Date pStartTime, Date pEndTime);

	Date[] extractTimess(Date pDate, String gTimes);

	void adjustAllPeriods(Long pCrsActyId, final String userName);

	void clearCourseAttendances(Long pCrsActyId, Long lvProgramInstanceId, Long lvPhaseInstanceId,String modifyUserId);

	CourseActivities lastCrsActyBuilt(Long pParentCrsActyId);

	Map<String, Object> getScheduleDateLimits(Long pProgramInstanceId, Long pPhaseListSeq, Long pSessionNo);

	Map<String, Object> csBuildAcpSchedule(Long pCrsActyId, Date pStartDate, Long pListSeqEnd, Long p_weeks_between, String userName);

	Map<String, Object> csBuildAcpSchedule(Long pCrsActyId, String pCourseClass, Long pNoOfSessions, Long pPhaseListSeq,
			Long pModuleListSeq, Long pListSeqEnd,String userName);

	Map<String, Object> adjustPeriod(Long pCrsActyId);

}