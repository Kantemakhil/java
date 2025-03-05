package net.syscon.s4.pkgs.tag_prison_activities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

public interface TagPrisonActivitiesRepository {
	Integer intIdCur(final Long crsActyId);

	List<CourseSchedules> getCourseScheculesDetails(final Long crsActyId, final Date offenderStartDate);

	Integer getEventId();

	Integer insertOffenderCourseAttendance(final OffenderCourseAttendance offCAttendance);

	int cursorC1(final OffenderProgramProfiles offPrgProfiles);

	int cursorC2(final OffenderProgramProfiles offPrgProfiles);

	int cursorC3(final OffenderProgramProfiles offPrgProfiles);

	Integer UpdateCourseSchedules(final Long crsActId, final Date scheduleEndDate, final String userName);

	List<OffenderProgramProfiles> cLock(final BigDecimal offenderBookId, final Date date);

	void updateOffPrgPro(final Long offPrgrefId, final String userName);

	Integer removeCourseSchedulesDelete(final List<CourseSchedules> list);

	List<OffenderProgramProfiles> executeQueryoffenderProgramStatus(final BigDecimal offenderBookId);

	Integer offProgProfUpdateOne(final Date pDate, final String pEndReason, final String userName);

	Integer offProgProfUpdateSecond(final Date pDate, final String pEndReason, final String userName);

	String getAgyLocIdDescription(final String agyLocId);

	String getProgramIdDescription(final Long programId);

	String getOffenderEndReasonDescription(final String offenderEndReason);

	String dispActyDesc(final Long pCrsActyId);

	Integer getCapacity(final Long pCrsActyId);

	Integer getCount(final Long pCrsActyId);

	String getDescCode(final String pDomain, final String pRefCode);

	String chkActyEndDate(final Long crsActyId, final Date scheduleEnddate);

	Long getCrsActyId();

	Long chkwaitList(final OffenderProgramProfiles bean);

	Date getAdmissionDate(final Long offenderBookId);

	Date getBookingDate(final Long offenderBookId);

	Long chkAllocated(final Long pCrsActyId,final Long pOffenderBookId,final Date pOffenderStartDate);

	String chkendDate(final Long crsActyId, final Date scheduleEnddate);

	List<CourseActivities> getCourseActivity(final String activity, final Long programId, final String agyLocId);

	String getServices(final Long pProgramId);
	
	Integer bulkUpdate(OffenderProgramProfiles inputObj);

}