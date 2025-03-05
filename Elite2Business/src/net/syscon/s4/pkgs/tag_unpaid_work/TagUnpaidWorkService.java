package net.syscon.s4.pkgs.tag_unpaid_work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

public interface TagUnpaidWorkService {
	Integer createOffCourseAttendance(final VOffenderCourseEvents offAttCourse, final String userName);

	Map<String, Object> getProjectAllocDetails(final Long crsActyId);

	void updateCredithrs(final VOffenderCourseEvents offcourseAtt, final String userName);

	VOffenderCourseEvents getAttendInfo(final OffenderCourseAttendance offCourAttends);

	List<CourseActivities> getDispActyDesc(final BigDecimal crsActyId, final Long offenderBookId);

	Integer allocExists(final Long pCrsActyId, final Date pScheduleEndDate);

	BigDecimal getOffSentTermHrs(final BigDecimal offenderBookId, final BigDecimal sentenceSeq);

	Integer getHolidayCount(final Date eventDate);

	Date getWeeklyDefStartDate(final BigDecimal offPrgrefId);

	Integer getCrsApptGrpId();

	Integer updateEnddate(final CourseActivities offenderId);
}