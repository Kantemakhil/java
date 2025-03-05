package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;

public interface OcsproinRepository {

	public List<Teams> rgTeamRecordGroup(String user);

	public List<CourseActivities> rgProjectsRecordGroup(String teamId);

	public List<CourseActivities> courseActExecuteQuery(CourseActivities searchBean);

	List<CourseActivities> rgProjectRgNoTeam();

	List<OffenderBookings> offenderExecQuery(Integer crsActyId);
	
	List<OffenderBookings> refExecuteQuery( CourseActivities searchBean);

	List<String> schWeekDays(Long offenderBookId, Integer crsActyId);


}
