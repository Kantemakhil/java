package net.syscon.s4.triggers;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfiles;

public interface CourseActivitiesT3Repository {

	ProgramServicesProfiles getProgServProf(final Long lvProgramId);

	Integer insertCourseActivityProfiles(final Long programId, final Long crsActyId,String username);
}
