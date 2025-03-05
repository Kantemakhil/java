package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.CourseActivitiesT3Repository;
import net.syscon.s4.triggers.CourseActivitiesT3Service;
/*
============================================================
   Below comments are copied from COURSE_ACTIVITIES_T3 Trigger
============================================================
  MODIFICATION HISTORY
   Person       Date       version       Comments
-----------   ---------     -----------  -------------------------------
   Neil       20-JUN-2006   2.3          Changed for Accredited programmes
   Patrick    12-JAN-2006   2.1          Changed course_activity_class to course_activity_type
   Patrick    12-JAN-2006   2.0          Intial version
*/
@Service
public class CourseActivitiesT3ServiceImpl implements CourseActivitiesT3Service {

	@Autowired
	private CourseActivitiesT3Repository courseActivitiesT3Repository;

	private static final String COURSE = "COURSE";
	@Override
	public void courseActivitiesT3Trigger(final String courseClass, final Long programId, Long crsActyId,String username) {

		if (COURSE.equals(courseClass)) {
			courseActivitiesT3Repository.insertCourseActivityProfiles(programId, crsActyId,username);
		}
	}

}
