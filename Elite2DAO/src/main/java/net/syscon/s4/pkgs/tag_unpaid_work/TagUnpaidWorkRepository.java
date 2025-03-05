package net.syscon.s4.pkgs.tag_unpaid_work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

public interface TagUnpaidWorkRepository {

	Integer insertOffCourseAttendance(final VOffenderCourseEvents offAttCourse);

	CourseActivities getProjectAllocDetails(final Long crsActyId);

	Float getTotCreditHrsCur(final VOffenderCourseEvents offcourseAtt);

	Integer updateOffProgramprofiles(final VOffenderCourseEvents offcourseAtt, final Float hours,final String userName);

	List<CourseActivities> getDispActyDesc(final BigDecimal crsActyId, final Long offenderBookId);

	public Integer getCount(final Long pCrsActyId, final Date pScheduleEndDate);

	BigDecimal getOffSentTermHrs(final BigDecimal offenderBookId, final BigDecimal sentenceSeq);

	Integer getHolidayCount(final Date eventDate);

	Date getWeeklyDefStartDate(final BigDecimal offPrgrefId);

	Integer getCrsApptGrpId();

	List<Object[]> c1Select(final Long crsActyId, final Date scheduleEndDate);

	Integer offenderProgramProfilesUpdate(final Date pScheduleEndDate, final Long vOffPrgrefId, final String userName);

	Integer vOffenderCourseEventsDelete(final Date pScheduleEndDate, final Long vOffPrgrefId,String modifyUserId);
}