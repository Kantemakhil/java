package net.syscon.s4.cm.programsservices.maintenance;
import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
  
public interface OimworkrService  {
	List<CourseActivities> CheckDuplicateCodes(CourseActivities paramBean)  ;

	List<CourseScheduleRules> DeleteCrsActy(CourseScheduleRules paramBean)  ;

	List<VAddresses> rgProjectLocationRecordGroup(Integer providerId) ;

	List<AgencyLocations> rgAgencyLocationRecordGroup( String caseLoadId) ;

	VAddresses GetDefaultLocation(VAddresses paramBean)  ;

	List<ProgramServices> crsActyPostQuery(ProgramServices paramBean)  ;

	Integer crsActyCommit(CourseActivitiesCommitBean CommitBean) ;

	ProgramServices CallOcumpvav(ProgramServices paramBean)  ;

	List<CourseActivities> crsActyExecuteQuery(CourseActivities objCourseActivities) ;

	List<ProgramServices> rgProjectTypeRecordGroup();

	List<Corporates> rgProviderRecordGroup();
	
	List<VAddresses> crsActyToGEtSuiteOne(Long ownerId);
	 
  
}
