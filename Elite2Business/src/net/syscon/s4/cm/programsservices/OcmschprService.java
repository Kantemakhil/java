package net.syscon.s4.cm.programsservices;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;
import net.syscon.s4.iwp.beans.OcmschprCommitBean;
import net.syscon.s4.iwp.beans.VAcpSchedules;
import net.syscon.s4.iwp.beans.VAcpSchedulesCommitBean;
/**
 * Interface OcmschprService 
 */
public interface OcmschprService  {
	
	List<CourseActivities> crsActExecuteQuery(final CourseActivities objCourseActivities) ;
	
	List<VAcpSchedules> vAcpSchedulesExecuteQuery(final VAcpSchedules objVAcpSchedules);
	
	 VAcpSchedules vAcpSchedulesInsertChecking(final Long crsActyId);
	
	List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules objCourseScheduleRules) ;
	
	 String getWeekday(final VAcpSchedules objSearchDao);
	 
	 CourseActivities vAcpSchedulesValidate(final VAcpSchedules vacp);

	 CourseActivities ocsmchprCommit(final OcmschprCommitBean searchBean);
	
	 Object vAcpSchedulesCommit(final VAcpSchedulesCommitBean CommitBean) ;
	
	Integer crsScheduleRulCommit(final CourseScheduleRulesCommitBean CommitBean) ;

	Integer vAcpSchedulesDelete(VAcpSchedules searchBean);

	Boolean chkAllocationExists(final CourseActivities searchBean);

	CourseActivities buildSchedule(final CourseActivities searchBean) throws Exception;
	
	CourseActivities defaultBuildParameters(final CourseActivities searchBean);

	CourseActivities reSchedule(final CourseActivities searchBean);
	
	Integer updateCrsActyChecksum(final Long programInstanceId, String userName);
	
	List<ReferenceCodes> rgRemainingRecordGroup(final String searchBean) ;

}
