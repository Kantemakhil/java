package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;

/**
 * Interface OcmssvasRepository
 */
public interface OcmssvasRepository {

	CourseActivityAreas cActAInsertCourseActivityAreas(List<CourseActivityAreas> lstCourseActivityAreas) ;

	List<ReferenceCodes> rgAreaClassRecordGroup() ;

	List<Areas> rgAreaRegionRecordGroup(String caseloadType , String areaClass) ;

	List<CourseActivityAreas> cActAExecuteQuery(CourseActivityAreas objCourseActivityAreas) ;
	
	String rgAreaClassRecordGroup(String areaType , String areaCod );

	CourseActivityAreas cActADeleteCourseActivityAreas(List<CourseActivityAreas> lstCourseActivityAreas);

}
