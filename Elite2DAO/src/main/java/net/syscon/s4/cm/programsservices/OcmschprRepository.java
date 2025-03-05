package net.syscon.s4.cm.programsservices;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.iwp.beans.VAcpSchedules;
/**
 * Interface OcmschprRepository
 */
public interface OcmschprRepository {

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities) ;
	
	CourseActivities crsActPostQuery(CourseActivities objSearchDao);
	
	 Long getActualSessions(CourseActivities objSearchDao);
	 
	 List<ProgramServices> getProgramServices(CourseActivities objSearchDao);
	 
	 Long getTotalSessions(CourseActivities objSearchDao);
	
	 List<VAcpSchedules> vAcpSchedulesExecuteQuery(VAcpSchedules objVAcpSchedules);
	 
	 VAcpSchedules vAcpSchedulesInsertChecking(final Long crsActyId);
	 
	 String getWeekday(final VAcpSchedules objSearchDao);
	 
	 List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules objCourseScheduleRules) ;
	 
	 CourseActivities vAcpSchedulesValidate(final VAcpSchedules vacp);

	long getCourseScheduleRuleId();
	
	Integer crsScheduleRulInsertCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) ;
	
	 Integer crsScheduleRulUpdateCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) ;
	 
	 Integer crsScheduleRulDeleteCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) ;
	 
	 Date insertVAcpSchedules(final VAcpSchedules  lstVAcpSchedules) ;
	 
	 Date updateVAcpSchedules(final VAcpSchedules lstVAcpSchedules);
	 
	 void updateCourseAttendanceDates(final VAcpSchedules lstVAcpSchedules);
	 
	 void cancelAttendForSchedule(final VAcpSchedules lstVAcpSchedules);

	void vAcpSchedulesDelete(final VAcpSchedules searchBean);
	
	Integer updateCrsActyChecksum(final Long programInstanceId, String userName);
	
	Integer getCrsActyChecksum(final Long programInstanceId);

	Boolean chkAllocationExists(final CourseActivities searchBean);
	
	 Date getLastProgramScheduleDate(final CourseActivities obj);
	 Date getNextPhaseScheduleDate (final CourseActivities obj);
	 
	 CourseActivities csBuildAcpSchedule(final  CourseActivities vacp);
	 
	 CourseActivities lastCrsActyBuilt(final CourseActivities obj) ;
	 
	 Long noSessionsDone(final CourseActivities obj);
	 
	 Boolean chkAnyToBuild(final Long crsActyId,Long lastListSeq);
	 
	 Date getPriorScheduleDate(final CourseActivities obj) ;
	 
	 CourseActivities csAcpReschedule(final CourseActivities obj);
	 
	 List<ReferenceCodes> rgRemainingRecordGroup(final Integer crsActyId,final Integer  lastListSeq) ;

	 Integer updatecrsAct(final List<CourseActivities> data);
	 
	 Long getSessionNo(Long phaseInstanceId);
}
