package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.common.beans.CourseActivityAreasCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;

/**
 * Interface OcmssvasService 
 */
public interface OcmssvasService  {
	CourseActivityAreas cActACommit(CourseActivityAreasCommitBean commitBean);

	List<ReferenceCodes> rgAreaClassRecordGroup() ;

	List<Areas> rgAreaRegionRecordGroup(String caseloadType , String areaClass) ;

	List<CourseActivityAreas> cActAExecuteQuery(CourseActivityAreas objCourseActivityAreas) ;

}
