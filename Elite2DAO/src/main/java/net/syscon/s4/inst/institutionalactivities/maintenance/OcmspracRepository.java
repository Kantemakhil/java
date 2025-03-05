package net.syscon.s4.inst.institutionalactivities.maintenance;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
/**
 * Interface OcmspracRepository
 */
public interface OcmspracRepository {
	List<IntLocUsageLocations> rgInternalLocationRecordGroup(final String agyLocId) ;

	List<AgencyLocations> rgAgyLocRecordGroup(String caseloadId) ;

	Integer courseActivitiesInsertCourseActivities(List<CourseActivities> lstCourseActivities) ;

	List<ReferenceCodes> rgIepLevelRecordGroup() ;

	List<CourseActivities> courseActivitiesExecuteQuery(CourseActivities objCourseActivities) ;

	List<ProgramServices> rgPrisonActivityRecordGroup() ;

	Integer courseActivitiesUpdateCourseActivities(List<CourseActivities> lstCourseActivities) ;

	Integer courseActivitiesDeleteCourseActivities(List<CourseActivities> lstCourseActivities) ;

	String chkActyEndDate(CourseActivities bean);

	Integer checkCodeExists(List<String> agyLocId, List<String> code, List<String> caseloadId,
			List<String> caseloadType);

	Integer checkCodeExistsonUpdate(List<String> agyLocId, List<String> code, List<String> caseloadId,
			List<String> caseloadType, List<String> rowId);

	Integer deleteCourseActivityAreas(CourseActivities bean);

	Integer updateCourseSchedules(CourseActivities updBean);

	Integer removeCourseSchedules(CourseActivities updBean);

	Long getcrsActyId();

	Integer okToDeleteRecord(Long crsActyId);

	Integer getChildCount(String tableName, String columnName, long crsactyId);

	List<TableColumnNameBean> okToDelRecord();

}
