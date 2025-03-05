package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;

public interface OimworkrRepository {

	OmsModules createFormGlobals(OmsModules paramBean);

	List<ProgramServices> rgProjectTypeRecordGroup();

	 List<VAddresses> rgProjectLocationRecordGroup(Integer providerId);

	Integer crsActyInsertCourseActivities(List<CourseActivities> lstCourseActivities);

	List<AgencyLocations> rgAgencyLocationRecordGroup(String caseLoadId);

	List<CourseActivities> checkDuplicateCodes(CourseActivities paramBean);

	ProgramServices callOcumpvav(ProgramServices paramBean);

	List<Corporates> rgProviderRecordGroup();

	List<CourseActivities> crsActyExecuteQuery(CourseActivities objCourseActivities);

	ProgramServices crsActyPostQuery(ProgramServices paramBean);

	List<VAddresses> getDefaultLocation(VAddresses paramBean);

	Integer crsActyUpdateCourseActivities(List<CourseActivities> lstCourseActivities);

	List<CourseScheduleRules> deleteCrsActy(CourseScheduleRules paramBean);

	Integer crsActyDeleteCourseActivities(List<CourseActivities> lstCourseActivities);

	Corporates crsActyPostQuery(Corporates paramBean);
	
	Boolean deleteRecordExist(Long crsActyId);
	
	Integer crsActyUpdateCourseActivitiesOne(CourseActivities bean);
	
	Long CrsActyIdNextVal();
	
	
	


	
	
	

}
