package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;

public interface OcsproinService {

	List<CourseActivities> rgProjectsRecordGroup(String teamId);

	List<CourseActivities> courseActExecuteQuery(CourseActivities searchBean);

	List<Teams> rgTeamRecordGroup(String user);

	List<CourseActivities> rgProjectsRgNoTeam();

	List<OffenderBookings> offenderExecQuery(Integer crsActyId);
	
	List<OffenderBookings> refExecuteQuery( CourseActivities searchBean);

}
