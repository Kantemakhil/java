package net.syscon.s4.pkgs.tag_prison_activities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.im.beans.OffenderProgramProfiles;

public interface TagPrisonActivitiesService {

	Integer backdateAttendances(final OffenderProgramProfiles bean);

	Map<String, Object> ChkWaitlist2(final OffenderProgramProfiles offPrgProfiles);

	Integer UpdateCourseSchedules(final Long crsActId, final Date scheduleEndDate, final String userName);

	void suspendAllocations(final BigDecimal offenderBookId, final Date date, final String userName);

	void removeCourseSchedules(final Long crsActyId, final Date scheduleEndDate,String modifyUserId);

	void endWaitlistAndAllocations(final BigDecimal offenderBookId, final Date pDate, final String pEndReason,
			final String userName);

	List<OffenderProgramProfiles> getAgyLocIdDescription(final OffenderProgramProfiles paramBean);

	Map<String, Object> displayWaitlistDetails(final OffenderProgramProfiles inputObj);

	String dispActyDesc(final Long pCrsActyId);

	Integer courseVacancy(final Long pCrsActyId);

	String chkActyEndDate(final Long crsActyId, final Date scheduleEnddate);

	Long getCrsActyId();

	Long chkwaitList(final OffenderProgramProfiles bean);

	Date getAdmissionDate(final Long offenderBookId);

	Date getBookingDate(final Long offenderBookId);

	Long chkAllocated(final Long pcrsActId,final Long pOffenderBookId,final Date offStartDate);

	String chkendDate(final Long crsActyId, final Date scheduleEnddate);

	OffenderProgramProfiles getCourseActivity(final OffenderProgramProfiles paramBean);

	String getServices(final Long pProgramId);
	
	Map<String,Object> populateActivity(OffenderProgramProfiles bean);
	
	Integer bulkUpdate(OffenderProgramProfiles inputObj);
}