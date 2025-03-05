package net.syscon.s4.inst.institutionalactivities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;

/**
 * Interface OidpactiService
 */
public interface OidpactiService {
	OffenderProgramProfiles offProgProfCommit(OffenderProgramProfilesCommitBean commitBean);

	List<VOffenderCourseEvents> vOffCourseEvntsExecuteQuery(VOffenderCourseEvents object);

	List<OffenderProgramProfiles> offProgProfExecuteQuery(OffenderProgramProfiles object);

	List<VPrisonActivities> rgServicesRecordGroup(String agyLocId);

	OffenderProgramProfiles offenderProgramProfiles2Commit(OffenderProgramProfilesCommitBean commitBean);

	List<ReferenceCodes> rgPerformanceRecordGroup();

	List<ReferenceCodes> rgEndReasonRecordGroup();

	List<ReferenceCodes> rgAttendenceRecordGroup(String pshowoutcome);

	List<AgencyLocations> rgEstablishmentRecordGroup(String caseloadId);

	List<OffenderProgramProfiles> chkActiveIaAllocation(OffenderProgramProfiles paramBean);

	List<VPrisonActivities> lovServices2RecordGroup();

	Integer vOffCourseEvntsCommit(VOffenderCourseEventsCommitBean commitBean);

	List<ReferenceCodes> rgDecisionRecordGroup(String systemMode);

	List<ReferenceCodes> rgPriorityRecordGroup();

	List<ReferenceCodes> pgPsRejRsnRecordGroup();

	List<VPrisonActivities> getServices();

	OffenderProgramProfiles getCourseActivity(OffenderProgramProfiles bean);

	Map<String, Object> checkWaitList2(OffenderProgramProfiles searchRecord);

	ProgramsNonAssocTmp checkConflict(OffenderProgramProfiles objPrograms);

	List<OffenderProgramProfiles> offenderProgramProfileswaitExecuteQuery(OffenderProgramProfiles searchBean);

	Integer courseVacancy(OffenderProgramProfiles object);

	Integer cntAsnCur(OffenderProgramProfiles object);

	OffenderProgramProfiles assignCommit(OffenderProgramProfiles objPrograms);

	String getProfileValue();

	Date getAdmissionDate(OffenderProgramProfiles objPrograms);

	Date getBookingDate(VHeaderBlock objPrograms);

	String checkNonAssociations(OffenderProgramProfiles objPrograms);

}
