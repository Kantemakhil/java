package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Interface OcmspracService
 */
public interface OcmspracService {
	List<IntLocUsageLocations> rgInternalLocationRecordGroup(String agyLocId);

	List<AgencyLocations> rgAgyLocRecordGroup(String caseloadId);

	CourseActivities courseActivitiesCommit(CourseActivitiesCommitBean commitBean);

	List<ReferenceCodes> rgIepLevelRecordGroup();

	List<CourseActivities> courseActivitiesExecuteQuery(CourseActivities object);

	List<ProgramServices> rgPrisonActivityRecordGroup();

	String chkActyEndDate(CourseActivities bean);

}
