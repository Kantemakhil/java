package net.syscon.s4.inst.institutionalactivities;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

/**
 * Interface OidpawliService
 */
public interface OidpawliService {
	List<AgencyLocations> rgEstablishmentRecordGroup(final String agyLocId);

	List<OffenderProgramProfiles> checkAssignConflict(final List<OffenderProgramProfiles> paramBean)
			throws ParseException;

	List<VPrisonActivities> rgServicesRecordGroup(final String ageLocId);

	List<OffenderProgramProfiles> waitlistCommit(final OffenderProgramProfilesCommitBean CommitBean);

	List<OffenderProgramProfiles> waitlistExecuteQuery(final OffenderProgramProfiles opf);

	List<CourseActivities> rgActDescRecordGroup(final String input);

	List<ReferenceCodes> rgDecisionRecordGroup(String systemMode);

	List<ReferenceCodes> rgPriorityRecordGroup();

	List<ReferenceCodes> rgReasonRecordGroup();

	Date getBookingDate(final Integer bookingId);

	Map<String, Object> checkWaitList2(final OffenderProgramProfiles searchRecord);

	OffenderProgramProfiles getCourseActivity(OffenderProgramProfiles offProfiles);

	Long ocdxprogOffPrgrefId();

	Map<String, Object> chkAllocated(List<OffenderProgramProfiles> searchRecord);

	Map<String, Object> backdateAttendances(OffenderProgramProfiles searchRecord);

	Long futureDays(OffenderProgramProfiles searchBean);

	List<OffenderProgramProfiles> checkNonAssociations(List<OffenderProgramProfiles> searchRecord, String userName);

}
