package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;

public interface VCoursePhaseOfferingsTuRepository {
	Long lCrsActyId();

	Integer courseActivitiesInsert(CourseActivities courseActivities);

	Integer courseActivitiesUpdate(CourseActivities courseActivities);

	Integer courseActivityAreasDelete(CourseActivityAreas courseActivityAreas);

	CourseActivities courseActivitiesDelete(CourseActivities courseActivities);

	Integer courseActivitiesUpdate2(CourseActivities courseActivities);

	Integer deleteCoursePhaseModules(VCoursePhaseOfferings newObj);

	VCoursePhaseOfferings getOldValuesVCoursePhaseOfferings(VCoursePhaseOfferings vCoursePhaseOfferings);
}
